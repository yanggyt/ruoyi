$(document).ready(function(){
	queryMenuTreeDaTa();
});

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

function update() {
	var roleId = $("input[name='roleId']").val();
	var roleName = $("input[name='roleName']").val();
	var roleKey = $("input[name='roleKey']").val();
	var roleSort = $("input[name='roleSort']").val();
	var status = $("input[name='status']").is(':checked') == true ? 0 : 1;
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/role/save",
		data : {
			"roleId": roleId,
			"roleName": roleName,
			"roleKey": roleKey,
			"roleSort": roleSort,
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
				window.parent.location.reload();
			} else {
				parent.layer.alert(data.m , {icon: 2,title:"系统提示"});
			}

		}
	});
}

function queryMenuTreeDaTa()
{
	// 树结构初始化加载
	var setting = {
		check:{enable:true,nocheckInherit:true,chkboxType:{"Y":"ps","N":"ps"}},
		view:{selectedMulti:false,nameIsHTML: true},
		data:{simpleData:{enable:true},key:{title:"title"}},
		callback:{
			beforeClick: function (treeId, treeNode, clickFlag) {
				var tree = $.fn.zTree.getZTreeObj(treeId);
				tree.checkNode(treeNode, !treeNode.checked, true, true);
				return false;
			},
			onCheck: function (event, treeId, treeNode){
				var tid = treeNode.tId;
				if(!treeNode.checked){
					$(".checkall[value="+treeId+"]").each(function(){
						if(this.checked){
						    $(this).removeAttr("checked").iCheck('update');
						}
						return false;
					}); 
				}
			}
		}
	}, tree, loadTree = function(){
		$.get("/system/menu/treeData/" + $("#roleId").val(), function(data) {
		    tree = $.fn.zTree.init($("#tree"), setting, data); //.expandAll(true);
		    // 展开第一级节点
		    var nodes = tree.getNodesByParam("level", 0);
		    for (var i = 0; i < nodes.length; i++) {
		        tree.expandNode(nodes[i], true, false, false);
		    }
		}, null, null, "正在加载，请稍后...");
	};loadTree();
}
