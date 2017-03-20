<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .address-list table tr td{ font-size: 14px; }
    .address-list table tr td input{ margin-top: 7px; margin-left: 10px;  }
</style>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="my-popup-buy-detailed">
    <div class="am-modal-dialog pre-bid" style="background-color: #e2e2e2;">
        <input id="bftId" type="hidden" value="${basicinformation.id}" />
        <div class="am-modal-hd" style="background-color: #FF4A4E;">
            <div class="am-active am-g am-padding-bottom-sm" style="color: #FFFFFF;">
                <span class="am-fl am-text-lg">购买清单</span>
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
                            <span class="am-fr layout-font-size-18" style="color: #646464;">
                                卖家最低叫价：
                                <span style="color: #646464;">￥
                                    <span id="seller_detailed_minimumSellingPrice">
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
                    <div id="buyer_detailed_tips_div" style="display: none;" class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-fl am-margin-top-lg am-margin-bottom-sm am-text-left">
                        <span class="wenxin-tips am-margin-right-sm" style="background: url('/assets/i/detail_icon.png');background-position: -406px -22px;"></span>
                        <span id="buyer_detailed_tips" class="bid-tips layout-font-size-16" style="color: #646464;"></span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-fl am-margin-top-sm">
                        <div class="am-u-md-2 am-fl am-padding-0 am-text-left">
                            <span style="color:#FD9192;font-size: 14px;">温馨提示：</span>
                        </div>
                        <div class="am-u-md-10 am-fr am-padding-0 am-text-left">
                            <span style="font-size: 14px;">
                                出价后，请保持手机畅通，当有卖家出售时，我们将短信通知。收到短信后，请务必在24小时内支付订单金额，否则将进行违约处理。
                            </span>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-top-xs am-margin-bottom-sm am-text-left">
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-sm">
                        <p class="layout-font-size-18 am-margin-bottom-sm">卖家最低叫价：</p>
                        <div class="am-form-group am-form-icon">
                            <i class="am-icon-cny layout-font-size-18 am-margin-top-xs" style="margin-top: -7px;color: #585858;"></i>
                            <input id="buyer_detailed_amount_price" type="hidden" value="<fmt:formatNumber value="${pricePeak2.minimumSellingPrice}" type="currency" pattern="#00.00#"/>" readonly/>
                            <input id="buyer_detailed_amount" type="text" class="am-form-field" value="<fmt:formatNumber value="${pricePeak2.minimumSellingPrice}" type="currency" pattern="#,#00.00#"/>" readonly/>
                        </div>
                    </div>
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-sm">
                        <div class="am-u-md-12 am-padding-0">
                            <p class="layout-font-size-18 am-margin-bottom-sm">尺码</p>
                            <%--<input id="buyer_detailed_size" type="text" class="am-form-field" value="${pricePeak2.peakSize}"/>--%>
                            <select id="buyer_detailed_size" class="select-pom" placeholder="请选择" data-am-selected="{btnWidth: 175,maxHeight: 200}">
                                <option value="${pricePeak2.peakSize}">${pricePeak2.peakSize}</option>
                                <c:forEach items="${sizes}" var="s">
                                    <option value="${s}">${s}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-xs">
                        <span class="am-u-md-6 am-padding-0 layout-font-size-18">运费：</span>
                        <span class="am-u-md-6 am-padding-0 layout-font-size-18 am-text-right">￥
                            <span id="yunFee"></span>
                        </span>
                    </div>
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-sm" style="border-bottom: 1px solid #ccc;"></div>
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-sm">
                        <span class="am-u-md-6 am-padding-0 layout-font-size-18">总计：</span>
                        <span class="am-u-md-6 am-padding-0 layout-font-size-18 am-text-right countMoney">-</span>
                    </div>
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-sm">
                        <span class="am-u-md-7 am-padding-0 layout-font-size-18">支付金额：</span>
                        <span class="am-u-md-5 am-padding-0 layout-font-size-20 am-text-right countMoney" style="font-weight: bold;color: #EA5957;">-</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="am-u-md-12" style="background-color: #EEEEEE;margin-top: 1px;">
            <div class="am-text-left address-list am-margin-top-lg">
                <div class="am-text-left am-margin-bottom-xs">
                    <span class="layout-font-size-20" style="color: #8D8D8D;">
                        收货地址 <a href="javascript:;" class="add-adders" data-am-modal="{target: '#adders-id', closeViaDimmer: 0, width: 487, height: 420}"><span style="color:#F98888;"> &nbsp;添加新地址</span></a>
                    </span>
                </div>
                <div id="new-address-div-content" style="max-height: 136px;overflow-y: scroll;">
                <c:choose>
                    <c:when test="${not empty userAddressesList}">
                    <table id="new-address" class="am-table am-table-bordered am-table-centered am-table-striped am-table-hover am-margin-bottom-xs">
                            <c:forEach items="${userAddressesList}" var="userAddresses" varStatus="idx">
                                <tr class="show-tr-address <c:if test="${userAddresses.type == 1}">checked-tr</c:if>" <c:if test="${idx.index>2}">style="display:none;"</c:if>>
                                    <td>
                                        <input id="doc-ipt-o-${userAddresses.id}" type="radio" data-userAddress="${userAddresses.id}" name="check-address" <c:if test="${userAddresses.type == 1}">checked="checked"</c:if> />
                                    </td>
                                    <td>
                                        <label for="doc-ipt-o-${userAddresses.id}" style="font-weight: normal;">
                                                <span class="am-margin-right-xs default-span-tips" style="color:#E77779;<c:if test="${userAddresses.type != 1}">display: none;</c:if>">[默认]</span>
                                                <span class="addressInfo">${userAddresses.localArea} ${userAddresses.detailedAddress}</span>
                                        </label>
                                    </td>
                                    <td><span>${userAddresses.consigneeName}</span></td>
                                    <td><span>
                                        <c:choose>
                                            <c:when test="${not empty userAddresses.phoneNumber}">
                                                ${userAddresses.phoneNumber}
                                            </c:when>
                                            <c:otherwise>
                                                --
                                            </c:otherwise>
                                        </c:choose>
                                        </span>
                                    </td>
                                    <td class="do" style="width: 24%;">
                                        <div style="float:left;margin-right: 20px;">
                                            <a href="javascript:;" class="edit-address" data-userAddress="${userAddresses.id}" data-am-modal="{target: '#adders-id', closeViaDimmer: 0, width: 487, height: 420}">编辑</a>
                                            |
                                            <a href="javascript:;" class="del-address" data-userAddress="${userAddresses.id}" data-type="${userAddresses.type}">删除</a>
                                        </div>
                                        <c:choose>
                                            <c:when test="${userAddresses.type == 0}">
                                                <div><a href="javascript:void(0);" data-userAddress="${userAddresses.id}" class="am-btn-sm am-text-danger set-default-address">设为默认</a></div>
                                            </c:when>
                                            <c:otherwise>
                                                <div><a href="javascript:void(0);" data-userAddress="${userAddresses.id}" style="opacity: 0;" class="am-btn-sm am-text-danger set-default-address">设为默认</a></div>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                    </table>
                    <div>
                        <a id="loading-address" href="javascript:;">其他收货地址</a>
                        <div id="loading-img" class="am-text-center" style="display: none;">
                            <img src="/assets/i/loading.gif" />
                        </div>
                    </div>
                    </c:when>
                    <c:otherwise>
                        <div id="not-address-tips" class="am-text-center">
                            <span style="color:#8D8D8D;">为了方便您进行交易，请先设置收货地址</span>
                        </div>
                    </c:otherwise>
                </c:choose>
                </div>
            </div>
            <div class="am-u-md-8"></div>
            <div class="am-u-md-4 am-text-left">
                <div class="am-padding-sm">
                    <div class="am-text-center am-margin-top-xs">
                        <a href="javascript:;" style="background-color: #FF4A4E;" id="buyer_submit_trade_" class="am-btn am-btn-danger am-btn-lg am-radius  am-btn-block" >提交</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function(){
        var $editAddress = "";
        var countMoney = 0;
        var amount = $("#buyer_detailed_amount_price").val();
        var addressId = $("#new-address").find("tr td input:radio[name='check-address']:checked").attr("data-userAddress");
        if(addressId == undefined){
            $("#yunFee").text("-");
        }else{
            //计算运费
            jisuan(addressId);
        }

        $("body").on("mouseenter",".show-tr-address",function(){
            if(!$(this).hasClass("checked-tr")){
                $(this).find(".set-default-address").css("opacity", "1");
            }
        }).on("mouseleave",".show-tr-address",function(){
            $(".set-default-address").css("opacity", "0");
        });

        $("#new-address").on("click","input:radio[name='check-address']",function(){
            jisuan($(this).attr("data-userAddress"));
        });
        function jisuan(addressId){
            if(addressId != undefined && addressId != ''){
                sendRequest("/trade/getYunfee",{
                    "addressId": addressId
                },function(res){
                    if(res.retCode == 1){
                        $("#yunFee").text(res.data);
                        var yunFee = $.trim($("#yunFee").text());
                        if(yunFee != "-" && yunFee != 0 && yunFee != ""){
                            var m1 = parseFloat(amount);
                            var m2 = parseFloat(yunFee);
                            countMoney = m1 + m2;
                        }
                        $(".countMoney").text(countMoney.toFixed(2));
                    }else{
                        alertTips(2,"提示",res.retMsg);
                    }
                });
            }
        }

        $("#loading-address").click(function(){
            $(this).css("display","none");
            $("#loading-img").fadeIn(200);
            setTimeout(function(){
                $("#loading-img").fadeOut();
                $(".show-tr-address").css("display","table-row");
            },500);
        });

        $("body").on("click",".set-default-address",function(){
            var $this =  $(this);
            sendRequest("/user/saveAdder",{
                 "id":$this.attr("data-userAddress"),
                 "type":1
            },function(res){
                if(res.retCode == 1){
                    $this.parent().parent().siblings().find("input[type='radio']").prop("checked","checked");
                    $(".checked-tr").removeClass("checked-tr");
                    $this.parent().parent().parent().addClass("checked-tr");
                    $(".default-span-tips").css("display","none");
                    $this.parent().parent().siblings().find("span[class='am-margin-right-xs default-span-tips']").css("display","inline-block");
                    $("#new-address tr:eq(0)").before($this.parent().parent().parent());
                    jisuan($this.attr("data-userAddress"));
                    alertTips(1,"地址设置","默认收货地址已更新");
                }
            });
        });

        $("body").on("click",".edit-address",function(){
           $("#up-address-title").text("编辑");
        });

        $("body").on("click",".add-adders",function(){
            $("#adder-id").val("");
            $("#up-address-title").text("添加");
        });

        $("body").on("click",".adder-stn",function(){
            var $this = $(this);
            var phone = "";
            var shopName = $('#shop-name').val();
            var phoneNumber = $('#phone-number').val();
            var areaCode = $('#area-code').val();
            var phoneCode = $('#phone-code').val();
            var extensionCode = $('#extension-code').val();
            if (areaCode){
                phone = phone + areaCode + "-";
            }
            if (phoneCode){
                phone = phone + phoneCode;
            }
            if (extensionCode){
                phone = phone + extensionCode;
            }
            if (phone == "" && phoneNumber == ""){
                alertTips(3,"编辑地址","请填写手机号或电话");
                return;
            }
            sendRequest("/user/saveAdder",{
                localArea : $('#city-name').val(),
                detailedAddress : $('#adder-name').val(),
                consigneeName : shopName,
                phoneNumber : phoneNumber,
                landlineNumber : phone,
                id: $('#adder-id').val()
            },function(res) {
                if (res.retCode == 1){
                    var address = res.obj;
                    if($('#adder-id').val() == "" || $('#adder-id').val() == undefined){
                        var checkTr = "";
                        var phoneNumber = "";
                        if(address.type == 1){
                            checkTr = "checked-tr";
                        }
                        if(address.phoneNumber != undefined){
                            phoneNumber = address.phoneNumber;
                        }else{
                            phoneNumber = "--";
                        }
                        var html = "";
                        if(undefined == $("#new-address tr:eq(0)").attr("class")){
                            html = '<table id="new-address" class="am-table am-table-bordered am-table-centered am-table-striped am-table-hover am-margin-bottom-xs"><tbody id="new-address-tbody">' +
                                    '<tr class="show-tr-address '+checkTr+'">' +
                                    '<td><input id="doc-ipt-o-"+address.id type="radio" name="check-address" data-userAddress="'+address.id+'" checked="checked" /></td><td><label for="doc-ipt-o-"+address.id style="font-weight: normal;"><span class="am-margin-right-xs default-span-tips" style="color:#E77779;">[默认]</span><span class="addressInfo"> '+address.localArea+address.detailedAddress+'</span></label></td>' +
                                    '<td><span> '+address.consigneeName+'</span></td>' +
                                    '<td><span> '+phoneNumber+' </span></td>' +
                                    '<td class="do" style="width: 24%;"><div style="float:left;margin-right: 20px;"><a href="javascript:;" class="edit-address" data-userAddress="'+address.id+'" data-am-modal="{target: \'#adders-id\', closeViaDimmer: 0, width: 487, height: 420}">编辑</a>|<a href="javascript:;" class="del-address" data-userAddress="'+address.id+'" data-type="'+address.type+'">删除</a></div><div><a href="javascript:void(0);" data-userAddress="'+address.id+'" class="am-btn-sm am-text-danger set-default-address">设为默认</a></div></td>' +
                                    '</tr>'+
                                    '</tbody></table>';
                            $("#not-address-tips").css("display","none");
                            $("#new-address-div-content").append(html);
                            jisuan(address.id);
                        }else{
                            html = '<tr class="show-tr-address">' +
                                    '<td><input id="doc-ipt-o-"+address.id type="radio" name="check-address" data-userAddress="'+address.id+'" /></td><td><label for="doc-ipt-o-"+address.id style="font-weight: normal;"><span class="am-margin-right-xs default-span-tips" style="color:#E77779;display: none;">[默认]</span><span class="addressInfo"> '+address.localArea+address.detailedAddress+'</span></label></td>' +
                                    '<td><span class="addressInfo"> '+address.consigneeName+'</span></td>' +
                                    '<td><span> '+phoneNumber+' </span></td>' +
                                    '<td class="do" style="width: 24%;"><div style="float:left;margin-right: 20px;"><a href="javascript:;" class="edit-address" data-userAddress="'+address.id+'" data-am-modal="{target: \'#adders-id\', closeViaDimmer: 0, width: 487, height: 420}">编辑</a>|<a href="javascript:;" class="del-address" data-userAddress="'+address.id+'" data-type="'+address.type+'">删除</a></div><div><a href="javascript:void(0);" data-userAddress="'+address.id+'" class="am-btn-sm am-text-danger set-default-address">设为默认</a></div></td>' +
                                    '</tr>'
                            $("#new-address tr:eq(0)").after(html);
                        }
                        alertTips(1,"","添加地址成功");
                    }else{
                        $editAddress.parent().parent().siblings().eq(1).find("label .addressInfo").text(address.localArea+address.detailedAddress);
                        $editAddress.parent().parent().siblings().eq(2).find("span").text(address.consigneeName);
                        $editAddress.parent().parent().siblings().eq(3).find("span").text(address.phoneNumber);
                        alertTips(1,"","修改地址成功");
                    }
                }else {
                    alertTips(2,"服务器繁忙","请重新操作");
                }
            });
        });

        $("body").on("click",".del-address",function(){
            var $this = $(this);
            var userAddressId = $this.attr("data-userAddress");
            var type = $this.attr('data-type');
            sendRequest("/user/saveAdder",{
                id: userAddressId,
                type: type,
                status : 1
            },function(res) {
                if (res.retCode == 1){
                    alertTips(1,"","删除成功");
                    if(type == 1){
                        $(".show-tr-address ").eq(1).find("input[type='radio']").prop("checked","checked");
                        $(".show-tr-address ").eq(1).addClass("checked-tr");
                        $(".show-tr-address ").eq(1).find("span[class='am-margin-right-xs default-span-tips']").css("display","inline-block");
                        var newAddressId = $(".show-tr-address ").eq(1).find("input[type='radio']").attr("data-userAddress");
                        jisuan(newAddressId);
                    }
                    $this.parent().parent().parent().remove();
                    if($("#new-address tr").length == 0){
                        $("#yunFee").text("-");
                        $(".countMoney").text("-");
                        $("#new-address-div-content").html('<div id="not-address-tips" class="am-text-center"><span style="color:#8D8D8D;">为了方便您进行交易，请先设置收货地址</span></div>');
                    }
                }else {
                    alertTips(2,"服务器繁忙","请重新操作");
                }
            });
        });

        $("body").on("click",".edit-address",function(){
            clearAddress();
            var $this = $(this);
            $editAddress = $this;
            var userAddressId = $this.attr("data-userAddress");
            sendRequest("/user/getAddress",{
                "userAddressId":userAddressId
            },function(res){
                var data = res.data;
                $('#city-name').val(data.localArea);
                $('#adder-name').val(data.detailedAddress);
                $('#shop-name').val(data.consigneeName);
                $('#phone-number').val(data.phoneNumber);
                $("#adder-id").val(data.id);
                if(data.landlineNumber != undefined){
                    if (data.landlineNumber.indexOf("-")>=0){
                        var areaCode = data.landlineNumber.split("-");
                        $('#area-code').val(areaCode[0]);
                        $('#phone-code').val(areaCode[1].substr(0,3));
                        $('#extension-code').val(areaCode[1].substr(4,areaCode[1].length));
                    }
                }
            });
        });

        $("#buyer_detailed_size").change(function(){
            sendRequest("/detail/getPricePeak",{
                "size":$(this).val(),
                "bid":$("#bftId").val()
            },function(res){
                if(res.retCode == 1){
                    if(res.data.pricePeak2 != undefined){
                        $("#seller_detailed_minimumSellingPrice").text(res.data.pricePeak2.minimumSellingPrice);
                        $("#buyer_detailed_amount").val(res.data.pricePeak2.minimumSellingPrice.toFixed(2));
                        $("#buyer_detailed_tips_div").css("display","none");
                        $("#buyer_submit_trade_").removeClass("noFunction");
                    }else{
                        $("#buyer_detailed_tips").text("暂时没有卖家叫价，无法下单购买");
                        $("#buyer_detailed_tips_div").css("display","block");
                        $("#seller_detailed_minimumSellingPrice").text("-");
                        $("#buyer_detailed_amount").val(0);
                        $("#buyer_submit_trade_").addClass("noFunction");
                    }
                }
            });
        });
    });
</script>
