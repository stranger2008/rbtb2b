/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Levelinfo.java 
 */
package com.rbt.model;

/**
 * @function 功能 会员级别信息实体
 * @author 创建人 邱景岩
 * @date 创建日期 Tue Jul 19 14:31:17 CST 2011
 */
public class Levelinfo {

	/*
	 * 会员唯一标识
	 */
	String cust_id;

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	/*
	 * 级别代码
	 */
	String level_code;

	public String getLevel_code() {
		return level_code;
	}

	public void setLevel_code(String level_code) {
		this.level_code = level_code;
	}

	/*
	 * 开始时间
	 */
	String start_date;

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	/*
	 * 截止时间
	 */
	String end_date;

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	/*
	 * 操作日期
	 */
	String in_date;

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	/*
	 * 操作员
	 */
	String user_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Levelinfo[");
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", level_code=");
		builder.append(this.level_code);
		builder.append(", start_date=");
		builder.append(this.start_date);
		builder.append(", end_date=");
		builder.append(this.end_date);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append("]");
		return builder.toString();
	}

}
