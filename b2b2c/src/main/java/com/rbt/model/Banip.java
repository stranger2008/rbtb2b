/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Ban_Ip.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 系统禁止IP访问实体
 * @author 创建人 胡惜坤
 * @date 创建日期 July 5, 2011
 */
public class Banip implements Serializable {

	static final long serialVersionUID = 7629669818175755450L;
	/*
	 * ip标识
	 */
	String ip_id;
	/*
	 * ip
	 */
	String ip;
	/*
	 * 用户标示
	 */
	String user_id;
	/*
	 * 添加时间
	 */
	String in_date;

	public String getIp_id() {
		return ip_id;
	}

	public void setIp_id(String ip_id) {
		this.ip_id = ip_id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ban_Ip [ip_id=");
		builder.append(this.ip_id);
		builder.append(", ip=");
		builder.append(this.ip);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}
