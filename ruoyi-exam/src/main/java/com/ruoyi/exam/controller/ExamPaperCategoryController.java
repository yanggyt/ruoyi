package com.ruoyi.exam.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.framework.web.util.ShiroUtils;
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
import com.ruoyi.exam.domain.ExamPaperCategory;
import com.ruoyi.exam.service.IExamPaperCategoryService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.ExcelUtil;

/**
 * 试卷分类 信息操作处理
 * 
 * @author zhujj
 * @date 2018-12-11
 */
@Controller
@RequestMapping("/exam/examPaperCategory")
public class ExamPaperCategoryController extends BaseController
{
    private String prefix = "exam/examPaperCategory";
	
	@Autowired
	private IExamPaperCategoryService examPaperCategoryService;
	
	@RequiresPermissions("exam:examPaperCategory:view")
	@GetMapping()
	public String examPaperCategory()
	{
	    return prefix + "/examPaperCategory";
	}
	
	/**
	 * 查询试卷分类列表
	 */
	@RequiresPermissions("exam:examPaperCategory:list")
	@GetMapping("/list")
	@ResponseBody
	public List<ExamPaperCategory> list(ExamPaperCategory examPaperCategory)
	{
        List<ExamPaperCategory> list = examPaperCategoryService.selectExamPaperCategoryPage(examPaperCategory);
		return list;
	}
	
	
	/**
	 * 导出试卷分类列表
	 */
	@RequiresPermissions("exam:examPaperCategory:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExamPaperCategory examPaperCategory)
    {
    	List<ExamPaperCategory> list = examPaperCategoryService.selectExamPaperCategoryList(examPaperCategory);
        ExcelUtil<ExamPaperCategory> util = new ExcelUtil<ExamPaperCategory>(ExamPaperCategory.class);
        return util.exportExcel(list, "examPaperCategory");
    }
	
	/**
	 * 新增试卷分类
	 */
	@GetMapping("/add/{parentId}")
	public String add(@PathVariable("parentId") String parentId, ModelMap mmap)
	{
	    mmap.put("examPaperCategory",examPaperCategoryService.selectById(parentId));
		return prefix + "/add";
	}
	
	/**
	 * 新增保存试卷分类
	 */
	@RequiresPermissions("exam:examPaperCategory:add")
	@Log(title = "试卷分类", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExamPaperCategory examPaperCategory)
	{
		examPaperCategory.setCreateBy(ShiroUtils.getLoginName());
		examPaperCategory.setCreateDate(new Date());
		examPaperCategory.setDelFlag("0");
		return toAjax(examPaperCategoryService.insert(examPaperCategory));
	}

	/**
	 * 修改试卷分类
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ExamPaperCategory examPaperCategory = examPaperCategoryService.selectById(id);
		mmap.put("examPaperCategory", examPaperCategory);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存试卷分类
	 */
	@RequiresPermissions("exam:examPaperCategory:edit")
	@Log(title = "试卷分类", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExamPaperCategory examPaperCategory)
	{
		examPaperCategory.setUpdateBy(ShiroUtils.getLoginName());
		examPaperCategory.setUpdateDate(new Date());
		return toAjax(examPaperCategoryService.updateSelectiveById(examPaperCategory));
	}
	
	/**
	 * 删除试卷分类
	 */
	@RequiresPermissions("exam:examPaperCategory:remove")
	@Log(title = "试卷分类", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(examPaperCategoryService.deleteByIds(ids));
	}



	@GetMapping("/selectExamPaperCategoryTree/{examPaperCategoryId}")
	public String selectDeptTree(@PathVariable("examPaperCategoryId") String examPaperCategoryId, ModelMap mmap)
	{
		mmap.put("examPaperCategory", examPaperCategoryService.selectById(examPaperCategoryId));
		return prefix + "/tree";
	}

	/**
	 * 选择试卷分类树
	 */
	@GetMapping("/treeDataForAdd")
	@ResponseBody
	public List<Map<String, Object>> treeDataForAdd()
	{
		List<Map<String, Object>> tree = examPaperCategoryService.selectDeptTree();
		List<Map<String, Object>> res = new ArrayList<>();
		for (Map<String, Object> stringObjectMap : tree) {
			String pId = stringObjectMap.get("pId").toString();
			if(pId.equals("0")||pId.equals("1")){
				res.add(stringObjectMap);
			}

		}

		return res;
	}

	/**
	 * 删除
	 */
	@Log(title = "部门管理", businessType = BusinessType.DELETE)
	@RequiresPermissions("system:examPaperCategory:remove")
	@PostMapping("/remove/{id}")
	@ResponseBody
	public AjaxResult remove(@PathVariable("id") Integer id)
	{
		ExamPaperCategory examPaperCategory = new ExamPaperCategory();
		examPaperCategory.setParentId(id);
		if (examPaperCategoryService.selectList(examPaperCategory).size() > 0)
		{
			return error(1, "存在下级分类,不允许删除");
		}

		return toAjax(examPaperCategoryService.deleteById(id));
	}

	/**
	 * 加载列表树
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData()
	{
		List<Map<String, Object>> tree = examPaperCategoryService.selectDeptTree();
		return tree;
	}
}
