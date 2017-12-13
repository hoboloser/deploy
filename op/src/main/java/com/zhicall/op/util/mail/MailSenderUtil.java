/**
 * 发送邮件
 * 2017-02-07
 */
package com.zhicall.op.util.mail;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

public class MailSenderUtil {

	private static Session session;// session 没有子类，可以被共享,来自javax.mail包

	/**
	 * 用服务器以及发送者信息构建Session
	 * 
	 * @return
	 */
	public static Session getSession(final String fromAddress, final String password, String smtpHost, String smtpPort) {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", smtpPort);
		session = Session.getInstance(props, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromAddress, password);
			}
		});
		return session;
	}

	public static void send(MailSenderBean mailSenderBean) throws Exception {
		String fromAddress = mailSenderBean.getFromAddress();
		String password = mailSenderBean.getFromPassword();
		String smtpHost = mailSenderBean.getSmtpHost();
		String smtpPort = mailSenderBean.getSmtpPort();
		String fromAddressTitle = mailSenderBean.getFromAddressTitle();
		String subject = mailSenderBean.getSubject();
		String content = mailSenderBean.getContent();
		Session session = getSession(fromAddress, password, smtpHost, smtpPort);
		session.setDebug(true);// 设置调试级别
		String fromAddressNick = MimeUtility.encodeText(fromAddressTitle);
		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress(fromAddressNick + "<" + fromAddress + ">"));
		for (String receiver : mailSenderBean.getToAddress()) {
			InternetAddress toAddress = new InternetAddress(receiver);// 收件人
			mimeMessage.addRecipient(Message.RecipientType.TO, toAddress);// 加收件人
		}
		String[] ccAddresses = mailSenderBean.getCcAddress();
		if (ccAddresses != null) {
			for (String cc : ccAddresses) {
				InternetAddress ccAddress = new InternetAddress(cc);
				mimeMessage.addRecipient(Message.RecipientType.CC, ccAddress);
			}
		}
		String[] bccAddresses = mailSenderBean.getBccAddress();
		if (bccAddresses != null) {
			for (String bcc : bccAddresses) {
				InternetAddress bccAddress = new InternetAddress(bcc);
				mimeMessage.addRecipient(Message.RecipientType.BCC, bccAddress);
			}
		}
		MimeMultipart multipart = new MimeMultipart();
		MimeBodyPart attachPart = null;
		List<MailSenderAttachFileBean> imageFiles = mailSenderBean.getImageFiles();
		if (imageFiles != null && imageFiles.size() > 0) {
			for (MailSenderAttachFileBean imageFileBean : imageFiles) {
				attachPart = createContent(content, imageFileBean);
				multipart.addBodyPart(attachPart);
			}
		} else {
			multipart.addBodyPart(createContent(content));
		}

		mimeMessage.setContent(multipart);
		mimeMessage.setSubject(subject);
		Date sendDate = mailSenderBean.getSendDate();
		if (sendDate != null) {
			mimeMessage.setSentDate(sendDate);
		}
		mimeMessage.saveChanges();
		Transport.send(mimeMessage);
	}

	/**
	 * 根据传入的邮件正文body和文件路径创建图文并茂的正文部分
	 */
	public static MimeBodyPart createContent(String body, MailSenderAttachFileBean attachFileBean) throws Exception {
		// 用于保存最终正文部分
		MimeBodyPart contentBody = new MimeBodyPart();
		// 用于组合文本和图片，"related"型的MimeMultipart对象
		MimeMultipart contentMulti = new MimeMultipart("related");
		// 正文的图片部分
//		if (attachFileBean != null) {
//			String fileName = attachFileBean.getFileName();
//			String contentId = attachFileBean.getId();
//			byte[] fileBytes = attachFileBean.getFileBytes();
//			MimeBodyPart jpgBody = createAttachment(fileBytes, fileName, contentId);
//			contentMulti.addBodyPart(jpgBody);
//		}
		// 正文的文本部分
		contentMulti.addBodyPart(createContent(body));
		// 将上面"related"型的 MimeMultipart 对象作为邮件的正文
		contentBody.setContent(contentMulti);
		return contentBody;
	}

	public static MimeBodyPart createContent(String body) throws Exception {
		// 正文的文本部分
		MimeBodyPart textBody = new MimeBodyPart();
		textBody.setContent(body, "text/html;charset=UTF-8");
		return textBody;
	}

}