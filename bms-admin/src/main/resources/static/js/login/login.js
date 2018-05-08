layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    //video背景
    $(window).resize(function () {
        if ($(".video-player").width() > $(window).width()) {
            $(".video-player").css({
                "height": $(window).height(),
                "width": "auto",
                "left": -($(".video-player").width() - $(window).width()) / 2
            });
        } else {
            $(".video-player").css({
                "width": $(window).width(),
                "height": "auto",
                "left": -($(".video-player").width() - $(window).width()) / 2
            });
        }
    }).resize();
});

var vm = new Vue({
    el: "#bmsBoot",
    data:{
        username:'',
        password:'',
        captcha:'',
        src: 'captcha.jpg'
    },
    beforeCreate: function(){
        if(self != top){
            top.location.href = self.location.href;
        }
    },
    methods:{
        refreshCode: function(){
            this.src = "captcha.jpg?t=" + $.now();
        },
        login: function (event) {
            if(vm.username==""||vm.password==""||vm.captcha==""){
                return;
            }
            var data = "username="+vm.username+"&password="+vm.password+"&captcha="+vm.captcha;
            $.ajax({
                type: "POST",
                url: "sys/login",
                data: data,
                dataType: "json",
                success: function(result){
                    if(result.code == 0){//登录成功
                        window.parent.location.href ='index.html';
                    }else{
                        vm.error = true;
                        vm.errorMsg = result.msg;
                        vm.refreshCode();
                    }
                }
            });
        }
    }
});
