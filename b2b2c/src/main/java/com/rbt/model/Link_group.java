/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Link_group.java 
 */

package com.rbt.model;
import java.io.Serializable;
/**
 * @function 功能  友情链接分组pojo类		
 * @author  创建人 蔡毅存
 * @date  创建日期  Jun 28, 2011
*/
public class Link_group  implements Serializable {
	/**
	 * 序列化
	 */
	static final long serialVersionUID = 5574699226888630924L;
	/*
	 * 友情链接唯一标识
	 */
	String id;
	/*
	 * 友情链接名称
	 */
	String name;
	String plat_type;

	public String getPlat_type() {
		return plat_type;
	}

	public void setPlat_type(String plat_type) {
		this.plat_type = plat_type;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Link_group [id=");
		builder.append(this.id);
		builder.append(", plat_type=");
		builder.append(this.plat_type);
		builder.append(", name=");
		builder.append(this.name);
		builder.append("]");
		return builder.toString();
	}
	
}
