/**
 * 邮件发送默认信息
 * 2017-02-07
 */
package com.zhicall.op.util.mail;

import java.io.FileInputStream;
import java.util.Properties;

public class MailSenderDefaultBean {

	public static final String SMTP_HOST_KEY = "mail.smtp.host";

	public static final String SMTP_PORT_KEY = "mail.smtp.port";

	public static final String SMTP_FROM_ADDR_KEY = "mail.smtp.fromAddr";

	public static final String SMTP_FROM_ADDR_PWD_KEY = "mail.smtp.fromAddr.pwd";

	public static final String SMTP_FROM_ADDR_NICK_KEY = "mail.smtp.fromAddrNick";

	public static final String SMTP_DEFAULT_TO_ADD_KEY = "mail.smtp.defaultToAddr";

	/**
	 * 发送方,例:xxx@xxx.com
	 */
	public static String fromAddress;

	/**
	 * 发送方密码
	 */
	public static String fromPassword;

	/**
	 * smtp服务器
	 */
	public static String smtpHost;

	/**
	 * smtp端口
	 */
	public static String smtpPort;

	/**
	 * 发送方,例:苏州智康信息科技股份有限公司-掌医项目组
	 */
	public static String fromAddressTitle;

	/**
	 * 默认发送给
	 */
	public static String[] defaultToAddress;

	static {
		try {
			Properties props = new Properties();
			FileInputStream fis;
			fis = new FileInputStream(System.getenv("ZHICALL_CONFIG") + "/pay/zhicall_mail.properties");
			props.load(fis);
			fis.close();
			fromAddress = props.getProperty(SMTP_FROM_ADDR_KEY);
			fromPassword = props.getProperty(SMTP_FROM_ADDR_PWD_KEY);
			smtpHost = props.getProperty(SMTP_HOST_KEY);
			smtpPort = props.getProperty(SMTP_PORT_KEY);
			fromAddressTitle = props.getProperty(SMTP_FROM_ADDR_NICK_KEY);
			defaultToAddress = props.getProperty(SMTP_DEFAULT_TO_ADD_KEY).split(",|;");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}