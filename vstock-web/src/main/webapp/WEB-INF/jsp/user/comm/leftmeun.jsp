<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/inc.jsp" %>
<html>
<head>
    <%@include file="../../layout/head.jsp" %>
    <style type="text/css">
        @media (min-width: 1400px) {.meun-width{width: 225px;}.span-img{width: 10%;}.userInfo-div{width: 22%;}.meun-font-size{font-size: 18px;}.highcharts-with-higth{width: 200px; height: 300px;}}
        @media (max-width: 1400px) {.meun-width{width: 16%}.meun-font-size{font-size: 14px;}.span-img{width: 11%}.highcharts-with-higth{width: 150px; height: 200px;}}
        @media (max-width: 996px) {.span-img{width: 6%}}
        .am-table>tbody>tr>td{vertical-align: inherit;}
    </style>
</head>
<body>
<%@include file="../../layout/top-search.jsp"%>
<div class="am-g">
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-text-xs">
        <div class="am-u-sm-2 am-u-md-2 am-u-lg-2"><p></p></div>
        <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 am-padding-left-0 am-padding-right-0 meun-width" id="div1" style="background-color: #F6F5F4;overflow:hidden;">
            <ul class="am-nav">
                <li class="am-padding-top-sm layout-font-size-36 am-text-center am-padding-bottom" style="background-color: #EBE9E7">小庞</li>
                <li><a href="javascript:void(0)" class="home-tab am-margin-top-lg"><div style="float: left; display: block;width: 60px;height: 30px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -264px -24px;"></div><span class="text-color am-text-danger layout-font-size-24" data-url="../user/sale" data-type="1" >出售记录</span></a></li>
                <li><a href="javascript:void(0)" class="home-tab"><div style="float: left; display: block;width: 60px;height: 30px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -215px -24px;"></div><span class="text-color am-text-danger am-link-muted layout-font-size-24" data-url="../user/purchase" data-type="2">购买记录</span></a></li>
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
                ajaxContent("../user/sale", "" ,"tradeforex_tilie",1);
            }
        }
        load("");

        $("div").on("click",".offer-btn",function(){
            var url = $(this).attr("data-url");
            loadingshow();
            ajaxContent(url, "", "tradeforex_tilie",1);
        });

        $("div").on("click",".sale-up",function(){
            var $this = $(this);
            $this.parent().next().children().attr("disabled", false);
            $this.parent().next().next().children().attr("disabled", false);
            $this.parent().next().next().next().children().attr("disabled", true);
            $this.attr("disabled", true);
            var $thoes = $this.parent().parent().parent().parent().prev().prev();
            var moeny = $thoes.text();
            var a = "<input type='text' value='"+moeny.substring(1,moeny.legend)+"' style='width: 120px;'/>"
            $thoes.html("");
            $thoes.append(a);
            var $th = $this.parent().parent().parent().parent().parent().parent().find("a[select_type='select-btn']");
            $th.attr("disabled", true);
            $this.parent().parent().parent().children().first().attr("disabled", false);
        });

        $("div").on("click",".sale-sub",function(){
            var $this = $(this);
            $this.parent().prev().children().attr("disabled", false);
            $this.parent().next().children().attr("disabled", true);
            $this.parent().next().next().children().attr("disabled", false);
            $this.attr("disabled", true);
            var $thoes = $this.parent().parent().parent().parent().prev().prev();
            var moeny = $thoes.val();
//            var a = "<input type='text' value='"+moeny.substring(1,moeny.legend)+"' style='width: 120px;'/>"
            $thoes.html("");
//            $thoes.append(a);
            var $th = $this.parent().parent().parent().parent().parent().parent().find("a[select_type='select-btn']");
            $th.attr("disabled", false);
        });

        $("div").on("click",".sale-quit",function(){
            var $this = $(this);
            $this.parent().prev().prev().children().attr("disabled", false);
            $this.parent().prev().children().attr("disabled", true);
            $this.parent().next().children().attr("disabled", false);
            $this.attr("disabled", true);
            var $thoes = $this.parent().parent().parent().parent().prev().prev();
            var moeny = $thoes.children().val();
//            var a = "<input type='text' value='"+moeny.substring(1,moeny.legend)+"' style='width: 120px;'/>"
            $thoes.html("");
            $thoes.text("￥"+moeny);
            var $th = $this.parent().parent().parent().parent().parent().parent().find("a[select_type='select-btn']");
            $th.attr("disabled", false);
        });

        $("div").on("click",".deliver-goods",function(){
            var $this = $(this);
            $this.parent().parent().parent().removeClass("am-active");
            $this.parent().parent().parent().children().first().attr("disabled", true);
        });
    });
</script>
</html>