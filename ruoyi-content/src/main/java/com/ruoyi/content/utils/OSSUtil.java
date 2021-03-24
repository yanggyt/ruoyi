package com.ruoyi.content.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.ruoyi.content.constants.PropertiesConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class OSSUtil {

    private static final Logger logger = LoggerFactory.getLogger(OSSUtil.class);

    /**
     * 上传文件（文件输入流）到阿里云OSS
     *
     * @param ossEndPoint 阿里云OSS提供的ossEndPoint
     * @param ossId       阿里云OSS提供的ossId
     * @param ossKey      阿里云OSS提供的ossKey
     * @param bucketName  阿里云OSS提供的ossBucket
     * @param input       文件输入流 * @param ossPath oss上保存文件的路径
     * @throws Exception
     */
    public static String uploadFileByInputStream(String ossEndPoint, String ossId, String ossKey, String bucketName,
                                                 InputStream input, String ossPath) {
        try {
            ObjectMetadata objectMeta = new ObjectMetadata();
            // objectMeta.setContentLength(file.length());
            // 可以在metadata中标记文件类型
            objectMeta.setContentType("multipart/form-data");
            OSSClient client = createOSSClient(ossEndPoint, ossId, ossKey);
            PutObjectResult result = client.putObject(bucketName, ossPath, input, objectMeta);
            client.shutdown();
            logger.info("上传阿里云OSS结果【{}】", result.getETag());
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("上传阿里云OSS出错!!");
            return "false";
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.info("上传阿里云OSS出错!");
                }
            }
        }
    }

    /**
     * 上传文件到阿里云OSS
     *
     * @param ossEndPoint 阿里云OSS提供的ossEndPoint
     * @param ossId       阿里云OSS提供的ossId
     * @param ossKey      阿里云OSS提供的ossKey
     * @param bucketName  阿里云OSS提供的ossBucket
     * @param localPath   本地文件的路径
     * @param ossPath     oss上保存文件的路径
     * @throws Exception
     */
    public static void uploadFile(String ossEndPoint, String ossId, String ossKey, String bucketName, String localPath,
                                  String ossPath) {
        InputStream input = null;
        try {
            File file = new File(localPath);
            ObjectMetadata objectMeta = new ObjectMetadata();
            objectMeta.setContentLength(file.length());
            // 可以在metadata中标记文件类型
            objectMeta.setContentType("multipart/form-data");
            input = new FileInputStream(file);
            OSSClient client = createOSSClient(ossEndPoint, ossId, ossKey);
            PutObjectResult result = client.putObject(bucketName, ossPath, input, objectMeta);
            client.shutdown();
            logger.info("上传阿里云OSS结果【{}】", result.getETag());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("上传阿里云OSS出错!!");
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.info("上传阿里云OSS出错!");
                }
            }
        }
    }

    /**
     * 上传html文件到阿里云OSS
     *
     * @param ossEndPoint 阿里云OSS提供的ossEndPoint
     * @param ossId       阿里云OSS提供的ossId
     * @param ossKey      阿里云OSS提供的ossKey
     * @param bucketName  阿里云OSS提供的ossBucket
     * @param localPath   本地文件的路径
     * @param ossPath     oss上保存文件的路径
     * @throws Exception
     */
    public static void uploadFileHtml(String ossEndPoint, String ossId, String ossKey, String bucketName, String localPath,
                                      String ossPath) {
        InputStream input = null;
        try {
            File file = new File(localPath);
            ObjectMetadata objectMeta = new ObjectMetadata();
            objectMeta.setContentLength(file.length());
            // 可以在metadata中标记文件类型
            objectMeta.setContentType("text/html");
            input = new FileInputStream(file);
            OSSClient client = createOSSClient(ossEndPoint, ossId, ossKey);
            PutObjectResult result = client.putObject(bucketName, ossPath, input, objectMeta);
            client.shutdown();
            logger.info("上传阿里云OSS结果【{}】", result.getETag());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("上传阿里云OSS出错!!");
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.info("上传阿里云OSS出错!");
                }
            }
        }
    }

    /**
     * 从阿里云OSS上下载文件 (不保存本地)
     *
     * @param ossEndPoint 阿里云OSS提供的ossEndPointOut
     * @param ossId       阿里云OSS提供的ossId
     * @param ossKey      阿里云OSS提供的ossKey
     * @param ossBucket   阿里云OSS提供的ossBucket
     * @param path        阿里云OSS上保存文件的路径
     * @return
     */
    public static byte[] downloadFile(String ossEndPoint, String ossId, String ossKey, String ossBucket, String path) {
        OSSClient client = createOSSClient(ossEndPoint, ossId, ossKey);
        OSSObject ossObject = client.getObject(ossBucket, path);
        ObjectMetadata meta = client.getObjectMetadata(ossBucket, path);
        client.shutdown();
        meta.getContentEncoding();
        InputStream inputStream = null;
        ByteArrayOutputStream bos = null;
        byte[] b = new byte[1024];
        int len;
        try {
            inputStream = ossObject.getObjectContent();
            bos = new ByteArrayOutputStream();
            while ((len = inputStream.read(b)) != -1) {
                bos.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("从阿里云OSS下载文件出错!");
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.info("从阿里云OSS下载文件出错!");
                }
            }
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.info("从阿里云OSS下载文件出错!");
                }
            }
        }
        return bos.toByteArray();
    }

    /**
     * 从阿里云OSS上下载文件 并保存指定路径下
     *
     * @param ossEndPoint 阿里云OSS提供的ossEndPoint
     * @param ossId       阿里云OSS提供的ossId
     * @param ossKey      阿里云OSS提供的ossKey
     * @param bucketName  阿里云OSS提供的ossBucket
     * @param ossPath     阿里云OSS上保存文件的路径
     * @param localPath   保存本地的路径（包括文件名）
     * @return oss上下载下来的文件的字节数组
     */
    public static void downloadAndSaveFile(String ossEndPoint, String ossId, String ossKey, String bucketName,
                                           String ossPath, String localPath) {
        OSSClient client = createOSSClient(ossEndPoint, ossId, ossKey);

        File localFileDir = new File(localPath);
        if (!localFileDir.exists()) {
            localFileDir.mkdirs();
            if (!localFileDir.exists()) {
                logger.info("本地目录不存在，且尝试创建失败！");
            }
        }

        File file = new File(localPath);
        logger.info("创建文件目录：" + localPath);
        client.getObject(new GetObjectRequest(bucketName, ossPath), file);
        client.shutdown();

    }

    /**
     * 删除阿里云OSS文件
     *
     * @param ossEndPoint 阿里云OSS提供的ossEndPoint
     * @param ossId       阿里云OSS提供的ossId
     * @param ossKey      阿里云OSS提供的ossKey
     * @param ossBucket   阿里云OSS提供的ossBucket
     * @param path        阿里云OSS上保存文件的路径
     * @return
     */
    public static void deleteFile(String ossEndPoint, String ossId, String ossKey, String ossBucket, String path) {
        OSSClient client = createOSSClient(ossEndPoint, ossId, ossKey);
        client.deleteObject(ossBucket, path);
        client.shutdown();
    }

    /**
     * 查询OSS内的文件列表
     *
     * @param ossEndPoint
     * @param ossId
     * @param ossKey
     * @param bucketName
     * @param path
     */
    public static void findFileList(String ossEndPoint, String ossId, String ossKey, String bucketName, String path) {
        OSSClient client = createOSSClient(ossEndPoint, ossId, ossKey);
        // 构造ListObjectsRequest请求
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
        if (StringUtils.isNotBlank(path)) {
            listObjectsRequest.setPrefix(path); // fun/
        }
        // 递归列出fun目录下的所有文件
        ObjectListing listing = client.listObjects(listObjectsRequest);
        // 遍历所有Object
        logger.info("Objects内的文件:");
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            logger.info("文件名【{}】, 文件大小【{}】", objectSummary.getKey(), objectSummary.getSize());
        }
        // 遍历所有CommonPrefix
        logger.info("\nCommonPrefixs:");
        for (String commonPrefix : listing.getCommonPrefixes()) {
            logger.info(commonPrefix);
        }
    }

    private static OSSClient createOSSClient(String ossEndPoint, String ossId, String ossKey) {
        // ClientConfiguration config = new ClientConfiguration();
        // config.setProxyHost("10.0.251.94");
        // config.setProxyPort(8080);
        // config.setProxyUsername("gaining");
        // config.setProxyPassword("abcd123%");
        // TODO
        // 本地设置代理
        // OSSClient client = new OSSClient(ossEndPoint, ossId, ossKey, config);
        OSSClient client = new OSSClient(ossEndPoint, ossId, ossKey);
        return client;
    }

    public static void main(String[] args) {
        uploadFile("oss-cn-szfinance.aliyuncs.com",
                "LTAIsVIQ8j9WueV2", "Il5Y1tugBi9u8giGi9n4lQhAWwkcih",
                "hx-cdn-int", "G:\\111.jpg", "gv/YXPT/network/article/450.jpg");
//		 uploadFile("oss-cn-beijing.aliyuncs.com", "6TpnBLywxITY6YNA",
//		 "dbYhx45upstobHmBBErQQkZlrC017X", "lifeilong", "F:\\图片\\460.jpg",
//		 "我的照片/460.jpg");
    }

    public static Map<String, Object> uploadFileByInputStreamReturnUrl(String ossEndPoint, String ossId, String ossKey,
                                                                       String bucketName, InputStream input, String ossPath) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            ObjectMetadata objectMeta = new ObjectMetadata();
            // objectMeta.setContentLength(file.length());
            // 可以在metadata中标记文件类型
            objectMeta.setContentType("multipart/form-data");
            OSSClient client = createOSSClient(ossEndPoint, ossId, ossKey);
            PutObjectResult result = client.putObject(bucketName, ossPath, input, objectMeta);
            client.shutdown();
            logger.info("上传阿里云OSS结果【{}】", result.getETag());
            map.put("ossFIleId", result.getETag());
            // 设置URL过期时间为10年 3600l* 1000*24*365*10
//			Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
            // 生成URL
//			URL url = client.generatePresignedUrl(bucketName, ossPath, expiration);
//			if (url != null) {
            map.put("result", true);
            map.put("url", PropertiesConstants.OSS_URL + ossPath);
//			} else {
//				msg.setResult(false);
//				msg.setInfo("上传阿里云获取访问地址失败！");
//			}
            return map;
        } catch (Exception e) {
            logger.error("上传阿里云OSS出错!!", e);
            map.put("result", false);
            return map;
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("上传阿里云OSS出错!", e);
                }
            }
        }
    }

}
