package com.vstock.front.service;

import com.vstock.db.dao.IPaymentDao;
import com.vstock.db.entity.Payment;
import com.vstock.ext.util.security.md.ToolMD5;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    final static Logger logger = Logger.getLogger(PaymentService.class);

    @Autowired
    IPaymentDao paymentDao;

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(Payment record){return paymentDao.insert(record);}

    /**
     * 创建支付
     * @return
     */
    public int cteatePay(Payment record,String md5Key){
        String sign = ToolMD5.encodeMD5Hex(new StringBuilder()
                .append(Payment.PAY_MD5_MARK_NOTIFY).append("payment_user_id=").append(record.getPayment_user_id())
                .append(Payment.PAY_MD5_MARK_NOTIFY).append("payment_type=").append(record.getPayment_type())
                .append(Payment.PAY_MD5_MARK_NOTIFY).append("payment_money=").append(record.getPayment_money())
                .append(Payment.PAY_MD5_MARK_NOTIFY).append("Md5Sign=").append(md5Key)
                .toString());
        if(record.getPayment_money().compareTo(BigDecimal.ZERO)==-1){
            logger.warn("支付金额小于0");
            return 0;
        }
        record.setSign(sign);
        return insert(record);
    }
}
