/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Category.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 分类信息表实体
 * @author 创建人 林俊钦
 * @date 创建日期 Tue Jul 12 13:04:58 CST 2011
 */
public class Category implements Serializable {

	static final long serialVersionUID = 1L;	
	
	String cat_id;
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	
	String cat_name;
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	
	String en_name;
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	
	String word_index;
	public String getWord_index() {
		return word_index;
	}
	public void setWord_index(String word_index) {
		this.word_index = word_index;
	}
	
	String up_cat_id;
	public String getUp_cat_id() {
		return up_cat_id;
	}
	public void setUp_cat_id(String up_cat_id) {
		this.up_cat_id = up_cat_id;
	}
	
	String cat_level;
	public String getCat_level() {
		return cat_level;
	}
	public void setCat_level(String cat_level) {
		this.cat_level = cat_level;
	}
	
	String module_type;
	public String getModule_type() {
		return module_type;
	}
	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	
	String is_display;
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	}
	
	String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	String member_add;
	public String getMember_add() {
		return member_add;
	}
	public void setMember_add(String member_add) {
		this.member_add = member_add;
	}
	
	String seotitle;
	public String getSeotitle() {
		return seotitle;
	}
	public void setSeotitle(String seotitle) {
		this.seotitle = seotitle;
	}
	
	String seokeyword;
	public String getSeokeyword() {
		return seokeyword;
	}
	public void setSeokeyword(String seokeyword) {
		this.seokeyword = seokeyword;
	}
	
	String seodesc;
	public String getSeodesc() {
		return seodesc;
	}
	public void setSeodesc(String seodesc) {
		this.seodesc = seodesc;
	}
	String mem_type;
	public String getMem_type() {
		return mem_type;
	}
	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}
	String cat_intr;
	public String getCat_intr() {
		return cat_intr;
	}
	public void setCat_intr(String cat_intr) {
		this.cat_intr = cat_intr;
	}
	
	String cat_simple;
	public String getCat_simple() {
		return cat_simple;
	}
	public void setCat_simple(String cat_simple) {
		this.cat_simple = cat_simple;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category[");
		builder.append(", cat_id=");
		builder.append(this.cat_id);
		builder.append(", cat_name=");
		builder.append(this.cat_name);
		builder.append(", en_name=");
		builder.append(this.en_name);
		builder.append(", word_index=");
		builder.append(this.word_index);
		builder.append(", up_cat_id=");
		builder.append(this.up_cat_id);
		builder.append(", cat_level=");
		builder.append(this.cat_level);
		builder.append(", module_type=");
		builder.append(this.module_type);
		builder.append(", is_display=");
		builder.append(this.is_display);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", member_add=");
		builder.append(this.member_add);
		builder.append(", seotitle=");
		builder.append(this.seotitle);
		builder.append(", mem_type=");
		builder.append(this.mem_type);
		builder.append(", seokeyword=");
		builder.append(this.seokeyword);
		builder.append(", seodesc=");
		builder.append(this.seodesc);
		builder.append(", cat_intr=");
		builder.append(this.cat_intr);
		builder.append("]");
		return builder.toString();
	}

}

