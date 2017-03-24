<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">关注用户列表</strong> / <small>发送短信</small></div>
        </div>
        <hr>
        <div class="am-g am-padding">
            <input type="text" id="mobile" name="mobile" />
            <span class="am-btn am-btn-sm am-btn-primary sendInfo">发送短信</span>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th class="am-text-center"></th>
                        <th class="am-text-center">短信模板内容</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="radio" name="muban" checked="checked"></td>
                            <td><span>	尊敬的用户您好，恭喜您已经入选我们平台“100名VIP邀请活动”，为方便我们后续为您发放邀请码以及活动的进行，请及时关注我们的微信：vstock113</span></td>
                        </tr>
                    </tbody>
                </table>
                <jsp:include page="../common/paging.jsp" flush="true">
                    <jsp:param name="page" value="${page}"/>
                </jsp:include>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/bottom.jsp" flush="true"/>
<script type="text/javascript">
    $(function(){
       $(".sendInfo").click(function(){
           var mobile = $("#mobile").val();
           $.post("/user/sendUserActivityInfo",{
               "mobile": mobile
           },function(res){
               if (res.reGode == 1){
                   alert("发送成功");
               }else {
                   alert("操作失败，请重新操作！");
               }
           });
       });
    });
</script>