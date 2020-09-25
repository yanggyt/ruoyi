package com.ruoyi.business.controller;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
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

import javax.annotation.Resource;

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

    @Resource
    private IBizTeamRewardService bizTeamRewardService;

    @RequiresPermissions("business:member:view")
    @GetMapping()
    public String reward(Long memberID, String rewardDate, ModelMap mmap)
    {
        mmap.put("memberID", memberID);
        //检索当前一天结算
        mmap.put("rewardDate", DateUtils.getDate(-1, rewardDate));
        mmap.put("rewardType", BizTeamReward.TEAM_REWARD_TYPE_TEAM);
        return prefix + "/reward";
    }

    /**
     * 查询团队奖励明细列表
     */
    @RequiresPermissions("business:member:view")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizTeamReward bizTeamReward)
    {
        startPage();
        List<BizTeamReward> list = bizTeamRewardService.selectBizTeamRewardList(bizTeamReward);
        return getDataTable(list);
    }

}
