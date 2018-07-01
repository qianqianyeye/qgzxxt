package utils;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.http.entity.StringEntity;

import teleware.base.Base64;

/**
 * DES算法加解密的类
 * 
 * 
 * @version $Revision: 1.0 $Date: 2011/03/12 $
 * 
 *          $1.0 完成基本的操作<br>
 */
public class DESUtility {

	// DES加密向量，与WM版本的相同
	private final static AlgorithmParameterSpec IV = new IvParameterSpec(
			new byte[] { (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 120,
					(byte) 0x90, (byte) 0xab, (byte) 0xcd, (byte) 0xef });

	/**
	 * decode 解密
	 * 
	 * @param data
	 *            待解密的数据
	 * @param keyStr
	 *            密钥
	 * @return
	 * @return String
	 */
	public static String decode(String data, String keyStr) {
		String result = "";
		try {
			result = teleware.base.DES.decode(data, keyStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * encode 加密
	 * 
	 * @param data
	 *            待加密的数据
	 * @param keyStr
	 *            密钥
	 * @return
	 * @return String
	 */
	public static String encode(String data, String keyStr) {
		String result = "";
		try {
			result = teleware.base.DES.encode(data, keyStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 解密内容
	 * @param data
	 * @param keyStr
	 * @return
	 */
	public static String decodeLogin(String data, String keyStr){
		String decodedString = "";
		try {
			DESKeySpec keySpec = new DESKeySpec(keyStr.getBytes());// 设置密钥参数
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
			Key key = keyFactory.generateSecret(keySpec);// 得到密钥对象
			Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
			deCipher.init(Cipher.DECRYPT_MODE, key, DESUtility.IV);// 设置工作模式为加密模式，给出密钥和向量
			if(data != null && !"".equals(data)){
				byte[] pasByte = deCipher.doFinal(Base64.decode(data.getBytes()));
				decodedString = new String(pasByte, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.error(e, e.getMessage()+"解密出错");
			decodedString = data;
		}
		return decodedString;
	} 
	/**
	 * 自定义加密内容
	 * @param data
	 * @param keyStr
	 * @return
	 */
	public static String encodeLogin(String data, String keyStr){
		String encodedString = "";
		try {
			DESKeySpec keySpec = new DESKeySpec(keyStr.getBytes());// 设置密钥参数
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
			Key key = keyFactory.generateSecret(keySpec);// 得到密钥对象
			Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
			enCipher.init(Cipher.ENCRYPT_MODE, key, DESUtility.IV);// 设置工作模式为加密模式，给出密钥和向量
			byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
			encodedString = new String(Base64.encode(pasByte));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodedString;
	} 
	
	
	/**
	 * 构造程序登陆时候用的串
	 * 
	 * @param userName
	 *            登陆的用户名
	 * @param userPwd
	 *            登陆的密码
	 * @return
	 * 
	 */
	public static String createLoginSTUP(String userName, String userPwd) {
		String stup = userName + "||" + userPwd + "||"
				+ new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		return DESUtility.encode(stup, "12345678");
	}
	
	
	/**
	 * 这里进行解密STUP的值  所返回的字符串是 用户名 和密码
	 * @param stup
	 * @return
	 */
	public static String[] decodeLoginStup(String stup){
		try {
			String dStup = decode(stup, "12345678");
			String[] result = dStup.split("\\|\\|");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	public static String[] decodeGovernMentLoginStup(String stup){
		try {
			String dStup = decode(stup, "government123");
			String[] result = dStup.split("\\|\\|");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	
	}
	
	
	public static String encodekey(String password){
		 String str = "";
         String str2 = "";
         String str3 = "";
         String str4 = "";
         str3 = "123456781234ffff";
         if (str.length() > 4)
         {
             str2 = str.substring(0, 4);
         }
         else
         {
             str2 = (str + "\0\0\0\0").substring(0, 4);
         }
         while (!str2.equals("") && str2 != null)
         {
           
             try {
            	byte[] buffer = new byte[0x10];
				byte[] bytes =new String(str2.getBytes(),"ascii").getBytes();
				 //int num = BcdToAsc(buffer, bytes, 8);
	             //num = key_encrypt_Asc(buffer, str3);
	             if (str.length() > 4)
	             {
	                 str = str.substring(4);
	                 if (str.length() > 4)
	                 {
	                     str2 = str.substring(0, 4);
	                 }
	                 else
	                 {
	                     str2 = (str + "\0\0\0\0").substring(0, 4);
	                 }
	             }
	             else
	             {
	                 str2 = "";
	             }
	             str4 = str4 + new String(buffer,"ascii").getBytes(); //Encoding.ASCII.GetString(buffer);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
         }
         return str4;
	}
	
	 /** 
     * 字符转ASC 
     *  
     * @param st 
     * @return 
     */  
    public static int getAsc(String st) {  
        byte[] gc = st.getBytes();  
        int ascNum = (int) gc[0];  
        return ascNum;  
    }  
	
	public static void main(String[] args) {
//		String code = encode("1", "12345678");
//		System.out.println("这里输出的密码："+code);
		String stu = createLoginSTUP("shenggm", "1");
		System.out.println("这里获取的串："+stu);
//		
//		//这里进行解密
//		String strSTU = decode(stu, "12345678");
//		System.out.println("这里获取的stu的值："+strSTU);
////		String dcode = decode("qvJLeTpGgzM=", "12345678");
////		System.out.println("这里进行解密："+dcode);
//		
//		System.out.println("加密："+encodekey("1"));
		//J9yS79iE+TGirrkQ9Ms2pw== 这个 是 解密 密钥
		
		//127DB2A06B4386DC 这个 获取 解密 注册码
		
		//E6D123FDD5557121 服务端 CPU 的id
//		System.out.println("获取的值: "+DESUtility.decode("TZVHalCRTGrpo97KTDFHjQ==", "12345678"));
//		System.out.println("获取的值: "+DESUtility.decode("DC141A57B8287319", "12345678"));
		
//		String data = DESUtility.encode("IsMobile", "12345678");
//		System.out.println("获取加密："+data);
//		
//		String test = DESUtility.decode("OSpCHitJ9WVJZOlw71Vf2g==", "12345678");
//		System.out.println("解密："+test);
		
//		String tests = DESUtility.decode("Gep/i/OdYRFMxVWLoAHDzkk6R/J+09K", "12345678");
//		System.out.println("解密："+tests);
		
//		String set = teleware.base.DES.decode("Gep/i/OdYRFMxVWLoAHDzkk6R/J+09K", "12345678");
		String set = teleware.base.DES.decode("Gep/i//OdYRFMxVWLoAHD+Njwh93CbFh", "12345678");
		System.out.println("解析 的值："+set);
//		DESUtility.encode("", DESUtility.decode("TZVHalCRTGrpo97KTDFHjQ==", "12345678")).getBytes();
	}
}
