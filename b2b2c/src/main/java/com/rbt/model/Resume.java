/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Resume.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 简历信息实体
 * @author 创建人 胡惜坤
 * @date 创建日期 Wed Jul 13 16:14:17 CST 2011
 */
public class Resume implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String resume_id;
	public String getResume_id() {
		return resume_id;
	}
	public void setResume_id(String resume_id) {
		this.resume_id = resume_id;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String resume_name;
	public String getResume_name() {
		return resume_name;
	}
	public void setResume_name(String resume_name) {
		this.resume_name = resume_name;
	}
	
	String cat_attr;
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	
	String real_name;
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	String img_path;
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
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
	
	String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	String birth;
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	String height;
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	
	String weight;
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	String education;
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	
	String college;
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	
	String spec;
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	String salary;
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	String job_type;
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	
	String work_exper;
	public String getWork_exper() {
		return work_exper;
	}
	public void setWork_exper(String work_exper) {
		this.work_exper = work_exper;
	}
	
	String self_desc;
	public String getSelf_desc() {
		return self_desc;
	}
	public void setSelf_desc(String self_desc) {
		this.self_desc = self_desc;
	}
	
	String technical;
	public String getTechnical() {
		return technical;
	}
	public void setTechnical(String technical) {
		this.technical = technical;
	}
	
	String language;
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	String cellphone;
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Resume[");
		builder.append(", resume_id=");
		builder.append(this.resume_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", resume_name=");
		builder.append(this.resume_name);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", real_name=");
		builder.append(this.real_name);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", sex=");
		builder.append(this.sex);
		builder.append(", marry=");
		builder.append(this.marry);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", birth=");
		builder.append(this.birth);
		builder.append(", height=");
		builder.append(this.height);
		builder.append(", weight=");
		builder.append(this.weight);
		builder.append(", education=");
		builder.append(this.education);
		builder.append(", college=");
		builder.append(this.college);
		builder.append(", spec=");
		builder.append(this.spec);
		builder.append(", salary=");
		builder.append(this.salary);
		builder.append(", job_type=");
		builder.append(this.job_type);
		builder.append(", work_exper=");
		builder.append(this.work_exper);
		builder.append(", self_desc=");
		builder.append(this.self_desc);
		builder.append(", technical=");
		builder.append(this.technical);
		builder.append(", language=");
		builder.append(this.language);
		builder.append(", cellphone=");
		builder.append(this.cellphone);
		builder.append(", email=");
		builder.append(this.email);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", address=");
		builder.append(this.address);
		builder.append(", qq=");
		builder.append(this.qq);
		builder.append(", msn=");
		builder.append(this.msn);
		builder.append(", skype=");
		builder.append(this.skype);
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

