package com.zhicall.op.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhicall.op.entity.Cmd;
import com.zhicall.op.entity.DeployInfo;
import com.zhicall.op.enums.BussinessType;
import com.zhicall.op.mapper.DeployMapper;
import com.zhicall.op.util.CommandUtil;
import com.zhicall.op.util.FileUtil;
import com.zhicall.op.util.shell.RemoteShellTool;
import com.zhicall.op.util.shell.SSHCommandExecutor;

@Service("deployService")
public class DeployServiceImpl implements DeployService {
	
	private static final String charset = "utf-8";
	
	@Autowired
	private DeployMapper deployMapper;

	private static Map<String, Boolean> map = new HashMap<>();
	
	private boolean isExcuting(String uuid) {
		Boolean flag = map.get(uuid);
		if (flag == null) {
			return false;
		}
		return flag;
	}
	
	private void setExcuting(String uuid) {
		map.put(uuid, Boolean.TRUE);
	}
	
	private void disableExcuting(String uuid) {
		map.remove(uuid);
	}
	@Override
	public String kill(String id) {
		DeployInfo info = getDeployInfo(id);
		if (info == null) {
			return "对应的项目不存在！";
		}
		if (isKilled(info)) {
			return "服务处于未启动状态";
		}
		return excuteCommand(info, BussinessType.KILL, info.getTname());
	}
	
	private String excuteCommand(DeployInfo info, BussinessType type, String... args) {
		RemoteShellTool shellTool = new RemoteShellTool(info.getIp(), info.getLname(), info.getPassword(), charset);
		String command = CommandUtil.generateShellCommand(info.getSdir(), FileUtil.getFileName(type), args);
		return shellTool.exec(command);
	}
	
	private boolean isKilled(DeployInfo info) {
		boolean flag = Boolean.parseBoolean(status(info));
		if (flag) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}
	
	private boolean waitKilled(DeployInfo info) {
		boolean flag = true;
		while (!flag) {
			flag = isKilled(info);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public DeployInfo getDeployInfo(String id) {
		return deployMapper.getDetail(id);
	}

	@Override
	public String start(String id) {
		try {
			if (isExcuting(id)) {
				return "该项目正在执行其它请求中....";
			}
			setExcuting(id);
			DeployInfo info = getDeployInfo(id);
			if (info == null) {
				return "对应的项目不存在！";
			}
			if (!isKilled(info)) {
				return "服务已处于启动状态";
			}
			return excuteCommand(info, BussinessType.START, info.getJavaHomePath(), info.getZhicallConfig(), info.getTdir(), info.getTname());
		} finally {
			disableExcuting(id);
		}
		
	}

	@Override
	public String restart(String id) {
		try {
			if (isExcuting(id)) {
				return "该项目正在执行其它请求中....";
			}
			setExcuting(id);
			StringBuilder builder = new StringBuilder();
			DeployInfo info = getDeployInfo(id);
			if (info == null) {
				return "对应的项目不存在！";
			}
			if (!isKilled(info)) {
				builder.append(excuteCommand(info, BussinessType.KILL, info.getTname()));
				waitKilled(info);
			}
			String result = builder.append(this.start(id)).toString();
			return result;
		} finally {
			disableExcuting(id);
		}
	}

	@Override
	public String deploy(String id) {
		try {
			if (isExcuting(id)) {
				return "该项目正在执行其它请求中....";
			}
			setExcuting(id);
			StringBuilder builder = new StringBuilder();
			DeployInfo info = getDeployInfo(id);
			if (info == null) {
				return "对应的项目不存在！";
			}
			if (!isKilled(info)) {
				builder.append(excuteCommand(info, BussinessType.KILL, info.getTname()));
				waitKilled(info);
			}
			
			String backupResult = excuteCommand(info, BussinessType.BACKUP, info.getBdir(), info.getWname(), info.getTdir(), info.getTname());
			builder.append(backupResult);
			
			String deployResult = excuteCommand(info, BussinessType.DEPLOY, info.getWdir(), info.getWname(), info.getTdir(), info.getTname());
			builder.append(deployResult);
			
			String result = builder.append(this.start(id)).toString();
			return result;
		} finally {
			disableExcuting(id);
		}
	}

	private String status(DeployInfo info){
		String result = excuteCommand(info, BussinessType.STATUS, info.getTname());
		return String.valueOf(StringUtils.hasText(result) && result.contains(info.getTname()) && result.contains("start"));
	}
	
	@Override
	public String status(String id) {
		DeployInfo info = getDeployInfo(id);
		return status(info);
	}

	@Override
	public String systemInfo(String uuid) {
		DeployInfo info = getDeployInfo(uuid);
		if (info == null) {
			return "对应的项目不存在！";
		}
		StringBuilder builder = new StringBuilder();
		RemoteShellTool shellTool = new RemoteShellTool(info.getIp(), info.getLname(), info.getPassword(), charset);
		builder.append("<br>").append("系统情况：").append("<br>");
		builder.append(shellTool.exec("uptime"));
		builder.append("<br>").append("CPU，内存，swap，io 情况：").append("<br>");
		builder.append(shellTool.exec("vmstat 1 4"));
		builder.append("<br>").append("磁盘容量：").append("<br>");
		builder.append(shellTool.exec("df -lh"));
		builder.append("<br>").append("磁盘状况：").append("<br>");
		builder.append(shellTool.exec("fdisk -l"));
		builder.append("<br>").append("CPU使用情况：").append("<br>");
		builder.append(shellTool.exec("top"));
			
		return builder.toString();
	}

	@Override
	public Cmd excute(String uuid, String cmd, String lastPath) {
		DeployInfo info = getDeployInfo(uuid);
		if (info == null) {
			return null;
		}
		return SSHCommandExecutor.exec(lastPath, cmd, info.getIp(), info.getLname(), info.getPassword());
	}

	@Override
	public List showFile(String uuid) {
		DeployInfo info = getDeployInfo(uuid);
		if (info == null) {
			return null;
		}
		RemoteShellTool shellTool = new RemoteShellTool(info.getIp(), info.getLname(), info.getPassword(), charset);
		String command = CommandUtil.generateShellCommand(info.getSdir(), FileUtil.getFileName(BussinessType.SHOWFILE), info.getLogpath());
		return shellTool.execList(command);
	}

	@Override
	public InputStream getInputStream(String type, String uuid, String file, String cmd, Map<String, Object> param) {
		DeployInfo info = getDeployInfo(uuid);
		if (info == null) {
			return null;
		}
		RemoteShellTool shellTool = new RemoteShellTool(info.getIp(), info.getLname(), info.getPassword(), charset);
		String command = null;
		if (!StringUtils.isEmpty(cmd)) {
			command = cmd;
		} else {
			if ("tomcat".equals(type)) {
				command = CommandUtil.generateShellCommand(info.getSdir(), FileUtil.getFileName(BussinessType.SHOWTOMCATLOG), info.getTdir(), info.getTname());
			} else {
				if (StringUtils.isEmpty(file)) {
					command = CommandUtil.generateShellCommand(info.getSdir(), FileUtil.getFileName(BussinessType.SHOWTOMCATLOG), info.getTdir(), info.getTname());
				} else {
					command = CommandUtil.generateShellCommand(info.getSdir(), FileUtil.getFileName(BussinessType.SHOWLOG), info.getLogpath(), file);
				}
			}
		}
		return shellTool.execWithInputStream(command, param);
	}
	
}
