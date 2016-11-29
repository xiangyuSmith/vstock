<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="saleRecord" action="${cxt}/index/testSale" method="post">
    <table class="am-table am-table-striped am-table-hover am-margin-bottom-xl">
        <caption><p class="am-fl am-text-xl am-text-danger am-margin-bottom-0">最近叫价</p></caption>
        <thead>
        <tr>
            <th>球鞋名称</th>
            <th>尺码</th>
            <th>叫价日期</th>
            <th>叫价金额</th>
            <th>最近叫价</th>
            <th>叫价状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Jorrdan 2 Retro Doembecher Peacock</td>
            <td>10</td>
            <td>2016/4/20</td>
            <td>8000</td>
            <td>--</td>
            <td>已过期</td>
            <td>
                <div class="am-dropdown" data-am-dropdown>
                    <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle"><span class="am-margin-left-xs"><i class="am-icon-gear"></i></span></a>
                    <ul class="am-dropdown-content">
                        <li><a class="am-btn am-btn-xs am-text-left" href="#"><i class="am-icon-eye am-margin-right-xs"></i>重新叫价</a></li>
                        <li><a class="am-btn am-btn-xs am-text-left" href="#"><i class="am-icon-eye am-margin-right-xs"></i>作废</a></li>
                    </ul>
                </div>
            </td>
        </tr>
        </tbody>
    </table>

    <table class="am-table am-table-striped am-table-hover">
        <caption><p class="am-fl am-text-xl am-margin-bottom-0">出售历史</p></caption>
        <thead>
        <tr>
            <th>球鞋名称</th>
            <th>尺码</th>
            <th>购买日期</th>
            <th>金额</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Jorrdan 2 Retro Doembecher Peacock</td>
            <td>10</td>
            <td>2016/4/20</td>
            <td>8000</td>
        </tr>
        </tbody>
    </table>
</form>