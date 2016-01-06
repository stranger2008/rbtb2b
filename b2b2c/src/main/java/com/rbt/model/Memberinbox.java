/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Memberinbox.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 会员收件信息表实体
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Aug 05 14:23:14 CST 2011
 */
public class Memberinbox implements Serializable {

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
	
	String send_cust_id;
	public String getSend_cust_id() {
		return send_cust_id;
	}
	public void setSend_cust_id(String send_cust_id) {
		this.send_cust_id = send_cust_id;
	}
	
	String is_read;
	public String getIs_read() {
		return is_read;
	}
	public void setIs_read(String is_read) {
		this.is_read = is_read;
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
		builder.append("Memberinbox[");
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
		builder.append(", send_cust_id=");
		builder.append(this.send_cust_id);
		builder.append(", is_read=");
		builder.append(this.is_read);
		builder.append(", is_del=");
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}

