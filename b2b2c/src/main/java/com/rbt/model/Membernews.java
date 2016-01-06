/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Membernews.java 
 */
package com.rbt.model;

/**
 * @function 功能 企业新闻信息实体
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 20 13:14:34 CST 2011
 */
public class Membernews {

	/*
	 * 企业新闻唯一标识
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
	 * 新闻标题
	 */
	String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * 新闻内容
	 */
	String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/*
	 * 新闻状态
	 */
	String news_state;

	public String getNews_state() {
		return news_state;
	}

	public void setNews_state(String news_state) {
		this.news_state = news_state;
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
		builder.append("Membernews[");
		builder.append(", cert_id=");
		builder.append(this.cert_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", news_state=");
		builder.append(this.news_state);
		builder.append(", no_reason=");
		builder.append(this.no_reason);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}
