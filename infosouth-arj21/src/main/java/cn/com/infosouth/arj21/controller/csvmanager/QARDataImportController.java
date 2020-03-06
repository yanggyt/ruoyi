package cn.com.infosouth.arj21.controller.csvmanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.com.infosouth.arj21.domain.InfoAcType;
import cn.com.infosouth.arj21.domain.InfoAircraft;
import cn.com.infosouth.arj21.domain.InfoFlight;
import cn.com.infosouth.arj21.domain.InfoHeaderParams;
import cn.com.infosouth.arj21.domain.InfoImexportLog;
import cn.com.infosouth.arj21.domain.InfoResource;
import cn.com.infosouth.arj21.service.IInfoAcTypeService;
import cn.com.infosouth.arj21.service.IInfoAircraftService;
import cn.com.infosouth.arj21.service.IInfoFlightService;
import cn.com.infosouth.arj21.service.IInfoHeaderParamsService;
import cn.com.infosouth.arj21.service.IInfoImexportLogService;
import cn.com.infosouth.arj21.service.IInfoResourceService;
import cn.com.infosouth.arj21.utils_oneselef.FileUtils;
import cn.com.infosouth.arj21.utils_oneselef.IdGeneratorUtils;
import cn.com.infosouth.common.core.controller.BaseController;
import cn.com.infosouth.common.utils.StringUtils;
import cn.com.infosouth.framework.util.ShiroUtils;
import cn.com.infosouth.system.domain.SysUser;

/**
 * @author Tiger
 * @date 2020/3/6 9:32
 * @description :
 */
@Controller
@RequestMapping("/arj21/csvmanager/qarDataImport")
public class QARDataImportController extends BaseController {

    private String prefix = "arj21/csvmanager";
    
    @Resource
    private IInfoResourceService infoResourceService;
    @Resource
    private IInfoAircraftService infoAircraftService;
    @Resource
    private IInfoAcTypeService infoActypeSevice;
    @Resource
    private IInfoFlightService infoFlightService;
    @Resource
    private IInfoHeaderParamsService infoHeaderParamsService;
    @Resource
    private IInfoImexportLogService infoImexportLogService;
    
	
    @RequiresPermissions("csvmanager:qarDataImport:view")
    @RequestMapping(value = {"import", ""})
    public String _import(InfoFlight infoFlight, Model model) {
        model.addAttribute("infoFlight", infoFlight);
        return prefix + "/qarDataImport";
    }

    @RequiresPermissions("csvmanager:qarDataImport:upload")
    @PostMapping("/upload")
	@ResponseBody
    public Map<String,String> upload(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("files") MultipartFile[]  files) throws IOException {
    	System.out.println("");
    	Enumeration<String> attributeNames = request.getSession().getAttributeNames();
    	Map<String,String> returnMap = new HashMap<String, String>();
		String index = request.getParameter("index");
		if(files.length > 0){
			String message = uploadSingleFile(request,files[0]);
			returnMap.put("index", index);
			returnMap.put("message", message);
		}
		return returnMap;
    }


