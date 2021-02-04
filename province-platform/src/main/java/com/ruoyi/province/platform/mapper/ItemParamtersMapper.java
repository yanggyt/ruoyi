package com.ruoyi.province.platform.mapper;

import java.util.List;
import com.ruoyi.province.platform.domain.ItemParamters;
import com.ruoyi.province.platform.domain.ItemParamtersUnit;

/**
 * 商品参数Mapper接口
 * 
 * @author dalin
 * @date 2021-01-14
 */
public interface ItemParamtersMapper 
{
    /**
     * 查询商品参数
     * 
     * @param itemParamtersId 商品参数ID
     * @return 商品参数
     */
    public ItemParamters selectItemParamtersById(Long itemParamtersId);

            /**
         * 校验 商品参数 名称是否重复
         *
         * @param ItemParamtersName
         * @return 商品参数
         */
        public ItemParamters checkItemParamtersUnique(String ItemParamtersName);
    
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
     * 删除商品参数
     * 
     * @param itemParamtersId 商品参数ID
     * @return 结果
     */
    public int deleteItemParamtersById(Long itemParamtersId);

    /**
     * 批量删除商品参数
     * 
     * @param itemParamtersIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteItemParamtersByIds(String[] itemParamtersIds);

    /**
     * 批量删除商品参数_计量单位
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteItemParamtersUnitByItemParamtersIds(String[] itemParamtersIds);
    
    /**
     * 批量新增商品参数_计量单位
     * 
     * @param itemParamtersUnitList 商品参数_计量单位列表
     * @return 结果
     */
    public int batchItemParamtersUnit(List<ItemParamtersUnit> itemParamtersUnitList);
    

    /**
     * 通过商品参数ID删除商品参数_计量单位信息
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteItemParamtersUnitByItemParamtersId(Long itemParamtersId);


}
