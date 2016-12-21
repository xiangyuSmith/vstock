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
                            <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">52</span>
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
                            <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">￥487,235</span>
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
                            <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">-￥4,176</span>
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
                            <span class="am-center layout-font-size-26 am-text-center am-padding-top-xs" style="color: #1FC52C;">￥900</span>
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
                <tr>
                    <td>Jordan 4 Retro Fear Pack</td>
                    <td>10</td>
                    <td>2016-04-20</td>
                    <td>￥8000</td>
                    <td></td>
                    <td></td>
                    <td style="width: 26%">
                        <div class="am-fl" style="width: 30%;">
                            <a href="#">
                                <span style="display: block;width: 55px;height: 45px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -125px -25px;"></span>
                                <span class="am-margin-left-sm am-link-muted">出售</span>
                            </a>
                        </div>
                        <div class="am-fl am-margin-left-sm" style="width: 35%;">
                            <a href="#">
                                <span style="display: block;width: 50px;height: 45px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -65px -22px;"></span>
                                <span class="am-margin-left am-link-muted">删除</span>
                            </a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Jordan 4 Retro Fear Pack</td>
                    <td>10</td>
                    <td>2016-04-20</td>
                    <td>￥8000</td>
                    <td></td>
                    <td></td>
                    <td style="width: 26%">
                        <div class="am-fl" style="width: 30%;">
                            <a href="#">
                                <span style="display: block;width: 55px;height: 45px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -125px -25px;"></span>
                                <span class="am-margin-left-sm am-link-muted">出售</span>
                            </a>
                        </div>
                        <div class="am-fl am-margin-left-sm" style="width: 35%;">
                            <a href="#">
                                <span style="display: block;width: 50px;height: 45px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -65px -22px;"></span>
                                <span class="am-margin-left am-link-muted">删除</span>
                            </a>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    jQuery(function($){
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
            credits:{
                enabled:false
            },
            exporting: {
                enabled:false
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: false,
                    colors:['#E17575','#8DC327','#046FB7','#7cb5ec', '#434348', '#90ed7d',
                        '#f7a35c', '#8085e9','#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'],
                    column:{
                        colorByPoint:true
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '占比',
                data: [
                    ['AIRJORDAN',   60],
                    ['Adidas',       30],
                    ['Foamposite',    10]
                ]
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
            credits:{
                enabled:false
            },
            exporting: {
                enabled:false
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: false,
                    colors:['#E17575','#8DC327','#046FB7','#7cb5ec', '#434348', '#90ed7d',
                        '#f7a35c', '#8085e9','#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'],
                    column:{
                        colorByPoint:true
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '占比',
                data: [
                    ['AIRJORDAN',   60],
                    ['Adidas',       30],
                    ['Foamposite',    10]
                ]
            }]
        });
    });
</script>
</form>