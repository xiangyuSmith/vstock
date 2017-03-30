<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">角色管理</strong> / <small>新增角色</small></div>
        </div>
        <hr>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="widget am-cf">
                    <div class="widget-body am-fl">
                        <form action="/role/insertRole" class="am-form tpl-form-line-form">
                            <div class="am-form-group am-padding">
                                <label class="am-fl">管理员名称:</label>
                                <input type="text" class="tpl-form-input" id="userName" name="roleName" placeholder="管理员名称" required>
                            </div>
                            <div class="am-form-group am-padding">
                                <label class="am-fl">密码:</label>
                                <input type="password" class="tpl-form-input" id="password" name="roleName" placeholder="密码" required>
                            </div>
                            <div class="am-form-group am-padding">
                                <button type="button" id="insertAdminBtn" class="am-btn am-btn-primary am-radius tpl-btn-bg-color-success ">提交</button>
                                <button type="button" onclick="javascript:history.back(-1);" class="am-btn am-radius tpl-btn-bg-color-success ">返回</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/bottom.jsp" flush="true"/>
<script type="text/javascript">
    $(function(){
        $("#insertAdminBtn").click(function(){
            var userName = $("#userName").val();
            var password = $("#password").val();
            if(userName == ""){
                $.toaster({ priority : 'danger', title : '新增管理员', message : '名称不能为空!'});
                return false;
            }
            if(password == ""){
                $.toaster({ priority : 'danger', title : '新增管理员', message : '密码不能为空!'});
                return false;
            }
            $.post("/adminLogin/registerAjax",{
                username : userName,
                password : password
            },function(res){
                location.href = "/role/admins";
            });
        });
    });
</script>