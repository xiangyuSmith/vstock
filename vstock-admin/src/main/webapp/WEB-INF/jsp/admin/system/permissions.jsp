<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <input id="roleId" type="hidden" value="${roleId}">
            <form class="am-form">
                <c:if test="${not empty permissionList}">
                    <c:forEach items="${permissionList}" var="permission">
                        <c:if test="${permission.parentId == 0}">
                            <div class="am-form-group">
                                <label class="am-checkbox-inline">
                                    <input type="checkbox" value="${permission.id}" class="${permission.enName}" name="boxOption" <c:if test="${not empty ps}"><c:forEach items="${ps}" var="p"><c:if test="${permission.id == p}">checked="checked"</c:if></c:forEach></c:if>> ${permission.name}
                                </label>
                            </div>
                        </c:if>
                        <div class="am-form-group am-margin-left-xl">
                            <c:forEach items="${permissionList}" var="pchild">
                                <c:if test="${pchild.parentId == permission.id}">
                                    <label class="am-checkbox-inline">
                                        <input type="checkbox" value="${pchild.id}" name="${permission.enName}_c" class="boxOptionChild" <c:if test="${not empty ps}"><c:forEach items="${ps}" var="p"><c:if test="${pchild.id == p}">checked="checked"</c:if></c:forEach></c:if>> ${pchild.name}
                                    </label>
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:if>
                <input type="button" id="savePermissions" class="am-btn am-btn-sm am-radius am-btn-primary am-margin-right-xl" value="保存" />
                <input type="button" onclick="javascript:history.back(-1);" class="am-btn am-btn-sm am-radius am-btn-danger" value="返回" />
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $("#savePermissions").click(function(){
            var permissionses = "";
            var roleId = $("#roleId").val();
            $("input[type='checkbox']").each(function(){
                var isChecked = $(this).is(":checked");
                if(isChecked){
                    permissionses = permissionses + $(this).val() + ",";
                }
            });
            permissionses = permissionses.substr(0,permissionses.length-1);
            $.post("/role/setPermissions",{
                permissionses : permissionses,
                roleId : roleId
            },function(res){
                if(res.data == 1){
                    $.toaster({ priority : 'success', title : '权限更新', message : '成功!'});
                }else{
                    $.toaster({ priority : 'danger', title : '权限更新', message : '失败,服务器异常!'});
                }
            });
        });
        $("input[name='boxOption']").each(function(){
            var boxOption = $(this).attr("class");
            $("body").on("click","."+boxOption,function(){
                var allchecked = $(this).is(":checked");
                if(allchecked){
                    $("input[name='"+boxOption+"_c']").each(function() {
                        $(this).prop("checked",allchecked);
                    });
                }else{
                    $("input[name='"+boxOption+"_c']").each(function() {
                        $(this).prop("checked",allchecked);
                    });
                }
            });
        });
        $("body").on("click",".boxOptionChild",function(){
            var children = 0;
            var boxOptionChild = $(this).attr("name");
            $("input[name='"+boxOptionChild+"']").each(function(){
                //子级取消的时候若不存在已勾选，则将父级取消勾选
                var childchecked = $(this).is(":checked");
                if(childchecked){
                    children = 1;
                }
            });
            boxOptionChild = boxOptionChild.substr(0,boxOptionChild.length-2);
            if(children == 0){
                $("."+boxOptionChild).prop("checked",false);
            }else{
                var allchecked = $("."+boxOptionChild).is(":checked");
                if(!allchecked){
                    //子级勾选
                    $("."+boxOptionChild).prop("checked",true);
                }
            }
        });
    });
</script>
