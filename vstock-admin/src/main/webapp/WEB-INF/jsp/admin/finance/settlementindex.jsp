<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<style type="text/css">
    .div_height{height: 32px;}
</style>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">财务管理</strong> / <small>违约金管理</small></div>

        </div>
        <hr>
        <div class="am-g">
            <%--<c:if test="${not empty trade}">--%>
            <form action="/resfund/index" method="post">
                <input name="type" type="hidden" value="${record.type}"/>
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-bottom-sm">
                    <div class="am-u-sm-7 am-u-md-7 am-u-lg-7">
                        <span class="am-text-default am-text-middle">结算时间：</span>
                        <input type="date" id="dt" name="startTime" placeholder="" value="${startTime}">
                        <%--<div class="dateStartTime"></div>--%>
                        <span class="am-text-default am-text-middle">至</span>
                        <input type="date" id="dtd" name="endTime" placeholder="" value="${endTime}">
                        <%--<div class="dateEndTime"></div>--%>
                    </div>
                    <div class="am-u-sm-5 am-u-md-5 am-u-lg-5 am-padding-left-0 am-padding-right-0">
                        <span class="am-text-default am-text-middle">订单号：</span>
                        <input type="text" id="tradeNo" name="tradeNo" placeholder="请输入交易订单号" value="${record.tradeNo}">
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-bottom-sm">
                    <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                        <span class="am-text-default am-text-middle">卖家：</span>
                        <input type="text" class="div_height" value="${nick}" name="nick" id="nick" placeholder="请输入昵称或电话号码"/>
                    </div>
                    <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                        <span class="am-text-default am-text-middle">支付宝账号：</span>
                        <input type="text" class="div_height" value="${alipayAccount}" name="alipayAccount" id="alipayAccount" placeholder="请输入支付宝账号"/>
                    </div>
                    <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                        <span class="am-text-default am-text-middle">结算状态：</span>
                        <select class="div_height" id="status" name="status">
                            <option value="-1">--请选择--</option>
                            <option value="0" <c:if test="${record.status == 0}">selected="selected"</c:if>>未结算</option>
                            <option value="1" <c:if test="${record.status == 1}">selected="selected"</c:if>>已结算</option>
                        </select>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom-sm">
                    <%--<a href="/resfund/insertRefund" class="am-btn am-btn-danger am-fl am-btn-sm am-margin-left am-padding-left-sm"><i class="am-icon-plus am-margin-right-xs"></i>新增</a>--%>
                    <%--<a href="/resfund/insertRefund" class="am-btn am-btn-success am-fl am-btn-sm am-margin-left am-padding-left-sm"><i class="am-icon-money am-margin-right-xs"></i>批量退款</a>--%>
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
                        <%--<th><input type="checkbox" id="SelectAll"/></th>--%>
                        <th>序号</th>
                        <th>结算时间</th>
                        <th>对应订单号</th>
                        <th>卖家昵称</th>
                        <th>卖家支付宝</th>
                        <th>交易金额</th>
                        <th>平台佣金</th>
                        <th>验货费用</th>
                        <th>结算金额</th>
                        <th>状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty refundList}">
                        <c:forEach items="${refundList}" var="refund">
                            <tr>
                                <%--<td><input type="checkbox" <c:if test="${refund.status == 1}">disabled="disabled" </c:if>/></td>--%>
                                <td>${refund.id}</td>
                                <td>${refund.createDate}</td>
                                <td>${refund.tradeNo}</td>
                                <td>${refund.refundObj}</td>
                                <td>${refund.remarks}</td>
                                <td><fmt:formatNumber value="${refund.refundPrice}" type="currency" pattern="#,##0.0#"/></td>
                                <td></td>
                                <td></td>
                                <td><fmt:formatNumber value="${refund.refundPrice}" type="currency" pattern="#,##0.0#"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${refund.status == 1}">
                                            已结算
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

//        $("#SelectAll").click(function () {selectAll();});
//
//        //复选框事件
//        //全选、取消全选的事件
//        function selectAll(){
//            if ($("#SelectAll").is(":checked")) {
//                $(":checkbox:enabled").prop("checked", true);
//            } else {
//                $(":checkbox").attr("checked", false);
//            }
//        }

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