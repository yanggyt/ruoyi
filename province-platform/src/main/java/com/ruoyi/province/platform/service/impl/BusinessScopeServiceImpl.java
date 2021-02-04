package com.ruoyi.province.platform.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;
import com.ruoyi.province.platform.domain.BusinessScope;
import com.ruoyi.province.platform.mapper.BusinessScopeMapper;
import com.ruoyi.province.platform.service.IBusinessScopeService;
import com.ruoyi.province.platform.utils.BussUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务规模Service业务层处理
 *
 * @author dalin
 * @date 2020-12-24
 */
@Service
public class BusinessScopeServiceImpl implements IBusinessScopeService
{
    @Autowired
    private BusinessScopeMapper businessScopeMapper;

    /**
     * 查询业务规模
     *
     * @param businessScaleId 业务规模ID
     * @return 业务规模
     */
    @Override
    public BusinessScope selectBusinessScopeById(Long businessScaleId)
    {
        return businessScopeMapper.selectBusinessScopeById(businessScaleId);
    }

    /**
     * 查询业务规模
     *
     * @param businessScaleId 业务规模ID
     * @return 业务规模
     */
    @Override
    public String checkBusinessScopeUnique(BusinessScope businessScope)
    {
        Long docId = StringUtils.isNull( businessScope.getBusinessScaleId() ) ? -1L : businessScope.getBusinessScaleId();
        BusinessScope info = businessScopeMapper.checkBusinessScopeUnique( businessScope.getBusinessScaleName() );
        if (StringUtils.isNotNull(info) && info.getBusinessScaleId().longValue() != docId.longValue())
        {
            return BussiConstants.DOC_NAME_NOT_UNIQUE;
        }
        return BussiConstants.DOC_NAME_UNIQUE;
    }

    /**
     * 查询业务规模列表
     *
     * @param businessScope 业务规模
     * @return 业务规模
     */
    @Override
    public List<BusinessScope> selectBusinessScopeList(BusinessScope businessScope)
    {
        return businessScopeMapper.selectBusinessScopeList(businessScope);
    }

    /**
     * 新增业务规模
     *
     * @param businessScope 业务规模
     * @return 结果
     */
    @Override
    public int insertBusinessScope(BusinessScope businessScope)
    {
        businessScope.setDocNum("00001".concat( BussUtils.nextValue("businessScope") ) );

        businessScope.setCreateBy( ShiroUtils.getLoginName() );
        businessScope.setCreateTime( DateUtils.getNowDate() );
        return businessScopeMapper.insertBusinessScope(businessScope);
    }

    /**
     * 修改业务规模
     *
     * @param businessScope 业务规模
     * @return 结果
     */
    @Override
    public int updateBusinessScope(BusinessScope businessScope)
    {
        businessScope.setUpdateTime(DateUtils.getNowDate());
        return businessScopeMapper.updateBusinessScope(businessScope);
    }

    /**
     * 删除业务规模对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBusinessScopeByIds(String ids)
    {
        return businessScopeMapper.deleteBusinessScopeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除业务规模信息
     *
     * @param businessScaleId 业务规模ID
     * @return 结果
     */
    @Override
    public int deleteBusinessScopeById(Long businessScaleId)
    {
        return businessScopeMapper.deleteBusinessScopeById(businessScaleId);
    }
}
