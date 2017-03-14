<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    #my-popup-sell-detailed .am-selected-btn{ width: 100%; }
    #my-popup-sell-detailed .am-selected{ width: 100%; }
</style>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="my-popup-sell-detailed">
    <div class="am-modal-dialog pre-bid" style="background-color: #e2e2e2;">
        <input id="bftId" type="hidden" value="${basicinformation.id}" />
        <div class="am-modal-hd" style="background-color: #00CD61;">
            <div class="am-active am-g am-padding-bottom-sm" style="color: #FFFFFF;">
                <span class="am-fl am-text-lg">出售清单</span>
            </div>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-modal-bd am-g" style="background-color: #FFFFFF;">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top">
                <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                    <img src="${configMap._site_url}${basicinformation.smallImgUrl}" style="width: 100%;"/>
                </div>
                <div class="am-u-sm-7 am-u-md-7 am-u-lg-7">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <b class="am-fl" style="color: #333333;">${basicinformation.brand}</b>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <span class="am-fl"><b class="am-fl am-text-left layout-font-size-22"  style="color: #333333;">${basicinformation.name}</b></span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <span class="am-fl am-text-left layout-font-size-18" style="color: #333333;">${basicinformation.chineselogo}</span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-top-sm">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-right-0">
                            <span class="am-fl layout-font-size-18" style="color: #646464;">
                                买家最高出价：
                                <span style="color: #646464;">￥
                                    <span id="buyer_detailed_highestBid">
                                        <c:choose>
                                            <c:when test="${not empty pricePeak1.highestBid}">
                                                <fmt:formatNumber value="${pricePeak1.highestBid}" type="currency" pattern="#,#00.0#"/>
                                            </c:when>
                                            <c:otherwise>
                                                -
                                            </c:otherwise>
                                        </c:choose>
                                    </span>
                                </span>
                            </span>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-left-0" style="border-left:1px solid #646464">
                            <span class="am-fr layout-font-size-18" style="color: #646464;">
                                卖家最低叫价：
                                <span style="color: #646464;">￥
                                    <c:choose>
                                        <c:when test="${not empty pricePeak2.minimumSellingPrice}">
                                            <fmt:formatNumber value="${pricePeak2.minimumSellingPrice}" type="currency" pattern="#,#00.0#"/>
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </span>
                            </span>
                        </div>
                    </div>
                    <div id="seller_detailed_tips_div" style="display: none;" class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-fl am-margin-top-lg am-margin-bottom-sm am-text-left">
                        <span class="wenxin-tips am-margin-right-sm" style="background: url('/assets/i/detail_icon.png');background-position: -406px -22px;"></span>
                        <span id="seller_detailed_tips" class="bid-tips layout-font-size-16" style="color: #646464;"></span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-fl am-margin-top-sm">
                        <div class="am-u-md-2 am-fl am-padding-0 am-text-left">
                            <span style="color:#FD9192;font-size: 14px;">温馨提示：</span>
                        </div>
                        <div class="am-u-md-10 am-fr am-padding-0 am-text-left">
                            <span style="font-size: 14px;">
                                叫价后，请保持手机畅通，当有买家购买时，我们将短信通知您发货至v－stock。发货时产生的运费由卖家自行承担，我们拒收一切到付件，否则造成的损失由卖家承担。
                            </span>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-top-xs am-margin-bottom-sm am-text-left">
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-lg">
                        <p class="layout-font-size-18 am-margin-bottom-sm">买家最高出价：</p>
                        <div class="am-form-group am-form-icon">
                            <i class="am-icon-cny layout-font-size-18 am-margin-top-xs" style="margin-top: -7px;color: #585858;"></i>
                            <input id="seller_detailed_amount" type="text" class="am-form-field" value="<fmt:formatNumber value="${pricePeak1.highestBid}" type="number" pattern="0.00" maxFractionDigits="2" />" readonly/>
                        </div>
                    </div>
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-lg">
                        <div class="am-u-md-12 am-padding-0">
                            <p class="layout-font-size-18 am-margin-bottom-sm">尺码</p>
                            <div>
                                <select id="seller_detailed_size" placeholder="请选择" data-am-selected="{btnWidth: 175,maxHeight: 200}">
                                    <option value="${pricePeak1.peakSize}">${pricePeak1.peakSize}</option>
                                    <c:forEach items="${sizes}" var="s">
                                        <option value="${s}">${s}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-sm">
                        <span class="am-u-md-6 am-padding-0 layout-font-size-18">运费：<span class="question-tips question-tips-sell" data-type="0.0.0.2" style="background: url('/assets/i/detail_icon.png');background-position: -538px -28px"></span></span>
                        <span class="am-u-md-6 am-padding-0 layout-font-size-18 am-text-right">卖家承担</span>
                    </div>
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-sm">
                        <span class="am-u-md-6 am-padding-0 layout-font-size-18">鉴定费：<span class="question-tips question-tips-sell" data-type="0.0.0.3" style="background: url('/assets/i/detail_icon.png');background-position: -538px -28px"></span></span>
                        <span class="am-u-md-6 am-padding-0 layout-font-size-18 am-text-right">免费</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-u-md-12" style="background-color: #fff;margin-top: 1px;">
            <div class="am-u-md-8"></div>
            <div class="am-u-md-4 am-text-left">
                <div class="am-padding-sm">
                    <span class="am-u-md-6 am-padding-0 layout-font-size-16">保证金：<span class="question-tips question-tips-sell" data-type="0.0.0.4" style="background: url('/assets/i/detail_icon.png');background-position: -538px -28px"></span></span><span class="am-u-md-6 am-padding-0 layout-font-size-20 am-text-right">￥10.00</span><br/>
                    <span class="am-u-md-6 am-padding-0 layout-font-size-16" style="height: 38px;line-height: 38px;">支付金额：</span><span class="am-u-md-6 am-padding-0 layout-font-size-22 am-text-right" style="color: #E75C58;font-weight: bold;">10.00元</span><br/>
                    <div class="am-text-center am-margin-top-xl">
                        <a href="javascript:;" style="background-color: #FF4A4E;" id="seller_submit_trade_" class="am-btn am-btn-danger am-btn-lg am-radius  am-btn-block" >提交</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function(){
        $("#seller_detailed_size").change(function(){
            sendRequest("/detail/getPricePeak",{
                "size":$(this).val(),
                "bid":$("#bftId").val()
            },function(res){
                if(res.retCode == 1){
                    if(res.data.pricePeak1 != undefined){
                        $("#buyer_detailed_highestBid").text(res.data.pricePeak1.highestBid);
                        $("#seller_detailed_amount").val(res.data.pricePeak1.highestBid.toFixed(2));
                        $("#seller_detailed_tips_div").css("display","none");
                        $("#seller_submit_trade_").removeClass("noFunction");
                    }else{
                        $("#seller_detailed_tips").text("暂时没有买家出价，无法下单出售");
                        $("#seller_detailed_tips_div").css("display","block");
                        $("#buyer_detailed_highestBid").text("-");
                        $("#seller_detailed_amount").val(0);
                        $("#seller_submit_trade_").addClass("noFunction");
                    }
                }
            });
        });
    });
</script>
