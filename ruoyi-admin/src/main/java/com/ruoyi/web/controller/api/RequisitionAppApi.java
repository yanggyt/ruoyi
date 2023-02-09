package com.ruoyi.web.controller.api;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.OrderTypes;
import com.ruoyi.common.enums.RequisitionStatus;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.system.component.requisition.RequisitionComponent;
import com.ruoyi.system.component.requisition.RequisitionComponentApp;
import com.ruoyi.system.domain.FormFile;
import com.ruoyi.system.domain.ProcessFlow;
import com.ruoyi.system.domain.requisition.Requisition;
import com.ruoyi.system.domain.requisition.RequisitionAuditResults;
import com.ruoyi.system.dto.requisition.RequisitionDto;
import com.ruoyi.system.service.IFormFileService;
import com.ruoyi.system.service.IProcessFlowService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.requisition.IRequisitionService;
import com.ruoyi.web.controller.common.CommonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;


@Controller
@RequestMapping("/api/requisition")
public class RequisitionAppApi extends BaseController
{

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private String prefix = "system/requisition";

    @Autowired
    private IRequisitionService requisitionService;

    @Autowired
    private IProcessFlowService processFlowService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private RequisitionComponentApp requisitionComponentApp;

    @Autowired
    private IFormFileService formFileService;

    @Autowired
    private CommonController commonController;

    /**
     * 获取表单下拉框信息
     *
     * @param token
     * @return
     */
    @RequestMapping("/getSelectInformation")
    @ResponseBody
    public AjaxResult getSelectInformation(@RequestParam(name = "token") String token)
    {
        /*if (!JwtUtils.verify(token)) {
            return error(-1, "身份信息过期，请联系IT人员。");
        }*/
        Map<Object, Object> map = requisitionComponentApp.selectInformation();
        return AjaxResult.success("success", map);
    }

    /**
     * 获取供应商信息
     *
     * @return
     */
    @RequestMapping("/getCodeName")
    @ResponseBody
    public AjaxResult getCodeName(@RequestParam(name = "token") String token)
    {

        return AjaxResult.success("success", CacheUtils.get(Constants.SYS_SUPPLIER_CACHE, Constants.SYS_SUPPLIER_KEY));
    }

