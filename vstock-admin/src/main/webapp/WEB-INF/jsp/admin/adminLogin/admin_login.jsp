<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/inc.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>欢迎来到stockx后台管理系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="format-detection" content="telephone=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <jsp:include page="../common/layout.jsp" flush="true"/>
    <style>
        .header {
            text-align: center;
            background: #0e90d2;
            color:#fff;
            background: url("/stockx/assets/admin/image/home.jpg");
        }
        .header h1 {
            font-size: 200%;
            color: #fff;
            margin-top: 30px;
        }
        .header p {
            font-size: 14px;
        }
        label.error{
            color:red;
            margin:10px 0;
            font-family: "微软雅黑";
        }
    </style>
</head>
<body>
<div class="header">
    <div class="am-g">
        <h1>Stockx login</h1>
        <p>Welcome To Stockx Management System<br/>欢迎来到stockx后台管理系统</p>
    </div>
    <hr />
</div>
<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
        <hr/>
        <input type="hidden" id="status" value="${status}" />
        <form action="/stockx/adminLogin/saveAdmin" method="post" class="am-form" id="form_login">
            <label for="username">用户名:</label>
            <input type="text" name="username" id="username" value="">
            <br>
            <label for="password">密码:</label>
            <input type="password" name="password" id="password" value="">
            <br>
            <label for="remember-me">
                <input id="remember-me" type="checkbox">
                记住密码
            </label>
            <br />
            <div class="am-cf">
                <input id="input_submit" type="button" name="" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl">
                <input type="button" name="" value="忘记密码 ^_^? " class="am-btn am-btn-default am-btn-sm am-fr">
            </div>
        </form>
        <hr>
        <p>© 2014 AllMobilize, Inc. Licensed under MIT license.</p>
    </div>
</div>
<script type="text/javascript">
    var user_flag = false;
    var status = $("#status").val();

    jQuery(document).ready(function($) {
        if(status!=3 && status!=""){
//            $.toaster({ priority : 'success', title : 'Notice', message : 'Your message here'});
//            $.toaster({ priority : 'info', title : 'Notice', message : 'Your message here'});
//            $.toaster({ priority : 'warning', title : 'Notice', message : 'Your message here'});
            $.toaster({ priority : 'danger', title : '登录失败', message : '账号密码有误'});
        }else if(status==3){
            $.toaster({ priority : 'success', title : '登录成功', message : ' '});
        }
        User.init();
        $("#input_submit").click(function(){
            //提交表单
            $("#form_login").submit();
        });

        document.onkeydown = function(e){
            var ev = document.all ? window.event : e;
            if(ev.keyCode==13) {
                $("#form_login").submit();
            }
        };
    });
</script>
</body>
</html>
