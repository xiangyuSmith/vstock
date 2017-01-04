<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="am-cf am-padding">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">退货管理</strong> /<small><c:if test="${type == 0}">新增页面</c:if><c:if test="${type == 1}">修改页面</c:if></small></div>
    </div>
    <hr>
    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom">
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                <span class="am-fr am-text-lg">退货人</span>
            </div>
            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-u-end">
                <input type="text" name="backPerson" id="backPerson" class="am-input-lg am-padding-left-xs" placeholder="退货人名称"/>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom">
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                <span class="am-fr am-text-lg">收货人</span>
            </div>
            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-u-end">
                <input type="text" name="consignee" id="consignee" class="am-input-lg am-padding-left-xs" placeholder="收货人名称"/>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top am-margin-bottom">
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                <span class="am-fr am-text-lg">商品名称</span>
            </div>
            <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-u-end">
                <input type="hidden" name="btfId" id="btfId"/>
                <input type="text" name="btfName" id="btfName" class="am-input-lg am-padding-left-xs" placeholder="商品名称" data-am-modal="{target: '#btf-modal', width: 700}"/>
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
                <span class="am-fr am-text-lg">退货原因</span>
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

<div class="am-modal am-modal-alert" tabindex="-1" id="btf-modal">
    <div class="am-modal-dialog">
        <div class="am-modal-hd" style="border-bottom: solid 1px #b1b1b1"><strong class="am-text-xl">商品信息查询</strong><a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a></div>
        <div class="am-modal-bd am-g">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0 am-margin-top">
                <div class="am-u-sm-5 am-u-md-5 am-u-lg-5 am-padding-left-0">
                    <span class="am-fl am-text-default">名称：</span>
                    <input type="text" id="name" class="am-input-lg am-fr" placeholder="名称"/>
                </div>
                <div class="am-u-sm-5 am-u-md-5 am-u-lg-5 am-padding-left-0 am-padding-right-0">
                    <span class="am-fl am-text-default">中文标识：</span>
                    <input type="text" id="chineselogo" class="am-input-lg am-fr" placeholder="中文标识"/>
                </div>
                <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 am-padding-left-0 am-padding-right-0 am-margin-bottom">
                    <a href="#" style="line-height: 14px;" id="sel-btn" class="am-btn am-btn-primary am-btn-sm">查询</a>
                </div>
                <div class="am-u-sm-12">
                    <table class="am-table am-table-bd am-table-striped admin-content-table">
                        <thead>
                        <tr>
                            <th class="am-text-center">品牌</th>
                            <th class="am-text-center">名称</th>
                            <th class="am-text-center">货号</th>
                            <th class="am-text-center">中文标识</th>
                        </tr>
                        </thead>
                        <tbody id="appnd-number">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="am-modal am-modal-alert" tabindex="-1" id="trade-modal">
    <div class="am-modal-dialog">
        <div class="am-modal-hd" style="border-bottom: solid 1px #b1b1b1"><strong class="am-text-xl">订单信息查询</strong><a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a></div>
        <div class="am-modal-bd am-g">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0 am-margin-top">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-padding-left-0">
                    <span class="am-fl am-text-default">买家：</span>
                    <input type="text" id="buyName" style="max-width: 100px;" class="am-input-lg am-fr" placeholder="买家"/>
                </div>
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-padding-left-0">
                    <span class="am-fl am-text-default">卖家：</span>
                    <input type="text" id="sellName" style="max-width: 100px;" class="am-input-lg am-fr" placeholder="卖家"/>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4 am-padding-left-0 am-padding-right-0">
                    <span class="am-fl am-text-default">球鞋名称：</span>
                    <input type="text" id="btfnames" style="max-width: 145px;" class="am-input-lg am-fr" placeholder="球鞋名称"/>
                </div>
                <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 am-padding-left-0 am-padding-right-0 am-margin-bottom">
                    <a href="#" style="line-height: 14px;" id="trade-sel" class="am-btn am-btn-primary am-btn-sm">查询</a>
                </div>
                <div class="am-u-sm-12">
                    <table class="am-table am-table-bd am-table-striped admin-content-table">
                        <thead>
                        <tr>
                            <th class="am-text-center">订单号</th>
                            <th class="am-text-center">买家</th>
                            <th class="am-text-center">卖家</th>
                            <th class="am-text-center">球鞋名称</th>
                            <th class="am-text-center">金额</th>
                        </tr>
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
            var consignee = $('#consignee').val();
            var backPerson = $('#backPerson').val();
            var btfName = $('#btfName').val();
            var tradeNo = $('#trade_no').val();
            var reasons = $('#reasons').val();
            $.post("/backCommodity/saveBackCommodity",{
                'consignee': consignee,
                'btfId' : $('#btfId').val(),
                'btfName' : $('#btfnames').val()
            },function(res){
            });
        })

        $("body").on("click",".sbt_on",function(){
            var $th = $(this).children().first();
            $('#btfId').val($th.children().val());
            $('#btfName').val($th.next().text());
        });

        $("body").on("click",".sbt_trade",function(){
            var $th = $(this).children().first();
            $('#trade_no').val($th.text());
        });

        $('#trade-sel').click(function () {
            tradeList();
        });

        $('#sel-btn').click(function () {
            lodaing();
        });

        lodaing();

        function lodaing() {
            $.post("/backCommodity/findBtf",{
                'name': $('#name').val(),
                'chineselogo' : $('#chineselogo').val()
            },function(res){
                if (res.btfList != null && res.btfList != ""){
                    if (res.btfList.length > 0){
                        $('#appnd-number').html("");
                        for (var i = 0; i < res.btfList.length; i++){
                            var btf = res.btfList[i];
                            var a = "<tr class='sbt_on' style='cursor: pointer;' data-am-modal-close>\n" +
                                    "<td><input type=\"hidden\" value=\""+btf.id+"\"/>"+btf.brand+"</td>\n" +
                                    "<td>"+btf.name+"</td>\n" +
                                    "<td>"+btf.artNo+"</td>\n" +
                                    "<td>"+btf.chineselogo+"</td>\n" +
                                    "</tr>";
                            $('#appnd-number').append(a);
                            if (i == 5){
                                return;
                            }
                        }
                    }
                }
            });
        }

        tradeList();

        function tradeList() {
            $.post("/backCommodity/findTrade",{
                'sellerName': $('#sellName').val(),
                'buyersName' : $('#buyName').val(),
                'btfName' : $('#btfnames').val()
            },function(res){
                if (res.tradeList != null && res.tradeList != ""){
                    if (res.tradeList.length > 0){
                        $('#trade').html("");
                        for (var i = 0; i < res.tradeList.length; i++){
                            var trade = res.tradeList[i];
                            var a = "<tr class='sbt_trade' style='cursor: pointer;' data-am-modal-close>\n" +
                                    "<td>"+trade.tradeNo+"</td>\n" +
                                    "<td>"+trade.buyersName+"</td>\n" +
                                    "<td>"+trade.sellerName+"</td>\n" +
                                    "<td>"+trade.bftName+"</td>\n" +
                                    "<td>"+trade.transactionMoney+"</td>\n" +
                                    "</tr>";
                            $('#trade').append(a);
                            if (i == 5){
                                return;
                            }
                        }
                    }
                }
            });
        }

    });
</script>