/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Jobtalent.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 人才库表实体
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 01 10:39:25 CST 2011
 */
public class Jobtalent implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String inbox_id;
	public String getInbox_id() {
		return inbox_id;
	}
	public void setInbox_id(String inbox_id) {
		this.inbox_id = inbox_id;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String resume_id;
	public String getResume_id() {
		return resume_id;
	}
	public void setResume_id(String resume_id) {
		this.resume_id = resume_id;
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
		builder.append("Jobtalent[");
		builder.append(", inbox_id=");
		builder.append(this.inbox_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", resume_id=");
		builder.append(this.resume_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}

