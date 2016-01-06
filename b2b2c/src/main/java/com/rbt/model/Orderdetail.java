/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Orderdetail.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 订单商品详细实体
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Mar 28 17:27:56 CST 2012
 */
public class Orderdetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String trade_id;
	public String getTrade_id() {
		return trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String order_price;
	public String getOrder_price() {
		return order_price;
	}
	public void setOrder_price(String order_price) {
		this.order_price = order_price;
	}
	
	private String order_num;
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	
	private String goods_attr;
	public String getGoods_attr() {
		return goods_attr;
	}
	public void setGoods_attr(String goods_attr) {
		this.goods_attr = goods_attr;
	}
	
	private String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Orderdetail[");
		builder.append(", trade_id=");
		builder.append(this.trade_id);
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", order_price=");
		builder.append(this.order_price);
		builder.append(", order_num=");
		builder.append(this.order_num);
		builder.append(", goods_attr=");
		builder.append(this.goods_attr);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append("]");
		return builder.toString();
	}

}

