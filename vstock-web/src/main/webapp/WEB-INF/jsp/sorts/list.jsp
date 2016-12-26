<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<input id="sorts-size" type="hidden" value="${size}" />
<input id="sorts-price" type="hidden" value="${price}" />
<input id="sorts-year" type="hidden" value="${year}" />
<input id="sorts-brand" type="hidden" value="${brand}" />
<input class="sorts-pageStart" type="hidden" value="${pageStart}" />
<c:forEach items="${bidList}" var="bid">
    <li>
        <a class="popover-tips-${pageStart}" href="/detail?proName=${bid.name}&size=${size}" data-name="${bid.name}" data-id="${bid.id}" data-img-url="${configMap._site_url}${bid.smallImgUrl}">
            <div class="clickZone" aria-describedby="product141637">
                <div class="img">
                    <span class="helper"></span>
                    <img class="show-lazy lazy" style="width: 100%;" src="/assets/i/blank.gif" data-echo="${configMap._site_url}${bid.smallImgUrl}" style="display: inline;">
                </div>
                <div class="name" style="padding-left: 5px;">
                    <div>${bid.name}</div>
                </div>
                <div class="price">
                    <div class="price-line">
                        <div class="price-label" style="padding-left: 5px;">最后出价</div>
                        <div style="font-size: 24px;">￥<fmt:formatNumber value="${bid.bid.bidMoney}" type="currency" pattern="#,#00.0#"/></div>
                    </div>
                </div>
            </div>
        </a>
    </li>
</c:forEach>
<script src="${ctx}/assets/js/bootstrap.min.js"></script>
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
                $("#show-img").attr("src",$this.attr("data-img-url"));
                $("#product-name").text($this.attr("data-name"));
                $("#trade-final-money").text(transactionMoney);
                $("#minimum_selling_price").text(minimumSellingPrice);
                if(res.data.pricePeak1==undefined){ $("#highest_bid").text("-"); }else{
                    $("#highest_bid").text(res.data.pricePeak1.highestBid==0?"-":res.data.pricePeak1.highestBid);
                }
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