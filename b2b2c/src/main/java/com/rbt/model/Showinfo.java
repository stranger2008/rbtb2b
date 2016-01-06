/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Exhibition.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 展会表实体
 * @author 创建人 胡惜坤
 * @date 创建日期 Thu Jul 28 09:17:39 CST 2011
 */
public class Showinfo implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String exh_id;
	public String getExh_id() {
		return exh_id;
	}
	public void setExh_id(String exh_id) {
		this.exh_id = exh_id;
	}
	
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
	
	String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	String host_unit;
	public String getHost_unit() {
		return host_unit;
	}
	public void setHost_unit(String host_unit) {
		this.host_unit = host_unit;
	}
	
	String sponsor;
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	
	String contact_man;
	public String getContact_man() {
		return contact_man;
	}
	public void setContact_man(String contact_man) {
		this.contact_man = contact_man;
	}
	
	String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	String cellphone;
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	String contact_addr;
	public String getContact_addr() {
		return contact_addr;
	}
	public void setContact_addr(String contact_addr) {
		this.contact_addr = contact_addr;
	}
	
	String fax;
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	String msn;
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	
	String qq;
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	String start_date;
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	
	String end_date;
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	String hall_name;
	public String getHall_name() {
		return hall_name;
	}
	public void setHall_name(String hall_name) {
		this.hall_name = hall_name;
	}
	
	String exh_desc;
	public String getExh_desc() {
		return exh_desc;
	}
	public void setExh_desc(String exh_desc) {
		this.exh_desc = exh_desc;
	}
	
	String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	String infoattr_id;
	public String getInfoattr_id() {
		return infoattr_id;
	}
	public void setInfoattr_id(String infoattr_id) {
		this.infoattr_id = infoattr_id;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Showinfo[");
		builder.append(", exh_id=");
		builder.append(this.exh_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", host_unit=");
		builder.append(this.host_unit);
		builder.append(", sponsor=");
		builder.append(this.sponsor);
		builder.append(", contact_man=");
		builder.append(this.contact_man);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", cellphone=");
		builder.append(this.cellphone);
		builder.append(", contact_addr=");
		builder.append(this.contact_addr);
		builder.append(", fax=");
		builder.append(this.fax);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", msn=");
		builder.append(this.msn);
		builder.append(", qq=");
		builder.append(this.qq);
		builder.append(", start_date=");
		builder.append(this.start_date);
		builder.append(", end_date=");
		builder.append(this.end_date);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", address=");
		builder.append(this.address);
		builder.append(", hall_name=");
		builder.append(this.hall_name);
		builder.append(", exh_desc=");
		builder.append(this.exh_desc);
		builder.append(", remark=");
		builder.append(this.remark);
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
		builder.append(", infoattr_id=");
		builder.append(this.infoattr_id);
		builder.append("]");
		return builder.toString();
	}

}

