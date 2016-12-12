<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<style>
    .popover{position:absolute;top:0;left:0;z-index:1060;display:none;max-width:326px;padding:1px;font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;font-size:14px;font-style:normal;font-weight:400;line-height:1.42857143;text-align:left;text-align:start;text-decoration:none;text-shadow:none;text-transform:none;letter-spacing:normal;word-break:normal;word-spacing:normal;word-wrap:normal;white-space:normal;background-color:#fff;-webkit-background-clip:padding-box;background-clip:padding-box;border:1px solid #ccc;border:1px solid rgba(0,0,0,.2);border-radius:6px;-webkit-box-shadow:0 5px 10px rgba(0,0,0,.2);box-shadow:0 5px 10px rgba(0,0,0,.2);line-break:auto}.popover.top{margin-top:-10px}.popover.right{margin-left:10px}.popover.bottom{margin-top:10px}.popover.left{margin-left:-10px}.popover-title{padding:8px 14px;margin:0;font-size:14px;background-color:#f7f7f7;border-bottom:1px solid #ebebeb;border-radius:5px 5px 0 0}.popover-content{padding:9px 14px}.popover>.arrow,.popover>.arrow:after{position:absolute;display:block;width:0;height:0;border-color:transparent;border-style:solid}.popover>.arrow{border-width:11px}.popover>.arrow:after{content:"";border-width:10px}.popover.top>.arrow{bottom:-11px;left:50%;margin-left:-11px;border-top-color:#999;border-top-color:rgba(0,0,0,.25);border-bottom-width:0}.popover.top>.arrow:after{bottom:1px;margin-left:-10px;content:" ";border-top-color:#fff;border-bottom-width:0}.popover.right>.arrow{top:50%;left:-11px;margin-top:-11px;border-right-color:#999;border-right-color:rgba(0,0,0,.25);border-left-width:0}.popover.right>.arrow:after{bottom:-10px;left:1px;content:" ";border-right-color:#fff;border-left-width:0}.popover.bottom>.arrow{top:-11px;left:50%;margin-left:-11px;border-top-width:0;border-bottom-color:#999;border-bottom-color:rgba(0,0,0,.25)}.popover.bottom>.arrow:after{top:1px;margin-left:-10px;content:" ";border-top-width:0;border-bottom-color:#fff}.popover.left>.arrow{top:50%;right:-11px;margin-top:-11px;border-right-width:0;border-left-color:#999;border-left-color:rgba(0,0,0,.25)}.popover.left>.arrow:after{right:1px;bottom:-10px;content:" ";border-right-width:0;border-left-color:#fff}
</style>
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