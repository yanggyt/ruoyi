/**
 * pitch分析设置
 * 
 */

var pitchAnalysis_locat = (window.location+'').split('/'); 
var pitchAnalysis_locate_url = '';
$(function(){
	pitchAnalysis_locat =  pitchAnalysis_locat[0]+'//'+pitchAnalysis_locat[2]+'/'+pitchAnalysis_locat[3];
	pitchAnalysis_locate_url = pitchAnalysis_locat + '/a';
});

var pitchAnalysis_static_acType_C900 = global_acTypeArr[0];
var pitchAnalysis_static_acType_B737 = global_acTypeArr[1];
var pitchAnalysis_static_acType_A320 = global_acTypeArr[2];
var pitchAnalysis_static_acType_B777 = global_acTypeArr[3];
var pitchAnalysis_static_acType_A330 = global_acTypeArr[4];

//获取数据库中保存的数据
var pitchAnalysis_getDataFromDB = null;
//统计类型临时变量
var pitchAnalysis_statisticsType_Temp = '';

/* **************************************不同机型起飞Pitch分析设置**开始********************************************* */

//获取pitch起飞参数结果数据柱状图,没有查询条件，用于默认加载首页
function getParamsStatisticsResultToBar(){
	$.ajax({
		type: "POST",
		url: pitchAnalysis_locate_url+'/defaultIndex/infoDefaultindexParamset/getParamsStatisticsResult',
    	data: {},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status=='1001'||data.status==1001){
				//
				pitchAnalysis_getDataFromDB = data;
				//
				var statisticsTitle = data.statisticsTitle;
				var statisticsType = data.statisticsType;
				var calcuteResultArr = data.calcuteResultArr;
				var $chart_container_width_bar=$(".chart_container_bar").width();
				var $chart_container_height_bar=$(".chart_container_bar").height(); 
				
				chart_container_width_bar = $chart_container_width_bar;
				chart_container_height_bar = $chart_container_height_bar;
				//绘制柱状图
				bar_chart(chart_container_width_bar, chart_container_height_bar, calcuteResultArr, statisticsType);
				
				
				//更新参数名标题
				//更新平均值还是中位数
				$(".choose_statisticsType").empty();
				if(statisticsType=='1')
					$(".choose_statisticsType").append('平均值');
				if(statisticsType=='2')
					$(".choose_statisticsType").append('中位数');					
				if(statisticsType=='3')
					$(".choose_statisticsType").append('最大值');
				if(statisticsType=='4')
					$(".choose_statisticsType").append('最小值');	
				//将统计类型传给临时变量
				pitchAnalysis_statisticsType_Temp = statisticsType;
				//更新标题-飞行参数
				$(".choose_paramName").empty();
				$(".choose_paramName").append(statisticsTitle);

				//打开模态框，初始化所有选过的下拉框
				pitchAnalysis_set_choosed_select_Init(data);
				
			}else{
				oDateAlert2(data.data);
				return;
			}
			
		}
		
	});
}

