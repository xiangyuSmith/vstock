<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../layout/head.jsp" %>
    <title>首页</title>
    <style type="text/css">
        .am-container-content{ max-width: 1000px; margin: auto; }
        .am-container-sell{ max-width: 1000px; margin: auto; }
        .am-imglist{ margin-top:50px; }
        .am_list_block { padding: 10px; background: #fff; position: relative; }
        .am_img_bg { display: block; }
        .am_listimg_info { color:#060606; padding-top: 5px; }
        .am_imglist_user { color: #b7b7b7; padding: 10px 0; display: block; }
        .am_imglist_user_ico { display: inline-block; height: 20px; padding-right: 8px; }
        .am_imglist_user_font { font-size: 12px; }
        .am_news_load { max-width: 810px; margin: 0 auto; color: #d1d1d1; height: 43px; line-height: 43px; background: #fff; text-align: center; margin-top: 20px; cursor: pointer; box-shadow: 0 1px 2px rgba(0,0,0,0.05); }
        .show-lazy { background: url("/assets/i/loading.gif") 50% no-repeat;}
        .am-container-content ul li{ padding: 20px; }
        body{font-size:12px}
        #buyBid,#sellBid{
            overflow:hidden;
            height:100px;
            width:280px;
            position:relative;
        }
        ul li{ list-style-type:none; }
    </style>
</head>
<body>
<%@include file="../layout/top-index.jsp" %>
<div class="get-index" style="margin-top: -80px;">
    <div class="am-g">
        <div class="am-u-lg-12">
            <div class="get-title-search">
                <form class="am-topbar-left am-form-inline" role="search">
                    <div class="am-form-group am-form-icon" style="color: #EB615F;font-size: 16px;opacity: 0.9;">
                        <input id="index_search" type="text" class="am-form-field get-input" placeholder="搜索颜色、款式......">
                        <i class="am-icon-search" style="font-size: 2.6rem;z-index: 0;"></i>
                    </div>
                </form>
            </div>
        </div>
        <div class="am-u-lg-12">
            <div class="get-title" data-am-scrollspy="{animation:'fade',repeat: true}">
                <div class="get_font_left am-hide-sm">当前鞋库</div>
                <div class="get_font_center" id="banner_num" >56000</div>
                <div class="get_font_rigth am-hide-sm">双鞋</div>
            </div>
        </div>
    </div>
</div>
<article class="am-show-lg-only">
    <div class="am-container-sell" style="height: 135px;">
        <div class="am-u-md-6 am-padding-right-sm">
            <div class="am-u-md-12" style="background-color: #EDFEF1;padding: 3px;">
                <div class="am-u-md-3" style="height: 80%;border-right: 1px solid #3fcd65;line-height: 110px;text-align: center;margin-top: 15px;"><span style="font-size: 26px;color: #3fcd65;">买家</span></div>
                <div class="am-u-md-9" style="height: 100%;font-size: 36px;color: #221714;padding-top: 17px;">
                    <div id="buyBid">
                        <ul id="buyBid1">
                            <c:forEach items="${buyBidList}" var="buybid">
                                <li><p><c:out value="${fn:substring(buybid.user.mobile, 0, 3)}" />****<c:out value="${fn:substring(buybid.user.mobile, 7, 11)}" /></p><p title="${buybid.bftName}"><c:out value="${fn:substring(buybid.bftName, 0, 30)}" /></p><p>出价：<fmt:formatNumber value="${buybid.bidMoney}" type="currency" pattern="#,#00.0#"/>元</p></li>
                            </c:forEach>
                        </ul>
                        <ul id="buyBid2"></ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-u-md-6 am-padding-0">
            <div class="am-u-md-12" style="background-color: #FEEFEF;padding: 3px;">
                <div class="am-u-md-3" style="height: 80%;border-right: 1px solid #E26472;line-height: 110px;text-align: center;margin-top: 15px;"><span style="font-size: 26px;color: #E26472;">卖家</span></div>
                <div class="am-u-md-9" style="height: 100%;font-size: 36px;color: #221714;padding-top: 17px;">
                    <div id="sellBid">
                        <ul id="sellBid1">
                            <c:forEach items="${sellBidList}" var="sellbid">
                                <li><p><c:out value="${fn:substring(sellbid.user.mobile, 0, 3)}" />****<c:out value="${fn:substring(sellbid.user.mobile, 7, 11)}" /></p><p title="${sellbid.bftName}"><c:out value="${fn:substring(sellbid.bftName, 0, 30)}" /></p><p>出价：<fmt:formatNumber value="${sellbid.bidMoney}" type="currency" pattern="#,#00.0#"/>元</p></li>
                            </c:forEach>
                        </ul>
                        <ul id="sellBid2"></ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</article>
<article class="am-show-lg-only am-text-center">
    <div style="max-width: 1000px;margin:auto;margin-top: 60px;margin-bottom: 74px;overflow: hidden;" class="am-margin-top-xl am-text-center" >
        <div class="am-u-md-4 am-padding-0">
            <div class="am-text-left" style="max-width: 256px; margin:0 auto">
                <span class="layout-font-size-26 am-padding-left-sm" id="frist_brand"></span><span class="layout-font-size-26 am-padding-left-sm" style="font-family: '黑体'">指数</span>
                <div id="containerA" style="height: 136px;"></div>
                <span class="layout-font-size-30 am-padding-left-sm" id="jordan_current" style="color: #060606;font-weight: bold;"></span><br/>
                <span class="layout-font-size-22 am-padding-left-sm" id="jordan_change"></span>
            </div>
        </div>
        <div class="am-u-md-4 am-padding-0">
            <div class="am-text-left" style="max-width: 256px; margin:0 auto">
                <span class="layout-font-size-26 am-padding-left-sm" id="sen_brand"></span><span class="layout-font-size-26 am-padding-left-sm" style="font-family: '黑体'">指数</span>
                <div id="containerB" style="height: 136px;"></div>
                <span class="layout-font-size-30 am-padding-left-sm" id="nike_current" style="color: #060606;font-weight: bold;"></span><br/>
                <span class="layout-font-size-22 am-padding-left-sm" id="nike_change"></span>
            </div>
        </div>
        <div class="am-u-md-4 am-padding-0">
            <div class="am-text-left" style="max-width: 256px; margin:0 auto">
                <span class="layout-font-size-26 am-padding-left-sm" id="th_brand"></span><span class="layout-font-size-26 am-padding-left-sm" style="font-family: '黑体'">指数</span>
                <div id="containerC" style="height: 136px;"></div>
                <span class="layout-font-size-30 am-padding-left-sm" id="yezzy_current" style="color: #060606;font-weight: bold;"></span><br/>
                <span class="layout-font-size-22 am-padding-left-sm" id="yezzy_change"></span>
            </div>
        </div>
    </div>

</article>
<article>
    <div class="am-container-content">
        <div class="am-g am-imglist">
            <div class="am-text-center" data-am-scrollspy="{animation: 'slide-right', repeat: false}">
                <span style="font-size: 30px;font-weight: bold;color: #060606;">爆款推荐</span><br/>
                <a href="/sorts"><span style="font-size: 18px;color: #060606;">了解更多 ></span></a>
            </div>
            <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-default am_index_addimglist am-no-layout">
                <c:forEach items="${bList}" var="b">
                    <c:if test="${b.type==1}">
                        <li>
                            <div class="am-gallery-item am_list_block">
                                <a href="/detail?proName=${b.name}" class="am_img_bg">
                                    <img class="show-lazy lazy" src="/assets/i/blank.gif" data-echo="${configMap._site_url}${b.smallImgUrl}" style="display: inline;">
                                </a>
                                <div class="am_listimg_info">
                                    <div class="am-gallery-title"><span style="font-size: 22px;"><b>${b.name}</b></span></div>
                                    <div>
                                        <div style="font-size: 16px;">最近售出</div>
                                        <div style="font-size: 22px;">68双</div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
        <div class="am-g am-imglist">
            <div class="am-text-center" data-am-scrollspy="{animation: 'slide-right', repeat: false}">
                <span style="font-size: 30px;font-weight: bold;color: #060606;">最低卖价</span><br/>
                <a href="/sorts"><span style="font-size: 18px;color: #060606;">了解更多 ></span></a>
            </div>
            <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-default am_index_addimglist am-no-layout">
                <c:forEach items="${bList}" var="b">
                    <c:if test="${b.type==2}">
                        <li>
                            <div class="am-gallery-item am_list_block">
                                <a href="/detail?proName=${b.name}" class="am_img_bg">
                                    <img class="show-lazy lazy" src="/assets/i/blank.gif" data-echo="${configMap._site_url}${b.smallImgUrl}" style="display: inline;">
                                </a>
                                <div class="am_listimg_info">
                                    <div class="am-gallery-title"><span style="font-size: 22px;"><b>${b.name}</b></span></div>
                                    <div>
                                        <div style="font-size: 16px;">最近售出</div>
                                        <div style="font-size: 22px;">68双</div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
        <div class="am-g am-imglist">
            <div class="am-text-center" data-am-scrollspy="{animation: 'slide-right', repeat: false}">
                <span style="font-size: 30px;font-weight: bold;color: #060606;">最高叫价</span><br/>
                <a href="/sorts"><span style="font-size: 18px;color: #060606;">了解更多 ></span></a>
            </div>
            <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-default am_index_addimglist am-no-layout">
                <c:forEach items="${bList}" var="b">
                    <c:if test="${b.type==3}">
                        <li>
                            <div class="am-gallery-item am_list_block">
                                <a href="/detail?proName=${b.name}" class="am_img_bg">
                                    <img class="show-lazy lazy" src="/assets/i/blank.gif" data-echo="${configMap._site_url}${b.smallImgUrl}" style="display: inline;">
                                </a>
                                <div class="am_listimg_info">
                                    <div class="am-gallery-title"><span style="font-size: 22px;"><b>${b.name}</b></span></div>
                                    <div>
                                        <div style="font-size: 16px;">最近售出</div>
                                        <div style="font-size: 22px;">68双</div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
        <div class="am-g am-imglist">
            <div class="am-text-center" data-am-scrollspy="{animation: 'slide-right', repeat: false}">
                <span style="font-size: 30px;font-weight: bold;color: #060606;">最大涨幅</span><br/>
                <a href="/sorts"><span style="font-size: 18px;color: #060606;">了解更多 ></span></a>
            </div>
            <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-default am_index_addimglist am-no-layout">
                <c:forEach items="${bList}" var="b">
                    <c:if test="${b.type==4}">
                        <li>
                            <div class="am-gallery-item am_list_block">
                                <a href="/detail?proName=${b.name}" class="am_img_bg">
                                    <img class="show-lazy lazy" src="/assets/i/blank.gif" data-echo="${configMap._site_url}${b.smallImgUrl}" style="display: inline;">
                                </a>
                                <div class="am_listimg_info">
                                    <div class="am-gallery-title"><span style="font-size: 22px;"><b>${b.name}</b></span></div>
                                    <div>
                                        <div style="font-size: 16px;">最低卖价</div>
                                        <div style="font-size: 22px;">￥800（12%）</div>
                                        <div class="am-margin-top-sm" style="font-size: 14px;">20分钟前</div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
        <div class="am-g am-imglist">
            <div class="am-text-center" data-am-scrollspy="{animation: 'slide-right', repeat: false}">
                <span style="font-size: 30px;font-weight: bold;color: #060606;">即将发布</span><br/>
                <a href="/sorts"><span style="font-size: 18px;color: #060606;">了解更多 ></span></a>
            </div>
            <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-default am_index_addimglist am-no-layout">
                <c:forEach items="${bList}" var="b">
                    <c:if test="${b.type==5}">
                        <li>
                            <div class="am-gallery-item am_list_block">
                                <a href="/detail?proName=${b.name}" class="am_img_bg">
                                    <img class="show-lazy lazy" src="/assets/i/blank.gif" data-echo="${configMap._site_url}${b.smallImgUrl}" style="display: inline;">
                                </a>
                                <div class="am_listimg_info">
                                    <div class="am-gallery-title"><span style="font-size: 22px;"><b>${b.name}</b></span></div>
                                    <div>
                                        <div style="font-size: 22px;">官方售价:￥800</div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>

        <div class="am_news_load am_news_load_index"><span><i class="am-icon-spinner am-icon-spin" style="display: none;"></i>了解更多</span></div>
        <br>
        <br>
        <br>
    </div>

</article>
<%@include file="../layout/footer.jsp" %>
<%@include file="../layout/bottom.jsp" %>
<script type="text/javascript">
    window.onload=function(){
        var speed=15;
        var buyBid=document.getElementById("buyBid");
        var buyBid2=document.getElementById("buyBid2");
        var buyBid1=document.getElementById("buyBid1");
        var sellBid=document.getElementById("sellBid");
        var sellBid2=document.getElementById("sellBid2");
        var sellBid1=document.getElementById("sellBid1");
        buyBid2.innerHTML=buyBid1.innerHTML
        sellBid2.innerHTML=sellBid1.innerHTML
        function Marquee(){
            if(buyBid.scrollTop>=buyBid1.offsetHeight){
                buyBid.scrollTop=0;
                sellBid.scrollTop=0;
                speedNum = 0;
                var bidhtml = "";
                var sellhtml = "";
                sendRequest("/index/getNewBid",null,function(res){
                    var buyList = res.data.buyBidList;
                    var sellList = res.data.sellBidList;
                    for(var i = 0;i < buyList.length;i++){
                        var mobile = buyList[i].user.mobile;
                        var bftName = buyList[i].bftName;
                        var bidMoney = buyList[i].bidMoney;
                        bidhtml += '<li><p>'+mobile.substr(0,3)+'****'+mobile.substr(7,11)+'</p><p title="'+bftName+'">'+bftName.substr(0,30)+'</p><p>出价：'+bidMoney+'元</p></li>';
                    }
                    for(var i = 0;i < sellList.length;i++){
                        var mobile = sellList[i].user.mobile;
                        var bftName = sellList[i].bftName;
                        var bidMoney = sellList[i].bidMoney;
                        sellhtml += '<li><p>'+mobile.substr(0,3)+'****'+mobile.substr(7,11)+'</p><p title="'+bftName+'">'+bftName.substr(0,30)+'</p><p>出价：'+bidMoney+'元</p></li>';
                    }
                    buyBid1.innerHTML=bidhtml;
                    buyBid2.innerHTML=bidhtml;
                    sellBid1.innerHTML=sellhtml;
                    sellBid2.innerHTML=sellhtml;
                });
            }else{
                if(buyBid.scrollTop==0){
                    buyBid.scrollTop=buyBid.scrollTop+1;
                    sellBid.scrollTop=sellBid.scrollTop+1
                }else{
                    if(buyBid.scrollTop%124==0){
                        outInterval();
                    }else{
                        buyBid.scrollTop=buyBid.scrollTop+1;
                        sellBid.scrollTop=sellBid.scrollTop+1;
                    }
                }
            }
        }
        var MyMar = setInterval(Marquee,speed)
        function outInterval(){
            clearInterval(MyMar);
            setTimeout(function(){
                buyBid.scrollTop=buyBid.scrollTop+1;
                sellBid.scrollTop=sellBid.scrollTop+1;
                MyMar=setInterval(Marquee,speed);
            },1500);
        }
    }
    $(function(){

        //@懒加载
        $("img.am_img").lazyload();
        $("a.am_img_bg").lazyload({
            effect : 'fadeIn'
        });

        //@首页 数字跳动
        var options = {
            useEasing : true,
            useGrouping : true,
            separator : '',
            decimal : '.',
            prefix : '',
            suffix : ''
        };
        var banner_num = new CountUp("banner_num", 56000, 56214, 0, 5, options);
        banner_num.start();

        document.onkeydown = function(e){
            if($("#index_search").val() != "" && $("#index_search").val() != undefined){
                var ev = document.all ? window.event : e;
                if(ev.keyCode==13) {
                    location.href = "sort?productName="+$("#index_search").val();
                }
            }
        }

        sendRequest("/index/overallIncrease",{
            brand : 'JORDAN'
        },function(res) {
            $('#frist_brand').text("JORDAN");
            if (res) {
                $('#jordan_current').text(res.Current_market_value);
                $('#jordan_change').text("￥"+res.Change_range + "(" + res.Percentage_change + "");
            }
        });

        sendRequest("/index/overallIncrease",{
            brand : 'NIKE'
        },function(res) {
            $('#sen_brand').text("NIKE");
            if (res) {
                $('#nike_current').text(res.Current_market_value);
                $('#nike_change').text("￥"+res.Change_range + "(" + res.Percentage_change + "");
            }
        });

        sendRequest("/index/overallIncrease",{
            brand : 'YEZZY'
        },function(res) {
            $('#th_brand').text("YEZZY");
            if (res) {
                $('#yezzy_current').text(res.Current_market_value);
                $('#yezzy_change').text("￥"+res.Change_range + "(" + res.Percentage_change + "");
            }
        });
    });
</script>
</body>
</html>
