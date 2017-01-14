<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="now" class="java.util.Date" />
<%@include file="../layout/inc.jsp" %>
<form id="saleRecord" action="${cxt}/index/sale" method="post">
    <table class="am-table am-table-striped am-table-hover am-margin-bottom-xl">
        <caption>
            <p class="am-fl layout-font-size-26" style="color: #ea5958">最近叫价</p>
            <c:if test="${not empty bidList}">
                <span class="am-fr" style="color: #249bd3;line-height: 50px;"><a href="javascript:void(0)" data-url="../user/offerlist?type=0&pageNow=1" class="offer-btn layout-font-size-18">更多记录</a></span>
                <input type="hidden" data-url="../user/sale?type=0" class="offer-ref-btn"/>
            </c:if>
        </caption>

        <c:choose>
            <c:when test="${not empty bidList}">

                <thead>
                <tr>
                    <td style="width: 20%;">球鞋名称</td>
                    <td>尺码</td>
                    <td>出价日期</td>
                    <td>我的出价</td>
                    <td>最高出价</td>
                    <td>最低售价</td>
                    <td>叫价状态</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${bidList}" var="bid">
                    <tr>
                        <td class="am-text-sm"><a href="/detail?proName=${bid.bftName}" target="_blank"><span style="color: #333;">${bid.bftName}</span></a></td>
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
                                <td class="am-text-sm">--</td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${not empty bid.minimumSellingPrice}">
                                <td class="am-text-sm"><fmt:formatNumber value="${bid.minimumSellingPrice}" type="number" pattern="￥0.00"/></td>
                            </c:when>
                            <c:otherwise>
                                <td class="am-text-sm">--</td>
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
                                    <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" select_type="select-btn"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                    <ul class="am-dropdown-content">
                                        <c:if test="${bid.status == 0}">
                                            <li><a class="am-btn am-btn-xs am-text-left deliver-bid-goods" bid-id="${bid.id}" bft-id="${bid.basicinformationId}" data-type="${bid.type}" bid_type="0" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去支付</span></a></li>
                                        </c:if>
                                        <c:if test="${bid.status == 2}">
                                            <li><a class="am-btn am-btn-xs am-text-left deliver-bid-goods" bid-id="${bid.id}" bft-id="${bid.basicinformationId}" data-type="${bid.type}" bid_type="0" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">重新支付</span></a></li>
                                        </c:if>
                                        <c:if test="${bid.status == 11}">
                                            <li><a class="am-btn am-btn-xs am-text-left sale-up" data_type="0" href="javascript:void(0)"><div style="float: left; display: block;width: 20px;height: 18px; background: url('/assets/shoesImg/personal_center.png'); background-position: -50px -32px;"></div><span class="am-text-left am-text-sm">重新叫价</span></a></li>
                                            <li><a class="am-btn am-btn-xs am-text-left sale-sub" status="${bid.status}" bid-date="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" />" href="javascript:void(0)"><i class="am-icon-save am-margin-right-xs"></i><span class="am-text-left am-text-sm">保存</span></a></li>
                                        </c:if>
                                        <c:if test="${bid.status == 10}">
                                            <li><a class="am-btn am-btn-xs am-text-left sale-up" data_type="0" href="javascript:void(0)"><div style="float: left; display: block;width: 20px;height: 18px; background: url('/assets/shoesImg/personal_center.png'); background-position: -50px -32px;"></div><span class="am-text-left am-text-sm">修改</span></a></li>
                                            <li><a class="am-btn am-btn-xs am-text-left sale-sub" data_type="0" data_id="${bid.id}" btf-id="${bid.basicinformationId}" href="javascript:void(0)" disabled='true'><i class="am-icon-save am-margin-right-xs"></i><span class="am-text-left am-text-sm">保存</span></a></li>
                                            <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-quit" href="javascript:void(0)" disabled='true'><i class="am-icon-remove am-margin-right-xs"></i><span class="am-text-left am-text-sm">取消</span></a></li>
                                        </c:if>
                                        <c:if test="${bid.status == 1 || bid.status == 11 || bid.status == 30}">
                                            <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-del" data-type="${bid.type}" del_data_id="${bid.id}" btf-id="${bid.basicinformationId}"  href="javascript:void(0)"><img class="am-margin-right-xs" src="/assets/shoesImg/delete.png"/><span class="am-text-left am-text-sm">删除</span></a></li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </c:when>
            <c:otherwise>
                <tbody>
                <tr style="width: 100%; height: 300px;">
                    <td style="background-color: #ffffff;">
                        <img class="am-center" src="${cxt}/assets/i/sale_order.png">
                        <a href="/sorts" style="margin-left: 42%;" class="am-btn am-radius am-padding-top-xs am-padding-bottom-xs am-padding-left-lg am-padding-right-lg am-btn-danger am-margin-top-lg">去出售</a>
                    </td>
                </tr>
                </tbody>
            </c:otherwise>
        </c:choose>
    </table>
    <table class="am-table am-table-striped am-table-hover">
    <caption>
        <p class="am-fl layout-font-size-26">出售记录</p>
        <c:if test="${not empty tradeList}">
            <span class="am-fr" style="color: #249bd3;line-height: 50px;"><a href="javascript:void(0)" data-url="../user/buysell?type=0&pageNow=1" class="offer-btn layout-font-size-18">更多记录</a></span>
        </c:if>
    </caption>
    <c:choose>
        <c:when test="${not empty tradeList}">
            <thead>
            <tr>
                <td style="width: 30%;">球鞋名称</td>
                <td>出售日期</td>
                <td>保证金</td>
                <td>尺码</td>
                <td>金额</td>
                <td>状态</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tradeList}" var="trade">
                <td class="am-text-sm"><a href="/detail?proName=${trade.bftName}" target="_blank"><span style="color: #333;">${trade.bftName}</span></a></td>
                <td class="am-text-sm">
                    <c:out value="${fn:substring(trade.transactionDate, 0, 10)}" />
                </td>
                <c:choose>
                    <c:when test="${not empty trade.bid.bidBond}">
                        <td class="am-text-sm"><fmt:formatNumber value="${trade.bid.bidBond}" type="number" pattern="￥0.00"/></td>
                    </c:when>
                    <c:otherwise>
                        <td class="am-text-sm">--</td>
                    </c:otherwise>
                </c:choose>
                <td class="am-text-sm">${trade.bftSize}</td>
                <td class="am-text-sm"><fmt:formatNumber value="${trade.transactionMoney}" type="number" pattern="￥0.00"/></td>
                <c:forEach items="${statusList}" var="status">
                    <c:if test="${status.id == trade.status}">
                        <td class="am-text-sm">${status.bftName}</td>
                    </c:if>
                </c:forEach>
                <td>
                    <div class="doc-dropdown-justify-js">
                        <div class="am-dropdown doc-dropdown-js">
                            <c:choose>
                                <c:when test="${trade.status == 0 || trade.status == 2}">
                                    <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" select_type="select-btn"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                </c:when>
                                <c:otherwise>
                                    <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" disabled="true"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                </c:otherwise>
                            </c:choose>
                            <ul class="am-dropdown-content">
                                <c:choose>
                                    <c:when test="${trade.status == 0}">
                                        <li><a class="am-btn am-btn-xs am-text-left trade-pament" data-id="${trade.id}"  trade-type="2" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去支付</span></a></li>
                                    </c:when>
                                    <c:when test="${trade.status == 2}">
                                        <li><a class="am-btn am-btn-xs am-text-left trade-save" explain="发货" status="10" utype="2" trade-no="${trade.tradeNo}" bidId="${trade.bidId}" data-id="${trade.id}"  trade-type="2" href="javascript:void(0)" data-am-modal="{target: '#deliverDoods-pop', width: 600}"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去发货</span></a></li>
                                    </c:when>
                                </c:choose>
                            </ul>
                        </div>
                    </div>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </c:when>
        <c:otherwise>
            <tbody>
            <tr style="width: 100%; height: 300px;">
                <td style="background-color: #ffffff;">
                    <img class="am-center" src="${cxt}/assets/i/sale_price.png">
                    <a href="/sorts" style="margin-left: 43%;" class="am-btn am-radius am-padding-top-xs am-padding-bottom-xs am-padding-left-lg am-padding-right-lg am-btn-danger am-margin-top-lg">去出售</a>
                </td>
            </tr>
            </tbody>
        </c:otherwise>
    </c:choose>
</table>
</form>
<%@include file="../user/popup/deliverDoods.jsp"%>
<script>
    $(function(){
        $(".doc-dropdown-js").each(function(index){
            $(this).dropdown({justify: '.doc-dropdown-justify-js:eq('+index+')'});
        });
    });
</script>
