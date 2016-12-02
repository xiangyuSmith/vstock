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
<button type="button" class="am-btn am-btn-danger" data-am-modal="{target: '#my-popup', width: 350}">测试</button>

<div class="am-modal am-modal-no-btn" id="my-popup">
    <div class="am-modal-dialog">
        <div data-am-widget="tabs" class="am-tabs am-tabs-d2">
            <ul class="am-tabs-nav am-cf">
                <li class="am-active" style="background-color: #FFFFFF;"><a href="[data-tab-panel-0]"><p class="am-text-xl">登录</p></a></li>
                <li class="" style="background-color: #FFFFFF;"><a href="[data-tab-panel-1]"><p class="am-text-xl">注册</p></a></li>
            </ul>
            <div class="am-tabs-bd" style="background-color: #EFEEEC;">
                <div data-tab-panel-0 class="am-tab-panel am-active am-g">
                    <div class="am-input-group am-input-group-lg am-center am-margin-top-sm am-margin-right-lg am-margin-left-lg">
                        <input type="text" id="mobile" class="am-form-field" placeholder="用户名" required/>
                    </div>
                    <div class="am-input-group am-input-group-lg am-center am-margin-right-lg am-margin-left-lg">
                        <input id="password" class="am-form-field" type="password" placeholder="密码" required/>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-top-sm am-margin-left-lg">
                        <a href="#" class="am-text-left am-fl">忘记密码？</a>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-padding-right-0 am-margin-right-lg">
                        <a href="javascript:void(0)" class="am-btn am-btn-danger am-fr" id="prLogin">登陆</a>
                    </div>
                </div>
                <div data-tab-panel-1 class="am-tab-panel am-g">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-input-group am-input-group-lg am-center am-margin-top-sm">
                            <input type="text" class="am-form-field" placeholder="昵称" required/>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-input-group am-input-group-lg">
                            <input class="am-form-field" type="text" placeholder="手机号码" required/>
                            <span class="am-input-group-btn">
                                <a href="#" class="am-btn am-btn-default" style="font-size: 14px!important;">发送验证码</a>
                            </span>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-input-group am-input-group-lg am-center">
                            <input class="am-form-field" type="text" placeholder="验证码" required/>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-input-group am-input-group-lg am-center">
                            <input class="am-form-field" type="password" placeholder="密码" required/>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-xs">
                        <div class="am-input-group am-input-group-lg am-center">
                            <input class="am-form-field" type="password" placeholder="再次输入密码" required/>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">
                        <div class="am-u-sm-1 am-u-md-1 am-u-lg-1 am-fl am-margin-left-0 am-padding-left-0">
                            <input type="checkbox" class="am-padding-top-lg am-fl"/>
                        </div>
                        <div class="am-u-sm-11 am-u-md-11 am-u-lg-11 am-padding-left-0 am-margin-left-0">
                            <span class="am-text-sm am-margin-left-0 am-text-left am-fl">我已阅读并同意《<a href="#">用户使用协议</a>》</span>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <a href="#" class="am-btn am-btn-danger am-btn-sm am-fr">注册</a>
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
    $(function(){
        $("#prLogin").click(function(){
            sendRequest("/login/prLogin",{
                'mobile':$("#mobile").val(),
                'password':$.md5($("#password").val())
            },function(data){

            });
        });
    });
</script>
</html>
