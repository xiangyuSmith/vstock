<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="am-cf am-padding">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">退款管理</strong> /<small>新增页面</small></div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom">
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                <span class="am-fr am-text-lg">退款对象</span>
            </div>
            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-u-end">
                <select class="am-input-lg am-padding-left-xs" id="refundObj">
                    <c:if test="${not empty objList}">
                        <c:forEach items="${objList}" var="obj">
                            <option value="${obj.refundObj}">${obj.btfName}</option>
                        </c:forEach>
                    </c:if>
                </select>
                <%--<input type="text" name="consignee" id="consignee" class="am-input-lg am-padding-left-xs" placeholder="收货人名称"/>--%>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom">
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                <span class="am-fr am-text-lg">退款类型</span>
            </div>
            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-u-end">
                <select class="am-input-lg am-padding-left-xs" id="type">
                    <c:if test="${not empty typeList}">
                        <c:forEach items="${typeList}" var="type">
                            <option value="${type.type}">${type.btfName}</option>
                        </c:forEach>
                    </c:if>
                </select>
                <%--<input type="text" name="backPerson" id="backPerson" class="am-input-lg am-padding-left-xs" placeholder="退货人名称"/>--%>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom">
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                <span class="am-fr am-text-lg">订单号</span>
            </div>
            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-u-end">
                <input type="text" id="trade_no" name="tradeNo" class="am-input-lg am-padding-left-xs" placeholder="订单号" data-am-modal="{target: '#trade-modal', width: 700}"/>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom">
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                <span class="am-fr am-text-lg">商品名称</span>
            </div>
            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-u-end">
                <input type="hidden" name="btfId" id="btfId"/>
                <input type="text" name="btfName" id="btfName" placeholder="商品名称" class="am-input-lg am-padding-left-xs" disabled="disabled" readonly/>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom">
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                <span class="am-fr am-text-lg">退款金额</span>
            </div>
            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-u-end">
                <input type="text" name="refundprice" placeholder="退款金额" id="refundprice" class="am-input-lg am-padding-left-xs" disabled="disabled"  readonly/>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom">
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                <span class="am-fr am-text-lg">退款原因</span>
            </div>
            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-u-end">
                <textarea type="text" id="reasons" name="reasons" style="min-width: 206px;" class="am-input-lg am-padding-left-xs" placeholder="退货原因"></textarea>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top">
            <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                <span class="am-fr">
                    <a href="javascript:void(0);"id="add-sbt" class="am-btn am-btn-primary am-btn-lg am-margin-right">提交</a>
                    <a href="javascript:void(0);" id="qiut-sbt" class="am-btn am-btn-danger am-btn-lg">取消</a>
                </span>
            </div>
        </div>
    </div>
</div>

<div class="am-modal am-modal-alert" tabindex="-1" id="trade-modal">
    <div class="am-modal-dialog">
        <div class="am-modal-hd" style="border-bottom: solid 1px #b1b1b1"><strong class="am-text-xl" id="model_hendname">订单信息查询</strong><a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a></div>
        <div class="am-modal-bd am-g">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0 am-margin-top">
                <div class="am-u-sm-5 am-u-md-5 am-u-lg-5">
                    <span class="am-fl am-text-default">球鞋名称：</span>
                    <input type="text" id="btfnames" style="max-width: 160px;" class="am-input-lg am-fr" placeholder="球鞋名称"/>
                </div>
                <div class="am-u-sm-5 am-u-md-5 am-u-lg-5 am-padding-left-0 am-padding-right-0">
                    <span class="am-fl am-text-default">卖家（叫价人）：</span>
                    <input type="text" id="sellName" style="max-width: 150px;" class="am-input-lg am-fr" placeholder="卖家（叫价人）"/>
                </div>
                <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 am-padding-left-0 am-padding-right-0 am-margin-bottom">
                    <a href="javascript: void(0);" style="line-height: 14px;" id="trade-sel" class="am-btn am-btn-primary am-btn-sm">查询</a>
                </div>
                <div class="am-u-sm-12">
                    <table class="am-table am-table-bd am-table-striped admin-content-table">
                        <thead id="tr_th_meun">
                        </thead>
                        <tbody id="trade">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/bottom.jsp" flush="true"/>
