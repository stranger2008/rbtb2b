/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Logs.java 
 */

package com.rbt.model;

import java.io.Serializable;

/**
 * @function 功能 角色实体类
 * @author 创建人 林俊钦
 * @date 创建日期 Jun 29, 2011 9:13:04 AM
 */

public class Logs implements Serializable {

	static final long serialVersionUID = 2083827134077851396L;
	/*
	 * 系统日志唯一标识
	 */
	String log_id;
	/*
	 * 客户标识
	 */
	String cust_id;
	/*
	 * 系统日志操作内容
	 */
	String content;
	/*
	 * 系统日志ip地址
	 */
	String ip;
	/*
	 * 系统日志存放时间
	 */
	String in_date;
	/*
	 * 系统日志操作的用户名
	 */
	String user_name;
	/*
	 * 系统日志操作的用户ID
	 */
	String user_id;

	/**
	 * @return the log_id
	 */
	public String getLog_id() {
		return log_id;
	}

	/**
	 * @param log_id
	 *            the log_id to set
	 */
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}

	/**
	 * @return the cust_id
	 */
	public String getCust_id() {
		return cust_id;
	}

	/**
	 * @param cust_id
	 *            the cust_id to set
	 */
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the in_date
	 */
	public String getIn_date() {
		return in_date;
	}

	/**
	 * @param in_date
	 *            the in_date to set
	 */
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name
	 *            the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
