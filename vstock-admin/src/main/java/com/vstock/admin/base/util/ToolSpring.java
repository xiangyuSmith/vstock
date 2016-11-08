package com.vstock.admin.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

/**
 * 获取spring信息的工具类
 * @author Administrator
 *
 */
public final class ToolSpring extends ApplicationObjectSupport {
    private static ApplicationContext applicationContext = null;
    @Override
    protected void initApplicationContext(ApplicationContext context)
            throws BeansException {
// TODO Auto-generated method stub
        super.initApplicationContext(context);
        if(ToolSpring.applicationContext == null){
            ToolSpring.applicationContext = context;
            System.out.println();
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
            System.out.println("========ApplicationContext配置成功,在普通类可以通过调用ToolSpring.getAppContext()获取applicationContext对象,applicationContext="+applicationContext+"========");
            System.out.println("---------------------------------------------------------------------");
            System.out.println();
            System.out.println();
        }
    }
    public static ApplicationContext getAppContext() {
        return applicationContext;
    }
    public static Object getBean(String name){
        return getAppContext().getBean(name);
    }
}