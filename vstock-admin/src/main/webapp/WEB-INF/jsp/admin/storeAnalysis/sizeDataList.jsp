<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<%-- 传入参数时用 <jsp:param name="parameterName" value="{parameterValue | EL表达式 }" />--%>
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
<!-- content start -->
<div class="admin-content am-text-primary Lg-text-color">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">数据中心</strong> / <small>尺码数据分析</small></br>
            </div>
        </div>
        <hr>
        <div class="am-g">
            <div class="am-u-sm-12 Lg-border-left">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th><input id="whole" type="checkbox"/></th>
                        <th>鞋码</th>
                        <th>均价</th>
                        <th>详情</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="insert-data" class="xy-dis"></tr>
                    <c:if test="${not empty stringBuffer}">
                        ${stringBuffer}
                    </c:if>
                    </tbody>
                </table>
            </div>

            <div>
                <select data-am-selected name="dateLook" id="updateDate" class="lineGraph">
                    <option value="30">显示30天历史数据</option>
                    <option value="15">显示15天历史数据</option>
                    <option value="7">显示7天历史数据</option>
                </select>
            <div id="main" class="Lg-border-right" style="height:500px;width: 50%;float: right;border:1px solid #ccc;padding:10px;"></div>
            </div>
            <c:if test="${not empty resultData}">
                <input type="hidden" id="productName" value="${resultData.productName}">
                <input type="hidden" id="girard" value="${resultData.girard}">
            </c:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function($){
        var testsize = new Array();
        $("#whole").click(function(){
            var a = document.getElementsByTagName("input");
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
            var i = $('#updateDate option:selected').val();
            var $this = $(this);
            updateGraph($this,i);
        });

        /**
         * 点击多选框更新线形图
         */
        $(".lineGraph").change(function () {
            var i = $('#updateDate option:selected').val();
            var $this = $(this);
            updateGraph($this,i);
        });

        $("#hqcx").change(function () {
            $("#colorForm").submit();
        });
        if ($("#colorly").val() != ""){
            $("#hqcx").val($("#colorly").val());
        }
        $(".lineGraph").eq(0).click();

        /**
         * 更新线形图方法
         */
        function updateGraph($this,i){
            // 找到选中行的input后面td的值
            var footagesls = new Array();
            var commodityName = $("#productName").val();
            var colorly = $("#girard").val();
            var commid = $("#commid").val();
            var footage = $(":checkbox:checked").closest('tr').find('td:eq(1)').map(function(){return this.innerHTML}).get().join();
            $.ajax({
                type: 'POST',
                url: '/stockx/dataCore/findLineGraph',
                dataType: 'json',
                data: {
                    "footage": footage,
                    "colorly": colorly,
                    "commodityName": commodityName,
                    "id": commid,
                    "dateTime": i
                }, success: function (res) {
                    var arr = res.arr;
                    footagesls = res.listprice;
                    var averagePricesSS = res.prices;
                    var text = [];
                    var averagePri = [];
                    var footages;
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
                            data:footagesls
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
                                data : arr
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

    });
</script>
<!-- content end -->
<jsp:include page="../common/bottom.jsp" flush="true"/>