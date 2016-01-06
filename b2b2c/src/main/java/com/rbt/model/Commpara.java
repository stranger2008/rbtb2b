/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Commpara.java 
 */
package com.rbt.model;

import java.io.Serializable;

/**
 * @function 功能 系统参数管理类
 * @author 创建人 胡惜坤
 * @date 创建日期 July 6, 2011
 */
public class Commpara implements Serializable {

	static final long serialVersionUID = -7590304047391890276L;

	/*
	 * 参数标志
	 */
	String para_id;
	/*
	 * 参数编码
	 */
	String para_code;
	/*
	 * 参数名称
	 */
	String para_name;
	/*
	 * 参数键
	 */
	String para_key;
	/*
	 * 参数值
	 */
	String para_value;
	/*
	 * 是否显示
	 */
	String enabled;
	/*
	 * 排序
	 */
	String sort_no;
	
	public String getPara_id() {
		return para_id;
	}

	public void setPara_id(String para_id) {
		this.para_id = para_id;
	}

	public String getPara_code() {
		return para_code;
	}

	public void setPara_code(String para_code) {
		this.para_code = para_code;
	}

	public String getPara_name() {
		return para_name;
	}

	public void setPara_name(String para_name) {
		this.para_name = para_name;
	}

	public String getPara_key() {
		return para_key;
	}

	public void setPara_key(String para_key) {
		this.para_key = para_key;
	}

	public String getPara_value() {
		return para_value;
	}

	public void setPara_value(String para_value) {
		this.para_value = para_value;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getSort_no() {
		return sort_no;
	}

	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}

}
