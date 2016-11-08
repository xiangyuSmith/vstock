var User = function(){
    return {
        init : function(){
            $("#form_login").validate({
                rules: {
                    'username': {
                        required:true
                    },
                    'password': {
                        required:true
                    }
                }
            });
        }
    };
}();
