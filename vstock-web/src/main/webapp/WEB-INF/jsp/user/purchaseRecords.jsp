<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="purchaseRecords" action="/user/purchase" method="post">
    <table class="am-table am-table-striped am-table-hover am-margin-bottom-xl">
        <caption style="border-bottom: 1px solid #CACACA;">
            <div style="float: left; display: block;width: 37px;height: 45px; background: url('/assets/i/personal_center_map.png'); background-position: -703px -18px;"></div>
            <span class="am-fl layout-font-size-26">最近出价</span>
            <c:if test="${not empty bidList}">
                <span class="am-fr" style="color: #249bd3;line-height: 38px;"><a href="javascript:void(0)" data-url="../user/offerlist?type=1&pageNow=1" class="offer-btn layout-font-size-18">更多记录</a><div style="float: right; display: block;width: 25px;height: 45px; background: url('/assets/i/personal_center_map.png'); background-position: -940px -18px;"></div></span>
                <input type="hidden" data-url="../user/sale?type=0" class="offer-ref-btn"/>
            </c:if>
        </caption>
        <c:choose>
            <c:when test="${not empty bidList}">
                <thead>
                <tr>
                    <td style="width: 20%;">名称</td>
                    <td>尺码</td>
                    <td>出价日期</td>
                    <td>我的出价</td>
                    <td>最高出价</td>
                    <td>最低售价</td>
                    <td>叫价状态</td>
                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty bidList}">
                    <c:forEach items="${bidList}" var="bid">
                        <tr>
                            <td class="am-text-sm"><a href="/detail?proName=${bid.bftName}" target="_blank"><span class="popover-tips" data-bftId="${bid.basicinformationId}" data-bftSize="${bid.bftSize}" data-img-url="${configMap._site_url}" style="color: #333;">${bid.bftName}</span></a></td>
                            <td class="am-text-sm">${bid.bftSize}</td>
                            <td class="am-text-sm">
                                <c:out value="${fn:substring(bid.bidDate, 0, 10)}" />
                            </td>
                            <td class="am-text-sm"><fmt:formatNumber value="${bid.bidMoney}" type="number" pattern="￥0.00"/></td>
                            <c:choose>
                                <c:when test="${not empty bid.highestBid}">
                                    <td class="am-text-sm"><fmt:formatNumber value="${bid.highestBid}" type="number" pattern="￥0.00"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td>--</td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${not empty bid.highestBid}">
                                    <td class="am-text-sm"><fmt:formatNumber value="${bid.minimumSellingPrice}" type="number" pattern="￥0.00"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td>--</td>
                                </c:otherwise>
                            </c:choose>
                            <td>
                                <c:if test="${not empty bidStatus}">
                                    <c:forEach items="${bidStatus}" var="bidStatus">
                                        <c:if test="${bid.status == bidStatus.status}">${bidStatus.bftName}</c:if>
                                    </c:forEach>
                                </c:if>
                            </td>
                            <td>
                                <div class="doc-dropdown-justify-js">
                                    <div class="am-dropdown doc-dropdown-js">
                                        <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle status-user-set" select_type="select-btn"><span class="am-icon-caret-down"></span></a>
                                        <ul class="am-dropdown-content">
                                            <c:if test="${bid.status == 10}">
                                                <li><a class="am-btn am-btn-xs am-text-left sale-up" data_type="1" href="javascript:void(0)"><div style="float: left; display: block;width: 20px;height: 18px; background: url('/assets/i/personal_center.png'); background-position: -50px -32px;"></div><span class="am-text-left am-text-sm">修改</span></a></li>
                                                <li><a class="am-btn am-btn-xs am-text-left sale-sub" data_type="1" data_id="${bid.id}" btf-id="${bid.basicinformationId}" href="javascript:void(0)" disabled='true'><i class="am-icon-save am-margin-right-xs"></i><span class="am-text-left am-text-sm">保存</span></a></li>
                                                <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-quit" href="javascript:void(0)" disabled='true'><i class="am-icon-remove am-margin-right-xs"></i><span class="am-text-left am-text-sm">取消</span></a></li>
                                            </c:if>
                                            <c:if test="${bid.status == 0}">
                                                <li><a class="am-btn am-btn-xs am-text-left deliver-bid-goods" bid-id="${bid.id}" bft-id="${bid.basicinformationId}" data-type="${bid.type}" bid_type="1" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去支付</span></a></li>
                                            </c:if>
                                            <c:if test="${bid.status == 2}">
                                                <li><a class="am-btn am-btn-xs am-text-left deliver-bid-goods" bid-id="${bid.id}" bft-id="${bid.basicinformationId}" data-type="${bid.type}" bid_type="0" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">重新支付</span></a></li>
                                            </c:if>
                                            <c:if test="${bid.status == 11 || bid.status == 50 || bid.status == 51}">
                                                <li><a class="am-btn am-btn-xs am-text-left" data_type="0" href="${cxt}/detail?proName=${bid.bftName}&size=${bid.bftSize}"><div style="float: left; display: block;width: 20px;height: 18px; background: url('/assets/i/personal_center.png'); background-position: -50px -32px;"></div><span class="am-text-left am-text-sm">重新叫价</span></a></li>
                                            </c:if>
                                            <c:if test="${bid.status == 1 || bid.status == 11 || bid.status == 30 || bid.status == 50 || bid.status == 51}">
                                                <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-del" data-type="${bid.type}" del_data_id="${bid.id}" btf-id="${bid.basicinformationId}"  href="javascript:void(0)"><i class="am-icon-trash am-margin-right-xs"></i><span class="am-text-left am-text-sm">删除</span></a></li>
                                            </c:if>
                                        </ul>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </c:when>
            <c:otherwise>
                <tbody>
                <tr style="width: 100%; height: 300px;">
                    <td style="background-color: #ffffff;">
                        <img class="am-center" src="/assets/i/purchase_order.png">
                        <a href="/sorts" style="margin-left: 41%;" class="am-btn am-radius am-padding-top-xs am-padding-bottom-xs am-padding-left-lg am-padding-right-lg am-btn-danger am-margin-top-lg">去购买</a>
                    </td>
                </tr>
                </tbody>
            </c:otherwise>
        </c:choose>
    </table>

    <table class="am-table am-table-striped am-table-hover">
        <caption style="border-bottom: 1px solid #CACACA;">
            <div style="float: left; display: block;width: 37px;height: 45px; background: url('/assets/i/personal_center_map.png'); background-position: -983px -18px;"></div>
            <span class="am-fl layout-font-size-26">购买记录</span>
            <c:if test="${not empty tradeList}">
                <span class="am-fr" style="color: #249bd3;line-height: 38px;"><a href="javascript:void(0)" data-url="../user/buysell?type=1&pageNow=1" class="offer-btn layout-font-size-18">更多记录</a><div style="float: right; display: block;width: 25px;height: 45px; background: url('/assets/i/personal_center_map.png'); background-position: -940px -18px;"></div></span>
            </c:if>
        </caption>
        <c:choose>
            <c:when test="${not empty tradeList}">
                <thead>
                <tr>
                    <td style="width: 30%;">名称</td>
                    <td>购买日期</td>
                    <td>尺码</td>
                    <td>金额</td>
                    <td>状态</td>
                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty tradeList}">
                    <c:forEach items="${tradeList}" var="trade">
                        <%--<c:if test="${trade.status != 51}">--%>
                        <tr>
                            <td class="am-text-sm"><a href="/detail?proName=${trade.bftName}" target="_blank"><span class="popover-tips" data-bftId="${trade.basicinformationId}" data-bftSize="${trade.bftSize}" data-img-url="${configMap._site_url}" style="color: #333;">${trade.bftName}</span></a></td>
                            <td class="am-text-sm">
                                <c:out value="${fn:substring(trade.transactionDate, 0, 10)}" />
                            </td>
                            <td class="am-text-sm">${trade.bftSize}</td>
                            <td class="am-text-sm"><fmt:formatNumber value="${trade.transactionMoney}" type="number" pattern="￥0.00"/></td>
                            <td class="am-text-sm">
                                <c:forEach items="${statusList}" var="status">
                                    <c:if test="${status.id == trade.status}">
                                        <c:choose>
                                            <c:when test="${trade.status == 60}">
                                                退款中
                                            </c:when>
                                            <c:when test="${trade.status == 61}">
                                                退款成功
                                            </c:when>
                                            <c:otherwise>
                                                ${status.bftName}
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <div class="doc-dropdown-justify-js">
                                    <div class="am-dropdown doc-dropdown-js">
                                        <c:choose>
                                            <c:when test="${trade.status == 1 || trade.status == 30 || trade.status == 41 || trade.status == 52}">
                                                <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle status-user-set" select_type="select-btn"><span class="am-icon-caret-down"></span></a>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="am-btn am-btn-default am-btn-xs trade-save status-user-set" disabled="true"><span class="am-icon-caret-down"></span></a>
                                            </c:otherwise>
                                        </c:choose>
                                        <ul class="am-dropdown-content">
                                            <c:if test="${trade.status == 1}">
                                                <li><a class="am-btn am-btn-xs am-text-left trade-pament" data-bftName="${trade.bftName}" data-id="${trade.id}" trade-size="${trade.bftSize}" add-id="${trade.userAddressId}"  trade-type="3" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去支付</span></a></li>
                                            </c:if>
                                            <c:if test="${trade.status == 30}">
                                                <li><a class="am-btn am-btn-xs am-text-left tradeSave" explain="收货" status="40" utype="3" trade-no="${trade.tradeNo}" bidId="${trade.bidId}" data-id="${trade.id}"  trade-type="3" href="javascript:void(0)"><span class="am-text-left am-text-sm">确认收货</span></a></li>
                                                <li><a class="am-btn am-btn-xs am-text-left express-get" data-id="${trade.id}" href="javascript:void(0)" data-am-modal="{target: '#express-pop', width: 900}"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">查看物流</span></a></li>
                                            </c:if>
                                            <c:if test="${trade.status == 41 || trade.status == 52}">
                                                <li>
                                                    <input type="hidden"class="tradeSave" explain="删除" status="51" utype="3" trade-no="${trade.tradeNo}" bidId="${trade.bidId}" data-id="${trade.id}"  trade-type="3"/>
                                                    <a class="am-btn am-btn-xs am-text-left" id="trade_del" href="javascript:void(0)"><i class="am-icon-trash am-margin-right-xs"></i><span class="am-text-left am-text-sm">删除</span></a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </div>
                                </div>

                            </td>
                        </tr>
                        <%--</c:if>--%>
                    </c:forEach>
                </c:if>
                </tbody>
            </c:when>
            <c:otherwise>
                <tbody>
                <tr style="width: 100%; height: 300px;">
                    <td style="background-color: #ffffff;">
                        <img class="am-center" src="/assets/i/purchase_price.png">
                        <a href="/sorts" style="margin-left: 41%;" class="am-btn am-radius am-padding-top-xs am-padding-bottom-xs am-padding-left-lg am-padding-right-lg am-btn-danger am-margin-top-lg">去购买</a>
                    </td>
                </tr>
                </tbody>
            </c:otherwise>
        </c:choose>
    </table>
