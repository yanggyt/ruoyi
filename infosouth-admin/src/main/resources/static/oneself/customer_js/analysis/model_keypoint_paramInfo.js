var model_keypoint_paramInfo_locat = (window.location+'').split('/'); 
var model_keypoint_paramInfo_locate_url = '';
$(function(){
	model_keypoint_paramInfo_locat =  model_keypoint_paramInfo_locat[0]+'//'+model_keypoint_paramInfo_locat[2]+'/'+model_keypoint_paramInfo_locat[3];
	model_keypoint_paramInfo_locate_url = model_keypoint_paramInfo_locat + '/a';

});



//每一个参数对应固定的颜色数组
var models_keypoint_params_colorArr = [];
//每个模型点击事件
$("#tbody_model_name").on("click","tr",function(){  
	$(this).addClass('selected') //为选中项添加高亮
	.siblings().removeClass('selected')//去除其他项的高亮形式
	.end();
	//根据contentTableName 确定变色的行
	tagscheckByContentTableName('contentTable1',this);
	 //显示遮罩层    
	show_zhezhao_mask();
	//显示加载中提示
    show_loading_tips();
	
	var trclass = $(this).attr("class");
	var trclassArr = trclass.replace(' selected','');
	var lineId = trclassArr;
	//除去 selected 标签
	
	var keyPointName = lineId;

	var req_jobId = $("#jobId").val();
		
	//重置每一个参数对应固定的颜色数组
	models_keypoint_params_colorArr = [];
	
	$.ajax({
		type: "POST",
		url: model_keypoint_paramInfo_locate_url+'/analysis/modelResultShow/getKeyPointData',
    	data: {jobId:req_jobId,keyPointName:keyPointName},
		dataType:'json',
		cache: false,
		success: function(data){	
			//隐藏遮罩层  
		    hide_zhezhao_mask();
		  	//隐藏加载中提示
		    hide_loading_tips();
			if(data.status=='1001'){
				var returnData = [];
				returnData = data.data;	
				var models_keypoint_fileNames = returnData.models_keypoint_fileNames;
				if(models_keypoint_fileNames==null||models_keypoint_fileNames==undefined){
					oDateAlert('没有执行的csv文件！没有结果数据');
					return false;
				}
				//文件名列表
				var fileNameArr = models_keypoint_fileNames.fileNameArr;
				var models_keypoint_paramsArr = returnData.models_keypoint_paramsArr;
				var fileNameHtml = "";
				if(fileNameArr==undefined||fileNameArr==''){
					oDateAlert('没有执行的csv文件！没有结果数据');
					return false;
				}
				for(var i=0;i<fileNameArr.length;i++){
					var paramObj = fileNameArr[i];
					fileNameHtml += "<tr class=\""+paramObj.keyPointName+"-"+"csv-"+i+"\">";
					fileNameHtml += "<td class='center' style=\"width: 30px;\">";
					fileNameHtml += "<input type='checkbox' name=\"checkbox_name_file\" value=\""+paramObj.keyPointName+";"+parseInt(i+1)+";"+paramObj.fileName+"\" id=\"checkbox_file_"+parseInt(i+1)+"\" alt=\"\"/>";
					fileNameHtml += "</td>";
					fileNameHtml += "<td class='center' style=\"width: 30px;\">";
					fileNameHtml += 'csv'+parseInt(i+1);
					fileNameHtml += "</td>";
					fileNameHtml += "<td>";
					keyPointNameTemp = paramObj.keyPointName;
					if(keyPointNameTemp.length>20){
						keyPointNameTemp = keyPointNameTemp.keyPointNameTemp(0,20)+'...';
					}
					fileNameHtml += keyPointNameTemp;
					fileNameHtml += "</td>";
					fileNameHtml += "<td>";
					fileNameTemp = paramObj.fileName;
					if(fileNameTemp.length>20){
						fileNameTemp = fileNameTemp.substring(0,20)+'...';
					}
					fileNameHtml += fileNameTemp;
					fileNameHtml += "</td>";					
					fileNameHtml += "</tr>";
				}
				$("#tbody_files_name").empty();
				$("#tbody_files_name").append(fileNameHtml);
				
				var paramshtml = "";
				for(var i=0;i<models_keypoint_paramsArr.length;i++){
					var models_keypoint_paramsObj = models_keypoint_paramsArr[i];
					paramshtml += "<tr class=\""+models_keypoint_paramsObj.keyPointName+"-"+models_keypoint_paramsObj.paramName+"\">";
					
					paramshtml += "<td class='center' style=\"width: 30px;\">";
					paramshtml += "<input type='checkbox' name=\"checkbox_name"+parseInt(i+1)+"\" value=\""+models_keypoint_paramsObj.keyPointName+";"+parseInt(i+1)+";"+models_keypoint_paramsObj.paramName+"\" id=\"checkbox_param_"+parseInt(i+1)+"\" alt=\"\"/>";
					paramshtml += "</td>";
					paramshtml += "<td>";
					keyPointNameTemp = models_keypoint_paramsObj.keyPointName;
					if(keyPointNameTemp.length>20){
						keyPointNameTemp = keyPointNameTemp.keyPointNameTemp(0,20)+'...';
					}
					paramshtml += keyPointNameTemp;
					paramshtml += "</td>";
					paramshtml += "<td class=\""+models_keypoint_paramsObj.paramName+"_class\">";
					paramshtml += models_keypoint_paramsObj.paramName;
					paramshtml += "</td>";
					
					
					paramshtml += "</tr>";
					
					//给每个参数添加显示的颜色序号
					models_keypoint_params_colorArr.push({"paramName_k":models_keypoint_paramsObj.paramName,"color_index":i});
				}
				$("#tbody_params_name").empty();
				$("#tbody_params_name").append(paramshtml);
				//重置全选勾选框
				$("#checkAll").attr("checked",this.checked==true?false:false);
				//清空x轴范围设定
			 	customer_x_field = [];
			 	//清空y轴范围设定
				customer_y_field = [];
				oDateAlert('查询参数成功！');
			}else if(data.status=='1002'){
				oDateAlert('没有数据，请重新请求任务');
				console.log('没有数据，请重新请求任务');
				return;
			}else if(data.status=='1003'){
				oDateAlert('没有数据，请重新请求任务！');
				console.log('没有数据，请重新请求任务！');
				return;
			}else if(data.status=='1004'){
				oDateAlert('Id为空，请重新请求任务');
				console.log('Id为空，请重新请求任务');
				return;
			}else if(data.status=='1005'){
				oDateAlert('jobId为空，请重新请求任务');
				console.log('jobId为空，请重新请求任务');
				return;
			}
			console.log('成功');
		}
	});
	//注销之前的图形缓存，1个以上的图表	
	disposeHistoryChatObjArr();
	//如果没有勾选参数，清除图表上方的参数名，清除勾选框,反之，加上勾选框
  	clearParamsTitle('');
	console.log('加载模型数据成功');

});

