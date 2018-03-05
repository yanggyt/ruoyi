$("#form-user-edit").validate({
	rules:{
		userName:{
			required:true,
		},
		email:{
			required:true,
		},
		phonenumber:{
			required:true,
		},
	},
	submitHandler:function(form){
		update();
	}
});

function update() {
	var userId = $("input[name='userId']").val();
	var userName = $("input[name='userName']").val();
	var email = $("input[name='email']").val();
	var phonenumber = $("input[name='phonenumber']").val();
	var status = $("input[name='status']").is(':checked') == true ? 0 : 1;
	var roleIds = getIsChecked("role");  
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/user/save",
		data : {
			"userId": userId,
			"userName": userName,
			"email": email,
			"phonenumber": phonenumber,
			"status": status,
			"roleIds": roleIds
		},
		async : false,
		error : function(request) {
			parent.layer.alert("系统错误");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg('修改成功',{icon:1,time:1000});
				layer_close();
				window.parent.location.reload();
			} else {
				parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
			}

		}
	});
}