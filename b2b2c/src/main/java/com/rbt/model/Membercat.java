/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Membercat.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 会员自定义分类表实体
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Jul 25 11:13:52 CST 2011
 */
public class Membercat implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String cat_id;
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String cat_name;
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	
	String cat_type;
	public String getCat_type() {
		return cat_type;
	}
	public void setCat_type(String cat_type) {
		this.cat_type = cat_type;
	}
	
	String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	String img_path;
	String is_recom;
	String up_cat_id;
	String cat_level;
	
	
	
	
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String getIs_recom() {
		return is_recom;
	}
	public void setIs_recom(String is_recom) {
		this.is_recom = is_recom;
	}
	public String getUp_cat_id() {
		return up_cat_id;
	}
	public void setUp_cat_id(String up_cat_id) {
		this.up_cat_id = up_cat_id;
	}
	public String getCat_level() {
		return cat_level;
	}
	public void setCat_level(String cat_level) {
		this.cat_level = cat_level;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Membercat[");
		builder.append(", cat_id=");
		builder.append(this.cat_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", cat_name=");
		builder.append(this.cat_name);
		builder.append(", cat_type=");
		builder.append(this.cat_type);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", is_recom=");
		builder.append(this.is_recom);
		builder.append(", up_cat_id=");
		builder.append(this.up_cat_id);
		builder.append(", cat_level=");
		builder.append(this.cat_level);
		builder.append("]");
		return builder.toString();
	}

}

