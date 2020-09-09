package com.ruoyi.his.service;

import java.util.List;
import com.ruoyi.his.domain.HisDoctor;

/**
 * 医生Service接口
 * 
 * @author bend
 * @date 2020-07-01
 */
public interface IHisDoctorService 
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
     * 查询医生列表
     *
     * @param orgCode 机构编码
     * @return 医生集合
     */
    public List<HisDoctor> selectHisDoctorOrgCode(String orgCode);

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
     * 批量删除医生
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisDoctorByIds(String ids);

    /**
     * 删除医生信息
     * 
     * @param id 医生ID
     * @return 结果
     */
    public int deleteHisDoctorById(Long id);

    /**
     *  获取机构虚拟账户
     * @param orgCode 机构编码
     * @return 虚拟账户
     */
    public HisDoctor getVirtualAccount(String orgCode);

}
