package com.vstock.server.alipay.service;

import com.vstock.server.alipay.config.AlipayConfig;
import com.vstock.server.alipay.util.AlipaySubmit;

import java.util.HashMap;
import java.util.Map;

public class alipaylogin {

    public static Map<String, String> alipayLogin(){
        Map<String, String> param = new HashMap<String, String>();
        String service = AlipayConfig.loginservice;
        String partner = AlipayConfig.partner;
        String _input_charset = AlipayConfig.input_charset;
        String sign_type = AlipayConfig.sign_type;
        String return_url = AlipayConfig.login_return_url;
        String target_service = AlipayConfig.target_service;
        param.put("service",service);
        param.put("partner",partner);
        param.put("_input_charset",_input_charset);
        param.put("sign_type",sign_type);
        param.put("return_url",return_url);
        param.put("target_service",target_service);
        return param;
    }
}
