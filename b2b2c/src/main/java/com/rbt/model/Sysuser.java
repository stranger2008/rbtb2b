/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Sysuser.java 
 */

package com.rbt.model;

import java.io.Serializable;

/**
 * @function 功能 系统用户表pojo类			
 * @author  创建人 李良林
 * @date  创建日期  Jun 3, 2011
 */
public class Sysuser implements Serializable {
	
	static final long serialVersionUID = -4831911050213379748L;
	
	/*
	 * 用户角色标识
	 */
	String role_id;
	
	/*
	 * 用户唯一标识
	 */
	String user_id;  
	/*
	 * 用户登录名
	 */
	String user_name;
	/*
	 * 用户笔名，如文章的编辑人
	 */
	String nike_name;  
	/*
	 * 密码
	 */
	String passwd;    
	/*
	 * 用户类型
	 */
	String user_type;   
	/*
	 * 用户真名
	 */
	String real_name;    
	/*
	 * 邮箱
	 */
	String email;
	/*
	 * 最近一次的登录时间
	 */
	String logintime;    
	/*
	 * 最近一次的登录IP
	 */
	String loginip;
	/*
	 * 状态 0:启用 1：禁用
	 */
	String state;
	String loginnum;
	String org_id;
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return the nike_name
	 */
	public String getNike_name() {
		return nike_name;
	}
	/**
	 * @param nike_name the nike_name to set
	 */
	public void setNike_name(String nike_name) {
		this.nike_name = nike_name;
	}
	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}
	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	/**
	 * @return the user_type
	 */
	public String getUser_type() {
		return user_type;
	}
	/**
	 * @param user_type the user_type to set
	 */
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	/**
	 * @return the real_name
	 */
	public String getReal_name() {
		return real_name;
	}
	/**
	 * @param real_name the real_name to set
	 */
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the logintime
	 */
	public String getLogintime() {
		return logintime;
	}
	/**
	 * @param logintime the logintime to set
	 */
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	/**
	 * @return the loginip
	 */
	public String getLoginip() {
		return loginip;
	}
	/**
	 * @param loginip the loginip to set
	 */
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sysuser [user_id=");
		builder.append(this.user_id);
		builder.append(", user_name=");
		builder.append(this.user_name);
		builder.append(", nike_name=");
		builder.append(this.nike_name);
		builder.append(", passwd=");
		builder.append(this.passwd);
		builder.append(", user_type=");
		builder.append(this.user_type);
		builder.append(", real_name=");
		builder.append(this.real_name);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", logintime=");
		builder.append(this.logintime);
		builder.append(", loginip=");
		builder.append(this.loginip);
		builder.append("]");
		return builder.toString();
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	/**
	 * @return the loginnum
	 */
	public String getLoginnum() {
		return loginnum;
	}
	/**
	 * @param loginnum the loginnum to set
	 */
	public void setLoginnum(String loginnum) {
		this.loginnum = loginnum;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	   
}
