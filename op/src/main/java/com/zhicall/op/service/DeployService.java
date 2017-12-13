package com.zhicall.op.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.zhicall.op.entity.Cmd;
import com.zhicall.op.entity.LogFile;

public interface DeployService {

	/**
	 * 停止服务
	 * @param id
	 */
	String kill(String id);
	
	/**
	 * 启动服务
	 * @param id
	 */
	String start(String id);
	
	/**
	 * 重启服务
	 * @param id
	 */
	String restart(String id);
	
	/**
	 * 部署服务
	 * @param id
	 */
	String deploy(String id);
	
	/**
	 * 服务状态
	 * @param id
	 */
	String status(String id);

	/**
	 * 系统状态
	 * @param uuid
	 * @return
	 */
	String systemInfo(String uuid);

	Cmd excute(String uuid, String string, String lastPath);

	List<LogFile> showFile(String uuid);
	
	
	InputStream getInputStream(String type, String uuid, String file, String cmd, Map<String, Object> returnMap);
}
