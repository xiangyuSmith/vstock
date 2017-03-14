<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="inc.jsp" %>
<style>
    .x-ul li {
        display: block;
        margin-right:2px;
    }
    .x-ul li a
    {
        display: block;
        padding: .4em 1em;
        color: #fff!important;
    }
    .am-sticky .x-ul li a{
        color: #000!important;
    }
    .x-ul li ul li a{ color: #000!important; }
</style>
<header class="m-hd am-margin-bottom-0">
    <section data-am-sticky class="am-show-md-up" style="height: 80px;padding: 15px 0;">
        <div style="line-height: 30px;">
            <div class="am-u-md-12">
                <div class="am-u-md-6">
                    <a href="/index" class="v-logo am-fr" style="display: none;"></a>
                    <form id="am-form-field" class="am-topbar-left am-form-inline" role="search" style="width:100%;display: none;">
                        <div class="am-form-group am-form-icon am-fr" style="color: #EB615F;font-size: 16px;width:50%;">
                            <input type="text" class="am-form-field index_search_top" placeholder="搜索颜色、货号......" style="width:100%;margin-top: 8px;" />
                            <i class="am-icon-search" style="margin-top:-3px;"></i>
                        </div>
                    </form>
                </div>
                <div class="am-u-md-6">
                    <nav>
                        <ul class="m-nav x-ul am-nav-pills am-fr" style="margin:5px;">
                            <li class="am-dropdown" data-am-dropdown="">
                                <a href="#" rel="nofollow" class="am-dropdown-toggle" data-am-dropdown-toggle="">所有商品 <i class="am-icon-caret-down"></i></a>
                                <ul class="am-dropdown-content">
                                    <c:forEach items="${brandList}" var="brand" >
                                        <li><a href="/sorts?brandName=${brand}" rel="nofollow">${brand}</a></li>
                                    </c:forEach>
                                </ul>
                            </li>
                            <li class="am-hide-sm am-hide-md"><a href="/sorts?type=5" rel="nofollow">即将发布</a></li>
                            <li class="am-dropdown" data-am-dropdown="">
                                <a href="#" rel="nofollow" class="am-dropdown-toggle" data-am-dropdown-toggle="">帮助中心 <i class="am-icon-caret-down"></i></a>
                                <ul class="am-dropdown-content">
                                    <li><a href="/index/noviceGuide" rel="nofollow">新手指引</a></li>
                                    <li><a href="/index/problem" rel="nofollow">常见问题</a></li>
                                </ul>
                            </li>
                            <li class="am-dropdown" data-am-dropdown="">
                                <c:choose>
                                    <c:when test="${not empty vUser}">
                                        <a href="${ctx}/user/index" rel="nofollow" class="am-dropdown-toggle" data-am-dropdown-toggle=""><span>欢迎您：</span>${vUser.nick} &nbsp;<i class="am-icon-caret-down"></i></a>
                                        <ul class="am-dropdown-content">
                                            <li><a href="${ctx}/user/index?type=0" rel="nofollow">个人中心</a></li>
                                            <li><a href="javascript:;" class="login-out" rel="nofollow">注销</a></li>
                                        </ul>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="javascript:;" rel="nofollow" data-am-modal="{target: '#my-popup-login', width: 350}">注册/登录</a>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                            <li style="display: none;"><a href="/sorts" class="v-a-boder" rel="nofollow" style="color: #EA5958!important;">SELL</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </section>
    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#doc-topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav am-show-sm-only" style="background-color: #f8f8f8">
            <li>
                <form class="am-topbar-left am-form-inline" role="search">
                    <div class="am-form-group am-form-icon" style="color: #EB615F;font-size: 16px;">
                        <input type="text" class="am-form-field index_search_top" placeholder="搜索颜色、款式......">
                        <i class="am-icon-search"></i>
                    </div>
                </form>
            </li>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    本店所有商品 <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li class="am-active"><a href="#">分类1...</a></li>
                    <li><a href="#">分类2...</a></li>
                    <li><a href="#">分类3...</a></li>
                </ul>
            </li>
            <li class="am-active"><a href="#">即将发布</a></li>
            <li><a href="#">作品集</a></li>
            <li><a href="#">常见问题</a></li>
            <li class="am-dropdown" data-am-dropdown>
                <c:choose>
                    <c:when test="${not empty vUser}">
                        <a href="${ctx}/user/index" rel="nofollow" class="am-dropdown-toggle" data-am-dropdown-toggle=""><span>欢迎你：</span>${vUser.mobile} &nbsp;<i class="am-icon-caret-down"></i></a>
                        <ul class="am-dropdown-content">
                            <li><a href="${ctx}/user/index" rel="nofollow">个人中心</a></li>
                            <li><a href="javascript:;" class="login-out" rel="nofollow">注销</a></li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:;" rel="nofollow" data-am-modal="{target: '#my-popup', width: 350}">注册/登录</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>
</header>