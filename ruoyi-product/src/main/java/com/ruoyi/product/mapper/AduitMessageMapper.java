package com.ruoyi.product.mapper;

import com.ruoyi.product.domain.AduitMessage;
import java.util.List;

/**
 * productMapper接口
 *
 * @author ruoyi
 * @date 2021-04-29
 */
public interface AduitMessageMapper
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
     * 删除product
     *
     * @param id productID
     * @return 结果
     */
    public int deleteAduitMessageById(Long id);

    /**
     * 批量删除product
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAduitMessageByIds(String[] ids);


    /**
     * @Description: 批量插入
     *
     * @param
     * @return
     * @Date
     * @author: wanghao
     *
     */
    public void insertBatch(List<AduitMessage> aduitMessages);
}
