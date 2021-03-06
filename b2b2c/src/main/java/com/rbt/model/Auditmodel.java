/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Auditmodel.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 审核模型信息实体
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 06 15:40:22 CST 2012
 */
public class Auditmodel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String model_type;
	public String getModel_type() {
		return model_type;
	}
	public void setModel_type(String model_type) {
		this.model_type = model_type;
	}
	
	private String userid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Auditmodel[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", model_type=");
		builder.append(this.model_type);
		builder.append(", userid=");
		builder.append(this.userid);
		builder.append(", username=");
		builder.append(this.username);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append("]");
		return builder.toString();
	}

}

