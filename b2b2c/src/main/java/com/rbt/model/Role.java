/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Role.java 
 */
package com.rbt.model;

import java.io.Serializable;

/**
 * @function 功能 角色实体类
 * @author 创建人 林俊钦
 * @date 创建日期 Jun 29, 2011 9:13:04 AM
 */

public class Role implements Serializable {
	static final long serialVersionUID = -8282130191439287993L;
	/*
	 * 角色唯一标识
	 */
	String role_id;
	/*
	 * 客户唯一标识
	 */
	String cust_id;
	/*
	 * 角色名称
	 */
	String role_name;
	/*
	 * 角色对应的管理员数量
	 */
	String adminnum;
	/*
	 * 角色所拥有的菜单权限串
	 */
	String menu_right;
	/*
	 * 角色所拥有的操作权限串
	 */
	String oper_right;
	/*
	 * 注释
	 */
	String remark;

	/**
	 * @return the role_name
	 */
	public String getRole_name() {
		return role_name;
	}

	/**
	 * @param role_name
	 *            the role_name to set
	 */
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	/**
	 * @return the menu_right
	 */
	public String getMenu_right() {
		return menu_right;
	}

	/**
	 * @param menu_right
	 *            the menu_right to set
	 */
	public void setMenu_right(String menu_right) {
		this.menu_right = menu_right;
	}

	/**
	 * @return the oper_right
	 */
	public String getOper_right() {
		return oper_right;
	}

	/**
	 * @param oper_right
	 *            the oper_right to set
	 */
	public void setOper_right(String oper_right) {
		this.oper_right = oper_right;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return the role_id
	 */
	public String getRole_id() {
		return role_id;
	}

	/**
	 * @param role_id
	 *            the role_id to set
	 */
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	/**
	 * @return the cust_id
	 */
	public String getCust_id() {
		return cust_id;
	}

	/**
	 * @param cust_id
	 *            the cust_id to set
	 */
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getAdminnum() {
		return adminnum;
	}

	public void setAdminnum(String adminnum) {
		this.adminnum = adminnum;
	}

}
