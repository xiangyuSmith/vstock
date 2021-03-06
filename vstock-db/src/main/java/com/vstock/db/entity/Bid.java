package com.vstock.db.entity;

import com.vstock.db.dto.LabelDto;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Bid implements Serializable {


    public final static String BID_MD5_MARK = "|";
    public final static String BID_MD5_MARK_NOTIFY = "~|~";


    public final static int STATUS_PENDING = 0; // 待付款
    public final static int STATUS_CLEAN = 1; //   已关闭
    public final static int STATUS_PAY_FAIL = 2; // 支付失败
    public final static int STATUS_INIT = 10; // 叫价中
    public final static int STATUS_OVERDUE = 11; // 已过期
    public final static int STATUS_LOCKING = 20; // 已锁定
    public final static int STATUS_SUCCESS = 30; // 已成交
    public final static int STATUS_DELETE = 40; // 已删除

    public final static String[] statusStr = {"0:待支付","1:已关闭","2:支付失败","10:叫价中","11:已过期","20:已锁定","30:已交易","40:已删除","50:已过期","51:已过期"};
    public final static String[] bidStatus = {"0:待支付","1:已关闭","2:支付失败","10:叫价中","11:已过期","20:已锁定","30:已交易","40:已删除","50:退款中","51:退款成功"};

    public final static String TIME_ONE = "1";
    public final static String TIME_THREE = "3";
    public final static String TIME_FIVE = "5";
    public final static String TIME_SERVEN = "7";

    @Id
    private Integer id;
    private Integer userId;
    private Integer basicinformationId;
    private Integer paymentId;
    private String bftName;
    private String bftSize;
    private BigDecimal bidMoney;
    private BigDecimal bidFreight;
    private BigDecimal bidBond;
    private BigDecimal latelyBid;
    private String termValidity;
    private String status;
    private String type;
    private String sign;
    private String bidDate;
    private String invalidDate;
    private BigDecimal highestBid;
    private BigDecimal minimumSellingPrice;
    private Basicinformation basicinformation;
    private String name;
    private User user;

    public Bid() {
        super();
    }

    public Bid(Integer id, Integer userId, Integer basicinformationId, Integer paymentId, String bftName, String bftSize, BigDecimal bidMoney, BigDecimal bidFreight, BigDecimal bidBond, BigDecimal latelyBid, String termValidity, String status, String type, String sign, String bidDate, String invalidDate, BigDecimal highestBid, BigDecimal minimumSellingPrice, Basicinformation basicinformation, String name, User user) {
        this.id = id;
        this.userId = userId;
        this.basicinformationId = basicinformationId;
        this.paymentId = paymentId;
        this.bftName = bftName;
        this.bftSize = bftSize;
        this.bidMoney = bidMoney;
        this.bidFreight = bidFreight;
        this.bidBond = bidBond;
        this.latelyBid = latelyBid;
        this.termValidity = termValidity;
        this.status = status;
        this.type = type;
        this.sign = sign;
        this.bidDate = bidDate;
        this.invalidDate = invalidDate;
        this.highestBid = highestBid;
        this.minimumSellingPrice = minimumSellingPrice;
        this.basicinformation = basicinformation;
        this.name = name;
        this.user = user;
    }

    public Bid(Integer basicinformationId, String bftSize, BigDecimal bidMoney, Integer userId){
        this.userId = userId;
        this.basicinformationId = basicinformationId;
        this.bftSize = bftSize;
        this.bidMoney = bidMoney;
    }

    public Basicinformation getBasicinformation() {
        return basicinformation;
    }

    public void setBasicinformation(Basicinformation basicinformation) {
        this.basicinformation = basicinformation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBasicinformationId() {
        return basicinformationId;
    }

    public void setBasicinformationId(Integer basicinformationId) {
        this.basicinformationId = basicinformationId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getBftName() {
        return bftName;
    }

    public void setBftName(String bftName) {
        this.bftName = bftName;
    }

    public String getBftSize() {
        return bftSize;
    }

    public void setBftSize(String bftSize) {
        this.bftSize = bftSize;
    }

    public BigDecimal getBidMoney() {
        return bidMoney;
    }

    public void setBidMoney(BigDecimal bidMoney) {
        this.bidMoney = bidMoney;
    }

    public BigDecimal getBidFreight() {
        return bidFreight;
    }

    public void setBidFreight(BigDecimal bidFreight) {
        this.bidFreight = bidFreight;
    }

    public BigDecimal getBidBond() {
        return bidBond;
    }

    public void setBidBond(BigDecimal bidBond) {
        this.bidBond = bidBond;
    }

    public BigDecimal getLatelyBid() {
        return latelyBid;
    }

    public void setLatelyBid(BigDecimal latelyBid) {
        this.latelyBid = latelyBid;
    }

    public String getTermValidity() {
        return termValidity;
    }

    public void setTermValidity(String termValidity) {
        this.termValidity = termValidity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBidDate() {
        return bidDate;
    }

    public void setBidDate(String bidDate) {
        this.bidDate = bidDate;
    }

    public String getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    public BigDecimal getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(BigDecimal highestBid) {
        this.highestBid = highestBid;
    }

    public BigDecimal getMinimumSellingPrice() {
        return minimumSellingPrice;
    }

    public void setMinimumSellingPrice(BigDecimal minimumSellingPrice) {
        this.minimumSellingPrice = minimumSellingPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LabelDto getStatusLbl(int status) {
        switch (status) {
            case STATUS_INIT:
                return new LabelDto("叫价中", "warning");
            case STATUS_OVERDUE:
                return new LabelDto("已过期", "default");
            case STATUS_PENDING:
                return new LabelDto("代付款", "default");
            case STATUS_LOCKING:
                return new LabelDto("已锁定", "warning");
            case STATUS_SUCCESS:
                return new LabelDto("交易成功", "secondary");
            default:
                return new LabelDto("待付款", "secondary");
        }
    }
}
