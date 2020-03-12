/**
 * 机型数据对比
 */

var locat = (window.location+'').split('/'); 
var locate_url = '';
$(function(){
	locat =  locat[0]+'//'+locat[2]+'/'+locat[3];
//	locate_url = locat + '/a';
});

var static_acType_C900 = global_acTypeArr[0];
var static_acType_B737 = global_acTypeArr[1];
var static_acType_A320 = global_acTypeArr[2];
var static_acType_B777 = global_acTypeArr[3];
var static_acType_A330 = global_acTypeArr[4];


//打开模态框，有没有选过机型，如果选过机型，累加机型选过的次数
var acTypeContrast_set_acTypeList_change_count = 0;


//保存设置所有选过的选项--------------------------
var acTypeContrast_set_params_All_Temp = [];
//保存设置所有选过的jobId选项--------------------------
var acTypeContrast_set_jobId_All_Temp = [];

var last_acType_Temp = "";

//打开模态框，有没有选过机型，如果选过机型，累加机型选过的次数, 数据来源，数据库
var acTypeContrast_set_params_All_Temp_fromDB = [];

//打开首页，默认初始化表格数据
function getParamsStatisticsResultToTable(){
	$.ajax({
		type: "POST",
		url: locate_url+'/defaultIndex/infoDefaultindexActypecontrastset/getParamsStatisticsResult',
    	data: {},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status=='1001'||data.status==1001){
				paramsetListJsonArr = [];
				paramsetListJsonArr = data.data;
				paramsetListJson = {};
				//从数据库查询的数据
				paramsetListJson = paramsetListJsonArr[0];
				
				//填充标题
				$(".acTypeContrast_set_title").empty();
				$(".acTypeContrast_set_title").append(paramsetListJson.statisticstitle);
				
				choose_paramNameJson = {"choose_paramName_col1":paramsetListJson.chooseParamnameCol1,"choose_paramName_col2":paramsetListJson.chooseParamnameCol2,"choose_paramName_col3":paramsetListJson.chooseParamnameCol3,"choose_paramName_col4":paramsetListJson.chooseParamnameCol4};
				//置空列名
				acTypeContrast_empty_choose_paramName_cols();
				//填充选中设置的列名
				showChoose_paramName(choose_paramNameJson);
				//初始化首页，显示表格中数据
				showTableData_InitPage(paramsetListJson);
				//打开模态框。根据机型，将数据库中保存的设置项显示在下拉框
				//acTypeContrast_set_choosed_select_Init_fromDB(paramsetListJson);
			}else{
				oDateAlert2(data.data);
				return;
			}
			
		}
		
	});
}

//置空列名
function acTypeContrast_empty_choose_paramName_cols(){
	$(".show_choose_paramName_col1").empty();
	$(".show_choose_paramName_col2").empty();
	$(".show_choose_paramName_col3").empty();
	$(".show_choose_paramName_col4").empty();
}

//置空计算结果
function acTypeContrast_empty_calculateResult(){
	$("."+static_acType_C900+"_param_col1").empty();
	$("."+static_acType_C900+"_param_col2").empty();
	$("."+static_acType_C900+"_param_col3").empty();
	$("."+static_acType_C900+"_param_col4").empty();
	$("."+static_acType_B737+"_param_col1").empty();
	$("."+static_acType_B737+"_param_col2").empty();
	$("."+static_acType_B737+"_param_col3").empty();
	$("."+static_acType_B737+"_param_col4").empty();
	$("."+static_acType_A320+"_param_col1").empty();
	$("."+static_acType_A320+"_param_col2").empty();
	$("."+static_acType_A320+"_param_col3").empty();
	$("."+static_acType_A320+"_param_col4").empty();
	$("."+static_acType_B777+"_param_col1").empty();
	$("."+static_acType_B777+"_param_col2").empty();
	$("."+static_acType_B777+"_param_col3").empty();
	$("."+static_acType_B777+"_param_col4").empty();
	$("."+static_acType_A330+"_param_col1").empty();
	$("."+static_acType_A330+"_param_col2").empty();
	$("."+static_acType_A330+"_param_col3").empty();
	$("."+static_acType_A330+"_param_col4").empty();
	
}

//打开机型数据对比模态框
function acTypeContrast_set_dlg(){
	//拖拽提示
	acTypeContrast_myDraggleTip();
	
	//如果有点击“提交”按钮，则置空该全局变量
	/*if(acTypeContrast_set_acTypeList_change_count==0){
		
	}*/
	//置空选过的参数
	acTypeContrast_set_params_All_Temp = [];
	//保存设置所有选过的jobId选项--------------------------
	acTypeContrast_set_jobId_All_Temp = [];
		
	//从数据库查询数据
	$.ajax({
		type: "POST",
		url: locate_url+'/defaultIndex/infoDefaultindexActypecontrastset/getParamsStatisticsResult',
    	data: {},
		dataType:'json',
		cache: false,
		success: function(data){
			if(data.status=='1001'||data.status==1001){
				paramsetListJsonArr = [];
				paramsetListJsonArr = data.data;
				paramsetListJson = {};
				//从数据库查询的数据
				paramsetListJson = paramsetListJsonArr[0];
				acTypeContrast_set_showModelDlg(paramsetListJson);
				
				//------------------------------------------------------------------
				//将任务默认选项设为空
				$("#acTypeContrast_set_jobId").val('').trigger("change");
				//置空上一次选中的机型全局变量
				last_acType_Temp = "";
				//打开模态框。根据机型，将数据库中保存的设置项显示在下拉框
				acTypeContrast_set_choosed_select_Init_fromDB(paramsetListJson);
				//------------------------------------------------------------------
			}else{
				oDateAlert2(data.data);
				return;
			}
			
		}
		
	});
	//调整弹出框显示的位置
	adjustModelDlgHorizontalPosition();
	$("#acTypeContrast_set_dlg").modal();

}



//选择机型，刷新任务下拉表
$("#acTypeContrast_set_acTypeList").change(function(){
	oDateAlert('请稍后...');
	show_zhezhao_mask();//显示遮罩层

	acType = '';
	acType = $("#acTypeContrast_set_acTypeList").val();
	//打开模态框，有没有选过机型，如果选过机型，累加机型选过的次数
	acTypeContrast_set_acTypeList_change_count = acTypeContrast_set_acTypeList_change_count+1;
	
	//收集上一个机型选过的选项-开始-----------------------------------------------------------------------
	collect_choosed_data(last_acType_Temp);
	//收集上一个机型选过的选项-结束-----------------------------------------------------------------------
	//last_acType_Temp设为当前选中的机型
	last_acType_Temp = acType;
	
	$.ajax({
		type: "POST",
		url: locate_url+'/defaultIndex/infoDefaultindexActypecontrastset/getJobListByAcType',
    	data: {acType:acType},
		dataType:'json',
		cache: false,
		async: true,  //注意，这里改成同步的方式
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
				$("#acTypeContrast_set_paramName_col2_statisticsType").val('1').trigger("change");
				$("#acTypeContrast_set_paramName_col3_statisticsType").val('1').trigger("change");
				$("#acTypeContrast_set_paramName_col4_statisticsType").val('1').trigger("change");
				
				
			}else{
				oDateAlert(data.data);
				return;
			}
			
		}
		
	});
	acTypeContrast_set_choosed_select_Init(acType,'');
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
				
				//置空参数下拉框
				$("#acTypeContrast_set_paramName_col1").empty();
				$("#acTypeContrast_set_paramName_col1").select2("val", "");
				$("#acTypeContrast_set_paramName_col2").empty();
				$("#acTypeContrast_set_paramName_col2").select2("val", "");
				$("#acTypeContrast_set_paramName_col3").empty();
				$("#acTypeContrast_set_paramName_col3").select2("val", "");
				$("#acTypeContrast_set_paramName_col4").empty();
				$("#acTypeContrast_set_paramName_col4").select2("val", "");
				
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


