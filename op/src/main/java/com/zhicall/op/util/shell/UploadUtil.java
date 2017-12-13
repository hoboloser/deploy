package com.zhicall.op.util.shell;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 上传文件工具类
 * 
 * @author abc
 * 
 */
public class UploadUtil {

	private static String name = "path";


	/**
	 * 读取本地服务器硬盘上的图片，并显示到页面
	 * 
	 * @param fileName
	 */
	public static void showImage(String fileName, HttpServletRequest request,
			HttpServletResponse response) {
		File file = new File(fileName);
		if (file.exists() && !"".equals(fileName)) {
			try {
				DataOutputStream dos = new DataOutputStream(
						response.getOutputStream());
				DataInputStream dis = new DataInputStream(new FileInputStream(
						file.getAbsolutePath()));
				byte[] data = new byte[2048];
				while ((dis.read(data)) != -1) {
					dos.write(data);
					dos.flush();
				}
				dis.close();
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 上传文件，返回路径和文件名
	 * @param request
	 * @return
	 */
	public static String upload(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile=multipartRequest.getFile("fileList");
		//获取文件原始名
		String fileName=multipartFile.getOriginalFilename();
		int index = fileName.lastIndexOf('.');  
		//获取后缀
		String extFileName = fileName.substring(index + 1);  
         if (index == -1) {  
             return "文件类型错误！";  
         }  
		
		//使用当前时间戳作为文件名
//		String imageName=System.currentTimeMillis()+"";
//		String name=imageName+"."+extFileName.toLowerCase();
		//获取当前项目路径
//		String path=Thread.currentThread().getContextClassLoader().getResource("").getPath();
//		int num=path.indexOf(".metadata");
//		String realPath=path.substring(1, num).replace('/', '\\')+"fogImage";
		String realPath = "/home/hm";
		File pathFile=new File(realPath);
		if (!pathFile.exists()){
			pathFile.mkdirs();
		}
		File file=new File(pathFile,fileName);
		
		String backPath="";
		if (!file.exists()) {
			try {
				file.createNewFile();
				multipartFile.transferTo(file);
				backPath=realPath + File.separator + fileName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			boolean flag = file.delete();
			if (flag) {
				try {
					file.createNewFile();
					multipartFile.transferTo(file);
					backPath=realPath + File.separator + fileName;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return backPath;
	}
	/**
	 * 上传文件，返回路径和文件名
	 * @param request
	 * @return
	 */
	public static String otherUpload(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile=multipartRequest.getFile("file");
		//获取文件原始名
		String fileName=multipartFile.getOriginalFilename();
		int index = fileName.lastIndexOf('.');  
		//获取后缀
		String extFileName = fileName.substring(index + 1);  
		if (index == -1) {  
			return "文件类型错误！";  
		}  
		
		//使用当前时间戳作为文件名
		String imageName=System.currentTimeMillis()+"";
		String name=imageName+"."+extFileName.toLowerCase();
		//获取当前项目路径
		//String path=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		//int num=path.indexOf(".metadata");
//		String realPath = "/home/hm/";
		String realPath = "E:/";
//		String realPath=path.substring(1, num).replace('/', '\\')+"fogImage";
		File pathFile=new File(realPath);
		if (!pathFile.exists()){
			pathFile.mkdirs();
		}
		File file=new File(pathFile,name);
		
		String backPath="";
		if (!file.exists()) {
			try {
				file.createNewFile();
				multipartFile.transferTo(file);
				backPath=realPath + File.separator + name;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return backPath;
	}
	
}
