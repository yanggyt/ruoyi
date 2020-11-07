$(function(){
	// var flag = IsPC();
	// console.log(flag);
	windoww = $(window).width();
	$(".img_load").each(function(){
		var _this = $(this);
		var _thisPc = _this.data("pc");
		var _thisIph = _this.data("iph");
		if( windoww < 768 ){
			_this.attr({
				src: _thisIph
			});
		}else{
			_this.attr({
				src: _thisPc
			});
		}
	})
})



// PC端/移动端
//true为PC端，false为手机端跟ipad端
function IsPC(){
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}