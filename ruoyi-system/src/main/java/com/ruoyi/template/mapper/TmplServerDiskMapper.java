package com.ruoyi.template.mapper;

import com.ruoyi.template.domain.TmplServerDisk;
import com.ruoyi.template.domain.TmplServerNetcard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务器硬盘类型 数据层
 *
 * @author TP
 * @date 2019-06-14
 */
public interface TmplServerDiskMapper {
    /**
     * 查询服务器硬盘类型信息
     *
     * @param serverDiskId 服务器硬盘类型ID
     * @return 服务器硬盘类型信息
     */
    public TmplServerDisk selectTmplServerDiskById(Integer serverDiskId);

    /**
     * 查询服务器硬盘类型信息
     *
     * @param serverId 服务器ID
     * @return 服务器硬盘类型信息
     */
    public TmplServerNetcard selectByServerId(@Param("serverId") Integer serverId);


    /**
     * 查询服务器硬盘类型列表
     *
     * @param tmplServerDisk 服务器硬盘类型信息
     * @return 服务器硬盘类型集合
     */
    public List<TmplServerDisk> selectTmplServerDiskList(TmplServerDisk tmplServerDisk);

    /**
     * 新增服务器硬盘类型
     *
     * @param tmplServerDisk 服务器硬盘类型信息
     * @return 结果
     */
    public int insertTmplServerDisk(TmplServerDisk tmplServerDisk);

    /**
     * 修改服务器硬盘类型
     *
     * @param tmplServerDisk 服务器硬盘类型信息
     * @return 结果
     */
    public int updateTmplServerDisk(TmplServerDisk tmplServerDisk);

    /**
     * 删除服务器硬盘类型
     *
     * @param serverDiskId 服务器硬盘类型ID
     * @return 结果
     */
    public int deleteTmplServerDiskById(Integer serverDiskId);

    /**
     * 批量删除服务器硬盘类型
     *
     * @param serverDiskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTmplServerDiskByIds(String[] serverDiskIds);

    /**
     * 通过服务器ID批量删除服务器硬盘类型
     *
     * @param serverId 服务器ID
     * @return 结果
     */
    public int deleteTmplServerDiskByServerId(Integer serverId);

    /**
     * 批量新增服务器硬盘类型信息
     *
     * @param tmplServerDiskList 服务器硬盘类型列表
     * @return 结果
     */
    public int batchTmplServerDisk(List<TmplServerDisk> tmplServerDiskList);
}