/**
 * 通用方法封装处理
 * Copyright (c) 2018 ruoyi 
 */
/*
	参数解释：
	title	标题
	url		请求的url
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
function layer_show(title, url, w, h) {
    if (title == null || title == '') {
        title = false;
    };
    if (url == null || url == '') {
        url = "404.html";
    };
    if (w == null || w == '') {
        w = 800;
    };
    if (h == null || h == '') {
        h = ($(window).height() - 50);
    };
    layer.open({
        type: 2,
        area: [w + 'px', h + 'px'],
        fix: false,
        //不固定
        maxmin: true,
        shade: 0.4,
        title: title,
        content: url
    });
}

/*关闭弹出框口*/
function layer_close() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

//状态码
web_status = {
    SUCCESS: 0,
    FAIL: 500
};

//对jquery的ajax方法再次封装
_ajax = function(url, data, type) {
    var config = {
        url: url,
        type: type,
        dataType: "json",
        data: data,
        success: function(result) {
            simpleSuccess(result);
        }
    };
    $.ajax(config)
};

/** 返回结果处理 */
function simpleSuccess(result) {
    if (result.code == web_status.SUCCESS) {
		layer.msg(result.msg, { icon: 1, time: 1000 });
		refresh();

    } else {
        layer.alert(result.msg, { icon: 2, title: "系统提示" });
    }
}