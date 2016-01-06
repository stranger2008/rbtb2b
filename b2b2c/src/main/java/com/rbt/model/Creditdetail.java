/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Creditdetail.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 会员信用指数明细实体
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Dec 08 20:44:27 CST 2011
 */
public class Creditdetail implements Serializable {

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
	
	String r_type;
	public String getR_type() {
		return r_type;
	}
	public void setR_type(String r_type) {
		this.r_type = r_type;
	}
	
	String r_name;
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	
	String r_num;
	public String getR_num() {
		return r_num;
	}
	public void setR_num(String r_num) {
		this.r_num = r_num;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Creditdetail[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", r_type=");
		builder.append(this.r_type);
		builder.append(", r_name=");
		builder.append(this.r_name);
		builder.append(", r_num=");
		builder.append(this.r_num);
		builder.append("]");
		return builder.toString();
	}

}

