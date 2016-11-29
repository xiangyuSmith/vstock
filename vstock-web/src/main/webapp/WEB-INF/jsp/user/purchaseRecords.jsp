<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="saleRecord" action="${cxt}/index/testPurchase" method="post">
    <table class="am-table am-table-striped am-table-hover am-margin-bottom-xl">
        <caption><p class="am-fl am-text-xl am-margin-bottom-0">最近出价</p><a href="#"><p class="am-fr am-margin-bottom-0">更多记录</p></a></caption>
        <thead>
        <tr>
            <th>名称</th>
            <th>尺码</th>
            <th>出价日期</th>
            <th>我的出价</th>
            <th>最高出价</th>
            <th>最低售价</th>
            <th>叫价状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Jorrdan 2 Retro Doembecher Peacock</td>
            <td>10</td>
            <td>2016-6-10</td>
            <td>1100</td>
            <td>605</td>
            <td>120</td>
            <td>已成交</td>
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
        <caption><p class="am-fl am-text-xl am-margin-bottom-0">全部出价记录</p><a href="#"><p class="am-fr am-margin-bottom-0">更多记录</p></a></caption>
        <thead>
        <tr>
            <th>名称</th>
            <th>尺码</th>
            <th>购买日期</th>
            <th>金额</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Jorrdan 2 Retro Doembecher Peacock</td>
            <td>10</td>
            <td>2016-06-10</td>
            <td>1100</td>
            <td>待支付</td>
        </tr>
        </tbody>
    </table>
</form>
<%@include file="../layout/commscript.jsp" %>