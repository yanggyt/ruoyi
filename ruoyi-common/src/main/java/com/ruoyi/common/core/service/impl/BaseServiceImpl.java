package com.ruoyi.common.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.mapper.BaseCrudMapper;
import com.ruoyi.common.core.service.BaseService;

/**
 * @author liuxing
 */
public class BaseServiceImpl<M extends BaseCrudMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
}
