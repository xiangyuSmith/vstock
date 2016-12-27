<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-cf">
                <strong class="am-text-primary am-text-lg">订单信息</strong> / <small>订单详情</small>
                <span class="am-fr"><a href="javascript:void(0);" id="quit-btn" class="am-btn am-btn-danger am-btn-xs"><i class="am-icon-reply am-margin-right-xs"></i>返回</a></span>
            </div>
        </div>
        <hr>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom am-margin-top">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>卖家：</label>
                    <span>${trade.sellerName}</span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>买家：</label>
                    <span>${trade.buyersName}</span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>球鞋名：</label>
                    <span>${trade.bftName}</span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>尺码：</label>
                    <span>${trade.bftSize}</span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>金额：</label>
                    <span><fmt:formatNumber value="${trade.transactionMoney}" type="currency" pattern="#,#00.0#"/></span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>运费：</label>
                    <span>${trade.tradeFreight}</span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>快递公司：</label>
                    <span></span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>快递单号：</label>
                    <span>${trade.courierNumber}</span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>状态：</label>
                    <span>
                        <c:if test="${not empty statusList}">
                            <c:forEach items="${statusList}" var="status">
                                <c:if test="${trade.status == status.get(0)}">${status.get(1)}</c:if>
                            </c:forEach>
                        </c:if>
                    </span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-lg">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>交易日期：</label>
                    <span>${trade.transactionDate}</span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4 am-u-end">
                    <label>更新日期：</label>
                    <span>${trade.updateDate}</span>
                </div>
            </div>
            <c:if test="${trade.status == 10}">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-lg">
                    <input type="hidden" id="id" value="${record.id}"/>
                    <div class="am-u-sm-6 am-u-md-4 am-u-lg-6">
                        <a href="javascript:void(0);" class="am-btn am-btn-primary am-radius am-fr am-margin-right-xl"data-type="20" id="testPass-btn">检验通过</a>
                    </div>
                    <div class="am-u-sm-6 am-u-md-4 am-u-lg-6">
                        <a href="javascript:void(0);" class="am-btn am-btn-danger am-radius" data-type="21" id="testFailure-btn">检验失败</a>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
    <form action="/trade/index" id="tradeindex" method="post">
        <input type="hidden" name="buyersName" value="${record.buyersName}"/>
        <input type="hidden" name="sellerName" value="${record.sellerName}"/>
        <input type="hidden" name="bftName" value="${record.bftName}"/>
        <input type="hidden" name="bftSize" value="${record.bftSize}"/>
        <input type="hidden" name="status" value="${record.status}"/>
        <input type="hidden" id="startTime" value="${record.transactionDate}"/>
        <input type="hidden" id="endTime" value="${record.updateDate}"/>
    </form>
</div>
<jsp:include page="../common/bottom.jsp" flush="true"/>
<script type="text/javascript">
    jQuery(function($){
        $('#quit-btn').click(function () {
            $('#tradeindex').submit();
        });

        $('#testPass-btn').click(function () {
            var status = $(this).attr('data-type');
            $.post("/trade/saveTrade",{
                'id': $('#id').val(),
                'status' : status
            },function(res){
                if (res.reGode == 1){
                    $('#tradeindex').submit();
                }
            });
        });

    });
</script>
