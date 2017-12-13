package com.zhicall.op.entity;

import java.util.Date;

import com.zhicall.op.util.DateUtil;

public class DeployJob {

	private Long uuid;
	
	private long id;
	
	private String type;
	
	private Date jobTime;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String result;

	private String email;
	
	private String mobile;
	
	private String remark;
	
	private int flag;
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int isFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJobTime() {
		if (null != jobTime) {
			return DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", jobTime);
		}
		return null;
	}
	
	public Date getJobTimeDate() {
		return jobTime;
	}

	public void setJobTime(Date jobTime) {
		this.jobTime = jobTime;
	}

	public String getCreateTime() {
		if (null != createTime) {
			return DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", createTime);
		}
		return null;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getUpdateTime() {
		if (null != updateTime) {
			return DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", updateTime);
		}
		return null;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
}
