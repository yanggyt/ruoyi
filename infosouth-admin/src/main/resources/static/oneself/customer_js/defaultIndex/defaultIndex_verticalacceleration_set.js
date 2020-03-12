/**
 * pitch分析设置
 * 
 */

var verticalacceleration_locat = (window.location+'').split('/'); 
var verticalacceleration_locate_url = '';
$(function(){
	verticalacceleration_locat =  verticalacceleration_locat[0]+'//'+verticalacceleration_locat[2]+'/'+verticalacceleration_locat[3];
	verticalacceleration_locate_url = verticalacceleration_locat + '/a';
});

var verticalacceleration_static_acType_C900 = global_acTypeArr[0];
var verticalacceleration_static_acType_B737 = global_acTypeArr[1];
var verticalacceleration_static_acType_A320 = global_acTypeArr[2];
var verticalacceleration_static_acType_B777 = global_acTypeArr[3];
var verticalacceleration_static_acType_A330 = global_acTypeArr[4];

//获取数据库中保存的数据
var verticalacceleration_getDataFromDB = null;
//统计类型临时变量
var verticalacceleration_statisticsType_Temp = '';

//查询最大垂直加速度统计结果,没有查询条件，用于默认加载首页
function getParamsStatisticsResultToLine(){
	$.ajax({
		type: "POST",
		url: verticalacceleration_locate_url+'/defaultIndex/infoDefaultindexVerticalaccelerationset/getParamsStatisticsResult',
    	data: {},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status=='1001'||data.status==1001){
				//verticalacceleration_getDataFromDB
				verticalacceleration_getDataFromDB = data;
				//
				var statisticsTitle = data.statisticsTitle;
				var statisticsType = data.statisticsType;
				var calcuteResultArr = data.calcuteResultArr;
				//绘图
				var $chart_container_width_line=$(".chart_container_line").width();
				var $chart_container_height_line=$(".chart_container_line").height(); 
				
				chart_container_width_line = $chart_container_width_line;
				chart_container_height_line = $chart_container_height_line;
				line_chart(chart_container_width_line, chart_container_height_line,calcuteResultArr,statisticsType);
				
				
				//更新参数名标题
				
				//更新平均值还是中位数
				$(".verticalAcceleration_choose_statisticsType").empty();
				if(statisticsType=='1')
					$(".verticalAcceleration_choose_statisticsType").append('平均值');
				if(statisticsType=='2')
					$(".verticalAcceleration_choose_statisticsType").append('中位数');					
				if(statisticsType=='3')
					$(".verticalAcceleration_choose_statisticsType").append('最大值');
				if(statisticsType=='4')
					$(".verticalAcceleration_choose_statisticsType").append('最小值');
				//更新标题-飞行参数
				$(".verticalAcceleration_title").empty();
				$(".verticalAcceleration_title").append(statisticsTitle);
				
				//将统计类型传给临时变量
				verticalacceleration_statisticsType_Temp = statisticsType;
				//打开模态框，初始化数据库中保存的所有选过的下拉框
				verticalAcceleration_set_choosed_select_Init(data);
			}else{
				oDateAlert2(data.data);
				return;
			}
			
		}
		
	});
}