//初始化所有选过的下拉框
function pitchAnalysis_set_choosed_select_Init(data){
	
	if(data!=null){
		//初始化统计类型,pitchAnalysis_statisticsType_Temp 是统计类型临时变量
		//$("#statisticsType").val(pitchAnalysis_statisticsType_Temp).trigger("change");
		$("#statisticsType").val(data.statisticsType).trigger("change");
		
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
				$("#jobId_CRJ900").val(choosedJobIdByAcType.jobidCrj900).trigger("change");
			}
		});	
		//
		$.each(infoDutyScheduleList_B737JsonArr,function(index,infoDutyScheduleList_B737JsonArrEach){					
			if(infoDutyScheduleList_B737JsonArrEach.id==choosedJobIdByAcType.jobidB737){
				$("#jobId_B737").val(choosedJobIdByAcType.jobidB737).trigger("change");
			}
		});	
		//
		$.each(infoDutyScheduleList_A320JsonArr,function(index,infoDutyScheduleList_A320JsonArrEach){					
			if(infoDutyScheduleList_A320JsonArrEach.id==choosedJobIdByAcType.jobidA320){
				$("#jobId_A320").val(choosedJobIdByAcType.jobidA320).trigger("change");
			}
		});	
		//
		$.each(infoDutyScheduleList_B777JsonArr,function(index,infoDutyScheduleList_B777JsonArrEach){					
			if(infoDutyScheduleList_B777JsonArrEach.id==choosedJobIdByAcType.jobidB777){
				$("#jobId_B777").val(choosedJobIdByAcType.jobidB777).trigger("change");
			}
		});	
		//
		$.each(infoDutyScheduleList_A330JsonArr,function(index,infoDutyScheduleList_A330JsonArrEach){					
			if(infoDutyScheduleList_A330JsonArrEach.id==choosedJobIdByAcType.jobidA330){
				$("#jobId_A330").val(choosedJobIdByAcType.jobidA330).trigger("change");
			}
		});	
		
		//查询所有模型下拉选项		
		//getModelsListByJobId_sync(choosedJobIdByAcType.jobidCrj900);
		//查询所有模型下拉选项		
		//getModelsListByJobId_sync(choosedJobIdByAcType.jobidB737);
		//查询所有模型下拉选项		
		//getModelsListByJobId_sync(choosedJobIdByAcType.jobidA320);
		//查询所有模型下拉选项		
		//getModelsListByJobId_sync(choosedJobIdByAcType.jobidB777);
		//查询所有模型下拉选项		
		//getModelsListByJobId_sync(choosedJobIdByAcType.jobidA330);
		
		//如果任务id有默认选中， 将选中过的模型设为默认----------------------------------------------------------------------------
		pitchAnalysis_jobId_CRJ900_chooseOption = '';
		pitchAnalysis_jobId_CRJ900_chooseOption = $("#jobId_CRJ900").val();
		pitchAnalysis_jobId_B737_chooseOption = '';
		pitchAnalysis_jobId_B737_chooseOption = $("#jobId_B737").val();
		pitchAnalysis_jobId_A320_chooseOption = '';
		pitchAnalysis_jobId_A320_chooseOption = $("#jobId_A320").val();
		pitchAnalysis_jobId_B777_chooseOption = '';
		pitchAnalysis_jobId_B777_chooseOption = $("#jobId_B777").val();
		pitchAnalysis_jobId_A330_chooseOption = '';
		pitchAnalysis_jobId_A330_chooseOption = $("#jobId_A330").val();
		
		//选中的模型名					
		if(pitchAnalysis_jobId_CRJ900_chooseOption!=''&&pitchAnalysis_jobId_CRJ900_chooseOption!=undefined){
			$("#modelName_C900").val(choosedModelNameByAcType.modelnameCrj900).trigger("change");
		}
		if(pitchAnalysis_jobId_B737_chooseOption!=''&&pitchAnalysis_jobId_B737_chooseOption!=undefined){
			$("#modelName_B737").val(choosedModelNameByAcType.modelnameB737).trigger("change");
		}
		if(pitchAnalysis_jobId_A320_chooseOption!=''&&pitchAnalysis_jobId_A320_chooseOption!=undefined){
			$("#modelName_A320").val(choosedModelNameByAcType.modelnameA320).trigger("change");
		}
		if(pitchAnalysis_jobId_B777_chooseOption!=''&&pitchAnalysis_jobId_B777_chooseOption!=undefined){
			$("#modelName_B777").val(choosedModelNameByAcType.modelnameB777).trigger("change");
		}
		if(pitchAnalysis_jobId_A330_chooseOption!=''&&pitchAnalysis_jobId_A330_chooseOption!=undefined){
			$("#modelName_A330").val(choosedModelNameByAcType.modelnameA330).trigger("change");
		}
		//如果任务id有默认选中， 将选中过的模型设为默认---------------------------------------------------------------------------
		
		
		//如果模型有默认选中，将选中过的参数设为默认------------------------------------------------------------------------------------
		//getParamsListByModelName_sync(choosedJobIdByAcType.jobidCrj900,choosedModelNameByAcType.modelnameCrj900);

		//getParamsListByModelName_sync(choosedJobIdByAcType.jobidB737,choosedModelNameByAcType.modelnameB737);
		//查询所有参数下拉选项
		//getParamsListByModelName_sync(choosedJobIdByAcType.jobidA320,choosedModelNameByAcType.modelnameA320);
		//查询所有参数下拉选项
		//getParamsListByModelName_sync(choosedJobIdByAcType.jobidB777,choosedModelNameByAcType.modelnameB777);
		//查询所有参数下拉选项
		//getParamsListByModelName_sync(choosedJobIdByAcType.jobidA330,choosedModelNameByAcType.modelnameA330);
		//
		modelName_C900_chooseOption = '';
		modelName_C900_chooseOption = $("#modelName_C900").val();
		if(modelName_C900_chooseOption!=''&&modelName_C900_chooseOption!=undefined){
			$("#paramName_C900").val(choosedParamNameByAcType.paramnameCrj900).trigger("change");
		}
		//
		modelName_B737_chooseOption = '';
		modelName_B737_chooseOption = $("#modelName_B737").val();
		if(modelName_B737_chooseOption!=''&&modelName_B737_chooseOption!=undefined){
			$("#paramName_B737").val(choosedParamNameByAcType.paramnameB737).trigger("change");
		}
		//
		modelName_A320_chooseOption = '';
		modelName_A320_chooseOption = $("#modelName_A320").val();
		if(modelName_A320_chooseOption!=''&&modelName_A320_chooseOption!=undefined){
			$("#paramName_A320").val(choosedParamNameByAcType.paramnameA320).trigger("change");
		}
		//
		modelName_B777_chooseOption = '';
		modelName_B777_chooseOption = $("#modelName_B777").val();
		if(modelName_B777_chooseOption!=''&&modelName_B777_chooseOption!=undefined){
			$("#paramName_B777").val(choosedParamNameByAcType.paramnameB777).trigger("change");
		}
		//
		modelName_A330_chooseOption = '';
		modelName_A330_chooseOption = $("#modelName_A330").val();
		if(modelName_A330_chooseOption!=''&&modelName_A330_chooseOption!=undefined){
			$("#paramName_A330").val(choosedParamNameByAcType.paramnameA330).trigger("change");
		}
		//如果模型有默认选中，将选中过的参数设为默认------------------------------------------------------------------------------------
	}else{
		oDateAlert2('缓存失效，请刷新页面');
	}
	
}


