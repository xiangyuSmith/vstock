package com.vstock.admin.support.interceptor;

import com.vstock.admin.service.BasicinformationService;
import com.vstock.db.entity.Basicinformation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

public class InitDataListener implements InitializingBean, ServletContextAware {

    private Logger logger = Logger.getLogger(InitDataListener.class);

    final static List<Basicinformation> nameLists = new ArrayList<Basicinformation>();

    @Autowired
    BasicinformationService basicinformationService;

    @Override
    public void afterPropertiesSet() throws Exception { }

    @Override
    public void setServletContext(ServletContext servletContext) {
        basicinformationService.finaBftList();
    }

}
