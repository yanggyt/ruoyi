function drawCharts(result_paramsArr_data,filesNameStr){					
	var paramAllData = [];	
	if(filesNameStr!='allFiles'){//如果不是选择的所有文件
		//解析filesNameArr，获取新的result_paramsArr_data---------------------
		result_paramsArr_data = getNewresult_paramsArr_data(result_paramsArr_data,filesNameStr);
		//解析filesNameArr，获取新的result_paramsArr_data---------------------
	}
		
	var result_paramsArr_dataLen = result_paramsArr_data.length;
	//每个图表容器的高度
	chart_containerHeight = parseInt(page_total_height/result_paramsArr_dataLen);
	//注销之前的图形缓存,图表1，初始图表
	myLineChart.destroy();
	//注销之前的图形缓存，2个以上的图表
	for(var charti=0;charti<myLineChartArr.length;charti++){
		myLineChartArr[charti].destroy();		
	}
	//清空
	myLineChartArr = [];
	console.log('注销之前的图形缓存成功');
	for(var k=0;k<result_paramsArr_data.length;k++){ //所有选中的参数
		//某个参数包含的数据
		var chartDataSets = [];
		var result_params_data = result_paramsArr_data[k];
		var req_param_data = result_params_data.req_param_data;
		//参数名
		var paramName_k = req_param_data.paramName;
				
		//参数包含的数据
		paramAllData = req_param_data.paramAllData;
		//自定义x轴范围---------------------------------------
		var paramName_k_x_min=0;
		var paramName_k_x_max=0;
		var hasCustomerXField = false;
		var paramValue_jArr_X_k_cus = [];
		for(var kk=0;kk<customer_x_field.length;kk++){
			customer_x_field_values = customer_x_field[kk];
			if(paramName_k==customer_x_field_values.set_paramName){
				if(customer_x_field_values.x_minValue!=''&&customer_x_field_values.x_minValue!="")
					paramName_k_x_min = customer_x_field_values.x_minValue
				if(customer_x_field_values.x_maxValue!=''&&customer_x_field_values.x_maxValue!="")
					paramName_k_x_max = customer_x_field_values.x_maxValue;
				paramValue_jArr_X_k_cus = customer_x_field_values.paramValue_jArr_X_k_cus;
				hasCustomerXField = true;
			}
		}
		//自定义x轴范围---------------------------------------
		//如果设置过x轴范围，则重新生成数据
		if(hasCustomerXField&&(paramName_k_x_min<paramName_k_x_max))
			paramAllData = get_Y_data_k_By_customer_X_Field(paramName_k_x_min,paramName_k_x_max,paramAllData);
		
		//自定义y轴范围数据------------------------------------
		var paramName_k_y_min = 0;
		var paramName_k_y_max = 0;		
		var hasCustomerYField = false;
		//遍历自定义y轴范围数据
		for(var i=0;i<customer_y_field.length;i++){
			var customer_y_fieldObj = customer_y_field[i];
			if(paramName_k==customer_y_fieldObj.set_paramName){
				if(customer_y_fieldObj.y_minValue!=''&&customer_y_fieldObj.y_minValue!="")
					paramName_k_y_min = customer_y_fieldObj.y_minValue;
				if(customer_y_fieldObj.y_maxValue!=''&&customer_y_fieldObj.y_maxValue!="")
					paramName_k_y_max = customer_y_fieldObj.y_maxValue;
				
				hasCustomerYField = true;
			}
		}
		//自定义y轴范围数据------------------------------------
		//获取图表的颜色
		//获取图表的颜色,models_keypoint_params_colorArr 存放参数的颜色序号 数组
		for(var i=0;i<models_keypoint_params_colorArr.length;i++){
			models_keypoint_params_colorObj = models_keypoint_params_colorArr[i];
			if(paramName_k==models_keypoint_params_colorObj.paramName_k)
				getColorNum(models_keypoint_params_colorObj.color_index);
		}
		//选择的的参数的字体颜色
		var param_fontColor = "rgba("+redcolor+","+greencolor+","+bluecolor+",1)";
		$("."+req_param_data.paramName+"_class").css({"color":param_fontColor});		
		//初始化图表------------------
		var ctxk;		
		if(result_paramsArr_dataLen==1) //只有一个参数
			ctxk = addOneChart(0,'onlyone',chart_containerHeight,paramName_k,param_fontColor);
		else  //多个参数
			ctxk = addOneChart(k,'',chart_containerHeight,paramName_k,param_fontColor);
		var myLineChartk;
		//初始化图表------------------
		//每一个csv中计算出来的x轴数据
		var paramValue_jArr_X = [];		
		//获取x轴数据时需要判断该值
		loop_paramAllData_temp_i = -1;
		
		for(var i=0;i<paramAllData.length;i++){
			var paramObj = paramAllData[i];
			var paramName = paramObj.paramName;
			//一个csv的计算后的数据
			var paramValue = paramObj.paramValue;	
			//文件名序号
			fileIndex = paramObj.fileIndex;
			
			//每一个csv中计算出来的y轴数据
			var paramValue_jArr_Y = [];
			var paramValueArr = paramValue.split(';');
			
			//获取每个参数x轴数据------------------------------------------
			if(paramValue==''||paramValue==""||paramValue==null){
				continue;
			}else{
				if(loop_paramAllData_temp_i==-1){
					loop_paramAllData_temp_i = i;
					if(loop_paramAllData_temp_i>-1){
						for(var ii=0;ii<paramValueArr.length;ii++){
							//只计算一个csv的x轴数据
							paramValue_jArr_X.push(ii);
						}
					}
				}								
			}							
			//获取每个参数x轴数据------------------------------------------		
			
			paramValue_jArr_Y = paramValueArr;
			
			//填充颜色
			var backgroundColor = "rgba("+redcolor+","+greencolor+","+bluecolor+",0.4)";
			var borderColor = "rgba("+redcolor+","+greencolor+","+bluecolor+",1)";
			var pointBorderColor = "rgba("+redcolor+","+greencolor+","+bluecolor+",1)";
			//图表数据对象
			var chartDataObj;
			if(hasCustomerYField&&(paramName_k_y_min<paramName_k_y_max)){
				//用户自定义y轴范围
				chartDataObj = {
						label : 'csv'+parseInt(fileIndex),
			            //backgroundColor: backgroundColor,
			            backgroundColor : 'rgba(0,0,0,0.0)',
                        borderColor: borderColor,
                        pointBorderColor: pointBorderColor,
                        pointHoverBackgroundColor: pointBorderColor,
                        pointHoverBorderColor: pointBorderColor,
                        pointHoverBackgroundColor: pointBorderColor,
                        pointBorderWidth:1,
                        yAxisID: 'customer_y_ID'+k,
						data : paramValue_jArr_Y
					};
			}else{
				chartDataObj = {
						label : 'csv'+parseInt(fileIndex),
			            //backgroundColor: backgroundColor,
			            backgroundColor : 'rgba(0,0,0,0.0)',
                        borderColor: borderColor,
                        pointBorderColor: pointBorderColor,
                        pointHoverBackgroundColor: pointBorderColor,
                        pointHoverBorderColor: pointBorderColor,
                        pointHoverBackgroundColor: pointBorderColor,
                        pointBorderWidth:1,	 
						data : paramValue_jArr_Y
					};
				
			}
								
			
			//图表数据集
			chartDataSets.push(chartDataObj);
			console.log('获取某个图表数据集成功');
		}
		if(hasCustomerXField&&(paramName_k_x_min<paramName_k_x_max)){
			paramValue_jArr_X = paramValue_jArr_X_k_cus;
		}
		//图表数据
		var showchartdata = {
				labels : paramValue_jArr_X,
				datasets : chartDataSets
		}
			
		
		//只勾选一个参数，只有一个图表
		if(k==0){	
			
			//画图表-生成图标图形------------------------------
			if(hasCustomerYField&&(paramName_k_y_min<paramName_k_y_max)){//该参数x轴范围已经被自定义过
				myLineChart = new Chart(ctx, {
				    type: 'line',
				    data: showchartdata,						    
				    options: {
					    scales: {
					    	yAxes: [{
						        id: 'customer_y_ID'+k,
						        type: 'linear',
						        position: 'left',
						        ticks: {
						            max: parseInt(paramName_k_y_max),
						            min: parseInt(paramName_k_y_min)
						          } 
						      }]
					      
					    },
						legend: {
				            display: false
				        }
					}
					
				});
			}else{
				myLineChart = new Chart(ctx, {
				    type: 'line',
				    data: showchartdata,						    
				    options : {
				    	legend: {
				            display: false
				        }
				    }//'top',
				    
				});
			}
			console.log('绘制第一个图表成功');					
			//画图表-结束------------------------------
		}else{
			//画图表-生成图标图形------------------------------
			if(hasCustomerYField&&(paramName_k_y_min<paramName_k_y_max)){//该参数x轴范围已经被自定义过
				myLineChartk = new Chart(ctxk, {
				    type: 'line',
				    data: showchartdata,						    
				    options: {
					    scales: {
					    	yAxes: [{
						        id: 'customer_y_ID'+k,
						        type: 'linear',
						        position: 'left',
						        ticks: {
						        	max: parseInt(paramName_k_y_max),
						            min: parseInt(paramName_k_y_min)
						          } 
						      }]
					      
					    },
						legend: {
					        display: false
					    }
					}
					
				});
				console.log('绘制自定义x轴范围，某个图表成功');
			}else{
				myLineChartk = new Chart(ctxk, {
				    type: 'line',
				    data: showchartdata,						    
				    options : {
				    	legend: {
				            display: false
				        }
				    }//'top',
				    
				});
				
				console.log('绘制某个图表成功');
			}
			myLineChartArr.push(myLineChartk);				
			//画图表-结束------------------------------
		}
		
	}
}	
	
	//
	function getNewresult_paramsArr_data(result_paramsArr_data,filesNameStr,allClickFlag){
		var result_paramsArr_dataNew = [];
		var filesNameArr = filesNameStr.split(",");
		var result_paramsArr_dataLen = result_paramsArr_data.length;
		for(var k=0;k<result_paramsArr_dataLen;k++){
			var result_params_data = result_paramsArr_data[k];
			var req_param_data = result_params_data.req_param_data;
			var paramAllData = req_param_data.paramAllData;	
			var result_params_dataNew;
			var req_param_dataNew;
			var paramAllDataNew=[];
			for(var i=0;i<filesNameArr.length;i++){
				for(var j=0;j<paramAllData.length;j++){
					var paramObj = paramAllData[j];
					var paramName = paramObj.paramName;
					//一个csv的计算后的数据
					var fileName = paramObj.fileName;
					if(filesNameArr[i]==fileName){
						paramAllDataNew.push(paramObj);
					}
				}
			}			
			req_param_dataNew = {"paramAllData":paramAllDataNew,"keyPointName":req_param_data.keyPointName,"paramName":req_param_data.paramName,"param_X_Length":req_param_data.param_X_Length,"param_from":req_param_data.param_from,"param_to":req_param_data.param_to};
			result_params_dataNew = {"req_param_data":req_param_dataNew}
			result_paramsArr_dataNew.push(result_params_dataNew);
		}
		
		return result_paramsArr_dataNew;
		
	}
	
	//根据自定义x轴的范围获取指定数量的y轴数据
	function get_Y_data_k_By_customer_X_Field(paramName_k_x_min,paramName_k_x_max,paramAllData){		
		var paramAllDataNew=[];
		for(var j=0;j<paramAllData.length;j++){
			var paramObj = paramAllData[j];
			var paramName = paramObj.paramName;
			var paramValue = paramObj.paramValue;
			paramValueArr = paramValue.split(";");
			var paramValueNew = '';
			for(var m=paramName_k_x_min;m<paramName_k_x_max;m++){
				paramValueNew=paramValueNew+paramValueArr[m]+";";
			}
			paramValueNew = paramValueNew.substring(0, paramValueNew.length-1);
			paramObjNew = {"keyPointName":paramObj.keyPointName,"fileName":paramObj.fileName,"paramName":paramObj.paramName,"paramValue":paramValueNew};
			paramAllDataNew.push(paramObjNew);	
		}
		return paramAllDataNew;
	}
	
	

	//暂时不使用该函数--------------
  	//生成自定义的x轴数据，选择过的参数
	var customer_x_field_valuesArr = [];
  	//是否有参数被自定义过x轴范围，只要有一个，就为true
  	var isContainCusXFieldsTag = false;
	//获取每个参数的自定义x轴范围和显示的y轴数据
	function get_customer_x_field_k_values(paramName_k){
		var paramName_k_x_min=0;
		var paramName_k_x_max=0;
		var hasCustomerXField = false;
		for(var i=0;i<customer_x_field.length;i++){
			var customer_x_fieldObj = customer_x_field[i];
			if(paramName_k==customer_x_fieldObj.set_paramName){
				paramName_k_x_min = customer_x_fieldObj.x_minValue;
				paramName_k_x_max = customer_x_fieldObj.x_maxValue;
				hasCustomerXField=true;
			}
		}
		
		//如果该参数没有被自定义过x轴范围，则不改变什么，如果有，则同时改变y轴要显示的数据，并重新绘图
		//只要有一个参数被自定义过x轴范围，就要重新绘图，所以，只要有一个参数被自定义过，返回false，则continue,执行下一个参数，继续判断下一个参数是否也被自定义过x轴范围，获取该x轴范围
		if(hasCustomerXField){
			//生成自定义的x轴数据
			paramValue_jArr_X_k_cus = [];
			for(var i=paramName_k_x_min;i<paramName_k_x_max;i++){
				paramValue_jArr_X_k_cus.push(i);
			}
			customer_x_field_k_values = {"paramName_k":paramName_k,"paramValue_jArr_X_k_cus":paramValue_jArr_X_k_cus,"paramName_k_x_min":paramName_k_x_min,"paramName_k_x_max":paramName_k_x_max};
			customer_x_field_valuesArr.push(customer_x_field_k_values);
			isContainCusXFieldsTag = true;
			//如果返回isContainCusXFieldsTag = true，则continue,执行下一个参数，继续判断下一个参数是否也被自定义过x轴范围
		}
		//如果返回isContainCusXFieldsTag = false，则不改变什么
			
	}
