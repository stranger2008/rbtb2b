/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: News.java 
 */
package com.rbt.model;

import java.io.Serializable;

/**
 * @function 功能 资讯管理实体类
 * @author 创建人 胡惜坤
 * @date 创建日期 July 8, 2011
 */
public class News implements Serializable {

	/**
	 * 
	 */
	static final long serialVersionUID = -4965389108332209475L;
	/*
	 * 资讯标识
	 */
	String news_id;
	/*
	 * 客户标识
	 */
	String cust_id;
	/*
	 * 资讯标题
	 */
	String title;
	/*
	 * 自定义属性
	 */
	String news_attr;
	/*
	 * 跳转网页
	 */
	String out_link;
	/*
	 * tag标签
	 */
	String tag;

	/*
	 * 缩略图
	 */
	String litpic;

	/*
	 * 文章来源
	 */
	String source;

	/*
	 * 来源链接
	 */
	String sourcelink;

	/*
	 * 作者
	 */
	String author;

	/*
	 * 所属分类
	 */
	String cat_attr;
	
	/*
	 * 所属地区
	 */
	String area_attr;

	/*
	 * 资讯内容
	 */
	String content;

	/*
	 * 评论选项
	 */
	String comment;

	/*
	 * 文章排序
	 */
	String sort_no;

	/*
	 * 标题颜色
	 */
	String title_color;

	/*
	 * 内容收费
	 */
	String fare;

	/*
	 * 关键字
	 */
	String keyword;

	/*
	 * 内容摘要
	 */
	String description;

	/*
	 * 投票标识
	 */
	String vote_id;

	/*
	 * 资讯状态
	 */
	String info_state;

	/*
	 * 审核不通过理由
	 */
	String no_reason;
	/*
	 * 逻辑删除
	 */
	String is_delete;
	/*
	 * 点击量
	 */
	String clicknum;
	/*
	 * 发布时间
	 */
	String in_date;
	/*
	 * 发布人标识
	 */
	String user_id;
	/*
	 * 设置排序的天数
	 */
	String sort_data_number;
	
	//公告类型
	String news_type;
	
	
	public String getNews_type() {
		return news_type;
	}
	public void setNews_type(String news_type) {
		this.news_type = news_type;
	}
	public String getNews_id() {
		return news_id;
	}
	public void setNews_id(String news_id) {
		this.news_id = news_id;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNews_attr() {
		return news_attr;
	}
	public void setNews_attr(String news_attr) {
		this.news_attr = news_attr;
	}
	public String getOut_link() {
		return out_link;
	}
	public void setOut_link(String out_link) {
		this.out_link = out_link;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getLitpic() {
		return litpic;
	}
	public void setLitpic(String litpic) {
		this.litpic = litpic;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourcelink() {
		return sourcelink;
	}
	public void setSourcelink(String sourcelink) {
		this.sourcelink = sourcelink;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	public String getTitle_color() {
		return title_color;
	}
	public void setTitle_color(String title_color) {
		this.title_color = title_color;
	}
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVote_id() {
		return vote_id;
	}
	public void setVote_id(String vote_id) {
		this.vote_id = vote_id;
	}
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	public String getNo_reason() {
		return no_reason;
	}
	public void setNo_reason(String no_reason) {
		this.no_reason = no_reason;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	public String getClicknum() {
		return clicknum;
	}
	public void setClicknum(String clicknum) {
		this.clicknum = clicknum;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSort_data_number() {
		return sort_data_number;
	}
	public void setSort_data_number(String sort_data_number) {
		this.sort_data_number = sort_data_number;
	}
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	String infoattr_id;
	public String getInfoattr_id() {
		return infoattr_id;
	}
	public void setInfoattr_id(String infoattr_id) {
		this.infoattr_id = infoattr_id;
	}
}
