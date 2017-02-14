<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<input id="sorts-size" type="hidden" value="${size}" />
<input id="sorts-price" type="hidden" value="${price}" />
<input id="sorts-year" type="hidden" value="${year}" />
<input id="sorts-brand" type="hidden" value="${brand}" />
<input class="sorts-pageStart" type="hidden" value="${pageStart}" />
<c:choose>
    <c:when test="${not empty bidList}">
        <c:forEach items="${bidList}" var="bid">
            <li>
                <a class="popover-tips-${pageStart}" href="/detail?proName=${bid.name}&size=${size}" data-name="${bid.name}" data-id="${bid.id}" data-img-url="${configMap._site_url}${bid.smallImgUrl}">
                    <div class="clickZone" aria-describedby="product141637">
                        <div class="img">
                            <span class="helper"></span>
                            <img class="show-lazy lazy" style="width: 100%;" src="/assets/i/blank.gif" data-echo="${configMap._site_url}${bid.smallImgUrl}" onerror="this.src='/assets/i/default.png;this.onerror=null'" style="display: inline;">
                        </div>
                        <div class="name" style="padding-left: 5px;height: 70px;">
                            <div>${bid.name}</div>
                        </div>
                        <div class="price">
                            <div class="price-line">
                                <div class="price-label" style="padding-left: 5px;">最后出价</div>
                                <div style="font-size: 24px;">￥
                                    <c:choose>
                                        <c:when test="${not empty bid.bid.bidMoney}">
                                            <fmt:formatNumber value="${bid.bid.bidMoney}" type="currency" pattern="#,#00.0#"/>
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
            </li>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <div class="bg-error">
            <div class="not-found-list"></div>
        </div>
    </c:otherwise>
</c:choose>


<script type="text/javascript">
    $(function(){
        var nowPage = $(".sorts-pageStart:last").val();
        $("#pageStart").val(parseInt(nowPage)+20);
        $(".popover-tips-"+nowPage).each(function(){
            var $this = $(this);
            sendRequest("/sorts/bidTips",{
                "bid":$this.attr("data-id"),
                "size":$("#sorts-size").val()
            },function(res){
                var transactionMoney = (res.data.trade== undefined?undefined:res.data.trade.transactionMoney)==undefined ? "-" : res.data.trade.transactionMoney;
                var minimumSellingPrice = (res.data.pricePeak2== undefined?undefined:res.data.pricePeak2.minimumSellingPrice)==undefined ? "-" : res.data.pricePeak2.minimumSellingPrice;
                var highest_bid = "";
                var difference = "";
                var percentag = "";
                var roseType = "";
                var price_color = "";
                if(res.data.pricePeak1==undefined){ $("#highest_bid").text("-"); }else{
                    highest_bid = res.data.pricePeak1.highestBid==0?"-":res.data.pricePeak1.highestBid;
                }
                difference = res.data.difference==0?"-":res.data.difference;
                percentag = res.data.percentag==0?"-":res.data.percentag;

                if(res.data.roseType == 0){
                    roseType = "-";
                    price_color = "#3bd278";
                }else{
                    roseType = "+";
                    price_color = "#FF6368";
                }
                var html = '<div id="tips-model" style="height: 453px;display: block;">'
                        +'<div class="am-u-md-12 am-text-center am-padding-left-lg am-padding-right-lg" style="border-bottom: 1px solid #ccc;">'
                        +'<img id="show-img" style="width: 100%;" src="'+$this.attr("data-img-url")+'">'
                        +'<div class="am-margin-bottom-xs"><span class="layout-font-size-24" style="color: #434343;" id="product-name">'+$this.attr("data-name")+'</span></div>'
                        +'</div>'
                        +'<div class="am-u-md-12 am-text-center am-margin-top-xs">'
                        +'<span class="layout-font-size-24">最后成交价</span><br/>'
                        +'<span class="layout-font-size-24" style="color: #000;">￥<span id="trade-final-money" class="layout-font-size-24" style="color: #000;">'+transactionMoney+'</span></span><br/>'
                        +'<span id="price-color" style="color: '+price_color+'">'
                        +'<span class="layout-font-size-20 roseType" >'+roseType+'</span>'
                        +'<span id="difference" class="layout-font-size-20">'+difference+'</span>'
                        +'<span class="layout-font-size-20">（ </span>'
                    +'<span class="layout-font-size-20 roseType">'+roseType+'</span>'
                        +'<span id="percentag" class="layout-font-size-20">'+percentag+'</span>'
                        +'<span class="layout-font-size-20"> %）</span>'
                        +'</span>'
                        +'</div>'
                        +'<div class="am-u-md-12 am-margin-top-sm am-margin-bottom-lg">'
                        +'<div class="am-u-md-6 am-text-center" style="border-right:1px solid #ccc;">'
                        +'<span class="layout-font-size-24">最低售价</span><br/>'
                        +'<span class="layout-font-size-20" style="color: #434343">￥</span>'
                        +'<span id="minimum_selling_price" class="layout-font-size-20" style="color: #434343">'+minimumSellingPrice+'</span>'
                        +'</div>'
                        +'<div class="am-u-md-6 am-text-center">'
                        +'<span class="layout-font-size-24">最高出价</span><br/>'
                        +'<span class="layout-font-size-20" style="color: #434343">￥</span>'
                        +'<span id="highest_bid" class="layout-font-size-20" style="color: #434343">'+highest_bid+'</span>'
                        +'</div>'
                        +'</div>'
                        +'</div>';
                $this.popover(
                        {
                            trigger:'hover focus',
                            html: true,
                            placement:'auto right',
                            content:html,
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