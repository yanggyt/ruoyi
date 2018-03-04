$("#form-dept-edit").validate({
	rules:{
		deptName:{
			required:true,
		},
		orderNum:{
			required:true,
		},
	},
	submitHandler:function(form){
		update();
	}
});

function update() {
	var deptId = $("input[name='deptId']").val();
	var parentId = $("input[name='parentId']").val();
	var orderNum = $("input[name='orderNum']").val();
	var deptName = $("input[name='deptName']").val();
	var status = $("input[name='status']").is(':checked') == true ? 0 : 1;
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/dept/save",
		data : {
			"deptId": deptId,
			"parentId": parentId,
			"deptName": deptName,
			"orderNum": orderNum,
			"status": status
		},
		async : false,
		error : function(request) {
			parent.layer.alert("系统错误");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg('修改成功',{icon:1,time:1000});
				layer_close();
				parent.loading();
			} else {
				parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
			}

		}
	});
}