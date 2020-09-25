package com.ruoyi.business.controller;

import com.ruoyi.business.domain.BizAccountDetail;
import com.ruoyi.business.domain.BizMember;
import com.ruoyi.business.service.IBizAccountService;
import com.ruoyi.business.service.IBizMemberService;
import com.ruoyi.business.service.IBizProductService;
import com.ruoyi.business.task.BusinessTask;
import com.ruoyi.business.utils.Encrypt;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.utils.DictUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员Controller
 * 
 * @author ruoyi
 * @date 2020-09-11
 */
@Controller
@RequestMapping("/business/member")
public class BizMemberController extends BaseController
{
    private String prefix = "business/member";

    @Autowired
    private IBizMemberService bizMemberService;

    @Autowired
    private IBizAccountService bizAccountService;

    @Autowired
    private IBizProductService bizProductService;

    @RequiresPermissions("business:member:view")
    @GetMapping()
    public String member()
    {
        return prefix + "/member";
    }

    /**
     * 查询会员列表
     */
    @RequiresPermissions("business:member:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizMember bizMember)
    {
        startPage();
        List<BizMember> list = bizMemberService.selectBizMemberList(bizMember);
        return getDataTable(list);
    }

    /**
     * 会员账户明细
     */
    @RequiresPermissions("business:member:view")
    @GetMapping("/accountDetail")
    public String accountDetail(Long memberID, int accountType, ModelMap mmap)
    {
        BizMember bizMember = bizMemberService.selectBizMemberSimple(memberID);
        mmap.put("memberID", memberID);
        mmap.put("mobile", bizMember.getMobile());
        mmap.put("memberName", bizMember.getMemberName());
        mmap.put("accountType", accountType);
        return prefix + "/accountDetail";
    }

    /**
     * 查询会员账户明细
     */
    @RequiresPermissions("business:member:view")
    @PostMapping("/listAccountDetail")
    @ResponseBody
    public TableDataInfo listAccountDetail(BizAccountDetail bizAccountDetail)
    {
        startPage();
        List<BizAccountDetail> list = bizAccountService.selectBizAccountDetailList(bizAccountDetail);
        return getDataTable(list);
    }

    /**
     * 会员架构
     */
    @RequiresPermissions("business:member:view")
    @GetMapping("/accountTeam")
    public String accountTeam(Long memberID, ModelMap mmap)
    {
        BizMember bizMember = bizMemberService.selectBizMemberSimple(memberID);
        mmap.put("memberID", memberID);
        mmap.put("memberName", bizMember.getMemberName());
        mmap.put("productList", bizProductService.selectTeamProductList());
        return prefix + "/accountTeam";
    }

    /**
     * 查询会员架构
     */
    @RequiresPermissions("business:member:view")
    @PostMapping("/accountTeamDetail")
    @ResponseBody
    public AjaxResult accountTeamDetail(Long memberID, Long productID)
    {
        Map paramMap = new HashMap();
        paramMap.put("memberID", memberID);
        paramMap.put("productID", productID);
        List<Map> teamList = bizMemberService.selectTeamData(paramMap);
        Map temp = new HashMap();
        Map temp2 = new HashMap();
        long teamNum = 0;
        //取出架构人员数据
        for (Map item : teamList) {
            Long rID = (Long) item.get("recommend_id");
            List<Map> chList = (List<Map>) temp.get(rID);
            if (chList == null) {
                chList = new ArrayList();
                temp.put(rID, chList);
            }
            chList.add(item);
            teamNum += ((BigDecimal) item.get("num")).longValue();
        }
        //重组数据
        for (Map item : teamList) {
            Long id = (Long) item.get("id");
            List<Map> chList = (List<Map>) temp.get(id);
            if (chList != null) {
                item.put("children", chList);
            }
        }
        //取出团队盒数等级配置
        List<SysDictData> levels = DictUtils.getDictCache("busi_teamaward_level");
        int numLimit = Integer.parseInt(DictUtils.getDictLabel("busi_award_set", "1"));
        long teamDou = getTeamDou(teamNum, levels, numLimit);
        //归总直属下级盒数
        List<Map> list = (List<Map>) temp.get(memberID);
        if (list != null) {
            for (Map item : list) {
                Long id = (Long) item.get("id");
                long num = ((BigDecimal) item.get("num")).longValue();
                item.put("totalNum", num);
                getTeamNum((List<Map>) temp.get(id), item);
                item.put("desc", getTeamDesc((Long) item.get("totalNum"), levels, numLimit, teamDou));
            }
        }
        Map resultMap = new HashMap();
        resultMap.put("teamNum", teamNum);
        resultMap.put("memberList", list);
        return AjaxResult.success(resultMap);
    }

    //取出子级团队盒数
    private void getTeamNum(List<Map> chList, Map parent)
    {
        if (chList == null) return;
        for (Map item : chList) {
            long num = ((BigDecimal) item.get("num")).longValue();
            long totalNum = (Long) parent.get("totalNum");
            parent.put("totalNum", totalNum + num);
            List<Map> children = (List<Map>) item.get("children");
            if (children != null) {
                getTeamNum(children, parent);
            }
        }
    }

    //取得当前团队盒数对应分成
    public static long getTeamDou(long totalNum, List<SysDictData> levels, int numLimit)
    {
        if (totalNum <= numLimit) return 0L;
        for (SysDictData data : levels) {
            String label = data.getDictLabel();
            long dou = Long.parseLong(data.getDictValue());
            String[] split = label.split("-");
            long begin = Long.parseLong(split[0]);
            long end = Long.parseLong(split[1]);
            if (totalNum >= begin && totalNum <= end) {
                return dou;
            }
        }
        return 0L;
    }

    //取出团队盒数说明
    private String getTeamDesc(long totalNum, List<SysDictData> levels, int numLimit, long teamDou)
    {
        long dou = getTeamDou(totalNum, levels, numLimit);
        if (teamDou == 0) {
            return "[团队盒数" + totalNum + " 无分成]";
        }
        return "[团队盒数" + totalNum + " 分成" + teamDou + "-" + dou + "=" + (teamDou - dou) + "福豆]";
    }

    /**
     * 导出会员列表
     */
    @RequiresPermissions("business:member:export")
    @Log(title = "会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizMember bizMember)
    {
        List<BizMember> list = bizMemberService.selectBizMemberList(bizMember);
        ExcelUtil<BizMember> util = new ExcelUtil<BizMember>(BizMember.class);
        return util.exportExcel(list, "member");
    }

    /**
     * 新增会员
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存会员
     */
    @RequiresPermissions("business:member:add")
    @Log(title = "会员", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BizMember bizMember)
    {
        return toAjax(bizMemberService.insertBizMember(bizMember));
    }

    /**
     * 修改会员
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BizMember bizMember = bizMemberService.selectBizMemberById(id);
        mmap.put("bizMember", bizMember);
        return prefix + "/edit";
    }

    /**
     * 修改保存会员
     */
    @RequiresPermissions("business:member:edit")
    @Log(title = "会员", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BizMember bizMember)
    {
        return toAjax(bizMemberService.updateBizMemberAndDou(bizMember));
    }

    /**
     * 查看会员密码
     */
    @RequiresPermissions("business:member:edit")
    @Log(title = "会员密码", businessType = BusinessType.UPDATE)
    @PostMapping("/showPassword")
    @ResponseBody
    public AjaxResult showPassword(Long memberID)
    {
        BizMember bizMember = bizMemberService.selectBizMemberSimple(memberID);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("pwd", Encrypt.decrypt(bizMember.getPassword()));
        return AjaxResult.success(resultMap);
    }

    /**
     * 修改会员密码
     */
    @RequiresPermissions("business:member:edit")
    @Log(title = "会员密码", businessType = BusinessType.UPDATE)
    @PostMapping("/editPassword")
    @ResponseBody
    public AjaxResult editPassword(Long memberID, String password)
    {
        BizMember bizMember = bizMemberService.selectBizMemberSimple(memberID);
        if(bizMember == null || StringUtils.isEmpty(password)) return toAjax(0);
        //加密
        bizMember.setPassword(Encrypt.encrypt(password));
        return toAjax(bizMemberService.updateBizMember(bizMember));
    }

    /**
     * 删除会员
     */
    @RequiresPermissions("business:member:remove")
    @Log(title = "会员", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bizMemberService.deleteBizMemberByIds(ids));
    }
}
