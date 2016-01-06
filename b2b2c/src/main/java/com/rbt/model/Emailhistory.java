/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Emailhistory.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录邮件发送历史记录实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Jul 15 09:47:57 CST 2011
 */
public class Emailhistory implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	String get_email;
	public String getGet_email() {
		return get_email;
	}
	public void setGet_email(String get_email) {
		this.get_email = get_email;
	}
	
	String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	String send_email;
	public String getSend_email() {
		return send_email;
	}
	public void setSend_email(String send_email) {
		this.send_email = send_email;
	}
	
	String send_name;
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	
	String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	String temp_id;
	public String getTemp_id() {
		return temp_id;
	}
	public void setTemp_id(String temp_id) {
		this.temp_id = temp_id;
	}
	
	String send_date;
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	
	String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Emailhistory[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", get_email=");
		builder.append(this.get_email);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", send_email=");
		builder.append(this.send_email);
		builder.append(", send_name=");
		builder.append(this.send_name);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", temp_id=");
		builder.append(this.temp_id);
		builder.append(", send_date=");
		builder.append(this.send_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append("]");
		return builder.toString();
	}

}

