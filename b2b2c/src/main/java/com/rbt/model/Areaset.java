/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Areaset.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录区域设置信息实体
 * @author 创建人 胡惜坤
 * @date 创建日期 Wed Mar 28 13:22:27 CST 2012
 */
public class Areaset implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String default_ship;
	
	private String areaset_id;
	public String getAreaset_id() {
		return areaset_id;
	}
	public void setAreaset_id(String areaset_id) {
		this.areaset_id = areaset_id;
	}

	private String end_area;
	public String getEnd_area() {
		return end_area;
	}
	public void setEnd_area(String end_area) {
		this.end_area = end_area;
	}
	
	private String first_price;
	public String getFirst_price() {
		return first_price;
	}
	public void setFirst_price(String first_price) {
		this.first_price = first_price;
	}
	
	private String cont_weight;
	public String getCont_weight() {
		return cont_weight;
	}
	public void setCont_weight(String cont_weight) {
		this.cont_weight = cont_weight;
	}
	
	private String cont_price;
	public String getCont_price() {
		return cont_price;
	}
	public void setCont_price(String cont_price) {
		this.cont_price = cont_price;
	}
	
	private String first_weight;
	public String getFirst_weight() {
		return first_weight;
	}
	public void setFirst_weight(String first_weight) {
		this.first_weight = first_weight;
	}
	
	private String smode_id;
	public String getSmode_id() {
		return smode_id;
	}
	public void setSmode_id(String smode_id) {
		this.smode_id = smode_id;
	}
	
	private String ship_id;
	public String getShip_id() {
		return ship_id;
	}
	public void setShip_id(String ship_id) {
		this.ship_id = ship_id;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Areaset[");
		builder.append(", areaset_id=");
		builder.append(this.areaset_id);
		builder.append(", end_area=");
		builder.append(this.end_area);
		builder.append(", first_price=");
		builder.append(this.first_price);
		builder.append(", cont_weight=");
		builder.append(this.cont_weight);
		builder.append(", cont_price=");
		builder.append(this.cont_price);
		builder.append(", first_weight=");
		builder.append(this.first_weight);
		builder.append(", smode_id=");
		builder.append(this.smode_id);
		builder.append("]");
		return builder.toString();
	}
	public String getDefault_ship() {
		return default_ship;
	}
	public void setDefault_ship(String default_ship) {
		this.default_ship = default_ship;
	}

}

