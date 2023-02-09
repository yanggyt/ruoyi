package com.ruoyi.system.mapper.petition;

import com.ruoyi.system.domain.petition.PetitionFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 签呈单文件 数据层
 */
public interface PetitionFileMapper 
{
	/**
     * 查询签呈单文件信息
     * 
     * @param id 签呈单文件ID
     * @return 签呈单文件信息
     */
	public PetitionFile selectPetitionFileById(Long id);
	
	/**
     * 查询签呈单文件列表
     * 
     * @param petitionFile 签呈单文件信息
     * @return 签呈单文件集合
     */
	public List<PetitionFile> selectPetitionFileList(PetitionFile petitionFile);
	
	/**
     * 新增签呈单文件
     * 
     * @param petitionFile 签呈单文件信息
     * @return 结果
     */
	public int insertPetitionFile(PetitionFile petitionFile);
	
	/**
     * 修改签呈单文件
     * 
     * @param petitionFile 签呈单文件信息
     * @return 结果
     */
	public int updatePetitionFile(PetitionFile petitionFile);
	
	/**
     * 删除签呈单文件
     * 
     * @param id 签呈单文件ID
     * @return 结果
     */
	public int deletePetitionFileById(Long id);
	
	/**
     * 批量删除签呈单文件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePetitionFileByIds(String[] ids);

    void deletePetitionFileByOrderIds(@Param("ids") String[] ids);
}