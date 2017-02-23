<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="my-popup-isbidcheck-tips" style="box-shadow: 0 0 1px 1px #ccc;">
    <div class="am-modal-dialog pre-bid" style="background-color: #e2e2e2;">
        <div class="am-modal-hd" style="background-color: #1E89C1;">
            <div class="am-active am-g am-padding-bottom-sm" style="color: #FFFFFF;">
                <span class="am-fl am-text-xl">温馨提示：</span>
            </div>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-modal-bd bgff am-g am-padding-lg">
            <div class="am-u-md-12 am-padding-lg am-margin-top-sm">
                <div class="am-u-md-12 am-padding-left-0 am-padding-right-0">
                    <span class="layout-font-size-20" style="color:#505050;">您当前购买鞋款已有叫价记录,是否选择保留原叫价？</span><br/>
                </div>
                <div class="am-u-md-12 am-margin-top-sm">
                    <span class="layout-font-size-16" style="color:#505050;">点击【是】原叫价记录保留，点击【否】原叫价记录关闭</span>
                </div>
            </div>
            <div class="am-u-md-12 am-padding-bottom-sm">
                <div class="am-u-md-6">
                    <button type="button" class="am-btn am-fr goAuthentication" data-type="1" style="width: 150px;">否</button>
                </div>
                <div class="am-u-md-6">
                    <button type="button" id="isbidcheck-stn" class="am-btn am-fl goAuthentication" data-type="0" style="width: 150px;" data-am-modal-close>是</button>
                </div>
            </div>
        </div>
    </div>
</div>
