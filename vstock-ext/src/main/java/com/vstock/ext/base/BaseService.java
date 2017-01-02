package com.vstock.ext.base;

import org.springframework.beans.factory.annotation.Value;

public class BaseService {

    @Value("${projectPath}")
    public String projectPath;

}
