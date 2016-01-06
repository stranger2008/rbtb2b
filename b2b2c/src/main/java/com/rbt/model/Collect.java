/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Collect.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录会员商机收藏信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Jul 20 14:27:09 CST 2011
 */
public class Collect implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String coll_id;
	public String getColl_id() {
		return coll_id;
	}
	public void setColl_id(String coll_id) {
		this.coll_id = coll_id;
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
	
	String link_url;
	public String getLink_url() {
		return link_url;
	}
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	
	String cat_id;
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	
	String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
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
		builder.append("Collect[");
		builder.append(", coll_id=");
		builder.append(this.coll_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", link_url=");
		builder.append(this.link_url);
		builder.append(", cat_id=");
		builder.append(this.cat_id);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", plat_type=");
		builder.append(this.plat_type);
		builder.append("]");
		return builder.toString();
	}

}