//初始化所有选过的下拉框
function verticalAcceleration_set_choosed_select_Init(data){
	if(data!=null){
		//初始化统计类型
		//$("#verticalAcceleration_statisticsType").val(verticalacceleration_statisticsType_Temp).trigger("change");
		$("#verticalAcceleration_statisticsType").val(data.statisticsType).trigger("change");
		
		var infoDutyScheduleList_Json = data.infoDutyScheduleList_Json;
		var infoDutyScheduleList_CRJ900JsonArr = infoDutyScheduleList_Json.infoDutyScheduleList_CRJ900JsonArr;
		var infoDutyScheduleList_B737JsonArr = infoDutyScheduleList_Json.infoDutyScheduleList_B737JsonArr;
		var infoDutyScheduleList_A320JsonArr = infoDutyScheduleList_Json.infoDutyScheduleList_A320JsonArr;
		var infoDutyScheduleList_B777JsonArr = infoDutyScheduleList_Json.infoDutyScheduleList_B777JsonArr;
		var infoDutyScheduleList_A330JsonArr = infoDutyScheduleList_Json.infoDutyScheduleList_A330JsonArr;
		
		var choosedJobIdByAcType = data.choosedJobIdByAcType;
		var choosedModelNameByAcType = data.choosedModelNameByAcType;
		var choosedParamNameByAcType = data.choosedParamNameByAcType;
		//
		$.each(infoDutyScheduleList_CRJ900JsonArr,function(index,infoDutyScheduleList_CRJ900JsonArrEach){					
			if(infoDutyScheduleList_CRJ900JsonArrEach.id==choosedJobIdByAcType.jobidCrj900){
				$("#verticalAcceleration_jobId_CRJ900").val(choosedJobIdByAcType.jobidCrj900).trigger("change");
			}
		});	
		//
		$.each(infoDutyScheduleList_B737JsonArr,function(index,infoDutyScheduleList_B737JsonArrEach){					
			if(infoDutyScheduleList_B737JsonArrEach.id==choosedJobIdByAcType.jobidB737){
				$("#verticalAcceleration_jobId_B737").val(choosedJobIdByAcType.jobidB737).trigger("change");
			}
		});	
		//
		$.each(infoDutyScheduleList_A320JsonArr,function(index,infoDutyScheduleList_A320JsonArrEach){					
			if(infoDutyScheduleList_A320JsonArrEach.id==choosedJobIdByAcType.jobidA320){
				$("#verticalAcceleration_jobId_A320").val(choosedJobIdByAcType.jobidA320).trigger("change");
			}
		});	
		//
		$.each(infoDutyScheduleList_B777JsonArr,function(index,infoDutyScheduleList_B777JsonArrEach){					
			if(infoDutyScheduleList_B777JsonArrEach.id==choosedJobIdByAcType.jobidB777){
				$("#verticalAcceleration_jobId_B777").val(choosedJobIdByAcType.jobidB777).trigger("change");
			}
		});	
		//
		$.each(infoDutyScheduleList_A330JsonArr,function(index,infoDutyScheduleList_A330JsonArrEach){					
			if(infoDutyScheduleList_A330JsonArrEach.id==choosedJobIdByAcType.jobidA330){
				$("#verticalAcceleration_jobId_A330").val(choosedJobIdByAcType.jobidA330).trigger("change");
			}
		});	
		
		//查询所有模型下拉选项		
		//verticalAcceleration_getModelsListByJobId_sync(choosedJobIdByAcType.jobidCrj900);
		//查询所有模型下拉选项		
		//verticalAcceleration_getModelsListByJobId_sync(choosedJobIdByAcType.jobidB737);
		//查询所有模型下拉选项		
		//verticalAcceleration_getModelsListByJobId_sync(choosedJobIdByAcType.jobidA320);
		//查询所有模型下拉选项		
		//verticalAcceleration_getModelsListByJobId_sync(choosedJobIdByAcType.jobidB777);
		//查询所有模型下拉选项		
		//verticalAcceleration_getModelsListByJobId_sync(choosedJobIdByAcType.jobidA330);
		
		//如果任务id有默认选中， 将选中过的模型设为默认----------------------------------------------------------------------------
		jobId_CRJ900_chooseOption = '';
		jobId_CRJ900_chooseOption = $("#verticalAcceleration_jobId_CRJ900").val();
		jobId_B737_chooseOption = '';
		jobId_B737_chooseOption = $("#verticalAcceleration_jobId_B737").val();
		jobId_A320_chooseOption = '';
		jobId_A320_chooseOption = $("#verticalAcceleration_jobId_A320").val();
		jobId_B777_chooseOption = '';
		jobId_B777_chooseOption = $("#verticalAcceleration_jobId_B777").val();
		jobId_A330_chooseOption = '';
		jobId_A330_chooseOption = $("#verticalAcceleration_jobId_A330").val();
		
		//选中的模型名					
		if(jobId_CRJ900_chooseOption!=''&&jobId_CRJ900_chooseOption!=undefined){
			$("#verticalAcceleration_modelName_C900").val(choosedModelNameByAcType.modelnameCrj900).trigger("change");
		}
		if(jobId_B737_chooseOption!=''&&jobId_B737_chooseOption!=undefined){
			$("#verticalAcceleration_modelName_B737").val(choosedModelNameByAcType.modelnameB737).trigger("change");
		}
		if(jobId_A320_chooseOption!=''&&jobId_A320_chooseOption!=undefined){
			$("#verticalAcceleration_modelName_A320").val(choosedModelNameByAcType.modelnameA320).trigger("change");
		}
		if(jobId_B777_chooseOption!=''&&jobId_B777_chooseOption!=undefined){
			$("#verticalAcceleration_modelName_B777").val(choosedModelNameByAcType.modelnameB777).trigger("change");
		}
		if(jobId_A330_chooseOption!=''&&jobId_A330_chooseOption!=undefined){
			$("#verticalAcceleration_modelName_A330").val(choosedModelNameByAcType.modelnameA330).trigger("change");
		}
		//如果任务id有默认选中， 将选中过的模型设为默认---------------------------------------------------------------------------
		
		
		//如果模型有默认选中，将选中过的参数设为默认------------------------------------------------------------------------------------
		//verticalAcceleration_getParamsListByModelName_sync(choosedJobIdByAcType.jobidCrj900,choosedModelNameByAcType.modelnameCrj900);

		//verticalAcceleration_getParamsListByModelName_sync(choosedJobIdByAcType.jobidB737,choosedModelNameByAcType.modelnameB737);
		//查询所有参数下拉选项
		//verticalAcceleration_getParamsListByModelName_sync(choosedJobIdByAcType.jobidA320,choosedModelNameByAcType.modelnameA320);
		//查询所有参数下拉选项
		//verticalAcceleration_getParamsListByModelName_sync(choosedJobIdByAcType.jobidB777,choosedModelNameByAcType.modelnameB777);
		//查询所有参数下拉选项
		//verticalAcceleration_getParamsListByModelName_sync(choosedJobIdByAcType.jobidA330,choosedModelNameByAcType.modelnameA330);
		//
		modelName_C900_chooseOption = '';
		modelName_C900_chooseOption = $("#verticalAcceleration_modelName_C900").val();
		if(modelName_C900_chooseOption!=''&&modelName_C900_chooseOption!=undefined){
			$("#verticalAcceleration_paramName_C900").val(choosedParamNameByAcType.paramnameCrj900).trigger("change");
		}
		//
		modelName_B737_chooseOption = '';
		modelName_B737_chooseOption = $("#verticalAcceleration_modelName_B737").val();
		if(modelName_B737_chooseOption!=''&&modelName_B737_chooseOption!=undefined){
			$("#verticalAcceleration_paramName_B737").val(choosedParamNameByAcType.paramnameB737).trigger("change");
		}
		//
		modelName_A320_chooseOption = '';
		modelName_A320_chooseOption = $("#verticalAcceleration_modelName_A320").val();
		if(modelName_A320_chooseOption!=''&&modelName_A320_chooseOption!=undefined){
			$("#verticalAcceleration_paramName_A320").val(choosedParamNameByAcType.paramnameA320).trigger("change");
		}
		//
		modelName_B777_chooseOption = '';
		modelName_B777_chooseOption = $("#verticalAcceleration_modelName_B777").val();
		if(modelName_B777_chooseOption!=''&&modelName_B777_chooseOption!=undefined){
			$("#verticalAcceleration_paramName_B777").val(choosedParamNameByAcType.paramnameB777).trigger("change");
		}
		//
		modelName_A330_chooseOption = '';
		modelName_A330_chooseOption = $("#verticalAcceleration_modelName_A330").val();
		if(modelName_A330_chooseOption!=''&&modelName_A330_chooseOption!=undefined){
			$("#verticalAcceleration_paramName_A330").val(choosedParamNameByAcType.paramnameA330).trigger("change");
		}
		//如果模型有默认选中，将选中过的参数设为默认------------------------------------------------------------------------------------
	}else{
		oDateAlert2('缓存失效，请刷新页面');
	}
	
}


