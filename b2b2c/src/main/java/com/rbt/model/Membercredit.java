/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Membercredit.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录会员信用指数实体
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Nov 30 13:37:20 CST 2011
 */
public class Membercredit implements Serializable {

	static final long serialVersionUID = 1L;
	
	
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
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Membercredit[");
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", c_num=");
		builder.append(this.c_num);
		builder.append("]");
		return builder.toString();
	}

}

