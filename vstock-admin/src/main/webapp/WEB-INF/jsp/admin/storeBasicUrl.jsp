<%--
  Created by IntelliJ IDEA.
  User: administor
  Date: 2016/5/11
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="common/top.jsp" flush="true"/>
<%-- 传入参数时用 <jsp:param name="parameterName" value="{parameterValue | EL表达式 }" />--%>

<!-- content start -->
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">店铺URL地址信息</strong> / <small>店铺URL地址信息维护</small></div>
            <div style="float: right"><button id="add">新增</button></div>
            <script>
                $('#add').click(function (){
                    var tr = '<tr>'+
                            '<td><input type="text" id="url_id" name="url_id" style="height: 35px"></td>'+
                            '<td>'+
                            '<select data-am-selected="{searchBox: 2}">'+
                            '<option value="a">Apple</option>'+
                            '<option value="b">Banana</option>'+
                            '<option value="o">Orange</option>'+
                            '<option value="m">Mango</option>'+
                            '<option value="phone">iPhone</option>'+
                            '<option value="im">iMac</option>'+
                            '<option value="mbp">Macbook Pro</option>'+
                            '</select>'+
                            '</td>'+
                            '<td><input type="text" id="url_name" name="url_name" style="height: 35px"></td>'+
                            '<td><input type="text" id="status" name="status" style="height: 35px"></td>'+
                            '<td>'+
                            '<div class="am-dropdown" data-am-dropdown>'+
                            '<button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle style="height: 35px"><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>'+
                            '<ul class="am-dropdown-content">'+
                            '<li><a href="#">1. 编辑</a></li>'+
                            '<li><a href="#">2. 下载</a></li>'+
                            '<li><a href="#">3. 删除</a></li>'+
                            '</ul>'+
                            '</div>'+
                            '</td>'+
                            '</tr>'
                    $('tbody').append(tr);
                });
            </script>
        </div>

        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th>url连接编号</th><th>店铺名</th><th>链接地址</th><th>url地址状态</th><th>管理</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><input type="text" id="url_id" name="url_id" style="height: 35px"></td>
                        <td>
                            <select data-am-selected="{searchBox: 1}">
                                <option value="a">Apple</option>
                                <option value="b">Banana</option>
                                <option value="o">Orange</option>
                                <option value="m">Mango</option>
                                <option value="phone">iPhone</option>
                                <option value="im">iMac</option>
                                <option value="mbp">Macbook Pro</option>
                            </select>
                        </td>
                        <td><input type="text" id="url_name" name="url_name" style="height: 35px"></td>
                        <td><input type="text" id="status" name="status" style="height: 35px"></td>
                        <td>
                            <div class="am-dropdown" data-am-dropdown>
                                <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle style="height: 35px"><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                                <ul class="am-dropdown-content">
                                    <li><a href="#">1. 编辑</a></li>
                                    <li><a href="#">2. 下载</a></li>
                                    <li><a href="#">3. 删除</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- content end -->
<jsp:include page="common/bottom.jsp" flush="true"/>
