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
        .index-icon{ display: inline-block;width: 35px;height: 20px; }
        @media ( max-width: 992px ){
            .str-sudio span{ letter-spacing: 1px; }
            .product-market-summary-wrap{ height: 75px;line-height: 75px; }
            .product-market-summary-wrap span { font-size: 22px; }
        }
        @media ( max-width: 1440px ){
            .str-title-font{ font-size: 30px; }
        }
        .noFunction{ background-color: #ccc!important; border-color: #ccc!important; }
        .noFunction:HOVER{ background-color: #ccc; border-color: #ccc; }
        .xy-dimmer-active{ overflow: auto;}
        .xy-dimmer-detailed{ position: absolute !important;top:15% !important; }
        .am-selected-btn{ border:1px solid #aeaeae; }
        .join-assets-icon{ display: inline-block;width: 90px;height: 30px;line-height: 30px;text-align: right;background: url('/assets/i/detail_icon.png');background-position: -606px -18px; }
        .am-show-md-up{ box-shadow: rgb(204, 204, 204) 0 2px 10px 0; }
        .set-default-address{ opacity : 0; }
        .show-tr-address:HOVER .set-default-address{ opacity : 1; }
    </style>
</head>
<body>
<%@include file="../layout/top-search.jsp" %>
<article>
    <input class="loginType" type="hidden" value="${resultModel.relogin}" />
    <input class="basicinformationName" type="hidden" value="${basicinformation.name}" />
    <input class="basicinformationId" type="hidden" value="${basicinformation.id}" />
    <div class="am-container-content" style="margin-top: 4.2rem">
        <div class="am-g am-u-md-12 am-show-lg-only">
            <span class="str-title-font" style="font-weight: bold;">
                ${basicinformation.name}
                <a href="javascript:void(0);" id="join-assets" class="layout-font-size-18 assets_btn_add am-btn am-btn-default am-btn-sm am-margin-bottom-sm am-margin-left-lg" style="border-radius: 3px;width: 110px;height:32px;padding: 0;"><span class="join-assets-icon layout-font-size-18">加入资产</span></a>
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
                    <span class="str layout-font-size-22">买家最高出价<span class="question-tips question-buy-hight-tips" data-type="0.0.0.1" style="background: url('/assets/i/detail_icon.png');background-position: -535px -28px"
                                                                      data-container="body" data-toggle="popover" data-placement="auto right"
                                                                      data-content="显示买家叫价中最高的价格，如卖家直接出售，则按此价格交易。"></span></span><br/>
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
                <input id="isbidcheck-click" type="hidden" data-am-modal="{target: '#my-popup-isbidcheck-tips', width: 530}" />
            </div>
            <div class="am-u-lg-4 am-u-md-5 am-u-sm-12 am-bid-border am-margin-top-xl">
                <div class="am-fl am-u-md-9 am-u-sm-6 str-sudio am-padding-right-0">
                    <span class="str layout-font-size-22">卖家最低叫价<span class="question-tips" data-type="0.0.0.1" style="background: url('/assets/i/detail_icon.png');background-position: -535px -28px"
                                                                      data-container="body" data-toggle="popover" data-placement="auto right"
                                                                      data-content="显示卖家叫价中最低的价格，如买家直接购买，则按此价格交易。"></span></span><br/>
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
            <img src="${configMap._site_url}${basicinformation.imgUrl}" onerror="this.src='/assets/i/default_big.png;this.onerror=null'" style="width:80%;" />
        </div>
        <div class="am-g am-text-center am-hide-sm product-detail" style="margin-top: -20px;margin-bottom: 30px;">
            <div class="am-u-lg-3 am-u-md-12">
                <span class="index-icon" style="background: url('/assets/i/detail_icon.png');background-position: -138px -21px;"></span>
                编码: ${basicinformation.artNo}
            </div>
            <div class="am-u-lg-3 am-u-md-12">
                <span class="index-icon" style="background: url('/assets/i/detail_icon.png');background-position: -165px -21px;"></span>
                颜色: ${basicinformation.colores}
            </div>
            <div class="am-u-lg-3 am-u-md-12">
                <span class="index-icon" style="background: url('/assets/i/detail_icon.png');background-position: -216px -21px;"></span>
                发售日期:
                <c:choose>
                    <c:when test="${not empty basicinformation.csaledate}">
                        ${basicinformation.csaledate}
                    </c:when>
                    <c:otherwise>
                        -
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="am-u-lg-3 am-u-md-12">
                <span class="index-icon" style="background: url('/assets/i/detail_icon.png');background-position: -190px -21px;"></span>
                原始售价：￥
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
        <span> 4周 </span>
        <span> 最高/最低 ：</span>
        <span> <span class="index-icon" style="background: url('/assets/i/detail_icon.png');background-position: -259px -23px;"></span> <span id="best-hight"></span> </span>
        <span> <span class="index-icon am-margin-right-sm" style="background: url('/assets/i/detail_icon.png');background-position: -311px -23px;"></span><span id="best-minimum"></span> </span>
    </div>
</article>
<div id="sale_record"></div>
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
        document.onkeydown=keyDownSearch;

        function keyDownSearch(e) {
            var theEvent = e || window.event;
            var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
            if (code == 13) {
                if(!$("#my-popup-login").is(":hidden")){
                    $("#prLogin").click();
                    return false;
                }
                location.href = "/sorts?productName="+$(".index_search_top").val();
                return false;
            }
            return true;
        }
        $("[data-toggle='popover']").popover({
            trigger:'hover focus'
        });
        $("[data-toggle='popover']").popover();
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
        //获取4周最高最低
        sendRequest("/detail/getPricePeak",{
            "size":$("#choose_size").val(),
            "bid":bId
        },function(res){
            if(res.retCode == 1){
                if(res.data.pricePeak1 != undefined){
                    $("#best-hight").text(fmoney(res.data.pricePeak1.highestBid,0));
                }else{
                    $("#best-hight").text("-");
                }
                if(res.data.pricePeak2 != undefined){
                    $("#best-minimum").text(fmoney(res.data.pricePeak2.minimumSellingPrice),0);
                }else{
                    $("#best-minimum").text("-");
                }
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
            if($(this).hasClass("noFunction")){
                return false;
            }
            var amount = $("#seller_detailed_amount").val();
            var size = $("#seller_detailed_size").val();
            var type = 1;
            box_create_trade(amount,size,type,0,null);
        });
        $("#buyer_submit_trade_").click(function(){
            if($(this).hasClass("noFunction")){
                return false;
            }
            var amount = $("#buyer_detailed_amount").val();
            var yunFee = $.trim($("#yunFee").text());
            var size = $("#buyer_detailed_size").val();
            var addressId = $("#new-address").find("tr td input:radio[name='check-address']:checked").attr("data-userAddress");
            var type = 0;
            box_create_trade(amount,size,type,yunFee,addressId);
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
            var dataType = "";
            if (type == 0){
                dataType = 1;
            }else {
                dataType = 0;
            }
            sendRequest("/bid/ischeck",{
                'bId': bId,
                'size' : size,
                'type': dataType
            },function(res){
                if(res.retCode > 0){
                    $('#isbidcheck-click').click();
                    $(".goAuthentication").click(function(){
                        var $this = $(this);
                        var data_type = $this.attr('data-type');
                        if (data_type == 1){
                            sendRequest("/bid/updateBid",{
                                'id' : res.retCode,
                                'btfId': bId,
                                'status' : "11",
                                'bidMoney' : res.retMsg,
                                'endDate' : TimeObjectUtil.formatterDate(new Date()),
                                'size' : size,
                                'type': dataType
                            },function(param){
                                if (param.sgin > 0){
                                    alertTips(1,"","关闭成功,去支付");
                                    $('#isbidcheck-stn').click();
                                }else {
                                    alertTips(3,"","关闭失败,下单失败");
                                }
                            });
                        }else {
                            paybox_create_trade(amount,size,type,yunFee,addressId);
                        }
                    });
                }else {
                    paybox_create_trade(amount,size,type,yunFee,addressId);
                }
            });
        }

        function paybox_create_trade(amount,size,type,yunFee,addressId) {
            sendRequest("/trade/istrade",{
                'basicinformationId': bId,
                'bftSize' : size,
                'type': type,
            },function(res){
                if(res.retCode == 2){
                    $("#alert-confirmBid-title").html("提示");
                    $("#alert-confirmBid-content").html(res.retMsg);
                    $('#my-bid-confirm').modal({
                        onConfirm : function() {
                            location.href = "/user/index";
                        },
                        onCancel:function(){}
                    });
                    return false;
                }else{
                    sendRequest("/trade",{
                        'bname': bname,
                        'bId': bId,
                        'amount': amount,
                        'yunFee' : yunFee,
                        'size' : size,
                        'type': type,
                        'addressId' : addressId
                    },function(res){
                        if(res.retCode == 1){
                            if(type == 0){
                                $("#alert-confirm-title").html("订单已提交");
                                $("#alert-confirm-content").html("是否去支付鞋款?");
                            }else{
                                $("#alert-confirm-title").html("订单已提交");
                                $("#alert-confirm-content").html("是否去支付保证金?");
                            }
                            $('#my-confirm').modal({
                                onConfirm : function() {
                                    createPay();
                                },
                                onCancel:function(){
                                    $('#my-popup-buy-detailed').modal('close');
                                    $('#my-popup-sell-detailed').modal('close');
                                    location.reload();
                                }
                            });
                            function createPay(){
                                type = type==1?2:3;
//                        location.href = "/trade/createTradePayAlipay?type="+type+"&amount="+amount+"&bId="+bId+"&size="+size+"&tradeId="+ res.data+"&bname="+bname;
//                        return;
                                sendRequest("/trade/createTradePay",{
                                    "bname": bname,
                                    "bId": bId,
                                    'amount': amount,
                                    'size' : size,
                                    'type': type==1?2:3,
                                    'yunFee' : yunFee,
                                    'tradeId': res.data.tradeId,
                                    'bidId': res.data.bidId,
                                    'pricePeakId': res.data.pricePeakId
                                },function(res){
                                    if(res.retCode == 1){
                                        alertshow("支付成功！！");
                                        location.reload();
                                    }
                                });
                            };
                        }else{
                            alertTips(2,"操作失败",res.retMsg);
                        }
                    });
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
            sendRequest("/bid/isbid",{
                "bftName": bname,
                "basicinformationId": bId,
                'bftSize': size,
                'type': type
            },function(res){
                if (res.retCode == 3){
                    alertTips(2,"叫价失败",res.retMsg);
                }else {
                    if(res.retCode == 2){
                        $("#alert-confirmBid-title").html("提示");
                        $("#alert-confirmBid-content").html(res.retMsg);
                        $('#my-bid-confirm').modal({
                            onConfirm : function() {
                                location.href = "/user/index";
                            },
                            onCancel:function(){}
                        });
                        return false;
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
                                $("#alert-confirm-title").html("叫价成功");
                            }else{
                                $("#alert-confirm-title").html("出价成功");
                            }
                            $("#alert-confirm-content").html("是否去支付?");
                            $('#my-confirm').modal({
                                onConfirm : function() {
//                                    location.href = "/bid/createPayAlipay?payStatus=0&amount="+amount+"&type="+type+"&bId="+bId+"&size="+size+"&bid="+ res.data+"&bname="+bname;
//                                    return;
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
                                },
                                onCancel:function(){}
                            });
                        }
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

        $("#seller_bid_amount").blur(function(){
            var amount = $(this).val();
            var size = $("#seller_buy_size").val();
            var type = 0;
            checkMoneyTips(bId,amount,size,type);
        });
        $("#seller_buy_size").change(function(){
            var amount = $("#seller_bid_amount").val();
            var size = $(this).val();
            var type = 0;
            checkMoneyTips(bId,amount,size,type);
        });
        $("#buyer_sell_size").change(function(){
            var amount = $("#buyer_sell_amount").val();
            var size = $(this).val();
            var type = 1;
            checkMoneyTips(bId,amount,size,type);
        });
        $("#buyer_sell_amount").blur(function(){
            var amount = $(this).val();
            var size = $("#buyer_sell_size").val();
            var type = 1;
            checkMoneyTips(bId,amount,size,type);
        });

        function checkMoneyTips(bId,amount,size,type){
            sendRequest("/bid/checkMoneyTips",{
                "bid" : bId,
                "amount" : amount,
                "size" : size,
                "type": type
            },function(res){
                if(res.retCode == 1){
                    moneytips(type,res.data);
                }
            });
        }
        var buy_hight_html = "<div class='question-tips-text'><span>买家最高叫价</span></div>";
        var bsell_hight_html = "<div class='question-tips-text'><span>卖家最低叫价</span></div>";
        $(".question-buy-hight-tips").popover(
                {
                    trigger:'hover focus',
                    html: true,
                    placement:'auto right',
                    content:buy_hight_html,
                    delay:
                    { show: 10, hide: 10 }
                }
        );
        $(".question-sell-hight-tips").popover(
                {
                    trigger:'hover focus',
                    html: true,
                    placement:'auto right',
                    content:bsell_hight_html,
                    delay:
                    { show: 10, hide: 10 }
                }
        );

        /**
         * 提示文案
         */
        $(".question-tips-sell").each(function(){
            var $this = $(this);
            var $html = "";
            switch ($this.attr("data-type")){
                case "0.0.0.1":
                    $html = "<div class='question-tips-text'><span>超出有效期仍未有买家购买,出价将失效</span></div>";
                    break;
                case "0.0.0.2":
                    $html = "<div class='question-tips-text'><span>卖家发货至v－stock的运费，卖家承担，拒收到付件；</span></div>";
                    break;
                case "0.0.0.3":
                    $html = "<div class='question-tips-text'>" +
                            "<span>鉴定鞋子真假的费用</span>" +
                            "</div>";
                    break;
                case "0.0.0.4":
                    $html = "<div class='question-tips-text'><span>1、叫价失效时或者交易成功后，保证金退回；</span><br/>" +
                            "<span>2、如鉴定不合格，保证金将作为违约金赔偿给买家。</span></div>";
                    break;
                default:
                    break;
            }
            $this.popover(
                    {
                        trigger:'hover focus',
                        html: true,
                        placement:'auto right',
                        content:$html,
                        animation:true,
                        delay:
                        { show: 300, hide: 100 }
                    });
        });
    });
</script>
</body>
</html>
