/**
 * tipView 0.1
 * Created by xiangyu on 2016/8/10.
 */
(function($){
    $.fn.tipView = function(options){
        var init = {
            a : "a",
            b : "b",
            c : "c"
        };
        var options = $.extend(init, options);
        alert(options.a);
    }
})(jQuery);