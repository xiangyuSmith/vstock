<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">出价信息</strong> / <small>叫价查询</small></div>

        </div>
        <hr>
        <div class="am-g">
            <c:if test="${not empty record}">
                <form action="/trade/pricepeak" method="post">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-lg am-text-middle">货号：</span>
                            <input type="text" class="am-input-lg" name="artNo" id="artNo" placeholder="货号" value="${record.basicinformation.artNo}"/>
                        </div>

                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-lg am-text-middle">球鞋名称：</span>
                            <input type="text" class="am-input-lg" name="name" id="name" placeholder="球鞋名称" value="${record.basicinformation.name}"/>
                        </div>

                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-lg am-text-middle">尺码：</span>
                            <select class="am-input-lg" name="peakSize" id="peakSize">
                                <option value="-1">请选择</option>
                                <c:if test="${not empty sizeList}">
                                    <c:forEach items="${sizeList}" var="sizes">
                                        <c:choose>
                                            <c:when test="${record.peakSize == sizes}">
                                                <option value="${sizes}" selected = "selected">${sizes}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${sizes}">${sizes}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <button href="javascript: void(0);" type="submit" class="am-btn am-btn-primary am-fr">查询</button>
                    </div>
                </form>
            </c:if>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th>货号</th>
                        <th>图片</th>
                        <th width="20%">球鞋名称</th>
                        <th>尺码</th>
                        <th>卖家最低出价</th>
                        <th>买家最高叫价</th>
                        <th>最新叫价时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="insert-data" class="xy-dis"></tr>
                    <c:if test="${not empty pricePeakList}">
                        <c:forEach items="${pricePeakList}" var="record">
                            <tr>
                                <td>${record.basicinformation.artNo}</td>
                                <td><a href="${record.basicinformation.imgUrl}" target="_blank" title="查看大图"><img src="${record.basicinformation.smallImgUrl}" style="height: 40px; width: 55px;"/></a></td>
                                <td>${record.basicinformation.name}</td>
                                <td>${record.peakSize}</td>
                                <td><fmt:formatNumber value="${record.minimumSellingPrice}" type="currency" pattern="#,##0.00#"/></td>
                                <td><fmt:formatNumber value="${record.highestBid}" type="currency" pattern="#,##0.00#"/></td>
                                <td>${record.createDate}</td>
                                <td><a href="/trade/bidindex?basicinformationId=${record.basicinformationId}&bftSize=${record.peakSize}" style="color: blue;">全部价格</a></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
                <jsp:include page="../common/paging.jsp" flush="true">
                    <jsp:param name="page" value="${page}"/>
                    <jsp:param name="linkAddress" value="${linkAddress}"/>
                </jsp:include>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/bottom.jsp" flush="true"/>
<script type="text/javascript">
    jQuery(function($){
    });
</script>