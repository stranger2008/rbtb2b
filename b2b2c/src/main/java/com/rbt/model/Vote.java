/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Vote.java 
 */

package com.rbt.model;

import java.io.Serializable;

/**
 * @function 功能  导航pojo类		
 * @author  创建人 蔡毅存
 * @date  创建日期  July 7, 2011
*/
public class Vote  implements Serializable {
	/**
	 * 序列化
	 */
	static final long serialVersionUID = 5224006710960464309L;
	/*
	 * 在线调查唯一标识
	 */
	String vote_id;
	/*
	 * 调查主题名称
	 */
	String vote_title;
	/*
	 * 调查开始时间
	 */
	String start_date;
	/*
	 * 调查结束时间
	 */
	String end_date;
	
	/*
	 * 是否支持多选
	 */
	String is_multi;
	
	/*
	 * 投票数
	 */
    String vote_count;
    /*
	 * 操作人ID
	 */
    String user_id;
    /*
	 * 录入时间
	 */
    String in_date;


	public String getVote_id() {
		return vote_id;
	}

	public void setVote_id(String vote_id) {
		this.vote_id = vote_id;
	}

	public String getVote_title() {
		return vote_title;
	}

	public void setVote_title(String vote_title) {
		this.vote_title = vote_title;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getIs_multi() {
		return is_multi;
	}

	public void setIs_multi(String is_multi) {
		this.is_multi = is_multi;
	}

	public String getVote_count() {
		return vote_count;
	}

	public void setVote_count(String vote_count) {
		this.vote_count = vote_count;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("vote [vote_id=");
		builder.append(this.vote_id);
		builder.append(", vote_title=");
		builder.append(this.vote_title);
		builder.append(", start_date=");
		builder.append(this.start_date);
		builder.append(", end_date=");
		builder.append(this.end_date);
		builder.append(", is_multi=");
		builder.append(this.is_multi);
		builder.append(", vote_count=");
		builder.append(this.vote_count);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
		builder.append("]");
		return builder.toString();
	}
	
}
