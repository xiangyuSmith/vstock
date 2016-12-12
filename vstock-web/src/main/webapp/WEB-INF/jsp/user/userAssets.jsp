<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="userAssets" action="${cxt}/index/testUserAssets" method="post">
<div class="am-g">
    <%--<div id="main"style="height:500px;border:1px solid #ccc;padding:10px;"></div>--%>
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
        <div class="am-u-sm-7 am-u-md-7 am-u-lg-7"></div>
        <div class="am-u-sm-5 am-u-md-5 am-u-lg-5">
            <ul class="am-nav" style="border: solid 1px #CDCDCD;">
                <li style="border-bottom: solid 1px #CDCDCD; height: 80px;" class="am-padding-top-sm">
                    <span style="float: left; display: block;width: 60px;height: 60px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -721px -25px;"></span>
                    <span class="layout-font-size-20 am-margin-left-sm am-text-middle" style="color: #646464; font-weight: initial">球鞋数量</span>
                    <span class="am-margin-left-xl am-padding-left-xl layout-font-size-30 am-text-middle" style="color: #1FC52C;">52</span>
                </li>
                <li style="border-bottom: solid 1px #CDCDCD; height: 80px;" class="am-padding-top-sm">
                    <span style="float: left; display: block;width: 60px;height: 60px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -606px -19px;"></span>
                    <span class="layout-font-size-20 am-margin-left-sm am-text-middle" style="color: #646464; font-weight: initial">市场价值</span>
                    <span class="am-margin-left-xl am-padding-left-xl layout-font-size-30 am-text-middle" style="color: #1FC52C;">52</span>
                </li>
                <li style="border-bottom: solid 1px #CDCDCD; height: 80px;" class="am-padding-top-sm">
                    <span style="float: left; display: block;width: 60px;height: 60px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -492px -25px;"></span>
                    <span class="layout-font-size-20 am-margin-left-sm am-text-middle" style="color: #646464; font-weight: initial">涨跌幅度</span>
                    <span class="am-margin-left-xl am-padding-left-xl layout-font-size-30 am-text-middle" style="color: #1FC52C;">52</span>
                </li>
                <li style="height: 80px;" class="am-padding-top-sm">
                    <span style="float: left; display: block;width: 60px;height: 60px; background: url('../../../../assets/shoesImg/personal_center.png'); background-position: -307px -18px;"></span>
                    <span class="layout-font-size-20 am-margin-left-sm am-text-middle" style="color: #646464; font-weight: initial">平均价格</span>
                    <span class="am-margin-left-xl am-padding-left-xl layout-font-size-30 am-text-middle" style="color: #1FC52C;">52</span>
                </li>
            </ul>
        </div>
    </div>
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
        <table class="am-table am-table-striped am-table-hover">
            <caption style="border-bottom: 1px solid #CACACA;"><p>资产明细</p></caption>
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
                    <td>
                        <a href="#" class="am-btn am-text-danger am-radius am-margin-left-sm" style="border: solid 1px #F25C58; background-color: #FFFFFF;">默认地址</a>
                    </td>
                </tr>
                <tr>
                    <td>Jordan 4 Retro Fear Pack</td>
                    <td>10</td>
                    <td>2016-04-20</td>
                    <td>￥8000</td>
                    <td></td>
                    <td></td>
                    <td>
                        <a href="#" class="am-btn am-text-danger am-radius am-margin-left-sm" style="border: solid 1px #F25C58; background-color: #FFFFFF;">默认地址</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    jQuery(function($){
//        var $this = document.getElementById('main');
//        var myChart = echarts.init($this);
//        myChart.setOption({
//            title : {
//                text: '某楼盘销售情况',
//                subtext: '纯属虚构'
//            },
//            tooltip : {
//                trigger: 'axis'
//            },
//            legend: {
//                data:['意向','预购','成交']
//            },
//            toolbox: {
//                show : true,
//                feature : {
//                    mark : {show: true},
//                    dataView : {show: true, readOnly: false},
//                    magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
//                    restore : {show: true},
//                    saveAsImage : {show: true}
//                }
//            },
//            xAxis : [
//                {
//                    type : 'category',
//                    boundaryGap : false,
//                    data : ['','','','','','','','','','','','','','','','','','','','','','','','','','','']
//                }
//            ],
//            yAxis : [
//                {
//                    type : 'value',
//                }
//            ],
//            series : [
//                {
//                    name:'成交',
//                    type:'line',
//                    smooth:true,
//                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
//                    data:[10, 12, 21, 54, 260, 830, 710, 5645, 34, 435, 434, 123, 786, 123, 45, 687, 127, 543, 783, 46, 3214, 434, 213, 412, 345, 45321, 4374]
//                },
//                {
//                    name:'预购',
//                    type:'line',
//                    smooth:true,
//                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
//                    data:[30, 182, 434, 791, 390, 30, 10, 5, 4531, 7867, 43, 783, 72, 3423, 7867, 43, 7867, 7863, 4534, 4337, 434, 42, 764, 7863, 431, 434, 7867]
//                },
//                {
//                    name:'意向',
//                    type:'line',
//                    smooth:true,
//                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
//                    data:[1320, 1132, 601, 234, 120, 90, 20, 524, 2254, 7865, 32, 4673, 4641, 4678, 3123, 2112, 8421, 4312, 1254, 1235, 321, 413, 473, 574, 122, 43, 123]
//                }
//            ]
//        });
    });
</script>
</form>