$('#checkAll').on('change', function() {
	//显示遮罩层    
	show_zhezhao_mask();
	//显示加载中提示
    show_loading_tips();
    
    //获取已勾选的JobId_modelName_paramsName
	var choosedDataObj = getJobId_modelName_paramsName();
   
	var req_jobId = choosedDataObj.req_jobId;
	var keyPointName = choosedDataObj.keyPointName;
	var paramsNameStr = choosedDataObj.paramsNameStr;
	if(req_jobId==undefined){
		req_jobId = '';
	}
	if(keyPointName==undefined){
		keyPointName = '';
	}
	if(paramsNameStr==undefined||paramsNameStr==''||paramsNameStr=='undefined'){
		//隐藏遮罩层  
	    hide_zhezhao_mask();
	  	//隐藏加载中提示
	    hide_loading_tips();
		if($(this).prop("checked")){
			$(this).attr("checked",this.checked==true?false:false);
			oDateAlert('请先勾选参数');
		}
		return;
	}
	var isAllCsvFlag='1';
	var csvFileNamesStr = '';
	var isChooseParamFlag = '0';
	
	console.log('------------checkbox choose--start---------------');
	var ischecked = $(this).prop("checked");  
    if(ischecked){
    	var checkboxValue = $(this).attr('value');
    	$("input[name='checkbox_name_file'").prop('checked', $(this).prop("checked"));
    	//var filesNameStr = getCheckedFiles();
    	//有勾选
    	//drawCharts(models_result_paramsArr_data,filesNameStr);
    	//req_refresh_chart_by_condition(models_result_paramsArr_data,'allFiles');    	
    }else{
    	$('[name=checkbox_name_file]:checkbox').each(function(){
    		$(this).attr("checked",this.checked==true?false:false);
    	});
    	//var filesNameStr = getCheckedFiles();
    	//没有勾选
    	//req_refresh_chart_by_condition(models_result_paramsArr_data,filesNameStr);
    	
    	isAllCsvFlag = '0';	
    }
    console.log('------------checkbox choose--end---------------');
    //获取服务端相关的参数，csv文件数据，并绘图
	getParamsArrDataToChart(choosedDataObj.req_jobId,choosedDataObj.keyPointName,choosedDataObj.paramsNameStr,isAllCsvFlag,csvFileNamesStr,isChooseParamFlag);
   
});


