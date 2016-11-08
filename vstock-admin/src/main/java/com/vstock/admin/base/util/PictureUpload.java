package com.vstock.admin.base.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Ligang on 2016/8/9.
 */
public class PictureUpload {

    /**
     * 本地上传图片
     * @author Ligang
     * @Date 2016.08.09
     * @param multipartFile
     * @param type
     * @return
     */
    public static String localUploadImg(MultipartFile multipartFile,String imgName, int type){
        BufferedInputStream bis = null;
        BufferedImage bfimg = null;
        //存储文件路径
        String saveUrl = "";
       try {
           InputStream inputStream = multipartFile.getInputStream();
            bis = new BufferedInputStream(inputStream);
            bfimg = ImageIO.read(inputStream);
            String ursl = ConstUtil.getProperties().getProperty("projectPath");
            if(type == 1){
                //缩略图
                saveUrl = "/assets/shoesImg/small/";
            }else{
                //大图
                saveUrl = "/assets/shoesImg/";
            }

           imgName = imgName.substring(0,imgName.lastIndexOf("."));
           imgName = imgName + DateUtils.getCurrentTimeAs14String()+".jpg";
           File file = new File(ursl + saveUrl);
            // 判断文件夹是否创建，没有创建则创建新文件夹
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(file,imgName);
            saveUrl = saveUrl + imgName;
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file.getAbsolutePath()));
            // 创建输入流缓冲
            byte[] buffer = new byte[1024];
            // 读入字节数
            int num = -1;
            while (true) {
                num = bis.read(buffer);
                if (num == -1) {
                    // 读取结束
                    bos.flush();
                    break;
                }
                bos.flush();
                bos.write(buffer, 0, num);
            }
            // 关闭字节流
            bos.close();
            ImageIO.write(bfimg, "jpg", file);
        } catch (Exception e){
            System.out.print(e.getMessage());
        }
        return saveUrl;
    }
}
