<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../layout/head.jsp" %>
    <style type="text/css">
        input::-webkit-input-placeholder{
            font-size: 14px;
        }
        .left_circle {
            width:25px;
            height:50px;
            border-radius:25px 0 0 25px;
            background:red;
        }
        .right_circle {
            width:25px;
            height:50px;
            border-radius:0 25px 25px 0;
            background:red;
        }
    </style>
</head>
<body>
<%@include file="../../layout/top-search.jsp"%>
<button
        type="button"
        class="am-btn am-btn-primary"
        data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 900}">
    Modal
</button>

<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
    <div class="am-modal-dialog">
        <div class="am-modal-hd" style="background-color: #00CD61;">
            <div class="am-active am-g am-padding-bottom-sm" style="color: #FFFFFF;">
                <span class="am-fl am-text-lg">出售清单</span>
            </div>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-modal-bd am-g" style="background-color: #FFFFFF;">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="am-u-sm-1 am-u-md-1 am-u-lg-1"><p></p></div>
                <div class="am-u-sm-10 am-u-md-10 am-u-lg-10">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom-xs">
                            <span><b class="am-text-xl"  style="color: #333333;">ADIDAS YEEZY BOOST 350 LOW OXFORD TAN</b></span>
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">
                            <img src="../../../../image/Adidas%20Ultra%20Boost%20Uncaged%20Hypebeast.jpg">
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">
                            <span class="am-text-xl" style="color: #646464;">最后成交价</span>
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                            <span class="am-text-lg" style="color: #646464;">6030</span><span class="am-margin-left-xs am-text-lg" style="color: #00D678;"><i class="am-icon-sort-down"></i>-320(17%)</span>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-lg">
                        <div class="am-u-sm-3 am-u-md-3 am-u-lg-3">
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                <span>最近最高叫价</span>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                <span>8000</span>
                            </div>
                        </div>
                        <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                            <a href="#" class="am-btn am-btn-success">叫价</a>
                        </div>
                        <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 left_circle"></div>
                            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 right_circle"></div>
                            <span>OR</span>
                        </div>
                        <div class="am-u-sm-3 am-u-md-3 am-u-lg-3">
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                <span>最近最低叫价</span>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                <span>8000</span>
                            </div>
                        </div>
                        <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                            <a href="#" class="am-btn am-btn-success">出售</a>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-1 am-u-md-1 am-u-lg-1"><p></p></div>
            </div>
        </div>
    </div>
</div>
<%@include file="../../layout/footer.jsp" %>
<%@include file="../../layout/bottom.jsp" %>
</body>
<script type="text/javascript">

</script>
</html>
