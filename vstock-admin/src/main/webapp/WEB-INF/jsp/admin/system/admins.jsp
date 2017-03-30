<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">系统成员</strong> / <small>管理员列表</small></div>
        </div>
        <div class="am-g">
            <a href="/role/addAdmins" class="am-btn am-btn-sm am-btn-primary am-fr am-margin-right-lg"><i class="am-icon-plus am-margin-right-xs"></i>新增管理员</a>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>账号</th>
                        <th>角色</th>
                        <th width="15%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty adminList}">
                        <c:forEach items="${adminList}" var="admin">
                            <tr>
                                <td>${admin.id}</td>
                                <td>${admin.username}</td>
                                <td>${admin.roleId}</td>
                                <td>
                                    <div class="am-dropdown" data-am-dropdown>
                                        <button class="am-btn am-btn-xs am-btn-danger am-dropdown-toggle" data-am-dropdown-toggle>分配角色<span class="am-icon-caret-down"></span></button>
                                        <ul class="am-dropdown-content">
                                            <c:if test="${not empty roleList}">
                                                <c:forEach items="${roleList}" var="role">
                                                    <li><a href="javascript:;" class="distributionBtn" data-adminId="${admin.id}" data-roleId="${role.id}">${role.name}</a></li>
                                                </c:forEach>
                                            </c:if>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
                <jsp:include page="../common/paging.jsp" flush="true">
                    <jsp:param name="page" value="${page}"/>
                </jsp:include>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/bottom.jsp" flush="true"/>
<script type="text/javascript">
    $(function(){
        $(".distributionBtn").click(function(){
            var adminId = $(this).attr("data-adminId");
            var roleId = $(this).attr("data-roleId");
            $.post("/role/distributionRole",{
                adminId : adminId,
                roleId : roleId
            },function(res){
                if(res.data == 1){
                    location.reload();
                }else{
                    $.toaster({ priority : 'danger', title : '角色分配', message : '失败,服务器异常!'});
                }
            });
        });
    });
</script>