// 自定义分页处理 ruoyi

// 初始化表格
function initTable(_columns, _url) {
    $('.bootstrap-table').bootstrapTable({
        method: 'GET',
        dataType: "json",
        contentType: 'application/x-www-form-urlencoded',
        url: _url,
        // search: true,              // 是否显示搜索框功能
        striped: true,                // 是否显示行间隔色
        pagination: true,             // 是否分页
        showColumns: false,           // 是否显示隐藏某列下拉框
		singleSelect: false,          // 是否单选复选框
        iconSize: 'outline',          // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
        toolbar: '#tableToolbar',     // 指定工作栏
        pageSize: 10,                 // 每页显示10条记录
        pageNumber: 1,                // 默认第1页
		pageList: [10, 25, 50],       // 可供选择的每页的行数
        sidePagination: "server",     // 启用服务端分页
        cache: false,                 // 是否使用缓存
        queryParams: function(params) {
            return {
                // 查询参数
                limit:  params.limit,
                offset: params.offset,
                search: params.search,
                sort:   params.sort,
                order:  params.order
            };
        },
        columns: _columns
    });
}

// 刷新
function refresh() {
    $('.bootstrap-table').bootstrapTable('refresh');
}

// 获取选中数组
function getIdSelections(_id) {
    return $.map($('.bootstrap-table').bootstrapTable('getSelections'), function (row) {
        return row[_id]
    });
}