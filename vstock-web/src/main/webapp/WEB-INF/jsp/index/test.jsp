<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<!DOCTYPE html>
<html>
<head lang="ch">
    <%@include file="../layout/head.jsp" %>
    <style>
        .get {
            background: #2588cb;
            color: #fff;
            text-align: center;
        }
        .get-title {
            font-size: 200%;
            display: inline-block;
        }
        .detail {
            background: #fff;
            margin-top: 40px;
        }
        .detail-h2 {
            text-align: center;
            font-size: 150%;
            margin: 40px 0;
        }
        .detail-h3 {
            color: #1f8dd6;
            text-align: center;
        }
        .detail-p {
            color: #464545;
            font-size: 18px;
            text-align: justify;
            text-justify: inter-ideograph;
            text-align: center;
        }
        .detail-mb {
            margin-bottom: 40px;
            margin-top: 40px;
        }
        .hope {
            background: #f2f2f2;
        }
        .hope2 {
            background: #fff;
            margin-top: 30px;
            margin-bottom: 30px;
        }
        .hope-img {
            text-align: center;
        }
        .hope-img-left {
            text-align: left;
        }
        .hope-img-right {
            text-align: right;
        }
        .hope-text {
            font-size: 34px;
            color: #464545;
            margin-top: 60px;
        }
        .hope-text-top {
            margin-top: 180px;
        }
        /*.hope-img img {
            margin-top:19px;
            }
        */
        .hope-hr {
            border-color: #149C88;
        }
        .hope-title {
            color: #2588cb;
        }
        .about {
            background: #fff;
            padding: 40px 0;
            color: #7f8c8d;
        }
        .about-color {
            color: #34495e;
        }
        .about-title {
            font-size: 180%;
            padding: 30px 0 50px 0;
            text-align: center;
        }
        .footer p {
            color: #7f8c8d;
            margin: 0;
            padding: 15px 0;
            text-align: center;
            font-size: 24px;
        }
        .am-topbar {
            margin-bottom: 0px;
            background: #F8F8F8;
        }
        .am-with-topbar-fixed-top {
            padding-top: 108px;
        }
        .am-topbar-brand {
            height: 108px;
            line-height: 108px;
        }
        .am-menu-height {
            margin-top: 26px;
        }
        /*.am-first-bg{height:582px;}*/
        /*.am-container-height{ height:582px;}*/
        /*.am-left-text{ margin-top:100px;}*/
        .get-title-text {
            font-size: 58px;
            text-align: left;
            width: 100%;
            margin-top: 70px;
        }
        .am-text-second {
            font-size: 42px;
        }
        .am-first-bg p {
            text-align: left;
        }
        .am-down {
            width: 196px;
            height: 64px;
            line-height: 64px;
            background: #2588cb;
            border: 2px solid #fff;
            color: #fff;
            font-size: 22px;
            text-align: center;
            padding: 0 0;
            margin-top: 30px;
        }
        .am-text-block {
            text-align: center;
            font-size: 26px;
            color: #000000;
            margin-top: 36px;
        }
        .footer {
            background: #000;
        }
        .footer p {
            padding: 5px 0;
            font-weight: 100;
            font-size: 18px;
        }
        .footer p a {
            padding: 5px 0;
            font-weight: 100;
            font-size: 18px;
            color: #fff;
        }
        .am-copy {
            color: #a6a6a6;
        }
        /*.first-img-height{ height:582px;}
            .first-img-height img{ margin-top:39px;}*/
        .am-container {
            padding: 0 0;
        }
        .am-container-height {
            padding-top: 39px;
        }
        .am-topbar-btn {
            margin-top: 36px;
        }
        .am-active {
            background-image: none;/*  -webkit-box-shadow: none;
          box-shadow: none;*/
        }
        .am-a-maodian {
            display: block;
            height: 108px;
        }
        .am-topbar-nav > li.am-active > a:after {
            opacity: 1;
            border-bottom: none;
            color: #0e90d2;
        }
        .am-topbar-nav > li > a:hover:after {
            opacity: 1;
            border-bottom: none;
        }
        .am-img-inline-block {
            display: inline-block;
            margin-bottom: 20px;
        }
        #amz-hero {
            background: #2588CB;
        }
        .amz-hero-intro {
            margin-top: 28px;
            float: left;
            padding-bottom: 2.0rem;
            width: 100%;
            max-width: 1000px;
        }
        .amz-container {
            padding-left: 0rem;
            padding-right: 0rem;
        }
        .amz-hero-top {
            margin-top: 40px;
        }
        .amz-hero-intro div {
            text-align: justify;
            text-justify: inter-ideograph;
            text-align: left;
        }
        .amz-hero-intro .am-img-textcenter {
            text-align: center;
        }
        .am-footer-alink {
            background: #000;
            text-align: center;
        }
        .am-footer-alink a {
            color: #fff;
        }
        .am-footer-alink a:hover {
            color: #a3a3a3;
        }
        .am-footer-alink span {
            color: #fff;
            margin-left: 0.6rem;
            margin-right: 0.6rem;
        }
        .am-footer-miscs {
            background: #000;
            text-align: center;
        }
    </style>
