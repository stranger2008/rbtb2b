/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Categoryattr.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 产品属性列表实体
 * @author 创建人 林俊钦
 * @date 创建日期 Tue Jul 19 08:59:50 CST 2011
 */
public class Categoryattr implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String attr_id;
	public String getAttr_id() {
		return attr_id;
	}
	public void setAttr_id(String attr_id) {
		this.attr_id = attr_id;
	}
	
	String attr_name;
	public String getAttr_name() {
		return attr_name;
	}
	public void setAttr_name(String attr_name) {
		this.attr_name = attr_name;
	}
	
	String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	String attr_type;
	public String getAttr_type() {
		return attr_type;
	}
	public void setAttr_type(String attr_type) {
		this.attr_type = attr_type;
	}
	

	String is_must;
	public String getIs_must() {
		return is_must;
	}
	public void setIs_must(String is_must) {
		this.is_must = is_must;
	}
	
	String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	//所属模块
	public String module_type;
	public String getModule_type() {
		return module_type;
	}
	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categoryattr[");
		builder.append(", attr_id=");
		builder.append(this.attr_id);
		builder.append(", attr_name=");
		builder.append(this.attr_name);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", attr_type=");
		builder.append(this.attr_type);
		builder.append(", is_must=");
		builder.append(this.is_must);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", module_type=");
		builder.append(this.module_type);
		builder.append("]");
		return builder.toString();
	}

}