//所有文件点过的checkbox
var clicked_files_checkboxValue = '';
var filesNameStr = '';
//每个文件点击点击事件
$("#tbody_files_name").on("click","input[type='checkbox']",function(){
	
	var tbodyObj = document.getElementById('tbody_files_name');  
        
	//var filesNameStr = getCheckedFiles();
	//req_refresh_chart_by_condition(models_result_paramsArr_data,filesNameStr);
	
	//获取已勾选的JobId_modelName_paramsName
	var choosedDataObj = getJobId_modelName_paramsName();    
	var req_jobId = choosedDataObj.req_jobId;
	var keyPointName = choosedDataObj.keyPointName;
	var paramsNameStr = choosedDataObj.paramsNameStr;
	if(req_jobId==undefined){
		req_jobId = '';
	}
	if(keyPointName==undefined){
		keyPointName = '';
	}
	if(paramsNameStr==undefined||paramsNameStr==''||paramsNameStr=='undefined'){
		if($(this).prop("checked")){
			$(this).attr("checked",this.checked==true?false:false);
			oDateAlert('请先勾选参数');
			console.log('请先勾选参数');
		}
		return;
	}
	var checkboxValue_csvFileId = $(this)[0].id;
	var isAllCsvFlag='2';
	if(isChoosed_CheckAll_CheckBox_Tag()){		
		isAllCsvFlag = '1';
		$(this).attr("checked",this.checked==true?true:true);
		oDateAlert('请先取消全选');
		console.log('请先取消全选');
		return;
	}
	
	var csvFileNamesStr = getCheckedFiles(checkboxValue_csvFileId,isAllCsvFlag);
	if(csvFileNamesStr=='checkAllFlag'){
		oDateAlert('勾选更多csv文件，请选择全选功能');
		console.log('勾选更多csv文件，请选择全选功能');
		return;
	}
	var isChooseParamFlag = '0';
	//获取服务端相关的参数，csv文件数据，并绘图
	getParamsArrDataToChart(req_jobId,keyPointName,paramsNameStr,isAllCsvFlag,csvFileNamesStr,isChooseParamFlag);
	
});

