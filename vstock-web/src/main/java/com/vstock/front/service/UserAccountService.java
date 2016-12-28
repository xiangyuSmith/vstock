package com.vstock.front.service;

import com.vstock.db.dao.IUserAccountDao;
import com.vstock.db.entity.UserAccount;
import com.vstock.ext.util.ToolDateTime;
import com.vstock.front.service.interfaces.IVstockConfigService;
import com.vstock.server.hydsk.Encrypt;
import com.vstock.server.hydsk.HttpUtil;
import com.vstock.server.hydsk.JPGToBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserAccountService {

    @Autowired
    IUserAccountDao userAccountDao;

    @Value("${projectPath}")
    String projectPath;

    public UserAccount findAccountByUid(String suid){
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(suid);
        List<UserAccount> userAccountList = findAll(userAccount);
        if(userAccountList.size() > 0){
            return userAccountList.get(0);
        }
        return null;
    }

    public List<UserAccount> findAll(UserAccount userAccount){
        return userAccountDao.findAll(userAccount);
    }


    public String httphyd(String pname,String pdocument_no,String imgUrl){
        String url = VstockConfigService.getConfig(IVstockConfigService.HYD_V_STOCK_API_URL);
        String query_company_id = VstockConfigService.getConfig(IVstockConfigService.HYD_V_STOCK_COMPANY_ID);
        String query_api_id = VstockConfigService.getConfig(IVstockConfigService.HYD_V_STOCK_API_ID);
        String query_company_orderid = VstockConfigService.getConfig(IVstockConfigService.HYD_V_STOCK_MERCHANT_KEY);
        String company_secret = VstockConfigService.getConfig(IVstockConfigService.HYD_V_STOCK_COMPANY_SECRET);
        String pphoto = JPGToBase64.getImageBinary("D://1.jpg");
        pphoto=pphoto.replaceAll("\r","").replaceAll("\n", "");
        String temp = query_company_id+"|"+query_api_id+"|"+pname+"|"+pdocument_no+"|"+pphoto+"|"+query_company_orderid+"|"+company_secret;
        String md5Sign = Encrypt.md5(temp);
        Map<String,String> maps=new HashMap<String,String>();
        maps.put("pname",pname);
        maps.put("pphoto",pphoto);
        maps.put("pdocno", pdocument_no);
        maps.put("rcomp_id", query_company_id);
        maps.put("rapi_id", query_api_id);
        maps.put("rcomp_orderid", query_company_orderid);
        maps.put("sign", md5Sign);
        //请求web service获取结果
        String result="";
        try {
            result = HttpUtil.callByPostStream(url,maps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }


    /**
     * 上传证件照片
     * @param multipartFile
     * @return
     */
    public String uploadFile(MultipartFile multipartFile, String uid){
        BufferedInputStream bis = null;
        BufferedImage bfimg = null;
        //存储文件路径
        String saveUrl = "";
        try {
            InputStream inputStream = multipartFile.getInputStream();
            bis = new BufferedInputStream(inputStream);
            bfimg = ImageIO.read(inputStream);
            saveUrl = "/upload/"+ ToolDateTime.format(ToolDateTime.getDate(),ToolDateTime.pattern_ym) + "/";
            String imgName = generateFileName(uid) + ".png";
            File file = new File(projectPath + saveUrl);
            // 判断文件夹是否创建，没有创建则创建新文件夹
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(file, imgName);
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
            ImageIO.write(bfimg, "png", file);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return saveUrl;
    }

    /**
     * 用日期和随机数格式化文件名避免冲突
     * @param fileName
     * @return
     */
    private String generateFileName(String fileName) {
        SimpleDateFormat sf = new SimpleDateFormat("yyMMddHHmmss");
        String formatDate = sf.format(new Date());
        String uuid = UUID.randomUUID().toString();
        return formatDate + uuid + "_" + fileName;
    }

}
