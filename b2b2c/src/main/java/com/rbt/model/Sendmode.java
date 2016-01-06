/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Sendmode.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 配送方式表实体
 * @author 创建人 胡惜坤
 * @date 创建日期 Fri Mar 23 09:55:49 CST 2012
 */
public class Sendmode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String smode_id;
	public String getSmode_id() {
		return smode_id;
	}
	public void setSmode_id(String smode_id) {
		this.smode_id = smode_id;
	}
	
	private String smode_name;
	public String getSmode_name() {
		return smode_name;
	}
	public void setSmode_name(String smode_name) {
		this.smode_name = smode_name;
	}
	
	private String smode_desc;
	public String getSmode_desc() {
		return smode_desc;
	}
	public void setSmode_desc(String smode_desc) {
		this.smode_desc = smode_desc;
	}
	
	private String is_insured;
	public String getIs_insured() {
		return is_insured;
	}
	public void setIs_insured(String is_insured) {
		this.is_insured = is_insured;
	}
	
	private String rate;
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	private String mix_insured;
	public String getMix_insured() {
		return mix_insured;
	}
	public void setMix_insured(String mix_insured) {
		this.mix_insured = mix_insured;
	}
	
	private String max_insured;
	public String getMax_insured() {
		return max_insured;
	}
	public void setMax_insured(String max_insured) {
		this.max_insured = max_insured;
	}
	
	private String reach_pay;
	public String getReach_pay() {
		return reach_pay;
	}
	public void setReach_pay(String reach_pay) {
		this.reach_pay = reach_pay;
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
		builder.append("Sendmode[");
		builder.append(", smode_id=");
		builder.append(this.smode_id);
		builder.append(", smode_name=");
		builder.append(this.smode_name);
		builder.append(", smode_desc=");
		builder.append(this.smode_desc);
		builder.append(", is_insured=");
		builder.append(this.is_insured);
		builder.append(", rate=");
		builder.append(this.rate);
		builder.append(", mix_insured=");
		builder.append(this.mix_insured);
		builder.append(", max_insured=");
		builder.append(this.max_insured);
		builder.append(", reach_pay=");
		builder.append(this.reach_pay);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append("]");
		return builder.toString();
	}

}

