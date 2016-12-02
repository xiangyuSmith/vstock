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
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom-lg">
                            <span><b class="am-text-xl"  style="color: #333333;">JORDAN 12 RETRO FLU GAME[2016]</b></span>
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-xl">
                            <img src="../../../../image/Adidas%20Ultra%20Boost%20Uncaged%20Hypebeast.jpg">
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                            <span class="am-text-xl" style="color: #646464;">请选择一个尺码</span>
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-lg">
                            <select data-am-selected="{btnWidth: '100%', btnSize: 'sm', maxHeight: 100}">
                                <option>尺码</option>
                                <option>36</option>
                                <option>36.5</option>
                                <option>37</option>
                                <option>37.5</option>
                                <option>38</option>
                            </select>
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
