package com.ruoyi.dfm.controller;


import com.ruoyi.dfm.pojo.FileInfo;
import com.ruoyi.dfm.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/file.do")
public class FileController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	@Autowired
	private FileService fileService;

	@RequestMapping("/download")
	public void download(HttpServletRequest request,
                         HttpServletResponse response) throws Exception{
		String fid = request.getParameter("fid");
		FileInfo fileInfo = fileService.getById(Integer.parseInt(fid));
		File file = fileService.getPhysicFile(fileInfo);
		if(null == file){
			outputMsg(response, "<script>alert('该文件不存在，下载失败，请联系管理员！');window.history.go(-1);</script>");
			return ;
		}
		//重命名文件为之前存储的名字
		String fname = fileInfo.getFileName();
		try {
			fname = new String(fname.getBytes("utf-8"),"iso8859-1");
			response.setHeader("Location", fname);
			response.setHeader("Cache-Control", "max-age=" + "");
			response.setHeader("Content-Disposition", "attachment; filename=" + fname);
			response.setContentLength((int) file.length());
			OutputStream outputStream = response.getOutputStream();
			InputStream inputStream = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();
			outputStream = null;
		} catch (Exception e) {
			log.error("下载文件"+fname+"失败！" , e);
			outputMsg(response, "<script>alert('下载文件失败，请联系管理员！');window.history.go(-1);</script>");
		} 
	}
}