//获取勾选的文件名
function getCheckedFiles(checkboxId,isAllCsvFlag){
	if(isAllCsvFlag!='1'&&isAllCsvFlag!=1){
		clicked_files_checkboxValue = '';
		console.log('------------checkbox choose--start---------------');
	    $("#contentTable3 tr").find("td:first input:checkbox").each(function () {  
	        var ischecked = $(this).prop("checked");  
	        if(ischecked){
	        	var checkboxValue = $(this).attr('value');
	        	clicked_files_checkboxValue += checkboxValue+',';
	        }
	        
			 });
	    console.log('------------checkbox choose--end---------------');
		
		$(this).addClass('selected') //为选中项添加高亮
		.siblings().removeClass('selected')//去除其他项的高亮形式
		.end();	
		//根据contentTableName 确定变色的行
		tagscheckByContentTableName('contentTable3',this);
		
		var trclassArr = clicked_files_checkboxValue.substring(0,clicked_files_checkboxValue.length-1).split(",");
		//如果勾选超过30个，给出一个全选提示
		if(trclassArr!=''&&trclassArr!=undefined&&trclassArr.length>30){
			
			$('#'+checkboxId).attr("checked",this.checked==true?false:false);			
			return 'checkAllFlag';
		}
		var keyPointName = '';
		filesNameStr = '';
		for(var i=0;i<trclassArr.length;i++){
			var lineId = trclassArr[i];
			//除去 selected 标签
			var fidStr = lineId.split(';');
			//获取关键点名
			keyPointName = fidStr[0];
			var linei = fidStr[1];
			filesNameStr+=fidStr[2]+',';

		}
		
		//包含选择过的参数
		filesNameStr = filesNameStr.substring(0,filesNameStr.length-1);
		if(filesNameStr=='undefined'||filesNameStr==undefined){
			filesNameStr = '';
		}
		return filesNameStr;
	}else{
		return '';
	}
	
}

//页面总高度
var page_total_height = 780;
//所有点过的checkbox
var clicked_checkboxValue = '';
var paramsNameStr = '';
var myLineChartArr = [];
var models_result_paramsArr_data = [];
var paramValue_jArr_X_k = [];	
//每个参数点击事件
$("#tbody_params_name").on("click","input[type='checkbox']",function(){		
	clicked_checkboxValue = '';
	var tbodyObj = document.getElementById('tbody_params_name');  
        
    console.log('------------checkbox choose--start---------------');
    $("#contentTable2 tr").find("td:first input:checkbox").each(function () {  
        var ischecked = $(this).prop("checked");  
        if(ischecked){
        	var checkboxValue = $(this).attr('value');
        	clicked_checkboxValue += checkboxValue+',';
        }
        
		 });
    console.log('------------checkbox choose--end---------------');
	
	$(this).addClass('selected') //为选中项添加高亮
	.siblings().removeClass('selected')//去除其他项的高亮形式
	.end();
	//根据contentTableName 确定变色的行
	tagscheckByContentTableName('contentTable2',this);
	
	var trclassArr = clicked_checkboxValue.substring(0,clicked_checkboxValue.length-1).split(",");
	var keyPointName = '';
	paramsNameStr = '';
	if(trclassArr.length>3){
		$(this).attr("checked",this.checked==true?false:false);
		oDateAlert('一个页面最多只能显示3个图表');
		console.log('返回异常，一个页面最多只能显示3个图表');
		return;
	}		
	for(var i=0;i<trclassArr.length;i++){
		var lineId = trclassArr[i];
		//除去 selected 标签
		var fidStr = lineId.split(';');
		//获取关键点名
		keyPointName = fidStr[0];
		var linei = fidStr[1];
		paramsNameStr+=fidStr[2]+',';
				

	}
	
	
	//是否勾选该参数
	var ischecked = $(this).prop("checked");  
    if(ischecked){
		//黑色
    	redcolor = 0;
    	greencolor = 0;
    	bluecolor = 0;
    }else{
    	//灰色
    	cancel_choosed_color = 85;
    	
    	//将取消勾选的参数置为灰色,默认颜色----------------------------------------------------------
    	var cancel_choosed_paramName = $(this).attr('value');
    	cancel_choosed_paramNameTempArr = [];
    	cancel_choosed_paramNameTempArr = cancel_choosed_paramName.split(";");
    	if(cancel_choosed_paramNameTempArr.length>2){
    		var cancel_choosed_param_fontColor = "rgba("+cancel_choosed_color+","+cancel_choosed_color+","+cancel_choosed_color+",1)";
    		$("."+cancel_choosed_paramNameTempArr[2]+"_class").css({"color":cancel_choosed_param_fontColor});
    	}
    	//将取消勾选的参数置为灰色----------------------------------------------------------
    }
	
	//包含选过的参数
	paramsNameStr = paramsNameStr.substring(0,paramsNameStr.length-1);		
	//如果没有勾选参数，清除图表上方的参数名，清除勾选框,反之，加上勾选框
	clearParamsTitle(paramsNameStr);
	
	
	//获取该job计算后的模型数据
	var req_jobId = $("#jobId").val();
	//显示遮罩层    
	show_zhezhao_mask();
	//显示加载中提示
    show_loading_tips();
	
    //不是全选，也不是空选
	var isAllCsvFlag = '2'; 
	//是否有勾选全选checkbox
	if(isChoosed_CheckAll_CheckBox_Tag()){
		isAllCsvFlag = '1';
	}
	//是否勾选参数操作
	var isChooseParamFlag = '1';
	//获取勾选的文件名
	var csvFileNamesStr = getCheckedFiles('',isAllCsvFlag);
	//获取服务端相关的参数，csv文件数据，并绘图
	getParamsArrDataToChart(req_jobId,keyPointName,paramsNameStr,isAllCsvFlag,csvFileNamesStr,isChooseParamFlag);
	console.log('ok');
	
});

