package com.zhicall.op.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("deployServiceFactory")
public class DeployServiceFactory{

	@Value("${op.module.type}")
	private String opModule;

	@Autowired
	private DeployService deployService;
	
	@Autowired
	private DeployService deploySingleService;
	
	public DeployService getServiceInstance() {
		if ("SINGLE".equals(opModule)) {
			return deploySingleService;
		} else {
			return deployService;
		}
	}
}
