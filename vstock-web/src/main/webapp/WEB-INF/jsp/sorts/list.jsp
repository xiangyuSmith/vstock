<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<input id="sorts-size" type="hidden" value="${size}" />
<input id="sorts-price" type="hidden" value="${price}" />
<input id="sorts-year" type="hidden" value="${year}" />
<input id="sorts-brand" type="hidden" value="${brand}" />
<div id="tips-model" style="width: 350px;height: 453px;display: none;">
    <div class="am-u-md-12 am-text-center am-padding-left-lg am-padding-right-lg" style="border-bottom: 1px solid #ccc;">
        <img id="show-img" style="width: 100%;" src="">
        <div class="am-margin-bottom-xs"><span class="layout-font-size-24" style="color: #434343;" id="product-name"></span></div>
    </div>
    <div class="am-u-md-12 am-text-center am-margin-top-xs">
        <span class="layout-font-size-24">最后成交价</span><br/>
        <span class="layout-font-size-24" style="color: #000;">￥<span id="trade-final-money" class="layout-font-size-24" style="color: #000;"></span></span><br/>
        <span id="price-color" style="color: #3bd278">
            <span class="layout-font-size-20 roseType" ></span>
            <span id="difference" class="layout-font-size-20">320</span>
            <span class="layout-font-size-20">（
            <span class="layout-font-size-20 roseType"></span>
            <span id="percentag" class="layout-font-size-20">17</span>
            <span class="layout-font-size-20"> %）</span>
        </span>
    </div>
    <div class="am-u-md-12 am-margin-top-sm am-margin-bottom-lg">
        <div class="am-u-md-6 am-text-center" style="border-right:1px solid #ccc;">
            <span class="layout-font-size-24">最低售价</span><br/>
            <span class="layout-font-size-20" style="color: #434343">￥</span>
            <span id="minimum_selling_price" class="layout-font-size-20" style="color: #434343"></span>
        </div>
        <div class="am-u-md-6 am-text-center">
            <span class="layout-font-size-24">最高出价</span><br/>
            <span class="layout-font-size-20" style="color: #434343">￥</span>
            <span id="highest_bid" class="layout-font-size-20" style="color: #434343"></span>
        </div>
    </div>
</div>
<ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-5 am-gallery-default am_index_addimglist am-no-layout">
    <c:forEach items="${bidList}" var="bid">
        <li>
            <a class="popover-tips" href="/detail?proName=${bid.basicinformation.name}&size=${size}" data-name="${bid.basicinformation.name}" data-id="${bid.basicinformation.id}" data-img-url="${configMap._site_url}${bid.basicinformation.smallImgUrl}">
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
        $(".popover-tips").each(function(){
            var $this = $(this);
            sendRequest("/sorts/bidTips",{
                "bid":$this.attr("data-id"),
                "size":$("#sorts-size").val()
            },function(res){
                var transactionMoney = (res.data.trade== undefined?undefined:res.data.trade.transactionMoney)==undefined ? "-" : res.data.trade.transactionMoney;
                $("#show-img").attr("src",$this.attr("data-img-url"));
                $("#product-name").text($this.attr("data-name"));
                $("#trade-final-money").text(transactionMoney);
                $("#minimum_selling_price").text(res.data.pricePeak.minimumSellingPrice);
                $("#highest_bid").text(res.data.pricePeak.highestBid==0?"-":res.data.pricePeak.highestBid);
                $("#difference").text(res.data.difference==0?"-":res.data.difference);
                $("#percentag").text(res.data.percentag==0?"-":res.data.percentag);
                if(res.data.roseType == 0){
                    $(".roseType").text("-");
                    $("#price-color").css("color","#3bd278");
                }else{
                    $(".roseType").text("+");
                    $("#price-color").css("color","#FF6368");
                }
                $this.popover(
                        {
                            trigger:'hover focus',
                            html: true,
                            placement:'auto right',
                            content:$("#tips-model").html(),
                            animation:true,
                            delay:
                            { show: 300, hide: 100 }
                        }
                );
            });
        });
    });
    Echo.init({
        offset: 0,
        throttle: 0
    });
</script>