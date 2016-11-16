package com.vstock.ext.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vstock.ext.util.StringUtil;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * Created by xiangyu on 2016/11/8.
 */
public class ModelAndView extends org.springframework.web.servlet.ModelAndView {

    private Logger logger = LoggerFactory.getLogger(getClass());
    public void setState(String state) {
        super.addObject("state", state);
    }

    public void put(String key, Object value) {
        addObject(key, value);
    }

    public ModelAndView() {

    }

    public ModelAndView(String viewName) {
        if ("json/json".equals(viewName)) {
            setView(new MappingJacksonJsonView());
        } else {
            logger.debug("即将跳转到页面：" + viewName);
            setViewName(viewName);
        }
    }

    public void noticeSuccess() {
        setState("success");
    }

    public void noticeFailure(String message) {
        this.setState("error");
        this.setMessage(message);
        if(StringUtil.isNotEmpty(message)) {
            logger.warn("invoke method failure:"+ message);

        }
    }

    public void setState(String state, String message) {
        super.addObject("state", state);
        super.addObject("message", message);
    }

    public void setMessage(String message) {
        super.addObject("message", message);

    }

}
