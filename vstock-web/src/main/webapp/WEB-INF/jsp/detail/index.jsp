<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <%@include file="../layout/head.jsp" %>
    <title>Adidas NMD Nice Kicks</title>
    <style>
        .am-container-content{ max-width: 1000px; margin: auto; }
        .am-bid-border{ border-left:1px solid #595959; }
        .str{ color: #060606; }
        .str-title{ font-size: 22px; }
        .str-price{ color: #2d2d2d; }
        .str-size{ font-size: 18px; }
        .product-detail div{ font-size: 18px;color: #595959; }
        .product-market-summary-wrap{ margin-top: 5px;background-color: #faf9f1; height: 130px;line-height: 130px;margin-top: 15px;border-bottom: 1px solid #cacaca; }
        .product-market-summary-wrap span { font-size: 35px;color: #595959; }
        .am-table thead tr td{ text-align: center;font-size: 20px;color: #595959; border-bottom: 1px solid #ddd; }
        .am-table tbody tr td{ font-size: 18px;color: #595959; border:none;line-height: 1.8; }
        .str-title-font{ font-size: 36px;color: #2d2d2d;letter-spacing: -2px; }
        .am-selected-btn span{ line-height: 20px; }
        .am-selected-btn{  }
        .clickZoneName{ display: inline-block; margin-top: 2px; font-weight: 400; font-family: BebasNeue; overflow-y: hidden; font-size: 22px; line-height: 22px; letter-spacing: 0.2px; color: #000; text-align: left;  width: 100%; }
        .assets_btn_add{ border: solid 1px #ccc; background-color: #fff; }
        @media ( max-width: 992px ){
            .str-sudio span{ letter-spacing: 1px; }
            .product-market-summary-wrap{ height: 75px;line-height: 75px; }
            .product-market-summary-wrap span { font-size: 22px; }
        }
        @media ( max-width: 1440px ){
            .str-title-font{ font-size: 30px; }
        }
        .xy-dimmer-active{ overflow: auto;}
        .xy-dimmer-detailed{ position: absolute !important;top:15% !important; }
        .am-selected-btn{ border:1px solid #aeaeae; }
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
            <span class="str-title-font" style="font-weight: bold;">
                ${basicinformation.name}
                <a href="javascript:void(0);" id="join-assets" class="layout-font-size-18 assets_btn_add am-btn am-btn-default am-btn-sm am-margin-bottom-sm am-margin-left-lg" style="border-radius: 3px;width: 150px;"><i class="am-icon-money am-margin-right-sm layout-font-size-18" style="color: #F97271;"></i>加入资产</a>
                <a href="javascript:void(0);" id="join-assets-click" style="display: none;" data-am-modal="{target: '#my-popup-assets',width: 440}"></a>
            </span>
            <span class="str-title-font"> </span>
        </div>
        <div class="am-g">
            <div class="am-u-lg-4 am-u-md-2 am-u-sm-12 am-margin-top-xl">
                <div class="am-fl am-u-lg-4 am-padding-0 str-sudio">
                    <span class="str layout-font-size-22">尺码</span>
                    <div style="margin-top: 15px;">
                        <select id="choose_size" placeholder="请选择" data-am-selected="{btnSize: 'lg',btnWidth: 120,  maxHeight: 180}">
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
            <div class="am-u-lg-4 am-u-md-5 am-u-sm-12 am-bid-border am-margin-top-xl">
                <div class="am-fl am-u-md-9 am-u-sm-6 str-sudio am-padding-right-0">
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
                <div class="am-fr am-u-md-3 am-u-sm-6 str-sudio am-padding-right-0">
                    <button id="sell" class="am-btn am-btn-lg am-fr am-margin-top-lg am-margin-right-lg" style="background-color: #3BD379;color: #fff;">出售</button>
                </div>
                <input id="sell-click" type="hidden" data-am-modal="{target: '#my-popup-saleList',width: 900}" />
                <input id="identify-click" type="hidden" data-am-modal="{target: '#my-popup-identify',width: 644}" />
                <input id="identify-tips-click" type="hidden" data-am-modal="{target: '#my-popup-identify-tips', width: 490}" />
                <input id="login-click" type="hidden" data-am-modal="{target: '#my-popup-login', width: 350}" />
                <input id="isbidcheck-click" type="hidden" data-am-modal="{target: '#my-popup-isbidcheck-tips', width: 490}" />
            </div>
            <div class="am-u-lg-4 am-u-md-5 am-u-sm-12 am-bid-border am-margin-top-xl">
                <div class="am-fl am-u-md-9 am-u-sm-6 str-sudio am-padding-right-0">
                    <span class="str layout-font-size-22">卖家最低叫价</span><br/>
                    <span class="str layout-font-size-20">￥
                        <c:choose>
                            <c:when test="${not empty pricePeak2.minimumSellingPrice}">
                                <fmt:formatNumber value="${pricePeak2.minimumSellingPrice}" type="currency" pattern="#,##0.0#"/>
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
                <div class="am-fr am-u-md-3 am-u-sm-6 str-sudio am-padding-right-0">
                    <button id="buy" class="am-btn am-btn-lg am-fr am-margin-top-lg am-margin-right-lg" style="background-color: #FE5B5F;color: #fff;">购买</button>
                </div>
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
        <span> 4周 </span><span> 最高/最低 ：</span> <span> + 1680 </span><span> - 1210 </span>
    </div>
</article>
<div id="sale_record">

</div>

<%@include file="../layout/footer.jsp" %>
<%@include file="../layout/bottom.jsp" %>
<%@include file="../common/assets/assets.jsp" %>
<%@include file="../common/popup/sellbidwindow.jsp" %>
<%@include file="../common/popup/buyerbidwindow.jsp" %>
<%@include file="../common/popup/salelistwindow.jsp" %>
<%@include file="../common/popup/purchaselistwindow.jsp" %>
<%@include file="../common/popup/selldetailedlist.jsp" %>
<%@include file="../common/popup/buydetailedlist.jsp" %>
<%@include file="../common/popup/bindIdentify.jsp" %>
<%@include file="../common/popup/checktips.jsp" %>
<%@include file="../common/popup/isbidcheck.jsp" %>
<%@include file="../common/address/addersAddorEdit.jsp" %>
<script>
    $(function(){

        $("#now-seller-buy").click(function(){
            $("body").addClass("xy-dimmer-active");
            $("#my-popup-buy-detailed").addClass("xy-dimmer-detailed");
        });

        var k = 0;

        var loginType = $(".loginType").val();

        var bname = $(".basicinformationName").val();

        var bId = $(".basicinformationId").val();

        //选择尺码
        $("#choose_size").change(function(){
            //TODO 异步读取数据
            if(k == 1){
                location.href = "/detail?proName="+bname+"&size="+$.trim($(this).val());
            }else{
                k = 1;
            }
        });

        $("#sell").click(function(){
            if(loginType == "false"){
                $("#login-click").click();
            }else{
                loadingshow();
                sendRequest("/user/cardIdentify",null,function(res){
                    loadingclose();
                    if(res.retCode == 1){
                        $("#sell-click").click();
                    }else{
                        $("#identify-tips-click").click();
                    }
                })
            }
        });
        $("#join-assets").click(function(){
            if(loginType == "false"){
                $("#login-click").click();
            }else{
                $("#join-assets-click").click();
            }
        });
        $("#goAuthentication").click(function(){
            $("#my-popup-identify-tips").modal('close');
            $("#identify-click").click();
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
        $("#now-seller-buy").click(function(){
            loadingPurchaseclose();
        });
        $("#seller_submit_trade_").click(function(){
            var amount = $("#seller_detailed_amount").val();
            var size = $("#seller_detailed_size").val();
            var type = 1;
            box_create_trade(amount,size,type,null,null);
        });
        $("#buyer_submit_trade_").click(function(){
            var amount = $("#buyer_detailed_amount").val();
            var yunFee = $.trim($("#yunFee").text());
            var size = $("#buyer_detailed_size").val();
            var addressId = $("#new-address").find("tr td input:radio[name='check-address']:checked").attr("data-userAddress");
            var type = 0;
            box_create_trade(amount,size,type,yunFee,addressId)
        });

        function box_create_trade(amount,size,type,yunFee,addressId){
            if (type == 0 && addressId == undefined) {
                alertTips(2, "购买失败", "请选择收货地址");
                return;
            }
            if (amount == "" || amount == null || amount < 0) {
                alertTips(3,"购买失败","金额不能为空且必须大于0");
                return;
            }
            if(type == 0 && yunFee <= 0){
                alertTips(3,"购买失败","运费价格有误");
                return;
            }
            sendRequest("/trade",{
                "bname": bname,
                "bId": bId,
                'amount': amount,
                'yunFee' : yunFee,
                'size' : size,
                'type': type,
                'addressId' : addressId
            },function(res){
                if(res.retCode == 1){
                    if(type == 0){
                        alertConfirm("订单已提交","是否去支付鞋款?");
                    }else{
                        alertConfirm("订单已提交","是否去支付保证金?");
                    }
                    $("#createPay").click(function(){
                        sendRequest("/trade/createTradePay",{
                            "bname": bname,
                            "bId": bId,
                            'amount': amount,
                            'size' : size,
                            'type': type==1?2:3,
                            'tradeId':res.data
                        },function(res){
                            if(res.retCode == 1){
                                alertshow("支付成功！！");
                                location.reload();
                            }
                        });
                    });
                }else{
                    alertTips(2,"操作失败",res.retMsg);
                }
            });
        }

        /**  ----- 叫价/出价 ----- **/
        $("#seller_btn_step_final").click(function(){
            box_bid($("#seller_bid_amount").val(),$("#seller_buy_size").val(),$("#seller_buy_time").val(),0);
        });

        $("#buyer_btn_step_final").click(function(){
            box_bid($("#buyer_sell_amount").val(),$("#buyer_sell_size").val(),$("#buyer_sell_time").val(),1);
        });

        function box_bid(amount,size,overdueTime,type){
            if (amount == "" || amount == undefined || amount < 0) {
                alertTips(3,"叫价失败","金额不能为空且必须大于0");
                return;
            }
            if(type==0){
                if($("#seller_buy_size").val() == "" || $("#seller_buy_size").val() == undefined){
                    alertTips(3,"叫价失败","请选择尺码");
                    return;
                }
            }else{
                if($("#buyer_sell_size").val() == "" || $("#buyer_sell_size").val() == undefined){
                    alertTips(3,"叫价失败","请选择尺码");
                    return;
                }
            }
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
                        alertConfirm("叫价成功","是否去支付?");
                    }else{
                        alertConfirm("出价成功","是否去支付?");
                    }
                    $("#createPay").click(function(){
                        sendRequest("/bid/createPay",{
                            'amount': amount,
                            'type': type,
                            "bId": bId,
                            'size': size,
                            'bid' : res.data
                        },function(res){
                            if(res.retCode == 1){
                                alertshow("支付成功！！");
                                location.reload();
                            }
                        })
                    });
                }
            });
        }

        $('.assets_btn_add').click(function () {
            $('#assets_purchaseDate').val("");
            $('#assets_money').val("");
            $('#assets_size').val("-1");
        });

        $('.assetsAdd-btn').click(function () {
            var assets_purchaseDate = $('#assets_purchaseDate').val();
            var assets_money = $('#assets_money').val();
            var assets_size = $('#assets_size').val();
            if (assets_purchaseDate == null || assets_purchaseDate == ""){
                alertTips(2,"","日期不能为空");
                return;
            }
            if (assets_money == null || assets_money == ""){
                alertTips(2,"","金额不能为空");
                return;
            }
            if (assets_size == "-1"){
                alertTips(2,"","请选择尺码");
                return;
            }
            sendRequest("/userAssets/saveUserAssets",{
                'money': assets_money,
                'purchaseDate': assets_purchaseDate,
                "bId": $('.basicinformationId').val(),
                'size': assets_size
            },function(res){
                if(res.retCode == 1){
                    alertTips(1,"","添加成功！！");
                    loadingassetsclose();
                }else {
                    alertTips(2,"","添加失败！！");
                }
            });
        });

        /** 销售记录 **/
        function loadSaleRecord(data){
            ajaxContent("/trade/saleRecord",data,"sale_record",0);
        }

        loadSaleRecord({
            "bid":bId,
            "bname" : bname
        });
    });
</script>
</body>
</html>
