var prefix = "/system/role"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'roleId',
            title: '角色编号'
        },
        {
            field: 'roleName',
            title: '角色名称'
        },
        {
            field: 'roleKey',
            title: '权限字符'
        },
        {
            field: 'roleSort',
            title: '显示顺序'
        },
        {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 0) {
                    return '<span class="label label-success">正常</span>';
                } else if (value == 1) {
                    return '<span class="label label-primary">禁用</span>';
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
                var msg = '<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\'' + row.roleId + '\')"><i class="fa fa-remove"></i></a> ';
                return msg;
            }
        }];
	var url = prefix + "/list";
    initTable(columns, url);
});

// 单条删除
function remove(id) {
	layer.confirm("确定要删除选中角色吗？",{icon: 3, title:'提示'},function(index){
		_ajax(prefix + "/remove/" + id, "", "post");
    })
}

// 批量删除
function batchRemove() {
	var rows = getIdSelections("roleId");
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的" + rows.length + "条数据吗?",{icon: 3, title:'提示'},function(index){
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post");
	});
}
