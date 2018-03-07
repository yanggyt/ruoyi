$("#form-user-add").validate({
	rules:{
		loginName:{
			required:true,
		},
		userName:{
			required:true,
		},
		password:{
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
		add();
	}
});

function getIsChecked(_name) {
	var adIds = "";
	$('input:checkbox[name="'+_name+'"]:checked').each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}

function add() {
	var userId = $("input[name='userId']").val();
	var deptId = $("input[name='deptId']").val();
	var loginName = $("input[name='loginName']").val();
	var userName = $("input[name='userName']").val();
	var password = $("input[name='password']").val();
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
			"deptId": deptId,
			"loginName": loginName,
			"userName": userName,
			"password": password,
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

/*用户管理-修改-选择部门树*/
function selectDeptTree() {
    var url = "/system/user/selectDeptTree";
    layer_show("选择部门", url, '400', '410');
}
