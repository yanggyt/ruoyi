var myChartApi_locat = (window.location+'').split('/'); 
var myChartApi_locate_url = '';
$(function(){
	myChartApi_locat =  myChartApi_locat[0]+'//'+myChartApi_locat[2]+'/'+myChartApi_locat[3];
	myChartApi_locate_url = myChartApi_locat + '/a';
});


//Echarts绘图
function echart_mychart(xaxis_data,yaxis_mySeries,clicked_columnNamesArr,main_chart_div_k){
	// 基于准备好的dom，初始化echarts实例
    //var myChart = echarts.init(document.getElementById('main_chart'));
	var myChart = echarts.init($('#'+main_chart_div_k)[0]);
	
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: ''
        },
        tooltip: {
        	trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            animation: false,
	            label: {
	                backgroundColor: '#0D4286'
	            }
	        }
        },
        dataZoom: [
          {
	        show: true,
	        realtime: true,
	        start: 0,
	        end: 100,
	        // backgroundColor:'#d'
	        textStyle: {
	            color: "#ffffff"
	        }
          },
	       {
		        type: 'inside',
		        realtime: true,
		        start: 5,
		        end: 85
		    }
	    ],
	    grid: {
	        top: '10%',
	        left: '2%',
	        right: '1%',
	        bottom: '14%',
	        containLabel: true
	    },
        legend: {
        	show:false,
            data:clicked_columnNamesArr
        },
        xAxis: {
            data: xaxis_data
        },
        yAxis: {},              
	    series: yaxis_mySeries
    };
    
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    //自适应屏幕
    window.onresize = function(){
    	myChart.resize();
    }

    //将图形对象存进数组，myLineChartArr 是全局变量
    myLineChartArr.push(myChart);
  
}

//绘图，result_paramsArr_data = data.data
function req_refresh_chart(result_paramsArr_data){
	var paramAllData = [];	
	
	//将模型返回的数据临时存储
	models_result_paramsArr_data = [];
	models_result_paramsArr_data = result_paramsArr_data;
	var result_paramsArr_dataLen = result_paramsArr_data.length;
	
	//每个图表容器的高度
	chart_containerHeight = parseInt(page_total_height/result_paramsArr_dataLen);
	
	//注销之前的图形缓存，1个以上的图表	
	disposeHistoryChatObjArr();

	//清空
	myLineChartArr = [];
	console.log('注销之前的图形缓存成功');
	
	//
	for(var k=0;k<result_paramsArr_data.length;k++){ //所有选中的参数
		//画图中的series参数
		var mySeries = [];
		//某个参数包含的数据
		var chartDataSets = [];
		var result_params_data = result_paramsArr_data[k];
		var req_param_data = result_params_data.req_param_data;
		//参数名
		var paramName_k = req_param_data.paramName;	
		paramName_kArr = [];
		paramName_kArr.push(paramName_k);
		//参数包含的数据
		paramAllData = req_param_data.paramAllData;
				
		//选择的的参数的字体颜色
		var param_fontColor = "rgba("+redcolor+","+greencolor+","+bluecolor+",1)";
		$("."+req_param_data.paramName+"_class").css({"color":param_fontColor});
		//图形对象div
		main_chart_div_k = '';
		if(result_paramsArr_dataLen==1) //只有一个参数
			main_chart_div_k = addOneChart(0,'onlyone',chart_containerHeight,paramName_k,param_fontColor);
		else  //多个参数
			main_chart_div_k = addOneChart(k,'',chart_containerHeight,paramName_k,param_fontColor);
		
		//每一个csv中计算出来的x轴数据
		var paramValue_jArr_X = [];
		//获取x轴数据时需要判断该值
		loop_paramAllData_temp_i = -1;
		
		//遍历该参数中的所有曲线		
		for(var i=0;i<paramAllData.length;i++){
			
			var paramObj = paramAllData[i];
			var paramName = paramObj.paramName;
			//一个csv的计算后的数据
			var paramValue = paramObj.paramValue;						
			//文件名序号
			fileIndex = paramObj.fileIndex;
			//文件名
			var paramMappingFileName = paramObj.fileName;
			
			
			
			//每一个csv中计算出来的y轴数据
			var paramValue_jArr_Y = [];			
			var paramValueArr = paramValue.split(';');


			//是否有勾选全选checkbox,没有勾选
			if(!isChoosed_CheckAll_CheckBox()){
				//只绘一条线，不管有没有值
				if(i==0){
					//有值的参数
					paramValue_jArr_Y = paramValueArr;
					//
					mySeries_data_i = {"name":'csv'+parseInt(fileIndex),"type":"line","data":paramValue_jArr_Y,"yAxisIndex":0};
					//图表数据集
					mySeries.push(mySeries_data_i);		
					paramValue_jArr_Y = [];
					
					//取消勾选所有csv文件的checkbox
					$('[name=checkbox_name_file]:checkbox').each(function(){
		        		$(this).attr("checked",this.checked==true?false:false);
		        	});
					//获取选中的第一个有值的csv文件的checkbox value
					getFirstChoosedFileCheckbox(paramMappingFileName);
					console.log('获取某个图表数据集成功1');
					//
				}
			}
			
			
			//获取每个参数x轴数据------------------------------------------
			if(paramValue==''||paramValue==""||paramValue==null){
				continue;
			}else{
				if(loop_paramAllData_temp_i==-1){
					loop_paramAllData_temp_i = i;
					if(loop_paramAllData_temp_i>-1){
						//有值的参数
						for(var ii=0;ii<paramValueArr.length;ii++){
							//只计算一个csv的x轴数据
							paramValue_jArr_X.push(ii);
						}						
					}
				}								
			}							
			//获取每个参数x轴数据------------------------------------------											
			paramValue_jArr_Y = paramValueArr;

			//是否有勾选全选checkbox,有勾选
			if(isChoosed_CheckAll_CheckBox()){
				//
				mySeries_data_i = {"name":'csv'+parseInt(fileIndex),"type":"line","data":paramValue_jArr_Y,"yAxisIndex":0};
				//图表数据集
				mySeries.push(mySeries_data_i);						
				//获取选中的第一个有值的csv文件的checkbox value
				getFirstChoosedFileCheckbox(paramMappingFileName);
				console.log('获取某个图表数据集成功2');
			}
			
		}
		
		//Echarts 绘图
		echart_mychart(paramValue_jArr_X,mySeries,paramName_kArr,main_chart_div_k);	

	}
}


