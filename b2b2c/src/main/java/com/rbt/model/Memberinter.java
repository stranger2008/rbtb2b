/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Memberinter.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录会员积分数实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 14 14:30:38 CST 2011
 */
public class Memberinter implements Serializable {

	static final long serialVersionUID = 1L;
	
	
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
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memberinter[");
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", fund_num=");
		builder.append(this.fund_num);
		builder.append("]");
		return builder.toString();
	}

}

