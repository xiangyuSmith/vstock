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
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">店铺URL地址信息</strong> / <small>店铺URL地址信息维护</small></div>

        </div>
        <hr>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-6">
                <div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                        <button type="button" id="add" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>

                        <%--<button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 审核</button>--%>

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
                        <th>店铺名称</th>
                        <th>品牌</th>
                        <th>店铺链接</th>
                        <th>页数</th>
                        <th>链接状态</th>
                        <th>创建时间</th>
                        <th>管理</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="insert-data" class="xy-dis"></tr>
                    <c:if test="${not empty storeList}">
                        <c:forEach items="${storeList}" var="store">
                        <tr>
                            <td>${store.name}</td>
                            <td>${store.brand}</td>
                            <td><a href="${store.url}" class="store_url" target="_blank" title="${store.url}">${fn:substring(store.url, 0, 80)}</a></td>
                            <td>${store.pageNo}</td>
                            <td><span class="am-badge am-badge-success">可用</span></td>
                            <td>${store.create_time}</td>
                            <td>
                                <div class="am-dropdown" data-am-dropdown>
                                    <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                                    <ul class="am-dropdown-content">
                                        <li><a href="#"> 编辑</a></li>
                                        <li><a href="#"> 删除</a></li>
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
                var tr = '<td><input type="text" id="storeName" class="am-input-sm xy-input-set" placeholder="请填写店铺名称"></td><td><input type="text" id="storeBrand" class="am-input-sm xy-input-set" placeholder="品牌"></td><td><input type="text" id="storeUrl" class="am-input-sm xy-input-set" placeholder="请填写店铺链接"></td><td><input type="text" id="storePageNo" class="am-input-sm xy-input-set" placeholder="请填写页数"></td><td><span class="am-badge am-badge-success">可用</span></td><td>——</td><td><button type="button" id="save-data" class="am-btn am-btn-xs am-btn-default"><span class="am-icon-save"></span> 保存</button><button type="button" id="delete-data" class="am-btn am-btn-xs am-btn-default"><span class="am-icon-trash-o"></span> 取消</button></div></td>'
                $('#insert-data').append(tr).fadeIn(200);
            }
        });

        //绑定添加事件
        $("body").on("click","#save-data",function(){
            var storeName = $("#storeName").val();
            var storeUrl = $("#storeUrl").val();
            var storePageNo = $("#storePageNo").val();
            var storeBrand = $("#storeBrand").val();
            $.post("/stockx/stockxStore/insert",{
                'storeName': storeName,
                'storeUrl' : storeUrl,
                'storePageNo' : storePageNo,
                'storeBrand' : storeBrand
            },function(res){
                setTimeout(function(){window.location.reload();},50);
            });
        });

        //绑定删除事件
        $("body").on("click","#delete-data",function(){
            $('#insert-data').html("");
            $('#insert-data').fadeOut(200);
        });
    });
</script>
<!-- content end -->
<jsp:include page="../common/bottom.jsp" flush="true"/>