//机型数据对比-通过jobid 获取模型列表,同步方式
function acTypeContrast_set_getModelsListByJobId_sync(jobId,acType){	
	$.ajax({
		type: "POST",
		url: locate_url+'/defaultIndex/infoDefaultindexActypecontrastset/getModelsListByJobId',
    	data: {jobId:jobId},
		dataType:'json',
		cache: false,
		async: true,  //注意，这里改成同步的方式
		success: function(data){
			hide_zhezhao_mask();//关闭遮罩层
			if(data.status==1001||data.status=='1001'){
				var returnParams = data.data;
				//置空参数下拉框
				$("#acTypeContrast_set_paramName_col1").empty();
				$("#acTypeContrast_set_paramName_col1").select2("val", "");
				$("#acTypeContrast_set_paramName_col2").empty();
				$("#acTypeContrast_set_paramName_col2").select2("val", "");
				$("#acTypeContrast_set_paramName_col3").empty();
				$("#acTypeContrast_set_paramName_col3").select2("val", "");
				$("#acTypeContrast_set_paramName_col4").empty();
				$("#acTypeContrast_set_paramName_col4").select2("val", "");
				
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
function acTypeContrast_set_getParamsListByModelName_sync(jobId,modelName,acType,paramType){	
	$.ajax({
		type: "POST",
		url: locate_url+'/defaultIndex/infoDefaultindexActypecontrastset/getParamsListByModelName',
    	data: {jobId:jobId,modelName:modelName},
		dataType:'json',
		cache: false,
		async: true,  //注意，这里改成同步的方式
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
	//
	acTypeContrast_set_getModelsListByJobId_sync(acTypeContrast_set_jobId,acType);
	
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
	acTypeContrast_set_getParamsListByModelName_sync(acTypeContrast_set_jobId,acTypeContrast_set_modelName_col1,acType,paramType);
	
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
	acTypeContrast_set_getParamsListByModelName_sync(acTypeContrast_set_jobId,acTypeContrast_set_modelName_col2,acType,paramType);
	
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
	acTypeContrast_set_getParamsListByModelName_sync(acTypeContrast_set_jobId,acTypeContrast_set_modelName_col3,acType,paramType);
	
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
	acTypeContrast_set_getParamsListByModelName_sync(acTypeContrast_set_jobId,acTypeContrast_set_modelName_col4,acType,paramType);
	
});


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
		var isMatch_acType = false;
		var isMatch_acType_ArrIndex = 0;
		for(var i=0;i<acTypeContrast_set_params_All_Temp.length;i++){
			var acTypeContrast_set_params_All_Temp_i = acTypeContrast_set_params_All_Temp[i];
			//如果有相同的机型，删除该机型数据，重新添加
			if(acType==acTypeContrast_set_params_All_Temp_i.acType){
				isMatch_acType = true;					
				isMatch_acType_ArrIndex = i;
			}				
		}
		//
		if(isMatch_acType){
			acTypeContrast_set_params_All_Temp.splice(isMatch_acType_ArrIndex, 1);
		}
		acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj);
	}else{
		acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj);
	}

	//收集jobId
	var acTypeContrast_set_jobId_Temp = {"acType":acType,"jobId":acTypeContrast_set_jobId};
	if(acTypeContrast_set_jobId_All_Temp.length>0){
		var isMatch_acType = false;
		var isMatch_acType_ArrIndex = 0;
		for(var i=0;i<acTypeContrast_set_jobId_All_Temp.length;i++){
			var acTypeContrast_set_jobId_All_Temp_i = acTypeContrast_set_jobId_All_Temp[i];
			//如果有相同的机型，删除该机型数据，重新添加
			if(acType==acTypeContrast_set_jobId_All_Temp_i.acType){
				isMatch_acType = true;					
				isMatch_acType_ArrIndex = i;
			}
		}
		//
		if(isMatch_acType){
			acTypeContrast_set_jobId_All_Temp.splice(isMatch_acType_ArrIndex, 1);
		}
		acTypeContrast_set_jobId_All_Temp.push(acTypeContrast_set_jobId_Temp);
	}else{
		acTypeContrast_set_jobId_All_Temp.push(acTypeContrast_set_jobId_Temp);
	}
	
}

//点击提交按钮，执行
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
		oDateAlert('请填写参数名1');
		return;
	}
	if(choose_paramName_col2==''||choose_paramName_col2==undefined){
		oDateAlert('请填写参数名2');
		return;
	}
	if(choose_paramName_col3==''||choose_paramName_col3==undefined){
		oDateAlert('请填写参数名3');
		return;
	}
	if(choose_paramName_col4==''||choose_paramName_col4==undefined){
		oDateAlert('请填写参数名4');
		return;
	}
	if(choose_paramName_col1.length>8){
		oDateAlert('请小于9个字');
		return;
	}
	if(choose_paramName_col2.length>8){
		oDateAlert('请小于9个字');
		return;
	}
	if(choose_paramName_col3.length>8){
		oDateAlert('请小于9个字');
		return;
	}
	if(choose_paramName_col4.length>8){
		oDateAlert('请小于9个字');
		return;
	}
	//收集最后选中的机型选过的选项-开始-----------------------------------------------------------------------
	if(acType!=''&&acType!=undefined&&acTypeContrast_set_acTypeList_change_count>0)
		collect_choosed_data(acType);
	
	//收集最后选中的机型选过的选项-结束-----------------------------------------------------------------------
	
	//打开模态框，有没有选过机型，如果选过机型，累加机型选过的次数,将该变量置空
	acTypeContrast_set_acTypeList_change_count = 0;
	
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
	oDateAlert('计算中，请稍后...');
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
				//所有机型计算结果数据
				for(var i=0;i<allCalcuteResultArr.length;i++){
					allCalcuteResultArr_i = {};
					allCalcuteResultArr_i = allCalcuteResultArr[i];
					//一个机型所有参数计算结果
					oneAcTypeCalcuteResultArr = [];
					oneAcTypeCalcuteResultArr = allCalcuteResultArr_i.oneAcTypeCalcuteResultArr;
					//显示一个机型新的计算结果
					showOneAcTypeCalcuteResult(allCalcuteResultArr_i.acType,oneAcTypeCalcuteResultArr);
				}
				//
				//更新标题
				$(".acTypeContrast_set_Title").empty();
				$(".acTypeContrast_set_Title").append(acTypeContrast_set_Title);
				
				
				//alert('成功');
			}else{
				oDateAlert(data.data);
				return;
			}
			
		}
		
	});		
}

//显示一个机型新的计算结果
function showOneAcTypeCalcuteResult(acType,oneAcTypeCalcuteResultArr){
	$.each(oneAcTypeCalcuteResultArr,function(index,oneAcTypeCalcuteResultArr_j){
		//4个参数的计算结果
		if(oneAcTypeCalcuteResultArr_j.statisticsType=='1'){
			$("."+acType+"_param_col"+oneAcTypeCalcuteResultArr_j.colIndex).append(oneAcTypeCalcuteResultArr_j.avg);
		}
		if(oneAcTypeCalcuteResultArr_j.statisticsType=='2'){
			$("."+acType+"_param_col"+oneAcTypeCalcuteResultArr_j.colIndex).append(oneAcTypeCalcuteResultArr_j.median);				
		}
		if(oneAcTypeCalcuteResultArr_j.statisticsType=='3'){
			$("."+acType+"_param_col"+oneAcTypeCalcuteResultArr_j.colIndex).append(oneAcTypeCalcuteResultArr_j.max);
		}
		if(oneAcTypeCalcuteResultArr_j.statisticsType=='4'){
			$("."+acType+"_param_col"+oneAcTypeCalcuteResultArr_j.colIndex).append(oneAcTypeCalcuteResultArr_j.min);
		}
		
	});
	
}


