package com.zhicall.op.entity;

public class LogFile {

	public LogFile() {
	}
	
	public LogFile(String name, String updateTime) {
		this.name = name;
		this.updateTime = updateTime;
	}
	
	private String name;
	
	private String updateTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
