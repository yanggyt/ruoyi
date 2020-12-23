package com.ruoyi.province.platform.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;
import com.ruoyi.province.platform.domain.BrandInfo;
import com.ruoyi.province.platform.mapper.BrandInfoMapper;
import com.ruoyi.province.platform.service.IBrandInfoService;
import com.ruoyi.province.platform.utils.BussUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌资料Service业务层处理
 * 
 * @author dalin
 * @date 2020-12-23
 */
@Service
public class BrandInfoServiceImpl implements IBrandInfoService 
{
    @Autowired
    private BrandInfoMapper brandInfoMapper;

    /**
     * 查询品牌资料
     * 
     * @param brandId 品牌资料ID
     * @return 品牌资料
     */
    @Override
    public BrandInfo selectBrandInfoById(Long brandId)
    {
        return brandInfoMapper.selectBrandInfoById(brandId);
    }

            /**
         * 查询品牌资料
         *
         * @param brandId 品牌资料ID
         * @return 品牌资料
         */
        @Override
        public String checkBrandInfoUnique(BrandInfo brandInfo)
        {
            Long docId = StringUtils.isNull( brandInfo.getBrandId() ) ? -1L : brandInfo.getBrandId();
            BrandInfo info = brandInfoMapper.checkBrandInfoUnique( brandInfo.getBrandName() );
            if (StringUtils.isNotNull(info) && info.getBrandId().longValue() != docId.longValue())
            {
                return BussiConstants.DOC_NAME_NOT_UNIQUE;
            }
            return BussiConstants.DOC_NAME_UNIQUE;
        }

        /**
     * 查询品牌资料列表
     * 
     * @param brandInfo 品牌资料
     * @return 品牌资料
     */
    @Override
    public List<BrandInfo> selectBrandInfoList(BrandInfo brandInfo)
    {
        return brandInfoMapper.selectBrandInfoList(brandInfo);
    }

    /**
     * 新增品牌资料
     * 
     * @param brandInfo 品牌资料
     * @return 结果
     */
    @Override
    public int insertBrandInfo(BrandInfo brandInfo)
    {
        brandInfo.setDocNum("00001".concat( BussUtils.nextValue("brandInfo") ) );

        brandInfo.setCreateTime(DateUtils.getNowDate());
        return brandInfoMapper.insertBrandInfo(brandInfo);
    }

    /**
     * 修改品牌资料
     * 
     * @param brandInfo 品牌资料
     * @return 结果
     */
    @Override
    public int updateBrandInfo(BrandInfo brandInfo)
    {
        brandInfo.setUpdateTime(DateUtils.getNowDate());
        return brandInfoMapper.updateBrandInfo(brandInfo);
    }

    /**
     * 删除品牌资料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBrandInfoByIds(String ids)
    {
        return brandInfoMapper.deleteBrandInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除品牌资料信息
     * 
     * @param brandId 品牌资料ID
     * @return 结果
     */
    @Override
    public int deleteBrandInfoById(Long brandId)
    {
        return brandInfoMapper.deleteBrandInfoById(brandId);
    }
}
