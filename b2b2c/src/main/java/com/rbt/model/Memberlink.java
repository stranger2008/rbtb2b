/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Memberlink.java 
 */
package com.rbt.model;

/**
 * @function 功能 企业友情链接信息实体
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 20 13:16:25 CST 2011
 */
public class Memberlink {

	/*
	 * 企业友情链接唯一标识
	 */
	String cert_id;

	public String getCert_id() {
		return cert_id;
	}

	public void setCert_id(String cert_id) {
		this.cert_id = cert_id;
	}

	/*
	 * 会员标识
	 */
	String cust_id;

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	/*
	 * 链接名称
	 */
	String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * 链接地址
	 */
	String link_url;

	public String getLink_url() {
		return link_url;
	}

	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}

	/*
	 * 链接状态
	 */
	String link_state;

	public String getLink_state() {
		return link_state;
	}

	public void setLink_state(String link_state) {
		this.link_state = link_state;
	}

	/*
	 * 不通过理由
	 */
	String no_reason;

	public String getNo_reason() {
		return no_reason;
	}

	public void setNo_reason(String no_reason) {
		this.no_reason = no_reason;
	}

	/*
	 * 添加时间
	 */
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
		builder.append("Memberlink[");
		builder.append(", cert_id=");
		builder.append(this.cert_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", link_url=");
		builder.append(this.link_url);
		builder.append(", link_state=");
		builder.append(this.link_state);
		builder.append(", no_reason=");
		builder.append(this.no_reason);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", plat_type=");
		builder.append(this.plat_type);
		builder.append("]");
		return builder.toString();
	}

}
