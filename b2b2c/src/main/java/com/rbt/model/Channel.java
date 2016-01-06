/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Channel.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录网站栏目信息实体
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 15 10:57:10 CST 2011
 */
public class Channel implements Serializable {

	static final long serialVersionUID = 1L;
	
	String sys_ch;
	
	
	
	String ch_id;
	public String getCh_id() {
		return ch_id;
	}
	public void setCh_id(String ch_id) {
		this.ch_id = ch_id;
	}
	
	String ch_name;
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
	
	String up_ch_id;
	public String getUp_ch_id() {
		return up_ch_id;
	}
	public void setUp_ch_id(String up_ch_id) {
		this.up_ch_id = up_ch_id;
	}
	
	String ch_level;
	public String getCh_level() {
		return ch_level;
	}
	public void setCh_level(String ch_level) {
		this.ch_level = ch_level;
	}
	
	String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	String save_dir;
	public String getSave_dir() {
		return save_dir;
	}
	public void setSave_dir(String save_dir) {
		this.save_dir = save_dir;
	}
	
	String file_name;
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	String temp_path;
	public String getTemp_path() {
		return temp_path;
	}
	public void setTemp_path(String temp_path) {
		this.temp_path = temp_path;
	}
	
	String meta_keyword;
	public String getMeta_keyword() {
		return meta_keyword;
	}
	public void setMeta_keyword(String meta_keyword) {
		this.meta_keyword = meta_keyword;
	}
	
	String meta_desc;
	public String getMeta_desc() {
		return meta_desc;
	}
	public void setMeta_desc(String meta_desc) {
		this.meta_desc = meta_desc;
	}
	
	String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	String ch_title;
	String module_type;
	String article_temp;
	String article_rule;
	String plat_type;
	
	
	public String getPlat_type() {
		return plat_type;
	}
	public void setPlat_type(String plat_type) {
		this.plat_type = plat_type;
	}
	public String getCh_title() {
		return ch_title;
	}
	public void setCh_title(String ch_title) {
		this.ch_title = ch_title;
	}
	public String getModule_type() {
		return module_type;
	}
	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	public String getArticle_temp() {
		return article_temp;
	}
	public void setArticle_temp(String article_temp) {
		this.article_temp = article_temp;
	}
	public String getArticle_rule() {
		return article_rule;
	}
	public void setArticle_rule(String article_rule) {
		this.article_rule = article_rule;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Channel[");
		builder.append(", ch_id=");
		builder.append(this.ch_id);
		builder.append(", ch_name=");
		builder.append(this.ch_name);
		builder.append(", up_ch_id=");
		builder.append(this.up_ch_id);
		builder.append(", ch_level=");
		builder.append(this.ch_level);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", save_dir=");
		builder.append(this.save_dir);
		builder.append(", file_name=");
		builder.append(this.file_name);
		builder.append(", temp_path=");
		builder.append(this.temp_path);
		builder.append(", meta_keyword=");
		builder.append(this.meta_keyword);
		builder.append(", meta_desc=");
		builder.append(this.meta_desc);
		builder.append(", remark=");
		builder.append(this.remark);		
		builder.append(", ch_title=");
		builder.append(this.ch_title);
		builder.append(", module_type=");
		builder.append(this.module_type);
		builder.append(", article_temp=");
		builder.append(this.article_temp);
		builder.append(", article_rule=");
		builder.append(this.article_rule);
		builder.append(", sys_ch=");
		builder.append(this.sys_ch);
		builder.append(", plat_type=");
		builder.append(this.plat_type);
		builder.append("]");
		return builder.toString();
	}
	public String getSys_ch() {
		return sys_ch;
	}
	public void setSys_ch(String sys_ch) {
		this.sys_ch = sys_ch;
	}

}

