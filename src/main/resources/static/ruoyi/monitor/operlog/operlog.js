var prefix = "/monitor/operlog"

$(function() {
		var columns = [{
            checkbox: true
        },
        {
            field: 'operId',
            // 列字段名
            title: '日志编号' // 列标题
        },
        {
            field: 'title',
            title: '模块'
        },
        {
            field: 'action',
            title: '功能'
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
            field: 'opertIp',
            title: '主机'
        },
        {
            field: 'status',
            title: '操作状态',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 0) {
                    return '<span class="label label-success">成功</span>';
                } else if (value == 1) {
                    return '<span class="label label-primary">异常</span>';
                }
            }
        },
        {
            field: 'operTime',
            title: '操作时间'
        },
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
                var d = '<a class="btn btn-warning btn-sm" href="#" title="详细" onclick="view(\'' + row.operId + '\')"><i class="fa fa-search"></i></a> ';
                return d;
            }
        }];
	var url = prefix + "/list";
    initTable(columns, url);
});

function view(id) {
}

function batchRemove() {
	var rows = getIdSelections("operId");
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的" + rows.length + "条数据吗?",{icon: 3, title:'提示'},function(index){
		$.ajax({
		    type: 'POST',
		    data: { "ids": rows },
		    url: prefix + '/batchRemove',
		    success: function(r) {
		        if (r.code == 0) {
		        	layer.msg(r.msg, { icon: 1, time: 1000 });
		            refresh();
		        } else {
		        	layer.alert(r.msg, {icon: 2, title:"系统提示"});
		        }
		    }
		});
	});
}
