package com.wuzhen.system.service.impl;

import com.wuzhen.system.domain.EnrollActiveUser;
import com.wuzhen.system.domain.EnrollUser;
import com.wuzhen.system.mapper.ActiveUserMapper;
import com.wuzhen.system.mapper.EnrollUserMapper;
import com.wuzhen.system.service.IActiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 岗位信息 服务层处理
 *
 * @author zhengzheng
 */
@Service
public class ActiveUserServiceImpl implements IActiveUserService {
    @Autowired
    private ActiveUserMapper activeUserMapper;

    /**
     * 查询报名用户信息集合
     *
     * @param enrollActiveUser 报名信息
     * @return 报名信息集合
     */
    @Override
    public List<EnrollActiveUser> selectActiveUserList(EnrollActiveUser enrollActiveUser) {
        return activeUserMapper.selectActiveUserList(enrollActiveUser);
    }

}
