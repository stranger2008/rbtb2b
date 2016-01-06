/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Supply.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 供应表实体
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Jul 21 17:15:43 CST 2011
 */
public class Supply implements Serializable {

	static final long serialVersionUID = 1L;
	
	/*
	 * onlineorder:是否支持在线订购 0：否 1：是
	 * shipfee 运费
	 */
	String onlineorder;
	String shipfee;
	
	String supply_id;
	public String getSupply_id() {
		return supply_id;
	}
	public void setSupply_id(String supply_id) {
		this.supply_id = supply_id;
	}
	
	String cat_name;
	String cat_id;
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	String supply_type;
	public String getSupply_type() {
		return supply_type;
	}
	public void setSupply_type(String supply_type) {
		this.supply_type = supply_type;
	}
	
	String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	String infoattr_id;
	public String getInfoattr_id() {
		return infoattr_id;
	}
	public void setInfoattr_id(String infoattr_id) {
		this.infoattr_id = infoattr_id;
	}
	
	String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	String self_cat_id;
	public String getSelf_cat_id() {
		return self_cat_id;
	}
	public void setSelf_cat_id(String self_cat_id) {
		this.self_cat_id = self_cat_id;
	}
	
	String model;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	String standard;
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	String brand;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	String unit;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	String price;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	String min_order;
	public String getMin_order() {
		return min_order;
	}
	public void setMin_order(String min_order) {
		this.min_order = min_order;
	}
	
	String max_supply;
	public String getMax_supply() {
		return max_supply;
	}
	public void setMax_supply(String max_supply) {
		this.max_supply = max_supply;
	}
	
	String send_day;
	public String getSend_day() {
		return send_day;
	}
	public void setSend_day(String send_day) {
		this.send_day = send_day;
	}
	
	String mem_recom;
	public String getMem_recom() {
		return mem_recom;
	}
	public void setMem_recom(String mem_recom) {
		this.mem_recom = mem_recom;
	}
	
	String end_date;
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	String is_recom;
	public String getIs_recom() {
		return is_recom;
	}
	public void setIs_recom(String is_recom) {
		this.is_recom = is_recom;
	}
	
	String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	String no_reason;
	public String getNo_reason() {
		return no_reason;
	}
	public void setNo_reason(String no_reason) {
		this.no_reason = no_reason;
	}
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	String clicknum;
	public String getClicknum() {
		return clicknum;
	}
	public void setClicknum(String clicknum) {
		this.clicknum = clicknum;
	}
	
	String fare;
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	
	String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Supply[");
		builder.append(", supply_id=");
		builder.append(this.supply_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", supply_type=");
		builder.append(this.supply_type);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", attr_desc=");
		builder.append(this.infoattr_id);
		builder.append(", infoattr_id=");
		builder.append(this.area_attr);
		builder.append(", self_cat_id=");
		builder.append(this.self_cat_id);
		builder.append(", model=");
		builder.append(this.model);
		builder.append(", standard=");
		builder.append(this.standard);
		builder.append(", brand=");
		builder.append(this.brand);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", unit=");
		builder.append(this.unit);
		builder.append(", price=");
		builder.append(this.price);
		builder.append(", min_order=");
		builder.append(this.min_order);
		builder.append(", max_supply=");
		builder.append(this.max_supply);
		builder.append(", send_day=");
		builder.append(this.send_day);
		builder.append(", mem_recom=");
		builder.append(this.mem_recom);
		builder.append(", end_date=");
		builder.append(this.end_date);
		builder.append(", is_recom=");
		builder.append(this.is_recom);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", no_reason=");
		builder.append(this.no_reason);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", clicknum=");
		builder.append(this.clicknum);
		builder.append(", fare=");
		builder.append(this.fare);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append("]");
		return builder.toString();
	}
	/**
	 * @return the cat_name
	 */
	public String getCat_name() {
		return cat_name;
	}
	/**
	 * @param cat_name the cat_name to set
	 */
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	/**
	 * @return the cat_id
	 */
	public String getCat_id() {
		return cat_id;
	}
	/**
	 * @param cat_id the cat_id to set
	 */
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public String getOnlineorder() {
		return onlineorder;
	}
	public void setOnlineorder(String onlineorder) {
		this.onlineorder = onlineorder;
	}
	public String getShipfee() {
		return shipfee;
	}
	public void setShipfee(String shipfee) {
		this.shipfee = shipfee;
	}

}

