<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/inc.jsp" %>
<style type="text/css">
    /*.before {*/
        /*content: '';*/
        /*border: 3px solid #f3f3f3;*/
        /*background-color: #d9d9d9;*/
        /*display: inline-block;*/
        /*width: 5px;*/
        /*height: 5px;*/
        /*border-radius: 5px;*/
        /*margin-left: -6px;*/
        /*margin-right: 10px;*/
    /*}*/
    .line1 {
        position: absolute;
        left: 6.4px;
        width: 0.57em;
        height: 2em;
        border-right: 0.08em solid #d2d2d2;
        top: -3px;
        z-index: 9999;
    }
    .col2 {
        position: relative;
        z-index: 9999;
    }
    .span-sty{
        display: block;
        height: 10px;
    }
</style>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="express-pop">
    <div class="am-modal-dialog pre-sale">
        <div class="am-modal-hd" style="background-color: #D3D0CB;">
            <div class="am-active am-g" style="color: #2A2A2A;">
                <span class="am-text-xl am-fl am-margin-left-lg">物流信息</span>
            </div>
            <a href="javascript: void(0);" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-modal-bd am-g am-padding-left-0 am-padding-right-0" style="background-color: #FFFFFF;">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom-sm">
                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                    <span class="am-text-xl am-fl">快递公司：</span>
                    <span id="expressName-value" class="am-text-xl am-fl"></span>
                </div>
                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                    <span class="am-text-xl am-fl">快递单号：</span>
                    <span id="expressNum-value" class="am-text-xl am-fl"></span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 doc-example am-padding-left-0 am-padding-right-0">
                <pre class="am-pre-scrollable" style="background-color:#FFFFF4;color:#2B292A; min-height: 288px;">
                    <table id="queryResult" class="result-info result-info-down" cellspacing="0">
                        <div>
                            <span  class="am-text-center am-padding-right-xl" style="color:#8D8D8D;">正在加载中...........</span>
                        </div>
                    </table>
                </pre>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom">
                <a href="javascript: void(0);" style="max-width: 140px; background-color:#FF4B49;" class="am-btn am-btn-danger am-radius am-center" data-am-modal-close>关闭</a>
            </div>
        </div>
    </div>
</div>