/**
 * 
 */
function getttt(){
	alert('ttt');
}
var locat = (window.location+'').split('/'); 
var locate_url = '';
$(function(){
	locat =  locat[0]+'//'+locat[2]+'/'+locat[3];
	locate_url = locat + '/a';
});


//置空列名
function acTypeContrast_empty_choose_paramName_cols(){
	$("#choose_paramName_col1").empty();
	$("#choose_paramName_col2").empty();
	$("#choose_paramName_col3").empty();
	$("#choose_paramName_col4").empty();
}

//置空计算结果
function acTypeContrast_empty_calculateResult(){
	$("#C900_param_col1").empty();
	$("#C900_param_col2").empty();
	$("#C900_param_col3").empty();
	$("#C900_param_col4").empty();
	$("#B737_param_col1").empty();
	$("#B737_param_col2").empty();
	$("#B737_param_col3").empty();
	$("#B737_param_col4").empty();
	$("#A320_param_col1").empty();
	$("#A320_param_col2").empty();
	$("#A320_param_col3").empty();
	$("#A320_param_col4").empty();
	$("#B777_param_col1").empty();
	$("#B777_param_col2").empty();
	$("#B777_param_col3").empty();
	$("#B777_param_col4").empty();
	$("#A330_param_col1").empty();
	$("#A330_param_col2").empty();
	$("#A330_param_col3").empty();
	$("#A330_param_col4").empty();
	
}

//打开机型数据对比模态框
function acTypeContrast_set_dlg(){
	$("#acTypeContrast_set_dlg").modal();
}


//选择机型，刷新任务下拉表
$("#acTypeContrast_set_acTypeList").change(function(){
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层

	acType = '';
	acType = $("#acTypeContrast_set_acTypeList").val();

	//收集上一个机型选过的选项-开始-----------------------------------------------------------------------
	collect_choosed_data(last_acType_Temp);
	//收集上一个机型选过的选项-结束-----------------------------------------------------------------------

	last_acType_Temp = acType;
	
	$.ajax({
		type: "POST",
		url: locate_url+'/defaultIndex/infoDefaultindexActypecontrastset/getJobListByAcType',
    	data: {acType:acType},
		dataType:'json',
		cache: false,
		success: function(data){
			hide_zhezhao_mask();//关闭遮罩层
			if(data.status==1001||data.status=='1001'){
				$("#acTypeContrast_set_jobId").empty();
				$("#acTypeContrast_set_jobId").select2("val", ""); 
				$("#acTypeContrast_set_modelName_col1").empty();
				$("#acTypeContrast_set_modelName_col1").select2("val", ""); 
				$("#acTypeContrast_set_paramName_col1").empty();
				$("#acTypeContrast_set_paramName_col1").select2("val", ""); 
				$("#acTypeContrast_set_paramName_col1_statisticsType").empty();
				$("#acTypeContrast_set_paramName_col1_statisticsType").select2("val", ""); 
				
				$("#acTypeContrast_set_modelName_col2").empty();
				$("#acTypeContrast_set_modelName_col2").select2("val", ""); 
				$("#acTypeContrast_set_paramName_col2").empty();
				$("#acTypeContrast_set_paramName_col2").select2("val", ""); 
				$("#acTypeContrast_set_paramName_col2_statisticsType").empty();
				$("#acTypeContrast_set_paramName_col2_statisticsType").select2("val", ""); 
				
				$("#acTypeContrast_set_modelName_col3").empty();
				$("#acTypeContrast_set_modelName_col3").select2("val", ""); 
				$("#acTypeContrast_set_paramName_col3").empty();
				$("#acTypeContrast_set_paramName_col3").select2("val", ""); 
				$("#acTypeContrast_set_paramName_col3_statisticsType").empty();
				$("#acTypeContrast_set_paramName_col3_statisticsType").select2("val", ""); 
				
				$("#acTypeContrast_set_modelName_col4").empty();
				$("#acTypeContrast_set_modelName_col4").select2("val", ""); 
				$("#acTypeContrast_set_paramName_col4").empty();
				$("#acTypeContrast_set_paramName_col4").select2("val", ""); 
				$("#acTypeContrast_set_paramName_col4_statisticsType").empty();
				$("#acTypeContrast_set_paramName_col4_statisticsType").select2("val", ""); 
				
				var returnParams = data.data;
				if(returnParams!='' && returnParams.length != 0){					
					$("#acTypeContrast_set_jobId").append("<option value=\"\"></option>"); 
	                for(var i=0; i<returnParams.length; i++){
	                	returnParams_i = {};
	                	returnParams_i = returnParams[i];
	                    var option="<option value=\""+returnParams_i.id+"\""; 
	                    option += ">"+returnParams_i.jobName+"--任务</option>";  //动态添加数据
	                    $("#acTypeContrast_set_jobId").append(option); 
	                }  
	        	} 
				//col1参数统计下拉框
				var option1="<option value=\"1\">平均值</option>";
				option1=option1+"<option value=\"2\">中位数</option>";
				option1=option1+"<option value=\"3\">最大值</option>";
				option1=option1+"<option value=\"4\">最小值</option>";
                $("#acTypeContrast_set_paramName_col1_statisticsType").append(option1);  
				//col2参数统计下拉框
                var option2="<option value=\"1\">平均值</option>";
                option2=option2+"<option value=\"2\">中位数</option>";
                option2=option2+"<option value=\"3\">最大值</option>";
                option2=option2+"<option value=\"4\">最小值</option>";
                $("#acTypeContrast_set_paramName_col2_statisticsType").append(option2);  
				//col3参数下拉框
                var option3="<option value=\"1\">平均值</option>";
                option3=option3+"<option value=\"2\">中位数</option>";
                option3=option3+"<option value=\"3\">最大值</option>";
                option3=option3+"<option value=\"4\">最小值</option>";
                $("#acTypeContrast_set_paramName_col3_statisticsType").append(option3); 
				//col4参数下拉框
                var option4="<option value=\"1\">平均值</option>";
                option4=option4+"<option value=\"2\">中位数</option>";
                option4=option4+"<option value=\"3\">最大值</option>";
                option4=option4+"<option value=\"4\">最小值</option>";
                $("#acTypeContrast_set_paramName_col4_statisticsType").append(option4); 
			
				$("#acTypeContrast_set_paramName_col1_statisticsType").val('1').trigger("change");
				$("#acTypeContrast_set_paramName_col2_statisticsType").val('2').trigger("change");
				$("#acTypeContrast_set_paramName_col3_statisticsType").val('3').trigger("change");
				$("#acTypeContrast_set_paramName_col4_statisticsType").val('4').trigger("change");
			}else{
				oDateAlert(data.data);
				return;
			}
			
		}
		
	});
});

