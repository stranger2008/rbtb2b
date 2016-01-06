/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Memberleave.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录会员留言信息表实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Jul 25 08:40:47 CST 2011
 */
public class Memberleave implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String leave_id;
	public String getLeave_id() {
		return leave_id;
	}
	public void setLeave_id(String leave_id) {
		this.leave_id = leave_id;
	}
	
	String send_cust_id;
	public String getSend_cust_id() {
		return send_cust_id;
	}
	public void setSend_cust_id(String send_cust_id) {
		this.send_cust_id = send_cust_id;
	}
	
	String send_user_name;
	public String getSend_user_name() {
		return send_user_name;
	}
	public void setSend_user_name(String send_user_name) {
		this.send_user_name = send_user_name;
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
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	String get_cust_id;
	public String getGet_cust_id() {
		return get_cust_id;
	}
	public void setGet_cust_id(String get_cust_id) {
		this.get_cust_id = get_cust_id;
	}
	
	String source;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	String is_del;
	public String getIs_del() {
		return is_del;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memberleave[");
		builder.append(", leave_id=");
		builder.append(this.leave_id);
		builder.append(", send_cust_id=");
		builder.append(this.send_cust_id);
		builder.append(", send_user_name=");
		builder.append(this.send_user_name);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", get_cust_id=");
		builder.append(this.get_cust_id);
		builder.append(", source=");
		builder.append(this.source);
		builder.append(", is_del=");
		builder.append(this.is_del);
		builder.append("]");
		return builder.toString(); 
	}

}

