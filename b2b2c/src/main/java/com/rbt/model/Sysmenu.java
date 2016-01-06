/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Sysmenu.java 
 */

package com.rbt.model;

import java.io.Serializable;

/**
 * @function 功能  系统菜单pojo类		
 * @author  创建人 李良林
 * @date  创建日期  Jun 22, 2011
*/
public class Sysmenu  implements Serializable {

	static final long serialVersionUID = 2083827134077851396L;
	
	/*
	 * 菜单唯一标识
	 */
	String menu_id;
	/*
	 * 菜单名称
	 */
	String menu_name;
	/*
	 * 菜单所属后台代码，sys：运营商 com：企业 per：个人
	 */
	String syscode;
	/*
	 * 上级菜单标识
	 */
	String up_menu_id;
	/*
	 * 菜单级别
	 */
	String menu_level;
	/*
	 * 排序
	 */
	String sort_no;
	/*
	 * 是否有效 0：有效 1：无效，无效则后台不显示
	 */
	String enabled;
	/*
	 * 菜单url地址
	 */
	String url;
	/*
	 * 链接类型
	 */
	String target;
	
	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return the menu_id
	 */
	public String getMenu_id() {
		return menu_id;
	}

	/**
	 * @param menu_id the menu_id to set
	 */
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	/**
	 * @return the menu_name
	 */
	public String getMenu_name() {
		return menu_name;
	}

	/**
	 * @param menu_name the menu_name to set
	 */
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	/**
	 * @return the syscode
	 */
	public String getSyscode() {
		return syscode;
	}

	/**
	 * @param syscode the syscode to set
	 */
	public void setSyscode(String syscode) {
		this.syscode = syscode;
	}

	/**
	 * @return the up_menu_id
	 */
	public String getUp_menu_id() {
		return up_menu_id;
	}

	/**
	 * @param up_menu_id the up_menu_id to set
	 */
	public void setUp_menu_id(String up_menu_id) {
		this.up_menu_id = up_menu_id;
	}

	/**
	 * @return the menu_level
	 */
	public String getMenu_level() {
		return menu_level;
	}

	/**
	 * @param menu_level the menu_level to set
	 */
	public void setMenu_level(String menu_level) {
		this.menu_level = menu_level;
	}

	/**
	 * @return the sort_no
	 */
	public String getSort_no() {
		return sort_no;
	}

	/**
	 * @param sort_no the sort_no to set
	 */
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}

	/**
	 * @return the enabled
	 */
	public String getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sysmenu [menu_id=");
		builder.append(this.menu_id);
		builder.append(", menu_name=");
		builder.append(this.menu_name);
		builder.append(", syscode=");
		builder.append(this.syscode);
		builder.append(", up_menu_id=");
		builder.append(this.up_menu_id);
		builder.append(", menu_level=");
		builder.append(this.menu_level);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", enabled=");
		builder.append(this.enabled);
		builder.append(", url=");
		builder.append(this.url);
		builder.append("]");
		return builder.toString();
	}
	
}
