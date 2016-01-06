/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Organize.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录组织部门实体
 * @author 创建人 林俊钦
 * @date 创建日期 Mon Nov 07 13:37:36 CST 2011
 */
public class Organize implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String org_id;
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	
	String org_name;
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	
	String up_org_id;
	public String getUp_org_id() {
		return up_org_id;
	}
	public void setUp_org_id(String up_org_id) {
		this.up_org_id = up_org_id;
	}
	
	String org_level;
	public String getOrg_level() {
		return org_level;
	}
	public void setOrg_level(String org_level) {
		this.org_level = org_level;
	}
	
	String sys_org;
	public String getSys_org() {
		return sys_org;
	}
	public void setSys_org(String sys_org) {
		this.sys_org = sys_org;
	}
	
	String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	String contact;
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
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
	
	String skype;
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	
	String qq;
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	String msn;
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	
	String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	String fax;
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Organize[");
		builder.append(", org_id=");
		builder.append(this.org_id);
		builder.append(", org_name=");
		builder.append(this.org_name);
		builder.append(", up_org_id=");
		builder.append(this.up_org_id);
		builder.append(", org_level=");
		builder.append(this.org_level);
		builder.append(", sys_org=");
		builder.append(this.sys_org);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", contact=");
		builder.append(this.contact);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", cellphone=");
		builder.append(this.cellphone);
		builder.append(", skype=");
		builder.append(this.skype);
		builder.append(", qq=");
		builder.append(this.qq);
		builder.append(", msn=");
		builder.append(this.msn);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", fax=");
		builder.append(this.fax);
		builder.append(", address=");
		builder.append(this.address);
		builder.append("]");
		return builder.toString();
	}

}

