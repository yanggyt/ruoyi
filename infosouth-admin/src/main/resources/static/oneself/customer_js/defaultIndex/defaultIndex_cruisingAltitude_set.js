/**
 * 巡航高度设置
 * 
 */

var cruisingAltitude_locat = (window.location+'').split('/'); 
var cruisingAltitude_locate_url = '';
$(function(){
	cruisingAltitude_locat =  cruisingAltitude_locat[0]+'//'+cruisingAltitude_locat[2]+'/'+cruisingAltitude_locat[3];
	cruisingAltitude_locate_url = cruisingAltitude_locat + '/a';
});

var cruisingAltitude_static_acType_C900 = global_acTypeArr[0];
var cruisingAltitude_static_acType_B737 = global_acTypeArr[1];
var cruisingAltitude_static_acType_A320 = global_acTypeArr[2];
var cruisingAltitude_static_acType_B777 = global_acTypeArr[3];
var cruisingAltitude_static_acType_A330 = global_acTypeArr[4];
//获取数据库中保存的数据
var cruisingAltitude_getDataFromDB = null;


/* *********************************************************C900巡航高度分析*开始******************************************************************************** */
//查询巡航高度统计结果,没有查询条件，用于默认加载首页
function getCruisingAltitudeFieldsStatisticsResultToPie(){
	$.ajax({
		type: "POST",
		url: cruisingAltitude_locate_url+'/defaultIndex/cruisingAltitude/getCruisingAltitudeFields',
    	data: {},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status=='1001'||data.status==1001){
				infoCruisingaltitudesettingJson = {};
				infoCruisingaltitudesettingJson = data.data;
				cruisingAltitude_getDataFromDB = data;
				
				resultField1Json = {};
				resultField1Json = {"field_index":"1","field_min":infoCruisingaltitudesettingJson.field1_min,"field_max":infoCruisingaltitudesettingJson.field1_max,"field_count":infoCruisingaltitudesettingJson.field1_flight_count};
				resultField2Json = {};
				resultField2Json = {"field_index":"2","field_min":infoCruisingaltitudesettingJson.field2_min,"field_max":infoCruisingaltitudesettingJson.field2_max,"field_count":infoCruisingaltitudesettingJson.field2_flight_count};
				resultField3Json = {};
				resultField3Json = {"field_index":"3","field_min":infoCruisingaltitudesettingJson.field3_min,"field_max":infoCruisingaltitudesettingJson.field3_max,"field_count":infoCruisingaltitudesettingJson.field3_flight_count};
				resultField4Json = {};
				resultField4Json = {"field_index":"4","field_min":infoCruisingaltitudesettingJson.field4_min,"field_max":infoCruisingaltitudesettingJson.field4_max,"field_count":infoCruisingaltitudesettingJson.field4_flight_count};
				
				resultParamDataJson = [];
				resultParamDataJson.push(resultField1Json);
				resultParamDataJson.push(resultField2Json);
				resultParamDataJson.push(resultField3Json);
				resultParamDataJson.push(resultField4Json);
				
				var $chart_container_width_pie=$(".chart_container_width_pie").width();
				var $chart_container_height_pie=$(".chart_container_height_pie").height(); 
				
				chart_container_width_pie = $chart_container_width_pie;
				chart_container_height_pie = $chart_container_height_pie;
				
				//绘制圆饼图
				pie_chart(chart_container_width_pie,chart_container_height_pie,resultParamDataJson);
				
				//更新标题-飞行参数
				cruisingAltitude_title_param = '';
				cruisingAltitude_title_param = infoCruisingaltitudesettingJson.statistics_title;
				$(".cruisingAltitude_task_title").empty();
				$(".cruisingAltitude_task_title").append(cruisingAltitude_title_param);
				
				//
				//初始化下拉框----------------------------------------------			
				var jobId = infoCruisingaltitudesettingJson.jobId;
				var infoDutyScheduleList_CRJ900JsonArr = [];
				infoDutyScheduleList_CRJ900JsonArr = data.infoDutyScheduleList_CRJ900JsonArr;
				cruisingAltitude_set_choosed_select_Init(infoCruisingaltitudesettingJson,infoDutyScheduleList_CRJ900JsonArr);

				//初始化下拉框----------------------------------------------
			}else{
				oDateAlert2(data.data);
				return;
			}
			
		}
		
	});
}
	
	
	
	//初始化所有选过的下拉框
	function cruisingAltitude_set_choosed_select_Init(infoCruisingaltitudesettingJson,infoDutyScheduleList_CRJ900JsonArr){
		chooseOption_jobId = '';
    	//
		$.each(infoDutyScheduleList_CRJ900JsonArr,function(index,infoDutyScheduleList_CRJ900JsonArrEach){					
			if(infoDutyScheduleList_CRJ900JsonArrEach.id==infoCruisingaltitudesettingJson.jobId){
				$("#cruisingAltitude_jobId_CRJ900").val(infoCruisingaltitudesettingJson.jobId).trigger("change");
			}
		});	

		//查询所有模型下拉选项		
		//cruisingAltitude_getModelsListByJobId_sync(infoCruisingaltitudesettingJson.jobId,infoCruisingaltitudesettingJson.modelname);
		//将选中过的模型设为默认
		cruisingAltitude_jobId_CRJ900_chooseOption = '';
		cruisingAltitude_jobId_CRJ900_chooseOption = $("#cruisingAltitude_jobId_CRJ900").val();
		var all = "";
		$("#cruisingAltitude_modelName_C900 option").each(function() {
		    all += $(this).attr("value")+" ";
		});
		//选中的模型名					
		if(cruisingAltitude_jobId_CRJ900_chooseOption!=''&&cruisingAltitude_jobId_CRJ900_chooseOption!=undefined){
			$("#cruisingAltitude_modelName_C900").val(infoCruisingaltitudesettingJson.modelname).trigger("change");
		}

		//查询所有参数下拉选项
		//cruisingAltitude_getParamsListByModelName_sync(infoCruisingaltitudesettingJson.jobId,infoCruisingaltitudesettingJson.modelname);
		
		//
		cruisingAltitude_modelName_C900_chooseOption = '';
		cruisingAltitude_modelName_C900_chooseOption = $("#cruisingAltitude_modelName_C900").val();
		if(cruisingAltitude_modelName_C900_chooseOption!=''&&cruisingAltitude_modelName_C900_chooseOption!=undefined){
			$("#cruisingAltitude_paramName_C900").val(infoCruisingaltitudesettingJson.paramname).trigger("change");
		}	
		
	}

	
	
