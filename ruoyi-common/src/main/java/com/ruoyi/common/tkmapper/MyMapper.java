package com.ruoyi.common.tkmapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自定义通用mapper
 *
 * @author solo
 * @date 2019/08/16.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