//打开最大垂直加速度模态框，显示上次设置过的属性
function verticalaccelerationset_showModelDlg(){
	$.ajax({
		type: "POST",
		url: verticalacceleration_locate_url+'/defaultIndex/infoDefaultindexVerticalaccelerationset/getParamsStatisticsResult',
    	data: {},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status=='1001'||data.status==1001){
				var statisticsTitle = data.statisticsTitle;
				var statisticsType = data.statisticsType;
				var calcuteResultArr = data.calcuteResultArr;
				
				//更新标题-飞行参数
				$("#verticalAcceleration_statisticsAction").empty();
				$("#verticalAcceleration_statisticsAction").val(statisticsTitle);
				
			}else{
				oDateAlert2(data.data);
				return;
			}
			
		}
		
	});
	//打开弹出框，初始化下拉框，从数据库查询数据
	$.ajax({
		type: "POST",
		url: verticalacceleration_locate_url+'/defaultIndex/infoDefaultindexVerticalaccelerationset/getParamsStatisticsResult',
    	data: {},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status=='1001'||data.status==1001){
				//verticalacceleration_getDataFromDB
				verticalacceleration_getDataFromDB = data;
				verticalAcceleration_set_choosed_select_Init(verticalacceleration_getDataFromDB);
			}
		}
	});
	
}



