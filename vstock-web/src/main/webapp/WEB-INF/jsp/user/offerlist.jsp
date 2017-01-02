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
                                        <li><a class="am-btn am-btn-xs am-text-left sale-up" href="javascript:void(0)"><div style="float: left; display: block;width: 20px;height: 18px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -50px -32px;"></div><span class="am-text-left am-text-sm">修改</span></a></li>
                                        <li><a class="am-btn am-btn-xs am-text-left sale-sub" data_type="1" data_id="${bid.id}" href="javascript:void(0)" disabled='true'><i class="am-icon-save am-margin-right-xs"></i><span class="am-text-left am-text-sm">保存</span></a></li>
                                        <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-quit" href="javascript:void(0)" disabled='true'><i class="am-icon-remove am-margin-right-xs"></i><span class="am-text-left am-text-sm">取消</span></a></li>
                                    </c:if>
                                    <c:if test="${bid.status == 10}">
                                        <li><a class="am-btn am-btn-xs am-text-left deliver-goods" bid-id="${bid.id}" bft-id="${bid.basicinformationId}" data-type="${bid.type}" bid_type="${type}" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去支付</span></a></li>
                                    </c:if>
                                    <c:if test="${bid.status == 1}">
                                        <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-del" del_data_id="${bid.id}" href="javascript:void(0)"><img class="am-margin-right-xs" src="../../../assets/shoesImg/delete.png"/><span class="am-text-left am-text-sm">删除</span></a></li>
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