<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>v-stock即将发布-敬请期待</title>
    <link rel="stylesheet" href="${ctx}/assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${ctx}/assets/css/admin.css"/>
    <style>
        *{ font-family: PingFangSC-Regular, sans-serif; }
        body{ background-color: #1e1e1e; }
        /************** html5 **************/
        header{background:url(/assets/i/comingSoon/top.gif) center no-repeat; margin:0 auto; width:900px; height:150px; text-align:center;}
        .content{background:#fafafa; width:900px; height:480px; margin:0 auto; text-align:center; -moz-border-radius:5px; -webkit-border-radius:5px; border-radius:5px; -moz-box-shadow:0px 0px 4px #cccccc; -webkit-box-shadow:0px 0px 4px #cccccc; box-shadow:0px 0px 4px #cccccc;}
        .content p:first-child{border-bottom:1px solid #ccc; color:#5e5e5e; font-size:24px; width:839px; margin:0 auto; padding:45px 0px 20px;}
        .content p{padding-top:12px;}
        .tips{font-size:18px; margin:55px 0px 40px;}
        .time{background:#1e1e1e url(/assets/i/comingSoon/date_bg.jpg) center no-repeat;    padding-left: 135px; margin:0 auto; height:130px; width:839px; overflow:hidden;}
        .time span{float:left;line-height:25px;height:87px;overflow:hidden;}
        .time span.danwei_first{padding-left:42px;}
        .time span.danwei{ padding-left:137px;}
        .clear{height:0;clear:both;overflow:hidden;}
    </style>
</head>
<body>
    <article>
        <div class="am-u-md-12">
            <div class="am-center am-text-center am-margin-top-xl am-margin-bottom-xl">
                <img src="/assets/i/comingSoon/logo.png" /><br/>
                <span class="layout-font-size-36 am-margin-top-sm" style="color: #575757;display: inline-block;">敬请期待</span>
            </div>
        </div>
        <div class="time am-margin-top-xl" id="time1">
            <span style="display: none;"><img id="day_01" alt="" src="/assets/i/comingSoon/allnum.png"></span>
            <span style="display: none;"><img id="day_02" alt="" src="/assets/i/comingSoon/allnum.png"></span>
            <span style="display: none;" class="danwei"></span>
            <span><img id="hour_01" alt="" src="/assets/i/comingSoon/allnum.png"></span>
            <span><img id="hour_02" alt="" src="/assets/i/comingSoon/allnum.png"></span>
            <span class="danwei"></span>
            <span><img id="minute_01" alt="" src="/assets/i/comingSoon/allnum.png"></span>
            <span><img id="minute_02" alt="" src="/assets/i/comingSoon/allnum.png"></span>
            <span class="danwei"></span>
            <span><img id="second_01" alt="" src="/assets/i/comingSoon/allnum.png"></span>
            <span><img id="second_02" alt="" src="/assets/i/comingSoon/allnum.png"></span>
            <br class="clear">
        </div>
        <div class="am-center am-margin-top-lg">
            <img class="am-center" src="/assets/i/comingSoon/comingSoon_foot.png">
        </div>
    </article>
    <script type="text/javascript">
        var overa;
        overa = new Date(2017, 03, 14, 00, 00);//倒计时时间（PS：月数要-1，原因你懂得）
        var localNow = new Date();
        function clock1(){
            var local = new Date();
            var intDiff = overa.getTime() - local.getTime();
            if(intDiff <= 0){document.getElementById('time1');//显示活动结束 .innerHTML = "活动已经结束了";
                return false;}
            var day    = Math.floor(intDiff / (1000 * 60 * 60 * 24));
            var hour   = Math.floor(intDiff / (1000 * 60 * 60)) - (day * 24);
            var minute = Math.floor(intDiff / (1000 * 60)) - (day * 24 * 60) - (hour * 60);
            var second = Math.floor(intDiff / 1000) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
            if (day <= 9) day = '0' + day;
            if (hour <= 9) hour = '0' + hour;
            if (minute <= 9) minute = '0' + minute;
            if (second <= 9) second = '0' + second;
            document.getElementById('day_01').style.marginTop = (day.toString().substr(0,1)) * (-82.5) +'px';
            document.getElementById('day_02').style.marginTop = (day.toString().substr(1,1)) * (-82.5) +'px';
            document.getElementById('hour_01').style.marginTop = (hour.toString().substr(0,1)) * (-82.5) +'px';
            document.getElementById('hour_02').style.marginTop = (hour.toString().substr(1,1)) * (-82.5) +'px';
            document.getElementById('minute_01').style.marginTop = (minute.toString().substr(0,1)) * (-82.5) +'px';
            document.getElementById('minute_02').style.marginTop = (minute.toString().substr(1,1)) * (-82.5) +'px';
            document.getElementById('second_01').style.marginTop = (second.toString().substr(0,1)) * (-82.5) +'px';
            document.getElementById('second_02').style.marginTop = (second.toString().substr(1,1)) * (-82.5) +'px';
            setTimeout("clock1()", 1000);
        }
        clock1();
    </script>
</body>
</html>
