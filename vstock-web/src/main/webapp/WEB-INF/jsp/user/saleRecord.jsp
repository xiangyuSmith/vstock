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
                    <td class="am-text-sm">${bid.bftName}</td>
                    <td class="am-text-sm">${bid.bftSize}</td>
                    <td class="am-text-sm">${bid.bidDate}</td>
                    <td class="am-text-sm">￥<fmt:formatNumber value="${bid.bidMoney}" type="currency" pattern="#,#00.0#"/></td>
                    <c:choose>
                        <c:when test="${not empty bid.highestBid}">
                            <td class="am-text-sm">￥<fmt:formatNumber value="${bid.highestBid}" type="currency" pattern="#,#00.0#"/></td>
                        </c:when>
                        <c:otherwise>
                            <td class="am-text-sm">--</td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${not empty bid.minimumSellingPrice}">
                            <td class="am-text-sm">￥<fmt:formatNumber value="${bid.minimumSellingPrice}" type="currency" pattern="#,#00.0#"/></td>
                        </c:when>
                        <c:otherwise>
                            <td class="am-text-sm">--</td>
                        </c:otherwise>
                    </c:choose>
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
                    <td>
                        <div class="am-dropdown" data-am-dropdown>
                            <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" select_type="select-btn"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                            <ul class="am-dropdown-content">
                                <li><a class="am-btn am-btn-xs am-text-left sale-up" href="javascript:void(0)"><div style="float: left; display: block;width: 20px;height: 18px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -50px -32px;"></div><span class="am-text-left am-text-sm">修改</span></a></li>
                                <li><a class="am-btn am-btn-xs am-text-left sale-sub" href="javascript:void(0)" disabled='true'><i class="am-icon-save am-margin-right-xs"></i><span class="am-text-left am-text-sm">保存</span></a></li>
                                <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-quit" href="javascript:void(0)" disabled='true'><i class="am-icon-remove am-margin-right-xs"></i><span class="am-text-left am-text-sm">取消</span></a></li>
                                <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-del" href="javascript:void(0)"><img class="am-margin-right-xs" src="../../../assets/shoesImg/delete.png"/><span class="am-text-left am-text-sm">删除</span></a></li>
                            </ul>
                        </div>
                    </td>
                    <%--<td class="span-img">--%>
                        <%--<a href="#"><span style="display: block;width: 16px;height: 30px; background: url('../../../assets/shoesImg/personal_center.png'); background-position: 6% 43%;"></span></a>--%>
                        <%--<a href="#"><img class="am-fr" style="margin-top: -26%;" src="../../../assets/shoesImg/delete.png"/></a>--%>
                        <%--&lt;%&ndash;<span style="display: block;width: 36px;height: 30px; background: url('../../../assets/shoesImg/personal_center.png'); background-position: -82px -23px;"></span>&ndash;%&gt;--%>
                    <%--</td>--%>
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
        <th style="width: 30%;">球鞋名称</th>
        <th>尺码</th>
        <th>出售日期</th>
        <th>金额</th>
        <th>状态</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${not empty tradeList}">
        <c:forEach items="${tradeList}" var="trade">
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
                <%--<c:choose>--%>
                    <%--<c:when test="${trade.status == 1}">--%>
                        <%--<td><a href="#" class="am-btn am-btn-danger">去发货</a></td>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<td></td>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
            <td>
                <div class="am-dropdown" data-am-dropdown>
                    <c:choose>
                        <c:when test="${trade.status == 1}">
                            <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                        </c:when>
                        <c:otherwise>
                            <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" select_type="select-btn" disabled="true"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                        </c:otherwise>
                    </c:choose>
                    <ul class="am-dropdown-content">
                        <li><a class="am-btn am-btn-xs am-text-left deliver-goods" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去发货</span></a></li>
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