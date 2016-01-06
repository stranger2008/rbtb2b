/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Answer.java 
 */
package com.rbt.model;

/**
 * @function 功能 答案信息实体
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:28:09 CST 2011
 */
public class Answer {

	/*
	 * 答案唯一标识
	 */
	String answer_id;

	public String getAnswer_id() {
		return answer_id;
	}

	public void setAnswer_id(String answer_id) {
		this.answer_id = answer_id;
	}

	/*
	 * 问题标识
	 */
	String ask_id;

	public String getAsk_id() {
		return ask_id;
	}

	public void setAsk_id(String ask_id) {
		this.ask_id = ask_id;
	}

	/*
	 * 问题标题
	 */
	String ask_title;

	public String getAsk_title() {
		return ask_title;
	}

	public void setAsk_title(String ask_title) {
		this.ask_title = ask_title;
	}
	/*
	 * 客户标识
	 */
	String cust_id;

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	/*
	 * 答案内容
	 */
	String answer_desc;

	public String getAnswer_desc() {
		return answer_desc;
	}

	public void setAnswer_desc(String answer_desc) {
		this.answer_desc = answer_desc;
	}

	/*
	 * IP
	 */
	String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/*
	 * 参考资料
	 */
	String refer_data;

	public String getRefer_data() {
		return refer_data;
	}

	public void setRefer_data(String refer_data) {
		this.refer_data = refer_data;
	}

	/*
	 * 最佳答案
	 */
	String isselect;

	public String getIsselect() {
		return isselect;
	}

	public void setIsselect(String isselect) {
		this.isselect = isselect;
	}

	/*
	 * 信息状态
	 */
	String info_state;

	public String getInfo_state() {
		return info_state;
	}

	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}

	/*
	 * 回答时间
	 */
	String in_date;

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	/*
	 * 发布人标识
	 */
	String user_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Answer[");
		builder.append(", answer_id=");
		builder.append(this.answer_id);
		builder.append(", ask_id=");
		builder.append(this.ask_id);
		builder.append(", ask_title=");
		builder.append(this.ask_title);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", answer_desc=");
		builder.append(this.answer_desc);
		builder.append(", ip=");
		builder.append(this.ip);
		builder.append(", refer_data=");
		builder.append(this.refer_data);
		builder.append(", isselect=");
		builder.append(this.isselect);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append("]");
		return builder.toString();
	}

}
