package com.vstock.front.service;

import com.vstock.db.dao.IVstockConfigDao;
import com.vstock.db.entity.VstockConfig;
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

    public static Map<String, String> getConfigMap() {
        return configMap;
    }
}
