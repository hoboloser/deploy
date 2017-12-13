package com.zhicall.op.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhicall.op.enums.ValidFlag;
import com.zhicall.op.enums.WebAccountType;

public class WebAccount implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	/**
	 *登录名
	 */
	private String login;
	
	/**
	 *密码(MD5)
	 */
	@JSONField(serialize=false)
	private String password;
	
	/**
	 *用户名
	 */
	private String name;
	
	/**
	 *头像id
	 */
	private String avatarFileId;
	
	/**
	 *手机号
	 */
	private String mobile;
	
	/**
	 *邮箱
	 */
	private String email;
	
	/**
	 *标志位(是否删除)
	 */
	private ValidFlag validFlag;
	
	/**
	 *注册时间
	 */
	private Date createTime;
	
	/**
	 *登陆次数
	 */
	private Integer loginCount;
	
	/**
	 *上次登录时间
	 */
	private Date lastLoginTime;

	/**
	 * 账号类型(非数据库字段)
	 */
	private List<WebAccountType> accountTypes;
	
	/**
	 * setter方法
	 */
	public void setId(Long id){
		this.id = id;
	}
	
	/**
	 * getter方法
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 *登录名 setter方法
	 */
	public void setLogin(String login){
		this.login = login;
	}
	
	/**
	 *登录名 getter方法
	 */
	public String getLogin(){
		return login;
	}
	
	/**
	 *密码(MD5) setter方法
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 *密码(MD5) getter方法
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 *用户名 setter方法
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 *用户名 getter方法
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * setter方法
	 */
	public void setAvatarFileId(String avatarFileId){
		this.avatarFileId = avatarFileId;
	}
	
	/**
	 * getter方法
	 */
	public String getAvatarFileId(){
		return avatarFileId;
	}
	
	/**
	 *手机号 setter方法
	 */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	/**
	 *手机号 getter方法
	 */
	public String getMobile(){
		return mobile;
	}
	
	/**
	 *邮箱 setter方法
	 */
	public void setEmail(String email){
		this.email = email;
	}
	
	/**
	 *邮箱 getter方法
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 *标志位(是否删除) setter方法
	 */
	public void setValidFlag(ValidFlag validFlag){
		this.validFlag = validFlag;
	}
	
	/**
	 *标志位(是否删除) getter方法
	 */
	public ValidFlag getValidFlag(){
		return validFlag;
	}
	
	/**
	 *注册时间 setter方法
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	/**
	 *注册时间 getter方法
	 */
	public Date getCreateTime(){
		return createTime;
	}
	
	/**
	 *登陆次数 setter方法
	 */
	public void setLoginCount(Integer loginCount){
		this.loginCount = loginCount;
	}
	
	/**
	 *登陆次数 getter方法
	 */
	public Integer getLoginCount(){
		return loginCount;
	}
	
	/**
	 *上次登录时间 setter方法
	 */
	public void setLastLoginTime(Date lastLoginTime){
		this.lastLoginTime = lastLoginTime;
	}
	
	/**
	 *上次登录时间 getter方法
	 */
	public Date getLastLoginTime(){
		return lastLoginTime;
	}

	public List<WebAccountType> getAccountTypes() {
		return accountTypes;
	}

	public void setAccountTypes(List<WebAccountType> accountTypes) {
		this.accountTypes = accountTypes;
	}

}
