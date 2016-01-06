/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Member.java 
 */
package com.rbt.model;

import java.io.Serializable;

/**
 * @function 功能 会员实体
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 13 08:48:07 CST 2011
 */
public class Member implements Serializable {

	static final long serialVersionUID = 1L;

	String is_active;
	
	
	
	/*
	 * 会员唯一标识
	 */
	String cust_id;

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	/*
	 * 会员名称
	 */
	String cust_name;

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	/*
	 * 企业logo
	 */
	String logo_path;

	public String getLogo_path() {
		return logo_path;
	}

	public void setLogo_path(String logo_path) {
		this.logo_path = logo_path;
	}

	/*
	 * 会员类型
	 */
	String mem_type;

	public String getMem_type() {
		return mem_type;
	}

	public void setMem_type(String mem_type) {
		this.mem_type = mem_type;
	}

	/*
	 * 会员状态
	 */
	String info_state;



	/*
	 * 不通过理由
	 */
	String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	/*
	 * 企业类别
	 */
	String cust_rage;

	public String getCust_rage() {
		return cust_rage;
	}

	public void setCust_rage(String cust_rage) {
		this.cust_rage = cust_rage;
	}

	/*
	 * 供应产品（服务）
	 */
	String cust_supply;

	public String getCust_supply() {
		return cust_supply;
	}

	public void setCust_supply(String cust_supply) {
		this.cust_supply = cust_supply;
	}

	/*
	 * 采购产品（服务）
	 */
	String cust_stock;

	public String getCust_stock() {
		return cust_stock;
	}

	public void setCust_stock(String cust_stock) {
		this.cust_stock = cust_stock;
	}

	/*
	 * 企业类型
	 */
	String cust_type;

