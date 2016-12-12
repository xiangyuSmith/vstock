<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../layout/head.jsp" %>
    <style type="text/css">
        .pre-sale input::-webkit-input-placeholder{
            font-size: 14px;
        }
        .pre-sale .circle {
            width: 100%;
            height: 100%;
            background: #FFFFFF;
            -moz-border-radius: 50%;
            -webkit-border-radius: 50%;
            border-radius: 50%;
            border:1px solid #ACA8A3;
        }
        .pre-sale .right_half{
            border: 1px solid #030303;
            border-left: 1px solid #ffffff;
            border-top-right-radius: 50%;
            border-bottom-right-radius: 50%;
            width: 50%;
            margin-left: -37px;
        }
        .pre-sale .left_half{
            border: 1px solid #030303;
            border-right: 1px solid #ffffff;
            border-top-left-radius: 50%;
            border-bottom-left-radius: 50%;
            width: 50%;
            margin-right: -10px;
        }
        .pre-sale .half_span{
            display: block;
            line-height: 80px;
            margin-left: -12px;
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
    <div class="am-modal-dialog pre-sale">
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
                            <img src="${ctx}/assets/shoesImg/small/Adidas%20Yeezy%20Boost%20350%20Low%20Pirate%20Black%20(2016).jpg" />
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">
                            <span class="am-text-xl" style="color: #646464;">最后成交价</span>
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                            <span class="am-text-lg" style="color: #646464;">￥6030</span><span class="am-margin-left-xs am-text-lg" style="color: #00D678;">-320(17%)</span>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0">
                                    <span class="am-text-default">最近最高叫价</span>
                                </div>
                                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                    <span class="am-text-default am-fl" style="color: #030303">￥8000</span>
                                </div>
                            </div>
                            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-top-xs">
                                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                    <a href="#" class="am-btn am-btn-success">叫价</a>
                                </div>
                            </div>
                        </div>

                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6" style="border-left: 1px solid #030303">
                            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0">
                                    <span class="am-text-default">最近最低叫价</span>
                                </div>
                                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                    <span class="am-text-default am-fl" style="color: #030303">￥8000</span>
                                </div>
                            </div>
                            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-top-xs">
                                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                    <a href="#" class="am-btn am-btn-danger">出售</a>
                                </div>
                            </div>
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
