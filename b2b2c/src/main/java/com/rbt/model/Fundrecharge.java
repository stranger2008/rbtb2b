/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Fundrecharge.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 会员资金充值记录实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 12 13:10:48 CST 2011
 */
public class Fundrecharge implements Serializable {

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
	
	String payplat;
	public String getPayplat() {
		return payplat;
	}
	public void setPayplat(String payplat) {
		this.payplat = payplat;
	}
	
	
	String pay_date;
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
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
		builder.append("Fundrecharge[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", fund_num=");
		builder.append(this.fund_num);
		builder.append(", payplat=");
		builder.append(this.payplat);
		builder.append(", pay_date=");
		builder.append(this.pay_date);
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