</head>
<body>
<header class="am-topbar am-top-height">
    <div class="am-container am-top-height">
        <h1 class="am-topbar-brand"> <a href="index.html"><img alt="医云logo" src="assets/i/logo.png" /></a> <small>医云健康</small></h1>
        <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-secondary am-show-sm-only"
                data-am-collapse="{target: '#collapse-head'}"><span class="am-sr-only">导航切换</span> <span
                class="am-icon-bars"></span></button>
        <div class="am-collapse am-topbar-collapse" id="collapse-head">
            <ul class="am-nav am-nav-pills am-topbar-nav am-menu-height">
                <li class="am-active"><a href="#doctor">医生解决方案</a></li>
                <li><a href="#patient">患者解决方案</a></li>
            </ul>
            <div class="am-topbar-right am-hide">
                <button class="am-btn am-btn-secondary am-topbar-btn am-btn-sm"><span class="am-icon-pencil"></span> 注册</button>
            </div>
            <div class="am-topbar-right am-hide">
                <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm"><span class="am-icon-user"></span> 登录</button>
            </div>
        </div>
    </div>
</header>
<div class="get">
    <div class="am-g am-container am-container-height">
        <div class="am-u-lg-6 am-u-md-6 am-u-sm-12 am-left-text am-first-bg">
            <div>
                <section>
                    <div class="amz-container am-cf">
                        <div class="amz-hero-intro">
                            <div class="amz-hero-headings am-img-textcenter"><img class="am-img-responsive am-img-inline-block" style="border: 2px solid #fff; padding: 1px; border-radius: 28px;" src="assets/i/img-logo.png"/>
                                <!--<h1 class="am-scrollspy-init am-scrollspy-inview">小心肝</h1>-->
                                <h2 class="am-scrollspy-init am-scrollspy-inview">专注心科技，关爱肝健康</h2>
                            </div>
                            <div class="am-img-textcenter">小心肝，挑战脂肪肝，还您健康... </div>
                            <div class="am-img-textcenter">
                                <button type="button" class="am-btn am-btn-default am-radius am-down" onclick="window.location.href='/d'">立即下载</button>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <div class="am-u-lg-6 am-u-md-6 am-u-sm-12 hope-img hope-img-right first-img-height  am-scrollspy-init am-scrollspy-inview am-animation-slide-right">
            <img class="am-img-responsive" src="${cxt}/assets/i/img-first.png" alt="" data-am-scrollspy="{animation:'slide-right', repeat: false}" align="right"> </div>
    </div>
