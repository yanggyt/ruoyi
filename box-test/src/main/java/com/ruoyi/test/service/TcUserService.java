package com.ruoyi.test.service;

import com.ruoyi.test.domain.TcUser;

import java.util.List;

public interface TcUserService {
    //查询所有用户
    List<TcUser> selectAll();

    //根据id查询用户信息
    TcUser selectById(int id);

    //新增用户
    TcUser insert (TcUser tcUser);

    // 根据id删除
    int deleteById (int id);

    //更新用户信息
    int updateById(TcUser tcUser);
}
