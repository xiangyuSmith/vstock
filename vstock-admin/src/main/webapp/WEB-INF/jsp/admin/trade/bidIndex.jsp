<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">出价信息</strong> / <small>叫价流水</small></div>

        </div>
        <hr>
        <div class="am-g">
            <c:if test="${not empty record}">
                <form action="/trade/bidindex" method="post">
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                            <span class="am-text-lg am-text-middle">货号：</span>
                            <input type="text" class="am-input-lg" name="artNo" id="artNo" placeholder="货号" value="${record.basicinformation.artNo}"/>
                        </div>
                        <div class="am-u-sm-6 am-u-md-6 am-u-lg-6">
                            <span class="am-text-lg am-text-middle">球鞋名称：</span>
                            <input type="text" class="am-input-lg" name="bftName" id="bftName" placeholder="球鞋名称" value="${record.bftName}"/>
                        </div>
                    </div>
                    <%--<div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-sm">--%>
                        <%--<div class="am-u-sm-6 am-u-md-6 am-u-lg-6">--%>
                            <%--<span class="am-text-lg am-text-middle">最小出价金额：</span>--%>
                            <%--<input type="number" class="am-input-lg" id="minimumMoney" name="minimumMoney" placeholder="最小出价金额" value="${minimumMoney}">--%>
                            <%--&lt;%&ndash;<div class="dateStartTime"></div>&ndash;%&gt;--%>
                        <%--</div>--%>
                        <%--<div class="am-u-sm-6 am-u-md-6 am-u-lg-6">--%>
                            <%--<span class="am-text-lg am-text-middle">最大出价金额：</span>--%>
                            <%--<input type="number" class="am-input-lg" id="maximumMoney" name="maximumMoney" placeholder="最大出价金额" value="${maximumMoney}">--%>
                            <%--&lt;%&ndash;<div class="dateEndTime"></div>&ndash;%&gt;--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-lg am-text-middle">尺码：</span>
                            <select class="am-input-lg" name="bftSize" id="bftSize">
                                <option value="0">请选择</option>
                                <c:if test="${not empty sizeList}">
                                    <c:forEach items="${sizeList}" var="sizes">
                                        <c:choose>
                                            <c:when test="${record.bftSize == sizes}">
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
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-lg am-text-middle">状态：</span>
                            <select class="am-input-lg" name="status" id="status">
                                <option value="-1">请选择</option>
                                <c:if test="${not empty statusList}">
                                    <c:forEach items="${statusList}" var="status">
                                        <c:choose>
                                            <c:when test="${record.status == status.status}">
                                                <option value="${status.status}" selected = "selected">${status.bftName}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${status.status}">${status.bftName}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                            <span class="am-text-lg am-text-middle">类型：</span>
                            <select class="am-input-lg" name="type" id="type">
                                <option value="-1">--请选择--</option>
                                <c:choose>
                                    <c:when test="${record.type == 0}">
                                        <option value="0" selected = "selected">买家叫价</option>
                                        <option value="1">卖家出价</option>
                                    </c:when>
                                    <c:when test="${record.type == 1}">
                                        <option value="1" selected = "selected">卖家出价</option>
                                        <option value="0">买家叫价</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">卖家出价</option>
                                        <option value="1">买家叫价</option>
                                    </c:otherwise>
                                </c:choose>
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
                        <th>叫价人</th>
                        <th width="20%">球鞋名称</th>
                        <th>货号</th>
                        <th>尺码</th>
                        <th>叫价金额</th>
                        <th>保证金</th>
                        <th>有效期</th>
                        <th>状态</th>
                        <th>叫价类型</th>
                        <th>叫价日期</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="insert-data" class="xy-dis"></tr>
                    <c:if test="${not empty bidList}">
                        <c:forEach items="${bidList}" var="bid">
                            <tr>
                                <td>${bid.name}</td>
                                <td>${bid.basicinformation.name}</td>
                                <td>${bid.basicinformation.artNo}</td>
                                <td>${bid.bftSize}</td>
                                <td><fmt:formatNumber value="${bid.bidMoney}" type="currency" pattern="#,##0.00#"/></td>
                                <td><fmt:formatNumber value="${bid.bidBond}" type="currency" pattern="#,##0.00#"/></td>
                                <td>${bid.termValidity}</td>
                                <td>
                                <c:if test="${not empty statusList}">
                                    <c:forEach items="${statusList}" var="status">
                                        <c:if test="${bid.status == status.status}">${status.bftName}</c:if>
                                    </c:forEach>
                                </c:if>
                                </td>
                                <c:if test="${bid.type == 0}">
                                    <td>卖家</td>
                                </c:if>
                                <c:if test="${bid.type == 1}">
                                    <td>买家</td>
                                </c:if>
                                <c:set var="bidDate" value="${fn:substring(bid.bidDate, 0, 19)}"/>
                                <td>${bidDate}</td>
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