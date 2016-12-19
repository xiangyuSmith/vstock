package com.vstock.front.service;

import com.vstock.db.dao.IVstockConfigDao;
import com.vstock.db.entity.VstockConfig;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VstockConfigService {

    @Autowired
    IVstockConfigDao vstockConfigDao;

    final static Map<String, String> configMap = new HashMap<>();

    public void loadTplForexConfig() {
        List<VstockConfig> configs = vstockConfigDao.findAll();
        configs.forEach(c -> configMap.put(c.getKey(), c.getValue()));
    }

    public static String getConfig(String key) {
        if (configMap.isEmpty()) {
            throw new RuntimeException("VstockConfigService 系统属性配置初始化失败");
        }
        return MapUtils.getString(configMap, key, "未配置该属性");
    }

    public static Map<String, String> getConfigMap() {
        return configMap;
    }
}