//填充选中设置的列名
function showChoose_paramName(choose_paramNameJson){	
	$(".show_choose_paramName_col1").append(choose_paramNameJson.choose_paramName_col1);
	$(".show_choose_paramName_col2").append(choose_paramNameJson.choose_paramName_col2);
	$(".show_choose_paramName_col3").append(choose_paramNameJson.choose_paramName_col3);
	$(".show_choose_paramName_col4").append(choose_paramNameJson.choose_paramName_col4);
		
}

//初始化首页，显示表格中数据
function showTableData_InitPage(paramsetListJson){
	paramnameCrj900Col1Statisticsjson = {};
	if(paramsetListJson.paramnameCrj900Col1Statisticsjson!=''&&paramsetListJson.paramnameCrj900Col1Statisticsjson!=undefined){
		paramnameCrj900Col1Statisticsjson = JSON.parse(paramsetListJson.paramnameCrj900Col1Statisticsjson);
	}
	
	paramnameCrj900Col2Statisticsjson = {};
	if(paramsetListJson.paramnameCrj900Col2Statisticsjson!=''&&paramsetListJson.paramnameCrj900Col2Statisticsjson!=undefined){
		paramnameCrj900Col2Statisticsjson = JSON.parse(paramsetListJson.paramnameCrj900Col2Statisticsjson);
	}
	
	paramnameCrj900Col3Statisticsjson = {};
	if(paramsetListJson.paramnameCrj900Col3Statisticsjson!=''&&paramsetListJson.paramnameCrj900Col3Statisticsjson!=undefined){
		paramnameCrj900Col3Statisticsjson = JSON.parse(paramsetListJson.paramnameCrj900Col3Statisticsjson);
	}
	
	paramnameCrj900Col4Statisticsjson = {};
	if(paramsetListJson.paramnameCrj900Col4Statisticsjson!=''&&paramsetListJson.paramnameCrj900Col4Statisticsjson!=undefined){
		paramnameCrj900Col4Statisticsjson = JSON.parse(paramsetListJson.paramnameCrj900Col4Statisticsjson);
	}
	
	paramnameB737Col1Statisticsjson = {};
	if(paramsetListJson.paramnameB737Col1Statisticsjson!=''&&paramsetListJson.paramnameB737Col1Statisticsjson!=undefined){
		paramnameB737Col1Statisticsjson = JSON.parse(paramsetListJson.paramnameB737Col1Statisticsjson);
	}
	
	paramnameB737Col2Statisticsjson = {};
	if(paramsetListJson.paramnameB737Col2Statisticsjson!=''&&paramsetListJson.paramnameB737Col2Statisticsjson!=undefined){
		paramnameB737Col2Statisticsjson = JSON.parse(paramsetListJson.paramnameB737Col2Statisticsjson);
	}
	
	paramnameB737Col3Statisticsjson = {};
	if(paramsetListJson.paramnameB737Col3Statisticsjson!=''&&paramsetListJson.paramnameB737Col3Statisticsjson!=undefined){
		paramnameB737Col3Statisticsjson = JSON.parse(paramsetListJson.paramnameB737Col3Statisticsjson);
	}
	
	paramnameB737Col4Statisticsjson = {};
	if(paramsetListJson.paramnameB737Col4Statisticsjson!=''&&paramsetListJson.paramnameB737Col4Statisticsjson!=undefined){
		paramnameB737Col4Statisticsjson = JSON.parse(paramsetListJson.paramnameB737Col4Statisticsjson);
	}
	
	paramnameA320Col1Statisticsjson = {};
	if(paramsetListJson.paramnameA320Col1Statisticsjson!=''&&paramsetListJson.paramnameA320Col1Statisticsjson!=undefined){
		paramnameA320Col1Statisticsjson = JSON.parse(paramsetListJson.paramnameA320Col1Statisticsjson);
	}
	
	paramnameA320Col2Statisticsjson = {};
	if(paramsetListJson.paramnameA320Col2Statisticsjson!=''&&paramsetListJson.paramnameA320Col2Statisticsjson!=undefined){
		paramnameA320Col2Statisticsjson = JSON.parse(paramsetListJson.paramnameA320Col2Statisticsjson);
	}
	
	paramnameA320Col3Statisticsjson = {};
	if(paramsetListJson.paramnameA320Col3Statisticsjson!=''&&paramsetListJson.paramnameA320Col3Statisticsjson!=undefined){
		paramnameA320Col3Statisticsjson = JSON.parse(paramsetListJson.paramnameA320Col3Statisticsjson);
	}
	
	paramnameA320Col4Statisticsjson = {};
	if(paramsetListJson.paramnameA320Col4Statisticsjson!=''&&paramsetListJson.paramnameA320Col4Statisticsjson!=undefined){
		paramnameA320Col4Statisticsjson = JSON.parse(paramsetListJson.paramnameA320Col4Statisticsjson);
	}
	
	paramnameB777Col1Statisticsjson = {};
	if(paramsetListJson.paramnameB777Col1Statisticsjson!=''&&paramsetListJson.paramnameB777Col1Statisticsjson!=undefined){
		paramnameB777Col1Statisticsjson = JSON.parse(paramsetListJson.paramnameB777Col1Statisticsjson);
	}
	
	paramnameB777Col2Statisticsjson = {};
	if(paramsetListJson.paramnameCrj900Col1Statisticsjson!=''&&paramsetListJson.paramnameB777Col2Statisticsjson!=undefined){
		paramnameB777Col2Statisticsjson = JSON.parse(paramsetListJson.paramnameB777Col2Statisticsjson);
	}
	
	paramnameB777Col3Statisticsjson = {};
	if(paramsetListJson.paramnameB777Col3Statisticsjson!=''&&paramsetListJson.paramnameB777Col3Statisticsjson!=undefined){
		paramnameB777Col3Statisticsjson = JSON.parse(paramsetListJson.paramnameB777Col3Statisticsjson);
	}
	
	paramnameB777Col4Statisticsjson = {};
	if(paramsetListJson.paramnameB777Col4Statisticsjson!=''&&paramsetListJson.paramnameB777Col4Statisticsjson!=undefined){
		paramnameB777Col4Statisticsjson = JSON.parse(paramsetListJson.paramnameB777Col4Statisticsjson);
	}
	
	paramnameA330Col1Statisticsjson = {};
	if(paramsetListJson.paramnameA330Col1Statisticsjson!=''&&paramsetListJson.paramnameA330Col1Statisticsjson!=undefined){
		paramnameA330Col1Statisticsjson = JSON.parse(paramsetListJson.paramnameA330Col1Statisticsjson);
	}
	
	paramnameA330Col2Statisticsjson = {};
	if(paramsetListJson.paramnameA330Col2Statisticsjson!=''&&paramsetListJson.paramnameA330Col2Statisticsjson!=undefined){
		paramnameA330Col2Statisticsjson = JSON.parse(paramsetListJson.paramnameA330Col2Statisticsjson);
	}
	
	paramnameA330Col3Statisticsjson = {};
	if(paramsetListJson.paramnameCrj900Col1Statisticsjson!=''&&paramsetListJson.paramnameA330Col3Statisticsjson!=undefined){
		paramnameA330Col3Statisticsjson = JSON.parse(paramsetListJson.paramnameA330Col3Statisticsjson);
	}
	
	paramnameA330Col4Statisticsjson = {};
	if(paramsetListJson.paramnameCrj900Col1Statisticsjson!=''&&paramsetListJson.paramnameA330Col4Statisticsjson!=undefined){
		paramnameA330Col4Statisticsjson = JSON.parse(paramsetListJson.paramnameA330Col4Statisticsjson);
	}
	
	paramnameCrj900StatisticsjsonArr = [];
	paramnameCrj900StatisticsjsonArr.push(paramnameCrj900Col1Statisticsjson);
	paramnameCrj900StatisticsjsonArr.push(paramnameCrj900Col2Statisticsjson);
	paramnameCrj900StatisticsjsonArr.push(paramnameCrj900Col3Statisticsjson);
	paramnameCrj900StatisticsjsonArr.push(paramnameCrj900Col4Statisticsjson);
	
	paramnameB737StatisticsjsonArr = [];
	paramnameB737StatisticsjsonArr.push(paramnameB737Col1Statisticsjson);
	paramnameB737StatisticsjsonArr.push(paramnameB737Col2Statisticsjson);
	paramnameB737StatisticsjsonArr.push(paramnameB737Col3Statisticsjson);
	paramnameB737StatisticsjsonArr.push(paramnameB737Col4Statisticsjson);
	
	paramnameA320StatisticsjsonArr = [];
	paramnameA320StatisticsjsonArr.push(paramnameA320Col1Statisticsjson);
	paramnameA320StatisticsjsonArr.push(paramnameA320Col2Statisticsjson);
	paramnameA320StatisticsjsonArr.push(paramnameA320Col3Statisticsjson);
	paramnameA320StatisticsjsonArr.push(paramnameA320Col4Statisticsjson);
	
	paramnameB777StatisticsjsonArr = [];
	paramnameB777StatisticsjsonArr.push(paramnameB777Col1Statisticsjson);
	paramnameB777StatisticsjsonArr.push(paramnameB777Col2Statisticsjson);
	paramnameB777StatisticsjsonArr.push(paramnameB777Col3Statisticsjson);
	paramnameB777StatisticsjsonArr.push(paramnameB777Col4Statisticsjson);
	
	paramnameA330StatisticsjsonArr = [];
	paramnameA330StatisticsjsonArr.push(paramnameA330Col1Statisticsjson);
	paramnameA330StatisticsjsonArr.push(paramnameA330Col2Statisticsjson);
	paramnameA330StatisticsjsonArr.push(paramnameA330Col3Statisticsjson);
	paramnameA330StatisticsjsonArr.push(paramnameA330Col4Statisticsjson);
	
	//
	paramnameCrj900Statisticsjson = {"acType":static_acType_C900,"oneAcTypeCalcuteResultArr":paramnameCrj900StatisticsjsonArr};
	paramnameB737Statisticsjson = {"acType":static_acType_B737,"oneAcTypeCalcuteResultArr":paramnameB737StatisticsjsonArr};
	paramnameA320Statisticsjson = {"acType":static_acType_A320,"oneAcTypeCalcuteResultArr":paramnameA320StatisticsjsonArr};
	paramnameB777Statisticsjson = {"acType":static_acType_B777,"oneAcTypeCalcuteResultArr":paramnameB777StatisticsjsonArr};
	paramnameA330Statisticsjson = {"acType":static_acType_A330,"oneAcTypeCalcuteResultArr":paramnameA330StatisticsjsonArr};
	
	//
	allCalcuteResultArr = [];
	
	//
	allCalcuteResultArr.push(paramnameCrj900Statisticsjson);
	allCalcuteResultArr.push(paramnameB737Statisticsjson);
	allCalcuteResultArr.push(paramnameA320Statisticsjson);
	allCalcuteResultArr.push(paramnameB777Statisticsjson);
	allCalcuteResultArr.push(paramnameA330Statisticsjson);
	
	
	for(var i=0;i<allCalcuteResultArr.length;i++){
		allCalcuteResultArr_i = {};
		allCalcuteResultArr_i = allCalcuteResultArr[i];
		//一个机型所有参数计算结果
		oneAcTypeCalcuteResultArr = [];
		oneAcTypeCalcuteResultArr = allCalcuteResultArr_i.oneAcTypeCalcuteResultArr;
		//显示一个机型新的计算结果
		showOneAcTypeCalcuteResult(allCalcuteResultArr_i.acType,oneAcTypeCalcuteResultArr);
	}
}


