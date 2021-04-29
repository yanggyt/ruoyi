package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.ApplyUser;
import java.util.List;

/**
 * productMapper接口
 * 
 * @author ruoyi
 * @date 2021-04-29
 */
public interface ApplyUserMapper 
{
    /**
     * 查询product
     * 
     * @param id productID
     * @return product
     */
    public ApplyUser selectApplyUserById(Long id);

    /**
     * 查询product列表
     * 
     * @param applyUser product
     * @return product集合
     */
    public List<ApplyUser> selectApplyUserList(ApplyUser applyUser);

    /**
     * 新增product
     * 
     * @param applyUser product
     * @return 结果
     */
    public int insertApplyUser(ApplyUser applyUser);

    /**
     * 修改product
     * 
     * @param applyUser product
     * @return 结果
     */
    public int updateApplyUser(ApplyUser applyUser);

    /**
     * 删除product
     * 
     * @param id productID
     * @return 结果
     */
    public int deleteApplyUserById(Long id);

    /**
     * 批量删除product
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteApplyUserByIds(String[] ids);
}
