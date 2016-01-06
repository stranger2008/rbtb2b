/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Complaint.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 会员投诉信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Dec 02 11:20:21 CST 2011
 */
public class Complaint implements Serializable {

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
	
	String comp_type;
	public String getComp_type() {
		return comp_type;
	}
	public void setComp_type(String comp_type) {
		this.comp_type = comp_type;
	}
	
	String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	String get_cust_id;
	public String getGet_cust_id() {
		return get_cust_id;
	}
	public void setGet_cust_id(String get_cust_id) {
		this.get_cust_id = get_cust_id;
	}
	
	String get_cust_name;
	public String getGet_cust_name() {
		return get_cust_name;
	}
	public void setGet_cust_name(String get_cust_name) {
		this.get_cust_name = get_cust_name;
	}
	
	String product_name;
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	String trade_fund;
	public String getTrade_fund() {
		return trade_fund;
	}
	public void setTrade_fund(String trade_fund) {
		this.trade_fund = trade_fund;
	}
	
	String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	String file_path;
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	String comp_path;
	public String getComp_path() {
		return comp_path;
	}
	public void setComp_path(String comp_path) {
		this.comp_path = comp_path;
	}
	
	String attach_path;
	public String getAttach_path() {
		return attach_path;
	}
	public void setAttach_path(String attach_path) {
		this.attach_path = attach_path;
	}
	
	String appeal_desc;
	public String getAppeal_desc() {
		return appeal_desc;
	}
	public void setAppeal_desc(String appeal_desc) {
		this.appeal_desc = appeal_desc;
	}
	
	String appeal_date;
	public String getAppeal_date() {
		return appeal_date;
	}
	public void setAppeal_date(String appeal_date) {
		this.appeal_date = appeal_date;
	}
	
	String appeal_user_id;
	public String getAppeal_user_id() {
		return appeal_user_id;
	}
	public void setAppeal_user_id(String appeal_user_id) {
		this.appeal_user_id = appeal_user_id;
	}
	
	String do_result;
	public String getDo_result() {
		return do_result;
	}
	public void setDo_result(String do_result) {
		this.do_result = do_result;
	}
	
	String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	String state_code;
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
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
		builder.append("Complaint[");
		builder.append(", info_id=");
		builder.append(this.info_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", comp_type=");
		builder.append(this.comp_type);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", get_cust_id=");
		builder.append(this.get_cust_id);
		builder.append(", get_cust_name=");
		builder.append(this.get_cust_name);
		builder.append(", product_name=");
		builder.append(this.product_name);
		builder.append(", trade_fund=");
		builder.append(this.trade_fund);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", file_path=");
		builder.append(this.file_path);
		builder.append(", comp_path=");
		builder.append(this.comp_path);
		builder.append(", attach_path=");
		builder.append(this.attach_path);
		builder.append(", appeal_desc=");
		builder.append(this.appeal_desc);
		builder.append(", appeal_date=");
		builder.append(this.appeal_date);
		builder.append(", appeal_user_id=");
		builder.append(this.appeal_user_id);
		builder.append(", do_result=");
		builder.append(this.do_result);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", state_code=");
		builder.append(this.state_code);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}
	

}