//打开模态框，输入框默认显示上一次设置的内容
function acTypeContrast_set_showModelDlg(paramsetListJson){
	$("#acTypeContrast_set_Title").val(paramsetListJson.statisticstitle);
	$("#choose_paramName_col1").val(paramsetListJson.chooseParamnameCol1);
	$("#choose_paramName_col2").val(paramsetListJson.chooseParamnameCol2);
	$("#choose_paramName_col3").val(paramsetListJson.chooseParamnameCol3);
	$("#choose_paramName_col4").val(paramsetListJson.chooseParamnameCol4);
	
	//填充标题
	$(".acTypeContrast_set_title").empty();
	$(".acTypeContrast_set_title").append(paramsetListJson.statisticstitle);

}

//打开模态框，前面选过机型“C900”，后来又重新选了C900, 显示前面选过的选项
function acTypeContrast_showLastSampleAcTypeSetting(acType){
	//
	for(var i=0;i<acTypeContrast_set_params_All_Temp.length;i++){
		acTypeContrast_set_params_All_Temp_i ={};
		acTypeContrast_set_params_All_Temp_i = acTypeContrast_set_params_All_Temp[i];
		if(acType==acTypeContrast_set_params_All_Temp_i.acType){
			
		}
	}
}

//打开模态框。根据机型，将数据库中保存的设置项显示在下拉框
function acTypeContrast_set_choosed_select_Init_fromDB(data){
	paramnameCrj900Col1Statisticsjson = {};
	if(data.paramnameCrj900Col1Statisticsjson!=''&&data.paramnameCrj900Col1Statisticsjson!=undefined){
		paramnameCrj900Col1Statisticsjson = JSON.parse(data.paramnameCrj900Col1Statisticsjson);
	}
	
	paramnameCrj900Col2Statisticsjson = {};
	if(data.paramnameCrj900Col2Statisticsjson!=''&&data.paramnameCrj900Col2Statisticsjson!=undefined){
		paramnameCrj900Col2Statisticsjson = JSON.parse(data.paramnameCrj900Col2Statisticsjson);
	}
	
	paramnameCrj900Col3Statisticsjson = {};
	if(data.paramnameCrj900Col3Statisticsjson!=''&&data.paramnameCrj900Col3Statisticsjson!=undefined){
		paramnameCrj900Col3Statisticsjson = JSON.parse(data.paramnameCrj900Col3Statisticsjson);
	}
	
	paramnameCrj900Col4Statisticsjson = {};
	if(data.paramnameCrj900Col4Statisticsjson!=''&&data.paramnameCrj900Col4Statisticsjson!=undefined){
		paramnameCrj900Col4Statisticsjson = JSON.parse(data.paramnameCrj900Col4Statisticsjson);
	}
	
	paramnameB737Col1Statisticsjson = {};
	if(data.paramnameB737Col1Statisticsjson!=''&&data.paramnameB737Col1Statisticsjson!=undefined){
		paramnameB737Col1Statisticsjson = JSON.parse(data.paramnameB737Col1Statisticsjson);
	}
	
	paramnameB737Col2Statisticsjson = {};
	if(data.paramnameB737Col2Statisticsjson!=''&&data.paramnameB737Col2Statisticsjson!=undefined){
		paramnameB737Col2Statisticsjson = JSON.parse(data.paramnameB737Col2Statisticsjson);
	}
	
	paramnameB737Col3Statisticsjson = {};
	if(data.paramnameB737Col3Statisticsjson!=''&&data.paramnameB737Col3Statisticsjson!=undefined){
		paramnameB737Col3Statisticsjson = JSON.parse(data.paramnameB737Col3Statisticsjson);
	}
	
	paramnameB737Col4Statisticsjson = {};
	if(data.paramnameB737Col4Statisticsjson!=''&&data.paramnameB737Col4Statisticsjson!=undefined){
		paramnameB737Col4Statisticsjson = JSON.parse(data.paramnameB737Col4Statisticsjson);
	}
	
	paramnameA320Col1Statisticsjson = {};
	if(data.paramnameA320Col1Statisticsjson!=''&&data.paramnameA320Col1Statisticsjson!=undefined){
		paramnameA320Col1Statisticsjson = JSON.parse(data.paramnameA320Col1Statisticsjson);
	}
	
	paramnameA320Col2Statisticsjson = {};
	if(data.paramnameA320Col2Statisticsjson!=''&&data.paramnameA320Col2Statisticsjson!=undefined){
		paramnameA320Col2Statisticsjson = JSON.parse(data.paramnameA320Col2Statisticsjson);
	}
	
	paramnameA320Col3Statisticsjson = {};
	if(data.paramnameA320Col3Statisticsjson!=''&&data.paramnameA320Col3Statisticsjson!=undefined){
		paramnameA320Col3Statisticsjson = JSON.parse(data.paramnameA320Col3Statisticsjson);
	}
	
	paramnameA320Col4Statisticsjson = {};
	if(data.paramnameA320Col4Statisticsjson!=''&&data.paramnameA320Col4Statisticsjson!=undefined){
		paramnameA320Col4Statisticsjson = JSON.parse(data.paramnameA320Col4Statisticsjson);
	}
	
	paramnameB777Col1Statisticsjson = {};
	if(data.paramnameB777Col1Statisticsjson!=''&&data.paramnameB777Col1Statisticsjson!=undefined){
		paramnameB777Col1Statisticsjson = JSON.parse(data.paramnameB777Col1Statisticsjson);
	}
	
	paramnameB777Col2Statisticsjson = {};
	if(data.paramnameCrj900Col1Statisticsjson!=''&&data.paramnameB777Col2Statisticsjson!=undefined){
		paramnameB777Col2Statisticsjson = JSON.parse(data.paramnameB777Col2Statisticsjson);
	}
	
	paramnameB777Col3Statisticsjson = {};
	if(data.paramnameB777Col3Statisticsjson!=''&&data.paramnameB777Col3Statisticsjson!=undefined){
		paramnameB777Col3Statisticsjson = JSON.parse(data.paramnameB777Col3Statisticsjson);
	}
	
	paramnameB777Col4Statisticsjson = {};
	if(data.paramnameB777Col4Statisticsjson!=''&&data.paramnameB777Col4Statisticsjson!=undefined){
		paramnameB777Col4Statisticsjson = JSON.parse(data.paramnameB777Col4Statisticsjson);
	}
	
	paramnameA330Col1Statisticsjson = {};
	if(data.paramnameA330Col1Statisticsjson!=''&&data.paramnameA330Col1Statisticsjson!=undefined){
		paramnameA330Col1Statisticsjson = JSON.parse(data.paramnameA330Col1Statisticsjson);
	}
	
	paramnameA330Col2Statisticsjson = {};
	if(data.paramnameA330Col2Statisticsjson!=''&&data.paramnameA330Col2Statisticsjson!=undefined){
		paramnameA330Col2Statisticsjson = JSON.parse(data.paramnameA330Col2Statisticsjson);
	}
	
	paramnameA330Col3Statisticsjson = {};
	if(data.paramnameCrj900Col1Statisticsjson!=''&&data.paramnameA330Col3Statisticsjson!=undefined){
		paramnameA330Col3Statisticsjson = JSON.parse(data.paramnameA330Col3Statisticsjson);
	}
	
	paramnameA330Col4Statisticsjson = {};
	if(data.paramnameCrj900Col1Statisticsjson!=''&&data.paramnameA330Col4Statisticsjson!=undefined){
		paramnameA330Col4Statisticsjson = JSON.parse(data.paramnameA330Col4Statisticsjson);
	}
	
	//
	C900_acTypeContrast_set_params_Temp = [];
	var C900_acTypeContrast_set_params_Temp_col1 = {"acType":static_acType_C900,"jobId":data.jobidCrj900,"col":"col1","acTypeContrast_set_modelName":data.modelnameCrj900Col1,"acTypeContrast_set_paramName":data.paramnameCrj900Col1,"acTypeContrast_set_paramName_statisticsType":paramnameCrj900Col1Statisticsjson.statisticsType};
	var C900_acTypeContrast_set_params_Temp_col2 = {"acType":static_acType_C900,"jobId":data.jobidCrj900,"col":"col2","acTypeContrast_set_modelName":data.modelnameCrj900Col2,"acTypeContrast_set_paramName":data.paramnameCrj900Col2,"acTypeContrast_set_paramName_statisticsType":paramnameCrj900Col2Statisticsjson.statisticsType};	
	var C900_acTypeContrast_set_params_Temp_col3 = {"acType":static_acType_C900,"jobId":data.jobidCrj900,"col":"col3","acTypeContrast_set_modelName":data.modelnameCrj900Col3,"acTypeContrast_set_paramName":data.paramnameCrj900Col3,"acTypeContrast_set_paramName_statisticsType":paramnameCrj900Col3Statisticsjson.statisticsType};
	var C900_acTypeContrast_set_params_Temp_col4 = {"acType":static_acType_C900,"jobId":data.jobidCrj900,"col":"col4","acTypeContrast_set_modelName":data.modelnameCrj900Col4,"acTypeContrast_set_paramName":data.paramnameCrj900Col4,"acTypeContrast_set_paramName_statisticsType":paramnameCrj900Col4Statisticsjson.statisticsType};
	C900_acTypeContrast_set_params_Temp.push(C900_acTypeContrast_set_params_Temp_col1);
	C900_acTypeContrast_set_params_Temp.push(C900_acTypeContrast_set_params_Temp_col2);
	C900_acTypeContrast_set_params_Temp.push(C900_acTypeContrast_set_params_Temp_col3);
	C900_acTypeContrast_set_params_Temp.push(C900_acTypeContrast_set_params_Temp_col4);
	//
	B737_acTypeContrast_set_params_Temp = [];
	var B737_acTypeContrast_set_params_Temp_col1 = {"acType":static_acType_B737,"jobId":data.jobidB737,"col":"col1","acTypeContrast_set_modelName":data.modelnameB737Col1,"acTypeContrast_set_paramName":data.paramnameB737Col1,"acTypeContrast_set_paramName_statisticsType":paramnameB737Col1Statisticsjson.statisticsType};
	var B737_acTypeContrast_set_params_Temp_col2 = {"acType":static_acType_B737,"jobId":data.jobidB737,"col":"col2","acTypeContrast_set_modelName":data.modelnameB737Col2,"acTypeContrast_set_paramName":data.paramnameB737Col2,"acTypeContrast_set_paramName_statisticsType":paramnameB737Col2Statisticsjson.statisticsType};	
	var B737_acTypeContrast_set_params_Temp_col3 = {"acType":static_acType_B737,"jobId":data.jobidB737,"col":"col3","acTypeContrast_set_modelName":data.modelnameB737Col3,"acTypeContrast_set_paramName":data.paramnameB737Col3,"acTypeContrast_set_paramName_statisticsType":paramnameB737Col3Statisticsjson.statisticsType};
	var B737_acTypeContrast_set_params_Temp_col4 = {"acType":static_acType_B737,"jobId":data.jobidB737,"col":"col4","acTypeContrast_set_modelName":data.modelnameB737Col4,"acTypeContrast_set_paramName":data.paramnameB737Col4,"acTypeContrast_set_paramName_statisticsType":paramnameB737Col4Statisticsjson.statisticsType};
	B737_acTypeContrast_set_params_Temp.push(B737_acTypeContrast_set_params_Temp_col1);
	B737_acTypeContrast_set_params_Temp.push(B737_acTypeContrast_set_params_Temp_col2);
	B737_acTypeContrast_set_params_Temp.push(B737_acTypeContrast_set_params_Temp_col3);
	B737_acTypeContrast_set_params_Temp.push(B737_acTypeContrast_set_params_Temp_col4);
	//
	A320_acTypeContrast_set_params_Temp = [];
	var A320_acTypeContrast_set_params_Temp_col1 = {"acType":static_acType_A320,"jobId":data.jobidA320,"col":"col1","acTypeContrast_set_modelName":data.modelnameA320Col1,"acTypeContrast_set_paramName":data.paramnameA320Col1,"acTypeContrast_set_paramName_statisticsType":paramnameA320Col1Statisticsjson.statisticsType};
	var A320_acTypeContrast_set_params_Temp_col2 = {"acType":static_acType_A320,"jobId":data.jobidA320,"col":"col2","acTypeContrast_set_modelName":data.modelnameA320Col2,"acTypeContrast_set_paramName":data.paramnameA320Col2,"acTypeContrast_set_paramName_statisticsType":paramnameA320Col2Statisticsjson.statisticsType};	
	var A320_acTypeContrast_set_params_Temp_col3 = {"acType":static_acType_A320,"jobId":data.jobidA320,"col":"col3","acTypeContrast_set_modelName":data.modelnameA320Col3,"acTypeContrast_set_paramName":data.paramnameA320Col3,"acTypeContrast_set_paramName_statisticsType":paramnameA320Col3Statisticsjson.statisticsType};
	var A320_acTypeContrast_set_params_Temp_col4 = {"acType":static_acType_A320,"jobId":data.jobidA320,"col":"col4","acTypeContrast_set_modelName":data.modelnameA320Col4,"acTypeContrast_set_paramName":data.paramnameA320Col4,"acTypeContrast_set_paramName_statisticsType":paramnameA320Col4Statisticsjson.statisticsType};
	A320_acTypeContrast_set_params_Temp.push(A320_acTypeContrast_set_params_Temp_col1);
	A320_acTypeContrast_set_params_Temp.push(A320_acTypeContrast_set_params_Temp_col2);
	A320_acTypeContrast_set_params_Temp.push(A320_acTypeContrast_set_params_Temp_col3);
	A320_acTypeContrast_set_params_Temp.push(A320_acTypeContrast_set_params_Temp_col4);
	//
	B777_acTypeContrast_set_params_Temp = [];
	var B777_acTypeContrast_set_params_Temp_col1 = {"acType":static_acType_B777,"jobId":data.jobidB777,"col":"col1","acTypeContrast_set_modelName":data.modelnameB777Col1,"acTypeContrast_set_paramName":data.paramnameB777Col1,"acTypeContrast_set_paramName_statisticsType":paramnameB777Col1Statisticsjson.statisticsType};
	var B777_acTypeContrast_set_params_Temp_col2 = {"acType":static_acType_B777,"jobId":data.jobidB777,"col":"col2","acTypeContrast_set_modelName":data.modelnameB777Col2,"acTypeContrast_set_paramName":data.paramnameB777Col2,"acTypeContrast_set_paramName_statisticsType":paramnameB777Col2Statisticsjson.statisticsType};	
	var B777_acTypeContrast_set_params_Temp_col3 = {"acType":static_acType_B777,"jobId":data.jobidB777,"col":"col3","acTypeContrast_set_modelName":data.modelnameB777Col3,"acTypeContrast_set_paramName":data.paramnameB777Col3,"acTypeContrast_set_paramName_statisticsType":paramnameB777Col3Statisticsjson.statisticsType};
	var B777_acTypeContrast_set_params_Temp_col4 = {"acType":static_acType_B777,"jobId":data.jobidB777,"col":"col4","acTypeContrast_set_modelName":data.modelnameB777Col4,"acTypeContrast_set_paramName":data.paramnameB777Col4,"acTypeContrast_set_paramName_statisticsType":paramnameB777Col4Statisticsjson.statisticsType};
	B777_acTypeContrast_set_params_Temp.push(B777_acTypeContrast_set_params_Temp_col1);
	B777_acTypeContrast_set_params_Temp.push(B777_acTypeContrast_set_params_Temp_col2);
	B777_acTypeContrast_set_params_Temp.push(B777_acTypeContrast_set_params_Temp_col3);
	B777_acTypeContrast_set_params_Temp.push(B777_acTypeContrast_set_params_Temp_col4);
	//
	A330_acTypeContrast_set_params_Temp = [];
	var A330_acTypeContrast_set_params_Temp_col1 = {"acType":static_acType_A330,"jobId":data.jobidA330,"col":"col1","acTypeContrast_set_modelName":data.modelnameA330Col1,"acTypeContrast_set_paramName":data.paramnameA330Col1,"acTypeContrast_set_paramName_statisticsType":paramnameA330Col1Statisticsjson.statisticsType};
	var A330_acTypeContrast_set_params_Temp_col2 = {"acType":static_acType_A330,"jobId":data.jobidA330,"col":"col2","acTypeContrast_set_modelName":data.modelnameA330Col2,"acTypeContrast_set_paramName":data.paramnameA330Col2,"acTypeContrast_set_paramName_statisticsType":paramnameA330Col2Statisticsjson.statisticsType};	
	var A330_acTypeContrast_set_params_Temp_col3 = {"acType":static_acType_A330,"jobId":data.jobidA330,"col":"col3","acTypeContrast_set_modelName":data.modelnameA330Col3,"acTypeContrast_set_paramName":data.paramnameA330Col3,"acTypeContrast_set_paramName_statisticsType":paramnameA330Col3Statisticsjson.statisticsType};
	var A330_acTypeContrast_set_params_Temp_col4 = {"acType":static_acType_A330,"jobId":data.jobidA330,"col":"col4","acTypeContrast_set_modelName":data.modelnameA330Col4,"acTypeContrast_set_paramName":data.paramnameA330Col4,"acTypeContrast_set_paramName_statisticsType":paramnameA330Col4Statisticsjson.statisticsType};
	A330_acTypeContrast_set_params_Temp.push(A330_acTypeContrast_set_params_Temp_col1);
	A330_acTypeContrast_set_params_Temp.push(A330_acTypeContrast_set_params_Temp_col2);
	A330_acTypeContrast_set_params_Temp.push(A330_acTypeContrast_set_params_Temp_col3);
	A330_acTypeContrast_set_params_Temp.push(A330_acTypeContrast_set_params_Temp_col4);
	//
	var acTypeContrast_set_params_Temp_Obj_C900 = {"acType":static_acType_C900,"jobId":data.jobidCrj900,"acTypeContrast_set_params_Temp":C900_acTypeContrast_set_params_Temp};
	var acTypeContrast_set_params_Temp_Obj_B737 = {"acType":static_acType_B737,"jobId":data.jobidB737,"acTypeContrast_set_params_Temp":B737_acTypeContrast_set_params_Temp};
	var acTypeContrast_set_params_Temp_Obj_A320 = {"acType":static_acType_A320,"jobId":data.jobidA320,"acTypeContrast_set_params_Temp":A320_acTypeContrast_set_params_Temp};
	var acTypeContrast_set_params_Temp_Obj_B777 = {"acType":static_acType_B777,"jobId":data.jobidB777,"acTypeContrast_set_params_Temp":B777_acTypeContrast_set_params_Temp};
	var acTypeContrast_set_params_Temp_Obj_A330 = {"acType":static_acType_A330,"jobId":data.jobidA330,"acTypeContrast_set_params_Temp":A330_acTypeContrast_set_params_Temp};
	
	//
	acTypeContrast_set_params_All_Temp_fromDB.push(acTypeContrast_set_params_Temp_Obj_C900);
	acTypeContrast_set_params_All_Temp_fromDB.push(acTypeContrast_set_params_Temp_Obj_B737);
	acTypeContrast_set_params_All_Temp_fromDB.push(acTypeContrast_set_params_Temp_Obj_A320);
	acTypeContrast_set_params_All_Temp_fromDB.push(acTypeContrast_set_params_Temp_Obj_B777);
	acTypeContrast_set_params_All_Temp_fromDB.push(acTypeContrast_set_params_Temp_Obj_A330);
	
	acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj_C900);
	acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj_B737);
	acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj_A320);
	acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj_B777);
	acTypeContrast_set_params_All_Temp.push(acTypeContrast_set_params_Temp_Obj_A330);
	
	
	//jobId 数组
	var C900_acTypeContrast_set_jobId_Temp = {"acType":static_acType_C900,"jobId":data.jobidCrj900};
	var B737_acTypeContrast_set_jobId_Temp = {"acType":static_acType_B737,"jobId":data.jobidB737};
	var A320_acTypeContrast_set_jobId_Temp = {"acType":static_acType_A320,"jobId":data.jobidA320};
	var B777_acTypeContrast_set_jobId_Temp = {"acType":static_acType_B777,"jobId":data.jobidB777};
	var A330_acTypeContrast_set_jobId_Temp = {"acType":static_acType_A330,"jobId":data.jobidA330};
	
	acTypeContrast_set_jobId_All_Temp.push(C900_acTypeContrast_set_jobId_Temp);
	acTypeContrast_set_jobId_All_Temp.push(B737_acTypeContrast_set_jobId_Temp);
	acTypeContrast_set_jobId_All_Temp.push(A320_acTypeContrast_set_jobId_Temp);
	acTypeContrast_set_jobId_All_Temp.push(B777_acTypeContrast_set_jobId_Temp);
	acTypeContrast_set_jobId_All_Temp.push(A330_acTypeContrast_set_jobId_Temp);

	//任选一个之前有保存设置过的机型选项，将它设为默认显示的机型：
	for(var i=0;i<acTypeContrast_set_jobId_All_Temp.length;i++){
		acTypeContrast_set_jobId_All_Temp_i = {};
		acTypeContrast_set_jobId_All_Temp_i = acTypeContrast_set_jobId_All_Temp[i];
		if(acTypeContrast_set_jobId_All_Temp_i.jobId!=''&&acTypeContrast_set_jobId_All_Temp_i.jobId!=undefined){
			$("#acTypeContrast_set_acTypeList").val(acTypeContrast_set_jobId_All_Temp_i.acType).trigger("change");
			break;
		}else{
			continue;
		}		
	}
	
}

