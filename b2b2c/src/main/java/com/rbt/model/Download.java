/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Download.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录下载信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 28 13:55:21 CST 2011
 */
public class Download implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String down_id;
	public String getDown_id() {
		return down_id;
	}
	public void setDown_id(String down_id) {
		this.down_id = down_id;
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
	
	String file_path;
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	String file_size;
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	
	String size_unit;
	public String getSize_unit() {
		return size_unit;
	}
	public void setSize_unit(String size_unit) {
		this.size_unit = size_unit;
	}
	
	String version;
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	String update_time;
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	String file_type;
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	
	String down_desc;
	public String getDown_desc() {
		return down_desc;
	}
	public void setDown_desc(String down_desc) {
		this.down_desc = down_desc;
	}
	
	String keyword;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	String down_num;
	public String getDown_num() {
		return down_num;
	}
	public void setDown_num(String down_num) {
		this.down_num = down_num;
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
		builder.append("Download[");
		builder.append(", down_id=");
		builder.append(this.down_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", file_path=");
		builder.append(this.file_path);
		builder.append(", file_size=");
		builder.append(this.file_size);
		builder.append(", size_unit=");
		builder.append(this.size_unit);
		builder.append(", version=");
		builder.append(this.version);
		builder.append(", update_time=");
		builder.append(this.update_time);
		builder.append(", file_type=");
		builder.append(this.file_type);
		builder.append(", down_desc=");
		builder.append(this.down_desc);
		builder.append(", keyword=");
		builder.append(this.keyword);
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
		builder.append(", down_num=");
		builder.append(this.down_num);
		builder.append(", infoattr_id=");
		builder.append(this.infoattr_id);
		builder.append("]");
		return builder.toString();
	}

}

