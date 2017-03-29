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
</style>
<!-- content start -->
<div class="admin-content am-text-primary Lg-text-color">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">数据中心</strong> / <small>球鞋数据分析</small></div>
        </div>
        <div class="am-cf am-padding">
            <form action="/dataCore/index" class="am-form am-form-inline" method="post">
                <div class="xy-search">
                    <label>编号:</label>
                    <div class="am-form-group"><input type="text" name="basiciformationId" placeholder="编号" value="<c:if test='${resultData.basiciformationId != 0}'>${resultData.basiciformationId}</c:if>"></div>
                </div>
                <div class="xy-search">
                    <label>商品名称:</label>
                    <div class="am-form-group"><input type="text" name="productName" placeholder="商品名称" value="${resultData.productName}"></div>
                </div>
                <div class="xy-search">
                    <label>货号:</label>
                    <div class="am-form-group"><input type="text" name="girard" placeholder="货号" value="${resultData.girard}"></div>
                </div>
                <div class="xy-search">
                    <label>开始时间:</label>
                    <div class="am-form-group">
                        <%--<input type="date" name="startTime" value="${startTime}"/>--%>
                        <input type="text" id="dt" name="startTime" placeholder="" value="${startTime}">
                        <div class="dateStartTime"></div>
                    </div>
                </div>
                <div class="xy-search">
                    <label>结束时间:</label>
                    <div class="am-form-group">
                        <%--<input type="date" name="endTime" value="${endTime}" />--%>
                        <input type="text" id="dtd" name="endTime" placeholder="" value="${endTime}">
                        <div class="dateEndTime"></div>
                    </div>
                </div>
                <input type="submit" class="am-btn am-btn-primary btn-loading-example xy-submit" value=" 查 询 " />
            </form>

            <!--详细提交form表单-->
            <form action="/dataCore/findMeasurement" method="post" id="details">
                <input type="hidden" id="commid" name="id"/>
                <input type="hidden" id="commodityName" name="commodityName"/>
            </form>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <%--<th>店铺名称</th>--%>
                        <th>商品名称</th>
                        <th>货号</th>
                        <th>销售量(总)</th>
                        <th>时间</th>
                        <th>详情</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="insert-data" class="xy-dis"></tr>
                    <c:if test="${not empty commodityDataList}">
                        <c:forEach items="${commodityDataList}" var="basicinformation">
                            <tr>
                                    <td>${basicinformation.id}</td>
                                    <td>${basicinformation.name}</td>
                                    <td>${basicinformation.artNo}</td>
                                    <td>${basicinformation.resultData.transactionRecord}</td>
                                    <td>${basicinformation.resultData.createTime}</td>
                                    <input type="hidden" name="id" value="${basicinformation.resultData.id}"/>
                                    <td><a href="javascript:void(0)" class="adetails" data-comid="${basicinformation.resultData.id}" data-commoName="${basicinformation.name}">详情</a></td>

                                <%--<td>${commodityData.basicinformation.id}</td>--%>
                                <%--<td>${commodityData.basicinformation.name}</td>--%>
                                <%--<td>${commodityData.basicinformation.artNo}</td>--%>
                                <%--<td>${commodityData.transactionRecord}</td>--%>
                                <%--<td>${commodityData.createTime}</td>--%>
                                <%--<input type="hidden" name="id" value="${commodityData.id}"/>--%>
                                <%--<td><a href="javascript:void(0)" class="adetails" data-comid="${commodityData.id}" data-commoName="${commodityData.productName}">详情</a></td>--%>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
                <!-- 分页 -->
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function($){

        var div1 = "#dt";
        var div2 = "#dtd";
        getDatePic(div1,div2);

        $("#add").click(function(){
            if($('#insert-data').css("display")=='none'){
                var tr = '<td><input type="text" id="storeName" class="am-input-sm xy-input-set" placeholder="请填写店铺名称"></td><td><input type="text" id="storeUrl" class="am-input-sm xy-input-set" placeholder="请填写店铺链接"></td><td><span class="am-badge am-badge-success">可用</span></td><td>——</td><td><button type="button" id="save-data" class="am-btn am-btn-xs am-btn-default"><span class="am-icon-save"></span> 保存</button><button type="button" id="delete-data" class="am-btn am-btn-xs am-btn-default"><span class="am-icon-trash-o"></span> 删除</button></div></td>'
                $('#insert-data').append(tr).fadeIn(200);
            }
        });

        <!--点击详细触发事件-->
        $(".adetails").click(function () {
            var comid = $(this).attr("data-comid");
            var commoName = $(this).attr("data-commoName");
            if (comid != ""){
                $("#commid").val(comid);
            }
            if(commoName != ""){
                $("#commodityName").val(commoName);
            }
            $("#details").submit();
        });

        //绑定添加事件
        $("body").on("click","#save-data",function(){
            var storeName = $("#storeName").val();
            var storeUrl = $("#storeUrl").val();
            $.post("/stockx/StockxStoreController/insert",{
                'storeName': storeName,
                'storeUrl' : storeUrl
            },function(res){
                setTimeout(function(){window.location.reload();},50);
            });
        });

        //绑定删除事件
        $("body").on("click","#delete-data",function(){
            $('#insert-data').html("");
            $('#insert-data').fadeOut(200);
        });
    });
</script>
<!-- content end -->
<jsp:include page="../common/bottom.jsp" flush="true"/>
