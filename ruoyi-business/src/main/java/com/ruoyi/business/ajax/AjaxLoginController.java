package com.ruoyi.business.ajax;

import com.ruoyi.business.service.IBizMemberService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 前端用户登录
 * @author bei.wu
 */
@RestController
@RequestMapping("/ajax")
public class AjaxLoginController extends BaseController {

    @Resource
    private IBizMemberService bizMemberService;

    @PostMapping("/login")
    public AjaxResult login(@Param("loginName") String loginName, @Param("password") String password) {
        return super.success();
    }
}