//获取服务端相关的参数，csv文件数据，并绘图
function getParamsArrDataToChart(req_jobId,keyPointName,paramsNameStr,isAllCsvFlag,csvFileNamesStr,isChooseParamFlag){
	//isAllCsvFlag:0,空选；isAllCsvFlag:1,全选；isAllCsvFlag:2不是全选，也不是空选	
	//isChooseParamFlag，是否勾选参数操作
		
	//
	$.ajax({
		type: "POST",
		url: model_keypoint_paramInfo_locate_url+'/analysis/modelResultShow/getParamsArrData',
    	data: {jobId:req_jobId,keyPointName:keyPointName,paramName:paramsNameStr,isAllCsvFlag:isAllCsvFlag,csvFileNamesStr:csvFileNamesStr,isChooseParamFlag:isChooseParamFlag},
		dataType:'json',
		cache: false,
		success: function(data){
			//隐藏遮罩层  
		    hide_zhezhao_mask();
		  	//隐藏加载中提示
		    hide_loading_tips();
			if(data.status=='1001'){									
				//获取result_paramsArr_data
				var result_paramsArr_data = data.data;
				//绘图
				if(isChooseParamFlag==1||isChooseParamFlag=='1'){
					req_refresh_chart(result_paramsArr_data);
				}else{
					req_refresh_chart_by_condition(result_paramsArr_data,csvFileNamesStr);
				}

				oDateAlert('绘图成功！');
			}else if(data.status=='1002'){
				oDateAlert('没有数据，请重新请求任务');
				console.log('没有数据，请重新请求任务');
				return;
			}else if(data.status=='1003'){
				oDateAlert('没有数据，请重新请求任务！');
				console.log('没有数据，请重新请求任务！');
				return;
			}else if(data.status=='1004'){
				oDateAlert('Id为空，请重新请求任务');
				console.log('Id为空，请重新请求任务');
				return;
			}else if(data.status=='1005'){
				oDateAlert('jobId为空，请重新请求任务');
				console.log('jobId为空，请重新请求任务');
				return;
			}
			console.log('成功');
		}
	});
}