	public String getCust_type() {
		return cust_type;
	}

	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}

	/*
	 * 经营模式
	 */
	String client_status;

	public String getClient_status() {
		return client_status;
	}

	public void setClient_status(String client_status) {
		this.client_status = client_status;
	}

	/*
	 * 推荐
	 */
	String recommend;

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	/*
	 * 公司关健词
	 */
	String cust_key;

	public String getCust_key() {
		return cust_key;
	}

	public void setCust_key(String cust_key) {
		this.cust_key = cust_key;
	}

	/*
	 * 公司经营范围
	 */
	String main_product;

	public String getMain_product() {
		return main_product;
	}

	public void setMain_product(String main_product) {
		this.main_product = main_product;
	}

	/*
	 * 公司介绍
	 */
	String cust_desc;

	public String getCust_desc() {
		return cust_desc;
	}

	public void setCust_desc(String cust_desc) {
		this.cust_desc = cust_desc;
	}

	/*
	 * 分类串
	 */
	String cat_attr;

	/*
	 * 地区串
	 */
	String area_attr;

	public String getArea_attr() {
		return area_attr;
	}

	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}

	/*
	 * 经营地址
	 */
	String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/*
	 * 公司电话
	 */
	String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/*
	 * 公司传真
	 */
	String fax;

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	/*
	 * 邮政编码
	 */
	String post_code;

	public String getPost_code() {
		return post_code;
	}

	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	/*
	 * 电子邮箱
	 */
	String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * 公司网址
	 */
	String website;

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	/*
	 * 联系人姓名
	 */
	String contact_name;

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	/*
	 * 联系人部门
	 */
	String contact_depart;

	public String getContact_depart() {
		return contact_depart;
	}

	public void setContact_depart(String contact_depart) {
		this.contact_depart = contact_depart;
	}

	/*
	 * 联系人职位
	 */
	String contact_job;

	public String getContact_job() {
		return contact_job;
	}

	public void setContact_job(String contact_job) {
		this.contact_job = contact_job;
	}

	/*
	 * 联系人性别
	 */
	String contact_sex;

	public String getContact_sex() {
		return contact_sex;
	}

	public void setContact_sex(String contact_sex) {
		this.contact_sex = contact_sex;
	}

	/*
	 * 联系人手机
	 */
	String contact_cellphone;

	public String getContact_cellphone() {
		return contact_cellphone;
	}

	public void setContact_cellphone(String contact_cellphone) {
		this.contact_cellphone = contact_cellphone;
	}

	/*
	 * 联系人MSN
	 */
	String contact_msn;

	public String getContact_msn() {
		return contact_msn;
	}

	public void setContact_msn(String contact_msn) {
		this.contact_msn = contact_msn;
	}

	/*
	 * 联系人QQ
	 */
	String contact_qq;

	public String getContact_qq() {
		return contact_qq;
	}

	public void setContact_qq(String contact_qq) {
		this.contact_qq = contact_qq;
	}

	/*
	 * 会员注册日期
	 */
	String in_date;

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	/*
	 * 注册币种
	 */
	String reg_money_type;

	public String getReg_money_type() {
		return reg_money_type;
	}

	public void setReg_money_type(String reg_money_type) {
		this.reg_money_type = reg_money_type;
	}

	/*
	 * 注册资金
	 */
	String reg_money;

	public String getReg_money() {
		return reg_money;
	}

	public void setReg_money(String reg_money) {
		this.reg_money = reg_money;
	}

	/*
	 * 工商注册日期
	 */
	String reg_date;

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	/*
	 * 公司法人代表
	 */
	String corporate;
	
	public String getCorporate() {
		return corporate;
	}

	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}

	/*
	 * 营业执照号
	 */
	String reg_no;

	public String getReg_no() {
		return reg_no;
	}

	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}

	/*
	 * 雇员人数
	 */
	String staff_num;

	public String getStaff_num() {
		return staff_num;
	}

	public void setStaff_num(String staff_num) {
		this.staff_num = staff_num;
	}

	/*
	 * 品牌名称
	 */
	String brand_name;

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	/*
	 * 年销售额
	 */
	String year_sum;

	public String getYear_sum() {
		return year_sum;
	}

	public void setYear_sum(String year_sum) {
		this.year_sum = year_sum;
	}

	/*
	 * 是否提供OEM代加工
	 */
	String isoem;

	public String getIsoem() {
		return isoem;
	}

	public void setIsoem(String isoem) {
		this.isoem = isoem;
	}

	/*
	 * 营业执照复印件
	 */
	String reg_no_path;

	public String getReg_no_path() {
		return reg_no_path;
	}

	public void setReg_no_path(String reg_no_path) {
		this.reg_no_path = reg_no_path;
	}

	public String getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public String getCat_attr() {
		return cat_attr;
	}

	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}

	public String getInfo_state() {
		return info_state;
	}

	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member[");
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", cust_name=");
		builder.append(this.cust_name);
		builder.append(", logo_path=");
		builder.append(this.logo_path);
		builder.append(", mem_type=");
		builder.append(this.mem_type);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", cust_rage=");
		builder.append(this.cust_rage);
		builder.append(", cust_supply=");
		builder.append(this.cust_supply);
		builder.append(", cust_stock=");
		builder.append(this.cust_stock);
		builder.append(", cust_type=");
		builder.append(this.cust_type);
		builder.append(", client_status=");
		builder.append(this.client_status);
		builder.append(", recommend=");
		builder.append(this.recommend);
		builder.append(", cust_key=");
		builder.append(this.cust_key);
		builder.append(", main_product=");
		builder.append(this.main_product);
		builder.append(", cust_desc=");
		builder.append(this.cust_desc);
		builder.append(", class_attr=");
		builder.append(this.cat_attr);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", address=");
		builder.append(this.address);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", fax=");
		builder.append(this.fax);
		builder.append(", post_code=");
		builder.append(this.post_code);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", website=");
		builder.append(this.website);
		builder.append(", contact_name=");
		builder.append(this.contact_name);
		builder.append(", contact_depart=");
		builder.append(this.contact_depart);
		builder.append(", contact_job=");
		builder.append(this.contact_job);
		builder.append(", contact_sex=");
		builder.append(this.contact_sex);
		builder.append(", contact_cellphone=");
		builder.append(this.contact_cellphone);
		builder.append(", contact_msn=");
		builder.append(this.contact_msn);
		builder.append(", contact_qq=");
		builder.append(this.contact_qq);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", reg_money_type=");
		builder.append(this.reg_money_type);
		builder.append(", reg_money=");
		builder.append(this.reg_money);
		builder.append(", reg_date=");
		builder.append(this.reg_date);
		builder.append(", reg_no=");
		builder.append(this.reg_no);
		builder.append(", corporate=");
		builder.append(this.corporate);
		builder.append(", staff_num=");
		builder.append(this.staff_num);
		builder.append(", brand_name=");
		builder.append(this.brand_name);
		builder.append(", year_sum=");
		builder.append(this.year_sum);
		builder.append(", isoem=");
		builder.append(this.isoem);
		builder.append(", reg_no_path=");
		builder.append(this.reg_no_path);
		builder.append("]");
		return builder.toString();
	}

}
