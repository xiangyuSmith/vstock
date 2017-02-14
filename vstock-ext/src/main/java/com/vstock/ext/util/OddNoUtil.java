package com.vstock.ext.util;

import com.vstock.db.entity.Refund;

import java.util.Date;

/**
 * 单号公共方法
 */
public class OddNoUtil {

    public static int no = 100;

    /**
     * 获取退货单单号
     * @return
     */
    public static String refundNo(){
        Long time =  DateUtils.getDate(DateUtils.dateToString(new Date()),"yyyy-MM-dd HH:mm:ss").getTime();
        String refundNo = String.valueOf(time)+ no;
        no++;
        if (no == 999){no=0;}
        return Refund.refNo + refundNo;
    }
}
