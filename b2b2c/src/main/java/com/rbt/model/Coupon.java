/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Coupon.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录优惠卷表信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Mar 22 13:38:28 CST 2012
 */
public class Coupon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String c_id;
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String c_name;
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	
	private String c_img;
	public String getC_img() {
		return c_img;
	}
	public void setC_img(String c_img) {
		this.c_img = c_img;
	}
	
	private String c_type;
	public String getC_type() {
		return c_type;
	}
	public void setC_type(String c_type) {
		this.c_type = c_type;
	}
	
	private String c_inter;
	public String getC_inter() {
		return c_inter;
	}
	public void setC_inter(String c_inter) {
		this.c_inter = c_inter;
	}
	
	private String c_money;
	public String getC_money() {
		return c_money;
	}
	public void setC_money(String c_money) {
		this.c_money = c_money;
	}
	
	private String c_discount;
	public String getC_discount() {
		return c_discount;
	}
	public void setC_discount(String c_discount) {
		this.c_discount = c_discount;
	}
	
	private String coupon_times;
	public String getCoupon_times() {
		return coupon_times;
	}
	public void setCoupon_times(String coupon_times) {
		this.coupon_times = coupon_times;
	}
	
	private String start_time;
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	
	private String end_time;
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	private String is_every;
	public String getIs_every() {
		return is_every;
	}
	public void setIs_every(String is_every) {
		this.is_every = is_every;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Coupon[");
		builder.append(", c_id=");
		builder.append(this.c_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", c_name=");
		builder.append(this.c_name);
		builder.append(", c_img=");
		builder.append(this.c_img);
		builder.append(", c_type=");
		builder.append(this.c_type);
		builder.append(", c_inter=");
		builder.append(this.c_inter);
		builder.append(", c_money=");
		builder.append(this.c_money);
		builder.append(", c_discount=");
		builder.append(this.c_discount);
		builder.append(", coupon_times=");
		builder.append(this.coupon_times);
		builder.append(", start_time=");
		builder.append(this.start_time);
		builder.append(", end_time=");
		builder.append(this.end_time);
		builder.append(", is_every=");
		builder.append(this.is_every);
		builder.append("]");
		return builder.toString();
	}

}

