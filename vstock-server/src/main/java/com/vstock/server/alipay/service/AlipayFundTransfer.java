package com.vstock.server.alipay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.vstock.server.alipay.config.AlipayConfig;

public class AlipayFundTransfer {

    /**
     * 支付宝转账接口
     * @param out_biz_no //订单号
     * @param payee_account  //转账账号
     * @param amount  //金额
     * @param remark  //备注
     * @param ext_param  //说明
     */
    public static void alipayfundServer(String out_biz_no,String payee_account,String amount,String remark,String ext_param){
        String url = "https://openapi.alipay.com/gateway.do";
        AlipayClient alipayClient = new DefaultAlipayClient(
                url,
                AlipayConfig.ALIPAY_APP_ID,  //APPID
                AlipayConfig.private_key,  //商户私钥
                "json",
                "GBK",
                AlipayConfig.alipay_app_public_key,  //应用的支付宝公钥
                "RSA"
        );
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent("{" +
                "    \"out_biz_no\":\""+out_biz_no+"\"," +
                "    \"payee_type\":\"ALIPAY_LOGONID\"," +
                "    \"payee_account\":\""+payee_account+"\"," +
                "    \"amount\":\""+amount+"\"," +
                "    \"remark\":\""+remark+"\"," +
                "    \"ext_param\":\"{\\\"order_title\\\":\\\""+ext_param+"\\\"}\"" +
                "  }");
        try {
            AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        }catch (Exception e){
            System.out.println("调用失败,原因:"+e.getMessage());
        }
    }

    //测试
    public static void main(String[] args) throws AlipayApiException {
        alipayfundServer("STXRN1484726872000101","562603138@qq.com","0.1","转账备注","转账说明");
    }
}
