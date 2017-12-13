package com.zhicall.op.entity;

import java.io.Serializable;
import java.util.Date;

import com.zhicall.op.enums.WebAccountType;

public class WebAccountTypeMapper implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	/**
	 *账号ID
	 */
	private Long accountId;
	
	/**
	 *账号类型
	 */
	private WebAccountType accountType;
	
	/**
	 *
	 */
	private Date createTime;
	
	/**
	 *账号ID setter方法
	 */
	public void setAccountId(Long accountId){
		this.accountId = accountId;
	}
	
	/**
	 *账号ID getter方法
	 */
	public Long getAccountId(){
		return accountId;
	}
	
	/**
	 *账号类型 setter方法
	 */
	public void setAccountType(WebAccountType accountType){
		this.accountType = accountType;
	}
	
	/**
	 *账号类型 getter方法
	 */
	public WebAccountType getAccountType(){
		return accountType;
	}
	
	/**
	 * setter方法
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	/**
	 * getter方法
	 */
	public Date getCreateTime(){
		return createTime;
	}
	
	
	
}
