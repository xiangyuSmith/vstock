/**
 * Created by GY on 2014/12/31.
 */

/**
 * 滚动条工具类
 * 结合自定义滚动条一起使用
 */

ScrollTools = (function(){
    function parivateAppend(element,html){
        if(element.find(".mCSB_container").html()!=undefined){
            element.find(".mCSB_container").append(html);
            element.mCustomScrollbar("update");
        }else{
            element.append(html);
            element.mCustomScrollbar("update");
        }
    }
    function privateReplace(element,html){
        if(element.find(".mCSB_container").html()!=undefined){
            element.find(".mCSB_container").html(html);
            element.mCustomScrollbar("update");
        }else{
            element.html(html);
            element.mCustomScrollbar("update");
        }
    }
    return {
        append: function(element,html){
            parivateAppend(element,html);
        },
        replace: function(element,html){
            privateReplace(element,html);
        }
    }
})();