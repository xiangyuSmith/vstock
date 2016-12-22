<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="inc.jsp" %>

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="${ctx}/assets/js/jquery.min.js"></script><!-- PRE_WINDOW -->
<script src="${ctx}/assets/js/amazeui.min.js"></script>
<script src="${ctx}/assets/js/md5.min.js"></script>
<script src="${ctx}/assets/js/echarts-all.js"></script>
<script src="${ctx}/assets/js/countUp.min.js"></script>
<script src="${ctx}/assets/js/amazeui.lazyload.min.js"></script>
<script src="${ctx}/assets/js/echo.min.js"></script>
<script src="${ctx}/assets/js/highcharts/highcharts.js"></script>
<script src="${ctx}/assets/js/highcharts/exporting.js"></script>
<script src="${ctx}/assets/js/highcharts/_settings_hcharts.js"></script>
<script src="${ctx}/assets/js/admin.js"></script>
<script>
    Echo.init({
        offset: 0,
        throttle: 0
    });
</script>

<%@include file="../common/popup/loginwindow.jsp" %>

<div class="am-modal am-modal-alert" tabindex="-1" id="forex-alert" style="z-index: 2110">
    <div class="am-modal-dialog">
        <div class="am-modal-bd" id="alert-content"></div>
        <div class="am-modal-footer"><span class="am-modal-btn" data-am-modal-confirm>确定</span></div>
    </div>
</div>
<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm" style="z-index: 2111">
    <div class="am-modal-dialog">
        <div class="am-modal-hd" id="alert-confirm-title"></div>
        <div class="am-modal-bd" id="alert-confirm-content"></div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" id="createPay" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>