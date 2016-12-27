<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    .pre-bid input::-webkit-input-placeholder{ font-size: 14px; }
    .pre-bid .select-pom{width: 90%;height: 37px;border: 1px solid #cdcdcd;background-color: #eee;box-shadow: 0px 2px #999;}
    .bgff{ background-color: #fff; }
</style>
<div class="am-modal am-modal-no-btn" tabindex="-1" id="my-popup-identify">
    <div class="am-modal-dialog pre-bid" style="background-color: #e2e2e2;">
        <div class="am-modal-hd" style="background-color: #00CD61;">
            <div class="am-active am-g am-padding-bottom-sm" style="color: #FFFFFF;">
                <span class="am-fl am-text-lg">认证信息</span>
            </div>
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>×</a>
        </div>
        <div class="am-g am-padding-sm am-text-left" style="background-color: #FFF6F4;">
            <span class="layout-font-size-18" style="color: #5d5d5d;">请填写基本信息</span>
        </div>
        <div class="am-modal-bd bgff am-g">
            <form class="am-form am-form-horizontal">
                <div class="am-form-group">
                    <span for="doc-ipt-3" class="am-u-sm-3 layout-font-size-18 am-text-right am-margin-top-xs">姓名：</span>
                    <div class="am-u-sm-5 am-u-end am-padding-left-0">
                        <input type="email" id="doc-ipt-3" placeholder="请输入您的真实姓名">
                    </div>
                </div>
                <div class="am-form-group">
                    <span for="doc-ipt-pwd-2" class="am-u-sm-3 layout-font-size-18 am-text-right am-margin-top-xs">身份证号码：</span>
                    <div class="am-u-sm-5 am-u-end am-padding-left-0">
                        <input type="password" id="doc-ipt-pwd-2" placeholder="请输入您的身份证号码">
                    </div>
                </div>
            </form>
        </div>
        <div class="am-g am-padding-sm am-text-left" style="background-color: #FFF6F4;">
            <span class="layout-font-size-18" style="color: #5d5d5d;">请上传本人清晰身份证照片</span>
        </div>
        <div class="am-modal-bd bgff am-g">
            <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2
  am-avg-md-3 am-avg-lg-4 am-gallery-default" data-am-gallery="{ pureview: true }" >
                <li>
                    <div class="am-gallery-item">
                        <a href="http://s.amazeui.org/media/i/demos/bing-1.jpg" class="">
                            <img src="http://s.amazeui.org/media/i/demos/bing-1.jpg"  alt="远方 有一个地方 那里种有我们的梦想"/>
                            <h3 class="am-gallery-title">远方 有一个地方 那里种有我们的梦想</h3>
                            <div class="am-gallery-desc">2375-09-26</div>
                        </a>
                    </div>
                </li>
                <li>
                    <div class="am-gallery-item">
                        <a href="http://s.amazeui.org/media/i/demos/bing-2.jpg" class="">
                            <img src="http://s.amazeui.org/media/i/demos/bing-2.jpg"  alt="某天 也许会相遇 相遇在这个好地方"/>
                            <h3 class="am-gallery-title">某天 也许会相遇 相遇在这个好地方</h3>
                            <div class="am-gallery-desc">2375-09-26</div>
                        </a>
                    </div>
                </li>
                <li>
                    <div class="am-gallery-item">
                        <a href="http://s.amazeui.org/media/i/demos/bing-3.jpg" class="">
                            <img src="http://s.amazeui.org/media/i/demos/bing-3.jpg"  alt="不要太担心 只因为我相信"/>
                            <h3 class="am-gallery-title">不要太担心 只因为我相信</h3>
                            <div class="am-gallery-desc">2375-09-26</div>
                        </a>
                    </div>
                </li>
                <li>
                    <div class="am-gallery-item">
                        <a href="http://s.amazeui.org/media/i/demos/bing-4.jpg" class="">
                            <img src="http://s.amazeui.org/media/i/demos/bing-4.jpg"  alt="终会走过这条遥远的道路"/>
                            <h3 class="am-gallery-title">终会走过这条遥远的道路</h3>
                            <div class="am-gallery-desc">2375-09-26</div>
                        </a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
