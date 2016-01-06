/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Gallerypic.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录图库图片信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 26 10:31:40 CST 2011
 */
public class Gallerypic implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String pic_id;
	public String getPic_id() {
		return pic_id;
	}
	public void setPic_id(String pic_id) {
		this.pic_id = pic_id;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String gal_id;
	public String getGal_id() {
		return gal_id;
	}
	public void setGal_id(String gal_id) {
		this.gal_id = gal_id;
	}
	
	String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	String pic_desc;
	public String getPic_desc() {
		return pic_desc;
	}
	public void setPic_desc(String pic_desc) {
		this.pic_desc = pic_desc;
	}
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Gallerypic[");
		builder.append(", pic_id=");
		builder.append(this.pic_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", gal_id=");
		builder.append(this.gal_id);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", pic_desc=");
		builder.append(this.pic_desc);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append("]");
		return builder.toString();
	}

}

