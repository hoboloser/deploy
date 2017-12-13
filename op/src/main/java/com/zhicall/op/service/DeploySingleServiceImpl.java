package com.zhicall.op.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.zhicall.op.entity.Cmd;

@Service("deploySingleService")
public class DeploySingleServiceImpl  implements DeployService {

	@Override
	public String kill(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String start(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String restart(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deploy(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String status(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String systemInfo(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cmd excute(String uuid, String string, String lastPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List showFile(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

@Override
public InputStream getInputStream(String type, String uuid, String file, String cmd,
		Map<String, Object> returnMap) {
	// TODO Auto-generated method stub
	return null;
}
}
