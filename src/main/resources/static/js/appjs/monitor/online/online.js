var prefix = "/monitor/online"

$(function() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'sessionId',
            // 列字段名
            title: '会话编号' // 列标题
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
            field: 'id',
            align: 'center',
            formatter: function(value, row, index) {
                var d = '<a class="btn btn-warning btn-sm" href="#" title="删除" onclick="forceLogout(\'' + row.sessionId + '\')"><i class="fa fa-remove"></i></a> ';
                return d;
            }
        }];
	var url = prefix + "/list";
    initTable(columns, url);
});

function forceLogout(id) {
    layer.confirm('确定要强制选中用户下线吗？', {
        btn: ['确定', '取消']
    },
    function() {
        $.ajax({
            url: prefix + "/forceLogout/" + id,
            type: "post",
            data: {
                'id': id
            },
            success: function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    refresh();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function addTest()
{
	alert("addTest");
}

function batDelTest()
{
	alert("batDelTest");
}