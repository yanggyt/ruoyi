package com.ruoyi.web.controller.system;



import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.FormFileConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.OrderTypes;
import com.ruoyi.common.enums.PetitionStatus;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.component.petition.PetitionComponent;
import com.ruoyi.system.domain.FormFile;
import com.ruoyi.system.domain.ProcessFlow;
import com.ruoyi.system.domain.petition.Petition;
import com.ruoyi.system.domain.petition.PetitionFile;
import com.ruoyi.system.domain.petition.PetitionSign;
import com.ruoyi.system.dto.petition.PetitionDto;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.IProcessFlowService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.petition.IPetitionFileService;
import com.ruoyi.system.service.petition.IPetitionService;
import com.ruoyi.system.service.petition.IPetitionSignService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * 签呈审批 信息操作处理
 * 
 * @author lxp
 * @date 2020-07-09
 */
	@Api("参数配置")
@Controller
@RequestMapping("/system/petition")
public class PetitionController extends BaseController
{

	private static final Logger log = LoggerFactory.getLogger(PetitionController.class);

    private String prefix = "system/petition";
	
	@Autowired
	private IPetitionService petitionService;

	@Autowired
	private ISysUserService userService;

//	@Autowired
//	private IPetitionLogService petitionLogService;

	@Autowired
	private IProcessFlowService processFlowService;

	@Autowired
	private IPetitionFileService petitionFileService;

//	@Autowired
//	private IPetitionAddauditService petitionAddauditService;

	@Autowired
	private IPetitionSignService petitionSignService;

//	@Autowired
//	private IPetitionTrialService petitionTrialService;
	
	@Autowired
	private PetitionComponent petitionComponent;

	@Autowired
	private SysUserMapper userMapper;


	private static final Integer FLAG_HIDDEN=0;
	private static final Integer FLAG_SHOW=1;

	@RequiresPermissions("system:petition:view")
	@RequestMapping()
	public String petition()
	{
	    return prefix + "/petition";
	}
	
