<%--
  Created by IntelliJ IDEA.
  User: administor
  Date: 2017/3/23
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="hidden" id="alipay_url" value="${url}"/>
<script type="text/javascript">
    window.location.href = document.getElementById("alipay_url").value;
</script>
</body>
</html>
