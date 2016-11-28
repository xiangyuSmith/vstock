<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="inc.jsp" %>
<header class="m-hd am-margin-bottom-0">
    <section data-am-sticky class="am-show-md-up">
        <div class="am-container">
            <a href="#" class="v-logo"></a>
            <nav>
                <ul class="m-nav am-nav am-nav-pills am-fr">
                    <li class="am-dropdown" data-am-dropdown=""><a href="/anli.html" rel="nofollow" class="am-dropdown-toggle" data-am-dropdown-toggle="">本店所有商品 <i class="am-icon-caret-down"></i></a>
                        <ul class="am-dropdown-content">
                            <li><a href="#" rel="nofollow">分类1...</a></li>
                            <li><a href="#" rel="nofollow">分类2...</a></li>
                            <li><a href="#" rel="nofollow">分类3...</a></li>
                        </ul>
                    </li>
                    <li><a href="#" rel="nofollow">即将发布</a></li>
                    <li><a href="#" rel="nofollow">作品集</a></li>
                    <li><a href="#" rel="nofollow">常见问题</a></li>
                    <li><a href="#" rel="nofollow">注册/登录</a></li>
                    <li><a href="#" class="v-a-boder" rel="nofollow">SELL</a></li>
                </ul>
            </nav>
        </div>
    </section>
    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#doc-topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav am-show-sm-only" style="background-color: #f8f8f8">
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
            <li><a href="#">注册/登录</a></li>

        </ul>
    </div>
</header>