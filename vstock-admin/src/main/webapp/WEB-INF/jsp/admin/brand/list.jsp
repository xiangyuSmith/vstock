<%--
  Created by IntelliJ IDEA.
  User: administor
  Date: 2016/5/11
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<%-- 传入参数时用 <jsp:param name="parameterName" value="{parameterValue | EL表达式 }" />--%>
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
</style>
<!-- content start -->
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">鞋库档案</strong> / <small>品牌配置</small></div>

        </div>
        <hr>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" id="add" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
                    </div>
                </div>
            </div>

            <div class="am-u-sm-12 am-u-md-3">
                <div class="am-input-group am-input-group-sm">
                    <input id="brandName" type="text" class="am-form-field" placeholder="请输入品牌名称">
                    <input id="brandNameNew" type="hidden" value="${brandName}">
                      <span class="am-input-group-btn">
                        <button class="am-btn am-btn-default" type="button" id="selectAll">搜索</button>
                      </span>
                </div>
            </div>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>品牌名称</th>
                        <th>状态</th>
                        <th>更新时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="insert-data" class="xy-dis"></tr>
                    <c:if test="${not empty brandList}">
                        <c:forEach items="${brandList}" var="brand">
                            <tr>
                                <td class="brandId">${brand.id}</td>
                                <td class="brandNames">${brand.brandName}</td>
                                <td class="statuses">
                                    <c:choose>
                                        <c:when test="${brand.status==1}">
                                            <span class="am-badge am-badge-success">启用</span>
                                        </c:when>
                                        <c:when test="${brand.status==0}">
                                            <span class="am-badge am-badge-danger">暂停</span>
                                        </c:when>
                                        <c:otherwise>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${brand.updateTime}</td>
                                <td>
                                    <div class="am-dropdown" data-am-dropdown>
                                        <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                                        <ul class="am-dropdown-content">
                                            <li><a href="javascript:;" class="editBrand"> 编辑</a></li>
                                            <li><a href="#" class="delete"> 删除</a></li>
                                            <li class="am-disabled"><a href="javascript:;" class="saveBrand">保存</a></li>
                                            <li class="am-disabled"><a href="javascript:;" class="redo">取消</a></li>
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
                    <jsp:param name="linkAddress" value="${linkAddress}"/>
                </jsp:include>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function($){
        $("#add").click(function(){
            if($('#insert-data').css("display")=='none'){
                var tr = '<td>——</td><td><input type="text" id="brandName" class="am-input-sm xy-input-set" placeholder="请填写品牌名称"></td><td><span class="am-badge am-badge-success">启用</span></td><td>——</td><td><button type="button" id="save-data" class="am-btn am-btn-xs am-btn-default"><span class="am-icon-save"></span> 保存</button><button type="button" id="delete-data" class="am-btn am-btn-xs am-btn-default"><span class="am-icon-trash-o"></span> 取消</button></div></td>'
                $('#insert-data').append(tr).fadeIn(200);
            }
        });

        //绑定添加事件
        $("body").on("click","#save-data",function(){
            var brandName = $("#insert-data").find("input").val();
            $.post("/brand/insertBrand",{
                'brandName': brandName
            },function(res){
                if(res.result == 1){
                    setTimeout(function(){window.location.reload();},1);
                }else{
                    $.toaster({ priority : 'error', title : '失败', message : '品牌数据添加失败.'});
                }
            });
        });

        //绑定删除事件
        $("body").on("click","#delete-data",function(){
            $('#insert-data').html("");
            $('#insert-data').fadeOut(200);
        });

        //保存编辑
        $(".saveBrand").click(function(){
            var $this = $(this);
            if($this.parent().attr("class") == undefined || $this.parent().attr("class") == ""){
                var $brandName = $this.parent().parent().parent().parent().siblings("td[class='brandNames']");
                var $brandId = $this.parent().parent().parent().parent().siblings("td[class='brandId']");
                var brandName = $brandName.find("input[type='text']").val();
                var brandId = $brandId.text();
                $.post("/brand/insertBrand",{
                    'brandName': brandName,
                    'brandId': brandId
                },function(res){
                    if(res.result == 1){
                        setTimeout(function(){window.location.reload();},1);
                    }else{
                        $.toaster({ priority : 'error', title : '失败', message : '品牌数据更新失败.'});
                    }
                });
            }
        });

        //删除
        $(".delete").click(function(){
            var $this = $(this);
            if($this.parent().attr("class") == undefined || $this.parent().attr("class") == ""){
                var $brandId = $this.parent().parent().parent().parent().siblings("td[class='brandId']");
                var brandId = $brandId.text();
                $.post("/brand/deleteBrand",{
                    'brandId': brandId
                },function(res){
                    if(res.result == 1){
                        setTimeout(function(){window.location.reload();},1);
                    }else{
                        $.toaster({ priority : 'error', title : '失败', message : '删除品牌失败.'});
                    }
                });
            }
        });

        //编辑品牌
        $(".editBrand").click(function(){
            var $this = $(this);
            if($this.parent().attr("class") == undefined || $this.parent().attr("class") == ""){
                $this.parent().siblings().find("a[class='delete']").parent().addClass("am-disabled").siblings("li[class='am-disabled']").removeClass("am-disabled");
                $this.parent().addClass("am-disabled");
                var $brandName = $this.parent().parent().parent().parent().siblings("td[class='brandNames']");
                var brandName = $brandName.text();
                $brandName.html("<input type='text' data-value='"+brandName+"' value='"+brandName+"' >");
            }
        });

        //取消编辑
        $(".redo").click(function(){
            var $this = $(this);
            $this.parent().addClass("am-disabled").siblings().find("a[class='editBrand']").parent().removeClass("am-disabled");
            $this.parent().siblings().find("a[class='saveBrand']").parent().addClass("am-disabled");
            $this.parent().siblings().find("a[class='delete']").parent().removeClass("am-disabled");
            var $brandName = $this.parent().parent().parent().parent().siblings("td[class='brandNames']");
            var brandName = $brandName.find("input[type='text']").attr("data-value");
            $brandName.html(brandName);
        });

        //搜索查询
        $("#selectAll").click(function () {
            var brand = $('#brandName').val();
            location.href="/brand/brandList?brandName="+brand;
        });

        if ($("#brandNameNew").val()){
            $('#brandName').val($("#brandNameNew").val());
        }

    });
</script>
<!-- content end -->
<jsp:include page="../common/bottom.jsp" flush="true"/>
