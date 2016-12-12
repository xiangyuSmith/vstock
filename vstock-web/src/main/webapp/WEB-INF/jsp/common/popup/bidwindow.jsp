<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../layout/head.jsp" %>
    <style type="text/css">
        input::-webkit-input-placeholder{
            font-size: 14px;
        }
    </style>
</head>
<body>
<%@include file="../../layout/top-search.jsp"%>
<button
        type="button"
        class="am-btn am-btn-primary"
        data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 900, height: 540}">
    Modal
</button>

<div class="am-modal am-modal-no-btn" tabindex="-1" id="doc-modal-1">
    <div class="am-modal-dialog">
        <div class="am-modal-hd" style="background-color: #00CD61;">
            <div class="am-active am-g am-padding-bottom-sm" style="color: #FFFFFF;">
                <span class="am-fl am-text-lg">最新叫价</span>
            </div>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-modal-bd am-g" style="background-color: #FFFFFF;">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom-xl">
                <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                    <img src="${ctx}/assets/shoesImg/small/Adidas%20Yeezy%20Boost%20350%20Low%20Pirate%20Black%20(2016).jpg" style="width: 100%;"/>
                </div>
                <div class="am-u-sm-7 am-u-md-7 am-u-lg-7">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <b class="am-fl" style="color: #333333;">ADIDAS</b>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <span class="am-fl"><b class="am-fl am-text-left layout-font-size-22"  style="color: #333333;">ADIDAS YEEZY BOOST 350 LOW PIRATE BLACK[2016]</b></span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <span class="am-fl am-text-left layout-font-size-20" style="color: #333333;">PIRATE BLACK/PIRATE BLACK-PIRATE BLACK</span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-top-lg">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-right-0">
                            <label class="am-fl layout-font-size-20" style="color: #646464;">买家最高出价：<span class="am-margin-left-sm" style="color: #646464;">￥6500</span></label>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-left-0" style="border-left:1px solid #646464">
                            <label class="am-fr layout-font-size-20" style="color: #646464;">卖家最低售价：<span class="am-margin-left-sm" style="color: #646464;">￥7000</span></label>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-fl am-margin-top-lg">
                        <div class="am-u-md-2 am-fl am-padding-0">
                            <span style="color:#FD9192;">温馨提示：</span>
                        </div>
                        <div class="am-u-md-10 am-fr am-padding-0 am-text-left">
                            <span>
                                出价后，请保持手机畅通，当有卖家出售时，我们将短信通知，收到短信后请务必在24小时内支付订单金额，否则将进行违约处理。
                            </span>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-top-xs am-margin-bottom-sm am-text-left">
                    <div class="am-g am-padding-top-sm am-padding-bottom-sm">
                        <p class="layout-font-size-20 am-margin-bottom-sm">出售金额：</p>
                        <input type="email" class="am-form-field" value="￥6800.00">
                    </div>
                    <div class="am-g am-padding-bottom-sm">
                        <div class="am-u-md-6 am-padding-0">
                            <p class="layout-font-size-20 am-margin-bottom-sm">尺码</p>
                            <select style="width: 90%;height: 37px;border: 1px solid #ccc;">
                                <option>36</option>
                                <option>36.5</option>
                                <option>37</option>
                                <option>37.5</option>
                                <option>38</option>
                            </select>
                        </div>
                        <div class="am-u-md-6 am-padding-0">
                            <p class="layout-font-size-20 am-margin-bottom-sm">有效期</p>
                            <select class="am-fr" style="width: 90%;height: 37px;border: 1px solid #ccc;">
                                <option>1天</option>
                                <option>3天</option>
                                <option>5天</option>
                                <option>7天</option>
                            </select>
                        </div>
                    </div>
                    <div class="am-g">
                        <p>
                            <span class="am-u-md-6 am-padding-0 layout-font-size-20">运费：</span>
                            <span class="am-u-md-6 am-padding-0 layout-font-size-20 am-text-right">卖家承担</span><br/>
                        </p>

                        <span class="am-u-md-6 am-padding-0 layout-font-size-20">鉴定费：</span>
                        <span class="am-u-md-6 am-padding-0 layout-font-size-20 am-text-right">免费</span><br/>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-u-md-12" style="background-color: #fff;margin-top: 3px;">
            <div class="am-u-md-8"></div>
            <div class="am-u-md-4 am-text-left">
                <div class="am-padding-sm">
                    <span class="am-u-md-6 am-padding-0 layout-font-size-20">保证金：</span><span class="am-u-md-6 am-padding-0 layout-font-size-20">￥10.00</span><br/>
                    <span class="am-u-md-6 am-padding-0 layout-font-size-20" style="height: 38px;line-height: 38px;">支付金额：</span><span class="am-u-md-6 am-padding-0 layout-font-size-26" style="color: #E75C58;">10元</span><br/>
                    <div class="am-text-center am-margin-top-xl">
                        <a href="#" class="am-btn am-btn-danger am-btn-lg am-radius  am-btn-block" >提交</a>
                    </div>
                </div>
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