//新增chart图表
function addOneChart(i,onlyoneFlag,chart_containerHeight,showParamName,param_fontColor){
		
	var charthtml = '';
		//$(".chart_container").css({"height":""+chart_containerHeight+"px","margin-top":"10px"});  		
  	var $width=$(".chart_container").width();			
	var $height=$(".chart_container").height(); 
	//图形对象div
	main_chart_div_k = '';	
	if(i==0){
		$(".span7").empty();
		//显示参数名
		showParamNameHtml = "<div class=\"showParamName_div"+i+"\"><span class=\"showParamName_style"+i+"\">参数名："+showParamName+"</span></div";
  		$(".span7").append(showParamNameHtml);
  		$(".showParamName_div"+i).css({"text-align":"center"});
		$(".showParamName_style"+i).css({"color":param_fontColor,"text-align":"center"});
		
  		var first_charthtml = '';
  		first_charthtml += "<div class=\"chart_container\">";	
  		if(onlyoneFlag=='onlyone')
  			first_charthtml += "<div id=\"main_chart\" style=\"height:400px;\"></div>";
  		else
  			first_charthtml += "<div id=\"main_chart\" style=\"height:400px;\"></div>";
  		first_charthtml += "</div>";
  		$(".span7").append(first_charthtml);
  		//改成不设高度
  		//$(".chart_container").css({"margin-top":"2px"});
  		//如果只有一个图表，设置高度为780
  		if(onlyoneFlag=='onlyone')
  			$(".chart_container").css({"height":""+page_total_height});
  		
		//
  		$("#main_chart").width=$width;
		$("#main_chart").height=$height;
		
		main_chart_div_k = 'main_chart';
		return main_chart_div_k;
  	} 	  		
	if(i>0){	
		//显示参数名
		showParamNameHtml = "<div class=\"showParamName_div"+i+"\"><span class=\"showParamName_style"+i+"\">参数名："+showParamName+"</span></div";
  		$(".span7").append(showParamNameHtml);
  		$(".showParamName_div"+i).css({"text-align":"center","margin-top":"20px"});
		$(".showParamName_style"+i).css({"color":param_fontColor,"text-align":"center"});
		
		charthtml += "<div class=\"chart_container\">";			
	  	charthtml += "<div id=\"main_chart"+i+"\" style=\"height:400px;\"></div>";
	  	charthtml += "</div>";
	  	$(".span7").append(charthtml);
	 	 //改成不设高度
  		//$(".chart_container").css({"margin-top":"2px"});	  		
		//
	  	$("#main_chart"+i).width=$width;
		$("#main_chart"+i).height=$height;
		main_chart_div_k = 'main_chart'+i;
		return main_chart_div_k;
	}			
}


	
//
function req_refresh_chart_by_condition(result_paramsArr_data,filesNameStr){
	var paramAllData = [];	
	if(filesNameStr!='allFiles'){//如果不是选择的所有文件
		//解析filesNameArr，获取新的result_paramsArr_data---------------------
		result_paramsArr_data = getNewresult_paramsArr_data(result_paramsArr_data,filesNameStr);
		//解析filesNameArr，获取新的result_paramsArr_data---------------------
	}
	
	var result_paramsArr_dataLen = result_paramsArr_data.length;
	
	//注销之前的图形缓存，1个以上的图表	
	disposeHistoryChatObjArr();
	
	//每个图表容器的高度
	chart_containerHeight = parseInt(page_total_height/result_paramsArr_dataLen);
	//
	for(var k=0;k<result_paramsArr_data.length;k++){ //所有选中的参数
		//画图中的series参数
		var mySeries = [];

		//某个参数包含的数据
		var chartDataSets = [];
		var result_params_data = result_paramsArr_data[k];
		var req_param_data = result_params_data.req_param_data;
		//参数名
		var paramName_k = req_param_data.paramName;	
		paramName_kArr = [];
		paramName_kArr.push(paramName_k);
		//参数包含的数据
		paramAllData = req_param_data.paramAllData;
				
		//选择的的参数的字体颜色
		var param_fontColor = "rgba("+redcolor+","+greencolor+","+bluecolor+",1)";
		$("."+req_param_data.paramName+"_class").css({"color":param_fontColor});
		//图形对象div
		main_chart_div_k = '';
		if(result_paramsArr_dataLen==1) //只有一个参数
			main_chart_div_k = addOneChart(0,'onlyone',chart_containerHeight,paramName_k,param_fontColor);
		else  //多个参数
			main_chart_div_k = addOneChart(k,'',chart_containerHeight,paramName_k,param_fontColor);
		
		//每一个csv中计算出来的x轴数据
		var paramValue_jArr_X = [];
		//获取x轴数据时需要判断该值
		loop_paramAllData_temp_i = -1;
		
		//遍历该参数中的所有曲线		
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
				//oDateAlert('该文件无结果数据');
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

			//
			mySeries_data_i = {"name":'csv'+parseInt(fileIndex),"type":"line","data":paramValue_jArr_Y,"yAxisIndex":0};
			//图表数据集
			mySeries.push(mySeries_data_i);
			console.log('获取某个图表数据集成功');
		}
		
		//Echarts 绘图
		echart_mychart(paramValue_jArr_X,mySeries,paramName_kArr,main_chart_div_k);	

	}
}

