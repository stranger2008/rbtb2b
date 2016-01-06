/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Shopconfig.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 商店设置实体
 * @author 创建人 hxk
 * @date 创建日期 Tue Feb 28 10:24:54 CST 2012
 */
public class Shopconfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String shop_id;
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String shop_name;
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	
	private String shop_logo;
	public String getShop_logo() {
		return shop_logo;
	}
	public void setShop_logo(String shop_logo) {
		this.shop_logo = shop_logo;
	}
	
	private String shop_site;
	public String getShop_site() {
		return shop_site;
	}
	public void setShop_site(String shop_site) {
		this.shop_site = shop_site;
	}
	
	private String shop_intro;
	public String getShop_intro() {
		return shop_intro;
	}
	public void setShop_intro(String shop_intro) {
		this.shop_intro = shop_intro;
	}
	
	private String busi_range;
	public String getBusi_range() {
		return busi_range;
	}
	public void setBusi_range(String busi_range) {
		this.busi_range = busi_range;
	}
	
	private String busi_mode;
	public String getBusi_mode() {
		return busi_mode;
	}
	public void setBusi_mode(String busi_mode) {
		this.busi_mode = busi_mode;
	}
	
	private String contant_man;
	public String getContant_man() {
		return contant_man;
	}
	public void setContant_man(String contant_man) {
		this.contant_man = contant_man;
	}
	
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private String qq;
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	private String msn;
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	
	private String alliwang;
	public String getAlliwang() {
		return alliwang;
	}
	public void setAlliwang(String alliwang) {
		this.alliwang = alliwang;
	}
	
	private String mobile;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String is_colse;
	public String getIs_colse() {
		return is_colse;
	}
	public void setIs_colse(String is_colse) {
		this.is_colse = is_colse;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	

	private String banner_image;
	private String adv_image;

	public String getBanner_image() {
		return banner_image;
	}
	public void setBanner_image(String banner_image) {
		this.banner_image = banner_image;
	}
	public String getAdv_image() {
		return adv_image;
	}
	public void setAdv_image(String adv_image) {
		this.adv_image = adv_image;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shopconfig[");
		builder.append(", shop_id=");
		builder.append(this.shop_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", shop_name=");
		builder.append(this.shop_name);
		builder.append(", shop_logo=");
		builder.append(this.shop_logo);
		builder.append(", shop_site=");
		builder.append(this.shop_site);
		builder.append(", shop_intro=");
		builder.append(this.shop_intro);
		builder.append(", busi_range=");
		builder.append(this.busi_range);
		builder.append(", busi_mode=");
		builder.append(this.busi_mode);
		builder.append(", contant_man=");
		builder.append(this.contant_man);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", qq=");
		builder.append(this.qq);
		builder.append(", msn=");
		builder.append(this.msn);
		builder.append(", alliwang=");
		builder.append(this.alliwang);
		builder.append(", mobile=");
		builder.append(this.mobile);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", address=");
		builder.append(this.address);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", is_colse=");
		builder.append(this.is_colse);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", banner_image=");
		builder.append(this.banner_image);
		builder.append(", adv_image=");
		builder.append(this.adv_image);
		builder.append("]");
		return builder.toString();
	}

}

