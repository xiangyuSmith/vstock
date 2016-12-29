<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .pre-bid input::-webkit-input-placeholder{ font-size: 14px; }
    .pre-bid .select-pom{width: 90%;height: 37px;border: 1px solid #cdcdcd;background-color: #eee;box-shadow: 0px 2px #999;}
    .bgff{ background-color: #fff; }
    .clickImg-upload{ display: inline-block; position: absolute; margin: 0 auto; margin-top: -60px; margin-left: -28px; color: #fff;}
    .am-gallery-item img{ max-height: 130px; }
    .bg-uoload{ width: 196px;height: 109px;position: absolute;background-color: #000;opacity: 0;}
    .bg-uoload-file{ width: 196px;height: 118px;position: absolute; z-index: 99;}
    .am-gallery-item:HOVER .bg-uoload{ opacity:0.2; }
    .bg-uoload {
        transition: opacity 0.2s linear;
        -moz-transition: opacity 0.2s linear;
        -webkit-transition: opacity 0.2s linear;
        -o-transition: opacity 0.2s linear;
    }
</style>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="my-popup-identify">
    <div class="am-modal-dialog pre-bid" style="background-color: #e2e2e2;">
        <div class="am-modal-hd" style="background-color: #00CD61;padding: 10px 10px 0px;">
            <div class="am-active am-g am-padding-bottom-xs" style="color: #FFFFFF;">
                <span class="am-fl am-text-lg">认证信息</span>
            </div>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-g am-padding-sm am-text-left" style="background-color: #FFF6F4;">
            <span class="layout-font-size-16" style="color: #5d5d5d;">请填写基本信息</span>
        </div>
        <div class="am-modal-bd bgff am-g am-modal-bd bgff am-g am-padding-bottom-0">
            <form class="am-form am-form-horizontal">
                <div class="am-form-group">
                    <span for="doc-ipt-3" class="am-u-sm-3 layout-font-size-18 am-text-right am-margin-top-xs">姓名：</span>
                    <div class="am-u-sm-5 am-u-end am-padding-left-0">
                        <input id="uname" type="text" id="doc-ipt-3" class="input-text-required" placeholder="请输入您的真实姓名">
                    </div>
                </div>
                <div class="am-form-group">
                    <span for="doc-ipt-pwd-2" class="am-u-sm-3 layout-font-size-18 am-text-right am-margin-top-xs">身份证号码：</span>
                    <div class="am-u-sm-5 am-u-end am-padding-left-0">
                        <input id="identifyNo" type="text" id="doc-ipt-pwd-2" class="input-text-required" placeholder="请输入您的身份证号码">
                    </div>
                </div>
            </form>
        </div>
        <div class="am-g am-padding-sm am-text-left" style="background-color: #FFF6F4;">
            <span class="layout-font-size-16" style="color: #5d5d5d;">请上传本人清晰身份证照片</span>
        </div>
        <div class="am-modal-bd bgff am-g">
            <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-3 am-gallery-default" >
                <li>
                    <div class="am-gallery-item">
                        <a href="javascript:;" class="">
                            <div style="width: 100%;height: 100%;">
                                <div class="bg-uoload" ></div>
                                <input id="identify_img_front" name="identify_img_front" type="file" class="bg-uoload-file" style="opacity: 0;" />
                                <img id="identify_front" src="http://s.amazeui.org/media/i/demos/bing-1.jpg"  alt="远方 有一个地方 那里种有我们的梦想" />
                            </div>
                            <span class="clickImg-upload">点击上传</span>
                            <h3 class="am-gallery-title">身份证正面</h3>
                        </a>
                    </div>
                </li>
                <li>
                    <div class="am-gallery-item">
                        <a href="javascript:;" class="">
                            <div style="width: 100%;height: 100%;">
                                <div class="bg-uoload" ></div>
                                <input id="identify_img_back" name="identify_img_back" type="file" class="bg-uoload-file" style="opacity: 0;" />
                                <img id="identify_back" src="http://s.amazeui.org/media/i/demos/bing-2.jpg"  alt="某天 也许会相遇 相遇在这个好地方"/>
                            </div>
                            <span class="clickImg-upload">点击上传</span>
                            <h3 class="am-gallery-title">身份证背面</h3>
                        </a>
                    </div>
                </li>
                <li>
                    <div class="am-gallery-item">
                        <a href="javascript:;" class="">
                            <div style="width: 100%;height: 100%;">
                                <div class="bg-uoload" ></div>
                                <input id="identify_img_handheld" name="identify_img_handheld"  type="file" class="bg-uoload-file" style="opacity: 0;" />
                                <img id="identify_handheld" src="http://s.amazeui.org/media/i/demos/bing-3.jpg"  alt="不要太担心 只因为我相信"/>
                            </div>
                            <span class="clickImg-upload">点击上传</span>
                            <h3 class="am-gallery-title">手持身份证</h3>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
        <div class="am-g am-padding-sm am-text-left" style="background-color: #FFF6F4;">
            <span class="layout-font-size-16" style="color: #5d5d5d;">请输入结算账号</span>
            <span class="layout-font-size-14">(用于结算订单金额)</span>
        </div>
        <div class="am-modal-bd bgff am-g">
            <form class="am-form am-form-horizontal">
                <div class="am-form-group">
                    <span for="doc-ipt-4" class="am-u-sm-3 layout-font-size-18 am-text-right am-margin-top-xs">支付宝账号：</span>
                    <div class="am-u-sm-5 am-u-end am-padding-left-0">
                        <input id="alipayAccount" type="text" id="doc-ipt-4" class="input-text-required" placeholder="请输入您用于结算的支付宝账号">
                    </div>
                </div>
            </form>
        </div>
        <div class="bgff am-g am-padding-bottom-lg">
            <div class="am-u-md-6">
                <button type="button" class="am-btn am-fr" style="width: 150px;" data-am-modal-close>取消</button>
            </div>
            <div class="am-u-md-6">
                <button id="submit_userAccount" type="button" class="am-btn am-fl" style="width: 150px;background-color: #ea5957;color: #fff;" >确定</button>
            </div>
        </div>
    </div>
</div>
<script src="/assets/js/ajaxfileupload.js"></script>
<script>
    $("#identify_img_front").change(function(){
        var objUrl = getObjectURL(this.files[0]) ;
        if (objUrl) {
            $("#identify_front").attr("src", objUrl) ;
            $(".clickImg-upload").eq(0).css("display","none");
        }
    });
    $("#identify_img_back").change(function(){
        var objUrl = getObjectURL(this.files[0]) ;
        if (objUrl) {
            $("#identify_back").attr("src", objUrl) ;
            $(".clickImg-upload").eq(1).css("display","none");
        }
    });
    $("#identify_img_handheld").change(function(){
        var objUrl = getObjectURL(this.files[0]) ;
        if (objUrl) {
            $("#identify_handheld").attr("src", objUrl) ;
            $(".clickImg-upload").eq(2).css("display","none");
        }
    });

    $("#submit_userAccount").click(function(){
        var flag = 0;
        $(".bg-uoload-file").each(function(){
            var $this = $(this);
            if($this.val() == ""){
                flag = 2;
            }
        });
        $(".input-text-required").each(function(){
            var $this = $(this);
            if($this.val() == ""){
                flag = 1;
                $this.css("border","1px solid #ff9335");
            }
        });
        switch(flag){
            case 0:
//                loadingshow("正在提交认证信息...");
//                $.ajaxFileUpload({
//                    url:'/user/uploadUserProfile',
//                    secureuri: false,
//                    data:{
//                        uname:$("#uname").val(),
//                        alipayAccount:$("#alipayAccount").val(),
//                        identifyNo:$("#identifyNo").val()
//                    },
//                    fileElementId:['identify_img_front','identify_img_back','identify_img_handheld'],
//                    dataType: 'json',
//                    success: function (res) {
//                        loadingclose();
//                        alert(res);
//                    },
//                    error: function () {
//                        alertTips(3,"提交失败","远程服务器正忙");
//                    }
//                });
                $("#my-popup-identify").modal('close');
                alertTips(1,"成功","认证信息已提交");
                break;
            case 1:
                alertTips(3,"信息有误","请填写认证信息");
                break;
            case 2:
                alertTips(3,"信息有误","请上传身份证照片");
                break;
            default:
                break;
        }
    });

    $(".input-text-required").focus(function(){
        $(this).css("border","1px solid #ccc");
    });

    //建立一個可存取到該file的url
    function getObjectURL(file) {
        var url = null ;
        if (window.createObjectURL!=undefined) { // basic
            url = window.createObjectURL(file) ;
        } else if (window.URL!=undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file) ;
        } else if (window.webkitURL!=undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
    }
</script>
