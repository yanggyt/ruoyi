/**
 * 自定义分页处理
 * 
 * @author ruoyi
 */

// 初始化表格
function initTable(_columns, _url) {
    $('.bootstrap-table').bootstrapTable({
        method: 'get',                // 请求方式（*）
        dataType: "json",             // 返回格式（*）
        url: _url,                    // 请求后台的URL（*）
        pagination: true,             // 是否显示分页（*）
		pageSize: 10,                 // 每页的记录行数（*）
        pageNumber: 1,                // 初始化加载第一页，默认第一页
		pageList: [10, 25, 50],       // 可供选择的每页的行数（*）
		search: true,                 // 是否显示搜索框功能
		singleSelect: false,          // 是否禁止多选
        iconSize: 'outline',          // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
        toolbar: '#tableToolbar',     // 指定工作栏
        sidePagination: "server",     // 启用服务端分页 
		showRefresh: true,            // 是否显示刷新按钮
		showColumns: true,            // 是否显示隐藏某列下拉框
		showToggle: true,             // 是否显示详细视图和列表视图的切换按钮
        cache: false,                 // 是否使用缓存
        queryParams: function(params) {
            return {
                // 传递参数查询参数
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
        return row[_id];
    });
}