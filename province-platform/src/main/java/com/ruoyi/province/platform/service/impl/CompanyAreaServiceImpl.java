package com.ruoyi.province.platform.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;
import com.ruoyi.province.platform.domain.CompanyArea;
import com.ruoyi.province.platform.mapper.CompanyAreaMapper;
import com.ruoyi.province.platform.service.ICompanyAreaService;
import com.ruoyi.province.platform.utils.BussUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 营业面积Service业务层处理
 * 
 * @author dalin
 * @date 2020-12-22
 */
@Service
public class CompanyAreaServiceImpl implements ICompanyAreaService 
{
    @Autowired
    private CompanyAreaMapper companyAreaMapper;

    /**
     * 查询营业面积
     * 
     * @param companyAreaId 营业面积ID
     * @return 营业面积
     */
    @Override
    public CompanyArea selectCompanyAreaById(Long companyAreaId)
    {
        return companyAreaMapper.selectCompanyAreaById(companyAreaId);
    }

            /**
         * 查询营业面积
         *
         * @param companyAreaId 营业面积ID
         * @return 营业面积
         */
        @Override
        public String checkCompanyAreaUnique(CompanyArea companyArea)
        {
            Long docId = StringUtils.isNull( companyArea.getCompanyAreaId() ) ? -1L : companyArea.getCompanyAreaId();
            CompanyArea info = companyAreaMapper.checkCompanyAreaUnique( companyArea.getCompanyAreaName() );
            if (StringUtils.isNotNull(info) && info.getCompanyAreaId().longValue() != docId.longValue())
            {
                return BussiConstants.DOC_NAME_NOT_UNIQUE;
            }
            return BussiConstants.DOC_NAME_UNIQUE;
        }

        /**  
     * 查询营业面积列表
     * 
     * @param companyArea 营业面积
     * @return 营业面积
     */
    @Override
    public List<CompanyArea> selectCompanyAreaList(CompanyArea companyArea)
    {
        return companyAreaMapper.selectCompanyAreaList(companyArea);
    }

    /**
     * 新增营业面积
     * 
     * @param companyArea 营业面积
     * @return 结果
     */
    @Override
    public int insertCompanyArea(CompanyArea companyArea)
    {
        companyArea.setDocNum("00001".concat( BussUtils.nextValue("companyArea") ) );
        companyArea.setCreateBy( ShiroUtils.getLoginName() );
        companyArea.setCreateTime(DateUtils.getNowDate());
        return companyAreaMapper.insertCompanyArea(companyArea);
    }

    /**
     * 修改营业面积
     * 
     * @param companyArea 营业面积
     * @return 结果
     */
    @Override
    public int updateCompanyArea(CompanyArea companyArea)
    {
        companyArea.setUpdateTime(DateUtils.getNowDate());
        return companyAreaMapper.updateCompanyArea(companyArea);
    }

    /**
     * 删除营业面积对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCompanyAreaByIds(String ids)
    {
        return companyAreaMapper.deleteCompanyAreaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除营业面积信息
     * 
     * @param companyAreaId 营业面积ID
     * @return 结果
     */
    @Override
    public int deleteCompanyAreaById(Long companyAreaId)
    {
        return companyAreaMapper.deleteCompanyAreaById(companyAreaId);
    }
}
