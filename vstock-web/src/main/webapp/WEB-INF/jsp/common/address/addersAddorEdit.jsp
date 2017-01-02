<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<script>
    $(function(){
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
    });
</script>