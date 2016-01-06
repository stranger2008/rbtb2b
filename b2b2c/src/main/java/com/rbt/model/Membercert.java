/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Membercert.java 
 */
package com.rbt.model;

/**
 * @function 功能 会员荣誉资质信息实体
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 20 13:11:13 CST 2011
 */
public class Membercert {

	/*
	 * 荣誉资质唯一标识
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
	 * 证书标题
	 */
	String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * 发证机构
	 */
	String organize;

	public String getOrganize() {
		return organize;
	}

	public void setOrganize(String organize) {
		this.organize = organize;
	}

	/*
	 * 发证时间
	 */
	String start_date;

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	/*
	 * 到期日期
	 */
	String end_date;

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	/*
	 * 证书图片
	 */
	String img_path;

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	/*
	 * 证书介绍
	 */
	String cert_desc;

	public String getCert_desc() {
		return cert_desc;
	}

	public void setCert_desc(String cert_desc) {
		this.cert_desc = cert_desc;
	}

	/*
	 * 证书状态
	 */
	String cert_state;

	public String getCert_state() {
		return cert_state;
	}

	public void setCert_state(String cert_state) {
		this.cert_state = cert_state;
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

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Membercert[");
		builder.append(", cert_id=");
		builder.append(this.cert_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", organize=");
		builder.append(this.organize);
		builder.append(", start_date=");
		builder.append(this.start_date);
		builder.append(", end_date=");
		builder.append(this.end_date);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", cert_desc=");
		builder.append(this.cert_desc);
		builder.append(", cert_state=");
		builder.append(this.cert_state);
		builder.append(", no_reason=");
		builder.append(this.no_reason);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}
