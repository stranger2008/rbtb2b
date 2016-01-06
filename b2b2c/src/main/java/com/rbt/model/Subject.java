/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Subject.java 
 */
package com.rbt.model;

/**
 * @function 功能 专题信息实体
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:19:02 CST 2011
 */
public class Subject {

	/*
	 * 专题唯一标识
	 */
	String sub_id;

	public String getSub_id() {
		return sub_id;
	}

	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
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
	 * 专题标题
	 */
	String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * 标题图片
	 */
	String img_path;

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	/*
	 * 横幅图片
	 */
	String header_path;

	public String getHeader_path() {
		return header_path;
	}

	public void setHeader_path(String header_path) {
		this.header_path = header_path;
	}

	/*
	 * 专题详情
	 */
	String sub_desc;

	public String getSub_desc() {
		return sub_desc;
	}

	public void setSub_desc(String sub_desc) {
		this.sub_desc = sub_desc;
	}

	/*
	 * 专题文字新闻
	 */
	String news_attr;

	public String getNews_attr() {
		return news_attr;
	}

	public void setNews_attr(String news_attr) {
		this.news_attr = news_attr;
	}

	/*
	 * 专题图片新闻
	 */
	String img_news_attr;

	public String getImg_news_attr() {
		return img_news_attr;
	}

	public void setImg_news_attr(String img_news_attr) {
		this.img_news_attr = img_news_attr;
	}

	/*
	 * 资讯关联栏目
	 */
	String link_cat;
    
	public String getLink_cat() {
		return link_cat;
	}

	public void setLink_cat(String link_cat) {
		this.link_cat = link_cat;
	}
	/*
	 * 是否允许评论
	 */
	String is_comment;

	public String getIs_comment() {
		return is_comment;
	}

	public void setIs_comment(String is_comment) {
		this.is_comment = is_comment;
	}

	/*
	 * 所属模板
	 */
	String temp_path;

	public String getTemp_path() {
		return temp_path;
	}

	public void setTemp_path(String temp_path) {
		this.temp_path = temp_path;
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
	String infoattr_id;
	public String getInfoattr_id() {
		return infoattr_id;
	}
	public void setInfoattr_id(String infoattr_id) {
		this.infoattr_id = infoattr_id;
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
		builder.append("Subject[");
		builder.append(", sub_id=");
		builder.append(this.sub_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", title=");
		builder.append(this.title);
		builder.append(", img_path=");
		builder.append(this.img_path);
		builder.append(", header_path=");
		builder.append(this.header_path);
		builder.append(", sub_desc=");
		builder.append(this.sub_desc);
		builder.append(", news_attr=");
		builder.append(this.news_attr);
		builder.append(", img_news_attr=");
		builder.append(this.img_news_attr);
		builder.append(", link_cat=");
		builder.append(this.link_cat);
		builder.append(", is_comment=");
		builder.append(this.is_comment);
		builder.append(", temp_path=");
		builder.append(this.temp_path);
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
		builder.append("]");
		return builder.toString();
	}

}
