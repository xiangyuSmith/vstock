<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<style type="text/css">
    .am-g{
        padding-bottom: 15px;
    }
    .xy-dis{
        display: none;
    }
    .xy-input-set{
        padding-left:5px;
    }
    input[type="text"]{
        width: 150px;
    }
</style>
<!-- content start -->
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">定时任务配置</strong> / <small>定时任务配置</small></div>

        </div>
        <hr>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" id="add" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>

                        <button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 审核</button>

                        <button type="button" id="startD" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 启动定时器</button>
                    </div>
                </div>
            </div>

            <div class="am-u-sm-12 am-u-md-3">
                <div class="am-input-group am-input-group-sm">
                    <input type="text" class="am-form-field">
          <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="button">搜索</button>
          </span>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th>任务描述</th>
                        <th>任务所属组名称</th>
                        <th>任务状态</th>
                        <th>任务运行时间表达式</th>
                        <th>备注</th>
                        <th>创建时间</th>
                        <th>更新时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="insert-data" class="xy-dis"></tr>
                    <c:if test="${not empty customJobList}">
                        <c:forEach items="${customJobList}" var="customJob">
                        <tr>
                            <td>${customJob.jobName}</td>
                            <td>${customJob.jobGroup}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${customJob.jobStatus==1}">
                                        <span class="am-badge am-badge-success">启用</span>
                                    </c:when>
                                    <c:when test="${customJob.jobStatus==0}">
                                        <span class="am-badge am-badge-danger">停止</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="am-badge">已删除</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${customJob.cronExpression}</td>
                            <td>${customJob.memos}</td>
                            <td>${customJob.createTime}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty customJob.upateTime}">
                                        ${customJob.upateTime}
                                    </c:when>
                                    <c:otherwise>
                                        无
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <div class="am-dropdown" data-am-dropdown>
                                    <button id="btns" class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                                    <ul class="am-dropdown-content">
                                        <li><a href="#">1. 启用</a></li>
                                        <li><a href="javascript:;" class="stopTask" data-jobName="${customJob.jobName}" data-jobGroup="${customJob.jobGroup}">2. 暂停</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function($){


        $("#add").click(function(){
            if($('#insert-data').css("display")=='none'){
                var tr = '<td><input type="text" id="jobName" class="am-input-sm xy-input-set" placeholder="任务描述"></td><td><input type="text" id="jobGroup" class="am-input-sm xy-input-set" placeholder="任务所属组名"></td><td><span class="am-badge am-badge-success">启用</span></td><td><input type="text" id="cronExpression" class="am-input-sm xy-input-set" placeholder="时间表达式"></td><td><input type="text" id="memos" class="am-input-sm xy-input-set" placeholder="填写备注"></td><td>/</td><td>/</td><td><button type="button" id="save-data" class="am-btn am-btn-xs am-btn-default"><span class="am-icon-save"></span> 保存</button><button type="button" id="delete-data" class="am-btn am-btn-xs am-btn-default"><span class="am-icon-trash-o"></span> 删除</button></div></td>'
                $('#insert-data').append(tr).fadeIn(200);
            }
        });

        //暂停任务
        $(".stopTask").click(function(){
            var $this = $(this);
            var jobName = $this.attr("data-jobName");
            var jobGroup = $this.attr("data-jobGroup");
            $.post("/quartz/stopTask",{
                'jobName': jobName,
                'jobGroup' : jobGroup
            },function(res){
                if(res.result == 1){
                    $.toaster({ priority : 'info', title : '成功', message : '任务已暂停！'});
                }else{
                    $.toaster({ priority : 'error', title : '失败', message : '任务无法暂停！'});
                }
                $("#btns").click();
            });
        });

        //绑定添加事件
        $("body").on("click","#save-data",function(){
            return;
            var storeName = $("#storeName").val();
            var storeUrl = $("#storeUrl").val();
            $.post("/stockxStore/insert",{
                'storeName': storeName,
                'storeUrl' : storeUrl
            },function(res){
                setTimeout(function(){window.location.reload();},50);
            });
        });

        //绑定删除事件
        $("body").on("click","#delete-data",function(){
            $('#insert-data').html("");
            $('#insert-data').fadeOut(200);
        });

        //启动定时器时间
        $("#startD").click(function () {
            $.post("/quartz/index",{
            },function(res){
                if (res.suc == "1"){
                    alert("启动成功");
                }else{
                    alert("启动失败");
                }
            });
        });
    });
</script>
<!-- content end -->
<jsp:include page="../common/bottom.jsp" flush="true"/>