//通过jobId获取模型列表
function getModelsListByJobId(jobId,acType){	
	$.ajax({
		type: "POST",
		url: pitchAnalysis_locate_url+'/defaultIndex/infoDefaultindexParamset/getModelsListByJobId',
    	data: {jobId:jobId},
		dataType:'json',
		cache: false,
		success: function(data){
			hide_zhezhao_mask();//关闭遮罩层
			if(data.status==1001||data.status=='1001'){
				//置空参数下拉框
				$("#paramName_"+acType).empty();      //清空	
				$("#paramName_"+acType).select2("val", ""); 
				$("#modelName_"+acType).empty();      //清空	
				$("#modelName_"+acType).select2("val", ""); 
				var returnParams = data.data;
				if(returnParams!='' && returnParams.length != 0){
					$("#modelName_"+acType).append("<option value=\"\"></option>"); 
	                for(var i=0; i<returnParams.length; i++){  
	                    var option="<option value=\""+returnParams[i]+"\""; 
	                    option += ">"+returnParams[i]+"--模型</option>";  //动态添加数据
	                    $("#modelName_"+acType).append(option); 
	                }  
	        	} 
			}else{
				oDateAlert(data.data);
				return;
			}
			
		}
		
	});
}

//通过jobId,模型名获取参数列表
function getParamsListByModelName(jobId,modelName,acType){	
	$.ajax({
		type: "POST",
		url: pitchAnalysis_locate_url+'/defaultIndex/infoDefaultindexParamset/getParamsListByModelName',
    	data: {jobId:jobId,modelName:modelName},
		dataType:'json',
		cache: false,
		success: function(data){
			$("#paramName_"+acType).empty();      //清空	
			$("#paramName_"+acType).select2("val", ""); 
			var returnParams = data.data;
			if(returnParams!=''&& returnParams.length != 0){
                for(var i=0; i<returnParams.length; i++){  
                    var option="<option value=\""+returnParams[i]+"\""; 
                    option += ">"+returnParams[i]+"--参数</option>";  //动态添加数据
                    $("#paramName_"+acType).append(option); 
                }  
        	} 
		}
		
	});
}

//通过jobId获取模型列表, 同步的方式
function getModelsListByJobId_sync(jobId,acType){	
	$.ajax({
		type: "POST",
		url: pitchAnalysis_locate_url+'/defaultIndex/infoDefaultindexParamset/getModelsListByJobId',
    	data: {jobId:jobId},
		dataType:'json',
		cache: false,
		async: true,  //注意，这里改成同步的方式
		success: function(data){
			hide_zhezhao_mask();//关闭遮罩层
			if(data.status==1001||data.status=='1001'){
				//置空参数下拉框
				$("#paramName_"+acType).empty();      //清空	
				$("#paramName_"+acType).select2("val", ""); 
				
				$("#modelName_"+acType).empty();      //清空	
				$("#modelName_"+acType).select2("val", ""); 
				var returnParams = data.data;
				if(returnParams!='' && returnParams.length != 0){
					$("#modelName_"+acType).append("<option value=\"\"></option>"); 
	                for(var i=0; i<returnParams.length; i++){  
	                    var option="<option value=\""+returnParams[i]+"\""; 
	                    option += ">"+returnParams[i]+"--模型</option>";  //动态添加数据
	                    $("#modelName_"+acType).append(option); 
	                }  
	        	} 
			}else{
				oDateAlert(data.data);
				return;
			}
			
		}
		
	});
}

