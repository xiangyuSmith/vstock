<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<div id="tips-model" style="width: 350px;height: 453px;display: none;">
    <div class="am-u-md-12 am-text-center am-padding-left-lg am-padding-right-lg" style="border-bottom: 1px solid #ccc;">
        <img style="width: 100%;" src="${ctx}/assets/shoesImg/small/Adidas%20Yeezy%20Boost%20350%20Low%20Pirate%20Black%20(2016).jpg">
        <div class="am-margin-bottom-xs"><span class="layout-font-size-24" style="color: #434343;">Adidas 白色运动鞋</span></div>
    </div>
    <div class="am-u-md-12 am-text-center am-margin-top-xs">
        <span class="layout-font-size-24">最后成交价</span><br/>
        <span class="layout-font-size-24" style="color: #000;">￥1360</span><br/>
        <span class="layout-font-size-20" style="color: #3bd278">-320（17%）</span>
    </div>
    <div class="am-u-md-12 am-margin-top-sm am-margin-bottom-lg">
        <div class="am-u-md-6 am-text-center" style="border-right:1px solid #ccc;">
            <span class="layout-font-size-24">最低售出</span><br/>
            <span class="layout-font-size-20" style="color: #434343">￥1210</span>
        </div>
        <div class="am-u-md-6 am-text-center">
            <span class="layout-font-size-24">最高售出</span><br/>
            <span class="layout-font-size-20" style="color: #434343">￥1680</span>
        </div>
    </div>
</div>
<ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-5 am-gallery-default am_index_addimglist am-no-layout">
    <c:forEach items="${bidList}" var="bid">
        <li>
            <a class="popover-tips" href="javascript:;">
                <div class="clickZone" aria-describedby="product141637">
                    <div class="img">
                        <span class="helper"></span>
                        <img class="show-lazy lazy"style="width: 100%;" src="/assets/i/blank.gif" data-echo="${configMap._site_url}${bid.basicinformation.smallImgUrl}" style="display: inline;">
                    </div>
                    <div class="name" style="padding-left: 5px;">
                        <div>${bid.basicinformation.name}</div>
                    </div>
                    <div class="price">
                        <div class="price-line">
                            <div class="price-label" style="padding-left: 5px;">最后出价</div>
                            <div style="font-size: 24px;">￥<fmt:formatNumber value="${bid.bidMoney}" type="currency" pattern="#,#00.0#"/></div>
                        </div>
                    </div>
                </div>
            </a>
        </li>
    </c:forEach>
</ul>
<script src="${ctx}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $(function(){
        $(".popover-tips").popover(
                {
                    trigger:'hover focus',
                    html: true,
                    placement:'auto right',
                    content:$("#tips-model").html(),
                    animation:false,
                    delay:
                    { show: 300, hide: 100 }
                }
        );
    });
    Echo.init({
        offset: 0,
        throttle: 0
    });
</script>