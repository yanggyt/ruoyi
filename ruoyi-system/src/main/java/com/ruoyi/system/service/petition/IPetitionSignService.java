package com.ruoyi.system.service.petition;


import com.ruoyi.system.domain.petition.PetitionSign;

import java.util.List;

/**
 * 核准人员服务层
 */
public interface IPetitionSignService 
{
	/**
     * 查询核准人员信息
     * @param id 核准人员ID
     * @return 核准人员信息
     */
	public PetitionSign selectPetitionSignById(Long id);
	
	/**
     * 查询核准人员列表
     * @param petitionSign 核准人员信息
     * @return 核准人员集合
     */
	public List<PetitionSign> selectPetitionSignList(PetitionSign petitionSign);
	
	/**
     * 新增核准人员
     * @param petitionSign 核准人员信息
     * @return 结果
     */
	public int insertPetitionSign(PetitionSign petitionSign);
	
	/**
     * 修改核准人员
     * @param petitionSign 核准人员信息
     * @return 结果
     */
	public int updatePetitionSign(PetitionSign petitionSign);
		
	/**
     * 删除核准人员信息
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePetitionSignByIds(String ids);
	
}
