package com.vstock.admin.service;

import com.vstock.db.dao.ITradeDao;
import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.Trade;
import com.vstock.db.entity.User;
import com.vstock.ext.util.Page;
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
    BasicinformationService basicinformationService;

    @Autowired
    UserService userService;

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
     * 修改
     * @param record
     * @return
     */
    public int update(Trade record){
        return tradeDao.update(record.getStatus(),record.getEndDate(),record.getId());
    }

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
            int dCListCount = this.findCountDate(record,startDate,endDate);
            Page page = new Page(dCListCount, pageNow);
            List<Trade> tradeList = tradeDao.findAllDate(record,page.getStartPos(),page.getPageSize(),startDate,endDate);
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
            user.setUname(trade.getBuyersName());
            user = userService.findUser(user);
            trade.setBuyersId(Integer.parseInt(user.getId()));
        }
        if (trade.getSellerName() != null && !"".equals(trade.getSellerName())){
            User user = new User();
            user.setUname(trade.getSellerName());
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

    public Trade findTrade(Trade record){
        Page page = new Page(10,"1");
        List<Trade> tradeList = this.findAndBid(record, page);
        return  tradeList.get(0);
    }

}
