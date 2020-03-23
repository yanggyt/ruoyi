package com.ruoyi.sso.mapper;

import java.util.List;
import com.ruoyi.sso.domain.SsoApplication;
import org.springframework.stereotype.Repository;

/**
 * 单点登录应用Mapper接口
 * 
 * @author shixueshu
 * @date 2020-03-23
 */
@Repository
public interface SsoApplicationMapper 
{
    /**
     * 查询单点登录应用
     * 
     * @param appId 单点登录应用ID
     * @return 单点登录应用
     */
    public SsoApplication selectSsoApplicationById(Long appId);

    /**
     * 查询单点登录应用列表
     * 
     * @param ssoApplication 单点登录应用
     * @return 单点登录应用集合
     */
    public List<SsoApplication> selectSsoApplicationList(SsoApplication ssoApplication);

    /**
     * 新增单点登录应用
     * 
     * @param ssoApplication 单点登录应用
     * @return 结果
     */
    public int insertSsoApplication(SsoApplication ssoApplication);

    /**
     * 修改单点登录应用
     * 
     * @param ssoApplication 单点登录应用
     * @return 结果
     */
    public int updateSsoApplication(SsoApplication ssoApplication);

    /**
     * 删除单点登录应用
     * 
     * @param appId 单点登录应用ID
     * @return 结果
     */
    public int deleteSsoApplicationById(Long appId);

    /**
     * 批量删除单点登录应用
     * 
     * @param appIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSsoApplicationByIds(String[] appIds);

    /**
     * 根据应用标识与应用密钥查找应用
     * @param ssoApplication
     * @return
     */
    List<SsoApplication> querySsoApplicationByAppKeyAndSecret(SsoApplication ssoApplication);
}
