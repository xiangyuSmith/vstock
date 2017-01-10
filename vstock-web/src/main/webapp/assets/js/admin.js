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
            $("#"+content).fadeIn(500);
            if(type == 1){
                loadingclose();
            }
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
        }
    });
}

function ajaxContentAppend(url, data, content,type){
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
            $("#"+content).append(returnData);
        }
    });
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
function loadingSaleListclose() {
    $('#my-popup-saleList').modal('close');
}
function loadingPurchaseclose() {
    $('#my-popup-purchaselistwindow').modal('close');
}
function loadingPurchaseListclose() {
    $('#my-popup-purchaselistwindow').modal('close');
}
function loadingBidclose() {
    $('#my-popup-bid').modal('close');
}

function loadingassetsclose() {
    $('#my-popup-assets').modal('close');
}

//提示弹框
function alertshow(msg, callback) {
    $("#alert-content").html(msg);
    $('#forex-alert').modal({
        onConfirm : function() { if (callback) callback(); $('#forex-alert').off('confirm.modal.amui'); }
    });
}
//tips窗口
function alertTips(type,title,msg){
    if(title == ""){
        title = "提示";
    }
    var msgType = "";
    switch (type){
        case 0:
            msgType = "info";
            break;
        case 1:
            msgType = "success";
            break;
        case 2:
            msgType = "danger";
            break;
        case 3:
            msgType = "warning";
            break;
        default:
            msgType = "info";
            break;
    }
    $.toaster({ priority : msgType, title : title, message : msg});
}
function alertConfirm(title , msg , callback) {
    $("#alert-confirm-title").html(title);
    $("#alert-confirm-content").html(msg);
    $('#my-confirm').modal({
        onConfirm : function() { if (callback) callback(); $('#my-confirm').off('confirm.modal.amui'); },
    });
}

//JS将number数值转化成为货币格式
Number.prototype.formatMoney = function (places, symbol, thousand, decimal) {
    places = !isNaN(places = Math.abs(places)) ? places : 2;
    symbol = symbol !== undefined ? symbol : "$";
    thousand = thousand || ",";
    decimal = decimal || ".";
    var number = this,
        negative = number < 0 ? "-" : "",
        i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
        j = (j = i.length) > 3 ? j % 3 : 0;
    return symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
};

/**
 * 格式化金额
 * @param s 金额
 * @param n 位数
 * @returns {string}
 */
function fmoney(s, n)
{
    n = n > 0 && n <= 20 ? n : 0;
    if(n > 0){
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    }else{
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")) + "";
    }
    var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
    t = "";
    for(i = 0; i < l.length; i ++ )
    {
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    if(n > 0){
        return t.split("").reverse().join("") + "." + r;
    }else{
        return t.split("").reverse().join("");
    }
}
