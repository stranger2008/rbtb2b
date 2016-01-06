/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Tradecomment.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录交易评价实体
 * @author 创建人 林俊钦
 * @date 创建日期 Sat Nov 26 13:03:51 CST 2011
 */
public class Tradecomment implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String id) {
		this.trade_id = id;
	}
	
	String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	String com_way;
	public String getCom_way() {
		return com_way;
	}
	public void setCom_way(String com_way) {
		this.com_way = com_way;
	}
	
	String set_cust_id;
	public String getSet_cust_id() {
		return set_cust_id;
	}
	public void setSet_cust_id(String set_cust_id) {
		this.set_cust_id = set_cust_id;
	}
	
	String get_cust_id;
	public String getGet_cust_id() {
		return get_cust_id;
	}
	public void setGet_cust_id(String get_cust_id) {
		this.get_cust_id = get_cust_id;
	}
	
	String com_type;
	public String getCom_type() {
		return com_type;
	}
	public void setCom_type(String com_type) {
		this.com_type = com_type;
	}
	
	String is_score;
	public String getIs_score() {
		return is_score;
	}
	public void setIs_score(String is_score) {
		this.is_score = is_score;
	}
	
	String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	String desc_num;
	public String getDesc_num() {
		return desc_num;
	}
	public void setDesc_num(String desc_num) {
		this.desc_num = desc_num;
	}
	
	String service_num;
	public String getService_num() {
		return service_num;
	}
	public void setService_num(String service_num) {
		this.service_num = service_num;
	}
	
	String seller_num;
	public String getSeller_num() {
		return seller_num;
	}
	public void setSeller_num(String seller_num) {
		this.seller_num = seller_num;
	}
	
	String ship_num;
	public String getShip_num() {
		return ship_num;
	}
	public void setShip_num(String ship_num) {
		this.ship_num = ship_num;
	}
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tradecomment[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", com_way=");
		builder.append(this.com_way);
		builder.append(", set_cust_id=");
		builder.append(this.set_cust_id);
		builder.append(", get_cust_id=");
		builder.append(this.get_cust_id);
		builder.append(", com_type=");
		builder.append(this.com_type);
		builder.append(", is_score=");
		builder.append(this.is_score);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", desc_num=");
		builder.append(this.desc_num);
		builder.append(", service_num=");
		builder.append(this.service_num);
		builder.append(", seller_num=");
		builder.append(this.seller_num);
		builder.append(", ship_num=");
		builder.append(this.ship_num);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}

}

