<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/inc.jsp" %>
<html>
<head>
    <%@include file="../../layout/head.jsp" %>
    <style type="text/css">
        @media (min-width: 1400px) {.meun-width{width: 225px; min-height: 350px;}.span-img{width: 10%;}.userInfo-div{width: 22%;}.meun-font-size{font-size: 18px;}}
        @media (max-width: 1400px) {.meun-width{width: 16%; min-height: 350px;}.meun-font-size{font-size: 14px;}.span-img{width: 11%}}
        @media (max-width: 996px) {.span-img{width: 6%}}
        .am-table>tbody>tr>td{vertical-align: inherit;}
        .cc input:focus {outline:none;}
        .leftmenunInfo li { padding:0 5px; }
        .xy-dimmer-active{ overflow: auto;}
        .xy-dimmer-detailed{ position: absolute !important;top:15% !important; }
        .am-show-md-up{ box-shadow: rgb(204, 204, 204) 0 2px 10px 0; }
        .am_news_load { max-width: 810px; margin: 0 auto; color: #3c3c3c; height: 43px; line-height: 43px; background: #fff; text-align: center; margin-top: 20px; cursor: pointer; box-shadow: 0 1px 2px #ccc; }
        .status-user-set{ width: 50px; background-color: #EA5858;border:none;border-radius: 3px; }
        .status-user-set span{ color:#AA0503; }
        .status-user-set:HOVER{ background-color: #FB7f6f; }
        .am-active .status-user-set{ background-color: #FB7f6f!important; }
        ul.am-dropdown-content>li>a{ padding: 6px 26px 6px 10px; }
        .set-default-address{ opacity : 0; }
        .show-tr-address:HOVER .set-default-address{ opacity : 1; }
        .lg-rect-css{font-size: 12px;border-radius: 50%;display: block; width: 18px;height: 18px;float: left;}
        .am-container-content-user{ max-width: 1180px; margin: auto; }
        @media (max-width: 1400px){
            .am-container-content-user{ max-width: 1080px; margin: auto; }
        }
    </style>
</head>
<body>
<%@include file="../../layout/top-search.jsp"%>
<div>
    <div class="am-container-content-user" style="overflow:hidden;">
        <div class="am-padding-0 am-fl am-margin-left-lg" id="div1" style="background-color: #F6F5F4;overflow:hidden;min-height: 450px; max-width: 20%;">
            <input type="hidden" id="url-type" value="${urlType}"/>
            <%--<c:if test="${not empty vUser}">--%>
                <%--<div class="am-text-center am-padding-bottom" style="background-color: #EBE9E7;padding: 20px 20px; border-bottom: solid 1px #CECAC5;"></div>--%>
            <%--</c:if>--%>
            <div class="layout-font-size-26 am-text-center am-padding-bottom" style="background-color: #EBE9E7;padding: 20px 20px; border-bottom: solid 1px #CECAC5;">${vUser.nick}</div>
            <div class="am-u-md-12 am-text-center am-margin-bottom-xl" style="background-color: #EBE9E7;padding: 10px 20px; border-bottom: solid 1px #CECAC5;">
                <div class="am-u-md-4 am-padding-left-0">
                    <c:choose>
                        <c:when test="${not empty vUser.mobile}">
                            <span class="am-fl" title="手机号已绑定" style="cursor: pointer;background: url('/assets/i/personal_center_map.png'); background-position: -146px -19px;width: 30px;height: 30px;display: block;"/>
                        </c:when>
                        <c:otherwise>
                            <span class="am-fl" style="background: url('/assets/i/personal_center_map.png'); background-position: -188px -19px;width: 30px;height: 30px;display: block;"/>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="am-u-md-4 am-padding-left-sm">
                    <c:choose>
                        <c:when test="${not empty vUser.uname}">
                            <span style="background: url('/assets/i/personal_center_map.png'); background-position: -238px -19px;width: 30px;height: 30px;display: block;"/>
                        </c:when>
                        <c:otherwise>
                            <span style="background: url('/assets/i/personal_center_map.png'); background-position: -284px -19px;width: 30px;height: 30px;display: block;"/>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="am-u-md-4 am-padding-right-0">
                    <c:choose>
                        <c:when test="${not empty userAccount}">
                            <c:choose>
                                <c:when test="${userAccount.status == 1}">
                                    <span class="am-fr" title="实名认证已通过" style="cursor: pointer;background: url('/assets/i/personal_center_map.png'); background-position: -334px -19px;width: 30px;height: 30px;display: block;"/>
                                </c:when>
                                <c:otherwise>
                                    <span class="am-fr" style="background: url('/assets/i/personal_center_map.png'); background-position: -388px -19px;width: 30px;height: 30px;display: block;"/>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <span class="am-fr" style="background: url('/assets/i/personal_center_map.png'); background-position: -388px -19px;width: 30px;height: 30px;display: block;"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <ul class="leftmenunInfo am-nav am-padding-top-xl">
                <li><a href="javascript:void(0);" class="home-tab am-margin-top-lg"><div style="float: left; display: block;width: 45px;height: 30px; background: url('/assets/i/personal_center_map.png'); background-position: -450px -17px;"></div><span class="text-color am-text-danger layout-font-size-24 home-frist" data-url="../user/sale?type=0" data-type="1" >出售记录</span></a></li>
                <li><a href="javascript:void(0);" class="home-tab"><div style="float: left; display: block;width: 45px;height: 30px; background: url('/assets/i/personal_center_map.png'); background-position: -515px -13px;"></div><span class="text-color am-text-danger am-link-muted layout-font-size-24" data-url="../user/sale?type=1" data-type="2">购买记录</span></a></li>
                <li><a href="javascript:void(0);" class="home-tab"><div style="float: left; display: block;width: 45px;height: 40px; background: url('/assets/i/personal_center_map.png'); background-position: -576px -13px;"></div><span class="text-color am-text-danger am-link-muted layout-font-size-24" data-url="../user/userAssets" data-type="3">我的资产</span></a></li>
                <li><a href="javascript:void(0);" class="home-tab"><div style="float: left; display: block;width: 45px;height: 36px; background: url('/assets/i/personal_center_map.png'); background-position: -639px -16px;"></div><span class="text-color am-text-danger am-link-muted layout-font-size-24 home-last" data-url="../user/userInfo" data-type="4">设置</span></a></li>
            </ul>
        </div>
        <div class="am-padding-top-lg am-padding-right-lg am-fl am-padding-sm" style="width: 75%;" id="tradeforex_tilie"></div>
    </div>
</div>
<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">是否删除</div>
        <div class="am-modal-bd">
            删除后将不再显示出价记录，确定要删除吗？
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>
<%@include file="../../layout/footer.jsp" %>
<%@include file="../../layout/bottom.jsp" %>
</body>
<script type="text/javascript">
    window.onload=function(){
        document.getElementById("div1").style.height = parseInt(document.getElementById("tradeforex_tilie").offsetHeight)+100+"px";
    }

    jQuery(function($){
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
        var upMoeny;

        $(".home-tab").click(function () {
            load($(this),"");
        });

        function load($thoes,type){
//            loadingshow();
            if ($thoes.length > 0) {
                var th = $thoes.find("span");
                var url = th.attr("data-url");
                $("#tradeforex_tilie").fadeOut(0);
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
                if (type == 0){
                    ajaxContent("../user/sale?type=0", "" ,"tradeforex_tilie",1);
                }else {
                    ajaxContent("../user/userInfo", "" ,"tradeforex_tilie",1);
                    $('.home-frist').addClass("am-link-muted");
                    $('.home-last').removeClass("am-link-muted");
                }
            }
        }
        load("",$('#url-type').val());

        $("body").on("click",".offer-btn",function(){
            var url = $(this).attr("data-url");
            ajaxContent(url, "", "tradeforex_tilie",1);
        });

        $("body").on("click",".offer-ref-btn",function(){
            var url = $(this).attr("data-url");
            ajaxContent(url, "", "tradeforex_tilie",1);
        });

        $("body").on("click","#load_more",function(){
            var a = $(this).children().children().attr("style","");
            var url = $(this).attr("data-url");
            ajaxContent(url, "", "tradeforex_tilie",1);
        });

        $("body").on("click",".sale-up",function(){
            var $this = $(this);
            $this.parent().next().children().attr("disabled", false);
            $this.parent().next().next().children().attr("disabled", false);
            $this.parent().next().next().next().children().attr("disabled", true);
            $this.attr("disabled", true);
            var $thoes = $this.parent().parent().parent().parent().parent().prev().prev().prev().prev();
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
            var type = $this.attr('data_type');
            var status = $this.attr('status');
            var bidDate = $this.attr("bid-date");
            var btf = $this.attr("btf-id");
            var $thoes = $this.parent().parent().parent().parent().parent().prev().prev().prev().prev();
            var moeny = $thoes.children().val();
            var size = $thoes.prev().prev().text();
            var id = $this.attr("data_id");
            if (type == 0){
                if (upMoeny < moeny) {
                    alertTips(3,"","最新出价不能高于原始出价");
                    return;
                }
            }else {
                if (upMoeny > moeny) {
                    alertTips(3,"","最新出价不能低于原始出价");
                    return;
                }
            }
            sendRequest("/bid/updateBid",{
                id : id,
                bidMoney : moeny,
                bidDate : bidDate,
                btfId : btf,
                type : type,
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
                    if (type == 0){
                        ajaxContent("../user/sale?type=0", "" ,"tradeforex_tilie",1);
                    }else {
                        ajaxContent("../user/sale?type=1", "" ,"tradeforex_tilie",1);
                    }
                }else {
                    alertTips(2,"保存失败","请重新输入");
                }
            });
        });

        $("body").on("click",".sale-quit",function(){
            var $this = $(this);
            $this.parent().prev().prev().children().attr("disabled", false);
            $this.parent().prev().children().attr("disabled", true);
            $this.parent().next().children().attr("disabled", false);
            $this.attr("disabled", true);
            var $thoes = $this.parent().parent().parent().parent().parent().prev().prev().prev().prev();
            upMoeny = upMoeny.formatMoney(1,"￥");
            $thoes.html("");
            $thoes.text(upMoeny);
            var $th = $this.parent().parent().parent().parent().parent().parent().parent().parent().find("a[select_type='select-btn']");
            $th.attr("disabled", false);
        });

        $("body").on("click",".sale-del",function(){
            $('#my-confirm').modal({
                relatedTarget: this,
                onConfirm: function(options) {
                    var $this = $(this.relatedTarget);
                    var id = $this.attr("del_data_id");
                    var type = $this.attr("data-type");
                    var bftId = $this.attr("btf-id");
                    var $thoes = $this.parent().parent().parent().parent().parent().prev().prev().prev().prev();
                    var moeny = $thoes.text();
                    moeny = parseFloat(moeny.substring(1,moeny.legend).replace(/[^\d\.-]/g, ""));
                    var size = $thoes.prev().prev().text();
                    sendRequest("/bid/updateBid",{
                        id : id,
                        type : type,
                        btfId : bftId,
                        bidMoney : moeny,
                        size : size,
                        endDate : TimeObjectUtil.formatterDate2(new Date()),
                        status : '40'
                    },function(res) {
                        if (res.sgin == 1){
                            alertTips(1,"","删除成功");
                            $('.offer-ref-btn').click();
                        }else {
                            alertTips(2,"","删除失败");
                        }
                    });
                },
                // closeOnConfirm: false,
                onCancel: function() {
                }
            });
        });

        $("body").on("click","#createPay",function(){
            var $this = $(this);
            var money = $('#userAssets_del_money').val();
            var size = $('#userAssets_del_size').val();
            money = parseFloat(money.replace(/[^\d\.-]/g, ""));
            sendRequest("/userAssets/saveUserAssets",{
                id : $('#userAssets_del_data_id').val(),
//                userId : $this.attr("user-id"),
                bId : $('#userAssets_btf_id').val(),
                money : money,
                size : size,
                status : '2'
            },function(res) {
                if (res.retCode == 1){
                    alertTips(1,"","删除成功");
                    $('.offer-btn').click();
                }else {
                    alertTips(2,"","删除失败");
                }
            });
        });

        $("body").on("click",".deliver-bid-goods",function(){
            var $this = $(this);
            var amount = $this.parent().parent().parent().parent().parent().prev().prev().prev().prev().text();
            var bftSize = $this.parent().parent().parent().parent().parent().prev().prev().prev().prev().prev().prev().text();
            var bftName = $this.parent().parent().parent().parent().parent().prev().prev().prev().prev().prev().prev().prev().text();
            alipay($this,amount,bftSize,bftName);
        });

        function alipay($this,amount,bftSize,bftName){
            var dataType = $this.attr("bid_type");
            amount = parseFloat(amount.substring(1,amount.legend).replace(/[^\d\.-]/g, ""));
            var type = $this.attr("data-type");
            var bId = $this.attr("bft-id");
            var bid = $this.attr("bid-id");
            location.href = "/bid/createPayAlipay?payStatus=0&amount="+amount+"&type="+type+"&bId="+bId+"&size="+bftSize+"&bid="+bid+"&bname="+bftName+"&isUserHome=1";
            return;
//            sendRequest("/bid/createPay",{
//                payStatus : 0,
//                bid : bid,
//                bId : bId,
//                type : type,
//                amount : amount,
//                size : bftSize
//            },function(res) {
//                if (res.retCode == 1){
//                   alertTips(1,"","支付成功");
//                   $this.parent().parent().parent().removeClass("am-active");
//                   $this.parent().parent().parent().children().first().attr("disabled", true);
//                    if (dataType == 0){
//                        ajaxContent("../user/sale?type=0", "" ,"tradeforex_tilie",1);
//                    }else {
//                        ajaxContent("../user/sale?type=1", "" ,"tradeforex_tilie",1);
//                    }
//                }else {
//                    alertTips(2,"","支付失败，请重新操作！");
//                }
//            });
        }

        $("body").on("click",".tradeSave",function(){
            var $this = $(this);
            var explain = $this.attr('explain');
            var type = $this.attr('utype');
            var status = $this.attr('status');
            var id = $this.attr('data-id');
            var bidId = $this.attr('bidId');
            var tradeNo = $this.attr('trade-no');
            var amount = $this.parent().parent().parent().parent().parent().prev().prev().text();
            amount = parseFloat(amount.substring(1,amount.legend).replace(/[^\d\.-]/g, ""));
            var size = $this.parent().parent().parent().parent().parent().prev().prev().prev().text();
            sendRequest("/trade/saveTrade",{
                id : id,
                bidId : bidId,
                tradeNo : tradeNo,
                transactionMoney : amount,
                bftSize : size,
                status : status
            },function(res) {
                if (res.retCode == 1){
                    alertTips(1,"",explain+"成功");
                    $this.parent().parent().parent().removeClass("am-active");
                    $this.parent().parent().parent().children().first().attr("disabled", true);
                    if (type == 2){
                        ajaxContent("../user/sale?type=0", "" ,"tradeforex_tilie",1);
                    }else {
                        ajaxContent("../user/sale?type=1", "" ,"tradeforex_tilie",1);
                    }
                }else {
                    alertTips(2,explain+"失败","请重新操作！");
                }
            });
        });

        $("body").on("click",".trade-save",function(){
            var $this = $(this);
            var explain = $this.attr('explain');
            var type = $this.attr('utype');
            var status = $this.attr('status');
            var id = $this.attr('data-id');
            var bidId = $this.attr('bidId');
            var tradeNo = $this.attr('trade-no');
            var amount = $this.parent().parent().parent().parent().parent().prev().prev().text();
            amount = parseFloat(amount.substring(1,amount.legend).replace(/[^\d\.-]/g, ""));
            var size = $this.parent().parent().parent().parent().parent().prev().prev().prev().text();
            $("body").on("click",".logistics-btn",function(){
                var companyName = $('#logisticsIn-name').val();
                var courierNumber = $('#logisticsIn-number').val();
                sendRequest("/user/insertlogiscsIn",{
                    tradeId : id,
                    companyName : companyName,
                    courierNumber : courierNumber
                },function(res) {
                    if (res.retCode > 0){
                        $(".am-dimmer").fadeOut(500);
                        sendRequest("/trade/saveTrade",{
                            id : id,
                            bidId : bidId,
                            tradeNo : tradeNo,
                            transactionMoney : amount,
                            bftSize : size,
                            status : status
                        },function(res) {
                            if (res.retCode == 1){
                                alertTips(1,"",explain+"成功");
                                $this.parent().parent().parent().removeClass("am-active");
                                $this.parent().parent().parent().children().first().attr("disabled", true);
                                if (type == 2){
                                    ajaxContent("../user/sale?type=0", "" ,"tradeforex_tilie",1);
                                }else {
                                    ajaxContent("../user/sale?type=1", "" ,"tradeforex_tilie",1);
                                }
                            }else {
                                alertTips(2,explain+"失败","请重新操作！");
                            }
                        });
                    }else {
                        alertTips(2,"发货提示","发货失败，请重新填写物流信息")
                    }
                });
            });
        });

        $("body").on("click",".trade-pament",function(){
            var $this = $(this);
            var tradeId = $this.attr("data-id");
            var addId = $this.attr("add-id")
            var type = $this.attr("trade-type");
            var size = $this.attr("trade-size");
            var bftName = $this.attr("data-bftName");
            var amount = $this.parent().parent().parent().parent().parent().prev().prev().text();
            amount = parseFloat(amount.substring(1,amount.legend).replace(/[^\d\.-]/g, ""));
            sendRequest("/trade/getBuyInfo",{
                "tradeId":tradeId,
                "size" : size
            },function(res){
                if(type == 3 && addId == 0){
                    var site_imgUrl = $("#site_url").val();
                    var address = res.data.userAddressesList;
                    amount = res.data.trade.transactionMoney;
                    $("#buyProductImgId").attr("src",site_imgUrl+res.data.basicinformation.smallImgUrl);
                    $(".basicinformationBrand").text(res.data.basicinformation.brand);
                    $(".basicinformationName").text(res.data.basicinformation.name);
                    $(".basicinformationChineselogo").text(res.data.basicinformation.chineselogo);
                    $("#buyer_detailed_amount").val(res.data.trade.transactionMoney);
//                    jisuan(res.data.trade.userAddressId);
                    $("#buyer_detailed_size").val(size);
                    var sssresult = parseFloat(amount)+parseFloat(res.data.trade.tradeFreight);
                    $(".countMoney").text(sssresult);
                    if(res.data.pricePeak1 == undefined){
                        $(".pricePeak1HighestBid").text("-");
                    }else{
                        $(".pricePeak1HighestBid").text(res.data.pricePeak1.highestBid);
                    }
                    if(res.data.pricePeak2 == undefined){
                        $(".pricePeak2MinimumSellingPrice").text("-");
                    }else{
                        $(".pricePeak2MinimumSellingPrice").text(res.data.pricePeak2.minimumSellingPrice);
                    }
                    $("body").addClass("xy-dimmer-active");
                    var html = "";
                    $("#new-address-div-content").html("");
                    for(var x = 0;x < address.length;x++){
                        var checkTr = "";
                        var phoneNumber = "";
                        if(address[x].type == 1){
                            checkTr = "checked-tr";
                        }
                        if(address[x].phoneNumber != undefined){
                            phoneNumber = address[x].phoneNumber;
                        }else{
                            phoneNumber = "--";
                        }
                        if (addId && addId != 0){
                            if (addId == address[x].id){
                                if (address[x].type==1){
                                    html += '<tr class="show-tr-address '+checkTr+'">' +
                                            '<td><input id="doc-ipt-o-'+address[x].id+'" type="radio" name="check-address" data-userAddress="'+address[x].id+'" checked="checked" /></td><td><label for="doc-ipt-o-'+address[x].id+'" style="font-weight: normal;"><span class="am-margin-right-xs default-span-tips" style="color:#E77779;">[默认]</span>'+address[x].localArea+address[x].detailedAddress+'</label></td>' +
                                            '<td> '+address[x].consigneeName+'</td>' +
                                            '<td> '+phoneNumber+' </td>' +
                                            '<td class="do" style="width: 24%;"><div style="float:left;margin-right: 20px;"><a href="javascript:;" data-userAddress="'+address[x].id+'" class="edit-address" data-am-modal="{target: \'#adders-id\', closeViaDimmer: 0, width: 487, height: 420}">编辑</a>' +
                                            '| <a href="javascript:;" class="del-address" data-userAddress="'+address[x].id+'" data-type="'+address[x].type+'">删除</a></div>'+
                                            '<div class="lg-default-none"><a href="javascript:void(0);" data-userAddress="'+address[x].id+'" class="am-btn-sm am-text-danger set-default-address">设为默认</a></div></td>' +
                                            '</tr>'
                                }else {
                                    html += '<tr class="show-tr-address '+checkTr+'">' +
                                            '<td><input id="doc-ipt-o-'+address[x].id+'" type="radio" name="check-address" data-userAddress="'+address[x].id+'" checked="checked" /></td><td><label for="doc-ipt-o-'+address[x].id+'" style="font-weight: normal;"><span class="am-margin-right-xs default-span-tips" style="color:#E77779;display: none;">[默认]</span>'+address[x].localArea+address[x].detailedAddress+'</label></td>' +
                                            '<td> '+address[x].consigneeName+'</td>' +
                                            '<td> '+phoneNumber+' </td>' +
                                            '<td class="do" style="width: 24%;"><div style="float:left;margin-right: 20px;"><a href="javascript:;" data-userAddress="'+address[x].id+'" class="edit-address" data-am-modal="{target: \'#adders-id\', closeViaDimmer: 0, width: 487, height: 420}">编辑</a>' +
                                            '| <a href="javascript:;" class="del-address" data-userAddress="'+address[x].id+'" data-type="'+address[x].type+'">删除</a></div>'+
                                            '<div><a href="javascript:void(0);" data-userAddress="'+address[x].id+'" class="am-btn-sm am-text-danger set-default-address">设为默认</a></div></td>' +
                                            '</tr>'
                                }
                            }else if (address[x].type==1){
                                html += '<tr class="show-tr-address '+checkTr+'">' +
                                        '<td><input id="doc-ipt-o-'+address[x].id+'" type="radio" name="check-address" data-userAddress="'+address[x].id+'" disabled="disabled" /></td><td><label for="doc-ipt-o-'+address[x].id+'" style="font-weight: normal;"><span class="am-margin-right-xs default-span-tips" style="color:#E77779;">[默认]</span>'+address[x].localArea+address[x].detailedAddress+'</label></td>' +
                                        '<td> '+address[x].consigneeName+'</td>' +
                                        '<td> '+phoneNumber+' </td>' +
                                        '<td class="do" style="width: 24%;"><div style="float:left;margin-right: 20px;"><a href="javascript:;" data-userAddress="'+address[x].id+'" class="edit-address" data-am-modal="{target: \'#adders-id\', closeViaDimmer: 0, width: 487, height: 420}">编辑</a>' +
                                        '| <a href="javascript:;" class="del-address" data-userAddress="'+address[x].id+'" data-type="'+address[x].type+'">删除</a></div>'+
                                        '<div class="lg-default-none"><a href="javascript:void(0);" data-userAddress="'+address[x].id+'" class="am-btn-sm am-text-danger set-default-address">设为默认</a></div></td>' +
                                        '</tr>'
                            }else {
                                html += '<tr class="show-tr-address '+checkTr+'">' +
                                        '<td><input id="doc-ipt-o-'+address[x].id+'" type="radio" name="check-address" data-userAddress="'+address[x].id+'" disabled="disabled" /></td><td><label for="doc-ipt-o-'+address[x].id+'" style="font-weight: normal;"><span class="am-margin-right-xs default-span-tips" style="color:#E77779;display: none;">[默认]</span>'+address[x].localArea+address[x].detailedAddress+'</label></td>' +
                                        '<td> '+address[x].consigneeName+'</td>' +
                                        '<td> '+phoneNumber+' </td>' +
                                        '<td class="do" style="width: 24%;"><div style="float:left;margin-right: 20px;"><a href="javascript:;" data-userAddress="'+address[x].id+'" class="edit-address" data-am-modal="{target: \'#adders-id\', closeViaDimmer: 0, width: 487, height: 420}">编辑</a>' +
                                        '| <a href="javascript:;" class="del-address" data-userAddress="'+address[x].id+'" data-type="'+address[x].type+'">删除</a></div>'+
                                        '<div><a href="javascript:void(0);" data-userAddress="'+address[x].id+'" class="am-btn-sm am-text-danger set-default-address">设为默认</a></div></td>' +
                                        '</tr>'
                            }
                        }else {
                            if(address[x].type==1){
                                html += '<tr class="show-tr-address '+checkTr+'">' +
                                        '<td><input id="doc-ipt-o-'+address[x].id+'" type="radio" name="check-address" data-userAddress="'+address[x].id+'" checked="checked" /></td><td><label for="doc-ipt-o-'+address[x].id+'" style="font-weight: normal;"><span class="am-margin-right-xs default-span-tips" style="color:#E77779;">[默认]</span>'+address[x].localArea+address[x].detailedAddress+'</label></td>' +
                                        '<td> '+address[x].consigneeName+'</td>' +
                                        '<td> '+phoneNumber+' </td>' +
                                        '<td class="do" style="width: 24%;"><div style="float:left;margin-right: 20px;"><a href="javascript:;" data-userAddress="'+address[x].id+'" class="edit-address" data-am-modal="{target: \'#adders-id\', closeViaDimmer: 0, width: 487, height: 420}">编辑</a>' +
                                        '| <a href="javascript:;" class="del-address" data-userAddress="'+address[x].id+'" data-type="'+address[x].type+'">删除</a></div>'+
                                        '<div class="lg-default-none"><a href="javascript:void(0);" data-userAddress="'+address[x].id+'" class="am-btn-sm am-text-danger set-default-address">设为默认</a></div></td>' +
                                        '</tr>'
                            }else{
                                html += '<tr class="show-tr-address '+checkTr+'">' +
                                        '<td><input id="doc-ipt-o-'+address[x].id+'" type="radio" name="check-address" data-userAddress="'+address[x].id+'"  /></td><td><label for="doc-ipt-o-'+address[x].id+'" style="font-weight: normal;"><span class="am-margin-right-xs default-span-tips" style="color:#E77779;display: none;">[默认]</span>'+address[x].localArea+address[x].detailedAddress+'</label></td>' +
                                        '<td> '+address[x].consigneeName+'</td>' +
                                        '<td> '+phoneNumber+' </td>' +
                                        '<td class="do" style="width: 24%;"><div style="float:left;margin-right: 20px;"><a href="javascript:;" data-userAddress="'+address[x].id+'" class="edit-address" data-am-modal="{target: \'#adders-id\', closeViaDimmer: 0, width: 487, height: 420}">编辑</a>' +
                                        '| <a href="javascript:;" class="del-address" data-userAddress="'+address[x].id+'" data-type="'+address[x].type+'">删除</a></div>'+
                                        '<div><a href="javascript:void(0);" data-userAddress="'+address[x].id+'" class="am-btn-sm am-text-danger set-default-address">设为默认</a></div></td>' +
                                        '</tr>'
                            }
                        }
                    }
                    if (html == ""){
                        html += '<div id="not-address-tips" class="am-text-center">'+
                                    '<span style="color:#8D8D8D;">为了方便您进行交易，请先设置收货地址</span>'+
                                '</div>';
                        $(".buyer_submit_trade_S").attr("disabled","disabled");
                    }else {
                        html = '<table id="new-address" class="am-table am-table-bordered am-table-centered am-table-striped am-table-hover am-margin-bottom-xs">'+
                                '<tbody id="new-address-tbody">'+html+'</tbody> </table>';
                        html +='<div>'+
                                '<a id="loading-address" href="javascript:;">其他收货地址</a>'+
                                '<div id="loading-img" class="am-text-center" style="display: none;">'+
                                '<img src="/assets/i/loading.gif" />'+
                                '</div>'+
                                '</div>';
                    }
                    $("#new-address-div-content").append(html);
                    $(".show-tr-address").find("input:radio[checked='checked']").click();
                }else {
                    apliytrade();
                }
                $(".buyer_submit_trade_S").click(function(){
                    apliytrade();
                });
                function apliytrade(){
                    if (type == 3){
                        var addressId = $("#new-address-tbody").find("tr td input:radio[name='check-address']:checked").attr("data-userAddress");
                    }
                    var yunFee = $.trim($("#yunFee").text());
                    if(type == 2){
                        location.href = "/trade/createTradePayAlipay?type="+type+"&amount="+amount+"&ischeck=1&size="+size+"&tradeId="+tradeId+"&bname="+bftName+"&isUserHome=1";
                    }else{
                        location.href = "/trade/createTradePayAlipay?yunFee="+yunFee+"&addressId="+addressId+"&type="+type+"&amount="+amount+"&size="+size+"&tradeId="+tradeId+"&bname="+bftName+"&isUserHome=1";
                    }
                    return;
//                    sendRequest("/trade/createTradePay",{
//                        tradeId : tradeId,
//                        type : type,
//                        amount : amount,
//                        addressId: addressId,
//                        size: size
//                    },function(res) {
//                        if (res.retCode == 1){
//                            alertTips(1,"","支付成功");
//                            $this.parent().parent().parent().removeClass("am-active");
//                            $this.parent().parent().parent().children().first().attr("disabled", true);
//                            if (type == 2){
//                                ajaxContent("../user/sale?type=0", "" ,"tradeforex_tilie",1);
//                                ajaxContent("../user/sale?type=0", "" ,"tradeforex_tilie",1);
//                            }else {
//                                ajaxContent("../user/sale?type=1", "" ,"tradeforex_tilie",1);
//                            }
//                            $("#my-popup-buy-userBuyAddress").modal('close');
//                        }else {
//                            alertTips(2,"支付失败","请重新支付");
//                        }
//                    });
                }
            });
            if (addId == 0){
                $("#now-seller-buy-detailed").click();
            }
        });

        $("body").on("click",".userInfo-upsbt",function(){
            var $this = $(this);
            $('#city-name').val($this.parent().prev().prev().prev().prev().text());
            $('#adder-name').val($this.parent().prev().prev().prev().text());
            $('#shop-name').val($this.parent().prev().prev().prev().prev().prev().text().replace(/(^\s*)|(\s*$)/g, ""));
            $('#phone-number').val($this.parent().prev().text().replace(/(^\s*)|(\s*$)/g, ""));
            $('#adder-id').val($this.attr('user-id'));
            var landlineNumber = $this.parent().prev().text().replace(/(^\s*)|(\s*$)/g, "");
            if (landlineNumber.indexOf("-")>=0){
                var areaCode = landlineNumber.split("-");
                $('#area-code').val(areaCode[0]);
                $('#phone-code').val(areaCode[1].substr(0,3));
                $('#extension-code').val(areaCode[1].substr(4,areaCode[1].length));
            }
        });

        $("body").on("click",".up-type",function(){
            var $this = $(this);
            sendRequest("/user/saveAdder",{
                id: $this.attr('user-id'),
                type : 1
            },function(res) {
                if (res.retCode == 1){
                    alertTips(1,"","操作成功");
                    ajaxContent("../user/userInfo", "" ,"tradeforex_tilie",1);
                }else {
                    alertTips(2,"服务器繁忙","请重新操作");
                }
            });
        });

        $("body").on("click",".is-delete-content",function(){
            sendRequest("/user/saveAdder",{
                id:  $("#user-address-id").val(),
                type: $("#user-address-type").val(),
                status : 1
            },function(res) {
                if (res.retCode == 1){
                    ajaxContent("../user/userInfo", "" ,"tradeforex_tilie",1);
                }else {
                    alertTips(2,"服务器繁忙","请重新操作");
                }
            });
        });
        $("body").on("click",".del-status",function(){
            var $this = $(this);
            $("#user-address-id").val($this.attr('user-id'));
            $("#user-address-type").val($this.attr('data-type'));
        });

        $("body").on("click","#userpass-sbt",function(){
            var $this = $(this);
            var passOne = $('#passOne').val();
            var passTow = $('#passTow').val();
            var verification = $('#verification').val();
            if (verification == null || verification == ""){
                $('#prompt-tips').html("");
                $('#prompt-tips').text("验证码不能为空！");
                $('#prompt').css("display","inline-block");
                return;
            }
            if (passOne == null || passOne == ""){
                $('#prompt-tips').html("");
                $('#prompt-tips').text("请输入密码！");
                $('#prompt').css("display","inline-block");
                return;
            }
            if (passTow == null || passTow == ""){
                $('#prompt-tips').html("");
                $('#prompt-tips').text("请再次输入密码！");
                $('#prompt').css("display","inline-block");
                return;
            }
            if (passOne != passTow){
                $('#prompt-tips').html("");
                $('#prompt-tips').text("两次密码不同，请重新输入！");
                $('#prompt').css("display","inline-block");
                return;
            }
            sendRequest("/index/updatePassword",{
                sendSmsCode: $('#verification').val(),
                password: $.md5(passOne)
            },function(res) {
                $('#prompt-tips').html("");
                $('#prompt').css("display","none");
                if (res.retCode == 1){
                    window.location.reload();
                    sendRequest("/login/logout",null,function(res) {});
                    window.location.href = "/index";
                }else{
                    $('#prompt-tips').html("");
                    $('#prompt-tips').text(res.retMsg);
                    $('#prompt').css("display","inline-block");
                }
            });
        });

        $("body").on("click",".userAssets-del",function(){
            var $this = $(this);
            var btfId = $this.attr("btf-id");
            var id = $this.attr("del_data_id");
            var money = $this.parent().parent().parent().parent().parent().parent().prev().prev().prev().text();
            var size = $this.parent().parent().parent().parent().parent().parent().prev().prev().prev().prev().prev().text();
            money = parseFloat(money.substring(1,money.length).replace(/[^\d\.-]/g, ""));
            $('#my-confirm-userA').modal({
                onConfirm: function() {
                    loadingshow("正在删除...");
                    sendRequest("/userAssets/saveUserAssets",{
                        id: id,
                        bId: btfId,
                        money: money,
                        size: size,
                        status : 1
                    },function(res) {
                        loadingclose();
                        if (res.retCode == 1){
                            alertTips(1,"","删除成功");
                            ajaxContent("../user/userAssets", "" ,"tradeforex_tilie",1);
                        }else {
                            alertTips(2,"服务器繁忙","请重新操作");
                        }
                    });
                },
                // closeOnConfirm: false,
                onCancel: function() {
                }
            });
        });

        var sendStatus = 0;
        $("body").on("click","#sendSms",function(){
            var $this = $(this);
            var mobile = $("#mobile_reg").text();
            if(mobile == ""){
                alertTips(3,"提示","请填写手机号");
                return;
            }
            sendsms($this,mobile,null);
        });

        $("body").on("click","#bindingse",function(){
            var $this = $(this);
            var mobile = $("#mobile_reg").text();
            if(mobile == ""){
                alertTips(3,"提示","请填写手机号");
                return;
            }
            sendsms($this,mobile,null);
        });

        var sendStatusT = 0;
        $("body").on("click","#new-bindingse",function(){
            var $this = $(this);
            var mobile = $("#new-phone").val();
            if(mobile == ""){
                alertTips(3,"提示","请填写手机号");
                return;
            }
            $('#old-code-sbt').css("display","none");
            if (sendStatusT == 0){
                sendStatusT = 1;
                sendStatus = 0;
            }
            sendsms($this,mobile,"1");
        });

        $("body").on("click","#bind-newphone",function(){
            sendRequest("/user/bindphone",{
                "sendSmsCode":$('#old-code').val(),
                "sendSmsCodeT":$('#new-code').val(),
                "mobile":$('#new-phone').val()
            },function(res){
                if (res.retCode == 1){
                    alertTips(1,"提示","修改绑定成功！");
                    $('#newphone').html("");
                    $('#newphone-tips').css("display","none");
                    $("#new-phone-close").click();
                    $("#binding-pas").modal('close');
                    ajaxContent("../user/userInfo", "" ,"tradeforex_tilie",1);
                }else {
                    $('#newphone').html("");
                    $('#newphone').text(res.retMsg);
                    $('#newphone-tips').css("display","inline-block");
                }
            })
        });

        function sendsms($this,mobile,type){
            if(sendStatus == 0){
                var url = "/login/sendSms";
                if (type){
                    url = "/login/sendSmsT";
                }
                $this.attr("disabled","true");
                $this.text("正在发送...");
                sendRequest(url,{
                    "mobile":mobile
                },function(res){
                    if(res.retCode==1){
                        sendStatus = 1;
                        var wait = 60;
                        var int = setInterval(function(){
                            if (wait == 0) {
                                sendStatus = 0;
                                sendStatusT = 0;
                                $this.text("重新发送");
                                $this.removeAttr("disabled");
                                clearInterval(int);
                            } else {
                                $this.text(wait+"s后重新发送");
                                wait--;
                            }
                        },1000);
                    }else{
                        $this.attr("disabled",false);
                        $this.text("重新发送");
                    }
                })
            }
        }

        $("body").on("click",".express-get",function(){
            var fhtml = "<tr>\n" +
                    "<td class=\"am-padding-left-xl\" style=\"color:#8D8D8D;\">正在加载中...........</td>\n" +
                    "</tr>";
            fhtml = "<tbody>"+fhtml+"</tbody>";
            $('#queryResult').html("");
            $('#queryResult').html(fhtml);
            var $this = $(this);
            sendRequest("/user/getExpress",{
                "tradeId":$this.attr("data-id")
            },function(res){
                if (res.length > 0){
                    var html = "";
                    if (res.length != 1) {
                        for (var a = 0; a < res.length - 1; a++) {
                            var expresArr = res[a];
                            for (var i = 0; i < expresArr.length; i++) {
                                var expresNum = expresArr[i];
                                if (a < res.length - 2) {
                                    if (i == 0) {
                                        var htmltext = "<tr>\n" +
                                                "<td class=\"am-padding-left-sm am-padding-right-sm\">\n" +
                                                "<div class=\"col2\">\n" +
                                                "<span class=\"step\">\n" +
                                                "<span class=\"line1\"></span>\n" +
                                                "</span>\n" +
                                                "</div>\n" +
                                                "</td>\n" +
                                                "<td class=\"am-padding-left-sm\">\n" +
                                                "<span>" + expresNum.createDate.substr(0, 10) + "</span>\n" +
                                                "<span class=\"span-sty\"></span>\n" +
                                                "</td>\n" +
                                                "<td class=\"am-padding-left\">\n" +
                                                "<span>周" + expresNum.status.substr(2, 2) + "</span>\n" +
                                                "<span class=\"span-sty\"></span>\n" +
                                                "</td>\n" +
                                                "<td class=\"am-padding-left\">\n" +
                                                "<span>" + expresNum.createDate.substr(11, expresNum.createDate.length) + "</span>\n" +
                                                "<span class=\"span-sty\"></span>\n" +
                                                "</td>\n" +
                                                "<td class=\"am-padding-left-xl\" style=\"display: inline;\">" + expresNum.expressName + "</td>\n" +
                                                "</tr>";
                                    }else {
                                        var htmltext = "<tr>\n" +
                                                "<td class=\"am-padding-left-sm am-padding-right-sm\">\n" +
                                                "<div class=\"col2\">\n" +
                                                "<span class=\"step\">\n" +
                                                "<span class=\"line1\"></span>\n" +
                                                "</span>\n" +
                                                "</div>\n" +
                                                "</td>\n" +
                                                "<td class=\"am-padding-left-sm\">\n" +
                                                "<span></span>\n" +
                                                "<span class=\"span-sty\"></span>\n" +
                                                "</td>\n" +
                                                "<td class=\"am-padding-left\">\n" +
                                                "<span></span>\n" +
                                                "<span class=\"span-sty\"></span>\n" +
                                                "</td>\n" +
                                                "<td class=\"am-padding-left\">\n" +
                                                "<span>" + expresNum.createDate.substr(11, expresNum.createDate.length) + "</span>\n" +
                                                "<span class=\"span-sty\"></span>\n" +
                                                "</td>\n" +
                                                "<td class=\"am-padding-left-xl\" style=\"display: inline;\">" + expresNum.expressName + "</td>\n" +
                                                "</tr>";
                                    }
                                    html = html + htmltext;
                                } else {
                                    if (i == expresArr.length-1) {
                                        if (i == 0) {
                                            var htmltext = "<tr>\n" +
                                                    "<td class=\"am-padding-left-sm am-padding-right-sm\">\n" +
                                                    "<div class=\"col2\">\n" +
                                                    "<span class=\"step\">\n" +
                                                    "</span>\n" +
                                                    "</div>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left-sm\">\n" +
                                                    "<span>" + expresNum.createDate.substr(0, 10) + "</span>\n" +
                                                    "<span class=\"span-sty\"></span>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left\">\n" +
                                                    "<span>周" + expresNum.status.substr(2, 2) + "</span>\n" +
                                                    "<span class=\"span-sty\"></span>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left\">\n" +
                                                    "<span>" + expresNum.createDate.substr(12, expresNum.createDate.length) + "</span>\n" +
                                                    "<span class=\"span-sty\"></span>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left-xl\" style=\"display: inline;\">" + expresNum.expressName + "</td>\n" +
                                                    "</tr>";
                                        }else {
                                            var htmltext = "<tr>\n" +
                                                    "<td class=\"am-padding-left-sm am-padding-right-sm\">\n" +
                                                    "<div class=\"col2\">\n" +
                                                    "<span class=\"step\">\n" +
                                                    "</span>\n" +
                                                    "</div>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left-sm\">\n" +
                                                    "<span></span>\n" +
                                                    "<span class=\"span-sty\"></span>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left\">\n" +
                                                    "<span></span>\n" +
                                                    "<span class=\"span-sty\"></span>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left\">\n" +
                                                    "<span>" + expresNum.createDate.substr(12, expresNum.createDate.length) + "</span>\n" +
                                                    "<span class=\"span-sty\"></span>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left-xl\" style=\"display: inline;\">" + expresNum.expressName + "</td>\n" +
                                                    "</tr>";
                                        }
                                        html = html + htmltext;
                                    }else {
                                        if (i == 0) {
                                            var htmltext = "<tr>\n" +
                                                    "<td class=\"am-padding-left-sm am-padding-right-sm\">\n" +
                                                    "<div class=\"col2\">\n" +
                                                    "<span class=\"step\">\n" +
                                                    "<span class=\"line1\"></span>\n" +
                                                    "</span>\n" +
                                                    "</div>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left-sm\">\n" +
                                                    "<span>" + expresNum.createDate.substr(0, 10) + "</span>\n" +
                                                    "<span class=\"span-sty\"></span>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left\">\n" +
                                                    "<span>周" + expresNum.status.substr(2, 2) + "</span>\n" +
                                                    "<span class=\"span-sty\"></span>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left\">\n" +
                                                    "<span>" + expresNum.createDate.substr(11, expresNum.createDate.length) + "</span>\n" +
                                                    "<span class=\"span-sty\"></span>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left-xl\" style=\"display: inline;\">" + expresNum.expressName + "</td>\n" +
                                                    "</tr>";
                                        }else {
                                            var htmltext = "<tr>\n" +
                                                    "<td class=\"am-padding-left-sm am-padding-right-sm\">\n" +
                                                    "<div class=\"col2\">\n" +
                                                    "<span class=\"step\">\n" +
                                                    "<span class=\"line1\"></span>\n" +
                                                    "</span>\n" +
                                                    "</div>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left-sm\">\n" +
                                                    "<span></span>\n" +
                                                    "<span class=\"span-sty\"></span>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left\">\n" +
                                                    "<span></span>\n" +
                                                    "<span class=\"span-sty\"></span>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left\">\n" +
                                                    "<span>" + expresNum.createDate.substr(11, expresNum.createDate.length) + "</span>\n" +
                                                    "<span class=\"span-sty\"></span>\n" +
                                                    "</td>\n" +
                                                    "<td class=\"am-padding-left-xl\" style=\"display: inline;\">" + expresNum.expressName + "</td>\n" +
                                                    "</tr>";
                                        }
                                        html = html + htmltext;
                                }
                                }
                            }
                        }
                        html = "<tbody>"+html+"</tbody>";
                        $('#queryResult').html("");
                        $('#queryResult').html(html);
                        $('#expressName-value').text(res[res.length-1][0].expressName);
                        $('#expressNum-value').text(res[res.length-1][0].status);
                    }else {
                        $('#expressName-value').text(res[0][0].expressName);
                        $('#expressNum-value').text(res[0][0].status);
                        html = "<tr>\n" +
                                "<td class=\"am-padding-left-xl\" style=\"color:#8D8D8D;\">抱歉，暂无物流信息！</td>\n" +
                                "</tr>";
                        html = "<tbody>"+html+"</tbody>";
                        $('#queryResult').html("");
                        $('#queryResult').html(html);
                    }
                }else {
                    html = "<tr>\n" +
                            "<td  class=\"am-padding-left-xl\" style=\"color:#8D8D8D;\">抱歉，暂无物流信息！</td>\n" +
                            "</tr>";
                    html = "<tbody>"+html+"</tbody>";
                    $('#queryResult').html("");
                    $('#queryResult').html(html);
                    alertTips(2,"提示","物流信息错误，请联系客服！");
                }
            })
        });

    });
</script>
</html>