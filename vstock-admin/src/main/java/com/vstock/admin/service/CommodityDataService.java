package com.vstock.admin.service;

import com.vstock.db.dao.*;
import com.vstock.db.entity.*;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.JsonTool;
import com.vstock.ext.util.Page;
import com.vstock.ext.util.StringUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by administor on 2016/5/12.
 */
@Service("commodityData")
public class CommodityDataService {

    @Autowired
    ICommodityData commodityDataDao;

    @Autowired
    ICommodityDetail commodityDetailDao;

    @Autowired
    IResultData resultDataDao;

    @Autowired
    IResultDataFactory resultDataFactoryDao;

    @Autowired
    IDictionariesDao dictionariesDao;

    /**
     * 数据列表查询方法
     *
     * @param record
     * @return
     */
    public List<Basicinformation> findResultDataFactoryAll(ResultDataFactory record, String startTime, String endTime, Page page) {
        return resultDataFactoryDao.findrdfAndBasiAll(record, startTime, endTime, page.getStartPos(), page.getPageSize());
    }

    /**
     * 查询总数
     *
     * @param record
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Integer> findResultDataFactoryCount(ResultDataFactory record, String startTime, String endTime) {
        return resultDataFactoryDao.findrdfAndBasiCount(record, startTime, endTime);
    }

    /**
     * 下一页查询条件封装
     *
     * @param linkAddress
     * @param list
     * @param record
     * @return
     */
    public String linkAddress(String linkAddress, List<String> list, ResultDataFactory record) {
        if (list.size() > 0) {
            for (String strLink : list) {
                String[] strs = strLink.split("=");
                linkAddress += strs[0] + "=" + strs[1];
            }
        }
        if (StringUtil.isNotBlank(record.getBasiciformationId())) {
            linkAddress += "&basiciformationId=" + record.getBasiciformationId();
        }
        if (StringUtil.isNotBlank(record.getCommodityDataId())) {
            linkAddress += "&brand=" + record.getCommodityDataId();
        }
        if (StringUtil.isNotBlank(record.getProductName())) {
            linkAddress += "&productName=" + record.getProductName();
        }
        if (StringUtil.isNotBlank(record.getGirard())) {
            linkAddress += "&girard=" + record.getGirard();
        }
        return linkAddress;
    }

    /**
     * 商品数据的新增方法
     * <p>
     * Date: 2016/05/12  下午：17:37:41
     *
     * @param cmmodityData
     * @return
     */
    public int insertCommodityData(CommodityData cmmodityData) {
        return commodityDataDao.insertcommodityData(cmmodityData);
    }


    /**
     * 商品数据详情
     */

    public int insertcommodityDetail(CommodityDetail commodityDetail) {
        return commodityDataDao.insertcommodityDetail(commodityDetail);
    }

    /**
     * 模糊查询单个商品
     *
     * @return
     */
    public CommodityData findByNames(String name, String girard) {
        List<CommodityData> commodityDataList = commodityDataDao.findByName(name, girard);
        if (commodityDataList.size() == 0) {
            return null;
        }
        return commodityDataDao.findByName(name, girard).get(0);
    }

