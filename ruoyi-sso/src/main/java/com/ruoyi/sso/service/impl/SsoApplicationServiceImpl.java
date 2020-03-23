package com.ruoyi.sso.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sso.mapper.SsoApplicationMapper;
import com.ruoyi.sso.domain.SsoApplication;
import com.ruoyi.sso.service.ISsoApplicationService;
import com.ruoyi.common.core.text.Convert;

/**
 * 单点登录应用Service业务层处理
 * 
 * @author shixueshu
 * @date 2020-03-23
 */
@Service
public class SsoApplicationServiceImpl implements ISsoApplicationService 
{
    @Autowired
    private SsoApplicationMapper ssoApplicationMapper;

    /**
     * 查询单点登录应用
     * 
     * @param appId 单点登录应用ID
     * @return 单点登录应用
     */
    @Override
    public SsoApplication selectSsoApplicationById(Long appId)
    {
        return ssoApplicationMapper.selectSsoApplicationById(appId);
    }

    /**
     * 查询单点登录应用列表
     * 
     * @param ssoApplication 单点登录应用
     * @return 单点登录应用
     */
    @Override
    public List<SsoApplication> selectSsoApplicationList(SsoApplication ssoApplication)
    {
        return ssoApplicationMapper.selectSsoApplicationList(ssoApplication);
    }

    /**
     * 新增单点登录应用
     * 
     * @param ssoApplication 单点登录应用
     * @return 结果
     */
    @Override
    public int insertSsoApplication(SsoApplication ssoApplication)
    {
        ssoApplication.setCreateTime(DateUtils.getNowDate());
        return ssoApplicationMapper.insertSsoApplication(ssoApplication);
    }

    /**
     * 修改单点登录应用
     * 
     * @param ssoApplication 单点登录应用
     * @return 结果
     */
    @Override
    public int updateSsoApplication(SsoApplication ssoApplication)
    {
        ssoApplication.setUpdateTime(DateUtils.getNowDate());
        return ssoApplicationMapper.updateSsoApplication(ssoApplication);
    }

    /**
     * 删除单点登录应用对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSsoApplicationByIds(String ids)
    {
        return ssoApplicationMapper.deleteSsoApplicationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除单点登录应用信息
     * 
     * @param appId 单点登录应用ID
     * @return 结果
     */
    @Override
    public int deleteSsoApplicationById(Long appId)
    {
        return ssoApplicationMapper.deleteSsoApplicationById(appId);
    }

    @Override
    public List<SsoApplication> querySsoApplicationByAppKeyAndSecret(SsoApplication ssoApplication) {
        return ssoApplicationMapper.querySsoApplicationByAppKeyAndSecret(ssoApplication);
    }
}
