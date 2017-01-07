<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="saleRecord" action="${cxt}/index/sale" method="post">
    <table class="am-table am-table-striped am-table-hover am-margin-bottom-xl">
        <caption>
            <p class="am-fl layout-font-size-30" style="color: #ea5958">最近叫价</p>
            <span class="am-fr" style="color: #249bd3;line-height: 50px;"><a href="javascript:void(0)" data-url="../user/offerlist?type=0&pageNow=1" class="offer-btn layout-font-size-18">更多记录</a></span>
        </caption>
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
                        <div class="am-dropdown" data-am-dropdown>
                            <c:choose>
                                <c:when test="${bid.status == 0 || bid.status == 10 || bid.status == 1}">
                                    <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" select_type="select-btn"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                </c:when>
                                <c:otherwise>
                                    <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" disabled="true"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                </c:otherwise>
                            </c:choose>

                            <ul class="am-dropdown-content">
                                <c:if test="${bid.status == 0}">
                                    <li><a class="am-btn am-btn-xs am-text-left deliver-goods" bid-id="${bid.id}" bft-id="${bid.basicinformationId}" data-type="${bid.type}" bid_type="0" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去支付</span></a></li>
                                </c:if>
                                <c:if test="${bid.status == 10}">
                                    <li><a class="am-btn am-btn-xs am-text-left sale-up" href="javascript:void(0)"><div style="float: left; display: block;width: 20px;height: 18px; background: url('/assets/shoesImg/personal_center.png'); background-position: -50px -32px;"></div><span class="am-text-left am-text-sm">修改</span></a></li>
                                    <li><a class="am-btn am-btn-xs am-text-left sale-sub" data_type="0" data_id="${bid.id}" btf-id="${bid.basicinformationId}" href="javascript:void(0)" disabled='true'><i class="am-icon-save am-margin-right-xs"></i><span class="am-text-left am-text-sm">保存</span></a></li>
                                    <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-quit" href="javascript:void(0)" disabled='true'><i class="am-icon-remove am-margin-right-xs"></i><span class="am-text-left am-text-sm">取消</span></a></li>
                                </c:if>
                                <c:if test="${bid.status == 1}">
                                    <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-del" del_data_id="${bid.id}" href="javascript:void(0)"><img class="am-margin-right-xs" src="/assets/shoesImg/delete.png"/><span class="am-text-left am-text-sm">删除</span></a></li>
                                </c:if>
                            </ul>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="am-table am-table-striped am-table-hover">
    <caption>
        <p class="am-fl layout-font-size-30">出售历史</p>
        <span class="am-fr" style="color: #249bd3;line-height: 50px;"><a href="javascript:void(0)" data-url="../user/buysell?type=0&pageNow=1" class="offer-btn layout-font-size-18">更多记录</a></span>
    </caption>
    <thead>
    <tr>
        <td style="width: 30%;">球鞋名称</td>
        <td>尺码</td>
        <td>出售日期</td>
        <td>金额</td>
        <td>保证金</td>
        <td>状态</td>
    </tr>
    </thead>
    <tbody>
    <c:if test="${not empty tradeList}">
        <c:forEach items="${tradeList}" var="trade">
            <td class="am-text-sm">${trade.bftName}</td>
            <td class="am-text-sm">${trade.bftSize}</td>
            <td class="am-text-sm">
                <c:out value="${fn:substring(trade.transactionDate, 0, 10)}" />
            </td>
            <td class="am-text-sm"><fmt:formatNumber value="${trade.transactionMoney}" type="number" pattern="￥0.00"/></td>
            <c:choose>
                <c:when test="${not empty trade.bid.bidBond}">
                    <td class="am-text-sm"><fmt:formatNumber value="${trade.bid.bidBond}" type="number" pattern="￥0.00"/></td>
                </c:when>
                <c:otherwise>
                    <td class="am-text-sm">--</td>
                </c:otherwise>
            </c:choose>
            <c:forEach items="${statusList}" var="status">
                <c:if test="${status.id == trade.status}">
                    <td class="am-text-sm">${status.bftName}</td>
                </c:if>
            </c:forEach>
            <td>
                <div class="am-dropdown" data-am-dropdown>
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
                                <li><a class="am-btn am-btn-xs am-text-left deliver-goods" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去发货</span></a></li>
                            </c:when>
                        </c:choose>
                    </ul>
                </div>
            </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
</form>
<script src="${ctx}/assets/js/amazeui.min.js"></script>