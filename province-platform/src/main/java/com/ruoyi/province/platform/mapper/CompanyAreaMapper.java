package com.ruoyi.province.platform.mapper;

import java.util.List;
import com.ruoyi.province.platform.domain.CompanyArea;

/**
 * 营业面积Mapper接口
 * 
 * @author dalin
 * @date 2020-12-22
 */
public interface CompanyAreaMapper 
{
    /**
     * 查询营业面积
     * 
     * @param companyAreaId 营业面积ID
     * @return 营业面积
     */
    public CompanyArea selectCompanyAreaById(Long companyAreaId);

            /**
         * 校验 营业面积 名称是否重复
         *
         * @param CompanyAreaName
         * @return 营业面积
         */
        public CompanyArea checkCompanyAreaUnique(String CompanyAreaName);
    
    /**
     * 查询营业面积列表
     * 
     * @param companyArea 营业面积
     * @return 营业面积集合
     */
    public List<CompanyArea> selectCompanyAreaList(CompanyArea companyArea);

    /**
     * 新增营业面积
     * 
     * @param companyArea 营业面积
     * @return 结果
     */
    public int insertCompanyArea(CompanyArea companyArea);

    /**
     * 修改营业面积
     * 
     * @param companyArea 营业面积
     * @return 结果
     */
    public int updateCompanyArea(CompanyArea companyArea);

    /**
     * 删除营业面积
     * 
     * @param companyAreaId 营业面积ID
     * @return 结果
     */
    public int deleteCompanyAreaById(Long companyAreaId);

    /**
     * 批量删除营业面积
     * 
     * @param companyAreaIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCompanyAreaByIds(String[] companyAreaIds);
}
