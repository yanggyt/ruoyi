var myChartApi_locat = (window.location+'').split('/'); 
var myChartApi_locate_url = '';
$(function(){
	myChartApi_locat =  myChartApi_locat[0]+'//'+myChartApi_locat[2]+'/'+myChartApi_locat[3];
	myChartApi_locate_url = myChartApi_locat + '/a';
});

var myChart = echarts.init(document.getElementById('main_chart'));
//Echarts绘图
function echart_mychart(xaxis_data,yaxis_mySeries,clicked_columnNamesArr,allYAxis_scale,myChartPropertySettingJson_New){
	//基于准备好的dom，初始化echarts实例
	myChart = null;
	
	//基于准备好的dom，初始化echarts实例
	myChart = echarts.init(document.getElementById('main_chart'));
	//y轴个数数组
	yAxisIndexArr = [];
	for(var yAxis_scale_i=0;yAxis_scale_i<allYAxis_scale.length;yAxis_scale_i++){
		yAxisIndexArr.push(yAxis_scale_i);
	}
	//全局颜色，会变
	//var whole_chartLine_colorArr = ['#c23531','#2f4554','#61a0a8','#d48265','#91c7ae','#749f83','#ca8622','#bda29a','#6e7074','#546570','#c4ccd3'];
	var whole_chartLine_colorArr = ['#3784D4','#0DCB5F','#9AD873','#5DBBE1','#75D3DF','#F36059','#FA9253','#F4D371','#D1EA85','#546570','#c4ccd3'];	
	//默认颜色，10个，不变
	var whole_chartLine_colorArrDefault = whole_chartLine_colorArr;
	//

	//默认只有12种颜色设置，如果参数少于12个，则获取参数的序号，将自定义的颜色取代默认的参数颜色，如果参数多于12个，则增加颜色
	whole_chartLine_colorArr = get_whole_chartLine_colorArr(yaxis_mySeries,thisParam_linePropertyArr,whole_chartLine_colorArr,whole_chartLine_colorArrDefault);
	//如果有设置过新的图形标题
	var customer_chart_title = '';
	if(refreshChartTitleFlag){
		customer_chart_title = setChartTitleJson.chart_xAxis_title;
	}
	
		
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: setChartTitleJson.chart_yAxis_title,
            textStyle: {
            	color: setChartTitleJson.chart_yAxis_color,
            	fontFamily: 'Microsoft YaHei'
            },
            /*left:'49%',
            top: '50%'*/
        },
        color:whole_chartLine_colorArr,
        tooltip: {
        	show: true,
        	trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            animation: false,
	            label: {
	                backgroundColor: '#0D4286'
	            }
	        },
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            dataView: {readOnly: false},
	            magicType: {type: ['line', 'bar']},
	            restore: {},
	            saveAsImage: {}
	        }
        },
        dataZoom: [
          {	        
	        // 这个dataZoom组件，默认控制x轴。slider 表示滑动条型
            type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
            xAxisIndex: 0,
	        start: 0,
	        end: 100,
	        // backgroundColor:'#d'
	        textStyle: {
	            color: "#555"
	        }
          },
          {
        	  //在坐标系内进行拖动，以及用滚轮（或移动触屏上的两指滑动）进行缩放  
        	  type: 'inside',
              xAxisIndex: 0,
              start: 0,
              end: 5
           },
           {
               type: 'slider',
               yAxisIndex: yAxisIndexArr,
               start: 0,
               end: 100
           },
           {
               type: 'inside',
               yAxisIndex: yAxisIndexArr,
               start: 0,
               end: 100
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
            data:clicked_columnNamesArr
        },
        xAxis: {
            data: xaxis_data,
            name: setChartTitleJson.chart_xAxis_title,
            nameLocation: 'center',
            nameTextStyle: {
            	fontWeight: 'bolder',
            	fontSize: 18,
            	verticalAlign: 'bottom',
            	padding: [13, 0, 0, 0],
            	color: setChartTitleJson.chart_xAxis_color,
            	fontFamily: 'Microsoft YaHei'
            }
        },
        yAxis: allYAxis_scale,              
	    series: yaxis_mySeries
    };
    
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.onresize = function(){
    	myChart.resize();
    }

    //将图形对象存进数组，myLineChartArr 是全局变量
    myLineChartArr.push(myChart);

}


		//根据设置的图形属性，请求刷新图形
		function req_refresh_chart(choosed_flightCsv_Id,returnDataJson){
			
			//ajax---------------------
			oDateAlert('正在处理中，请稍后...');
			show_zhezhao_mask();//显示遮罩层
		
			//注销之前的图形缓存，1个以上的图表	
			disposeHistoryChatObjArr();
			
			var chartData = {};
			chartData = returnDataJson;
			if(chartData=={}||chartData=="{}"||chartData==undefined){
				oDateAlert('该csv文件数据为空，请另选csv文件');
				console.log('该csv文件数据为空，请另选csv文件');
				return;
			}

			//选择的图表列名
			var clicked_columnNamesArr = chartData.clicked_columnNamesArr;
			
			//选择的字符串列名
			var clicked_string_columnNamesArr = chartData.clicked_string_columnNamesArr;
			//该列行数
			var countArr = chartData.chartXData;
	
			//新的数据集，多csv文件的//新的数据集，多csv文件的
			var col_chartYDataEachColumnsNameJsonAll = chartData.col_chartYDataEachColumnsNameJsonAll;
			//所有点过的列数据,显示用
			var chartDataSets = [];
			//画图中的series参数
			var mySeries = [];
			//
			var allYAxis_scale = [];
			//图形属性信息-开始-------------------------------------------------------------
			myChartPropertySettingJson_New = {};
			myChartPropertySettingJson_New = getMyChartPropertySetting(myInfoChartpropertysettingJson);
			//图形属性信息-结束-------------------------------------------------------------
			if(clicked_columnNamesArr!=null&&clicked_columnNamesArr.length>0){						
				var columnsNameStrArr = [];
				columnsNameStrArr = clicked_columnNamesArr;
				var columnsNameStrArrSize = clicked_columnNamesArr.length;
				
				for(var j=0;j<columnsNameStrArrSize;j++){   //遍历每个参数
					//参数名
					columnsNameStrArr_j = '';
					columnsNameStrArr_j = columnsNameStrArr[j];
					
					//var col_chartYData = clicked_chartYData[j].colData;					
					for(var csv_j=0;csv_j<col_chartYDataEachColumnsNameJsonAll.length;csv_j++){   //遍历csv中参数数据
						//j 代表一个参数序号，而不是一个csv中的参数序号，如果有返回多个csv文件数据，不要这样做---------------------------
						//
						var col_chartYDataEachColumnsNameJson = {};						
						col_chartYDataEachColumnsNameJson = col_chartYDataEachColumnsNameJsonAll[csv_j];
						if(col_chartYDataEachColumnsNameJson.columnName==columnsNameStrArr_j){    //如果有相同的参数，则进入
							col_chartYDataEachColumnsNameJsonArr = [];
							col_chartYDataEachColumnsNameJsonArr = col_chartYDataEachColumnsNameJson.col_chartYDataEachColumnsNameJsonArr;
							//新的数据集，多csv文件的，一个csv文件的参数,多个同名参数数据
							for(var param_i=0;param_i<col_chartYDataEachColumnsNameJsonArr.length;param_i++){
								//新的数据集，多csv文件的，一个csv文件的参数,一个参数数据
								col_chartYDataEachCsv = {};
								col_chartYDataEachCsv = col_chartYDataEachColumnsNameJsonArr[param_i];
								col_chartYDataEachCsv_colName = '';
								col_chartYDataEachCsv_colData = [];
								col_chartYDataEachCsv_colName = col_chartYDataEachCsv.colName;
								col_chartYDataEachCsv_colData = col_chartYDataEachCsv.colData;
								
								//获取该参数的曲线的属性----------------------------------------------
								var thisParam_linePropertyJson = get_thisParam_lineProperty(columnsNameStrArr_j);
								var thisParam_lineWidth = '1';
								var thisParam_lineColor = '#000';
								if(thisParam_linePropertyJson=={}||thisParam_linePropertyJson=='{}'||thisParam_linePropertyJson==undefined||thisParam_linePropertyJson==''){
									//获取该参数的曲线的属性----------------------------------------------
									mySeries_data_i = {"name":col_chartYDataEachCsv_colName,"type":"line","data":col_chartYDataEachCsv_colData,"yAxisIndex":j,"lineStyle":{"width":1}};
								}else{
									if(thisParam_linePropertyJson.chartlinewidth!=''&&thisParam_linePropertyJson.chartlinewidth!=undefined){
										thisParam_lineWidth = thisParam_linePropertyJson.chartlinewidth;
									}
									if(thisParam_linePropertyJson.leftChartlinecolor!=''&&thisParam_linePropertyJson.leftChartlinecolor!=undefined){
										thisParam_lineColor = thisParam_linePropertyJson.leftChartlinecolor;
									}
									
									mySeries_data_i = {"name":col_chartYDataEachCsv_colName,"type":"line","data":col_chartYDataEachCsv_colData,"yAxisIndex":j,"lineStyle":{"width":parseInt(thisParam_lineWidth),"color":thisParam_lineColor}};//j 代表一个参数序号，而不是一个csv中的参数序号，如果有返回多个csv文件数据，不要这样做
									//mySeries_data_i = {"name":col_chartYDataEachCsv_colName,"type":"line","data":col_chartYDataEachCsv_colData,"yAxisIndex":j,"lineStyle":{"width":1}};
								}
								
								
								mySeries.push(mySeries_data_i);
								createOneyAxis_scale_position = '';
								//j 代表一个参数序号，而不是一个csv中的参数序号，如果有返回多个csv文件数据，不要这样做---------------------------
								if(j%2==0){
									createOneyAxis_scale_position = 'left';
								}else{
									createOneyAxis_scale_position = 'right';
								}
								//j 代表一个参数序号，而不是一个csv中的参数序号，如果有返回多个csv文件数据，不要这样做---------------------------
								//每个参数的y轴
								allYAxis_scale.push(createOneyAxis_scale(col_chartYDataEachCsv_colName,createOneyAxis_scale_position,j));//j 代表一个参数序号，而不是一个csv中的参数序号，如果有返回多个csv文件数据，不要这样做
							}
						}
						
					}
				
				}
				//Echarts 绘图
				echart_mychart(countArr,mySeries,clicked_columnNamesArr,allYAxis_scale,myChartPropertySettingJson_New);	
			}
			
			hide_zhezhao_mask();//关闭遮罩层

			//将字符串数据显示在表格
			var clicked_stringData = [];
			clicked_stringData = chartData.clicked_stringData;
			loadAll(clicked_string_columnNamesArr, clicked_stringData);
			if(clicked_stringData.length>0&&(clicked_columnNamesArr.length<clicked_string_columnNamesArr.length)){						
				oDateAlert('所选参数含有不可数值化的值,请查看表格！');
			}

			
		}
		
		//创建一个y轴刻度
		function createOneyAxis_scale(colName,createOneyAxis_scale_position,j){
			offset_size = 0;
			if(j%2==0){
				offset_size = 0-(j/2)*60;
			}else{
				offset_size = 0-((j-1)/2)*60;
			}
			if(j>6){
				oDateAlert('图形中参数比较多，请减少勾选的参数，以便更好的观察！');
			}
			oneyAxis_scale = {};
			oneyAxis_scale = {
                boundaryGap: [0, '50%'],
                axisLine: {
                    lineStyle: {
                        color: '#000'
                    }
                },
                type: 'value',
                name: colName,
                position: createOneyAxis_scale_position,
                offset: offset_size,
                splitNumber: 10,
                /*axisLabel: {
                    formatter: '{value}',
                    textStyle: {
                        color: '#FFFFFF'
                    }
                },*/
                splitLine: {
                    show: false
                }
            }
			return oneyAxis_scale;
		}
		
		//点击曲线上面的点，弹出曲线设置弹出框
		myChart.on('click', function (params) {
			if (params.componentType === 'series'){
				//点击曲线事件标记
				//clickLineEventFlag = 1;
				openChartPropertySettingDlgByClickLine(params.seriesName);
			    // 在控制台中打印
			    console.log('点击曲线图例' + params.name+'点击的参数：'+params.seriesName);
			}
			

		});
		
		//打开模态框
			function openChartPropertySettingDlgByClickLine(clicked_params_seriesName){
				//打开模态框
				$('#chartPropertySettingModal_byClickLineEvent').modal();
				
				//更新弹出框标题----------------------------------------
				$(".choosed_paramName_byClickLineEvent").empty();
				$(".choosed_paramName_byClickLineEvent").append(clicked_params_seriesName);
				//更新弹出框标题----------------------------------------
				
				//更新参数名输入框-----------------------------------------

				//选择的图表列名
				$("#choosed_paramNameInput").val('');
				$("#choosed_paramNameInput").val(clicked_params_seriesName);
				//更新参数名输入框------------------------------------------
				
				var lastParampropertysettingJsonTemp={};
				lastParampropertysettingJsonTemp = get_thisParam_lineProperty(clicked_params_seriesName);

				if(chartPropertySetting_saveSetFlag=='1'||chartPropertySetting_saveSetFlag==1){
					$.ajax({
						type: "POST",
						url: myChartApi_locate_url+'/csvmanager/infoChartpropertysetting/getChartPropertySetting',
				    	data: {},
						dataType:'json',
						cache: false,
						success: function(data){
							if(data.status=='1001'||data.status==1001){
								paramLinepropertyJsonarrFromDB = data.data;
								//自定设置之前设置过的颜色	
								$("#left_chartLineColor_byClickLineEvent").attr("hx", lastParampropertysettingJsonTemp.leftChartlinecolor);
								$('#left_chartLineColor_byClickLineEvent').iColor(function(hx) {				
						    		this.val('').css('background', '#' + hx);
						    		left_chartLineColor = '#' + hx;
						    	});
								$(".propertyIsSavedTip").empty();
								if(lastParampropertysettingJsonTemp==''||lastParampropertysettingJsonTemp==undefined){
									
									$(".propertyIsSavedTip").append('(该参数图形没有设置过属性)');
								}
								$("#chartLineWidth_byClickLineEvent").empty();      //清空	
								$("#chartLineWidth_byClickLineEvent").select2("val", "");								
								//线条粗细显示
								chartLineWidth_prepend = '';
								chartLineWidth_options = '';
								
								for(var i=0;i<chartLineWidthTemplate.length;i++){
									chartLineWidthTemplate_i = chartLineWidthTemplate[i];
									if(lastParampropertysettingJsonTemp.chartLineWidth==chartLineWidthTemplate_i.widthIndex){
										var option="<option value=\""+chartLineWidthTemplate_i.widthIndex+"\""; 
					                    option += ">"+chartLineWidthTemplate_i.widthNum+"</option>";
										chartLineWidth_prepend = option;
										continue;
									}
									var option="<option value=\""+chartLineWidthTemplate_i.widthIndex+"\""; 
				                    option += ">"+chartLineWidthTemplate_i.widthNum+"</option>";
				                    chartLineWidth_options = chartLineWidth_options+option;
																
								}
								//数据库中有线条粗细匹配						
								$("#chartLineWidth_byClickLineEvent").append(chartLineWidth_options);	
								$("#chartLineWidth_byClickLineEvent").prepend(chartLineWidth_prepend);
								$("#chartLineWidth_byClickLineEvent").val(lastParampropertysettingJsonTemp.chartlinewidth).trigger("change");
								//左边y轴颜色显示
								$("#left_chartLineColor_byClickLineEvent").css({"background":lastParampropertysettingJsonTemp.leftChartlinecolor});				
							}else{
								//没有保存数据库，从页面临时变量取
								//左边y轴颜色显示
								$("#left_chartLineColor_byClickLineEvent").css({"background":lastParampropertysettingJsonTemp.leftChartlinecolor});
									
							}
						}
						
					});
				}else{
					
				}
		
			}
		

		function loadAll(header, response) {
	  		$("#thead_string_columnsname").empty();
	  		$("#tbody_string_columnsname").empty();
	  		if(header.length>0&&response.length>0){
	  			 currIndex = 0;
	  			  //header
	  			  var header_html = '';
	  			  header_html+='<tr>';
	  			  header_html+='<th>';
	  			  header_html+= '序号';
	  			  header_html+='</th>';
	  			  for(var j=0;j<header.length;j++){			  
	  				  header_html+='<th>';
	  				  header_html+=header[j];
	  				  header_html+='</th>';			  
	  			  }
	  			  header_html+='</tr>';
	  			  //$("#thead_string_columnsname").empty();  	  		
	  			  $("#thead_string_columnsname").append(header_html);	
	  			  //$("#tbody_string_columnsname").empty();
	  			  
	  			  var pageSize = 100;
	  			  //数据分组， 每组100条，
	  			  var groups = group(response,pageSize);		  
	  			  for (var i = 0; i < groups.length; i++) {
	  			    //闭包， 保持i值的正确性
	  			    window.setTimeout(function () {
	  			      var group = groups[i];
	  			      var index = i + 1;
	  			      return function () {
	  			        //分批渲染
	  			        loadPart( group, index, pageSize);
	  			      }
	  			    }(), 2);
	  			    
	  			 }
	  		}
	  		
		}
			 
			//数据分组函数（每组500条）
			function group(response,pageSize) {
				//	var data = eval(response.qarData);
				var data=response;
				  var result = [];
				  var groupItem;
				  for (var i=0;i<data.length;i++) {
				    if (i % pageSize == 0) {
				      groupItem != null && result.push(groupItem);
				      groupItem = [];
				    }
				    groupItem.push(data[i]);
				  }
				  result.push(groupItem);				  
				  return result;
			}
			//表格行数分页索引
			var currIndex = 0;
			
			function loadPart(group, index, pageSize) {
			      var currPageCount = parseInt(pageSize*currIndex);
				  var html = "";
				  for (var i = 0; i < group.length; i++) {				 
					currPageCount+=1;
					html += "<tr><td class=\"center\" style=\"width: 30px;\">"+currPageCount+"</td>";
				    var jsonitem = group[i];
				    var item=jsonitem;
				    var arr=item.split(",");
				    for(var j=0;j<arr.length;j++){
				    	html+="<td>"+arr[j]+"</td>";
				    }
				    html+="</tr>";
				  }
				  //保证顺序不错乱
				  while (index-currIndex==1) {
				    $("#tbody_string_columnsname").append(html);
				    currIndex = index;
				  }
			}
			
			
			//选择文件导出类型，格式
			function exportFile(){
				let box2 = document.getElementById('paramExportFileType')
				box2.style.display = box2.style.display == 'block' ? 'none' : 'block';  
			}
			
			
			//参数导出
			function paramExport(choosed_whole_lineId,choosed_columnsNameStr,choosed_versionId, fileType){					
				console.log('导出的参数有：'+choosed_columnsNameStr);				

				if(choosed_columnsNameStr==''||choosed_columnsNameStr==""||choosed_columnsNameStr==undefined){
					oDateAlert('请勾选参数');
					console.log('请勾选参数');				
					return;
				}
				fidChooseRow = '';
				fidChooseRow = choosed_whole_lineId;
				console.log('fidChooseRow = ' + fidChooseRow);
				if(fidChooseRow==''||fidChooseRow==""||fidChooseRow==undefined){
					oDateAlert('请勾选csv文件');
					console.log('请勾选csv文件');				
					return;
				}
				var dataZoomArr = [];
				var dataZoomAxisStartValue=0;
				var dataZoomAxisEndValue=0;
				//图形下载是否成功
				var downloadImgSuccessFlag = false;
				//图形是否存在
			    var chartImgIsExistFlag = 0;
			    //图片名
			    var chartImgName = 'chartImg_'+fidChooseRow +'_'+new Date().getTime();
				if(myChart._model!=null&&myChart._model!=undefined&&fileType=='xlsx'){
					dataZoomArr = myChart._model.option.dataZoom;
					if(dataZoomArr!=''&&dataZoomArr!=undefined){
						for(var i=0;i<dataZoomArr.length;i++){
							if(dataZoomArr[i].type=='slider'&&dataZoomArr[i].xAxisIndex[0]==0){
								dataZoomAxisStartValue=dataZoomArr[i].startValue;
								dataZoomAxisEndValue=dataZoomArr[i].endValue;
								break;
							}
						}
					}
					if(dataZoomAxisStartValue==undefined){
						dataZoomAxisStartValue=0;
					}
					if(dataZoomAxisEndValue==undefined){
						oDateAlert('要导出的图形异常，请刷新后再试');
						console.log('要导出的图形异常，请刷新后再试');				
						return;
					}
					
				    var picBase64Info = myChart.getDataURL();//获取echarts图的base64编码，为png格式。				    
				    
				    if(picBase64Info!=''&&picBase64Info!=undefined){
				    	chartImgIsExistFlag = 1;
				    	picBase64Info = picBase64Info.split(',')[1];
				    	
				    	$.ajax({
				    	　　　　type: "post",
				    	　　　　data: {
				    	　　　　　　baseImg: picBase64Info,
				    	      	imgName: chartImgName
				    	　　　　},
				    	　　　　url: myChartApi_locate_url+'/csvmanager/showMultiCsv/exportChartImg',
				    	　　　　async: false,
				    	　　　　success: function(data) {
				    			if(data.status==1001||data.status=='1001'){
				    				console.log('图形图片保存成功');
				    				downloadImgSuccessFlag = true;
				    			}else{
				    				console.log('图形图片保存失败');
				    			}
				    	　　　　},
				    	　　　　error: function(err){
				    	　　　　　　console.log('图形图片保存失败');
				    	　　　　　　oDateAlert('图形图片保存失败');
				    			
				    	　　　　}

				    	　　});

				    }
				}
				

				if((chartImgIsExistFlag==1)&&!downloadImgSuccessFlag){
					oDateAlert('要导出的图形异常，请刷新后再试');
					console.log('要导出的图形异常，请刷新后再试,原因：下载图片异常');				
					return;
				}
				oDateAlert('正在准备导出，请稍后...');
				if(fileType=='xlsx'){
					export_url = myChartApi_locate_url+"/csvmanager/showMultiCsv/exportSelectRows?columnsNameStr="+choosed_columnsNameStr+"&fid="+fidChooseRow+'&choosed_versionId='+choosed_versionId+'&dataZoomAxisStartValue='+dataZoomAxisStartValue+'&dataZoomAxisEndValue='+dataZoomAxisEndValue+'&chartImgName='+chartImgName+'&chartImgIsExistFlag='+chartImgIsExistFlag+'&fileType='+fileType;					
				}
				else if(fileType=='xml'){
					export_url = myChartApi_locate_url+"/csvmanager/showMultiCsv/exportSelectRows?columnsNameStr="+choosed_columnsNameStr+"&fid="+fidChooseRow+'&choosed_versionId='+choosed_versionId+'&dataZoomAxisStartValue='+dataZoomAxisStartValue+'&dataZoomAxisEndValue='+dataZoomAxisEndValue+'&chartImgName='+chartImgName+'&chartImgIsExistFlag='+chartImgIsExistFlag+'&fileType='+fileType;					
				}
				else if(fileType=='txt'){
					export_url = myChartApi_locate_url+"/csvmanager/showMultiCsv/exportSelectRows?columnsNameStr="+choosed_columnsNameStr+"&fid="+fidChooseRow+'&choosed_versionId='+choosed_versionId+'&dataZoomAxisStartValue='+dataZoomAxisStartValue+'&dataZoomAxisEndValue='+dataZoomAxisEndValue+'&chartImgName='+chartImgName+'&chartImgIsExistFlag='+chartImgIsExistFlag+'&fileType='+fileType;					
				}else{
					console.log('文件格式有误！');
				}
				//window.open(export_url);
				window.location.href=export_url;
			}		
			
			
			
			//注销之前的图形缓存，1个以上的图表	
			function disposeHistoryChatObjArr(){
				//注销之前的图形缓存，1个以上的图表	
				for(var charti=0;charti<myLineChartArr.length;charti++){
					//myLineChartArr[charti].dispose();	
					myLineChartArr[charti].clear(); //清空setOption()
				}
			}	
			
		//获取该参数的曲线的属性----------------------
		//获取该参数的曲线的属性的数组，包含多个匹配的参数
		var thisParam_linePropertyArr = [];
		function get_thisParam_lineProperty(paramName){
			//
			var thisParam_linePropertyJson = {};
			var isMatchFromDB_flag = false;
			for(var i=0;i<paramLinepropertyJsonarrFromDB.length;i++){
				if(paramLinepropertyJsonarrFromDB[i].paramName==paramName){
					//
					thisParam_linePropertyJson = paramLinepropertyJsonarrFromDB[i];
					isMatchFromDB_flag = true;
				}
			}
			var isMatch_flag = false;
			for(var i=0;i<paramLinepropertyJsonarr.length;i++){
				if(paramLinepropertyJsonarr[i].paramName==paramName){
					//
					thisParam_linePropertyJson = paramLinepropertyJsonarr[i];
					isMatch_flag = true;
				}
			}
			if(isMatchFromDB_flag||isMatch_flag){
				thisParam_linePropertyArr.push(thisParam_linePropertyJson);
			}else{
				return '';
			}
			return thisParam_linePropertyJson;
		}
			
		
		//默认只有12种颜色设置，如果参数少于12个，则获取参数的序号，将自定义的颜色取代默认的参数颜色，如果参数多于12个，则则增加颜色
		function get_whole_chartLine_colorArr(yaxis_mySeries,thisParam_linePropertyArr,whole_chartLine_colorArr,whole_chartLine_colorArrDefault){
			//
			var isMatch_params_yAxisIndex_Arr = [];
			
			for(var yaxis_mySeries_i=0;yaxis_mySeries_i<yaxis_mySeries.length;yaxis_mySeries_i++){
				//遍历yaxis_mySeries
				var isMatch_yaxis_mySeries_i_Flag = false;
				var isMatch_yaxis_mySeries_i_yAxisIndex = 0;
				var isMatch_yaxis_mySeries_i_color = '';
				yaxis_mySeries_i_obj = {};
				yaxis_mySeries_i_obj = yaxis_mySeries[yaxis_mySeries_i];
				//如果自定义属性的曲线，参数匹配，记录yAxisIndex
				for(var i=0;i<thisParam_linePropertyArr.length;i++){
					if(thisParam_linePropertyArr[i].paramName==yaxis_mySeries_i_obj.name){
						//
						isMatch_yaxis_mySeries_i_Flag = true;
						isMatch_yaxis_mySeries_i_yAxisIndex = yaxis_mySeries_i_obj.yAxisIndex;
						isMatch_yaxis_mySeries_i_color = thisParam_linePropertyArr[i].leftChartlinecolor;
					}
				}
				if(isMatch_yaxis_mySeries_i_Flag){
					isMatch_params_yAxisIndex_Arr.push({"match_yAxisIndex":isMatch_yaxis_mySeries_i_yAxisIndex,"match_color":isMatch_yaxis_mySeries_i_color});
				}
			}
			//如果有匹配，修改这个参数，这条曲线的颜色
			for(var i=0;i<isMatch_params_yAxisIndex_Arr.length;i++){
				isMatch_yaxis_mySeries_i_yAxisIndex = 0;
				isMatch_yaxis_mySeries_i_yAxisIndex = isMatch_params_yAxisIndex_Arr[i].match_yAxisIndex;
				//如果大于10，则要减去10
				if(isMatch_yaxis_mySeries_i_yAxisIndex>10){
					//循环push 默认的颜色
					for(var more_i=0;more_i<(isMatch_yaxis_mySeries_i_yAxisIndex-10-1);more_i++){
						whole_chartLine_colorArr.push(whole_chartLine_colorArrDefault[more_i]);
					}
					//然后轮到该匹配的参数时，把自定的颜色push进去
					whole_chartLine_colorArr.push(isMatch_params_yAxisIndex_Arr[i].match_color);
				}else{
					whole_chartLine_colorArr[isMatch_yaxis_mySeries_i_yAxisIndex]=isMatch_params_yAxisIndex_Arr[i].match_color;
				}	
			}
			return whole_chartLine_colorArr;
		} 

	
		//
		//参数属性设置，选择参数
		$("#choosed_paramNameSelect").change(function(){
			var choosed_paramNameSelect = $('#choosed_paramNameSelect').val();		
			var thisParam_linePropertyJson = {};
			thisParam_linePropertyJson = get_thisParam_lineProperty(choosed_paramNameSelect);
			if(thisParam_linePropertyJson!={}&&thisParam_linePropertyJson!='{}'&&thisParam_linePropertyJson!=undefined&&thisParam_linePropertyJson!=''){
				//自动设置之前设置过的线条粗细
				$("#chartLineWidth").val(thisParam_linePropertyJson.chartlinewidth).trigger("change");
				//自定设置之前设置过的颜色
				$("#left_chartLineColor").css({"background":thisParam_linePropertyJson.leftChartlinecolor});	
				$("#left_chartLineColor").attr("hx", thisParam_linePropertyJson.leftChartlinecolor);
				$('#left_chartLineColor').iColor(function(hx) {
		    		this.val('').css('background', '#' + hx);
		    	});
			}
			
		});
		