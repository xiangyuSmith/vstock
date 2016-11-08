/**
 * 分页链接HTML
 * @param divId
 * @param formId
 * @param totalRow
 * @param pageSize
 * @param pageNumber
 * @param totalPages
 * @param isSelectPage
 * @param isSelectSize
 * @param orderColunm
 * @param orderMode
 * @returns {String}
 */			
function splitPageHtml(divId, formId, totalRow, pageSize, pageNumber, totalPages, isSelectPage, isSelectSize, orderColunm, orderMode){
	var splitStr = ' <div class="pagination pagination-sm pagination-centered" style="padding-right: 333px;padding: 0;">';
	
	if (pageNumber == 1 || totalPages == 0) {
		splitStr += '<a href="javascript:void(0)">' + i18n_common_splitPage_previous + '</a>';
	} else {
		splitStr += '<a href="javascript:splitPageLink(\''+divId+'\', \''+formId+'\', ' + (pageNumber - 1) + ');">' + i18n_common_splitPage_previous + '</a>';
	}
	
	for (var i = 1; i <= totalPages; i++) {
        if (i == 2 && pageNumber - 4 > 1) {
        	splitStr += '<a href="javascript:void(0)">...</a>';
            i = pageNumber - 4;
        } else if (i == pageNumber + 4 && pageNumber + 4 < totalPages) {
        	splitStr += '<a href="javascript:void(0)">...</a>';
            i = totalPages - 1;
        } else {
            if (pageNumber == i) {
            	splitStr += '<span class="current" style="padding:0;"><a href="javascript:void(0)">' + pageNumber + '</a></span>';
            } else {
            	splitStr += '<a href="javascript:splitPageLink(\''+divId+'\', \''+formId+'\', ' + i + ');">';
            	splitStr += i;
            	splitStr += '</a>';
            }
        }
    }
	
	if (pageNumber == totalPages || totalPages == 0) {
		splitStr += '<a href="javascript:void(0)">' + i18n_common_splitPage_next + '</a>';
	} else {
		splitStr += '<a href="javascript:splitPageLink(\''+divId+'\', \''+formId+'\', ' + (pageNumber + 1) + ');">' + i18n_common_splitPage_next + '</a>';
	}
		splitStr += '&nbsp;&nbsp;';
	if(isSelectPage == true){
		splitStr += '<a class="pagination-a"><select name="pageNumber" onChange="splitPageLink(\''+divId+'\', \''+formId+'\', this.value);" style="width: 100px; ">';
		for (var i = 1; i <= totalPages; i++) {
			if (i == pageNumber) {
				splitStr += '<option selected value="' + i + '">' + i18n_common_splitPage_jump + i + i18n_common_splitPage_jumpPage + '</option>';
			} else {
				splitStr += '<option value="' + i + '">' + i18n_common_splitPage_jump + i + i18n_common_splitPage_jumpPage + '</option>';
			}
		}
		if(totalPages == 0){
			splitStr += '<option value="0">' + i18n_common_splitPage_noJump + '</option>';
		}
		splitStr += '</select></a>';
		splitStr += '&nbsp;&nbsp;';
	}else{
		splitStr += '<input type="hidden" name="pageNumber">';
	}
	
	
//	if(isSelectSize == true){
//		splitStr += '<a class="pagination-a"><select name="pageSize" onChange="splitPageLink(\''+divId+'\', \''+formId+'\', 1);" style="width: 85px;">';
//		
//		var optionStr = '<option value="10">' + i18n_common_splitPage_perPage + '10' + i18n_common_splitPage_strip + '</option>';
//		optionStr += '<option value="20">' + i18n_common_splitPage_perPage + '20' + i18n_common_splitPage_strip + '</option>';
//		optionStr += '<option value="40">' + i18n_common_splitPage_perPage + '40' + i18n_common_splitPage_strip + '</option>';
//		optionStr += '<option value="80">' + i18n_common_splitPage_perPage + '80' + i18n_common_splitPage_strip + '</option>';
//		optionStr += '<option value="100">' + i18n_common_splitPage_perPage + '100' + i18n_common_splitPage_strip + '</option>';
//		optionStr += '<option value="200">' + i18n_common_splitPage_perPage + '200' + i18n_common_splitPage_strip + '</option>';
//		optionStr += '<option value="500">' + i18n_common_splitPage_perPage + '500' + i18n_common_splitPage_strip + '</option>';
//		optionStr += '<option value="1000">' + i18n_common_splitPage_perPage + '1000' + i18n_common_splitPage_strip + '</option>';
//		optionStr = optionStr.replace('"' + pageSize + '"', '"' + pageSize + '" selected="selected"');
//		
//		splitStr += optionStr;
//		
//		splitStr += '</select></a>';
//	}else{
//		splitStr += '<input type="hidden" name="pageSize">';
//	}
	splitStr += '&nbsp;';
	splitStr += '<a>共 <strong>' + totalRow + '</strong> ' + i18n_common_splitPage_records + '</a>';
	splitStr += '</div>';
	splitStr += '<input type="hidden" name="orderColunm" value="'+orderColunm+'"/>';
	splitStr += '<input type="hidden" name="orderMode" value="'+orderMode+'"/>';
	
	return splitStr;
}


