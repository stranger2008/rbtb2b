/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Infoattr.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 信息属性实体
 * @author 创建人 邱景岩
 * @date 创建日期 Sat Mar 17 10:32:08 CST 2012
 */
public class Infoattr implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String infoattr_id;
	public String getInfoattr_id() {
		return infoattr_id;
	}
	public void setInfoattr_id(String infoattr_id) {
		this.infoattr_id = infoattr_id;
	}
	
	private String attr_id;
	public String getAttr_id() {
		return attr_id;
	}
	public void setAttr_id(String attr_id) {
		this.attr_id = attr_id;
	}
	
	private String attr_name;
	public String getAttr_name() {
		return attr_name;
	}
	public void setAttr_name(String attr_name) {
		this.attr_name = attr_name;
	}
	
	private String attr_value;
	public String getAttr_value() {
		return attr_value;
	}
	public void setAttr_value(String attr_value) {
		this.attr_value = attr_value;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	String value_id;
	public String getValue_id() {
		return value_id;
	}
	public void setValue_id(String value_id) {
		this.value_id = value_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Infoattr[");
		builder.append(", infoattr_id=");
		builder.append(this.infoattr_id);
		builder.append(", attr_id=");
		builder.append(this.attr_id);
		builder.append(", attr_name=");
		builder.append(this.attr_name);
		builder.append(", attr_value=");
		builder.append(this.attr_value);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append("]");
		return builder.toString();
	}

}

