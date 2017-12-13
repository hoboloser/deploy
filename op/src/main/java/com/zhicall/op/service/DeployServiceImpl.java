package com.zhicall.op.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zhicall.op.entity.Cmd;
import com.zhicall.op.entity.DeployInfo;
import com.zhicall.op.entity.LogFile;
import com.zhicall.op.enums.BussinessType;
import com.zhicall.op.util.CommandUtil;
import com.zhicall.op.util.FileUtil;
import com.zhicall.op.util.shell.RemoteShellTool;
import com.zhicall.op.util.shell.SSHCommandExecutor;

@Service("deployService")
public class DeployServiceImpl extends AbstractDeployService implements DeployService {
	
	protected String excuteCommand(DeployInfo info, BussinessType type, String... args) {
		RemoteShellTool shellTool = new RemoteShellTool(info.getIp(), info.getLname(), info.getPassword(), charset);
		String command = CommandUtil.generateShellCommand(info.getSdir(), FileUtil.getFileName(type), args);
		return shellTool.exec(command);
	}

	@Override
	protected List<LogFile> excuteCommandList(DeployInfo info, BussinessType type, String... args) {
		RemoteShellTool shellTool = new RemoteShellTool(info.getIp(), info.getLname(), info.getPassword(), charset);
		String command = CommandUtil.generateShellCommand(info.getSdir(), FileUtil.getFileName(type), args);
		return shellTool.execList(command);
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
	public InputStream queryInputStream(DeployInfo info, String command, Map<String, Object> param) {
		RemoteShellTool shellTool = new RemoteShellTool(info.getIp(), info.getLname(), info.getPassword(), charset);
		return shellTool.execWithInputStream(command, param);
	}

	@Override
	protected String excute(DeployInfo info, String command) {
		RemoteShellTool shellTool = new RemoteShellTool(info.getIp(), info.getLname(), info.getPassword(), charset);
		return shellTool.exec(command);
	}
	
}
