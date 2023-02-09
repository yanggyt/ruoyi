package com.ruoyi.web.controller.system;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.FormFileConstants;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.OrderTypes;
import com.ruoyi.common.enums.PaymentRequestStatus;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.FormFile;
import com.ruoyi.system.domain.PaymentAccount;
import com.ruoyi.system.domain.ProcessFlow;
import com.ruoyi.system.domain.paymentRequest.PaymentAuditResults;
import com.ruoyi.system.domain.paymentRequest.PaymentRequest;
import com.ruoyi.system.domain.paymentRequest.PaymentRequestDt1;
import com.ruoyi.system.domain.paymentRequest.PaymentRequestFile;
import com.ruoyi.system.domain.requisition.Requisition;
import com.ruoyi.system.dto.paymentRequest.PaymentRequestDt1Dto;
import com.ruoyi.system.service.IFormFileService;
import com.ruoyi.system.service.IPaymentAccountService;
import com.ruoyi.system.service.IProcessFlowService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.paymentRequest.IPaymentRequestDt1Service;
import com.ruoyi.system.service.paymentRequest.IPaymentRequestFileService;
import com.ruoyi.system.service.paymentRequest.IPaymentRequestService;
import com.ruoyi.system.component.paymentRequest.PaymentRequestComponent;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ruoyi.common.utils.CacheUtils.getCacheKey;
import static com.ruoyi.common.utils.CacheUtils.getCacheName;


/**
 * 请款单Controller
 * 
 * @author SKaiL
 * @date 2022-09-21
 */
@Controller
@RequestMapping("/system/paymentRequest")
public class PaymentRequestController extends BaseController
{
    private String prefix = "system/paymentRequest";

    private static final Logger log = LoggerFactory.getLogger(PaymentRequestController.class);

    @Autowired
    private IPaymentRequestService paymentRequestService;

    @Autowired
    private IPaymentAccountService paymentAccountService;

    @Autowired
    private PaymentRequestComponent paymentRequestComponent;

    @Autowired
    private IPaymentRequestDt1Service paymentRequestDt1Service;

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private IPaymentRequestFileService iPaymentRequestFileService;

    @Autowired
    private IProcessFlowService processFlowService;

    @Autowired
    private IFormFileService formFileService;

//    @Autowired
//    private RequisitionComponent requisitionComponent;

    @RequiresPermissions("system:paymentRequest:view")
    @GetMapping()
    public String paymentRequest()
    {
        return prefix + "/paymentRequest";
    }

