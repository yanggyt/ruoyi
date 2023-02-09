package com.ruoyi.web.controller.api;


import com.alibaba.fastjson.JSON;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.FormFileConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.OrderTypes;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.jwt.utils.JwtUtils;
import com.ruoyi.system.component.paymentRequest.PaymentRequestComponentApp;
import com.ruoyi.system.domain.FormFile;
import com.ruoyi.system.domain.PaymentAccount;
import com.ruoyi.system.domain.ProcessFlow;
import com.ruoyi.system.domain.paymentRequest.PaymentAuditResults;
import com.ruoyi.system.domain.paymentRequest.PaymentRequest;
import com.ruoyi.system.domain.paymentRequest.PaymentRequestDt1;
import com.ruoyi.system.service.IFormFileService;
import com.ruoyi.system.service.IPaymentAccountService;
import com.ruoyi.system.service.IProcessFlowService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.paymentRequest.IPaymentRequestDt1Service;
import com.ruoyi.system.service.paymentRequest.IPaymentRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/api/paymentRequest")
public class PaymentRequestApi extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(PaymentRequestApi.class);

    private String prefix = "system/paymentRequest";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private PaymentRequestComponentApp paymentRequestComponentApp;

    @Autowired
    private IPaymentRequestService paymentRequestService;

    @Autowired
    private IProcessFlowService processFlowService;

    @Autowired
    private IPaymentRequestDt1Service paymentRequestDt1Service;

    @Autowired
    private IFormFileService formFileService;

    @Autowired
    private IPaymentAccountService paymentAccountService;
    /**
     * 列表查询
     * @param infos 信息
     * @param sid 操作用户
     * @param token 令牌
     * @return
     */
    @Log(title = "请款单API列表查询", businessType = BusinessType.OTHER)
    @RequestMapping("/page")
    @ResponseBody
    public AjaxResult paymentRequestPage(@RequestParam(name="infos")String infos,
                                         @RequestParam(name="sid")String sid,
                                         @RequestParam(name="token")String token) {
//        if (!TokenUtil.verifyToken(token)) {
//            return error(-1, "身份信息过期，请联系IT人员。");
//        }
        
        log.info("请款单APP列表查询接口infos数据------>{},sid数据------>{}", infos, sid);
        Map<String, Object> apiParameter = JSON.parseObject(infos, Map.class);
        if (StringUtils.isNull(apiParameter)) {
            log.error("请款单APP列表查询接口出错，未获取到正确infos数据，问题infos------>{}", infos);
            return error(-1, "查询列表信息出错，请稍后重试或联系IT人员。");
        }
        SysUser user = userService.selectUserByUserCode(sid);
        if (StringUtils.isNull(user)) {
            log.error("请款单APP列表查询接口出错，未获取到正确的用户标识，问题用户标识------>{}", sid);
            return error(-1, "获取登录用户信息失败，请重新登录或联系IT人员。");
        }
        PaymentRequest paymentRequest = new PaymentRequest();
        List<PaymentRequest> resList = new ArrayList<>();
        if(Objects.equals(apiParameter.get("type").toString(),"1")){
            //说明是列表
            //拥有FinanceAudit角色\老总\管理员查看所有
            List<SysRole> roles = user.getRoles().stream().filter(r -> r.getRoleKey().equals("FinanceAudit")).collect(Collectors.toList());
            if (user.isAdmin() || StringUtils.isNotEmpty(roles) ||
                    user.getUserCode().contains("S00001") ||
                    user.getUserCode().contains("S00002") ||
                    user.getUserCode().contains("S00003")) {

            }else {
                //只能查看自己的
                paymentRequest.setEmployeeNo(sid);
//                paymentRequest.setDept(user.getDept().getDeptName().trim());
            }
            resList = paymentRequestService.selectPaymentRequestList(paymentRequest);
        }else if(Objects.equals(apiParameter.get("type").toString(),"2")){
            //说明是我的待办
            if (!user.isAdmin()) {
                paymentRequest.setSendToCode(sid);
            }
//            startPage();
            resList = paymentRequestService.selectPaymentRequestList(paymentRequest);
        }else if(Objects.equals(apiParameter.get("type").toString(),"3")){
            //说明是我的已办
//            startPage();
            paymentRequest.setHandlesCode(sid);
            resList = paymentRequestService.selectPaymentRequestList(paymentRequest);
        }
        return AjaxResult.success("S",resList);
    }

    /**
     * 移动端新增请款
     * @param infos 请款单数据
     * @param file0 文件1
     * @param file1 文件2
     * @param file2 文件3
     * @param file3 文件4
     * @param operationType 操作类型, 1-保存, 2-提交
     * @param sid 员工编号
     * @param token 令牌
     * @return 结果
     */
    @Log(title = "请款单API新增", businessType = BusinessType.INSERT)
    @PostMapping("/add/{operationType}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult addPaymentRequestSave(@RequestParam(name="infos")String infos,
                                            @RequestParam(name = "file0", required = false) MultipartFile file0,
                                            @RequestParam(name = "file1", required = false) MultipartFile file1,
                                            @RequestParam(name = "file2", required = false) MultipartFile file2,
                                            @RequestParam(name = "file3", required = false) MultipartFile file3,
                                            @PathVariable(name = "operationType", required = false) String operationType,
                                            @RequestParam(name="sid")String sid,
                                            @RequestParam(name="token")String token) {
//        if (!TokenUtil.verifyToken(token)) {
//            return error(-1, "身份信息过期，请联系IT人员。");
//        }
        PaymentRequest paymentRequest = JSON.parseObject(infos, PaymentRequest.class);
        if (StringUtils.isNull(paymentRequest)) {
            log.error("请款单APP新增接口出错，未获取到正确infos数据，问题infos------>{}", infos);
            return error(-1, "新增请款单出错，请稍后重试或联系IT人员。");
        }
        SysUser user = userService.selectUserByUserCode(sid);
        if (StringUtils.isNull(user)) {
            log.error("请款单APP新增接口出错，未获取到正确的用户标识，问题用户标识------>{}", sid);
            return error(-1, "获取登录用户信息失败，请重新登录或联系IT人员。");
        }
        String msg = paymentRequestComponentApp.insertCheck(paymentRequest);
        if (StringUtils.isNotEmpty(msg)) {
            return error(-1, msg);
        }
        paymentRequest.setApplicant(user.getUserName());
        paymentRequest.setEmployeeNo(user.getUserCode());
        paymentRequest.setDept(user.getDept().getDeptName());
        paymentRequest.setDeptCode(user.getDepartmentCode());
        AjaxResult ajaxResult;
        try {
             ajaxResult = paymentRequestService.insertPaymentRequest(paymentRequest, user);
            //文件上传
            List<MultipartFile> files = new ArrayList<>();
            if (StringUtils.isNotNull(file0)){
                files.add(file0);
            }
            if (StringUtils.isNotNull(file1)){
                files.add(file1);
            }
            if (StringUtils.isNotNull(file2)){
                files.add(file2);
            }
            if (StringUtils.isNotNull(file3)){
                files.add(file3);
            }
            if (StringUtils.isNotEmpty(files)){
//                for (MultipartFile file:files) {
//                    String filePath = RuoYiConfig.getUploadPath();
//                    String fileNameRes = file.getOriginalFilename().replaceAll(" ","").replaceAll("&","");
//                    String suffix = fileNameRes.substring(fileNameRes.lastIndexOf("."));
//                    String filePath1 = FileUploadUtils.upload(filePath, file, suffix);
//                    PaymentRequestFile paymentRequestFile = new PaymentRequestFile();
//                    paymentRequestFile.setAssociationId(ajaxResult.get("data"));
//                    paymentRequestFile.setType(1);
//                    paymentRequestFile.setFile(filePath1);
//                    paymentRequestFile.setFileName(fileNameRes);
//                    paymentRequestFile.setCreateTime(new Date());
//                    paymentRequestFileService.insertPaymentRequestFile(paymentRequestFile);
//                }
            }
            if (Objects.equals(operationType, "2")) {
                //进行提交
                paymentRequestService.submitProcess((Long) ajaxResult.get("data"), user);
            }
        } catch (Exception e) {
            log.error("请款单APP新增接口提交出错", e);
            return error(-1, "提交出错，请稍后重试或联系IT人员。");
        }
        return success(ajaxResult.get("data").toString());
    }

    /**
     * APP详情(包含审核页面详情)
     * @param id
     * @param type
     * @param sid
     * @param token
     * @return
     */
    @RequestMapping(value = "/detail/{id}/{type}")
    @ResponseBody
    public AjaxResult paymentRequestDetail(@PathVariable("id") Long id,
                                           @PathVariable("type") Integer type,
                                           @RequestParam(name="sid")String sid,
                                           @RequestParam(name="token")String token)
    {
//        if (!TokenUtil.verifyToken(token)) {
//            return error(-1, "身份信息过期，请联系IT人员。");
//        }
        SysUser user = userService.selectUserByUserCode(sid);
        if (StringUtils.isNull(user)) {
            log.error("请款单APP详情接口出错，未获取到正确的用户标识，问题用户标识------>{}", sid);
            return error(-1, "获取登录用户信息失败，请重新登录或联系IT人员。");
        }
        Map<String,Object> map = new HashMap<>();
        try {
            PaymentRequest paymentRequest = paymentRequestService.selectPaymentRequestById(id);
            if (StringUtils.isNull(paymentRequest)){
                log.error("请款单APP详情接口出错，未查询到主数据信息，问题ID------>{}",id);
                throw new GlobalException("获取请款单主数据出错！");
            }
            map.put("paymentRequest", paymentRequest);
            PaymentRequestDt1 paymentRequestDt1 = new PaymentRequestDt1();
            paymentRequestDt1.setRequestId(id);
            List<PaymentRequestDt1> paymentRequestDt1List = paymentRequestDt1Service.selectPaymentRequestDt1List(paymentRequestDt1);
            map.put("paymentRequestDt1List", paymentRequestDt1List);
            ProcessFlow processFlow = new ProcessFlow();
            processFlow.setOrderId(id);
            processFlow.setOrderType(OrderTypes.PAYMENT_REQUEST.getCode());
            List<ProcessFlow> lists = processFlowService.selectProcessFlowListByOrderId(id,OrderTypes.PAYMENT_REQUEST.getCode());
            map.put("lists", lists);
            FormFile formFile = new FormFile();
            formFile.setFileType(FormFileConstants.PAYMENTREQUEST);
            formFile.setParentId(id);
            List<FormFile> formFiles = formFileService.selectFormFileList(formFile);
            map.put("listFile", formFiles);
            //详情页功能控制
            Map<String, Object> resultMap = paymentRequestComponentApp.editableArea(paymentRequest, user, type);
            map.put("event", resultMap);
        }catch (Exception e){
            log.error("请款单APP详情接口出错，获取详情信息时出错------>{}", e);
            return error(-1, "获取详情出错，请稍后重试或联系IT人员。");
        }
        return AjaxResult.success("S",map);
    }

    /**
     * 审核提交
     * @param infos 审核信息
     * @param sid 操作用户
     * @param token 令牌
     * @return
     */
    @Log(title = "请款单API审核提交", businessType = BusinessType.UPDATE)
    @RequestMapping("/auditSubmit")
    @ResponseBody
    public AjaxResult requisitionSubmit1(@RequestParam(name="infos")String infos,
                                         @RequestParam(name="sid")String sid,
                                         @RequestParam(name="token")String token)
    {
//        if(!TokenUtil.verifyToken(token)) {
//            return error(-1, "身份信息过期，请联系IT人员。");
//        }
        log.info("请款单审核提交API 表单数据------>{},操作人------>{}",infos,sid);
        try {
            //解析数据
            PaymentAuditResults auditResults = JSON.parseObject(infos, PaymentAuditResults.class);
            PaymentRequest paymentRequestRes = paymentRequestService.selectPaymentRequestById(auditResults.getPaymentRequestId());
            SysUser userRes = userService.selectUserByUserCode(sid);
            if(!paymentRequestRes.getSendToCode().contains(userRes.getUserCode()) && !userRes.isAdmin()){
                return error(-1,"已经签核完成，请签核下一单");
            }
            paymentRequestService.auditSubmit(auditResults, userRes, paymentRequestRes);
        } catch (GlobalException e){
            return error(-1, e.getMessage());
        } catch (Exception e){
            log.error("请款单审核提交API出错！错误------>{}",e);
            return error(-1,"提交失败，请返回重试或联系IT人员。");
        }
        return AjaxResult.success("Success","操作成功");
    }

    /**
     * 批量审核
     * @param infos
     * @param sid
     * @param token
     * @return
     */
    @Log(title = "请款单API批量审核", businessType = BusinessType.UPDATE)
    @PostMapping("/batchReview")
    @ResponseBody
    public AjaxResult batchReview(@RequestParam(name="infos")String infos,
                                  @RequestParam(name="sid")String sid,
                                  @RequestParam(name="token")String token){
//        if(!TokenUtil.verifyToken(token)) {
//            return error(-1, "身份信息过期，请联系IT人员。");
//        }
        long startTime = System.currentTimeMillis(); // 开始时间
        log.info("请款单批量审核API 表单数据------>{},操作人------>{}",infos,sid);
        String s = "";
        try {
            //解析数据
            PaymentAuditResults auditResults = JSON.parseObject(infos, PaymentAuditResults.class);
            SysUser userRes = userService.selectUserByUserCode(sid);
            s = paymentRequestService.paymentRequestBatchReview(auditResults, userRes);
        } catch (GlobalException e){
            return error(-1, e.getMessage());
        } catch (Exception e){
            log.error("请款单批量审核API出错！错误------>{}",e);
            return error(-1,"提交失败，请返回重试或联系IT人员。");
        }

        long endTime = System.currentTimeMillis(); // 执行完时间
        log.info("APP批量审核花费时间 timecost=[{}]ms;",(endTime - startTime)); // 计算时间差
        return AjaxResult.success("Success",s.replaceAll("<br/>","\n"));
    }



    /**
     * 获取表单下拉框信息
     *
     * @return
     */
    @RequestMapping("/getSelectInformation")
    @ResponseBody
    public AjaxResult getSelectInformation(){
//        if (!TokenUtil.verifyToken(token)) {
//            return error(-1, "身份信息过期，请联系IT人员。");
//        }
        Map<Object, Object> map = paymentRequestComponentApp.selectInformation();
        return AjaxResult.success("success",map);
    }

    /**
     * 上传附件
     *
     * @param multipartFiles
     * @return
     */
    @Log(title = "请款单API上传附件", businessType = BusinessType.INSERT)
    @PostMapping(value = "/file/upload")
    @ResponseBody
    public AjaxResult uploadFile1(@RequestParam("file") MultipartFile multipartFiles)
    {
        if (multipartFiles == null) {
            return error(8, "文件为空。");
        }
        Map<String, String> map = new HashMap<>();
        try {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileNameRes = multipartFiles.getOriginalFilename().replaceAll(" ", "").replaceAll("&", "");
            String suffix = fileNameRes.substring(fileNameRes.lastIndexOf("."));
            String fileName = FileUploadUtils.uploads(filePath, multipartFiles, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
            String url = fileName;
            map.put("fileName", fileNameRes);
            map.put("filePath", url);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success("success", map);
    }

    /**
     * 删除请款单
     * @param infos
     * @return
     */
    @Log(title = "请款单API删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestParam(name = "infos") String infos, @RequestParam(name = "token") String token) {
//        if (!TokenUtil.verifyToken(token)) {
//            return error(-1, "身份信息过期，请联系IT人员。");
//        }
        String ids;
        try {
            HashMap map = JSON.parseObject(infos, HashMap.class);
            ids = (String) map.get("ids");
            if (StringUtils.isEmpty(ids)) {
                throw new GlobalException("删除失败，请稍后重试或联系IT人员。");
            }
            paymentRequestService.deletePaymentRequestByIds(ids);
            return success();
        } catch (GlobalException businessException) {
            log.error("请款单删除API出错！错误原因------>", businessException.getMessage());
            return error(-1, businessException.getMessage());
        } catch (Exception exception) {
            log.error("请款单删除API出错！错误原因------>", exception);
            return error(-1, "删除失败，请稍后重试或联系IT人员。");
        }
    }


    /**
     * 请款单撤回
     * @param infos 请款单信息
     * @param token token
     * @return
     */
    @Log(title = "请款单API撤回", businessType = BusinessType.UPDATE)
    @PostMapping("/withdrawal")
    @ResponseBody
    public AjaxResult Withdrawal(@RequestParam(name = "infos") String infos, @RequestParam(name = "token") String token) {
//        if (!TokenUtil.verifyToken(token)) {
//            return error(-1, "身份信息过期，请联系IT人员。");
//        }
        Long id;
        try {
            HashMap map = JSON.parseObject(infos, HashMap.class);
            id = Long.valueOf((String)map.get("id"));
            if (StringUtils.isNull(id)) {
                throw new GlobalException("撤回失败，请稍后重试或联系IT人员。");
            }
            paymentRequestService.WithdrawalOfInitiation(id);
            return success();
        } catch (GlobalException businessException) {
            return error(businessException.getMessage());
        } catch (Exception e) {
            log.error("请款单撤回API出错！错误原因------>", e);
            return error(-1, "操作失败，请稍后重试或联系IT人员。");
        }
    }

    /**
     * 请款单编辑
     * @param infos 请款单信息
     * @param operationType 操作类型 1-保存, 2-提交
     * @param sid 员工编号
     * @param token 令牌
     * @return 结果
     */
    @Log(title = "请款单API编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit/{operationType}")
    @ResponseBody
    public AjaxResult editSave(@RequestParam(name = "infos") String infos,
                               @PathVariable(name = "operationType", required = false) String operationType,
                               @RequestParam(name="sid")String sid,
                               @RequestParam(name = "token") String token) {
//        if (!TokenUtil.verifyToken(token)) {
//            return error(-1, "身份信息过期，请联系IT人员。");
//        }
        PaymentRequest paymentRequest = JSON.parseObject(infos, PaymentRequest.class);
        if (StringUtils.isNull(paymentRequest)) {
            log.error("请款单APP编辑接口出错，未获取到正确infos数据，问题infos------>{}", infos);
            return error(-1, "操作失败，请稍后重试或联系IT人员。");
        }
        SysUser user = userService.selectUserByUserCode(sid);
        if (StringUtils.isNull(user)) {
            log.error("请款单APP编辑接口出错，未获取到正确的用户标识，问题用户标识------>{}", sid);
            return error(-1, "获取登录用户信息失败，请重新登录或联系IT人员。");
        }
        String msg = paymentRequestComponentApp.insertCheck(paymentRequest);
        if (StringUtils.isNotEmpty(msg)) {
            return error(-1, msg);
        }
        Long paymentRequestId;
        try {
            AjaxResult ajaxResult = paymentRequestService.updatePaymentRequest(paymentRequest);
            if (Objects.equals(operationType, "2")) {
                //进行提交
                paymentRequestService.submitProcess((Long) ajaxResult.get("data"), user);
            }
            return success();
        } catch (GlobalException businessException) {
            return error(businessException.getMessage());
        } catch (Exception e) {
            log.error("请款单编辑API出错！错误原因------>", e);
            return error(-1, "操作失败，请稍后重试或联系IT人员。");
        }
    }


    /**
     * 请款单历史付款信息
     * @param sid 用户id
     * @param token 令牌
     * @return
     */
    @RequestMapping("/payAccountHistory")
    @ResponseBody
    public AjaxResult payAccountHistory(@RequestParam(name="sid")String sid,
                                        @RequestParam(name="token")String token) {
//        if(!TokenUtil.verifyToken(token)) {
//            return error(-1, "身份信息过期，请联系IT人员。");
//        }
        Map<String,Object> mmap = new HashMap<>();
        SysUser user = userService.selectUserByLoginName(sid);
        if (StringUtils.isNull(user)) {
            log.error("请款单APP列表查询接口出错，未获取到正确的用户标识，问题用户标识------>{}", sid);
            return error(-1, "获取登录用户信息失败，请重新登录或联系IT人员。");
        }
        PaymentAccount paymentAccount = new PaymentAccount();
        paymentAccount.setCreateBy(user.getUserCode());
        //历史付款账号信息
        mmap.put("payeeList", paymentAccountService.selectPaymentAccountList(paymentAccount));
        return AjaxResult.success("S",mmap);
    }

}
