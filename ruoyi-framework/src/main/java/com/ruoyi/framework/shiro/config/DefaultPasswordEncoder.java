package com.ruoyi.framework.shiro.config;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Description 默认的密码加密
 * @Author 安安汐而
 * @Date 2019/6/4
 **/
public class DefaultPasswordEncoder implements RyPasswordEncoder
{
    @Override
    public String encode(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex().toString();
    }
}
