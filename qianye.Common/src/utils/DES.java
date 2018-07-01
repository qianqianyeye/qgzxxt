package utils;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import teleware.base.Base64;

/**
 * DES算法加解密的类
 * 
 * 
 * @version $Revision: 1.0 $Date: 2011/03/12 $
 * 
 *          $1.0 完成基本的操作<br>
 */
public class DES {
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
		String decodedString = "";
		try {
			DESKeySpec keySpec = new DESKeySpec(keyStr.getBytes());// 设置密钥参数
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
			Key key = keyFactory.generateSecret(keySpec);// 得到密钥对象
			Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
			deCipher.init(Cipher.DECRYPT_MODE, key, DES.IV);// 设置工作模式为加密模式，给出密钥和向量
			byte[] pasByte = deCipher.doFinal(Base64.decode(data.getBytes()));
			decodedString = new String(pasByte, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decodedString;
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
		String encodedString = "";
		try {
			DESKeySpec keySpec = new DESKeySpec(keyStr.getBytes());// 设置密钥参数
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
			Key key = keyFactory.generateSecret(keySpec);// 得到密钥对象
			Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
			enCipher.init(Cipher.ENCRYPT_MODE, key, DES.IV);// 设置工作模式为加密模式，给出密钥和向量
			byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
			encodedString = new String(Base64.encode(pasByte));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodedString;
	}
}
