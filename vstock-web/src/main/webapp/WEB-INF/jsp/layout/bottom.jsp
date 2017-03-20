<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="inc.jsp" %>
<!--[if (gte IE 9)|!(IE)]><!-->
<script src="${ctx}/assets/js/jquery.min.js"></script><!-- PRE_WINDOW -->
<script src="${ctx}/assets/js/amazeui.min.js"></script>
<script src="${ctx}/assets/js/bootstrap.min.js"></script>
<script src="${ctx}/assets/js/md5.min.js"></script>
<script src="${ctx}/assets/js/countUp.min.js"></script>
<script src="${ctx}/assets/js/amazeui.lazyload.min.js"></script>
<script src="${ctx}/assets/js/echo.min.js"></script>
<script src="${ctx}/assets/js/highcharts/highcharts.js"></script>
<script src="${ctx}/assets/js/highcharts/exporting.js"></script>
<script src="${ctx}/assets/js/admin.js"></script>
<script src="${ctx}/assets/js/date.js"></script>
<script src="${ctx}/assets/js/jquery.validate/jquery.validate.min.js"></script>
<script src="${ctx}/assets/js/jquery.validate/messages_zh.js"></script>
<script src="${ctx}/assets/js/jquery.toaster.js"></script>
<script type="text/javascript">
    Echo.init({
        offset: 0,
        throttle: 0
    });
    var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cspan style='display:none;' id='cnzz_stat_icon_1261516476'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1261516476%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));
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
<div class="am-modal am-modal-confirm" tabindex="-1" id="my-bid-confirm" style="z-index: 2111">
    <div class="am-modal-dialog">
        <div class="am-modal-hd" id="alert-confirmBid-title"></div>
        <div class="am-modal-bd" id="alert-confirmBid-content"></div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>
<div class="am-modal am-modal-confirm" tabindex="-1" id="my-trade-confirm" style="z-index: 2112">
    <div class="am-modal-dialog">
        <div class="am-modal-hd" id="alert-confirmTrade-title"></div>
        <div class="am-modal-bd" id="alert-confirmTrade-content"></div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>再看看</span>
            <span class="am-modal-btn" data-am-modal-confirm>去支付</span>
        </div>
    </div>
</div>