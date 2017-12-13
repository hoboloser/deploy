package com.zhicall.op.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhicall.op.entity.DeployInfo;

@Service("deployServiceFactory")
public class DeployServiceFactory{

	@Value("${op.module.type}")
	private String opModule;

	@Autowired
	private DeployService deployService;
	
	@Autowired
	private DeployService deploySingleService;
	
	@Autowired
	private DeployService deployRemoteWindowsSingleService;
	
	@Autowired
	private DeployService deployWindowsSingleService;
	
	@Autowired
	private OpConfigService opConfigService;
	
	public DeployService getServiceInstance(String uuid) {
		DeployInfo deployInfo = opConfigService.getForObject(uuid);
		if ("SINGLE".equals(opModule)) {
			
			return deploySingleService;
			
		} else if ("W_SINGLE".equals(opModule)) {
			
			return deployWindowsSingleService;
			
		} else if ("PROTOTYPE".equals(opModule)) {
			
			return deployService;
			
		} else if ("W_PROTOTYPE".equals(opModule)) {
			
			return deployRemoteWindowsSingleService;
			
		} else {
			return deployService;
		}
	}
}