	/**
	 * 查询所有签呈审批列表
	 */
	@RequiresPermissions("system:petition:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Petition petition)
	{
		startPage();
		SysUser sysUser = ShiroUtils.getSysUser();
		String loginName = sysUser.getLoginName();
		//管理员查看所有
		if (getSysUser().isAdmin()){
//			petition.setTypeType(2);
		}else{
			petition.setSid(sysUser.getLoginName());
		}
		petition.setFlag(FLAG_SHOW);
        List<Petition> list = petitionService.selectPetitionList(petition);
		petitionComponent.buildList(list);
		return getDataTable(list);
	}
	/**
	 * 我的已办
	 */
	@GetMapping("/did")
	public String did()
	{
		return prefix + "/petitionDid";
	}

	/**
	 * 查询我的已办列表
	 */
	@RequiresPermissions("system:petition:list")
	@PostMapping("/list/Did")
	@ResponseBody
	public TableDataInfo Did(Petition petition)
	{
		startPage();
//		petition.setFormSta(PetitionStatus.TODO.value());
		petition.setHandlesCode(getSysUser().getUserCode());
		petition.setFlag(FLAG_SHOW);
		List<Petition> list=petitionService.selectPetitionList(petition);
		petitionComponent.buildList(list);
		return getDataTable(list);
	}

	/**
	 * 我的待办
	 */
	@GetMapping("/todo")
	public String todo()
	{
		return prefix + "/petitionTodo";
	}
	/**
	 * 查询我的待办列表
	 */
	@RequiresPermissions("system:petition:list")
	@PostMapping("/list/Todo")
	@ResponseBody
	public TableDataInfo Todo(Petition petition)
	{
		startPage();
		petition.setFormSta(PetitionStatus.TODO.value());
		petition.setFromSendto(getSysUser().getUserCode());
		petition.setFlag(FLAG_SHOW);
		List<Petition> list=petitionService.selectPetitionList(petition);
		petitionComponent.buildList(list);
		return getDataTable(list);
	}

	/**
	 * 我的发起
	 */
	@GetMapping("/create")
	public String create()
	{
		return prefix + "/petitionCreate";
	}
	/**
	 * 查询我的发起列表
	 */
	@RequiresPermissions("system:petition:list")
	@PostMapping("/list/create")
	@ResponseBody
	public TableDataInfo petitionCreate(Petition petition)
	{
		startPage();
		String loginName = getSysUser().getLoginName();
		if (!getSysUser().isAdmin()){
			petition.setCreatBy(loginName);
		}
		petition.setFlag(FLAG_SHOW);
		List<Petition> list=petitionService.selectPetitionList(petition);
		petitionComponent.buildList(list);
		return getDataTable(list);
	}
	
	/**
	 * 导出签呈审批列表
	 */
	@RequiresPermissions("system:petition:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Petition petition)
    {
    	List<Petition> list = petitionService.selectPetitionList(petition);
        ExcelUtil<Petition> util = new ExcelUtil<Petition>(Petition.class);
        return util.exportExcel(list, "petition");
    }
	
	/**
	 * 新增签呈审批
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存签呈审批
	 */
	@RequiresPermissions("system:petition:add")
	@Log(title = "签呈审批", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult add(@RequestBody PetitionDto petitionDto) {
		Petition petition = petitionDto.getPetition();
		try {
			SysUser sysUser = getSysUser();
			petition.setCreatBy(sysUser.getLoginName());
			petition.setCreatDate(new Date());
			petition.setFlag(FLAG_SHOW);
			Long aLong = petitionService.insertPetition(petitionDto,sysUser);
			this.submit(aLong);
//			buildAttribute(petition.getId(), OrderTypes.PETITION.getCode());
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("保存电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success(petition.getId().toString());
	}

	/**
	 * 修改签呈审批
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Petition petition = petitionService.selectPetitionById(id);
		PetitionSign petitionSign=new PetitionSign();
		petitionSign.setPetitionId(id);
		List<PetitionSign> petitionSigns = petitionSignService.selectPetitionSignList(petitionSign);
		mmap.put("petition", petition);
		mmap.put("petitionSigns",petitionSigns);
		petitionComponent.buildCheckBox(petition);
		mmap.put("typeMaps", petition.getParams());
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存签呈审批
	 */
	@RequiresPermissions("system:petition:edit")
	@Log(title = "签呈审批", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult edit(@RequestBody Petition petition)
	{
		try {
			SysUser sysUser = getSysUser();
			petition.setUpdateBy(sysUser.getLoginName());
			petition.setUpdateDate(new Date());
//			buildAttribute(petition.getId(), OrderTypes.PETITION.value());
			petitionService.updatePetition(petition);
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("修改电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success();
	}
	
	/**
	 * 逻辑删除签呈审批
	 */
	@RequiresPermissions("system:petition:removeLogic")
	@Log(title = "签呈审批", businessType = BusinessType.DELETE)
	@PostMapping( "/removeLogic")
	@ResponseBody
	public AjaxResult removeLogic(Long id)
	{
		Petition petition = petitionService.selectPetitionById(id);
		petition.setFlag(FLAG_HIDDEN);

		return toAjax(petitionService.updatePetition(petition));
	}
	
	/**
	 * 管理员删除签呈审批
	 */
	@RequiresPermissions("system:petition:remove")
	@Log(title = "签呈审批", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(petitionService.deletePetitionByIds(ids));
	}

	/**
	 * 自动查询员工编号查询
	 */
	@PostMapping("/getMessage")
	@ResponseBody
	public AjaxResult getMessage() {
		SysUser sysUser = getSysUser();
		//正式
		SysUser user = userService.selectUserByLoginName(sysUser.getLoginName());
		if (StringUtils.isNull(user)) {
			return error(AjaxResult.Type.ERROR, "2333");
		}
		SysUser manager= null;
		//查询直接主管
		String zgsid = user.getZgsid();
		//查询代签主管
		String agentsid = user.getPurchasesid();
		if (StringUtils.isNotEmpty(agentsid)){
			manager=userService.selectUserByLoginName(agentsid);
		}else{
			manager=userService.selectUserByLoginName(zgsid);
		}
		Map<String,String> map = new HashMap<>();
		map.put("applyname",user.getUserName());
		map.put("dept",user.getDept().getDeptName().trim());
		map.put("company",user.getOffices());
		map.put("sid",user.getLoginName());
		map.put("deptmanagerSid",manager.getLoginName());
		map.put("deptmanager",manager.getUserName());
		return AjaxResult.success("",map);
	}
	/**
	 * 手动查询员工编号查询
	 */

	@GetMapping("/getMessage/{sid}")
	@ResponseBody
	public AjaxResult getMessage(@PathVariable("sid") String sid) {
		SysUser user =new SysUser();
		if (StringUtils.isEmpty(sid)){
			SysUser sysUser = getSysUser();
			//正式
			user = userService.selectUserByLoginName(sysUser.getLoginName());
			//测试
			//user = userService.selectUserByLoginName("S01221");
		}else{
			user = userService.selectUserByLoginName(sid);
		}
		if(user == null){
			return error(AjaxResult.Type.ERROR,"2333");
		}
		SysUser manager= null;
		//查询直接主管
		String zgsid = user.getZgsid();
		//查询代签主管
		String agentsid = user.getPurchasesid();
		if (StringUtils.isNotEmpty(agentsid)){
			manager=userService.selectUserByLoginName(agentsid);
		}else{
			manager=userService.selectUserByLoginName(zgsid);
		}

		Map<String,String> map = new HashMap<>();
		map.put("applyname",user.getUserName());
		map.put("dept",user.getDept().getDeptName().trim());
		map.put("company",user.getOffices());
		map.put("deptmanagerSid",manager.getLoginName());
		map.put("deptmanager",manager.getUserName());
		return AjaxResult.success("",map);
	}


	/**
	 * 详情
	 */
	@RequiresPermissions("system:petition:detail")
	@GetMapping("/detail/{id}/{type}")
	public String detail(@PathVariable("id") Long id,@PathVariable("type") Integer type, ModelMap mmap)
	{
		Petition petition = petitionService.selectPetitionById(id);
		PetitionSign p=new PetitionSign();
		p.setPetitionId(id);
		p.setSignType(3);
		List<PetitionSign> petitionAddaudits = petitionSignService.selectPetitionSignList(p);
		PetitionSign  ps=new PetitionSign();
		ps.setPetitionId(id);
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignService.selectPetitionSignList(ps);

		PetitionSign  pt=new PetitionSign();
		pt.setPetitionId(id);
		pt.setSignType(2);
		List<PetitionSign> petitionTrialList = petitionSignService.selectPetitionSignList(pt);
		
		petitionComponent.buildCheckBox(petition);

		mmap.put("petition", petition);
		mmap.put("petitionAddaudits",petitionAddaudits);
		mmap.put("petitionSigns",petitionSigns);
		mmap.put("petitionTrialList",petitionTrialList);
		
		mmap.put("petitionFiles", petitionService.selectFormFile(id, FormFileConstants.PETITION));
		mmap.put("typeMaps", petition.getParams());
		SysUser user = ShiroUtils.getSysUser();
		if (petition.getCreatBy().equals(user.getLoginName())){
			mmap.put("isClose", "1");
		}
		if (Objects.equals(type, 2)){
			//展示审批截图
			return prefix + "/detailPDF";
		} else {
			return prefix + "/detail";
		}
	}
	
	/**
	 * 进入filePage页面
	 */
	@GetMapping("/filePage/{id}")
	public String filePage(@PathVariable("id") Long id, ModelMap mmap)
	{
		mmap.put("petitionId", id);
		Petition petition = petitionService.selectPetitionById(id);
		mmap.put("petition", petition);
		return prefix + "/filePage";
	}

	/**
	 * 查询filePage页面数据
	 */
	@PostMapping("/filePageList")
	@ResponseBody
	public TableDataInfo filePageList(@RequestParam("petitionId") Long petitionId)
	{
		List<FormFile> petitionFiles = petitionService.selectFormFile(petitionId, FormFileConstants.PETITION);
		return getDataTable(petitionFiles);
	}
	/**
	 * 进入新增附件页面
	 */
	@RequiresPermissions("system:petition:edit")
	@GetMapping("/addFile/{id}")
	public String addFile(@PathVariable("id") Long id, ModelMap mmap)
	{
		mmap.put("petitionId", id);
		return prefix + "/addFile";
	}
//	/**
//	 * 新增保存附件
//	 */
//	@RequiresPermissions("system:petition:add")
//	@Log(title = "签呈文件", businessType = BusinessType.INSERT)
//	@PostMapping("/addFile")
//	@ResponseBody
//	public AjaxResult addFile(@RequestParam("id") Long id, @RequestParam("file") MultipartFile[] files) {
//		Integer i = null;
//		try {
//			String filePath = RuoYiConfig.getUploadPath();
//			List<PetitionFile> petitionFileList = new ArrayList<>();
//			for (MultipartFile multipartFile: files) {
//				PetitionFile petitionFile = new PetitionFile();
//				String fileName = multipartFile.getOriginalFilename().replaceAll(" ","").replaceAll("&","");
//				String suffix = fileName.substring(fileName.lastIndexOf("."));
//				String uploadPath = FileUploadUtils.upload(filePath, multipartFile,suffix);
//				petitionFile.setAssociationId(id);
//				petitionFile.setType(1);
//				petitionFile.setFile(uploadPath);//存储
//				petitionFile.setFileName(fileName);//文件名
//				petitionFileList.add(petitionFile);
//			}
////			buildAttribute(id, OrderTypes.PETITION.value());
//			i = petitionService.insertRequisitionFile(petitionFileList);
//		} catch (GlobalException businessException) {
//			return error(businessException.getMessage());
//		} catch (Exception e) {
//			log.error("保存电子签呈附件出错,错误原因--->", e);
//			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
//		}
//		return success(i.toString());
//	}
//	/**
//	 * 上传保存附件
//	 */
//	@RequiresPermissions("system:petition:add")
//	@Log(title = "签呈文件", businessType = BusinessType.INSERT)
//	@PostMapping("/addFile/save")
//	@ResponseBody
//	public AjaxResult save(@RequestParam("petitionId") Long id, @RequestParam("file") MultipartFile[] files) throws IOException {
//		String filePath = RuoYiConfig.getUploadPath();
//		List<PetitionFile> petitionFileList = new ArrayList<>();
//		for (MultipartFile multipartFile: files) {
//			PetitionFile petitionFile = new PetitionFile();
//			String fileName = multipartFile.getOriginalFilename().replaceAll(" ","").replaceAll("&","");
//			String suffix = fileName.substring(fileName.lastIndexOf("."));
//			String uploadPath = FileUploadUtils.upload(filePath, multipartFile,suffix);
//			petitionFile.setAssociationId(id);
//			petitionFile.setType(1);
//			petitionFile.setFile(uploadPath);//存储
//			petitionFile.setFileName(fileName);//文件名
//			petitionFileList.add(petitionFile);
//		}
////		buildAttribute(id, OrderTypes.PETITION.value());
//		return toAjax(petitionService.insertRequisitionFile(petitionFileList));
//	}


//	@Log(title = "下载文件", businessType = BusinessType.OTHER)
//	@GetMapping("/downloadFile")
//	public void downloadFile(@RequestParam("id") Long id, @RequestParam("resource") String resource, HttpServletRequest request, HttpServletResponse response)
//			throws Exception
//	{
//		PetitionFile petitionFile =new PetitionFile();
//		petitionFile.setId(id);
//		petitionFile.setFile(resource);
//		List<PetitionFile> petitionFiles = petitionFileService.selectPetitionFileList(petitionFile);
//		String filePath = petitionFiles.get(0).getFile();
//		String fileName = petitionFiles.get(0).getFileName();
////		buildAttribute(id, OrderTypes.PETITION.value());
//		FileDownloadUtils.resourceDownload(filePath,fileName,request,response);
//	}

	/**
	 * 删除里面的文件
	 */
	@Log(title = "删除文件", businessType = BusinessType.UPDATE)
	@GetMapping("/removeFile")
	@ResponseBody
	public AjaxResult removeFile(String id,Long requisitionId)
	{
		petitionFileService.deletePetitionFileByIds(id);
//		buildAttribute(requisitionId, OrderTypes.PETITION.value());
		return success();
	}

	/**
	 * 开启审批流程
	 * 1 提交表单
	 */
	/**
	 * 提交表单
	 */
	@RequiresPermissions("system:petition:sub")
	@Log(title = "签呈审批单", businessType = BusinessType.UPDATE)
	@GetMapping("/submit")
	@ResponseBody
	public AjaxResult submit(@RequestParam("id") Long id)
	{
		try {
			SysUser sysUser = getSysUser();
			petitionService.submit(id,sysUser);
//			buildAttribute(id, OrderTypes.PETITION.value());
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("提交电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success(id.toString());
	}
	/**
	 * 进入审核界面
	 */
	@RequiresPermissions("system:petition:audit")
	@GetMapping("/audit/{id}")
	public String auditFW(@PathVariable("id") Long id, ModelMap mmap)
	{
		List<FormFile> petitionFiles = petitionService.selectFormFile(id, FormFileConstants.PETITION);
		mmap.put("petitionFiles", petitionFiles);
		Petition petition = petitionService.selectPetitionById(id);
		Integer status = petition.getStatus();
		
		//加签人员列表
		PetitionSign p=new PetitionSign();
		p.setPetitionId(id);
		p.setSignType(3);
		List<PetitionSign> petitionAddaudits = petitionSignService.selectPetitionSignList(p);
		petitionComponent.buildCheckBox(petition);
		
		
		//核准人员列表
		PetitionSign  ps=new PetitionSign();
		ps.setPetitionId(id);
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignService.selectPetitionSignList(ps);

		//会审人员列表
		PetitionSign  pt=new PetitionSign();
		pt.setPetitionId(id);
		pt.setSignType(2);
		List<PetitionSign> petitionTrialList = petitionSignService.selectPetitionSignList(pt);
		
		if (status.equals(PetitionStatus.WAIT_LAW.value())){
			mmap.put("petition", petition);
			mmap.put("petitionAddaudits",petitionAddaudits);
			mmap.put("typeMaps", petition.getParams());
			mmap.put("petitionSigns",petitionSigns);
			mmap.put("petitionTrialList",petitionTrialList);
			return prefix + "/auditFW";
		}else if (status.equals(PetitionStatus.WAIT_MANAGER.value())){
			mmap.put("petition", petition);
			mmap.put("petitionAddaudits",petitionAddaudits);
			mmap.put("typeMaps", petition.getParams());
			mmap.put("petitionSigns",petitionSigns);
			mmap.put("petitionTrialList",petitionTrialList);
			return prefix + "/auditManager";
		}else if (status.equals(PetitionStatus.WAIT_HR.value())){
			mmap.put("petition", petition);
			mmap.put("petitionAddaudits",petitionAddaudits);
			mmap.put("typeMaps", petition.getParams());
			mmap.put("petitionSigns",petitionSigns);
			mmap.put("petitionTrialList",petitionTrialList);
			return prefix + "/auditHR";
		}else if (status.equals(PetitionStatus.WAIT_FC_MANAGER.value())){
			mmap.put("petition", petition);
			mmap.put("petitionAddaudits",petitionAddaudits);
			mmap.put("typeMaps", petition.getParams());
			mmap.put("petitionSigns",petitionSigns);
			mmap.put("petitionTrialList",petitionTrialList);
			return prefix + "/auditcw";
		}else if (status.equals(PetitionStatus.C_WAIT_AUDIT.value())){
			mmap.put("petition", petition);
			mmap.put("petitionAddaudits",petitionAddaudits);
			mmap.put("typeMaps", petition.getParams());
			mmap.put("petitionSigns",petitionSigns);
			mmap.put("petitionTrialList",petitionTrialList);
			return prefix + "/auditAC";
		}
		else if (status.equals(PetitionStatus.WAIT_GM.value())){
			mmap.put("petition", petition);
			mmap.put("petitionAddaudits",petitionAddaudits);
			mmap.put("typeMaps", petition.getParams());
			mmap.put("petitionSigns",petitionSigns);
			mmap.put("petitionTrialList",petitionTrialList);
			return prefix + "/auditGM";
		}else if (status.equals(PetitionStatus.WAIT_CFCO_MANAGER.value())){
			mmap.put("petition", petition);
			mmap.put("petitionAddaudits",petitionAddaudits);
			mmap.put("typeMaps", petition.getParams());
			mmap.put("petitionSigns",petitionSigns);
			mmap.put("petitionTrialList",petitionTrialList);
			return prefix + "/auditcwManager";
		}else {
			mmap.put("petition", petition);
			mmap.put("petitionAddaudits",petitionAddaudits);
			mmap.put("petitionSigns",petitionSigns);
			mmap.put("petitionFiles", petitionFiles);
			mmap.put("typeMaps", petition.getParams());
			mmap.put("petitionTrialList",petitionTrialList);
			return prefix + "/detail";
		}
	}

	/**
	 * 法务审核结果提交
	 */
	@RequiresPermissions("system:petition:audit")
	@Log(title = "签呈审批法务审核结果", businessType = BusinessType.UPDATE)
	@PostMapping("/auditFW")
	@ResponseBody
	public AjaxResult auditFw(@RequestBody PetitionDto petitionDto)
	{
		try {
			SysUser sysUser = getSysUser();
			petitionService.updateFwAudit(petitionDto,sysUser);
//			buildAttribute(petitionDto.getPetition().getId(), OrderTypes.PETITION.value());
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("法务审核电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success();
	}

	/**
	 * 加签人员审核界面
	 */
	@RequiresPermissions("system:petition:audit")
	@GetMapping("/addaudit/{id}")
	public String adddaudit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Petition petition = petitionService.selectPetitionById(id);
		PetitionSign petitionAddaudit=new PetitionSign();
		petitionAddaudit.setPetitionId(id);
		petitionAddaudit.setSignType(3);
		List<PetitionSign> petitionAddaudits = petitionSignService.selectPetitionSignList(petitionAddaudit);
		mmap.put("petition", petition);
		mmap.put("petitionAddaudits",petitionAddaudits);
		
		List<FormFile> petitionFiles = petitionService.selectFormFile(id, FormFileConstants.PETITION);
		mmap.put("petitionFiles", petitionFiles);

		//核准人员列表
		PetitionSign  ps=new PetitionSign();
		ps.setPetitionId(id);
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignService.selectPetitionSignList(ps);
		mmap.put("petitionSigns",petitionSigns);
		
		//会审人员列表
		PetitionSign  pt=new PetitionSign();
		pt.setPetitionId(id);
		pt.setSignType(2);
		List<PetitionSign> petitionTrialList = petitionSignService.selectPetitionSignList(pt);
		mmap.put("petitionTrialList",petitionTrialList);
		
		petitionComponent.buildCheckBox(petition);
		mmap.put("typeMaps", petition.getParams());
		return prefix + "/addAudit";
	}

	/**
	 * 加签审核结果提交
	 */
	@RequiresPermissions("system:petition:audit")
	@Log(title = "签呈审批法务审核结果", businessType = BusinessType.UPDATE)
	@PostMapping("/addaudit")
	@ResponseBody
	public AjaxResult addaudit(@RequestBody PetitionDto petitionDto)
	{
		try {
			SysUser sysUser = getSysUser();
			petitionService.updateaddAudit(petitionDto,sysUser);
//			buildAttribute(petitionDto.getPetition().getId(), OrderTypes.PETITION.value());
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("加签审核电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success();
	}




	/**
	 * 主管审核结果提交
	 */
	@RequiresPermissions("system:petition:audit")
	@Log(title = "签呈审批主管审核结果", businessType = BusinessType.UPDATE)
	@PostMapping("/auditManager")
	@ResponseBody
	public AjaxResult auditSave(@RequestBody PetitionDto petitionDto)
	{
		try {
			SysUser sysUser = getSysUser();
			Integer i=petitionService.updateAudit(petitionDto,sysUser);
//			buildAttribute(petitionDto.getPetition().getId(), OrderTypes.PETITION.value());
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("主管审核电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success();
	}


	/**
	 * HR审核结果提交
	 */
	@RequiresPermissions("system:petition:auditHR")
	@Log(title = "签呈审批HR审核结果", businessType = BusinessType.UPDATE)
	@PostMapping("/auditHR")
	@ResponseBody
	public AjaxResult auditHR(@RequestBody PetitionDto petitionDto)
	{
		try {
			SysUser sysUser = getSysUser();
			Integer i=petitionService.updateAuditHR(petitionDto,sysUser);
//			buildAttribute(petitionDto.getPetition().getId(), OrderTypes.PETITION.value());
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("HR审核电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success();
	}

	/**
	 * 财务总账审核结果提交
	 */
	@RequiresPermissions("system:petition:auditcw")
	@Log(title = "签呈审批财务审核结果", businessType = BusinessType.UPDATE)
	@PostMapping("/auditcw")
	@ResponseBody
	public AjaxResult auditcw(@RequestBody PetitionDto petitionDto)
	{
		try {
			SysUser sysUser = getSysUser();
			petitionService.updateAuditcw(petitionDto,sysUser);
//			buildAttribute(petitionDto.getPetition().getId(), OrderTypes.PETITION.value());
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("财务总账审核电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success();
	}
	/**
	 * 财务经理审核结果提交
	 */
	@RequiresPermissions("system:petition:audit")
	@Log(title = "签呈审批财务审核结果", businessType = BusinessType.UPDATE)
	@PostMapping("/auditcwManager")
	@ResponseBody
	public AjaxResult auditcwManager(@RequestBody PetitionDto petitionDto)
	{
		try {
			SysUser sysUser = getSysUser();
			Integer i=petitionService.updateAuditcwManager(petitionDto,sysUser);
//			buildAttribute(petitionDto.getPetition().getId(), OrderTypes.PETITION.value());
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("财务经理审核电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success();
	}
	/**
	 * 审核人审核结果提交
	 */
	@RequiresPermissions("system:petition:audit")
	@Log(title = "签呈审批审核人审核结果", businessType = BusinessType.UPDATE)
	@PostMapping("/auditAC")
	@ResponseBody
	public AjaxResult auditAC(@RequestBody PetitionDto petitionDto)
	{
		try {
			SysUser sysUser = getSysUser();
			Integer i=petitionService.updateAuditAC(petitionDto,sysUser);
//			buildAttribute(petitionDto.getPetition().getId(), OrderTypes.PETITION.value());
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("审核人审核电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success();
	}

	/**
	 * 核准人审核结果提交
	 */
	@RequiresPermissions("system:petition:auditGM")
	@Log(title = "签呈审批总经理审核结果", businessType = BusinessType.UPDATE)
	@PostMapping("/auditGM")
	@ResponseBody
	public AjaxResult auditGM(@RequestBody PetitionDto petitionDto)
	{
		try {
			SysUser sysUser = getSysUser();
			Integer i=petitionService.updateAuditGM(petitionDto,sysUser);
//			buildAttribute(petitionDto.getPetition().getId(), OrderTypes.PETITION.value());
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("核准人审核电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success();
	}



	/**
	 * 新增核准人审核界面
	 */
	@RequiresPermissions("system:petition:audit")
	@GetMapping("/auditOtherGM/{id}")
	public String auditOtherGM(@PathVariable("id") Long id, ModelMap mmap)
	{
		Petition petition = petitionService.selectPetitionById(id);
		mmap.put("petition", petition);
		PetitionSign ps=new PetitionSign();
		ps.setPetitionId(id);
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignService.selectPetitionSignList(ps);
		mmap.put("petitionSigns",petitionSigns);

		PetitionSign pt=new PetitionSign();
		pt.setPetitionId(id);
		pt.setSignType(2);
		List<PetitionSign> petitionTrialList = petitionSignService.selectPetitionSignList(pt);
		mmap.put("petitionTrialList",petitionTrialList);
		
		List<FormFile> petitionFiles = petitionService.selectFormFile(id, FormFileConstants.PETITION);
		mmap.put("petitionFiles", petitionFiles);


		//加签人员列表
		PetitionSign p=new PetitionSign();
		p.setPetitionId(id);
		p.setSignType(3);
		List<PetitionSign> petitionAddaudits = petitionSignService.selectPetitionSignList(p);
		mmap.put("petitionAddaudits",petitionAddaudits);

		petitionComponent.buildCheckBox(petition);
		mmap.put("typeMaps", petition.getParams());
		
		return prefix + "/addSign";
	}


	/**
	 * 新增核准人审核结果提交
	 */
	@RequiresPermissions("system:petition:audit")
	@Log(title = "签呈审批新增核准人审核结果", businessType = BusinessType.UPDATE)
	@PostMapping("/addSign")
	@ResponseBody
	public AjaxResult addSign(@RequestBody PetitionDto petitionDto)
	{
		try {
			SysUser sysUser = getSysUser();
			Integer i=petitionService.updateaddSign(petitionDto,sysUser);
//			buildAttribute(petitionDto.getPetition().getId(), OrderTypes.PETITION.value());
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("新增核准人审核电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success();
	}

	/**
	 * 新增会审人审核界面
	 */
	@RequiresPermissions("system:petition:audit")
	@GetMapping("/auditOtherPT/{id}")
	public String auditOtherPT(@PathVariable("id") Long id, ModelMap mmap)
	{
		Petition petition = petitionService.selectPetitionById(id);
		PetitionSign ps=new PetitionSign();
		ps.setPetitionId(id);
		ps.setSignType(1);
		List<PetitionSign> petitionSigns = petitionSignService.selectPetitionSignList(ps);
		mmap.put("petitionSigns",petitionSigns);
		
		mmap.put("petition", petition);
		PetitionSign pt=new PetitionSign();
		pt.setPetitionId(id);
		pt.setSignType(2);
		List<PetitionSign> petitionTrialList = petitionSignService.selectPetitionSignList(pt);
		mmap.put("petitionTrialList",petitionTrialList);
		
		List<FormFile> petitionFiles = petitionService.selectFormFile(id, FormFileConstants.PETITION);
		mmap.put("petitionFiles", petitionFiles);


		//加签人员列表
		PetitionSign p=new PetitionSign();
		p.setPetitionId(id);
		p.setSignType(3);
		List<PetitionSign> petitionAddaudits = petitionSignService.selectPetitionSignList(p);
		mmap.put("petitionAddaudits",petitionAddaudits);

		petitionComponent.buildCheckBox(petition);
		mmap.put("typeMaps", petition.getParams());

		return prefix + "/addTrial";
	}


	/**
	 * 新增会审人审核结果提交
	 * @param petitionDto
	 * @return
	 */
	@RequiresPermissions("system:petition:audit")
	@Log(title = "签呈审批新增会审人审核结果", businessType = BusinessType.UPDATE)
	@PostMapping("/addTrial")
	@ResponseBody
	public AjaxResult addTrial(@RequestBody PetitionDto petitionDto)
	{
		try {
			SysUser sysUser = getSysUser();
			Integer i=petitionService.updateaddTrial(petitionDto,sysUser);
//			buildAttribute(petitionDto.getPetition().getId(), OrderTypes.PETITION.value());
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("会审人审核电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success();
	}

	/**
	 * 进入审批历史页面
	 */
	@RequestMapping("/audithistory/{id}")
	public String auditHistory(@PathVariable("id") Long id, ModelMap mmap)
	{
		mmap.put("id", id);
		return prefix + "/audithistory";
	}

	/**
	 * 审批历史相关数据
	 */
	@PostMapping("/auditHistoryList")
	@ResponseBody
	public TableDataInfo auditHistoryList(ProcessFlow processFlow) {
		processFlow.setOrderId(processFlow.getOrderId());
		startPage();
		List<ProcessFlow> list = processFlowService.selectProcessFlowList(processFlow);
		return getDataTable(list);
	}


//	@Log(title = "导出PDF", businessType = BusinessType.OTHER)
//	@GetMapping("/exportPDF/{id}")
//	public void exportPDF(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
//		ArrayList<String> arrayList = new ArrayList<>();
//		Page page = null;
//		Browser browser = null;
//		String uuid = UUID.randomUUID().toString();
//		//生成pdf必须在无厘头模式下才能生效
//		String path = "D:\\puppeteer\\node_modules\\puppeteer\\.local-chromium\\win64-782078\\chrome-win\\chrome.exe";
//		LaunchOptions options = new LaunchOptionsBuilder().withArgs(arrayList).withHeadless(true).withExecutablePath(path).build();
//		arrayList.add("--no-sandbox");
//		arrayList.add("--disable-setuid-sandbox");
//		try {
//			browser = Puppeteer.launch(options);
//			page = browser.newPage();
//			page.goTo("http://127.0.0.1:8060/api/system/petitionApp/detail/"+ id +"?secret=122acdsdbgfdgffdddd");
//			PDFOptions pdfOptions = new PDFOptions();
//			page.setDefaultTimeout(1000);
//			pdfOptions.setPath("E:\\java\\pdfDownload\\" + uuid + ".pdf");
//			pdfOptions.setFormat("a4");
//			page.pdf(pdfOptions);
//		}catch (Exception e){
//			log.error("电子签呈生成PDF页面失败,错误如下--->",e);
//			throw new GlobalException("生成PDF页面失败！请稍后重试。");
//		}finally {
//			if (StringUtils.isNotNull(page)) {
//				try {
//					page.close();
//				}catch (Exception e){
//					log.error("电子签呈生成PDF页面失败,错误如下--->",e);
//					throw new GlobalException("生成PDF页面失败！请稍后重试。");
//				}
//			}
//			if (StringUtils.isNotNull(browser)) {
//				browser.close();
//			}
//		}
//
//		String url = "E:\\java\\pdfDownload\\" + uuid + ".pdf";
//		File fileurl = new File(url);
//		try{
//			//根据条件得到文件路径
////			System.out.println("===========文件路径==========="+fileurl);
//			//将文件读入文件流
//			InputStream inStream = new FileInputStream(fileurl);
//			//获得浏览器代理信息
//			final String userAgent = request.getHeader("USER-AGENT");
//			//判断浏览器代理并分别设置响应给浏览器的编码格式
//			String finalFileName = null;
//			if(StringUtils.contains(userAgent, "MSIE")|| StringUtils.contains(userAgent,"Trident")){//IE浏览器
//				finalFileName = URLEncoder.encode("Petition.pdf","UTF8");
//				System.out.println("IE浏览器");
//			}else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
//				finalFileName = new String("Petition.pdf".getBytes(), "ISO8859-1");
//			}else{
//				finalFileName = URLEncoder.encode("Petition.pdf","UTF8");//其他浏览器
//			}
//			//设置HTTP响应头
//			response.reset();//重置 响应头
//			response.setContentType("application/x-download");//告知浏览器下载文件，而不是直接打开，浏览器默认为打开
//			response.addHeader("Content-Disposition" ,"attachment;filename=\"" +finalFileName+ "\"");//下载文件的名称
//
//			// 循环取出流中的数据
//			byte[] b = new byte[1024];
//			int len;
//			while ((len = inStream.read(b)) > 0){
//				response.getOutputStream().write(b, 0, len);
//			}
//			inStream.close();
//			response.getOutputStream().close();
//		}catch(Exception e) {
//			log.error("电子签呈下载PDF页面失败,错误如下--->",e);
//			throw new GlobalException("打印PDF失败，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
//		}
//		fileurl.delete();
//	}

	//自动补全搜索框
	@GetMapping("/getAlluser")
	@ResponseBody
	public AjaxResult getAlluser(SysUser u){
		List<SysUser> users = userService.selectUserList(u);
		List<String> value=new ArrayList<>();
		for (SysUser user : users) {
			String userName = user.getUserName();
			value.add(userName);
		}
		AjaxResult ajaxResult=new AjaxResult();
		ajaxResult.put("value",value);
		return ajaxResult;
	}


	/**
	 * 提交表单
	 */
	@PostMapping("/revent")
	@ResponseBody
	public AjaxResult revent(@RequestParam("id") Long id, @RequestParam("cancelRemark") String cancelRemark)
	{
		try {
			SysUser sysUser = ShiroUtils.getSysUser();
			petitionService.WithdrawalOfInitiation(id,cancelRemark,sysUser);
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("提交电子签呈出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success(id.toString());
	}

	/**
	 * 进入撤回页面
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/cancel/{id}")
	public String forCancel(@PathVariable("id") Long id, ModelMap mmap)
	{
		mmap.put("id", id);
		return prefix + "/cancel";
	}

	/**
	 * 进入审批截图新增附件页面
	 */
	@RequiresPermissions("system:petition:edit")
	@GetMapping("/addCloseFile/{id}")
	public String addCloseFile(@PathVariable("id") Long id, ModelMap mmap)
	{
		mmap.put("petitionId", id);
		return prefix + "/addCloseFile";
	}

	/**
	 * 上传审批截图
	 * @param file 文件
	 * @param petitionId 请款单id
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions("system:petition:edit")
	@PostMapping("/addCloseFile/save")
	@ResponseBody
	public AjaxResult addClosePaymentRequestFileSave(@RequestParam("file") MultipartFile file,
													 @RequestParam("petitionId") long petitionId) {
		try {
			petitionService.insertPetitionFile(file, petitionId, ShiroUtils.getSysUser());
		} catch (GlobalException businessException) {
			return error(businessException.getMessage());
		} catch (Exception e) {
			log.error("电子签呈上传审批截图出错,错误原因--->", e);
			return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
		}
		return success();
	}

//	/**
//	 * 下载截图凭证
//	 * @param id 请款单id
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	@GetMapping("/download/ClosePaymentRequestFile")
//	public void downloadClosePaymentRequestFile(@RequestParam("id") Long id,
//                                                HttpServletRequest request, HttpServletResponse response)throws Exception
//	{
//		 Petition petition= new Petition();
//		petition.setId(id);
//		Petition petitions = petitionService.selectPetitionById(petition.getId());
//		String filePath = petitions.getCloseFile();
//		String fileName = petitions.getCloseFileName();
//		FileDownloadUtils.resourceDownload(filePath,fileName,request,response);
//	}
}
