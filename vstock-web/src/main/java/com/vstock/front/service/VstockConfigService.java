package com.vstock.front.service;

import com.vstock.db.dao.IVstockConfigDao;
import com.vstock.db.entity.VstockConfig;
import org.apache.commons.collections.MapUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VstockConfigService {

    @Autowired
    IVstockConfigDao vstockConfigDao;

    @Autowired
    CityAddressService cityAddressService;

    final static Map<String, String> configMap = new HashMap<>();

    static JSONObject jsonAdder = new JSONObject();

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

    public void lodingstaAdder(){YieldThread t1 = new YieldThread("t1"); t1.start();}

    public static JSONObject getJsonAdder(){return jsonAdder;}

    class YieldThread extends Thread{
        public YieldThread(String name) {
            super(name);
        }

        public void run() {
            Thread.yield();
            jsonAdder = cityAddressService.adderssAll();
        }
    }
}
