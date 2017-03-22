package com.vstock.server.express;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.HashMap;
import java.util.Map;

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
    public static Map<Object,String> getexpress(String expressName, String expressNum){
        HttpClient client = new org.apache.commons.httpclient.HttpClient();
        String Url = "https://m.kuaidi100.com/query"+"?type="+expressName+"&postid="+expressNum;
        Map<Object,String> param = new HashMap<Object,String>();
        PostMethod method = new PostMethod(Url);
        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");
        try {
            client.executeMethod(method);
            String SubmitResult =method.getResponseBodyAsString();
            JSONObject jsonObject = JSONObject.parseObject(SubmitResult);
            Object data = jsonObject.get("data");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return param;
    }
}
