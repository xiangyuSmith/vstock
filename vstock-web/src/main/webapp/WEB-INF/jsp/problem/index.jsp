<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <%@include file="../layout/head.jsp" %>
    <title>Adidas NMD Nice Kicks</title>
    <style>
        .a-title{ color: #2f2e2e; }
        .a-content{ color: #616161; }
        .q-title-bg{ background-color: #fff9f8!important; }
        .a-content-bg{ background-color: #fff9f8; }
        .am-panel{ border:none;margin-bottom: 20px!important; }
        ul { margin: 5px 0; }
        ul li span { color: #616161; font-size: 18px; }
        @media (max-width: 1450px){ ul li span { color: #616161;font-size: 16px; } }
    </style>
</head>
<body>
<%@include file="../layout/top-search.jsp" %>
<article style="margin-top: 50px;">
    <div class="am-container-content">
        <p class="am-text-center layout-font-size-28" style="font-weight: bold;">V-stock Q&A</p>
        <div class="am-panel-group" id="accordion">
            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-1'}">
                        Q：我想买鞋，怎么购买？
                    </span>
                </div>
                <div id="do-not-say-1" class="a-content-bg am-panel-collapse am-collapse am-in">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18"><span class="a-title layout-font-size-22">A：</span>我们为大家提供了2种购买方式。</span>
                        <ul>
                            <li><span>1)：您可以选择款式和尺码后，出一个自己觉得满意的心理价位，等待卖家出售；</span></li>
                            <li><span>2)：如卖家的叫价，正好满足您的心理价位，您可以选择款式和尺码后，直接购买。</span></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-2'}">
                        Q：我有鞋子，怎么出售？
                    </span>
                </div>
                <div id="do-not-say-2" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18"><span class="a-title layout-font-size-22">A：</span>我们为大家提供了2种出售方式。</span>
                        <ul>
                            <li><span>1)：您可以选择款式和尺码后，出一个自己觉得满意的心理价位，等待买家购买；</span></li>
                            <li><span>2)：如买家现有的出价中，正好满足您的心理价位，您可以选择款式和尺码后，直接出售。</span></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-3'}">
                        Q：我是卖家，我叫价后，优先按照什么价格成交？
                    </span>
                </div>
                <div id="do-not-say-3" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18"><span class="a-title layout-font-size-22">A：</span>针对买家的出价，我们会优先按照最高出价成交。</span>
                    </div>
                </div>
            </div>
            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-4'}">
                        Q：我有鞋子，怎么出售？
                    </span>
                </div>
                <div id="do-not-say-4" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">
                            A：</span>真对卖家的叫价，我们会优先按照最低叫价成交。
                        </span>
                    </div>
                </div>
            </div>

            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-5'}">
                        Q：购买后，可以申请退款吗？
                    </span>
                </div>
                <div id="do-not-say-5" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">
                            A：</span>不可以。所有的鞋子，一经售出，不支持退款。
                        </span>
                    </div>
                </div>
            </div>

            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-6'}">
                        Q：我是买家，出价时要付款吗？
                    </span>
                </div>
                <div id="do-not-say-6" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">
                            A：</span>买家出价时需要支付保证金。
                        </span>
                    </div>
                </div>
            </div>

            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-7'}">
                        Q：我是卖家，叫价时需要付款吗？
                    </span>
                </div>
                <div id="do-not-say-7" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">
                            A：</span>卖家叫价时需要支付保证金。
                        </span>
                    </div>
                </div>
            </div>

            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-8'}">
                        Q：为什么要支付保证金？
                    </span>
                </div>
                <div id="do-not-say-8" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">
                            A：</span>保证金是为了约束买家/卖家双方的义务，同时也保障买家/卖家的权益。
                                    当买家违约（出价后有卖家出售，买家未在约定时间内支付订单金额）时，买家支付的保证金将作为违约金赔偿给卖家。
                                    当卖家违约（鉴定不合格）时，卖家支付的保证金将作为违约金赔偿给买家。
                        </span>
                    </div>
                </div>
            </div>

            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-9'}">
                        Q：什么情况下，保证金会退还？
                    </span>
                </div>
                <div id="do-not-say-9" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">A：</span>
                            买家的保证金，在如下2种情况下会退回：
                        </span>
                        <ul>
                            <li><span>1)：在叫价后，过了有效期未能成功交易的，叫价失效，退回；</span></li>
                            <li><span>2)：买家出价后，有卖家出售且支付完订单金额后，退回；</span></li>
                        </ul>
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">B：</span>
                            卖家的保证金，在如下2种情况下会退回：
                        </span>
                        <ul>
                            <li><span>1)：在叫价后，过了有效期未能成功交易的，叫价失效，退回；</span></li>
                            <li><span>2)：买家收到鞋子，交易完成后，退回。</span></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-10'}">
                        Q：叫价有效期是多久？
                    </span>
                </div>
                <div id="do-not-say-10" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">
                            A：</span>出价的有效期是由买家/卖价出价时，自己设置的。
                        </span>
                    </div>
                </div>
            </div>

            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-11'}">
                        Q：保证金，怎么退还？
                    </span>
                </div>
                <div id="do-not-say-11" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">
                            A：</span>符合上诉保证金退还条件的，支付的保证金将退回您支付时使用的银行卡/账户中。
                        </span>
                    </div>
                </div>
            </div>


            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-12'}">
                        Q：网站上购买的鞋子，都是正品吗？
                    </span>
                </div>
                <div id="do-not-say-12" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">
                            A：</span>我们网站上所交易的全部鞋子，都会经过我们公司专业的鉴定师进行验货，检验合格后才会发给买家，保证是正品。
                        </span>
                    </div>
                </div>
            </div>

            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-13'}">
                        Q：验货不通过的鞋子，对买家怎么处理？
                    </span>
                </div>
                <div id="do-not-say-13" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">
                            A：</span>验货不通过的鞋子，我们会退回卖家，并对买家进行违约赔偿。
                        </span>
                    </div>
                </div>
            </div>

            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-14'}">
                        Q：验货不通过的鞋子，对卖家怎么处理？
                    </span>
                </div>
                <div id="do-not-say-14" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">
                            A：</span>如果验货中发现鞋子是假鞋等原因不通过，则卖家将支付违约金（从保证金中扣除）用以赔偿给买家，同时卖家将被禁止继续出售鞋子。
                        </span>
                    </div>
                </div>
            </div>

            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-15'}">
                        Q：我有很多球鞋，怎么统计统计我的鞋子价值多少钱？
                    </span>
                </div>
                <div id="do-not-say-15" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">
                            A：</span>我们提供了资产管理的功能，您可以在我们的网站查询到您拥有的鞋子，并在详情中点击“添加至资产”将您的鞋子录入您的资产中，每天可以根据最新的市场价值，轻松统计自己当前资产的涨跌情况。
                        </span>
                    </div>
                </div>
            </div>

            <div class="am-panel am-panel-default">
                <div class="am-panel-hd q-title-bg">
                    <span class="am-panel-title layout-font-size-22" data-am-collapse="{parent: '#accordion', target: '#do-not-say-16'}">
                        Q：我想买鞋，包邮吗？
                    </span>
                </div>
                <div id="do-not-say-16" class="a-content-bg am-panel-collapse am-collapse">
                    <div class="am-panel-bd">
                        <span class="a-content layout-font-size-18">
                            <span class="a-title layout-font-size-22">
                            A：</span>不包邮。我们会按照不同的地区，收取相应的运费。如下：
                            <table border="1" cellpadding="0" cellspacing="0" width="100%" class="am-text-center">
                                <tr>
                                    <td width="50%" height="50">地区</td>
                                    <td width="50%" height="50">价格(元)</td>
                                </tr>
                                <tr>
                                    <td width="50%" height="50">上海、浙江、江苏</td>
                                    <td width="50%" height="50">12</td>
                                </tr>
                                <tr>
                                    <td width="50%" height="50">广东、吉林、黑龙江</td>
                                    <td width="50%" height="50">18</td>
                                </tr>
                                <tr>
                                    <td width="50%" height="50">安徽</td>
                                    <td width="50%" height="50">14</td>
                                </tr>
                                <tr>
                                    <td width="50%" height="50">重庆、四川、福建、甘肃、广西、贵州、海南、辽宁、内蒙古、宁夏、青海、云南</td>
                                    <td width="50%" height="50">18</td>
                                </tr>
                                <tr>
                                    <td width="50%" height="50">北京、河北、河南、湖北、湖南、江西、山东、山西、陕西、天津</td>
                                    <td width="50%" height="50">18</td>
                                </tr>
                                <tr>
                                    <td width="50%" height="50">新疆</td>
                                    <td width="50%" height="50">20</td>
                                </tr>
                                <tr>
                                    <td width="50%" height="50">西藏</td>
                                    <td width="50%" height="50">26</td>
                                </tr>
                            </table>
                            <%--<span>--%>
                                <%--上海、浙江、江苏	12--%>
                                <%--广东、吉林、黑龙江	18--%>
                                <%--安徽	14--%>
                                <%--重庆、四川、福建、甘肃、广西、贵州、海南、辽宁、内蒙古、宁夏、青海、云南	18--%>
                                <%--北京、河北、河南、湖北、湖南、江西、山东、山西、陕西、天津	18--%>
                                <%--新疆	20--%>
                                <%--西藏	26--%>
                            <%--</span>--%>
                        </span>
                    </div>
                </div>
            </div>

        </div>
    </div>
</article>
<%@include file="../layout/footer.jsp" %>
<%@include file="../layout/bottom.jsp" %>
<script>
    document.onkeydown=keyDownSearch;

    function keyDownSearch(e) {
        var theEvent = e || window.event;
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
        if (code == 13) {
            if(!$("#my-popup-login").is(":hidden")){
                $("#prLogin").click();
                return false;
            }
            location.href = "/sorts?productName="+$(".index_search_top").val();
            return false;
        }
        return true;
    }
</script>
</body>
</html>