//通过jobId,模型名获取参数列表, 同步的方式
function getParamsListByModelName_sync(jobId,modelName,acType){	
	$.ajax({
		type: "POST",
		url: pitchAnalysis_locate_url+'/defaultIndex/infoDefaultindexParamset/getParamsListByModelName',
    	data: {jobId:jobId,modelName:modelName},
		dataType:'json',
		cache: false,
		async: true,  //注意，这里改成同步的方式
		success: function(data){
			$("#paramName_"+acType).empty();      //清空	
			$("#paramName_"+acType).select2("val", ""); 
			var returnParams = data.data;
			if(returnParams!=''&& returnParams.length != 0){
                for(var i=0; i<returnParams.length; i++){  
                    var option="<option value=\""+returnParams[i]+"\""; 
                    option += ">"+returnParams[i]+"--参数</option>";  //动态添加数据
                    $("#paramName_"+acType).append(option); 
                }  
        	} 
		}
		
	});
}

//选择任务，查询模型
$("#jobId_CRJ900").change(function(){
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	var jobId_CRJ900 = $("#jobId_CRJ900").val();
	getModelsListByJobId_sync(jobId_CRJ900,'C900');
	
});
$("#jobId_B737").change(function(){
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	var jobId_B737 = $("#jobId_B737").val();
	getModelsListByJobId_sync(jobId_B737,'B737');
});
$("#jobId_A320").change(function(){
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	var jobId_A320 = $("#jobId_A320").val();
	getModelsListByJobId_sync(jobId_A320,'A320');
});
$("#jobId_B777").change(function(){
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	var jobId_B777 = $("#jobId_B777").val();
	getModelsListByJobId_sync(jobId_B777,'B777');
});
$("#jobId_A330").change(function(){
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	var jobId_A330 = $("#jobId_A330").val();
	getModelsListByJobId_sync(jobId_A330,'A330');
});
//选择模型，查询参数--------------------------------------------
$("#modelName_C900").change(function(){
	var jobId_CRJ900 = $("#jobId_CRJ900").val();
	var modelName_C900 = $("#modelName_C900").val();
	getParamsListByModelName_sync(jobId_CRJ900,modelName_C900,'C900');
});
$("#modelName_B737").change(function(){
	var jobId_B737 = $("#jobId_B737").val();
	var modelName_B737 = $("#modelName_B737").val();
	getParamsListByModelName_sync(jobId_B737,modelName_B737,'B737');
});
$("#modelName_A320").change(function(){
	var jobId_A320 = $("#jobId_A320").val();
	var modelName_A320 = $("#modelName_A320").val();
	getParamsListByModelName_sync(jobId_A320,modelName_A320,'A320');
});
$("#modelName_B777").change(function(){
	var jobId_B777 = $("#jobId_B777").val();
	var modelName_B777 = $("#modelName_B777").val();
	getParamsListByModelName_sync(jobId_B777,modelName_B777,'B777');
});
$("#modelName_A330").change(function(){
	var jobId_A330 = $("#jobId_A330").val();
	var modelName_A330 = $("#modelName_A330").val();
	getParamsListByModelName_sync(jobId_A330,modelName_A330,'A330');
});

//pitch柱状图设置打开模态框
function pitch_set_dlg(){
	//
	pitch_set_myDraggleTip();
	$('#pitch_set_dlg').modal();
	//
	$.ajax({
		type: "POST",
		url: pitchAnalysis_locate_url+'/defaultIndex/infoDefaultindexParamset/getParamsStatisticsResult',
    	data: {},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status=='1001'||data.status==1001){
				var statisticsTitle = data.statisticsTitle;
				var statisticsType = data.statisticsType;
			
				//更新参数名标题
				$("#statisticsAction").empty();
				$("#statisticsAction").val(statisticsTitle);
				
			}else{
				oDateAlert2(data.data);
				return;
			}
			
		}
		
	});
	//打开弹出框，初始化下拉框，从数据库查询数据
	$.ajax({
		type: "POST",
		url: pitchAnalysis_locate_url+'/defaultIndex/infoDefaultindexParamset/getParamsStatisticsResult',
    	data: {},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status=='1001'||data.status==1001){
				pitchAnalysis_getDataFromDB = data;
				pitchAnalysis_set_choosed_select_Init(pitchAnalysis_getDataFromDB);
			}
		}
	});
	
}

//弹出框拖拽提示-------------------------------------------
function pitch_set_myDraggleTip(){
	//弹出框拖拽提示-------------------------------------------
	draggleTip = '';
	draggleTip = '拖拽弹出框，以方便设置';
	oDateAlert3(draggleTip);
	//弹出框拖拽提示-------------------------------------------
}