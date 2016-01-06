/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Malllevelset.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 会员等级设置表实体
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Feb 29 10:28:35 CST 2012
 */
public class Malllevelset implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String level_id;
	public String getLevel_id() {
		return level_id;
	}
	public void setLevel_id(String level_id) {
		this.level_id = level_id;
	}
	
	private String level_name;
	public String getLevel_name() {
		return level_name;
	}
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}
	
	private String inter_lower;
	public String getInter_lower() {
		return inter_lower;
	}
	public void setInter_lower(String inter_lower) {
		this.inter_lower = inter_lower;
	}
	
	private String inter_height;
	public String getInter_height() {
		return inter_height;
	}
	public void setInter_height(String inter_height) {
		this.inter_height = inter_height;
	}
	
	private String mem_type;
	public String getMem_type() {
		return mem_type;
	}
	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}
	
	private String img_url;
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
	private String discount;
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Malllevelset[");
		builder.append(", level_id=");
		builder.append(this.level_id);
		builder.append(", level_name=");
		builder.append(this.level_name);
		builder.append(", inter_lower=");
		builder.append(this.inter_lower);
		builder.append(", inter_height=");
		builder.append(this.inter_height);
		builder.append(", mem_type=");
		builder.append(this.mem_type);
		builder.append(", img_url=");
		builder.append(this.img_url);
		builder.append(", discount=");
		builder.append(this.discount);
		builder.append("]");
		return builder.toString();
	}

}

