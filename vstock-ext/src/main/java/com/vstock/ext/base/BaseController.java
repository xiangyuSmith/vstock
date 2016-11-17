package com.vstock.ext.base;

import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by xiangyu on 2016/11/8.
 */
public class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected Logger log = Logger.getLogger(getClass());
    protected static final String ERROR_MSG_KEY = "errorMsg";

    Logger logger = Logger.getLogger(getClass());

    /**
     * spring 中request、response是线程安全的，可以直接注入
     *
     * @ModelAttribute注解只有在被
     * @Controller和@ControllerAdvice两个注解的类下使用 ModelAttribute的作用
     *    1)放置在方法的形参上： 表示引用Model中的数据
     *    2)放置在方法上面：表示请求该类的每个Action前都会首先执行它也可以将一些准备数据的操作放置在该方法里面。
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * @return
     */
    protected HashMap<String, String> getRequestHeaders() {
        HashMap<String, String> requestHeaders = new HashMap<String, String>();
        @SuppressWarnings("unchecked")
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            requestHeaders.put(headerName, headerValue);
        }
        return requestHeaders;
    }

    /**
     * 获取请求属性封装为Map类型
     * @param request
     * @return
     */
    protected HashMap<String, Object> getParamMap(HttpServletRequest request) {
        HashMap<String, Object> conditions = new HashMap<String, Object>();
        Map map = request.getParameterMap();
        for (Object o : map.keySet()) {
            String key = (String) o;
            conditions.put(key, ((String[]) map.get(key))[0]);
        }
        return conditions;
    }

    protected String getParam(HttpServletRequest request,String param,String defaultValue){
        Map<String,Object> map = getParamMap(request);
        String result = (String)map.get(param);
        return result != null && !"".equals(result) ? result : defaultValue;
    }
    protected String getParam(HttpServletRequest request,String param){
        Map<String,Object> map = getParamMap(request);
        String result = (String)map.get(param);
        return result != null && !"".equals(result) ? result : "";
    }

    protected Integer getParamToInt(HttpServletRequest request, String param){
        Map<String,Object> map = getParamMap(request);
        return toInt(String.valueOf(map.get(param)),null);
    }

    public final String getAppbaseUrl(HttpServletRequest request, String url) {
        Assert.hasLength(url, "url不能为空");
        Assert.isTrue(url.startsWith("/"), "必须以/打头");
        return request.getContextPath() + url;
    }

    private Integer toInt(String value, Integer defaultValue) {
        try {
            if (value == null || "".equals(value.trim()))
                return defaultValue;
            value = value.trim();
            if (value.startsWith("N") || value.startsWith("n"))
                return -Integer.parseInt(value.substring(1));
            return Integer.parseInt(value);
        }
        catch (Exception e) {
            logger.error("Can not parse the parameter \"" + value + "\" to Integer value.");
        }
        return null;
    }

}