//机型数据对比-通过jobid 获取模型列表
function acTypeContrast_set_getModelsListByJobId(jobId,acType){	
	$.ajax({
		type: "POST",
		url: locate_url+'/defaultIndex/infoDefaultindexActypecontrastset/getModelsListByJobId',
    	data: {jobId:jobId},
		dataType:'json',
		cache: false,
		success: function(data){
			hide_zhezhao_mask();//关闭遮罩层
			if(data.status==1001||data.status=='1001'){
				var returnParams = data.data;
				
				$("#acTypeContrast_set_modelName_col1").empty();
				$("#acTypeContrast_set_modelName_col1").select2("val", ""); 								
				if(returnParams!='' && returnParams.length != 0){
					$("#acTypeContrast_set_modelName_col1").append("<option value=\"\"></option>"); 
	                for(var i=0; i<returnParams.length; i++){
	                    var option="<option value=\""+returnParams[i]+"\""; 
	                    option += ">"+returnParams[i]+"--模型</option>";  //动态添加数据
	                    $("#acTypeContrast_set_modelName_col1").append(option); 
	                }  
	        	}
				
				$("#acTypeContrast_set_modelName_col2").empty();
				$("#acTypeContrast_set_modelName_col2").select2("val", ""); 
				var returnParams = data.data;
				if(returnParams!='' && returnParams.length != 0){
					$("#acTypeContrast_set_modelName_col2").append("<option value=\"\"></option>"); 
	                for(var i=0; i<returnParams.length; i++){  
	                    var option="<option value=\""+returnParams[i]+"\""; 
	                    option += ">"+returnParams[i]+"--模型</option>";  //动态添加数据
	                    $("#acTypeContrast_set_modelName_col2").append(option); 
	                }  
	        	}
				
				$("#acTypeContrast_set_modelName_col3").empty();
				$("#acTypeContrast_set_modelName_col3").select2("val", ""); 
				var returnParams = data.data;
				if(returnParams!='' && returnParams.length != 0){
					$("#acTypeContrast_set_modelName_col3").append("<option value=\"\"></option>"); 
	                for(var i=0; i<returnParams.length; i++){  
	                    var option="<option value=\""+returnParams[i]+"\""; 
	                    option += ">"+returnParams[i]+"--模型</option>";  //动态添加数据
	                    $("#acTypeContrast_set_modelName_col3").append(option); 
	                }  
	        	}
				
				$("#acTypeContrast_set_modelName_col4").empty();
				$("#acTypeContrast_set_modelName_col4").select2("val", ""); 
				var returnParams = data.data;
				if(returnParams!='' && returnParams.length != 0){
					$("#acTypeContrast_set_modelName_col4").append("<option value=\"\"></option>"); 
	                for(var i=0; i<returnParams.length; i++){  
	                    var option="<option value=\""+returnParams[i]+"\""; 
	                    option += ">"+returnParams[i]+"--模型</option>";  //动态添加数据
	                    $("#acTypeContrast_set_modelName_col4").append(option); 
	                }  
	        	}

			}else{
				oDateAlert(data.data);
				return;
			}
			
		}
		
	});
}

