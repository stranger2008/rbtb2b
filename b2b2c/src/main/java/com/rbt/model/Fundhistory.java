/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Fundhistory.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 会员资金流实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Jul 13 10:06:11 CST 2011
 */
public class Fundhistory implements Serializable {

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
	
	String fund_in;
	public String getFund_in() {
		return fund_in;
	}
	public void setFund_in(String fund_in) {
		this.fund_in = fund_in;
	}
	
	String fund_out;
	public String getFund_out() {
		return fund_out;
	}
	public void setFund_out(String fund_out) {
		this.fund_out = fund_out;
	}
	
	String balance;
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	String reason;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fundhistory[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", fund_in=");
		builder.append(this.fund_in);
		builder.append(", fund_out=");
		builder.append(this.fund_out);
		builder.append(", balance=");
		builder.append(this.balance);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", reason=");
		builder.append(this.reason);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append("]");
		return builder.toString();
	}

}

