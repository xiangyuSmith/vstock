<%--
  Created by IntelliJ IDEA.
  User: administor
  Date: 2016/5/11
  Time: 10:43
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
    .Lg-text-color{
        color: #1a1a1a;
    }
    span{
        font-size: 14px;
        line-height: 19px;
    }
    .infoDiv{
        display: none;
        position: absolute;
        width: 210px;
        background-color: #fff;
        height: 90px;
        margin-top: 10px;
        margin-left: 10px;
        z-index: 999;
        border: 1px solid #ccc;
        border-radius: 5px;
        text-align: left;
        padding: 10px;
    }
</style>
<!-- content start -->
<div class="admin-content am-text-primary Lg-text-color">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">数据中心</strong> / <small>数据审查</small></div>
        </div>
        <div class="am-cf am-padding">
            <form action="/stockx/dataAudit/list" class="am-form am-form-inline" id="selectTiao"  method="POST">
                <div class="xy-search">
                    <label>编号:</label>
                    <div class="am-form-group"><input id="bidvalue" type="text" name="bid" placeholder="编号" value="<c:if test="${dictionaries.bid != 0}">${dictionaries.bid}</c:if>"></div>
                </div>
                <div class="xy-search">
                    <label>店铺名称:</label>
                    <div class="am-form-group">
                        <select data-am-selected="{searchBox: 1,maxHeight: 240}" placeholder="店铺名称" name="storeName" >
                            <option value="">店铺名称</option>
                            <c:if test="${not empty stockxStores}">
                                <c:forEach items="${stockxStores}" var="store">
                                    <option value="${store.name}" <c:if test="${storeName==store.name}">selected</c:if> >${store.name}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="xy-search">
                    <label>商品名称:</label>
                    <div class="am-form-group"><input type="text" name="productName" placeholder="商品名称" value="${productName}"></div>
                </div>
                <div class="xy-search">
                    <label>颜色状态:</label>
                    <div class="am-form-group">
                        <select data-am-selected name="colorStatus">
                            <option value="">--请选择--</option>
                            <option value="无" <c:if test="${colorStatus=='无'}">selected</c:if> >无</option>
                            <option value="null" <c:if test="${colorStatus=='null'}">selected</c:if>>null</option>
                        </select>
                    </div>
                </div>
                <div class="xy-search">
                    <label>标识:</label>
                    <div class="am-form-group">
                        <select data-am-selected="{searchBox: 1,maxHeight: 240}" class="identification" name="identification" placeholder="标识">
                            <c:choose>
                                <c:when test="${not empty dictionaries.identification}">
                                    <option value="${dictionaries.identification}">${dictionaries.identification}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value=""></option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                </div>
                <div class="xy-search">
                    <label>货号:</label>
                    <div class="am-form-group">
                        <select data-am-selected="{searchBox: 1,maxHeight: 240}" class="girard"  name="girard" placeholder="货号">
                            <c:choose>
                                <c:when test="${not empty dictionaries.girard}">
                                    <option value="${dictionaries.girard}">${dictionaries.girard}</option>
                                </c:when>
                                <c:otherwise>
                                    <option></option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                </div>


                <div class="xy-search">
                    <label>状态:</label>
                    <div class="am-form-group">
                        <select data-am-selected="{maxHeight: 200}" name="status">
                            <option value="-1">所有</option>
                            <option value="0" <c:if test="${status==0}">selected</c:if>>已爬取</option>
                            <option value="1" <c:if test="${status==1}">selected</c:if> >已审核</option>
                            <option value="2" <c:if test="${status==2}">selected</c:if>>延期待审</option>
                            <option value="3" <c:if test="${status==3}">selected</c:if>>无用数据</option>
                        </select>
                    </div>
                </div>
                <input type="button" class="am-btn am-btn-primary btn-loading-example xy-submit" id="selectSubmit" value=" 查 询 " />
            </form>
            <div>
                当前选中：
                <a href="" id="viewImgHref" target="_blank"><img id="viewImg" src="/stockx/assets/admin/image/1-1.jpg" style="width:150px;"></a><br/>
                <span id="viewImgSpan"></span>
            </div>

            <!--详细提交form表单-->
            <form action="findMeasurement" method="post" id="details">
                <input type="hidden" id="commid" name="id"/>
                <input type="hidden" id="commodityName" name="commodityName"/>
            </form>
        </div>
        <div class="am-g">
            <div class="am-u-sm-12">
                <div id="infoDiv" class="infoDiv"></div>
                <table class="am-table am-table-bd am-table-striped admin-content-table">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>商品名称</th>
                        <th>商品地址</th>
                        <th>颜色</th>
                        <th>标识</th>
                        <th>货号</th>
                        <th>状态</th>
                        <%--<th>获取时间</th>--%>
                        <th width="10%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="insert-data" class="xy-dis"></tr>
                    <c:if test="${not empty dCList}">
                        <c:forEach items="${dCList}" var="dictionariesData">
                            <tr>
                                <td class="id">${dictionariesData.bid}</td>
                                <td><span title="${dictionariesData.commodityData.commodityName}">${fn:substring(dictionariesData.commodityData.commodityName, 0, 50)}</span></td>
                                <td><a href="${dictionariesData.commodityData.productUrl}" target="_blank"  style="color: royalblue;">商品地址</a></td>
                                <td>${dictionariesData.colorly}</td>
                                <td class="identifications">
                                    <select data-am-selected="{searchBox: 1,maxHeight: 240}" class="identification">
                                        <c:choose>
                                            <c:when test="${not empty dictionariesData.identification}">
                                                <option value="${dictionariesData.identification}">${dictionariesData.identification}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value=""></option>
                                            </c:otherwise>
                                        </c:choose>
                                        <%--<c:if test="${not empty nameLists}">--%>
                                            <%--<c:forEach items="${nameLists}" var="data">--%>
                                                <%--<option value="${data.name}">${data.name} ${data.chineselogo}</option>--%>
                                            <%--</c:forEach>--%>
                                        <%--</c:if>--%>
                                    </select>
                                </td>
                                <td class="girards">
                                    <select data-am-selected="{searchBox: 1,maxHeight: 240}" class="girard" >
                                        <c:choose>
                                            <c:when test="${not empty dictionariesData.girard}">
                                                <option value="${dictionariesData.girard}">${dictionariesData.girard}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option></option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </td>
                                <td>
                                    <select class="status">
                                        <c:choose>
                                            <c:when test="${not empty dictionariesData.girard}">
                                                <option value="">已审查</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="">已爬取</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${status==2}">
                                                <option value="-1" selected>延期待审</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="2">延期待审</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <option value="3" <c:if test="${status==3}">selected</c:if> >无用数据</option>
                                    </select>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty dictionariesData.identification}">
                                            <a href="javascript:void(0)" class="submitItem am-badge am-badge-warning am-radius" style="color:#fff;" data-id="${dictionariesData.id}" >编辑</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascript:void(0)" class="submitItem am-badge am-badge-success am-radius" style="color:#fff;" data-id="${dictionariesData.id}" >提交</a>
                                        </c:otherwise>
                                    </c:choose>
                                    <a href="javascript:void(0)" class="deleteItem am-badge am-badge-danger am-radius" style="color:#fff;" data-id="${dictionariesData.id}" >删除</a>
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
    var data = null;
    var res = null;
    jQuery(function($){

        var artNoArray = null;
        var $keydownObj = null;
        var idenName = "";
        var artNoText = "";
        var imgUrl = "";
        var smallImgUrl = "";
        var viewImgSpan = "";
        var mouseX = 300;
        var mouseY = 300;
        //查询事件
        $("#selectSubmit").click(function () {
            if ($("#bidvalue").val() == ""){
                $("#bidvalue").val(0);
            }
            $("#selectTiao").submit();
        });

        <!--点击提交触发事件-->
        $(".submitItem").click(function () {
            var $this = $(this);
            var dicid = $this.attr("data-id");
            var identification = $this.parent().siblings().find("select[class='identification']").val();
            var girard = $this.parent().siblings().find("select[class='girard']").val();
            var status = $this.parent().siblings().find("select[class='status']").val();
            if((identification == "" || girard  == "") && status == ""){
                $.toaster({ priority : 'warning', title : '无法提交', message : '标识列或货号为空！'});
                return;
            }else{
                $.post("/stockx/dataAudit/updateDataAudit",{
                    'dicid':dicid,
                    'identification':identification,
                    'girard':girard,
                    'status':status
                },function(res){
                    $.toaster({ priority : 'info', title : '修改成功', message : '页面即将刷新！'});
                    setTimeout(function(){
                        window.location.reload();
                    },800);
                });
            }
        });


        //点击加载下来列表
        $(".identification").siblings("div").find("button").click(function(){
            var $select = $(this).parent().siblings("select");
            if(data != null){
                //获取select 下 option 的数量
                var optionsNumber = $select.find("option").size();
                if(optionsNumber < 1000){
                    var html = new Array(i);
                    for(var i = 0;i < data.length;i++){
                        html[i] = ['<option value="'+data[i].name+'">'+data[i].name+data[i].chineselogo+'</option>'].join("");
                    }
                    $select.append(html.join(""));
                }
            }else{
                $.post("/stockx/basicinfrom/getNames",{
                },function(dataRes){
                    data = dataRes;
                    //获取select 下 option 的数量
                    var optionsNumber = $select.find("option").size();
                    if(optionsNumber < 1000){
                        var html = new Array(i);
                        for(var i = 0;i < data.length;i++){
                            html[i] = ['<option value="'+data[i].name+'">'+data[i].name+data[i].chineselogo+'</option>'].join("");
                        }
                        $select.append(html.join(""));
                    }
                });
            }
        });

        $(".girard").siblings("div").find("button").click(function(){
            var $select = $(this).parent().siblings("select");
            if(res != null){
                var optionsNumber = $select.find("option").size();
                if(optionsNumber < 1000){
                    var html = new Array(i);
                    for(var i = 0;i < res.length;i++){
                        html[i] = ['<option value="'+res[i].artNo+'" data-small-img="'+res[i].smallImgUrl+'" ' +
                        'data-img="'+res[i].imgUrl+'" data-span="'+res[i].name+'">'+res[i].artNo+' </option>'].join("");
                    }
                    $select.append(html.join(""));
                }
            }
            $.post("/stockx/basicinfrom/getGirard",{
            },function(resData){
                res = resData;
                var optionsNumber = $select.find("option").size();
                if(optionsNumber < 1000){
                    var html = new Array(i);
                    for(var i = 0;i < res.length;i++){
                        html[i] = ['<option value="'+res[i].artNo+'" data-small-img="'+res[i].smallImgUrl+'" ' +
                        'data-img="'+res[i].imgUrl+'" data-span="'+res[i].name+'">'+res[i].artNo+' </option>'].join("");
                    }
                    $select.append(html.join(""));
                }
            });
        });

        //保存编辑
        $(".saveBrand").click(function(){
            var $this = $(this);
            if($this.parent().attr("class") == undefined || $this.parent().attr("class") == ""){
                var $girard = $this.parent().parent().parent().parent().siblings("td[class='girards']");
//                var $identification = $this.parent().parent().parent().parent().siblings("td[class='identifications']").find("span[class='identificationSpan']");
                var $identification = $this.parent().parent().parent().parent().siblings("td[class='identifications']");
                var $id = $this.parent().parent().parent().parent().siblings("td[class='id']");
                var girard = $girard.find("select[class='girard']").val();
                var identificationard = $identification.find("input[type='text']").val();
                var id = $id.text();
                $.post("/stockx/dataAudit/updateDictionResult",{
                    'id': id,
                    'girard': girard,
                    'identification':identificationard
                },function(res){
                    if(res.resultCount == 1){
                        setTimeout(function(){window.location.reload();},1);
                    }else{
                        $.toaster({ priority : 'error', title : '失败', message : '品牌数据更新失败.'});
                    }
                });
            }
        });

        $(".identification").change(function(){
           var name = $(this).val();
            $keydownObj = $(this).parent().siblings("td[class='girards']").find("select[class='girard']");
            var girard = $(this).parent().siblings().find("select").val();
            if(idenName != name){
                $.post(
                        "/stockx/basicinfrom/getGirard",
                        {"productName":name},
                        function(result){
                            artNoArray = result;
                            $keydownObj.html("");
                            var s = "";
                            for (var i = 0;i < result.length;i++){
                                s = s + '<option value="'+result[i].artNo+'" data-span="'+result[i].name+"  "+result[i].artNo+'" data-img="'+result[i].imgUrl+'" data-small-img="'+result[i].smallImgUrl+'">'+result[i].artNo+'</option>';
                            }
                            $keydownObj.append(s);
                            var idenName = name;
                        },"json");
            }
        });

        $(".girard").change(function(){
            var $this = $(this);
            smallImgUrl = $this.find("option").eq(0).attr("data-small-img");
            imgUrl = $this.find("option").eq(0).attr("data-img");
            viewImgSpan = $this.find("option").eq(0).attr("data-span");
            $("#viewImg").attr("src","/stockx"+smallImgUrl);
            $("#viewImgHref").attr("href","/stockx"+imgUrl);
            $("#viewImgSpan").text(viewImgSpan);
            var identification = $(this).parent().siblings().find("select").val();
            var $identificationObj = $(this).parent().siblings("td[class='identifications']").find("select[class='identification']");
            var artNo = $this.val();
            if(artNoText != artNo){
                $.post(
                        "/stockx/basicinfrom/getNames",
                        {"artNo":artNo},
                        function(result){
                            $identificationObj.html("");
                            var s = "";
                            for (var i = 0;i < result.length;i++){
                                s = s + '<option value="'+result[i].name+'" data-span="'+result[i].name+"  "+result[i].artNo+'" data-img="'+result[i].imgUrl+'" data-small-img="'+result[i].smallImgUrl+'">'+result[i].name+'</option>';
                            }
                            $identificationObj.append(s);
                            artNoText = artNo;
                        },"json");
            }
        });

        $(".girards").find("ul li").mousemove(function(event){
            var $this = $(this);
            var $obj = $(this).parent().parent().parent().parent().find("select option:eq("+dataIndex+")");
            var dataIndex = $(this).attr("data-index");
            smallImgUrl = $(this).parent().parent().parent().parent().find("select option:eq("+dataIndex+")").attr("data-small-img");
            imgUrl = $(this).parent().parent().parent().parent().find("select option:eq("+dataIndex+")").attr("data-img");
            viewImgSpan = $(this).parent().parent().parent().parent().find("select option:eq("+dataIndex+")").attr("data-span");
            var artNo = $(this).parent().parent().parent().parent().find("select option:eq("+dataIndex+")").val();
            $("#viewImg").attr("src","/stockx"+smallImgUrl);
            $("#viewImgHref").attr("href","/stockx"+imgUrl);
            $("#viewImgSpan").text(viewImgSpan + "  " + artNo);
            //show(event,$obj);
        });

        function show(event,$this) {
            var infoDiv = $("#infoDiv");
            mouseOver(event);
            infoDiv.css("display","block");
            infoDiv.css("left",mouseX + 10 + "px");
            infoDiv.css("top",mouseY + 10 + "px");
        }
        function mouseOver(obj) {
            e = obj || window.event;
            mouseX =  e.layerX|| e.offsetX;
            mouseY =  e.layerY|| e.offsetY;
            mouseX += 300;
            mouseY += 300;
        }
    });
</script>
<!-- content end -->
<jsp:include page="../common/bottom.jsp" flush="true"/>
