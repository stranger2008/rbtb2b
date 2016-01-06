/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Membersendbox.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录发件箱的发送信息实体
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Sep 28 21:32:25 CST 2011
 */
public class Membersendbox implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String info_id;
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String mess_type;
	public String getMess_type() {
		return mess_type;
	}
	public void setMess_type(String mess_type) {
		this.mess_type = mess_type;
	}
	
	String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	String get_cust_id;
	public String getGet_cust_id() {
		return get_cust_id;
	}
	public void setGet_cust_id(String get_cust_id) {
		this.get_cust_id = get_cust_id;
	}
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Membersendbox[");
		builder.append(", info_id=");
		builder.append(this.info_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", mess_type=");
		builder.append(this.mess_type);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", get_cust_id=");
		builder.append(this.get_cust_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}

