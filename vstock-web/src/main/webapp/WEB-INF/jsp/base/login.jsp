<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<html>
<head>
    <%@include file="../layout/head.jsp" %>
    <title>登录</title>
</head>
<body>
    <div class="am-g">
        <form class="am-form">
            手机号登录:<input id="mobile" type="text" name="username" />
            密 码:<input id="password" type="password" name="password" />
            <input type="button" id="prLogin" value="确定" />
        </form>
    </div>
    <%@include file="../layout/bottom.jsp" %>
    <script>
        $(function(){
            $("#prLogin").click(function(){
                sendRequest("/login/prLogin",{
                    'mobile':$("#mobile").val(),
                    'password':$.md5($("#password").val())
                },function(data){
                    if(data.retCode == 1){
                        location.href = "/index";
                    }
                });
            });
        });
    </script>
</body>
</html>
