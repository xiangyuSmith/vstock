<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="saleRecord" action="${cxt}/index/testUserInfo" method="post">
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-bottom-xl">
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-bottom-sm" style="border-bottom: 1px solid #030303;">
            <span class="am-text-xl">个人信息</span>
        </div>
        <div class="am-form-group am-text-sm">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-padding-left-0">
                    <label class="am-form-label am-fr">姓名：</label>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-u-end">
                    <span>刘颖婕</span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-padding-left-0">
                    <label class="am-form-label am-fr">尺码：</label>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-u-end">
                    <span>10</span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-padding-left-0">
                    <label class="am-form-label am-fr">手机号码：</label>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-u-end">
                    <span>15685478548</span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-padding-left-0">
                    <label class="am-form-label am-fr">邮箱：</label>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-u-end">
                    <span>258745878@qq.com</span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-padding-left-0">
                    <label class="am-form-label am-fr">用户名：</label>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-u-end">
                    <span>GUI</span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4"><p></p></div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4 am-u-end">
                    <a href="#" class="am-btn-primary am-btn am-text-lg">修改登录密码</a>
                </div>
            </div>
        </div>
    </div>

    <table class="am-table am-table-striped am-table-hover">
        <caption><p class="am-fl am-text-xl am-margin-bottom-0">地址信息</p></caption>
        <thead>
        <tr>
            <th>省份</th>
            <th>城市</th>
            <th>县区</th>
            <th>详细地址</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>上海市</td>
            <td>浦东新区</td>
            <td>张江高科</td>
            <td>半岛科技园</td>
        </tr>
        </tbody>
    </table>
</form>
<%@include file="../layout/commscript.jsp" %>