    /**
     * 人员下拉接口
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/user/search")
    @ResponseBody
    public AjaxResult userSearch(@RequestParam(name = "userName", required = false) String userName)
    {
        return AjaxResult.success("success", null);
    }

    /**
     * 请购单新增
     *
     * @param infos         请购单信息
     * @param file0         文件1
     * @param file1         文件2
     * @param file2         文件3
     * @param file3         文件4
     * @param operationType 操作类型
     * @param sid           员工编号
     * @param token         令牌
     * @return 结果
     */
    @Log(title = "请购单API新增", businessType = BusinessType.INSERT)
    @RequestMapping("/add/{operationType}")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult addSave(@RequestParam(name = "infos") String infos,
                              @RequestParam(name = "file0", required = false) MultipartFile file0,
                              @RequestParam(name = "file1", required = false) MultipartFile file1,
                              @RequestParam(name = "file2", required = false) MultipartFile file2,
                              @RequestParam(name = "file3", required = false) MultipartFile file3,
                              @PathVariable(name = "operationType", required = false) String operationType,
                              @RequestParam(name = "sid") String sid,
                              @RequestParam(name = "token") String token)
    {
        log.info("请购单APP新增接口infos数据------>{},sid数据------>{}", infos, sid);
        SysUser user = userService.selectUserByLoginName(sid);
        if (StringUtils.isNull(user)) {
            log.error("请购单APP新增接口出错，未获取到正确的用户标识，问题用户标识------>{}", sid);
            return error(-1, "获取登录用户信息失败，请重新登录或联系IT人员。");
        }
        RequisitionDto requisitionDto = JSON.parseObject(infos, RequisitionDto.class);
        if (StringUtils.isNull(requisitionDto)) {
            log.error("请购单APP新增接口出错，未获取到正确infos数据，问题infos------>{}", infos);
            return error(-1, "新增请购单出错，请稍后重试或联系IT人员。");
        }
        String msg = requisitionComponentApp.insertCheck(requisitionDto);
        if (StringUtils.isNotEmpty(msg)) {
            return error(-1, msg);
        }
        requisitionDto.getRequisition().setCreateBy(user.getUserName());
        requisitionDto.getRequisition().setCreateByCode(user.getUserCode());
        requisitionDto.getRequisition().setStatus(RequisitionStatus.SAVE.getCode());
        requisitionDto.getRequisition().setCreateTime(DateUtils.getNowDate());
        requisitionDto.getRequisition().setUpdateTime(DateUtils.getNowDate());
        requisitionDto.getRequisition().setUserDepartment(user.getDept().getDeptName());
        requisitionDto.getRequisition().setUserName(user.getUserName());
        requisitionDto.getRequisition().setOffice(user.getOffices());
        requisitionDto.getRequisition().setSendToCode(user.getUserCode());
        Long requisitionId;
        try {
            Requisition requisition = requisitionDto.getRequisition();
            requisition.setRequisitionDt1List(requisitionDto.getRequisitionDt1s());
            requisition.setRequisitionDt2List(requisitionDto.getRequisitionDt2s());
            requisitionId = requisitionService.insertRequisition(requisitionDto.getRequisition(), user);
            //文件上传
            List<MultipartFile> files = new ArrayList<>();
            if (StringUtils.isNotNull(file0)) {
                files.add(file0);
            }
            if (StringUtils.isNotNull(file1)) {
                files.add(file1);
            }
            if (StringUtils.isNotNull(file2)) {
                files.add(file2);
            }
            if (StringUtils.isNotNull(file3)) {
                files.add(file3);
            }
            //文件上传相关
            /*if (StringUtils.isNotEmpty(files)){
                for (MultipartFile file:files) {
                    String filePath = RuoYiConfig.getUploadPath();
                    String fileNameRes = file.getOriginalFilename().replaceAll(" ","").replaceAll("&","");
                    String suffix = fileNameRes.substring(fileNameRes.lastIndexOf("."));
                    String filePath1 = FileUploadUtils.upload(filePath, file, suffix);
                    RequisitionFile requisitionFile = new RequisitionFile();
                    requisitionFile.setAssociationId(requisitionId);
                    requisitionFile.setType(1);
                    requisitionFile.setFile(filePath1);
                    requisitionFile.setFileName(fileNameRes);
                    requisitionFileService.insertRequisitionFile(requisitionFile);
                }
            }*/
            if (Objects.equals(operationType, "2")) {
                requisitionService.submit(requisitionId, user);
            }
        } catch (GlobalException e) {
            log.error("请购单APP新增接口提交出错--->", e);
            return error(-1, "提交出错，请稍后重试或联系IT人员。");
        } catch (Exception e) {
            log.error("请购单APP新增接口提交出错", e);
            return error(-1, "提交出错，请稍后重试或联系IT人员。");
        }
        return AjaxResult.success("success", requisitionId);
    }

    /**
     * 请购单列表
     *
     * @param infos
     * @param sid
     * @param token
     * @return
     */
    @Log(title = "请购单API列表查询", businessType = BusinessType.OTHER)
    @RequestMapping("/page")
    @ResponseBody
    public AjaxResult page(@RequestParam(name = "infos") String infos,
                           @RequestParam(name = "sid") String sid,
                           @RequestParam(name = "token") String token)
    {
        log.info("请购单APP列表查询接口infos数据------>{},sid数据------>{}", infos, sid);
        Map<String, Object> apiParameter = JSON.parseObject(infos, Map.class);
        if (StringUtils.isNull(apiParameter)) {
            log.error("请购单APP列表查询接口出错，未获取到正确infos数据，问题infos------>{}", infos);
            return error(-1, "查询列表信息出错，请稍后重试或联系IT人员。");
        }
        SysUser user = userService.selectUserByUserCode(sid);
        if (StringUtils.isNull(user)) {
            log.error("请购单APP列表查询接口出错，未获取到正确的用户标识，问题用户标识------>{}", sid);
            return error(-1, "获取登录用户信息失败，请重新登录或联系IT人员。");
        }
        Requisition requisition = new Requisition();
        List<Requisition> resList = null;
        if (Objects.equals(apiParameter.get("type").toString(), "1")) {
            //说明是列表
            requisition.setEmployeeNo(user.getUserCode());
            resList = requisitionService.selectRequisitionList(requisition);
        } else if (Objects.equals(apiParameter.get("type").toString(), "2")) {
            //说明是我的待办
            if (!user.isAdmin()) {
                requisition.setSendToCode(sid);
            }
            resList = requisitionService.selectRequisitionList(requisition);
        } else if (Objects.equals(apiParameter.get("type").toString(), "3")) {
            //说明是我的已办
            requisition.setHandlesCode(sid);
            resList = requisitionService.selectRequisitionList(requisition);
        } else {
            return error(-1, "查询列表信息出错，请稍后重试或联系IT人员。");
        }
        return AjaxResult.success("success", resList);
    }

