package com.ruoyi.content.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.content.constants.PropertiesConstants;
import com.ruoyi.content.domain.CmsArticleAdInfo;
import com.ruoyi.content.mapper.CmsArticleAdInfoMapper;
import com.ruoyi.content.redis.RedisManager;
import com.ruoyi.content.service.ICmsArticleAdInfoService;
import com.ruoyi.content.utils.OSSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文章广告Service业务层处理
 *
 * @author ruoyi
 * @date 2021-03-23
 */
@Service
public class CmsArticleAdInfoServiceImpl implements ICmsArticleAdInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsArticleAdInfoServiceImpl.class);

    @Autowired
    private CmsArticleAdInfoMapper cmsArticleAdInfoMapper;
    @Autowired
    private RedisManager redisManager;

    /**
     * 查询文章广告
     *
     * @param adId 文章广告ID
     * @return 文章广告
     */
    @Override
    public CmsArticleAdInfo selectCmsArticleAdInfoById(Long adId) {
        return cmsArticleAdInfoMapper.selectCmsArticleAdInfoById(adId);
    }

    /**
     * 查询文章广告列表
     *
     * @param cmsArticleAdInfo 文章广告
     * @return 文章广告
     */
    @Override
    public List<CmsArticleAdInfo> selectCmsArticleAdInfoList(CmsArticleAdInfo cmsArticleAdInfo) {
        cmsArticleAdInfo.setAdState("0");
        cmsArticleAdInfo.setCompanyId("1");
        return cmsArticleAdInfoMapper.selectCmsArticleAdInfoList(cmsArticleAdInfo);
    }

    /**
     * 新增文章广告
     *
     * @param file             广告图片
     * @param cmsArticleAdInfo 文章广告
     * @return
     */
    @Override
    public int insertCmsArticleAdInfo(MultipartFile file, CmsArticleAdInfo cmsArticleAdInfo) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();// 文件名
            String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());// 文件后缀
            String fileTime = DateUtils.getMillisecond();
            fileName = PropertiesConstants.AD_IMG_PATH + fileTime + ext;// OSS保存路径
            String flag = null;
            try {
                flag = OSSUtil.uploadFileByInputStream(PropertiesConstants.OSSENDPOINT, PropertiesConstants.OSSID, PropertiesConstants.OSSKEY,
                        PropertiesConstants.BUCKETNAME, file.getInputStream(), PropertiesConstants.OSSPATH + fileName);
            } catch (IOException e) {
                LOGGER.error("上传阿里云失败！", e);
            }
            if (null == flag || flag.equals("false")) {
                LOGGER.info("广告图片上传oss失败");
                return 0;
            } else {
                cmsArticleAdInfo.setAdImageUrl(PropertiesConstants.OSS_URL + PropertiesConstants.OSSPATH + fileName);
            }
        }
        String date = DateUtils.getDate();
        String time = DateUtils.getTimeNow();
        cmsArticleAdInfo.setAdLinkUrl(cmsArticleAdInfo.getAdLinkUrl() + "(");
        cmsArticleAdInfo.setCompanyId("1");
        cmsArticleAdInfo.setAdState("0");
        cmsArticleAdInfo.setCreateDate(date);
        cmsArticleAdInfo.setCreateTime(time);
        cmsArticleAdInfo.setCreateUser("company");
        cmsArticleAdInfo.setUpdateDate(date);
        cmsArticleAdInfo.setUpdateTime(time);
        cmsArticleAdInfo.setUpdateUser("company");
        return cmsArticleAdInfoMapper.insertCmsArticleAdInfo(cmsArticleAdInfo);
    }

    /**
     * 修改文章广告
     *
     * @param file             广告图片
     * @param cmsArticleAdInfo 文章广告
     * @return 结果
     */
    @Override
    public int updateCmsArticleAdInfo(MultipartFile file, CmsArticleAdInfo cmsArticleAdInfo) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();// 文件名
            String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());// 文件后缀
            String fileTime = DateUtils.getMillisecond();
            fileName = PropertiesConstants.AD_IMG_PATH + fileTime + ext;// OSS保存路径
            String flag = null;
            try {
                flag = OSSUtil.uploadFileByInputStream(PropertiesConstants.OSSENDPOINT, PropertiesConstants.OSSID, PropertiesConstants.OSSKEY,
                        PropertiesConstants.BUCKETNAME, file.getInputStream(), PropertiesConstants.OSSPATH + fileName);
            } catch (IOException e) {
                LOGGER.error("上传阿里云失败！", e);
            }
            if (null == flag || flag.equals("false")) {
                LOGGER.info("广告图片上传oss失败");
                return 0;
            } else {
                cmsArticleAdInfo.setAdImageUrl(PropertiesConstants.OSS_URL + PropertiesConstants.OSSPATH + fileName);
            }
        }
        String date = DateUtils.getDate();
        String time = DateUtils.getTimeNow();
        cmsArticleAdInfo.setAdLinkUrl(cmsArticleAdInfo.getAdLinkUrl() + "(");
        cmsArticleAdInfo.setUpdateDate(date);
        cmsArticleAdInfo.setUpdateTime(time);
        cmsArticleAdInfo.setUpdateUser("company");
        if (cmsArticleAdInfoMapper.updateCmsArticleAdInfo(cmsArticleAdInfo) > 0) {
            redisManager.delete("articleAdInfo_id" + cmsArticleAdInfo.getAdId());
        } else {
            LOGGER.info("编辑广告失败");
            return 0;
        }
        return 1;
    }

    /**
     * 删除文章广告对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsArticleAdInfoByIds(String ids) {
        String[] arrId = ids.split(",");
        for (String id : arrId) {
            CmsArticleAdInfo delType = cmsArticleAdInfoMapper.selectByPrimaryKey(Integer.valueOf(id));
            if (delType != null) {
                redisManager.delete("articleAdInfo_id" + id);
                redisManager.delete("article_ad_typeList_" + delType.getAdType());
                cmsArticleAdInfoMapper.deleteCmsArticleAdInfoById(Long.valueOf(id));
            }
        }
        return 1;
    }

    /**
     * 删除文章广告信息
     *
     * @param adId 文章广告ID
     * @return 结果
     */
    @Override
    public int deleteCmsArticleAdInfoById(Long adId) {
        return cmsArticleAdInfoMapper.deleteCmsArticleAdInfoById(adId);
    }
}
