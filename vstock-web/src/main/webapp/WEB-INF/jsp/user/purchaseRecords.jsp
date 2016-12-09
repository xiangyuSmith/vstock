<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="purchaseRecords" action="${cxt}/index/testPurchase" method="post">
    <table class="am-table am-table-striped am-table-hover am-margin-bottom-xl">
        <caption>
            <p class="am-fl layout-font-size-30" style="color: #ea5958">最近出价</p>
            <span class="am-fr" style="color: #249bd3;line-height: 50px;"><a href="#" class="layout-font-size-18">更多记录</a></span>
        </caption>
        <thead>
        <tr>
            <th style="width: 20%;">名称</th>
            <th>尺码</th>
            <th>出价日期</th>
            <th>我的出价</th>
            <th>最高出价</th>
            <th>最低售价</th>
            <th>叫价状态</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty bidList}">
            <c:forEach items="${bidList}" var="bid">
                <tr>
                    <td class="am-text-sm">${bid.bftName}</td>
                    <td class="am-text-sm">${bid.bftSize}</td>
                    <td class="am-text-sm">${bid.bidDate}</td>
                    <td class="am-text-sm">￥<fmt:formatNumber value="${bid.bidMoney}" type="currency" pattern="#,#00.0#"/></td>
                    <td class="am-text-sm">￥<fmt:formatNumber value="${bid.highestBid}" type="currency" pattern="#,#00.0#"/></td>
                    <td class="am-text-sm">￥<fmt:formatNumber value="${bid.minimumSellingPrice}" type="currency" pattern="#,#00.0#"/></td>
                    <c:choose>
                        <c:when test="${bid.status == 0}">
                            <td class="am-text-sm">已生效</td>
                        </c:when>
                        <c:when test="${bid.status == 10}">
                            <td class="am-text-sm">待付款</td>
                        </c:when>
                        <c:when test="${bid.status == 11}">
                            <td class="am-text-sm">开始交易</td>
                        </c:when>
                        <c:when test="${bid.status == 20}">
                            <td class="am-text-sm">交易成功</td>
                        </c:when>
                        <c:otherwise>
                            <td class="am-text-sm">已过期</td>
                        </c:otherwise>
                    </c:choose>
                    <td class="span-img">
                        <a href="#"><span style="display: block;width: 16px;height: 30px; background: url('../../../assets/shoesImg/personal_center.png'); background-position: 6% 43%;"></span></a>
                        <a href="#"><img class="am-fr" style="margin-top: -32%;" src="../../../assets/shoesImg/delete.png"/></a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="am-table am-table-striped am-table-hover">
        <caption>
            <p class="am-fl layout-font-size-30">购买记录</p>
            <span class="am-fr" style="color: #249bd3;line-height: 50px;"><a href="#" class="layout-font-size-18">更多记录</a></span>
        </caption>
        <thead>
        <tr>
            <th style="width: 30%;">名称</th>
            <th>尺码</th>
            <th>购买日期</th>
            <th>金额</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty tradeList}">
            <c:forEach items="${tradeList}" var="trade">
                <tr>
                    <td class="am-text-sm">${trade.bftName}</td>
                    <td class="am-text-sm">${trade.bftSize}</td>
                    <td class="am-text-sm">${trade.transactionDate}</td>
                    <td class="am-text-sm">￥<fmt:formatNumber value="${trade.transactionMoney}" type="currency" pattern="#,#00.0#"/></td>
                    <c:choose>
                        <c:when test="${trade.status == 0}">
                            <td class="am-text-sm">已下单待支付</td>
                        </c:when>
                        <c:when test="${trade.status == 1}">
                            <td class="am-text-sm">已支付待发货</td>
                        </c:when>
                        <c:when test="${trade.status == 10}">
                            <td class="am-text-sm">已发货待检验</td>
                        </c:when>
                        <c:when test="${trade.status == 20}">
                            <td class="am-text-sm">检验通过</td>
                        </c:when>
                        <c:when test="${trade.status == 21}">
                            <td class="am-text-sm">检验未通过</td>
                        </c:when>
                        <c:when test="${trade.status == 30}">
                            <td class="am-text-sm">已发货待签收</td>
                        </c:when>
                        <c:when test="${trade.status == 40}">
                            <td class="am-text-sm">交易完成</td>
                        </c:when>
                        <c:otherwise>
                            <td class="am-text-sm">交易关闭</td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <c:choose>
                            <c:when test="${trade.status == 0}">
                                <a href="#" class="am-btn am-btn-danger">去支付</a>
                            </c:when>
                            <c:otherwise>
                                <a href="#" class="am-btn am-btn-danger" disabled="disabled">去支付</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</form>
<script src="${ctx}/assets/js/amazeui.min.js"></script>