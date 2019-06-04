package com.ruoyi.framework.shiro.config;


/**
 * 密码验证接口
 * 自己项目里实现这个service、即可覆盖默认的实现
 *
 * @author 安安汐而
 */
public interface RyPasswordEncoder {
    /**
     * 加密密码
     *
     * @param username 用户名
     * @param salt     盐
     * @param password 原始密码
     * @return 加密后密码
     */
    String encode(String username, String password, String salt);
}
