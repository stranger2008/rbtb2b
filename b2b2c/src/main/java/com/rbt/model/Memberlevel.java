/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Memberlevel.java 
 */
package com.rbt.model;

import java.io.Serializable;

/**
 * @function 功能 会员级别配置实体
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 13 15:08:05 CST 2011
 */
public class Memberlevel implements Serializable {

	static final long serialVersionUID = 1L;

	/*
	 * 会员级别唯一标识
	 */
	String level_id;

	public String getLevel_id() {
		return level_id;
	}

	public void setLevel_id(String level_id) {
		this.level_id = level_id;
	}

	String module_attr;
	
	/*
	 * 会员级别名称
	 */
	String level_name;

	public String getLevel_name() {
		return level_name;
	}

	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

	/*
	 * 会员级别菜单权限
	 */
	String menu_right;

	public String getMenu_right() {
		return menu_right;
	}

	public void setMenu_right(String menu_right) {
		this.menu_right = menu_right;
	}

	/*
	 * 会员级别操作权限
	 */
	String oper_right;

	public String getOper_right() {
		return oper_right;
	}

	public void setOper_right(String oper_right) {
		this.oper_right = oper_right;
	}

	/*
	 * 备注
	 */
	String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/*
	 * 级别类型
	 */
	String syslevel;

	public String getSyslevel() {
		return syslevel;
	}

	public void setSyslevel(String syslevel) {
		this.syslevel = syslevel;
	}

	String num_control;
	
	public String getNum_control() {
		return num_control;
	}

	public void setNum_control(String num_control) {
		this.num_control = num_control;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Memberlevel[");
		builder.append(", level_id=");
		builder.append(this.level_id);
		builder.append(", level_name=");
		builder.append(this.level_name);
		builder.append(", menu_right=");
		builder.append(this.menu_right);
		builder.append(", oper_right=");
		builder.append(this.oper_right);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append(", syslevel=");
		builder.append(this.syslevel);
		builder.append("]");
		return builder.toString();
	}

	public String getModule_attr() {
		return module_attr;
	}

	public void setModule_attr(String module_attr) {
		this.module_attr = module_attr;
	}
	

}
