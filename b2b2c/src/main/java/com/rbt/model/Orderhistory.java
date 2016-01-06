/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Orderhistory.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 订单状态历史记录实体
 * @author 创建人 订单历史
 * @date 创建日期 Tue Nov 01 13:15:49 CST 2011
 */
public class Orderhistory implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String info_id;
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	
	String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	String action_name;
	public String getAction_name() {
		return action_name;
	}
	public void setAction_name(String action_name) {
		this.action_name = action_name;
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
		builder.append("Orderhistory[");
		builder.append(", info_id=");
		builder.append(this.info_id);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", action_name=");
		builder.append(this.action_name);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}

