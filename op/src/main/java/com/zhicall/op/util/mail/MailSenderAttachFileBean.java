/**
 * 附件
 * 2017-02-07
 */
package com.zhicall.op.util.mail;

import java.util.UUID;

public class MailSenderAttachFileBean {

	/**
	 * 不需要传递
	 */
	private String id;

	/**
	 * 带有文件后缀名（必须传递）
	 */
	private String fileName;

	/**
	 * 需要传递，附件字节码（必须传递）
	 */
	private byte[] fileBytes;

	public MailSenderAttachFileBean() {
		id = UUID.randomUUID().toString().replaceAll("-", "");
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}

	public String getId() {
		return id;
	}
}