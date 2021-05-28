package com.ruoyi.test.service.impl;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.test.mapper.TestVerifyMapper;
import com.ruoyi.test.service.TestVerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DataSource(value = DataSourceType.SQLSVR)
public class TestVerifyServiceImpl implements TestVerifyService {
    @Autowired
    private TestVerifyMapper testVerifyMapper;
    @Override
    public int isNameUnique(String name) {
        return testVerifyMapper.isNameUnique(name);
    }
}
