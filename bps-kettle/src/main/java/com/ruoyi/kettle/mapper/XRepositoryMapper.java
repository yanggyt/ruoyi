package com.ruoyi.kettle.mapper;

import java.util.List;
import com.ruoyi.kettle.domain.XRepository;

/**
 * 资源库Mapper接口
 * 
 * @author kone
 * @date 2021-07-12
 */
public interface XRepositoryMapper 
{
    /**
     * 查询资源库
     * 
     * @param id 资源库ID
     * @return 资源库
     */
    public XRepository selectXRepositoryById(Long id);

    /**
     * 查询资源库列表
     * 
     * @param xRepository 资源库
     * @return 资源库集合
     */
    public List<XRepository> selectXRepositoryList(XRepository xRepository);

    /**
     * 新增资源库
     * 
     * @param xRepository 资源库
     * @return 结果
     */
    public int insertXRepository(XRepository xRepository);

    /**
     * 修改资源库
     * 
     * @param xRepository 资源库
     * @return 结果
     */
    public int updateXRepository(XRepository xRepository);

    /**
     * 删除资源库
     * 
     * @param id 资源库ID
     * @return 结果
     */
    public int deleteXRepositoryById(Long id);

    /**
     * 批量删除资源库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteXRepositoryByIds(String[] ids);
}
