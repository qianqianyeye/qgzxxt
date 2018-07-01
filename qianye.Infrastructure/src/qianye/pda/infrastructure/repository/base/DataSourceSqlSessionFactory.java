package qianye.pda.infrastructure.repository.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import qianye.core.domain.enums.DataSourceEnvironment;



/**
 * 根据mybatis-config-pda.xml中配置的不同的environment创建对应的SqlSessionFactory 
 * @author Administrator
 *
 */
public class DataSourceSqlSessionFactory {
	private static final String CONFIGURATION_PATH = "mybatis-config-pda.xml";  
    
    private static final Map<String, SqlSessionFactory> SQLSESSIONFACTORYS   
        = new HashMap<String, SqlSessionFactory>();  
      
    /** 
     * 根据指定的DataSourceEnvironment获取对应的SqlSessionFactory 
     * @param environment 数据源environment 
     * @return SqlSessionFactory 
     */  
    public static SqlSessionFactory getSqlSessionFactory(DataSourceEnvironment environment) {  
          return getSqlSessionFactory(environment.name());
    }  
    
    public static SqlSessionFactory getSqlSessionFactory(String environment) {  
        
        SqlSessionFactory sqlSessionFactory = SQLSESSIONFACTORYS.get(environment);  
        if (sqlSessionFactory != null)  
            return sqlSessionFactory;  
        else {  
            InputStream inputStream = null;  
            try {  
                inputStream = Resources.getResourceAsStream(CONFIGURATION_PATH);  
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, environment);  
            } catch (IOException e) {  
            	e.printStackTrace();
            }  
            finally {  
                if(inputStream != null){
                	try {
						inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }  
            SQLSESSIONFACTORYS.put(environment, sqlSessionFactory);  
            return sqlSessionFactory;  
        }  
    }
    
    public static SqlSession getSqlSession(DataSourceEnvironment environment){
    	return getSqlSession(environment.name());
	}
    
    public static SqlSession getSqlSession(String environment){
		 //1、指定MyBaties配置文件  
      SqlSessionFactory sessionFactory = null; 
      SqlSession session = null;
	   //2、创建SqlSessionFactory()  
	   sessionFactory = getSqlSessionFactory(environment);  
	   session = sessionFactory.openSession();  
	   return session;
	}
      
}
