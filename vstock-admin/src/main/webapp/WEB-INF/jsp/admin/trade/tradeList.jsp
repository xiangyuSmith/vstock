<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">订单信息</strong> / <small>订单详情</small></div>
        </div>
        <hr>
        <div class="am-g">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom am-margin-top">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>卖家：</label>
                    <span></span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>买家：</label>
                    <span></span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>球鞋名：</label>
                    <span></span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>尺码：</label>
                    <span></span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>金额：</label>
                    <span></span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>运费：</label>
                    <span></span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>快递公司：</label>
                    <span></span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>快递单号：</label>
                    <span></span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>状态：</label>
                    <span></span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-bottom-lg">
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
                    <label>交易日期：</label>
                    <span></span>
                </div>
                <div class="am-u-sm-4 am-u-md-4 am-u-lg-4 am-u-end">
                    <label>结束日期：</label>
                    <span></span>
                </div>
            </div>
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-lg">
                <div class="am-u-sm-6 am-u-md-4 am-u-lg-6">
                    <a href="#" class="am-btn am-btn-primary am-radius am-fr am-margin-right-xl">检验通过</a>
                </div>
                <div class="am-u-sm-6 am-u-md-4 am-u-lg-6">
                    <a href="#" class="am-btn am-btn-danger am-radius">检验失败</a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/bottom.jsp" flush="true"/>
