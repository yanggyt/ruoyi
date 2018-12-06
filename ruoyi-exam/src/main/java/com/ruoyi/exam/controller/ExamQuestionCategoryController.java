package com.ruoyi.web.controller.exam;

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
import com.ruoyi.exam.domain.ExamQuestionCategory;
import com.ruoyi.exam.service.IExamQuestionCategoryService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 试题分类 信息操作处理
 *
 * @author zhujj
 * @date 2018-12-06
 */
@Controller
@RequestMapping("/exam/examQuestionCategory")
public class ExamQuestionCategoryController extends BaseController
{
	private String prefix = "exam/examQuestionCategory";

	@Autowired
	private IExamQuestionCategoryService examQuestionCategoryService;

	@RequiresPermissions("exam:examQuestionCategory:view")
	@GetMapping()
	public String examQuestionCategory()
	{
		return prefix + "/examQuestionCategory";
	}

	/**
	 * 查询试题分类列表
	 */
	@RequiresPermissions("exam:examQuestionCategory:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ExamQuestionCategory examQuestionCategory)
	{
		List<ExamQuestionCategory> list = examQuestionCategoryService.selectExamQuestionCategoryList(examQuestionCategory);
		return getDataTable(list);
	}


	/**
	 * 导出试题分类列表
	 */
	@RequiresPermissions("exam:examQuestionCategory:export")
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(ExamQuestionCategory examQuestionCategory)
	{
		List<ExamQuestionCategory> list = examQuestionCategoryService.selectExamQuestionCategoryList(examQuestionCategory);
		ExcelUtil<ExamQuestionCategory> util = new ExcelUtil<ExamQuestionCategory>(ExamQuestionCategory.class);
		return util.exportExcel(list, "examQuestionCategory");
	}

	/**
	 * 新增试题分类
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存试题分类
	 */
	@RequiresPermissions("exam:examQuestionCategory:add")
	@Log(title = "试题分类", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExamQuestionCategory examQuestionCategory)
	{
		return toAjax(examQuestionCategoryService.insertExamQuestionCategory(examQuestionCategory));
	}

	/**
	 * 修改试题分类
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		ExamQuestionCategory examQuestionCategory = examQuestionCategoryService.selectExamQuestionCategoryById(id);
		mmap.put("examQuestionCategory", examQuestionCategory);
		return prefix + "/edit";
	}

	/**
	 * 修改保存试题分类
	 */
	@RequiresPermissions("exam:examQuestionCategory:edit")
	@Log(title = "试题分类", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExamQuestionCategory examQuestionCategory)
	{
		return toAjax(examQuestionCategoryService.updateExamQuestionCategory(examQuestionCategory));
	}

	/**
	 * 删除试题分类
	 */
	@RequiresPermissions("exam:examQuestionCategory:remove")
	@Log(title = "试题分类", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(examQuestionCategoryService.deleteExamQuestionCategoryByIds(ids));
	}

}
