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
        .active{ color: #FF5A5F!important; }
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
                        <li><a href="javasript:;" class="brand-btn">${brand}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="sizeBlock filter">
                <div class="title">尺码</div>
                <div class="row">
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">3.5</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">4</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">4.5</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">5</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">5.5</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">6</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">6.5</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">7</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">7.5</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">8</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">8.5</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">9</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">9.5</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">10</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">10.5</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">11</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">11.5</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">12</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">12.5</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">13</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">13.5</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">14</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">14.5</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">15</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">16</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6"><a href="javascript:;"><div class="size">17</div></a></div>
                    <div class="am-u-lg-3 am-u-md-4 am-u-sm-4 am-u-xs-6 am-u-end"><a href="javascript:;"><div class="size">18</div></a></div>
                </div>
            </div>
            <div class="priceBlock filter">
                <div class="title">价格</div>
                <div class="form-group"><a class="checkbox priceCheck" href="javascript:;"><input type="checkbox" id="price0" class="price" data-val="0-500"><label for="price0">500元以下</label></a></div>
                <div class="form-group"><a class="checkbox priceCheck" href="javascript:;"><input type="checkbox" id="price1" class="price" data-val="500-1000"><label for="price1">500元 - 1000元</label></a></div>
                <div class="form-group"><a class="checkbox priceCheck" href="javascript:;"><input type="checkbox" id="price2" class="price" data-val="1000-2000"><label for="price2">1000元 - 2000元</label></a></div>
                <div class="form-group"><a class="checkbox priceCheck" href="javascript:;"><input type="checkbox" id="price3" class="price" data-val="2000-3000"><label for="price3">2000元 - 3000元</label></a></div>
                <div class="form-group"><a class="checkbox priceCheck" href="javascript:;"><input type="checkbox" id="price4" class="price" data-val="3000-4000"><label for="price4">3000元 - 4000元</label></a></div>
                <div class="form-group"><a class="checkbox priceCheck" href="javascript:;"><input type="checkbox" id="price5" class="price" data-val="4000-5000"><label for="price5">4000元 - 5000元</label></a></div>
                <div class="form-group"><a class="checkbox priceCheck" href="javascript:;"><input type="checkbox" id="price6" class="price" data-val="5000-9999999"><label for="price6">5000元以上</label></a></div>
            </div>
            <div class="yearBlock filter">
                <div class="title">年份</div>
                <div class="row">
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year0" class="year" data-val="<2000"><label for="year0">&lt; 2001</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year1" class="year" data-val="2001"><label for="year1">2001</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year2" class="year" data-val="2002"><label for="year2">2002</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year3" class="year" data-val="2003"><label for="year3">2003</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year4" class="year" data-val="2004"><label for="year4">2004</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year5" class="year" data-val="2005"><label for="year5">2005</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year6" class="year" data-val="2006"><label for="year6">2006</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year7" class="year" data-val="2007"><label for="year7">2007</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year8" class="year" data-val="2008"><label for="year8">2008</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year9" class="year" data-val="2009"><label for="year9">2009</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year10" class="year" data-val="2010"><label for="year10">2010</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year11" class="year" data-val="2011"><label for="year11">2011</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year12" class="year" data-val="2012"><label for="year12">2012</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year13" class="year" data-val="2013"><label for="year13">2013</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year14" class="year" data-val="2014"><label for="year14">2014</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year15" class="year" data-val="2015"><label for="year15">2015</label></a></div></div>
                    <div class="am-u-sm-12 am-u-md-6 am-padding-0 am-u-end"><div class="form-group"><a class="checkbox yearCheck" href="javascript:;"><input type="checkbox" id="year16" class="year" data-val="2016"><label for="year16">2016</label></a></div></div>
                </div>
            </div>
        </div>
        <div class="am-u-md-10">
            <div class="am-tips"></div>
            <div id="box-sorts-list" class="box-sorts-list"></div>
        </div>
    </div>
</article>
<%@include file="../layout/footer.jsp" %>
<%@include file="../layout/bottom.jsp" %>
<script>
    $(function(){
        var sizes = new Array();
        var prices = new Array();
        var years = new Array();

        function load(){
            ajaxContent("/sorts/list",null,"box-sorts-list",0);
        }
        load();

        $('.brand-btn').click(function(){
            $(this).parents().find("a").removeClass("active");
            $(this).addClass("active");
        })

        $(".size").click(function(){
            var $this = $(this);
            if($this.hasClass("active")){
                $this.removeClass('active');
                sizes = $.grep(sizes, function(value) {
                    return value != $.trim($this.text());
                });
            }else{
                $this.addClass("active");
                sizes.splice(-1,0,$.trim($this.text()));
            }
        });

        $(".price").change(function(){
           var $this = $(this);
           if($this.is(':checked')){
               prices.splice(-1,0,$this.attr("data-val"));
           }else{
               prices = $.grep(prices, function(value) {
                   return value != $.trim($this.attr("data-val"));
               });
           }
        });

        $(".year").change(function(){
            var $this = $(this);
            if($this.is(':checked')){
                years.splice(-1,0,$this.attr("data-val"));
            }else{
                years = $.grep(prices, function(value) {
                    return value != $.trim($this.attr("data-val"));
                });
            }
            alert(years);
        });


    });
</script>
</body>
</html>
