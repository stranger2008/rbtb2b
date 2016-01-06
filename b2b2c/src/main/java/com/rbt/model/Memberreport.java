/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Memberreport.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 会员举报信息表实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Nov 30 14:19:40 CST 2011
 */
public class Memberreport implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String report_id;
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String link_url;
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	
	String report_type;
	public String getReport_type() {
		return report_type;
	}
	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}
	
	String re_desc;
	public String getRe_desc() {
		return re_desc;
	}
	public void setRe_desc(String re_desc) {
		this.re_desc = re_desc;
	}
	
	String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	String doresult;
	public String getDoresult() {
		return doresult;
	}
	public void setDoresult(String doresult) {
		this.doresult = doresult;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memberreport[");
		builder.append(", report_id=");
		builder.append(this.report_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", link_url=");
		builder.append(this.link_url);
		builder.append(", report_type=");
		builder.append(this.report_type);
		builder.append(", re_desc=");
		builder.append(this.re_desc);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", doresult=");
		builder.append(this.doresult);
		builder.append("]");
		return builder.toString();
	}

}

