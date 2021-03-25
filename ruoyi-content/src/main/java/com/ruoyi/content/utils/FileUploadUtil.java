package com.ruoyi.content.utils;

import com.ruoyi.content.domain.OssDTO;
import com.ruoyi.content.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 说明：文件上传
 *
 * @author wang.q
 * @date 2017年8月18日
 */
public class FileUploadUtil {
    private final static Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    /**
     * 文件上传处理-上传到本地
     *
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Message fileUploadTOLocal(HttpServletRequest request) {
        Message msg = new Message();
        try {
            // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                    request.getSession().getServletContext());
            // 检查form中是否有enctype="multipart/form-data"
            if (multipartResolver.isMultipart(request)) {
                // 将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    // 一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    if (file != null) {
                        String path = "" + file.getOriginalFilename();
                        // 上传
                        file.transferTo(new File(path));
                    }
                }
                msg.setResult(true);
            } else {
                logger.info("文件上传失败，form表单没有文件信息");
                msg.setInfo("文件上传失败，没有获取到上传文件信息！");
                msg.setResult(false);
            }
        } catch (Exception e) {
            logger.info("文件上传失败,失败原因【{}】", e.getMessage());
            msg.setInfo("文件上传失败，请稍后再试！");
            msg.setResult(false);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 上传文件到OSS上
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Message filesUploadTOOSS(String path, MultipartHttpServletRequest multiRequest, OssDTO ossDTO) {
        Message msg = new Message();
        try {
            // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
            // 检查form中是否有enctype="multipart/form-data"
            Map<String, String> map = new HashMap<String, String>();
            // 将request变成多部分request
            // 获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    Message fileMsg = new Message();
                    String fileName = file.getOriginalFilename();
                    String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                    fileName = path + "/" + DateUtil.convertDate(new Date(), DateUtil.yyyyMMddHHmmssSSS) + ext;
                    fileMsg = ossFileUpload(fileName, file.getInputStream(), ossDTO);
                    if (fileMsg.getResult()) {
                        map.put(file.getOriginalFilename(), fileMsg.getInfo());
                    }
                    // String path = "" + file.getOriginalFilename();
                    // 上传到本地临时
                    // file.transferTo(new File(path));
                }
            }
            msg.setResult(true);
        } catch (Exception e) {
            logger.info("文件上传失败,失败原因【{}】", e.getMessage());
            msg.setInfo("文件上传失败，请稍后再试！");
            msg.setResult(false);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 上传图片到阿里云服务器
     *
     * @param fileName
     * @param in
     * @return
     */
    public static Message ossFileUpload(String fileName, InputStream in, OssDTO ossDTO) {
        Message msg = new Message();
        try {
            if (in == null) {
                logger.info("从微信端下载图片失败");
                return new Message(false, "图片上传失败，请重新上传图片信息");
            }
            String path = ossDTO.getOssPath() + fileName;
            logger.info("文件上传OSS路径【{}】", path);

            msg = OSSUtil.uploadFileByInputStreamReturnUrl(ossDTO.getOssEndPoint(), ossDTO.getOssId(),
                    ossDTO.getOssKey(), ossDTO.getBucketName(), in, path);
        } catch (Exception ex) {
            logger.info("系统异常【{}】", ex);
        }
        return msg;
    }

    /**
     * 单文件上传
     *
     * @param path
     * @return
     */
    public static Message fileUploadTOOSS(String path, InputStream inp, OssDTO ossDTO) {
        Message msg = new Message();
        try {
            msg = ossFileUpload(path, inp, ossDTO);
        } catch (Exception e) {
            logger.info("文件上传失败,失败原因【{}】", e.getMessage());
            msg.setInfo("文件上传失败，请稍后再试！");
            msg.setResult(false);
            e.printStackTrace();
        }
        return msg;
    }

}
