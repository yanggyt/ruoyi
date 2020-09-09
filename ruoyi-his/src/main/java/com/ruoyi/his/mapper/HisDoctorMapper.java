package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisDoctor;

import java.util.List;

/**
 * 医生Mapper接口
 * 
 * @author bend
 * @date 2020-07-01
 */
public interface HisDoctorMapper extends RuoYiBaseMapper<HisDoctor>
{
    /**
     * 查询医生
     * 
     * @param id 医生ID
     * @return 医生
     */
    public HisDoctor selectHisDoctorById(Long id);

    /**
     * 查询医生列表
     * 
     * @param hisDoctor 医生
     * @return 医生集合
     */
    public List<HisDoctor> selectHisDoctorList(HisDoctor hisDoctor);

    /**
     * 新增医生
     * 
     * @param hisDoctor 医生
     * @return 结果
     */
    public int insertHisDoctor(HisDoctor hisDoctor);

    /**
     * 修改医生
     * 
     * @param hisDoctor 医生
     * @return 结果
     */
    public int updateHisDoctor(HisDoctor hisDoctor);

    /**
     * 删除医生
     * 
     * @param id 医生ID
     * @return 结果
     */
    public int deleteHisDoctorById(Long id);

    /**
     * 批量删除医生
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisDoctorByIds(String[] ids);

    /**
     * 查询医生列表
     *
     * @param orgCode 机构编码
     * @return 医生集合
     */
    public List<HisDoctor> selectHisDoctorOrgCode(String orgCode);

    /**
     *  获取机构虚拟账户
     * @param orgCode 机构编码
     * @return 虚拟账户
     */
    public HisDoctor getVirtualAccount(String orgCode);

    /**
     *
     * @param vmUserId 虚拟用户ID
     * @return
     */
    public HisDoctor getVirtualAccountById(String vmUserId);
}
