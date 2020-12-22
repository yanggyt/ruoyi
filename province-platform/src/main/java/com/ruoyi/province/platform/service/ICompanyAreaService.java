package com.ruoyi.province.platform.service;

import java.util.List;
import com.ruoyi.province.platform.domain.CompanyArea;

/**
 * 营业面积Service接口
 * 
 * @author dalin
 * @date 2020-12-22
 */
public interface ICompanyAreaService 
{
    /**
     * 查询营业面积
     * 
     * @param companyAreaId 营业面积ID
     * @return 营业面积
     */
    public CompanyArea selectCompanyAreaById(Long companyAreaId);

            /**
         * 查询营业面积
         *
         * @param companyAreaId 营业面积ID
         * @return 营业面积
         */
        public String checkCompanyAreaUnique(CompanyArea companyArea);
    
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
     * 批量删除营业面积
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCompanyAreaByIds(String ids);

    /**
     * 删除营业面积信息
     * 
     * @param companyAreaId 营业面积ID
     * @return 结果
     */
    public int deleteCompanyAreaById(Long companyAreaId);
}
