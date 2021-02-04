package com.ruoyi.province.platform.service;

import java.util.List;
import com.ruoyi.province.platform.domain.ItemParamters;

/**
 * 商品参数Service接口
 * 
 * @author dalin
 * @date 2021-01-14
 */
public interface IItemParamtersService 
{
    /**
     * 查询商品参数
     * 
     * @param itemParamtersId 商品参数ID
     * @return 商品参数
     */
    public ItemParamters selectItemParamtersById(Long itemParamtersId);

            /**
         * 查询商品参数
         *
         * @param itemParamters 商品参数ID
         * @return 商品参数
         */
        public String checkItemParamtersUnique(ItemParamters itemParamters);
    
    /**
     * 查询商品参数列表
     * 
     * @param itemParamters 商品参数
     * @return 商品参数集合
     */
    public List<ItemParamters> selectItemParamtersList(ItemParamters itemParamters);

    /**
     * 新增商品参数
     * 
     * @param itemParamters 商品参数
     * @return 结果
     */
    public int insertItemParamters(ItemParamters itemParamters);

    /**
     * 修改商品参数
     * 
     * @param itemParamters 商品参数
     * @return 结果
     */
    public int updateItemParamters(ItemParamters itemParamters);

    /**
     * 批量删除商品参数
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteItemParamtersByIds(String ids);

    /**
     * 删除商品参数信息
     * 
     * @param itemParamtersId 商品参数ID
     * @return 结果
     */
    public int deleteItemParamtersById(Long itemParamtersId);
}
