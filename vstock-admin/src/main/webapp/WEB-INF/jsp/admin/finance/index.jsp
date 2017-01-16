<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">财务管理</strong> / <small>退款查询</small></div>

        </div>
        <hr>
        <div class="am-g">
            <%--<c:if test="${not empty trade}">--%>
                <form action="/backCommodity/index" method="post">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-lg am-text-middle">退货人：</span>
                            <input type="text" name="backPerson" id="backPersonId" class="am-input-lg am-padding-left-xs" placeholder="退货人"/>
                        </div>
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-lg am-text-middle">收货人：</span>
                            <input type="text" name="consignee" id="consigneeId" class="am-input-lg am-padding-left-xs" placeholder="收货人"/>
                        </div>
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-lg am-text-middle">订单号：</span>
                            <input type="text" name="tradeNo" id="tradeNoId" class="am-input-lg am-padding-left-xs" placeholder="订单号"/>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">
                        <div class="am-u-sm-6 am-u-md-6-u-lg-6">
                            <span class="am-text-lg am-text-middle">商品名称：</span>
                            <input type="text" name="bftName" id="bftName" class="am-input-lg am-padding-left-xs" placeholder="商品名称"/>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6-u-lg-6">
                            <span class="am-text-lg am-text-middle">状态：</span>
                            <select class="am-input-lg" name="status" id="status">
                                <option value="-1">--请选择--</option>
                                <c:if test="${not empty statusList}">
                                    <c:forEach items="${statusList}" var="status">
                                        <option value="${status.status}">${status.btfName}</option>
                                        <%--<c:choose>--%>
                                            <%--<c:when test="${trade.status == status.status}">--%>
                                                <%--<option value="${status.status}" selected = "selected">${status.bftName}</option>--%>
                                            <%--</c:when>--%>
                                            <%--<c:otherwise>--%>
                                                <%--<option value="${status.status}">${status.bftName}</option>--%>
                                            <%--</c:otherwise>--%>
                                        <%--</c:choose>--%>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                            <span class="am-text-lg am-text-middle">创建日期（起）：</span>
                            <input class="am-input-lg am-padding-left-xs" type="date" id="dt" name="startTime" placeholder="">
                            <%--<div class="dateStartTime"></div>--%>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                            <span class="am-text-lg am-text-middle">创建日期（止）：</span>
                            <input class="am-input-lg am-padding-left-xs" type="date" id="dtd" name="endTime" placeholder="">
                            <%--<div class="dateEndTime"></div>--%>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom-sm">
                        <a href="../backCommodity/lodejsp?type=0" class="am-btn am-btn-danger am-fl am-btn-sm am-margin-left am-padding-left-sm"><i class="am-icon-plus am-margin-right-xs"></i>新增</a>
                        <button href="javascript: void(0);" type="submit" class="am-btn am-btn-primary am-fr">查询</button>
                    </div>
                </form>
            <%--</c:if>--%>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th>退货人</th>
                        <th>收货人</th>
                        <th>商品名称</th>
                        <th>订单号</th>
                        <th>状态</th>
                        <th>创建日期</th>
                        <th>更新日期</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty backCommodityList}">
                        <c:forEach items="${backCommodityList}" var="backCommodity">
                            <tr>
                                <td>${backCommodity.backPerson}</td>
                                <td>${backCommodity.consignee}</td>
                                <td>${backCommodity.btfName}</td>
                                <td>${backCommodity.tradeNo}</td>
                                <c:if test="${not empty statusList}">
                                    <c:forEach items="${statusList}" var="status">
                                        <c:if test="${backCommodity.status == status.status}">
                                            <td>${status.btfName}</td>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <td>${backCommodity.createTime}</td>
                                <td>${backCommodity.updateTime}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${backCommodity.status == 10}">
                                            <a href="javascript:void(0);" data-id="${backCommodity.id}" class="sbt-on deliver-btn" data-am-modal="{target: '#deliverDoods-pop', width: 450}">发货</a>
                                        </c:when>
                                        <c:when test="${backCommodity.status == 11}">
                                            <a href="javascript:void(0);" data-id="${backCommodity.id}" class="sbt-on deliver-close">关闭</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascript:void(0);" class="sbt-on stn-details" data_id="${backCommodity.id}">详情</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
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
    <form action="/backCommodity/backList" id="trateList" method="post">
        <input type="hidden" name="id" id="id"/>
        <input type="hidden" name="backPerson" id="backPerson"/>
        <input type="hidden" name="consignee" id="consignee"/>
        <input type="hidden" name="tradeNo" id="tradeNo"/>
        <input type="hidden" name="btfName" id="btfName"/>
        <input type="hidden" name="status" id="statuse"/>
        <input type="hidden" id="startTime" name="startTime"/>
        <input type="hidden" id="endTime" name="endTime"/>
    </form>
</div>
<jsp:include page="../common/deliverDoods.jsp" flush="true"/>
<jsp:include page="../common/bottom.jsp" flush="true"/>
<script type="text/javascript">
    jQuery(function($){

//        var div1 = "#dt";
//        var div2 = "#dtd";
//        getDatePic(div1,div2);

        $('.stn-details').click(function () {
            $('#id').val($(this).attr("data_id"));
            $('#backPerson').val($('#backPersonId').val());
            $('#consignee').val($('#consigneeId').val());
            $('#tradeNo').val($('#tradeNoId').val());
            $('#btfName').val($('#bftName').val());
            $('#statuse').val($('#status').val());
            $('#startTime').val($('#dt').val());
            $('#endTime').val($('#dtd').val());
            $('#trateList').submit();
        });

        var backId = "";
        $('.deliver-btn').click(function () {backId = $(this).attr('data-id');});

        $('.logistics-btn').click(function () {
            $.post("/backCommodity/saveBackCommodity",{
                'id' : backId,
                'status' : 20,
                'express': $('#logisticsIn-name').val(),
                'courierNumber' : $('#logisticsIn-number').val()
            },function(res){
                if (res.reGode == 1){
                    $('.logistics-quit').click();
                    window.location.reload();
                    alert("发货成功！");
                }else {
                    alert("发货失败，重新发货！");
                }
            });
        });

        $('.deliver-close').click(function () {
            var id = $(this).attr('data-id');
            $.post("/backCommodity/saveBackCommodity",{
                'id' : id,
                'status' : 40
            },function(res){
                if (res.reGode == 1){
                    window.location.reload();
                    alert("关闭申请单成功！");
                }else {
                    alert("关闭申请单失败，重新操作！");
                }
            });
        });

    });
</script>