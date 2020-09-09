package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.domain.HisDoctor;
import com.ruoyi.his.domain.HisHospitalInfo;
import com.ruoyi.his.mapper.HisDoctorMapper;
import com.ruoyi.his.mapper.HisHospitalInfoMapper;
import com.ruoyi.his.service.IHisHospitalInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 医疗机构Service业务层处理
 * 
 * @author bend
 * @date 2020-06-28
 */
@Service
public class HisHospitalInfoServiceImpl implements IHisHospitalInfoService 
{
    @Resource
    private HisHospitalInfoMapper hisHospitalInfoMapper;
    @Resource
    private HisDoctorMapper doctorMapper;

    /**
     * 查询医疗机构
     * 
     * @param id 医疗机构ID
     * @return 医疗机构
     */
    @Override
    public HisHospitalInfo selectHisHospitalInfoById(Long id)
    {
        return hisHospitalInfoMapper.selectHisHospitalInfoById(id);
    }

    /**
     * 查询医疗机构
     * @param orgCode 医疗机构编码
     * @return 医疗机构
     */
    @Override
    public HisHospitalInfo selectHisHospitalInfoByOrgCode(String orgCode)
    {
        return hisHospitalInfoMapper.selectHisHospitalInfoByOrgCode(orgCode);
    }

    /**
     * 查询医疗机构列表
     * 
     * @param hisHospitalInfo 医疗机构
     * @return 医疗机构
     */
    @Override
    public List<HisHospitalInfo> selectHisHospitalInfoList(HisHospitalInfo hisHospitalInfo)
    {

        List<HisHospitalInfo> hospitalInfoList = hisHospitalInfoMapper.selectHisHospitalInfoList(hisHospitalInfo);

        if(StringUtils.isNotEmpty(hospitalInfoList)){
            hospitalInfoList.forEach(info ->{
                String vmUserId = info.getVmUserId();
                if (StringUtils.isNotEmpty(vmUserId)){
                    HisDoctor hisDoctor =doctorMapper.getVirtualAccountById(vmUserId);
                    if (null!=hisDoctor){
                        //虚拟操作员
                        info.setVmUserName(hisDoctor.getDoctorName());
                    }
                }
            });
        }
        return hospitalInfoList;
    }

    /**
     * 新增医疗机构
     * 
     * @param hisHospitalInfo 医疗机构
     * @return 结果
     */
    @Override
    public int insertHisHospitalInfo(HisHospitalInfo hisHospitalInfo)
    {
        return hisHospitalInfoMapper.insertHisHospitalInfo(hisHospitalInfo);
    }

    /**
     * 修改医疗机构
     * 
     * @param hisHospitalInfo 医疗机构
     * @return 结果
     */
    @Override
    public int updateHisHospitalInfo(HisHospitalInfo hisHospitalInfo)
    {
        return hisHospitalInfoMapper.updateHisHospitalInfo(hisHospitalInfo);
    }

    /**
     * 删除医疗机构对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisHospitalInfoByIds(String ids)
    {
        return hisHospitalInfoMapper.deleteHisHospitalInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除医疗机构信息
     * 
     * @param id 医疗机构ID
     * @return 结果
     */
    @Override
    public int deleteHisHospitalInfoById(Long id)
    {
        return hisHospitalInfoMapper.deleteHisHospitalInfoById(id);
    }
}
