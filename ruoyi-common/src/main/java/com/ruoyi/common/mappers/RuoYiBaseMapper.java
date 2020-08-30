package com.ruoyi.common.mappers;


import com.ruoyi.common.core.domain.BaseEntity;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 通用Mapper继承Mapper、MySqlMapper
 * @param <T>
 */
public interface RuoYiBaseMapper<T extends BaseEntity> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T>, InsertListMapper<T> {

}