    /**
     * APP详情(包含审核页面详情)
     *
     * @param id    请购单id
     * @param type  列表类型(1.类别 2.待办 3.已办)
     * @param sid   处理人code
     * @param token
     * @return
     */
    @RequestMapping(value = "/detail/{id}/{type}")
    @ResponseBody
    public AjaxResult deatil(@PathVariable("id") Long id,
                             @PathVariable("type") Integer type,
                             @RequestParam(name = "sid") String sid,
                             @RequestParam(name = "token") String token)
    {
        SysUser user = userService.selectUserByUserCode(sid);
        if (StringUtils.isNull(user)) {
            log.error("请购单APP详情接口出错，未获取到正确的用户标识，问题用户标识------>{}", sid);
            return error(-1, "获取登录用户信息失败，请重新登录或联系IT人员。");
        }
        Map<String, Object> map = new HashMap<>();
        try {
            Requisition requisition = requisitionService.selectRequisitionById(id);
            if (StringUtils.isNotEmpty(requisition.getTotalAmount())) {
                requisition.setRemark(requisition.getTotalAmount());
            }
            map.put("requisition", requisition);
            map.put("requisitionDt1s", requisition.getRequisitionDt1List());
            map.put("requisitionDt2s", requisition.getRequisitionDt2List());
            ProcessFlow processFlow = new ProcessFlow();
            processFlow.setOrderId(id);
            processFlow.setOrderType(OrderTypes.REQUISITION.getCode());
            List<ProcessFlow> list = processFlowService.selectProcessFlowList(processFlow);
            map.put("lists", list);
            FormFile requisitionFile = new FormFile();
            requisitionFile.setParentId(id);
            List<FormFile> listFile = formFileService.selectFormFileList(requisitionFile);
            listFile.forEach(f -> f.setFile(f.getFilePath()));
            map.put("listFile", listFile);
            Map<String, Object> resultMap = requisitionComponentApp.editableArea(requisition, user, type);
            map.put("event", resultMap);
        } catch (Exception e) {
            log.error("请购单APP详情接口出错，获取详情信息时出错------>{}", e);
            return error(-1, "获取详情出错，请稍后重试或联系IT人员。");
        }
        return AjaxResult.success("success", map);
    }

    /**
     * 审核提交
     *
     * @param infos 审核信息
     * @param sid   操作用户
     * @param token 令牌
     * @return
     */
    @Log(title = "请购单API审核提交", businessType = BusinessType.UPDATE)
    @RequestMapping("/auditSubmit")
    @ResponseBody
    public AjaxResult requisitionSubmit(@RequestParam(name = "infos") String infos,
                                        @RequestParam(name = "sid") String sid,
                                        @RequestParam(name = "token") String token)
    {
        log.info("请购单审核提交API 表单数据------>{},操作人------>{}", infos, sid);
        try {
            //解析数据
            RequisitionAuditResults auditResults = JSON.parseObject(infos, RequisitionAuditResults.class);
            Requisition requisitionRes = requisitionService.selectRequisitionById(auditResults.getRequisitionId());
            SysUser userRes = userService.selectUserByUserCode(sid);
            if (!requisitionRes.getSendToCode().equals(userRes.getUserCode()) && !userRes.isAdmin()) {
                return error(-1, "已经签核完成，请签核下一单");
            }
            requisitionService.auditSubmit(auditResults, userRes);
        } catch (GlobalException e) {
            return error(-1, e.getMessage());
        } catch (Exception e) {
            log.error("请购单审核提交API出错！错误------>{}", e);
            return error(-1, "提交失败，请返回重试或联系IT人员。");
        }
        return AjaxResult.success("success", "操作成功");
    }

