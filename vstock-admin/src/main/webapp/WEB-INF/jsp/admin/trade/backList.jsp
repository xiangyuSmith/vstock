<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-cf">
                <strong class="am-text-primary am-text-lg">退货信息</strong> / <small>退货详情</small>
                <span class="am-fr"><a href="javascript:void(0);" id="quit-btn" class="am-btn am-btn-danger am-btn-xs"><i class="am-icon-reply am-margin-right-xs"></i>返回</a></span>
            </div>
        </div>
        <hr>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom am-margin-top">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>退货人：</label>
                    <span>${backCommodity.backPerson}</span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>收货人：</label>
                    <span>${backCommodity.consignee}</span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>商品名称：</label>
                    <span>${backCommodity.btfName}</span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>快递单号：</label>
                    <span>${backCommodity.courierNumber}</span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>订单号：</label>
                    <span>${backCommodity.tradeNo}</span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>订单号：</label>
                    <span id="tradeNos">${backCommodity.tradeNo}</span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>状态：</label>
                    <span>
                        <c:if test="${not empty statusList}">
                            <c:forEach items="${statusList}" var="status">
                                <c:if test="${backCommodity.status == status.status}">${status.btfName}</c:if>
                            </c:forEach>
                        </c:if>
                    </span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>创建日期：</label>
                    <span>${backCommodity.createTime}</span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>更新日期：</label>
                    <span>${backCommodity.updateTime}</span>
                </div>
            </div>
            <c:if test="${backCommodity.status == 0}">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-lg">
                    <input type="hidden" id="id" value="${backCommodity.id}"/>
                    <div class="am-u-sm-6 am-u-md-4 am-u-lg-6">
                        <a href="javascript:void(0);" class="am-btn am-btn-primary am-radius am-fr am-margin-right-xl testPass-btn"data-type="10">审核通过</a>
                    </div>
                    <div class="am-u-sm-6 am-u-md-4 am-u-lg-6">
                        <a href="javascript:void(0);" class="am-btn am-btn-danger am-radius testPass-btn" data-type="11">审核失败</a>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
    <form action="/backCommodity/index" id="tradeindex" method="post">
        <input type="hidden" name="backPerson" id="backPerson" value="${record.backPerson}"/>
        <input type="hidden" name="consignee" id="consignee" value="${record.consignee}"/>
        <input type="hidden" name="tradeNo" id="tradeNo" value="${record.tradeNo}"/>
        <input type="hidden" name="btfName" id="btfName" value="${btfName}"/>
        <input type="hidden" name="status" id="statuse" value="${record.status}"/>
        <input type="hidden" id="startTime" name="startTime" value="${startTime}"/>
        <input type="hidden" id="endTime" name="endTime" value="${endTime}"/>
    </form>
</div>
<jsp:include page="../common/bottom.jsp" flush="true"/>
<script type="text/javascript">
    jQuery(function($){
        $('#quit-btn').click(function () {
            $('#tradeindex').submit();
        });

        $('.testPass-btn').click(function () {
            var status = $(this).attr('data-type');
            $.post("/backCommodity/saveBackCommodity",{
                'id': $('#id').val(),
                'tradeNo' : $('#tradeNos').text(),
                'status' : status
            },function(res){
                if (res.reGode == 1){
                    $('#tradeindex').submit();
                }
            });
        });

    });
</script>
