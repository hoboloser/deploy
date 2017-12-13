package com.zhicall.op.enums;

public enum WebAccountType {

	USER_ADMIN("用户管理员"),
	ADMIN("管理员");

	private final String name;

	private WebAccountType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
