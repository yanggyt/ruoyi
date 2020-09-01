package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.his.domain.HisDoctor;
import com.ruoyi.his.mapper.HisDoctorMapper;
import com.ruoyi.his.service.IHisDoctorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 医生Service业务层处理
 * 
 * @author bend
 * @date 2020-07-01
 */
@Service
public class HisDoctorServiceImpl implements IHisDoctorService 
{
    @Resource
    private HisDoctorMapper hisDoctorMapper;

    /**
     * 查询医生
     * 
     * @param id 医生ID
     * @return 医生
     */
    @Override
    public HisDoctor selectHisDoctorById(Long id)
    {
        return hisDoctorMapper.selectHisDoctorById(id);
    }

    /**
     * 查询医生列表
     * 
     * @param hisDoctor 医生
     * @return 医生
     */
    @Override
    public List<HisDoctor> selectHisDoctorList(HisDoctor hisDoctor)
    {
        return hisDoctorMapper.selectHisDoctorList(hisDoctor);
    }

    /**
     * 查询医生列表
     *
     * @param orgCode 机构编码
     * @return 医生集合
     */
    @Override
    public List<HisDoctor> selectHisDoctorOrgCode(String orgCode) {
        return hisDoctorMapper.selectHisDoctorOrgCode(orgCode);
    }

    /**
     * 新增医生
     * 
     * @param hisDoctor 医生
     * @return 结果
     */
    @Override
    public int insertHisDoctor(HisDoctor hisDoctor)
    {
        return hisDoctorMapper.insertHisDoctor(hisDoctor);
    }

    /**
     * 修改医生
     * 
     * @param hisDoctor 医生
     * @return 结果
     */
    @Override
    public int updateHisDoctor(HisDoctor hisDoctor)
    {
        return hisDoctorMapper.updateHisDoctor(hisDoctor);
    }

    /**
     * 删除医生对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisDoctorByIds(String ids)
    {
        return hisDoctorMapper.deleteHisDoctorByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除医生信息
     * 
     * @param id 医生ID
     * @return 结果
     */
    @Override
    public int deleteHisDoctorById(Long id)
    {
        return hisDoctorMapper.deleteHisDoctorById(id);
    }

    /**
     *  获取机构虚拟账户
     * @param orgCode 机构编码
     * @return 虚拟账户
     */
    @Override
    public HisDoctor getVirtualAccount(String orgCode)
    {
        return hisDoctorMapper.getVirtualAccount(orgCode);
    }
}
