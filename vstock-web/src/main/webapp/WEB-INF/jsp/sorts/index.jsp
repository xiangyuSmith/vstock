<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../layout/head.jsp" %>
    <title>分类页</title>
    <style>
        .am-container-content{ max-width: 1160px; margin: auto; }
        .left-list li { margin:2!important; }
        .left-list li a{  font-family: "微软雅黑"; padding: 0 5px; text-transform: uppercase; font-size: 16px; color: #2d2d2d;  }
        .filter{  margin-top: 40px; }
        .sizeBlock{ max-width: 100%; }
        .title{ font-family: "微软雅黑", sans-serif; text-transform: uppercase;font-size: 22px; color: #2d2d2d;  margin-bottom: 7px; letter-spacing: 1px; }
        .sizeBlock .row .size{ font-weight: 600; font-family: BebasNeue; font-size: 14px; padding-top: 2px;  height: 32px;  width: 32px; text-align: center; line-height: 30px; background-color: #fcf9e9;  margin-bottom: 10px; color: #2d2d2d; }
        .sizeBlock .row { margin-left: -15px; margin-right: -15px; overflow: hidden; }
        .form-group{  margin-bottom: 0 !important; }
        .filter .priceCheck{ font-family: "proxima-nova", sans-serif;  margin-bottom: 0 !important;  position: relative; display: block; margin-bottom: 10px; }
        .filter .checkbox{ color: #2d2d2d; }
        .filter .checkbox label { display: inline-block; vertical-align: middle; position: relative;  padding-left: 5px; font-weight: normal;  }
        .box-sorts-list a{ color: #2d2d2d; }
        .show-lazy { background: url("/assets/i/loading.gif") 50% no-repeat;}
        .box-sorts-list ul li{ padding: 20px; }
        .active{ color: #FF5A5F; }
    </style>
</head>
<body>
<%@include file="../layout/top-search.jsp" %>
<div class="get-sorts"></div>
<article>
    <div class="am-g am-container-content">
        <div class="am-u-md-2 am-u-xs-12 am-hide-sm">
            <div style="margin-top: 56px;">
                <div id="sss" class="title">分类
                </div>
                <ul class="am-nav left-list">
                    <c:forEach items="${brandList}" var="brand" >
                        <li><a href="#">${brand}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="sizeBlock filter">
                <div class="title">尺码</div>
                <div class="row">
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">3.5</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">4</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">4.5</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">5</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">5.5</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">6</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">6.5</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">7</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">7.5</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">8</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">8.5</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">9</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">9.5</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">10</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">10.5</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">11</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">11.5</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">12</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">12.5</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">13</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">13.5</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">14</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">14.5</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">15</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">16</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6"><a href="#"><div class="size">17</div></a></div>
                    <div class="am-u-md-3 am-u-sm-4 am-u-xs-6 am-u-end"><a href="#"><div class="size">18</div></a></div>
                </div>
            </div>
            <div class="priceBlock filter">
                <div class="title">价格</div>
                <div class="form-group"><a class="checkbox priceCheck" href="#"><input type="checkbox" id="price0"><label for="price0">500元以下</label></a></div>
                <div class="form-group"><a class="checkbox priceCheck" href="#"><input type="checkbox" id="price1"><label for="price1">500元 - 1000元</label></a></div>
                <div class="form-group"><a class="checkbox priceCheck" href="#"><input type="checkbox" id="price2"><label for="price2">1000元 - 2000元</label></a></div>
                <div class="form-group"><a class="checkbox priceCheck" href="#"><input type="checkbox" id="price3"><label for="price3">2000元 - 3000元</label></a></div>
                <div class="form-group"><a class="checkbox priceCheck" href="#"><input type="checkbox" id="price4"><label for="price4">3000元 - 4000元</label></a></div>
                <div class="form-group"><a class="checkbox priceCheck" href="#"><input type="checkbox" id="price5"><label for="price5">4000元 - 5000元</label></a></div>
                <div class="form-group"><a class="checkbox priceCheck" href="#"><input type="checkbox" id="price6"><label for="price6">5000元以上</label></a></div>
            </div>
            <div class="yearBlock filter">
                <div class="title">年份</div>
                <div class="row">
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year0"><label for="year0">&lt; 2001</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year1"><label for="year1">2001</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year2"><label for="year2">2002</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year3"><label for="year3">2003</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year4"><label for="year4">2004</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year5"><label for="year5">2005</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year6"><label for="year6">2006</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year7"><label for="year7">2007</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year8"><label for="year8">2008</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year9"><label for="year9">2009</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year10"><label for="year10">2010</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year11"><label for="year11">2011</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year12"><label for="year12">2012</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year13"><label for="year13">2013</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year14"><label for="year14">2014</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year15"><label for="year15">2015</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0 am-u-end"><div class="form-group"><a class="checkbox yearCheck" href="#"><input type="checkbox" id="year16"><label for="year16">2016</label></a></div></div>
                </div>
            </div>
        </div>
        <div class="am-u-md-10">
            <div class="am-tips"></div>
            <div id="box-sorts-list" class="box-sorts-list"></div>
        </div>
    </div>
</article>

<div class="container" style="padding: 100px 50px 10px;" >
    <button type="button" class="btn btn-primary" title="Popover title"
            data-container="body" data-toggle="popover" data-placement="top"
            data-content="顶部的 Popover 中的一些内容">
        顶部的 Popover
    </button>
    <button type="button" class="btn btn-success" title="Popover title"
            data-container="body" data-toggle="popover" data-placement="bottom"
            data-content="底部的 Popover 中的一些内容">
        底部的 Popover
    </button>
    <button type="button" class="btn btn-warning" title="Popover title"
            data-container="body" data-toggle="popover" data-placement="right"
            data-content="右侧的 Popover 中的一些内容">
        右侧的 Popover
    </button>
</div>
<%@include file="../layout/footer.jsp" %>
<%@include file="../layout/bottom.jsp" %>
<script>
    $(function(){
        function load(){
            ajaxContent("/sorts/list",null,"box-sorts-list",0);
        }
        load();
    });
</script>
</body>
</html>
