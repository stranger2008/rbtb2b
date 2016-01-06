/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Membertemplate.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录企业站模板信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Aug 25 14:37:44 CST 2011
 */
public class Membertemplate implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String temp_id;
	public String getTemp_id() {
		return temp_id;
	}
	public void setTemp_id(String temp_id) {
		this.temp_id = temp_id;
	}
	
	String temp_img;
	public String getTemp_img() {
		return temp_img;
	}
	public void setTemp_img(String temp_img) {
		this.temp_img = temp_img;
	}
	
	String temp_code;
	public String getTemp_code() {
		return temp_code;
	}
	public void setTemp_code(String temp_code) {
		this.temp_code = temp_code;
	}
	
	String temp_loc;
	public String getTemp_loc() {
		return temp_loc;
	}
	public void setTemp_loc(String temp_loc) {
		this.temp_loc = temp_loc;
	}
	
	String temp_name;
	public String getTemp_name() {
		return temp_name;
	}
	public void setTemp_name(String temp_name) {
		this.temp_name = temp_name;
	}
	
	String author;
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	String price;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	String p_unit;
	public String getP_unit() {
		return p_unit;
	}
	public void setP_unit(String p_unit) {
		this.p_unit = p_unit;
	}
	
	String mem_level;
	public String getMem_level() {
		return mem_level;
	}
	public void setMem_level(String mem_level) {
		this.mem_level = mem_level;
	}
	
	String file_pos;
	public String getFile_pos() {
		return file_pos;
	}
	public void setFile_pos(String file_pos) {
		this.file_pos = file_pos;
	}
	
	String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
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
		builder.append("Membertemplate[");
		builder.append(", temp_id=");
		builder.append(this.temp_id);
		builder.append(", temp_img=");
		builder.append(this.temp_img);
		builder.append(", temp_code=");
		builder.append(this.temp_code);
		builder.append(", temp_loc=");
		builder.append(this.temp_loc);
		builder.append(", temp_name=");
		builder.append(this.temp_name);
		builder.append(", author=");
		builder.append(this.author);
		builder.append(", price=");
		builder.append(this.price);
		builder.append(", p_unit=");
		builder.append(this.p_unit);
		builder.append(", mem_level=");
		builder.append(this.mem_level);
		builder.append(", file_pos=");
		builder.append(this.file_pos);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", plat_type=");
		builder.append(this.plat_type);
		builder.append("]");
		return builder.toString();
	}

}