/*模拟alert*/
function oDateAlert(txt){
	$('#date_alert').html(txt);
 	$('#date_alert').show();
	setInterval(function(){
		$('#date_alert').fadeOut();
	},3000);
}



//颜色随机数
	var redcolor = 0;
	var bluecolor = 0;
	var greencolor = 0;
	function getColorNum(linei){
		redcolor = 0;
		bluecolor = 0;
		greencolor = 360;
		redcolor = redcolor + parseInt(linei*30);
		bluecolor = bluecolor + parseInt(linei*180);
		greencolor = greencolor - parseInt(linei*30);
		if(redcolor>=360){
			redcolor=0;
		}
	if(bluecolor>=360){
		bluecolor=0;
		}
	if(greencolor<=0){
		greencolor=360;
	}
	}
	


	//如果没有勾选参数，清除图表上方的参数名，清除勾选框,反之，加上勾选框
	function clearParamsTitle(choose_paramsNameStr){
		if(choose_paramsNameStr.length==0||choose_paramsNameStr==''||choose_paramsNameStr==','||choose_paramsNameStr==undefined||choose_paramsNameStr=="undefined"){
			for(var i=0;i<3;i++){
				$(parent.window.frames["modelResultShowDetail"].document).find(".showParamName_div"+i).empty();
			}
			//清除勾选框
			$("#checkAll").attr("checked",this.checked==true?false:false);
			$('[name=checkbox_name_file]:checkbox').each(function(){
				$(this).attr("checked",this.checked==true?false:false);
			});
		}else{
			//$("#checkAll").attr("checked",this.checked==true?true:true);
			/* $('[name=checkbox_name_file]:checkbox').each(function(index,obj){
				    		
    	}); */
		}
	}
	
	//取消勾选参数
	function clear_tbody_params_name(){
		//
		$("#contentTable2 tr").find("td:first input:checkbox").each(function () {  
			$(this).attr("checked",this.checked==true?false:false);
	        
		});
	}
	
	//触发清空右边图形的点击事件
	function toggleClearChartClickEvent(){
		//
		$(parent.window.frames["modelResultShowDetail"].document).find("#clearChartClickEvent_a").click();
	}
	
	
	//获取已勾选的JobId_modelName_paramsName
	function getJobId_modelName_paramsName(){
		//获取该job计算后的模型数据
		var req_jobId = $("#jobId").val();
		
		var clicked_checkboxValue_param = '';
		var tbodyObj = document.getElementById('tbody_params_name');  
	        
	    console.log('------------checkbox choose--start---------------');
	    $("#contentTable2 tr").find("td:first input:checkbox").each(function () {  
	        var ischecked = $(this).prop("checked");  
	        if(ischecked){
	        	var checkboxValue = $(this).attr('value');
	        	clicked_checkboxValue_param += checkboxValue+',';
	        }
	        
		});
	    console.log('------------checkbox choose--end---------------');
		
		var trclassArr = clicked_checkboxValue_param.substring(0,clicked_checkboxValue_param.length-1).split(",");
		var keyPointName = '';
		choosed_paramsNameStr = '';
		
		for(var i=0;i<trclassArr.length;i++){
			var lineId = trclassArr[i];
			//除去 selected 标签
			var fidStr = lineId.split(';');
			//获取关键点名
			keyPointName = fidStr[0];
			var linei = fidStr[1];
			choosed_paramsNameStr+=fidStr[2]+',';
		}
		var choosedData = {"req_jobId":req_jobId,"keyPointName":keyPointName,"paramsNameStr":paramsNameStr};
		return choosedData;		
	}

	//是否有勾选全选checkbox
	function isChoosed_CheckAll_CheckBox_Tag(){
		var ischecked = $('#checkAll').prop("checked");  
		if(ischecked)
			return true;
		else
			return false;
	}