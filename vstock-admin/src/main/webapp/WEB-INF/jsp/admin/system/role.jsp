<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">角色管理</strong> / <small>角色列表</small></div>
        </div>
        <div class="am-g">
            <a href="/role/addRole" class="am-btn am-btn-sm am-btn-primary am-fr am-margin-right-lg"><i class="am-icon-plus am-margin-right-xs"></i>新增角色</a>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>名称</th>
                        <th>状态</th>
                        <th width="15%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${not empty roleList}">
                        <c:forEach items="${roleList}" var="role">
                            <tr>
                                <td>${role.id}</td>
                                <td>${role.name}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${role.status == 10}">
                                            启用
                                        </c:when>
                                        <c:otherwise>
                                            禁用
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="/role/searchPermissions?roleId=${role.id}">
                                        <span class="am-btn am-btn-xs am-btn-danger"><i class="am-icon-search am-margin-right-xs"></i>查看权限</span>
                                    </a>
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

    });
</script>