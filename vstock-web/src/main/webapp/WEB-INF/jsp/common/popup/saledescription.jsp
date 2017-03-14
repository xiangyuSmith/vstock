<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="my-description-sale">
    <div class="am-modal-dialog pre-bid" style="background-color: #e2e2e2;">
        <input id="bftId" type="hidden" value="${basicinformation.id}" />
        <div class="am-modal-hd" style="background-color: #00CD61;">
            <div class="am-active am-g am-padding-bottom-sm" style="color: #FFFFFF;">
                <span class="am-fl am-text-lg am-margin-left-sm">购买流程</span>
            </div>
            <a href="javascript: void(0)" id="my-sale-description" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-modal-bd am-g" style="background-color: #FFFFFF;">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-left-xl" style="width: 811px;height: 312px;border: inherit; background-image: url('/assets/i/saledis.png');"></div>
            <div class="am-u-md-12">
                <div class="am-u-md-12 am-margin-top-xl">
                    <a href="javascript:;" id="sale-close" class="am-btn am-btn-success am-btn-lg am-radius am-btn-block am-center" style="width: 30%;background-color: #00CD61;" >我知道了</a>
                </div>
                <div class="am-u-md-12 am-margin-top-xl">
                    <span class="am-center">温馨提示：如了解更多交易相关的问题，可以打开“常见问题”查询</span>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function(){
    });
</script>