//最大垂直加速度，通过jobId获取模型列表
function verticalAcceleration_getModelsListByJobId(jobId,acType){	
	$.ajax({
		type: "POST",
		url: verticalacceleration_locate_url+'/defaultIndex/infoDefaultindexVerticalaccelerationset/getModelsListByJobId',
    	data: {jobId:jobId},
		dataType:'json',
		cache: false,
		success: function(data){
			hide_zhezhao_mask();//关闭遮罩层
			if(data.status==1001||data.status=='1001'){
				//置空参数下拉框
				$("#verticalAcceleration_paramName_"+acType).empty();      //清空	
				$("#verticalAcceleration_paramName_"+acType).select2("val", ""); 
				
				$("#verticalAcceleration_modelName_"+acType).empty();      //清空	
				$("#verticalAcceleration_modelName_"+acType).select2("val", ""); 
				var returnParams = data.data;
				if(returnParams!='' && returnParams.length != 0){
					$("#verticalAcceleration_modelName_"+acType).append("<option value=\"\"></option>"); 
	                for(var i=0; i<returnParams.length; i++){  
	                    var option="<option value=\""+returnParams[i]+"\""; 
	                    option += ">"+returnParams[i]+"--模型</option>";  //动态添加数据
	                    $("#verticalAcceleration_modelName_"+acType).append(option); 
	                }  
	        	} 
			}else{
				oDateAlert(data.data);
				return;
			}
			
		}
		
	});
}
//最大垂直加速度，通过jobId，模型名获取模型参数列表
function verticalAcceleration_getParamsListByModelName(jobId,modelName,acType){	
	$.ajax({
		type: "POST",
		url: verticalacceleration_locate_url+'/defaultIndex/infoDefaultindexVerticalaccelerationset/getParamsListByModelName',
    	data: {jobId:jobId,modelName:modelName},
		dataType:'json',
		cache: false,
		success: function(data){
			$("#verticalAcceleration_paramName_"+acType).empty();      //清空	
			$("#verticalAcceleration_paramName_"+acType).select2("val", ""); 
			var returnParams = data.data;
			if(returnParams!=''&& returnParams.length != 0){
                for(var i=0; i<returnParams.length; i++){  
                    var option="<option value=\""+returnParams[i]+"\""; 
                    option += ">"+returnParams[i]+"--参数</option>";  //动态添加数据
                    $("#verticalAcceleration_paramName_"+acType).append(option); 
                }  
        	} 
		}
		
	});
}


//最大垂直加速度，通过jobId获取模型列表
function verticalAcceleration_getModelsListByJobId_sync(jobId,acType){	
	$.ajax({
		type: "POST",
		url: verticalacceleration_locate_url+'/defaultIndex/infoDefaultindexVerticalaccelerationset/getModelsListByJobId',
    	data: {jobId:jobId},
		dataType:'json',
		cache: false,
		async: true,  //注意，这里改成同步的方式
		success: function(data){
			hide_zhezhao_mask();//关闭遮罩层
			if(data.status==1001||data.status=='1001'){
				$("#verticalAcceleration_modelName_"+acType).empty();      //清空	
				$("#verticalAcceleration_modelName_"+acType).select2("val", ""); 
				var returnParams = data.data;
				if(returnParams!='' && returnParams.length != 0){
					$("#verticalAcceleration_modelName_"+acType).append("<option value=\"\"></option>"); 
	                for(var i=0; i<returnParams.length; i++){  
	                    var option="<option value=\""+returnParams[i]+"\""; 
	                    option += ">"+returnParams[i]+"--模型</option>";  //动态添加数据
	                    $("#verticalAcceleration_modelName_"+acType).append(option); 
	                }  
	        	} 
			}else{
				oDateAlert(data.data);
				return;
			}
			
		}
		
	});
}
//最大垂直加速度，通过jobId，模型名获取模型参数列表
function verticalAcceleration_getParamsListByModelName_sync(jobId,modelName,acType){	
	$.ajax({
		type: "POST",
		url: verticalacceleration_locate_url+'/defaultIndex/infoDefaultindexVerticalaccelerationset/getParamsListByModelName',
    	data: {jobId:jobId,modelName:modelName},
		dataType:'json',
		cache: false,
		async: true,  //注意，这里改成同步的方式
		success: function(data){
			$("#verticalAcceleration_paramName_"+acType).empty();      //清空	
			$("#verticalAcceleration_paramName_"+acType).select2("val", ""); 
			var returnParams = data.data;
			if(returnParams!=''&& returnParams.length != 0){
                for(var i=0; i<returnParams.length; i++){  
                    var option="<option value=\""+returnParams[i]+"\""; 
                    option += ">"+returnParams[i]+"--参数</option>";  //动态添加数据
                    $("#verticalAcceleration_paramName_"+acType).append(option); 
                }  
        	} 
		}
		
	});
}