</div>
<div class="detail">
    <div class="am-g am-container">
        <div class="am-u-lg-12">
            <div class="am-g">
                <div class="am-u-lg-3 am-u-md-6 am-u-sm-12 detail-mb">
                    <h3 class="detail-h3"> <img alt="健康中心" src="assets/i/img-jkzx.png" align="middle" /> </h3>
                    <p class="am-text-block">健康中心</p>
                    <p class="detail-p"> 医生可以组建健康中心，扩建自己的人脉 </p>
                </div>
                <div class="am-u-lg-3 am-u-md-6 am-u-sm-12 detail-mb">
                    <h3 class="detail-h3"> <img alt="医患交流" src="assets/i/img-yhjl.png" /> </h3>
                    <p class="am-text-block">医患交流</p>
                    <p class="detail-p"> 医生与医生，医生与患者无障碍沟通 </p>
                </div>
                <div class="am-u-lg-3 am-u-md-6 am-u-sm-12 detail-mb">
                    <h3 class="detail-h3"> <img alt="找医生" src="assets/i/img-zys.png" /> </h3>
                    <p class="am-text-block">找医生</p>
                    <p class="detail-p"> 全国各地线上线下名医咨询诊治 </p>
                </div>
                <div class="am-u-lg-3 am-u-md-6 am-u-sm-12 detail-mb">
                    <h3 class="detail-h3"> <img alt="定制方案" src="assets/i/img-dzfa.png" /> </h3>
                    <p class="am-text-block">定制方案</p>
                    <p class="detail-p"> 私人医生，定制专属您的健康方案 </p>
                </div>
            </div>
        </div>
    </div>
</div>
<a name="doctor" id="doctor" class="am-a-maodian"></a>
<div class="hope">
    <div class="am-g am-container">
        <div class="am-u-lg-6 am-u-md-6 am-u-sm-12 hope-img hope-img-left"> <img class="am-img-responsive" src="assets/i/img-second.png" alt="" data-am-scrollspy="{animation:'slide-left', repeat: false}"> </div>
        <div class="am-u-lg-6 am-u-md-6 am-u-sm-12">
            <section>
                <div class="amz-container am-cf">
                    <div class="amz-hero-intro">
                        <div class="amz-hero-headings amz-hero-top">
                            <h2 class="am-scrollspy-init am-scrollspy-inview am-animation-slide-right hope-title">患者数量庞大，医生如何进行管理？</h2>
                        </div>
                        <div>医生可以通过“小心肝”平台的健康中心模块组建自己的健康中心，然后通过通讯录、二维码或者查找医生等功能添加其他医生到自己的健康中心，医生也可以主动申请加入健康中心，组成虚拟团队，让团队中的每一位成员去管理患者，减少一位医生管理过多患者的烦恼。</div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
<div class="hope2">
    <div class="am-g am-container">
        <div class="am-u-lg-6 am-u-md-6 am-u-sm-12">
            <section>
                <div class="amz-container am-cf">
                    <div class="amz-hero-intro">
                        <div class="amz-hero-headings amz-hero-top">
                            <h2 class="am-scrollspy-init am-scrollspy-inview am-animation-slide-left hope-title">作为某一领域的医生如何对患者进行各方面康复治疗的详细专业指导？</h2>
                        </div>
                        <div>“小心肝”平台可以邀请不同类型的专业医生（包括运动、营养类医生等）加入进来，在治疗、饮食、运动等方面更专业的、全方位的管理病人。</div>
                    </div>
                </div>
            </section>
        </div>
        <div class="am-u-lg-6 am-u-md-6 am-u-sm-12 hope-img hope-img-right"> <img class="am-img-responsive" src="assets/i/img-seventh.png" alt="" data-am-scrollspy="{animation:'slide-right', repeat: false}" align="right"> </div>
    </div>
