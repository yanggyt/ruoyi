package com.ruoyi.content.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.content.mapper.CmsArticleAdInfoMapper;
import com.ruoyi.content.domain.CmsArticleAdInfo;
import com.ruoyi.content.service.ICmsArticleAdInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文章广告Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-23
 */
@Service
public class CmsArticleAdInfoServiceImpl implements ICmsArticleAdInfoService 
{
    @Autowired
    private CmsArticleAdInfoMapper cmsArticleAdInfoMapper;

    /**
     * 查询文章广告
     * 
     * @param adId 文章广告ID
     * @return 文章广告
     */
    @Override
    public CmsArticleAdInfo selectCmsArticleAdInfoById(Long adId)
    {
        return cmsArticleAdInfoMapper.selectCmsArticleAdInfoById(adId);
    }

    /**
     * 查询文章广告列表
     * 
     * @param cmsArticleAdInfo 文章广告
     * @return 文章广告
     */
    @Override
    public List<CmsArticleAdInfo> selectCmsArticleAdInfoList(CmsArticleAdInfo cmsArticleAdInfo)
    {
        return cmsArticleAdInfoMapper.selectCmsArticleAdInfoList(cmsArticleAdInfo);
    }

    /**
     * 新增文章广告
     * 
     * @param cmsArticleAdInfo 文章广告
     * @return 结果
     */
    @Override
    public int insertCmsArticleAdInfo(CmsArticleAdInfo cmsArticleAdInfo)
    {
        cmsArticleAdInfo.setCreateTime(DateUtils.getNowDate());
        return cmsArticleAdInfoMapper.insertCmsArticleAdInfo(cmsArticleAdInfo);
    }

    /**
     * 修改文章广告
     * 
     * @param cmsArticleAdInfo 文章广告
     * @return 结果
     */
    @Override
    public int updateCmsArticleAdInfo(CmsArticleAdInfo cmsArticleAdInfo)
    {
        cmsArticleAdInfo.setUpdateTime(DateUtils.getNowDate());
        return cmsArticleAdInfoMapper.updateCmsArticleAdInfo(cmsArticleAdInfo);
    }

    /**
     * 删除文章广告对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsArticleAdInfoByIds(String ids)
    {
        return cmsArticleAdInfoMapper.deleteCmsArticleAdInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文章广告信息
     * 
     * @param adId 文章广告ID
     * @return 结果
     */
    @Override
    public int deleteCmsArticleAdInfoById(Long adId)
    {
        return cmsArticleAdInfoMapper.deleteCmsArticleAdInfoById(adId);
    }
}
