/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Subscribe.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录会员商机订阅信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 21 08:53:30 CST 2011
 */
public class Subscribe implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String sub_id;
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String info_type;
	public String getInfo_type() {
		return info_type;
	}
	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}
	
	String keyword;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	String period;
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	
	String send_type;
	public String getSend_type() {
		return send_type;
	}
	public void setSend_type(String send_type) {
		this.send_type = send_type;
	}
	
	String enabled;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Subscribe[");
		builder.append(", sub_id=");
		builder.append(this.sub_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", info_type=");
		builder.append(this.info_type);
		builder.append(", keyword=");
		builder.append(this.keyword);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", period=");
		builder.append(this.period);
		builder.append(", send_type=");
		builder.append(this.send_type);
		builder.append(", enabled=");
		builder.append(this.enabled);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}

