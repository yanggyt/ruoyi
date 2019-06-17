package com.ruoyi.template.mapper;

import com.ruoyi.template.domain.TmplServerMemory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务器内存类型 数据层
 *
 * @author TP
 * @date 2019-06-16
 */
public interface TmplServerMemoryMapper {
    /**
     * 查询服务器内存类型信息
     *
     * @param serverMemoryId 服务器内存类型ID
     * @return 服务器内存类型信息
     */
    public TmplServerMemory selectTmplServerMemoryById(Integer serverMemoryId);

    /**
     * 查询服务器内存类型信息
     *
     * @param serverId 服务器ID
     * @return 服务器内存类型信息
     */
    public TmplServerMemory selectByServerId(@Param("serverId") Integer serverId);

    /**
     * 查询服务器内存类型列表
     *
     * @param tmplServerMemory 服务器内存类型信息
     * @return 服务器内存类型集合
     */
    public List<TmplServerMemory> selectTmplServerMemoryList(TmplServerMemory tmplServerMemory);

    /**
     * 新增服务器内存类型
     *
     * @param tmplServerMemory 服务器内存类型信息
     * @return 结果
     */
    public int insertTmplServerMemory(TmplServerMemory tmplServerMemory);

    /**
     * 修改服务器内存类型
     *
     * @param tmplServerMemory 服务器内存类型信息
     * @return 结果
     */
    public int updateTmplServerMemory(TmplServerMemory tmplServerMemory);

    /**
     * 删除服务器内存类型
     *
     * @param serverMemoryId 服务器内存类型ID
     * @return 结果
     */
    public int deleteTmplServerMemoryById(Integer serverMemoryId);

    /**
     * 通过服务器ID批量删除服务器内存类型
     *
     * @param serverId 服务器ID
     * @return 结果
     */
    public int deleteTmplServerMemoryByServerId(Integer serverId);

    /**
     * 批量删除服务器内存类型
     *
     * @param serverMemoryIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTmplServerMemoryByIds(String[] serverMemoryIds);

    /**
     * 批量新增服务器内存类型信息
     *
     * @param tmplServerMemoryList 服务器内存类型列表
     * @return 结果
     */
    public int batchTmplServerMemory(List<TmplServerMemory> tmplServerMemoryList);

}