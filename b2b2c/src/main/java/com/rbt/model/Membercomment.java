/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Membercomment.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录会员信息评论信息实体
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Jul 22 14:21:40 CST 2011
 */
public class Membercomment implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String comm_id;
	public String getComm_id() {
		return comm_id;
	}
	public void setComm_id(String comm_id) {
		this.comm_id = comm_id;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String ip;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	String back_con;
	public String getBack_con() {
		return back_con;
	}
	public void setBack_con(String back_con) {
		this.back_con = back_con;
	}
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	String comm_state;
	public String getComm_state() {
		return comm_state;
	}
	public void setComm_state(String comm_state) {
		this.comm_state = comm_state;
	}
	
	String up_num;
	public String getUp_num() {
		return up_num;
	}
	public void setUp_num(String up_num) {
		this.up_num = up_num;
	}
	
	String down_num;
	public String getDown_num() {
		return down_num;
	}
	public void setDown_num(String down_num) {
		this.down_num = down_num;
	}
	
	String info_id;
	public String getInfo_id() {
		return info_id;
	}
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}
	
	String info_title;
	public String getInfo_title() {
		return info_title;
	}
	public void setInfo_title(String info_title) {
		this.info_title = info_title;
	}
	
	String info_url;
	public String getInfo_url() {
		return info_url;
	}
	public void setInfo_url(String info_url) {
		this.info_url = info_url;
	}
	
	String comm_num;
	public String getComm_num() {
		return comm_num;
	}
	public void setComm_num(String comm_num) {
		this.comm_num = comm_num;
	}
	String info_type;

	public String getInfo_type() {
		return info_type;
	}
	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}
	
	String  user_name;		
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Membercomment[");
		builder.append(", comm_id=");
		builder.append(this.comm_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", ip=");
		builder.append(this.ip);
		builder.append(", content=");
		builder.append(this.content);
		builder.append(", info_type=");
		builder.append(this.info_type);
		builder.append(", back_con=");
		builder.append(this.back_con);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", comm_state=");
		builder.append(this.comm_state);
		builder.append(", up_num=");
		builder.append(this.up_num);
		builder.append(", down_num=");
		builder.append(this.down_num);
		builder.append(", info_id=");
		builder.append(this.info_id);
		builder.append(", info_title=");
		builder.append(this.info_title);
		builder.append(", info_url=");
		builder.append(this.info_url);
		builder.append(", comm_num=");
		builder.append(this.comm_num);
		builder.append(", user_name=");
		builder.append(this.user_name);
		builder.append("]");
		return builder.toString();
	}

}

