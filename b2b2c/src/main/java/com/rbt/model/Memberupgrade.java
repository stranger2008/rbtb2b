/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Memberupgrade.java 
 */
package com.rbt.model;

/**
 * @function 功能 会员升级记录信息实体
 * @author 创建人 邱景岩
 * @date 创建日期 Fri Jul 29 16:37:21 CST 2011
 */
public class Memberupgrade {

	/*
	 * 升级标识
	 */
	String grade_id;

	public String getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(String grade_id) {
		this.grade_id = grade_id;
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
	 * 当前级别
	 */
	String now_level;

	public String getNow_level() {
		return now_level;
	}

	public void setNow_level(String now_level) {
		this.now_level = now_level;
	}

	/*
	 * 申请级别
	 */
	String apply_level;

	public String getApply_level() {
		return apply_level;
	}

	public void setApply_level(String apply_level) {
		this.apply_level = apply_level;
	}

	/*
	 * 申请时间
	 */
	String in_date;

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	/*
	 * 申请用户名
	 */
	String user_name;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/*
	 * 审核状态
	 */
	String audit_state;

	public String getAudit_state() {
		return audit_state;
	}

	public void setAudit_state(String audit_state) {
		this.audit_state = audit_state;
	}

	/*
	 * 拒绝理由
	 */
	String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	/*
	 * 审核时间
	 */
	String audit_date;

	public String getAudit_date() {
		return audit_date;
	}

	public void setAudit_date(String audit_date) {
		this.audit_date = audit_date;
	}

	/*
	 * 审核用户名
	 */
	String audit_user;

	public String getAudit_user() {
		return audit_user;
	}

	public void setAudit_user(String audit_user) {
		this.audit_user = audit_user;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memberupgrade[");
		builder.append(", grade_id=");
		builder.append(this.grade_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", now_level=");
		builder.append(this.now_level);
		builder.append(", apply_level=");
		builder.append(this.apply_level);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_name=");
		builder.append(this.user_name);
		builder.append(", audit_state=");
		builder.append(this.audit_state);
		builder.append(", reason=");
		builder.append(this.reason);
		builder.append(", audit_date=");
		builder.append(this.audit_date);
		builder.append(", audit_user=");
		builder.append(this.audit_user);
		builder.append("]");
		return builder.toString();
	}

}
