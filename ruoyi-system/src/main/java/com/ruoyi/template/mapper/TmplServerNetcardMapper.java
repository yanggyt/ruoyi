package com.ruoyi.template.mapper;

import com.ruoyi.template.domain.TmplServerNetcard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务器网卡类型 数据层
 *
 * @author TP
 * @date 2019-06-14
 */
public interface TmplServerNetcardMapper {
    /**
     * 查询服务器网卡类型信息
     *
     * @param serverNetcardId 服务器网卡类型ID
     * @return 服务器网卡类型信息
     */
    public TmplServerNetcard selectTmplServerNetcardById(Integer serverNetcardId);

    /**
     * 查询服务器网卡类型信息
     *
     * @param serverId 服务器ID
     * @return 服务器网卡类型信息
     */
    public TmplServerNetcard selectByServerId(@Param("serverId") Integer serverId);

    /**
     * 查询服务器网卡类型列表
     *
     * @param tmplServerNetcard 服务器网卡类型信息
     * @return 服务器网卡类型集合
     */
    public List<TmplServerNetcard> selectTmplServerNetcardList(TmplServerNetcard tmplServerNetcard);

    /**
     * 新增服务器网卡类型
     *
     * @param tmplServerNetcard 服务器网卡类型信息
     * @return 结果
     */
    public int insertTmplServerNetcard(TmplServerNetcard tmplServerNetcard);

    /**
     * 修改服务器网卡类型
     *
     * @param tmplServerNetcard 服务器网卡类型信息
     * @return 结果
     */
    public int updateTmplServerNetcard(TmplServerNetcard tmplServerNetcard);

    /**
     * 删除服务器网卡类型
     *
     * @param serverNetcardId 服务器网卡类型ID
     * @return 结果
     */
    public int deleteTmplServerNetcardById(Integer serverNetcardId);

    /**
     * 通过服务器ID批量删除服务器网卡类型
     *
     * @param serverId 服务器ID
     * @return 结果
     */
    public int deleteTmplServerNetcardByServerId(Integer serverId);

    /**
     * 批量删除服务器网卡类型
     *
     * @param serverNetcardIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTmplServerNetcardByIds(String[] serverNetcardIds);

    /**
     * 批量新增服务器网卡类型信息
     *
     * @param tmplServerNetcardList 服务器网卡类型列表
     * @return 结果
     */
    public int batchTmplServerNetcard(List<TmplServerNetcard> tmplServerNetcardList);

}