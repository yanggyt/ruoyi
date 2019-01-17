package com.ruoyi.cms.controller;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 通用请求处理
 * 
 * @author ruoyi
 */
@Controller
public class CmsController
{
    private static final Logger log = LoggerFactory.getLogger(CmsController.class);

    @RequestMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {

    }

}
