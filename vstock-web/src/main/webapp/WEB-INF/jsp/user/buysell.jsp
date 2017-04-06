<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="buysell" action="/user/buysell" method="post">
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
                <td>出售日期</td>
            </c:if>
            <c:if test="${type == 1}">
                <td>购买日期</td>
            </c:if>
            <c:if test="${type == 0}">
                <td>保证金</td>
            </c:if>
            <td>尺码</td>
            <td>金额</td>
            <td>状态</td>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty tradeList}">
            <c:forEach items="${tradeList}" var="trade">
                <%--<c:if test="${type == 0 && trade.status != 52 || type == 1 && trade.status != 51}">--%>
                <tr>
                    <td class="am-text-sm">${trade.bftName}</td>
                    <td class="am-text-sm"><c:out value="${fn:substring(trade.transactionDate, 0, 10)}" /></td>
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
                    <td class="am-text-sm">
                        <c:forEach items="${statusList}" var="status">

                            <c:if test="${status.id == trade.status}">
                                <c:if test="${type == 0}">${status.bftName}</c:if>
                                <c:if test="${type == 1}">
                                    <c:choose>
                                        <c:when test="${trade.status == 60}">
                                            退款中
                                        </c:when>
                                        <c:when test="${trade.status == 61}">
                                            退款成功
                                        </c:when>
                                        <c:otherwise>
                                            ${status.bftName}
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:if test="${type == 0}">
                            <div class="doc-dropdown-justify-js">
                                <div class="am-dropdown doc-dropdown-js">
                                    <c:choose>
                                        <c:when test="${trade.status == 0 || trade.status == 2 || trade.status == 41 || trade.status == 51}">
                                            <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle status-user-set" select_type="select-btn"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle status-user-set" disabled="true"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                        </c:otherwise>
                                    </c:choose>
                                    <ul class="am-dropdown-content">
                                        <c:choose>
                                            <c:when test="${trade.status == 0}">
                                                <li><a class="am-btn am-btn-xs am-text-left trade-pament" data-bftName="${trade.bftName}" data-id="${trade.id}"  trade-type="2" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去支付</span></a></li>
                                            </c:when>
                                            <c:when test="${trade.status == 2}">
                                                <li><a class="am-btn am-btn-xs am-text-left trade-save" explain="发货" status="10" utype="2" trade-no="${trade.tradeNo}" bidId="${trade.bidId}" data-id="${trade.id}"  trade-type="2" href="javascript:void(0)" data-am-modal="{target: '#deliverDoods-pop', width: 600}"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去发货</span></a></li>
                                            </c:when>
                                            <c:when test="${trade.status == 41 || trade.status == 51}">
                                                <li>
                                                    <input type="hidden" class="tradeSave"  explain="删除" status="52" utype="3" trade-no="${trade.tradeNo}" bidId="${trade.bidId}" data-id="${trade.id}"  trade-type="3"/>
                                                    <a class="am-btn am-btn-xs am-text-left trade_del" href="javascript:void(0)"><i class="am-icon-trash am-margin-right-xs"></i><span class="am-text-left am-text-sm">删除</span></a>
                                                </li>
                                            </c:when>
                                        </c:choose>
                                    </ul>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${type == 1}">
                            <div class="doc-dropdown-justify-js">
                                <div class="am-dropdown doc-dropdown-js">
                                    <c:choose>
                                        <c:when test="${trade.status == 1 || trade.status == 30 || trade.status == 41 || trade.status == 52 || trade.status == 10 || trade.status == 30 || trade.status == 20}">
                                            <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" select_type="select-btn"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="am-btn am-btn-default am-btn-xs trade-save" disabled="true"><span class="am-icon-caret-down am-margin-left-xs"></span></a>
                                        </c:otherwise>
                                    </c:choose>
                                    <ul class="am-dropdown-content">
                                        <c:if test="${trade.status == 1}">
                                            <li><a class="am-btn am-btn-xs am-text-left trade-pament" data-bftName="${trade.bftName}" data-id="${trade.id}"  trade-type="3" href="javascript:void(0)"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">去支付</span></a></li>
                                        </c:if>
                                        <c:if test="${trade.status == 30}">
                                            <li><a class="am-btn am-btn-xs am-text-left tradeSave" explain="收货" status="40" utype="3" trade-no="${trade.tradeNo}" bidId="${trade.bidId}" data-id="${trade.id}"  trade-type="3" href="javascript:void(0)"><span class="am-text-left am-text-sm">确认收货</span></a></li>
                                        </c:if>
                                        <c:if test="${trade.status == 10 || trade.status == 30 || trade.status == 20}">
                                            <li><a class="am-btn am-btn-xs am-text-left express-get" courierNumber="${trade.courierNumber}" data-id="${trade.id}" href="javascript:void(0)" data-am-modal="{target: '#express-pop', width: 900}"><i class="am-icon-share am-margin-right-xs"></i><span class="am-text-left am-text-sm">查看物流</span></a></li>
                                        </c:if>
                                        <c:if test="${trade.status == 41 || trade.status == 52}">
                                            <li>
                                                <input type="hidden" class="tradeSave" explain="删除" status="51" utype="3" trade-no="${trade.tradeNo}" bidId="${trade.bidId}" data-id="${trade.id}" trade-type="3"/>
                                                <a class="am-btn am-btn-xs am-text-left trade_del" href="javascript:void(0)"><span class="am-text-left am-text-sm">删除</span></a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </c:if>
                    </td>
                </tr>
                <%--</c:if>--%>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
    <c:choose>
        <c:when test="${page.pageNow == 1}">
            <a href="javascript:void(0)" data-url="../user/buysell?type=${type}&pageNow=1" class="offer-btn am-btn am-btn-primary am-radius" disabled="disabled ">上一页</a>
        </c:when>
        <c:otherwise>
            <a href="javascript:void(0)" data-url="../user/buysell?type=${type}&pageNow=${page.pageNow-1}" class="offer-btn am-btn am-btn-primary am-radius">上一页</a>
        </c:otherwise>
    </c:choose>
    <span class="am-margin-left-sm am-margin-right-sm layout-font-size-20">当前第${page.pageNow}页/共${page.totalPageCount}页</span>
    <c:choose>
        <c:when test="${page.pageNow == page.totalPageCount}">
            <a href="javascript:void(0)" data-url="../user/buysell?type=${type}&pageNow=1" class="offer-btn am-btn am-btn-primary am-radius" disabled="disabled">下一页</a>
        </c:when>
        <c:otherwise>
            <a href="javascript:void(0)" data-url="../user/buysell?type=${type}&pageNow=${page.pageNow+1}" class="offer-btn am-btn am-btn-primary am-radius">下一页</a>
        </c:otherwise>
    </c:choose>
</div>
</form>

<div class="am-modal am-modal-confirm" tabindex="-1" id="trade_del_confirm">
    <div class="am-modal-dialog">
        <div class="am-modal-hd"></div>
        <div class="am-modal-bd">
            删除后将不再显示出价记录，确定要删除吗？
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>
<%@include file="../user/popup/deliverDoods.jsp"%>
<%@include file="popup/userBuyAddress.jsp" %>
<%@include file="popup/expressList.jsp"%>
<a href="javascript:;" id="now-seller-buy-detailed" style="display: none;" data-am-modal="{target: '#my-popup-buy-userBuyAddress', width: 900, height: 520}">购买</a>
<%--<script src="${ctx}/assets/js/amazeui.min.js"></script>--%>
<script>
    $(function(){
        $(".doc-dropdown-js").each(function(index){
            $(this).dropdown({justify: '.doc-dropdown-justify-js:eq('+index+')'});
        });

        $('.trade_del').click(function(){
            $('#trade_del_confirm').modal({
                onConfirm: function() {
                    $('.tradeSave').click();
                },
                // closeOnConfirm: false,
                onCancel: function() {
                }
            });
        });
    });
</script>