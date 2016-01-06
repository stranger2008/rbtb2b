/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Emailtemplate.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录会员邮件发送模板信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Jul 15 09:13:20 CST 2011
 */
public class Emailtemplate implements Serializable {

	static final long serialVersionUID = -4301622348425579263L;
	
	String temp_code;
	String sys_temp;
	
	String temp_id;
	public String getTemp_id() {
		return temp_id;
	}
	public void setTemp_id(String temp_id) {
		this.temp_id = temp_id;
	}
	
	String temp_name;
	public String getTemp_name() {
		return temp_name;
	}
	public void setTemp_name(String temp_name) {
		this.temp_name = temp_name;
	}
	
	String temp_con;
	public String getTemp_con() {
		return temp_con;
	}
	public void setTemp_con(String temp_con) {
		this.temp_con = temp_con;
	}
	
	String tag_desc;
	public String getTag_desc() {
		return tag_desc;
	}
	public void setTag_desc(String tag_desc) {
		this.tag_desc = tag_desc;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Emailtemplate[");
		builder.append(", temp_id=");
		builder.append(this.temp_id);
		builder.append(", temp_name=");
		builder.append(this.temp_name);
		builder.append(", temp_con=");
		builder.append(this.temp_con);
		builder.append(", tag_desc=");
		builder.append(this.tag_desc);
		builder.append("]");
		return builder.toString();
	}
	public String getTemp_code() {
		return temp_code;
	}
	public void setTemp_code(String temp_code) {
		this.temp_code = temp_code;
	}
	public String getSys_temp() {
		return sys_temp;
	}
	public void setSys_temp(String sys_temp) {
		this.sys_temp = sys_temp;
	}

}

