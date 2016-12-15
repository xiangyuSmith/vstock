package com.vstock.front.service;

import com.vstock.db.dao.ICityAddressDao;
import com.vstock.db.entity.CityAddress;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by administor on 2016/12/14.
 */
@Service
public class CityAddressService {
    @Autowired
    ICityAddressDao cityAddressDao;

    /**
     * 查询所有地址
     * @param record
     * @return
     */
    public List<CityAddress> findAll(CityAddress record){return cityAddressDao.findAll(record);}

    /**
     * 查询总数
     * @param record
     * @return
     */
    public int findCount(CityAddress record){return cityAddressDao.findCount(record);}

    /**
     * 添加新数据
     * @param record
     * @return
     */
    public int insert(CityAddress record){return cityAddressDao.insert(record);}

    /**
     * 循环获取各级地址封装到jsonObject
     * @return
     */
    public JSONObject adderssAll(){
        JSONObject jsonObject = new JSONObject();
        List<Map<List<String>,JSONArray>> objList = new ArrayList<Map<List<String>,JSONArray>>();
        List<Map<List<String>,JSONArray>> temporary = new ArrayList<Map<List<String>,JSONArray>>();
        Map<List<String>,JSONArray> provincial = this.adderss(0,0);
        temporary.add(provincial);
        int b = 4;
        for (int a = 0; a < b; a++) {
            objList = new ArrayList<Map<List<String>,JSONArray>>();
            objList = temporary;
            temporary = new ArrayList<Map<List<String>,JSONArray>>();
            for (Map<List<String>,JSONArray> obj : objList) {
                for (List<String> taskList : obj.keySet()) {
                    String parentId = "";
                    for (int i = 0; i < taskList.size(); i++) {
                        String[] str = taskList.get(i).split("/");
                        if (a != 3) {
                            Map<List<String>, JSONArray> municipal = this.adderss(Integer.parseInt(str[0]), 1);
                            temporary.add(municipal);
                        }
                        parentId = str[1];
                    }
                    if (!"".equals(parentId) && parentId!=null){
                        jsonObject.put(parentId, obj.get(taskList));
                    }
                }
            }
        }
        return jsonObject;
    }

    /**
     * 根据父类ID获取各级地址记录
     * @param parentId
     * @param type
     * @return
     */
    public Map<List<String>,JSONArray> adderss(Integer parentId, Integer type){
        Map<List<String>,JSONArray> param = new HashMap<List<String>,JSONArray>();
        CityAddress record = new CityAddress();
        record.setParentId(parentId);
        JSONArray jsonArray = new JSONArray();
        List<String> codeList = new ArrayList<String>();
        List<CityAddress> cityAddressList = this.findAll(record);
        for (int i = 0; i < cityAddressList.size(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("i",cityAddressList.get(i).getCode());
            jsonObject.put("n",cityAddressList.get(i).getName());
            if (type == 0) {
                String s = this.firstFight(cityAddressList.get(i).getName());
                jsonObject.put("s", s);
            }
            codeList.add(cityAddressList.get(i).getCode()+"/"+cityAddressList.get(i).getParentId());
            jsonArray.put(jsonObject);
        }
        param.put(codeList,jsonArray);
        return param;
    }

    /**
     * 省级地址排序
     * @param name
     * @return
     */
    public String firstFight(String name){
        String[] adder = new String[]{"安/a","澳/a","北/b","重/c","福/f","广/g","贵/g","甘/g",
                "河/h","海/h","黑/h","湖/h","吉/j","江/j","辽/l","内/n","宁/n","青/q",
                "山/s","上/s","四/s","陕/s","天/t","台/t","西/x","新/x","香/x","云/y","浙/z"};
        for (String str : adder){
            String[] adderss = str.split("/");
            if (name.contains(adderss[0])){
                return adderss[1];
            }
        }
        return null;
    }
}
