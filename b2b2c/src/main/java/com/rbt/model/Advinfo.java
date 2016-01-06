/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Advinfo.java 
 */
package com.rbt.model;

/**
 * @function 功能 广告信息表pojo类
 * @author  创建人 邱景岩
 * @date  创建日期 Jul 7, 2011 4:07:21 PM
 */

public class Advinfo {
	/*
	 * 广告信息唯一标识
	 */
	String adv_id;
	/*
	 * 广告位唯一标识
	 */
	String pos_id;
	/*
	 * 广告名称
	 */
	String adv_name;
	/*
	 * 广告介绍
	 */
	String adv_desc;
	/*
	 * 关键字
	 */
	String keyword;
	/*
	 * 所属模块
	 */
	String module_type;
	/*
	 * 信息ID
	 */
	String info_id;
	/*
	 * 分类标识串
	 */
	String cat_attr;
	/*
	 * 广告代码
	 */
	String adv_code;
	/*
	 * 广告图片
	 */
	String img_path;
	/*
	 * flash地址
	 */
	String flash_url;
	/*
	 * 链接地址
	 */
	String link_url;
	/*
	 * 显示标题
	 */
	String title;
	/*
	 * 显示描述
	 */
	String content;
	/*
	 * 开始时间
	 */
	String start_date;
	/*
	 * 结束时间
	 */
	String end_date;
	/*
	 * 备注
	 */
	String remark;
	/*
	 * 客户标识
	 */
	String cust_id;
	/*
	 * 广告状态
	 */
	String adv_state;
	/*
	 * 点击统计
	 */
	String iscount;
	/*
	 * 点击次数
	 */
	String addnum;
	/*
	 * 地区
	 */
	String area_attr;
	
	/**
	 * @return the adv_id
	 */
	public String getAdv_id() {
		return adv_id;
	}
	/**
	 * @param adv_id the adv_id to set
	 */
	public void setAdv_id(String adv_id) {
		this.adv_id = adv_id;
	}
	/**
	 * @return the pos_id
	 */
	public String getPos_id() {
		return pos_id;
	}
	/**
	 * @param pos_id the pos_id to set
	 */
	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}
	/**
	 * @return the module_type
	 */
	public String getModule_type() {
		return module_type;
	}
	/**
	 * @param module_type the module_type to set
	 */
	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	/**
	 * @return the cat_attr
	 */
	public String getCat_attr() {
		return cat_attr;
	}
	/**
	 * @param cat_attr the cat_attr to set
	 */
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	/**
	 * @return the adv_name
	 */
	public String getAdv_name() {
		return adv_name;
	}
	/**
	 * @param adv_name the adv_name to set
	 */
	public void setAdv_name(String adv_name) {
		this.adv_name = adv_name;
	}
	/**
	 * @return the adv_desc
	 */
	public String getAdv_desc() {
		return adv_desc;
	}
	/**
	 * @param adv_desc the adv_desc to set
	 */
	public void setAdv_desc(String adv_desc) {
		this.adv_desc = adv_desc;
	}
	/**
	 * @return the img_path
	 */
	public String getImg_path() {
		return img_path;
	}
	/**
	 * @param img_path the img_path to set
	 */
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	/**
	 * @return the link_url
	 */
	public String getLink_url() {
		return link_url;
	}
	/**
	 * @param link_url the link_url to set
	 */
	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the start_date
	 */
	public String getStart_date() {
		return start_date;
	}
	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	/**
	 * @return the end_date
	 */
	public String getEnd_date() {
		return end_date;
	}
	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the cust_id
	 */
	public String getCust_id() {
		return cust_id;
	}
	/**
	 * @param cust_id the cust_id to set
	 */
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	/**
	 * @return the adv_state
	 */
	public String getAdv_state() {
		return adv_state;
	}
	/**
	 * @param adv_state the adv_state to set
	 */
	public void setAdv_state(String adv_state) {
		this.adv_state = adv_state;
	}
	/**
	 * @return the iscount
	 */
	public String getIscount() {
		return iscount;
	}
	/**
	 * @param iscount the iscount to set
	 */
	public void setIscount(String iscount) {
		this.iscount = iscount;
	}
	/**
	 * @return the addnum
	 */
	public String getAddnum() {
		return addnum;
	}
	/**
	 * @param addnum the addnum to set
	 */
	public void setAddnum(String addnum) {
		this.addnum = addnum;
	}
	
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	public String getAdv_code() {
		return adv_code;
	}
	public void setAdv_code(String adv_code) {
		this.adv_code = adv_code;
	}
	public String getFlash_url() {
		return flash_url;
	}
	public void setFlash_url(String flash_url) {
		this.flash_url = flash_url;
	}
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
}
