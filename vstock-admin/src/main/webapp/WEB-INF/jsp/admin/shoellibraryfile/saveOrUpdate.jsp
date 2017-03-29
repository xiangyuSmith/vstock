<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<!-- content start -->
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">鞋库基本档案信息</strong> / <small>新增界面</small></div>
        </div>
        <hr>
        <div class="am-g">
            <div class="am-panel-bd">
                <div class="am-u-md-12">
                    <form id="insert" action="/basicinfrom/insertbasicinfrom" method="post" enctype="multipart/form-data">
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
                        <input type="hidden" name="bscsaledate" value="${csaledates}"/>
                        <c:choose>
                            <c:when test="${not empty basicinformation.id}">
                                <input type="hidden" name="id" value="${basicinformation.id}">
                                <div class="am-form-group">
                                    <label>品 &nbsp;&nbsp;牌 : </label>
                                    <input type="hidden" id="brand" class="am-input-sm" value="${basicinformation.brand}">
                                    <select id="hqcx" name="brand">
                                        <c:if test="${not empty brandList}">
                                            <c:forEach items="${brandList}" var="brand">
                                                <option>${brand.brandName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                                <div class="am-form-group">
                                    <label>名 &nbsp;&nbsp;称 : </label>
                                    <input type="text" name="name" class="am-input-sm" value="${basicinformation.name}" readonly>
                                </div>
                                <div class="am-form-group">
                                    <label>货 &nbsp;&nbsp;号 : </label>
                                    <input type="text" name="artNo" class="am-input-sm" value="${basicinformation.artNo}">
                                </div>
                                <div class="am-form-group">
                                    <label>颜 &nbsp;&nbsp;色 : </label>
                                    <input type="text" name="colores" class="am-input-sm" value="${basicinformation.colores}" readonly>
                                </div>
                                <div class="am-form-group">
                                    <label>中文标识 : </label>
                                    <input type="text" name="chineselogo" class="am-input-sm" value="${basicinformation.chineselogo}">
                                </div>
                                <div class="am-form-group">
                                        <label>发售价（国内） : </label><input type="text" name="cofferprice" class="am-input-sm" value="${basicinformation.cofferprice}">
                                </div>
                                <div class="am-form-group">
                                    <label>发售价（国外） : </label> <input type="text" name="eofferprice" class="am-input-sm" value="${basicinformation.eofferprice}" readonly>
                                </div>
                                <div class="am-form-group">
                                    <label>发售日期（国内） : </label> <input type="date" name="csaledate" class="am-input-sm" value="${basicinformation.csaledate}">
                                </div>
                                <div class="am-form-group">
                                    <label>发售日期（国外） : </label> <input type="date" name="esaledate" class="am-input-sm" value="${fn:substring(basicinformation.esaledate, 0, 4)}-${fn:substring(basicinformation.esaledate, 5, 7)}-${fn:substring(basicinformation.esaledate, 8, 10)}" readonly>
                                </div>
                                <div class="am-form-group">
                                    <label>图片（存储路径） : </label>
                                    <input type="text" name="imgUrl" id="imgUrl" class="am-input-sm" value="${basicinformation.imgUrl}" readonly />
                                    <%-- 图片 --%>
                                    <input type="file" name="file" id="file" /><img src="" id="img" >
                                </div>
                                <div class="am-form-group">
                                    <label>缩略图（存储路径） : </label>
                                    <input type="text" class="am-input-sm" name="smallImgUrl" value="${basicinformation.smallImgUrl}" readonly />
                                    <%-- 缩略图 --%>
                                    <input type="file" name="files" id="files" /><img src="" id="imgs" />
                                </div>
                                <input type="hidden" name="state" value="${basicinformation.state}" readonly/>
                                <input type="hidden" name="bid" value="${basicinformation.bid}"/>
                            </c:when>
                            <c:otherwise>
                                <div class="am-form-group">
                                   <label>品 &nbsp;&nbsp;牌 : </label>
                                    <select name="brand" class="text-input">
                                        <option>请选择</option>
                                        <c:if test="${not empty brandList}">
                                            <c:forEach items="${brandList}" var="brand">
                                                <option>${brand.brandName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                                <div class="am-form-group">
                                    <label>名 &nbsp;&nbsp;称 : </label> <input type="text" name="name" class="am-input-sm" placeholder="请填写名称" required />
                                </div>
                                <div class="am-form-group">
                                    <label>货 &nbsp;&nbsp;号 : </label> <input type="text" name="artNo" class="am-input-sm" placeholder="请填写货号" required />
                                </div>
                                <div class="am-form-group">
                                    <label>颜 &nbsp;&nbsp;色 : </label> <input type="text" name="colores" class="am-input-s" placeholder="请填写颜色" required />
                                </div>
                                <div class="am-form-group">
                                    <label>中文标识 : </label> <input type="text" name="chineselogo" class="am-input-s" placeholder="请填写中文标识" required />
                                </div>
                                <div class="am-form-group">
                                    <label>发售价（国内） : </label> <input type="text" name="cofferprice" class="am-input-sm" placeholder="请填写发售价（国内）" />
                                </div>
                                <div class="am-form-group">
                                    <label>发售价（国外） : </label> <input type="text" name="eofferprice" class="am-input-sm" placeholder="请填写发售价（国外）" />
                                </div>
                                <div class="am-form-group">
                                    <label>发售日期（国内） : </label> <input type="date" name="csaledate" class="am-input-sm" placeholder="请填写发售日期（国内）" />
                                </div>
                                <div class="am-form-group">
                                    <label>发售日期（国外） : </label> <input type="date" name="esaledate" class="am-input-sm" placeholder="请填写发售日期（国外）" />
                                </div>
                                <div class="am-form-group">
                                    <label>图片（存储路径） : </label>
                                    <input type="text" name="imgUrl" class="am-input-sm" placeholder="请填写图片（存储路径）">
                                    <%-- 图片 --%>
                                    <input type="file" name="file" id="file"  required /><img src="" id="img" >
                                </div>
                                <div class="am-form-group">
                                    <label>缩略图（存储路径） : </label>
                                    <input type="text" name="smallImgUrl" class="am-input-sm" placeholder="请填写缩略图（存储路径）"/>
                                    <%-- 缩略图 --%>
                                    <input type="file" name="files" id="files"  required /><img src="" id="imgs" >
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <button type="submit" class="am-btn am-btn-xs am-btn-primary  am-margin-right-sm"><span class="am-icon-save am-margin-right-xs"></span> 保存</button>
                        <a href="/basicinfrom/shoeLibraryFile" class="am-btn am-btn-xs am-btn-default"><span class="am-icon-trash-o"></span> 取消</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        if ($("#brand").val() != ""){
            $("#hqcx").val($("#brand").val());
        }

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
        });

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
    });
</script>
<!-- content end -->
<jsp:include page="../common/bottom.jsp" flush="true"/>
