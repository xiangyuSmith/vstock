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
        data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0, width: 900, height: 588}">
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
                    <img src="../../../../image/Adidas%20Ultra%20Boost%20Uncaged%20Hypebeast.jpg" style="width: 100%; height: 20%;"/>
                </div>
                <div class="am-u-sm-7 am-u-md-7 am-u-lg-7">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <span class="am-fl am-text-lg" style="color: #333333;">ADIDAS</span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <span class="am-fl"><b class="am-fl am-text-left am-text-xl"  style="color: #333333;">ADIDAS YEEZY BOOST 350 LOW PIRATE BLACK[2016]</b></span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-xs">
                        <span class="am-fl am-text-left am-text-lg" style="color: #333333;">PIRATE BLACK/PIRATE BLACK-PIRATE BLACK</span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-xs am-padding-left-0">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-text-lg">
                            <label class="am-fl" style="color: #646464;">最高售价：<span class="am-margin-left-sm" style="color: #646464;">2000</span></label>

                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-text-lg">
                            <label class="am-fr" style="color: #646464;">最低售价：<span class="am-margin-left-sm" style="color: #646464;">1210</span></label>

                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-fl am-margin-top-xs">
                        <span class="am-text-lg am-fl" style="color: #646464;"><input type="checkbox"/>    店家保证所提供的鞋子均为正品</span>
                    </div>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-top-xs am-margin-bottom-sm">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <label class="am-fl am-text-lg">售价：</label>
                        <span class="am-fr am-text-lg">1810</span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-xs am-padding-bottom" style="border-bottom:1px solid #050505;">
                        <label class="am-fl am-text-lg">运费：</label>
                        <span class="am-fr am-text-lg">***</span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-sm am-margin-bottom">
                        <label class="am-fl am-text-lg">总计：</label>
                        <span class="am-fr am-text-lg">***</span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <span class="am-fl am-text-lg">尺码</span>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-fr am-padding-right-0">
                            <select data-am-selected="{btnWidth: '100%', btnSize: 'sm', btnStyle: 'secondary', maxHeight: 100}">
                                <option>36</option>
                                <option>36.5</option>
                                <option>37</option>
                                <option>37.5</option>
                                <option>38</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top" style="background-color: #EFEEEC">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top">
                    <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                        <span class="am-fl am-margin-top am-text-lg am-padding-bottom-xs" style="color: #646464;">保证金</span>
                    </div>
                    <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                        <span class="am-fr am-margin-top am-text-sm" style="color: #646464;"><a href="#">保证金说明</a></span>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-lg" style="background-color: #FFFFFF;">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-fl am-padding-left-0 am-margin-top">
                        <label class="am-fl am-text-xl" style="color: #646464;">总金额：<span class="am-text-xl am-margin-left" style="color: #646464;">1800</span></label>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                        <a href="#" class="am-btn am-btn-danger am-fr am-btn-lg am-radius am-padding-left-xl am-padding-right-xl">提交</a>
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
