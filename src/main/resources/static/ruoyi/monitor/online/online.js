var prefix = "/monitor/online"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'sessionId',
            title: '会话编号'
        },
        {
            field: 'loginName',
            title: '登录名称'
        },
        {
            field: 'deptName',
            title: '部门名称'
        },
        {
            field: 'ipaddr',
            title: '主机'
        },
        {
            field: 'browser',
            title: '浏览器'
        },
        {
            field: 'os',
            title: '操作系统'
        },
        {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 'on_line') {
                    return '<span class="label label-success">在线</span>';
                } else if (value == 'off_line') {
                    return '<span class="label label-primary">离线</span>';
                }
            }
        },
        {
            field: 'startTimestamp',
            title: '登录时间'
        },
        {
            field: 'lastAccessTime',
            title: '最后访问时间'
        },
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
                var msg = '<a class="btn btn-warning btn-sm" href="#" title="强退" onclick="forceLogout(\'' + row.sessionId + '\')"><i class="fa fa-remove"></i></a> ';
                return msg;
            }
        }];
	var url = prefix + "/list";
    initTable(columns, url);
});

// 单条强退
function forceLogout(id) {
	layer.confirm("确定要强制选中用户下线吗？",{icon: 3, title:'提示'},function(index){
		_ajax(prefix + "/forceLogout/" + id, { 'id': id }, "post");
    })
}

// 批量强退
function batchForceLogout() {
	var rows = getIdSelections("sessionId");
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的" + rows.length + "条数据吗?",{icon: 3, title:'提示'},function(index){
		_ajax(prefix + '/batchForceLogout', { "ids": rows }, "post");
	});
}
