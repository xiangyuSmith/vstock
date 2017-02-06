<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<form id="buysell" action="/user/buysell" method="post">
    <table class="am-table am-table-striped am-table-hover">
        <caption>
            <p class="am-fl layout-font-size-30">资产明细</p>
        </caption>
        <thead>
        <tr>
            <th style="width: 30%;">球鞋名称</th>
            <th>尺码</th>
            <th>购买日期</th>
            <th>购买金额</th>
            <th>当前市场价</th>
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
                                    <c:when test="${userAssets.basicinformationRose.type == 0}">
                                        <td>-<fmt:formatNumber value="${userAssets.basicinformationRose.change_range}" type="number" pattern="0.00%"/></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><fmt:formatNumber value="${userAssets.basicinformationRose.change_range}" type="number" pattern="0.00%"/></td>
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
    </table>
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
    <c:choose>
        <c:when test="${page.pageNow == 1}">
            <a href="javascript:void(0)" data-url="../user/userAssetsList?pageNow=1" class="offer-btn am-btn am-btn-primary am-radius" disabled="disabled ">上一页</a>
        </c:when>
        <c:otherwise>
            <a href="javascript:void(0)" data-url="../user/userAssetsList?pageNow=${page.pageNow-1}" class="offer-btn am-btn am-btn-primary am-radius">上一页</a>
        </c:otherwise>
    </c:choose>
    <span class="am-margin-left-sm am-margin-right-sm layout-font-size-20">当前第${page.pageNow}页/共${page.totalPageCount}页</span>
    <c:choose>
        <c:when test="${page.pageNow == page.totalPageCount}">
            <a href="javascript:void(0)" data-url="../user/userAssetsList?pageNow=1" class="offer-btn am-btn am-btn-primary am-radius" disabled="disabled">下一页</a>
        </c:when>
        <c:otherwise>
            <a href="javascript:void(0)" data-url="../user/userAssetsList?pageNow=${page.pageNow+1}" class="offer-btn am-btn am-btn-primary am-radius">下一页</a>
        </c:otherwise>
    </c:choose>
</div>
</form>
<script>
    $(function(){
        $(".doc-dropdown-js").each(function(index){
            $(this).dropdown({justify: '.doc-dropdown-justify-js:eq('+index+')'});
        });
    });
</script>