package com.zhicall.op.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhicall.op.entity.DeployInfo;
import com.zhicall.op.entity.DeployJob;
import com.zhicall.op.mapper.DeployJobMapper;
import com.zhicall.op.mapper.DeployMapper;

@Service("opConfigService")
public class OpConfigServiceImpl implements OpConfigService{

	@Autowired
	private DeployMapper deployMapper;
	@Autowired
	private DeployJobMapper deployJobMapper;
	
	@Override
	public List<DeployInfo> listConfigs() {
		return deployMapper.getList();
	}

	@Override
	public DeployInfo getForObject(String id) {
		return deployMapper.getDetail(id);
	}

	@Override
	public List queryJob(String uuid) {
		if (uuid == null) {
			return deployJobMapper.getAllList();
		} else {
			return deployJobMapper.getList(uuid);
		}
	}

	@Override
	public void insertJob(DeployJob deployJob) {
		deployJob.setCreateTime(new Date());
		deployJobMapper.insert(deployJob);
	}

	@Override
	public void insert(DeployInfo deployInfo) {
		deployMapper.insert(deployInfo);
	}

	@Override
	public void delete(String id) {
		deployMapper.delete(id);
	}

	@Override
	public void update(DeployInfo deployInfo) {
		deployMapper.update(deployInfo);
	}

	
}
