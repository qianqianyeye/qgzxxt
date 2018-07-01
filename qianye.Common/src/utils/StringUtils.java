package utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 对应字符串操作的工具类
 * @author Administrator
 *
 */
public class StringUtils {
	  /**
	   * 去除除数字以外的字符
	   * @param op
	   * @return
	   */
	  public static String deleteBesideNumber(String op){
		  String regEx = "[^0-9.]";
	      Pattern p = Pattern.compile(regEx);
	      Matcher matcher = p.matcher(op);
	      return matcher.replaceAll("").trim();
      }
	  
	  /**
	   * 过滤特殊字符
	   * @param str
	   * @return
	   * @throws PatternSyntaxException
	   */
	  public static String StringFilter(String str) throws PatternSyntaxException{  
          // 只允许字母和数字       
          // String   regEx  =  "[^a-zA-Z0-9]";                     
         // 清除掉所有特殊字符  
		  String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
	   	  Pattern p = Pattern.compile(regEx);     
	   	  Matcher m = p.matcher(str);     
	   	  return m.replaceAll("").trim();     
	  }     
	  
	  /**
	   * 将字节数组转换为字符串
	   * @param srtbyte
	   * @return
	   */
	  public static String binaryToString(byte[] srtbyte){
		  String result = "";
		  try {
			  result = new String(srtbyte,"UTF-8");
		  } catch (UnsupportedEncodingException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		  }
		  return result;
	  }
	  
	  /**
	   * DES解码
	   * @param decryptString 待解密的字符串
	   * @param decryptKey 解密密钥,要求为8位,和加密密钥相同
	   * @return 解密成功返回解密后的字符串,失败返源串
	   */
	  public static String DESDecrypt(String decryptString, String decryptKey){
		 return DESUtility.decode(decryptString, decryptKey);
	  }
	  
	  /**
	   * DES加密字符串
	   * @param encryptString 待加密的字符串
	   * @param encryptKey 加密密钥,要求为8位
	   * @return 加密成功返回加密后的字符串，失败返回源串
	   */
	  public static  String DESEncrypt(String encryptString, String encryptKey){
		  return DESUtility.encode(encryptString, encryptKey);
	  }
}
