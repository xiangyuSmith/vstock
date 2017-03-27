package com.vstock.server.express;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 快递公司物流信息借口获取
 */
public class ExpressLogistics {

    /**
     * 获取最新物流信息
     * @param expressName  快递公司名称全拼
     * @param expressNum   快递单号
     * @return
     */
    public static JSONObject getexpress(String expressName, String expressNum){
        HttpClient client = new org.apache.commons.httpclient.HttpClient();
        String Url = "https://m.kuaidi100.com/query"+"?type="+expressName+"&postid="+expressNum;
        JSONObject jsonObject = new JSONObject();
        PostMethod method = new PostMethod(Url);
        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");
        try {
            client.executeMethod(method);
            String SubmitResult =method.getResponseBodyAsString();
            jsonObject = JSONObject.parseObject(SubmitResult);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return jsonObject;
    }
}
