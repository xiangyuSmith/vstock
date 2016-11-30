<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="userAssets" action="${cxt}/index/testUserAssets" method="post">
<div class="am-g">
    <div id="main"style="height:500px;border:1px solid #ccc;padding:10px;"></div>
</div>
<script type="text/javascript">
    jQuery(function($){
        var $this = document.getElementById('main');
        var myChart = echarts.init($this);
        myChart.setOption({
            title : {
                text: '某楼盘销售情况',
                subtext: '纯属虚构'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['意向','预购','成交']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : ['','','','','','','','','','','','','','','','','','','','','','','','','','','']
                }
            ],
            yAxis : [
                {
                    type : 'value',
                }
            ],
            series : [
                {
                    name:'成交',
                    type:'line',
                    smooth:true,
                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data:[10, 12, 21, 54, 260, 830, 710, 5645, 34, 435, 434, 123, 786, 123, 45, 687, 127, 543, 783, 46, 3214, 434, 213, 412, 345, 45321, 4374]
                },
                {
                    name:'预购',
                    type:'line',
                    smooth:true,
                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data:[30, 182, 434, 791, 390, 30, 10, 5, 4531, 7867, 43, 783, 72, 3423, 7867, 43, 7867, 7863, 4534, 4337, 434, 42, 764, 7863, 431, 434, 7867]
                },
                {
                    name:'意向',
                    type:'line',
                    smooth:true,
                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data:[1320, 1132, 601, 234, 120, 90, 20, 524, 2254, 7865, 32, 4673, 4641, 4678, 3123, 2112, 8421, 4312, 1254, 1235, 321, 413, 473, 574, 122, 43, 123]
                }
            ]
        });
    });
</script>
</form>