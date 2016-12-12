<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="offerlist" action="${cxt}/user/offerlist" method="post">
    <table class="am-table am-table-striped am-table-hover">
        <caption>
            <c:if test="${type == 0}">
                <p class="am-fl layout-font-size-30">出价历史</p>
            </c:if>
            <c:if test="${type == 1}">
                <p class="am-fl layout-font-size-30">叫价历史</p>
            </c:if>
        </caption>
        <thead>
        <tr>
            <th style="width: 30%;">球鞋名称</th>
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
                        <td class="span-img">
                            <a href="#"><span style="display: block;width: 16px;height: 30px; background: url('../../../assets/shoesImg/personal_center.png'); background-position: 6% 43%;"></span></a>
                            <a href="#"><img class="am-fr" style="margin-top: -26%;" src="../../../assets/shoesImg/delete.png"/></a>
                                <%--<span style="display: block;width: 36px;height: 30px; background: url('../../../assets/shoesImg/personal_center.png'); background-position: -82px -23px;"></span>--%>
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
