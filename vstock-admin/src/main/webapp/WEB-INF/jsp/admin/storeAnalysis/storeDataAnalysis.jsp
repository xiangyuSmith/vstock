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
</style>
<!-- content start -->
<div class="admin-content am-text-primary Lg-text-color">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf">
                <strong class="am-text-primary am-text-lg">数据中心</strong> /
                <small>店铺尺码鞋品分析</small>
                <small>当前鞋码:${footage}</small>
            </div>
        </div>
        <div class="am-cf am-padding">
            <form action="storeDataAnalysis" class="am-form am-form-inline" method="post">
                <%--<div class="xy-search">--%>
                    <%--<label>鞋码:</label>--%>
                    <%--<label>${footage}</label>--%>
                <%--</div>--%>

                <div class="xy-search Lg-border-left">
                    <label>店铺:&nbsp;&nbsp;</label>
                    <div class="am-form-group"><input type="text" id="storeStockx" name="storeStockx" placeholder="店铺" value="${storeStockx}"></div>
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
                <input type="hidden" name="shopName" value="${storeName}">
                <input type="hidden" name="footage" value="${footage}">
                <input type="submit" class="am-btn am-btn-primary btn-loading-example xy-submit" value=" 查 询 " />
            </form>
        <hr>
        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th>店铺</th>
                        <th>名称</th>
                        <th>鞋码</th>
                        <th>获取时间</th>
                        <th>价格</th>
                        <th>详情</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="insert-data" class="xy-dis"></tr>
                    <c:if test="${not empty resultDatalist}">
                        <c:forEach items="${resultDatalist}" var="re">
                            <tr>
                                <td>${re.storeName}</td>
                                <td>${re.productName}</td>
                                <td>${re.sizePrice}</td>
                                <td>${re.createTime}</td>
                                <td>${re.reservedField}</td>
                                <td><a href="/stockx/dataCore/getStoreTrend?storeName=${re.storeName}&productName=${re.productName}&footage=${footage}">详情</a></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    jQuery(function($){

        var div1 = "#dt";
        var div2 = "#dtd";
        getDatePic(div1,div2);
    });
</script>
<!-- content end -->
<jsp:include page="../common/bottom.jsp" flush="true"/>