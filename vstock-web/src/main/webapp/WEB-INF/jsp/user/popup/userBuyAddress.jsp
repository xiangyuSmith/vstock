<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .address-list table tr td{ font-size: 14px; }
    .address-list table tr td input{ margin-top: 7px; margin-left: 10px;  }
    .set-default-address{ border: solid 1px #F25C58;background-color: #FFFFFF;padding: 5px 12px;border-radius: 5px; }
    .set-default-address:hover{ color:#fff;  background-color: #F25C58!important; }
</style>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="my-popup-buy-userBuyAddress" style="top: 200px;">
    <div class="am-modal-dialog pre-bid" style="background-color: #e2e2e2;">
        <div class="am-modal-hd" style="background-color: #FF5A60;">
            <div class="am-active am-g am-padding-bottom-sm" style="color: #FFFFFF;">
                <span class="am-fl am-text-lg">购买清单</span>
            </div>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <input type="hidden" value="${configMap._site_url}" id="site_url" />
        <div class="am-modal-bd am-g" style="background-color: #FFFFFF;">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top">
                <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                    <img id="buyProductImgId" src="" style="width: 100%;"/>
                </div>
                <div class="am-u-sm-7 am-u-md-7 am-u-lg-7">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <b class="am-fl basicinformationBrand" style="color: #333333;"></b>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <span class="am-fl"><b class="am-fl am-text-left layout-font-size-22 basicinformationName"  style="color: #333333;"></b></span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <span class="am-fl am-text-left layout-font-size-18 basicinformationChineselogo" style="color: #333333;"></span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-margin-top-sm">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-right-0">
                            <span class="am-fl layout-font-size-18" style="color: #646464;">
                                买家最高出价：
                                <span style="color: #646464;">￥
                                    <span class="pricePeak1HighestBid"></span>
                                </span>
                            </span>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-left-0" style="border-left:1px solid #646464">
                            <span class="am-fr layout-font-size-18" style="color: #646464;">
                                卖家最低叫价：
                                <span style="color: #646464;">￥
                                    <span class="pricePeak2MinimumSellingPrice"></span>
                                </span>
                            </span>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-fl am-margin-top-sm am-text-left">
                        <span>卖家出价需大于买家最高叫价</span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-fl am-margin-top-sm">
                        <div class="am-u-md-2 am-fl am-padding-0 am-text-left">
                            <span style="color:#FD9192;font-size: 14px;">温馨提示：</span>
                        </div>
                        <div class="am-u-md-10 am-fr am-padding-0 am-text-left">
                            <span style="font-size: 14px;">
                                叫价后，请保持手机畅通，当有买家购买时，我们将短信通知您发货至v－stock。发货时运费由卖家承担，我们拒收一切到付件，否则造成的损失由卖家承担。
                            </span>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-margin-top-xs am-margin-bottom-sm am-text-left">
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-sm">
                        <p class="layout-font-size-18 am-margin-bottom-sm">金额：</p>
                        <div class="am-form-group am-form-icon">
                            <i class="am-icon-cny layout-font-size-18 am-margin-top-xs" style="margin-top: -7px;color: #585858;"></i>
                            <input id="buyer_detailed_amount" type="text" class="am-form-field" readonly/>
                        </div>
                    </div>
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-sm">
                        <div class="am-u-md-12 am-padding-0">
                            <p class="layout-font-size-18 am-margin-bottom-sm">尺码</p>
                            <input id="buyer_detailed_size" type="text" class="am-form-field" readonly/>
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
                        <span class="am-u-md-6 am-padding-0 layout-font-size-18 am-text-right countMoney"></span>
                    </div>
                    <div class="am-u-md-12 am-padding-0 am-margin-bottom-sm">
                        <span class="am-u-md-7 am-padding-0 layout-font-size-18">支付金额：</span>
                        <span class="am-u-md-5 am-padding-0 layout-font-size-20 am-text-right countMoney" style="font-weight: bold;color: #EA5957;"></span>
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
                <div style=" height: 136px;overflow-y: scroll;">
                    <table id="new-address" class="am-table am-table-bordered am-table-centered am-table-striped am-table-hover am-margin-bottom-xs">
                        <tbody id="new-address-tbody">

                        </tbody>
                    </table>
                </div>
                <div>
                    <a id="loading-address" href="javascript:;">其他收货地址</a>
                    <div id="loading-img" class="am-text-center" style="display: none;">
                        <img src="/assets/i/loading.gif" />
                    </div>
                </div>
            </div>
            <div class="am-u-md-8"></div>
            <div class="am-u-md-4 am-text-left">
                <div class="am-padding-sm">
                    <div class="am-text-center am-margin-top-xs">
                        <a href="javascript:;" id="buyer_submit_trade_S" class="am-btn am-btn-danger am-btn-lg am-radius  am-btn-block" >提交</a>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="../../common/address/addersAddorEdit_1.jsp" %>
<script>
    $(function(){
        var $editAddress = "";
        var countMoney = 0;
        var amount = $("#buyer_detailed_amount").val();
        var addressId = $("#new-address").find("tr td input:radio[name='check-address']:checked").attr("data-userAddress");
        if(addressId == undefined){
            $("#yunFee").text("-");
        }else{
            //计算运费
            jisuan(addressId);
        }
        $("#new-address").find("tr td input:radio[name='check-address']").click(function(){
            jisuan($(this).attr("data-userAddress"));
        });
        function jisuan(addressId){
            sendRequest("/trade/getYunfee",{
                "addressId": addressId
            },function(res){
                if(res.retCode == 1){
                    $("#yunFee").text(res.data);
                    var yunFee = $.trim($("#yunFee").text());
                    if(yunFee != "-" && yunFee != 0 && yunFee != ""){
                        countMoney = parseFloat(yunFee) + parseFloat(amount);
                    }
                    $(".countMoney").text(countMoney.toFixed(2));
                }else{
                    alertTips(2,"提示",res.retMsg);
                }
            });
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
                    $(".edit-address").css("display","none").siblings().css("display","inline-block");
                    $this.parent().css("display","none").siblings().css("display","inline-block");
                    $this.parent().parent().siblings().find("input[type='radio']").prop("checked","checked");
                    $(".default-span-tips").css("display","none");
                    $this.parent().parent().siblings().find("span[class='am-margin-right-xs default-span-tips']").css("display","inline-block");
                    jisuan($this.attr("data-userAddress"));
                    alertTips(1,"地址设置","默认收货地址已更新");
                }
            });
        });
        $(".edit-address").click(function(){
           $("#up-address-title").text("编辑");
        });

        $(".add-adders").click(function(){
            $("#up-address-title").text("添加");
        });


        $("body").on("click","#address-btn",function(){
            var $this = $(this);
            var shopName = $('#shop-name').val();
            var phone = "";
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
                        var html = '<tr class="show-tr-address '+checkTr+'">' +
                                '<td><input id="doc-ipt-o-"+address.id type="radio" name="check-address" /></td><td><label for="doc-ipt-o-"+address.id style="font-weight: normal;"><span class="am-margin-right-xs default-span-tips" style="color:#E77779;display: none;">[默认]</span>'+address.localArea+address.detailedAddress+'</label></td>' +
                                '<td> '+address.consigneeName+'</td>' +
                                '<td> '+phoneNumber+' </td>' +
                                '<td class="do" style="width: 13%;"><a href="javascript:;" class="edit-address" style="display: none;" data-am-modal="{target: \'#adders-id\', closeViaDimmer: 0, width: 487, height: 420}">编辑</a><div><a href="javascript:void(0);" data-userAddress="'+address.id+'" class="am-btn-sm am-text-danger set-default-address">设为默认</a></div></td>' +
                                '</tr>'
                        $("#new-address tr:eq(0)").after(html);
                        alertTips(1,"","添加地址成功");
                    }else{
                        $editAddress.parent().siblings().eq(1).find("label .addressInfo").text(address.localArea+address.detailedAddress);
                        $editAddress.parent().siblings().eq(2).find("span").text(address.consigneeName);
                        $editAddress.parent().siblings().eq(3).find("span").text(address.phoneNumber);
                        alertTips(1,"","修改地址成功");
                    }
                    $("#adders-id").modal('close');
                }else {
                    alertTips(2,"服务器繁忙","请重新操作");
                }
            });
        });

        $(".edit-address").click(function(){
            clearAddress();
            var $this = $(this);
            $editAddress = $this;
            var userAddressId = $this.attr("data-userAddress");
            sendRequest("/user/getFindByAddress",{
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

    });
</script>
