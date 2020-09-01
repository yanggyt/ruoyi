package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisHospitalInfo;

import java.util.List;

/**
 * 医疗机构Mapper接口
 * 
 * @author bend
 * @date 2020-06-28
 */
public interface HisHospitalInfoMapper extends RuoYiBaseMapper<HisHospitalInfo>
{
    /**
     * 查询医疗机构
     * 
     * @param id 医疗机构ID
     * @return 医疗机构
     */
    public HisHospitalInfo selectHisHospitalInfoById(Long id);

    /**
     * 查询医疗机构
     * @param orgCode 医疗机构编码
     * @return 医疗机构
     */
    public HisHospitalInfo selectHisHospitalInfoByOrgCode(String orgCode);

    /**
     * 查询医疗机构列表
     * 
     * @param hisHospitalInfo 医疗机构
     * @return 医疗机构集合
     */
    public List<HisHospitalInfo> selectHisHospitalInfoList(HisHospitalInfo hisHospitalInfo);

    /**
     * 新增医疗机构
     * 
     * @param hisHospitalInfo 医疗机构
     * @return 结果
     */
    public int insertHisHospitalInfo(HisHospitalInfo hisHospitalInfo);

    /**
     * 修改医疗机构
     * 
     * @param hisHospitalInfo 医疗机构
     * @return 结果
     */
    public int updateHisHospitalInfo(HisHospitalInfo hisHospitalInfo);

    /**
     * 删除医疗机构
     * 
     * @param id 医疗机构ID
     * @return 结果
     */
    public int deleteHisHospitalInfoById(Long id);

    /**
     * 批量删除医疗机构
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisHospitalInfoByIds(String[] ids);
}