/**
 * 分页链接HTML 角色分页
 * @param 同上...
 */			
function splitPageHtmlRole(divId, formId, totalRow, pageSize, pageNumber, totalPages, isSelectPage, isSelectSize, orderColunm, orderMode){
	var splitStr = ' <div class="pagination pagination-sm pagination-centered" style="padding-right: 333px;padding: 0;">';
	
	if (pageNumber == 1 || totalPages == 0) {
		splitStr += '<a class="prevRole" style="top: 60px;left: 29px;width: 110px;height: 20px;" href="javascript:void(0)"></a>';
	} else {
		splitStr += '<a class="prevRole" style="top: 60px;left: 29px;width: 110px;height: 20px;" href="javascript:splitPageLink(\''+divId+'\', \''+formId+'\', ' + (pageNumber - 1) + ');"></a>';
	}

	
	if (pageNumber == totalPages || totalPages == 0) {
		splitStr += '<a class="nextRole" href="javascript:void(0)" style="border-bottom-right-radius: 4px; border-top-right-radius: 4px;left: 30px;top: 725px;width: 110px;height: 20px;"></a>';
	} else {
		splitStr += '<a class="nextRole" href="javascript:splitPageLink(\''+divId+'\', \''+formId+'\', ' + (pageNumber + 1) + ');" style="border-bottom-right-radius: 4px; border-top-right-radius: 4px;left: 30px;top: 725px;width: 110px;height: 20px;"></a>';
	}
		splitStr += '&nbsp;&nbsp;';
	if(isSelectPage == true){
		splitStr += '<a class="pagination-a"><select name="pageNumber" onChange="splitPageLink(\''+divId+'\', \''+formId+'\', this.value);" style="width: 100px; ">';
		for (var i = 1; i <= totalPages; i++) {
			if (i == pageNumber) {
				splitStr += '<option selected value="' + i + '">' + i18n_common_splitPage_jump + i + i18n_common_splitPage_jumpPage + '</option>';
			} else {
				splitStr += '<option value="' + i + '">' + i18n_common_splitPage_jump + i + i18n_common_splitPage_jumpPage + '</option>';
			}
		}
		if(totalPages == 0){
			splitStr += '<option value="0">' + i18n_common_splitPage_noJump + '</option>';
		}
		splitStr += '</select></a>';
		splitStr += '&nbsp;&nbsp;';
	}else{
		splitStr += '<input type="hidden" name="pageNumber">';
	}

	splitStr += '</div>';
	splitStr += '<input type="hidden" name="orderColunm" value="'+orderColunm+'"/>';
	splitStr += '<input type="hidden" name="orderMode" value="'+orderMode+'"/>';
	
	return splitStr;
}

/**
 * 分页链接处理
 * @param divId
 * @param formId
 * @param toPage
 */
function splitPageLink(divId, formId, toPage){
	//alert($("#" + formId + " select[name=pageNumber]").attr("name"));//input[name=pageNumber]
	$("#" + formId + " select[name=pageNumber],input[name=pageNumber] ").val(toPage);
	ajaxForm(divId, formId);
}

/**
 * 分页列排序点击事件处理
 * @param divId
 * @param formId
 * @param colunmName
 */
