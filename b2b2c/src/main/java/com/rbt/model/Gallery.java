/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Gallery.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录图库信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Jul 25 14:50:37 CST 2011
 */
public class Gallery implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String gal_id;
	public String getGal_id() {
		return gal_id;
	}
	public void setGal_id(String gal_id) {
		this.gal_id = gal_id;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	String gal_desc;
	public String getGal_desc() {
		return gal_desc;
	}
	public void setGal_desc(String gal_desc) {
		this.gal_desc = gal_desc;
	}
	
	String is_recom;
	public String getIs_recom() {
		return is_recom;
	}
	public void setIs_recom(String is_recom) {
		this.is_recom = is_recom;
	}
	
	String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	String no_reason;
	public String getNo_reason() {
		return no_reason;
	}
	public void setNo_reason(String no_reason) {
		this.no_reason = no_reason;
	}
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	String clicknum;
	public String getClicknum() {
		return clicknum;
	}
	public void setClicknum(String clicknum) {
		this.clicknum = clicknum;
	}
	
	String fare;
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	
	String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	String infoattr_id;
	public String getInfoattr_id() {
		return infoattr_id;
	}
	public void setInfoattr_id(String infoattr_id) {
		this.infoattr_id = infoattr_id;
	}
	
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Gallery[");
		builder.append(", gal_id=");
		builder.append(this.gal_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", gal_desc=");
		builder.append(this.gal_desc);
		builder.append(", is_recom=");
		builder.append(this.is_recom);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", no_reason=");
		builder.append(this.no_reason);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", clicknum=");
		builder.append(this.clicknum);
		builder.append(", fare=");
		builder.append(this.fare);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append("]");
		return builder.toString();
	}

}

