/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Job.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 招聘信息实体
 * @author 创建人 胡惜坤
 * @date 创建日期 Tue Jul 12 15:29:27 CST 2011
 */
public class Job implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String job_id;
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
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
	
	String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	String org_name;
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	
	String job_num;
	public String getJob_num() {
		return job_num;
	}
	public void setJob_num(String job_num) {
		this.job_num = job_num;
	}
	
	String salary;
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	String job_type;
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	
	String sex;
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	String marry;
	public String getMarry() {
		return marry;
	}
	public void setMarry(String marry) {
		this.marry = marry;
	}
	
	String enducation;
	public String getEnducation() {
		return enducation;
	}
	public void setEnducation(String enducation) {
		this.enducation = enducation;
	}
	
	String birth;
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	String workexper;
	public String getWorkexper() {
		return workexper;
	}
	public void setWorkexper(String workexper) {
		this.workexper = workexper;
	}
	
	String job_desc;
	public String getJob_desc() {
		return job_desc;
	}
	public void setJob_desc(String job_desc) {
		this.job_desc = job_desc;
	}
	
	String contact_name;
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	
	String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	String cellphone;
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	String qq;
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	String msn;
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	
	String skype;
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
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

	String no_reason;
	public String getNo_reason() {
		return no_reason;
	}
	public void setNo_reason(String no_reason) {
		this.no_reason = no_reason;
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
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
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
	String is_trust;
	public String getIs_trust() {
		return is_trust;
	}
	public void setIs_trust(String is_trust) {
		this.is_trust = is_trust;
	}
	String is_where;
	public String getIs_where() {
		return is_where;
	}
	public void setIs_where(String is_where) {
		this.is_where = is_where;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Job[");
		builder.append(", job_id=");
		builder.append(this.job_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", org_name=");
		builder.append(this.org_name);
		builder.append(", job_num=");
		builder.append(this.job_num);
		builder.append(", salary=");
		builder.append(this.salary);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", job_type=");
		builder.append(this.job_type);
		builder.append(", sex=");
		builder.append(this.sex);
		builder.append(", marry=");
		builder.append(this.marry);
		builder.append(", enducation=");
		builder.append(this.enducation);
		builder.append(", birth=");
		builder.append(this.birth);
		builder.append(", workexper=");
		builder.append(this.workexper);
		builder.append(", job_desc=");
		builder.append(this.job_desc);
		builder.append(", contact_name=");
		builder.append(this.contact_name);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", cellphone=");
		builder.append(this.cellphone);
		builder.append(", address=");
		builder.append(this.address);
		builder.append(", qq=");
		builder.append(this.qq);
		builder.append(", msn=");
		builder.append(this.msn);
		builder.append(", skype=");
		builder.append(this.skype);
		builder.append(", end_date=");
		builder.append(this.end_date);
		builder.append(", is_recom=");
		builder.append(this.is_recom);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", no_reason=");
		builder.append(this.no_reason);
		builder.append(", clicknum=");
		builder.append(this.clicknum);
		builder.append(", fare=");
		builder.append(this.fare);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", infoattr_id=");
		builder.append(this.infoattr_id);
		builder.append(", is_trust=");
		builder.append(this.is_trust);
		builder.append(", is_where=");
		builder.append(this.is_where);
		builder.append("]");
		return builder.toString();
	}
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	

}