//机型数据对比-通过modelName 获取参数列表
function acTypeContrast_set_getParamsListByModelName(jobId,modelName,acType,paramType){	
	$.ajax({
		type: "POST",
		url: locate_url+'/defaultIndex/infoDefaultindexActypecontrastset/getParamsListByModelName',
    	data: {jobId:jobId,modelName:modelName},
		dataType:'json',
		cache: false,
		success: function(data){
			hide_zhezhao_mask();//关闭遮罩层
			if(data.status==1001||data.status=='1001'){
				var returnParams = data.data;
				if(paramType=='col1'){
					if(returnParams!='' && returnParams.length != 0){
						$("#acTypeContrast_set_paramName_col1").empty();
						$("#acTypeContrast_set_paramName_col1").select2("val", "");
						$("#acTypeContrast_set_paramName_col1").append("<option value=\"\"></option>"); 
		                for(var i=0; i<returnParams.length; i++){  
		                    var option="<option value=\""+returnParams[i]+"\""; 
		                    option += ">"+returnParams[i]+"--参数</option>";  //动态添加数据
		                    $("#acTypeContrast_set_paramName_col1").append(option); 
		                }  
		        	}
				}else if(paramType=='col2'){
					if(returnParams!='' && returnParams.length != 0){
						$("#acTypeContrast_set_paramName_col2").empty();
						$("#acTypeContrast_set_paramName_col2").select2("val", ""); 	
						$("#acTypeContrast_set_paramName_col2").append("<option value=\"\"></option>"); 
		                for(var i=0; i<returnParams.length; i++){  
		                    var option="<option value=\""+returnParams[i]+"\""; 
		                    option += ">"+returnParams[i]+"--参数</option>";  //动态添加数据
		                    $("#acTypeContrast_set_paramName_col2").append(option); 
		                }  
		        	}
				}else if(paramType=='col3'){
					if(returnParams!='' && returnParams.length != 0){
						$("#acTypeContrast_set_paramName_col3").empty();
						$("#acTypeContrast_set_paramName_col3").select2("val", ""); 
						$("#acTypeContrast_set_paramName_col3").append("<option value=\"\"></option>"); 
		                for(var i=0; i<returnParams.length; i++){  
		                    var option="<option value=\""+returnParams[i]+"\""; 
		                    option += ">"+returnParams[i]+"--参数</option>";  //动态添加数据
		                    $("#acTypeContrast_set_paramName_col3").append(option); 
		                }  
		        	}
				}else if(paramType=='col4'){
					if(returnParams!='' && returnParams.length != 0){
						$("#acTypeContrast_set_paramName_col4").empty();
						$("#acTypeContrast_set_paramName_col4").select2("val", ""); 
						$("#acTypeContrast_set_paramName_col4").append("<option value=\"\"></option>"); 
		                for(var i=0; i<returnParams.length; i++){  
		                    var option="<option value=\""+returnParams[i]+"\""; 
		                    option += ">"+returnParams[i]+"--参数</option>";  //动态添加数据
		                    $("#acTypeContrast_set_paramName_col4").append(option); 
		                }
		        	}
				}else{
					
				}

			}else{
				oDateAlert(data.data);
				return;
			}
			
		}
		
	});
}
//选择任务，查询模型
$("#acTypeContrast_set_jobId").change(function(){
	
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	acType = '';
	acType = $("#acTypeContrast_set_acTypeList").val();
	var acTypeContrast_set_jobId = $("#acTypeContrast_set_jobId").val();
	acTypeContrast_set_getModelsListByJobId(acTypeContrast_set_jobId,acType);
	
});

