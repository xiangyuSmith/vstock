package com.vstock.ext.base;

import com.vstock.ext.util.ToolSpring;
import com.vstock.server.memcached.SpyMemcachedManager;
import org.springframework.beans.factory.annotation.Value;

public class BaseService {

    @Value("${projectPath}")
    public String projectPath;

    protected SpyMemcachedManager spyMemcachedManager = (SpyMemcachedManager) ToolSpring.getBean("memcachedManager");
}
