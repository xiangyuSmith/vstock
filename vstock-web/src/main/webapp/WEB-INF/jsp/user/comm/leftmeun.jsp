<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/inc.jsp" %>
<html>
<head>
    <%@include file="../../layout/head.jsp" %>
    <style type="text/css">
        @media (min-width: 1400px) {
            .meun-width{width: 12%}

            .span-img{width: 8%}
        }
        @media (max-width: 1400px) {
            .meun-width{width: 16%}

            .span-img{width: 11%}
        }
        @media (max-width: 996px) {
            .span-img{width: 6%}
        }
        .am-table>tbody>tr>td{     vertical-align: inherit;}
    </style>
</head>
<body>
<%@include file="../../layout/top-search.jsp"%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-text-xs">
        <div class="am-u-sm-2 am-u-md-2 am-u-lg-2"><p></p></div>
        <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 am-padding-left-0 am-padding-right-0 meun-width" id="div1" style="background-color: #F6F5F4;overflow:hidden;">
            <ul class="am-nav">
                <li class="am-padding-top-sm" style="background-color: #EBE9E7"><a href="#" class="layout-font-size-36  am-link-muted">小庞</a></li>
                <li><a href="javascript:void(0)" class="home-tab am-margin-top-lg"><div style="float: left; display: block;width: 60px;height: 30px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -264px -24px;"></div><span class="text-color am-text-danger layout-font-size-24" data-url="../bid/sale" data-type="1" >出售记录</span></a></li>
                <li><a href="javascript:void(0)" class="home-tab"><div style="float: left; display: block;width: 60px;height: 30px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -215px -24px;"></div><span class="text-color am-text-danger am-link-muted layout-font-size-24" data-url="../bid/purchase" data-type="2">购买记录</span></a></li>
                <li><a href="javascript:void(0)" class="home-tab"><img class="am-margin-left am-padding-bottom-sm" src="../../../../assets/shoesImg/assets.png"><span class="am-margin-left text-color am-text-danger am-link-muted layout-font-size-24" data-url="../bid/userAssets" data-type="3">我的资产</span></a></li>
                <li><a href="javascript:void(0)" class="home-tab"><div style="float: left; display: block;width: 60px;height: 36px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -166px -24px;"></div><span class="text-color am-text-danger am-link-muted layout-font-size-24" data-url="../bid/userInfo" data-type="4">设置</span></a></li>
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
                ajaxContent("../bid/sale", "" ,"tradeforex_tilie",1);
            }
        }
        load("");
    });
</script>
</html>