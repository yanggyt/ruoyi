package com.ruoyi.product.service;

import com.ruoyi.product.domain.AduitMessage;
import java.util.List;

/**
 * productService接口
 * 
 * @author ruoyi
 * @date 2021-04-29
 */
public interface IAduitMessageService 
{
    /**
     * 查询product
     * 
     * @param id productID
     * @return product
     */
    public AduitMessage selectAduitMessageById(Long id);

    /**
     * 查询product列表
     * 
     * @param aduitMessage product
     * @return product集合
     */
    public List<AduitMessage> selectAduitMessageList(AduitMessage aduitMessage);

    /**
     * 新增product
     * 
     * @param aduitMessage product
     * @return 结果
     */
    public int insertAduitMessage(AduitMessage aduitMessage);

    /**
     * 修改product
     * 
     * @param aduitMessage product
     * @return 结果
     */
    public int updateAduitMessage(AduitMessage aduitMessage);

    /**
     * 批量删除product
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAduitMessageByIds(String ids);

    /**
     * 删除product信息
     * 
     * @param id productID
     * @return 结果
     */
    public int deleteAduitMessageById(Long id);
}