    /**
     *
     *
     * @param infos
     * @param sid
     * @param token
     * @param response
     * @param request
     * @return
     */
    /*@RequestMapping(value = "/file/download")
    @ResponseBody
    public AjaxResult downloadFile(@RequestParam(name = "infos") String infos,
                                   @RequestParam(name = "sid") String sid,
                                   @RequestParam(name = "token") String token, HttpServletResponse response, HttpServletRequest request)
    {
        if (StringUtils.isEmpty(infos)) {
            return error(-1, "下载文件出错，请稍后重试或联系IT人员。");
        }
        log.info("请购单下载附件API 数据------>{},操作人------>{}", infos, sid);
        Map<String, String> resultMap = new HashMap<>();
        HashMap map = JSON.parseObject(infos, HashMap.class);
        String filePath = (String) map.get("filepath");
        String ext = FileUtils.ext(filePath);
        try {
            String filePathRes = RuoYiConfig.getUploadPath() + filePath;
            File f = new File(filePathRes);
            if (!f.exists()) {
                throw new FileNotFoundException(filePathRes);
            }
            // 读取图片字节数组
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
            BufferedInputStream in = null;
            try {
                in = new BufferedInputStream(new FileInputStream(f));
                int buf_size = 1024;
                byte[] buffer = new byte[buf_size];
                int len = 0;
                while (-1 != (len = in.read(buffer, 0, buf_size))) {
                    bos.write(buffer, 0, len);
                }
                in.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String file = requisitionComponentApp.byteToBase64(bos.toByteArray());
            resultMap.put("file", file);
            resultMap.put("title", UUID.randomUUID().toString());
            resultMap.put("extension", ext);
            return AjaxResult.success("success", resultMap);
        } catch (Exception e) {
            log.error("请购单下载文件API出错！错误------>{}", e);
            return error(-1, "下载文件出错，请稍后重试或联系IT人员。");
        }
    }*/

