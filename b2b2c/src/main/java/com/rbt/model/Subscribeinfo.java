/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Subscribeinfo.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录会员商机订阅信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 21 14:04:42 CST 2011
 */
public class Subscribeinfo implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String send_id;
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String sub_id;
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	
	String info_type;
	public String getInfo_type() {
		return info_type;
	}
	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}
	
	String info_attr;
	public String getInfo_attr() {
		return info_attr;
	}
	public void setInfo_attr(String info_attr) {
		this.info_attr = info_attr;
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
		builder.append("Subscribeinfo[");
		builder.append(", send_id=");
		builder.append(this.send_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", sub_id=");
		builder.append(this.sub_id);
		builder.append(", info_type=");
		builder.append(this.info_type);
		builder.append(", info_attr=");
		builder.append(this.info_attr);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}

