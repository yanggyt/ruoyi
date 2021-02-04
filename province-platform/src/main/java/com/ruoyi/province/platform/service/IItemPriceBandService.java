package com.ruoyi.province.platform.service;

import java.util.List;
import com.ruoyi.province.platform.domain.ItemPriceBand;

/**
 * 商品价格带Service接口
 * 
 * @author dalin
 * @date 2020-12-24
 */
public interface IItemPriceBandService 
{
    /**
     * 查询商品价格带
     * 
     * @param priceBandId 商品价格带ID
     * @return 商品价格带
     */
    public ItemPriceBand selectItemPriceBandById(Long priceBandId);

            /**
         * 查询商品价格带
         *
         * @param itemPriceBand 商品价格带ID
         * @return 商品价格带
         */
        public String checkItemPriceBandUnique(ItemPriceBand itemPriceBand);
    
    /**
     * 查询商品价格带列表
     * 
     * @param itemPriceBand 商品价格带
     * @return 商品价格带集合
     */
    public List<ItemPriceBand> selectItemPriceBandList(ItemPriceBand itemPriceBand);

    /**
     * 新增商品价格带
     * 
     * @param itemPriceBand 商品价格带
     * @return 结果
     */
    public int insertItemPriceBand(ItemPriceBand itemPriceBand);

    /**
     * 修改商品价格带
     * 
     * @param itemPriceBand 商品价格带
     * @return 结果
     */
    public int updateItemPriceBand(ItemPriceBand itemPriceBand);

    /**
     * 批量删除商品价格带
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteItemPriceBandByIds(String ids);

    /**
     * 删除商品价格带信息
     * 
     * @param priceBandId 商品价格带ID
     * @return 结果
     */
    public int deleteItemPriceBandById(Long priceBandId);
}
