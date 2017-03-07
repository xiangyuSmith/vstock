<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="offerlist" action="/user/offerlist" method="post">
    <table class="am-table am-table-striped am-table-hover">
        <caption style="border-bottom: 1px solid #CACACA;">
            <div style="float: left; display: block;width: 37px;height: 45px; background: url('/assets/i/personal_center_map.png'); background-position: -703px -18px;"></div>
            <c:if test="${type == 0}">
                <span class="am-fl layout-font-size-26 am-inline-block">出价历史</span>
            </c:if>
            <c:if test="${type == 1}">
                <span class="am-fl layout-font-size-26 am-inline-block">叫价历史</span>
            </c:if>
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
                            <div class="doc-dropdown-justify-js">
                                <div class="am-dropdown doc-dropdown-js">
                                    <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle status-user-set" select_type="select-btn"><span class="am-icon-caret-down"></span></a>
                                    <ul class="am-dropdown-content">
                                        <c:if test="${bid.status == 0}">
                                            <li><a class="am-btn am-btn-xs am-text-left deliver-bid-goods" bid-id="${bid.id}" bft-id="${bid.basicinformationId}" data-type="${bid.type}" bid_type="0" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去支付</span></a></li>
                                        </c:if>
                                        <c:if test="${bid.status == 2}">
                                            <li><a class="am-btn am-btn-xs am-text-left deliver-bid-goods" bid-id="${bid.id}" bft-id="${bid.basicinformationId}" data-type="${bid.type}" bid_type="0" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">重新支付</span></a></li>
                                        </c:if>
                                        <c:if test="${bid.status == 11}">
                                            <li><a class="am-btn am-btn-xs am-text-left" data_type="0" href="${cxt}/detail?proName=${bid.bftName}&size=${bid.bftSize}"><div style="float: left; display: block;width: 20px;height: 18px; background: url('/assets/i/personal_center.png'); background-position: -50px -32px;"></div><span class="am-text-left am-text-sm">重新叫价</span></a></li>
                                        </c:if>
                                        <c:if test="${bid.status == 10}">
                                            <li><a class="am-btn am-btn-xs am-text-left sale-up" data_type="0" href="javascript:void(0)"><div style="float: left; display: block;width: 20px;height: 18px; background: url('/assets/i/personal_center.png'); background-position: -50px -32px;"></div><span class="am-text-left am-text-sm">修改</span></a></li>
                                            <li><a class="am-btn am-btn-xs am-text-left sale-sub" data_type="0" data_id="${bid.id}" btf-id="${bid.basicinformationId}" href="javascript:void(0)" disabled='true'><i class="am-icon-save am-margin-right-xs"></i><span class="am-text-left am-text-sm">保存</span></a></li>
                                            <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-quit" href="javascript:void(0)" disabled='true'><i class="am-icon-remove am-margin-right-xs"></i><span class="am-text-left am-text-sm">取消</span></a></li>
                                        </c:if>
                                        <c:if test="${bid.status == 1 || bid.status == 11 || bid.status == 30}">
                                            <li><a class="am-btn am-btn-xs am-text-left am-link-muted sale-del" del_data_id="${bid.id}" data-type="${bid.type}" btf-id="${bid.basicinformationId}"  href="javascript:void(0)"><img class="am-margin-right-xs" src="/assets/i/delete.png"/><span class="am-text-left am-text-sm">删除</span></a></li>
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
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
        <c:choose>
            <c:when test="${page.pageNow == 1}">
                <input type="hidden" data-url="../user/offerlist?type=${type}&pageNow=1" class="offer-ref-btn"/>
                <a href="javascript:void(0)" data-url="../user/offerlist?type=${type}&pageNow=1" class="offer-btn am-btn am-btn-primary am-radius" disabled="disabled ">上一页</a>
            </c:when>
            <c:otherwise>
                <a href="javascript:void(0)" data-url="../user/offerlist?type=${type}&pageNow=${page.pageNow-1}" class="offer-btn am-btn am-btn-primary am-radius">上一页</a>
            </c:otherwise>
        </c:choose>
        <span class="am-margin-left-sm am-margin-right-sm layout-font-size-20">当前第${page.pageNow}页/共${page.totalPageCount}页</span>
        <c:choose>
            <c:when test="${page.pageNow == page.totalPageCount}">
                <a href="javascript:void(0)" data-url="../user/offerlist?type=${type}&pageNow=1" class="offer-btn am-btn am-btn-primary am-radius" disabled="disabled">下一页</a>
            </c:when>
            <c:otherwise>
                <a href="javascript:void(0)" data-url="../user/offerlist?type=${type}&pageNow=${page.pageNow+1}" class="offer-btn am-btn am-btn-primary am-radius">下一页</a>
            </c:otherwise>
        </c:choose>
    </div>
</form>
<%@include file="../user/popup/deliverDoods.jsp"%>
<script>
    $(function(){
        $(".doc-dropdown-js").each(function(index){
            $(this).dropdown({justify: '.doc-dropdown-justify-js:eq('+index+')'});
        });
    });
</script>