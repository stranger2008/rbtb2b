/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Memberchconfig.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录会员企业站栏目配置信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Aug 26 13:24:50 CST 2011
 */
public class Memberchconfig implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String ch_id;
	public String getCh_id() {
		return ch_id;
	}
	public void setCh_id(String ch_id) {
		this.ch_id = ch_id;
	}
	
	String ch_name;
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
	
	String ch_code;
	public String getCh_code() {
		return ch_code;
	}
	public void setCh_code(String ch_code) {
		this.ch_code = ch_code;
	}
	
	String ch_type;
	public String getCh_type() {
		return ch_type;
	}
	public void setCh_type(String ch_type) {
		this.ch_type = ch_type;
	}
	
	String is_dis;
	public String getIs_dis() {
		return is_dis;
	}
	public void setIs_dis(String is_dis) {
		this.is_dis = is_dis;
	}
	
	String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	String ch_num;
	public String getCh_num() {
		return ch_num;
	}
	public void setCh_num(String ch_num) {
		this.ch_num = ch_num;
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
		builder.append("Memberchconfig[");
		builder.append(", ch_id=");
		builder.append(this.ch_id);
		builder.append(", ch_name=");
		builder.append(this.ch_name);
		builder.append(", ch_code=");
		builder.append(this.ch_code);
		builder.append(", ch_type=");
		builder.append(this.ch_type);
		builder.append(", is_dis=");
		builder.append(this.is_dis);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", ch_num=");
		builder.append(this.ch_num);
		builder.append(", plat_type=");
		builder.append(this.plat_type);
		builder.append("]");
		return builder.toString();
	}

}

