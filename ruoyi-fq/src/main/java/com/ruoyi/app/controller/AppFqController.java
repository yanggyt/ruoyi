package com.ruoyi.app.controller;

import com.ruoyi.app.service.AppCommonService;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.shiro.jwt.JWTUtil;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封铅app接口
 * 
 * @author mario
 * @date 2020-07-02
 */
@Controller
@RequestMapping("/app/")
public class AppFqController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private AppCommonService appCommonService;

    @Autowired
    private ISysDictDataService sysDictDataService;

    /**
     * 返回字典
     */
    @GetMapping("/dic")
    @ResponseBody
    public AjaxResult dic()
    {
        Map<String,Object> dic = new HashMap<>();
        //区域字典
        SysDept dept = new SysDept();
        dept.setParentId(200L); //区域id
        List<SysDept> deptList = appCommonService.selectDeptList(dept);

        dic.put("dept",deptList);
        //状态字典
        SysDictData parms = new SysDictData();

        //封铅包状态
        parms.setDictType("fq_p_status");
        List <SysDictData> fq_p_status = sysDictDataService.selectDictDataList(parms);
        dic.put("fq_p_status",fq_p_status);

        //封铅状态
        parms = new SysDictData();
        parms.setDictType("fq_f_type");
        List <SysDictData> fq_f_type = sysDictDataService.selectDictDataList(parms);
        dic.put("fq_f_type",fq_f_type);

        //封铅包日志状态
        parms = new SysDictData();
        parms.setDictType("fq_l_type");
        List <SysDictData> fq_l_type = sysDictDataService.selectDictDataList(parms);
        dic.put("fq_l_type",fq_l_type);

        return AjaxResult.success("操作成功",dic);
    }

    /**
     * app登录
     */
    @PostMapping("/fq/appLogin")
    @ResponseBody
    @CrossOrigin
    public AjaxResult appLogin(@RequestBody  SysUser sysUser)
    {
        SysUser realUser = userService.selectUserByLoginName(sysUser.getLoginName());

        if (realUser == null) {
            return AjaxResult.error("用户名错误");
        } else if (!passwordService.matches(realUser,sysUser.getPassword())) {
            return error("密码错误");
        }
        String tokenStr = JWTUtil.createToken(sysUser.getUserName());
        //JWTToken jwtToken = new JWTToken(token);
        //提交给realm进行登入，如果错误就会抛出异常并被捕获
        //Subject subject = SecurityUtils.getSubject();
        //subject.login(jwtToken);

        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getLoginName(), sysUser.getPassword(), false);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return AjaxResult.success("登录成功",tokenStr);
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }


}
