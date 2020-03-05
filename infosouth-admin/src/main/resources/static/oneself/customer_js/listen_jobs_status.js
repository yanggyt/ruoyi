
        var Chat = {};  
  
        Chat.socket = null;  
  
        function mywebsocketMsg(getJobs_Status_Uri){
        	Chat.connect = (function(host) {  
                if ('WebSocket' in window) {  
                    Chat.socket = new WebSocket(host);  
                } else if ('MozWebSocket' in window) {  
                    Chat.socket = new MozWebSocket(host);  
                } else {  
                    Console.log('Error: WebSocket is not supported by this browser.');  
                    return;  
                }  
      
                Chat.socket.onopen = function () {  
                    Console.log('Info: WebSocket connection opened.');  
                   
                };  
      
                Chat.socket.onclose = function () {  
                    
                    Console.log('Info: WebSocket closed.');  
                };  
      
                Chat.socket.onmessage = function (message) {  
                	var respDataStr = message.data;
                	var respData = JSON.parse(respDataStr);
                	var returnData = respData.returnData;
                	//遍历节点
                	for(var i=0;i<returnData.length;i++){            		  
                		//节点名
                		var nodeName=returnData[i].nodeName;
                		var jobsExecStatusData=returnData[i].jobsExecStatusData;
                		for(var j=0;j<jobsExecStatusData.length;j++){ 
                			jobsExecStatusObj = {};
                			jobsExecStatusObj = jobsExecStatusData[j];
                			jobId = jobsExecStatusObj.jobID;
                			jobStatus = jobsExecStatusObj.jobStatus;
                			newjobStatus = jobStatus;
    
                			old_execute_status = $(".execute_status_"+jobId).val();
                			if(old_execute_status!='-1'&&old_execute_status!='-2'&&old_execute_status!=-1&&old_execute_status!=-2&&old_execute_status!=''){ 
                				$(".execute_job_"+jobId).empty();	
                			}
                			
                			               			
                			if(jobStatus=='1'){
                				
                				if(old_execute_status!='-1'&&old_execute_status!='-2'&&old_execute_status!=-1&&old_execute_status!=-2&&old_execute_status!=''){                					
                					jobStatus = '运行完成';
                
                					$(".execute_job_"+jobId).append("执行");
                					newjobStatus = 2;
                				}
                				
                			}
                			else if(jobStatus=='2'){
                				
                				if(old_execute_status!='-1'&&old_execute_status!='-2'&&old_execute_status!=-1&&old_execute_status!=-2){
                	
                					jobStatus = '正在运行';
                					$(".execute_job_"+jobId).append("停止");
                					newjobStatus = 0;
                				}
                				
                			}
                			else if(jobStatus=='3'){
                				
                				if(old_execute_status!='-1'&&old_execute_status!='-2'&&old_execute_status!=-1&&old_execute_status!=-2&&old_execute_status!=''){
                		
                					jobStatus = '错误';
                					$(".execute_job_"+jobId).append("停止");
                					newjobStatus = 0;
                				}
                				
                			}else if(jobStatus=='6'){
                				
                				if(old_execute_status!='-1'&&old_execute_status!='-2'&&old_execute_status!=-1&&old_execute_status!=-2&&old_execute_status!=''){
                				
                					jobStatus = '文件不存在';
                					$(".execute_job_"+jobId).append("停止");
                					newjobStatus = 0;
                				}
                				
                			}	
                			else{
                				
                				if(old_execute_status!='-1'&&old_execute_status!='-2'&&old_execute_status!=-1&&old_execute_status!=-2&&old_execute_status!=''){
                					
                					 
                            		jobStatus = '错误';
                					$(".execute_job_"+jobId).append("停止");
                					newjobStatus = 0;
                				}
                				
                			}

                			
                    		
                    		//
                    					
    						
    						//开关临时状态
                    		if(old_execute_status!='-1'&&old_execute_status!='-2'&&old_execute_status!=-1&&old_execute_status!=-2&&old_execute_status!=''){
                    			$(".jobStatus_"+jobId).empty();
                        		$(".jobStatus_"+jobId).append(jobStatus); 
                        		$(".execute_status_"+jobId).val(newjobStatus);
                    		}
                    		
                    		    						
    						
                		}
                	}
                };  
            });  
      
            Chat.initialize = function() {  
            	console.log(''+window.location.protocol);
            	if (window.location.protocol == 'http:') { 
                    Chat.connect('ws://' + getJobs_Status_Uri);  
                } else {  
                	Console.log(''+window.location.host);
                    Chat.connect('wss://' + getJobs_Status_Uri); 
                }  
            };  
      
            Chat.sendMessage = (function() {  
                
            });  
      
            var Console = {};  
      
            Console.log = (function(message) {  
               
            });  
      
            Chat.initialize();  
      
      
            document.addEventListener("DOMContentLoaded", function() {  
                // Remove elements with "noscript" class - <noscript> is not allowed in XHTML  
                var noscripts = document.getElementsByClassName("noscript");  
                for (var i = 0; i < noscripts.length; i++) {  
                    noscripts[i].parentNode.removeChild(noscripts[i]);  
                }  
            }, false); 
        }
         
        
      //判断dom有没有指定class
        var rclass = /[\t\r\n\f]/g;
        jQuery.fn.extend({
            hasClass: function(selector) {
                var className = " " + selector + " ",
                    i = 0,
                    l = this.length;
                for (; i < l; i++) {
                    if (this[i].nodeType === 1 &&
                        (" " + this[i].className + " ").replace(rclass, " ").indexOf(className) > -1) {
                        return true;
                    }
                }

                return false;
            }
        });
  