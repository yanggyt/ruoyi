package com.ruoyi.his.controller;

import java.util.List;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.func.Func;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.his.domain.HisDepartment;
import com.ruoyi.his.domain.HisDoctor;
import com.ruoyi.his.service.IHisDepartmentService;
import com.ruoyi.his.service.IHisDoctorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.domain.HisHospitalInfo;
import com.ruoyi.his.service.IHisHospitalInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 医疗机构Controller
 * 
 * @author bend
 * @date 2020-06-28
 */
@Controller
@RequestMapping("/his/hospital")
public class HisHospitalInfoController extends BaseController
{
    private String prefix = "his/hospital";

    @Autowired
    private IHisHospitalInfoService hisHospitalInfoService;
    @Autowired
    private IHisDoctorService hisDoctorService;
    @Autowired
    private IHisDepartmentService iHisDepartmentService;

    @RequiresPermissions("his:hospital:view")
    @GetMapping()
    public String hospital()
    {
        return prefix + "/hospital";
    }

    /**
     * 查询医疗机构列表
     */
    @RequiresPermissions("his:hospital:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisHospitalInfo hisHospitalInfo)
    {
        startPage();
        List<HisHospitalInfo> list = hisHospitalInfoService.selectHisHospitalInfoList(hisHospitalInfo);
        return getDataTable(list);
    }

    /**
     * 导出医疗机构列表
     */
    @RequiresPermissions("his:hospital:export")
    @Log(title = "医疗机构", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisHospitalInfo hisHospitalInfo)
    {
        List<HisHospitalInfo> list = hisHospitalInfoService.selectHisHospitalInfoList(hisHospitalInfo);
        ExcelUtil<HisHospitalInfo> util = new ExcelUtil<HisHospitalInfo>(HisHospitalInfo.class);
        return util.exportExcel(list, "hospital");
    }

    /**
     * 新增医疗机构
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存医疗机构
     */
    @RequiresPermissions("his:hospital:add")
    @Log(title = "医疗机构", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisHospitalInfo hisHospitalInfo)
    {
        return toAjax(hisHospitalInfoService.insertHisHospitalInfo(hisHospitalInfo));
    }

    /**
     * 修改医疗机构
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisHospitalInfo hisHospitalInfo = hisHospitalInfoService.selectHisHospitalInfoById(id);
        mmap.put("hisHospitalInfo", hisHospitalInfo);
        if (Func.isNotEmpty(hisHospitalInfo)){
            HisDoctor hisDoctor = new HisDoctor();
            hisDoctor.setOrgCode(hisHospitalInfo.getOrgCode());
            hisDoctor.setIsShow(1);
            //选择优秀医生
            List<HisDoctor> hisDoctorList = hisDoctorService.selectHisDoctorList(hisDoctor);
            if (Func.isNotEmpty(hisDoctorList)){
                //已经选中的优秀医生
                String excellentDoctorIds = hisHospitalInfo.getExcellentDoctorIds();
                if (Func.isNotEmpty(excellentDoctorIds)){
                    String[] doctorIds = Func.toStrArray(",", excellentDoctorIds);
                    if (Func.isNotEmpty(doctorIds)){
                        for (String doctorId : doctorIds) {
                            for (HisDoctor doctor : hisDoctorList) {
                                if (doctorId.equals(doctor.getDoctorId())) {
                                    doctor.setFlag(true);
                                    break;
                                }
                            }
                        }
                    }
                }
                //设置选中标签
                mmap.put("hisDoctorList", hisDoctorList);
            }

            //选择机构虚拟操作员列表
            HisDoctor vmDoctor = new HisDoctor();
            vmDoctor.setOrgCode(hisHospitalInfo.getOrgCode());
            vmDoctor.setIsVirtualAccount(1);
            List<HisDoctor> vmUserList = hisDoctorService.selectHisDoctorList(vmDoctor);
            if (Func.isNotEmpty(vmUserList)){
                String vmUserId = hisHospitalInfo.getVmUserId();
                //已经选中的虚拟操作员
                if (Func.isNotEmpty(vmUserId)){
                    for (HisDoctor vm : vmUserList) {
                        if (vmUserId.equals(vm.getDoctorId())) {
                            vm.setFlag(true);//设置选中标签
                            break;
                        }
                    }
                }
                mmap.put("vmUserList", vmUserList);
            }

            //选择优秀科室
            HisDepartment hisDepartment = new HisDepartment();
            hisDepartment.setOrgCode(hisHospitalInfo.getOrgCode());
            hisDepartment.setIsShow(1);
            List<HisDepartment> hisDepartmentList = iHisDepartmentService.selectHisDepartmentList(hisDepartment);
            if (Func.isNotEmpty(hisDepartmentList)){
                //已经选中的优秀科室
                String excellentDeptIds = hisHospitalInfo.getExcellentDeptIds();
                if (Func.isNotEmpty(excellentDeptIds)){
                    String[]  deptIds= Func.toStrArray(",", excellentDeptIds);
                    if (Func.isNotEmpty(deptIds)){
                        for (String deptId : deptIds) {
                            for (HisDepartment department : hisDepartmentList) {
                                if (deptId.equals(department.getDeptId())) {
                                    department.setFlag(true);
                                    break;
                                }
                            }
                        }
                    }
                }
                //设置选中标签
                mmap.put("hisDepartmentList", hisDepartmentList);
            }
        }

        return prefix + "/edit";
    }

    /**
     * 修改保存医疗机构
     */
    @RequiresPermissions("his:hospital:edit")
    @Log(title = "医疗机构", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisHospitalInfo hisHospitalInfo)
    {
        return toAjax(hisHospitalInfoService.updateHisHospitalInfo(hisHospitalInfo));
    }

    /**
     * 删除医疗机构
     */
    @RequiresPermissions("his:hospital:remove")
    @Log(title = "医疗机构", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisHospitalInfoService.deleteHisHospitalInfoByIds(ids));
    }

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 机构图标
     */
    @Log(title = "上传机构图标", businessType = BusinessType.UPDATE)
    @PostMapping("/update/logo")
    @ResponseBody
    public AjaxResult updateLogo(@RequestParam("logoFile") MultipartFile file)
    {
        try
        {
            if (!file.isEmpty())
            {
                String fileName = FileUploadUtils.upload(Global.getUploadPath(), file);
                String url = serverConfig.getUrl() + fileName;
                AjaxResult ajax = AjaxResult.success();
                ajax.put("data", fileName);
                ajax.put("url", url);
                return ajax;
            }
            return error();
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
}
