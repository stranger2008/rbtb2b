/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: RoleAction.java
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Role;
import com.rbt.service.IRoleService;
import com.rbt.service.ISysmenuService;
import com.rbt.service.ISysuserService;

/**
 * @function 功能 角色的管理action类
 * @author 创建人 林俊钦
 * @date 创建日期 Jun 28, 2011 1:47:20 PM
 */
@Controller
public class RoleAction extends BaseAction implements Preparable {
	
	private static final long serialVersionUID = -5492437578684030364L;

	private static final String SYSCODE_COM_VALUE = "com";

	private static final String SYSCODE_SYS_VALUE = "sys";
	/*
	 * 菜单业务层接口
	 */
	@Autowired
	public ISysmenuService sysmenuService;
	@Autowired
	public IRoleService roleService;
	@Autowired
	public ISysuserService sysuserService;

	/*
	 * 菜单信息集合
	 */
	public List menuList;
	/*
	 * 角色信息集合
	 */
	public List rolelist;
	/*
	 * 角色对象集合
	 */
	public Role role;
	/*
	 * 定义验证方法
	 */
	public ValidateUtil validateFactory;
	public String rolemenuright;

	/**
	 * @MethodDescribe 方法描述 跳转到新增角色页面
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jun 28, 2011 1:50:41 PM
	 */
	public String add() throws Exception {
		// 加载树
		roleTree();
		return goUrl(ADD);
	}

	/**
	 * @MethodDescribe 方法描述 得到角色菜单树
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jun 30, 2011 5:22:08 PM
	 */
	public void roleTree() {
		HttpSession session = getSession();
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("syscode", SYSCODE_COM_VALUE);
		} else {
			pageMap.put("syscode", SYSCODE_SYS_VALUE);
		}
		// 找出信息列表，放入list
		menuList = this.sysmenuService.getList(pageMap);
		Map listMap = new HashMap();
		// 从数据库获取权限串,取出session中的菜单串,用于匹配菜单
		String menu_string = "";
		if (session.getAttribute("menu_right") != null) {
			menu_string = session.getAttribute("menu_right").toString();
		}
		// 操作者为运营商时运行方法
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			// 匹配菜单串，若不存在删除节点
			String menu_id = "";
			if (menuList != null && menuList.size() > 0) {
				for (int i = 0; i < menuList.size(); i++) {
					listMap = (HashMap) menuList.get(i);
					if (listMap.get("menu_id") != null) {
						menu_id = listMap.get("menu_id").toString();
						if (menu_string.indexOf(menu_id) < 0) {
							menuList.remove(i);
							// 因为remove方法在删除时去向前移位所以要减一
							i = i - 1;
						}
					}
				}
			}
		}

	}

	/**
	 * @MethodDescribe 方法描述 添加角色的方法
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jun 28, 2011 5:27:43 PM
	 */
	public String insert() throws Exception {
		if (!ValidateUtil.isRequired(role.getMenu_right())) {
			String menu_right = this.role.getMenu_right();
			menu_right = menu_right.replace(" ", "");
			role.setMenu_right(menu_right);
		} 
		if (!ValidateUtil.isRequired(role.getOper_right())) {
			String oper_right = this.role.getOper_right();
			oper_right = oper_right.replace(" ", "");
			role.setOper_right(oper_right);
		}
		role.setCust_id(this.session_cust_id);
		//字段验证
		super.commonValidateField(role);
		if(super.ifvalidatepass){
			return add();
		}		
		this.roleService.insert(role);
		this.role = null;
		this.addActionMessage("添加角色信息成功");
		return add();
	}

	/**
	 * @MethodDescribe 方法描述 根据条件返回数据库条件的列表
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jun 29, 2011 3:36:01 PM
	 */
	public String list() throws Exception {
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		pageMap.put("cust_id", this.session_cust_id);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.roleService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		// 找出信息列表，放入list
		rolelist = this.roleService.getList(pageMap);
		//找出角色对应的管理员数量
		if(rolelist != null && rolelist.size()>0){
			int adminnum = 0;
			Map aMap = new HashMap();
			Map bMap = new HashMap();
			for(int i=0;i<rolelist.size();i++){
				aMap = (Map) rolelist.get(i);
				if(aMap.get("role_id") != null){
					bMap.put("role_id", aMap.get("role_id"));
					adminnum = this.sysuserService.getCount(bMap);
				}
				aMap.put("adminnum", adminnum);
				rolelist.set(i, aMap);
			}
		}
		return goUrl(INDEXLIST);
	}

	/**
	 * @MethodDescribe 方法描述 根据ID删除一个角色
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jun 29, 2011 3:36:44 PM
	 */
	public String delete() throws Exception {
		if (this.role.getRole_id() != null) {
			String roleid = this.role.getRole_id();
			roleid = roleid.replace(" ", "");
			this.roleService.delete(roleid);
			this.addActionMessage("删除角色信息成功");
		}
		return list();
	}

	/**
	 * @MethodDescribe 方法描述 通过主键ID返回角色的详细信息
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jun 30, 2011 2:21:57 PM
	 */
	public String view() throws Exception {
		if(role.getCust_id()!=null){
			if(accessControl(role.getCust_id())){
				return list();
			}
		}
		if(ValidateUtil.isRequired(role.getRole_id())){
			return list();
		}
		roleTree();
		return goUrl(VIEW);
	}

	/**
	 * @MethodDescribe 方法描述 根据ID对角色进行修改操作
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jun 30, 2011 3:22:58 PM
	 */
	public String update() throws Exception {
		if (!ValidateUtil.isRequired(rolemenuright)) {
			String menu_right =rolemenuright;
			menu_right = menu_right.replace(" ", "");
			role.setMenu_right(menu_right);
		} 
		else {
			role.setMenu_right("");
		}
		if (!ValidateUtil.isRequired(role.getOper_right())) {
			String oper_right = this.role.getOper_right();
			oper_right = oper_right.replace(" ", "");
			role.setOper_right(oper_right);
		}
		role.setCust_id(this.session_cust_id);
		//字段验证
		super.commonValidateField(role);
		if(super.ifvalidatepass){
			return view();
		}		
		this.roleService.update(role);
		this.addActionMessage("修改角色信息成功");
		return list();
	}

	public String getRolemenuright() {
		return rolemenuright;
	}

	public void setRolemenuright(String rolemenuright) {
		this.rolemenuright = rolemenuright;
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
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the rolelist
	 */
	public List getRolelist() {
		return rolelist;
	}

	/**
	 * @param rolelist
	 *            the rolelist to set
	 */
	public void setRolelist(List rolelist) {
		this.rolelist = rolelist;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if(role == null){
			role = new Role();
		}
		String id = role.getRole_id();
		if(!ValidateUtil.isDigital(id)){
			role = this.roleService.get(id);
		}
	}

}
