package com.zhicall.op.openlog;

import com.alibaba.fastjson.JSON;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileCtr extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String ROOTPATH = "/home/esb_logs";

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getParameter("path");
		if ((path == null) || ("".equals(path.trim()))) {
			path = ROOTPATH;
		}
		Map<String, List<Map<String, Object>>> map = new HashMap<>();
		File file = new File(path);
		if (file.exists()) {
			List<Map<String, Object> > dirList = new ArrayList<>();
			List<Map<String, Object> > list = new ArrayList<>();
			File[] files = file.listFiles();
			for (File file2 : files) {
				if (file2.isDirectory()) {
					dirList.add(getFileDetail(file2));
				} else {
					list.add(getFileDetail(file2));
				}
			}
			map.put("file", list);
			map.put("dirFile", dirList);
			((ServletRequest) resp).setCharacterEncoding("UTF-8");

			PrintWriter out = null;
			try {
				out = resp.getWriter();
				out.write(JSON.toJSONString(map));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null)
					out.close();
			}
		}
	}

	private Map<String, Object> getFileDetail(File file) {
		Map<String, Object> fileMap = new HashMap<>();
		Date date = new Date(file.lastModified());
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String dddd = df.format(date);
		fileMap.put("name", file.getName());
		fileMap.put("path", file.getAbsolutePath());
		fileMap.put("length", Long.valueOf(file.length() / 1024L + 1L));
		fileMap.put("updateTime", dddd);
		return fileMap;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String strName = new String(req.getParameter("filePath").getBytes("iso-8859-1"), "utf-8");
		String[] names = strName.split(",");
		if (names.length <= 1) {
			downloadFile(names[0], req, resp);
		}
	}

	private void downloadFiles(String[] files, HttpServletRequest req, HttpServletResponse resp) {
		List<File> fileList = new ArrayList<File>();
		for (String str : files) {
			File file = new File(str);
			fileList.add(file);
		}
	}

	private void downloadFile(String fileName, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		File file = new File(fileName);
		if (file.exists()) {
			String filename = URLEncoder.encode(file.getName(), "utf-8");
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			int fileLength = (int) file.length();
			resp.setContentLength(fileLength);

			if (fileLength != 0) {
				RandomAccessFile accessFile = new RandomAccessFile(file, "r");
				byte[] buf = new byte[1048576];

				ServletOutputStream servletOS = resp.getOutputStream();
				int readLength = 0;
				while ((readLength = accessFile.read(buf)) != -1) {
					servletOS.write(buf, 0, readLength);
				}
				accessFile.close();
				servletOS.flush();
				servletOS.close();
			}
		}
	}
}