/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Vote_option.java 
 */

package com.rbt.model;

import java.io.Serializable;

/**
 * @function 功能  在线调查选项pojo类		
 * @author  创建人 蔡毅存
 * @date  创建日期  July 7, 2011
*/
public class Vote_option  implements Serializable {
	/**
	 * 序列化
	 */
	static final long serialVersionUID = 1918313182642701993L;
	/*
	 * 选项标识
	 */
	String option_id;
	/*
	 * 调查标识名称
	 */
	String vote_id;
	/*
	 * 选项名称
	 */
	String option_name;
	/*
	 * 选项投票数
	 */
	String  option_count;
 
	public String getOption_id() {
		return option_id;
	}

	public void setOption_id(String option_id) {
		this.option_id = option_id;
	}

	public String getVote_id() {
		return vote_id;
	}

	public void setVote_id(String vote_id) {
		this.vote_id = vote_id;
	}

	public String getOption_name() {
		return option_name;
	}

	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}

	public String getOption_count() {
		return option_count;
	}

	public void setOption_count(String option_count) {
		this.option_count = option_count;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("vote_option [option_id=");
		builder.append(this.option_id);
		builder.append(", vote_id=");
		builder.append(this.vote_id);
		builder.append(", option_name=");
		builder.append(this.option_name);
		builder.append(", option_count=");
		builder.append(this.option_count);
		builder.append("]");
		return builder.toString();
	}
	
}
