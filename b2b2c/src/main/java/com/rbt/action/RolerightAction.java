/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: RolerightAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Roleright;
import com.rbt.service.IRolerightService;
import com.rbt.service.ISysmenuService;

/**
 * @function 功能 权限管理Action层实现
 * @author 创建人 邱景岩
 * @date 创建日期 Jul 7, 2011 3:40:23 PM
 */
@Controller
public class RolerightAction extends BaseAction implements Preparable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7921122555497270488L;
	/*
	 * 角色权限业务层接口
	 */
	@Autowired
	public IRolerightService rolerightService;
	/*
	 * 系统菜单业务层接口
	 */
	@Autowired
	public ISysmenuService sysmenuService;
	/*
	 * 角色权限对象
	 */
	public Roleright roleright;
	/*
	 * 角色权限信息集合
	 */
	public List rolerightList;
	/*
	 * 菜单集合
	 */
	public List menuList;
	public List membermenuList;
	public List adminmenuList;
	/*
	 * 搜索字段 right_name_s:用户名 menu_attr_s:匿名 syscode_s:后台类型 menu_name_s:菜单名称
	 */
	public String right_name_s;

	public String menu_attr_s;

	public String syscode_s;

	public String menu_name_s;


	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (right_name_s != null && !right_name_s.equals("")) {
			pageMap.put("right_name", right_name_s);
		}
		if (menu_attr_s != null && !menu_attr_s.equals("")) {
			pageMap.put("menu_attr", menu_attr_s);
		}
		if (syscode_s != null && !syscode_s.equals("")) {
			pageMap.put("syscode", syscode_s);
		}
		if (menu_name_s != null && !menu_name_s.equals("")) {
			pageMap.put("menu_name", menu_name_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.rolerightService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 找出信息列表，放入list
		rolerightList = this.rolerightService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：返回新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Map treeMap = new HashMap();
		if (syscode_s != null && !syscode_s.equals("")) {
			treeMap.put("syscode", syscode_s);
		} else {
			treeMap.put("syscode", "sys");
			setSyscode_s("sys");
		}
		// 找出信息列表，放入list
		menuList = this.sysmenuService.getList(treeMap);
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增角色权限信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String right_id = RandomStrUtil.getNumberRand();
		roleright.setRight_id(right_id);
		//字段验证
		super.commonValidateField(roleright);
		if(super.ifvalidatepass){
			return add();
		}	
		this.rolerightService.insert(roleright);
		this.addActionMessage("新增操作权限信息成功");
		return add();
	}

	/**
	 * 方法描述：根据主键找出角色权限信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		Map adminMap = new HashMap();
		adminMap.put("syscode", "sys");
		adminmenuList = this.sysmenuService.getList(adminMap);
		Map memberMap = new HashMap();
		memberMap.put("syscode", "com");
		membermenuList = this.sysmenuService.getList(memberMap);
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：修改角色权限信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证
		super.commonValidateField(roleright);
		if(super.ifvalidatepass){
			return view();
		}		
		this.rolerightService.update(roleright);
		this.addActionMessage("修改操作权限信息成功");
		return list();
	}

	/**
	 * 方法描述：删除角色权限信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String roleid = this.roleright.getRight_id();
		roleid = roleid.replace(" ", "");
		this.rolerightService.delete(roleid);
		this.addActionMessage("删除操作权限信息成功");
		return list();
	}

	/**
	 * @return the roleright
	 */
	public Roleright getRoleright() {
		return roleright;
	}

	/**
	 * @param roleright
	 *            the roleright to set
	 */
	public void setRoleright(Roleright roleright) {
		this.roleright = roleright;
	}

	/**
	 * @return the menuList
	 */
	public List getMenuList() {
		return menuList;
	}

	/**
	 * @param menuList
	 *            the menuList to set
	 */
	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	/**
	 * @return the membermenuList
	 */
	public List getMembermenuList() {
		return membermenuList;
	}

	/**
	 * @param membermenuList
	 *            the membermenuList to set
	 */
	public void setMembermenuList(List membermenuList) {
		this.membermenuList = membermenuList;
	}

	/**
	 * @return the adminmenuList
	 */
	public List getAdminmenuList() {
		return adminmenuList;
	}

	/**
	 * @param adminmenuList
	 *            the adminmenuList to set
	 */
	public void setAdminmenuList(List adminmenuList) {
		this.adminmenuList = adminmenuList;
	}

	/**
	 * @return the right_name_s
	 */
	public String getRight_name_s() {
		return right_name_s;
	}

	/**
	 * @param right_name_s
	 *            the right_name_s to set
	 */
	public void setRight_name_s(String right_name_s) {
		this.right_name_s = right_name_s;
	}

	/**
	 * @return the menu_attr_s
	 */
	public String getMenu_attr_s() {
		return menu_attr_s;
	}

	/**
	 * @param menu_attr_s
	 *            the menu_attr_s to set
	 */
	public void setMenu_attr_s(String menu_attr_s) {
		this.menu_attr_s = menu_attr_s;
	}

	/**
	 * @return the syscode_s
	 */
	public String getSyscode_s() {
		return syscode_s;
	}

	/**
	 * @param syscode_s
	 *            the syscode_s to set
	 */
	public void setSyscode_s(String syscode_s) {
		this.syscode_s = syscode_s;
	}

	/**
	 * @return the menu_name_s
	 */
	public String getMenu_name_s() {
		return menu_name_s;
	}

	/**
	 * @param menu_name_s
	 *            the menu_name_s to set
	 */
	public void setMenu_name_s(String menu_name_s) {
		this.menu_name_s = menu_name_s;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if(roleright == null){
			roleright = new Roleright();
		}
		String id = roleright.getRight_id();
		if(!ValidateUtil.isDigital(id)){
			roleright = this.rolerightService.get(id);
		}
	}

}
