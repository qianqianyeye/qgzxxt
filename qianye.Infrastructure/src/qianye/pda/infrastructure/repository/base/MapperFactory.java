package qianye.pda.infrastructure.repository.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import qianye.core.domain.enums.DataSourceEnvironment;
import qianye.interfaces.dao.order.base.IRepository;
import utils.LogUtils;



public class MapperFactory {
	  public static <T> T createMapper(Class<? extends IRepository> clazz, DataSourceEnvironment environment) {  
		  SqlSession  sqlSession = null;
		  IRepository mapper = null;
		  try {
	        	SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(environment);  
		         sqlSession = sqlSessionFactory.openSession();  
		         mapper = sqlSession.getMapper(clazz); 
			} catch (Exception e) {
				LogUtils.error(e);
				e.printStackTrace();
			}
		  
	        return (T)MapperProxy.bind(mapper, sqlSession);  
	    }  
	      
	    /** 
	     * Mapper Proxy  
	     * executing mapper method and close sqlsession 
	     * @author boyce 
	     * @version 2014-4-9 
	     */  
	    private static class MapperProxy implements InvocationHandler {  
	        private IRepository mapper;  
	        private SqlSession sqlSession;  
	          
	        private MapperProxy(IRepository mapper, SqlSession sqlSession) {  
	            this.mapper = mapper;  
	            this.sqlSession = sqlSession;  
	        }  
	          
	        @SuppressWarnings("unchecked")  
	        private static IRepository bind(IRepository mapper, SqlSession sqlSession) {  
	            return (IRepository) Proxy.newProxyInstance(mapper.getClass().getClassLoader(),  
	                    mapper.getClass().getInterfaces(), new MapperProxy(mapper, sqlSession));  
	        }  
	  
	        /** 
	         * execute mapper method and finally close sqlSession 
	         */  
	        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {  
	            Object object = null;  
	            try {  
	                object = method.invoke(mapper, args);  
	            } catch(Exception e) { 
	            	LogUtils.error(e);
	                e.printStackTrace();  
	            } finally {  
	                sqlSession.close();  
	            }  
	            return object;  
	        }  
	    }  
	      
	    //Get a SqlSessionFactory of environment  
	    private static SqlSessionFactory getSqlSessionFactory(DataSourceEnvironment environment) {  
	        return DataSourceSqlSessionFactory.getSqlSessionFactory(environment);  
	    }  
	    
	    public static SqlSession getSqlSession(DataSourceEnvironment environment){
	    	return DataSourceSqlSessionFactory.getSqlSession(environment);
	    }
	    
	    public static SqlSession getSqlSession(String environment){
	    	return DataSourceSqlSessionFactory.getSqlSession(environment);
	    }
}
