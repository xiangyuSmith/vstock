<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<style type="text/css">
    .div_height{height: 32px;}
</style>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">财务管理</strong> / <small>退款查询</small></div>

        </div>
        <hr>
        <div class="am-g">
            <%--<c:if test="${not empty trade}">--%>
                <form action="/resfund/index" method="post">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-bottom-sm">
                        <div class="am-u-sm-5 am-u-md-5 am-u-lg-5">
                            <span class="am-text-default am-text-middle">退单日期：</span>
                            <input type="date" id="dt" name="startTime" placeholder="" value="${startTime}">
                            <%--<div class="dateStartTime"></div>--%>
                            <span class="am-text-default am-text-middle">至</span>
                            <input type="date" id="dtd" name="endTime" placeholder="" value="${endTime}">
                            <%--<div class="dateEndTime"></div>--%>
                        </div>
                        <div class="am-u-sm-7 am-u-md-7 am-u-lg-7 am-padding-left-0 am-padding-right-0">
                            <div class="am-u-sm-7 am-u-md-7 am-u-lg-7 am-padding-left-0 am-padding-right-0">
                                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-left-0 am-padding-right-0">
                                    <span class="am-text-default am-text-middle am-padding-left-sm">状态：</span>
                                    <select name="status" id="status" class="div_height">
                                        <option value="-1">--请选择--</option>
                                        <c:if test="${not empty statusList}">
                                            <c:forEach items="${statusList}" var="status">
                                                <c:choose>
                                                    <c:when test="${record.status == status.status}">
                                                        <option value="${status.status}" selected>${status.btfName}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${status.status}">${status.btfName}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-left-0 am-padding-right-0">
                                    <span class="am-text-default am-text-middle">退款对象：</span>
                                    <select name="refundObj" id="refundObj" class="div_height">
                                        <option value="-1">--请选择--</option>
                                        <c:if test="${not empty objList}">
                                            <c:forEach items="${objList}" var="obj">
                                                <c:choose>
                                                    <c:when test="${record.refundObj == obj.refundObj}">
                                                        <option value="${obj.refundObj}" selected>${obj.btfName}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${obj.refundObj}">${obj.btfName}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <div class="am-u-sm-5 am-u-md-5 am-u-lg-5 am-padding-left-0 am-padding-right-0">
                                <span class="am-text-default am-text-middle am-padding-left">退款类型：</span>
                                <select name="type" id="type" class="div_height">
                                    <option value="-1">--请选择--</option>
                                    <c:if test="${not empty typeList}">
                                        <c:forEach items="${typeList}" var="type">
                                            <c:choose>
                                                <c:when test="${record.type == type.type}">
                                                    <option value="${type.type}" selected>${type.btfName}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${type.type}">${type.btfName}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-bottom-sm">
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-default am-text-middle">商品名称：</span>
                            <input type="text" class="div_height" value="${record.btfName}" name="bftName" id="bftName" placeholder="商品名称"/>
                        </div>
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-default am-text-middle">订单号：</span>
                            <input type="text" class="div_height" value="${record.tradeNo}" name="tradeNo" id="tradeNo" placeholder="订单号"/>
                        </div>
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-default am-text-middle">退单号：</span>
                            <input type="text" class="div_height" value="${record.refundNo}" name="refundNo" id="refundNo" placeholder="退单号"/>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom-sm">
                        <a href="/resfund/insertRefund" class="am-btn am-btn-danger am-fl am-btn-sm am-margin-left am-padding-left-sm"><i class="am-icon-plus am-margin-right-xs"></i>新增</a>
                        <a href="/resfund/insertRefund" class="am-btn am-btn-success am-fl am-btn-sm am-margin-left am-padding-left-sm"><i class="am-icon-money am-margin-right-xs"></i>批量退款</a>
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
                        <th><input type="checkbox" id="SelectAll"/></th>
                        <th>退款类型</th>
                        <th>退款单号</th>
                        <th>订单号</th>
                        <th>商品名称</th>
                        <th>退款对象</th>
                        <th>退款金额</th>
                        <th>状态</th>
                        <th>退单时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty refundList}">
                        <c:forEach items="${refundList}" var="refund">
                            <tr>
                                <td><input type="checkbox" <c:if test="${refund.status == 1}">disabled="disabled" </c:if>/></td>
                                <td><c:forEach items="${typeList}" var="type"><c:if test="${type.type == refund.type}">${type.btfName}</c:if></c:forEach></td>
                                <td>${refund.refundNo}</td>
                                <td>${refund.tradeNo}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${fn:length(refund.btfName) > 12}">
                                            ${fn:substring(refund.btfName, 0, 12)}
                                        </c:when>
                                        <c:otherwise>
                                            ${refund.btfName}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><c:forEach items="${objList}" var="obj"><c:if test="${obj.refundObj == refund.refundObj}">${obj.btfName}</c:if></c:forEach></td>
                                <td><fmt:formatNumber value="${refund.refundPrice}" type="currency" pattern="#,##0.0#"/></td>
                                <td><c:forEach items="${statusList}" var="status"><c:if test="${status.status == refund.status}">${status.btfName}</c:if></c:forEach></td>
                                <td>${refund.createDate}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${refund.type == 1 || refund.type == 3 || refund.type == 4 || refund.type == 5 || refund.type == 0}">
                                            <a href="/resfund/alipayRefund?id=${refund.id}&tradeNo=${refund.tradeNo}&type=${refund.type}&status=1&upstatus=51" class="am-btn am-btn-sm am-btn-success am-radius" style="color: #ffffff;" <c:if test="${refund.status == 1}">disabled="disabled" </c:if>>退款</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascript: void(0);" data-id="${refund.id}" data-type="${refund.type}" btf_id="${refund.btfId}" tradeNo="${refund.tradeNo}" class="finance-sbt-btn am-btn am-btn-sm am-btn-danger am-radius" style="color: #ffffff;" <c:if test="${refund.status == 1}">disabled="disabled" </c:if>>转账</a>
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
    <div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
        <div class="am-modal-dialog">
            <div class="am-modal-hd">付款信息</div>
            <div class="am-modal-bd">
                您确定是否已经付款？
            </div>
            <div class="am-modal-footer">
                <span class="am-modal-btn" data-am-modal-cancel>取消</span>
                <span class="am-modal-btn" data-am-modal-confirm>确定</span>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/bottom.jsp" flush="true"/>
