<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/inc.jsp" %>
<html>
<head>
    <%@include file="../../layout/head.jsp" %>
    <style type="text/css">
        @media (min-width: 1400px) {.meun-width{width: 225px; min-height: 350px;}.span-img{width: 10%;}.userInfo-div{width: 22%;}.meun-font-size{font-size: 18px;}.highcharts-with-higth{width: 200px; height: 300px;}}
        @media (max-width: 1400px) {.meun-width{width: 16%; min-height: 350px;}.meun-font-size{font-size: 14px;}.span-img{width: 11%}.highcharts-with-higth{width: 150px; height: 200px;}}
        @media (max-width: 996px) {.span-img{width: 6%}}
        .am-table>tbody>tr>td{vertical-align: inherit;}
        .cc input:focus {outline:none;}
    </style>
</head>
<body>
<%@include file="../../layout/top-search.jsp"%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-text-xs">
        <div class="am-u-sm-2 am-u-md-2 am-u-lg-2"><p></p></div>
        <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 am-padding-left-0 am-padding-right-0 meun-width" id="div1" style="background-color: #F6F5F4;overflow:hidden;">
            <ul class="am-nav">
                <c:if test="${not empty vUser}">
                    <li class="am-padding-top-sm layout-font-size-36 am-text-center am-padding-bottom" style="background-color: #EBE9E7">${vUser.nick}</li>
                </c:if>
                <li><a href="javascript:void(0)" class="home-tab am-margin-top-lg"><div style="float: left; display: block;width: 60px;height: 30px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -264px -24px;"></div><span class="text-color am-text-danger layout-font-size-24" data-url="../user/sale?type=0" data-type="1" >出售记录</span></a></li>
                <li><a href="javascript:void(0)" class="home-tab"><div style="float: left; display: block;width: 60px;height: 30px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -215px -24px;"></div><span class="text-color am-text-danger am-link-muted layout-font-size-24" data-url="../user/sale?type=1" data-type="2">购买记录</span></a></li>
                <li><a href="javascript:void(0)" class="home-tab"><img class="am-margin-left am-padding-bottom-xs" src="../../../../assets/shoesImg/assets.png"><span class="am-margin-left text-color am-text-danger am-link-muted layout-font-size-24" data-url="../user/userAssets" data-type="3">我的资产</span></a></li>
                <li><a href="javascript:void(0)" class="home-tab"><div style="float: left; display: block;width: 60px;height: 36px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -166px -24px;"></div><span class="text-color am-text-danger am-link-muted layout-font-size-24" data-url="../user/userInfo" data-type="4">设置</span></a></li>
            </ul>
        </div>
        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-u-end am-margin-top-xl am-margin-bottom-xl" id="tradeforex_tilie"></div>
    </div>
