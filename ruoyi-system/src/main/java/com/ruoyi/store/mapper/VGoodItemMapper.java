package com.ruoyi.store.mapper;

import com.ruoyi.store.domain.VGoodItem;
import java.util.List;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 商品分类 数据层
 * 
 * @author Enzo
 * @date 2019-03-25
 */
@Repository
public interface VGoodItemMapper extends Mapper<VGoodItem>
{
	/**
     * 查询商品分类信息
     * 
     * @param id 商品分类
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
     * 删除商品分类
     * 
     * @param id 商品分类ID
     * @return 结果
     */
	int deleteVGoodItemById(Long id);
	
	/**
     * 批量删除商品分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteVGoodItemByIds(String[] ids);
	
}