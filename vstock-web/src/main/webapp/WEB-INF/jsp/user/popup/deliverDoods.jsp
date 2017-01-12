<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/inc.jsp" %>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="deliverDoods-pop">
    <div class="am-modal-dialog pre-sale">
        <div class="am-modal-hd" style="background-color: #4aaa4a;">
            <div class="am-active am-g am-margin-bottom-sm" style="color: #FFFFFF;">
                <span class="am-text-xl am-fl">发货信息</span>
            </div>
            <a href="javascript: void(0);" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-modal-bd am-g" style="background-color: #FFFFFF;">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-xl">
                <span class="am-text-lg am-fl">收货地址：</span>
                <span>上海市浦东新区达尔文路88号1号楼113室 上海波哥网络科技有限公司市场部（收）021-1234648</span>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-lg">
                <span class="am-text-lg am-fl">运单信息：</span>
                <select id="logisticsIn-name" class="am-fl" style="min-height: 35px; min-width: 160px;">
                    <c:if test="${not empty expressList}">
                        <c:forEach items="${expressList}" var="express">
                            <option>${express.expressName}</option>
                        </c:forEach>
                    </c:if>
                </select>
                <input id="logisticsIn-number" style="min-height: 35px; min-width: 270px;" type="text"/>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-xl am-margin-bottom-lg">
                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                    <a href="javascript: void(0);" style="min-width: 100px;" class="am-btn am-btn-default am-radius logistics-quit am-fr" data-am-modal-close>取消</a>
                </div>
                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                    <a href="javascript: void(0);" style="min-width: 100px;" class="am-btn am-btn-danger am-radius logistics-btn am-fl">提交</a>
                </div>
            </div>
        </div>
    </div>
</div>