package com.zhicall.op.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zhicall.op.enums.BussinessType;

public class FileUtil {
	
	private static final String PREFIX = "&";
	
	private static Map<BussinessType, String> map;
	
	static {
		map = new HashMap<>();
		map.put(BussinessType.STATUS, "isrunning.sh");
		map.put(BussinessType.START, "start.sh");
		map.put(BussinessType.KILL, "kill.sh");
		map.put(BussinessType.SHOWLOG, "showlog.sh");
		map.put(BussinessType.SHOWFILE, "showfile.sh");
		map.put(BussinessType.SHOWTOMCATLOG, "showtomcatlog.sh");
		map.put(BussinessType.BACKUP, "backup.sh");
		map.put(BussinessType.DEPLOY, "deploy.sh");
	}
	
	public static String getFileName(BussinessType type) {
		return map.get(type);
	}
	
	public static String getShell(BussinessType type, List<String> param){
		return parseShell(getShell(type), param);
	}
	
	public static String getShell(BussinessType type, String... param){
		List<String> list = new ArrayList<>();
		for (String string : param) {
			list.add(string);
		}
		return parseShell(getShell(type), list);
	}
	
	public static String getShell(BussinessType type){
		String fileName = map.get(type);
		String basePath = FileUtil.class.getClassLoader().getResource("").getPath();
		String file = basePath + "shell" + File.separator + fileName;
		File fil = new File(file);
		BufferedReader in = null;
		try{
			in = new BufferedReader(new FileReader(fil));
			StringBuilder builder = new StringBuilder();
			String str = null;
			while((str = in.readLine()) != null){
				builder.append(str);
			}
			return builder.toString();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static String parseShell(String shell, List<String> param) {
		if (shell.contains(PREFIX) && param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				int index = i + 1;
				String oldChat = PREFIX + index;
				shell = shell.replace(oldChat, param.get(i));
			}
		}
		return shell;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		List<String> list = new ArrayList<>() ;
		list.add("tomcat");
	   System.out.println();
		
	}
}
