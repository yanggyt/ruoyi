var prefix = "/system/user"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'userId',
            title: '用户ID'
        },
        {
            field: 'loginName',
            title: '登录名称'
        },
        {
            field: 'userName',
            title: '用户名称'
        },
        {
            field: 'email',
            title: '邮箱'
        },
        {
            field: 'phonenumber',
            title: '手机'
        },
        {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == '0') {
                    return '<span class="label label-success">正常</span>';
                } else if (value == '1') {
                    return '<span class="label label-danger">禁用</span>';
                }
            }
        },
        {
            field: 'createTime',
            title: '创建时间'
        },
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
            	var edit = '<a class="btn btn-primary btn-sm" href="#" title="编辑" mce_href="#" onclick="edit(\'' + row.userId + '\')"><i class="fa fa-edit"></i></a> ';
                var del = '<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.userId + '\')"><i class="fa fa-remove"></i></a> ';
                return edit + del;
            }
        }];
	var url = prefix + "/list";
    initTable(columns, url);
});

/*用户管理-删除*/
function remove(userId) {
	layer.confirm("确定要删除选中用户吗？",{icon: 3, title:'提示'},function(index){
		_ajax(prefix + "/remove/" + userId, "", "post");
    })
}

/*用户管理-修改*/
function edit(deptId) {
    var url = prefix + '/edit/' + deptId;
    layer_show("修改用户", url, '800', '500');
}

// 批量强退
function batchRemove() {
	var rows = getIdSelections("userId");
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的" + rows.length + "条数据吗?",{icon: 3, title:'提示'},function(index){
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post");
	});
}
