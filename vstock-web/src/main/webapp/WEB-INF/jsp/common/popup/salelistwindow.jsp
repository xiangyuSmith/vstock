<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .pre-sale input::-webkit-input-placeholder{ font-size: 14px; }
    .pre-sale .circle {  width: 100%; height: 100%; background: #FFFFFF; -moz-border-radius: 50%; -webkit-border-radius: 50%; border-radius: 50%; border:1px solid #ACA8A3; }
    .pre-sale .right_half{ border: 1px solid #030303; border-left: 1px solid #ffffff; border-top-right-radius: 50%; border-bottom-right-radius: 50%; width: 50%; margin-left: -37px; }
    .pre-sale .left_half{ border: 1px solid #030303; border-right: 1px solid #ffffff; border-top-left-radius: 50%; border-bottom-left-radius: 50%; width: 50%; margin-right: -10px; }
    .pre-sale .half_span{ display: block; line-height: 80px; margin-left: -12px; }
</style>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="my-popup-saleList">
    <div class="am-modal-dialog pre-sale">
        <div id="repertoire-title" class="am-modal-hd" style="background-color: #00CD61;">
            <div class="am-active am-g am-padding-bottom-sm" style="color: #FFFFFF;">
                <span class="am-fl am-text-lg">出售清单</span>
            </div>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-modal-bd am-g" style="background-color: #FFFFFF;">
            <div class="am-u-md-12">
                <div class="am-u-md-12">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom-xs">
                        <span><b class="am-text-xl"  style="color: #333333;">${basicinformation.name} ${basicinformation.chineselogo}</b></span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">
                        <img src="${configMap._site_url}${basicinformation.smallImgUrl}" />
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">
                        <span class="am-text-xl" style="color: #646464;">最后成交价</span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                        <span class="am-text-lg" style="color: #646464;">￥
                            <c:choose>
                                <c:when test="${not empty trade}">
                                    <fmt:formatNumber value="${trade.transactionMoney}" type="currency" pattern="#,##0.00#"/>
                                </c:when>
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose>
                        </span>
                        <span class="am-margin-left-xs am-text-lg" style="color: #00D678;">
                            <c:choose>
                                <c:when test="${resParams.roseType == 0}">
                                    -
                                </c:when>
                                <c:otherwise>
                                    +
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${resParams.difference == 0}">
                                    -
                                </c:when>
                                <c:otherwise>
                                    ${resParams.difference}
                                </c:otherwise>
                            </c:choose>
                            (
                            <c:choose>
                                <c:when test="${resParams.percentag == 0}">
                                    -
                                </c:when>
                                <c:otherwise>
                                    ${resParams.percentag}
                                </c:otherwise>
                            </c:choose>
                            %)
                        </span>
                    </div>
                </div>
                <div class="am-u-md-12 am-margin-bottom-sm">
                    <div class="am-u-md-6">
                        <div class="am-u-md-6">
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0">
                                <span class="layout-font-size-20">卖家最低叫价</span>
                            </div>
                            <div class="am-u-md-12">
                                <span class="layout-font-size-20" style="color: #030303">￥
                                    <c:choose>
                                        <c:when test="${not empty pricePeak2.minimumSellingPrice}">
                                            <fmt:formatNumber value="${pricePeak2.minimumSellingPrice}" type="currency" pattern="#,##0.00#"/>
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </span>
                            </div>
                        </div>
                        <div class="am-u-md-6 am-margin-top-xs">
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                <a href="javascript:;" id="now-sell-bid" style="background-color: #00CD61" class="am-btn am-btn-success" data-am-modal="{target: '#my-popup-sell-bid', width: 900, height: 520}">叫价</a>
                            </div>
                        </div>
                    </div>

                    <div class="am-u-sm-6 am-u-md-6 am-u-lg-6" style="border-left: 1px solid #030303">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0">
                                <span class="layout-font-size-20">买家最高出价</span>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                <span class="layout-font-size-20" style="color: #030303">￥
                                    <c:choose>
                                        <c:when test="${not empty pricePeak1.highestBid}">
                                            <fmt:formatNumber value="${pricePeak1.highestBid}" type="currency" pattern="#,##0.00#"/>
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </span>
                            </div>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-top-xs">
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                <c:choose>
                                    <c:when test="${not empty pricePeak1.highestBid}">
                                        <a href="javascript:;" id="now-buyer-sell" class="am-btn am-btn-danger" style="background-color: #FF4A4E;" data-am-modal="{target: '#my-popup-sell-detailed', width: 900, height: 520}">出售</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="javascript:;" class="am-btn am-btn-danger" style="background-color: #FF4A4E;" disabled="disabled">出售</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
