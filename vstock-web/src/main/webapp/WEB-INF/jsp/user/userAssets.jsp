<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="userAssets" action="${cxt}/user/userAssets" method="post">
<div class="am-g">
    <%--<div id="main"style="height:500px;border:1px solid #ccc;padding:10px;"></div>--%>
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-right-0 am-padding-left-0">
        <div class="am-u-sm-7 am-u-md-7 am-u-lg-7 am-padding-left-0 am-padding-right-0">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12"><span class="layout-font-size-30">资产概览</span></div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0 am-margin-top-lg highcharts-sum">
                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-left-0 am-padding-right-0 highcharts-with-higth">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12"><span class="am-center am-text-center layout-font-size-22">数量分布</span></div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0 highcharts-with-higth" id="container_number"></div>
                </div>
                <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-padding-left-0 am-padding-right-0 highcharts-with-higth">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 layout-font-size-20"><span class="am-center am-text-center layout-font-size-22">价值分布</span></div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0 highcharts-with-higth" id="container_value"></div>
                </div>
            </div>

        </div>
        <div class="am-u-sm-5 am-u-md-5 am-u-lg-5 am-padding-left-0 am-padding-right-0">
            <ul class="am-nav" style="border: solid 1px #CDCDCD;background-color: #F5F5F5;">
                <li style="border-bottom: solid 1px #CDCDCD; height: 80px;" class="am-padding-top-sm am-padding-left-0">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-padding-left-0 am-padding-right-0">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-margin-left-0 am-padding-left-0 am-margin-right-0 am-padding-right-0">
                            <span class="am-margin-right-xs" style="float: left; display: block;width: 60px;height: 60px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -721px -21px;"></span>
                            <span class="am-center meun-font-size am-text-middle am-padding-top-sm" style="color: #646464; font-weight: initial">球鞋数量</span>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-margin-left-0 am-padding-left-0 am-margin-right-0 am-padding-right-0">
                            <c:choose>
                                <c:when test="${not empty basicinformationRose.id}">
                                    <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">${basicinformationRose.id}</span>
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
                            <span class="am-margin-right-xs" style="float: left; display: block;width: 60px;height: 60px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -606px -16px;"></span>
                            <span class="am-center meun-font-size am-text-middle am-padding-top-sm" style="color: #646464; font-weight: initial">市场价值</span>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-margin-left-0 am-padding-left-0 am-margin-right-0 am-padding-right-0">
                            <c:choose>
                                <c:when test="${not empty basicinformationRose.current_market_value}">
                                    <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">￥<fmt:formatNumber value="${basicinformationRose.current_market_value}" type="currency" pattern="#,#00.0#"/></span>
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
                            <span class="am-margin-right-xs" style="float: left; display: block;width: 60px;height: 60px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -492px -22px;"></span>
                            <span class="am-center meun-font-size am-text-middle am-padding-top-sm" style="color: #646464; font-weight: initial">涨跌幅度</span>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-margin-left-0 am-padding-left-0 am-margin-right-0 am-padding-right-0">
                            <c:choose>
                                <c:when test="${not empty basicinformationRose.change_range}">
                                    <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">￥<fmt:formatNumber value="${basicinformationRose.change_range}" type="currency" pattern="#,#00.0#"/></span>
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
                            <span class="am-margin-right-xs" style="float: left; display: block;width: 60px;height: 60px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -307px -18px;"></span>
                            <span class="am-center meun-font-size am-text-middle am-padding-top-sm" style="color: #646464; font-weight: initial">平均价格</span>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6 am-margin-left-0 am-padding-left-0 am-margin-right-0 am-padding-right-0">
                            <c:choose>
                                <c:when test="${not empty basicinformationRose.percentage_change}">
                                    <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">￥<fmt:formatNumber value="${basicinformationRose.percentage_change}" type="currency" pattern="#,#00.0#"/></span>
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
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
        <table class="am-table am-table-striped am-table-hover">
            <caption><p class="layout-font-size-30 am-fl">资产明细</p></caption>
            <thead>
                <tr>
                    <th style="width: 20%;">球鞋名称</th>
                    <th>尺码</th>
                    <th>购买日期</th>
                    <th>金额</th>
                    <th>当前市场价值</th>
                    <th>涨跌幅度</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${not empty userAssetsList}">
                    <c:forEach items="${userAssetsList}" var="userAssets">
                        <tr>
                            <td>${userAssets.basicinformation.name}</td>
                            <td>${userAssets.userAssetsSize}</td>
                            <td>${userAssets.purchaseDate}</td>
                            <td>￥<fmt:formatNumber value="${userAssets.money}" type="currency" pattern="#,#00.0#"/></td>
                            <c:choose>
                                <c:when test="${not empty userAssets.basicinformationRose.current_market_value}">
                                    <td>￥<fmt:formatNumber value="${userAssets.basicinformationRose.current_market_value}" type="currency" pattern="#,#00.0#"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td>--</td>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${not empty userAssets.basicinformationRose.change_range}">
                                    <c:choose>
                                        <c:when test="${userAssets.basicinformationRose.type == 0}">
                                            <td>￥-<fmt:formatNumber value="${userAssets.basicinformationRose.change_range}" type="currency" pattern="#,#00.0#"/></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>￥<fmt:formatNumber value="${userAssets.basicinformationRose.change_range}" type="currency" pattern="#,#00.0#"/></td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <td>--</td>
                                </c:otherwise>
                            </c:choose>
                            <td style="width: 26%">
                                <div class="am-fl" style="width: 30%;">
                                    <a href="javascript:void(0);">
                                        <span style="display: block;width: 55px;height: 45px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -125px -25px;"></span>
                                        <span class="am-margin-left-sm am-link-muted">出售</span>
                                    </a>
                                </div>
                                <div class="am-fl am-margin-left-sm" style="width: 35%;">
                                    <a href="javascript:void(0);">
                                        <span style="display: block;width: 50px;height: 45px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -65px -22px;"></span>
                                        <span class="am-margin-left am-link-muted userAssets-del" btf-id="${userAssets.basicinformationId}" del_data_id="${userAssets.id}">删除</span>
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    jQuery(function($){
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
            if (res != null) {
                $('#container_number').highcharts({
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false
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
                        plotShadow: false
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
            }
        });
    });
</script>
</form>