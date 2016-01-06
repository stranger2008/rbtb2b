package com.rbt.common.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.rbt.function.SysconfigFuc;

public class MailUtil {

	private String fromMailAddr, smtp, user_name, passwd;
	private int port;

	public MailUtil() {
		fromMailAddr = SysconfigFuc.getSysValue("cfg_smtp_usermail");
		smtp = SysconfigFuc.getSysValue("cfg_smtp_server");
		user_name = SysconfigFuc.getSysValue("cfg_smtp_user");
		passwd = SysconfigFuc.getSysValue("cfg_smtp_password");
		port = Integer.parseInt(SysconfigFuc.getSysValue("cfg_smtp_port"));
	}

	public static void main(String[] args) {

		new MailUtil().sendBatchMail("111", "111", "947069540@qq.com");

	}

	/*
	 * 功能：发送邮件 title：邮件标题 content：邮件正文 toMailAddr：接收邮件人地址 mailType：txt：文本邮件
	 * html：网页格式邮件
	 */
	public void sendMail(String title, String content, String toMailAddr,
			String mailType) {

		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		Message msg = new MimeMessage(session);
		try {

			// 发送的邮箱地址
			msg.setFrom(new InternetAddress(fromMailAddr));
			msg.setSubject(title);
			// mailType txt或为空：文本邮件 html：网页格式邮件
			if (mailType.equals("html")) {
				msg.setContent(content, "text/html;charset=UTF-8;");
			} else {
				msg.setText(content);
			}
			Transport transport = session.getTransport();
			// 设置服务器以及账号和密码
			transport.connect(smtp, port, user_name, passwd);
			// 发送到的邮箱地址
			transport.sendMessage(msg, new Address[] { new InternetAddress(
					toMailAddr) });
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	// 发送HTML邮件
	public void sendMail(String title, String content, String toMailAddr) {
		sendMail(title, content, toMailAddr, "html");
	}

	// 批量发送邮件
	/*
	 * toMailAddr：接收邮件地址,以,号隔开
	 */
	public void sendBatchMail(String title, String content, String toMailAddr) {
		// 创建session
		Properties props = new Properties();
		props.put("mail.smtp.host", smtp);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props,
		new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user_name, passwd);
			}
		}
		);
		// 创建Message
		Message msg = new MimeMessage(session);
		
		try {
			msg.setFrom(new InternetAddress(fromMailAddr));
			msg.setSubject(title);
			msg.setContent(content,"text/html;charset=UTF-8");
			Transport.send(msg,InternetAddress.parse(toMailAddr));
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		System.out.println("邮件发送成功");
	}

	public String getFromMailAddr() {
		return fromMailAddr;
	}

	public void setFromMailAddr(String fromMailAddr) {
		this.fromMailAddr = fromMailAddr;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