//巡航高度，通过jobId 获取模型列表
function cruisingAltitude_getModelsListByJobId(jobId){	
	$.ajax({
		type: "POST",
		url: cruisingAltitude_locate_url+'/defaultIndex/cruisingAltitude/getModelsListByJobId',
    	data: {jobId:jobId},
		dataType:'json',
		cache: false,
		success: function(data){
			hide_zhezhao_mask();//关闭遮罩层
			if(data.status==1001||data.status=='1001'){
				$("#cruisingAltitude_modelName_C900").empty();      //清空	
				$("#cruisingAltitude_modelName_C900").select2("val", ""); 
				var returnParams = data.data;
				if(returnParams!='' && returnParams.length != 0){
					$("#cruisingAltitude_modelName_C900").append("<option value=\"\"></option>"); 
	                for(var i=0; i<returnParams.length; i++){  
	                    var option="<option value=\""+returnParams[i]+"\""; 
	                    option += ">"+returnParams[i]+"--模型</option>";  //动态添加数据
	                    $("#cruisingAltitude_modelName_C900").append(option); 
	                }  
	        	} 
				return true;
			}else{
				oDateAlert(data.data);
				return false;
			}
			
		}
		
	});
}

//巡航高度，通过jobId，模型名 获取参数列表
function cruisingAltitude_getParamsListByModelName(jobId,modelName){	
	$.ajax({
		type: "POST",
		url: cruisingAltitude_locate_url+'/defaultIndex/cruisingAltitude/getParamsListByModelName',
    	data: {jobId:jobId,modelName:modelName},
		dataType:'json',
		cache: false,
		success: function(data){
			$("#cruisingAltitude_paramName_C900").empty();      //清空	
			$("#cruisingAltitude_paramName_C900").select2("val", ""); 
			var returnParams = data.data;
			if(returnParams!=''&& returnParams.length != 0){
                for(var i=0; i<returnParams.length; i++){  
                    var option="<option value=\""+returnParams[i]+"\""; 
                    option += ">"+returnParams[i]+"--参数</option>";  //动态添加数据
                    $("#cruisingAltitude_paramName_C900").append(option); 
                }  
        	} 
			return true;
		}
		
	});
}