<script type="text/javascript">
    jQuery(function($){

        $("#SelectAll").click(function () {selectAll();});

        //复选框事件
        //全选、取消全选的事件
        function selectAll(){
            if ($("#SelectAll").is(":checked")) {
                $(":checkbox:enabled").prop("checked", true);
            } else {
                $(":checkbox").attr("checked", false);
            }
        }

        $('.finance-sbt-btn').click(function () {
            $('#my-confirm').modal({
                relatedTarget: this,
                onConfirm: function(options) {
                    var $this = $(this.relatedTarget);
                    var id = $this.attr('data-id');
                    var tradeNo = $this.attr('tradeNo');
                    var typee = $this.attr('data-type');
                    var upstatus = "61";
                    if (typee == 1){upstatus = "51";}
                    $.post("/resfund/transferAccounts",{
                        'id' : id,
                        'tradeNo' : tradeNo,
                        'type': typee,
                        'upstatus':upstatus,
                        'status' : 1
                    },function(res){
                        if (res.reGode > 0){
                            window.location.reload();
                            alert("确认收款成功！");
                        }else {
                            alert("确认收款失败，请联系管理员！");
                        }
                    });
                },
                // closeOnConfirm: false,
                onCancel: function() {
                }
            });
        });

//        var div1 = "#dt";
//        var div2 = "#dtd";
//        getDatePic(div1,div2);

//        $('.stn-details').click(function () {
//            $('#id').val($(this).attr("data_id"));
//            $('#backPerson').val($('#backPersonId').val());
//            $('#consignee').val($('#consigneeId').val());
//            $('#tradeNo').val($('#tradeNoId').val());
//            $('#btfName').val($('#bftName').val());
//            $('#statuse').val($('#status').val());
//            $('#startTime').val($('#dt').val());
//            $('#endTime').val($('#dtd').val());
//            $('#trateList').submit();
//        });
//
//        var backId = "";
//        $('.deliver-btn').click(function () {backId = $(this).attr('data-id');});
//
//        $('.logistics-btn').click(function () {
//            $.post("/backCommodity/saveBackCommodity",{
//                'id' : backId,
//                'status' : 20,
//                'express': $('#logisticsIn-name').val(),
//                'courierNumber' : $('#logisticsIn-number').val()
//            },function(res){
//                if (res.reGode == 1){
//                    $('.logistics-quit').click();
//                    window.location.reload();
//                    alert("发货成功！");
//                }else {
//                    alert("发货失败，重新发货！");
//                }
//            });
//        });
//
//        $('.deliver-close').click(function () {
//            var id = $(this).attr('data-id');
//            $.post("/backCommodity/saveBackCommodity",{
//                'id' : id,
//                'status' : 40
//            },function(res){
//                if (res.reGode == 1){
//                    window.location.reload();
//                    alert("关闭申请单成功！");
//                }else {
//                    alert("关闭申请单失败，重新操作！");
//                }
//            });
//        });

    });
</script>