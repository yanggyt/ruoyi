package com.ruoyi.test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("TestVerifyMapper")
public interface TestVerifyMapper {
    //检查姓名是否唯一
    int isNameUnique(String name);
}
