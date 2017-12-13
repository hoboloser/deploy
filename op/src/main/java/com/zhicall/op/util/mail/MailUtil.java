package com.zhicall.op.util.mail;

import java.util.Date;

public class MailUtil {
	
	private static MailUtil instance = new MailUtil();
	
	public static MailUtil getInstance() {
		return instance;
	}
	/**
	 * 发送告警邮件
	 * 
	 * @param message
	 * @param monitorDate
	 */
	public void sendAlertMail(String message, String monitorDate,String[] toAddress) {
		MailSenderBean mailSenderBean = new MailSenderBean();
		// 发送人
		String fromAddress = "";
		mailSenderBean.setFromAddress(fromAddress);
		// 标题
		mailSenderBean.setFromAddressTitle("");
		// 发送方邮箱密码
		mailSenderBean.setFromPassword("");
		mailSenderBean.setSmtpHost("smtp.exmail.qq.com");
		mailSenderBean.setSmtpPort("25");
		// 主题
		mailSenderBean.setSubject("自动部署");
		if (null != toAddress) {
			mailSenderBean.setToAddress(toAddress);
		}

		StringBuffer theMessage = new StringBuffer();
		theMessage.append("<p>管理员，你好：<p><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目自动部署结果<p>");
		theMessage.append("<p>监测内容：" + message + "</p>");
		theMessage.append("<p>监测时间：" + monitorDate + "</p>");
		theMessage.append("<p>自动部署</p>");

		mailSenderBean.setContent(theMessage.toString());
		try {
			MailSenderUtil.send(mailSenderBean);
		} catch (Exception e) {
		}
	}
	
	@SuppressWarnings("unused")
	private class AlertMessage {

		private String systemCode; // 告警系统编号

		private int alertLevel; // 告警级别 （1-5，等级逐级提高）

		private int alertCategory; // 告警类别（1：业务告警 2：系统告警 3：...）

		private String alertMessage; // 告警信息

		private Date createTime; // 告警时间

		private boolean isNeedSMS; // 是否需要短信告警

		private boolean isNeedMail; // 是否需要邮件告警

		public AlertMessage(int alertLevel, int alertCategory, String alertMessage, boolean isNeedSMS, boolean isNeedMail) {
			this.alertLevel = alertLevel;
			this.alertCategory = alertCategory;
			this.alertMessage = alertMessage;
			this.isNeedSMS = isNeedSMS;
			this.isNeedMail = isNeedMail;
		}

		public void setSystemCode(String systemCode) {
			this.systemCode = systemCode;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getSystemCode() {
			return systemCode;
		}

		public int getAlertLevel() {
			return alertLevel;
		}

		public int getAlertCategory() {
			return alertCategory;
		}

		public String getAlertMessage() {
			return alertMessage;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public boolean isNeedSMS() {
			return isNeedSMS;
		}

		public boolean isNeedMail() {
			return isNeedMail;
		}
	}
}
