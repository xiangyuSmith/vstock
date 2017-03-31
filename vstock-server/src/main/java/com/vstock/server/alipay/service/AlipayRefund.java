package com.vstock.server.alipay.service;

import com.vstock.server.alipay.config.AlipayConfig;
import com.vstock.server.alipay.util.UtilDate;
import com.vstock.server.alipay.util.httpClient.HttpProtocolHandler;
import com.vstock.server.alipay.util.httpClient.HttpRequest;
import com.vstock.server.alipay.util.httpClient.HttpResponse;
import com.vstock.server.alipay.util.httpClient.HttpResultType;
import org.apache.commons.httpclient.NameValuePair;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝退款方法
 */
public class AlipayRefund {

    private static int i = 100;

    /**
     * 支付宝有密退款借口参数封装
     * @param batch_num      总退款单数
     * @param detail_data     退款原因
     * @return
     */
    public static Map<String, String> refund(String batch_num, String detail_data, String url){
        Map<String, String> sParaTemp = new HashMap<String, String>();
//        String alipayRefund = "https://mapi.alipay.com/gateway.do";
        sParaTemp.put("service","refund_fastpay_by_platform_pwd");
        sParaTemp.put("partner",AlipayConfig.partner);
        sParaTemp.put("_input_charset",AlipayConfig.input_charset);
        sParaTemp.put("sign_type",AlipayConfig.sign_type);
        sParaTemp.put("sign",AlipayConfig.private_key);
        sParaTemp.put("notify_url","http://116.228.89.158:1235/resfund/returnRefund"+url);
        sParaTemp.put("seller_email",AlipayConfig.seller_email);
        sParaTemp.put("refund_date",UtilDate.getDateFormatter());
        sParaTemp.put("batch_no",UtilDate.getDate()+UtilDate.getThree()+i);
        i++;
        if (i == 999999999){
            i=100;
        }
        sParaTemp.put("batch_num",batch_num);
        sParaTemp.put("detail_data",detail_data);
        return sParaTemp;
    }
}
