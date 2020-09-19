package com.ruoyi.business.ajax;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.business.domain.BizMember;
import com.ruoyi.business.service.IBizMemberService;
import com.ruoyi.business.utils.Encrypt;
import com.ruoyi.business.utils.JWTUtil;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import org.apache.ibatis.annotations.Param;
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

        BizMember member = bizMemberService.selectBizMemberByMobile(mobile);
        if (Objects.isNull(member)) {
            return AjaxResult.warn("用户名或密码错误");
        }
        // DES加密
        String encryptPassword = Encrypt.encrypt(password);
        if (!encryptPassword.equals(member.getPassword())) {
            return AjaxResult.warn("用户名或密码错误");
        }

        if (member.getIsEnable() == 0) {
            return AjaxResult.warn("账户已禁用，请联系系统管理员");
        }

        JSONObject object = new JSONObject();
        object.put("id", member.getId());
        object.put("name", member.getMemberName());
        object.put("mobile", member.getMobile());

        Long day = 1000L * 60L * 60L;
        String token = JWTUtil.createJWT(object.toJSONString(), day);
        return super.success(token);
    }
}
