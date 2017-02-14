package com.vstock.server.ihuyi;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;

public class Sendsms {

    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

    /**
     * 短信接口
     * @param mobile  电话号码
     * @param account  账号
     * @param key   密钥
     * @param content  短信内容
     * @return
     */
    public static boolean sendHuyi(String mobile,String account,String key,String content){
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");
        NameValuePair[] data = {
                //提交短信
                new NameValuePair("account", account),
                new NameValuePair("password", key), //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
                new NameValuePair("mobile", mobile),
                new NameValuePair("content", content),
        };
        method.setRequestBody(data);
        try {
            client.executeMethod(method);
            String SubmitResult =method.getResponseBodyAsString();
            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();
            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");
            System.out.println(code);
            System.out.println(msg);
            System.out.println(smsid);
            if("2".equals(code)){
                return true;
            }
            return false;
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
