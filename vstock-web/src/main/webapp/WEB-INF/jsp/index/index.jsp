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
                    <p>138****6859</p>
                    <p>Adidas Yee idaBlack (2016)</p>
                    <p>出价：2500元</p>
                </div>
            </div>
        </div>
        <div class="am-u-md-6 am-padding-0">
            <div class="am-u-md-12" style="background-color: #FEEFEF;padding: 3px;">
                <div class="am-u-md-3" style="height: 80%;border-right: 1px solid #E26472;line-height: 110px;text-align: center;margin-top: 15px;"><span style="font-size: 26px;color: #E26472;">卖家</span></div>
                <div class="am-u-md-9" style="height: 100%;font-size: 36px;color: #221714;padding-top: 17px;">
                    <p>138****6859</p>
                    <p>Adidas Yee idaBlack (2016)</p>
                    <p>出价：2500元</p>
                </div>
            </div>
        </div>
    </div>
</article>
<article class="am-show-lg-only am-text-center">
    <div style="max-width: 1000px;margin:auto;margin-top: 60px;margin-bottom: 74px;overflow: hidden;" class="am-margin-top-xl am-text-center" >
        <div class="am-u-md-4 am-padding-0">
            <div class="am-text-left" style="max-width: 256px; margin:0 auto">
                <span class="layout-font-size-26 am-padding-left-sm">JORDAN</span><span class="layout-font-size-26 am-padding-left-sm" style="font-family: '黑体'">指数</span>
                <div id="containerA" style="height: 136px;"></div>
                <span class="layout-font-size-30 am-padding-left-sm" style="color: #060606;font-weight: bold;">366，673</span><br/>
                <span class="layout-font-size-22 am-padding-left-sm">-￥1.113(0.6%)</span>
            </div>
        </div>
        <div class="am-u-md-4 am-padding-0">
            <div class="am-text-left" style="max-width: 256px; margin:0 auto">
                <span class="layout-font-size-26 am-padding-left-sm">NIKE</span><span class="layout-font-size-26 am-padding-left-sm" style="font-family: '黑体'">指数</span>
                <div id="containerB" style="height: 136px;"></div>
                <span class="layout-font-size-30 am-padding-left-sm" style="color: #060606;font-weight: bold;">171，573</span><br/>
                <span class="layout-font-size-22 am-padding-left-sm">+￥1.113(0.6%)</span>
            </div>
        </div>
        <div class="am-u-md-4 am-padding-0">
            <div class="am-text-left" style="max-width: 256px; margin:0 auto">
                <span class="layout-font-size-26 am-padding-left-sm">YEZZY</span><span class="layout-font-size-26 am-padding-left-sm" style="font-family: '黑体'">指数</span>
                <div id="containerC" style="height: 136px;"></div>
                <span class="layout-font-size-30 am-padding-left-sm" style="color: #060606;font-weight: bold;">366，673</span><br/>
                <span class="layout-font-size-22 am-padding-left-sm">-￥1.113(0.6%)</span>
            </div>
        </div>
    </div>

</article>
<article>
    <div class="am-container-content">
        <div class="am-g am-imglist">
            <div class="am-text-center" data-am-scrollspy="{animation: 'slide-right', repeat: false}">
                <span style="font-size: 30px;font-weight: bold;color: #060606;">更多流行</span><br/>
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
    });
</script>
</body>
</html>
