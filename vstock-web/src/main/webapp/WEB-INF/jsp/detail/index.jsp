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
        .str-price{ color: #2d2d2d; }
        .str-size{ font-size: 18px; }
        .product-detail div{ font-size: 18px;color: #595959; }
        .product-market-summary-wrap{ margin-top: 5px;background-color: #f5f5f5; height: 130px;line-height: 130px;margin-top: 15px; }
        .product-market-summary-wrap span { font-size: 35px;color: #595959; }
        .am-table thead tr td{ text-align: center;font-size: 20px;color: #595959; border-bottom: 1px solid #ddd; }
        .am-table tbody tr td{ font-size: 18px;color: #595959; border:none;line-height: 1.8; }
        .str-title-font{ font-size: 36px;color: #2d2d2d;letter-spacing: -2px; }
        @media ( max-width: 992px ){
            .str-sudio span{ letter-spacing: 1px; }
            .product-market-summary-wrap{ height: 75px;line-height: 75px; }
            .product-market-summary-wrap span { font-size: 22px; }
        }
        @media ( max-width: 1440px ){
            .str-title-font{ font-size: 30px; }
        }
    </style>
</head>
<body>
<%@include file="../layout/top.jsp" %>
<article>
    <input class="loginType" type="hidden" value="${resultModel.relogin}" />
    <input class="basicinformationName" type="hidden" value="${basicinformation.name}" />
    <input class="basicinformationId" type="hidden" value="${basicinformation.id}" />
    <div class="am-container-content" style="margin-top: 4.2rem">
        <div class="am-g am-u-md-12 am-show-lg-only">
            <span class="str-title-font" style="font-weight: bold;">${basicinformation.name} </span>
            <span class="str-title-font">${basicinformation.chineselogo}</span>
        </div>
        <div class="am-g">
            <div class="am-u-lg-4 am-u-md-2 am-u-sm-2 am-margin-top-xl">
                <div class="am-fl am-u-lg-4 am-padding-0 str-sudio">
                    <span class="layout-font-size-22">尺码</span>
                    <div style="margin-top: 18px;">
                        <select class="am-input-sm am-form-field" placeholder="请选择" data-am-selected="{btnSize: 'xl',btnWidth: 120,  maxHeight: 200}">
                            <c:choose>
                                <c:when test="${not empty size}">
                                    <option value="${size}">${size}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="">全部尺码</option>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach items="${sizes}" var="s">
                                <option value="${s}">${s}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="am-fr am-u-lg-6 am-padding-0 am-show-lg-only str-sudio ">
                    <span class="str layout-font-size-22">最后成交价</span><br/>
                    <span class="str layout-font-size-20">￥
                        <c:choose>
                            <c:when test="${not empty trade}">
                                <fmt:formatNumber value="${trade.transactionMoney}" type="currency" pattern="#,#00.0#"/>
                            </c:when>
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                    </span><br/>
                    <span class="str str-size">尺码：
                         <c:choose>
                             <c:when test="${not empty size}">
                                 ${size}
                             </c:when>
                             <c:otherwise>
                                 ${trade.bftSize}
                             </c:otherwise>
                         </c:choose>
                    </span>
                </div>
            </div>
            <div class="am-u-lg-4 am-u-md-5 am-u-sm-5 am-bid-border am-margin-top-xl">
                <div class="am-fl am-u-lg-6 am-u-md-6 am-u-sm-12 str-sudio am-padding-right-0">
                    <span class="str layout-font-size-22">买家最高出价</span><br/>
                    <span class="str layout-font-size-20">￥
                        <c:choose>
                            <c:when test="${not empty pricePeak1.highestBid}">
                                <fmt:formatNumber value="${pricePeak1.highestBid}" type="currency" pattern="#,#00.0#"/>
                            </c:when>
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                    </span><br/>
                    <span class="str str-size">尺码：
                        <c:choose>
                            <c:when test="${not empty size}">
                                ${size}
                            </c:when>
                            <c:otherwise>
                                ${pricePeak1.peakSize}
                            </c:otherwise>
                        </c:choose>
                    </span>
                </div>
                <button id="sell" class="am-btn am-btn-lg am-fr am-margin-top-lg am-hide-sm am-margin-right-lg" style="background-color: #3BD379;color: #fff;">出售</button>
                <input id="sell-click" type="hidden" data-am-modal="{target: '#my-popup-saleList',width: 900}" />
                <input id="login-click" type="hidden" data-am-modal="{target: '#my-popup-login', width: 350}" />
            </div>
            <div class="am-u-lg-4 am-u-md-5 am-u-sm-5 am-bid-border am-margin-top-xl">
                <div class="am-fl am-u-lg-6 am-u-md-6 am-u-sm-12 str-sudio am-padding-right-0">
                    <span class="str layout-font-size-22">卖家最低叫价</span><br/>
                    <span class="str layout-font-size-20">￥
                        <c:choose>
                            <c:when test="${not empty pricePeak2.minimumSellingPrice}">
                                <fmt:formatNumber value="${pricePeak2.minimumSellingPrice}" type="currency" pattern="#,#00.0#"/>
                            </c:when>
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                    </span><br/>
                    <span class="str str-size">尺码：
                         <c:choose>
                             <c:when test="${not empty size}">
                                 ${size}
                             </c:when>
                             <c:otherwise>
                                 ${pricePeak2.peakSize}
                             </c:otherwise>
                         </c:choose>
                    </span>
                </div>
                <button id="buy" class="am-btn am-btn-lg am-fr am-margin-top-lg am-hide-sm am-margin-right-lg" style="background-color: #FE5B5F;color: #fff;">购买</button>
                <input id="buy-click" type="hidden" data-am-modal="{target: '#my-popup-purchaselistwindow',width: 900}" />
            </div>
        </div>
        <div class="am-g am-text-center am-padding-lg">
            <img src="${configMap._site_url}${basicinformation.imgUrl}" style="width:80%;" />
        </div>
        <div class="am-g am-text-center am-hide-sm product-detail" style="margin-top: -20px;margin-bottom: 30px;">
            <div class="am-u-lg-3 am-u-md-12">编码: ${basicinformation.artNo}</div>
            <div class="am-u-lg-3 am-u-md-12">颜色: ${basicinformation.colores}</div>
            <div class="am-u-lg-3 am-u-md-12">发售日期:
                <c:choose>
                    <c:when test="${not empty basicinformation.csaledate}">
                        ${basicinformation.csaledate}
                    </c:when>
                    <c:otherwise>
                        -
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="am-u-lg-3 am-u-md-12">原始售价：￥
                <c:choose>
                    <c:when test="${not empty basicinformation.cofferprice}">
                        ${basicinformation.cofferprice}
                    </c:when>
                    <c:otherwise>
                        -
                    </c:otherwise>
                </c:choose>
            </div>
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
                    ${basicinformation.name} ${basicinformation.chineselogo}
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

        var loginType = $(".loginType").val();

        var bname = $(".basicinformationName").val();

        var bId = $(".basicinformationId").val();

        $("#sell").click(function(){
            if(loginType == "false"){
                $("#login-click").click();
            }else{
                $("#sell-click").click();
            }
        });

        $("#buy").click(function(){
            if(loginType == "false"){
                $("#login-click").click();
            }else{
                $("#buy-click").click();
            }
        });

        $("#now-sell-bid").click(function(){
            loadingSaleListclose();
        });

        $("#now-buyer-bid").click(function(){
            loadingPurchaseListclose();
        });

        /**  ----- 清单 ----- **/
        $("#now-buyer-sell").click(function(){
            loadingSaleListclose();
        });
        $("#seller_submit_trade_").click(function(){
            var amount = $("#seller_detailed_amount").val();
            var size = $("#seller_detailed_size").val();
            var type = 0;
            sendRequest("/trade",{
                "bname": bname,
                "bId": bId,
                'amount': amount,
                'size' : size,
                'type': type
            },function(res){
                if(res.retCode == 1){

                }else{
                    alertshow(res.retMsg);
                }
            });
        });


        /**  ----- 叫价/出价 ----- **/

        $("#seller_btn_step_final").click(function(){
            if ($("#seller_bid_amount").val() == "" || $("#seller_bid_amount").val() == null || $("#seller_bid_amount").val() < 0) {
                alertshow("出售金额不能为空，且必须大于0");
                return;
            }
            box_bid($("#seller_bid_amount").val(),$("#seller_buy_size").val(),$("#seller_buy_time").val(),0);
        });

        $("#buyer_btn_step_final").click(function(){
            if ($("#buyer_sell_amount").val() == "" || $("#buyer_sell_amount").val() == null || $("#buyer_sell_amount").val() < 0) {
                alertshow("出售金额不能为空，且必须大于0");
                return;
            }
            box_bid($("#buyer_sell_amount").val(),$("#buyer_sell_size").val(),$("#buyer_sell_time").val(),1);
        });

        function box_bid(amount,size,overdueTime,type){
            sendRequest("/bid",{
                "bname": bname,
                "bId": bId,
                'size': size,
                'amount': amount,
                'overdueTime': overdueTime,
                'type': type
            },function(res){
                if(res.retCode == 1){
                    if(type == 0){
                        alertshow("叫价成功");
                    }else{
                        alertshow("出价成功");
                    }
                    loadingBidclose();
                    location.reload();
                }
            });
        }
    });
</script>
</body>
</html>
