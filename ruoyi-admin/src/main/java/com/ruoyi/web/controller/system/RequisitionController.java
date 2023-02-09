package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.FormFileConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.OrderTypes;
import com.ruoyi.common.enums.RequisitionStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.component.requisition.RequisitionComponent;
import com.ruoyi.system.domain.ProcessFlow;
import com.ruoyi.system.domain.requisition.Requisition;
import com.ruoyi.system.domain.requisition.RequisitionAuditResults;
import com.ruoyi.system.service.IProcessFlowService;
import com.ruoyi.system.service.requisition.IRequisitionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 请购单Controller
 *
 * @author SKaiL
 * @date 2022-09-26
 */
@Controller
@RequestMapping("/system/requisition")
public class RequisitionController extends BaseController
{
    private String prefix = "system/requisition";

    @Autowired
    private IRequisitionService requisitionService;

    @Autowired
    private IProcessFlowService processFlowService;

    @Autowired
    private RequisitionComponent requisitionComponent;

    @RequiresPermissions("system:requisition:view")
    @GetMapping()
    public String requisition()
    {
        return prefix + "/requisition";
    }

    /**
     * 查询请购单列表
     */
    @RequiresPermissions("system:requisition:listAll")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Requisition requisition)
    {
        startPage();
        List<Requisition> list = requisitionService.selectRequisitionList(requisition);
        return getDataTable(list);
    }

    @RequiresPermissions("system:requisition:view")
    @GetMapping("/create")
    public String requisitionCreate()
    {
        return prefix + "/requisitionCreate";
    }

    /**
     * 我的创建
     */
    @RequiresPermissions("system:requisition:list")
    @PostMapping("/list/create")
    @ResponseBody
    public TableDataInfo listCreate(Requisition requisition)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        //财务，稽核和管理员查看所有
        if (!sysUser.isAdmin()) {
            requisition.setEmployeeNo(getSysUser().getUserCode());
        }
        startPage();
        List<Requisition> list = requisitionService.selectRequisitionList(requisition);
        return getDataTable(list);
    }

    @GetMapping("/draft")
    public String requisitionDraft()
    {
        return prefix + "/requisitionDraft";
    }

    /**
     * 我的草稿
     */
    @PostMapping("/list/draft")
    @ResponseBody
    public TableDataInfo listDraft(Requisition requisition)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        //财务，稽核和管理员查看所有
        if (!sysUser.isAdmin()) {
            requisition.setEmployeeNo(getSysUser().getUserCode());
        }
        startPage();
        List<Requisition> list = requisitionService.selectRequisitionListDraft(requisition);
        return getDataTable(list);
    }

    @RequiresPermissions("system:requisition:view")
    @GetMapping("/todo")
    public String requisitionTodo()
    {
        return prefix + "/requisitionToDo";
    }

    /**
     * 我的待办
     */
    @RequiresPermissions("system:requisition:list")
    @PostMapping("/list/todo")
    @ResponseBody
    public TableDataInfo listToDo(Requisition requisition)
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        if (!sysUser.isAdmin()) {
            requisition.setSendToCode(getSysUser().getUserCode());
        }
        startPage();
        List<Requisition> list = requisitionService.selectRequisitionList(requisition);
        return getDataTable(list);
    }

    @RequiresPermissions("system:requisition:view")
    @GetMapping("/do")
    public String requisitionDo()
    {
        return prefix + "/requisitionDo";
    }

    /**
     * 我的已办
     */
    @RequiresPermissions("system:requisition:list")
    @PostMapping("/list/do")
    @ResponseBody
    public TableDataInfo listDo(Requisition requisition)
    {
        requisition.setHandlesCode(ShiroUtils.getSysUser().getUserCode());
        startPage();
        List<Requisition> list = requisitionService.selectRequisitionList(requisition);
        return getDataTable(list);
    }

    /**
     * 导出请购单列表
     */
    @RequiresPermissions("system:requisition:export")
    @Log(title = "请购单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Requisition requisition)
    {
        List<Requisition> list = requisitionService.selectRequisitionList(requisition);
        ExcelUtil<Requisition> util = new ExcelUtil<Requisition>(Requisition.class);
        return util.exportExcel(list, "请购单数据");
    }

    /**
     * 新增请购单
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("user", getSysUser());
        String s = requisitionComponent.buildData();
        mmap.put("data", s);
        mmap.put("projectCode", CacheUtils.get(Constants.ZT_WGCPRORELEASE_CACHE, Constants.ZT_WGCPRORELEASE_KEY + "proCode"));
        return prefix + "/add";
    }

    /**
     * 新增保存请购单
     */
    @RequiresPermissions("system:requisition:add")
    @Log(title = "请购单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @RepeatSubmit
    public AjaxResult addSave(@RequestBody Requisition requisition)
    {
        return success(requisitionService.insertRequisition(requisition, getSysUser()));
    }

    /**
     * 修改请购单
     */
    @RequiresPermissions("system:requisition:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Requisition requisition = requisitionService.selectRequisitionById(id);
        mmap.put("requisition", requisition);
        String s = requisitionComponent.buildData();
        mmap.put("data", s);
        mmap.put("projectCode", CacheUtils.get(Constants.ZT_WGCPRORELEASE_CACHE, Constants.ZT_WGCPRORELEASE_KEY + "proCode"));
        return prefix + "/edit";
    }

    /**
     * 修改保存请购单
     */
    @RequiresPermissions("system:requisition:edit")
    @Log(title = "请购单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody Requisition requisition)
    {
        return success(requisitionService.updateRequisition(requisition, getSysUser()));
    }

    /**
     * 删除请购单
     */
    @RequiresPermissions("system:requisition:remove")
    @Log(title = "请购单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(requisitionService.deleteRequisitionByIds(ids));
    }

    /**
     * 请购单提交
     */
    @RequiresPermissions("system:requisition:add")
    @Log(title = "请购单主", businessType = BusinessType.UPDATE)
    @GetMapping("/submit")
    @ResponseBody
    @RepeatSubmit
    public AjaxResult startSubmit(@RequestParam("id") Long requisitionId) throws ServiceException
    {
        SysUser sysUser = ShiroUtils.getSysUser();
        requisitionService.submit(requisitionId, sysUser);
        return success();
    }

    @RequiresPermissions("system:requisition:edit")
    @GetMapping(value = {"/addFile/{type}","/addFile/{id}/{type}"})
    public String forFaReportUpload(@PathVariable(value = "id",required = false) Long id, @PathVariable("type") Integer type, ModelMap mmap)
    {
        mmap.put("requisitionId", id);
        //文件类型 22使用文档, 2请购单附件
        mmap.put("type", type);
        return prefix + "/addFile";
    }

    @GetMapping(value = {"/filePage/{type}/{flag}","/filePage/{id}/{type}/{flag}"})
    public String filePage(@PathVariable(value = "id",required = false) Long id, @PathVariable("type") Integer type,
                               @PathVariable("flag") Boolean flag, ModelMap mmap)
    {
        mmap.put("requisitionId", id);
        //文件类型 22使用文档, 2请购单附件
        mmap.put("type", type);
        mmap.put("flag", flag);
        //模版不允许普通用户删除
        if (Objects.equals(FormFileConstants.REQUISTION_TEMPLATE, type)){
            if (getSysUser().isAdmin()){
                mmap.put("flag", true);
            } else {
                mmap.put("flag", false);
            }
        }
        return prefix + "/filePage";
    }

    /**
     * 查询文件列表
     */
    @PostMapping("/filePageList")
    @ResponseBody
    public TableDataInfo filePage(@RequestParam(value = "requisitionId",required = false) Long requisitionId,
                                          @RequestParam("type") Integer type)
    {
        return getDataTable(requisitionService.selectFormFile(requisitionId, type));
    }

    /**
     * 详情页
     */
    @GetMapping("/detail/{id}/{type}")
    public String deatil(@PathVariable("id") Long id, @PathVariable("type") Integer type, ModelMap mmap)
    {
        Requisition requisition = requisitionService.selectRequisitionById(id);
        requisitionComponent.approvalProgress(requisition);
        mmap.put("requisition", requisition);
        //审批记录
        List<ProcessFlow> list = processFlowService.selectProcessFlowListByOrderId(id, OrderTypes.REQUISITION.getCode());
        mmap.put("lists", list);
        mmap.put("listFile", requisitionService.selectFormFile(id, FormFileConstants.REQUISITION));
        mmap.put("isClose", "0");
        //陈总审核暂时上传审批截图按钮
        List<ProcessFlow> collect = list.stream().filter(pf -> (Objects.equals(requisition.getEmployeeNo(), getSysUser().getUserCode())
                || getSysUser().isAdmin()) && Objects.equals(pf.getCreateByCode(), "S00001")
                && StringUtils.isNull(pf.getAuditResult())).collect(Collectors.toList());
        if (StringUtils.isNotEmpty(collect) && Objects.equals(collect.get(0).getStatus(), requisition.getStatus())){
            mmap.put("isClose", "1");
        }
        if (Objects.equals(type, 2)){
            //展示审批截图
            if (requisition.getStatus() == 5 && StringUtils.isNotEmpty(requisition.getCloseFile())) {
                String url = requisition.getCloseFile();
                if (url.contains(".jpg") || url.contains(".png")) {
                    mmap.put("url", url);
                }
            }
            return prefix + "/detailPDF";
        } else {
            return prefix + "/detail";
        }
    }

    @GetMapping("/review/{id}")
    @RequiresPermissions("system:requisition:review")
    public String review(@PathVariable("id") Long id, ModelMap mmap)
    {
        Requisition requisition = requisitionService.selectRequisitionById(id);
        requisitionComponent.approvalProgress(requisition);
        mmap.put("requisition", requisition);
        mmap.put("projectCode", CacheUtils.get(Constants.ZT_WGCPRORELEASE_CACHE, Constants.ZT_WGCPRORELEASE_KEY + "proCode"));
        //审批记录
        List<ProcessFlow> list = processFlowService.selectProcessFlowListByOrderId(id, OrderTypes.REQUISITION.getCode());
        mmap.put("lists", list);
        mmap.put("listFile", requisitionService.selectFormFile(id, FormFileConstants.REQUISITION));
        Integer status = requisition.getStatus();
        if (status.equals(RequisitionStatus.DB_OFFER.getCode())) {
            //资产管理员和采购代表
            return prefix + "/assetReview";
        } else {
            //其他
            return prefix + "/review";
        }
    }


    /**
     * 请购单审核
     * @param auditResults
     * @return
     */
    @Log(title = "请购单审核", businessType = BusinessType.OTHER)
    @RequiresPermissions("system:requisition:review")
    @PostMapping("/auditSubmit")
    @ResponseBody
    @RepeatSubmit
    public AjaxResult auditSubmit(@RequestBody RequisitionAuditResults auditResults) throws ServiceException
    {
        return toAjax(requisitionService.auditSubmit(auditResults, getSysUser()));
    }

    /**
     * 撤回
     *
     * @param id
     * @return
     */
    @GetMapping("/withdrawal")
    @ResponseBody
    public AjaxResult Withdrawal(Long id)
    {
        return toAjax(requisitionService.withdraw(id));
    }

    /**
     * 上传审批截图文件
     */
    @RequiresPermissions("system:requisition:edit")
    @PostMapping("/addCloseRequisitionFile/save")
    @ResponseBody
    public AjaxResult addCloseRequisitionFileSave(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("requisitionId") Long requisitionId)
    {
        return toAjax(requisitionService.closeRequisitionFile(file, requisitionId, getSysUser()));
    }

    /**
     * 校验是否审核
     */
    @GetMapping("/forReview/{id}")
    @ResponseBody
    public AjaxResult forReview(@PathVariable("id") Long id) {
        Requisition  requisition = requisitionService.selectRequisitionById(id);
        if(!ShiroUtils.getSysUser().isAdmin()&&!Objects.equals(requisition.getSendToCode(),ShiroUtils.getLoginName())){
            throw new ServiceException("已经签核完成，请签核下一单。");
        }
        return AjaxResult.success();
    }

    /**
     * 使用说明相关文档下载
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/downloadFileDescription/{type}")
    public void downloadFileDescription(@PathVariable("type")Integer type,
                                        HttpServletRequest request, HttpServletResponse response)
    {
        requisitionService.downloadFileDescription(type, response);
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
    public AjaxResult batchReview(@RequestBody RequisitionAuditResults auditResults){
        return success(requisitionService.requisitionBatchReview(auditResults, getSysUser()));
    }

    @GetMapping("/forCodeNamePage/{index}")
    public String getCodeName(@PathVariable("index") String index, ModelMap mmap)
    {
        mmap.put("index", index);
        return prefix + "/doCodeNameTree";
    }

    /**
     * 厂商比议价信息
     *
     * @return
     */
    @GetMapping("/doCodeNameTreeData")
    @ResponseBody
    public List<Ztree> doCodeNameTreeData()
    {
        Object o = CacheUtils.get(Constants.SYS_SUPPLIER_CACHE, Constants.SYS_SUPPLIER_KEY);
//        return JSONObject.parseArray((String) o, Ztree.class);
        return StringUtils.cast(o);
    }

}
