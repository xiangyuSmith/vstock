<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../layout/head.jsp" %>
    <title>注册</title>
</head>
<body>
    <input id="uname" type="text" placeholder="请输入用户名" /><br/>
    <input id="password" type="password" minlength="6" placeholder="请输入密码" /><br/>
    <input id="finalpassword" type="password" minlength="6" placeholder="确认密码" /><br/>
    <input type="submit" value="提交" />
    <%@include file="../layout/bottom.jsp" %>
</body>
</html>
