package com.wuzhen.web.controller.busi;

import com.wuzhen.common.annotation.Log;
import com.wuzhen.common.core.controller.BaseController;
import com.wuzhen.common.core.domain.AjaxResult;
import com.wuzhen.common.core.page.TableDataInfo;
import com.wuzhen.common.enums.BusinessType;
import com.wuzhen.common.utils.poi.ExcelUtil;
import com.wuzhen.system.domain.EnrollUser;
import com.wuzhen.system.service.IEnrollUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 报名用户查询
 *
 * @author zhengzheng
 */
@Controller
@RequestMapping("/enroll/user")
public class EnrollUserController extends BaseController
{
    private String prefix = "enroll/user";

    @Autowired
    private IEnrollUserService iEnrollUserService;

    @RequiresPermissions("enroll:user:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/index";
    }

    @RequiresPermissions("enroll:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EnrollUser enrollUser)
    {
        startPage();
        List<EnrollUser> list = iEnrollUserService.selectEnrollUserList(enrollUser);
        return getDataTable(list);
    }

    @Log(title = "用户导出管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("enroll:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EnrollUser enrollUser)
    {
        List<EnrollUser> list = iEnrollUserService.selectEnrollUserList(enrollUser);
        ExcelUtil<EnrollUser> util = new ExcelUtil<EnrollUser>(EnrollUser.class);
        return util.exportExcel(list, "报名用户数据");
    }

//    @RequiresPermissions("system:post:remove")
//    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
//    @PostMapping("/remove")
//    @ResponseBody
//    public AjaxResult remove(String ids)
//    {
//        try
//        {
//            return toAjax(postService.deletePostByIds(ids));
//        }
//        catch (Exception e)
//        {
//            return error(e.getMessage());
//        }
//    }
//
//    /**
//     * 新增岗位
//     */
//    @GetMapping("/add")
//    public String add()
//    {
//        return prefix + "/add";
//    }
//
//    /**
//     * 新增保存岗位
//     */
//    @RequiresPermissions("system:post:add")
//    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
//    @PostMapping("/add")
//    @ResponseBody
//    public AjaxResult addSave(@Validated SysPost post)
//    {
//        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(postService.checkPostNameUnique(post)))
//        {
//            return error("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
//        }
//        else if (UserConstants.POST_CODE_NOT_UNIQUE.equals(postService.checkPostCodeUnique(post)))
//        {
//            return error("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
//        }
//        post.setCreateBy(getLoginName());
//        return toAjax(postService.insertPost(post));
//    }
//
//    /**
//     * 修改岗位
//     */
//    @RequiresPermissions("system:post:edit")
//    @GetMapping("/edit/{postId}")
//    public String edit(@PathVariable("postId") Long postId, ModelMap mmap)
//    {
//        mmap.put("post", postService.selectPostById(postId));
//        return prefix + "/edit";
//    }
//
//    /**
//     * 修改保存岗位
//     */
//    @RequiresPermissions("system:post:edit")
//    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
//    @PostMapping("/edit")
//    @ResponseBody
//    public AjaxResult editSave(@Validated SysPost post)
//    {
//        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(postService.checkPostNameUnique(post)))
//        {
//            return error("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
//        }
//        else if (UserConstants.POST_CODE_NOT_UNIQUE.equals(postService.checkPostCodeUnique(post)))
//        {
//            return error("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
//        }
//        post.setUpdateBy(getLoginName());
//        return toAjax(postService.updatePost(post));
//    }
//
//    /**
//     * 校验岗位名称
//     */
//    @PostMapping("/checkPostNameUnique")
//    @ResponseBody
//    public String checkPostNameUnique(SysPost post)
//    {
//        return postService.checkPostNameUnique(post);
//    }
//
//    /**
//     * 校验岗位编码
//     */
//    @PostMapping("/checkPostCodeUnique")
//    @ResponseBody
//    public String checkPostCodeUnique(SysPost post)
//    {
//        return postService.checkPostCodeUnique(post);
//    }
}
