$(function(){
	//
	var alert_tip_html = '';
	alert_tip_html = "<div id=\"alert_tip\" style=\"width:70%;position:fixed;left:15%;top:40%;background:rgba(0,0,0,0.6);height:3rem;text-align:center;line-height:3rem;border-radius:0.3rem;color:#fff;font-size:1.2rem;display:none;\">添加成功</div>";
	//遮罩层
	var zhezhao_html = '';
	zhezhao_html = "<div id=\"masking_bg\" style=\"display:none;position: fixed;top: 0;right: 0;bottom: 0;left: 0;z-index: 1040;background-color:black;opacity: 0.1\"></div>";
	zhezhao_html += "<div id=\"zhezhao_mask\" class=\"zhezhao_mask\" style=\"position: absolute; top: 0px; filter: alpha(opacity=40); background-color: #777;z-index: 1002;left: 0px;opacity:0.5;-moz-opacity:0.5;\"></div>";

	$("body").append(alert_tip_html);
	$("body").append(zhezhao_html);
})
/*模拟alert*/
    function customer_alertTip(txt){
    	$('#alert_tip').html(txt);
     	$('#alert_tip').show();
    	setTimeout(function(){
    		$('#alert_tip').fadeOut();
    	},4000);
	}
    
  //兼容火狐、IE8   
    //显示遮罩层    
    function show_zhezhao_mask(){     
        $("#zhezhao_mask").css("height",$(document).height());     
        $("#zhezhao_mask").css("width",$(document).width());     
        $("#zhezhao_mask").show();
    }  
	
    //隐藏遮罩层  
    function hide_zhezhao_mask(){               
        $("#zhezhao_mask").hide();     
    } 