<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/top.jsp"%>
<style type="text/css">
    .xy-dis{
        display: none;
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
    .for_list{
        position: absolute;width: 500px;overflow: hidden;border: 1px solid #ccc;box-shadow: 1px 1px 3px #ededed;background-color: #fff;display: none;
    }
    .for_list ul {
        padding:0;
    }
    .for_list li {
        width: 500px;
        color: #000;
        list-style: none;
        font-size: 14px;
        line-height: 25px;
        padding: 0 8px;
        position: relative;
        cursor: default;
    }
    .for_list li:hover{
        background-color: #f0f0f0;
    }
</style>
<!-- content start -->
<div class="admin-content">
    <div class="admin-content-body">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">数据中心</strong> / <small>数据审查2</small></div>
        </div>
        <div class="for_list">
            <ul class="for_list_ul"></ul>
        </div>
        <div class="am-g">
            <form action="/dataAudit/list" class="am-form am-form-inline" id="selectTiao"  method="POST">
                <input type="hidden" name="type" value="${type}" />
                <div class="am-u-md-12 am-padding-bottom">
                    <div class="am-u-md-4">
                        <label class="am-padding-right">商品编号:</label>
                        <div class="am-form-group"><input id="bidvalue" type="text" name="id" placeholder="编号" value="<c:if test="${dictionaries.id != 0}">${dictionaries.id}</c:if>"></div>
                    </div>
                    <div class="am-u-md-4 am-u-end">
                        <label class="am-padding-right">商品名称:</label>
                        <div class="am-form-group"><input type="text" name="productName" placeholder="商品名称" value="${productName}"></div>
                    </div>
                    <div class="am-u-md-4">
                        <label class="am-padding-right">商品货号:</label>
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
                </div>
                <div class="am-u-md-12 am-padding-bottom">
                    <div class="am-u-md-4">
                        <label class="am-padding-right">店铺名称:</label>
                        <div class="am-form-group">
                            <select data-am-selected="{searchBox: 1,maxHeight: 240}" placeholder="店铺名称" name="storeName" >
                                <option value="1">--请选择店铺--</option>
                                <c:if test="${not empty stockxStores}">
                                    <c:forEach items="${stockxStores}" var="store">
                                        <option value="${store.name}" <c:if test="${storeName==store.name}">selected</c:if> >${store.name}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="am-u-md-4">
                        <label class="am-padding-right">颜色状态:</label>
                        <div class="am-form-group">
                            <select data-am-selected name="colorly">
                                <option value="1">--请选择--</option>
                                <option value="无" <c:if test="${dictionaries.colorly=='无'}">selected</c:if> >无</option>
                                <option value="null" <c:if test="${dictionaries.colorly=='null'}">selected</c:if>>null</option>
                            </select>
                        </div>
                    </div>
                    <div class="am-u-md-4">
                        <label class="am-padding-right">商品标识:</label>
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
                </div>

                <div class="am-u-md-12 am-padding-bottom">
                    <div class="am-u-md-4">
                        <label class="am-padding-right">商品状态:</label>
                        <div class="am-form-group">
                            <select data-am-selected="{maxHeight: 200}" name="status">
                                <option value="9">所有</option>
                                <option value="0" <c:if test="${dictionaries.status==0}">selected</c:if>>已爬取</option>
                                <option value="1" <c:if test="${dictionaries.status==1}">selected</c:if> >已审核</option>
                                <option value="2" <c:if test="${dictionaries.status==2}">selected</c:if>>延期待审</option>
                                <option value="3" <c:if test="${dictionaries.status==3}">selected</c:if>>无用数据</option>
                            </select>
                        </div>
                    </div>
                    <div class="am-u-md-8">
                        <button type="submit" class="am-btn am-btn-primary am-fr">查 询</button>
                    </div>
                </div>
            </form>
            <div class="am-u-md-12">
                <div class="am-u-md-8 am-u-end">
                    当前选中：
                    <a href="" id="viewImgHref" target="_blank"><img id="viewImg" src="/assets/admin/image/1-1.jpg" style="width:150px;"></a><br/>
                    <span id="viewImgSpan"></span>
                </div>
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
                        <th width="10%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="insert-data" class="xy-dis"></tr>
                    <c:if test="${not empty dCList}">
                        <c:forEach items="${dCList}" var="dictionariesData">
                            <tr>
                                <td class="id">${dictionariesData.id}</td>
                                <td><span title="${dictionariesData.commodityData.commodityName}">${fn:substring(dictionariesData.commodityData.commodityName, 0, 50)}</span></td>
                                <td><a href="${dictionariesData.commodityData.productUrl}" target="_blank"  style="color: royalblue;">商品地址</a></td>
                                <td>${dictionariesData.colorly}</td>
                                <td class="identifications">
                                    <input type="text" class="identification" />
                                </td>
                                <td class="girards">
                                    <input type="text" class="girard" />
                                </td>
                                <td>
                                    <select class="status">
                                        <option value="0" <c:if test="${dictionariesData.status==0}">selected</c:if>>已爬取</option>
                                        <option value="1" <c:if test="${dictionariesData.status==1}">selected</c:if> >已审核</option>
                                        <option value="2" <c:if test="${dictionariesData.status==2}">selected</c:if>>延期待审</option>
                                        <option value="3" <c:if test="${dictionariesData.status==3}">selected</c:if>>无用数据</option>
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

        var $forList = $(".for_list");
        var artNoArray = null;
        var $keydownObj = null;
        var idenName = "";
        var artNoText = "";
        var imgUrl = "";
        var smallImgUrl = "";
        var viewImgSpan = "";
        var mouseX = 300;
        var mouseY = 300;
        var $identification = null;
        var $girard = null;
        var type=0;

        $(".for_list").click(function(e){
            e.stopPropagation();
            $(this).show();
        });
        $(document).click(function(){
            $('.for_list').hide();
        });
        //监听input值改变
        $(".identification").on("input propertychange",function(e){
            e.stopPropagation();
            $identification = $(this);
            type = 1;
            var name = $(this).val();
            var $ul = $(".for_list_ul");
            if($(this).val()!=""){
                $.post("/basicinfrom/getBftList",{
                    'name':name,
                    'type':"1"
                },function(dataRes){
                    var html = new Array(i);
                    if(dataRes.length > 10){
                        for(var i = 0;i < 10;i++){
                            html[i] = ['<li class="for_list_ul_li" data-small-img="'+dataRes[i].smallImgUrl+'"  data-img="'+dataRes[i].imgUrl+'"  data-span="'+dataRes[i].name+'" data-name="'+dataRes[i].name+'">'+dataRes[i].name+dataRes[i].chineselogo+'</li>'].join("");
                        }
                    }else{
                        for(var i = 0;i < dataRes.length;i++){
                            html[i] = ['<li class="for_list_ul_li" data-small-img="'+dataRes[i].smallImgUrl+'"  data-img="'+dataRes[i].imgUrl+'"  data-span="'+dataRes[i].name+'" data-name="'+dataRes[i].name+'">'+dataRes[i].name+dataRes[i].chineselogo+'</li>'].join("");
                        }
                    }
                    $ul.html("");
                    $ul.append(html.join(""));
                });
                $('.for_list').show();
                if($(".identification").index(this) >= 5){
                    $forList.css("margin-top","-261px");
                    $(this).before($forList);
                }else{
                    $forList.css("margin-top","1px");
                    $(this).after($forList);
                }
            }else{
                $ul.html("");
                $('.for_list').hide();
            }
        });

        $(".girard").on("input propertychange",function(e){
            e.stopPropagation();
            $girard = $(this);
            type = 2;
            var name = $(this).val();
            var $ul = $(".for_list_ul");
            if($(this).val()!=""){
                $.post("/basicinfrom/getBftList",{
                    'name':name,
                    'type':"2"
                },function(dataRes){
                    var html = new Array(i);
                    if(dataRes.length > 10){
                        for(var i = 0;i < 10;i++){
                            html[i] = ['<li class="for_list_ul_li" data-small-img="'+dataRes[i].smallImgUrl+'"  data-img="'+dataRes[i].imgUrl+'"  data-span="'+dataRes[i].name+'" data-name="'+dataRes[i].artNo+'">'+dataRes[i].artNo+dataRes[i].chineselogo+'</li>'].join("");
                        }
                    }else{
                        for(var i = 0;i < dataRes.length;i++){
                            html[i] = ['<li class="for_list_ul_li" data-small-img="'+dataRes[i].smallImgUrl+'"  data-img="'+dataRes[i].imgUrl+'"  data-span="'+dataRes[i].name+'" data-name="'+dataRes[i].artNo+'">'+dataRes[i].artNo+dataRes[i].chineselogo+'</li>'].join("");
                        }
                    }
                    $ul.html("");
                    $ul.append(html.join(""));
                });
                $('.for_list').show();
                if($(".girard").index(this) >= 5){
                    $forList.css("margin-top","-261px");
                    $(this).before($forList);
                }else{
                    $forList.css("margin-top","1px");
                    $(this).after($forList);
                }
            }else{
                $ul.html("");
                $('.for_list').hide();
            }
        });

        $(".for_list").on("click",".for_list_ul_li",function(){
            var name = $(this).attr("data-name");
            if(type == 1){
                $identification.val(name);
                smallImgUrl = $(this).attr("data-small-img");
                imgUrl = $(this).attr("data-img");
                viewImgSpan = $(this).attr("data-span");
                $("#viewImg").attr("src","http://admin.v-stock.com"+smallImgUrl);
                $("#viewImgHref").attr("href","http://admin.v-stock.com"+imgUrl);
                $("#viewImgSpan").text(viewImgSpan);
                $.post("/basicinfrom/getGirard",{
                    "productName":name
                },function(result){
                    $identification.parent().siblings("td[class='girards']").find("input[class='girard']").val(result[0].artNo);
                });
            }else{
                $girard.val(name);
                smallImgUrl = $(this).attr("data-small-img");
                imgUrl = $(this).attr("data-img");
                viewImgSpan = $(this).attr("data-span");
                $("#viewImg").attr("src","http://admin.v-stock.com"+smallImgUrl);
                $("#viewImgHref").attr("href","http://admin.v-stock.com"+imgUrl);
                $("#viewImgSpan").text(viewImgSpan);
                $.post("/basicinfrom/getNames",{
                    "artNo":name
                },function(result){
                    $girard.parent().siblings("td[class='identifications']").find("input[class='identification']").val(result[0].name);
                });
            }
            $(this).hide();
        });

        //删除事件
        $(".deleteItem").click(function () {
            var dicid = $(this).attr("data-id");
            $.post("/dataAudit/updateDataAudit",{
                'dicid':dicid,
                'status':9
            },function(res){
                $.toaster({ priority : 'info', title : '删除成功', message : '页面即将刷新！'});
                setTimeout(function(){
                    window.location.reload();
                },800);
            });
        });

        <!--点击提交触发事件-->
        $(".submitItem").click(function () {
            var $this = $(this);
            var dicid = $this.attr("data-id");
            var identification = $this.parent().siblings().find("input[class='identification']").val();
            var girard = $this.parent().siblings().find("input[class='girard']").val();
            var status = $this.parent().siblings().find("select[class='status']").val();
            if((identification == "" || girard  == "") && status == ""){
                $.toaster({ priority : 'warning', title : '无法提交', message : '标识列或货号为空！'});
                return;
            }else{
                $.post("/dataAudit/updateDataAudit",{
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
    });
</script>
<!-- content end -->
<jsp:include page="../common/bottom.jsp" flush="true"/>
