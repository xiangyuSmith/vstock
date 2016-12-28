package com.vstock.server.hydsk;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 问题描述： 可能由于公安部采集等问题，部分返回的base64编码图片没有办法直接解析显示 解决方案:
 * 可以先将公安部返回的base64信息以IO流输出文件，在读取出生成文件的base64编码，即可解析
 * @author Administrator
 */
public class JPGToBase64 {
	private static BASE64Encoder encoder = new BASE64Encoder();
	private static BASE64Decoder decoder = new BASE64Decoder();
	private static final String URL = "d://";
	private static final String FILE_NAME = "wjp.png";

	public static void main(String[] args) {
		String base64 = "base64String";//<--将返回的base64字符串作为参数
		base64StringToImage(base64);
		System.out.println(getImageBinary());
	}

	/**
	 * 编码图片到Base64
	 * @return
	 */
	static String getImageBinary() {
		File f = new File(URL+FILE_NAME);
		BufferedImage bi;
		try {
			bi = ImageIO.read(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			byte[] bytes = baos.toByteArray();
			System.out.println(bytes.length);
			return encoder.encodeBuffer(bytes).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 编码图片到Base64
	 * @return
	 */
	public static String getImageBinary(String url) {
		File f = new File(url);
		BufferedImage bi;
		try {
			bi = ImageIO.read(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			byte[] bytes = baos.toByteArray();
			System.out.println(bytes.length);
			return encoder.encodeBuffer(bytes).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解码Base64成为图片
	 * @param base64String
	 */
	static void base64StringToImage(String base64String) {
		try {
			byte[] bytes1 = decoder.decodeBuffer(base64String);
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
			BufferedImage bi1 = ImageIO.read(bais);
			File w2 = new File(URL+FILE_NAME);
			ImageIO.write(bi1, "jpg", w2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
