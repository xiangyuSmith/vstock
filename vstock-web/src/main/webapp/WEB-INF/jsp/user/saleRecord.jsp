<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="saleRecord" action="${cxt}/index/testSale" method="post">
    <table class="am-table am-table-striped am-table-hover am-margin-bottom-xl">
        <caption>
            <p class="am-fl am-text-xl am-text-danger">最近叫价</p>
            <span class="am-fr am-margin-bottom-0"><a href="#">更多记录</a></span>
        </caption>
        <thead>
        <tr>
            <th style="width: 20%;">球鞋名称</th>
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
                    <td>${bid.bftName}</td>
                    <td>${bid.bftSize}</td>
                    <td>${bid.bidDate}</td>
                    <td><fmt:formatNumber value="${bid.bidMoney}" type="currency" pattern="#,#00.0#"/></td>
                    <c:choose>
                        <c:when test="${not empty bid.highestBid}">
                            <td><fmt:formatNumber value="${bid.highestBid}" type="currency" pattern="#,#00.0#"/></td>
                        </c:when>
                        <c:otherwise>
                            <td>--</td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${not empty bid.minimumSellingPrice}">
                            <td><fmt:formatNumber value="${bid.minimumSellingPrice}" type="currency" pattern="#,#00.0#"/></td>
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
                        <span style="display: block;width: 75px;height: 30px; background: url('../../../assets/shoesImg/personal_center.png'); background-position: -36px -23px;"></span>
                        <%--<span style="display: block;width: 36px;height: 30px; background: url('../../../assets/shoesImg/personal_center.png'); background-position: -82px -23px;"></span>--%>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="am-table am-table-striped am-table-hover">
        <caption>
            <p class="am-fl am-text-xl">出售历史</p>
            <span class="am-fr am-margin-bottom-0"><a href="#">更多记录</a></span>
        </caption>
        <thead>
        <tr>
            <th style="width: 30%;">球鞋名称</th>
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
                    <td>${trade.bftName}</td>
                    <td>${trade.bftSize}</td>
                    <td>${trade.transactionDate}</td>
                    <td><fmt:formatNumber value="${trade.transactionMoney}" type="currency" pattern="#,#00.0#"/></td>
                    <c:choose>
                        <c:when test="${trade.status == 0}">
                            <td>已下单待支付</td>
                        </c:when>
                        <c:when test="${trade.status == 1}">
                            <td>
                                <select>
                                    <option>已支付待发货</option>
                                    <option>已发货待检验</option>
                                </select>
                            </td>
                        </c:when>
                        <c:when test="${trade.status == 10}">
                            <td>已发货待检验</td>
                        </c:when>
                        <c:when test="${trade.status == 20}">
                            <td>检验通过</td>
                        </c:when>
                        <c:when test="${trade.status == 21}">
                            <td>检验未通过</td>
                        </c:when>
                        <c:when test="${trade.status == 30}">
                            <td>已发货待签收</td>
                        </c:when>
                        <c:when test="${trade.status == 40}">
                            <td>交易完成</td>
                        </c:when>
                        <c:otherwise>
                            <td>交易关闭</td>
                        </c:otherwise>
                    </c:choose>
                    </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</form>
<script src="${ctx}/assets/js/amazeui.min.js"></script>