package com.vstock.admin.service;

import com.vstock.db.dao.IRefundDao;
import com.vstock.db.entity.Bid;
import com.vstock.db.entity.Refund;
import com.vstock.db.entity.Trade;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.OddNoUtil;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RefundService {
    @Autowired
    IRefundDao refundDao;

    @Autowired
    BidService bidService;

    @Autowired
    TradeService tradeService;

    /**
     * 查询所有记录
     * @param record
     * @return
     */
    public List<Refund> findAll(Refund record, Page page){
        return refundDao.findAll(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * 查询一个对象
     * @param record
     * @return
     */
    public Refund find(Refund record){return refundDao.find(record);}

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(Refund record){return refundDao.findCount(record);}

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(Refund record){return refundDao.insert(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(Refund record){return refundDao.update(record);}

    /**
     * 根据时间区间分页查询
     * @param record    对象
     * @param page      分页
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public List<Refund> findAllDate(Refund record,Page page,String startTime,String endTime){
        return refundDao.findAllDate(record,startTime,endTime,page.getStartPos(),page.getPageSize());
    }

    /**
     * 根据时间区间查询所有
     * @param record       对象
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @return
     */
    public int findCountDate(Refund record,String startTime,String endTime){return refundDao.findCountDate(record,startTime,endTime);}

    /**
     * 保存方法
     * @param record
     * @return
     */
    public int save(Refund record){
        if (record.getId() != null){
            return this.update(record);
        }else {
            if (record.getCreateDate() == null || "".equals(record.getCreateDate())){
                record.setCreateDate(DateUtils.getCurrentTimeAsString());
            }
            if (record.getRefundNo() == null || "".equals(record.getRefundNo())){
                record.setRefundNo(OddNoUtil.refundNo());
            }
            return this.insert(record);
        }
    }

    /**
     * 验货不合格退回金额，生成退款单
     * @param record
     * @return
     */
    public int refundLiquidated(Trade record, String remarks){
        int i = 0;
        Bid bid = new Bid();
        bid.setId(record.getBidId());
        Page page = new Page(10,"1");
        List<Bid> bidList = bidService.findAll(bid,page);
        for (int a = 0; a <= bidList.size(); a++ ) {
            Refund refund = new Refund();
            refund.setTradeNo(record.getTradeNo());
            refund.setRefundObj("1");
            refund.setBtfId(record.getBasicinformationId());
            refund.setBtfName(record.getBftName());
            if (a == 0) {
                refund.setRefundPrice(record.getTransactionMoney().add(record.getTradeFreight()));
                refund.setType("0");
            }else {
                refund.setRefundPrice(bidList.get(0).getBidBond());
                refund.setType("6");
            }
            refund.setRemarks(remarks);
            refund.setStatus("0");
            i = this.save(refund);
        }
        return i;
    }

    /**
     * 传入ID和类型，查询退款的金额
     * @param id
     * @param type
     * @return
     */
    public BigDecimal refundAmount(int id, int type){
        BigDecimal amount = new BigDecimal(0);
        if (type == 1){
            Bid record = new Bid();
            record.setId(id);
            Page page = new Page(10,"1");
            List<Bid> bidList = bidService.findAll(record,page);
            if (bidList.size() > 0){
                amount = bidList.get(0).getBidBond();
            }
        }else{
            Trade record = new Trade();
            record.setId(id);
            List<Trade> tradeList = tradeService.findModel(record);
            if (tradeList.size() > 0) {
                if (type == 0) {
                    amount = tradeList.get(0).getTransactionMoney();
                }else{
                    amount = tradeList.get(0).getBid().getBidBond();
                }
            }
        }
        return amount;
    }

    public String linkAddress(Refund record,String startTime,String endTime, String linkAddress){
        if (startTime != null && !"".equals(startTime)){
            linkAddress = linkAddress + "&startTime="+startTime;
        }
        if (endTime != null && !"".equals(endTime)){
            linkAddress = linkAddress + "&endTime="+endTime;
        }
        if (record.getRefundNo() != null && !"".equals(record.getRefundNo())){
            linkAddress = linkAddress + "&refundNo="+record.getRefundNo();
        }
        if (record.getStatus() != null && !"".equals(record.getStatus())){
            linkAddress = linkAddress + "&status="+record.getStatus();
        }
        if (record.getType() != null && !"".equals(record.getType())){
            linkAddress = linkAddress + "&type()="+record.getType();
        }
        if (record.getRefundObj() != null && !"".equals(record.getRefundObj())){
            linkAddress = linkAddress + "&refundObj()="+record.getRefundObj();
        }
        if (record.getBtfName() != null && !"".equals(record.getBtfName())){
            linkAddress = linkAddress + "&btfName()="+record.getBtfName();
        }
        if (record.getTradeNo()!= null && !"".equals(record.getTradeNo())){
            linkAddress = linkAddress + "&tradeNo()="+record.getTradeNo();
        }
        return linkAddress;
    }

}
