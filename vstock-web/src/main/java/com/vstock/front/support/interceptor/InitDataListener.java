package com.vstock.front.support.interceptor;

import com.vstock.front.service.VstockConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

public class InitDataListener implements InitializingBean, ServletContextAware {

    private Logger logger = Logger.getLogger(InitDataListener.class);

    @Autowired
    VstockConfigService vstockConfigService;

    @Override
    public void afterPropertiesSet() throws Exception { }

    @Override
    public void setServletContext(ServletContext servletContext) {
        vstockConfigService.loadTplForexConfig();
    }
}
