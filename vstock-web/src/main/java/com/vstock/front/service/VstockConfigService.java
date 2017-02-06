package com.vstock.front.service;

import com.vstock.db.dao.IVstockConfigDao;
import com.vstock.db.entity.BasicinformationRose;
import com.vstock.db.entity.Point;
import com.vstock.db.entity.VstockConfig;
import com.vstock.ext.util.DateUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VstockConfigService {

    final static Logger logger = Logger.getLogger(VstockConfigService.class);

    @Autowired
    IVstockConfigDao vstockConfigDao;

    @Autowired
    CityAddressService cityAddressService;

    @Autowired
    ResultDataService resultDataService;

    @Autowired
    BasiciformationRoseService basiciformationRoseService;

    final static Map<String, String> configMap = new HashMap<>();

    final static Map<String, List<Point>> brandMap = new HashMap<>();

    final static Map<String, Map<String, Object>> roesMap = new HashMap<>();

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

    public static List<Point> getBrand(String key) {
        if (brandMap.isEmpty()) {
            logger.warn("VstockConfigService 获取市场价值数据失败");
        }
        return brandMap.get(key);
    }

    public static Map<String, List<Point>> getBrandMap() {
        return brandMap;
    }

    public static void setBrandMap(String brand, List<Point> brandMarket) {
        brandMap.put(brand,brandMarket);
    }

    public static Map<String, Map<String, Object>> getRoesMap() {
        return roesMap;
    }

    public static void setRoesMap(String brand, Map<String, Object> roes) {
        roesMap.put(brand,roes);
    }

    public static Map<String, Object> getRoes(String key) {
        if (roesMap.isEmpty()) {
            logger.warn("VstockConfigService 获取市场价值数据失败");
        }
        return roesMap.get(key);
    }

    public static boolean isChineseChar(String str){
        boolean temp = false;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m=p.matcher(str);
        if(m.find()){
            temp =  true;
        }
        return temp;
    }

    class YieldThread extends Thread{
        public YieldThread(String name) {
            super(name);
        }

        public void run() {
            Thread.yield();
            jsonAdder = cityAddressService.adderssAll();
            for (String brand : BasicinformationRose.brandStr) {
                List<Point> brad = resultDataService.brandMarket(brand);
                if (brad.size() > 0) {
                    brandMap.put(brand, brad);
                }
                Map<String, Object> roseDegree = basiciformationRoseService.reseDegreeN(brand);
                if (roseDegree.size() > 0) {
                    roesMap.put(brand, roseDegree);
                }
            }
        }
    }
}
