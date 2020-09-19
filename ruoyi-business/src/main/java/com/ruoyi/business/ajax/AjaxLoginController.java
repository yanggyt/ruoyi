package com.ruoyi.business.ajax;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.business.domain.BizMember;
import com.ruoyi.business.model.Member;
import com.ruoyi.business.service.IBizMemberService;
import com.ruoyi.business.utils.Encrypt;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.JWTUtil;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

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
    public AjaxResult login(String mobile, String password) {
        if (StringUtils.isBlank(mobile) || StringUtils.isBlank(password)) {
            return AjaxResult.warn("请输入用户名密码");
        }

        BizMember bizMember = bizMemberService.selectBizMemberByMobile(mobile);
        if (Objects.isNull(bizMember)) {
            return AjaxResult.warn("用户名或密码错误");
        }
        // DES加密
        String encryptPassword = Encrypt.encrypt(password);
        if (!encryptPassword.equals(bizMember.getPassword())) {
            return AjaxResult.warn("用户名或密码错误");
        }

        if (bizMember.getIsEnable() == 0) {
            return AjaxResult.warn("账户已禁用，请联系系统管理员");
        }

        Member member = new Member(bizMember.getId(), bizMember.getMemberName(), bizMember.getMobile());

        Long day = 1000L * 60L * 60L;
        String token = JWTUtil.createJWT(member.toJsonString(), day);
        return AjaxResult.success(token);
    }
}