<script type="text/javascript">
    jQuery(function($){

        $('#qiut-sbt').click(function () {
            history.back();
        });

        $('#add-sbt').click(function () {
            var upstatus = "60";
            var refundObj = $('#refundObj').val();
            var type = $('#type').val();
            var tradeNo = $('#trade_no').val();
            var btfName = $('#btfName').val();
            var refundprice = $('#refundprice').val();
            var reasons = $('#reasons').val();
            if (type == 1){upstatus = "50";}
            $.post("/resfund/saveRefund",{
                'tradeNo' : tradeNo,
                'refundObj' : refundObj,
                'type': type,
                'upstatus':upstatus,
                'status' : 0,
                'btfId' : $('#btfId').val(),
                'btfName' : btfName,
                'refundPrice' : refundprice,
                'reasons' : reasons
            },function(res){
                if (res.reGode > 0){
                    window.location.href = "/resfund/index";
                }else {
                    alert("添加失败，重新操作！");
                }
            });
        })

        $("body").on("click",".sbt_trade",function(){
            var $th = $(this).children().last().prev();
            var $this = $(this).children().first();
            $('#btfId').val($th.children().val());
            $('#btfName').val($th.text());
            $('#trade_no').val($this.text());
            var id = $this.children().val();
            $.post("/resfund/calculateRefund",{
                'type' : $('#type').val(),
                'id' : id
            },function(res){
                if (res.price){
                    $('#refundprice').val(res.price);
                }
            });
        });

        $('#trade-sel').click(function () {
            tradeList($('#type').val());
        });

        $('#trade_no').click(function () {
           if ($('#type').val() == ""){
               alert("请先选择退款类型！");
               return;
           }else {
               typeValue($('#type').val());
           }
            tradeList($('#type').val());
        });

        function typeValue(type) {
            $('#tr_th_meun').html("");
            $('#btfnames').val("");
            $('#sellName').val("");
            if(type == 1){
                var a = " <tr>\n" +
                        "<th class=\"am-text-center\">编号</th>\n" +
                        "<th class=\"am-text-center\">叫价人</th>\n" +
                        "<th class=\"am-text-center\">尺码</th>\n" +
                        "<th class=\"am-text-center\">球鞋名称</th>\n" +
                        "<th class=\"am-text-center\">叫价金额</th>\n" +
                        "</tr>";
                $('#tr_th_meun').append(a);
            }else {
                var a = " <tr>\n" +
                        "<th class=\"am-text-center\">订单号</th>\n" +
                        "<th class=\"am-text-center\">买家</th>\n" +
                        "<th class=\"am-text-center\">卖家</th>\n" +
                        "<th class=\"am-text-center\">球鞋名称</th>\n" +
                        "<th class=\"am-text-center\">金额</th>\n" +
                        "</tr>";
                $('#tr_th_meun').append(a);
            }
        }

        function tradeList(type) {
            $.post("/resfund/findBidTrade",{
                'name': $('#sellName').val(),
                'type' : type,
                'btfName' : $('#btfnames').val()
            },function(res){
                if (res.tradeList != null && res.tradeList != ""){
                    if (res.tradeList.length > 0){
                        $('#trade').html("");
                        for (var i = 0; i < res.tradeList.length; i++){
                            var trade = res.tradeList[i];
                            var a = "<tr class='sbt_trade' style='cursor: pointer;' data-am-modal-close>\n" +
                                    "<td><input type=\"hidden\" value=\""+trade.id+"\"/>"+trade.tradeNo+"</td>\n" +
                                    "<td>"+trade.buyersName+"</td>\n" +
                                    "<td>"+trade.sellerName+"</td>\n" +
                                    "<td><input type=\"hidden\" value=\""+trade.basicinformationId+"\"/>"+trade.bftName+"</td>\n" +
                                    "<td>"+trade.transactionMoney+"</td>\n" +
                                    "</tr>";
                            $('#trade').append(a);
                            if (i == 5){
                                return;
                            }
                        }
                    }
                }else if (res.bidList != null && res.bidList != ""){
                    if (res.bidList.length > 0){
                        $('#trade').html("");
                        for (var i = 0; i < res.bidList.length; i++){
                            var bid = res.bidList[i];
                            var a = "<tr class='sbt_trade' style='cursor: pointer;' data-am-modal-close>\n" +
                                    "<td><input type=\"hidden\" value=\""+bid.id+"\"/>"+bid.id+"</td>\n" +
                                    "<td>"+bid.userId+"</td>\n" +
                                    "<td>"+bid.bftSize+"</td>\n" +
                                    "<td><input type=\"hidden\" value=\""+bid.basicinformationId+"\"/>"+bid.bftName+"</td>\n" +
                                    "<td>"+bid.bidMoney+"</td>\n" +
                                    "</tr>";
                            $('#trade').append(a);
                            if (i == 5){
                                return;
                            }
                        }
                    }
                }else {return;}
            });
        }

    });
</script>