<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="userInfo" action="${cxt}/user/userInfo" method="post">
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-bottom-xl">
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-bottom-sm" style="border-bottom: 1px solid #CACACA;">
            <div style="float: left; display: block;width: 66px;height: 45px; background: url('../../../assets/shoesImg/personal_center.png'); background-position: -380px -20px;"></div>
            <div class="am-margin-bottom-sm"><b class="layout-font-size-30 am-padding-left-sm">个人信息</b></div>
        </div>
        <div class="am-form-group am-text-sm" style="font-weight: normal;">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0 am-margin-top-sm">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 userInfo-div">
                    <span class="am-form-label am-margin-left-xl" style="width: 100%">姓名：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span style="font-weight: bolder;">${user.nick}</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0 am-margin-top-sm">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 userInfo-div">
                    <span class="am-margin-left-xl">尺码：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span style="font-weight: bolder;">10</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0 am-margin-top-sm">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 userInfo-div">
                    <span class="am-margin-left-xs am-text-right">手机号码：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span style="font-weight: bolder;">${user.mobile}</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0 am-margin-top-sm">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 userInfo-div">
                    <span class="am-margin-left-xl userInfo-div">邮箱：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span style="font-weight: bolder;">${user.mobile}</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0 am-margin-top-sm">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 userInfo-div">
                    <span class="am-margin-left userInfo-div">用户名：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span style="font-weight: bolder;">${user.nick}</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0 am-margin-top-sm">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 userInfo-div">
                    <span class="am-margin-left-xs userInfo-div">登录密码：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <a href="javascript:void(0);" data-am-modal="{target: '#login-pas', closeViaDimmer: 0, width: 400, height: 380}">
                        修改密码
                    </a>

                <%--<a href="javascript:void(0);" data-am-modal="{target: '#login-pas', closeViaDimmer: 0, width: 400, height: 300}">设置登录密码</a>--%>
                    <div class="am-modal am-modal-no-btn" tabindex="-1" id="login-pas">
                        <div class="am-modal-dialog">
                            <div class="am-modal-hd layout-font-size-30" style="font-weight: bolder;">
                                修改登录密码
                                <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>
                            </div>
                            <div class="am-modal-bd am-g">
                                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom-sm">
                                        <span>需要填写您手机号码138***8890收到的验证码</span>
                                    </div>
                                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-input-group">
                                        <input class="am-form-field" style="min-height: 50px; border-bottom: solid 0px;" type="text" placeholder="验证码" required/>
                                        <span class="am-input-group-label"><a href="#">发送验证码</a></span>
                                    </div>
                                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-input-group">
                                        <input class="am-form-field" style="min-height: 50px; border-bottom: solid 0px;" type="text" placeholder="请输入新密码" required/>
                                    </div>
                                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-input-group am-margin-bottom-sm">
                                        <input class="am-form-field" style="min-height: 50px;" type="text" placeholder="请再次输入新密码" required/>
                                    </div>
                                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-input-group am-margin-bottom">
                                        <a href="javascript:void(0);" class="am-btn am-btn-danger am-center">确定</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%--<div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-sm">--%>
                <%--<button type="button" class="am-btn-default am-radius am-btn am-text-xl am-margin-left-sm" style="border: solid 1px #F25C58; background-color: #FFFFFF;" data-am-modal="{target: '#login-pas', closeViaDimmer: 0, width: 400, height: 300}">设置登录密码</button>--%>
            <%--</div>--%>
        </div>
    </div>

    <table class="am-table am-table-striped am-table-hover">
        <caption style="border-bottom: 1px solid #CACACA;">
            <div style="float: left; display: block;width: 66px;height: 60px; background: url('../../../assets/shoesImg/personal_center.png'); background-position: -665px -18px;"></div>
            <b class="layout-font-size-30 am-fl am-margin-left-sm">收货信息</b>
            <a href="javascript:void(0);" class="am-fl am-text-danger layout-font-size-20 am-margin-left am-margin-top-sm" data-am-modal="{target: '#adders-id', closeViaDimmer: 0, width: 400, height: 350}">添加新地址</a>

            <div class="am-modal am-modal-no-btn" tabindex="-1" id="adders-id">
                <div class="am-modal-dialog">
                    <div class="am-modal-hd layout-font-size-30" style="font-weight: bolder;">
                        新增收货地址
                        <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>
                    </div>
                    <div class="am-modal-bd am-g">
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-input-group" id="aa">
                                <span class="am-input-group-label am-text-lg">所在地区:</span>
                                <input class="am-form-field" style="min-height: 30px;" placeholder="请选择省市区县" required/>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-input-group">
                                <span class="am-input-group-label am-text-lg">详细地址:</span>
                                <input class="am-form-field" style="min-height: 60px;" placeholder="建议您如实详细填写收货地址" required/>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-input-group">
                                <span class="am-input-group-label am-text-lg">收货人姓名:</span>
                                <input class="am-form-field" style="min-height: 30px;" required/>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-input-group">
                                <span class="am-input-group-label am-text-lg">手机号码:</span>
                                <input class="am-form-field" style="min-height: 30px;" required/>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-input-group">
                                <span class="am-input-group-label am-text-lg">电话号码:</span>
                                <input class="am-form-field" style="min-height: 30px;" required/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </caption>
        <tbody>
            <c:if test="${not empty userAddressesList}">
                <c:forEach items="${userAddressesList}" var="userAddresses">
                    <tr>
                        <td>${userAddresses.uname}</td>
                        <td style="width: 20%">${userAddresses.localArea}</td>
                        <td style="width: 15%">${userAddresses.detailedAddress}</td>
                        <td>${userAddresses.zipCode}</td>
                        <td>${userAddresses.phoneNumber}</td>
                        <td>
                            <a href="#">编辑</a>|<a href="#">删除</a>
                        </td>
                        <td>
                            <c:if test="${userAddresses.type == 0}">
                                <a href="#" class="am-btn am-text-danger am-radius am-margin-left-sm" style="border: solid 1px #F25C58; background-color: #FFFFFF;">设为默认地址</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</form>