</div>
<div class="hope">
    <div class="am-g am-container">
        <div class="am-u-lg-6 am-u-md-6 am-u-sm-12 hope-img hope-img-left"> <img class="am-img-responsive" src="assets/i/img-sixth.png" alt="" data-am-scrollspy="{animation:'slide-left', repeat: false}"> </div>
        <div class="am-u-lg-6 am-u-md-6 am-u-sm-12">
            <section>
                <div class="amz-container am-cf">
                    <div class="amz-hero-intro">
                        <div class="amz-hero-headings amz-hero-top">
                            <h2 class="am-scrollspy-init am-scrollspy-inview am-animation-slide-right hope-title">医生如何高效管理对患者后期的随访工作</h2>
                        </div>
                        <div>在“小心肝”平台中，随访不再只是打电话、发短信，可以变得更专业。患者平时可以购买一些穿戴设备或者日常基本监测的医疗工具，另外，平台也会推送一些工具，患者可以通过这些工具检测并随时上传自己最新的医学数据和行为数据，医生可以根据这些数据，进行方案调整，有助于患者后期恢复。同时，通过这些数据，医生也可及时发现患者的危险情况，及时帮助患者。</div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
<div class="hope2">
    <div class="am-g am-container">
        <div class="am-u-lg-5 am-u-md-5 am-u-sm-12">
            <section>
                <div class="amz-container am-cf">
                    <div class="amz-hero-intro">
                        <div class="amz-hero-headings amz-hero-top">
                            <h2 class="am-scrollspy-init am-scrollspy-inview am-animation-slide-left hope-title">通过哪些方面可以最大提高医生的价值</h2>
                        </div>
                        <div>1、在长期慢性病管理的过程中，医生可以获得患者病情各个阶段的详细数据，通过这些数据，更好的研究某个领域的疾病。<br />
                            2、医生可以随时接收到最新的科研成果转化的工具，以利于对患者更好的专业服务。<br />
                            3、在平时对患者的治疗过程中，通过服务去提升自己的价值，也可得到额外收益。</div>
                    </div>
                </div>
            </section>
        </div>
        <div class="am-u-lg-7 am-u-md-7 am-u-sm-12 hope-img hope-img-right"> <img class="am-img-responsive" src="assets/i/img-third.png" alt="" data-am-scrollspy="{animation:'slide-right', repeat: false}" align="right"> </div>
    </div>
</div>
<div class="hope">
    <div class="am-g am-container">
        <div class="am-u-lg-7 am-u-md-7 am-u-sm-12 hope-img hope-img-left"> <img class="am-img-responsive" src="assets/i/img-fourth.png" alt="" data-am-scrollspy="{animation:'slide-left', repeat: false}"> </div>
        <div class="am-u-lg-5 am-u-md-5 am-u-sm-12">
            <section>
                <div class="amz-container am-cf">
                    <div class="amz-hero-intro">
                        <div class="amz-hero-headings amz-hero-top">
                            <h2 class="am-scrollspy-init am-scrollspy-inview am-animation-slide-right hope-title">医生如何获取科研所需的科研数据</h2>
                        </div>
                        <div>医生可以通过“小心肝”平台长期随访自己的患者，并有意识的完善患者的主要医学数据信息，从而获得有效的队列数据。并通过医生间的数据共享，从而使研究更趋科学。</div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
<a name="patient" id="patient" class="am-a-maodian"></a>
<div class="hope2">
    <div class="am-g am-container">
        <div class="am-u-lg-5 am-u-md-5 am-u-sm-12">
            <section>
                <div class="amz-container am-cf">
                    <div class="amz-hero-intro">
                        <div class="amz-hero-headings amz-hero-top">
                            <h2 class="am-scrollspy-init am-scrollspy-inview am-animation-slide-left hope-title">如何找到一位可信赖的医生？</h2>
                        </div>
                        <div>“小心肝”平台里的医生都是经过严格审核的，患者可以放心，通过平台可以按地区、科室、排序找到最值得自己信赖的医生。对于慢性疾病管理，从专业角度更建议长期跟随自己的诊疗医生，避免信息复杂化，从而让患者无所适从。甚至通过自己的医生得到其他疾病领域专家的帮助。</div>
                    </div>
                </div>
            </section>
        </div>
        <div class="am-u-lg-7 am-u-md-7 am-u-sm-12 hope-img hope-img-right"> <img class="am-img-responsive" src="assets/i/img-fifth.png" alt="" data-am-scrollspy="{animation:'slide-right', repeat: false}" align="right"> </div>
    </div>
