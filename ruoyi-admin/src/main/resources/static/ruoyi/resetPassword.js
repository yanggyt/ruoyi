
$(function() {
	reset();
	$('.imgcode').click(function() {
		var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
		$(".imgcode").attr("src", url);
	});
});

$.validator.setDefaults({
    submitHandler: function() {
    	resetPassword();
    }
});



function resetPassword() {
	$.modal.loading($("#resetPassword").data("loading"));
	var userCode = $.common.trim($("input[name='userCode']").val());
    var email = $.common.trim($("input[name='email']").val());
    $.ajax({
        type: "post",
        url: "/system/user/sendForgetPassword",
        data: {
            "email": email,
            "userCode": userCode,
        },
        success: function(res) {
        	console.log(res);
            if (res.code == 0) {
            	$.modal.closeLoading();
            	alert(res.msg);
            } else {
            	$.modal.closeLoading();
            	alert(res.msg);
            }
        }
    });
}

function reset() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#reset").validate({
        rules: {
        	userCode: {
                required: true
            },
            email: {
                required: true
            }
        },
        messages: {
        	userCode: {
                required: icon + "请输入您的用户名",
            },
            email: {
                required: icon + "请输入您的密码",
            }
        }
    })
}
