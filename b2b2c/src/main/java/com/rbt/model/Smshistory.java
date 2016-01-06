/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Smshistory.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录短信发送历史记录实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 19 16:23:30 CST 2011
 */
public class Smshistory implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	String phoneattr;
	public String getPhoneattr() {
		return phoneattr;
	}
	public void setPhoneattr(String phoneattr) {
		this.phoneattr = phoneattr;
	}
	
	String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
		builder.append("Smshistory[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", phoneattr=");
		builder.append(this.phoneattr);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", send_date=");
		builder.append(this.send_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append("]");
		return builder.toString();
	}

}

