package com.ruoyi.store.mapper;

import com.ruoyi.store.domain.VStore;
import java.util.List;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 门店管理 数据层
 * 
 * @author Enzo
 * @date 2019-03-25
 */
@Repository
public interface VStoreMapper extends Mapper<VStore>
{
	/**
     * 查询门店管理信息
     * 
     * @param storeId 门店管理
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
     * 删除门店管理
     * 
     * @param storeId 门店管理ID
     * @return 结果
     */
	int deleteVStoreById(Long storeId);
	
	/**
     * 批量删除门店管理
     * 
     * @param storeIds 需要删除的数据ID
     * @return 结果
     */
	int deleteVStoreByIds(String[] storeIds);
	
}