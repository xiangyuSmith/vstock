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
        .leftmenunInfo li { padding:0 5px; }
    </style>
</head>
<body>
<%@include file="../../layout/top-search.jsp"%>
<div class="am-g">
    <div class="am-container-content" style="overflow:hidden;">
        <div class="am-u-sm-3 am-u-md-3 am-padding-0" id="div1" style="background-color: #F6F5F4;overflow:hidden;">
            <input type="hidden" id="url-type" value="${urlType}"/>
            <c:if test="${not empty vUser}">
                <div class="am-padding-top-sm layout-font-size-36 am-text-center am-padding-bottom" style="background-color: #EBE9E7;padding: 25px 20px;">${vUser.nick}</div>
            </c:if>
            <ul class="leftmenunInfo am-nav am-margin-top-0">
                <li><a href="javascript:void(0)" class="home-tab am-margin-top-lg"><div style="float: left; display: block;width: 60px;height: 30px; background: url('/assets/shoesImg/personal_center.png'); background-position: -264px -24px;"></div><span class="text-color am-text-danger layout-font-size-24 home-frist" data-url="../user/sale?type=0" data-type="1" >出售记录</span></a></li>
                <li><a href="javascript:void(0)" class="home-tab"><div style="float: left; display: block;width: 60px;height: 30px; background: url('/assets/shoesImg/personal_center.png'); background-position: -215px -24px;"></div><span class="text-color am-text-danger am-link-muted layout-font-size-24" data-url="../user/sale?type=1" data-type="2">购买记录</span></a></li>
                <li><a href="javascript:void(0)" class="home-tab"><img class="am-margin-left am-padding-bottom-xs" src="/assets/shoesImg/assets.png"><span class="am-margin-left text-color am-text-danger am-link-muted layout-font-size-24" data-url="../user/userAssets" data-type="3">我的资产</span></a></li>
                <li><a href="${ctx}/user/index?type=1" id="userInfo-sbt"><div style="float: left; display: block;width: 60px;height: 36px; background: url('/assets/shoesImg/personal_center.png'); background-position: -166px -24px;"></div><span class="text-color am-text-danger am-link-muted layout-font-size-24 home-last" data-url="../user/userInfo" data-type="4">设置</span></a></li>
            </ul>
        </div>
        <div class="am-u-sm-9 am-u-md-9 am-margin-top-xl am-margin-bottom-xl" id="tradeforex_tilie"></div>
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
//            loadingshow();
            ajaxContent(url, "", "tradeforex_tilie",1);
        });

        $("body").on("click","#load_more",function(){
            $(this).children().children().attr("style","");
            var url = $(this).attr("data-url");
//            loadingshow();
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
            var type = $this.attr('data_type');
            var btf = $this.attr("btf-id");
            var $thoes = $this.parent().parent().parent().parent().prev().prev().prev().prev();
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
                    alertTips(1,"","删除成功");
                    $('.offer-btn').click();
                }else {
                    alertTips(2,"","删除失败");
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

        $("body").on("click",".deliver-goods",function(){
            var $this = $(this);
            var amount = $this.parent().parent().parent().parent().prev().prev().text();
            var bftSize = $this.parent().parent().parent().parent().prev().prev().prev().text();
            alipay($this,amount,bftSize);
        });

        $("body").on("click",".deliver-bid-goods",function(){
            var $this = $(this);
            var amount = $this.parent().parent().parent().parent().prev().prev().prev().prev().text();
            var bftSize = $this.parent().parent().parent().parent().prev().prev().prev().prev().prev().prev().text();
            alipay($this,amount,bftSize);
        });

        function alipay($this,amount,bftSize){
            var dataType = $this.attr("bid_type");
            amount = parseFloat(amount.substring(1,amount.legend).replace(/[^\d\.-]/g, ""));
            var type = $this.attr("data-type");
            var bId = $this.attr("bft-id");
            var bid = $this.attr("bid-id");
            sendRequest("/bid/createPay",{
                bid : bid,
                bId : bId,
                type : type,
                amount : amount,
                size : bftSize
            },function(res) {
                if (res.retCode == 1){
                   alertTips(1,"","支付成功");
                   $this.parent().parent().parent().removeClass("am-active");
                   $this.parent().parent().parent().children().first().attr("disabled", true);
                    if (dataType == 0){
                        ajaxContent("../user/sale?type=0", "" ,"tradeforex_tilie",1);
                    }else {
                        ajaxContent("../user/sale?type=1", "" ,"tradeforex_tilie",1);
                    }
                }else {
                    alertTips(2,"","支付失败，请重新操作！");
                }
            });
        }

        $("body").on("click",".trade-save",function(){
            var $this = $(this);
            var explain = $this.attr('explain');
            var type = $this.attr('utype');
            var status = $this.attr('status');
            var id = $this.attr('data-id');
            var bidId = $this.attr('bidId');
            var tradeNo = $this.attr('trade-no');
            var amount = $this.parent().parent().parent().parent().prev().prev().text();
            amount = parseFloat(amount.substring(1,amount.legend).replace(/[^\d\.-]/g, ""));
            var size = $this.parent().parent().parent().parent().prev().prev().prev().text();
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

        $("body").on("click",".trade-pament",function(){
            var $this = $(this);
            var bidId = $this.attr("data-id");
            var type = $this.attr("trade-type");
            var amount = $this.parent().parent().parent().parent().prev().prev().text();
            amount = parseFloat(amount.substring(1,amount.legend).replace(/[^\d\.-]/g, ""));
            sendRequest("/trade/createTradePay",{
                tradeId : bidId,
                type : type,
                amount : amount
            },function(res) {
                if (res.retCode == 1){
                    alertTips(1,"","支付成功");
                    $this.parent().parent().parent().removeClass("am-active");
                    $this.parent().parent().parent().children().first().attr("disabled", true);
                    if (type == 2){
                        ajaxContent("../user/sale?type=0", "" ,"tradeforex_tilie",1);
                    }else {
                        ajaxContent("../user/sale?type=1", "" ,"tradeforex_tilie",1);
                    }
                }else {
                    alertTips(2,"支付失败","请重新支付");
                }
            });
        });

        $("body").on("click",".adder-stn",function(){
            var $this = $(this);
            var shopName = $('#shop-name').val();
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
                alertTips(3,"编辑地址","请填写手机号或电话");
                return;
            }
            sendAddress({
                localArea : $('#city-name').val(),
                detailedAddress : $('#adder-name').val(),
                consigneeName : shopName,
                phoneNumber : phoneNumber,
                landlineNumber : phone,
                id: $('#adder-id').val()
            });
        });

        function sendAddress(data){
            sendRequest("/user/saveAdder",data,function(res) {
                if (res.retCode == 1){
                    window.location.reload();
                }else {
                    alertTips(2,"服务器繁忙","请重新操作");
                }
            });
        }

        $("body").on("click",".userInfo-upsbt",function(){
            var $this = $(this);
            $('#city-name').val($this.parent().prev().prev().prev().prev().prev().text());
            $('#adder-name').val($this.parent().prev().prev().prev().prev().text());
            $('#shop-name').val($this.parent().prev().prev().prev().prev().prev().prev().text().replace(/(^\s*)|(\s*$)/g, ""));
            $('#phone-number').val($this.parent().prev().prev().text().replace(/(^\s*)|(\s*$)/g, ""));
            $('#adder-id').val($this.attr('user-id'));
            var landlineNumber = $this.parent().prev().text().replace(/(^\s*)|(\s*$)/g, "");
            if (landlineNumber.indexOf("-")>=0){
                var areaCode = landlineNumber.split("-");
                $('#area-code').val(areaCode[0]);
                $('#phone-code').val(areaCode[1].substr(0,3));
                $('#extension-code').val(areaCode[1].substr(4,areaCode[1].length));
            }
        });

        $("body").on("click","#up-type",function(){
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

        $("body").on("click","#del-status",function(){
            var $this = $(this);
            sendRequest("/user/saveAdder",{
                id: $this.attr('user-id'),
                status : 1
            },function(res) {
                if (res.retCode == 1){
                    alertTips(1,"","删除成功");
                    ajaxContent("../user/userInfo", "" ,"tradeforex_tilie",1);
                }else {
                    alertTips(2,"服务器繁忙","请重新操作");
                }
            });
        });

        $('#verification').bind('input propertychange', function() {
            var verification = $('#verification').val();
            sendRequest("/user/verification",{
                sendSmsCode: verification
            },function(res) {
                if (res.retCode == 1){
                    $('#prompt').html("");
                    $('#prompt').removeClass("inline-block");
                    $('#prompt').addClass("none");
                }else {
                    $('#prompt').html("");
                    $('#prompt').text("验证码错误，请重新输入！");
                    $('#prompt').removeClass("none");
                    $('#prompt').addClass("inline-block");
                }
            });
        });

        $("body").on("click","#userpass-sbt",function(){
            var $this = $(this);
            var passOne = $.md5($('#passOne').val());
            var passTow = $.md5($('#passTow').val());
            var verification = $('#verification').val();
            if (verification == null || verification == ""){
                $('#prompt').html("");
                $('#prompt').text("验证码不能为空！");
                $('#prompt').removeClass("none");
                $('#prompt').addClass("inline-block");
                return;
            }
            if (passOne == null || passOne == ""){
                $('#prompt').html("");
                $('#prompt').text("请输入密码！");
                $('#prompt').removeClass("none");
                $('#prompt').addClass("inline-block");
                return;
            }
            if (passTow == null || passTow == ""){
                $('#prompt').html("");
                $('#prompt').text("请再次输入密码！");
                $('#prompt').removeClass("none");
                $('#prompt').addClass("inline-block");
                return;
            }
            if (passOne != passTow){
                $('#prompt').html("");
                $('#prompt').text("两次密码不同，请重新输入！");
                $('#prompt').removeClass("none");
                $('#prompt').addClass("inline-block");
                return;
            }

            $('#prompt').html("");
            $('#prompt').removeClass("inline-block");
            $('#prompt').addClass("none");
            sendRequest("/user/updatePassword",{
                mobile: $("#mobile_reg").text(),
                password: passOne
            },function(res) {
                if (res.retCode == 1){
                    window.location.reload();
                    sendRequest("/login/logout",null,function(res) {});
                    window.location.href = "/index";
                }
            });
        });

        $("body").on("click","#sendSms",function(){
            var $this = $(this);
            var mobile = $("#mobile_reg").text();
            if(mobile == ""){
                alertTips(3,"提示","请填写手机号");
                return;
            }
            sendsms($this,mobile);
        });


        function sendsms($this,mobile){
            $this.text("正在发送...");
            sendRequest("/login/sendSms",{
                "mobile":mobile
            },function(res){
                if(res.retCode==1){
                    var wait = 60;
                    $this.attr("disabled",true);
                    var int = setInterval(function(){
                        if (wait == 0) {
                            $this.text("重新发送");
                            $this.attr("disabled",false);
                            clearInterval(int);
                        } else {
                            $this.text(wait+"s后重新发送");
                            wait--;
                        }
                    },1000);
                }else{
                    alert(res.retMsg);
                    $this.text("重新发送");
                }
            })
        }

    });
</script>
</html>