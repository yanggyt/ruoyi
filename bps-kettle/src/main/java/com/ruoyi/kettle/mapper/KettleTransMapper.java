package com.ruoyi.kettle.mapper;

import java.util.List;
import com.ruoyi.kettle.domain.KettleTrans;
import org.apache.ibatis.annotations.Param;

/**
 * 转换Mapper接口
 * 
 * @author kone
 * @date 2021-07-14
 */
public interface KettleTransMapper 
{
    /**
     * 查询转换
     * 
     * @param id 转换ID
     * @return 转换
     */
    public KettleTrans selectKettleTransById(Long id);

    /**
     * 查询转换列表
     * 
     * @param kettleTrans 转换
     * @param roleKey
     * @return 转换集合
     */
    public List<KettleTrans> selectKettleTransList(@Param("KettleTrans") KettleTrans kettleTrans,@Param("roleKey") List<String> roleKey);

    /**
     * 新增转换
     * 
     * @param kettleTrans 转换
     * @return 结果
     */
    public int insertKettleTrans(KettleTrans kettleTrans);

    /**
     * 修改转换
     * 
     * @param kettleTrans 转换
     * @return 结果
     */
    public int updateKettleTrans(KettleTrans kettleTrans);

    /**
     * 删除转换
     * 
     * @param id 转换ID
     * @return 结果
     */
    public int deleteKettleTransById(Long id);

    /**
     * 批量删除转换
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKettleTransByIds(String[] ids);
}
