/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Resumeinbox.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 简历收件箱表实体
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 01 09:17:11 CST 2011
 */
public class Resumeinbox implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String inbox_id;
	public String getInbox_id() {
		return inbox_id;
	}
	public void setInbox_id(String inbox_id) {
		this.inbox_id = inbox_id;
	}
	
	String get_cust_id;
	public String getGet_cust_id() {
		return get_cust_id;
	}
	public void setGet_cust_id(String get_cust_id) {
		this.get_cust_id = get_cust_id;
	}
	
	String job_id;
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	
	String resume_id;
	public String getResume_id() {
		return resume_id;
	}
	public void setResume_id(String resume_id) {
		this.resume_id = resume_id;
	}
	
	String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	String isinvite;
	public String getIsinvite() {
		return isinvite;
	}
	public void setIsinvite(String isinvite) {
		this.isinvite = isinvite;
	}
	
	String apply_date;
	public String getApply_date() {
		return apply_date;
	}
	public void setApply_date(String apply_date) {
		this.apply_date = apply_date;
	}
	
	String apply_cust_id;
	public String getApply_cust_id() {
		return apply_cust_id;
	}
	public void setApply_cust_id(String apply_cust_id) {
		this.apply_cust_id = apply_cust_id;
	}
	String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	String resume_name;
	
	public String getResume_name() {
		return resume_name;
	}
	public void setResume_name(String resume_name) {
		this.resume_name = resume_name;
	}
	String user_name;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Resumeinbox[");
		builder.append(", inbox_id=");
		builder.append(this.inbox_id);
		builder.append(", get_cust_id=");
		builder.append(this.get_cust_id);
		builder.append(", job_id=");
		builder.append(this.job_id);
		builder.append(", resume_id=");
		builder.append(this.resume_id);
		builder.append(", state=");
		builder.append(this.state);
		builder.append(", isinvite=");
		builder.append(this.isinvite);
		builder.append(", apply_date=");
		builder.append(this.apply_date);
		builder.append(", apply_cust_id=");
		builder.append(this.apply_cust_id);
		builder.append("]");
		return builder.toString();
	}

}

