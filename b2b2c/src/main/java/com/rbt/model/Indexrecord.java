/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Indexrecord.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录更新的索引记录实体
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Jul 18 15:42:50 CST 2012
 */
public class Indexrecord implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String module_name;
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	
	private String info_id;
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	
	private String oper_type;
	public String getOper_type() {
		return oper_type;
	}
	public void setOper_type(String oper_type) {
		this.oper_type = oper_type;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Indexrecord[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", module_name=");
		builder.append(this.module_name);
		builder.append(", info_id=");
		builder.append(this.info_id);
		builder.append(", oper_type=");
		builder.append(this.oper_type);
		builder.append("]");
		return builder.toString();
	}

}

