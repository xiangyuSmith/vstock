<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<style>
    .hchar_btn{background-color: #FF4A4E!important; color:#FBFDF5!important; }
    .hchar_sel{background-color: #FFFFFF!important;}
</style>
<article class="am-g">
    <div class="am-container-content" style="margin-top: 2.4rem">
        <div class="am-u-lg-12 am-margin-bottom-lg">
            <div class="am-text-center">
                <p style="color:#e36471;font-size: 22px;margin-top: 38px; font-size: 30px;">
                    ${bname}
                </p>
                <p style="color: #595959;font-size: 18px;">
                    最后销售记录
                </p>
            </div>
        </div>
        <div>
            <c:choose>
                <c:when test="${not empty tradeList}">
                    <table class="am-table am-table-striped am-table-hover am-text-center">
                        <thead>
                        <tr>
                            <td>尺码</td>
                            <td>价格</td>
                            <td>日期</td>
                            <td>时间</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${tradeList}" var="trade">
                            <tr>
                                <td>${trade.bftSize}</td>
                                <td>￥
                                    <fmt:formatNumber value="${trade.transactionMoney}" type="currency" pattern="#,##0.0#"/>
                                </td>
                                <td><c:out value="${fn:substring(trade.transactionDate, 0, 4)}" />/<c:out value="${fn:substring(trade.transactionDate, 5, 7)}" />/<c:out value="${fn:substring(trade.transactionDate,8, 10)}" /></td>
                                <td><c:out value="${fn:substring(trade.transactionDate, 10, 16)}" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="am-text-center">
                        <span>还没有销售记录哦，赶紧叫价交易吧！</span>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <%@include file="../detail/salehchar.jsp" %>
    </div>
</article>
<article class="am-g am-margin-top-xl">
    <div class="am-container-content">
        <div class="am-u-lg-12">
            <div class="am-text-center">
                <p style="color:#fe5c5c;font-size: 28px;">
                    相关推荐>
                </p>
            </div>
        </div>
    </div>
</article>
<div  style="border-bottom: 1px solid #D6D6D6;"></div>
<article class="am-g am-margin-bottom-xl">
    <div class="am-container-content">
        <ul data-am-widget="gallery" class="am-gallery am-avg-sm-3 am-avg-md-4 am-avg-lg-4 am-gallery-default">
            <c:forEach items="${bList}" var="basicinfortion" varStatus="idx">
                <li>
                    <div class="am-gallery-item am-padding-lg am-padding-right-sm" style="<c:if test="${idx.index==0}">border-left: 1px solid #ccc;</c:if>border-right: 1px solid #ccc;height: 280px;">
                        <a href="/detail?proName=${basicinfortion.name}" class="am_img_bg">
                            <img src="${configMap._site_url}${basicinfortion.smallImgUrl}" style="display: inline;">
                            <span class="clickZoneName">${basicinfortion.name}</span>
                        </a>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</article>

