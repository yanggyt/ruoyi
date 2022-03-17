package com.wuzhen.system.service.impl;

import com.wuzhen.system.domain.EnrollUser;
import com.wuzhen.system.mapper.EnrollUserMapper;
import com.wuzhen.system.service.IEnrollUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 岗位信息 服务层处理
 * 
 * @author zhengzheng
 */
@Service
public class EnrollUserServiceImpl implements IEnrollUserService
{
    @Autowired
    private EnrollUserMapper enrollUserMapper;

    /**
     * 查询报名用户信息集合
     * 
     * @param enrollUser 报名信息
     * @return 报名信息集合
     */
    @Override
    public List<EnrollUser> selectEnrollUserList(EnrollUser enrollUser)
    {
        return enrollUserMapper.selectEnrollUserList(enrollUser);
    }

}
