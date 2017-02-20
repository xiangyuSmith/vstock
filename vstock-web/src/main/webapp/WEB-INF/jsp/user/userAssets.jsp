<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="userAssets" action="/user/userAssets" method="post">
<div class="am-g">
    <%--<div id="main"style="height:500px;border:1px solid #ccc;padding:10px;"></div>--%>
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0" style="border-bottom:1px solid #CACACA;"><div style="float: left; display: block;width: 37px;height: 35px; background: url('/assets/i/personal_center_map.png'); background-position: -1097px -15px;"></div><span class="layout-font-size-26">资产概览</span></div>
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-right-0 am-padding-left-0">
        <div class="am-u-sm-7 am-u-md-7 am-u-lg-7 am-padding-left-0 am-padding-right-0">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0 am-margin-top-lg highcharts-sum">
                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-left-0 am-padding-right-0 highcharts-with-higth">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12"><span class="am-center am-text-center layout-font-size-20 am-fl am-padding-left-sm">数量分布</span></div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0" id="container_number">
                        <img src="/assets/i/spherical_graph.png" class="am-padding-top" id="spherical_id" style="display:none;">
                    </div>
                </div>
                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-left-0 am-padding-right-0 highcharts-with-higth">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 layout-font-size-20">
                        <span class="am-center am-text-center layout-font-size-20 am-fl am-padding-left-sm">价值分布</span>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0" id="container_value">
                        <img src="/assets/i/spherical_graph.png" class="am-padding-top" id="sphericals_id" style="display:none;">
                    </div>
                </div>
            </div>

        </div>
        <div class="am-u-sm-5 am-u-md-5 am-u-lg-5 am-padding-left-0 am-padding-right-0 am-padding-top">
            <ul class="am-nav" style="border: solid 1px #CDCDCD;background-color: #F5F5F5;">
                <li style="border-bottom: solid 1px #CDCDCD; height: 80px;" class="am-padding-top-sm am-padding-left-0">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-margin-left-0 am-padding-left-0 am-margin-right-0 am-padding-right-0">
                            <span class="am-margin-right-xs" style="float: left; display: block;width: 60px;height: 60px; background: url('/assets/i/personal_center_map.png'); background-position: -1214px -12px;"></span>
                            <span class="am-center meun-font-size am-text-middle am-padding-top-sm" style="color: #646464; font-weight: initial">球鞋数量</span>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-margin-left-0 am-padding-left-0 am-margin-right-0 am-padding-right-0">
                            <c:choose>
                                <c:when test="${not empty basicinformationRose.id}">
                                    <c:choose>
                                        <c:when test="${basicinformationRose.id > 0}">
                                            <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">${basicinformationRose.id}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">--</span>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">--</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </li>
                <li style="border-bottom: solid 1px #CDCDCD; height: 80px;" class="am-padding-top-sm">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-margin-left-0 am-padding-left-0 am-margin-right-0 am-padding-right-0">
                            <span class="am-margin-right-xs" style="float: left; display: block;width: 60px;height: 60px; background: url('/assets/i/personal_center_map.png'); background-position: -1286px -16px;"></span>
                            <span class="am-center meun-font-size am-text-middle am-padding-top-sm" style="color: #646464; font-weight: initial">市场价值</span>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-margin-left-0 am-padding-left-0 am-margin-right-0 am-padding-right-0">
                            <c:choose>
                                <c:when test="${not empty basicinformationRose.current_market_value}">
                                    <c:choose>
                                        <c:when test="${basicinformationRose.current_market_value > 0}">
                                            <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;"><fmt:formatNumber value="${basicinformationRose.current_market_value}" type="currency" pattern="￥#,##0.0"/></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">--</span>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">--</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </li>
                <li style="border-bottom: solid 1px #CDCDCD; height: 80px;" class="am-padding-top-sm">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-margin-left-0 am-padding-left-0 am-margin-right-0 am-padding-right-0">
                            <span class="am-margin-right-xs" style="float: left; display: block;width: 60px;height: 60px; background: url('/assets/i/personal_center_map.png'); background-position: -1365px -19px;"></span>
                            <span class="am-center meun-font-size am-text-middle am-padding-top-sm" style="color: #646464; font-weight: initial">涨跌幅度</span>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-margin-left-0 am-padding-left-0 am-margin-right-0 am-padding-right-0">
                            <c:choose>
                                <c:when test="${not empty basicinformationRose.change_range}">
                                    <c:choose>
                                        <c:when test="${basicinformationRose.change_range == 0}">
                                            <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">--</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;"><fmt:formatNumber value="${basicinformationRose.change_range}" type="currency" pattern="￥#,##0.0"/></span>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">--</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </li>
                <li style="height: 80px;" class="am-padding-top-sm">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-margin-left-0 am-padding-left-0 am-margin-right-0 am-padding-right-0">
                            <span class="am-margin-right-xs" style="float: left; display: block;width: 60px;height: 60px; background: url('/assets/i/personal_center_map.png'); background-position: -1445px -14px;"></span>
                            <span class="am-center meun-font-size am-text-middle am-padding-top-sm" style="color: #646464; font-weight: initial">平均价格</span>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-margin-left-0 am-padding-left-0 am-margin-right-0 am-padding-right-0">
                            <c:choose>
                                <c:when test="${not empty basicinformationRose.percentage_change}">
                                    <c:choose>
                                        <c:when test="${basicinformationRose.percentage_change > 0}">
                                            <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;"><fmt:formatNumber value="${basicinformationRose.percentage_change}" type="currency" pattern="￥#,##0.0"/></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">--</span>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">--</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-lg">
        <table class="am-table am-table-striped am-table-hover">
            <caption style="border-bottom:1px solid #CACACA;">
                <div style="float: left; display: block;width: 37px;height: 45px; background: url('/assets/i/personal_center_map.png'); background-position: -1152px -18px;"></div>
                <span class="layout-font-size-26 am-fl">资产明细</span>
                <c:if test="${not empty userAssetsList}">
                    <span class="am-fr" style="color: #249bd3;line-height: 38px;"><a href="javascript:void(0)" data-url="../user/userAssetsList?pageNow=1" class="offer-btn layout-font-size-16">更多记录</a><div style="float: right; display: block;width: 25px;height: 45px; background: url('/assets/i/personal_center_map.png'); background-position: -940px -18px;"></div></span>
                </c:if>
            </caption>
            <c:choose>
                <c:when test="${not empty userAssetsList}">
                    <thead>
                        <tr>
                            <td style="width: 20%;">球鞋名称</td>
                            <td>尺码</td>
                            <td>购买日期</td>
                            <td>购买金额</td>
                            <td>当前市场价</td>
                            <td>涨跌幅度</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${not empty userAssetsList}">
                            <c:forEach items="${userAssetsList}" var="userAssets">
                                <tr>
                                    <td><a href="/detail?proName=${userAssets.basicinformation.name}" target="_blank"><span class="popover-tips" data-bftId="${userAssets.basicinformationId}" data-bftSize="${bid.userAssetsSize}" data-img-url="${configMap._site_url}" style="color: #333;">${userAssets.basicinformation.name}</span></a></td>
                                    <td>${userAssets.userAssetsSize}</td>
                                    <td>${userAssets.purchaseDate}</td>
                                    <td><fmt:formatNumber value="${userAssets.money}" type="currency" pattern="￥#,##0.00"/></td>
                                    <c:choose>
                                        <c:when test="${not empty userAssets.basicinformationRose.current_market_value}">
                                            <td><fmt:formatNumber value="${userAssets.basicinformationRose.current_market_value}" type="currency" pattern="￥#,##0.00"/></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>--</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${not empty userAssets.basicinformationRose.change_range}">
                                            <c:choose>
                                                <c:when test="${userAssets.basicinformationRose.type == 0 && userAssets.basicinformationRose.change_range > 0}">
                                                    <td>-<fmt:formatNumber value="${userAssets.basicinformationRose.change_range}" type="currency" pattern="￥#,##0.00"/></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><fmt:formatNumber value="${userAssets.basicinformationRose.change_range}" type="currency" pattern="￥#,##0.00"/></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <td>--</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td style="width: 26%">
                                        <div class="am-fl" style="width: 30%;">
                                            <a href="/detail?proName=${userAssets.basicinformation.name}&size=${userAssets.userAssetsSize}">
                                                <span style="display: block;width: 55px;height: 45px; background: url('/assets/shoesImg/personal_center.png'); background-position: -125px -25px;"></span>
                                                <span class="am-margin-left-sm am-link-muted">出售</span>
                                            </a>
                                        </div>
                                        <div class="am-fl am-margin-left-sm" style="width: 35%;">
                                            <a href="javascript:void(0);">
                                                <span style="display: block;width: 50px;height: 45px; background: url('/assets/shoesImg/personal_center.png'); background-position: -65px -22px;" class="offer-btn" data-url="../user/userAssets"></span>
                                                <span class="am-margin-left am-link-muted userAssets-del" btf-id="${userAssets.basicinformationId}" del_data_id="${userAssets.id}">删除</span>
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </c:when>
                <c:otherwise>
                    <tbody>
                    <tr style="width: 100%; height: 300px;">
                        <td style="background-color: #ffffff;">
                            <img class="am-center" src="/assets/i/asset_detail.png">
                            <a href="/sorts" style="margin-left: 41%;" class="am-btn am-radius am-padding-top-xs am-padding-bottom-xs am-padding-left-lg am-padding-right-lg am-btn-danger am-margin-top-lg">去加入</a>
                        </td>
                    </tr>
                    </tbody>
                </c:otherwise>
            </c:choose>
        </table>
    </div>
    <input type="hidden" id="userAssets_btf_id"/>
    <input type="hidden" id="userAssets_del_data_id"/>
    <input type="hidden" id="userAssets_del_money"/>
    <input type="hidden" id="userAssets_del_size"/>
