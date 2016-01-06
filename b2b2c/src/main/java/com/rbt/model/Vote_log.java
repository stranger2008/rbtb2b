/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Vote_log.java 
 */

package com.rbt.model;

import java.io.Serializable;

/**
 * @function 功能  在线调查记录pojo类		
 * @author  创建人 蔡毅存
 * @date  创建日期  July 6, 2011
*/
public class Vote_log  implements Serializable {
	/**
	 * 序列化
	 */
	static final long serialVersionUID = -7678498914054323486L;
	/*
	 * 记录唯一标识
	 */
	String log_id;
	/*
	 * 调查标识名称
	 */
	String vote_id;
	/*
	 * ip地址
	 */
	String ip_addr;
	/*
	 * 投票时间
	 */
	String log_date;
	/*
	 * 投票时间
	 */
    String option_attr;
	
	public String getLog_id() {
		return log_id;
	}


	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}


	public String getVote_id() {
		return vote_id;
	}


	public void setVote_id(String vote_id) {
		this.vote_id = vote_id;
	}


	public String getIp_addr() {
		return ip_addr;
	}


	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}


	public String getLog_date() {
		return log_date;
	}


	public void setLog_date(String log_date) {
		this.log_date = log_date;
	}

    
	public String getOption_attr() {
		return option_attr;
	}


	public void setOption_attr(String option_attr) {
		this.option_attr = option_attr;
	}


	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("vote_log [log_id=");
		builder.append(this.log_id);
		builder.append(", vote_id=");
		builder.append(this.vote_id);
		builder.append(", ip_addr=");
		builder.append(this.ip_addr);
		builder.append(", log_date=");
		builder.append(this.log_date);
		builder.append(", option_attr=");
		builder.append(this.option_attr);
		builder.append("]");
		return builder.toString();
	}
	
}
