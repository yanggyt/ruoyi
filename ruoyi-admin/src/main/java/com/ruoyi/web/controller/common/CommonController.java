package com.ruoyi.web.controller.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.FormFileConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.system.domain.FormFile;
import com.ruoyi.system.service.IFormFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;


/**
 * 通用请求处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/common")
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private IFormFileService formFileService;

    private static final String FILE_DELIMETER = ",";

    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = RuoYiConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    @ResponseBody
    public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            List<String> urls = new ArrayList<String>();
            List<String> fileNames = new ArrayList<String>();
            List<String> newFileNames = new ArrayList<String>();
            List<String> originalFilenames = new ArrayList<String>();
            for (MultipartFile file : files)
            {
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = serverConfig.getUrl() + fileName;
                urls.add(url);
                fileNames.add(fileName);
                newFileNames.add(FileUtils.getName(fileName));
                originalFilenames.add(file.getOriginalFilename());
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
            ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
            ajax.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMETER));
            ajax.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMETER));
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 自定义文件上传
     * @param files 文件列表
     * @param type 文件类型
     * @return
     */
    @PostMapping("/uploadFiles")
    @ResponseBody
    public AjaxResult customizeUploadFile(List<MultipartFile> files, Long parentId, Integer type)
    {
        try
        {
            String uploadPath = "";
            if (Objects.equals(type, FormFileConstants.PAYMENTREQUEST_TEMPLATE) || Objects.equals(type, FormFileConstants.PAYMENTREQUEST)){
                uploadPath = FormFileConstants.PAYMENTREQUEST_PATH;
            } else if (Objects.equals(type, FormFileConstants.REQUISTION_TEMPLATE) || Objects.equals(type, FormFileConstants.REQUISITION)){
                uploadPath = FormFileConstants.REQUISITION_PATH;
            } else if (Objects.equals(type, FormFileConstants.PETITION_TEMPLATE) || Objects.equals(type, FormFileConstants.PETITION)){
                uploadPath = FormFileConstants.PETITION_PATH;
            }
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath() + uploadPath;
            List<FormFile> list = new ArrayList<>();
            for (MultipartFile file : files)
            {
                // 上传并返回新文件名称
                String path = FileUploadUtils.uploads(filePath, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
                FormFile formFile = new FormFile();
                if (StringUtils.isNotNull(parentId)) {
                    formFile.setParentId(parentId);
                }
                formFile.setFileType(type);
                formFile.setFilePath(uploadPath + path);
                formFile.setFileName(file.getOriginalFilename());
                formFile.setCreateBy(ShiroUtils.getSysUser().getUserName());
                formFile.setCreateTime(DateUtils.getNowDate());
                list.add(formFile);
            }
            //保存文件信息
            formFileService.batchFormFile(list);
            return AjaxResult.success();
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载(自定义文件名)
     * @param resource 文件路径
     * @param fileName 文件名
     * @throws Exception
     */
    @GetMapping("/download/resources")
    public void resourceDownloads(String resource, String fileName, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = RuoYiConfig.getUploadPath();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX) + resource;
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            if(StringUtils.isNotEmpty(fileName)){
                downloadName = fileName;
            }
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 本地文件删除
     */
    @PostMapping("/remove/file")
    @ResponseBody
    public AjaxResult removeFile(FormFile formFile)
    {
        String filePath = RuoYiConfig.getUploadPath();
        boolean b = FileUtils.deleteFile(filePath + formFile.getFilePath());
        if (b) {
            formFileService.deleteFormFileById(formFile.getId());
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = RuoYiConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

}