//巡航高度，通过jobId 获取模型列表
function cruisingAltitude_getModelsListByJobId_sync(jobId){	
	$.ajax({
		type: "POST",
		url: cruisingAltitude_locate_url+'/defaultIndex/cruisingAltitude/getModelsListByJobId',
    	data: {jobId:jobId},
		dataType:'json',
		cache: false,
		async: true,  //注意，这里改成同步的方式
		success: function(data){
			hide_zhezhao_mask();//关闭遮罩层
			if(data.status==1001||data.status=='1001'){
				$("#cruisingAltitude_modelName_C900").empty();      //清空	
				$("#cruisingAltitude_modelName_C900").select2("val", ""); 
				var returnParams = data.data;
				if(returnParams!='' && returnParams.length != 0){
					$("#cruisingAltitude_modelName_C900").append("<option value=\"\"></option>"); 
	                for(var i=0; i<returnParams.length; i++){  
	                    var option="<option value=\""+returnParams[i]+"\""; 
	                    option += ">"+returnParams[i]+"--模型</option>";  //动态添加数据
	                    $("#cruisingAltitude_modelName_C900").append(option); 
	                }  
	        	} 

				return true;
			}else{
				oDateAlert(data.data);
				return false;
			}
			
		}
		
	});
}

//巡航高度，通过jobId，模型名 获取参数列表
function cruisingAltitude_getParamsListByModelName_sync(jobId,modelName){	
	$.ajax({
		type: "POST",
		url: cruisingAltitude_locate_url+'/defaultIndex/cruisingAltitude/getParamsListByModelName',
    	data: {jobId:jobId,modelName:modelName},
		dataType:'json',
		cache: false,
		async: true,  //注意，这里改成同步的方式
		success: function(data){
			$("#cruisingAltitude_paramName_C900").empty();      //清空	
			$("#cruisingAltitude_paramName_C900").select2("val", ""); 
			var returnParams = data.data;
			if(returnParams!=''&& returnParams.length != 0){
                for(var i=0; i<returnParams.length; i++){  
                    var option="<option value=\""+returnParams[i]+"\""; 
                    option += ">"+returnParams[i]+"--参数</option>";  //动态添加数据
                    $("#cruisingAltitude_paramName_C900").append(option); 
                }  
        	} 
			return true;
		}
		
	});
}

//选择任务，查询模型
$("#cruisingAltitude_jobId_CRJ900").change(function(){
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	var jobId_CRJ900 = $("#cruisingAltitude_jobId_CRJ900").val();
	cruisingAltitude_getModelsListByJobId_sync(jobId_CRJ900);
	//置空默认勾选参数下拉框
	$("#cruisingAltitude_paramName_C900").empty();      //清空	
	$("#cruisingAltitude_paramName_C900").select2("val", ""); 
});	
//选择模型，查询参数--------------------------------------------
$("#cruisingAltitude_modelName_C900").change(function(){
	var cruisingAltitude_jobId_CRJ900 = $("#cruisingAltitude_jobId_CRJ900").val();
	var cruisingAltitude_modelName_C900 = $("#cruisingAltitude_modelName_C900").val();
	cruisingAltitude_getParamsListByModelName_sync(cruisingAltitude_jobId_CRJ900,cruisingAltitude_modelName_C900);
});


