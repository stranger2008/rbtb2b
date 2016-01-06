/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Memberconfig.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录会员企业站设置信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Aug 29 16:12:02 CST 2011
 */
public class Memberconfig implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String temp_code;
	public String getTemp_code() {
		return temp_code;
	}
	public void setTemp_code(String temp_code) {
		this.temp_code = temp_code;
	}
	
	String temp_loc;
	public String getTemp_loc() {
		return temp_loc;
	}
	public void setTemp_loc(String temp_loc) {
		this.temp_loc = temp_loc;
	}
	
	String is_dis;
	public String getIs_dis() {
		return is_dis;
	}
	public void setIs_dis(String is_dis) {
		this.is_dis = is_dis;
	}
	
	String loc_num;
	public String getLoc_num() {
		return loc_num;
	}
	public void setLoc_num(String loc_num) {
		this.loc_num = loc_num;
	}
	
	String back_color;
	public String getBack_color() {
		return back_color;
	}
	public void setBack_color(String back_color) {
		this.back_color = back_color;
	}
	
	String word_num;
	public String getWord_num() {
		return word_num;
	}
	public void setWord_num(String word_num) {
		this.word_num = word_num;
	}
	
	String back_img;
	public String getBack_img() {
		return back_img;
	}
	public void setBack_img(String back_img) {
		this.back_img = back_img;
	}
	
	String back_img_repeat;
	public String getBack_img_repeat() {
		return back_img_repeat;
	}
	public void setBack_img_repeat(String back_img_repeat) {
		this.back_img_repeat = back_img_repeat;
	}
	
	String back_img_position;
	public String getBack_img_position() {
		return back_img_position;
	}
	public void setBack_img_position(String back_img_position) {
		this.back_img_position = back_img_position;
	}
	
	String logo_img;
	public String getLogo_img() {
		return logo_img;
	}
	public void setLogo_img(String logo_img) {
		this.logo_img = logo_img;
	}
	
	String header_img;
	public String getHeader_img() {
		return header_img;
	}
	public void setHeader_img(String header_img) {
		this.header_img = header_img;
	}
	
	String site_notice;
	public String getSite_notice() {
		return site_notice;
	}
	public void setSite_notice(String site_notice) {
		this.site_notice = site_notice;
	}
	
	String web_name;
	public String getWeb_name() {
		return web_name;
	}
	public void setWeb_name(String web_name) {
		this.web_name = web_name;
	}
	
	String web_title;
	public String getWeb_title() {
		return web_title;
	}
	public void setWeb_title(String web_title) {
		this.web_title = web_title;
	}
	
	String keywords;
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	String site_desc;
	public String getSite_desc() {
		return site_desc;
	}
	public void setSite_desc(String site_desc) {
		this.site_desc = site_desc;
	}
	
	String reg_date;
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	String banner;
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	
	String qq;
	String aliim;
	String msn;
	String skype;
	
	
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getAliim() {
		return aliim;
	}
	public void setAliim(String aliim) {
		this.aliim = aliim;
	}
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memberconfig[");
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", temp_code=");
		builder.append(this.temp_code);
		builder.append(", temp_loc=");
		builder.append(this.temp_loc);
		builder.append(", is_dis=");
		builder.append(this.is_dis);
		builder.append(", loc_num=");
		builder.append(this.loc_num);
		builder.append(", back_color=");
		builder.append(this.back_color);
		builder.append(", word_num=");
		builder.append(this.word_num);
		builder.append(", back_img=");
		builder.append(this.back_img);
		builder.append(", back_img_repeat=");
		builder.append(this.back_img_repeat);
		builder.append(", back_img_position=");
		builder.append(this.back_img_position);
		builder.append(", logo_img=");
		builder.append(this.logo_img);
		builder.append(", header_img=");
		builder.append(this.header_img);
		builder.append(", site_notice=");
		builder.append(this.site_notice);
		builder.append(", web_name=");
		builder.append(this.web_name);
		builder.append(", web_title=");
		builder.append(this.web_title);
		builder.append(", keywords=");
		builder.append(this.keywords);
		builder.append(", site_desc=");
		builder.append(this.site_desc);
		builder.append(", reg_date=");
		builder.append(this.reg_date);
		builder.append(", qq=");
		builder.append(this.qq);
		builder.append(", aliim=");
		builder.append(this.aliim);
		builder.append(", msn=");
		builder.append(this.msn);
		builder.append(", skype=");
		builder.append(this.skype);
		builder.append("]");
		return builder.toString();
	}

}