    private String uploadSingleFile(HttpServletRequest request,MultipartFile files) throws IOException{
		String fileName = files.getOriginalFilename();
		//设置上传文件名
		
		System.out.println("01.上传文件名:"+fileName);
		//文件名js已验证
		/*if(fileName.length() <25){
			out.print("2");
			return;
		}*/
		
		
		//1.解析文件名中的飞机号,航班日期
		String airNo = fileName.substring(0, 6);
		//set AirNo
		
		InfoAircraft air = infoAircraftService.getByAcReg(airNo);
		if(air == null){
			return "飞机号不存在";
		}
		//通过飞机号查询机型,此处测试默认CRJ-900 //TODO
		String airId = air.getId();//飞机id
//		String acTypeId = air.getInfoAcType().getId(); //"CRJ-900";//机型 (id)
		InfoAcType actype = infoActypeSevice.selectInfoAcTypeById(air.getInfoAcTypeId());
		String acTypeId = "";
		if(actype != null) {
			acTypeId = actype.getId();
		}
		
		String resourceId = air.getUploadDirId();

		String flightDateStr = fileName.substring(7, 21);
		Date flightDate = null;
		InfoResource resource = infoResourceService.selectInfoResourceById(resourceId);
		String csvPath = "";
		if(resource != null){
			csvPath = resource.getMappingPath();
		}
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			flightDate = sdf.parse(flightDateStr);
		}
		catch (ParseException e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("02.flightDateStr:"+flightDateStr);
		
		InfoFlight flight = new InfoFlight();
		flight.setArn(airId);
		flight.setFlDate(flightDate);
		
		String flightNo = " ";//航班号暂时默认为空
		flight.setFlNo(flightNo);
		String pod = "";//起飞机场
		String poa = "";//落地机场
		////////////////////////////////////////////////////////////正则匹配起落机场是否在CSV名中设置
		//{0}_{1}_{2}-{3}.CSV
		String pod_poa_str = fileName.substring(fileName.lastIndexOf('_')+1, fileName.lastIndexOf('.')).toUpperCase();
		String regexStr = "^([A-Z]{3,6})\\-([A-Z]{3,6})$";
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regexStr);
		java.util.regex.Matcher matcher = pattern.matcher(pod_poa_str);
		if(matcher.find())
		{
			pod = matcher.group(1);
			poa = matcher.group(2);
		}
		
		System.out.println("03.pod=" + pod + ", poa=" + poa);
		
		flight.setPod(pod);
		flight.setPoa(poa);
		flight.setRemarks("");
		flight.setCsvName(fileName);
		flight.setAcType(acTypeId);
		flight.setInfoResourceId(resourceId);
		flight.setCsvPath(csvPath);//hdfs存储路径\
		
		//创建导入导出日志实体类
		InfoImexportLog oprationLogBean = new InfoImexportLog();
		SysUser user = ShiroUtils.getSysUser();

		String currentUserName = user.getLoginName();
		//机型
		
		InfoAcType infoAcType = infoActypeSevice.selectInfoAcTypeById(acTypeId);
		
