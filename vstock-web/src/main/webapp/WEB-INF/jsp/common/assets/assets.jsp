<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="my-popup-assets">
    <div class="am-modal-dialog pre-bid" style="background-color: #e2e2e2;">
        <div class="am-modal-hd" style="background-color: #00D273;">
            <div class="am-active am-g am-padding-bottom-sm" style="color: #FFFFFF;">
                <span class="am-fl am-text-lg">加入资产</span>
            </div>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-modal-bd am-g" style="background-color: #FFFFFF;">
            <form class="am-form am-form-horizontal">
                <div class="am-form-group am-margin-top">
                    <label for="assets_purchaseDate" class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-form-label" style="font-weight: lighter;">购买日期:</label>
                    <div class="am-u-sm-7 am-u-md-7 am-u-lg-7 am-u-end">
                        <input type="date" name="purchaseDate" id="assets_purchaseDate" placeholder="请输入您的购买日期">
                    </div>
                </div>
                <div class="am-form-group">
                    <label for="assets_money" class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-form-label" style="font-weight: lighter;">购买价格:</label>
                    <div class="am-u-sm-7 am-u-md-7 am-u-lg-7 am-u-end">
                        <input type="text" name="money" id="assets_money" placeholder="请输入您的购买价格">
                    </div>
                </div>
                <div class="am-form-group">
                    <label for="assets_size" class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-form-label" style="font-weight: lighter;">尺码:</label>
                    <div class="am-u-sm-7 am-u-md-7 am-u-lg-7 am-u-end">
                        <select id="assets_size" name="size">
                            <option value="-1" selected = "selected">--请选择--</option>
                            <c:if test="${not empty sizes}">
                                <c:forEach items="${sizes}" var="size">
                                    <option value="${size}">${size}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="am-form-group">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-u-sm-offset-2 am-margin-top">
                        <a class="am-btn am-btn-default am-radius am-fl" style="min-width: 100px;" data-am-modal-close>取消</a>
                        <a class="assetsAdd-btn am-btn am-btn-danger am-radius" style="min-width: 100px;">提交</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>