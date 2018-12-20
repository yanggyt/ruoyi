package com.ruoyi.exam.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.exam.domain.ExamPaperCategory;
import com.ruoyi.exam.domain.ExamQuestion;
import com.ruoyi.exam.domain.ExamQuestionCategory;
import com.ruoyi.exam.service.IExamPaperCategoryService;
import com.ruoyi.exam.service.IExamPaperQuestionService;
import com.ruoyi.framework.web.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.exam.domain.ExamPaper;
import com.ruoyi.exam.service.IExamPaperService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 试卷 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-16
 */
@Controller
@RequestMapping("/exam/examPaper")
public class ExamPaperController extends BaseController
{
    private String prefix = "exam/examPaper";
	
	@Autowired
	private IExamPaperService examPaperService;

	@Autowired
	private IExamPaperCategoryService examPaperCategoryService;

	@Autowired
	private IExamPaperQuestionService examPaperQuestionService;
	
	@RequiresPermissions("exam:examPaper:view")
	@GetMapping()
	public String examPaper()
	{
	    return prefix + "/examPaper";
	}
	
	/**
	 * 查询试卷列表
	 */
	@RequiresPermissions("exam:examPaper:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ExamPaper examPaper)
	{
        List<ExamPaper> list = new ArrayList<>();
		List<ExamPaper> listByIds = getListByIds(list, examPaper);
		return getDataTable(listByIds);
	}

	/**
	 * 递归找到所有下级的试卷
	 * @param list
	 * @param examPaper
	 * @return
	 */
	private List<ExamPaper> getListByIds(List<ExamPaper> list,ExamPaper examPaper){
		list.addAll(examPaperService.selectExamPaperList(examPaper));
		String categoryId = examPaper.getExamPaperCategoryId().toString();
		ExamPaperCategory examPaperCategory = new ExamPaperCategory();
		examPaperCategory.setParentId(Integer.parseInt(categoryId));
		List<ExamPaperCategory> examPaperCategorys = examPaperCategoryService.selectList(examPaperCategory);
		for (ExamPaperCategory questionCategory : examPaperCategorys) {
			examPaper.setExamPaperCategoryId(questionCategory.getId());
			getListByIds(list,examPaper);
		}
		return list;
	}
	
	
	/**
	 * 导出试卷列表
	 */
	@RequiresPermissions("exam:examPaper:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExamPaper examPaper)
    {
    	List<ExamPaper> list = examPaperService.selectExamPaperList(examPaper);
        ExcelUtil<ExamPaper> util = new ExcelUtil<ExamPaper>(ExamPaper.class);
        return util.exportExcel(list, "examPaper");
    }
	
	/**
	 * 新增试卷
	 */
	@GetMapping("/add/{id}")
	public String add(@PathVariable("id") String id, ModelMap mmap)
	{
		mmap.put("examPaperCategoryId",id);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存试卷
	 */
	@RequiresPermissions("exam:examPaper:add")
	@Log(title = "试卷", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExamPaper examPaper)
	{
		examPaper.setCreateBy(ShiroUtils.getLoginName());
		examPaper.setCreateDate(new Date());
		examPaper.setDelFlag("0");
		return toAjax(examPaperService.insert(examPaper));
	}

	/**
	 * 修改试卷
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ExamPaper examPaper = examPaperService.selectById(id);
		mmap.put("examPaper", examPaper);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存试卷
	 */
	@RequiresPermissions("exam:examPaper:edit")
	@Log(title = "试卷", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExamPaper examPaper)
	{
		examPaper.setUpdateBy(ShiroUtils.getLoginName());
		examPaper.setUpdateDate(new Date());
		return toAjax(examPaperService.updateSelectiveById(examPaper));
	}
	
	/**
	 * 删除试卷
	 */
	@RequiresPermissions("exam:examPaper:remove")
	@Log(title = "试卷", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(examPaperService.deleteByIds(ids));
	}


	@GetMapping("/addQuestion/{id}")
	public String addQuestion(@PathVariable("id") Integer id, ModelMap mmap)
	{
		mmap.put("examPaperId", id);
		mmap.put("examPaperQuestionIds",examPaperQuestionService.selectQuestionIdsForPaperId(id));
		return prefix + "/examQuestion";
	}

	@RequiresPermissions("exam:examPaper:remove")
	@Log(title = "试卷", businessType = BusinessType.DELETE)
	@PostMapping( "/saveQuestion")
	@ResponseBody
	public AjaxResult saveQuestion(@RequestParam(value = "questionId[]" ,required = false) String[] questionId,@RequestParam("paperId")String paperId)
	{

		return toAjax(examPaperQuestionService.saveQuestion(paperId,questionId));
	}
	
}
