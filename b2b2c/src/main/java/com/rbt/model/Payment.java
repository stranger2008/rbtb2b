/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Payment.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录平台支付方式信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Oct 24 10:57:44 CST 2011
 */
public class Payment implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String pay_id;
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	
	String pay_code;
	public String getPay_code() {
		return pay_code;
	}
	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}
	
	String pay_name;
	public String getPay_name() {
		return pay_name;
	}
	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}
	
	String pay_desc;
	public String getPay_desc() {
		return pay_desc;
	}
	public void setPay_desc(String pay_desc) {
		this.pay_desc = pay_desc;
	}
	
	String seller_name;
	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	
	String pay_account;
	public String getPay_account() {
		return pay_account;
	}
	public void setPay_account(String pay_account) {
		this.pay_account = pay_account;
	}
	
	String passwd;
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	String hand_fare;
	public String getHand_fare() {
		return hand_fare;
	}
	public void setHand_fare(String hand_fare) {
		this.hand_fare = hand_fare;
	}
	
	String enabled;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Payment[");
		builder.append(", pay_id=");
		builder.append(this.pay_id);
		builder.append(", pay_code=");
		builder.append(this.pay_code);
		builder.append(", seller_name=");
		builder.append(this.seller_name);
		builder.append(", pay_name=");
		builder.append(this.pay_name);
		builder.append(", pay_desc=");
		builder.append(this.pay_desc);
		builder.append(", pay_account=");
		builder.append(this.pay_account);
		builder.append(", passwd=");
		builder.append(this.passwd);
		builder.append(", hand_fare=");
		builder.append(this.hand_fare);
		builder.append(", enabled=");
		builder.append(this.enabled);
		builder.append("]");
		return builder.toString();
	}

}