//选择模型，查询参数col1
$("#acTypeContrast_set_modelName_col1").change(function(){
	
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	acType = '';
	acType = $("#acTypeContrast_set_acTypeList").val();
	var acTypeContrast_set_jobId = $("#acTypeContrast_set_jobId").val();
	var acTypeContrast_set_modelName_col1 = $("#acTypeContrast_set_modelName_col1").val();
	var paramType = 'col1';
	acTypeContrast_set_getParamsListByModelName(acTypeContrast_set_jobId,acTypeContrast_set_modelName_col1,acType,paramType);
	
});

//选择模型，查询参数col2
$("#acTypeContrast_set_modelName_col2").change(function(){
	
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	acType = '';
	acType = $("#acTypeContrast_set_acTypeList").val();
	var acTypeContrast_set_jobId = $("#acTypeContrast_set_jobId").val();
	var acTypeContrast_set_modelName_col2 = $("#acTypeContrast_set_modelName_col2").val();
	var paramType = 'col2';
	acTypeContrast_set_getParamsListByModelName(acTypeContrast_set_jobId,acTypeContrast_set_modelName_col2,acType,paramType);
	
});

//选择模型，查询参数col3
$("#acTypeContrast_set_modelName_col3").change(function(){
	
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	acType = '';
	acType = $("#acTypeContrast_set_acTypeList").val();
	var acTypeContrast_set_jobId = $("#acTypeContrast_set_jobId").val();
	var acTypeContrast_set_modelName_col3 = $("#acTypeContrast_set_modelName_col3").val();
	var paramType = 'col3';
	acTypeContrast_set_getParamsListByModelName(acTypeContrast_set_jobId,acTypeContrast_set_modelName_col3,acType,paramType);
	
});

//选择模型，查询参数col3
$("#acTypeContrast_set_modelName_col4").change(function(){
	
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层
	acType = '';
	acType = $("#acTypeContrast_set_acTypeList").val();
	var acTypeContrast_set_jobId = $("#acTypeContrast_set_jobId").val();
	var acTypeContrast_set_modelName_col4 = $("#acTypeContrast_set_modelName_col4").val();
	var paramType = 'col4';
	acTypeContrast_set_getParamsListByModelName(acTypeContrast_set_jobId,acTypeContrast_set_modelName_col4,acType,paramType);
	
});





//保存设置所有选过的选项--------------------------
var acTypeContrast_set_params_All_Temp = [];
//保存设置所有选过的jobId选项--------------------------
var acTypeContrast_set_jobId_All_Temp = [];

var last_acType_Temp = "";

