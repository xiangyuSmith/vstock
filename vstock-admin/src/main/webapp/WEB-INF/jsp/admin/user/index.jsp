<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户信息</strong> / <small>用户管理</small></div>

        </div>
        <hr>
        <div class="am-g">
            <c:if test="${not empty record}">
                <form action="/user/userindex" method="post">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-u-sm-3 am-u-md-3 am-u-lg-3">
                            <span class="am-text-lg am-text-middle">昵称：</span>
                            <input type="text" name="nick" id="nick" style="max-width: 168px;" class="am-input-lg" placeholder="昵称" value="${record.nick}"/>
                        </div>
                        <div class="am-u-sm-3 am-u-md-3 am-u-lg-3">
                            <span class="am-text-lg am-text-middle">手机号：</span>
                            <input type="text" name="mobile" id="mobile" style="max-width: 150px;" class="am-input-lg" placeholder="手机号" value="${record.mobile}"/>
                        </div>
                        <div class="am-u-sm-3 am-u-md-3 am-u-lg-3">
                            <span class="am-text-lg am-text-middle">尺码：</span>
                            <select class="am-input-lg" name="size" id="size">
                                <option value="0">--请选择--</option>
                                <c:if test="${not empty sizeList}">
                                    <c:forEach items="${sizeList}" var="sizes">
                                        <c:choose>
                                            <c:when test="${record.size == sizes}">
                                                <option value="${sizes}" selected = "selected">${sizes}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${sizes}">${sizes}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                        <div class="am-u-sm-3 am-u-md-3 am-u-lg-3">
                            <span class="am-text-lg am-text-middle">状态：</span>
                            <select class="am-input-lg" name="status" id="status">
                                <option value="-1">--请选择--</option>
                                    <c:choose>
                                        <c:when test="${record.status == 0}">
                                            <option value="0" selected = "selected">禁用</option>
                                            <option value="1">可用</option>
                                        </c:when>
                                        <c:when test="${record.status == 1}">
                                            <option value="1" selected = "selected">可用</option>
                                            <option value="0">禁用</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="0">禁用</option>
                                            <option value="1">可用</option>
                                        </c:otherwise>
                                    </c:choose>
                            </select>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                            <span class="am-text-lg am-text-middle">注册开始时间：</span>
                            <%--<div class="am-form-group">--%>
                            <input type="date" id="startCreateTime" name="startCreateTime" value="${startCreateTime}">
                                <%--<div class="dateStartTime"></div>--%>
                            <%--</div>--%>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                            <span class="am-text-lg am-text-middle">注册结束时间：</span>
                            <%--<div class="am-form-group">--%>
                            <input type="date" id="endCreateTime" name="endCreateTime" value="${endCreateTime}">
                                <%--<div class="dateEndTime"></div>--%>
                            <%--</div>--%>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <button href="javascript: void(0);" type="submit" class="am-btn am-btn-primary am-fr">查询</button>
                    </div>
                </form>
            </c:if>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>昵称</th>
                        <th>手机号</th>
                        <th>尺码</th>
                        <th>资产</th>
                        <th>状态</th>
                        <th>创建时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="insert-data" class="xy-dis"></tr>
                    <c:if test="${not empty userList}">
                        <c:forEach items="${userList}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.nick}</td>
                                <td>${user.mobile}</td>
                                <td>${user.size}</td>
                                <td><fmt:formatNumber value="${user.salt}" pattern="#,##0.0#"/></td>
                                <td>
                                    <c:choose><c:when test="${user.status == 0}">禁用</c:when><c:otherwise>可用</c:otherwise></c:choose>
                                </td>
                                <td>${user.create_time}</td>
                                <td>
                                    <c:if test="${user.status == 1}">
                                        <a href="javascript:void(0);" status="0" data_id="${user.id}" style="color: #ffffff" class="am-btn am-btn-xs am-btn-danger am-radius disable_btn">禁用</a>
                                    </c:if>
                                    <c:if test="${user.status == 0}">
                                        <a href="javascript:void(0);" status="1" data_id="${user.id}" style="color: #ffffff" class="am-btn am-btn-xs am-btn-success am-radius disable_btn">恢复</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
                <jsp:include page="../common/paging.jsp" flush="true">
                    <jsp:param name="page" value="${page}"/>
                    <jsp:param name="linkAddress" value="${linkAddress}"/>
                </jsp:include>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/bottom.jsp" flush="true"/>
<script type="text/javascript">
    jQuery(function($){

//        var div1 = "#sstartCsaledate";
//        var div2 = "#sendCsaledate";
//        getDatePic(div1,div2);

        $('.disable_btn').click(function () {
            var status = $(this).attr("status");
            $.post("/user/saveUser",{
                id: $(this).attr("data_id"),
                status : status
            },function(res){
                if (res.reGode == 1){
                    if (status == 0){
                        alert("成功禁用！");
                    }else {
                        alert("成功恢复！");
                    }
                    setTimeout(function(){window.location.reload();},50);
                }else {
                    alert("操作失败，请重新操作！");
                }
            });
        });

    });
</script>