</form>

<div class="am-modal am-modal-confirm" tabindex="-1" id="trade_del_confirm">
    <div class="am-modal-dialog">
        <div class="am-modal-hd"></div>
        <div class="am-modal-bd">
            删除后将不再显示出价记录，确定要删除吗？
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>
<%@include file="popup/userBuyAddress.jsp" %>
<%@include file="popup/expressList.jsp"%>
<a href="javascript:;" id="now-seller-buy-detailed" style="display: none;" data-am-modal="{target: '#my-popup-buy-userBuyAddress', width: 900, height: 520}">购买</a>
<script>
    $(function(){
        $(".doc-dropdown-js").each(function(index){
            $(this).dropdown({justify: '.doc-dropdown-justify-js:eq('+index+')'});
        });

        $('#trade_del').click(function(){
            $('#trade_del_confirm').modal({
                onConfirm: function() {
                    $('.tradeSave').click();
                },
                // closeOnConfirm: false,
                onCancel: function() {
                }
            });
        });

        $(".popover-tips").each(function(){
            var $this = $(this);
            sendRequest("/sorts/bidTips",{
                "bid":$this.attr("data-bftId"),
                "size":$this.attr("data-bftSize")
            },function(res){
                var basicinfortionImg = res.data.basicinformation.smallImgUrl;
                var bft_name = res.data.basicinformation.name;
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
                        +'<img id="show-img" style="width: 100%;" src="'+$this.attr("data-img-url")+basicinfortionImg+'">'
                        +'<div class="am-margin-bottom-xs"><span class="layout-font-size-24" style="color: #434343;" id="product-name">'+bft_name+'</span></div>'
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
</script>
