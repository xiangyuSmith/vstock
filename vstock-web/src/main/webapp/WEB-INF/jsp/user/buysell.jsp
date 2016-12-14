<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="buysell" action="${cxt}/user/buysell" method="post">
    <table class="am-table am-table-striped am-table-hover">
        <caption>
            <c:if test="${type == 0}">
                <p class="am-fl layout-font-size-30">出售记录</p>
            </c:if>
            <c:if test="${type == 1}">
                <p class="am-fl layout-font-size-30">购买记录</p>
            </c:if>
        </caption>
        <thead>
        <tr>
            <th style="width: 30%;">球鞋名称</th>
            <th>尺码</th>
            <c:if test="${type == 0}">
                <th>出售日期</th>
            </c:if>
            <c:if test="${type == 0}">
                <th>购买日期</th>
            </c:if>
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
                        <div class="am-dropdown" data-am-dropdown>
                            <c:choose>
                                <c:when test="${trade.status == 1}">
                                    <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                </c:when>
                                <c:when test="${trade.status == 0}">
                                    <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                </c:when>
                                <c:otherwise>
                                    <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" select_type="select-btn" disabled="true"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                </c:otherwise>
                            </c:choose>
                            <ul class="am-dropdown-content">
                                <c:if test="${type == 0}">
                                    <li><a class="am-btn am-btn-xs am-text-left deliver-goods" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去发货</span></a></li>
                                </c:if>
                                <c:if test="${type == 1}">
                                    <li><a class="am-btn am-btn-xs am-text-left deliver-goods" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去支付</span></a></li>
                                </c:if>
                            </ul>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
    <c:choose>
        <c:when test="${page.pageNow == 1}">
            <a href="javascript:void(0)" data-url="../user/offerlist?type=0&pageNow=1" class="offer-btn am-btn am-btn-primary am-radius" disabled="disabled ">上一页</a>
        </c:when>
        <c:otherwise>
            <a href="javascript:void(0)" data-url="../user/offerlist?type=0&pageNow=${page.pageNow-1}" class="offer-btn am-btn am-btn-primary am-radius">上一页</a>
        </c:otherwise>
    </c:choose>
    <span class="am-margin-left-sm am-margin-right-sm layout-font-size-20">当前第${page.pageNow}页/共${page.totalPageCount}页</span>
    <c:choose>
        <c:when test="${page.pageNow == page.totalPageCount}">
            <a href="javascript:void(0)" data-url="../user/offerlist?type=0&pageNow=1" class="offer-btn am-btn am-btn-primary am-radius" disabled="disabled">下一页</a>
        </c:when>
        <c:otherwise>
            <a href="javascript:void(0)" data-url="../user/offerlist?type=0&pageNow=${page.pageNow+1}" class="offer-btn am-btn am-btn-primary am-radius">下一页</a>
        </c:otherwise>
    </c:choose>
</div>
</form>
<script src="${ctx}/assets/js/amazeui.min.js"></script>