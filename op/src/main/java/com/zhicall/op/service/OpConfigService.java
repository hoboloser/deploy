package com.zhicall.op.service;

import java.util.List;

import com.zhicall.op.entity.DeployInfo;
import com.zhicall.op.entity.DeployJob;

public interface OpConfigService {

	List<DeployInfo> listConfigs();
	
	DeployInfo getForObject(String id);

	List queryJob(String uuid);

	void insertJob(DeployJob deployJob);

	void insert(DeployInfo deployInfo);

	void delete(String id);

	void update(DeployInfo deployInfo);
	
}