    /**
     * 上传附件
     *
     * @param multipartFiles
     * @return
     */
    @Log(title = "请购单API附件上传", businessType = BusinessType.INSERT)
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
     * 下载文件
     *
     * @param infos
     * @param sid
     * @param token
     * @param response
     * @param request
     * @return
     */
    @Log(title = "请购单API附件下载", businessType = BusinessType.OTHER)
    @RequestMapping(value = "/file/download/image")
    @ResponseBody
    public AjaxResult download(@RequestParam(name = "infos") String infos,
                               @RequestParam(name = "sid") String sid,
                               @RequestParam(name = "token") String token, HttpServletResponse response, HttpServletRequest request)
    {
        if (StringUtils.isEmpty(infos)) {
            return error(-1, "下载文件出错，请稍后重试或联系IT人员。");
        }
        Map<String, String> resultMap = new HashMap<>();
        HashMap map = JSON.parseObject(infos, HashMap.class);
        String filePath = (String) map.get("filepath");
        String suffix = filePath.substring(filePath.lastIndexOf("."));
        try {
            if (".jpg".equals(suffix) || ".png".equals(suffix)) {
                String filePathRes = RuoYiConfig.getUploadPath() + filePath;
                File f = new File(filePathRes);
                if (!f.exists()) {
                    throw new FileNotFoundException(filePathRes);
                }
                // 读取图片字节数组
                ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
                BufferedInputStream in = null;
                try {
                    in = new BufferedInputStream(new FileInputStream(f));
                    int buf_size = 1024;
                    byte[] buffer = new byte[buf_size];
                    int len = 0;
                    while (-1 != (len = in.read(buffer, 0, buf_size))) {
                        bos.write(buffer, 0, len);
                    }
                    in.close();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String file = requisitionComponentApp.byteToBase64(bos.toByteArray());
                if (".jpg".equals(suffix)) {
                    suffix = ".jpeg";
                }
                file = "data:image/" + suffix.replace(".", "") + ";base64," + file;
                resultMap.put("file", file);
                return AjaxResult.success("success", resultMap);
            } else {
                String fileName = UUID.randomUUID().toString();
                commonController.resourceDownloads(filePath, fileName, request, response);
                return null;
            }
        } catch (Exception e) {
            log.error("下载失败");
            return error(-1, "下载文件出错，请稍后重试或联系IT人员。");
        }
    }

    /**
     * 删除请购单
     *
     * @param infos
     * @return
     */
    @Log(title = "请购单API删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(@RequestParam(name = "infos") String infos, @RequestParam(name = "token") String token)
    {
        String ids;
        try {
            HashMap map = JSON.parseObject(infos, HashMap.class);
            ids = (String) map.get("ids");
            if (StringUtils.isEmpty(ids)) {
                throw new GlobalException("删除失败，请稍后重试或联系IT人员。");
            }
            requisitionService.deleteRequisitionByIds(ids);
            return success();
        } catch (GlobalException e) {
            log.error("请购单删除API出错！错误原因------>", e.getMessage());
            return error(-1, e.getMessage());
        } catch (Exception exception) {
            log.error("请购单删除API出错！错误原因------>", exception);
            return error(-1, "删除失败，请稍后重试或联系IT人员。");
        }
    }

    /**
     * 获取项目编号
     *
     * @return 项目编号列表
     */
    @PostMapping("/getProjectCode")
    @ResponseBody
    public AjaxResult getProjectCode(@RequestParam(name = "token") String token)
    {
        return AjaxResult.success("success!", CacheUtils.get(Constants.ZT_WGCPRORELEASE_CACHE, Constants.ZT_WGCPRORELEASE_KEY + "proCode"));
    }

    /**
     * 撤回请购单
     *
     * @param infos 请购单信息
     * @param token 令牌
     * @return
     */
    @Log(title = "请购单API撤回", businessType = BusinessType.UPDATE)
    @PostMapping("/withdrawal")
    @ResponseBody
    public AjaxResult withdrawal(@RequestParam(name = "infos") String infos, @RequestParam(name = "token") String token)
    {
        Long id;
        try {
            HashMap map = JSON.parseObject(infos, HashMap.class);
            id = Long.valueOf((String) map.get("id"));
            if (StringUtils.isNull(id)) {
                throw new GlobalException("撤回失败，请稍后重试或联系IT人员。");
            }
            requisitionService.withdraw(id);
            return success();
        } catch (GlobalException e) {
            log.error("请购单删除API出错！错误原因------>", e.getMessage());
            return error(-1, e.getMessage());
        } catch (Exception exception) {
            log.error("请购单撤回API出错！错误原因------>", exception);
            return error(-1, "操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
        }
    }

    /**
     * 请购单编辑
     *
     * @param infos         请购单信息
     * @param operationType 操作类型
     * @param sid           员工编号
     * @param token         令牌
     * @return 结果
     */
    @Log(title = "请购单API编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit/{operationType}")
    @ResponseBody
    public AjaxResult editSave(@RequestParam(name = "infos") String infos,
                               @PathVariable(name = "operationType", required = false) String operationType,
                               @RequestParam(name = "sid") String sid,
                               @RequestParam(name = "token") String token)
    {
        RequisitionDto requisitionDto = JSON.parseObject(infos, RequisitionDto.class);
        if (StringUtils.isNull(requisitionDto)) {
            log.error("请购单APP编辑接口出错，未获取到正确infos数据，问题infos------>{}", infos);
            return error(-1, "操作失败，请稍后重试或联系IT人员。");
        }
        SysUser user = userService.selectUserByUserCode(sid);
        if (StringUtils.isNull(user)) {
            log.error("请购单APP编辑接口出错，未获取到正确的用户标识，问题用户标识------>{}", sid);
            return error(-1, "获取登录用户信息失败，请重新登录或联系IT人员。");
        }
        String msg = requisitionComponentApp.insertCheck(requisitionDto);
        if (StringUtils.isNotEmpty(msg)) {
            return error(-1, msg);
        }
        Long requisitionId;
        try {
            Requisition requisition = requisitionDto.getRequisition();
            requisition.setRequisitionDt1List(requisitionDto.getRequisitionDt1s());
            requisition.setRequisitionDt2List(requisitionDto.getRequisitionDt2s());
            requisitionId = requisitionService.updateRequisition(requisition, user);
            if (Objects.equals(operationType, "2")) {
                //进行提交
                requisitionService.submit(requisitionId, user);
            }
            return success();
        } catch (GlobalException e) {
            return error(e.getMessage());
        } catch (Exception e) {
            log.error("请购单编辑API出错！错误原因------>", e);
            return error(-1, "操作失败，请稍后重试或联系IT人员。");
        }
    }

}