//收集选择过的选项
function collect_choosed_data(acType){

	var acTypeContrast_set_Title = $("#acTypeContrast_set_Title").val();
	var acTypeContrast_set_jobId = $("#acTypeContrast_set_jobId").val();
	
	var acTypeContrast_set_modelName_col1 = $("#acTypeContrast_set_modelName_col1").val();
	var acTypeContrast_set_paramName_col1 = $("#acTypeContrast_set_paramName_col1").val();
	var acTypeContrast_set_paramName_col1_statisticsType = $("#acTypeContrast_set_paramName_col1_statisticsType").val();
	
	var acTypeContrast_set_modelName_col2 = $("#acTypeContrast_set_modelName_col2").val();
	var acTypeContrast_set_paramName_col2 = $("#acTypeContrast_set_paramName_col2").val();
	var acTypeContrast_set_paramName_col2_statisticsType = $("#acTypeContrast_set_paramName_col2_statisticsType").val();
	
	var acTypeContrast_set_modelName_col3 = $("#acTypeContrast_set_modelName_col3").val();
	var acTypeContrast_set_paramName_col3 = $("#acTypeContrast_set_paramName_col3").val();
	var acTypeContrast_set_paramName_col3_statisticsType = $("#acTypeContrast_set_paramName_col3_statisticsType").val();
	
	var acTypeContrast_set_modelName_col4 = $("#acTypeContrast_set_modelName_col4").val();
	var acTypeContrast_set_paramName_col4 = $("#acTypeContrast_set_paramName_col4").val();
	var acTypeContrast_set_paramName_col4_statisticsType = $("#acTypeContrast_set_paramName_col4_statisticsType").val();
	
	//

	if(acType=='C900'){
		var acTypeContrast_set_params_Temp_Obj = {};
		var acTypeContrast_set_params_Temp = [];
		var acTypeContrast_set_jobId = $("#acTypeContrast_set_jobId").val();
		var acTypeContrast_set_params_Temp_col1 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col1","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col1,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col1,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col1_statisticsType};
		var acTypeContrast_set_params_Temp_col2 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col2","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col2,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col2,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col2_statisticsType};	
		var acTypeContrast_set_params_Temp_col3 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col3","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col3,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col3,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col3_statisticsType};
		var acTypeContrast_set_params_Temp_col4 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col4","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col4,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col4,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col4_statisticsType};
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col1);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col2);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col3);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col4);
		acTypeContrast_set_params_Temp_Obj = {"acType":acType,"jobId":acTypeContrast_set_jobId,"acTypeContrast_set_params_Temp":acTypeContrast_set_params_Temp};
		if(acTypeContrast_set_params_All_Temp.length>0){
			for(var i=0;i<acTypeContrast_set_params_All_Temp.length;i++){
				var acTypeContrast_set_params_All_Temp_i = acTypeContrast_set_params_All_Temp[i];
				//如果有相同的机型，删除该机型数据，重新添加
				if(acType==acTypeContrast_set_params_All_Temp_i.acType){
					acTypeContrast_set_params_All_Temp.splice(i, 1, acTypeContrast_set_params_All_Temp_i);
					
				}
				acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj);
			}
		}else{
			acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj);
		}
		
		
		//收集jobId
		var acTypeContrast_set_jobId_Temp = {"acType":acType,"jobId":acTypeContrast_set_jobId};
		if(acTypeContrast_set_jobId_All_Temp.length>0){
			for(var i=0;i<acTypeContrast_set_jobId_All_Temp.length;i++){
				var acTypeContrast_set_jobId_All_Temp_i = acTypeContrast_set_jobId_All_Temp[i];
				//如果有相同的机型，删除该机型数据，重新添加
				if(acType==acTypeContrast_set_jobId_All_Temp_i.acType){
					acTypeContrast_set_jobId_All_Temp.splice(i, 1, acTypeContrast_set_jobId_All_Temp_i);
					
				}
				acTypeContrast_set_jobId_All_Temp.push(acTypeContrast_set_jobId_Temp);
			}
		}else{
			acTypeContrast_set_jobId_All_Temp.push(acTypeContrast_set_jobId_Temp);
		}
		
	}
	if(acType=='B737'){
		var acTypeContrast_set_params_Temp_Obj = {};
		var acTypeContrast_set_params_Temp = [];
		var acTypeContrast_set_jobId = $("#acTypeContrast_set_jobId").val();
		var acTypeContrast_set_params_Temp_col1 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col1","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col1,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col1,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col1_statisticsType};
		var acTypeContrast_set_params_Temp_col2 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col2","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col2,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col2,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col2_statisticsType};	
		var acTypeContrast_set_params_Temp_col3 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col3","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col3,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col3,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col3_statisticsType};
		var acTypeContrast_set_params_Temp_col4 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col4","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col4,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col4,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col4_statisticsType};
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col1);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col2);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col3);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col4);
		acTypeContrast_set_params_Temp_Obj = {"acType":acType,"jobId":acTypeContrast_set_jobId,"acTypeContrast_set_params_Temp":acTypeContrast_set_params_Temp};
		
		if(acTypeContrast_set_params_All_Temp.length>0){
			for(var i=0;i<acTypeContrast_set_params_All_Temp.length;i++){
				var acTypeContrast_set_params_All_Temp_i = acTypeContrast_set_params_All_Temp[i];
				//如果有相同的机型，删除该机型数据，重新添加
				if(acType==acTypeContrast_set_params_All_Temp_i.acType){
					acTypeContrast_set_params_All_Temp.splice(i, 1, acTypeContrast_set_params_All_Temp_i);
					
				}
				acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj);
			}
		}else{
			acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj);
		}

		//收集jobId
		var acTypeContrast_set_jobId_Temp = {"acType":acType,"jobId":acTypeContrast_set_jobId};
		if(acTypeContrast_set_jobId_All_Temp.length>0){
			for(var i=0;i<acTypeContrast_set_jobId_All_Temp.length;i++){
				var acTypeContrast_set_jobId_All_Temp_i = acTypeContrast_set_jobId_All_Temp[i];
				//如果有相同的机型，删除该机型数据，重新添加
				if(acType==acTypeContrast_set_jobId_All_Temp_i.acType){
					acTypeContrast_set_jobId_All_Temp.splice(i, 1, acTypeContrast_set_jobId_All_Temp_i);
					
				}
				acTypeContrast_set_jobId_All_Temp.push(acTypeContrast_set_jobId_Temp);
			}
		}else{
			acTypeContrast_set_jobId_All_Temp.push(acTypeContrast_set_jobId_Temp);
		}
		
	}
	if(acType=='A320'){
		var acTypeContrast_set_params_Temp_Obj = {};
		var acTypeContrast_set_params_Temp = [];
		var acTypeContrast_set_jobId = $("#acTypeContrast_set_jobId").val();
		var acTypeContrast_set_params_Temp_col1 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col1","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col1,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col1,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col1_statisticsType};
		var acTypeContrast_set_params_Temp_col2 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col2","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col2,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col2,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col2_statisticsType};	
		var acTypeContrast_set_params_Temp_col3 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col3","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col3,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col3,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col3_statisticsType};
		var acTypeContrast_set_params_Temp_col4 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col4","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col4,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col4,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col4_statisticsType};
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col1);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col2);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col3);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col4);
		acTypeContrast_set_params_Temp_Obj = {"acType":acType,"jobId":acTypeContrast_set_jobId,"acTypeContrast_set_params_Temp":acTypeContrast_set_params_Temp};
		if(acTypeContrast_set_params_All_Temp.length>0){
			for(var i=0;i<acTypeContrast_set_params_All_Temp.length;i++){
				var acTypeContrast_set_params_All_Temp_i = acTypeContrast_set_params_All_Temp[i];
				//如果有相同的机型，删除该机型数据，重新添加
				if(acType==acTypeContrast_set_params_All_Temp_i.acType){
					acTypeContrast_set_params_All_Temp.splice(i, 1, acTypeContrast_set_params_All_Temp_i);
					
				}
				acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj);
			}
		}else{
			acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj);
		}
				
		//收集jobId
		var acTypeContrast_set_jobId_Temp = {"acType":acType,"jobId":acTypeContrast_set_jobId};
		if(acTypeContrast_set_jobId_All_Temp.length>0){
			for(var i=0;i<acTypeContrast_set_jobId_All_Temp.length;i++){
				var acTypeContrast_set_jobId_All_Temp_i = acTypeContrast_set_jobId_All_Temp[i];
				//如果有相同的机型，删除该机型数据，重新添加
				if(acType==acTypeContrast_set_jobId_All_Temp_i.acType){
					acTypeContrast_set_jobId_All_Temp.splice(i, 1, acTypeContrast_set_jobId_All_Temp_i);
					
				}
				acTypeContrast_set_jobId_All_Temp.push(acTypeContrast_set_jobId_Temp);
			}
		}else{
			acTypeContrast_set_jobId_All_Temp.push(acTypeContrast_set_jobId_Temp);
		}
		
	}
	if(acType=='B777'){
		var acTypeContrast_set_params_Temp_Obj = {};
		var acTypeContrast_set_params_Temp = [];
		var acTypeContrast_set_jobId = $("#acTypeContrast_set_jobId").val();
		var acTypeContrast_set_params_Temp_col1 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col1","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col1,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col1,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col1_statisticsType};
		var acTypeContrast_set_params_Temp_col2 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col2","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col2,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col2,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col2_statisticsType};	
		var acTypeContrast_set_params_Temp_col3 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col3","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col3,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col3,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col3_statisticsType};
		var acTypeContrast_set_params_Temp_col4 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col4","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col4,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col4,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col4_statisticsType};
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col1);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col2);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col3);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col4);
		acTypeContrast_set_params_Temp_Obj = {"acType":acType,"jobId":acTypeContrast_set_jobId,"acTypeContrast_set_params_Temp":acTypeContrast_set_params_Temp};
		if(acTypeContrast_set_params_All_Temp.length>0){
			for(var i=0;i<acTypeContrast_set_params_All_Temp.length;i++){
				var acTypeContrast_set_params_All_Temp_i = acTypeContrast_set_params_All_Temp[i];
				//如果有相同的机型，删除该机型数据，重新添加
				if(acType==acTypeContrast_set_params_All_Temp_i.acType){
					acTypeContrast_set_params_All_Temp.splice(i, 1, acTypeContrast_set_params_All_Temp_i);
					
				}
				acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj);
			}
		}else{
			acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj);
		}
				
		//收集jobId
		var acTypeContrast_set_jobId_Temp = {"acType":acType,"jobId":acTypeContrast_set_jobId};
		if(acTypeContrast_set_jobId_All_Temp.length>0){
			for(var i=0;i<acTypeContrast_set_jobId_All_Temp.length;i++){
				var acTypeContrast_set_jobId_All_Temp_i = acTypeContrast_set_jobId_All_Temp[i];
				//如果有相同的机型，删除该机型数据，重新添加
				if(acType==acTypeContrast_set_jobId_All_Temp_i.acType){
					acTypeContrast_set_jobId_All_Temp.splice(i, 1, acTypeContrast_set_jobId_All_Temp_i);
					
				}
				acTypeContrast_set_jobId_All_Temp.push(acTypeContrast_set_jobId_Temp);
			}
		}else{
			acTypeContrast_set_jobId_All_Temp.push(acTypeContrast_set_jobId_Temp);
		}
		
	}
	if(acType=='A330'){
		var acTypeContrast_set_params_Temp_Obj = {};
		var acTypeContrast_set_params_Temp = [];
		var acTypeContrast_set_jobId = $("#acTypeContrast_set_jobId").val();
		var acTypeContrast_set_params_Temp_col1 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col1","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col1,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col1,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col1_statisticsType};
		var acTypeContrast_set_params_Temp_col2 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col2","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col2,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col2,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col2_statisticsType};	
		var acTypeContrast_set_params_Temp_col3 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col3","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col3,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col3,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col3_statisticsType};
		var acTypeContrast_set_params_Temp_col4 = {"acType":acType,"jobId":acTypeContrast_set_jobId,"col":"col4","acTypeContrast_set_modelName":acTypeContrast_set_modelName_col4,"acTypeContrast_set_paramName":acTypeContrast_set_paramName_col4,"acTypeContrast_set_paramName_statisticsType":acTypeContrast_set_paramName_col4_statisticsType};
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col1);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col2);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col3);
		acTypeContrast_set_params_Temp.push(acTypeContrast_set_params_Temp_col4);
		acTypeContrast_set_params_Temp_Obj = {"acType":acType,"jobId":acTypeContrast_set_jobId,"acTypeContrast_set_params_Temp":acTypeContrast_set_params_Temp};
		if(acTypeContrast_set_params_All_Temp.length>0){
			for(var i=0;i<acTypeContrast_set_params_All_Temp.length;i++){
				var acTypeContrast_set_params_All_Temp_i = acTypeContrast_set_params_All_Temp[i];
				//如果有相同的机型，删除该机型数据，重新添加
				if(acType==acTypeContrast_set_params_All_Temp_i.acType){
					acTypeContrast_set_params_All_Temp.splice(i, 1, acTypeContrast_set_params_All_Temp_i);
					
				}
				acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj);
			}
		}else{
			acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj);
		}
		
		
		//收集jobId
		var acTypeContrast_set_jobId_Temp = {"acType":acType,"jobId":acTypeContrast_set_jobId};
		if(acTypeContrast_set_jobId_All_Temp.length>0){
			for(var i=0;i<acTypeContrast_set_jobId_All_Temp.length;i++){
				var acTypeContrast_set_jobId_All_Temp_i = acTypeContrast_set_jobId_All_Temp[i];
				//如果有相同的机型，删除该机型数据，重新添加
				if(acType==acTypeContrast_set_jobId_All_Temp_i.acType){
					acTypeContrast_set_jobId_All_Temp.splice(i, 1, acTypeContrast_set_jobId_All_Temp_i);
					
				}
				acTypeContrast_set_jobId_All_Temp.push(acTypeContrast_set_jobId_Temp);
			}
		}else{
			acTypeContrast_set_jobId_All_Temp.push(acTypeContrast_set_jobId_Temp);
		}		
	}
	
	
}

