<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="purchaseRecords" action="${cxt}/user/purchase" method="post">
    <table class="am-table am-table-striped am-table-hover am-margin-bottom-xl">
        <caption>
            <p class="am-fl layout-font-size-30" style="color: #ea5958">最近出价</p>
            <span class="am-fr" style="color: #249bd3;line-height: 50px;"><a href="javascript:void(0)" data-url="../user/offerlist?type=1&pageNow=1" class="offer-btn layout-font-size-18">更多记录</a></span>
        </caption>
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
                    <td class="am-text-sm">${bid.bftName}</td>
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
                                <c:choose>
                                    <c:when test="${bid.status == 0 || bid.status == 10 || bid.status == 1}">
                                        <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" select_type="select-btn"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" disabled="true"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                    </c:otherwise>
                                </c:choose>

                                <ul class="am-dropdown-content">
                                    <c:if test="${bid.status == 10}">
                                        <li><a class="am-btn am-btn-xs am-text-left sale-up" data_type="1" href="javascript:void(0)"><div style="float: left; display: block;width: 20px;height: 18px; background: url('/assets/shoesImg/personal_center.png'); background-position: -50px -32px;"></div><span class="am-text-left am-text-sm">修改</span></a></li>
                                        <li><a class="am-btn am-btn-xs am-text-left sale-sub" data_type="1" data_id="${bid.id}" btf-id="${bid.basicinformationId}" href="javascript:void(0)" disabled='true'><i class="am-icon-save am-margin-right-xs"></i><span class="am-text-left am-text-sm">保存</span></a></li>
                                        <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-quit" href="javascript:void(0)" disabled='true'><i class="am-icon-remove am-margin-right-xs"></i><span class="am-text-left am-text-sm">取消</span></a></li>
                                    </c:if>
                                    <c:if test="${bid.status == 0}">
                                        <li><a class="am-btn am-btn-xs am-text-left deliver-bid-goods" bid-id="${bid.id}" bft-id="${bid.basicinformationId}" data-type="${bid.type}" bid_type="1" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去支付</span></a></li>
                                    </c:if>
                                    <c:if test="${bid.status == 1}">
                                        <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-del" del_data_id="${bid.id}" href="javascript:void(0)"><img class="am-margin-right-xs" src="/assets/shoesImg/delete.png"/><span class="am-text-left am-text-sm">删除</span></a></li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </td>
                    <%--<td class="span-img">--%>
                        <%--<a href="#"><span style="display: block;width: 16px;height: 30px; background: url('../../../assets/shoesImg/personal_center.png'); background-position: 6% 43%;"></span></a>--%>
                        <%--<a href="#"><img class="am-fr" style="margin-top: -26%;" src="../../../assets/shoesImg/delete.png"/></a>--%>
                    <%--</td>--%>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="am-table am-table-striped am-table-hover">
        <caption>
            <p class="am-fl layout-font-size-30">购买记录</p>
            <span class="am-fr" style="color: #249bd3;line-height: 50px;"><a href="javascript:void(0)" data-url="../user/buysell?type=1" class="offer-btn layout-font-size-18">更多记录</a></span>
        </caption>
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
                <tr>
                    <td class="am-text-sm">${trade.bftName}</td>
                    <td class="am-text-sm">
                        <c:out value="${fn:substring(trade.transactionDate, 0, 10)}" />
                    </td>
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
                                    <c:when test="${trade.status == 1 || trade.status == 30 || trade.status == 41}">
                                        <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" select_type="select-btn"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="am-btn am-btn-default am-btn-xs trade-save" disabled="true"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                    </c:otherwise>
                                </c:choose>
                                <ul class="am-dropdown-content">
                                    <c:if test="${trade.status == 1}">
                                        <li><a class="am-btn am-btn-xs am-text-left trade-pament" data-id="${trade.id}" trade-size="${trade.bftSize}"  trade-type="3" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去支付</span></a></li>
                                    </c:if>
                                    <c:if test="${trade.status == 30}">
                                        <li><a class="am-btn am-btn-xs am-text-left trade-save" explain="收货" status="40" utype="3" trade-no="${trade.tradeNo}" bidId="${trade.bidId}" data-id="${trade.id}"  trade-type="3" href="javascript:void(0)"><span class="am-text-left am-text-sm">确认收货</span></a></li>
                                    </c:if>
                                    <c:if test="${trade.status == 41}">
                                        <li><a class="am-btn am-btn-xs am-text-left trade-save" explain="删除" status="50" utype="3" trade-no="${trade.tradeNo}" bidId="${trade.bidId}" data-id="${trade.id}"  trade-type="3" href="javascript:void(0)"><span class="am-text-left am-text-sm">删除</span></a></li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>

                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</form>
<%@include file="popup/userBuyAddress.jsp" %>
<a href="javascript:;" id="now-seller-buy-detailed" style="display: none;" data-am-modal="{target: '#my-popup-buy-userBuyAddress', width: 900, height: 520}">购买</a>
<script>
    $(function(){
        $(".doc-dropdown-js").each(function(index){
            $(this).dropdown({justify: '.doc-dropdown-justify-js:eq('+index+')'});
        });
    });
</script>
