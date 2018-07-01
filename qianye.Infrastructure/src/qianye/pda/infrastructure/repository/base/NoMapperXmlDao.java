package qianye.pda.infrastructure.repository.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import qianye.core.domain.enums.DataSourceEnvironment;
import utils.LogUtils;



public class NoMapperXmlDao {
	
	public synchronized  <T> T  getDaoWithXml(Class<T> clazz,String dbName,boolean commit){
		if (dbName==null) {
			dbName=DataSourceEnvironment.development.name();
		}
		SqlSessionFactory sessionFactory=DataSourceSqlSessionFactory.getSqlSessionFactory(dbName);
		SqlSession sqlSession = sessionFactory.openSession();
		T t =sqlSession.getMapper(clazz);
		return new MapperProxy<T>(t, sqlSession,commit).getInstance();
	}
	
	public <T> T getDaoWithXml(Class<T> clazz,String dbName){
		return getDaoWithXml(clazz, dbName, false);
	}
	
	public <T> T getDaoWithXml(Class<T> clazz,boolean commint){
		return getDaoWithXml(clazz, DataSourceEnvironment.development.name(),commint);
	}
	
	public <T> T getDaoWithXml(Class<T> clazz){
		return getDaoWithXml(clazz, DataSourceEnvironment.development.name(),false);
	}
	
	/**
	 * 提供数据源参数，不提供 提交 参数（适用于查询操作）
	 * @param clazz
	 * @param dbName
	 * @return
	 */
	public <T> T getDao(Class<T> clazz,String dbName){
		return getDao(clazz, dbName, false);
	}
	
	/**
	 * 提供数据源参数，提供 提交 参数（适用于增删改查）
	 * @param clazz
	 * @param dbName
	 * @param commit
	 * @return
	 */
	public synchronized  <T> T  getDao(Class<T> clazz,String dbName,boolean commit){
		if (dbName==null) {
			dbName=DataSourceEnvironment.development.name();
		}
		SqlSessionFactory sessionFactory=DataSourceSqlSessionFactory.getSqlSessionFactory(dbName);
		if (!sessionFactory.getConfiguration().hasMapper(clazz)) {
			sessionFactory.getConfiguration().addMapper(clazz);
		}
		SqlSession sqlSession = sessionFactory.openSession();
		T t =sqlSession.getMapper(clazz);
		return new MapperProxy<T>(t, sqlSession,commit).getInstance();
	}
	
	/**
	 * 不提供数据源参数，提供 提交 参数（适用于默认PDA数据源的增删改查操作）
	 * @param clazz
	 * @param commint
	 * @return
	 */
	public <T> T getDao(Class<T> clazz,boolean commint){
		return getDao(clazz, DataSourceEnvironment.development.name(),commint);
	}
	
	/**
	 * 不提供数据源参数，不提供 提交 参数
	 * @param clazz
	 * @return
	 */
	public <T> T getDao(Class<T> clazz){
		return getDao(clazz, DataSourceEnvironment.development.name(),false);
	}
	
	
    private class MapperProxy<T> implements InvocationHandler {  
        private T mapper;  
        private SqlSession sqlSession;  
        private boolean commit;
          
        private MapperProxy(T mapper, SqlSession sqlSession,boolean commit) {  
            this.mapper = mapper;  
            this.sqlSession = sqlSession;  
            this.commit=commit;
        }  
          
        @SuppressWarnings("unchecked")
		private T getInstance() {  
            return (T) Proxy.newProxyInstance(mapper.getClass().getClassLoader(),  
                    mapper.getClass().getInterfaces(), new MapperProxy<T>(mapper, sqlSession,commit));  
        }  
  
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {  
            Object object = null;  
            try {  
                object = method.invoke(mapper, args);
                if (commit) {
                	sqlSession.commit();
				}
            } catch(Exception e) {  
            	LogUtils.error(e);
                e.printStackTrace();  
            } finally {  
                sqlSession.close();  
            }  
            return object;  
        }  
    }
}
