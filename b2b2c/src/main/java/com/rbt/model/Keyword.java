/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Keyword.java 
 */
package com.rbt.model;
import java.io.Serializable;
/**
 * @function 功能  关键字pojo类		
 * @author  创建人 蔡毅存
 * @date  创建日期  July 6, 2011
*/
public class Keyword  implements Serializable {
	/**
	 * 序列化
	 */
	static final long serialVersionUID = 6774355621991793528L;
	/*
	 * 关键字唯一标识
	 */
	String key_id;
	/*
	 * 关键字名称
	 */
	String key_name;
	/*
	 * 关键字参照类型
	 */
	String module_type;
	/*
	 * 关键字搜索频率
	 */
	String num;
	

	public String getKey_id() {
		return key_id;
	}

	public void setKey_id(String key_id) {
		this.key_id = key_id;
	}

	public String getKey_name() {
		return key_name;
	}

	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}

	public String getModule_type() {
		return module_type;
	}

	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}



	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Keyword [key_id=");
		builder.append(this.key_id);
		builder.append(", key_name=");
		builder.append(this.key_name);
		builder.append(", module_type=");
		builder.append(this.module_type);
		builder.append(", num=");
		builder.append(this.num);
		builder.append("]");
		return builder.toString();
	}
	
}
