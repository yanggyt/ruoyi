package com.ruoyi.business.ajax;

import com.ruoyi.business.service.IBizMemberService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.security.Md5Utils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

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
