package com.ruoyi.exam.controller;

import java.util.Enumeration;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.domain.ExamQuestion;
import com.ruoyi.exam.service.IExamQuestionService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;


/**
 * 问题 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-06
 */
@Controller
@RequestMapping("/exam/examQuestion")
public class ExamQuestionController extends BaseController
{
    private String prefix = "exam/examQuestion";
	
	@Autowired
	private IExamQuestionService examQuestionService;
	
	@RequiresPermissions("exam:examQuestion:view")
	@GetMapping()
	public String examQuestion()
	{
	    return prefix + "/examQuestion";
	}

	@GetMapping("/choiceadd/{id}")
	public String addChoiceUrl(@PathVariable("id") String id, ModelMap mmap)
	{
		mmap.put("categoryId",id);
		mmap.put("type",1);
		return prefix + "/choiceadd";
	}
	
	/**
	 * 查询问题列表
	 */
	@RequiresPermissions("exam:examQuestion:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ExamQuestion examQuestion)
	{
        List<ExamQuestion> list = examQuestionService.selectExamQuestionList(examQuestion);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出问题列表
	 */
	@RequiresPermissions("exam:examQuestion:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExamQuestion examQuestion)
    {
    	List<ExamQuestion> list = examQuestionService.selectExamQuestionList(examQuestion);
        ExcelUtil<ExamQuestion> util = new ExcelUtil<ExamQuestion>(ExamQuestion.class);
        return util.exportExcel(list, "examQuestion");
    }
	
	/**
	 * 新增问题
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存问题
	 */
	@RequiresPermissions("exam:examQuestion:add")
	@Log(title = "问题", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExamQuestion examQuestion,@RequestParam(value = "number", required = true) String[] number,
							  @RequestParam(value = "content", required = true) String[] content)
	{

		return toAjax(examQuestionService.insertQuestion(examQuestion,number,content));
	}

	/**
	 * 修改问题
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		ExamQuestion examQuestion = examQuestionService.selectExamQuestionById(id);
		mmap.put("examQuestion", examQuestion);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存问题
	 */
	@RequiresPermissions("exam:examQuestion:edit")
	@Log(title = "问题", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExamQuestion examQuestion)
	{		
		return toAjax(examQuestionService.updateExamQuestion(examQuestion));
	}
	
	/**
	 * 删除问题
	 */
	@RequiresPermissions("exam:examQuestion:remove")
	@Log(title = "问题", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(examQuestionService.deleteExamQuestionByIds(ids));
	}
	
}
