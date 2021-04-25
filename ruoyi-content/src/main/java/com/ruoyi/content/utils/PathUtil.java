package com.ruoyi.content.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 获取路径方法
 *
 * @author Administrator
 */
public class PathUtil {

    /**
     * 获取web项目访问路径
     * 使用该方法是必须在web.xml中配置了监听  org.springframework.web.context.request.RequestContextListener
     *
     * @return
     */
    public static String getWebPath() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        return basePath;
    }

    /**
     * 获取web项目classes路径
     *
     * @return
     */
    public static String getClassesPath() {
        return Thread.currentThread().getContextClassLoader().getResource("").getPath();
    }

    /**
     * 获取已发布文章的浏览静态页
     * <p>
     * liu.hx
     * 2018年4月24日
     *
     * @throws
     */
    public static String getArticleViewUrl(String timestamp, String articleId) {
        return "template/html/view/" + timestamp.substring(0, 8) + "/" + articleId + "b" + timestamp + ".html";
    }

    /**
     * 获取文章编辑静态页
     * <p>
     * liu.hx
     * 2018年4月24日
     *
     * @throws
     */
    public static String getArticleEditUrl(String timestamp, String name) {
        return "template/html/edit/" + timestamp.substring(0, 8) + "/" + name + ".html";
    }

    /**
     * 获取文章编辑静态页
     * <p>
     * liu.hx
     * 2018年4月24日
     *
     * @throws
     */
    public static String getArticleCreateUrl(String timestamp, String name) {
        return "template/html/create/" + timestamp.substring(0, 8) + "/" + name + ".html";
    }

}
