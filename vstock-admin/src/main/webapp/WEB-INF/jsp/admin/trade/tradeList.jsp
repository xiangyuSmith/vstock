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
                    <span><fmt:formatNumber value="${trade.transactionMoney}" type="currency" pattern="#,##0.00#"/></span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>运费：</label>
                    <span>${trade.tradeFreight}</span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>收货地区：</label>
                    <span><c:if test="${not empty trade.receivingInformation}">${fn:split(trade.receivingInformation,'-')[0]}</c:if></span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>详细地址：</label>
                    <span><c:if test="${not empty trade.receivingInformation}">${fn:split(trade.receivingInformation,'-')[1]}</c:if></span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>收货人：</label>
                    <span><c:if test="${not empty trade.receivingInformation}">${fn:split(trade.receivingInformation,'-')[2]}</c:if></span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>手机号码：</label>
                    <span>
                        <c:if test="${not empty trade.receivingInformation}">
                            <c:choose>
                                <c:when test="${not empty fn:split(trade.receivingInformation,'-')[3]}">
                                    ${fn:split(trade.receivingInformation,'-')[3]}
                                </c:when>
                                <c:when test="${not empty fn:split(trade.receivingInformation,'-')[4]}">
                                    ${fn:split(trade.receivingInformation,'-')[4]}
                                </c:when>
                                <c:otherwise></c:otherwise>
                            </c:choose>
                        </c:if>
                    </span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>卖家发货快递公司：</label>
                    <c:if test="${logisticsInformationList != null}">
                        <c:forEach items="${logisticsInformationList}" var="logisticsInformation">
                            <c:if test="${logisticsInformation.type == 0}">
                            <span>${logisticsInformation.companyName}</span>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>卖家发货快递单号：</label>
                    <c:if test="${logisticsInformationList != null}">
                        <c:forEach items="${logisticsInformationList}" var="logisticsInformation">
                            <c:if test="${logisticsInformation.type == 0}">
                                <span>${logisticsInformation.courierNumber}</span>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>买家收货快递公司：</label>
                    <c:if test="${logisticsInformationList != null}">
                        <c:forEach items="${logisticsInformationList}" var="logisticsInformation">
                            <c:if test="${logisticsInformation.type == 1}">
                                <span>${logisticsInformation.companyName}</span>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>买家收货快递单号：</label>
                    <c:if test="${logisticsInformationList != null}">
                        <c:forEach items="${logisticsInformationList}" var="logisticsInformation">
                            <c:if test="${logisticsInformation.type == 1}">
                                <span>${logisticsInformation.courierNumber}</span>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>退货卖家快递公司：</label>
                    <c:if test="${logisticsInformationList != null}">
                        <c:forEach items="${logisticsInformationList}" var="logisticsInformation">
                            <c:if test="${logisticsInformation.type == 2}">
                                <span>${logisticsInformation.companyName}</span>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>退货卖家快递单号：</label>
                    <c:if test="${logisticsInformationList != null}">
                        <c:forEach items="${logisticsInformationList}" var="logisticsInformation">
                            <c:if test="${logisticsInformation.type == 2}">
                                <span>${logisticsInformation.courierNumber}</span>
                            </c:if>
                        </c:forEach>
                    </c:if>
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
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>交易日期：</label>
                    <span>${trade.transactionDate}</span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-lg">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4 am-u-end">
                    <label>更新日期：</label>
                    <span>${trade.updateDate}</span>
                </div>
            </div>
            <c:if test="${trade.status == 10}">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-lg">
                    <input type="hidden" id="id" value="${record.id}"/>
                    <div class="am-u-sm-6 am-u-md-4 am-u-lg-6">
                        <a href="javascript:void(0);" class="am-btn am-btn-primary am-radius am-fr am-margin-right-xl testPass-btn"data-type="20">检验通过</a>
                    </div>
                    <div class="am-u-sm-6 am-u-md-4 am-u-lg-6">
                        <a href="javascript:void(0);" class="am-btn am-btn-danger am-radius" data-am-modal="{target: '#refund-remarks', width: 400}">检验失败</a>
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
<div class="am-modal am-modal-no-btn" tabindex="-1" id="refund-remarks">
    <div class="am-modal-dialog pre-sale">
        <div class="am-modal-hd" style="background-color: #4aaa4a;">
            <div class="am-active am-g am-margin-bottom-sm" style="color: #FFFFFF;">
                <span class="am-text-xl am-fl">失败原因</span>
            </div>
            <a href="javascript: void(0);" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-modal-bd am-g" style="background-color: #FFFFFF;">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-sm">
                <textarea type="text" id="reasons" name="reasons" style="min-width: 312px;" class="am-input-lg am-padding-left-xs" placeholder="失败原因"></textarea>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-sm">
                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                    <a href="javascript: void(0);" data-type="21" class="am-btn am-btn-primary am-radio testPass-btn">确定</a>
                </div>
                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                    <a href="javascript: void(0);" class="am-btn am-btn-danger am-radio" data-am-modal-close>取消</a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/bottom.jsp" flush="true"/>
<script type="text/javascript">
    jQuery(function($){
        $('#quit-btn').click(function () {
            $('#tradeindex').submit();
        });

        $('.testPass-btn').click(function () {
            var status = $(this).attr('data-type');
            var remarks = $(this)
            $.post("/trade/saveTrade",{
                'id': $('#id').val(),
                'status' : status,
                'remarks':$('#reasons').val()
            },function(res){
                if (res.reGode == 1){
                    $('#tradeindex').submit();
                }
            });
        });

    });
</script>
