package com.ruoyi.store.service;

import com.ruoyi.store.domain.VGoodItem;
import java.util.List;

/**
 * 商品分类 服务层
 * 
 * @author Enzo
 * @date 2019-03-25
 */
public interface IVGoodItemService 
{
	/**
     * 查询商品分类信息
     * 
     * @param id 商品分类ID
     * @return 商品分类信息
     */
	VGoodItem selectVGoodItemById(Long id);
	
	/**
     * 查询商品分类列表
     * 
     * @param vGoodItem 商品分类信息
     * @return 商品分类集合
     */
	List<VGoodItem> selectVGoodItemList(VGoodItem vGoodItem);
	
	/**
     * 新增商品分类
     * 
     * @param vGoodItem 商品分类信息
     * @return 结果
     */
	int insertVGoodItem(VGoodItem vGoodItem);
	
	/**
     * 修改商品分类
     * 
     * @param vGoodItem 商品分类信息
     * @return 结果
     */
	int updateVGoodItem(VGoodItem vGoodItem);
		
	/**
     * 删除商品分类信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteVGoodItemByIds(String ids);


    /**
    * 删除商品分类 单条信息
	* @return
	*/
	int deleteVGoodItemById(Long id);
}
