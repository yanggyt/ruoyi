package com.ruoyi.system.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.ruoyi.common.base.BaseService;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.QSysConfig;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.repository.SysConfigRepository;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 参数配置 服务层实现
 *
 * @author ruoyi
 */
@Service
public class SysConfigServiceImpl extends BaseService implements ISysConfigService {
    @Autowired
    private SysConfigRepository sysConfigRepository;

    /**
     * 查询参数配置信息
     *
     * @param configId 参数配置ID
     * @return 参数配置信息
     */
    @Override
    public SysConfig selectConfigById(Long configId) {
        return sysConfigRepository.findById(configId).get();
    }

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        SysConfig retConfig = sysConfigRepository.findFirstByConfigKey(configKey);
        return StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue() : "";
    }

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    @Override
    public Page<SysConfig> selectConfigList(SysConfig config, Pageable pageable) {
        return sysConfigRepository.findAll(getPredicate(config), pageable);
    }

    private Predicate getPredicate(SysConfig config){
        QSysConfig qSysConfig = QSysConfig.sysConfig;
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.isNotEmpty(config.getConfigName())){
            predicates.add(buildLike(qSysConfig.configName, config.getConfigName()));
        }
        if(StringUtils.isNotEmpty(config.getConfigType())){
            predicates.add(buildEqual(qSysConfig.configType, config.getConfigType()));
        }
        if(StringUtils.isNotEmpty(config.getConfigKey())){
            predicates.add(buildLike(qSysConfig.configKey, config.getConfigKey()));
        }
        if(config.getStartTime() != null){
            predicates.add(buildGreaterThanOrEqualTo(qSysConfig.createTime, config.getStartTime()));
        }
        if(config.getEndTime() != null){
            predicates.add(buildLessThanOrEqualTo(qSysConfig.createTime, config.getEndTime()));
        }
        return ExpressionUtils.allOf(predicates);
    }

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int insertConfig(SysConfig config) {
        sysConfigRepository.save(config);
        return 1;
    }

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public int updateConfig(SysConfig config) {
        sysConfigRepository.save(config);
        return 1;
    }

    /**
     * 批量删除参数配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteConfigByIds(String ids) {
        sysConfigRepository.deleteByConfigIdIn(Arrays.asList(Convert.toLongArray(ids)));
        return 1;
    }

    /**
     * 校验参数键名是否唯一
     *
     * @param config 参数配置信息
     * @return 结果
     */
    @Override
    public String checkConfigKeyUnique(SysConfig config) {
        Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
        SysConfig info = sysConfigRepository.findFirstByConfigKey(config.getConfigKey());
        if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue()) {
            return UserConstants.CONFIG_KEY_NOT_UNIQUE;
        }
        return UserConstants.CONFIG_KEY_UNIQUE;
    }

    @CacheEvict(allEntries = true)
    @Override
    public void clearCache() {

    }
}
