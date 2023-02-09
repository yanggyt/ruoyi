
$(function() {
    validateKickout();
    validateRule();
    $('.imgcode').click(function() {
        var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
        $(".imgcode").attr("src", url);
    });

    /*$(document).ready(function(){
        //刚开始点击第一个
        $('.load-page').trigger("click");
        generateCode()
    });*/

    $(".login-tab li").click(function() {
        var index = $(this).index();
        var text = $(this).text();
        $(this).addClass("current").siblings().removeClass("current");
        $(".mc").eq(index).show().siblings('.mc').hide();
        // debugger
        if(text === "扫码登录"){
            getDo();
        }
    });
});

$.validator.setDefaults({
    submitHandler: function() {
        login();
    }
});

function login() {
    $.modal.loading($("#btnSubmit").data("loading"));
    var username = $.common.trim($("input[name='username']").val());
    var password = $.common.trim($("input[name='password']").val());
    var validateCode = $("input[name='validateCode']").val();
    var rememberMe = $("input[name='rememberme']").is(':checked');
    $.ajax({
        type: "post",
        url: ctx + "login",
        data: {
            "username": username,
            "password": password,
            "validateCode": validateCode,
            "rememberMe": rememberMe
        },
        success: function(r) {
            if (r.code == web_status.SUCCESS) {
                setCookie("name",r.msg);
                location.href = ctx + 'index';
            } else {
            	$('.imgcode').click();
            	$(".code").val("");
            	$.modal.msg(r.msg);
            }
            $.modal.closeLoading();
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}

function validateKickout() {
    if (getParam("kickout") == 1) {
        layer.alert("<font color='red'>您已在别处登录，请您修改密码或重新登录</font>", {
            icon: 0,
            title: "系统提示"
        },
        function(index) {
            //关闭弹窗
            layer.close(index);
            if (top != self) {
                top.location = self.location;
            } else {
                var url = location.search;
                if (url) {
                    var oldUrl = window.location.href;
                    var newUrl = oldUrl.substring(0, oldUrl.indexOf('?'));
                    self.location = newUrl;
                }
            }
        });
    }
}

function getParam(paramName) {
    var reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}
function utf16to8(str) {
    var out, i, len, c;
    out = "";
    len = str.length;
    for(i = 0; i < len; i++) {
        c = str.charCodeAt(i);
        if ((c >= 0x0001) && (c <= 0x007F)) {
            out += str.charAt(i);
        } else if (c > 0x07FF) {
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
            out += String.fromCharCode(0x80 | ((c >> 6) & 0x3F));
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
        } else {
            out += String.fromCharCode(0xC0 | ((c >> 6) & 0x1F));
            out += String.fromCharCode(0x80 | ((c >> 0) & 0x3F));
        }
    }
    return out;
}

function generateCode(){
    var ts = Math.round(new Date().getTime()/1000).toString();
    $.ajax({
        url: 'http://192.168.5.16:8060/api/get/jsessionid',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            $('#qrcode').qrcode({
                render:"canvas",//设置渲染方式 （有两种方式 table和canvas，默认是canvas）
                width: 155,//宽度
                height: 155,//高度
                correctLevel:0,//纠错等级
                text: self.utf16to8(data.msg + "-WO-" + ts),
            });
        }
    })
}

function getApi() {
    $.ajax({
        url: 'http://192.168.5.16:8060/api/auto/login',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            console.log(data);
            if (data.code == 0) {
                location.href = ctx + 'index';
            }
        }
    })
}

function getDo() {
    var count=0;
    var inervalId = setInterval(function() {
        count++;
        if(count>=20) clearInterval(inervalId);
        getApi();
    }, 3000);
    setTimeout(function(){
        $(".switch-word").text("此二维码已失效");
        $(".switch-word").css({color:"red"})
    }, 60000)
}