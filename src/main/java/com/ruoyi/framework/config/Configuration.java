package com.ruoyi.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置信息
 * 
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "ruoyi")
public class Configuration
{
    /** 上传路径 */
    private String uploadPath;

    public String getUploadPath()
    {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath)
    {
        this.uploadPath = uploadPath;
    }
}
