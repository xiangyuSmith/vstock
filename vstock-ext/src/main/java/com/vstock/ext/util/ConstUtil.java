package com.vstock.ext.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by sunson on 2015/11/22.
 * standard deviation
 */
public final class ConstUtil {

    public final static String DATE_FULL_FORMAT_PATTERN = "yyyyMMddHHmmssSSS";
    public final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static void main(String args[]) {
        System.out.println("平均数" + "\t\t\t\t" + "标准差");

        double[] num = {712, 675, 1012, 650, 700, 675, 665, 616, 590, 700, 712, 650, 675, 650, 675, 800, 650, 650, 616};
        double[] num2 = {200, 250, 150, 250, 210, 250, 250, 275, 275, 275, 275, 250, 250, 275, 250, 275, 275, 275};
        test(num2);
    }

    public static void test(double[] array) {
        double sum = 0, k = 0;
        int i = 0;
        double s = 0;
        for (i = 0; i < array.length; i++) {
            k = array[i];
            sum += k;
            s += Math.pow(k - (double) sum / i, 2) / i;
        }
        System.out.print((double) sum / i);

        double mean = sum / i;
        double var = 0;
        for (double d : array) {
            var += (d - mean) * (d - mean);
        }
//        s +=Math.pow(k-sum/i,2)/i;
        System.out.println("\t" + var / i);
    }

    /**
     * 读取系统的属性文件
     *
     * @return
     */
    public static Properties getProperties() {
        Properties prop = new Properties();
        try {
            InputStream in = ConstUtil.class.getResourceAsStream("/vstock.properties");
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * 读取爬虫的属性文件
     *
     * @return
     */
    public static Properties getSpiderProperties() {
        Properties prop = new Properties();
        try {
            InputStream in = ConstUtil.class.getResourceAsStream("/spider.properties");
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