</div>
<%@include file="../../layout/footer.jsp" %>
<%@include file="../../layout/bottom.jsp" %>
</body>
<script type="text/javascript">
    window.onload=function(){
        document.getElementById("div1").style.height = document.getElementById("tradeforex_tilie").offsetHeight+"px";
    }
    jQuery(function($){

        var upMoeny;

        $(".home-tab").click(function () {
            load($(this));
        });

        function load($thoes){
            loadingshow();
            if ($thoes.length > 0) {
                var th = $thoes.find("span");
                var url = th.attr("data-url");
                ajaxContent(url, "", "tradeforex_tilie",1);
                var divB = document.getElementsByClassName('text-color');
                for (var a = 0; a < divB.length; a++) {
                    var divA = divB[a];
                    if (divA.className.indexOf("am-link-muted") == -1) {
                        divA.className += ' am-link-muted';
                    }
                }
                th.removeClass("am-link-muted");
            }else {
                ajaxContent("../user/sale?type=0", "" ,"tradeforex_tilie",1);
            }
        }
        load("");

        $("body").on("click",".offer-btn",function(){
            var url = $(this).attr("data-url");
            loadingshow();
            ajaxContent(url, "", "tradeforex_tilie",1);
        });

        $("body").on("click",".sale-up",function(){
            var $this = $(this);
            $this.parent().next().children().attr("disabled", false);
            $this.parent().next().next().children().attr("disabled", false);
            $this.parent().next().next().next().children().attr("disabled", true);
            $this.attr("disabled", true);
            var $thoes = $this.parent().parent().parent().parent().prev().prev().prev().prev();
            var moeny = $thoes.text();
            upMoeny = parseFloat(moeny.substring(1,moeny.legend).replace(/[^\d\.-]/g, ""));
            var a = "<input type='text' value='"+upMoeny+"' style='width: 120px;'/>"
            $thoes.html("");
            $thoes.append(a);
            var $th = $this.parent().parent().parent().parent().parent().parent().parent().parent().find("a[select_type='select-btn']");
            $th.attr("disabled", true);
            $this.parent().parent().parent().children().first().attr("disabled", false);
        });

        $("body").on("click",".sale-sub",function(){
            var $this = $(this);
            var dataType = $this.attr("data_type");
            var btf = $this.attr("btf-id");
            var $thoes = $this.parent().parent().parent().parent().prev().prev().prev().prev();
            var moeny = $thoes.children().val();
            var size = $thoes.prev().prev().text();
            var id = $this.attr("data_id");
            if (dataType == 0){
                if (upMoeny < moeny) {
                    alertshow("最新出价不能高于原始出价");
                    return;
                }
            }else {
                if (upMoeny > moeny) {
                    alertshow("最新出价不能低于原始出价");
                    return;
                }
            }
            sendRequest("/bid/updateBid",{
                id : id,
                bidMoney : moeny,
                btfId : btf,
                size : size
            },function(res) {
                if (res.sgin == 1) {
                    $this.parent().prev().children().attr("disabled", false);
                    $this.parent().next().children().attr("disabled", true);
                    $this.parent().next().next().children().attr("disabled", false);
                    $this.attr("disabled", true);
                    moeny = parseInt(moeny).formatMoney(1,"￥");
                    $thoes.html("");
                    $thoes.text(moeny);
                    var $th = $this.parent().parent().parent().parent().parent().parent().parent().parent().find("a[select_type='select-btn']");
                    $this.parent().parent().parent().removeClass("am-active");
                    $th.attr("disabled", false);
                    if (dataType == 0){
                        ajaxContent("../user/sale?type=0", "" ,"tradeforex_tilie",1);
                    }else {
                        ajaxContent("../user/sale?type=1", "" ,"tradeforex_tilie",1);
                    }
                }else {
                    alertshow("保存失败，请重新输入！");
                }
            });
        });

        $("body").on("click",".sale-quit",function(){
            var $this = $(this);
            $this.parent().prev().prev().children().attr("disabled", false);
            $this.parent().prev().children().attr("disabled", true);
            $this.parent().next().children().attr("disabled", false);
            $this.attr("disabled", true);
            var $thoes = $this.parent().parent().parent().parent().prev().prev().prev().prev();
            upMoeny = upMoeny.formatMoney(1,"￥");
            $thoes.html("");
            $thoes.text(upMoeny);
            var $th = $this.parent().parent().parent().parent().parent().parent().parent().parent().find("a[select_type='select-btn']");
            $th.attr("disabled", false);
        });

        $("body").on("click",".sale-del",function(){
            var $this = $(this);
            var id = $this.attr("del_data_id");
            sendRequest("/bid/updateBid",{
                id : id,
                status : '3'
            },function(res) {
                if (res.sgin == 1){
                    alertshow("删除成功！");
                    $('.offer-btn').click();
                }else {
                    alertshow("删除失败！");
                }
            });
        });

        $("body").on("click",".userAssets-del",function(){
            var $this = $(this);
            var id = $this.attr("del_data_id");
            sendRequest("/bid/updateBid",{
                id : id,
                status : '2'
            },function(res) {
                if (res.sgin == 1){
                    alertshow("删除成功！");
                    $('.offer-btn').click();
                }else {
                    alertshow("删除失败！");
                }
            });
        });

        $("body").on("click",".deliver-goods",function(){
            var $this = $(this);
            var dataType = $this.attr("bid_type");
            var amount = $this.parent().parent().parent().parent().prev().prev().prev().prev().text();
            amount = parseFloat(amount.substring(1,amount.legend).replace(/[^\d\.-]/g, ""));
            var type = $this.attr("data-type");
            var bId = $this.attr("bft-id");
            var bid = $this.attr("bid-id");
            var bftSize = $this.parent().parent().parent().parent().prev().prev().prev().prev().prev().prev().text();
            sendRequest("/bid/createPay",{
                bid : bid,
                bId : bId,
                type : type,
                amount : amount,
                size : bftSize
            },function(res) {
                if (res.retCode == 1){
                   alertshow("支付成功！");
                   $this.parent().parent().parent().removeClass("am-active");
                   $this.parent().parent().parent().children().first().attr("disabled", true);
                    if (dataType == 0){
                        ajaxContent("../user/sale?type=0", "" ,"tradeforex_tilie",1);
                    }else {
                        ajaxContent("../user/sale?type=1", "" ,"tradeforex_tilie",1);
                    }
                }else {
                    alertshow("支付失败，请重新支付！");
                }
            });
        });

        $("body").on("click",".trade-pament",function(){
            var $this = $(this);
            var bidId = $this.attr("data-id");
            var type = $this.attr("trade-type");
            var amount = $this.parent().parent().parent().parent().prev().prev().prev().text();
            amount = parseFloat(amount.substring(1,amount.legend).replace(/[^\d\.-]/g, ""));
            sendRequest("/trade/createTradePay",{
                tradeId : bidId,
                type : type,
                amount : amount
            },function(res) {
                if (res.retCode == 1){
                    alertshow("支付成功！");
                    $this.parent().parent().parent().removeClass("am-active");
                    $this.parent().parent().parent().children().first().attr("disabled", true);
                    if (type == 2){
                        ajaxContent("../user/sale?type=0", "" ,"tradeforex_tilie",1);
                    }else {
                        ajaxContent("../user/sale?type=1", "" ,"tradeforex_tilie",1);
                    }
                }else {
                    alertshow("支付失败，请重新支付！");
                }
            });
        });

        $("body").on("click",".adder-stn",function(){
            var $this = $(this);
            var shopName = $('#shop-name').val();
            if (shopName.length > 20){
                alertshow("收货人姓名太长，请修改！")
                return;
            }
            var phone = "";
            var phoneNumber = $('#phone-number').val();
            var areaCode = $('#area-code').val();
            var phoneCode = $('#phone-code').val();
            var extensionCode = $('#extension-code').val();
            if (areaCode){
                phone = phone + areaCode + "-";
            }
            if (phoneCode){
                phone = phone + phoneCode;
            }
            if (extensionCode){
                phone = phone + extensionCode;
            }
            if (phone == "" && phoneNumber == ""){
                alertshow("请任意填写一个联系电话！");
                $("body").on("click","#add-adders",function(){});
                return;
            }
            sendRequest("/user/insertAdder",{
                localArea : $('#city-name').val(),
                detailedAddress : $('#adder-name').val(),
                consigneeName : shopName,
                phoneNumber : phoneNumber,
                landlineNumber : phone
            },function(res) {
                if (res.retCode == 1){
                    alertshow("添加成功！");
                    ajaxContent("../user/userInfo", "" ,"tradeforex_tilie",1);
                }else {
                    alertshow("添加失败，请重新添加！");
                    $('#add-adders').click();
                }
            });
        });
    });
</script>
</html>