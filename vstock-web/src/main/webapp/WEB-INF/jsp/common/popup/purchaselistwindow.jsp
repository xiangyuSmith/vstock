<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="my-popup-purchaselistwindow">
    <div class="am-modal-dialog pre-sale">
        <div class="am-modal-hd" style="background-color: #FF5A60;">
            <div class="am-active am-g am-padding-bottom-sm" style="color: #FFFFFF;">
                <span class="am-fl am-text-lg">购买清单</span>
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
                                    <fmt:formatNumber value="${trade.transactionMoney}" type="currency" pattern="#,#00.0#"/>
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
                                <span class="layout-font-size-20">买家最高出价</span>
                            </div>
                            <div class="am-u-md-12">
                                <span class="layout-font-size-20" style="color: #030303">￥
                                    <c:choose>
                                        <c:when test="${not empty pricePeak1.highestBid}">
                                            <fmt:formatNumber value="${pricePeak1.highestBid}" type="currency" pattern="#,#00.0#"/>
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
                                <a href="javascript:;" id="now-buyer-bid" class="am-btn am-btn-success" data-am-modal="{target: '#my-popup-buyer-bid', width: 900, height: 520}">叫价</a>
                            </div>
                        </div>
                    </div>

                    <div class="am-u-sm-6 am-u-md-6 am-u-lg-6" style="border-left: 1px solid #030303">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0">
                                <span class="layout-font-size-20">卖家最低叫价</span>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                                <span class="layout-font-size-20" style="color: #030303">￥
                                    <c:choose>
                                        <c:when test="${not empty pricePeak2.minimumSellingPrice}">
                                            <fmt:formatNumber value="${pricePeak2.minimumSellingPrice}" type="currency" pattern="#,#00.0#"/>
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
                                <a href="javascript:;" id="now-seller-buy" class="am-btn am-btn-danger" data-am-modal="{target: '#my-popup-buy-detailed', width: 900, height: 520}">购买</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
