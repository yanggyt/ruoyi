package com.ruoyi.system.service.petition.impl;


import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.domain.petition.PetitionFile;
import com.ruoyi.system.mapper.petition.PetitionFileMapper;
import com.ruoyi.system.service.petition.IPetitionFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 签呈单文件 服务层实现
 */
@Service
public class PetitionFileServiceImpl implements IPetitionFileService 
{
	@Autowired
	private PetitionFileMapper petitionFileMapper;

	/**
     * 查询签呈单文件信息
     * 
     * @param id 签呈单文件ID
     * @return 签呈单文件信息
     */
    @Override
	public PetitionFile selectPetitionFileById(Long id)
	{
	    return petitionFileMapper.selectPetitionFileById(id);
	}
	
	/**
     * 查询签呈单文件列表
     * 
     * @param petitionFile 签呈单文件信息
     * @return 签呈单文件集合
     */
	@Override
	public List<PetitionFile> selectPetitionFileList(PetitionFile petitionFile)
	{
	    return petitionFileMapper.selectPetitionFileList(petitionFile);
	}
	
    /**
     * 新增签呈单文件
     * 
     * @param petitionFile 签呈单文件信息
     * @return 结果
     */
	@Override
	public int insertPetitionFile(PetitionFile petitionFile)
	{
	    return petitionFileMapper.insertPetitionFile(petitionFile);
	}
	
	/**
     * 修改签呈单文件
     * 
     * @param petitionFile 签呈单文件信息
     * @return 结果
     */
	@Override
	public int updatePetitionFile(PetitionFile petitionFile)
	{
	    return petitionFileMapper.updatePetitionFile(petitionFile);
	}

	/**
     * 删除签呈单文件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePetitionFileByIds(String ids)
	{
		return petitionFileMapper.deletePetitionFileByIds(Convert.toStrArray(ids));
	}
	
}
