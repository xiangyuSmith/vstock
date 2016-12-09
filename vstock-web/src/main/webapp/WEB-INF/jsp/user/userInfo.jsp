<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="userInfo" action="${cxt}/index/testUserInfo" method="post">
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-bottom-xl">
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-bottom-sm" style="border-bottom: 1px solid #CACACA;">
            <div style="float: left; display: block;width: 66px;height: 45px; background: url('../../../assets/shoesImg/personal_center.png'); background-position: -380px -20px;"></div>
            <div class="am-margin-bottom-sm"><b class="layout-font-size-30 am-padding-left-sm">个人信息</b><a href="#" class="am-margin-left am-text-danger layout-font-size-20">编辑</a></div>
        </div>
        <div class="am-form-group am-text-sm">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0 am-margin-top-sm">
                <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                    <span class="am-form-label am-margin-left-xl" style="width: 100%">姓名：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span>${user.nick}</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0 am-margin-top-sm">
                <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                    <span class="am-margin-left-xl">尺码：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span>10</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0 am-margin-top-sm">
                <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                    <span class="am-margin-left-xs am-text-right">手机号码：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span>${user.mobile}</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0 am-margin-top-sm">
                <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                    <span class="am-margin-left-xl">邮箱：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span>${user.mobile}</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0 am-margin-top-sm">
                <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                    <span class="am-margin-left">用户名：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span>${user.uname}</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-sm">
                <a href="#" class="am-btn-default am-radius am-btn am-text-xl am-margin-left-sm" style="border: solid 1px #F25C58; background-color: #FFFFFF;">设置登录密码</a>
            </div>
        </div>
    </div>

    <table class="am-table am-table-striped am-table-hover">
        <caption style="border-bottom: 1px solid #CACACA;">
            <div style="float: left; display: block;width: 66px;height: 60px; background: url('../../../assets/shoesImg/personal_center.png'); background-position: -665px -18px;"></div>
            <b class="layout-font-size-30 am-fl am-margin-left-sm">收货信息</b>
            <a href="#" class="am-fl am-text-danger layout-font-size-20 am-margin-left am-margin-top-sm">添加新地址</a>
        </caption>
        <tbody>
        <tr>
            <td>小庞</td>
            <td style="width: 20%">上海/上海市/浦东新区/张江高科科技园区</td>
            <td style="width: 15%">达尔文路88号半岛科技园1号楼</td>
            <td>200000</td>
            <td>15618713257</td>
            <td>
                <a href="#">编辑</a>|<a href="#">删除</a>
            </td>
            <td>
                <a href="#" class="am-btn am-text-danger am-radius am-margin-left-sm" style="border: solid 1px #F25C58; background-color: #FFFFFF;">默认地址</a>
            </td>
        </tr>
        <tr>
            <td>小庞</td>
            <td style="width: 20%">上海/上海市/浦东新区/张江高科科技园区</td>
            <td style="width: 15%">达尔文路88号半岛科技园1号楼</td>
            <td>200000</td>
            <td>15618713257</td>
            <td>
                <a href="#">编辑</a>|<a href="#">删除</a>
            </td>
            <td>
                <a href="#" class="am-btn am-text-danger am-radius am-margin-left-sm" style="border: solid 1px #F25C58; background-color: #FFFFFF;">默认地址</a>
            </td>
        </tr>
        </tbody>
    </table>
</form>