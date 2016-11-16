package com.vstock.ext.base;

import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * Created by xiangyu on 2016/11/8.
 */
public class BaseController {

    protected static final String ERROR_MSG_KEY = "errorMsg";

    Logger logger = Logger.getLogger(getClass());

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
