package com.ruoyi.content.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.content.domain.CmsArticleAdInfo;
import com.ruoyi.content.domain.CmsPicAdInfo;
import com.ruoyi.content.mapper.CmsPicAdInfoMapper;
import com.ruoyi.content.redis.RedisManager;
import com.ruoyi.content.service.ICmsPicAdInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图片广告Service业务层处理
 *
 * @author liushenlu
 * @date 2021-03-25
 */
@Service
public class CmsPicAdInfoServiceImpl implements ICmsPicAdInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsPicAdInfoServiceImpl.class);

    @Autowired
    private CmsPicAdInfoMapper cmsPicAdInfoMapper;
    @Autowired
    private RedisManager redisManager;

    /**
     * 查询图片广告
     *
     * @param picAdId 图片广告ID
     * @return 图片广告
     */
    @Override
    public CmsPicAdInfo selectCmsPicAdInfoById(Long picAdId) {
        return cmsPicAdInfoMapper.selectCmsPicAdInfoById(picAdId);
    }

    /**
     * 查询图片广告列表
     *
     * @param cmsPicAdInfo 图片广告
     * @return 图片广告
     */
    @Override
    public List<CmsPicAdInfo> selectCmsPicAdInfoList(CmsPicAdInfo cmsPicAdInfo) {
        cmsPicAdInfo.setCompanyId("1");
        return cmsPicAdInfoMapper.selectCmsPicAdInfoList(cmsPicAdInfo);
    }

    /**
     * 新增图片广告
     *
     * @param cmsPicAdInfo 图片广告
     * @return 结果
     */
    @Override
    public int insertCmsPicAdInfo(CmsPicAdInfo cmsPicAdInfo) {
        String date = DateUtils.getDate();
        String time = DateUtils.getTimeNow();
        cmsPicAdInfo.setCompanyId("1");
        cmsPicAdInfo.setPicAdUrl(cmsPicAdInfo.getPicAdUrl() + "(");
        cmsPicAdInfo.setCreateDate(date);
        cmsPicAdInfo.setCreateTime(time);
        cmsPicAdInfo.setCreateUser("company");
        cmsPicAdInfo.setUpdateUser("company");
        cmsPicAdInfo.setUpdateDate(date);
        cmsPicAdInfo.setUpdateTime(time);
        cmsPicAdInfo.setPicAdState("0");
        return cmsPicAdInfoMapper.insertCmsPicAdInfo(cmsPicAdInfo);
    }

    /**
     * 修改图片广告
     *
     * @param cmsPicAdInfo 图片广告
     * @return 结果
     */
    @Override
    public int updateCmsPicAdInfo(CmsPicAdInfo cmsPicAdInfo) {
        if (StringUtils.isNotBlank(cmsPicAdInfo.getPicAdUrl()) && cmsPicAdInfo.getPicAdUrl().indexOf("(") == -1) {
            cmsPicAdInfo.setPicAdUrl(cmsPicAdInfo.getPicAdUrl() + "(");
        }
        cmsPicAdInfo.setUpdateDate(DateUtils.getDate());
        cmsPicAdInfo.setUpdateTime(DateUtils.getTimeNow());
        if (cmsPicAdInfoMapper.updateCmsPicAdInfo(cmsPicAdInfo) > 0) {
            redisManager.delete("picAdInfo_1");
        } else {
            LOGGER.info("编辑广告失败");
            return 0;
        }
        return 1;
    }

    /**
     * 删除图片广告对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCmsPicAdInfoByIds(String ids) {
        String[] arrId = ids.split(",");
        for (String id : arrId) {
            CmsPicAdInfo delType = cmsPicAdInfoMapper.selectCmsPicAdInfoById(Long.valueOf(id));
            if (delType != null) {
                redisManager.delete("picAdInfo_1");
                cmsPicAdInfoMapper.deleteCmsPicAdInfoById(Long.valueOf(id));
            }
        }
        return 1;
    }

    /**
     * 删除图片广告信息
     *
     * @param picAdId 图片广告ID
     * @return 结果
     */
    @Override
    public int deleteCmsPicAdInfoById(Long picAdId) {
        return cmsPicAdInfoMapper.deleteCmsPicAdInfoById(picAdId);
    }
}
