/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Memberfriend.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 会员商友表实体
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Aug 04 13:41:00 CST 2011
 */
public class Memberfriend implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String info_id;
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String f_cust_id;
	public String getF_cust_id() {
		return f_cust_id;
	}
	public void setF_cust_id(String f_cust_id) {
		this.f_cust_id = f_cust_id;
	}
	
	String cat_id;
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	
	String cust_name;
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	String career;
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	
	String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	String cellphone;
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	String website;
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	String qq;
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	String aliwang;
	public String getAliwang() {
		return aliwang;
	}
	public void setAliwang(String aliwang) {
		this.aliwang = aliwang;
	}
	
	String msn;
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	
	String skype;
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	
	String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
		builder.append("Memberfriend[");
		builder.append(", info_id=");
		builder.append(this.info_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", f_cust_id=");
		builder.append(this.f_cust_id);
		builder.append(", cat_id=");
		builder.append(this.cat_id);
		builder.append(", cust_name=");
		builder.append(this.cust_name);
		builder.append(", name=");
		builder.append(this.name);
		builder.append(", career=");
		builder.append(this.career);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", cellphone=");
		builder.append(this.cellphone);
		builder.append(", website=");
		builder.append(this.website);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", qq=");
		builder.append(this.qq);
		builder.append(", aliwang=");
		builder.append(this.aliwang);
		builder.append(", msn=");
		builder.append(this.msn);
		builder.append(", skype=");
		builder.append(this.skype);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}

