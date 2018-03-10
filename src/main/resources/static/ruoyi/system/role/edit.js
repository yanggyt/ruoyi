// 树结构初始化加载
var setting = {
	check:{enable:true,nocheckInherit:true,chkboxType:{"Y":"ps","N":"ps"}},
	view:{selectedMulti:false,nameIsHTML: true},
	data:{simpleData:{enable:true},key:{title:"title"}},
	callback:{
		beforeClick: function (treeId, treeNode, clickFlag) {
			var menuTrees = $.fn.zTree.getZTreeObj(treeId);
			menuTrees.checkNode(treeNode, !treeNode.checked, true, true);
			return false;
		}
	}
}, menuTrees, loadTree = function(){
	$.get("/system/menu/treeData?roleId=" + $("#roleId").val(), function(data) {
		menuTrees = $.fn.zTree.init($("#menuTrees"), setting, data); //.expandAll(true);
	}, null, null, "正在加载，请稍后...");
};loadTree();

$("#form-role-edit").validate({
	rules:{
		roleName:{
			required:true,
		},
		roleKey:{
			required:true,
		},
		roleSort:{
			required:true,
		},
	},
	submitHandler:function(form){
		update();
	}
});

function getIsChecked() {
    var menuIds = "";
    var treeNodes = menuTrees.getCheckedNodes(true);
    for (var i = 0; i < treeNodes.length; i++) {
        if (0 == i) {
        	menuIds = treeNodes[i].id;
        } else {
        	menuIds += ("," + treeNodes[i].id);
        }
    }
    return menuIds;
}

function update() {
	var roleId = $("input[name='roleId']").val();
	var roleName = $("input[name='roleName']").val();
	var roleKey = $("input[name='roleKey']").val();
	var roleSort = $("input[name='roleSort']").val();
	var status = $("input[name='status']").is(':checked') == true ? 0 : 1;
	var remark = $("input[name='remark']").val();
	var menuIds = getIsChecked();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/role/save",
		data : {
			"roleId": roleId,
			"roleName": roleName,
			"roleKey": roleKey,
			"roleSort": roleSort,
			"status": status,
			"remark": remark,
			"menuIds": menuIds
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
