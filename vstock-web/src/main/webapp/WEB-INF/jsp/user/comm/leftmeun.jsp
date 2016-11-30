<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/inc.jsp" %>
<html>
<head>
    <%@include file="../../layout/head.jsp" %>
</head>
<body>
<%@include file="../../layout/top-search.jsp"%>
<div class="am-container">
    <div class="am-center">
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-text-xs am-center">
            <div class="am-u-sm-1 am-u-md-1 am-u-lg-1"><p></p></div>
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 am-text-lg am-padding-left-0 am-padding-right-0" style="background-color: #F6F5F4;">
                <ul class="am-nav">
                    <li class="am-padding-top-sm" style="background-color: #EBE9E7"><a href="#" class="am-text-xxl am-link-muted">小庞</a></li>
                    <li><a href="javascript:void(0)" class="home-tab"><i class="am-icon-sellsy am-margin-right-xs am-link-muted"></i><span class="text-color am-text-danger" data-url="../index/testSale" data-type="1">出售记录</span></a></li>
                    <li><a href="javascript:void(0)" class="home-tab"><i class="am-icon-cart-arrow-down  am-margin-right-xs am-link-muted"></i><span class="text-color am-text-danger am-link-muted" data-url="../index/testPurchase" data-type="2">购买记录</span></a></li>
                    <li><a href="javascript:void(0)" class="home-tab"><i class="am-icon-gear am-margin-right-xs am-link-muted"></i><span class="text-color am-text-danger am-link-muted" data-url="../index/testUserInfo" data-type="3">个人设置</span></a></li>
                    <li><a href="javascript:void(0)" class="home-tab"><i class="am-icon-rmb am-margin-right-xs am-link-muted"></i><span class="text-color am-text-danger am-link-muted" data-url="../index/testUserAssets" data-type="4">我的资产</span></a></li>
                </ul>
            </div>
            <div class="am-u-sm-8 am-u-md-8 am-u-lg-8 am-u-end am-margin-top-xl am-margin-bottom-xl" id="tradeforex_tilie"></div>
        </div>
    </div>
</div>
<%@include file="../../layout/footer.jsp" %>
<%@include file="../../layout/bottom.jsp" %>
</body>
<script type="text/javascript">
    jQuery(function($){

        $(".home-tab").click(function () {
            loadingshow();
            load($(this));
        });

        function load($thoes){
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
                ajaxContent("../index/testSale", "" ,"tradeforex_tilie",1);
            }
        }
        load("");
    });
</script>
</html>