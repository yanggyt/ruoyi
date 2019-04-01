package com.ruoyi.store.service.impl;

import java.util.List;

import com.ruoyi.common.core.page.ExampleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.store.mapper.VGoodItemMapper;
import com.ruoyi.store.domain.VGoodItem;
import com.ruoyi.store.service.IVGoodItemService;
import com.ruoyi.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

/**
 * 商品分类 服务层实现
 *
 * @author Enzo
 * @date 2019-03-25
 */
@Service
public class VGoodItemServiceImpl implements IVGoodItemService
{
	@Autowired
	private VGoodItemMapper vGoodItemMapper;

	/**
     * 查询商品分类信息
     *
     * @param id 商品分类ID
     * @return 商品分类信息
     */
    @Override
	public VGoodItem selectVGoodItemById(Long id)
	{
	    return vGoodItemMapper.selectByPrimaryKey(id);
	}

	/**
     * 查询商品分类列表
     *
     * @param vGoodItem 商品分类信息
     * @return 商品分类集合
     */
	@Override
	public List<VGoodItem> selectVGoodItemList(VGoodItem vGoodItem)
	{
		Example example = new Example(VGoodItem.class);
		Example.Criteria criteria = example.createCriteria();

		ExampleUtils.andEqualTo(criteria, "id", vGoodItem.getId());
		ExampleUtils.andEqualTo(criteria, "itemName", vGoodItem.getItemName());
		ExampleUtils.andEqualTo(criteria, "itemDesc", vGoodItem.getItemDesc());
		ExampleUtils.andBetweenDateToDate(criteria, "ctime", vGoodItem.getParams().get("beginCtime"),vGoodItem.getParams().get("endCtime"));
		ExampleUtils.andEqualTo(criteria, "mStoreid", vGoodItem.getMStoreid());
		ExampleUtils.andEqualTo(criteria, "sore", vGoodItem.getSore());

	    return vGoodItemMapper.selectByExample(example);
	}

    /**
     * 新增商品分类
     *
     * @param vGoodItem 商品分类信息
     * @return 结果
     */
	@Override
	public int insertVGoodItem(VGoodItem vGoodItem)
	{
	    return vGoodItemMapper.insertSelective(vGoodItem);
	}

	/**
     * 修改商品分类
     *
     * @param vGoodItem 商品分类信息
     * @return 结果
     */
	@Override
	public int updateVGoodItem(VGoodItem vGoodItem)
	{
	    return vGoodItemMapper.updateByPrimaryKeySelective(vGoodItem);
	}

	/**
     * 删除商品分类对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteVGoodItemByIds(String ids)
	{
        Example example = new Example(VGoodItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",Convert.toList(ids));
		return vGoodItemMapper.deleteByExample(example);
	}


    /**
     * 删除商品分类 单条信息
	 * @param id 删除的主键ID
     * @return
     */
    @Override
    public int deleteVGoodItemById(Long id){
        return vGoodItemMapper.deleteByPrimaryKey(id);
	}

}
