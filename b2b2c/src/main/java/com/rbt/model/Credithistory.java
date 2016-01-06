/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Credithistory.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录会员信用指数历史实体
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Nov 30 13:35:49 CST 2011
 */
public class Credithistory implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String c_num;
	public String getC_num() {
		return c_num;
	}
	public void setC_num(String c_num) {
		this.c_num = c_num;
	}
	
	String r_type;
	public String getR_type() {
		return r_type;
	}
	public void setR_type(String r_type) {
		this.r_type = r_type;
	}
	
	String reason;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	String now_num;
	public String getNow_num() {
		return now_num;
	}
	public void setNow_num(String now_num) {
		this.now_num = now_num;
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
		builder.append("Credithistory[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", c_num=");
		builder.append(this.c_num);
		builder.append(", r_type=");
		builder.append(this.r_type);
		builder.append(", reason=");
		builder.append(this.reason);
		builder.append(", now_num=");
		builder.append(this.now_num);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}

