package com.ruoyi.test.service.impl;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.test.domain.TcUser;
import com.ruoyi.test.mapper.TcUserMapper;
import com.ruoyi.test.service.TcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSource(value = DataSourceType.SQLSVR)
public class TcUserServiceImpl implements TcUserService {
    @Autowired
    private TcUserMapper tcUserMapper;

    //查询所有用户
    @Override
    public List<TcUser> selectAll() {
        return tcUserMapper.selectAll();
    }

    //查询 by id
    @Override
    public TcUser selectById(int id) {
        return tcUserMapper.selectById(id);
    }

    //新增
    @Override
    public TcUser insert(TcUser tcUser) {
        int user=tcUserMapper.insert(tcUser);
        return tcUser;
    }

    //删除by id
    @Override
    public int deleteById(int id) {
        return tcUserMapper.deleteById(id);
    }
    //更新 by id
    @Override
    public int updateById(TcUser tcUser) {
        return tcUserMapper.updateById(tcUser);
    }
}
