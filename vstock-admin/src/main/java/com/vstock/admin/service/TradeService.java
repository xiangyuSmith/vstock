package com.vstock.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.vstock.db.dao.IPricePeakDao;
import com.vstock.db.dao.ITradeDao;
import com.vstock.db.entity.*;
import com.vstock.ext.util.Page;
import com.vstock.ext.util.StringUtil;
import com.vstock.server.express.ExpressLogistics;
import com.vstock.server.ihuyi.Sendsms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by administor on 2016/12/7.
 */
@Service
public class TradeService {
    @Autowired
    ITradeDao tradeDao;

    @Autowired
    IPricePeakDao pricePeakDao;

    @Autowired
    BasicinformationService basicinformationService;

    @Autowired
    LogisticsInformationService logisticsInformationService;

    @Autowired
    UserService userService;
    @Autowired
    RefundService refundService;
    @Autowired
    BackCommodityService backCommodityService;

    /**
     * 分页查询所有记录
     * @param record
     * @param page
     * @return
     */
    public List<Trade> findAll(Trade record, Page page){
        return tradeDao.findAll(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * 根据时间区间分页查询
     * @param record
     * @param page
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Trade> findAllDate(Trade record, Page page, String startDate, String endDate){
        return tradeDao.findAllDate(record,page.getStartPos(),page.getPageSize(),startDate,endDate);
    }

    public List<Trade> findAndBid(Trade record, Page page){return tradeDao.findAndBid(record,page.getStartPos(),page.getPageSize());}

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(Trade record){return tradeDao.findCount(record);}

    /**
     * 根据时间区间查询总数
     * @param record
     * @param startDate
     * @param endDate
     * @return
     */
    public int findCountDate(Trade record, String startDate, String endDate){return tradeDao.findCountDate(record,startDate,endDate);}

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(Trade record){return tradeDao.insert(record);}

    /**
     * 修改所有
     * @param record
     * @return
     */
    public int updateAll(Trade record){return tradeDao.updateAll(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(Trade record){
        Trade t = new Trade();
        t.setId(record.getId());
        t.setStatus(record.getStatus());
        t.setUpdateDate(record.getUpdateDate());
        return tradeDao.update(t);
    }

    /**
     * 后台弹窗用查询，固定查询状态为待发货、检验失败、交易完成订单
     * @param record
     * @return
     */
    public List<Trade> findModel(Trade record){return tradeDao.findModel(record);}

    //个人中心出售查询
    public Map<Page,List<Trade>> dateFindAll(Trade record, String startDate, String endDate, String pageNow){
        Map<Page,List<Trade>> param = new HashMap<>();
        try {
            if (startDate != null && !"".equals(startDate)){
                startDate = startDate + " 00:00:00";
            }
            if (endDate != null && !"".equals(endDate)){
                endDate = endDate + " 23:59:59";
            }
            if (pageNow == null || "".equals(pageNow)){
                pageNow = "1";
            }
            if (record.getIsBond() != null) {
                if (record.getIsBond() == -1) {
                    record.setIsBond(null);
                }
            }
            if (record.getStatus() != null) {
                if (record.getStatus() == -1) {
                    record.setStatus(null);
                }
            }
            int dCListCount = tradeDao.findAdminCountDate(record,startDate,endDate);
            Page page = new Page(dCListCount, pageNow);
            List<Trade> tradeList = tradeDao.findAdminAllDate(record,page.getStartPos(),page.getPageSize(),startDate,endDate);
            param.put(page,tradeList);
            return param;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    //拼接查詢條件
    public String linkAddress(String linkAddress, Trade record, String startDate, String endDate){
        if (startDate != null && !"".equals(startDate)){
            linkAddress = linkAddress + "&startTime=" + startDate;
        }
        if (endDate != null && !"".equals(endDate)){
            linkAddress = linkAddress + "&endTime=" + endDate;
        }
        if (record.getSellerName() != null && !"".equals(record.getSellerName())){
            linkAddress = linkAddress + "&bidId=" + record.getSellerName();
        }
        if (record.getBuyersName() != null && !"".equals(record.getBuyersName())){
            linkAddress = linkAddress + "&buyersId=" + record.getBuyersName();
        }
        if (record.getBftName() != null && !"".equals(record.getBftName())){
            linkAddress = linkAddress + "&basicinformationId=" + record.getBftName();
        }
        if (record.getBftSize() != null){
            linkAddress = linkAddress + "&bftSize=" + record.getBftSize();
        }
        if (record.getStatus() != null && !"".equals(record.getStatus())){
            linkAddress = linkAddress + "&status=" + record.getStatus();
        }
        if (record.getIsBond() != null && !"".equals(record.getIsBond())){
            linkAddress = linkAddress + "&isBond=" + record.getIsBond();
        }
        return linkAddress;
    }

    //获取订单状态
    public List<List<String>> status(){
        List<List<String>> statusList = new ArrayList<List<String>>();
        for (String str : Trade.statuses){
            List<String> strList = new ArrayList<String>();
            String[] status = str.split(":");
            strList.add(status[0]);
            strList.add(status[1]);
            statusList.add(strList);
        }
        return statusList;
    }

    public Trade btfUser(Trade trade){
        if (trade.getBuyersName() != null && !"".equals(trade.getBuyersName())){
            User user = new User();
            user.setNick(trade.getBuyersName());
            user = userService.findUser(user);
            trade.setBuyersId(Integer.parseInt(user.getId()));
        }
        if (trade.getSellerName() != null && !"".equals(trade.getSellerName())){
            User user = new User();
            user.setNick(trade.getSellerName());
            user = userService.findUser(user);
            trade.setSellerId(Integer.parseInt(user.getId()));
        }
        if (trade.getBftName() != null && !"".equals(trade.getBftName())){
            Basicinformation basicinformation = new Basicinformation();
            basicinformation.setName(trade.getBftName());
            List<Basicinformation> btfList = basicinformationService.findAll(basicinformation);
            basicinformation = btfList.get(0);
            trade.setBasicinformationId(Integer.parseInt(basicinformation.getId()));
        }
        return trade;
    }

    //查询对象
    public Trade findTrade(Trade record){
        Page page = new Page(10,"1");
        List<Trade> tradeList = this.findAndBid(record, page);
        return  tradeList.get(0);
    }

    //保存方法
    public int save(Trade record){
        if (record.getId() == null && "".equals(record.getId())){
            return this.insert(record);
        }else {
            String remarks = "";
            if (record.getSign() != null || !"".equals(record.getSign())){
                remarks = record.getSign();
                record.setSign(null);
            }
            int i = this.updateAll(record);
            //判断做检验通过和失败时，获取上一个物流信息存储
            if (21 == record.getStatus() || 20 == record.getStatus()){
                LogisticsInformation logisticsInformation = new LogisticsInformation();
                logisticsInformation.setTradeId(record.getId());
                List<LogisticsInformation> logisticsInformationList = logisticsInformationService.findAll(logisticsInformation);
                if (logisticsInformationList.size() > 0) {
                    //传入快递公司名称，转化为快递100接口参数名
                    String expresName = StringUtil.expressName(logisticsInformationList.get(0).getCompanyName());
                    //调用快递100接口获取物流信息
                    JSONObject jsonObject = ExpressLogistics.getexpress(expresName,logisticsInformationList.get(0).getCourierNumber());
                    logisticsInformation = logisticsInformationList.get(0);
                    Object data = jsonObject.get("data");
                    logisticsInformation.setInformation(data.toString());
                    i = logisticsInformationService.save(logisticsInformation);
                }
            }
            if (21 == record.getStatus()){
                Trade trade = new Trade();
                trade.setId(record.getId());
                record = this.findTrade(trade);
                int a = refundService.refundLiquidated(record,remarks);
                if (a > 0) {
                    //验货失败
                    User user = new User();
                    user.setId(String.valueOf(record.getSellerId()));
                    user.setStatus(0);
                    userService.update(user);
                    i = backCommodityService.savefail(record, remarks);
                }
            }
            return i;
        }
    }

//    public String getSgin(Trade trade){
//        String sign = ToolMD5.encodeMD5Hex(new StringBuilder()
//                .append("trade_no=").append(trade.getTrandeNo())
//                .append(Trade.TRADE_MD5_MARK_NOTIFY).append("bid_id=").append(trade)
//                .append(Trade.TRADE_MD5_MARK_NOTIFY).append("transaction_money=").append(trade.getTransactionMoney())
//                .append(Trade.TRADE_MD5_MARK_NOTIFY).append("bft_size=").append(trade.getBftSize())
//                .append(Trade.TRADE_MD5_MARK_NOTIFY).append("Md5Sign=").append(VstockConfigService.getConfig(IVstockConfigService.TRADE__BOGE_VSTOCK_MD5KEY))
//                .toString());
//    }

    //峰值表关联鞋库信息
    public List<PricePeak> findAndBft (PricePeak record, Page page){
        return pricePeakDao.findAndBft(record,page.getStartPos(),page.getPageSize());
    }

    //峰值表关联鞋库总数
    public int findBftCount(PricePeak record){return pricePeakDao.findBftCount(record);}

    public String pricelinkAddress(String linkAddress, PricePeak record){
        if (record.getPeakSize() != null && !"".equals(record.getPeakSize())){
            linkAddress = linkAddress + "&peakSize=" + record.getPeakSize();
        }
        if (record.getBasicinformation() != null && !"".equals(record.getBasicinformation())) {
            if (record.getBasicinformation().getArtNo() != null && !"".equals(record.getBasicinformation().getArtNo())) {
                linkAddress = linkAddress + "&artNo=" + record.getBasicinformation().getArtNo();
            }
            if (record.getBasicinformation().getName() != null && !"".equals(record.getBasicinformation().getName())) {
                linkAddress = linkAddress + "&name=" + record.getBasicinformation().getName();
            }
        }
        return linkAddress;
    }

    /**
     * 验证失败
     * @param record   订单对象
     * @param key      短信接口密钥
     * @param account  短信账号
     */
    public void failSendsms(Trade record, String key, String account){
        User user = new User();
        user.setId(record.getSellerId().toString());
        user = userService.findUser(user);
        String content = "您出售的鞋子"+record.getBftName()+" ，经v-stock鉴定为不合格。您的鞋子将被我们退回，交易保证金将作为违约金赔偿给买家。您将被取消在v-stock的卖家资格。如有疑问请及时联系v-stock客服。";
        Sendsms.sendHuyi(user.getMobile(),account,key,content);
        user = new User();
        user.setId(record.getBuyersId().toString());
        user = userService.findUser(user);
        content = "您购买的鞋子"+record.getBftName()+" ，经v-stock鉴定为不合格。我们会将鞋子退回给卖家，并赔偿违约金给您。如有疑问请联系v-stock客服。";
        Sendsms.sendHuyi(user.getMobile(),account,key,content);
    }

    /**
     * 验证成功
     * @param record    订单对象
     * @param key       短信接口密钥
     * @param account   短信账号
     */
    public void successSendsms(Trade record, String key, String account){
        User user = new User();
        user.setId(record.getSellerId().toString());
        user = userService.findUser(user);
        String content = "您出售的鞋子"+record.getBftName()+" ，经v-stock鉴定为真。我们将尽快为买家发货。如有疑问请联系v-stock客服。";
        Sendsms.sendHuyi(user.getMobile(),account,key,content);
        user = new User();
        user.setId(record.getBuyersId().toString());
        user = userService.findUser(user);
        content = "您购买的鞋子"+record.getBftName()+" ，经v-stock鉴定为真。我们将尽快为您发货，请注意查收。如有疑问请联系v-stock客服。";
        Sendsms.sendHuyi(user.getMobile(),account,key,content);
    }

}
