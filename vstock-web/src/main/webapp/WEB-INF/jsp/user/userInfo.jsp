<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="../../../assets/css/address/address.css" />
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
                        <span style="font-weight: bolder;" id="mobile_reg">${user.mobile}</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-padding-left-0 am-margin-top-sm">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 userInfo-div">
                    <span class="am-margin-left-xl userInfo-div">邮箱：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span style="font-weight: bolder;"></span>
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
                    <a href="javascript:void(0);" data-am-modal="{target: '#login-pas', closeViaDimmer: 0, width: 400, height: 360}">
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
                                        <span>需要填写您手机号码${user.mobile}收到的验证码</span>
                                    </div>
                                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-input-group">
                                        <input class="am-form-field" style="min-height: 50px; border-bottom: solid 0px;" id="verification" type="text" placeholder="验证码" required/>
                                        <span class="am-input-group-label"><a href="javascript:void(0);" id="sendSms">发送验证码</a></span>
                                    </div>
                                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-input-group">
                                        <input class="am-form-field" style="min-height: 50px; border-bottom: solid 0px;" id="passOne" type="password" placeholder="请输入新密码" required/>
                                    </div>
                                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-input-group am-margin-bottom-sm">
                                        <input class="am-form-field" style="min-height: 50px;" type="password" id="passTow" placeholder="请再次输入新密码" required/>
                                    </div>
                                    <span id="prompt" disabled="none" class="am-text-danger"></span>
                                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-input-group am-margin-bottom">
                                        <a href="javascript:void(0);" id="userpass-sbt" class="am-btn am-btn-danger am-center">确定</a>
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
            <a href="javascript:void(0);" id="add-adders" class="am-fl am-text-danger layout-font-size-20 am-margin-left am-margin-top-sm" data-am-modal="{target: '#adders-id', closeViaDimmer: 0, width: 450, height: 420}">添加新地址</a>

            <div class="am-modal am-modal-no-btn" tabindex="-1" id="adders-id">
                <div class="am-modal-dialog">
                    <div class="am-modal-hd layout-font-size-30" style="font-weight: bolder; border-bottom: 1px solid #CACACA;">
                        新增收货地址
                        <%--<a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>--%>
                    </div>
                    <div class="am-modal-bd am-g">
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top">
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom" id="aa">
                                <span class="am-text-default am-fl am-margin-left-sm" style="font-weight: bolder; line-height: 24px;">所在地区:</span>
                                <input class="am-fr am-margin-right-xl am-padding-left-xs" id="city-name" style="min-height: 30px; min-width: 260px;" placeholder="请选择省市区县" required="required"/></div>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                                <span class="am-text-default am-fl am-margin-left-lg" style="font-weight: bolder; line-height: 30px;">详细地址:</span>
                                <textarea class="am-fr am-margin-right-lg am-padding-left-xs" id="adder-name" style="min-height: 60px; min-width: 287px;" placeholder="建议您如实详细填写收货地址" required="required"/>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                                <span class="am-text-default am-fl am-margin-left-sm" style="font-weight: bolder; line-height: 30px;">收货人姓名:</span>
                                <input class="am-fr am-margin-right-lg am-padding-left-xs" id="shop-name" style="min-height: 30px; min-width: 287px;" placeholder="长度不要超过20个字符" required="required"/>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                                <span class="am-text-default am-fl am-margin-left-lg" style="font-weight: bolder; line-height: 30px;">手机号码:</span>
                                <input class="am-fl am-margin-left am-padding-left-xs" type="text" placeholder="中国大陆 +86" style="min-height: 30px; max-width: 100px;"/>
                                <input class="am-fr am-margin-right-lg am-padding-left-xs" id="phone-number" style="min-height: 30px; max-width: 180px;" placeholder="电话号码，手机号码填一下"/>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-lg">
                                <span class="am-text-default am-fl am-margin-left-lg" style="font-weight: bolder; line-height: 30px;">电话号码:</span>
                                <input class="am-fl am-margin-left am-padding-left-xs" type="text" placeholder="中国大陆 +86" style="min-height: 30px; max-width: 100px;"/>
                                <div class="am-margin-right">
                                    <input class="am-padding-left-xs" id="area-code" type="text" style="min-height: 30px; max-width: 50px;" placeholder="区号"/>-
                                    <input class="am-padding-left-xs" id="phone-code" type="text" style="min-height: 30px; max-width: 55px;" placeholder="电话号码"/>-
                                    <input class="am-padding-left-xs" id="extension-code" type="text" style="min-height: 30px; max-width: 55px;" placeholder="分机"/>
                                </div>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6"><a href="javascript: void(0);" class="am-btn am-btn-primary am-fr adder-stn">确定</a></div>
                                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6"><a href="javascript: void(0);" class="am-btn am-btn-primary am-fl adder-quit" data-am-modal-close>取消</a></div>
                            </div>
                        <input type="hidden" id="adder-id"/>
                        </div>
                    </div>
                </div>
            </div>
        </caption>
        <tbody>
            <c:if test="${not empty userAddressesList}">
                <c:forEach items="${userAddressesList}" var="userAddresses">
                    <tr>
                        <td>${userAddresses.consigneeName}</td>
                        <td style="max-width: 80px;">${userAddresses.localArea}</td>
                        <td style="max-width: 100px;">${userAddresses.detailedAddress}</td>
                        <td>${userAddresses.zipCode}</td>
                        <c:choose>
                            <c:when test="${not empty userAddresses.phoneNumber}">
                                <td>${userAddresses.phoneNumber}</td>
                            </c:when>
                            <c:otherwise>
                                <td>--</td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${not empty userAddresses.landlineNumber}">
                                <td>${userAddresses.landlineNumber}</td>
                            </c:when>
                            <c:otherwise>
                                <td>--</td>
                            </c:otherwise>
                        </c:choose>
                        <td style="width: 50px;">
                            <a href="javascript:void(0);" user-id="${userAddresses.id}" class="userInfo-upsbt" data-am-modal="{target: '#adders-id', closeViaDimmer: 0, width: 450, height: 420}">编辑</a>
                            <a href="javascript:void(0);" user-id="${userAddresses.id}" id="del-status">删除</a>
                        </td>
                        <td>
                            <c:if test="${userAddresses.type == 0}">
                                <a href="javascript:void(0);" user-id="${userAddresses.id}" id="up-type" class="am-btn am-text-danger am-radius am-margin-left-sm" style="border: solid 1px #F25C58; background-color: #FFFFFF;">设为默认地址</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
    <a href="javascript:void(0);" class="am-center" id="load_more" data-url="../user/userInfo?type=1"><span class="am-center am-text-center layout-font-size-18"><i class="am-icon-spinner am-icon-spin" style="display: none;"></i>点击加载更多</span></a>
    <input type="hidden" id="user-lodaType" value="${type}"/>
</form>
<script type="text/javascript" src="../../../../assets/js/address/jquery.address.min.js"></script>
<script type="text/javascript">

    if ($('#user-lodaType').val() != ""){
        $('#load_more').css('display','none');
    }

    sendRequest("/user/address",null,function(res){
        var c = eval('('+res+')');

        $("#aa").address(c,function () {});
        $("#aa").Address({
            callback:function(infos,selected_ids) {
                var str = '';
                for(var i=0;i<infos.length;i++) {
                    str = str+infos[i];
                }
                $('#aa input').val(str);
            }
        });
    });
</script>