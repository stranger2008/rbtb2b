/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Goodsbrand.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商品品牌表实体
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Feb 27 12:42:32 CST 2012
 */
public class Goodsbrand implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String brand_id;
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	
	private String brand_name;
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	
	private String brand_site;
	public String getBrand_site() {
		return brand_site;
	}
	public void setBrand_site(String brand_site) {
		this.brand_site = brand_site;
	}
	
	private String logo;
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private String goods_attr;
	public String getGoods_attr() {
		return goods_attr;
	}
	public void setGoods_attr(String goods_attr) {
		this.goods_attr = goods_attr;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	private String is_recom;
	public String getIs_recom() {
		return is_recom;
	}
	public void setIs_recom(String is_recom) {
		this.is_recom = is_recom;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goodsbrand[");
		builder.append(", brand_id=");
		builder.append(this.brand_id);
		builder.append(", brand_name=");
		builder.append(this.brand_name);
		builder.append(", brand_site=");
		builder.append(this.brand_site);
		builder.append(", logo=");
		builder.append(this.logo);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", goods_attr=");
		builder.append(this.goods_attr);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", is_recom=");
		builder.append(this.is_recom);
		builder.append("]");
		return builder.toString();
	}

}

