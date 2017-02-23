<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="my-popup-buyer-bid">
    <div class="am-modal-dialog pre-bid" style="background-color: #e2e2e2;">
        <input id="bftId" type="hidden" value="${basicinformation.id}" />
        <div class="am-modal-hd" style="background-color: #FF5A60;">
            <div class="am-active am-g am-padding-bottom-sm" style="color: #FFFFFF;">
                <span class="am-fl am-text-lg">买家出价</span>
            </div>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-modal-bd am-g" style="background-color: #FFFFFF;">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top">
                <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 am-padding-0">
                    <img src="${configMap._site_url}${basicinformation.smallImgUrl}" style="width: 100%;"/>
                </div>
                <div class="am-u-sm-7 am-u-md-7 am-u-lg-7">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <b class="am-fl" style="color: #333333;">${basicinformation.brand}</b>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <span class="am-fl"><b class="am-fl am-text-left layout-font-size-28"  style="color: #333333;">${basicinformation.name}</b></span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <span class="am-fl am-text-left layout-font-size-18" style="color: #333333;">${basicinformation.chineselogo}</span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-top-sm">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-right-0">
                            <span class="am-fl" style="color: #646464;font-size: 16px;">
                                买家最高出价：
                                <span style="color: #646464;">￥
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
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-left-0" style="border-left:1px solid #646464">
                            <span class="am-fr" style="color: #646464;font-size: 16px;">
                                卖家最低叫价：
                                <span style="color: #646464;">￥
                                    <span id="seller_bid_minimumSellingPrice">
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
                            </span>
                        </div>
                    </div>
                    <div id="bid_money_tips_div" style="display: none;" class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-fl am-margin-top-lg am-margin-bottom-sm am-text-left">
                        <span class="wenxin-tips am-margin-right-sm" style="background: url('/assets/i/detail_icon.png');background-position: -406px -22px;"></span>
                        <span id="bid_money_tips" class="bid-tips layout-font-size-16" style="color: #646464;">卖家叫价需大于买家最高出价</span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-fl am-margin-top-sm">
                        <div class="am-u-md-2 am-fl am-padding-0 am-text-left">
                            <span style="color:#FD9192;font-size: 14px;">温馨提示：</span>
                        </div>
                        <div class="am-u-md-10 am-fr am-padding-0 am-text-left">
                            <span style="color: #646464;font-size: 14px;">
                                出价后，请保持手机畅通，当有卖家出售时，我们将短信通知，收到短信后请务必在24小时内支付订单金额，否则将进行违约处理。
                            </span>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-top-xs am-margin-bottom-sm am-text-left">
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-sm">
                        <p class="layout-font-size-18 am-margin-bottom-sm">出价金额：</p>
                        <div class="am-form-group am-form-icon">
                            <i class="am-icon-cny layout-font-size-18 am-margin-top-xs" style="margin-top: -7px;color: #585858;"></i>
                            <input id="buyer_sell_amount" type="text" class="am-form-field" />
                        </div>
                    </div>
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-lg">
                        <div class="am-u-md-6 am-padding-0">
                            <p class="layout-font-size-18 am-margin-bottom-sm">尺码</p>
                            <div>
                                <select id="buyer_sell_size" class="select-pom" placeholder="请选择" data-am-selected="{btnWidth: 80,maxHeight: 200}">
                                    <option value=""></option>
                                    <c:forEach items="${sizes}" var="s">
                                        <option value="${s}">${s}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="am-u-md-6 am-padding-0">
                            <p class="layout-font-size-18 am-margin-bottom-sm" style="padding-left: 8px;">有效期<span class="question-tips question-tips-buy" data-type="0.0.0.1" style="background: url('/assets/i/detail_icon.png');background-position: -535px -28px"></span></p>
                            <div style="padding-left: 8px;">
                                <select id="buyer_sell_time" class="am-input-sm am-form-field select-pom" placeholder="请选择" data-am-selected="{btnWidth: 80}">
                                    <option value="1">1天</option>
                                    <option value="3">3天</option>
                                    <option value="7">7天</option>
                                    <option value="15">15天</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-sm">
                        <span class="am-u-md-6 am-padding-0 layout-font-size-18">运费：<span class="question-tips question-tips-buy" data-type="0.0.0.2" style="background: url('/assets/i/detail_icon.png');background-position: -538px -28px"></span></span>
                        <span class="am-u-md-6 am-padding-0 layout-font-size-18 am-text-right">买家承担</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-u-md-12" style="background-color: #fff;margin-top: 1px;">
            <div class="am-u-md-8"></div>
            <div class="am-u-md-4 am-text-left">
                <div class="am-padding-sm">
                    <span class="am-u-md-6 am-padding-0 layout-font-size-16">保证金：<span class="question-tips question-tips-buy" data-type="0.0.0.4" style="background: url('/assets/i/detail_icon.png');background-position: -538px -28px"></span></span><span class="am-u-md-6 am-padding-0 layout-font-size-20 am-text-right">￥10.00</span><br/>
                    <span class="am-u-md-6 am-padding-0 layout-font-size-16" style="height: 38px;line-height: 38px;">支付金额：</span><span class="am-u-md-6 am-padding-0 layout-font-size-22 am-text-right" style="color: #E75C58;font-weight: bold;">10.00元</span><br/>
                    <div class="am-text-center am-margin-top-xl">
                        <a href="javascript:;" id="buyer_btn_step_final" class="am-btn am-btn-danger am-btn-lg am-radius  am-btn-block" >提交</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function(){
        $("#buyer_sell_size").change(function(){
            sendRequest("/detail/getPricePeak",{
                "size":$(this).val(),
                "bid":$("#bftId").val()
            },function(res){
                if(res.retCode == 1){
                    if(res.data.pricePeak1 != undefined){
                        $("#seller_bid_minimumSellingPrice").text(res.data.pricePeak2.minimumSellingPrice);
                    }else{
                        $("#seller_bid_minimumSellingPrice").text("-");
                    }
                }
            });
        });
        /**
         * 提示文案
         */
        $(".question-tips-buy").each(function(){
            var $this = $(this);
            var $html = "";
            switch ($this.attr("data-type")){
                case "0.0.0.1":
                    $html = "<div class='question-tips-text'><span>超出有效期仍未有卖家出售,叫价将失效</span></div>";
                    break;
                case "0.0.0.2":
                    $html = "<div class='question-tips-text'>" +
                            "<span>由v－stock发给买家的运费</span>" +
                            "</div>";
                    break;
                case "0.0.0.4":
                    $html = "<div class='question-tips-text'><span>1、叫价失效时或者订单成交支付全款后，保证金退回；</span><br/>" +
                            "<span>2、卖家出售但未能在限定时间内支付全款，保证金作为违约金赔偿给卖家。</span></div>";
                    break;
                default:
                    break;
            }
            $this.popover(
                    {
                        trigger:'hover focus',
                        html: true,
                        placement:'auto right',
                        content:$html,
                        animation:true,
                        delay:
                        { show: 300, hide: 100 }
                    });
        });
    });
</script>