function orderbyFun(divId, formId, colunmName){
	var orderColunmNode = $("#" + formId + " input[name=orderColunm]");
	var orderColunm = orderColunmNode.val();
	
	var orderModeNode = $("#" + formId + " input[name=orderMode]");
	var orderMode = orderModeNode.val();
	
	if(colunmName == orderColunm){
		if(orderMode == ""){
			orderModeNode.val("asc");
		}else if(orderMode == "asc"){
			orderModeNode.val("desc");
		}else if(orderMode == "desc"){
			orderModeNode.val("");
		}
	}else{
		orderColunmNode.val(colunmName);
		orderModeNode.val("asc");
	}
	//alert(orderColunmNode.val()+"--"+orderModeNode.val());
	ajaxForm(divId, formId);
}

/**
 * ajax提交form替换content
 * @param divId 返回替换div
 * @param formId 提交formid
 * @param callback 回调
 */
function ajaxForm(divId, formId){
	//$('.loading-img').css('display','block');
	$("#" + formId).ajaxSubmit({
		cache: false,
	    success:  function (data) {
	    	if(data != ""){
	    		$("#" + divId).html(data);
	    	}
			$('.loading-img').css('display','none');
	    }
	});
}

/**
 * ajax请求url替换指定div
 * @param shade 是否开启遮罩层
 * @param divId 返回替换div
 * @param url 请求地址
 * @param data 参数
 * @param callback 回调
 */
function ajaxDiv(shade, divId, url, data, callback){
	if(shade){
		 $('.loading-img').css('display','block');
	}	
	$.ajax({
		type : "post",
		url : encodeURI(encodeURI(cxt + url)),
		data : data,
		dataType : "html",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		cache: false,
		success:function(returnData){
			$("#" + divId).html(returnData);
			//扩展回调函数
			if( callback != null ){
				callback();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {

        },
        complete: function(XMLHttpRequest, textStatus) { 
        	if(shade){
        		 $('.loading-img').css('display','none');
        	}
        }
	});
}

/**
 * ajax请求url替换div content
 * @param url 请求地址
 * @param data 参数
 * @param callback 回调
 */
function ajaxContent(url, data, content){
	$('.loading-img').css('display','block');
	$.ajax({
		type : "post",
		url : encodeURI(encodeURI(url)),
		data : data,
		dataType : "html",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		cache: false,
		success:function(returnData){
			//$('.loading-img').css('display','none');
			$("#"+content).html(returnData);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
			// 这个方法有三个参数：XMLHttpRequest 对象，错误信息，（可能）捕获的错误对象。
			// 通常情况下textStatus和errorThown只有其中一个有值
            // alert(XMLHttpRequest.status);
            // alert(XMLHttpRequest.readyState);
            // alert(textStatus);

        },
        complete: function(XMLHttpRequest, textStatus) { 
        	// 请求完成后回调函数 (请求成功或失败时均调用)。参数： XMLHttpRequest 对象，成功信息字符串。
            // 调用本次AJAX请求时传递的options参数
        	//$('.loading-img').css('display','none');
        }
	});
}

/**
 * ajax请求url替换DiaLog
 * @param url 请求地址
 * @param data 参数
 * @param callback 回调
 */
function ajaxDiaLog(url, data, callback){
	$('.loading-img').css('display','block');
	$.ajax({
		type : "post",
		url : encodeURI(encodeURI(cxt + url)),
		data : data,
		dataType : "html",
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		async: false,
		cache: false,
		success:function(returnData){
			$('#myModal').html(returnData);
			//扩展回调函数
			if( callback != null ){
				callback();
			}
			$('#myModal').modal('show');
			 $('.loading-img').css('display','none');
		}
	});
}

/**
 * 加载日历控件
 * @param callback
 */
function loadCalendarScript(callback) {
	function loadFullCalendarScript(){
		if(!$.fn.fullCalendar) {
			$.getScript(cxt+'/assets/plugins/fullcalendar/dist/fullcalendar.min.js', callback);
		}else{
			if(callback && typeof(callback) == "function") {
				callback();
			}
		}
	}
	
	if(!$.fn.moment) {
		$.getScript(cxt+'/assets/plugins/moment/min/moment.min.js', loadFullCalendarScript);
	}else{
		loadFullCalendarScript();
	}
}

/**
 * 加载zTree
 * @param callback
 */
function loadZTreeScript(callback) {
	if(!$.fn.zTree) {
		$.getScript(cxt+'/assets/global/plugins/zTree/js/jquery.ztree.all-3.5.min.js', callback);
	}else{
		if(callback && typeof(callback) == "function") {
			callback();
		}
	}
}

$(function() {
	
});