    /**
     * 查询请款单列表
     */
    @RequiresPermissions("system:paymentRequest:listAll")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PaymentRequest paymentRequest)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roles = sysUser.getRoles().stream().filter(r -> r.getRoleKey().equals("FinanceAudit")).collect(Collectors.toList());
        if (!sysUser.isAdmin() && StringUtils.isEmpty(roles) &&
                !sysUser.getUserCode().contains("S00001") &&
                !sysUser.getUserCode().contains("S00002") &&
                !sysUser.getUserCode().contains("S00003")) {
            //只能查看本部门
//            paymentRequest.setDept(sysUser.getDept().getDeptName().trim());
            //查看自己的
            paymentRequest.setEmployeeNo(sysUser.getUserCode());
        }
        List<PaymentRequest> list = paymentRequestService.selectPaymentRequestList(paymentRequest);
        paymentRequestComponent.buildList(list);
        return getDataTable(list);
    }

    @RequiresPermissions("system:paymentRequest:view")
    @GetMapping("/todo")
    public String paymentRequestToDo() {
        return prefix + "/paymentRequestToDo";
    }


    /**
     * 我的待办
     */
    @RequiresPermissions("system:paymentRequest:list")
    @PostMapping("/list/todo")
    @ResponseBody
    public TableDataInfo listToDo(PaymentRequest paymentRequest) {
        SysUser sysUser = ShiroUtils.getSysUser();
        startPage();
        if (!sysUser.isAdmin()) {
            paymentRequest.setSendToCode(ShiroUtils.getSysUser().getUserCode());
        }
        List<PaymentRequest> list = paymentRequestService.selectPaymentRequestList(paymentRequest);
        paymentRequestComponent.buildList(list);
        return getDataTable(list);
    }

    @RequiresPermissions("system:paymentRequest:view")
    @GetMapping("/do")
    public String paymentRequestDo() {
        return prefix + "/paymentRequestDo";
    }

    /**
     * 我的已办
     */
    @RequiresPermissions("system:paymentRequest:list")
    @PostMapping("/list/do")
    @ResponseBody
    public TableDataInfo listDo(PaymentRequest paymentRequest) {
        SysUser sysUser = ShiroUtils.getSysUser();
        if (!sysUser.isAdmin()) {
            paymentRequest.setHandlesCode(sysUser.getUserCode());
        }
        startPage();
        List<PaymentRequest> list = paymentRequestService.selectPaymentRequestList(paymentRequest);
        paymentRequestComponent.buildList(list);
        return getDataTable(list);
    }

    /**
     * 我的创建
     */
    @RequiresPermissions("system:paymentRequest:view")
    @GetMapping("/create")
    public String requisitionCreate() {
        return prefix + "/paymentRequestCreate";
    }

    @RequiresPermissions("system:paymentRequest:list")
    @PostMapping("/list/create")
    @ResponseBody
    public TableDataInfo listCreate(PaymentRequest paymentRequest) {
        SysUser sysUser = ShiroUtils.getSysUser();
        if (!sysUser.isAdmin()) {
            paymentRequest.setEmployeeNo(ShiroUtils.getSysUser().getUserCode());
        }
        startPage();
        List<PaymentRequest> list = paymentRequestService.selectPaymentRequestList(paymentRequest);
        paymentRequestComponent.buildList(list);
        return getDataTable(list);
    }

    /**
     * 我的草稿
     */
    @RequiresPermissions("system:paymentRequest:view")
    @GetMapping("/draft")
    public String paymentRequestDraft() {
        return prefix + "/paymentRequestDraft";
    }

    /**
     * 我的草稿
     */
    @RequiresPermissions("system:paymentRequest:list")
    @PostMapping("/list/draft")
    @ResponseBody
    public TableDataInfo listDraft(PaymentRequest paymentRequest) {
        SysUser sysUser = ShiroUtils.getSysUser();
        if (!sysUser.isAdmin()) {
            paymentRequest.setEmployeeNo(ShiroUtils.getSysUser().getUserCode());
        }
        startPage();
        List<PaymentRequest> list = paymentRequestService.selectPaymentRequestListDraft(paymentRequest);
        paymentRequestComponent.buildList(list);
        return getDataTable(list);
    }

    /**
     * 导出请款单列表
     */
    @RequiresPermissions("system:paymentRequest:export")
    @Log(title = "请款单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PaymentRequest paymentRequest)
    {
        List<PaymentRequest> list = paymentRequestService.selectPaymentRequestList(paymentRequest);
        ExcelUtil<PaymentRequest> util = new ExcelUtil<PaymentRequest>(PaymentRequest.class);
        return util.exportExcel(list, "请款单数据");
    }

    /**
     * 新增请款单
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        PaymentAccount paymentAccount = new PaymentAccount();
        paymentAccount.setCreateBy(sysUser.getUserCode());
        mmap.put("user", sysUser);
        mmap.put("projectCode", CacheUtils.get(getCacheName(Constants.ZT_WGCPRORELEASE_CACHE), getCacheKey(Constants.ZT_WGCPRORELEASE_KEY,"proCode")));
        return prefix + "/add";
    }

    /**
     * 新增保存请款单
     */
    @RequiresPermissions("system:paymentRequest:add")
    @Log(title = "请款单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody PaymentRequest paymentRequest)
    {
        return paymentRequestService.insertPaymentRequest(paymentRequest,getSysUser());
    }

    /**
     * 查询收款人信息
     *
     * @return
     */
    @GetMapping("/selectPaymentInfo")
    public String selectPaymentInfo() {
        return prefix + "/paymentInfoList";
    }

    /**
     * 查询收款人信息
     *
     * @return
     */
    @PostMapping("/selectPaymentInfoList")
    @ResponseBody
    public TableDataInfo selectPaymentInfoList(PaymentAccount paymentAccount) {
        //payee有值代表点击的全文搜索
        if (StringUtils.isEmpty(paymentAccount.getPayee())) {
            if (!getSysUser().isAdmin()) {
                paymentAccount.setCreateBy(getSysUser().getUserCode());
            }
        }
        startPage();
        List<PaymentAccount> payeeList = paymentAccountService.selectPaymentAccountList(paymentAccount);
        return getDataTable(payeeList);
    }

    /**
     * 修改请款单
     */
    @RequiresPermissions("system:paymentRequest:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PaymentRequest paymentRequest = paymentRequestService.selectPaymentRequestById(id);
        PaymentAccount paymentAccount = new PaymentAccount();
        paymentAccount.setParentId(id);
        PaymentRequestDt1 paymentRequestDt1 = new PaymentRequestDt1();
        paymentRequestDt1.setRequestId(id);
        List<PaymentRequestDt1> paymentRequestDt1List = paymentRequestDt1Service.selectPaymentRequestDt1List(paymentRequestDt1);
        //历史付款账号信息
        PaymentAccount paymentAccounts = new PaymentAccount();
        paymentAccounts.setCreateBy(paymentRequest.getCreateByCode());
        mmap.put("payeeList", paymentAccountService.selectPaymentAccountList(paymentAccounts));
        mmap.put("paymentRequest", paymentRequest);
        mmap.put("paymentRequestDt1List", paymentRequestDt1List);
        mmap.put("projectCode", CacheUtils.get(getCacheName(Constants.ZT_WGCPRORELEASE_CACHE), getCacheKey(Constants.ZT_WGCPRORELEASE_KEY,"proCode")));
        return prefix + "/edit";
    }

    /**
     * 修改保存请款单
     */
    @RequiresPermissions("system:paymentRequest:edit")
    @Log(title = "请款单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody PaymentRequest paymentRequest)
    {
        return paymentRequestService.updatePaymentRequest(paymentRequest);
    }

    /**
     * 复制请款
     */
    @GetMapping("/copy/{id}")
    public String copy(@PathVariable("id") Long id, ModelMap mmap) {
        PaymentRequest paymentRequest = paymentRequestService.selectPaymentRequestById(id);
        PaymentAccount paymentAccount = new PaymentAccount();
        paymentAccount.setParentId(id);
        PaymentRequestDt1 paymentRequestDt1 = new PaymentRequestDt1();
        paymentRequestDt1.setRequestId(id);
        List<PaymentRequestDt1> paymentRequestDt1List = paymentRequestDt1Service.selectPaymentRequestDt1List(paymentRequestDt1);
        PaymentAccount paymentAccounts = new PaymentAccount();
        paymentAccounts.setCreateBy(paymentRequest.getCreateByCode());
        //历史付款账号信息
        mmap.put("payeeList", paymentAccountService.selectPaymentAccountList(paymentAccounts));
        mmap.put("paymentRequest", paymentRequest);
        mmap.put("paymentRequestDt1List", paymentRequestDt1List);
        mmap.put("projectCode", CacheUtils.get(getCacheName(Constants.ZT_WGCPRORELEASE_CACHE), getCacheKey(Constants.ZT_WGCPRORELEASE_KEY,"proCode")));
        return prefix + "/addCopy";
    }

    /**
     * 进行提交
     */
    @Log(title = "请款单提交", businessType = BusinessType.OTHER)
    @GetMapping("/start/submit")
    @ResponseBody
    public AjaxResult startSubmit(@RequestParam("id") Long id) {

            PaymentRequest paymentRequest = paymentRequestService.selectPaymentRequestById(id);
            if(!getSysUser().isAdmin()&&!Objects.equals(paymentRequest.getSendToCode(),getSysUser().getUserCode())){
                throw new GlobalException("提交失败！只允许申请人操作。");
            }
            SysUser sysUser = getSysUser();
            return paymentRequestService.submitProcess(id, sysUser);
    }

    /**
     * 进入审核页面
     */
    @GetMapping("/review/{id}")
    public String review(@PathVariable("id") Long id, ModelMap mmap) {
        PaymentRequest paymentRequest = paymentRequestService.selectPaymentRequestById(id);
        PaymentAccount paymentAccount = new PaymentAccount();
        paymentAccount.setParentId(id);
        SysUser user = new SysUser();
        user.setStatus("0");
        List<SysUser> userList = iSysUserService.selectUserList(user);
        Iterator<SysUser> iterator = userList.iterator();
        while (iterator.hasNext()) {
            SysUser next = iterator.next();
            //不是S开头人员
            if (StringUtils.isEmpty(next.getUserCode())) {
                iterator.remove();
            } else if (!Objects.equals(next.getUserCode().substring(0, 1), "S")) {
                iterator.remove();
            }
        }
//        mmap.put("userList", userList);
        PaymentRequestDt1 paymentRequestDt1 = new PaymentRequestDt1();
        paymentRequestDt1.setRequestId(id);
        List<PaymentRequestDt1> paymentRequestDt1List = paymentRequestDt1Service.selectPaymentRequestDt1List(paymentRequestDt1);
        paymentRequestComponent.approvalProgress(paymentRequest);
        mmap.put("paymentRequest", paymentRequest);
        mmap.put("paymentRequestDt1List", paymentRequestDt1List);
        ProcessFlow processFlow = new ProcessFlow();
        processFlow.setOrderId(id);
        processFlow.setOrderType(OrderTypes.PAYMENT_REQUEST.getCode());
        List<ProcessFlow> lists = processFlowService.selectProcessFlowListByOrderId(id,OrderTypes.PAYMENT_REQUEST.getCode());
        mmap.put("lists", lists);
//        mmap.put("progressbar", paymentRequestComponent.approvalProgress(lists, toBeReviewed, paymentRequest));
        FormFile formFile = new FormFile();
        formFile.setFileType(FormFileConstants.PAYMENTREQUEST);
        formFile.setParentId(id);
        List<FormFile> formFiles = formFileService.selectFormFileList(formFile);
        mmap.put("listFile", formFiles);
        return prefix + "/review";
    }
    /**
     * 删除请款单
     */
    @RequiresPermissions("system:paymentRequest:remove")
    @Log(title = "请款单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(paymentRequestService.deletePaymentRequestByIds(ids));
    }

    /**
     * 通用审核提交
     *
     * @param paymentAuditResults 审核信息
     * @return
     */
    @Log(title = "请款单审核提交", businessType = BusinessType.OTHER)
    @PostMapping("/supervisor/auditSubmit")
    @ResponseBody
    public AjaxResult paymentRequestAuditSubmit(@RequestBody PaymentAuditResults paymentAuditResults) {
        try {
            forReview(paymentAuditResults.getPaymentRequestId());
            paymentRequestService.auditSubmit(paymentAuditResults, getSysUser(),null);
        } catch (GlobalException businessException){
            return error(businessException.getMessage());
        } catch (Exception e){
            log.error("审核提交请款单出错,错误原因--->", e);
            return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
        }
        return success();
    }

    @GetMapping(value = {"/filePage/{type}/{flag}","/filePage/{id}/{type}/{flag}"})
    public String filePage(@PathVariable(value = "id",required = false) Long id, @PathVariable("type") Integer type,
                           @PathVariable("flag") Boolean flag, ModelMap mmap)
    {
        mmap.put("paymentRequestId", id);
        //文件类型 11使用文档, 1请购单附件
        mmap.put("type", type);
        mmap.put("flag", flag);
        //模版不允许普通用户删除
        if (Objects.equals(FormFileConstants.PAYMENTREQUEST_TEMPLATE, type)){
            if (getSysUser().isAdmin()){
                mmap.put("flag", true);
            } else {
                mmap.put("flag", false);
            }
        }
        return prefix + "/filePage";
    }

    /**
     * 查询filePage页面数据
     */
    /**
     * 查询文件列表
     */
    @PostMapping("/filePageList")
    @ResponseBody
    public TableDataInfo filePage(@RequestParam(value = "paymentRequestId",required = false) Long paymentRequestId,
                                  @RequestParam("type") Integer type)
    {
        return getDataTable(paymentRequestService.selectFormFile(paymentRequestId, type));
    }


//    @GetMapping("/downloadFile")
//    public void downloadFile(@RequestParam("fileName") String fileName, @RequestParam("filePath") String filePath, HttpServletRequest request, HttpServletResponse response)
//    {
////        FileDownloadUtils.resourceDownload(filePath, fileName, request, response);
//    }


    /**
     * 请款单附件删除
     * @param id
     * @param file
     * @param paymentRequestId
     * @return
     */
    @Log(title = "请款单附件删除", businessType = BusinessType.UPDATE)
    @GetMapping("/remove/file")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult removeFile(String id, String file, Long paymentRequestId) {
       return  null;
    }

    /**
     * 进入新增附件页面
     */
    @GetMapping("/addFile/{id}")
    public String forFaReportUpload(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("paymentRequestId", id);
        return prefix + "/addFile";
    }
    /**
     * 上传文件
     */
    @PostMapping("/addFile/save")
    @ResponseBody
    public AjaxResult addFileSave(@RequestParam("file") MultipartFile file, @RequestParam("paymentRequestId") Long paymentRequestId) throws IOException {
        return success();
    }

    /**
     * 查看相关模板
     *
     * @return
     */
    @GetMapping("/templatePage")
    public String templatePage() {
        return prefix + "/templatePage";
    }

    /**
     * 相关模板列表
     */
    @PostMapping("/templateList")
    @ResponseBody
    public TableDataInfo templatePageList(FormFile formFile) {
        formFile.setFileType(11);
        startPage();
        List<FormFile> list = formFileService.selectFormFileList(formFile);
        return getDataTable(list);
    }

    /**
     * 进入新增模板页面
     */
    @GetMapping("/addTemplate")
    public String forTemplateUpload(ModelMap mmap) {
        return prefix + "/addTemplate";
    }

    /**
     * 新增相关模板
     */
   /* @Log(title = "请款单模版新增", businessType = BusinessType.INSERT)
    @PostMapping("/addTemplate/save")
    @ResponseBody
    public AjaxResult addtemplateSave(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = RuoYiConfig.getUploadPath();
        String fileName = file.getOriginalFilename().replaceAll(" ","").replaceAll("&","");
        String suffix = fileName.substring(fileName.lastIndexOf("."));
//        String filePath1 = FileUploadUtils.upload(filePath, file,suffix);
        PaymentRequestFile paymentRequestFile = new PaymentRequestFile();
        paymentRequestFile.setType(2);
//        paymentRequestFile.setFile(filePath1);
        paymentRequestFile.setFileName(fileName);
        iPaymentRequestFileService.insertPaymentRequestFile(paymentRequestFile);
        return toAjax(true);
    }*/

    /**
     * 删除相关模板
     */
    @RequiresPermissions("system:paymentRequest:templateOperation")
    @Log(title = "请款单模版删除", businessType = BusinessType.UPDATE)
    @GetMapping("/remove/templateFile")
    @ResponseBody
    public AjaxResult removeTemplate(Long id) {
        FormFile formFile = formFileService.selectFormFileById(id);
//        PaymentRequestFile paymentRequestFile = iPaymentRequestFileService.selectPaymentRequestFileById(id);
        String filePath = RuoYiConfig.getUploadPath();
        boolean b = FileUtils.deleteFile(filePath + formFile.getFilePath());
        if (b) {
            formFileService.deleteFormFileByIds(id.toString());
//            iPaymentRequestFileService.deletePaymentRequestFileByIds(id.toString());
            return success("删除成功");
        } else {
            return error("删除失败");
        }
    }

    @GetMapping("/downloadFileDescription")
    public void downloadFileDescription(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FormFile formFile = new FormFile();
        formFile.setFileType(11);
        List<FormFile> formFiles = formFileService.selectFormFileList(formFile);
        String filePath = formFiles.get(0).getFilePath();
        String fileName = formFiles.get(0).getFileName();
        try
        {
            if (!FileUtils.checkAllowDownload(filePath))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", filePath));
            }
            // 本地资源路径
            String localPath = RuoYiConfig.getUploadPath();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(filePath, Constants.RESOURCE_PREFIX) + filePath;
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            if(StringUtils.isNotEmpty(fileName)){
                downloadName = fileName;
            }
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 详情页
     */
    @GetMapping("/detail/{id}/{type}")
    public String deatil(@PathVariable("id") Long id, @PathVariable("type") Integer type, ModelMap mmap) {
        PaymentRequest paymentRequest = paymentRequestService.selectPaymentRequestById(id);
        PaymentAccount paymentAccount = new PaymentAccount();
        paymentAccount.setParentId(id);
        PaymentRequestDt1 paymentRequestDt1 = new PaymentRequestDt1();
        paymentRequestDt1.setRequestId(id);
        List<PaymentRequestDt1> paymentRequestDt1List = paymentRequestDt1Service.selectPaymentRequestDt1List(paymentRequestDt1);
        paymentRequestComponent.approvalProgress(paymentRequest);
        mmap.put("paymentRequest", paymentRequest);
        mmap.put("paymentRequestDt1List", paymentRequestDt1List);
        ProcessFlow processFlow = new ProcessFlow();
        processFlow.setOrderId(id);
        processFlow.setOrderType(OrderTypes.PAYMENT_REQUEST.getCode());
        List<ProcessFlow> lists = processFlowService.selectProcessFlowListByOrderId(id,OrderTypes.PAYMENT_REQUEST.getCode());
        mmap.put("lists", lists);
        FormFile formFile = new FormFile();
        formFile.setFileType(FormFileConstants.PAYMENTREQUEST);
        formFile.setParentId(id);
        List<FormFile> formFiles = formFileService.selectFormFileList(formFile);
        mmap.put("listFile", formFiles);
        mmap.put("isClose", "0");

        //陈总审核暂时上传审批截图按钮
        List<ProcessFlow> collect = lists.stream().filter(pf -> (Objects.equals(paymentRequest.getEmployeeNo(), getSysUser().getUserCode())
                || getSysUser().isAdmin()) && Objects.equals(pf.getCreateByCode(), "S00001")
                && StringUtils.isNull(pf.getAuditResult())).collect(Collectors.toList());
        if (StringUtils.isNotEmpty(collect) && Objects.equals(collect.get(0).getStatus(), paymentRequest.getStatus())){
            mmap.put("isClose", "1");
        }
        if (Objects.equals(type, 2)){
            //展示审批截图
            if (paymentRequest.getStatus() == 21 && StringUtils.isNotEmpty(paymentRequest.getCloseFile())) {
                String url = paymentRequest.getCloseFile();
                if (url.contains(".jpg") || url.contains(".png")) {
                    mmap.put("url", url);
                }
            }
            return prefix + "/detailPDF";
        } else {
            return prefix + "/detail";
        }
    }

    /**
     * 请款单撤回
     *
     * @param id 请款单id
     * @return
     */
    @GetMapping("/withdrawal")
    @ResponseBody
    public AjaxResult Withdrawal(Long id) {
        return  paymentRequestService.WithdrawalOfInitiation(id);
    }


    /**
     * 上传签核截图页面
     * @param id 请款单id
     * @param mmap
     * @return
     */
    @RequiresPermissions("system:paymentRequest:edit")
    @GetMapping("/addClosePaymentRequestFile/{paymentRequestId}")
    public String forAddClosePaymentRequestFileUpload(@PathVariable("paymentRequestId") String id, ModelMap mmap)
    {
        mmap.put("paymentRequestId", id);
        return prefix + "/closePaymentRequestFile";
    }
    /**
     * 上传签核截图
     * @param file 文件
     * @param paymentRequestIds 请款单id
     * @return
     * @throws IOException
     */
    @RequiresPermissions("system:paymentRequest:edit")
    @PostMapping("/addClosePaymentRequestFile/save")
    @ResponseBody
    public AjaxResult addClosePaymentRequestFileSave(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("paymentRequestId") String paymentRequestIds,
                                                     @RequestParam("type") Integer type) {
            return   paymentRequestService.closePaymentRequestFile(file, paymentRequestIds, type, ShiroUtils.getSysUser());

    }

    /**
     * 下载截图凭证
     * @param id 请款单id
     * @param resource 文件地址
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/download/ClosePaymentRequestFile")
    public void downloadClosePaymentRequestFile(@RequestParam("id") Long id, @RequestParam("resource") String resource,
                                                HttpServletRequest request, HttpServletResponse response)throws Exception
    {

    }

    /**
     * 验证当前用户有无审核权限
     * @param id 请款单id
     */
    public void forReview(Long id) {
        PaymentRequest paymentRequest = paymentRequestService.selectPaymentRequestById(id);
        if (!Objects.equals(paymentRequest.getSendToCode(),getSysUser().getUserCode()) &&
                !getSysUser().isAdmin() && !paymentRequest.getSendToCode().contains(getSysUser().getUserCode())) {
            throw new GlobalException("已经签核完成，请签核下一单");
        }
    }

    /**
     * 更改待审核人
     * @param paymentRequestId
     * @return
     */
    @RequiresPermissions("system:paymentRequest:changeReviewer")
    @PostMapping("/changeReviewer")
    @ResponseBody
    public AjaxResult changeReviewer(@RequestParam("paymentRequestId") Long paymentRequestId,
                                     @RequestParam("sendToCode") String sendToCode){
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setId(paymentRequestId);
        paymentRequest.setSendToCode(sendToCode);
        paymentRequestService.updatePaymentRequest(paymentRequest);
        return success();
    }

    /**
     * 批量审核界面
     * @param ids
     * @param map
     * @return
     */
    @GetMapping("/batchReview/{ids}")
    public String batchReview(@PathVariable("ids") String ids, ModelMap map){
        map.put("ids", ids);
        return prefix + "/batchReview";
    }

    /**
     * 批量审核
     * @param auditResults 审核信息
     * @return
     */
    @PostMapping("/batchReview")
    @ResponseBody
    public AjaxResult batchReview(@RequestBody PaymentAuditResults auditResults){
            return success(paymentRequestService.paymentRequestBatchReview(auditResults, getSysUser()));
    }

    /**
     * 删除付款账号信息
     * @param ids
     * @return
     */
    @PostMapping("/remove/paymentAccount")
    @ResponseBody
    public AjaxResult removePaymentAccountInfo(String ids){
        return toAjax(paymentAccountService.deletePaymentAccountByIds(ids));
    }

    /**
     * 发送加急邮件
     * @param id
     * @return
     */
    @PostMapping("/expedited")
    @ResponseBody
    public AjaxResult expedited(@RequestParam("id") Long id) {
        paymentRequestComponent.sendExpeditedEmail(id);
        return success();
    }



    /**
     * 上传汇总表
     * @return
     */
    @RequiresPermissions("system:paymentRequest:view")
    @GetMapping("/summary")
    public String summary(){
        return prefix + "/summary";
    }

    /**
     * 上传汇总表
     * @return
     */
    @PostMapping("/summaryTable")
    @ResponseBody
    public AjaxResult summaryTable(@RequestParam("file") MultipartFile file, @RequestParam("paymentDate")String paymentDate){
        return paymentRequestComponent.summaryTable(file, paymentDate);
    }

    @GetMapping("/importSummaryTemplate")
    @ResponseBody
    public AjaxResult importSummaryTemplate()
    {

        PaymentRequestFile paymentRequestFile = new PaymentRequestFile();
        paymentRequestFile.setType(2);
        List<PaymentRequestFile> list = iPaymentRequestFileService.selectPaymentRequestFileList(paymentRequestFile);
        PaymentRequestFile file = list.get(list.size() - 1);
        AjaxResult result = new AjaxResult();
        result.put("code", 0);
        result.put("fileName", file.getFileName());
        result.put("filePath", file.getFile());
        return result;
    }

    /**
     * 上传明细表
     * @return
     */
    @RequiresPermissions("system:paymentRequest:view")
    @GetMapping("/uploadDetail")
    public String uploadDetail(){
        return prefix + "/uploadDetail";
    }


    /**
     * TODO
     * 上传明细表
     * @return
     */
    @PostMapping("/uploadDetailTable")
    @ResponseBody
    public AjaxResult uploadDetailTable(@RequestParam("file") MultipartFile file, @RequestParam("deptCode")String deptCode){
        return paymentRequestComponent.uploadDetailTable(file,deptCode);
    }

    @GetMapping("/importUploadDetailTemplate")
    @ResponseBody
    public AjaxResult importUploadDetailTemplate()
    {
        ExcelUtil<PaymentRequestDt1Dto> util = new ExcelUtil<>(PaymentRequestDt1Dto.class);
        return util.importTemplateExcel("上传明细表");
    }

    /**
     * 异步计算金额
     * @return
     */
    @PostMapping("/calculateTheAmount")
    @ResponseBody
    public AjaxResult calculateTheAmount(@RequestBody PaymentRequest paymentRequest){
        AjaxResult result = new AjaxResult();
        result.put("code", 0);
        result.put("data", paymentRequestComponent.calculateTheAmount(paymentRequest));
        return result;
    }


}
