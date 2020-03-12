package cn.com.infosouth.arj21.utils_oneselef;

import java.io.File;

/**
 * 自定义文件工具类
 * @author lenove
 *
 */
public class FileUtils {

	
	// 将byte流输出到文件
 	public static String getFileSizeByPath(String dstFilePath) {
 		String fileSize = "";
 		File file = null;
 		try {
 			file = new File(dstFilePath);
 			if (!file.exists() && file.isDirectory()) {// 判断是否是文件夹
 				file.mkdirs();
 				System.out.println("there not exist that file" + dstFilePath);
 			} else {
 				fileSize = String.valueOf(file.length());
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return fileSize;
 	}
	
}
