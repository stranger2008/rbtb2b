/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Memberfund.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 会员资金实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 12 09:26:58 CST 2011
 */
public class Memberfund implements Serializable {

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
	
	String use_num;
	public String getUse_num() {
		return use_num;
	}
	public void setUse_num(String use_num) {
		this.use_num = use_num;
	}
	
	String freeze_num;
	public String getFreeze_num() {
		return freeze_num;
	}
	public void setFreeze_num(String freeze_num) {
		this.freeze_num = freeze_num;
	}
	
	String pay_passwd;
	public String getPay_passwd(){
		return pay_passwd;
	}
	public void setPay_passwd(String pay_passwd){
		this.pay_passwd=pay_passwd;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memberfund[");
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", fund_num=");
		builder.append(this.fund_num);
		builder.append(", use_num=");
		builder.append(this.use_num);
		builder.append(", freeze_num=");
		builder.append(this.freeze_num);
		builder.append(", pay_passwd=");
		builder.append(this.pay_passwd);
		builder.append("]");
		return builder.toString();
	}

}

