package com.ruoyi.exam.controller;

import java.util.List;
import java.util.Map;

import com.ruoyi.exam.domain.ExamUserExaminationVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.domain.ExamUserExamination;
import com.ruoyi.exam.service.IExamUserExaminationService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 我的考试记录 信息操作处理
 * 
 * @author zhujj
 * @date 2019-01-12
 */
@Controller
@RequestMapping("/exam/examUserExamination")
public class ExamUserExaminationController extends BaseController
{
    private String prefix = "exam/examUserExamination";
	
	@Autowired
	private IExamUserExaminationService examUserExaminationService;
	
	@RequiresPermissions("exam:examUserExamination:view")
	@GetMapping()
	public String examUserExamination()
	{
	    return prefix + "/examUserExamination";
	}
	
	/**
	 * 查询我的考试记录列表
	 */
	@RequiresPermissions("exam:examUserExamination:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(@RequestParam  Map<String,Object> map)
	{
        List<ExamUserExaminationVO> list = examUserExaminationService.selectMyExamUserExamination(map);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出我的考试记录列表
	 */
	@RequiresPermissions("exam:examUserExamination:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExamUserExamination examUserExamination)
    {
    	List<ExamUserExamination> list = examUserExaminationService.selectExamUserExaminationList(examUserExamination);
        ExcelUtil<ExamUserExamination> util = new ExcelUtil<ExamUserExamination>(ExamUserExamination.class);
        return util.exportExcel(list, "examUserExamination");
    }
	
	/**
	 * 新增我的考试记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存我的考试记录
	 */
	@RequiresPermissions("exam:examUserExamination:add")
	@Log(title = "我的考试记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExamUserExamination examUserExamination)
	{		
		return toAjax(examUserExaminationService.insert(examUserExamination));
	}

	/**
	 * 修改我的考试记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ExamUserExamination examUserExamination = examUserExaminationService.selectById(id);
		mmap.put("examUserExamination", examUserExamination);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存我的考试记录
	 */
	@RequiresPermissions("exam:examUserExamination:edit")
	@Log(title = "我的考试记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExamUserExamination examUserExamination)
	{		
		return toAjax(examUserExaminationService.updateById(examUserExamination));
	}
	
	/**
	 * 删除我的考试记录
	 */
	@RequiresPermissions("exam:examUserExamination:remove")
	@Log(title = "我的考试记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(examUserExaminationService.deleteByIds(ids));
	}
	
}
