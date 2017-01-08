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
            <c:if test="${type == 0}">
                <th>出售日期</th>
            </c:if>
            <c:if test="${type == 1}">
                <th>购买日期</th>
            </c:if>
            <c:if test="${type == 0}">
                <th>保证金</th>
            </c:if>
            <th>尺码</th>
            <th>金额</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty tradeList}">
            <c:forEach items="${tradeList}" var="trade">
                <tr>
                    <td class="am-text-sm">${trade.bftName}</td>
                    <td class="am-text-sm">${trade.transactionDate}</td>
                    <c:if test="${type == 0}">
                        <c:choose>
                            <c:when test="${not empty trade.bid.bidBond}">
                                <td class="am-text-sm"><fmt:formatNumber value="${trade.bid.bidBond}" type="number" pattern="￥0.00"/></td>
                            </c:when>
                            <c:otherwise>
                                <td class="am-text-sm">--</td>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <td class="am-text-sm">${trade.bftSize}</td>
                    <td class="am-text-sm"><fmt:formatNumber value="${trade.transactionMoney}" type="number" pattern="￥0.00"/></td>
                    <c:forEach items="${statusList}" var="status">
                        <c:if test="${status.id == trade.status}">
                            <td class="am-text-sm">${status.bftName}</td>
                        </c:if>
                    </c:forEach>
                    <td>
                        <c:if test="${type == 0}">
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
                                            <li><a class="am-btn am-btn-xs am-text-left trade-save" explain="发货" status="10" utype="2" trade-no="${trade.tradeNo}" bidId="${trade.bidId}" data-id="${trade.id}"  trade-type="2" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去发货</span></a></li>
                                        </c:when>
                                    </c:choose>
                                </ul>
                            </div>
                        </c:if>
                        <c:if test="${type == 1}">
                            <div class="am-dropdown" data-am-dropdown>
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
                                        <li><a class="am-btn am-btn-xs am-text-left trade-pament" data-id="${trade.id}"  trade-type="3" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去支付</span></a></li>
                                    </c:if>
                                    <c:if test="${trade.status == 30}">
                                        <li><a class="am-btn am-btn-xs am-text-left trade-save" explain="收货" status="40" utype="3" trade-no="${trade.tradeNo}" bidId="${trade.bidId}" data-id="${trade.id}"  trade-type="3" href="javascript:void(0)"><span class="am-text-left am-text-sm">确认收货</span></a></li>
                                    </c:if>
                                    <c:if test="${trade.status == 41}">
                                        <li><a class="am-btn am-btn-xs am-text-left trade-save" explain="删除" status="50" utype="3" trade-no="${trade.tradeNo}" bidId="${trade.bidId}" data-id="${trade.id}"  trade-type="3" href="javascript:void(0)"><span class="am-text-left am-text-sm">删除</span></a></li>
                                    </c:if>
                                </ul>
                            </div>
                        </c:if>
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