<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .pre-login input::-webkit-input-placeholder{font-size: 14px;}
    .pre-login .am-form-field{border:none;border-bottom: 1px solid #ccc;}
    .pre-login .am-form-field:focus{outline:none;box-shadow: none;border-bottom: 1px solid #ccc;}
    .check-size{  font-size: 14px;  }
    .check-size option{  text-align: center;  }
    .tips-tab-error { color: #CC0000; }
    .am-tabs-d2 .am-tabs-nav>.am-active a{ color: #DC4D48; }
    .am-tabs-d2 .am-tabs-nav>.am-active{ border-bottom: 2px solid #DC4D48; }
    .am-tabs-d2 .am-tabs-nav>.am-active:after{ border-bottom-color: #DC4D48; }
</style>
<!--------------------  Type_Login  -------------------->
<div class="am-modal am-modal-no-btn" id="my-popup-login">
    <div class="am-modal-dialog pre-login">
        <div data-am-widget="tabs" class="am-tabs am-tabs-d2 am-margin-0"  data-am-tabs="{noSwipe: 1}">
            <ul class="am-tabs-nav am-cf">
                <li class="am-active" style="background-color: #FFFFFF;"><a href="[data-tab-panel-0]"><p class="layout-font-size-22">登录</p></a></li>
                <li class="" style="background-color: #FFFFFF;"><a href="[data-tab-panel-1]"><p class="layout-font-size-22">注册</p></a></li>
            </ul>
            <div class="am-tabs-bd" style="background-color: #EFEEEC;">
                <!---------- TYPE_LOG ---------->
                <div data-tab-panel-0 class="am-tab-panel am-active am-g">
                    <div class="am-input-group am-input-group-lg am-center am-margin-top-lg am-margin-right-lg am-margin-left-lg">
                        <input id="mobile" class="am-form-field" type="text" placeholder="手机号" required/>
                    </div>
                    <div class="am-input-group am-input-group-lg am-center am-margin-right-lg am-margin-left-lg">
                        <input id="password" class="am-form-field" type="password" placeholder="密码" required/>
                    </div>
                    <div class="am-u-md-12 am-padding-left-0 am-margin-top-sm am-margin-left-lg">
                        <p><a href="javascript:;" class="am-text-left am-fl layout-font-size-14" style="color: #646464;" data-am-modal="{target: '#find-login-pwd', closeViaDimmer: 0, width: 350, height: 380}">忘记密码？</a></p>
                    </div>
                    <div class="am-u-md-12" style="padding: 15px 30px 20px 30px;">
                        <a id="prLogin" href="javascript:void(0)" class="am-btn am-btn-danger am-btn-block" style="height: 35px;line-height: 15px;">登录</a>
                    </div>
                    <div class="am-u-md-12" style="padding: 15px 30px 0px 30px;">
                        <div class="am-panel-group am-margin-bottom-0" id="accordion">
                            <div class="am-panel-default">
                                <div class="">
                                    <span class="am-panel-title" style="color: #646464;" data-am-collapse="{parent: '#accordion', target: '#do-not-say-1'}">——其他登录方式——</span>
                                </div>
                                <div id="do-not-say-1" class="am-panel-collapse am-collapse am-in">
                                    <div class="am-panel-bd am-padding-top-sm am-padding-bottom-0">
                                        <a href="/login/alipay"><p class="am-margin-sm"><img src="/assets/i/alipay.png" style="width: 26px;"></p></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!---------- TYPE_REG ---------->
                <div data-tab-panel-1 class="am-tab-panel am-g">
                    <form id="saveUserForm">
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                            <div class="am-input-group am-input-group-lg">
                                <div class="am-u-md-8 am-padding-0 am-margin-0">
                                    <input id="nick_reg" name="nick_reg" type="text" class="am-form-field" placeholder="昵称" maxlength="12" required/>
                                </div>
                                <div class="am-u-md-4 am-padding-0 am-margin-0">
                                    <span class="am-input-group-btn">
                                            <select id="size_reg" class="am-form-field check-size" style="height: 38px;">
                                              <option value="">选择尺码</option>
                                              <c:forEach items="${sizes}" var="s">
                                                 <option value="${s}">${s}</option>
                                              </c:forEach>
                                            </select>
                                            <i class="am-selected-icon am-icon-caret-down" style="margin-top: 11px;margin-left: -20px;z-index: 2;position: absolute;"></i>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                            <div class="am-input-group am-input-group-lg">
                                <input id="mobile_reg" name="mobile_reg" class="am-form-field" type="text" placeholder="手机号码" maxlength="11" style="border-right: 1px solid #ccc;" required/>
                                <span class="am-input-group-btn">
                                    <a href="javascript:;" id="sendSms" class="am-btn am-btn-default" style="font-size: 14px!important;line-height: 27px;background-color: #fff;border:none;border-bottom: 1px solid #ccc;border-left: 1px solid #ccc;">发送验证码</a>
                                </span>
                            </div>
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                            <div class="am-input-group am-input-group-lg am-center">
                                <input id="sendSmsCode" name="sendSmsCode" class="am-form-field" type="text" placeholder="验证码" maxlength="6" required/>
                            </div>
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                            <div class="am-input-group am-input-group-lg am-center">
                                <input id="password_reg" class="am-form-field" name="password" type="password" placeholder="密码" required/>
                            </div>
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-xs">
                            <div class="am-input-group am-input-group-lg am-center">
                                <input id="password_reg_final" class="am-form-field" name="password_reg_final" type="password" placeholder="再次输入密码" required/>
                            </div>
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-xs am-text-left">
                            <span id="tips-tab" class="tips-tab-error"><span id="tips-text" class="tips-tab-error"></span></span>
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-margin-top-sm">
                            <div class="am-u-sm-1 am-u-md-1 am-u-lg-1 am-fl am-margin-left-0 am-padding-left-0">
                                <input id="agreement" type="checkbox" class="am-padding-top-lg am-fl"/>
                            </div>
                            <div class="am-u-sm-11 am-u-md-11 am-u-lg-11 am-padding-left-0 am-margin-left-0">
                                <span class="am-text-sm am-margin-left-0 am-text-left am-fl"><label for="agreement" style="font-weight: normal;">我已阅读并同意<a href="javascript:;" style="color: #DC4D48;" data-am-modal="{target: '#my-popup-agreement'}">《服务条款》</a>和<a href="javascript:;" style="color: #DC4D48;" data-am-modal="{target: '#my-popup-privacy'}">《隐私权政策》</a></label></span>
                            </div>
                        </div>
                        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                            <input id="prReg" type="submit" class="am-btn am-btn-danger am-btn-sm am-btn-block" value="注册" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../register/agreement.jsp" %>
<%@include file="findPwd.jsp" %>
<script>
    $(function(){
        $(".login-out").click(function(){
            sendRequest("/login/logout",null,function(data){
                location.href = "/index";
            });
        });
        $("#prLogin").click(function(){
            preLogin($("#mobile").val(),$.md5($("#password").val()));
        });
        function preLogin(mobile,pwd){
            sendRequest("/login/prLogin",{
                'mobile': mobile,
                'password':pwd
            },function(data){
                if(data.retCode == 1){
                    //重新加载页面
                    window.location.reload();
                }else{
                    alertTips(2,"登录失败",data.retMsg);
                }
            });
        }
        function tiphide(){
            $("#tips-tab").css("display","none");
            $("#tips-text").text("");
        }
        function tipshow(tips){
            $("#tips-tab").css("display","inline-block");
            $("#tips-text").text(tips);
        }
        //校验
        $("#saveUserForm").validate({
            onsubmit:true,
            errorPlacement : function(error, element) {
                if (element.is(":radio"))
                    error.appendTo(element.parent());
                else if (element.is(":checkbox"))
                    error.appendTo(element.parent().parent());
                else
                    var tips = error.text();
                    tipshow(tips);
            },
            rules: {
                nick_reg : {
                    required: true,
                    maxlength: 12
                },
                mobile_reg:{
                    minlength : 11,
                    isMobile : true,
                    required: true
                },
                sendSmsCode : {
                    required: true
                },
                password: {
                    required: true,
                    rangelength: [6, 16]
                },
                password_reg_final : {
                    required: true,
                    rangelength: [6, 16],
                    equalTo: "#password_reg"
                }
            },
            messages : {
                mobile_find : {
                    required : "手机号不能为空",
                    minlength : "手机号格式不正确",
                    isMobile : "手机号格式不正确"
                }
            },
            submitHandler: function(form) {  //通过之后回调
                var param = $("#saveUserForm").serialize();
                if($("#size_reg").val() == ""){
                    tipshow("请选择尺码");
                    return;
                }
                if(!$("#agreement").is(':checked')){
                    tipshow("请勾选用户使用协议");
                    return;
                }
                tiphide();
                sendRequest("/register/insertUser",{
                    'mobile':$("#mobile_reg").val(),
                    'password':$.md5($("#password_reg").val()),
                    'size':$("#size_reg").val(),
                    'nick':$("#nick_reg").val(),
                    'sendSmsCode' : $("#sendSmsCode").val()
                },function(data){
                    if(data.retCode == 1){
                        alertTips(1,"注册成功",data.retMsg);
                        setTimeout(function(){
                            preLogin($("#mobile_reg").val(),$.md5($("#password_reg").val()));
                        },1000);
                    }else{
                        alertTips(2,"注册失败",data.retMsg);
                    }
                });
            },
            invalidHandler: function(form, validator) { }
        })

        $("#sendSms").click(function(){
            var $this = $(this);
            var mobile = $("#mobile_reg").val();
            var geshi = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            if(mobile.length != 11 || !geshi.test(mobile)){
                tipshow("手机号格式不正确");
                return;
            }
            if(mobile == ""){
                tipshow("请填写手机号");
                return;
            }
            sendsms($this,mobile);
        });


        function sendsms($this,mobile){
            $this.text("正在发送...");
            sendRequest("/login/sendSms",{
                "mobile":mobile
            },function(res){
                if(res.retCode==1){
                    var wait = 60;
                    $this.attr("disabled",true);
                    var int = setInterval(function(){
                        if (wait == 0) {
                            $this.text("重新发送");
                            $this.attr("disabled",false);
                            clearInterval(int);
                        } else {
                            $this.text(wait+"s后重新发送");
                            wait--;
                        }
                    },1000);
                }else{
                    alertTips(2,"提示",res.retMsg);
                }
            })
        }
    });
</script>