		oprationLogBean.setCsvName(fileName);
		oprationLogBean.setArn(airNo);
		oprationLogBean.setFlDate(flightDate);
		oprationLogBean.setFlNo(flightNo);
		oprationLogBean.setCsvPath(csvPath);
		oprationLogBean.setOperateType("import");
		oprationLogBean.setOperateBy(currentUserName);
		oprationLogBean.setOperateDate(new Date());
		oprationLogBean.setAcType(infoAcType.getAcTpye());
		//设置用户的IP
		try {
			oprationLogBean.setHostIp(getClientIpAddr(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
        boolean flag = false;
        boolean uploadflag = false;
        String fileSizeStr = "";
        //String uploadPath = request.getSession().getServletContext().getRealPath(path);
        String uploadPath = "/tmp";
        //根据机型插入机型对应的记录参数数据info_header_params
        int version = actype.getVersionId().intValue();
        InfoHeaderParams infoHeaderParams = infoHeaderParamsService.getByVersionId(version);
        if(infoHeaderParams == null || !StringUtils.isNotEmpty(infoHeaderParams.getHeaderParams())){
        	String headerParams = "";
        	int headerIndex = actype.getCsvHeaderIndex().intValue();
        	try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(files.getInputStream()));
                String line = null;
                while((line=reader.readLine())!=null){
                	if(headerIndex == 0){
                		headerParams = line.replace("\"", "");
                		break;
                	}
                	headerIndex--;
                }
                if(infoHeaderParams == null)
                	infoHeaderParams = new InfoHeaderParams();
                infoHeaderParams.setVersionId(version + "");
            	infoHeaderParams.setHeaderParams(headerParams);
            	infoHeaderParams.setRemarks("");
            	infoHeaderParams.setId(IdGeneratorUtils.uuid());
            	infoHeaderParamsService.save(infoHeaderParams);
            } catch (Exception e) {
            	//读取csv参数名行时出错...
            	System.out.println("读取csv参数名行时出错...");
            	e.printStackTrace();
            }
        }
        if (files.getSize() > 0) {
            try {
            	org.apache.commons.io.FileUtils.copyInputStreamToFile(files.getInputStream(),
                        new File(uploadPath, files.getOriginalFilename()));//将上传的文件拷贝到上传文件临时目录
            	fileSizeStr = FileUtils.getFileSizeByPath(uploadPath +"/" + files.getOriginalFilename());
            	oprationLogBean.setFileSize(fileSizeStr);
            	
            	try {
//					HDFSHandleService.uploadCSV2HDFSAsync(uploadPath +"/" + file.getOriginalFilename(), csvPath+File.separator+file.getOriginalFilename());
					//直接将file.getInputStream()上传到hdfs，上一步就不需要了
	            	oprationLogBean.setIsSucce("true");
	            	oprationLogBean.setFailType("");
	            	uploadflag = true;
	            	
	            	System.out.println("04.uploadCSV2HDFSAsync storageFullPath=>"+ csvPath+File.separator+files.getOriginalFilename());
				} catch (Exception e) {
					System.out.println("04.catch uploadCSV2HDFSAsync error=>"+ e.getMessage());
					
					System.out.println("上传失败，原因：HDFSHandleService.uploadCSV2HDFSAsync 异常：");
	            	oprationLogBean.setIsSucce("false");
	            	oprationLogBean.setFailType(e.getMessage());
	            	e.printStackTrace();
				}
                if(!uploadflag){
                	oprationLogBean.setIsSucce("false");
	            	oprationLogBean.setFailType("上传失败，原因：HDFSHandleService.uploadCSV2HDFSAsync 异常");
                }
                
                boolean result =infoFlightService.csvExist(fileName);
                System.out.println("05.infoFlightService.csvExist（"+ fileName + ") => return result="+result);
                
        		if(result){
        			//已上传过
        			String oldCsvId= infoFlightService.getIdByCsvName(fileName);
        			flight.setId(oldCsvId);
        			
        			flight.setUpdateDate(new Date());
        		}
        		flight.setCreateBy(user.getLoginName());
        		flight.setId(IdGeneratorUtils.uuid());
        		flight.setCreateDate(new Date());
        		flight.setCreateTime(new Date());
        		flight.setUpdateDate(new Date());
        		flight.setUpdateTime(new Date());
        		flight.setUpdateBy(user.getLoginName());
        		flight.setRemarks(" ");
        		flight.setDelFlag("0");
        		infoFlightService.insertInfoFlight(flight);
                flag = true;
            } catch (Exception e) {
            	System.out.println("上传失败：");
            	oprationLogBean.setIsSucce("false");
            	oprationLogBean.setFailType(e.getMessage());
            	e.printStackTrace();
            }
            oprationLogBean.setId(IdGeneratorUtils.uuid());
            oprationLogBean.setCreateDate(new Date());
            oprationLogBean.setCreateTime(new Date());
            oprationLogBean.setCreateBy(user.getLoginName());
            oprationLogBean.setUpdateDate(new Date());
            oprationLogBean.setUpdateTime(new Date());
            oprationLogBean.setUpdateBy(user.getLoginName());
            oprationLogBean.setRemarks(" ");
            oprationLogBean.setDelFlag("0");
            infoImexportLogService.insertInfoImexportLog(oprationLogBean);
            
        }
        
        if (flag == true) {
            return "1";
        } else {
            return "上传失败";
        }
	}
	
    /**  
     * @Description: 获取客户端IP地址    
     */    
    private String getClientIpAddr(HttpServletRequest request) {     
           String ip = request.getHeader("x-forwarded-for");     
           if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
               ip = request.getHeader("Proxy-Client-IP");     
           }     
           if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
               ip = request.getHeader("WL-Proxy-Client-IP");     
           }     
           if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
               ip = request.getRemoteAddr();     
               if(ip.equals("127.0.0.1")){       
                   //根据网卡取本机配置的IP       
                   InetAddress inet=null;       
                   try {       
                       inet = InetAddress.getLocalHost();       
                   } catch (Exception e) {       
                       e.printStackTrace();       
                   }       
                   ip= inet.getHostAddress();       
               }    
           }     
           // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割    
           if(ip != null && ip.length() > 15){      
               if(ip.indexOf(",")>0){       
                   ip = ip.substring(0,ip.indexOf(","));       
               }       
           }       
           return ip;     
    }

 






}