//根据勾选的csv文件，从所有csv文件中选出勾选的文件数据
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


//获取选中的第一个csv文件的checkbox value
function getFirstChoosedFileCheckbox(paramMappingFileName){
	$('[name=checkbox_name_file]:checkbox').each(function(index,obj){
		choosed_file_checkbox_value = '';
		choosed_file_checkbox_value = $(this).attr('value');
		choosed_file_checkbox_valueArrTemp = [];
		choosed_file_checkbox_valueArrTemp = choosed_file_checkbox_value.split(";");
		choosed_fileName = '';
		if(choosed_file_checkbox_valueArrTemp!=''&&choosed_file_checkbox_valueArrTemp.length>2){
			choosed_fileName = choosed_file_checkbox_valueArrTemp[2];
		}
		if(choosed_fileName == paramMappingFileName){
			$(this).attr("checked",this.checked==true?true:true);
		}
	});
}



//注销之前的图形缓存，1个以上的图表	
function disposeHistoryChatObjArr(){
	//注销之前的图形缓存，1个以上的图表	
	for(var charti=0;charti<myLineChartArr.length;charti++){
		myLineChartArr[charti].dispose();		
	}
}

//是否有勾选全选checkbox
function isChoosed_CheckAll_CheckBox(){
	var ischecked = $('#checkAll').prop("checked");  
	if(ischecked)
		return true;
	else
		return false;
}