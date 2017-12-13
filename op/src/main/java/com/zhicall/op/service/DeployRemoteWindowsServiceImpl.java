package com.zhicall.op.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zhicall.op.entity.DeployInfo;
import com.zhicall.op.entity.LogFile;
import com.zhicall.op.enums.BussinessType;

@Service("deployRemoteWindowsSingleService")
public class DeployRemoteWindowsServiceImpl extends AbstractDeployService  implements DeployService {

	@Override
	protected String excuteCommand(DeployInfo info, BussinessType type, String... args) {
		return null;
	}

	@Override
	protected String excute(DeployInfo info, String command) {
		return null;
	}

	@Override
	protected List<LogFile> excuteCommandList(DeployInfo info, BussinessType type, String... args) {
		return null;
	}

	@Override
	public InputStream queryInputStream(DeployInfo info, String command, Map<String, Object> param) {
		return null;
	}

}
