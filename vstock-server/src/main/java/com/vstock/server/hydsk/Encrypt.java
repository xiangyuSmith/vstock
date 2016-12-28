package com.vstock.server.hydsk;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密工具类
 * 
 * md5加密出来的长度是32位
 * 
 * sha加密出来的长度是40位
 * 
 * @author Varro
 * 
 */
public class Encrypt {

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// md5加密测试
		String md5_1 = md5("1111");
		String md5_2 = md5("马洋");
		System.out.println(md5_1 + "\n" + md5_2);
		
		String a="1212";
		String b="234";
		String c="张三";
		String d="34878879988545125";
		String temp = a+"|"+b+"|"+c+"|"+d;
		String md5Sign = Encrypt.md5(temp);
		System.out.println(md5Sign);
		
		//查询参数3DES加密
		String name="张三";
		String enc_key="12345678";
//		String threedes_en=threeDesEncrypt(enc_key, URLEncoder.encode(name,"UTF-8"));
//		System.out.println("加密后："+threedes_en);
//		System.out.println("解密后："+URLDecoder.decode(threeDesDecrypt(enc_key, threedes_en),"UTF-8"));
	}

	/**
	 * md5加密
	 * 
	 * @param inputText
	 * @return
	 */
	public static String md5(String inputText) {
		return encrypt(inputText, "md5");
	}


	/**
	 * md5
	 * 
	 * @param inputText
	 *            要加密的内容
	 * @param algorithmName
	 *            加密算法名称：md5或者sha-1，不区分大小写
	 * @return
	 */
	private static String encrypt(String inputText, String algorithmName) {
		if (inputText == null || "".equals(inputText.trim())) {
			throw new IllegalArgumentException("请输入要加密的内容");
		}
		if (algorithmName == null || "".equals(algorithmName.trim())) {
			algorithmName = "md5";
		}
		String encryptText = null;
		try {
			MessageDigest m = MessageDigest.getInstance(algorithmName);
			m.update(inputText.getBytes("UTF8"));
			byte s[] = m.digest();
			return hex(s);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encryptText;
	}

	/**
	 * 返回十六进制字符串
	 * 
	 * @param arr
	 * @return
	 */
	private static String hex(byte[] arr) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; ++i) {
			sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
					3));
		}
		return sb.toString();
	}

	/**
	 * 3DES加密
	 * 
	 * @param key
	 * @param src
	 * @return
	 */
	public static String threeDesEncrypt(String key, String src) {
		return byte2Hex(threeDesEncrypt(key.getBytes(),src.getBytes()));
	}

	/**
	 * 3DES解密
	 * 
	 * @param key
	 * @param src
	 * @return
	 */
	public static String threeDesDecrypt(String key, String src) {
		return new String(threeDesDecrypt(key.getBytes(),hex2Bytes(src)));
	}
	
	/**
	 * 3DES加密
	 * 
	 * @param keybyte
	 * @param src
	 * @return
	 */
	private static byte[] threeDesEncrypt(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			byte[] key = new byte[24];
			if (keybyte.length < key.length) {
				System.arraycopy(keybyte, 0, key, 0, keybyte.length);
			} else {
				System.arraycopy(keybyte, 0, key, 0, key.length);
			}
			SecretKey deskey = new SecretKeySpec(key, "DESede");
			// 加密
			Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return src;
	}

	/**
	 * 3DES解密
	 * 
	 * @param keybyte
	 *            加密密钥，长度为24字节
	 * @param src
	 *            加密后的缓冲区
	 * @return
	 */
	private static byte[] threeDesDecrypt(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			byte[] key = new byte[24];
			if (keybyte.length < key.length) {
				System.arraycopy(keybyte, 0, key, 0, keybyte.length);
			} else {
				System.arraycopy(keybyte, 0, key, 0, key.length);
			}
			SecretKey deskey = new SecretKeySpec(key, "DESede");
			// 解密
			Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return src;
	}

	/**
	 * 将byte[] 转换成字符串
	 * 
	 * @param
	 * @return
	 */
	private static String byte2Hex(byte[] srcBytes) {
		StringBuilder hexRetSB = new StringBuilder();
		for (byte b : srcBytes) {
			String hexString = Integer.toHexString(0x00ff & b);
			hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
		}
		return hexRetSB.toString();
	}
	
	/**
	 * 将16进制字符串转为转换成字符串
	 * 
	 * @param source
	 * @return
	 */
	private static byte[] hex2Bytes(String source) {
		byte[] sourceBytes = new byte[source.length() / 2];
		for (int i = 0; i < sourceBytes.length; i++) {
			sourceBytes[i] = (byte) Integer.parseInt(
					source.substring(i * 2, i * 2 + 2), 16);
		}
		return sourceBytes;
	}

}
