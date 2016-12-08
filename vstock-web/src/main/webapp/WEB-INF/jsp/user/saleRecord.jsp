<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="saleRecord" action="${cxt}/index/testSale" method="post">
    <table class="am-table am-table-striped am-table-hover am-margin-bottom-xl">
        <caption><p class="am-fl am-text-xl am-text-danger am-margin-bottom-0">最近叫价</p></caption>
        <thead>
        <tr>
            <th>球鞋名称</th>
            <th>尺码</th>
            <th>叫价日期</th>
            <th>叫价金额</th>
            <th>最近叫价</th>
            <th>叫价状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty bidList}">
            <c:forEach items="${bidList}" var="bid">
                <tr>
                    <td>${bid.bftName}</td>
                    <td>${bid.bftSize}</td>
                    <td>${bid.bidDate}</td>
                    <td><fmt:formatNumber value="${bid.bidMoney}" type="currency" pattern="#,#00.0#"/></td>
                    <c:choose>
                        <c:when test="${not empty bid.latelyBid}">
                            <td class="am-text-sm"><fmt:formatNumber value="${bid.latelyBid}" type="currency" pattern="#,#00.0#"/></td>
                        </c:when>
                        <c:otherwise>
                            <td>--</td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${bid.status == 0}">
                            <td>已生效</td>
                        </c:when>
                        <c:when test="${bid.status == 10}">
                            <td>待付款</td>
                        </c:when>
                        <c:when test="${bid.status == 11}">
                            <td>开始交易</td>
                        </c:when>
                        <c:when test="${bid.status == 20}">
                            <td>交易成功</td>
                        </c:when>
                        <c:otherwise>
                            <td>已过期</td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <div class="am-dropdown" data-am-dropdown>
                            <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><i class="am-icon-gear"></i><span class="am-margin-left-xs am-icon-caret-down"></span></a>
                            <ul class="am-dropdown-content">
                                <li><a class="am-btn am-btn-xs am-text-left" href="#"><i class="am-icon-eye am-margin-right-xs"></i>重新叫价</a></li>
                                <li><a class="am-btn am-btn-xs am-text-left" href="#"><i class="am-icon-eye am-margin-right-xs"></i>作废</a></li>
                            </ul>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="am-table am-table-striped am-table-hover">
        <caption><p class="am-fl am-text-xl am-margin-bottom-0">出售历史</p></caption>
        <thead>
        <tr>
            <th>球鞋名称</th>
            <th>尺码</th>
            <th>购买日期</th>
            <th>金额</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty tradeList}">
            <c:forEach items="${tradeList}" var="trade">
                <tr>
                    <td>${trade.bftName}</td>
                    <td>${trade.bftSize}</td>
                    <td>${trade.transactionDate}</td>
                    <td><fmt:formatNumber value="${trade.transactionMoney}" type="currency" pattern="#,#00.0#"/></td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</form>
<script src="${ctx}/assets/js/amazeui.min.js"></script>