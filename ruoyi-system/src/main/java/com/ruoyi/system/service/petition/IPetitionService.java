package com.ruoyi.system.service.petition;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.FormFile;
import com.ruoyi.system.domain.petition.Petition;
import com.ruoyi.system.domain.petition.PetitionFile;
import com.ruoyi.system.dto.petition.PetitionDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 签呈审批 服务层
 *
 */
public interface IPetitionService 
{
	/**
     * 查询签呈审批信息
     * 
     * @param id 签呈审批ID
     * @return 签呈审批信息
     */
	public Petition selectPetitionById(Long id);
	
	/**
     * 查询签呈审批列表
     * 
     * @param petition 签呈审批信息
     * @return 签呈审批集合
     */
	public List<Petition> selectPetitionList(Petition petition);
	
	/**
     * 新增签呈审批
     * 
     * @param petitionDto 签呈审批信息
     * @return 结果
     */
	public Long insertPetition(PetitionDto petitionDto, SysUser user);
	
	/**
     * 修改签呈审批
     * 
     * @param petition 签呈审批信息
     * @return 结果
     */
	public int updatePetition(Petition petition);
		
	/**
     * 删除签呈审批信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePetitionByIds(String ids);

    String seleteZJBySid(String zgsid);

    Integer insertRequisitionFile(List<PetitionFile> petitionFileList);

	/**
	 * 上传审批截图
	 * @param file 截图凭证
	 * @param petitionId id
	 * @param sysUser 当前用户
	 * @return
	 */
	Integer insertPetitionFile(MultipartFile file, long petitionId, SysUser sysUser);

	Integer submit(Long id,SysUser user);

	Integer updateAudit(PetitionDto petitionDto, SysUser sysyuser);

	Integer updateAuditHR(PetitionDto petitionDto, SysUser sysUser);

	Integer updateAuditcw(PetitionDto petitionDto, SysUser sysUser);

	Integer updateAuditcwManager(PetitionDto petitionDto, SysUser sysUser);

	Integer updateAuditGM(PetitionDto petitionDto, SysUser sysUser);

    Map<String, Object> getPetitionMessage(Long id);

	Integer updateFwAudit(PetitionDto petitionDto, SysUser sysUser);

	List<Petition> selectTodoPetitionList(Petition petition);
	
	List<Petition> selectTodoApiPetitionList(Petition petition);

//	PageDatas<Petition> selectDidPetition(String userName, Integer pageNum, Integer pageSize);

	int updateaddAudit(PetitionDto petitionDto,SysUser sysUser);

	Integer updateaddSign(PetitionDto petitionDto, SysUser sysUser);

	Integer updateaddTrial(PetitionDto petitionDto, SysUser sysUser);

	Integer updateAuditAC(PetitionDto petitionDto, SysUser sysUser);

	List<Petition> selectCreatePetition(Petition petition);

	/**
	 * 撤回
	 */
	int WithdrawalOfInitiation(Long id, String cancelRemark, SysUser sysUser);

	/**
	 * 查询文件列表
	 * @param parentId 父id
	 * @param type 类型
	 * @return 文件列表
	 */
	public List<FormFile> selectFormFile(Long parentId, Integer type);

}
