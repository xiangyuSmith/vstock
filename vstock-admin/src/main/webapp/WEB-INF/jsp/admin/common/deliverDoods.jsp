<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="inc.jsp"%>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="deliverDoods-pop">
    <div class="am-modal-dialog pre-sale">
        <div class="am-modal-hd" style="background-color: #4aaa4a;">
            <div class="am-active am-g am-margin-bottom-sm" style="color: #FFFFFF;">
                <span class="am-text-xl am-fl">发货信息</span>
            </div>
            <a href="javascript: void(0);" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-modal-bd am-g" style="background-color: #FFFFFF;">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top">
                <span class="am-text-lg">快递公司：</span>
                <select id="logisticsIn-name" class="am-input-lg" style="min-width: 197px;">
                    <c:if test="${not empty expressList}">
                        <c:forEach items="${expressList}" var="express">
                            <option>${express.expressName}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-sm">
                <span class="am-text-lg">快递单号：</span><input id="logisticsIn-number" class="am-input-lg" type="text"/>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-sm">
                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                    <a href="javascript: void(0);" class="am-btn am-btn-primary am-radio logistics-btn">确定</a>
                </div>
                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                    <a href="javascript: void(0);" class="am-btn am-btn-danger am-radio logistics-quit" data-am-modal-close>取消</a>
                </div>
            </div>
        </div>
    </div>
</div>