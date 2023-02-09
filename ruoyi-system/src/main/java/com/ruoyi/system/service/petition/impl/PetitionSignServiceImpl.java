package com.ruoyi.system.service.petition.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.petition.PetitionSign;
import com.ruoyi.system.mapper.petition.PetitionSignMapper;
import com.ruoyi.system.service.petition.IPetitionSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 核准人员
 服务层实现
 */
@Service
public class PetitionSignServiceImpl implements IPetitionSignService 
{
	@Autowired
	private PetitionSignMapper petitionSignMapper;

	/**
     * 查询核准人员信息
     * @param id 核准人员ID
     * @return 核准人员信息
     */
    @Override
	public PetitionSign selectPetitionSignById(Long id)
	{
	    return petitionSignMapper.selectPetitionSignById(id);
	}
	
	/**
     * 查询核准人员列表
     * @param petitionSign 核准人员信息
     * @return 核准人员集合
     */
	@Override
	public List<PetitionSign> selectPetitionSignList(PetitionSign petitionSign)
	{
	    return petitionSignMapper.selectPetitionSignList(petitionSign);
	}
	
    /**
     * 新增核准人员
     * @param petitionSign 核准人员信息
     * @return 结果
     */
	@Override
	public int insertPetitionSign(PetitionSign petitionSign)
	{
	    return petitionSignMapper.insertPetitionSign(petitionSign);
	}
	
	/**
     * 修改核准人员
     * @param petitionSign 核准人员信息
     * @return 结果
     */
	@Override
	public int updatePetitionSign(PetitionSign petitionSign)
	{
	    return petitionSignMapper.updatePetitionSign(petitionSign);
	}

	/**
     * 删除核准人员对象
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePetitionSignByIds(String ids)
	{
		return petitionSignMapper.deletePetitionSignByIds(Convert.toStrArray(ids));
	}
	
}
