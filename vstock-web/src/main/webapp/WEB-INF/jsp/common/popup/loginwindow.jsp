<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .pre-login input::-webkit-input-placeholder{font-size: 14px;}
    .pre-login .am-form-field{border:none;border-bottom: 1px solid #ccc;}
    .pre-login .am-form-field:focus{outline:none;box-shadow: none;border-bottom: 1px solid #ccc;}
    .check-size{  font-size: 14px;  }
    .check-size option{  text-align: center;  }
</style>
<!--------------------  Type_Login  -------------------->
<div class="am-modal am-modal-no-btn" id="my-popup-login">
    <div class="am-modal-dialog pre-login">
        <div data-am-widget="tabs" class="am-tabs am-tabs-d2 am-margin-0">
            <ul class="am-tabs-nav am-cf">
                <li class="am-active" style="background-color: #FFFFFF;"><a href="[data-tab-panel-0]"><p class="layout-font-size-22">登录</p></a></li>
                <li class="" style="background-color: #FFFFFF;"><a href="[data-tab-panel-1]"><p class="layout-font-size-22">注册</p></a></li>
            </ul>
            <div class="am-tabs-bd" style="background-color: #EFEEEC;">
                <!---------- TYPE_LOG ---------->
                <div data-tab-panel-0 class="am-tab-panel am-active am-g">
                    <div class="am-input-group am-input-group-lg am-center am-margin-top-sm am-margin-right-lg am-margin-left-lg">
                        <input id="mobile" class="am-form-field" type="text" placeholder="用户名" required/>
                    </div>
                    <div class="am-input-group am-input-group-lg am-center am-margin-right-lg am-margin-left-lg">
                        <input id="password" class="am-form-field" type="password" placeholder="密码" required/>
                    </div>
                    <div class="am-u-md-12 am-padding-left-0 am-margin-top-sm am-margin-left-lg">
                        <p><a href="#" class="am-text-left am-fl" style="color: #646464;">忘记密码？</a></p>
                    </div>
                    <div class="am-u-md-12" style="padding: 10px 30px 20px 30px;">
                        <a id="prLogin" href="javascript:void(0)" class="am-btn am-btn-danger am-btn-block" style="height: 35px;line-height: 15px;">登陆</a>
                    </div>
                    <div class="am-u-md-12" style="padding: 10px 30px 0px 30px;">
                        <div class="am-panel-group am-margin-bottom-0" id="accordion">
                            <div class="am-panel-default">
                                <div class="">
                                    <span class="am-panel-title" style="color: #646464;" data-am-collapse="{parent: '#accordion', target: '#do-not-say-3'}">——————其他支付方式——————</span>
                                </div>
                                <div id="do-not-say-3" class="am-panel-collapse am-collapse">
                                    <div class="am-panel-bd am-padding-top-sm am-padding-bottom-0">
                                        <p class="am-margin-sm"><img src="/assets/i/alipay.png" style="width: 26px;"></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!---------- TYPE_REG ---------->
                <div data-tab-panel-1 class="am-tab-panel am-g">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-input-group am-input-group-lg">
                            <div class="am-u-md-8 am-padding-0 am-margin-0">
                                <input id="nick_reg" type="text" class="am-form-field" placeholder="昵称" required/>
                            </div>
                            <div class="am-u-md-4 am-padding-0 am-margin-0">
                                <span class="am-input-group-btn">
                                        <select id="size_reg" class="am-form-field check-size" style="height: 38px;">
                                          <option value="">选择尺码</option>
                                          <option value="41">41</option>
                                          <option value="42">42</option>
                                          <option value="43">43</option>
                                        </select>
                                        <i class="am-selected-icon am-icon-caret-down" style="margin-top: 11px;margin-left: -20px;z-index: 2;position: absolute;"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-input-group am-input-group-lg">
                            <input id="mobile_reg" class="am-form-field" type="text" placeholder="手机号码" style="border-right: 1px solid #ccc;" required/>
                            <span class="am-input-group-btn">
                                <a href="#" class="am-btn am-btn-default" style="font-size: 14px!important;line-height: 27px;background-color: #fff;border:none;border-bottom: 1px solid #ccc;border-left: 1px solid #ccc;">发送验证码</a>
                            </span>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-input-group am-input-group-lg am-center">
                            <input class="am-form-field" type="text" placeholder="验证码" required/>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-input-group am-input-group-lg am-center">
                            <input id="password_reg" class="am-form-field" type="password" placeholder="密码" required/>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-xs">
                        <div class="am-input-group am-input-group-lg am-center">
                            <input id="password_reg_final" class="am-form-field" type="password" placeholder="再次输入密码" required/>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm am-margin-top-sm">
                        <div class="am-u-sm-1 am-u-md-1 am-u-lg-1 am-fl am-margin-left-0 am-padding-left-0">
                            <input id="agreement" type="checkbox" class="am-padding-top-lg am-fl"/>
                        </div>
                        <div class="am-u-sm-11 am-u-md-11 am-u-lg-11 am-padding-left-0 am-margin-left-0">
                            <span class="am-text-sm am-margin-left-0 am-text-left am-fl"><label for="agreement" style="font-weight: normal;">我已阅读并同意《<a href="#" style="color: #646464;">用户使用协议</a>》</label></span>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <a id="prReg" href="javascript:void(0);" class="am-btn am-btn-danger am-btn-sm am-btn-block">注册</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function(){
        $(".login-out").click(function(){
            sendRequest("/login/logout","",function(data){
                window.location.reload();
            });
        });
        $("#prLogin").click(function(){
            sendRequest("/login/prLogin",{
                'mobile':$("#mobile").val(),
                'password':$.md5($("#password").val())
            },function(data){
                if(data.retCode == 1){
                    //重新加载页面
                    window.location.reload();
                }else{
                    alert(data.retMsg);
                }
            });
        });
        $("#prReg").click(function(){
            sendRequest("/register/insertUser",{
                'mobile':$("#mobile_reg").val(),
                'password':$.md5($("#password_reg").val()),
                'size':$("#size_reg").val(),
                'nick':$("#nick_reg").val()
            },function(data){
                if(data.retCode == 1){
                    alert("注册成功");
                }else{
                    alert("注册失败");
                }
            });
        });
    });
</script>
