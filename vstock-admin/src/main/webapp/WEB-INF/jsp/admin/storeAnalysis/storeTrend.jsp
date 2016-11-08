<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<style type="text/css">
    .am-g{
        padding-bottom: 15px;
    }
    .xy-dis{
        display: none;
    }
    .xy-input-set{
        padding-left:5px;
    }
    .Lg-text-color{
        color: #1a1a1a;
    }
    .Lg-test-size{
        font-size: 20px;
        font-weight: bold;
    }
    .Lg-border-left{
        width: 50%;
        border: 1px;
        float: left;
    }
    .Lg-border-right{
        width: 50%;
        border: 1px;
        float: right;
    }
</style>
<div class="admin-content am-text-primary Lg-text-color">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">数据中心</strong> / <small>尺码数据分析</small></br>
            </div>
        </div>
        <div class="am-cf am-padding" style="    padding-top: 0;">
            <form action="getStoreTrend" class="am-form am-form-inline" method="post">
                <div class="xy-search">
                    <div>
                        <label style="font-weight: bold;">店铺名称:</label>
                        <label>${storeName}</label>
                    </div>
                    <div>
                        <label style="font-weight: bold;">商品名称:</label>
                        <label>${productName}</label>
                    </div>
                    <div>
                        <label style="font-weight: bold;">当前鞋码:</label>
                        <label>${footage}</label>
                    </div>
                </div>

                <input type="hidden" name="storeName" value="${storeName}" />
                <input type="hidden" name="productName" value="${productName}" />
                <input type="hidden" name="footage" value="${footage}" />
                <%--<input type="submit" class="am-btn am-btn-primary btn-loading-example xy-submit" value=" 查 询 ">--%>
            </form>
            <hr>
            <div class="am-g">
                <div class="am-u-sm-12 Lg-border-left">
                    <table class="am-table am-table-bd am-table-striped admin-content-table">
                        <thead>
                        <tr>
                            <th><input id="whole" type="checkbox"/></th>
                            <th>类型</th>
                            <th>价格</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr id="insert-data" class="xy-dis"></tr>
                            <c:if test="${not empty resultDatalist}">
                                <c:forEach items="${resultDatalist}" var="re">
                                    <tr>
                                        <td><input class="lineGraph" type="checkbox" /></td>
                                        <td class="size">${re.sizePrice}</td>
                                        <td>${re.reservedField}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
                <div class="xy-search" style="width: 21%;margin: 0;">
                    <label style="display: block;float: left;">开始时间:</label>
                    <div class="am-form-group">
                        <input type="text" id="dt" name="startTime" placeholder="" value="${startTime}">
                        <div class="dateStartTime"></div>
                    </div>
                </div>
                <div class="xy-search" style="width: 21%;margin: 0;">
                    <label style="display: block;float: left;">结束时间:</label>
                    <div class="am-form-group">
                        <input type="text" id="dtd" name="endTime" placeholder="" value="${endTime}">
                        <div class="dateEndTime"></div>
                    </div>
                </div>
                <input type="button" id="searchBytime" class="am-btn am-btn-primary btn-loading-example" value=" 查询 ">

                <div id="main" class="Lg-border-right" style="height:500px;width: 50%;float: right;border:1px solid #ccc;padding:10px;"></div>

            </div>
        </div>
    </div>
</div>

<div>
    <input type="hidden" id="timeList" value="${timeList}" />
    <input type="hidden" id="priceList" value="${priceList}" />
    <input type="hidden" id="footage" value="${footage}" />
    <input type="hidden" id="storeName" value="${storeName}" />
    <input type="hidden" id="productName" value="${productName}" />
</div>

