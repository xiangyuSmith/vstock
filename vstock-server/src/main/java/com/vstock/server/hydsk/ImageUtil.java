package com.vstock.server.hydsk;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtil {

	/**
	 * 图片压缩 不失真
	 * 
	 * @param imgsrc  原图片路径
	 * @param imgdist   压缩后保存路径
	 */
	public static void reduceImg(String imgsrc, String imgdist) {
		try {
			File srcfile = new File(imgsrc);
			if (!srcfile.exists()) {
				return;
			}
			Image src = javax.imageio.ImageIO.read(srcfile);
			int imgWidth = src.getWidth(null);
			int imgHeight = src.getHeight(null);
			int widthdist = imgWidth / 5;
			int heightdist = imgHeight / 5;
			BufferedImage tag = new BufferedImage((int) widthdist,(int) heightdist, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,Image.SCALE_SMOOTH), 0, 0, null);
			FileOutputStream out = new FileOutputStream(imgdist);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag);
			out.close();
			tag.flush();
			src.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String imgsrc = "F:\\1.png";
			String imgdist = "F:\\1_yasuohou.png";
			reduceImg(imgsrc, imgdist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
