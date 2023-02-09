package com.ruoyi.system.mapper;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @Filename: CommonParamsMapper
 * @Author: lss
 * @Data:2022/10/9 16:26
 */
public interface ComMonParamsMapper {
    /**
     * 获取项目代码(从zt_wgcprorelease获取)
     */
    public List<String> getProjectCode1();

    /**
     * 获取项目代码(从zt_projecttoproline获取)
     */
    public List<String> getProjectCode2();

    /**
     * 查询各币别汇率 (1币别 * 汇率 = 美元)
     * @return 各币别汇率
     */
    @DataSource(value = DataSourceType.SLAVE)
    @MapKey("currency")
    public Map<String, Map<String, String>> exchangeRateQuery();
}
