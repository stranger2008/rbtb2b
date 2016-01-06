/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Audithistory.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录模块审核历史实体
 * @author 创建人 林俊钦
 * @date 创建日期 Tue Nov 15 10:35:16 CST 2011
 */
public class Audithistory implements Serializable {

	static final long serialVersionUID = 1L;
	
	String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	String info_id;
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	
	String module_type;
	public String getModule_type() {
		return module_type;
	}
	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	
	String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	String user_name;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	String no_reason;
	public String getNo_reason() {
		return no_reason;
	}
	public void setNo_reason(String no_reason) {
		this.no_reason = no_reason;
	}
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Audithistory[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", info_id=");
		builder.append(this.info_id);
		builder.append(", module_type=");
		builder.append(this.module_type);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", user_name=");
		builder.append(this.user_name);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", no_reason=");
		builder.append(this.no_reason);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append("]");
		return builder.toString();
	}
	
	public Audithistory(){
		
	}
	
	public Audithistory(String info_id, String module_type, String user_id,
			String user_name, String info_state, String no_reason,
			String cust_id) {
		super();
		this.info_id = info_id;
		this.module_type = module_type;
		this.user_id = user_id;
		this.user_name = user_name;
		this.info_state = info_state;
		this.no_reason = no_reason;
		this.cust_id = cust_id;
	}
}

