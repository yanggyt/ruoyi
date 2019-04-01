package com.ruoyi.store.service;

import com.ruoyi.store.domain.VStore;
import java.util.List;

/**
 * 门店管理 服务层
 * 
 * @author Enzo
 * @date 2019-03-25
 */
public interface IVStoreService 
{
	/**
     * 查询门店管理信息
     * 
     * @param storeId 门店管理ID
     * @return 门店管理信息
     */
	VStore selectVStoreById(Long storeId);
	
	/**
     * 查询门店管理列表
     * 
     * @param vStore 门店管理信息
     * @return 门店管理集合
     */
	List<VStore> selectVStoreList(VStore vStore);
	
	/**
     * 新增门店管理
     * 
     * @param vStore 门店管理信息
     * @return 结果
     */
	int insertVStore(VStore vStore);
	
	/**
     * 修改门店管理
     * 
     * @param vStore 门店管理信息
     * @return 结果
     */
	int updateVStore(VStore vStore);
		
	/**
     * 删除门店管理信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	int deleteVStoreByIds(String ids);


    /**
    * 删除门店管理 单条信息
	* @return
	*/
	int deleteVStoreById(Long storeId);
}
