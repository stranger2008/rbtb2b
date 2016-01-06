/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Memberuser.java 
 */
package com.rbt.model;

/**
 * @function 功能 用户账号实体
 * @author 创建人 邱景岩
 * @date 创建日期 Tue Jul 19 09:37:16 CST 2011
 */
public class Memberuser {

	/*
	 * 用户唯一标识
	 */
	String user_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	 * 用户类型
	 */
	String user_type;

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	/*
	 * 用户名
	 */
	String user_name;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/*
	 * 密码
	 */
	String passwd;

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/*
	 * 角色代码
	 */
	String role_code;

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	/*
	 * 找回密码问题
	 */
	String passwd_ques;

	public String getPasswd_ques() {
		return passwd_ques;
	}

	public void setPasswd_ques(String passwd_ques) {
		this.passwd_ques = passwd_ques;
	}

	/*
	 * 找回密码答案
	 */
	String passwd_answer;

	public String getPasswd_answer() {
		return passwd_answer;
	}

	public void setPasswd_answer(String passwd_answer) {
		this.passwd_answer = passwd_answer;
	}

	/*
	 * email
	 */
	String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * 真实姓名
	 */
	String real_name;

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	/*
	 * 性别
	 */
	String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	/*
	 * 部门
	 */
	String org_name;

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	/*
	 * 职位
	 */
	String career;

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	/*
	 * 手机
	 */
	String cellphone;

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	/*
	 * 电话
	 */
	String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/*
	 * qq
	 */
	String qq;

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	/*
	 * msn
	 */
	String msn;

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	/*
	 * skype
	 */
	String skype;

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}
	
	String pass_strength;
	

	public String getPass_strength() {
		return pass_strength;
	}

	public void setPass_strength(String pass_strength) {
		this.pass_strength = pass_strength;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memberuser[");
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", user_type=");
		builder.append(this.user_type);
		builder.append(", user_name=");
		builder.append(this.user_name);
		builder.append(", passwd=");
		builder.append(this.passwd);
		builder.append(", role_code=");
		builder.append(this.role_code);
		builder.append(", passwd_ques=");
		builder.append(this.passwd_ques);
		builder.append(", passwd_answer=");
		builder.append(this.passwd_answer);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", real_name=");
		builder.append(this.real_name);
		builder.append(", sex=");
		builder.append(this.sex);
		builder.append(", org_name=");
		builder.append(this.org_name);
		builder.append(", career=");
		builder.append(this.career);
		builder.append(", cellphone=");
		builder.append(this.cellphone);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", qq=");
		builder.append(this.qq);
		builder.append(", msn=");
		builder.append(this.msn);
		builder.append(", skype=");
		builder.append(this.skype);
		builder.append(", pass_strength=");
		builder.append(this.pass_strength);
		builder.append("]");
		return builder.toString();
	}

}
