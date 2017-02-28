<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="userInfo" action="/user/userInfo" method="post">
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-bottom-xl">
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-bottom-sm" style="border-bottom: 1px solid #CACACA;">
            <div style="float: left; display: block;width: 30px;height: 45px; background: url('/assets/i/personal_center_map.png'); background-position: -1611px -20px;"></div>
            <div class="am-margin-bottom-sm"><span class="layout-font-size-26 am-padding-left-sm">个人信息</span></div>
        </div>
        <div class="am-form-group am-text-sm" style="font-weight: normal;">
            <div class="am-u-sm-12 am-u-md-12 am-margin-bottom-sm am-padding-left-0">
                <div class="am-u-sm-2 am-u-md-2">
                    <span class="am-fr">昵称：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span>${user.nick}</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-margin-bottom-sm am-padding-left-0">
                <div class="am-u-sm-2 am-u-md-2">
                    <span class="am-fr">尺码：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span>${user.size}</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-margin-bottom-sm am-padding-left-0">
                <div class="am-u-sm-2 am-u-md-2">
                    <span class="am-fr">手机号码：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span id="mobile_reg">${user.mobile}</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-margin-bottom-sm am-padding-left-0">
                <div class="am-u-sm-2 am-u-md-2">
                    <span class="am-fr">用户名：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:if test="${not empty user}">
                        <span>${user.nick}</span>
                    </c:if>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-margin-bottom-sm am-padding-left-0">
                <div class="am-u-sm-2 am-u-md-2">
                    <span class="am-fr">身份认证：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-margin-left-0 am-padding-left-0 am-u-end">
                    <c:choose>
                        <c:when test="${not empty userAccount}">
                            <c:choose>
                                <c:when test="${userAccount.status == 1}"><span>已认证</span></c:when>
                                <c:otherwise><span><a href="javascript:void(0);" class="identify">去认证</a></span></c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise><span><a href="javascript:void(0);" class="identify">去认证</a></span></c:otherwise>
                    </c:choose>
                    <input id="identify-click" type="hidden" data-am-modal="{target: '#my-popup-identify',width: 644}" />
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-margin-bottom-sm am-padding-left-0">
                <div class="am-u-sm-2 am-u-md-2">
                    <span class="am-fr">登录密码：</span>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-margin-left-0 am-padding-left-0 am-u-end">
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
            <div style="float: left; display: block;width: 25px;height: 50px; background: url('/assets/i/personal_center_map.png'); background-position: -1680px -18px;"></div>
            <span class="layout-font-size-26 am-fl am-margin-left-sm">收货信息</span>
            <span class="am-fr"><a href="javascript:void(0);" class="add-adders am-fl layout-font-size-20 am-margin-left am-margin-top-sm" data-am-modal="{target: '#adders-id', closeViaDimmer: 0, width: 480, height: 420}">添加新地址</a><div style="float: right; display: block;width: 25px;height: 45px; background: url('/assets/i/personal_center_map.png'); background-position: -940px -11px;"></div></span>
        </caption>
        <thead>
        <tr>
            <td>收货人</td>
            <td>所在地区</td>
            <td>详细地址</td>
            <td>邮编</td>
            <td>手机号码</td>
        </tr>
        </thead>
        <tbody>
            <c:if test="${not empty userAddressesList}">
                <c:forEach items="${userAddressesList}" var="userAddresses">
                    <tr class="show-tr-address">
                        <td style="max-width: 80px;">${userAddresses.consigneeName}</td>
                        <td style="max-width: 80px;">${userAddresses.localArea}</td>
                        <td style="max-width: 100px;">${userAddresses.detailedAddress}</td>
                        <td>${userAddresses.zipCode}</td>
                        <td>
                        <c:choose>
                            <c:when test="${not empty userAddresses.phoneNumber}">
                                ${userAddresses.phoneNumber}
                            </c:when>
                            <c:otherwise>
                                <c:when test="${not empty userAddresses.landlineNumber}">
                                    ${userAddresses.landlineNumber}
                                </c:when>
                                <c:otherwise>
                                    --
                                </c:otherwise>
                            </c:otherwise>
                        </c:choose>
                        </td>
                        <td>
                            <a href="javascript:void(0);" user-id="${userAddresses.id}" class="am-fl userInfo-upsbt" data-am-modal="{target: '#adders-id', closeViaDimmer: 0, width: 480, height: 420}">编辑</a>&nbsp;|
                            <a href="javascript:void(0);" user-id="${userAddresses.id}" data-type="${userAddresses.type}" id="del-status">删除</a>
                        </td>
                        <td>
                            <c:if test="${userAddresses.type == 0}">
                                <a href="javascript:void(0);" user-id="${userAddresses.id}" class="up-type am-btn-sm am-text-danger set-default-address">设为默认</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
    <c:if test="${not empty userAddressesList}">
        <div class="am_news_load am_news_load_index" id="load_more" data-url="../user/userInfo?type=1"><span><i class="am-icon-spinner am-icon-spin am-margin-right-sm" style="display: none;"></i>加载更多地址</span></div>
    </c:if>
    <input type="hidden" id="user-lodaType" value="${type}"/>
</form>
<%@include file="../common/address/addersAddorEdit.jsp" %>
<%@include file="../common/popup/bindIdentify.jsp" %>
<script type="text/javascript">
    if ($('#user-lodaType').val() != ""){
        $('#load_more').css('display','none');
    }
    $(function () {
        $('.identify').click(function () {
            $('#identify-click').click();
        })

//        var $this;
//        $(".mark-hover").hover(function(){
//            $this = $(this).find("td[hover-btn='sbt']");
//            alert($this.attr("user-id"));
//            $this.show();
//        },function(){
//            $this.hide();
//        })

        $("body").on("click",".adder-stn",function(){
            var $this = $(this);
            var shopName = $('#shop-name').val();
            var phone = "";
            var phoneNumber = $('#phone-number').val();
            var areaCode = $('#area-code').val();
            var phoneCode = $('#phone-code').val();
            var extensionCode = $('#extension-code').val();
            if (areaCode){
                phone = phone + areaCode + "-";
            }
            if (phoneCode){
                phone = phone + phoneCode;
            }
            if (extensionCode){
                phone = phone + extensionCode;
            }
            if (phone == "" && phoneNumber == ""){
                alertTips(3,"编辑地址","请填写手机号或电话");
                return;
            }
            sendAddress({
                localArea : $('#city-name').val(),
                detailedAddress : $('#adder-name').val(),
                consigneeName : shopName,
                phoneNumber : phoneNumber,
                landlineNumber : phone,
                id: $('#adder-id').val()
            });
        });
        function sendAddress(data){
            sendRequest("/user/saveAdder",data,function(res) {
                if (res.retCode == 1){
                    ajaxContent("/user/userInfo", "", "tradeforex_tilie",1);
                }else {
                    alertTips(2,"服务器繁忙","请重新操作");
                }
            });
        }
    })
</script>