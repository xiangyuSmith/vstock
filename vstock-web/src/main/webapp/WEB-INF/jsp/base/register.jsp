<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../layout/head.jsp" %>
    <title>注册</title>
</head>
<body>
    <%@include file="../layout/top.jsp" %>
    <div data-am-widget="slider" class="am-slider am-slider-i2 am-no-layout" data-am-flexslider="{controlNav:false}"></div>
    <div class="get">
        <div class="am-g am-container am-container-height">
            <form class="am-form">
                手机号：<input id="mobile" type="text" placeholder="请输入用户名" /><br/>
                密码：<input id="password" type="password" minlength="6" placeholder="请输入密码" /><br/>
                确认密码：<input id="finalpassword" type="password" minlength="6" placeholder="确认密码" /><br/>
                <input type="button" id="prLogin" value="提交" />
            </form>
        </div>
    </div>
    <%@include file="../layout/bottom.jsp" %>
    <script>
        $(function(){
            $("#prLogin").click(function(){
               sendRequest("/register/insertUser",{
                   'mobile':$("#mobile").val(),
                   'password':$.md5($("#password").val())
               },function(data){

               });
            });
        });
    </script>
</body>
</html>
