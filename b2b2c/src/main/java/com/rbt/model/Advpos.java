/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Advpos.java 
 */
package com.rbt.model;

/**
 * @function 功能 广告位表pojo类
 * @author 创建人 邱景岩
 * @date 创建日期 Jul 7, 2011 4:00:59 PM
 */
public class Advpos {
	
	/*
	 * 是否系统广告位
	 */
	String sys_adv;
	/*
	 * 广告位唯一标识
	 */
	String pos_id;
	/*
	 * 广告位名称
	 */
	String pos_name;
	/*
	 * 广告位介绍
	 */
	String pos_desc;
	/*
	 * 广告位类型
	 */
	String pos_type;
	/*
	 * 宽
	 */
	String p_width;
	/*
	 * 高
	 */
	String p_height;
	/*
	 * 模板类型
	 */
	String module_type;
	/*
	 * 价格
	 */
	String price;
	/*
	 * 无广告代码
	 */
	String default_code;
	/*
	 * 前台是否显示
	 */
	String isshow;
	/*
	 * 排序
	 */
	String sort_no;
	/*
	 * 示意图
	 */
    String img_path;
    
    /*广告所属模块*/
    String  adv_pos;
    
    
	public String getAdv_pos() {
		return adv_pos;
	}

	public void setAdv_pos(String adv_pos) {
		this.adv_pos = adv_pos;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	/**
	 * @return the pos_id
	 */
	public String getPos_id() {
		return pos_id;
	}

	/**
	 * @param pos_id
	 *            the pos_id to set
	 */
	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}

	/**
	 * @return the pos_name
	 */
	public String getPos_name() {
		return pos_name;
	}

	/**
	 * @param pos_name
	 *            the pos_name to set
	 */
	public void setPos_name(String pos_name) {
		this.pos_name = pos_name;
	}

	/**
	 * @return the pos_desc
	 */
	public String getPos_desc() {
		return pos_desc;
	}

	/**
	 * @param pos_desc
	 *            the pos_desc to set
	 */
	public void setPos_desc(String pos_desc) {
		this.pos_desc = pos_desc;
	}

	/**
	 * @return the pos_type
	 */
	public String getPos_type() {
		return pos_type;
	}

	/**
	 * @param pos_type
	 *            the pos_type to set
	 */
	public void setPos_type(String pos_type) {
		this.pos_type = pos_type;
	}



	public String getP_width() {
		return p_width;
	}

	public void setP_width(String p_width) {
		this.p_width = p_width;
	}

	public String getP_height() {
		return p_height;
	}

	public void setP_height(String p_height) {
		this.p_height = p_height;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the default_code
	 */
	public String getDefault_code() {
		return default_code;
	}

	/**
	 * @param default_code
	 *            the default_code to set
	 */
	public void setDefault_code(String default_code) {
		this.default_code = default_code;
	}

	/**
	 * @return the isshow
	 */
	public String getIsshow() {
		return isshow;
	}

	/**
	 * @param isshow
	 *            the isshow to set
	 */
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	/**
	 * @return the sort_no
	 */
	public String getSort_no() {
		return sort_no;
	}

	/**
	 * @param sort_no
	 *            the sort_no to set
	 */
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}

	public String getModule_type() {
		return module_type;
	}

	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}

	public String getSys_adv() {
		return sys_adv;
	}

	public void setSys_adv(String sys_adv) {
		this.sys_adv = sys_adv;
	}
	
}
