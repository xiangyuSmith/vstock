function sendRequest(requsetUrl, paramData, callback) {
    $.ajax({
        cache: false,
        type: "post",
        url: requsetUrl + "?timestamp=" + new Date().getTime(),
        data: paramData,
        success: function (back) {
            callback && callback(back);
        },
        error: function () {
            callback && callback({retCode: 0, retMsg: "远程服务器正忙"});
        }
    });
}

function fileUpload(url, fileElement, data, callback) {
    if (!data) {
        data = {};
    }
    $.ajaxFileUpload({
        url: url,
        fileElement: fileElement,
        data: data,
        dataType: 'json',
        success: function (back) {
            callback && callback(back);
        },
        error: function () {
            callback && callback({retCode: 0, retMsg: "远程服务器正忙"});
        }
    });
}

/**
 * ajax请求url替换div content
 * @param url 请求地址 （必填）
 * @param data 参数
 * @param content 异步加载容器DIV
 * @param type 弹出层状态 1:存在
 * @param callback 回调
 */
function ajaxContent(url, data, content,type){
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
            if(type == 1){
                loadingclose();
            }
            // 请求完成后回调函数 (请求成功或失败时均调用)。参数： XMLHttpRequest 对象，成功信息字符串。
            // 调用本次AJAX请求时传递的options参数
        }
    });
}

function alertshow(msg, callback) {
    //TODO 消息提示框
}

function confirmshow(title, msg, callback, initfunc) {
    //TODO 弹出层
}

//加载等待弹出层
function loadingshow(msg) {
    if (!msg) {
        msg = "正在载入...";
    }
    $("#loading-content").html(msg);
    $('#vstock-loading').modal();
}

function loadingclose() {
    $('#vstock-loading').modal('close');
}