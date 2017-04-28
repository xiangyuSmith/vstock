<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .am-modal-bd{ padding: 10px 10px 15px; }
    .find-pwd input::-webkit-input-placeholder{font-size: 14px;}
    .find-pwd .am-form-field{border:none;border-bottom: 1px solid #ccc;}
    .find-pwd .am-form-field:focus{outline:none;box-shadow: none;border-bottom: 1px solid #ccc;}
</style>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="ali-bind-phone">
    <div class="am-modal-dialog find-pwd">
        <div class="am-modal-hd layout-font-size-26" style="font-weight: bolder;border-bottom: 1px solid #ccc;margin-bottom: 20px;">
            绑定手机号
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-modal-bd am-g">
            <form id="aliBindFrom">
                <div data-tab-panel-0 class="am-tab-panel am-active">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-input-group am-input-group-lg">
                            <input id="ali_mobile_find" name="mobile_find" class="am-form-field" type="text" placeholder="手机号" maxlength="11" style="border-right: 1px solid #ccc;" required/>
                            <span class="am-input-group-btn">
                                <a href="javascript:;" id="ali_sendSms-find" class="am-btn am-btn-default" style="font-size: 14px!important;line-height: 27px;background-color: #fff;border:none;border-bottom: 1px solid #ccc;border-left: 1px solid #ccc;">发送验证码</a>
                            </span>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-input-group am-input-group-lg am-center">
                            <input id="ali_sendSmsCode_find" name="sendSmsCode_find" class="am-form-field" type="text" placeholder="验证码" maxlength="6" required/>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-input-group am-input-group-lg am-center">
                            <input id="ali_password_find" class="am-form-field" name="password_find" type="password" placeholder="新密码" required/>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-xs">
                        <div class="am-input-group am-input-group-lg am-center">
                            <input id="ali_password_find_final" class="am-form-field" name="password_find_final" type="password" placeholder="确认新密码" required/>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-text-left">
                        <span id="ali_tips-tab-find" class="tips-tab-error"><span id="ali_tips-text-find" class="tips-tab-error"></span></span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <input id="ali_prFind" type="submit" class="am-btn am-btn-danger am-btn-sm am-btn-block" value="确定">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        function tiphide(){
            $("#ali_tips-tab-find").css("display","none");
            $("#ali_tips-text-find").text("");
        }
        function tipshow(tips){
            $("#ali_tips-tab-find").css("display","inline-block");
            $("#ali_tips-text-find").text(tips);
        }
        //校验
        jQuery.validator.addMethod("isMobile", function(value, element) {
            var length = value.length;
            var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "请正确填写您的手机号码");
        $("#aliBindFrom").validate({
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
                mobile_find:{
                    minlength : 11,
                    isMobile : true,
                    required: true
                },
                sendSmsCode_find : {
                    required: true
                },
                password_find: {
                    required: true,
                    rangelength: [6, 16]
                },
                password_find_final : {
                    required: true,
                    rangelength: [6, 16],
                    equalTo: "#ali_password_find"
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
                sendRequest("/index/updatePassword",{
                    sendSmsCode: $("#ali_sendSmsCode_find").val(),
                    password: $.md5($("#ali_password_find_final").val())
                },function(res) {
                    if (res.retCode == 1){
                        tiphide();
                        alertTips(0,"提示","密码已重置,页面即将刷新....")
                        window.location.reload();
                    }else{
                        tipshow(res.retMsg);
                        return;
                    }
                });
            },
            invalidHandler: function(form, validator) { }
        })

        $("#ali_sendSms-find").click(function(){
            var $this = $(this);
            var mobile = $("#ali_mobile_find").val();
            var geshi = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
            if(mobile.length != 11 || !geshi.test(mobile)){
                tipshow("手机号格式不正确");
                return;
            }
            if(mobile == ""){
                tipshow("手机号不能为空");
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