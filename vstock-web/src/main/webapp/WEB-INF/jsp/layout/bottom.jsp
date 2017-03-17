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

<script>
    Echo.init({
        offset: 0,
        throttle: 0
    });
    function uaredirect(f) {
        try {
            if (document.getElementById("bdmark") != null) {
                return
            }
            var b = false;
            if (arguments[1]) {
                var e = window.location.host;
                var a = window.location.href;
                if (isSubdomain(arguments[1], e) == 1) {
                    f = f + "/#m/" + a;
                    b = true
                } else {
                    if (isSubdomain(arguments[1], e) == 2) {
                        f = f + "/#m/" + a;
                        b = true
                    } else {
                        f = a;
                        b = false
                    }
                }
            } else {
                b = true
            }
            if (b) {
                var c = window.location.hash;
                if (!c.match("fromapp")) {
                    if ((navigator.userAgent.match(/(iPhone|iPod|Android|ios|SymbianOS)/i))) {
                        location.replace(f)
                    }
                }
            }
        } catch(d) {}
    }
    function isSubdomain(c, d) {
        this.getdomain = function(f) {
            var e = f.indexOf("://");
            if (e > 0) {
                var h = f.substr(e + 3)
            } else {
                var h = f
            }
            var g = /^www\./;
            if (g.test(h)) {
                h = h.substr(4)
            }
            return h
        };
        if (c == d) {
            return 1
        } else {
            var c = this.getdomain(c);
            var b = this.getdomain(d);
            if (c == b) {
                return 1
            } else {
                c = c.replace(".", "\\.");
                var a = new RegExp("\\." + c + "$");
                if (b.match(a)) {
                    return 2
                } else {
                    return 0
                }
            }
        }
    };
    uaredirect("手机站","WEB站");
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