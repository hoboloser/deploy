/**
 * 邮件信息
 * 2017-02-07
 */
package com.zhicall.op.util.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MailSenderBean {

	/**
	 * 发送方,例:xxx@xxx.com
	 */
	private String fromAddress;

	/**
	 * 发送方密码
	 */
	private String fromPassword;

	/**
	 * smtp服务器
	 */
	private String smtpHost;

	/**
	 * smtp端口
	 */
	private String smtpPort;

	/**
	 * 发送方,例:苏州智康信息科技有限公司-支付项目组
	 */
	private String fromAddressTitle;

	/**
	 * 主题,例:对账单
	 */
	private String subject;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 发送地址
	 */
	private String[] toAddress;

	/**
	 * 抄送地址
	 */
	private String[] ccAddress;

	/**
	 * 密送地址
	 */
	private String[] bccAddress;

	/**
	 * 附件
	 */
	private List<MailSenderAttachFileBean> attachFiles;

	/**
	 * 这个右键中插入的图片，非附件
	 */
	private List<MailSenderAttachFileBean> imageFiles;

	/**
	 * 这个日期基本没意思，邮件上显示的日期 ， 如果要定时发送需利用定时器，可以不填
	 */
	private Date sendDate;

	public MailSenderBean() {
		this.attachFiles = new ArrayList<MailSenderAttachFileBean>();
		this.imageFiles = new ArrayList<MailSenderAttachFileBean>();
	}

	public MailSenderBean(String fromAddress, String fromPassword, String smtpHost, String smtpPort, String fromAddressTitle,
			String subject, String content, String[] toAddress, String[] ccAddress, String[] bccAddress) {
		if (subject == null) {
			throw new IllegalArgumentException("subject is null");
		}
		if (fromAddress != null) {
			this.fromAddress = fromAddress;
			this.fromPassword = fromPassword;
			this.smtpHost = smtpHost;
			this.smtpPort = smtpPort;
			this.fromAddressTitle = fromAddressTitle;
		} else {
			this.fromAddress = MailSenderDefaultBean.fromAddress;
			this.fromPassword = MailSenderDefaultBean.fromPassword;
			this.smtpHost = MailSenderDefaultBean.smtpHost;
			this.smtpPort = MailSenderDefaultBean.smtpPort;
			this.fromAddressTitle = MailSenderDefaultBean.fromAddressTitle;
		}
		this.subject = subject;
		this.content = content;
		this.toAddress = toAddress != null ? toAddress : MailSenderDefaultBean.defaultToAddress;
		this.ccAddress = ccAddress;
		this.bccAddress = bccAddress;
		this.attachFiles = new ArrayList<MailSenderAttachFileBean>();
		this.imageFiles = new ArrayList<MailSenderAttachFileBean>();
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getFromAddressTitle() {
		return fromAddressTitle;
	}

	public void setFromAddressTitle(String fromAddressTitle) {
		this.fromAddressTitle = fromAddressTitle;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getToAddress() {
		return toAddress;
	}

	public void setToAddress(String[] toAddress) {
		this.toAddress = toAddress;
	}

	public String[] getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String[] ccAddress) {
		this.ccAddress = ccAddress;
	}

	public String[] getBccAddress() {
		return bccAddress;
	}

	public void setBccAddress(String[] bccAddress) {
		this.bccAddress = bccAddress;
	}

	public String getFromPassword() {
		return fromPassword;
	}

	public void setFromPassword(String fromPassword) {
		this.fromPassword = fromPassword;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public List<MailSenderAttachFileBean> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(List<MailSenderAttachFileBean> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public List<MailSenderAttachFileBean> getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(List<MailSenderAttachFileBean> imageFiles) {
		this.imageFiles = imageFiles;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
}