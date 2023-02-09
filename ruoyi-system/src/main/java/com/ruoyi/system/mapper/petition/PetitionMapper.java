package com.ruoyi.system.mapper.petition;

import com.ruoyi.system.domain.petition.Petition;
import com.ruoyi.system.domain.petition.PetitionSign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 电子签呈Mapper接口
 * 
 * @author SKaiL
 * @date 2022-09-26
 */
public interface PetitionMapper
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
     * @param petition 签呈审批信息
     * @return 结果
     */
    public int insertPetition(Petition petition);

    /**
     * 修改签呈审批
     *
     * @param petition 签呈审批信息
     * @return 结果
     */
    public int updatePetition(Petition petition);

    /**
     * 删除签呈审批
     *
     * @param id 签呈审批ID
     * @return 结果
     */
    public int deletePetitionById(Long id);

    /**
     * 批量删除签呈审批
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePetitionByIds(String[] ids);

    List<Petition> selectTodoPetitionList(Petition petition);

    List<Petition> selectTodoApiPetitionList(Petition petition);

    List<Petition> selectDidPetitionByOrderIds(@Param("ids")List<Long> ids);

    /**
     *获取数据
     */
    List<String> selectPetitionByNow(@Param("typeType") int typeType,@Param("com") String com);


    /**
     * 当选择撤回时，将已审核过的数据进行清空
     * @param petition
     * @return
     */
    public int updatePeById(Petition petition);
}
