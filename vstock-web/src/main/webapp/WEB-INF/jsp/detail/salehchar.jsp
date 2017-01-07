<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../layout/inc.jsp" %>
<div class="am-margin-bottom-xl">
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12 am-margin-top-xl">
        <div class="am-u-sm-5 am-u-md-5 am-u-lg-5">
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 am-padding-right-0  am-padding-bottom-0"><span class="am-fr" style="font-size: large;">时间:</span></div>
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2 am-margin-bottom-0">
                <a href="javascript:void(0);" date_number="7" class="hchar_date_btn am-btn am-radius am-btn-default am-btn-sm am-padding-top-xs am-padding-bottom-xs hchar_sel">1周</a>
            </div>
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
                <a href="javascript:void(0);" date_number="14" class="hchar_date_btn am-btn am-radius am-btn-default am-btn-sm am-padding-top-xs am-padding-bottom-xs hchar_btn">2周</a>
            </div>
            <div class="am-u-sm-3 am-u-md-3 am-u-lg-3">
                <a href="javascript:void(0);" date_number="30" class="hchar_date_btn am-btn am-radius am-btn-default am-btn-sm am-padding-top-xs am-padding-bottom-xs hchar_sel">1个月</a>
            </div>
            <div class="am-u-sm-3 am-u-md-3 am-u-lg-3 am-padding-left-0">
                <a href="javascript:void(0);" date_number="60" class="hchar_date_btn am-btn am-radius am-btn-default am-btn-sm am-padding-top-xs am-padding-bottom-xs hchar_sel">2个月</a>
            </div>
        </div>
        <div class="am-u-sm-7 am-u-md-7 am-u-lg-7">
            <div class="am-u-sm-5 am-u-md-5 am-u-lg-5 am-padding-left-0"><span style="font-size: large;">从</span><input class="am-margin-bottom-xs am-margin-left-sm" id="start_hchar" type="date"/></div>
            <div class="am-u-sm-5 am-u-md-5 am-u-lg-5 am-padding-left-0"><span style="font-size: large;">至</span><input class="am-margin-bottom-xs am-margin-left-sm" id="end_hchar" type="date"/></div>
            <div class="am-u-sm-2 am-u-md-2 am-u-lg-2"><a href="javascript:void(0);" class="am-btn am-btn-danger am-radius am-btn-sm am-padding-top-xs am-padding-bottom-xs hchar_sel_btn">搜索</a></div>
        </div>
    </div>
    <div id="containerSale" style="height: 300px;"></div>
</div>
<script src="${ctx}/assets/js/highcharts/_salelist_hcharts.js"></script>
<script type="text/javascript">
    jQuery(function($) {
        loadDate("");

        $('.hchar_date_btn').click(function () {
            var $this = $(this);
            loadDate($this);
        });

        $('.hchar_sel_btn').click(function () {
            var $th = $(this).parent().parent().prev().find("a");
            $th.removeClass("hchar_btn");
            $th.addClass("hchar_sel");
            var size = $('#choose_size').val();
            var url = "/trade/saleMarket?size="+size+"&startDate="+$('#start_hchar').val()+"&endDate="+$('#end_hchar').val()+"&bidId="+$('.basicinformationId').val();
            saleHcharGet(url);
        });

        function loadDate($this) {
            if ($this != "") {
                var $th = $this.parent().parent().find("a");
                $th.removeClass("hchar_btn");
                $th.addClass("hchar_sel");
                $this.removeClass("hchar_sel");
                $this.addClass("hchar_btn");
                var dateNum = $this.attr("date_number");
                var date = new Date();
                var a = date.valueOf() - dateNum * 24 * 60 * 60 * 1000
                var dateTime = new Date(a);
                $('#start_hchar').val(TimeObjectUtil.formatterDate(dateTime));
                $('#end_hchar').val(TimeObjectUtil.formatterDate(date));
                var size = $('#choose_size').val();
                var url = "/trade/saleMarket?size="+size+"&startDate="+TimeObjectUtil.formatterDate(dateTime)+"&endDate="+TimeObjectUtil.formatterDate(date)+"&bidId="+$('.basicinformationId').val();
                saleHcharGet(url);
            } else {
                var date = new Date();
                var a = date.valueOf() - 14 * 24 * 60 * 60 * 1000
                var dateTime = new Date(a);
                $('#start_hchar').val(TimeObjectUtil.formatterDate(dateTime));
                $('#end_hchar').val(TimeObjectUtil.formatterDate(date));
                var size = $('#choose_size').val();
                var url = "/trade/saleMarket?size="+size+"&startDate="+TimeObjectUtil.formatterDate(dateTime)+"&endDate="+TimeObjectUtil.formatterDate(date)+"&bidId="+$('.basicinformationId').val();
                saleHcharGet(url);
            }
        }
    });
</script>