    public List<CommodityData> findList() {
        List<CommodityData> list = new ArrayList<CommodityData>();
        try {
            list = commodityDataDao.findCommodityDatalist();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return list;
    }

    //获取页面总数
    public Long getCount(CommodityData recode, String startTime, String endTime) {
        return commodityDataDao.getCommodityCount(recode, startTime, endTime);
    }

    //进行分页处理
    public List<CommodityData> queryByPage(Page page, CommodityData commodityData, String startTime, String endTime) {
        return commodityDataDao.findAll(commodityData, page.getStartPos(), page.getPageSize(), startTime, endTime);
    }

    /**
     * 查询尺码数据方法
     *
     * @param record
     * @param page
     * @return
     * @Date 2016：06:23  下午14:36:32
     */
    public List<CommodityDetail> findPageAll(CommodityDetail record, Page page) {
        return commodityDetailDao.findPageAll(record, page.getStartPos(), page.getPageSize());
    }

    public List<CommodityDetail> findAllComm(CommodityDetail record, String createDateStart, String createDateEnd) {
        return commodityDetailDao.findAllComm(record, createDateStart, createDateEnd);
    }

    public List<ResultData> findResultDataAll(ResultData record) {
        return resultDataDao.findResultDataAll(record);
    }

    /**
     * 公共查询带条件总记录数
     *
     * @param record
     * @return
     * @Date 2016.06.23 下午 14:48:52
     */
    public Long findCount(CommodityData record) {
        return commodityDataDao.getAllCount(record);
    }

    /**
     * 公共查询商品详细信息方法
     *
     * @param record
     * @return
     * @Date 2016.06.23 上午 11:33:10
     */
    public List<CommodityDetail> findCommdityDetail(CommodityDetail record) {
        return commodityDetailDao.findCommAll(record);
    }

    /**
     * 返回一个集合
     *
     * @param record
     * @return
     * @Date 2016.06.24 下午 16:10:39
     */
    public List<CommodityData> findCommodityData(CommodityData record) {
        return commodityDataDao.findCommodityDataAll(record);
    }

    /**
     * 查询查询详细信息表
     *
     * @param record
     * @return
     * @Date 2016.06.24 下午 18:21:22
     */
    public Long findAllCount(CommodityDetail record) {
        return commodityDetailDao.findAllCount(record);
    }

    /**
     * 判断字典数据是否存在
     */
    public List<Dictionaries> finddictionaries(String commodityId) {
        return dictionariesDao.findByCommodityId(commodityId);
    }

    /**
     * 添加字典数据
     */
    public int insertDicInfo(Dictionaries dictionarie) {
        return dictionariesDao.insertdictionaries(dictionarie);
    }


    /**
     * 添加最终结果数据
     */
    public int saveResultDatas(ResultData resultData) {
        return resultDataDao.insertresultData(resultData);
    }

    /**
     * 根据时间区间查询
     *
     * @param record
     * @param startTime
     * @param endTime
     * @return
     */
    public List<ResultData> findResultDataTime(ResultData record, String startTime, String endTime) {
        return resultDataDao.findResultDataTime(record, startTime, endTime);
    }

    /**
     * 公共调用取json里面的值，分开保存
     *
     * @param CommodityDetaillist
     * @return
     * @throws Exception
     * @Date 2016.06.28 下午 18:39:10
     */
    public List<CommodityDetailys> takeJson(CommodityDetail CommodityDetaillist) throws Exception {
        //初始化值
        List<CommodityDetailys> commodityList = new ArrayList<CommodityDetailys>();
        //把颜色尺码的值转换为json对象
        JSONObject jsonObject = new JSONObject(CommodityDetaillist.getColorSize());
        //调用方法获取json中所有的key和value的值
        Map<String, Object> valueMap = JsonTool.jsonObtainKey(jsonObject);
        //循环取key和value(颜色和尺码对应的价格信息)
        for (String key : valueMap.keySet()) {
            //根据key获取vaule
            Object obj = valueMap.get(key);
            JSONObject json = new JSONObject(obj.toString());
            Map<String, Object> value = JsonTool.jsonObtainKey(json);
            //循环取map中的key和value（尺码信息和对应的价格信息）
            for (String str : value.keySet()) {
                CommodityDetailys commoditys = new CommodityDetailys();
                commoditys.setColorly(key);
                commoditys.setAveragePrice(value.get(str).toString());
                commoditys.setFootage(str);
                commodityList.add(commoditys);
            }
        }
        return commodityList;
    }

    /**
     * 获取商品所有的店铺所有尺码详细信息
     *
     * @param record
     * @return
     * @Date 2016.06.28 下午 18:53:33
     */
    public List<List> takeOtherShop(CommodityData record) {

        //初始化值
        CommodityData commodityDatas = new CommodityData();
        List<CommodityData> commodityData = new ArrayList<CommodityData>();
        List<List> commodiList = new ArrayList<List>();
        List<CommodityDetail> CommodityDetaillist = new ArrayList<CommodityDetail>();
        CommodityDetail commodityDetail = new CommodityDetail();
        //获取截取后的商品名称
        commodityDatas.setCommodityName(StringUtil.takeIn(record.getCommodityName()));
        //获取商品名模糊查询的同一商品
        commodityData = this.findCommodityData(commodityDatas);
        //判断是否存在值
        if (commodityData.size() > 0) {
            //循环取值
            for (CommodityData commod : commodityData) {
                //获取截取后的商品名称
                commod.setCommodityName(StringUtil.takeIn(commod.getCommodityName()));
                //获取商品名称的对比值
                float i = StringUtil.getSimilarityRatio(commodityDatas.getCommodityName(), commod.getCommodityName());
                //判断相识度是否超过50%
                if (i > 0.5) {
                    //赋值为查询
                    commodityDetail.setCommodityDataId(commod.getId());
                    if (record.getCreateDate() == null || record.getCreateDate().equals("")) {
                        //查询数据
                        CommodityDetaillist = this.findCommdityDetail(commodityDetail);
                    } else {
                        String createDateStart = record.getCreateDate() + " 00:00:00";
                        String createDateEnd = record.getCreateDate() + " 23:59:59";
                        CommodityDetaillist = this.findAllComm(commodityDetail, createDateStart, createDateEnd);
                    }
                    try {
                        //判断是否有值
                        if (CommodityDetaillist.size() > 0) {
                            //获取解析的鞋码和价格
                            commodiList.add(this.takeJson(CommodityDetaillist.get(0)));
                        }
                    } catch (Exception e) {
                        System.out.print(e.getMessage());
                    }
                }
            }
        }
        return commodiList;
    }

    /**
     * 选好获取球鞋不同颜色的均价
     *
     * @param commodiList
     * @param commodityDetailys
     * @return
     * @Date 2016.06.29 下午 15:11:33
     */
    public CommodityDetailys commodityDetai(List<List> commodiList, CommodityDetailys commodityDetailys) {
        //初始化变量
        CommodityDetailys commodity = new CommodityDetailys();
        DecimalFormat df = new DecimalFormat("####.00");
        List<Double> stringList = new ArrayList<Double>();
        List<CommodityDetailys> commodityList = new ArrayList<CommodityDetailys>();
        Double z = new Double(0);
        //获取颜色和尺码
        String str = commodityDetailys.getFootage();
        String colorly = commodityDetailys.getColorly();
        //判断是否存在值
        if (commodiList.size() > 0) {//存在
            //循环取值
            for (List<CommodityDetailys> list : commodiList) {
                //遍历取值
                for (int x = 0; x < list.size(); x++) {
                    //判断尺码和颜色是否是一样
                    if (str.equals(list.get(x).getFootage()) && colorly.equals(list.get(x).getColorly())) {
                        //保存价格
                        stringList.add(Double.parseDouble(list.get(x).getAveragePrice()));
                    }
                }
            }
        }
        //判断是否存在值
        if (stringList.size() > 0) {
            //遍历取值
            for (int y = 0; y < stringList.size(); y++) {
                //值相加
                z += stringList.get(y);
            }
            //获取平均值
            z = z / stringList.size();
            //赋值传回前台
            commodity.setColorly(colorly);
            commodity.setFootage(str);
            commodity.setAveragePrice(df.format(z).toString());
        } else {
            //赋值传回前台
            commodity.setColorly(colorly);
            commodity.setFootage(str);
            commodity.setAveragePrice(commodityDetailys.getAveragePrice());
        }
        return commodity;
    }

    //根据商品名称修改
    public int updateResultData(String productName, String productNameNew) {
        return resultDataDao.update(productName, productNameNew);
    }

    //获取尺码信息
    public StringBuffer shoeCodeInformation(List<CommodityDetailys> list) {
        //默认显示的尺码信息
        String[] str = new String[]{"35", "35.5", "36", "36.5", "37", "37.5", "38", "38.5", "39", "39.5", "40", "40.5", "41", "41.5",
                "42", "42.5", "43", "43.5", "44", "44.5", "45", "45.5", "46", "46.5", "47", "47.5", "48", "48.5"};
        //初始化变量
        StringBuffer divTr = new StringBuffer();
        //根据要显示的尺码循环遍历
        for (String string : str) {
            //获取数据库中集合的值
            int x = 0;
            List<CommodityDetailys> commodityDetailys = new ArrayList<CommodityDetailys>();
            CommodityDetailys commodity = new CommodityDetailys();
            if (list.size() > 0) {
                for (CommodityDetailys comm : list) {
                    //判断是否包含当前尺码
                    if (comm.getFootage().contains(string)) {//包含
                        x++;
                        commodity = comm;
                        commodityDetailys.add(comm);
                    }
                }
            }
            if (x >= 1) {
                divTr.append(this.verification(string, commodityDetailys));
            } else {
                divTr.append("<tr>\n" +
                        " <td><input class=\"lineGraph\" type=\"checkbox\" disabled=\"disabled\"/></td>\n" +
                        " <td>" + string + "</td>\n" +
                        " <td>" + "—" + "</td>\n" +
                        " <td><a href=\"javascript:void(0)\">详情</a></td>\n" +
                        " </tr>");
            }
        }
        return divTr;
    }

    //验证出现同时都包含的情况
    public StringBuffer verification(String string, List<CommodityDetailys> list) {
        //初始化变量
        StringBuffer divTr = new StringBuffer();
        String nowDate = DateUtils.getCurrentTimeAsString();
        nowDate = nowDate.substring(0, 10) + " 00:00:00";
        for (CommodityDetailys comm : list) {
            String createDate = DateUtils.compareDateDown(nowDate, comm.getCreateDate());
            //获取字符所在的位置
            int a = comm.getFootage().indexOf(string);
            //判断字符的长度是否超过下标的位置
            if (comm.getFootage().length() > a + 2) {//超过长度
                //获取字符后面第一个的值
                String shoeCode = comm.getFootage().substring(a + 2, a + 3);
                //判断后面一个值是否是.
                if (shoeCode.equals(".")) {//是
                    //获取后面两位的值
                    shoeCode = comm.getFootage().substring(a + 3, a + 4);
                    if (!"5".equals(shoeCode)) {
                        if (nowDate.equals(createDate)) {
                            return divTr.append("<tr>\n" +
                                    " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                    " <td>" + string + "</td>\n" +
                                    " <td>" + comm.getAveragePrice() + "</td>\n" +
                                    " <td><a href=\"/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                    " </tr>");
                        } else {
                            return divTr.append("<tr>\n" +
                                    " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                    " <td>" + string + "</td>\n" +
                                    " <td>" + comm.getAveragePrice() + "</td>\n" +
                                    " <td><a href=\"/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                    " </tr>");
                        }
                    } else if ("5".equals(shoeCode)) {
                        if (a >= 1) {
                            if (comm.getFootage().substring(a - 1, a + 4).trim().equals(string)) {
                                if (nowDate.equals(createDate)) {
                                    return divTr.append("<tr>\n" +
                                            " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                            " <td>" + string + "</td>\n" +
                                            " <td>" + comm.getAveragePrice() + "</td>\n" +
                                            " <td><a href=\"/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                            " </tr>");
                                } else {
                                    return divTr.append("<tr>\n" +
                                            " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                            " <td>" + string + "</td>\n" +
                                            " <td>" + comm.getAveragePrice() + "</td>\n" +
                                            " <td><a href=\"/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                            " </tr>");
                                }
                            } else {
                                return divTr.append("<tr>\n" +
                                        " <td><input class=\"lineGraph\" type=\"checkbox\" disabled=\"disabled\"/></td>\n" +
                                        " <td>" + string + "</td>\n" +
                                        " <td>" + "—" + "</td>\n" +
                                        " <td><a href=\"javascript:void(0)\">详情</a></td>\n" +
                                        " </tr>");
                            }
                        } else {
                            if (comm.getFootage().substring(a, a + 4).trim().equals(string)) {
                                if (nowDate.equals(createDate)) {
                                    return divTr.append("<tr>\n" +
                                            " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                            " <td>" + string + "</td>\n" +
                                            " <td>" + comm.getAveragePrice() + "</td>\n" +
                                            " <td><a href=\"/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                            " </tr>");
                                } else {
                                    return divTr.append("<tr>\n" +
                                            " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                            " <td>" + string + "</td>\n" +
                                            " <td>" + comm.getAveragePrice() + "</td>\n" +
                                            " <td><a href=\"/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                            " </tr>");
                                }
                            } else {
                                return divTr.append("<tr>\n" +
                                        " <td><input class=\"lineGraph\" type=\"checkbox\" disabled=\"disabled\"/></td>\n" +
                                        " <td>" + string + "</td>\n" +
                                        " <td>" + "—" + "</td>\n" +
                                        " <td><a href=\"javascript:void(0)\">详情</a></td>\n" +
                                        " </tr>");
                            }
                        }
                    }
                } else {//不是
                    if (nowDate.equals(createDate)) {
                        return divTr.append("<tr>\n" +
                                " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                " <td>" + string + "</td>\n" +
                                " <td>" + comm.getAveragePrice() + "</td>\n" +
                                " <td><a href=\"/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                " </tr>");
                    } else {
                        return divTr.append("<tr>\n" +
                                " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                " <td>" + string + "</td>\n" +
                                " <td>" + comm.getAveragePrice() + "</td>\n" +
                                " <td><a href=\"/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                " </tr>");
                    }
                }
            } else {
                if (nowDate.equals(createDate)) {
                    return divTr.append("<tr>\n" +
                            " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                            " <td>" + string + "</td>\n" +
                            " <td>" + comm.getAveragePrice() + "</td>\n" +
                            " <td><a href=\"/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                            " </tr>");
                } else {
                    return divTr.append("<tr>\n" +
                            " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                            " <td>" + string + "</td>\n" +
                            " <td>" + comm.getAveragePrice() + "</td>\n" +
                            " <td><a href=\"/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                            " </tr>");
                }
            }
        }
        return divTr;
    }

    public Map<String, Object> findLineGraph(ResultData record, String colorly, String footage, Integer dateTime) {
        Map<String, Object> params = new HashMap<String, Object>();
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        Map<String, Map<String, CommodityDetailys>> sizeMap = new HashMap<String, Map<String, CommodityDetailys>>();
        String str = "";
        String[] arr = new String[]{};
        //获取当前时间
        Date date = new Date();
        //尺码数量
        int sizeNum = 0;
        //判断鞋码是否为空
        if (footage != null && !"".equals(footage)) {
            //分割获取值
            String[] strfootage = footage.split(",");
            sizeNum = strfootage.length;
            for (int x = 0; x < sizeNum; x++) {
                record.setSizePrice(strfootage[x]);
                List<List> list = new ArrayList<List>();
                //获取一个月前的时间
                Date thdate = DateUtils.wantToLose(date, dateTime);
                for (int i = 0; i <= dateTime; i++) {
                    //拼接日期
                    String startTime = DateUtils.dateToString(DateUtils.addDaysToDate(thdate, i), "yyyy-MM-dd") + " 00:00:00";
                    String endTime = DateUtils.dateToString(DateUtils.addDaysToDate(thdate, i), "yyyy-MM-dd") + " 23:59:59";
                    //查询所有数据
                    resultDataList = this.findResultDataTime(record, startTime, endTime);
                    //判断是否存在值
                    if (resultDataList.size() != 0) {//存在
                        List<ResultData> resultDatas = this.verificationDateShoeCode(resultDataList, record.getSizePrice());
                        if (resultDatas.size() != 0) {
                            //添加并记录时间
                            list.add(resultDataList);
                            str = str + "," + DateUtils.dateToString(DateUtils.addDaysToDate(thdate, i)).substring(5, 10);
                        }
                    }
                }
                if (str.length() > 1) {
                    //X轴日期存入数组
                    arr = str.substring(1, str.length()).split(",");
                    arr = StringUtil.array_unique(arr);
                    Arrays.sort(arr);
                }

                Map<String, CommodityDetailys> priceMap = new HashMap<String, CommodityDetailys>();
                String createDate = "";

                //初始化变量，并且获取尺码颜色的值
                CommodityDetailys commodityDetailys = new CommodityDetailys();
                commodityDetailys.setFootage(strfootage[x]);
                commodityDetailys.setColorly(colorly);
                if (list.size() > 0) {
                    //循环取值
                    for (List<ResultData> commlist : list) {
                        //初始化变量用于结果算法
                        int a = 0;
                        Double price = Double.parseDouble("0");
                        CommodityDetailys commodityDetailysT = new CommodityDetailys();
                        //遍历取值
                        for (int y = 0; y < commlist.size(); y++) {
                            ResultData resultData = commlist.get(y);
                            float b = StringUtil.getSimilarityRatio(resultData.getProductName(), resultData.getProductName());
                            if (b == 1.0) {
                                //截取，左右的值
                                String[] strArr = resultData.getSizePrice().split(",");
                                int i = 0;
                                List<String[]> sizapricelist = new ArrayList<String[]>();
                                String[] sizePrices = new String[]{};
                                //循环取鞋码价格
                                for (int z = 0; z < strArr.length; z++) {
                                    String[] sizePrice = strArr[z].split("=");
                                    if (!"".equals(commodityDetailys.getFootage()) && commodityDetailys.getFootage() != null) {
                                        sizePrice = this.taobaoShoeCodeRule(sizePrice, commodityDetailys.getFootage());
                                        //判断尺码是否相等
                                        if (sizePrice[0].contains(commodityDetailys.getFootage())) {
                                            i++;
                                            sizePrices = sizePrice;
                                            sizapricelist.add(sizePrice);
                                        }
                                    }
                                }
                                if (i >= 1) {
                                    String[] strings = this.judgeprice(sizapricelist, commodityDetailys);
                                    if (strings.length > 1) {
                                        a = a + Integer.parseInt(strings[1]);
                                        price = price + Double.parseDouble(strings[0]);
                                        createDate = resultData.getCreateTime();
                                    }
                                }
                            }
                        }
                        if (price > 0) {
                            price = price / a;
                            commodityDetailys.setAveragePrice(price.toString());
                            String shopName = commodityDetailys.getShopName();
                            String createDates = commodityDetailys.getCreateDate();
                            String footages = commodityDetailys.getFootage();
                            String averagePrice = commodityDetailys.getAveragePrice();
                            String colorlys = commodityDetailys.getColorly();
                            commodityDetailysT.setShopName(shopName);
                            commodityDetailysT.setCreateDate(createDates);
                            commodityDetailysT.setFootage(footages);
                            commodityDetailysT.setAveragePrice(averagePrice);
                            commodityDetailysT.setColorly(colorlys);
                            priceMap.put(createDate, commodityDetailysT);
                        }
                    }
                }
                sizeMap.put(record.getSizePrice(), priceMap);
            }
        }
        params = this.shoeCodeChartMosaic(sizeMap, arr);
        return params;
    }

    public String[] judgeprice(List<String[]> sizePriceList, CommodityDetailys commodityDetailys) {
        String[] str = new String[2];
        int i = 0;
        Double price = Double.parseDouble("0");
        for (String[] sizePrice : sizePriceList) {
            //获取字符所在的位置
            int index = sizePrice[0].indexOf(commodityDetailys.getFootage());
            //判断字符的长度是否超过下标的位置
            if (sizePrice[0].length() > index + 2) {//超过长度
                //获取字符后面第一个的值
                String shoeCode = sizePrice[0].substring(index + 2, index + 3);
                //判断后面一个值是否是.
                if (shoeCode.equals(".")) {//是
                    //获取后面两位的值
                    shoeCode = sizePrice[0].substring(index + 3, index + 4);
                    if (!"5".equals(shoeCode)) {
                        if (sizePrice.length > 1) {
                            price = this.price(sizePrice[1], price);
                            i++;
                        }
                    } else if ("5".equals(shoeCode)) {
                        if (sizePrice[0].substring(0, index + 4).trim().equals(commodityDetailys.getFootage())) {
                            if (sizePrice.length > 1) {
                                price = this.price(sizePrice[1], price);
                                i++;
                            }
                        }
                    }
                } else {//不是
                    if (sizePrice.length > 1) {
                        price = this.price(sizePrice[1], price);
                        i++;
                    }
                }
            } else {
                if (sizePrice.length > 1) {
                    price = this.price(sizePrice[1], price);
                    i++;
                }
            }
        }
        str[0] = price.toString();
        str[1] = Integer.toString(i);
        return str;
    }

    //获取价格相加
    public Double price(String str, Double price) {
        if (str.contains("-")) {
            price = price + Double.parseDouble("0");
        } else {
            price = price + Double.parseDouble(str);
        }
        return price;
    }

    //验证店铺包含鞋码信息
    public List<ResultData> shopName(List<String[]> sizePriceList, ResultData res, String footage) {
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        List<ResultData> resultDa = new ArrayList<ResultData>();
        String sizePrce = res.getSizePrice();
        String id = res.getId();
        int bid = res.getBid();
        String commodityDataId = res.getCommodityDataId();
        String storeId = res.getStoreId();
        String storeName = res.getStoreName();
        String productName = res.getProductName();
        String brand = res.getBrand();
        String girard = res.getGirard();
        String sizePrices = res.getSizePrice();
        String transactionRecord = res.getTransactionRecord();
        String createTime = res.getCreateTime();
        String reservedField = res.getReservedField();
        for (String[] sizePrice : sizePriceList) {
            ResultData resultDatas = new ResultData();
            resultDatas.setId(id);
            resultDatas.setBid(bid);
            resultDatas.setCommodityDataId(commodityDataId);
            resultDatas.setStoreId(storeId);
            resultDatas.setStoreName(storeName);
            resultDatas.setProductName(productName);
            resultDatas.setBrand(brand);
            resultDatas.setGirard(girard);
            resultDatas.setSizePrice(sizePrices);
            resultDatas.setTransactionRecord(transactionRecord);
            resultDatas.setCreateTime(createTime);
            resultDatas.setReservedField(reservedField);
            //获取字符所在的位置
            int index = sizePrice[0].indexOf(footage);
            //判断字符的长度是否超过下标的位置
            if (sizePrice[0].length() > index + 2) {//超过长度
                //获取字符后面第一个的值
                String shoeCode = sizePrice[0].substring(index + 2, index + 3);
                //判断后面一个值是否是.
                if (shoeCode.equals(".")) {//是
                    //获取后面两位的值
                    shoeCode = sizePrice[0].substring(index + 3, index + 4);
                    if (!"5".equals(shoeCode)) {
                        res.setSizePrice(sizePrice[0]);
                        if (sizePrice.length > 1) {
                            resultDatas.setReservedField(sizePrice[1]);
                        } else {
                            resultDatas.setReservedField("0");
                        }
                        resultDataList.add(resultDatas);
                    } else if ("5".equals(shoeCode)) {
                        if (index >= 1) {
                            if (sizePrice[0].substring(index - 1, index + 4).trim().equals(footage)) {
                                resultDatas.setSizePrice(sizePrice[0]);
                                if (sizePrice.length > 1) {
                                    resultDatas.setReservedField(sizePrice[1]);
                                } else {
                                    resultDatas.setReservedField("0");
                                }
                                resultDataList.add(resultDatas);
                            }
                        } else {
                            if (sizePrice[0].substring(index, index + 4).trim().equals(footage)) {
                                resultDatas.setSizePrice(sizePrice[0]);
                                if (sizePrice.length > 1) {
                                    resultDatas.setReservedField(sizePrice[1]);
                                } else {
                                    resultDatas.setReservedField("0");
                                }
                                resultDataList.add(resultDatas);
                            }
                        }
                    } else {//不是
                        resultDatas.setSizePrice(sizePrice[0]);
                        if (sizePrice.length > 1) {
                            resultDatas.setReservedField(sizePrice[1]);
                        } else {
                            resultDatas.setReservedField("0");
                        }
                        resultDataList.add(resultDatas);
                    }
                } else {
                    resultDatas.setSizePrice(sizePrice[0]);
                    if (sizePrice.length > 1) {
                        resultDatas.setReservedField(sizePrice[1]);
                    } else {
                        resultDatas.setReservedField("0");
                    }
                    resultDataList.add(resultDatas);
                }
            } else {
                resultDatas.setSizePrice(sizePrice[0]);
                if (sizePrice.length > 1) {
                    resultDatas.setReservedField(sizePrice[1]);
                } else {
                    resultDatas.setReservedField("0");
                }
                resultDataList.add(resultDatas);
            }
        }
        for (ResultData resultData : resultDataList) {
            if (resultData.getSizePrice().equals(sizePrce)) {
                ResultData result = new ResultData();
                resultDa.add(result);
            } else {
                resultDa.add(resultData);
            }
        }
        return resultDa;
    }

    //验证店铺包含鞋码信息
    public ResultData individualPrice(List<String[]> sizePriceList, ResultData reData, String footage) {
        for (String[] sizePrice : sizePriceList) {
            //获取字符所在的位置
            int index = sizePrice[0].indexOf(footage);
            //判断字符的长度是否超过下标的位置
            if (sizePrice[0].length() > index + 2) {//超过长度
                //获取字符后面第一个的值
                String shoeCode = sizePrice[0].substring(index + 2, index + 3);
                //判断后面一个值是否是.
                if (shoeCode.equals(".")) {//是
                    //获取后面两位的值
                    shoeCode = sizePrice[0].substring(index + 3, index + 4);
                    if (!"5".equals(shoeCode)) {
                        reData.setSizePrice(sizePrice[0]);
                        if (sizePrice.length > 1) {
                            reData.setReservedField(sizePrice[1]);
                        } else {
                            reData.setReservedField("0");
                        }
                    } else if ("5".equals(shoeCode)) {
                        if (index >= 1) {
                            if (sizePrice[0].substring(index - 1, index + 4).trim().equals(footage)) {
                                reData.setSizePrice(sizePrice[0]);
                                if (sizePrice.length > 1) {
                                    reData.setReservedField(sizePrice[1]);
                                } else {
                                    reData.setReservedField("0");
                                }
                            }
                        } else {
                            if (sizePrice[0].substring(index, index + 4).trim().equals(footage)) {
                                reData.setSizePrice(sizePrice[0]);
                                if (sizePrice.length > 1) {
                                    reData.setReservedField(sizePrice[1]);
                                } else {
                                    reData.setReservedField("0");
                                }
                            }
                        }
                    }
                } else {//不是
                    reData.setSizePrice(sizePrice[0]);
                    if (sizePrice.length > 1) {
                        reData.setReservedField(sizePrice[1]);
                    } else {
                        reData.setReservedField("0");
                    }
                }
            } else {
                reData.setSizePrice(sizePrice[0]);
                if (sizePrice.length > 1) {
                    reData.setReservedField(sizePrice[1]);
                } else {
                    reData.setReservedField("0");
                }

            }
        }
        return reData;
    }

    /**
     * 分割获取鞋码信息
     *
     * @param sizePriceList
     * @param shoeSize
     * @return
     */
    public String shopSize(List<String[]> sizePriceList, String shoeSize) {
        int i = 0;
        Double parseDouble = Double.parseDouble("0");
        for (String[] sizePrice : sizePriceList) {
            //获取字符所在的位置
            int index = sizePrice[0].indexOf(shoeSize);
            //判断字符的长度是否超过下标的位置
            if (sizePrice[0].length() > index + 2) {
                //获取字符后面第一个的值
                String shoeCode = sizePrice[0].substring(index + 2, index + 3);
                //判断后面一个值是否为 '.'
                if (shoeCode.equals(".")) {
                    //获取后面两位的值
                    shoeCode = sizePrice[0].substring(index + 3, index + 4);
                    if (!"5".equals(shoeCode)) {
                        if (sizePrice.length > 1) {
                            i++;
                            parseDouble = parseDouble + Double.parseDouble(sizePrice[1]);
                        } else {
                            i++;
                            parseDouble = parseDouble + Double.parseDouble("0");
                        }
                    } else if ("5".equals(shoeCode)) {
                        if (index >= 1) {
                            if (sizePrice[0].substring(index - 1, index + 4).trim().equals(shoeSize)) {
                                i++;
                                parseDouble = parseDouble + Double.parseDouble(sizePrice[1]);
                            }
                        } else {
                            if (sizePrice[0].substring(index, index + 4).trim().equals(shoeSize)) {
                                i++;
                                parseDouble = parseDouble + Double.parseDouble(sizePrice[1]);
                            }
                        }
                    }
                } else {
                    if (sizePrice.length > 1) {
                        i++;
                        parseDouble = parseDouble + Double.parseDouble(sizePrice[1]);
                    } else {
                        i++;
                        parseDouble = parseDouble + Double.parseDouble("0");
                    }
                }
            } else {
                if (sizePrice.length > 1) {
                    i++;
                    parseDouble = parseDouble + Double.parseDouble(sizePrice[1]);
                } else {
                    i++;
                    parseDouble = parseDouble + Double.parseDouble("0");
                }
            }
        }
        String str = parseDouble.toString() + "=" + i;
        return str;
    }

    /**
     * 查询尺码数据
     *
     * @param record
     * @return
     */
    public List<CommodityDetailys> shoesCodeInformation(ResultData record) {
        //初始化变量
        List<ResultData> resultDatalist = new ArrayList<ResultData>();
        List<CommodityDetailys> commodityDetailysList = new ArrayList<CommodityDetailys>();
        DecimalFormat df = new DecimalFormat("#.00");
        String[] shoeSize = new String[]{"35", "35.5", "36", "36.5", "37", "37.5", "38", "38.5", "39", "39.5", "40", "40.5", "41", "41.5",
                "42", "42.5", "43", "43.5", "44", "44.5", "45", "45.5", "46", "46.5", "47", "47.5", "48", "48.5"};
        //循环获取固定的鞋码
        for (String shoeSizes : shoeSize) {
            record.setSizePrice(shoeSizes);
            //查询数据
            resultDatalist = this.findResultDataAll(record);
            Double price = Double.parseDouble("0");
            int z = 0;
            if (resultDatalist.size() > 0) {
                String nowDate = "";
                for (int y = 0; y < resultDatalist.size(); y++) {
                    ResultData resultData = resultDatalist.get(resultDatalist.size() - (y + 1));
                    if (!"".equals(nowDate)) {
                        String createDate = DateUtils.compareDateDown(resultData.getCreateTime(), nowDate);
                        if (createDate.equals(nowDate)) {
                            //去掉前后{}
                            String averagePrices = resultData.getSizePrice().substring(1, resultData.getSizePrice().length() - 1);
                            //根据逗号分隔获取鞋码对应的价格
                            String[] strarr = averagePrices.split(",");
                            List<String[]> list = new ArrayList<String[]>();
                            String[] sizePrices = new String[]{};
                            int i = 0;
                            //循环获取鞋码和价格
                            for (int x = 0; x < strarr.length; x++) {
                                //根据等于分隔分别获取鞋码和价格
                                String[] sizePrice = strarr[x].split("=");
                                sizePrice = this.taobaoShoeCodeRule(sizePrice, shoeSizes);
                                //判断是否包含鞋码
                                if (sizePrice[0].contains(shoeSizes)) {
                                    i++;
                                    sizePrices = sizePrice;
                                    list.add(sizePrices);
                                    record.setGirard(resultData.getGirard());
                                }
                            }
                            if (i >= 1) {
                                String str = this.shopSize(list, shoeSizes);
                                String[] strings = str.split("=");
                                if (Double.parseDouble(strings[0]) > 0) {
                                    z = z + Integer.parseInt(strings[1]);
                                    price = price + Double.parseDouble(strings[0]);
                                }
                            } else {
                                price = price + Double.parseDouble("0");
                            }
                        }
                    } else {
                        //去掉前后{}
                        String averagePrices = resultData.getSizePrice().substring(1, resultData.getSizePrice().length() - 1);
                        //根据逗号分隔获取鞋码对应的价格
                        String[] strarr = averagePrices.split(",");
                        List<String[]> list = new ArrayList<String[]>();
                        String[] sizePrices = new String[]{};
                        int i = 0;
                        //循环获取鞋码和价格
                        for (int x = 0; x < strarr.length; x++) {
                            //根据等于分隔分别获取鞋码和价格
                            String[] sizePrice = strarr[x].split("=");
                            sizePrice = this.taobaoShoeCodeRule(sizePrice, shoeSizes);
                            //判断是否包含鞋码
                            if (sizePrice[0].contains(shoeSizes)) {
                                i++;
                                sizePrices = sizePrice;
                                list.add(sizePrices);
                                record.setGirard(resultData.getGirard());
                            }
                        }
                        if (i >= 1) {
                            String str = this.shopSize(list, shoeSizes);
                            String[] strings = str.split("=");
                            if (Double.parseDouble(strings[0]) > 0) {
                                z = z + Integer.parseInt(strings[1]);
                                price = price + Double.parseDouble(strings[0]);
                                nowDate = resultData.getCreateTime().substring(0, 10) + " 00:00:00";
                            }
                        } else {
                            price = price + Double.parseDouble("0");
                        }
                    }
                }
                if (price != 0) {
                    CommodityDetailys commodityDetailys = new CommodityDetailys();
                    price = price / z;
                    commodityDetailys.setShopName(record.getProductName());
                    commodityDetailys.setCreateDate(nowDate);
                    commodityDetailys.setFootage(shoeSizes);
                    commodityDetailys.setColorly(record.getGirard());
                    commodityDetailys.setAveragePrice(df.format(price));
                    commodityDetailysList.add(commodityDetailys);
                }
            }
        }
        return commodityDetailysList;
    }

    //淘宝店铺鞋子鞋码匹配规则
    public String[] taobaoShoeCodeRule(String[] str, String shoeSizes) {
        //初始化变量
        String rule = "1/3";
        String taobaoRule = "2/3";
        int i = 0;
        int x = 0;
        String shoe = null;
        String size = null;
        //判断是否包含1/3
        if (str[0].contains(rule)) {//包含
            //获取鞋码和1/3所在位置下标
            i = str[0].indexOf(shoeSizes);
            x = str[0].indexOf(rule);
            if (i == -1) {
                i = 1;
            }
            //是否是在鞋码后面
            if (x - i < 4) {//存在
                //获取鞋码
                shoe = str[0].substring(0, i + 2);
                //拼接鞋码加上把1/3换成0.5
                shoe = shoe + ".5";
                //判断数组长度是否超过要取值的长度
                if (str[0].length() > x + 3) {//没有
                    //获取1/3后面的值
                    size = str[0].substring(x + 3, str[0].length());
                    str[0] = shoe + size;
                } else {
                    str[0] = shoe;
                }
            }
        }//判断是否包含2/3
        else if (str[0].contains(taobaoRule)) {
            i = str[0].indexOf(shoeSizes);
            x = str[0].indexOf(taobaoRule);
            if (i == -1) {
                i = 1;
            }
            if (x - i < 4) {
                shoe = str[0].substring(0, i + 2);
                shoe = shoe + ".5";
                if (str[0].length() > x + 3) {
                    size = str[0].substring(x + 3, str[0].length());
                    str[0] = shoe + size;
                } else {
                    str[0] = shoe;
                }
            }
        }
        return str;
    }

    //验证相同鞋码的数据
    public List<ResultData> verificationDateShoeCode(List<ResultData> list, String sizePrice) {
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        for (ResultData resultData : list) {
            if (resultData.getSizePrice() != null && !"".equals(resultData.getSizePrice())) {
                resultData.setSizePrice(resultData.getSizePrice().substring(1, resultData.getSizePrice().length() - 1));
                String[] strings = resultData.getSizePrice().split(",");
                for (int i = 0; i < strings.length; i++) {
                    String[] str = strings[i].split("=");
                    String size = str[0].trim();
                    if (size.contains(sizePrice)) {
                        int x = size.indexOf(sizePrice);
                        if (x == -1) {
                            x = 0;
                        }
                        if (size.length() > x + 3) {
                            String sizeO = size.substring(x + 2, x + 3);
                            if (".".equals(sizeO)) {
                                String sizeT = size.substring(x + 3, x + 4);
                                if (!"5".equals(sizeT)) {
                                    if (x > 1) {
                                        if (size.substring(x - 1, x + 1).equals(sizePrice)) {
                                            resultDataList.add(resultData);
                                        }
                                    } else {
                                        if (size.substring(x, x + 2).equals(sizePrice)) {
                                            resultDataList.add(resultData);
                                        }
                                    }
                                } else if ("5".equals(sizeT)) {
                                    if (x > 1) {
                                        if (size.substring(x - 1, x + 3).equals(sizePrice)) {
                                            resultDataList.add(resultData);
                                        }
                                    } else {
                                        if (size.substring(x, x + 4).equals(sizePrice)) {
                                            resultDataList.add(resultData);
                                        }
                                    }
                                }
                            } else {
                                if (x > 1) {
                                    if (size.substring(x - 1, x + 1).equals(sizePrice)) {
                                        resultDataList.add(resultData);
                                    }
                                } else {
                                    if (size.substring(x, x + 2).equals(sizePrice)) {
                                        resultDataList.add(resultData);
                                    }
                                }
                            }
                        } else {
                            if (size.equals(sizePrice)) {
                                resultDataList.add(resultData);
                            }

                        }
                    }
                }
            }
        }
        return resultDataList;
    }

    //鞋码图表字段拼接
    public Map<String, Object> shoeCodeChartMosaic(Map<String, Map<String, CommodityDetailys>> prams, String[] arr) {
        //初始化变量
        List<String> list = new ArrayList<String>();
        List<String[]> prices = new ArrayList<String[]>();
        //把map转换为set
        Set<Map.Entry<String, Map<String, CommodityDetailys>>> entries = prams.entrySet();
        //循环获取map中的值
        for (Map.Entry<String, Map<String, CommodityDetailys>> entry : entries) {
            //获取key和vaule
            String shoe = entry.getKey();
            Map<String, CommodityDetailys> sizeMap = entry.getValue();
            //把map转换为set
            Set<Map.Entry<String, CommodityDetailys>> entrySet = sizeMap.entrySet();
            String[] string = new String[64];

            //循环获取时间的值
            for (int i = 0; i < arr.length; i++) {
                String str = arr[i];
                //循环获取map中的值
                for (Map.Entry<String, CommodityDetailys> detailysEntry : entrySet) {
                    //判断鞋码、时间是否一样
                    if (shoe.equals(detailysEntry.getValue().getFootage()) && str.substring(3, str.length()).equals(detailysEntry.getKey().substring(8, 10))) {//相同把价格赋值给数组
                        String sizeprice = detailysEntry.getValue().getAveragePrice();
                        string[i] = sizeprice;
                    }
                }
                //判断数组的长度是否跟时间数组长度一样
                if (string[i] == null) {//不一样
                    string[i] = "0";
                }
            }
            list.add(shoe);
            prices.add(string);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("listprice", list);
        params.put("prices", prices);
        params.put("arr", arr);
        return params;
    }

    /**
     * 店铺鞋码价格趋势分析
     *
     * @param nameSet
     * @param startTime
     * @param endTime
     * @param storeName
     * @param footage
     * @return
     */
    public List<ResultData> storeDataAnalysis(Set<String> nameSet,
                                              String startTime, String endTime, String storeName, String footage) {
        List<ResultData> resultDatalist = new ArrayList<ResultData>();
        if (!"".equals(startTime)) {
            startTime = DateUtils.dateTime(startTime);
            startTime = startTime + " 00:00:00";
        }
        if (!"".equals(endTime)) {
            endTime = DateUtils.dateTime(endTime);
            endTime = endTime + " 23:59:59";
        }
        String statUninx = DateUtils.date2TimeStamp(startTime, "yyyy-MM-dd HH:mm:ss");
        String endUninx = DateUtils.date2TimeStamp(endTime, "yyyy-MM-dd HH:mm:ss");
        for (String stockxStore : nameSet) {
            ResultData resultDataes = new ResultData();
            resultDataes.setProductName(storeName);
            resultDataes.setStoreName(stockxStore);
            //根据商品名查询所有结果
            List<ResultData> reslist = this.findResultDataAll(resultDataes);
            String nowDate = "";
            for (int x = 0; reslist.size() > x; x++) {
                ResultData res = reslist.get(reslist.size() - (x + 1));
                if (!"".equals(nowDate)) {
                    String createDate = DateUtils.compareDateDown(nowDate, res.getCreateTime());
                    if (nowDate.equals(createDate)) {
                        String sizePric = res.getSizePrice().substring(1, res.getSizePrice().length() - 1);
                        String[] strArr = sizePric.split(",");
                        List<String[]> list = new ArrayList<String[]>();
                        String[] sizePrices = new String[]{};
                        int i = 0;
                        //循环取鞋码价格
                        for (int z = 0; z < strArr.length; z++) {
                            String[] sizePrice = strArr[z].split("=");
                            sizePrice = this.taobaoShoeCodeRule(sizePrice, footage);
                            //判断尺码是否相等
                            if (sizePrice[0].contains(footage)) {
                                sizePrices = sizePrice;
                                i++;
                                list.add(sizePrices);
                            }
                        }
                        if (i >= 1) {
                            List<ResultData> resultDataListxs = this.shopName(list, res, footage);
                            for (ResultData resultData : resultDataListxs) {
                                if (resultData != null) {
                                    int ifNum = 0;
                                    for (ResultData re : resultDatalist) {
                                        if (re.getStoreName().equals(resultData.getStoreName()) && re.getSizePrice().equals(resultData.getSizePrice())) {
                                            ifNum = 1;
                                        }
                                    }
                                    if (ifNum != 1) {
                                        //判断时间statUninx endUninx
                                        Long statUninxL = Long.valueOf(0);
                                        Long endUninxL = Long.valueOf(2099999999);
                                        if (!"".equals(statUninx)) {
                                            statUninxL = Long.parseLong(statUninx);
                                        }
                                        if (!"".equals(endUninx)) {
                                            endUninxL = Long.parseLong(endUninx);
                                        }
                                        Long nowDateUninx = Long.parseLong(DateUtils.date2TimeStamp(resultData.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                                        if (statUninxL < nowDateUninx && nowDateUninx < endUninxL) {
                                            resultDatalist.add(resultData);
                                        }

                                    }

                                }
                            }
                        }
                    }
                } else {
                    String sizePric = res.getSizePrice().substring(1, res.getSizePrice().length() - 1);
                    String[] strArr = sizePric.split(",");
                    List<String[]> list = new ArrayList<String[]>();
                    String[] sizePrices = new String[]{};
                    int i = 0;
                    //循环取鞋码价格
                    for (int z = 0; z < strArr.length; z++) {
                        String[] sizePrice = strArr[z].split("=");
                        sizePrice = this.taobaoShoeCodeRule(sizePrice, footage);
                        //判断尺码是否相等
                        if (sizePrice[0].contains(footage)) {
                            sizePrices = sizePrice;
                            i++;
                            list.add(sizePrices);
                        }
                    }
                    if (i >= 1) {
                        List<ResultData> resultDataList = this.shopName(list, res, footage);
                        for (ResultData resultData : resultDataList) {
                            if (resultData != null) {
                                //判断时间statUninx endUninx
                                Long statUninxL = Long.valueOf(0);
                                Long endUninxL = Long.valueOf(2099999999);
                                if (!"".equals(statUninx)) {
                                    statUninxL = Long.parseLong(statUninx);
                                }
                                if (!"".equals(endUninx)) {
                                    endUninxL = Long.parseLong(endUninx);
                                }
                                Long nowDateUninx = Long.parseLong(DateUtils.date2TimeStamp(resultData.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                                if (statUninxL < nowDateUninx && nowDateUninx < endUninxL) {
                                    resultDatalist.add(resultData);
                                }
                                nowDate = res.getCreateTime().substring(0, 10) + " 00:00:00";
                            }
                        }
                    }
                }
            }
        }
        return resultDatalist;
    }

    /**
     * 店铺尺码月趋势
     *
     * @param record
     * @param footage
     * @param statUninx
     * @param endUninx
     * @return
     */
    public List<ResultData> getResultDataList(ResultData record, String footage, String statUninx, String endUninx) {
        List<ResultData> resultDatalist = new ArrayList<ResultData>();
        List<ResultData> reslist = this.findResultDataAll(record);
        String nowDate = "";
        for (int x = 0; reslist.size() > x; x++) {
            ResultData res = reslist.get(reslist.size() - (x + 1));
            if (!"".equals(nowDate)) {
                String createDate = DateUtils.compareDateDown(nowDate, res.getCreateTime());
                if (nowDate.equals(createDate)) {
                    String sizePric = res.getSizePrice().substring(1, res.getSizePrice().length() - 1);
                    String[] strArr = sizePric.split(",");
                    List<String[]> list = new ArrayList<String[]>();
                    String[] sizePrices = new String[]{};
                    int i = 0;
                    //循环取鞋码价格
                    for (int z = 0; z < strArr.length; z++) {
                        String[] sizePrice = strArr[z].split("=");
                        sizePrice = this.taobaoShoeCodeRule(sizePrice, footage);
                        //判断尺码是否相等
                        if (sizePrice[0].contains(footage)) {
                            sizePrices = sizePrice;
                            i++;
                            list.add(sizePrices);
                        }
                    }
                    if (i >= 1) {
                        List<ResultData> resultDataListxs = this.shopName(list, res, footage);
                        for (ResultData resultData : resultDataListxs) {
                            if (resultData != null) {
                                int ifNum = 0;
                                for (ResultData re : resultDatalist) {
                                    if (re.getStoreName().equals(resultData.getStoreName()) && re.getSizePrice().equals(resultData.getSizePrice())) {
                                        ifNum = 1;
                                    }
                                }
                                if (ifNum != 1) {
                                    resultDatalist.add(resultData);
                                }

                            }
                        }
                    }
                }
            } else {
                String sizePric = res.getSizePrice().substring(1, res.getSizePrice().length() - 1);
                String[] strArr = sizePric.split(",");
                List<String[]> list = new ArrayList<String[]>();
                String[] sizePrices = new String[]{};
                int i = 0;
                //循环取鞋码价格
                for (int z = 0; z < strArr.length; z++) {
                    String[] sizePrice = strArr[z].split("=");
                    sizePrice = this.taobaoShoeCodeRule(sizePrice, footage);
                    //判断尺码是否相等
                    if (sizePrice[0].contains(footage)) {
                        sizePrices = sizePrice;
                        i++;
                        list.add(sizePrices);
                    }
                }
                if (i >= 1) {
                    List<ResultData> resultDataList = this.shopName(list, res, footage);
                    for (ResultData resultDatax2 : resultDataList) {
                        if (resultDatax2 != null) {
                            resultDatalist.add(resultDatax2);
                            nowDate = res.getCreateTime().substring(0, 10) + " 00:00:00";
                        }
                    }
                }
            }
        }
        return resultDatalist;
    }

    /**
     * 尺码数据分析
     *
     * @param storeName
     * @param productName
     * @param footage
     * @param startTime
     * @param endTime
     * @param sizeView
     * @return
     */
    public Map<String, Object> getResultDataLineGraph(String storeName, String productName, String footage,
                                                      String startTime, String endTime, String sizeView) {
        if (!"".equals(startTime)) {
            startTime = DateUtils.dateTime(startTime);
            startTime = startTime + " 00:00:00";
        }
        if (!"".equals(endTime)) {
            endTime = DateUtils.dateTime(endTime);
            endTime = endTime + " 23:59:59";
        }
        String statUninx = DateUtils.date2TimeStamp(startTime, "yyyy-MM-dd HH:mm:ss");
        String endUninx = DateUtils.date2TimeStamp(endTime, "yyyy-MM-dd HH:mm:ss");
        String[] sizeViews = sizeView.split(",");
        ResultData resultData = new ResultData();
        resultData.setStoreName(storeName);
        resultData.setProductName(productName);
        //获取该店铺商品数据
        List<ResultData> resultDataList = this.findResultDataAll(resultData);
        //时间
        List<Object> timeList = new ArrayList<Object>();
        //价格
        List<Object> priceList = new ArrayList<Object>();
        List<String[]> rePriceList = new ArrayList<String[]>();
        if (resultDataList.size() != 0) {
            //遍历结果集
            for (String size : sizeViews) {
                String[] strResult = new String[1024];
                String strTime = "";
                int xnum = 0;
                for (ResultData reData : resultDataList) {
                    String sizePric = reData.getSizePrice().substring(1, reData.getSizePrice().length() - 1);
                    String[] strArr = sizePric.split(",");
                    int i = 0;
                    if (i < 30) {
                        i++;
                        int x = 0;
                        List<String[]> list = new ArrayList<String[]>();
                        String[] sizePrices = new String[]{};
                        //循环取鞋码价格
                        for (int z = 0; z < strArr.length; z++) {
                            String[] sizePrice = strArr[z].split("=");
                            sizePrice = this.taobaoShoeCodeRule(sizePrice, footage);
                            //判断尺码是否相等
                            if (sizePrice[0].contains(footage)) {
                                //校验是否匹配选中的类型
                                if (sizePrice[0].equals(size)) {
                                    x++;
                                    sizePrices = sizePrice;
                                    list.add(sizePrices);
                                }
                            }
                        }
                        if (x >= 1) {
                            reData = this.individualPrice(list, reData, footage);
                            if (reData != null) {
                                //判断时间
                                Long statUninxL = Long.valueOf(0);
                                Long endUninxL = Long.valueOf(2099999999);
                                if (!"".equals(statUninx)) {
                                    statUninxL = Long.parseLong(statUninx);
                                }
                                if (!"".equals(endUninx)) {
                                    endUninxL = Long.parseLong(endUninx);
                                }
                                Long createTime = Long.parseLong(DateUtils.date2TimeStamp(reData.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                                if (statUninxL < createTime && createTime < endUninxL) {
                                    timeList.add(reData.getCreateTime());
                                    strResult[xnum] = reData.getReservedField() + "!" + reData.getCreateTime();
                                    xnum++;
                                }

                            }
                        }
                    }
                }
                if (!"".equals(strResult[0]) && strResult[0] != null) {
                    String resultStr = "";
                    for (String str : strResult) {
                        if ("".equals(str) || str == null) {
                            break;
                        }
                        resultStr = resultStr + str + ",";
                    }
                    resultStr = resultStr.substring(0, resultStr.length() - 1);
                    String[] resultPrices = resultStr.split(",");
                    rePriceList.add(resultPrices);
                }
            }
        }
        List<String[]> result = new ArrayList<String[]>();
        for (String[] prices : rePriceList) {
            String[] resuPrices = new String[1024];
            String resuStr = "";
            String[] priSS = new String[2];
            int ii = 0;
            for (Object time : timeList) {
                int iix = 0;
                for (String pri : prices) {
                    String times = String.valueOf(time);
                    priSS = pri.split("!");
                    //如果时间匹配则存在
                    if (times.equals(priSS[1])) {
                        iix = 1;
                    }
                }
                if (iix == 1) {
                    resuPrices[ii] = priSS[0];
                    resuStr = resuStr + priSS[0] + ",";
                } else {
                    resuPrices[ii] = "0";
                    resuStr = resuStr + 0 + ",";
                }
                ii++;
            }
            resuStr = resuStr.substring(0, resuStr.length() - 1);
            result.add(resuPrices);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("result", result);
        params.put("sizeViewsResult", sizeViews);
        params.put("timeList", timeList);
        params.put("priceList", priceList);
        return params;
    }

}
