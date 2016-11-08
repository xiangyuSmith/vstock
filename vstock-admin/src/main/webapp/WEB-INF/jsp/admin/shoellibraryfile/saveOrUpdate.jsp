<%--
  Created by IntelliJ IDEA.
  User: administor
  Date: 2016/7/12
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<%-- 传入参数时用 <jsp:param name="parameterName" value="{parameterValue | EL表达式 }" />--%>
<style type="text/css">
    .am-g{
        padding-bottom: 15px;
    }
    .xy-dis{
        display: none;
    }
    .xy-input-set{
        padding-left:5px;
    }
    .Lg-text-size{
        padding: 15px;
    }
    .Lg-text-height{
        line-height: 35px;
    }
</style>
<!-- content start -->
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">鞋库基本档案信息</strong> / <small>新增界面</small></div>
        </div>
        <hr>
        <div class="am-g Lg-text-size">
                <form id="insert" action="/stockx/basicinfrom/insertbasicinfrom" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="pageNow" value="${pageNow}"/>
                    <input type="hidden" name="brid" value="${brid}"/>
                    <input type="hidden" name="bstartCsaledate" value="${startCsaledate}"/>
                    <input type="hidden" name="bendCsaledate" value="${endCsaledate}"/>
                    <input type="hidden" name="bstartEsaledate" value="${startEsaledate}"/>
                    <input type="hidden" name="bendEsaledate" value="${endEsaledate}"/>
                    <input type="hidden" name="bbrandName" value="${brandName}"/>
                    <input type="hidden" name="bname" value="${name}"/>
                    <input type="hidden" name="bartNo" value="${artNo}"/>
                    <input type="hidden" name="bchineselogo" value="${chineselogo}"/>
                    <input type="hidden" name="bchineselogos" value="${chineselogos}"/>
                    <input type="hidden" name="bcofferprice" value="${cofferprice}"/>
                    <input type="hidden" name="bcofferprices" value="${cofferprices}"/>
                    <input type="hidden" name="bscofferprice" value="${bscofferprice}"/>
                    <input type="hidden" name="csaledate" value="${csaledates}"/>
                <c:choose>
                <c:when test="${not empty basicinformation.id}">
                    <input type="hidden" name="id" value="${basicinformation.id}">
                    <span class="Lg-text-height"><label>品 &nbsp;&nbsp;牌 : </label> <span><input type="hidden" id="brand" class="am-input-sm xy-input-set" value="${basicinformation.brand}">
                    <select id="hqcx" name="brand">
                            <c:if test="${not empty brandList}">
                                <c:forEach items="${brandList}" var="brand">
                                    <option>${brand.brandName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </span></span><br/>
                    <span class="Lg-text-height"><label>名 &nbsp;&nbsp;称 : </label> <span><input type="text" name="name" class="am-input-sm xy-input-set" value="${basicinformation.name}" readonly></span></span><br/>
                    <span class="Lg-text-height"><label>货 &nbsp;&nbsp;号 : </label> <span><input type="text" name="artNo" class="am-input-sm xy-input-set" value="${basicinformation.artNo}"></span></span><br/>
                    <span class="Lg-text-height"><label>颜 &nbsp;&nbsp;色 : </label> <span><input type="text" name="colores" class="am-input-sm xy-input-set" value="${basicinformation.colores}" readonly></span></span><br/>
                    <span class="Lg-text-height"><label>中文标识 : </label> <span><input type="text" name="chineselogo" class="am-input-sm xy-input-set" value="${basicinformation.chineselogo}"></span></span><br/>
                    <span class="Lg-text-height"><label>发售价（国内） : </label> <span><input type="text" name="cofferprice" class="am-input-sm xy-input-set" value="${basicinformation.cofferprice}"></span></span><br/>
                    <span class="Lg-text-height"><label>发售价（国外） : </label> <span><input type="text" name="eofferprice" class="am-input-sm xy-input-set" value="${basicinformation.eofferprice}" readonly></span></span><br/>
                    <span class="Lg-text-height"><label>发售日期（国内） : </label> <span><input type="date" name="csaledate" class="am-input-sm xy-input-set" value="${basicinformation.csaledate}"></span></span><br/>
                    <span class="Lg-text-height"><label>发售日期（国外） : </label> <span><input type="date" name="esaledate" class="am-input-sm xy-input-set" value="${fn:substring(basicinformation.esaledate, 0, 4)}-${fn:substring(basicinformation.esaledate, 5, 7)}-${fn:substring(basicinformation.esaledate, 8, 10)}" readonly></span></span><br/>
                    <span class="Lg-text-height"><label>图片（存储路径） : </label> <span>
                        <input type="text" name="imgUrl" id="imgUrl" class="am-input-sm xy-input-set" value="${basicinformation.imgUrl}" readonly>
                        <%-- 图片上传 --%>
                        <input type="file" name="file" id="file" /><img src="" id="img" >
                    </span></span><br/>
                    <span class="Lg-text-height"><label>小图片（存储路径） : </label> <span>
                        <input type="text" class="am-input-sm xy-input-set" name="smallImgUrl" value="${basicinformation.smallImgUrl}" readonly/>
                        <%-- 图片上传 --%>
                        <input type="file" name="files" id="files" /><img src="" id="imgs" >
                    </span></span><br/>
                    <input type="hidden" name="state" value="${basicinformation.state}" readonly/>
                    <input type="hidden" name="bid" value="${basicinformation.bid}"/>
                    <%--<td>爬取日期（添加日期） : </td><td><input type="date" name="createtime" class="am-input-sm xy-input-set">${basicinformation.createtime}</td><br/>--%>
                    <%--</c:forEach>--%>
                </c:when>
                <c:otherwise>
                    <span class="Lg-text-height"><label>品 &nbsp;&nbsp;牌 : </label> <span>
                        <select name="brand">
                            <option>请选择</option>
                            <c:if test="${not empty brandList}">
                                <c:forEach items="${brandList}" var="brand">
                                    <option>${brand.brandName}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </span></span><br/>
                    <span class="Lg-text-height"><label>名 &nbsp;&nbsp;称 : </label> <span><input type="text" name="name" class="am-input-sm xy-input-set" placeholder="请填写名称"></span></span><br/>
                    <span class="Lg-text-height"><label>货 &nbsp;&nbsp;号 : </label> <span><input type="text" name="artNo" class="am-input-sm xy-input-set" placeholder="请填写货号"></span></span><br/>
                    <span class="Lg-text-height"><label>颜 &nbsp;&nbsp;色 : </label> <span><input type="text" name="colores" class="am-input-sm xy-input-set" placeholder="请填写颜色"></span></span><br/>
                    <span class="Lg-text-height"><label>中文标识 : </label><span> <input type="text" name="chineselogo" class="am-input-sm xy-input-set" placeholder="请填写中文标识"></span></span><br/>
                    <span class="Lg-text-height"><label>发售价（国内） : </label> <span><input type="text" name="cofferprice" class="am-input-sm xy-input-set" placeholder="请填写发售价（国内）"></span></span><br/>
                    <span class="Lg-text-height"><label>发售价（国外） : </label> <span><input type="text" name="eofferprice" class="am-input-sm xy-input-set" placeholder="请填写发售价（国外）"></span></span><br/>
                    <span class="Lg-text-height"><label>发售日期（国内） : </label> <span><input type="date" name="csaledate" class="am-input-sm xy-input-set" placeholder="请填写发售日期（国内）"></span></span><br/>
                    <span class="Lg-text-height"><label>发售日期（国外） : </label> <span><input type="date" name="esaledate" class="am-input-sm xy-input-set" placeholder="请填写发售日期（国外）"></span></span><br/>
                    <span class="Lg-text-height"><label>图片（存储路径） : </label> <span>
                        <input type="text" name="imgUrl" class="am-input-sm xy-input-set" placeholder="请填写图片（存储路径）">
                        <%-- 图片上传 --%>
                        <input type="file" name="file" id="file" /><img src="" id="img" >
                    </span></span><br/>
                    <span class="Lg-text-height"><label>小图片（存储路径） : </label> <span>
                        <input type="text" name="smallImgUrl" class="am-input-sm xy-input-set" placeholder="请填写小图片（存储路径）"/>
                        <%-- 图片上传 --%>
                        <input type="file" name="files" id="files" /><img src="" id="imgs" >
                    </span></span><br/>
                        <%--<td>爬取日期（添加日期） : </td><td><input type="date" name="createtime" class="am-input-sm xy-input-set" placeholder="请填写爬取日期（添加日期）"></td><br/>--%>
                </c:otherwise>
                </c:choose>
                <span>
                    <button type="button" id="save-data" class="am-btn am-btn-xs am-btn-default"><span class="am-icon-save"></span> 保存</button> &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/stockx/basicinfrom/shoeLibraryFile" class="am-btn am-btn-xs am-btn-default"><span class="am-icon-trash-o"></span> 取消</a>
                </span>
                </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function($){
        if ($("#brand").val() != ""){
            $("#hqcx").val($("#brand").val());
        }

        $("#save-data").click(function(){
            $("#insert").submit();
        });
        });

    /**
     * 上传图片
     */

    $("#file").change(function(){
        var objUrl = getObjectURL(this.files[0]) ;
        console.log("objUrl = "+objUrl) ;
        if (objUrl) {
            $("#img").attr("src", objUrl) ;
        }
    }) ;

    $("#files").change(function(){
        var objUrl = getObjectURL(this.files[0]) ;
        console.log("objUrl = "+objUrl) ;
        if (objUrl) {
            $("#imgs").attr("src", objUrl) ;
        }
    }) ;
    //建立一個可存取到該file的url
    function getObjectURL(file) {
        var url = null ;
        if (window.createObjectURL!=undefined) { // basic
            url = window.createObjectURL(file) ;
        } else if (window.URL!=undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file) ;
        } else if (window.webkitURL!=undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
    }
</script>
<!-- content end -->
<jsp:include page="../common/bottom.jsp" flush="true"/>
