<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="inc.jsp"%>
<!-- 分页功能 start -->
<div align="center">
    <ul id="pagingList" class="am-pagination am-fr admin-content-pagination">

        <!-- <li class="am-active"><a href="#">1</a></li>  <li><a href="#">»</a></li>-->

    <c:set var="page" scope="session" value="${param.page}"/>
    <%--<font size="2">共 ${page.totalPageCount} 页</font> --%>
    <c:choose>
        <c:when test="${page.totalPageCount == 0}">
            <font size="2">当前第 <span class="pagenow">${page.pageNow}</span>/<span class="pageCount">${page.pageNow}</span> 页</font>
        </c:when>
        <c:otherwise>
            <font size="2">当前第 <span class="pagenow">${page.pageNow}</span>/<span class="pageCount">${page.totalPageCount}</span> 页</font>
        </c:otherwise>
    </c:choose>
        <c:choose>
            <c:when test="${fn:containsIgnoreCase(linkAddress, '?')}">
                <li><a href="${linkAddress}&pageNow=1">首页</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${linkAddress}?pageNow=1">首页</a></li>
            </c:otherwise>
        </c:choose>
    <c:choose>
        <c:when test="${page.pageNow - 1 > 0}">
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(linkAddress, '?')}">
                    <li><a href="${linkAddress}&pageNow=${page.pageNow - 1}">«</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${linkAddress}?pageNow=${page.pageNow - 1}">«</a></li>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:when test="${page.pageNow - 1 <= 0}">
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(linkAddress, '?')}">
                    <li class="am-disabled"><a href="${linkAddress}&pageNow=1">«</a></li>
                </c:when>
                <c:otherwise>
                    <li class="am-disabled"><a href="${linkAddress}?pageNow=1">«</a></li>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:if test="${page.pageNow <= 3 && page.totalPageCount > 5}">
        <c:choose>
            <c:when test="${fn:containsIgnoreCase(linkAddress, '?')}">
                <li><a href="${linkAddress}&pageNow=1">1</a></li>
                <li><a href="${linkAddress}&pageNow=2">2</a></li>
                <li><a href="${linkAddress}&pageNow=3">3</a></li>
                <li><a href="${linkAddress}&pageNow=4">4</a></li>
                <li><a href="${linkAddress}&pageNow=5">5</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${linkAddress}?pageNow=1">1</a></li>
                <li><a href="${linkAddress}?pageNow=2">2</a></li>
                <li><a href="${linkAddress}?pageNow=3">3</a></li>
                <li><a href="${linkAddress}?pageNow=4">4</a></li>
                <li><a href="${linkAddress}?pageNow=5">5</a></li>
            </c:otherwise>
        </c:choose>
    </c:if>
    <c:if test="${page.pageNow <= 4 && page.totalPageCount <= 5}">
        <c:forEach var="i" begin="1" end="${page.totalPageCount}">
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(linkAddress, '?')}">
                    <li><a href="${linkAddress}&pageNow=${i}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${linkAddress}?pageNow=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:if>
    <c:if test="${page.pageNow > 3 && (page.totalPageCount - page.pageNow) > 1}">
        <c:forEach var="i" begin="${page.pageNow - 2}" end="${page.pageNow}">
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(linkAddress, '?')}">
                    <li><a href="${linkAddress}&pageNow=${i}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${linkAddress}?pageNow=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:if>
    <c:if test="${page.pageNow > 3 && (page.totalPageCount - page.pageNow) > 1}">
        <c:forEach var="i" begin="${page.pageNow + 1}" end="${page.pageNow + 2}">
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(linkAddress, '?')}">
                    <li><a href="${linkAddress}&pageNow=${i}">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${linkAddress}?pageNow=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:if>
    <c:if test="${(page.totalPageCount - page.pageNow) <= 1 && page.pageNow > 4}">
        <c:choose>
            <c:when test="${fn:containsIgnoreCase(linkAddress, '?')}">
                <li><a href="${linkAddress}&pageNow=${page.totalPageCount - 4}">${page.totalPageCount - 4}</a></li>
                <li><a href="${linkAddress}&pageNow=${page.totalPageCount - 3}">${page.totalPageCount - 3}</a></li>
                <li><a href="${linkAddress}&pageNow=${page.totalPageCount - 2}">${page.totalPageCount - 2}</a></li>
                <li><a href="${linkAddress}&pageNow=${page.totalPageCount - 1}">${page.totalPageCount - 1}</a></li>
                <li><a href="${linkAddress}&pageNow=${page.totalPageCount}">${page.totalPageCount}</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${linkAddress}?pageNow=${page.totalPageCount - 4}">${page.totalPageCount - 4}</a></li>
                <li><a href="${linkAddress}?pageNow=${page.totalPageCount - 3}">${page.totalPageCount - 3}</a></li>
                <li><a href="${linkAddress}?pageNow=${page.totalPageCount - 2}">${page.totalPageCount - 2}</a></li>
                <li><a href="${linkAddress}?pageNow=${page.totalPageCount - 1}">${page.totalPageCount - 1}</a></li>
                <li><a href="${linkAddress}?pageNow=${page.totalPageCount}">${page.totalPageCount}</a></li>
            </c:otherwise>
        </c:choose>
    </c:if>
    <c:choose>
        <c:when test="${page.totalPageCount==0}">
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(linkAddress, '?')}">
                    <li><a href="${linkAddress}&pageNow=${page.pageNow}">»</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${linkAddress}?pageNow=${page.pageNow}">»</a></li>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:when test="${page.pageNow + 1 < page.totalPageCount + 1}">
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(linkAddress, '?')}">
                    <li><a href="${linkAddress}&pageNow=${page.pageNow + 1}">»</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${linkAddress}?pageNow=${page.pageNow + 1}">»</a></li>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:when test="${page.pageNow + 1 >= page.totalPageCount + 1}">
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(linkAddress, '?')}">
                    <li class="am-disabled"><a href="${linkAddress}&pageNow=${page.totalPageCount}">»</a></li>
                </c:when>
                <c:otherwise>
                    <li class="am-disabled"><a href="${linkAddress}?pageNow=${page.totalPageCount}">»</a></li>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:choose>
        <c:when test="${page.totalPageCount==0}">
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(linkAddress, '?')}">
                    <li><a href="${linkAddress}&pageNow=${page.pageNow}">尾页</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${linkAddress}?pageNow=${page.pageNow}">尾页</a></li>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${fn:containsIgnoreCase(linkAddress, '?')}">
                    <li><a href="${linkAddress}&pageNow=${page.totalPageCount}">尾页</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${linkAddress}?pageNow=${page.totalPageCount}">尾页</a></li>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>

    <!-- 直接跳转 -->
    跳转至 <input type="text"  id="jumpTo" size="3px"/> 页
        <button type="button" class="am-btn am-btn-primary am-radius am-btn-xs" onclick="jumpTo(${page.totalPageCount})" >跳转</button>
    </ul>
</div>
<!-- 分页功能 End -->
<script src="../assets/admin/js/jquery.toaster.js" type="text/javascript"></script>
<script type="text/javascript">
    jQuery(function($){
       $("#pagingList>li>a").each(function(){
           var pageNowList = $(this).text();
           var pageNow = $(".pagenow").text();
           if(pageNow == pageNowList){
               $(this).parent().addClass("am-active");
           }
       });
        var optionHtml = "";
        var pageCount = $(".pageCount").text();
        for(var i = 1;i < pageCount;i++){
            optionHtml = optionHtml + '<option value="'+i+'">'+i+'</option>';
        }
        $("#toPageNum").append(optionHtml);

    });
    function jumpTo(maxPage) {
        var page = $("#jumpTo").val();
        if(page > maxPage || page < 1){
            $.toaster({ priority : 'warning', title : '警告', message : '页码信息错误，请重新输入~'});
        }else{
            var url = '${linkAddress}';
            var sear = '?';
            if (url.indexOf(sear)!= -1){
                location.href = '${linkAddress}&pageNow=' + page;
            }else {
                location.href = '${linkAddress}?pageNow=' + page;
            }
        }
    }
</script>