//选择任务，查询模型
$("#verticalAcceleration_jobId_CRJ900").change(function(){
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	var jobId_CRJ900 = $("#verticalAcceleration_jobId_CRJ900").val();
	verticalAcceleration_getModelsListByJobId_sync(jobId_CRJ900,'C900');
	
});
$("#verticalAcceleration_jobId_B737").change(function(){
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	var jobId_B737 = $("#verticalAcceleration_jobId_B737").val();
	verticalAcceleration_getModelsListByJobId_sync(jobId_B737,'B737');
});
$("#verticalAcceleration_jobId_A320").change(function(){
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	var jobId_A320 = $("#verticalAcceleration_jobId_A320").val();
	verticalAcceleration_getModelsListByJobId_sync(jobId_A320,'A320');
});
$("#verticalAcceleration_jobId_B777").change(function(){
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	var jobId_B777 = $("#verticalAcceleration_jobId_B777").val();
	verticalAcceleration_getModelsListByJobId_sync(jobId_B777,'B777');
});
$("#verticalAcceleration_jobId_A330").change(function(){
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	var jobId_A330 = $("#verticalAcceleration_jobId_A330").val();
	verticalAcceleration_getModelsListByJobId_sync(jobId_A330,'A330');
});
//选择模型，查询参数--------------------------------------------
$("#verticalAcceleration_modelName_C900").change(function(){
	var jobId_CRJ900 = $("#verticalAcceleration_jobId_CRJ900").val();
	var modelName_C900 = $("#verticalAcceleration_modelName_C900").val();
	verticalAcceleration_getParamsListByModelName_sync(jobId_CRJ900,modelName_C900,'C900');
});
$("#verticalAcceleration_modelName_B737").change(function(){
	var jobId_B737 = $("#verticalAcceleration_jobId_B737").val();
	var modelName_B737 = $("#verticalAcceleration_modelName_B737").val();
	verticalAcceleration_getParamsListByModelName_sync(jobId_B737,modelName_B737,'B737');
});
$("#verticalAcceleration_modelName_A320").change(function(){
	var jobId_A320 = $("#verticalAcceleration_jobId_A320").val();
	var modelName_A320 = $("#verticalAcceleration_modelName_A320").val();
	verticalAcceleration_getParamsListByModelName_sync(jobId_A320,modelName_A320,'A320');
});
$("#verticalAcceleration_modelName_B777").change(function(){
	var jobId_B777 = $("#verticalAcceleration_jobId_B777").val();
	var modelName_B777 = $("#verticalAcceleration_modelName_B777").val();
	verticalAcceleration_getParamsListByModelName_sync(jobId_B777,modelName_B777,'B777');
});
$("#verticalAcceleration_modelName_A330").change(function(){
	var jobId_A330 = $("#verticalAcceleration_jobId_A330").val();
	var modelName_A330 = $("#verticalAcceleration_modelName_A330").val();
	verticalAcceleration_getParamsListByModelName_sync(jobId_A330,modelName_A330,'A330');
});
//
function verticalAcceleration_set_dlg(){
	verticalAcceleration_myDraggleTip();
	$("#verticalAcceleration_set_dlg").modal();
	
	//打开垂直高度模态框，显示上次设置过的属性
	verticalaccelerationset_showModelDlg();
	//初始化下拉框，从数据库中查询数据
	//打开弹出框，初始化下拉框，从数据库查询数据
	$.ajax({
		type: "POST",
		url: verticalacceleration_locate_url+'/defaultIndex/infoDefaultindexVerticalaccelerationset/getParamsStatisticsResult',
    	data: {},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status=='1001'||data.status==1001){
				//verticalacceleration_getDataFromDB
				verticalacceleration_getDataFromDB = data;
				verticalAcceleration_set_choosed_select_Init(verticalacceleration_getDataFromDB);
			}
		}
	});
	
}

//弹出框拖拽提示-------------------------------------------
function verticalAcceleration_myDraggleTip(){
	//弹出框拖拽提示-------------------------------------------
	draggleTip = '';
	draggleTip = '拖拽弹出框，以方便设置';
	oDateAlert3(draggleTip);
	//弹出框拖拽提示-------------------------------------------
}