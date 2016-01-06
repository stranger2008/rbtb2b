/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Interrule.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 积分规则表实体
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Nov 10 14:26:30 CST 2011
 */
public class Interrule implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String rule_id;
	public String getRule_id() {
		return rule_id;
	}
	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}
	
	String rule_code;
	public String getRule_code() {
		return rule_code;
	}
	public void setRule_code(String rule_code) {
		this.rule_code = rule_code;
	}
	
	String rule_name;
	public String getRule_name() {
		return rule_name;
	}
	public void setRule_name(String rule_name) {
		this.rule_name = rule_name;
	}
	
	String internum;
	public String getInternum() {
		return internum;
	}
	public void setInternum(String internum) {
		this.internum = internum;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Interrule[");
		builder.append(", rule_id=");
		builder.append(this.rule_id);
		builder.append(", rule_code=");
		builder.append(this.rule_code);
		builder.append(", rule_name=");
		builder.append(this.rule_name);
		builder.append(", internum=");
		builder.append(this.internum);
		builder.append("]");
		return builder.toString();
	}

}

