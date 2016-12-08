<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../layout/head.jsp" %>
    <title>Adidas NMD Nice Kicks</title>
    <style>
        .am-container-content{ max-width: 1000px; margin: auto; }
        .am-bid-border{ border-left:1px solid #595959; }
        .str{ color: #595959; }
        .str-title{ font-size: 22px; }
        .str-price{ font-size: 26px;color: #2d2d2d; }
        .str-size{ font-size: 18px; }
        .product-detail div{ font-size: 18px;color: #595959; }
        .product-market-summary-wrap{ margin-top: 5px;background-color: #f5f5f5; height: 130px;line-height: 130px;margin-top: 15px; }
        .product-market-summary-wrap span { font-size: 35px;color: #595959; }
        .am-table thead tr td{ text-align: center;font-size: 20px;color: #595959; border-bottom: 1px solid #ddd; }
        .am-table tbody tr td{ font-size: 18px;color: #595959; border:none;line-height: 1.8;}
        @media (max-width: 992px){
            .str-sudio span{
                font-size: 16px;
                letter-spacing: 1px;
            }
            .product-market-summary-wrap{ height: 75px;line-height: 75px; }
            .product-market-summary-wrap span { font-size: 22px; }
        }
        .str-title-font{
            font-size: 36px;color: #2d2d2d;letter-spacing: -2px;
        }
        @media (max-width: 1400px){
            .str-title-font{
                font-size: 30px;
            }
        }
    </style>
</head>
<body>
<%@include file="../layout/top.jsp" %>
<article>
    <div class="am-container-content" style="margin-top: 4.2rem">
        <div class="am-g am-u-md-12 am-show-lg-only">
            <span class="str-title-font" style="font-weight: bold;">Air Jordan 1 Retro High OG UNC </span>
            <span class="str-title-font">北卡蓝</span>
        </div>
        <div class="am-g">
            <div class="am-u-lg-4 am-u-md-2 am-u-sm-2 am-margin-top-xl">
                <div class="am-fl am-u-lg-4 am-padding-0 str-sudio">
                    <span class="str-title">尺码</span>
                    <select class="am-input-lg am-form-field" style="margin-top: 25px;">
                        <option>1`</option>
                        <option>1`</option>
                        <option>1`</option>
                    </select>
                </div>
                <div class="am-fr am-u-lg-6 am-padding-0 am-show-lg-only str-sudio ">
                    <span class="str str-title">最后成交价</span><br/>
                    <span class="str str-price">￥800</span><br/>
                    <span class="str str-size">尺码：12</span>
                </div>
            </div>
            <div class="am-u-lg-4 am-u-md-5 am-u-sm-5 am-bid-border am-margin-top-xl">
                <div class="am-fl am-u-lg-6 am-u-md-6 am-u-sm-12 str-sudio">
                    <span class="str str-title">最高出价</span><br/>
                    <span class="str str-price">￥800</span><br/>
                    <span class="str str-size">尺码：12</span>
                </div>
                <button class="am-btn am-btn-lg am-fr am-margin-top-lg am-hide-sm am-margin-right-lg" style="background-color: #3BD379;color: #fff;">出售</button>
            </div>
            <div class="am-u-lg-4 am-u-md-5 am-u-sm-5 am-bid-border am-margin-top-xl">
                <div class="am-fl am-u-lg-6 am-u-md-6 am-u-sm-12 str-sudio">
                    <span class="str str-title">最低叫价</span><br/>
                    <span class="str str-price">￥800</span><br/>
                    <span class="str str-size">尺码：12</span>
                </div>
                <button class="am-btn am-btn-lg am-fr am-margin-top-lg am-hide-sm am-margin-right-lg" style="background-color: #FE5B5F;color: #fff;">购买</button>
            </div>
        </div>
        <div class="am-g am-text-center am-padding-lg">
            <img src="/assets/shoesImg/Adidas%20NMD%20Nice%20Kicks.jpg" style="width:80%;" />
        </div>
        <div class="am-g am-text-center am-hide-sm product-detail" style="margin-top: -70px;margin-bottom: 30px;">
            <div class="am-u-lg-3 am-u-md-12">编码: 717302-600</div>
            <div class="am-u-lg-3 am-u-md-12">颜色: 全白 纯白 </div>
            <div class="am-u-lg-3 am-u-md-12">发售日期: 2016/04/29</div>
            <div class="am-u-lg-3 am-u-md-12">原始售价：￥1080</div>
        </div>
    </div>
</article>
<article class="am-g product-market-summary-wrap">
    <div class="am-container-content am-text-center">
        <span> 20周 </span><span> 最高/最低 ：</span> <span> + 1680 </span><span> - 1210 </span>
    </div>
</article>
<article class="am-g">
    <div class="am-container-content" style="margin-top: 2.4rem">
        <div class="am-u-lg-12 am-margin-bottom-lg">
            <div class="am-text-center">
                <p style="color:#fe5c5c;font-size: 22px;margin-top: 38px; font-size: 30px;">
                    Air Jordan 1 Retro High OG UNC 北卡蓝
                </p>
                <p style="color: #595959;font-size: 18px;">
                    最后销售记录
                </p>
            </div>
        </div>
        <table class="am-table am-table-striped am-table-hover am-text-center">
            <thead>
                <tr>
                    <td>尺码</td>
                    <td>价格</td>
                    <td>日期</td>
                    <td>时间</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>7.5</td>
                    <td>￥1530</td>
                    <td>2016/4/20</td>
                    <td>1:06pm</td>
                </tr>
                <tr>
                    <td>7.5</td>
                    <td>￥1530</td>
                    <td>2016/4/20</td>
                    <td>1:06pm</td>
                </tr>
                <tr>
                    <td>7.5</td>
                    <td>￥1530</td>
                    <td>2016/4/20</td>
                    <td>1:06pm</td>
                </tr>
            </tbody>
        </table>
    </div>
</article>
<article class="am-g am-margin-top-xl">
    <div class="am-container-content">
        <div class="am-u-lg-12">
            <div class="am-text-center">
                <p style="color:#fe5c5c;font-size: 28px;">
                    相关推荐>
                </p>
            </div>
        </div>

    </div>
</article>
<div  style="border-bottom: 1px solid #D6D6D6;"></div>
<article class="am-g am-margin-bottom-xl">
    <div class="am-container-content">
        <img src="/assets/i/u1263.png" style="width: 100%;">
    </div>
</article>

<%@include file="../layout/footer.jsp" %>
<%@include file="../layout/bottom.jsp" %>
<script>
    $(function(){

    });
</script>
</body>
</html>