//根据条件，查询巡航高度
function getCruisingAltitudeFields(jobId,modelName,paramName){
	$.ajax({
		type: "POST",
		url: cruisingAltitude_locate_url+'/defaultIndex/cruisingAltitude/getCruisingAltitudeFields',
    	data: {jobId:jobId,modelName:modelName,paramName:paramName},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status=='1001'||data.status==1001){
				infoCruisingaltitudesettingJson = {};
				infoCruisingaltitudesettingJson = data.data;
				
				
				$("#field1_min").val(infoCruisingaltitudesettingJson.field1_min);
				$("#field1_max").val(infoCruisingaltitudesettingJson.field1_max);
				
				$("#field2_min").val(infoCruisingaltitudesettingJson.field2_min);
				$("#field2_max").val(infoCruisingaltitudesettingJson.field2_max);
				
				$("#field3_min").val(infoCruisingaltitudesettingJson.field3_min);
				$("#field3_max").val(infoCruisingaltitudesettingJson.field3_max);
				
				$("#field4_min").val(infoCruisingaltitudesettingJson.field4_min);
				$("#field4_max").val(infoCruisingaltitudesettingJson.field4_max);
					
				//标题
				$("#cruisingAltitude_title_param").empty();
				$("#cruisingAltitude_title_param").val(infoCruisingaltitudesettingJson.statistics_title);
				
				
			}else{
				oDateAlert(data.data);
				return;
			}
			
		}
		
	});
}




//打开巡航高度模态框
function cruisingAltitude_set_dlg(){
	cruisingAltitude_myDraggleTip();
	$("#cruisingAltitude_set__dlg").modal();
	
	//初始化该图形设置，从数据库查询
	$.ajax({
		type: "POST",
		url: cruisingAltitude_locate_url+'/defaultIndex/cruisingAltitude/getCruisingAltitudeFields',
    	data: {},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status=='1001'||data.status==1001){
				infoCruisingaltitudesettingJson = {};
				infoCruisingaltitudesettingJson = data.data;
				cruisingAltitude_getDataFromDB = data;
				cruisingAltitude_selected(cruisingAltitude_getDataFromDB);
			}
		}
	});
	
	
	//
	var jobId = $("#cruisingAltitude_jobId_CRJ900").val();
	var modelName = $("#cruisingAltitude_modelName_C900").val();
	var paramName = $("#cruisingAltitude_paramName_C900").val();
	//根据条件，查询巡航高度
	getCruisingAltitudeFields(jobId,modelName,paramName);
	
}


//弹出框拖拽提示-------------------------------------------
function cruisingAltitude_myDraggleTip(){
	//弹出框拖拽提示-------------------------------------------
	draggleTip = '';
	draggleTip = '拖拽弹出框，以方便设置';
	oDateAlert3(draggleTip);
	//弹出框拖拽提示-------------------------------------------
}


//打开弹出框，初始化下拉框，默认从数据库查询数据
function cruisingAltitude_selected(data){
	if(data!=null){
		//
		//初始化下拉框----------------------------------------------	
		infoCruisingaltitudesettingJson = {};
		infoCruisingaltitudesettingJson = data.data;
		var jobId = infoCruisingaltitudesettingJson.jobId;
		var infoDutyScheduleList_CRJ900JsonArr = [];
		infoDutyScheduleList_CRJ900JsonArr = data.infoDutyScheduleList_CRJ900JsonArr;
		cruisingAltitude_set_choosed_select_Init(infoCruisingaltitudesettingJson,infoDutyScheduleList_CRJ900JsonArr);

		//初始化下拉框----------------------------------------------
	}else{
		//缓存失效，请刷新页面
		oDateAlert2('缓存失效，请刷新页面');
	}
	
}