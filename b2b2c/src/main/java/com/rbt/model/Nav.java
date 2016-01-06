/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Nav.java 
 */
package com.rbt.model;
import java.io.Serializable;
/**
 * @function 功能  导航pojo类		
 * @author  创建人 蔡毅存
 * @date  创建日期  July 5, 2011
*/
public class Nav  implements Serializable {
	/**
	 * 序列化
	 */
	static final long serialVersionUID = 4660862818546505945L;
	/*
	 * 导航链接唯一标识
	 */
	String nav_id;
	/*
	 * 导航链接名称
	 */
	String nav_name;
	/*
	 * 是否显示
	 */
	String isshow;
	/*
	 * 排序
	 */
	String sort_no;
	
	/*
	 * 是否打开新页面
	 */
	String isopen;
	
	/*
	 * 链接地址
	 */
    String link_url;
    /*
	 * 放置位置
	 */
    String nav_post;
    /*
	 * 代码高亮编码
	 */
    String nav_code;
    String plat_type;
    

	public String getPlat_type() {
		return plat_type;
	}

	public void setPlat_type(String plat_type) {
		this.plat_type = plat_type;
	}

	public String getNav_id() {
		return nav_id;
	}

	public void setNav_id(String nav_id) {
		this.nav_id = nav_id;
	}
	
	public String getNav_name() {
		return nav_name;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public void setNav_name(String nav_name) {
		this.nav_name = nav_name;
	}

	public String getSort_no() {
		return sort_no;
	}

	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}

	public String getIsopen() {
		return isopen;
	}

	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}

	public String getLink_url() {
		return link_url;
	}

	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}

	public String getNav_post() {
		return nav_post;
	}

	public void setNav_post(String nav_post) {
		this.nav_post = nav_post;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Nav [nav_id=");
		builder.append(this.nav_id);
		builder.append(", nav_name=");
		builder.append(this.nav_name);
		builder.append(", isshow=");
		builder.append(this.isshow);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", plat_type=");
		builder.append(this.plat_type);
		builder.append(", isopen=");
		builder.append(this.isopen);
		builder.append(", link_url=");
		builder.append(this.link_url);
		builder.append(", nav_post=");
		builder.append(this.nav_post);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the nav_code
	 */
	public String getNav_code() {
		return nav_code;
	}

	/**
	 * @param nav_code the nav_code to set
	 */
	public void setNav_code(String nav_code) {
		this.nav_code = nav_code;
	}
	
}
