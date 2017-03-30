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
                                <label class="am-fl">角色名称:</label>
                                <input type="text" class="tpl-form-input" id="roleName" name="roleName" placeholder="角色名称" required>
                            </div>
                            <div class="am-form-group am-padding">
                                <button type="button" id="insertRoleBtn" class="am-btn am-btn-primary am-radius tpl-btn-bg-color-success ">提交</button>
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
        $("#insertRoleBtn").click(function(){
            var roleName = $("#roleName").val();
            if(roleName == ""){
                $.toaster({ priority : 'danger', title : '新增角色', message : '名称不能为空!'});
                return false;
            }
            $.post("/role/insertRole",{
                roleName : roleName
            },function(res){
                if(res.data == 1){
                    location.href = "/role";
                }else{
                    $.toaster({ priority : 'danger', title : '角色分配', message : '失败,服务器异常!'});
                }
            });
        });
    });
</script>