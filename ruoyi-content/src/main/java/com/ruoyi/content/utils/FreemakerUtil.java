package com.ruoyi.content.utils;

import com.ruoyi.content.constants.PropertiesConstants;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.Map;

public class FreemakerUtil {
    private static Log logger = LogFactory.getLog(FreemakerUtil.class);

    /**
     * 生成静态文件
     *
     * @param conf
     * @param templateName 模板名称
     * @param filePath     文件路径
     * @return
     */
    public static boolean generateStaticPage(Configuration conf, String templateName, String filePath,
                                             Map<String, Object> data) {
        Writer out = null;
        try {
            logger.info("开始生成静态文件：" + filePath);
            long time = System.currentTimeMillis();
            templateName = templateName.replaceAll("//", "/");
            conf.setDirectoryForTemplateLoading(new File(PropertiesConstants.PROJECT_LOCALPATH));
            Template template = conf.getTemplate(templateName, "UTF-8");
            filePath = filePath.replaceAll("//", "/");
            logger.info("打印1【" + filePath + "】");
            File contentFileDir = new File(filePath.substring(0, filePath.lastIndexOf("/")));

            if (!contentFileDir.exists()) {
                contentFileDir.mkdirs();
            }
            contentFileDir = new File(filePath);
            if (!contentFileDir.exists()) {
                logger.info(contentFileDir.createNewFile());
                contentFileDir.createNewFile();
            }
            logger.info("文件位置" + filePath);
            out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(contentFileDir), "UTF-8"));
            // out = new BufferedWriter(new OutputStreamWriter(new
            // FileOutputStream(contentFileDir),"UTF-8"));
            template.process(data, out);
            time = System.currentTimeMillis() - time;
            logger.info("成功生成静态文件，耗时(ms)：" + time);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("模板文件未找到", e);
            return false;
        } catch (TemplateException e) {
            logger.error("生成静态文件异常", e);
            return false;
        } catch (Exception e) {
            logger.error(e);
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
    }

    /**
     * 删除目录下的所有物理文件
     *
     * @param filePath 文件目录
     * @return
     */
    public static boolean deleteAllFile(String filePath) {
        File dir = new File(filePath);
        if (dir.exists()) {
            for (File file : dir.listFiles()) {
                if (!file.delete()) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 删除指定文件
     *
     * @param filePath
     * @param fileNames
     * @return
     */
    public static boolean deleteFiles(String filePath, String fileNames) {
        File dir = new File(filePath);
        if (dir.exists()) {
            for (File file : dir.listFiles()) {
                if (file.getName().contains(fileNames)) {
                    if (!file.delete()) {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean archivePageCommonData(Map<String, Object> data) {
        // data.put(TemplateTagConstant.SITE_ROOT, "");
        return true;
    }

    public static void main(String[] args) {
        // Configuration conf=new Configuration();
        // Map<String, Object> pageData = new HashMap<String, Object>();
        // CmsChannel4Page channel4Page = new CmsChannel4Page();
        // channel4Page.setTitle("健康热点");
        // channel4Page.setChannelDes("channeldes");
        // channel4Page.setChannelDetailPageFileName("channelDetailPage");
        // channel4Page.setChannelId("10163");
        // channel4Page.setChannelUrl("/financial");
        // channel4Page.setMakeDate(new Date());
        // channel4Page.setChannelName("名称");
        // channel4Page.setPageSize(0);
        // CmsContent4Page content4Page = new CmsContent4Page();
        // CmsContent content=new CmsContent();
        // content.setChannelId("10886");
        // content.setTitle("健康热点");
        // content4Page = content4Page.copyproperty(content);
        // content4Page.setFileExt(".html");
        // content4Page.setContentHtml("<div
        // class=\"article_container\"><h2>老年人“华夏慈善基金”成立</h2><div><span>2016-08-23</span><i>华夏保险</i></div><img
        // src=\"gv/marketing_zone/images/img_picture.png\"><p>华夏保险是一家全国性、股份制人寿保险公司。注册资本金153亿元人民币，总资产近4000亿元。公司以全方位产品线满足客户需求，树立了“华夏福”“富贵竹”“常青藤”等产品线的良好市场口碑，是“亚洲品牌500强”企业、“稳健经营保险公司”。服务热线：95300</p></div>");
        //
        //
        //
        // pageData.put("channelID", "10163");
        // pageData.put("onlineHome", "D:\\");//online的发布路径
        // pageData.put("ctx", "D:/cms");
        // pageData.put("publishPath", "/publish");//发布路径
        // pageData.put("site", "D:/cms/test");
        // pageData.put("quotePage", "/publish/product");
        // pageData.put("systemConfig", SystemConfig.getConfig());
        // pageData.put("ctx_resource", "ctx");
        // pageData.put("channel", channel4Page);
        // pageData.put("content", content4Page);
        //
        // generateStaticPage(conf, "/WEB-INF/template/20170816/100000.flt", "C:/work/tool/tomcat/tomcatbug81/webapps/test/a.html", pageData);
    }
}
