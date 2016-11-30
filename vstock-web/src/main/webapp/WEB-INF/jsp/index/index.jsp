<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../layout/head.jsp" %>
    <title>首页</title>
</head>
<body>
<%@include file="../layout/top-search.jsp" %>
<div data-am-widget="slider" class="am-slider am-slider-i2 am-no-layout" data-am-flexslider="{controlNav:false}">
    <div class="am-viewport" style="overflow: hidden; position: relative;">
        <ul class="am-slides" style="width: 600%; transition-duration: 0s; transform: translate3d(-1213px, 0px, 0px);">
            <li class="am-slider-images clone" style="width: 1213px; float: left; display: block; background-image: url(assets/i/banner.png);" aria-hidden="true">
                <div class="am-container am-slider-desc">
                    <div class="am-slider-content">
                        <%--<h2 class="am-slider-title am-animation-slide-left" data-am-scrollspy="{animation: 'slide-left', delay: 100}">创新企业定制服务</h2>--%>
                        <%--<p data-am-scrollspy="{animation:'slide-right', delay: 600}" class="am-animation-slide-right">来自俊贰网络永不畏惧的创新力量</p>--%>
                        <%--<a href="/fuwu.html" class="am-btn-xs am-btn am-btn-danger am-radius am-animation-slide-bottom am-animation-delay-1" rel="nofollow" data-am-scrollspy="{animation:'slide-bottom', delay: 100}">READ MORE</a>--%>
                    </div>
                </div>
            </li>
            <li class="am-slider-images clone" style="width: 1213px; float: left; display: block; background-image: url(assets/i/banner.png);" aria-hidden="true">
                <div class="am-container am-slider-desc">
                    <div class="am-slider-content">
                        <%--<h2 class="am-slider-title am-animation-slide-left" data-am-scrollspy="{animation: 'slide-left', delay: 100}">创新企业定制服务</h2>--%>
                        <%--<p data-am-scrollspy="{animation:'slide-right', delay: 600}" class="am-animation-slide-right">来自俊贰网络永不畏惧的创新力量</p>--%>
                        <%--<a href="/fuwu.html" class="am-btn-xs am-btn am-btn-danger am-radius am-animation-slide-bottom am-animation-delay-1" rel="nofollow" data-am-scrollspy="{animation:'slide-bottom', delay: 100}">READ MORE</a>--%>
                    </div>
                </div>
            </li>
        </ul>
    </div>
    <ul class="am-direction-nav">
        <li class="am-nav-prev">
            <a class="am-prev am-disabled" href="#" tabindex="-1"> </a>
        </li>
        <li class="am-nav-next">
            <a class="am-next am-disabled" href="#" tabindex="-1"> </a>
        </li>
    </ul>
</div>
<div>
    <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2
  am-avg-md-3 am-avg-lg-4 am-gallery-default" data-am-gallery="{ pureview: true }" >
        <li>
            <div class="am-gallery-item">
                <a href="http://s.amazeui.org/media/i/demos/bing-1.jpg" class="">
                    <img src="http://s.amazeui.org/media/i/demos/bing-1.jpg"  alt="远方 有一个地方 那里种有我们的梦想"/>
                    <h3 class="am-gallery-title">远方 有一个地方 那里种有我们的梦想</h3>
                    <div class="am-gallery-desc">2375-09-26</div>
                </a>
            </div>
        </li>
        <li>
            <div class="am-gallery-item">
                <a href="http://s.amazeui.org/media/i/demos/bing-2.jpg" class="">
                    <img src="http://s.amazeui.org/media/i/demos/bing-2.jpg"  alt="某天 也许会相遇 相遇在这个好地方"/>
                    <h3 class="am-gallery-title">某天 也许会相遇 相遇在这个好地方</h3>
                    <div class="am-gallery-desc">2375-09-26</div>
                </a>
            </div>
        </li>
        <li>
            <div class="am-gallery-item">
                <a href="http://s.amazeui.org/media/i/demos/bing-3.jpg" class="">
                    <img src="http://s.amazeui.org/media/i/demos/bing-3.jpg"  alt="不要太担心 只因为我相信"/>
                    <h3 class="am-gallery-title">不要太担心 只因为我相信</h3>
                    <div class="am-gallery-desc">2375-09-26</div>
                </a>
            </div>
        </li>
        <li>
            <div class="am-gallery-item">
                <a href="http://s.amazeui.org/media/i/demos/bing-4.jpg" class="">
                    <img src="http://s.amazeui.org/media/i/demos/bing-4.jpg"  alt="终会走过这条遥远的道路"/>
                    <h3 class="am-gallery-title">终会走过这条遥远的道路</h3>
                    <div class="am-gallery-desc">2375-09-26</div>
                </a>
            </div>
        </li>
    </ul>
</div>
<%@include file="../layout/footer.jsp" %>
<%@include file="../layout/bottom.jsp" %>
</body>
</html>