<script type="text/javascript">
    jQuery(function($){
        var footage = $("#footage").val();
        var storeName = $("#storeName").val();
        var productName = $("#productName").val();

        var div1 = "#dt";
        var div2 = "#dtd";
        getDatePic(div1,div2);

        $("#whole").click(function(){
            var a = $("input[type='checkbox']");
            if(a[0].checked){
                for(var i = 0;i<a.length;i++){
                    if(!a[i].disabled){
                        if(a[i].type == "checkbox") a[i].checked = true;
                    }
                }
            }
            else{
                for(var i = 0;i<a.length;i++){
                    if(!a[i].disabled) {
                        if (a[i].type == "checkbox") a[i].checked = false;
                    }
                }
            }
            updateGraph();
        });
        /**
         * 点击多选框更新线形图
         */
        $(".lineGraph").change(function () {
            var $this = $(this);
            var sizeView = $(this).parent().siblings("td[class='size']").text();
            updateGraph(sizeView);
        });

        $("#searchBytime").click(function(){
            updateGraph();
        })

        function updateGraph(sizeView){
            var sizeViews = $(":checkbox:checked").closest('tr').find('td:eq(1)').map(function(){return this.innerHTML}).get().join();
            var dt = $("#dt").val();
            var dtd = $("#dtd").val();
            $.ajax({
                type: 'POST',
                url: '/stockx/dataCore/getResultDataLineGraph',
                dataType: 'json',
                data: {
                    "footage": footage,
                    "storeName": storeName,
                    "productName": productName,
                    "sizeViews" :　sizeViews,
                    "startTime":dt,
                    "endTime":dtd
                }, success: function (res) {

                    var timeList = [];
                    var priceList = [];
//                    var time = $("#timeList").val().substr(1,$("#timeList").val().length-2);
//                    var price  = $("#priceList").val().substr(1,$("#priceList").val().length-2);
//                    var time = res.timeList.substr(1,res.timeList.length-2);
//                    var price  = res.priceList.substr(1,res.priceList.length-2);

//                    timeList = time.split(",");
//                    priceList = price.split(",");
                    var footagesls = res.sizeViewsResult;
                    var averagePricesSS = res.result;
                    var text = [];
                    var averagePri = [];
                    var footages = "";
                    for(var i = 0; i < footagesls.length; i++){
                        footages = footagesls[i];
                        averagePri = averagePricesSS[i];
                        //封装图形数据
                        text.push({name: footages, type: 'bar', data: averagePri});
                    }
                    var myChart = echarts.init(document.getElementById('main'));
                    myChart.setOption({
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            data:res.sizeViewsResult
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                dataView : {show: true, readOnly: false},
                                magicType : {show: true, type: ['line', 'bar']},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        calculable : true,
                        xAxis : [
                            {
                                type : 'category',
                                data :  res.timeList
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value',
                                splitArea : {show : true}
                            }
                        ],
                        series : text
                    });


                }
            });
        }
//        var timeList = [];
//        var priceList = [];
//        var time = $("#timeList").val().substr(1,$("#timeList").val().length-2);
//        var price  = $("#priceList").val().substr(1,$("#priceList").val().length-2);
//        var footage = $("#footage").val();
//        timeList = time.split(",");
//        priceList = price.split(",");
//        var myChart = echarts.init(document.getElementById('main'));
//        myChart.setOption({
//            tooltip : {
//                trigger: 'axis'
//            },
//            legend: {
//                data:[footage]
//            },
//            toolbox: {
//                show : true,
//                feature : {
//                    dataView : {show: true, readOnly: false},
//                    magicType : {show: true, type: ['line', 'bar']},
//                    restore : {show: true},
//                    saveAsImage : {show: true}
//                }
//            },
//            calculable : true,
//            xAxis : [
//                {
//                    type : 'category',
//                    data :  timeList
//                }
//            ],
//            yAxis : [
//                {
//                    type : 'value',
//                    splitArea : {show : true}
//                }
//            ],
//            series : [
//                {
//                    name:footage,
//                    type:'bar',
//                    data:priceList
//                }
//            ]
//        });
    });
</script>
<!-- content end -->
<jsp:include page="../common/bottom.jsp" flush="true"/>
