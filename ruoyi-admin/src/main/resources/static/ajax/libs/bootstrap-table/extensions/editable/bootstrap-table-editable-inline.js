/**
 * bootstrapTable行内编辑扩展
 * 可以自行扩展其他属性，如data-type="date"
 */

(function($) {

    'use strict';

    var BootstrapTable = $.fn.bootstrapTable.Constructor,
        _initBody = BootstrapTable.prototype.initBody;

    BootstrapTable.prototype.initBody = function() {
        var that = this;
        _initBody.apply(this, Array.prototype.slice.apply(arguments));
        var data = that.getData();
        var $tr = that.$body.find('tr');
        $.each($tr, function(rowindex, row) {//循环行
        	var $td = $(row).find('td');//当前行的所有列
        	$.each(that.columns, function(columnindex, column) {//循环列
        		const $time = $($td[columnindex]).find('.time');// 查找时间控件
        		if($time.length > 0){
        			$time.each((i, element) => {//循环time控件，获取element
        				//可以获取其他属性
        				layui.use('laydate', function () {
         		        	layui.laydate.render({
         						elem: element,
         						theme: 'molv',
         						trigger: 'click',
         						type: 'date',
         						done: function (value, d) {
         							//此处可以扩展callback
         							data[rowindex][column.field] = value;
         						}
         	 		        });
         		        });
        			});
        		}
        	});
        });
    };

})(jQuery);