package com.vstock.db.entity;


/**
 * Created by xiangyu on 2016/5/5.
 * 数据收集日志
 */
public class TaobaoRepo {

    //商品名
    private String name;

    //链接
    private String author;

    //价格
    private String price;

    //销量
    private String sales;

    //备注
    private String readme;

    public String getName() { return name; }

    public void setName(String name) { this.name = name;}

    public String getAuthor() { return author;}

    public void setAuthor(String author) { this.author = author; }

    public String getPrice() { return price;}

    public void setPrice(String price) { this.price = price; }

    public String getSales() { return sales;}

    public void setSales(String sales) { this.sales = sales; }

    public String getReadme() { return readme; }

    public void setReadme (String readme) { this.readme = readme; }
}
