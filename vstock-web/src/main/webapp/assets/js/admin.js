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

function alertshow(msg, callback) {
    //TODO 消息提示框
}

function confirmshow(title, msg, callback, initfunc) {
    //TODO 弹出层
}