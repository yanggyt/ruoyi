package com.ruoyi.store.service.impl;

import java.util.List;

import com.ruoyi.common.core.page.ExampleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.store.mapper.VStoreMapper;
import com.ruoyi.store.domain.VStore;
import com.ruoyi.store.service.IVStoreService;
import com.ruoyi.common.core.text.Convert;
import tk.mybatis.mapper.entity.Example;

/**
 * 门店管理 服务层实现
 *
 * @author Enzo
 * @date 2019-03-25
 */
@Service
public class VStoreServiceImpl implements IVStoreService
{
	@Autowired
	private VStoreMapper vStoreMapper;

	/**
     * 查询门店管理信息
     *
     * @param storeId 门店管理ID
     * @return 门店管理信息
     */
    @Override
	public VStore selectVStoreById(Long storeId)
	{
	    return vStoreMapper.selectByPrimaryKey(storeId);
	}

	/**
     * 查询门店管理列表
     *
     * @param vStore 门店管理信息
     * @return 门店管理集合
     */
	@Override
	public List<VStore> selectVStoreList(VStore vStore)
	{
		Example example = new Example(VStore.class);
		Example.Criteria criteria = example.createCriteria();

		ExampleUtils.andEqualTo(criteria, "storeId", vStore.getStoreId());
		ExampleUtils.andEqualTo(criteria, "storeCode", vStore.getStoreCode());
		ExampleUtils.andEqualTo(criteria, "storeName", vStore.getStoreName());
		ExampleUtils.andEqualTo(criteria, "storePeople", vStore.getStorePeople());
		ExampleUtils.andEqualTo(criteria, "storeTel", vStore.getStoreTel());
		ExampleUtils.andEqualTo(criteria, "storeEmail", vStore.getStoreEmail());
		ExampleUtils.andEqualTo(criteria, "storeLogo", vStore.getStoreLogo());
		ExampleUtils.andEqualTo(criteria, "storeAddress", vStore.getStoreAddress());
		ExampleUtils.andEqualTo(criteria, "storeStatus", vStore.getStoreStatus());
		ExampleUtils.andBetweenDateToDate(criteria, "ctime", vStore.getParams().get("beginCtime"),vStore.getParams().get("endCtime"));
		ExampleUtils.andBetweenDateToDate(criteria, "stime", vStore.getParams().get("beginStime"),vStore.getParams().get("endStime"));

	    return vStoreMapper.selectByExample(example);
	}

    /**
     * 新增门店管理
     *
     * @param vStore 门店管理信息
     * @return 结果
     */
	@Override
	public int insertVStore(VStore vStore)
	{
	    return vStoreMapper.insertSelective(vStore);
	}

	/**
     * 修改门店管理
     *
     * @param vStore 门店管理信息
     * @return 结果
     */
	@Override
	public int updateVStore(VStore vStore)
	{
	    return vStoreMapper.updateByPrimaryKeySelective(vStore);
	}

	/**
     * 删除门店管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteVStoreByIds(String ids)
	{
        Example example = new Example(VStore.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("storeId",Convert.toList(ids));
		return vStoreMapper.deleteByExample(example);
	}


    /**
     * 删除门店管理 单条信息
	 * @param storeId 删除的主键ID
     * @return
     */
    @Override
    public int deleteVStoreById(Long storeId){
        return vStoreMapper.deleteByPrimaryKey(storeId);
	}

}
