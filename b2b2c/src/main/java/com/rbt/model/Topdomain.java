/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Topdomain.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录会员申请的顶级域名申请信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Aug 01 14:24:27 CST 2011
 */
public class Topdomain implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String info_id;
	
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String domain_url;
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	String enabled;
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	
	String go_url;
	String domain_type;
	
	
	
	public String getGo_url() {
		return go_url;
	}
	public void setGo_url(String go_url) {
		this.go_url = go_url;
	}
	public String getDomain_type() {
		return domain_type;
	}
	public void setDomain_type(String domain_type) {
		this.domain_type = domain_type;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Topdomain[");
		builder.append(", info_id=");
		builder.append(this.info_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", domain_url=");
		builder.append(this.domain_url);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", enabled=");
		builder.append(this.enabled);
		builder.append(", go_url=");
		builder.append(this.go_url);
		builder.append(", domain_type=");
		builder.append(this.domain_type);
		builder.append("]");
		return builder.toString();
	}
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	public String getDomain_url() {
		return domain_url;
	}
	public void setDomain_url(String domain_url) {
		this.domain_url = domain_url;
	}

}

