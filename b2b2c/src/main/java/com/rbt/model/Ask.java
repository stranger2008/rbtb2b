/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Ask.java 
 */
package com.rbt.model;

/**
 * @function 功能 问题实体
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:25:38 CST 2011
 */
public class Ask {

	/*
	 * 问题唯一标识
	 */
	String ask_id;

	public String getAsk_id() {
		return ask_id;
	}

	public void setAsk_id(String ask_id) {
		this.ask_id = ask_id;
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
	 * 所属分类
	 */
	String cat_attr;

	public String getCat_attr() {
		return cat_attr;
	}

	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}

	/*
	 * 问题标题
	 */
	String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * 问题图片
	 */
	String img_path;

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	/*
	 * 问题说明
	 */
	String ask_desc;

	public String getAsk_desc() {
		return ask_desc;
	}

	public void setAsk_desc(String ask_desc) {
		this.ask_desc = ask_desc;
	}

	/*
	 * 补充信息
	 */
	String add_desc;

	public String getAdd_desc() {
		return add_desc;
	}

	public void setAdd_desc(String add_desc) {
		this.add_desc = add_desc;
	}

	/*
	 * 悬赏积分
	 */
	String integral;

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	/*
	 * 是否推荐
	 */
	String is_recom;

	public String getIs_recom() {
		return is_recom;
	}

	public void setIs_recom(String is_recom) {
		this.is_recom = is_recom;
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
	 * 拒绝理由
	 */
	String no_reason;

	public String getNo_reason() {
		return no_reason;
	}

	public void setNo_reason(String no_reason) {
		this.no_reason = no_reason;
	}

	/*
	 * 回答状态
	 */
	String answer_state;

	public String getAnswer_state() {
		return answer_state;
	}

	public void setAnswer_state(String answer_state) {
		this.answer_state = answer_state;
	}

	String infoattr_id;
	public String getInfoattr_id() {
		return infoattr_id;
	}
	public void setInfoattr_id(String infoattr_id) {
		this.infoattr_id = infoattr_id;
	}
	
	
	/*
	 * 回答数answernum
	 */
	String answernum;

	public String getAnswernum() {
		return answernum;
	}

	public void setAnswernum(String answernum) {
		this.answernum = answernum;
	}

	/*
	 * 发布时间
	 */
	String in_date;

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	/*
	 * 点击量
	 */
	String clicknum;

	public String getClicknum() {
		return clicknum;
	}

	public void setClicknum(String clicknum) {
		this.clicknum = clicknum;
	}

	/*
	 * 内容收费
	 */
	String fare;

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
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
		builder.append("Ask[");
		builder.append(", ask_id=");
		builder.append(this.ask_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", ask_desc=");
		builder.append(this.ask_desc);
		builder.append(", add_desc=");
		builder.append(this.add_desc);
		builder.append(", integral=");
		builder.append(this.integral);
		builder.append(", is_recom=");
		builder.append(this.is_recom);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", no_reason=");
		builder.append(this.no_reason);
		builder.append(", answer_state=");
		builder.append(this.answer_state);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append(", clicknum=");
		builder.append(this.clicknum);
		builder.append(", fare=");
		builder.append(this.fare);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append("]");
		return builder.toString();
	}

}
