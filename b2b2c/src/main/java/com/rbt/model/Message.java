/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Message.java 
 */
package com.rbt.model;
import java.io.Serializable;
/**
 * @function 功能  网站留言pojo类		
 * @author  创建人 蔡毅存
 * @date  创建日期  July 11, 2011
*/
public class Message  implements Serializable {
	/**
	 * 序列化
	 */
	static final long serialVersionUID = 4354500492693710809L;
	/*
	 * 网站留言唯一标识
	 */
	String mess_id;
	/*
	 * 留言主题
	 */
	String title;
	/*
	 * 留言内容
	 */
	String content;
	/*
	 * 联系人
	 */
	String name;
	
	/*
	 * 联系电话
	 */
	String phone;
	
	/*
	 * 电子邮件
	 */
    String email;
    /*
	 * QQ
	 */
    String qq;
    /*
	 *MSN
	 */
    String msn;
    /*
	 * skype
	 */
    String skype;
    /*
	 * 留言时间
	 */
    String in_date;


	

	public String getMess_id() {
		return mess_id;
	}




	public void setMess_id(String mess_id) {
		this.mess_id = mess_id;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getQq() {
		return qq;
	}




	public void setQq(String qq) {
		this.qq = qq;
	}




	public String getMsn() {
		return msn;
	}




	public void setMsn(String msn) {
		this.msn = msn;
	}




	public String getSkype() {
		return skype;
	}




	public void setSkype(String skype) {
		this.skype = skype;
	}




	public String getIn_date() {
		return in_date;
	}




	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}




	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("message [mess_id=");
		builder.append(this.mess_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", name=");
		builder.append(this.name);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", qq=");
		builder.append(this.qq);
		builder.append(", msn=");
		builder.append(this.msn);
		builder.append(", skype=");
		builder.append(this.skype);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}
	
}
