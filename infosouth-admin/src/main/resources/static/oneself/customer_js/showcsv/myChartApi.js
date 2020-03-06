var myChartApi_locat = (window.location+'').split('/'); 
var myChartApi_locate_url = '';
$(function(){
	myChartApi_locat =  myChartApi_locat[0]+'//'+myChartApi_locat[2]+'/'+myChartApi_locat[3];
	myChartApi_locate_url = myChartApi_locat + '/a';
});

//Echarts绘图
function echart_mychart(xaxis_data,yaxis_mySeries,clicked_columnNamesArr,allYAxis_scale){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main_chart'));

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
	        end: 5,
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
            data:clicked_columnNamesArr
        },
        xAxis: {
            data: xaxis_data
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
		function req_refresh_chart(fid){
			$.ajax({
				type: "POST",
				url: myChartApi_locate_url+'/csvmanager/showCsv/getChartData',
		    	data: {fid:fid,columnsName:columnsNameStr},
				dataType:'json',
				cache: false,
				success: function(data){
					hide_zhezhao_mask();//关闭遮罩层
					//注销之前的图形缓存，1个以上的图表	
					disposeHistoryChatObjArr();
					if(data.status=='1001'){
						var chartData = data.data;

						//选择的图表列名
						var clicked_columnNamesArr = chartData.clicked_columnNamesArr;
						var colsGroup = chartData.colsGroup;					
						//检测所有点过的参数是否在同一组里面，是，true,否false
						check_is_one_groupFlag = check_is_one_group(colsGroup);
						
						//选择的字符串列名
						var clicked_string_columnNamesArr = chartData.clicked_string_columnNamesArr;
						//该列行数
						var countArr = chartData.chartXData;
						
						//该列所有行数据
						var clicked_chartYData = chartData.clicked_chartYData;					
						//所有点过的列数据,显示用
						var chartDataSets = [];
						//画图中的series参数
						var mySeries = [];
						//
						var allYAxis_scale = [];
						if(clicked_columnNamesArr!=null&&clicked_columnNamesArr.length>0){
							var columnsNameStrArr = [];
							columnsNameStrArr = clicked_columnNamesArr;
							var columnsNameStrArrSize = clicked_columnNamesArr.length;
							
							for(var j=0;j<columnsNameStrArrSize;j++){					
								var col_chartYData = clicked_chartYData[j].colData;
								//
								mySeries_data_i = {"name":clicked_columnNamesArr[j],"type":"line","data":col_chartYData,"yAxisIndex":j};//
								mySeries.push(mySeries_data_i);
								//
								createOneyAxis_scale_position = '';
								if(j%2==0){
									createOneyAxis_scale_position = 'left';
								}else{
									createOneyAxis_scale_position = 'right';
								}
								//每个参数的y轴
								allYAxis_scale.push(createOneyAxis_scale(clicked_columnNamesArr[j],createOneyAxis_scale_position,j));
							}
							//Echarts 绘图
							echart_mychart(countArr,mySeries,clicked_columnNamesArr,allYAxis_scale);	
						}
						
						

						//将字符串数据显示在表格
						var clicked_stringData = [];
						clicked_stringData = chartData.clicked_stringData;
						loadAll(clicked_string_columnNamesArr, clicked_stringData);
						if(clicked_stringData.length>0){						
							oDateAlert('所选参数含有不可数值化的值,请查看表格！');
						}
					}else if(data.status=='1002'){
						hide_zhezhao_mask();//关闭遮罩层
						oDateAlert('返回数据异常');
						console.log('1002返回数据异常');
						
						return;
					}else if(data.status=='1003'){
						hide_zhezhao_mask();//关闭遮罩层
						oDateAlert('所选参数含有不可数值化的值,被忽略！');
						console.log('1003sorry,该参数含有字符串，无法图表化！');
						
						return;
					}else if(data.status=='1004'){					
						console.log('1004没有参数可查询！');
						hide_zhezhao_mask();//关闭遮罩层
						return;
					}else if(data.status=='1005'){	
						//注销之前的图形缓存
						myLineChart.destroy();
						//空图表------------------------------
						myLineChart = new Chart(ctx, {
						    type: 'line',
						    data: [],
						    options: 'top' //'bottom''left''top''right'
						});
						//空图表-结束------------------------------
						console.log('1005没有参数可查询！');
						hide_zhezhao_mask();//关闭遮罩层
						return;
					}else if(data.status=='1006'){	
						//注销之前的图形缓存
						myLineChart.destroy();
						//显示字符串
						console.log('1006显示字符串！');
						hide_zhezhao_mask();//关闭遮罩层
						return;
					}else if(data.status=='1010'){	
						//注销之前的图形缓存
						myLineChart.destroy();
						//显示字符串
						oDateAlert('设置的x轴刻度过大，请重新设置！');
						console.log('设置的x轴刻度过大，请重新设置！');
						hide_zhezhao_mask();//关闭遮罩层
						return;
					}else{
						hide_zhezhao_mask();//关闭遮罩层
						oDateAlert('返回数据异常，可能是系统错误');
						console.log('返回数据异常，可能是系统错误');
						return;
					}
					console.log('返回图表数据成功');
				}
			});
			hide_zhezhao_mask();//关闭遮罩层
			console.log('ok');
			
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
			
			
			//参数导出
			function paramExport(){		
				console.log('导出的参数有：'+columnsNameStr);
				var trclassArr = clicked_checkboxValue.substring(0,clicked_checkboxValue.length-1).split(",");
				var fid = '';
				for(var i=0;i<trclassArr.length;i++){
					var lineId = trclassArr[i];
					//除去 selected 标签
					var fidStr = lineId.split(';');
					fid = fidStr[0];
					var linei = fidStr[1];
				}
				console.log('fid = ' + fid);
				
				if(columnsNameStr==''||columnsNameStr==""||columnsNameStr==undefined){
					oDateAlert('请勾选参数');
					console.log('请勾选参数');				
					return;
				}
				fidChooseRow = '';
				fidChooseRow = $("#fidChooseRow_input").val();
				console.log('fidChooseRow = ' + fidChooseRow);
				if(fidChooseRow==''||fidChooseRow==""||fidChooseRow==undefined){
					oDateAlert('请选择相应的任务');
					console.log('请选择相应的任务');				
					return;
				}
				
				if(fid==''||fid==""||fid==undefined){
					oDateAlert('请选择相应的任务');
					console.log('请选择相应的任务');				
					return;
				}
				oDateAlert('正在准备导出，请稍后...');
				export_url = myChartApi_locate_url+"/csvmanager/showCsv/exportSelectRows?columnsNameStr="+columnsNameStr+"&fid="+fid;
				window.location.href=export_url;
			}
			
			//注销之前的图形缓存，1个以上的图表	
			function disposeHistoryChatObjArr(){
				//注销之前的图形缓存，1个以上的图表	
				for(var charti=0;charti<myLineChartArr.length;charti++){
					myLineChartArr[charti].dispose();		
				}
			}	