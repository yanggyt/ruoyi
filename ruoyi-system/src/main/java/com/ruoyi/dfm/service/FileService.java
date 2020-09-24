package com.ruoyi.dfm.service;

import com.ruoyi.dfm.dao.FileDAO;
import com.ruoyi.dfm.pojo.FileInfo;
import com.ruoyi.dfm.pojo.UserInfo;
import com.ruoyi.dfm.util.PropertiesUtils;
import com.ruoyi.dfm.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Component
public class FileService {
	private static final Logger log = LoggerFactory.getLogger(FileService.class);
	@Autowired
	private FileDAO fileDAO;


	// 默认文件存储路径
	public static String rootPath = "";

	// 文件上传的最大值，100MB
	private int maxPostSize = 100 * 1024 * 1024;
	// 文件传输编码
	private String fileEncoding = "utf-8";

	// 获取系统文件根路径
	static {
		try {
//			rootPath = PropertiesUtils.getProperties().getProperty(
//					"FILE_PHYSICAL_ROOT");
			File file = new File(rootPath);
			if (!file.exists()) {
				file.mkdir();
			}
			file = null;
		} catch (Exception e) {
			log.error("读取资源文件错误！");
		}
	}

	/**
	 * 获取系统文件根目录 Kfighter Dec 10, 2010 修改者名字 修改日期 修改内容
	 * 
	 * @return
	 * @return String
	 * @throws
	 */
	public String getRootPath() {
		return rootPath;
	}
	
	/**
	 * @Title: savePhysicFile
	 * @Description: 存储物理文件
	 * @param
	 * @return
	 * @return boolean
	 * @throws
	 */
	public void savePhysicFile(UserInfo loginUser, MultipartFile[] multipartFiles, List<FileInfo> list , String filePath, boolean isRename, String reNameKey) throws Exception{

		try {
			String path = rootPath + filePath;
			if(!createDir(path))
			{
				log.error("创建项目文件目录失败");
				throw new IOException();
			}
			FileInfo fileInfo;
			for(MultipartFile multipartFile : multipartFiles) {
				if(null == multipartFile) {
					continue;
				}
				// 表单file元素name属性值
				String name =  multipartFile.getName();
				// 原文件名值
				String original = multipartFile.getOriginalFilename();
				if(null == original || "".equals(original))
				{
					continue ;
				}
				InputStream is = multipartFile.getInputStream();
				if (null == is) {
					continue;
				}
				
				String newFileName = original;
				
				//处理DFM文件名称
				if(isRename) {
					if(!newFileName.startsWith(reNameKey)) {
						newFileName = reNameKey + newFileName;
					}
				}
				
				FileOutputStream fs = new FileOutputStream( path + "/"+ newFileName);
		        byte[] buffer = new byte[1024*1024];   
		        int bytesum = 0;   
		        int byteread = 0;    
		        while ((byteread=is.read(buffer))!=-1)   
		        {   
		           bytesum += byteread;   
		           fs.write(buffer,0,byteread);   
		           fs.flush();   
		        }    
		        fs.close();   
		        is.close();  
		        
				fileInfo = new FileInfo();
				fileInfo.setFieldName(name);
				fileInfo.setFileName(newFileName);
				fileInfo.setFileSize(multipartFile.getSize());
				fileInfo.setRelaPath(filePath);
				fileInfo.setUploadTime(TimeUtil.getNowChar14());
				fileInfo.setUploadUser(loginUser.getId());
				// 设置实际名称
				String extendName = original.substring(original.lastIndexOf(".") + 1);
				fileInfo.setExtendName(extendName);
				//需要返回文件ID
				fileDAO.add(fileInfo);
				list.add(fileInfo);
			}
		} catch (IOException e) {
			log.error("存储物理文件失败",e);
			throw e;
		}

	}

	/**
	 * 根据文件表获取物理文件 Kfighter Oct 22, 2010 修改者名字 修改日期 修改内容
	 * 
	 * @param file
	 *            附件
	 * @throws
	 */
	public File getPhysicFile(FileInfo file) {
		File pFile = new File(rootPath + "/" +file.getRelaPath() + "/"
				+ file.getFileName());
		if (!pFile.exists()) {
			return null;
		} else {
			return pFile;
		}
	}
	
	
	public boolean createDir(String dir)
	{
		boolean flag = true;
		File file = new File(dir);
		if(!file.exists())
		{
			return file.mkdirs();
		}
		return flag;
	}
	
	public File createFile(String filePath) throws IOException
	{
		String path = rootPath + filePath;
		File file = new File(path);
		if(!file.exists())
		{
			file.createNewFile();
		}
		return file;
	}
	
	
	public boolean deleteFile(int fid)
	{
		FileInfo fileInfo = fileDAO.getById(fid);
		
		//File file = new File(rootPath + "/" + fileInfo.getRelaPath()+ "/" + fileInfo.getFileName());
		//file.deleteOnExit();
		if(fileInfo != null)
		{
			fileDAO.delete(fid);
			String path = rootPath + "/" + fileInfo.getRelaPath()+ "/" + fileInfo.getFileName();
			deletePhysicFile(path);
		}
		return false;
	}
	
	public boolean deletePhysicFile(String path)
	{
		File file = new File(path);
		file.delete();
		return true;
	}

	
	public FileInfo getById(int fid)
	{
		return fileDAO.getById(fid);
	}

	@Value("${FILE_PHYSICAL_ROOT}")
	public void setRootPath(String rootPath) {
		FileService.rootPath = rootPath;
	}
}