//打开模态框。根据机型，将之前选中的设置项显示在下拉框
function acTypeContrast_set_choosed_select_Init(acType,data){
	if(acType==''||acType==undefined||acType==null){
		oDateAlert('请选择机型');
		return;
	}
	var acType_match_flag = false;
	var acType_match_jobId = '';
	//匹配的机型的之前下拉框设置过的选项
	match_acTypeContrast_set_params_All_Temp_byAcType = {};
	for(var i=0;i<acTypeContrast_set_params_All_Temp.length;i++){
		acTypeContrast_set_params_All_Temp_i = {};
		acTypeContrast_set_params_All_Temp_i = acTypeContrast_set_params_All_Temp[i];
		if(acTypeContrast_set_params_All_Temp_i.acType!=''&&acTypeContrast_set_params_All_Temp_i.acType!=undefined){
			if(acType==acTypeContrast_set_params_All_Temp_i.acType){
				acType_match_flag = true;
				acType_match_jobId = acTypeContrast_set_params_All_Temp_i.jobId;
				match_acTypeContrast_set_params_All_Temp_byAcType = acTypeContrast_set_params_All_Temp_i;
			}
		}
		//
		
	}
	jobId = '';
	
	if(acType_match_flag){
		jobId = acType_match_jobId;
	}
	

	if(acType!=''&&acType!=undefined){
		//$("#acTypeContrast_set_acTypeList").val(acType).trigger("change");
		//
		if(jobId!=''&&jobId!=undefined){
			$("#acTypeContrast_set_jobId").val(jobId).trigger("change");
		}
		//
		acTypeContrast_set_jobId = '';
		acTypeContrast_set_jobId = $("#acTypeContrast_set_jobId").val();
		if(acTypeContrast_set_jobId!=''&&acTypeContrast_set_jobId!=undefined){
			//
			//查询所有模型下拉选项		
			//acTypeContrast_set_getModelsListByJobId_sync(acTypeContrast_set_jobId);
			
			//如果任务id有默认选中， 将选中过的模型设为默认---------------------------------------------------------------------------
			//将选中过的模型设为默认
			acTypeContrast_set_modelName_col1 = '';
			acTypeContrast_set_modelName_col2 = '';
			acTypeContrast_set_modelName_col3 = '';
			acTypeContrast_set_modelName_col4 = '';
			
			//将选中过的参数设为默认
			acTypeContrast_set_paramName_col1 = '';
			acTypeContrast_set_paramName_col2 = '';
			acTypeContrast_set_paramName_col3 = '';
			acTypeContrast_set_paramName_col4 = '';
			//将选中过的统计类型设为默认
			acTypeContrast_set_paramName_statisticsType_col1 = '';
			acTypeContrast_set_paramName_statisticsType_col2 = '';
			acTypeContrast_set_paramName_statisticsType_col3 = '';
			acTypeContrast_set_paramName_statisticsType_col4 = '';
			
			match_acTypeContrast_set_params_Temp_byAcType_data = [];
			match_acTypeContrast_set_params_Temp_byAcType_data = match_acTypeContrast_set_params_All_Temp_byAcType.acTypeContrast_set_params_Temp;
			for(var i=0;i<match_acTypeContrast_set_params_Temp_byAcType_data.length;i++){
				match_acTypeContrast_set_params_Temp_byAcType_data_i = {};
				match_acTypeContrast_set_params_Temp_byAcType_data_i = match_acTypeContrast_set_params_Temp_byAcType_data[i];
				//
				var match_col=match_acTypeContrast_set_params_Temp_byAcType_data_i.col;
				var match_acTypeContrast_set_modelName = match_acTypeContrast_set_params_Temp_byAcType_data_i.acTypeContrast_set_modelName;
				var match_acTypeContrast_set_paramName = match_acTypeContrast_set_params_Temp_byAcType_data_i.acTypeContrast_set_paramName;
				var match_acTypeContrast_set_paramName_statisticsType = match_acTypeContrast_set_params_Temp_byAcType_data_i.acTypeContrast_set_paramName_statisticsType;
				//
				if(match_col=='col1'){
					if(match_acTypeContrast_set_modelName!=''&&match_acTypeContrast_set_modelName!=undefined){
						acTypeContrast_set_modelName_col1 = match_acTypeContrast_set_modelName;
					}
					if(match_acTypeContrast_set_modelName!=''&&match_acTypeContrast_set_modelName!=undefined){
						acTypeContrast_set_paramName_col1 = match_acTypeContrast_set_paramName;
					}
					if(match_acTypeContrast_set_paramName_statisticsType!=''&&match_acTypeContrast_set_paramName_statisticsType!=undefined){
						acTypeContrast_set_paramName_statisticsType_col1 = match_acTypeContrast_set_paramName_statisticsType;
					}
				}
				if(match_col=='col2'){
					if(match_acTypeContrast_set_modelName!=''&&match_acTypeContrast_set_modelName!=undefined){
						acTypeContrast_set_modelName_col2 = match_acTypeContrast_set_modelName;
					}
					if(match_acTypeContrast_set_modelName!=''&&match_acTypeContrast_set_modelName!=undefined){
						acTypeContrast_set_paramName_col2 = match_acTypeContrast_set_paramName;
					}
					if(match_acTypeContrast_set_paramName_statisticsType!=''&&match_acTypeContrast_set_paramName_statisticsType!=undefined){
						acTypeContrast_set_paramName_statisticsType_col2 = match_acTypeContrast_set_paramName_statisticsType;
					}
				}
				if(match_col=='col3'){
					if(match_acTypeContrast_set_modelName!=''&&match_acTypeContrast_set_modelName!=undefined){
						acTypeContrast_set_modelName_col3 = match_acTypeContrast_set_modelName;
					}
					if(match_acTypeContrast_set_modelName!=''&&match_acTypeContrast_set_modelName!=undefined){
						acTypeContrast_set_paramName_col3 = match_acTypeContrast_set_paramName;
					}
					if(match_acTypeContrast_set_paramName_statisticsType!=''&&match_acTypeContrast_set_paramName_statisticsType!=undefined){
						acTypeContrast_set_paramName_statisticsType_col3 = match_acTypeContrast_set_paramName_statisticsType;
					}
				}
				if(match_col=='col4'){
					if(match_acTypeContrast_set_modelName!=''&&match_acTypeContrast_set_modelName!=undefined){
						acTypeContrast_set_modelName_col4 = match_acTypeContrast_set_modelName;
					}
					if(match_acTypeContrast_set_modelName!=''&&match_acTypeContrast_set_modelName!=undefined){
						acTypeContrast_set_paramName_col4 = match_acTypeContrast_set_paramName;
					}
					if(match_acTypeContrast_set_paramName_statisticsType!=''&&match_acTypeContrast_set_paramName_statisticsType!=undefined){
						acTypeContrast_set_paramName_statisticsType_col4 = match_acTypeContrast_set_paramName_statisticsType;
					}
				}

			}
			//测试下拉框的所有选项-----------------------------------------------------
			var all_test = "";
			$("#acTypeContrast_set_modelName_col1 option").each(function() {
				all_test += $(this).attr("value")+" ";
			});
			//测试下拉框的所有选项-----------------------------------------------------
						
			//选中的模型名					
			if(acTypeContrast_set_jobId!=''&&acTypeContrast_set_jobId!=undefined){
				if(acTypeContrast_set_modelName_col1!=''&&acTypeContrast_set_modelName_col1!=undefined){
					$("#acTypeContrast_set_modelName_col1").val(acTypeContrast_set_modelName_col1).trigger("change");
				}
				if(acTypeContrast_set_modelName_col2!=''&&acTypeContrast_set_modelName_col2!=undefined){
					$("#acTypeContrast_set_modelName_col2").val(acTypeContrast_set_modelName_col2).trigger("change");					
								}
				if(acTypeContrast_set_modelName_col3!=''&&acTypeContrast_set_modelName_col3!=undefined){
					$("#acTypeContrast_set_modelName_col3").val(acTypeContrast_set_modelName_col3).trigger("change");
				}
				if(acTypeContrast_set_modelName_col4!=''&&acTypeContrast_set_modelName_col4!=undefined){
					$("#acTypeContrast_set_modelName_col4").val(acTypeContrast_set_modelName_col4).trigger("change");
				}

			}
			//如果任务id有默认选中， 将选中过的模型设为默认---------------------------------------------------------------------------
			
			//如果模型有默认选中，将选中过的参数设为默认------------------------------------------------------------------------------------
			//查询所有参数下拉选项
			//acTypeContrast_set_getParamsListByModelName_sync(acTypeContrast_set_jobId,acTypeContrast_set_modelName_col1);
			//查询所有参数下拉选项
			//acTypeContrast_set_getParamsListByModelName_sync(acTypeContrast_set_jobId,acTypeContrast_set_modelName_col2);
			//查询所有参数下拉选项
			//acTypeContrast_set_getParamsListByModelName_sync(acTypeContrast_set_jobId,acTypeContrast_set_modelName_col3);
			//查询所有参数下拉选项
			//acTypeContrast_set_getParamsListByModelName_sync(acTypeContrast_set_jobId,acTypeContrast_set_modelName_col4);
						
			//将选中过的参数设为默认			
			if(acTypeContrast_set_paramName_col1!=''&&acTypeContrast_set_paramName_col1!=undefined){
				$("#acTypeContrast_set_paramName_col1").val(acTypeContrast_set_paramName_col1).trigger("change");
			}
			if(acTypeContrast_set_paramName_col2!=''&&acTypeContrast_set_paramName_col2!=undefined){
				$("#acTypeContrast_set_paramName_col2").val(acTypeContrast_set_paramName_col2).trigger("change");
			}
			if(acTypeContrast_set_paramName_col3!=''&&acTypeContrast_set_paramName_col3!=undefined){
				$("#acTypeContrast_set_paramName_col3").val(acTypeContrast_set_paramName_col3).trigger("change");
			}
			if(acTypeContrast_set_paramName_col4!=''&&acTypeContrast_set_paramName_col4!=undefined){
				$("#acTypeContrast_set_paramName_col4").val(acTypeContrast_set_paramName_col4).trigger("change");
			}
			//将选中过的统计类型设为默认
			if(acTypeContrast_set_paramName_statisticsType_col1!=''&&acTypeContrast_set_paramName_statisticsType_col1!=undefined){
				$("#acTypeContrast_set_paramName_col1_statisticsType").val(acTypeContrast_set_paramName_statisticsType_col1).trigger("change");
			}
			if(acTypeContrast_set_paramName_statisticsType_col2!=''&&acTypeContrast_set_paramName_statisticsType_col2!=undefined){
				$("#acTypeContrast_set_paramName_col2_statisticsType").val(acTypeContrast_set_paramName_statisticsType_col2).trigger("change");
			}
			if(acTypeContrast_set_paramName_statisticsType_col3!=''&&acTypeContrast_set_paramName_statisticsType_col3!=undefined){
				$("#acTypeContrast_set_paramName_col3_statisticsType").val(acTypeContrast_set_paramName_statisticsType_col3).trigger("change");
			}
			if(acTypeContrast_set_paramName_statisticsType_col4!=''&&acTypeContrast_set_paramName_statisticsType_col4!=undefined){
				$("#acTypeContrast_set_paramName_col4_statisticsType").val(acTypeContrast_set_paramName_statisticsType_col4).trigger("change");
			}
				
			//
			
			//如果模型有默认选中，将选中过的参数设为默认------------------------------------------------------------------------------------
		}
	}
	
}

//置空下拉框
function acTypeContrast_empty_selected(){
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
	
}


//弹出框拖拽提示-------------------------------------------
function acTypeContrast_myDraggleTip(){
	//弹出框拖拽提示-------------------------------------------
	draggleTip = '';
	draggleTip = '拖拽弹出框，以方便设置';
	oDateAlert3(draggleTip);
	//弹出框拖拽提示-------------------------------------------
}