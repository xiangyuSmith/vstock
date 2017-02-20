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
                                    <c:when test="${userAssets.basicinformationRose.type == 0 && userAssets.basicinformationRose.change_range > 0}">
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
                        <td>
                            <div class="doc-dropdown-justify-js">
                                <div class="am-dropdown doc-dropdown-js">
                                    <a class="am-btn am-btn-default am-btn-xs am-dropdown-toggle status-user-set" select_type="select-btn"><span class="am-icon-caret-down"></span></a>
                                    <ul class="am-dropdown-content">
                                        <li>
                                            <a href="/detail?proName=${userAssets.basicinformation.name}&size=${userAssets.userAssetsSize}" style="min-width: 100px;">
                                                <span class="am-fl" style="display: block;width: 30px;height: 45px; background: url('/assets/i/personal_center_map.png'); background-position: -1539px -17px;"></span>
                                                <span class="am-margin-left-sm am-link-muted" style="line-height: 39px;">出售</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void(0);" style="min-width: 100px;">
                                                <span class="am-fl" style="display: block;width: 30px;height: 34px; background: url('/assets/i/personal_center_map.png'); background-position: -794px -22px;" class="offer-btn" data-url="../user/userAssets"></span>
                                                <span class="am-margin-left-sm am-link-muted userAssets-del" btf-id="${userAssets.basicinformationId}" del_data_id="${userAssets.id}">删除</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
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
</form>
<script>
    $(function(){
        $(".doc-dropdown-js").each(function(index){
            $(this).dropdown({justify: '.doc-dropdown-justify-js:eq('+index+')'});
        });

//        $(".userAssets-del").click(function(){
//            var $this = $(this);
//            $('#userAssets_btf_id').val($this.attr("btf-id"));
//            $('#userAssets_del_data_id').val($this.attr("del_data_id"));
//            $('#userAssets_del_money').val($this.parent().parent().parent().prev().prev().prev().text());
//            $('#userAssets_del_size').val($this.parent().parent().parent().prev().prev().prev().prev().prev().text());
//            alertConfirm("是否需要删除！","","");
//        });
    });
</script>