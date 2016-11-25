<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="inc.jsp"%>
<style type="text/css">
    .current{
        background-color: #0DA2F0;
        color: #fff!important;
    }
    .admin-sidebar-list li *{
        text-decoration:none;
    }
    .admin-sidebar-list li a:hover{
        background-color: #0DA2F0;
        color: #fff!important;
    }
    .admin-sidebar-list li a{
        transition: background-color 0.1s linear,color 0.1s linear;
        -moz-transition: background-color 0.1s linear,color 0.1s linear;
        -webkit-transition: background-color 0.1s linear,color 0.1s linear;
        -o-transition: background-color 0.1s linear,color 0.1s linear;
    }
</style>
    <div class="admin-sidebar am-offcanvas asd" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul id="Mu1" class="am-list admin-sidebar-list">
                <c:if test="${not empty menuList}">
                    <c:forEach items="${menuList}" var="menu">
                        <c:if test="${menu.level == 1}">
                            <li <c:if test="${menu.isChild == 1}"> class="admin-parent"</c:if>>
                                <c:choose>
                                    <c:when test="${menu.isChild == 1}">
                                        <a class="am-cf" data-am-collapse="{target: '#${menu.enName}'}"><span class="${menu.imgIcon}"></span> ${menu.menuName} <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                                        <ul class="am-list am-collapse admin-sidebar-sub" id="${menu.enName}">
                                            <c:forEach items="${menuList}" var="menuChild">
                                                <c:if test="${menu.id==menuChild.parentId}">
                                                    <li><a href="${menuChild.toUrl}"><span class="${menuChild.imgIcon}"></span> ${menuChild.menuName}</a></li>
                                                </c:if>
                                            </c:forEach>
                                        </ul>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${menu.toUrl}"><span class="${menu.imgIcon}"></span> ${menu.menuName}</a>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </c:if>
                    </c:forEach>
                </c:if>
            </ul>

            <div class="am-panel am-panel-default admin-sidebar-panel">
                <div class="am-panel-bd">
                    <p><span class="am-icon-bookmark"></span> 公告</p>
                    <p>时光静好，与君语；细水流年，与君同。—— Amaze UI</p>
                </div>
            </div>

            <div class="am-panel am-panel-default admin-sidebar-panel">
                <div class="am-panel-bd">
                    <p><span class="am-icon-tag"></span> wiki</p>
                    <p>Welcome to the Amaze UI wiki!</p>
                </div>
            </div>
        </div>
    </div>
<script type="text/javascript">
    $(function(){
        //利用 cookie 控制菜单 选中/取消 状态
        var navstation = $.cookie("navstation");
        if(navstation != null){
            $("#Mu1 li[class='admin-parent'] ul li a").each(function(){
                if($(this).html() == navstation){
                    $(this).parent().parent().css("display","block");
                    $(this).addClass("current");
                }
            });
        }
        $("#Mu1 li[class='admin-parent'] ul li a").click(function(){
            $.cookie("navstation", $(this).html(), { path: "/" });
        });
    });
</script>