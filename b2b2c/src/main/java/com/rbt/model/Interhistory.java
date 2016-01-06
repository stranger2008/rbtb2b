/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Interhistory.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录会员积分异动历史实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 14 15:01:09 CST 2011
 */
public class Interhistory implements Serializable {

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
	
	String inter_in;
	public String getInter_in() {
		return inter_in;
	}
	public void setInter_in(String inter_in) {
		this.inter_in = inter_in;
	}
	
	String inter_out;
	public String getInter_out() {
		return inter_out;
	}
	public void setInter_out(String inter_out) {
		this.inter_out = inter_out;
	}
	
	String thisinter;
	public String getThisinter() {
		return thisinter;
	}
	public void setThisinter(String thisinter) {
		this.thisinter = thisinter;
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
	
	String plat_type;
	public String getPlat_type() {
		return plat_type;
	}
	public void setPlat_type(String plat_type) {
		this.plat_type = plat_type;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Interhistory[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", inter_in=");
		builder.append(this.inter_in);
		builder.append(", inter_out=");
		builder.append(this.inter_out);
		builder.append(", thisinter=");
		builder.append(this.thisinter);
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