</div>

    <div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm-userA">
        <div class="am-modal-dialog">
            <div class="am-modal-hd">我的资产</div>
            <div class="am-modal-bd">
                你，确定要删除这条记录吗？
            </div>
            <div class="am-modal-footer">
                <span class="am-modal-btn" data-am-modal-cancel>取消</span>
                <span class="am-modal-btn" data-am-modal-confirm>确定</span>
            </div>
        </div>
    </div>
<script type="text/javascript">
    jQuery(function($){

//        $(".userAssets-del").click(function(){
//            var $this = $(this);
//            var btfId = $('#userAssets_btf_id').val($this.attr("btf-id"));
//            var id = $('#userAssets_del_data_id').val($this.attr("del_data_id"));
//            var money = $('#userAssets_del_money').val($this.parent().parent().parent().prev().prev().prev().text());
//            var size = $('#userAssets_del_size').val($this.parent().parent().parent().prev().prev().prev().prev().prev().text());
//            $('#my-confirm-userA').modal({
//                onConfirm: function() {
//                    sendRequest("/userAssets/saveUserAssets",{
//                        id: id,
//                        bId: btfId,
//                        money: money,
//                        size: size,
//                        status : 1
//                    },function(res) {
//                        if (res.retCode == 1){
//                            alertTips(1,"","删除成功");
//                            ajaxContent("../user/userAssets", "" ,"tradeforex_tilie",1);
//                        }else {
//                            alertTips(2,"服务器繁忙","请重新操作");
//                        }
//                    });
//                },
//                // closeOnConfirm: false,
//                onCancel: function() {
//                }
//            });
//        });

        sendRequest("/user/hchar",null,function(res) {
            var a;
            var b;
            var i = 0;
            for(var key in res){
                value = res[key];
                if (i == 0){
                    a = eval('('+value+')');
                }else {
                    b = eval('('+value+')');
                }
                i++;
            }
            if (i > 0) {
                $('#container_number').highcharts({
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        width: 180,
                        height: 180
                    },
                    title: {
                        text: false
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    credits: {
                        enabled: false
                    },
                    exporting: {
                        enabled: false
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: false
                            },
                            showInLegend: false,
                            colors: ['#E17575', '#8DC327', '#046FB7', '#7cb5ec', '#434348', '#90ed7d',
                                '#f7a35c', '#8085e9', '#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'],
                            column: {
                                colorByPoint: true
                            }
                        }
                    },
                    series: [{
                        type: 'pie',
                        name: '占比',
                        data: b
                    }]
                });

                $('#container_value').highcharts({
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        width: 180,
                        height: 180
                    },
                    title: {
                        text: false
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    credits: {
                        enabled: false
                    },
                    exporting: {
                        enabled: false
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: false
                            },
                            showInLegend: false,
                            colors: ['#E17575', '#8DC327', '#046FB7', '#7cb5ec', '#434348', '#90ed7d',
                                '#f7a35c', '#8085e9', '#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'],
                            column: {
                                colorByPoint: true
                            }
                        }
                    },
                    series: [{
                        type: 'pie',
                        name: '占比',
                        data: a
                    }]
                });
            }else {
                $('#spherical_id').css('display','block');
                $('#sphericals_id').css('display','block');
            }
        });
    });
</script>
</form>