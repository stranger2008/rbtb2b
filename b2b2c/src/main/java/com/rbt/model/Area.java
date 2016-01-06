/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Area.java 
 */
package com.rbt.model;

import java.io.Serializable;

/**
 * @function 功能 系统地区管理类
 * @author 创建人 胡惜坤
 * @date 创建日期 Jun 28, 2011
 */
public class Area implements Serializable {

	static final long serialVersionUID = 4680076343593566487L;
	/*
	 * 地区标识
	 */
	String area_id;
	/*
	 * 地区名称
	 */
	String area_name;
	/*
	 * 地区英文名
	 */
	String en_name;
	/*
	 * 上级地区
	 */
	String up_area_id;
	/*
	 * 地区级别
	 */
	String area_level;
	/*
	 * 地区排序
	 */
	String sort_no;
	
	//划分大中华区域
	String area_have;
	

	public String getArea_have() {
		return area_have;
	}

	public void setArea_have(String area_have) {
		this.area_have = area_have;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getEn_name() {
		return en_name;
	}

	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}

	public String getUp_area_id() {
		return up_area_id;
	}

	public void setUp_area_id(String up_area_id) {
		this.up_area_id = up_area_id;
	}

	public String getArea_level() {
		return area_level;
	}

	public void setArea_level(String area_level) {
		this.area_level = area_level;
	}

	public String getSort_no() {
		return sort_no;
	}

	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}

}
