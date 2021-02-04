package com.ruoyi.province.platform.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.province.platform.mapper.RegionalMapper;
import com.ruoyi.province.platform.domain.Regional;
import com.ruoyi.province.platform.service.IRegionalService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;
import com.ruoyi.province.platform.utils.BussUtils;
import com.ruoyi.common.utils.ShiroUtils;

/**
 * 地区资料Service业务层处理
 * 
 * @author dalin
 * @date 2021-01-11
 */
@Service
public class RegionalServiceImpl implements IRegionalService 
{
    @Autowired
    private RegionalMapper regionalMapper;

    /**
     * 查询地区资料
     * 
     * @param regionalId 地区资料ID
     * @return 地区资料
     */
    @Override
    public Regional selectRegionalById(Long regionalId)
    {
        return regionalMapper.selectRegionalById(regionalId);
    }

            /**
         * 查询地区资料
         *
         * @param regionalId 地区资料ID
         * @return 地区资料
         */
        @Override
        public String checkRegionalUnique(Regional regional)
        {
            Long docId = StringUtils.isNull( regional.getRegionalId() ) ? -1L : regional.getRegionalId();
            Regional info = regionalMapper.checkRegionalUnique( regional.getRegionalName() );
            if (StringUtils.isNotNull(info) && info.getRegionalId().longValue() != docId.longValue())
            {
                return BussiConstants.DOC_NAME_NOT_UNIQUE;
            }
            return BussiConstants.DOC_NAME_UNIQUE;
        }

        /**
     * 查询地区资料列表
     * 
     * @param regional 地区资料
     * @return 地区资料
     */
    @Override
    public List<Regional> selectRegionalList(Regional regional)
    {
        return regionalMapper.selectRegionalList(regional);
    }

    /**
     * 新增地区资料
     * 
     * @param regional 地区资料
     * @return 结果
     */
    @Override
    public int insertRegional(Regional regional)
    {
        regional.setDocNum("00001".concat( BussUtils.nextValue("regional") ) );

        regional.setCreateBy( ShiroUtils.getLoginName() );
        regional.setCreateTime( DateUtils.getNowDate() );
        return regionalMapper.insertRegional(regional);
    }

    /**
     * 修改地区资料
     * 
     * @param regional 地区资料
     * @return 结果
     */
    @Override
    public int updateRegional(Regional regional)
    {
        regional.setUpdateTime(DateUtils.getNowDate());
        return regionalMapper.updateRegional(regional);
    }

    /**
     * 删除地区资料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRegionalByIds(String ids)
    {
        return regionalMapper.deleteRegionalByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除地区资料信息
     * 
     * @param regionalId 地区资料ID
     * @return 结果
     */
    @Override
    public int deleteRegionalById(Long regionalId)
    {
        return regionalMapper.deleteRegionalById(regionalId);
    }

    /**
     * 查询地区资料树列表
     * 
     * @return 所有地区资料信息
     */
    @Override
    public List<Ztree> selectRegionalTree()
    {
        List<Regional> regionalList = regionalMapper.selectRegionalList(new Regional());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (Regional regional : regionalList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(regional.getRegionalId());
            ztree.setpId(regional.getParentId());
            ztree.setName(regional.getRegionalName());
            ztree.setTitle(regional.getRegionalName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