</div>
<div class="hope">
    <div class="am-g am-container">
        <div class="am-u-lg-7 am-u-md-7 am-u-sm-12 hope-img hope-img-left"> <img class="am-img-responsive" src="assets/i/img-eighth.png" alt="" data-am-scrollspy="{animation:'slide-left', repeat: false}"> </div>
        <div class="am-u-lg-5 am-u-md-5 am-u-sm-12">
            <section>
                <div class="amz-container am-cf">
                    <div class="amz-hero-intro">
                        <div class="amz-hero-headings amz-hero-top">
                            <h2 class="am-scrollspy-init am-scrollspy-inview am-animation-slide-right hope-title">慢性病的治疗，需要定期去医院，但是没有时间，日常情况的变化如何能让我的医生也对我及时予以建议？</h2>
                        </div>
                        <div>在“小心肝”平台里通过咨询自己的医生，也可通过平台推送的检测工具，及时对自己的情况进行随访，通过咨询医生得到建议并进行线下的诊疗。</div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
<div class="hope2">
    <div class="am-g am-container">
        <div class="am-u-lg-5 am-u-md-5 am-u-sm-12">
            <section>
                <div class="amz-container am-cf">
                    <div class="amz-hero-intro">
                        <div class="amz-hero-headings amz-hero-top">
                            <h2 class="am-scrollspy-init am-scrollspy-inview am-animation-slide-left hope-title">没有以往病历数据？</h2>
                        </div>
                        <div>通过“小心肝”平台的所有治疗，各个阶段的病历数据都加密后保存在数据库中，方便以后查阅，患者通过穿戴设备、检测工具产生的一些数据通过上传也会保存在平台中，平台还能根据患者的日常数据、医学数据、行为数据等进行整理回顾、预后评估，便于医生对您的治疗方案进行调整。</div>
                    </div>
                </div>
            </section>
        </div>
        <div class="am-u-lg-7 am-u-md-7 am-u-sm-12 hope-img hope-img-right"> <img class="am-img-responsive" src="assets/i/img-ninth.png" alt="" data-am-scrollspy="{animation:'slide-right', repeat: false}" align="right"> </div>
    </div>
</div>
<footer class="am-footer footer">
    <div class="am-footer-switch am-footer-alink"> <a data-rel="desktop" class="am-footer-desktop"
                                                      href="javascript:">关于医云</a> <span class="am-footer-divider">|</span> <a data-rel="desktop" class="am-footer-desktop"
                                                                                                                              href="javascript:">加入医云</a> <span class="am-footer-divider">|</span> <a data-rel="desktop" class="am-footer-desktop"
                                                                                                                                                                                                      href="javascript:">联系我们</a> </div>
    <div class="am-footer-miscs am-text-center">
        <p class="am-copy">Copyright &copy;2015  Doctors-Cloud.COM All rights reserved<br>
            <a href="http://www.miibeian.gov.cn" target="_blank">京ICP备15046334号-1</a></p>
        <p class="am-copy">医云健康 版权所有 </p>
    </div>
</footer>
<%@include file="../layout/footer.jsp" %>
<script type="text/javascript">
    $(function(){
        $("#collapse-head ul li").click(function(){
            $(this).addClass("am-active").siblings().removeClass("am-active");
        });
    });
</script>
</body>
</html>
