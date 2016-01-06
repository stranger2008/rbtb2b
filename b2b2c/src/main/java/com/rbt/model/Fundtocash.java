/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Fundtocash.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 会员资金提现记录实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Jul 13 09:52:27 CST 2011
 */
public class Fundtocash implements Serializable {

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
	
	String fund_num;
	public String getFund_num() {
		return fund_num;
	}
	public void setFund_num(String fund_num) {
		this.fund_num = fund_num;
	}
	
	String getcash_type;
	public String getGetcash_type() {
		return getcash_type;
	}
	public void setGetcash_type(String getcash_type) {
		this.getcash_type = getcash_type;
	}
	
	String account;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	String account_name;
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
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
	
	String order_state;
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
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
		builder.append("Fundtocash[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", fund_num=");
		builder.append(this.fund_num);
		builder.append(", getcash_type=");
		builder.append(this.getcash_type);
		builder.append(", account=");
		builder.append(this.account);
		builder.append(", account_name=");
		builder.append(this.account_name);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", order_state=");
		builder.append(this.order_state);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append("]");
		return builder.toString();
	}

}

