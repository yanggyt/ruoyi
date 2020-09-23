package com.ruoyi.business.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.business.domain.BizTeamReward;
import com.ruoyi.business.service.IBizTeamRewardService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 团队奖励明细Controller
 * 
 * @author ruoyi
 * @date 2020-09-22
 */
@Controller
@RequestMapping("/business/reward")
public class BizTeamRewardController extends BaseController
{
    private String prefix = "business/reward";

    @Autowired
    private IBizTeamRewardService bizTeamRewardService;

    @RequiresPermissions("business:reward:view")
    @GetMapping()
    public String reward()
    {
        return prefix + "/reward";
    }

    /**
     * 查询团队奖励明细列表
     */
    @RequiresPermissions("business:reward:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizTeamReward bizTeamReward)
    {
        startPage();
        List<BizTeamReward> list = bizTeamRewardService.selectBizTeamRewardList(bizTeamReward);
        return getDataTable(list);
    }

    /**
     * 导出团队奖励明细列表
     */
    @RequiresPermissions("business:reward:export")
    @Log(title = "团队奖励明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizTeamReward bizTeamReward)
    {
        List<BizTeamReward> list = bizTeamRewardService.selectBizTeamRewardList(bizTeamReward);
        ExcelUtil<BizTeamReward> util = new ExcelUtil<BizTeamReward>(BizTeamReward.class);
        return util.exportExcel(list, "reward");
    }

    /**
     * 新增团队奖励明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存团队奖励明细
     */
    @RequiresPermissions("business:reward:add")
    @Log(title = "团队奖励明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BizTeamReward bizTeamReward)
    {
        return toAjax(bizTeamRewardService.insertBizTeamReward(bizTeamReward));
    }

    /**
     * 修改团队奖励明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BizTeamReward bizTeamReward = bizTeamRewardService.selectBizTeamRewardById(id);
        mmap.put("bizTeamReward", bizTeamReward);
        return prefix + "/edit";
    }

    /**
     * 修改保存团队奖励明细
     */
    @RequiresPermissions("business:reward:edit")
    @Log(title = "团队奖励明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BizTeamReward bizTeamReward)
    {
        return toAjax(bizTeamRewardService.updateBizTeamReward(bizTeamReward));
    }

    /**
     * 删除团队奖励明细
     */
    @RequiresPermissions("business:reward:remove")
    @Log(title = "团队奖励明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bizTeamRewardService.deleteBizTeamRewardByIds(ids));
    }
}
