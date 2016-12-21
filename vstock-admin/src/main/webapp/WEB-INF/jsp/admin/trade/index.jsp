<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">订单信息</strong> / <small>订单查询</small></div>

        </div>
        <hr>
        <div class="am-g">
            <c:if test="${not empty trade}">
                <form action="/trade/index" method="post">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-lg am-text-middle">买家：</span>
                            <input type="text" name="buyersName" id="buyersId" class="am-input-lg" placeholder="购买人" value="${trade.buyersName}"/>
                        </div>
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-lg am-text-middle">卖家：</span>
                            <input type="text" name="sellerName" id="sellerId" class="am-input-lg" placeholder="出售人" value="${trade.sellerName}"/>
                        </div>
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-lg am-text-middle">球鞋名称：</span>
                            <input type="text" name="bftName" id="bftId" class="am-input-lg" placeholder="球鞋名称" value="${trade.bftName}"/>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-lg am-text-middle">尺码：</span>
                            <select class="am-input-lg" name="bftSize" id="bftSize">
                                <option value="0">--请选择--</option>
                                <c:if test="${not empty sizeList}">
                                    <c:forEach items="${sizeList}" var="sizes">
                                        <c:choose>
                                            <c:when test="${trade.bftSize == sizes}">
                                                <option value="${sizes}" selected = "selected">${sizes}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${sizes}">${sizes}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                        <div class="am-u-lg-4 am-u-end">
                            <span class="am-text-lg am-text-middle">状态：</span>
                            <select class="am-input-lg" name="status" id="status">
                                <option value="-1">--请选择--</option>
                                <c:if test="${not empty statusList}">
                                    <c:forEach items="${statusList}" var="status">
                                        <c:choose>
                                            <c:when test="${trade.status == status.get(0)}">
                                                <option value="${status.get(0)}" selected = "selected">${status.get(1)}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${status.get(0)}">${status.get(1)}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                            <span class="am-text-lg am-text-middle">交易日期（起）：</span>
                            <input class="am-input-lg" type="date" id="dt" name="startTime" placeholder="" value="${trade.transactionDate}">
                            <%--<div class="dateStartTime"></div>--%>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                            <span class="am-text-lg am-text-middle">交易日期（止）：</span>
                            <input class="am-input-lg" type="date" id="dtd" name="endTime" placeholder="" value="${trade.endDate}">
                            <%--<div class="dateEndTime"></div>--%>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <button href="javascript: void(0);" type="submit" class="am-btn am-btn-primary am-fr">查询</button>
                    </div>
                </form>
            </c:if>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th>买家</th>
                        <th>卖家</th>
                        <th width="20%">球鞋名称</th>
                        <th>尺码</th>
                        <th>金额</th>
                        <th>状态</th>
                        <th>订单日期</th>
                        <th>结束日期</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="insert-data" class="xy-dis"></tr>
                    <c:if test="${not empty tradeList}">
                        <c:forEach items="${tradeList}" var="trade">
                            <tr>
                                <td>${trade.buyersName}</td>
                                <td>${trade.sellerName}</td>
                                <td>${trade.bftName}</td>
                                <td>${trade.bftSize}</td>
                                <td><fmt:formatNumber value="${trade.transactionMoney}" type="currency" pattern="#,#00.0#"/></td>
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
                                <td>${trade.transactionDate}</td>
                                <td>${trade.endDate}</td>
                                <td><a href="javascript:void(0);" class="sbt-on" data_id="${trade.id}">详情</a></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
                <jsp:include page="../common/paging.jsp" flush="true">
                    <jsp:param name="page" value="${page}"/>
                    <jsp:param name="linkAddress" value="${linkAddress}"/>
                </jsp:include>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/bottom.jsp" flush="true"/>
<script type="text/javascript">
    jQuery(function($){

//        var div1 = "#dt";
//        var div2 = "#dtd";
//        getDatePic(div1,div2);

        $('.sbt-on').click(function () {
            var fid = $(this).attr("data_id");
            var buyersName = $('#buyersId').val();
            var sellerName = $('#sellerId').val();
            var bftName = $('#bftId').val();
            var bftSize = $('#bftSize').val();
            var status = $('#status').val();
            var startTime = $('#dt').val();
            var endTime = $('#dtd').val();
            $.post("/trade/tradeList",{
                'fid': fid,
                'buyersName' : buyersName,
                'sellerName' : sellerName,
                'bftName' : bftName,
                'bftSize' : bftSize,
                'status' : status,
                'startTime' : startTime,
                'endTime' : endTime
            },function(res){
            });
        });

    });
</script>