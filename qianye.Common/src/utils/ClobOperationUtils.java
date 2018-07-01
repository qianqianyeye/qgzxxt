package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

import oracle.sql.CLOB;

/**
 * Clob字段操作工具类
 * 
 * @author Administrator
 * 
 */
public class ClobOperationUtils {
	/**
	 * 读取Clob字段的值将其转换为String类型的值
	 * @param clob
	 * @return
	 */
	public static String clobToString(CLOB clob) {
		String clobStr = null;
		BufferedReader br = null;
		try {
			if(clob != null){
				br = new BufferedReader(clob.getCharacterStream());
				StringBuffer sb = new StringBuffer();
				String temp;
				while ((temp = br.readLine()) != null) {
					sb.append(temp);
				}
				clobStr = sb.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clobStr;
	}
	
	/**
	 * 读取Clob字段的值将其转换为String类型的值有进行换行操作
	 * @param clob
	 * @return
	 */
	public static String clobToStringWrap(CLOB clob) {
		String clobStr = null;
		BufferedReader br = null;
		try {
			if(clob != null){
				br = new BufferedReader(clob.getCharacterStream());
				StringBuffer sb = new StringBuffer();
				String temp;
				while ((temp = br.readLine()) != null) {
//					temp = HtmlHelperUtils.stripHtml(temp);
					sb.append(temp+"<br>");
				}
				clobStr = sb.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return clobStr;
	}
	
	
	 /** 
     * 将Clob转成String ,静态方法 
     *  
     * @param clob 
     *            字段 
     * @return 内容字串，如果出现错误，返回 null 
     */  
    public static String clobToStringMethod(Clob clob) {  
        if (clob == null)  
            return null;  
        StringBuffer sb = new StringBuffer();  
        Reader clobStream = null;  
        try {  
            clobStream = clob.getCharacterStream();  
            char[] b = new char[60000];// 每次获取60K  
            int i = 0;  
            while ((i = clobStream.read(b)) != -1) {  
                sb.append(b, 0, i);  
            }  
        } catch (Exception ex) {  
            sb = null;  
        } finally {  
            try {  
                if (clobStream != null) {  
                    clobStream.close();  
                }  
            } catch (Exception e) {  
            }  
        }  
        if (sb == null) {
        	 return null;  
        } 
        else {
        	return sb.toString();  
        }
    }  
}
