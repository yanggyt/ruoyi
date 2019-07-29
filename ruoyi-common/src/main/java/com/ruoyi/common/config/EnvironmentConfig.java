package com.ruoyi.common.config;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 从spring配置变量中获取属性值
 * @author pineapple
 * @date 2019-07-29
 * @email 2443755705@qq.com
 */
@Component
public class EnvironmentConfig implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        EnvironmentConfig.context = applicationContext;
    }

    public static String getConfig(String key) {
        return context.getEnvironment().getProperty(key);
    }

    public static String getConfig(String key, String defaultValue) {
        return context.getEnvironment().getProperty(key, defaultValue);
    }

    public static <T>T getConfig(String key, Class<T> clazz) {
        return context.getEnvironment().getProperty(key, clazz);
    }

    public static <T>T getConfig(String key, Class<T> clazz, T defaultValue) {
        return context.getEnvironment().getProperty(key, clazz, defaultValue);
    }

    /**
     * 获取项目名称
     */
    public String getName()
    {
        return StringUtils.nvl(getConfig("ruoyi.name"), "RuoYi");
    }

    /**
     * 获取项目版本
     */
    public String getVersion()
    {
        return StringUtils.nvl(getConfig("ruoyi.version"), "3.2.0");
    }

    /**
     * 获取版权年份
     */
    public String getCopyrightYear()
    {
        return StringUtils.nvl(getConfig("ruoyi.copyrightYear"), "2018");
    }

    /**
     * 获取ip地址开关
     */
    public Boolean isAddressEnabled()
    {
        return Boolean.valueOf(getConfig("ruoyi.addressEnabled"));
    }

    /**
     * 获取文件上传路径
     */
    public String getProfile()
    {
        return getConfig("ruoyi.profile");
    }

    /**
     * 获取头像上传路径
     */
    public String getAvatarPath()
    {
        return getConfig("ruoyi.profile") + "avatar/";
    }

    /**
     * 获取下载路径
     */
    public String getDownloadPath()
    {
        //下载目录
        // return getConfig("ruoyi.profile") + "download/";

        return getConfig("ruoyi.profile") + "upload/";
    }

    public String seeProfile()
    {
        //下载目录
        // return getConfig("ruoyi.profile") + "download/";

        return getConfig("ruoyi.seeProfile");
    }

    /**
     * 获取上传路径
     */
    public String getUploadPath()
    {
        return getConfig("ruoyi.profile") + "upload/";
    }

    /**
     * 获取作者
     */
    public String getAuthor()
    {
        return StringUtils.nvl(getConfig("gen.author"), "ruoyi");
    }

    /**
     * 生成包路径
     */
    public String getPackageName()
    {
        return StringUtils.nvl(getConfig("gen.packageName"), "com.ruoyi.project.module");
    }

    /**
     * 是否自动去除表前缀
     */
    public String getAutoRemovePre()
    {
        return StringUtils.nvl(getConfig("gen.autoRemovePre"), "true");
    }

    /**
     * 表前缀(类名不会包含表前缀)
     */
    public String getTablePrefix()
    {
        return StringUtils.nvl(getConfig("gen.tablePrefix"), "sys_");
    }

}
