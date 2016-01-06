/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Sysconfig.java 
 */
package com.rbt.model;

/**
 * @function 功能 系统变量设置pojo类
 * @author 创建人 邱景岩
 * @date 创建日期 Jul 6, 2011 10:02:27 AM
 */

public class Sysconfig {
	/*
	 * 系统变量唯一标识
	 */
	String var_id;
	/*
	 * 变量名称
	 */
	String var_name;
	/*
	 * 变量值
	 */
	String var_value;
	/*
	 * 变量说明
	 */
	String var_desc;
	/*
	 * 所属分组
	 */
	String var_group;
	/*
	 * 变量类型
	 */
	String var_type;
	/*
	 * 标量
	 */
	String val_sys;
	/*
	 * 所属模块
	 */
	String module_type;

	/**
	 * @return the var_id
	 */
	public String getVar_id() {
		return var_id;
	}

	/**
	 * @param var_id
	 *            the var_id to set
	 */
	public void setVar_id(String var_id) {
		this.var_id = var_id;
	}

	/**
	 * @return the var_name
	 */
	public String getVar_name() {
		return var_name;
	}

	/**
	 * @param var_name
	 *            the var_name to set
	 */
	public void setVar_name(String var_name) {
		this.var_name = var_name;
	}

	/**
	 * @return the var_value
	 */
	public String getVar_value() {
		return var_value;
	}

	/**
	 * @param var_value
	 *            the var_value to set
	 */
	public void setVar_value(String var_value) {
		this.var_value = var_value;
	}

	/**
	 * @return the var_desc
	 */
	public String getVar_desc() {
		return var_desc;
	}

	/**
	 * @param var_desc
	 *            the var_desc to set
	 */
	public void setVar_desc(String var_desc) {
		this.var_desc = var_desc;
	}

	/**
	 * @return the var_group
	 */
	public String getVar_group() {
		return var_group;
	}

	/**
	 * @param var_group
	 *            the var_group to set
	 */
	public void setVar_group(String var_group) {
		this.var_group = var_group;
	}

	/**
	 * @return the var_type
	 */
	public String getVar_type() {
		return var_type;
	}

	/**
	 * @param var_type
	 *            the var_type to set
	 */
	public void setVar_type(String var_type) {
		this.var_type = var_type;
	}

	/**
	 * @return the val_sys
	 */
	public String getVal_sys() {
		return val_sys;
	}

	/**
	 * @param val_sys
	 *            the val_sys to set
	 */
	public void setVal_sys(String val_sys) {
		this.val_sys = val_sys;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sysconfig [var_id=");
		builder.append(this.var_id);
		builder.append(", var_name=");
		builder.append(this.var_name);
		builder.append(", var_value=");
		builder.append(this.var_value);
		builder.append(", var_desc=");
		builder.append(this.var_desc);
		builder.append(", var_group=");
		builder.append(this.var_group);
		builder.append(", var_type=");
		builder.append(this.var_type);
		builder.append(", val_sys=");
		builder.append(this.val_sys);
		builder.append("]");
		return builder.toString();
	}

	public String getModule_type() {
		return module_type;
	}

	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}

}
