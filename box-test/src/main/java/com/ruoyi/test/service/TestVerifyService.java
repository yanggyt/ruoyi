package com.ruoyi.test.service;

import org.springframework.stereotype.Service;

@Service
public interface TestVerifyService {
    //检查姓名是否唯一
    int isNameUnique(String name);
}