function acTypeContrast_set_func(){
	var acTypeContrast_set_Title = $("#acTypeContrast_set_Title").val();
	var choose_paramName_col1 = $("#choose_paramName_col1").val();
	var choose_paramName_col2 = $("#choose_paramName_col2").val();
	var choose_paramName_col3 = $("#choose_paramName_col3").val();
	var choose_paramName_col4 = $("#choose_paramName_col4").val();
	
	if(acTypeContrast_set_Title==''||acTypeContrast_set_Title==undefined){
		oDateAlert('请填写标题');
		return;
	}
	if(choose_paramName_col1==''||choose_paramName_col1==undefined){
		oDateAlert('请填写参数名');
		return;
	}
	if(choose_paramName_col2==''||choose_paramName_col2==undefined){
		oDateAlert('请填写参数名');
		return;
	}
	if(choose_paramName_col3==''||choose_paramName_col3==undefined){
		oDateAlert('请填写参数名');
		return;
	}
	if(choose_paramName_col4==''||choose_paramName_col4==undefined){
		oDateAlert('请填写参数名');
		return;
	}
	//收集最后选中的机型选过的选项-开始-----------------------------------------------------------------------
	collect_choosed_data(acType);
	//收集最后选中的机型选过的选项-结束-----------------------------------------------------------------------
	
	//选择设置的列名
	var choose_paramName_cols = {};
	choose_paramName_cols = {"choose_paramName_col1":choose_paramName_col1,"choose_paramName_col2":choose_paramName_col2,"choose_paramName_col3":choose_paramName_col3,"choose_paramName_col4":choose_paramName_col4};
	var reqchoose_paramName_colsString = JSON.stringify(choose_paramName_cols);
	
	var reqParamDataJsonString = JSON.stringify(acTypeContrast_set_params_All_Temp);
	
	//
	var reqJobIdDataJsonString = JSON.stringify(acTypeContrast_set_jobId_All_Temp);
	
	//关闭模态框
	$('#acTypeContrast_set_dlg').modal('hide');
	
	//置空列名
	acTypeContrast_empty_choose_paramName_cols();
	//置空之前的计算结果
	acTypeContrast_empty_calculateResult();
	//
	$.ajax({
		type: "POST",
		url: locate_url+'/defaultIndex/infoDefaultindexActypecontrastset/getParamsStatisticsResult_Table',
    	data: {acTypeContrast_set_Title:acTypeContrast_set_Title,reqParamDataJsonString:reqParamDataJsonString,reqJobIdDataJsonString:reqJobIdDataJsonString,reqchoose_paramName_colsString:reqchoose_paramName_colsString},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status=='1001'||data.status==1001){
				allCalcuteResultArr = [];
				allCalcuteResultArr = data.data;
				//填充选中设置的列名
				choose_paramNameJson = {};
				choose_paramNameJson = data.choose_paramNameJson;
				showChoose_paramName(choose_paramNameJson);
				
				for(var i=0;i<allCalcuteResultArr.length;i++){
					allCalcuteResultArr_i = {};
					allCalcuteResultArr_i = allCalcuteResultArr[i];
					//一个机型所有参数计算结果
					oneAcTypeCalcuteResultArr = [];
					oneAcTypeCalcuteResultArr = allCalcuteResultArr_i.oneAcTypeCalcuteResultArr;
					//显示新的计算结果
					showOneAcTypeCalcuteResult(allCalcuteResultArr_i.acType,oneAcTypeCalcuteResultArr);
				}
				//
				alert('成功');
			}else{
				oDateAlert(data.data);
				return;
			}
			
		}
		
	});
	
	//显示新的计算结果
	function showOneAcTypeCalcuteResult(acType,oneAcTypeCalcuteResultArr){
		$.each(oneAcTypeCalcuteResultArr,function(index,oneAcTypeCalcuteResultArr_j){
			//4个参数的计算结果
			if(oneAcTypeCalcuteResultArr_j.statisticsType=='1'){
				$("#"+acType+"_param_col"+oneAcTypeCalcuteResultArr_j.colIndex).text(oneAcTypeCalcuteResultArr_j.avg);
			}
			if(oneAcTypeCalcuteResultArr_j.statisticsType=='2'){
				$("#"+acType+"_param_col"+oneAcTypeCalcuteResultArr_j.colIndex).text(oneAcTypeCalcuteResultArr_j.median);				
			}
			if(oneAcTypeCalcuteResultArr_j.statisticsType=='3'){
				$("#"+acType+"_param_col"+oneAcTypeCalcuteResultArr_j.colIndex).text(oneAcTypeCalcuteResultArr_j.max);
			}
			if(oneAcTypeCalcuteResultArr_j.statisticsType=='4'){
				$("#"+acType+"_param_col"+oneAcTypeCalcuteResultArr_j.colIndex).text(oneAcTypeCalcuteResultArr_j.min);
			}
			
		});
		
	}
	
	
	//填充选中设置的列名
	function showChoose_paramName(choose_paramNameJson){	
		$("#show_choose_paramName_col1").val(choose_paramNameJson.choose_paramName_col1);
		$("#show_choose_paramName_col2").val(choose_paramNameJson.choose_paramName_col2);
		$("#show_choose_paramName_col3").val(choose_paramNameJson.choose_paramName_col3);
		$("#show_choose_paramName_col4").val(choose_paramNameJson.choose_paramName_col4);
	}
	
}