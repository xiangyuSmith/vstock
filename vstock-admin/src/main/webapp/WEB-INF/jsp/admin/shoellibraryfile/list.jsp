<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
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
    .Lg-border-left{
        padding-left: 30px;
    }
    .Lg-asize{
        width: 64px;
        height: 28px;
        font-size: 12px;
    }
</style>
<!-- content start -->
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">鞋库</strong> / <small>鞋库基本档案信息</small></div>
        </div>
        <hr>
        <div class="am-g">
            <form action="shoeLibraryFile" class="am-form am-form-inline" method="post">
                <div class="am-u-md-12 am-padding-bottom">
                    <div class="am-u-md-3">
                        <label class="am-padding-right"><b>编号:</b></label>
                        <div class="am-form-group"><input type="text" id="sid" name="id" placeholder="编号" value="${basicinformation.id}"></div>
                    </div>
                    <div class="am-u-md-3">
                        <label class="am-padding-right"><b>标识:&nbsp;&nbsp;</b></label>
                        <div class="am-form-group"><input type="text" id="chineselogo" name="chineselogo" placeholder="中文标识" value="${basicinformation.chineselogo}"></div>
                    </div>
                    <div class="am-u-md-3">
                        <label class="am-padding-right"><b>名称:</b></label>
                        <div class="am-form-group"><input type="text" id="sname" name="name" placeholder="鞋名" value="${basicinformation.name}"></div>
                    </div>
                    <div class="am-u-md-3">
                        <label class="am-padding-right"><b>货号:</b></label>
                        <div class="am-form-group"><input type="text" id="sartNo" name="artNo" placeholder="商品货号" value="${basicinformation.artNo}"></div>
                    </div>
                </div>
                <div class="am-u-md-12 am-padding-bottom">
                    <div class="am-u-md-3">
                        <label class="am-padding-right"><b>品牌:</b></label>
                        <div class="am-form-group">
                            <select id="searchBrand" name="brand" placeholder="品牌">
                                <c:choose>
                                    <c:when test="${not empty basicinformation.brand}">
                                        <option value="${basicinformation.brand}">${basicinformation.brand}</option>
                                        <option value="">全部</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="">全部</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${not empty brandList}">
                                    <c:forEach items="${brandList}" var="brand">
                                        <option value="${brand.brandName}">${brand.brandName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>

                    <div class="am-u-md-3">
                        <label class="am-padding-right"><b>是否有中文标识:</b></label>
                        <div class="am-form-group" id="chineselogos">
                            <select name="chineselogos" style="width: 80px;">
                                <c:choose>
                                    <c:when test="${basicinformation.chineselogos == 1}">
                                        <option value="${basicinformation.chineselogos}">有</option>
                                        <option value="0">全部</option>
                                        <option value="2">无</option>
                                    </c:when>
                                    <c:when test="${basicinformation.chineselogos == 2}">
                                        <option value="${basicinformation.chineselogos}">无</option>
                                        <option value="0">全部</option>
                                        <option value="1">有</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">全部</option>
                                        <option value="1">有</option>
                                        <option value="2">无</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                    </div>

                    <div class="am-u-md-3">
                        <label class="am-padding-right"><b>国内发售价:</b></label>
                        <div class="am-form-group" id="bscofferprice">
                            <select name="bscofferprice" style="width: 80px;">
                                <c:choose>
                                    <c:when test="${basicinformation.bscofferprice == 1}">
                                        <option value="${basicinformation.bscofferprice}">有</option>
                                        <option value="0">全部</option>
                                        <option value="2">无</option>
                                    </c:when>
                                    <c:when test="${basicinformation.bscofferprice == 2}">
                                        <option value="${basicinformation.bscofferprice}">无</option>
                                        <option value="0">全部</option>
                                        <option value="1">有</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">全部</option>
                                        <option value="1">有</option>
                                        <option value="2">无</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                    </div>

                    <div class="am-u-md-3">
                        <label class="am-padding-right"><b>国内发售日期:</b></label>
                        <div class="am-form-group" id="csaledate">
                            <select name="csaledate" style="width: 80px;">
                                <c:choose>
                                    <c:when test="${basicinformation.csaledate == 1}">
                                        <option value="${basicinformation.csaledate}">有</option>
                                        <option value="0">全部</option>
                                        <option value="2">无</option>
                                    </c:when>
                                    <c:when test="${basicinformation.csaledate == 2}">
                                        <option value="${basicinformation.csaledate}">无</option>
                                        <option value="0">全部</option>
                                        <option value="1">有</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">全部</option>
                                        <option value="1">有</option>
                                        <option value="2">无</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="am-u-md-12 am-padding-bottom">
                    <div class="am-u-md-4">
                        <label class="am-padding-right"><b>国内发售价(低):</b></label>
                        <div class="am-form-group"><input type="text" id="cofferprice" name="cofferprice" placeholder="国内发售价（低）" value="${basicinformation.cofferprice}"></div>
                    </div>
                    <div class="am-u-md-4 am-u-end">
                        <label class="am-padding-right"><b>国内发售价(高):</b></label>
                        <div class="am-form-group"><input type="text" id="cofferprices" name="cofferprices" placeholder="国内发售价（高）" value="${basicinformation.cofferprices}"></div>
                    </div>
                </div>
                <div class="xy-search Lg-border-left am-margin-left-xs">
                    <label><b>国内发售开始时间:&nbsp;&nbsp;</b></label>
                    <div class="am-form-group">
                        <input type="text" id="sstartCsaledate" name="startCsaledate" placeholder="" value="${startCsaledate}">
                        <div class="dateStartTime"></div>
                    </div>
                </div>
                <div class="xy-search Lg-border-left am-margin-left">
                    <label><b>国内发售结束时间:&nbsp;&nbsp;</b></label>
                    <div class="am-form-group">
                        <input type="text" id="sendCsaledate" name="endCsaledate" placeholder="" value="${endCsaledate}">
                        <div class="dateEndTime"></div>
                    </div>
                </div>
                <div class="am-u-md-12">
                    <input type="submit" class="am-btn am-btn-primary  am-btn-sm btn-loading-example am-fr am-margin-right-sm" value=" 查 询 " />
                    <a href="/basicinfrom/insertbasicinfromjsp?id="><button type="button" id="add" class="am-btn am-btn-default am-btn-sm am-fr  am-margin-right-sm"><i class="am-icon-plus am-margin-right-xs"></i> 新增</button></a>
                </div>
            </form>
        </div>

        <form id="insertUpdate" action="insertbasicinfromjsp" method="post">
            <input type="hidden" name="pageNow" value="${page.pageNow}"/>
            <input type="hidden" name="brid" id="brid"/>
            <input type="hidden" name="id" id="bid"/>
            <input type="hidden" name="brand" id="brand"/>
            <input type="hidden" name="name" id="name"/>
            <input type="hidden" name="artNo" id="artNo"/>
            <input type="hidden" name="chineselogo" id="bchineselogo"/>
            <input type="hidden" name="chineselogos" id="bchineselogos"/>
            <input type="hidden" name="cofferprice" id="bcofferprice"/>
            <input type="hidden" name="cofferprices" id="bcofferprices"/>
            <input type="hidden" name="state" id="state"/>
            <input type="hidden" name="startCsaledate" id="startCsaledate"/>
            <input type="hidden" name="endCsaledate" id="endCsaledate"/>
        </form>

        <form id="deletebas" action="insertbasicinfrom" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" id="baid"/>
            <input type="hidden" name="bid" id="bkid"/>
            <input type="hidden" name="brand" id="barand"/>
            <input type="hidden" name="name" id="baname"/>
            <input type="hidden" name="artNo" id="bartNo"/>
            <input type="hidden" name="colores" id="bacolores"/>
            <input type="hidden" name="chineselogo" id="bachineselogo"/>
            <input type="hidden" name="cofferprice" id="bacofferprice"/>
            <input type="hidden" name="eofferprice" id="baeofferprice"/>
            <input type="hidden" name="csaledate" id="bacsaledate"/>
            <input type="hidden" name="esaledate" id="baesaledate"/>
            <input type="hidden" name="imgUrl" id="baimgUrl"/>
            <input type="hidden" name="smallImgUrl" id="basmallImgUrl"/>
            <input type="hidden" name="createtime" id="bcreatetime"/>
            <input type="hidden" name="state" value="0"/>
            <input type="hidden" name="pageNow" value="${page.pageNow}"/>
            <input type="hidden" name="brid" id="barid"/>
            <input type="hidden" name="bbrandName" id="berand"/>
            <input type="hidden" name="bname" id="aname"/>
            <input type="hidden" name="bartNo" id="aartNo"/>
            <input type="hidden" name="bstartCsaledate" id="astartCsaledate"/>
            <input type="hidden" name="bendCsaledate" id="aendCsaledate"/>
        </form>

        <div class="am-g">
            <div class="am-u-sm-12">
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                        <tr>
                            <th>编号</th>
                            <th>品牌</th>
                            <th>名称</th>
                            <th>货号</th>
                            <th>颜色</th>
                            <th>国内发售日期</th>
                            <th>国外发售日期</th>
                            <th>国内发售价</th>
                            <th>国外发售价</th>
                            <th>中文标识</th>
                            <th>图片</th>
                            <th>管理</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr id="insert-data" class="xy-dis"></tr>
                        <c:if test="${not empty basicinformationList}">
                            <c:forEach items="${basicinformationList}" var="basicinformation">
                                <tr>
                                    <td>${basicinformation.id}</td>
                                    <td>${basicinformation.brand}</td>
                                    <td>${basicinformation.name}</td>
                                    <td>${basicinformation.artNo}</td>
                                    <td>${basicinformation.colores}</td>
                                    <td>${basicinformation.csaledate}</td>
                                    <td>${basicinformation.esaledate}</td>
                                    <td>${basicinformation.cofferprice}</td>
                                    <td>${basicinformation.eofferprice}</td>
                                    <td>${basicinformation.chineselogo}</td>
                                    <td>
                                        <a href="${basicinformation.imgUrl}" target="_blank" title="查看大图"><img src="${basicinformation.smallImgUrl}" style="height: 40px; width: 55px;"/></a>
                                    </td>
                                    <td>
                                        <div class="am-dropdown" data-am-dropdown>
                                            <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                                            <ul class="am-dropdown-content">
                                                <li><a href="javascript:void(0)" class="updatebasic" data-comid="${basicinformation.id}" date-state="${basicinformation.state}"> 编辑</a></li>
                                                <li><a href="javascript:void(0)" class="deletebasic" data-cid="${basicinformation.id}" data-bid="${basicinformation.bid}" data-brand="${basicinformation.brand}"
                                                       data-name="${basicinformation.name}" data-artNo="${basicinformation.artNo}" data-colores="${basicinformation.colores}"
                                                       data-csaledate="${basicinformation.csaledate}" data-esaledate="${basicinformation.esaledate}"
                                                       data-cofferprice="${basicinformation.cofferprice}" data-eofferprice="${basicinformation.eofferprice}" data-chineselogo="${basicinformation.chineselogo}"
                                                       data-imgUrl="${basicinformation.imgUrl}" data-smallImgUrl="${basicinformation.smallImgUrl}" data-createtime="${basicinformation.createtime}"> 删除</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
                <jsp:include page="../common/paging.jsp" flush="true">
                    <jsp:param name="page" value="${page}"/>
                    <jsp:param name="linkAddress" value="${linkAddress}"/>
                </jsp:include>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    jQuery(function($){

        var div1 = "#sstartCsaledate";
        var div2 = "#sendCsaledate";
        getDatePic(div1,div2);

        //绑定添加事件
        $("body").on("click","#save-data",function(){
            $.post("/admin/insertjsp",{
            },function(res){
                setTimeout(function(){window.location.reload();},50);
            });
        });

        //绑定删除事件
        $("body").on("click","#delete-data",function(){
            $('#insert-data').html("");
            $('#insert-data').fadeOut(200);
        });

        <!--点击编辑触发事件-->
        $(".updatebasic").click(function () {
            var $this = $(this);
            var comid = $this.attr("data-comid");
            var commid = $("#sid").val();
            $("#state").val($this.attr("date-state"));
            $("#brid").val(commid);
            $("#bid").val(comid);
            $("#brand").val(jQuery("#searchBrand  option:selected").val());
            $("#name").val($("#sname").val());
            $("#artNo").val($("#sartNo").val());
            $("#bchineselogo").val($("#chineselogo").val());
            $("#bchineselogos").val(jQuery("#chineselogos  option:selected").val());
            $("#bcofferprice").val($("#cofferprice").val());
            $("#bcofferprices").val($("#cofferprices").val());
            $("#startCsaledate").val($("#sstartCsaledate").val());
            $("#aendCsaledate").val($("#sendCsaledate").val());

            $("#insertUpdate").submit();
        });

        <!--点击删除触发事件-->
        $(".deletebasic").click(function () {
            var $this = $(this);
            var id = $this.attr("data-cid");
            var bid = $this.attr("data-bid");
            var brand = $this.attr("data-brand");
            var name = $this.attr("data-name");
            var artNo = $this.attr("data-artNo");
            var colores = $this.attr("data-colores");
            var csaledate = $this.attr("data-csaledate");
            var esaledate = $this.attr("data-esaledate");
            var cofferprice = $this.attr("data-cofferprice");
            var eofferprice = $this.attr("data-eofferprice");
            var chineselogo = $this.attr("data-chineselogo");
            var imgUrl = $this.attr("data-imgUrl");
            var smallImgUrl = $this.attr("data-smallImgUrl");
            var createtime = $this.attr("data-createtime");
            $("#baid").val(id);
            $("#bkid").val(bid);
            $("#barand").val(brand);
            $("#baname").val(name);
            $("#bartNo").val(artNo);
            $("#bacolores").val(colores);
            $("#bacsaledate").val(csaledate);
            $("#baesaledate").val(esaledate);
            $("#bacofferprice").val(cofferprice);
            $("#baeofferprice").val(eofferprice);
            $("#bachineselogo").val(chineselogo);
            $("#baimgUrl").val(imgUrl);
            $("#basmallImgUrl").val(smallImgUrl);
            $("#bcreatetime").val(createtime);

            $("#barid").val($("#sid").val());
            $("#berand").val(jQuery("#searchBrand  option:selected").val());
            $("#aname").val($("#sname").val());
            $("#aartNo").val($("#sartNo").val());
            $("#astartCsaledate").val($("#sstartCsaledate").val());
            $("#endCsaledate").val($("#sendCsaledate").val());
            $("#deletebas").submit();
        });
    });
</script>
<!-- content end -->
<jsp:include page="../common/bottom.jsp" flush="true"/>