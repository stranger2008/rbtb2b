/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: SysmenuAction.java 
 */

package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.model.Sysmenu;
import com.rbt.service.ISysmenuService;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;

/**
 * @function 功能 系统菜单action类
 * @author 创建人 李良林
 * @date 创建日期 Jun 13, 2011
 */
@Controller
public class SysmenuAction extends BaseAction implements Preparable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -6657573364095211387L;
	/*
	 * 后台类型默认值
	 */
	private static final String DEFAULT_SYSCODE_VALUE = "sys";
	/*
	 * 后台类型字段 syscode_s
	 */
	public String syscode_s;
	/*
	 * 系统菜单对象
	 */
	public Sysmenu sysmenu;

	/*
	 * 业务层接口
	 */
	@Autowired
	public ISysmenuService sysmenuService;

	/*
	 * 菜单信息集合
	 */
	public List adminmenuList;
	public List membermenuList;
	public List b2cmenuList;
	/**
	 * 方法描述：返回新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String menu_id = RandomStrUtil.getNumberRand();
		sysmenu.setMenu_id(menu_id);
		//字段验证
		super.commonValidateField(sysmenu);
		if(super.ifvalidatepass){
			return add();
		}
		this.sysmenuService.insert(sysmenu);
		this.addActionMessage("新增菜单信息成功");
		this.sysmenu = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改菜单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证
		super.commonValidateField(sysmenu);
		if(super.ifvalidatepass){
			return view();
		}
		this.sysmenuService.update(sysmenu);
		this.addActionMessage("修改菜单信息成功");
		return list();
	}

	/**
	 * 方法描述：删除菜单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deletemenu() throws Exception {
		String menuid = this.sysmenu.getMenu_id();
		menuid = menuid.replace(" ", "");
		this.sysmenuService.delete(menuid);
		this.addActionMessage("删除菜单信息成功");
		return list();
	}

	public static String getListByUpmenuid() throws Exception {
		return "sssssssssss";
	}
 
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 页面搜索提交的参数
		Map memberMap = new HashMap();
		if (syscode_s != null && !syscode_s.equals("")) {
			memberMap.put("syscode", syscode_s);
		} else {
			setSyscode_s("sys");
			memberMap.put("syscode", "com");
		}
        membermenuList = this.sysmenuService.getList(memberMap);
		
		Map adminMap = new HashMap();
		adminMap.put("syscode", "sys");
		adminmenuList = this.sysmenuService.getList(adminMap);
		
		Map b2cMap = new HashMap();
		b2cMap.put("syscode", "b2c");
		b2cmenuList = this.sysmenuService.getList(b2cMap);
		
		
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出菜单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}

	/**
	 * @function 功能 系统后台菜单
	 * @author  创建人 邱景岩
	 * @date  创建日期 Aug 31, 2011 5:46:47 PM
	 */
	@Action(value="sysmenu",results={@Result(name="success",location="/WEB-INF/template/manager/admin/Sysmenu/menulist.ftl")})
	public String execute() throws Exception {
		//所有后台菜单
		Map menuMap = new HashMap();
		menuMap.put("syscode", DEFAULT_SYSCODE_VALUE);
		adminmenuList = this.sysmenuService.getList(menuMap);
		return SUCCESS;
	}
	
	public String menuview(){
		HttpServletRequest request = getRequest();
		HttpServletResponse response=getResponse();
		redirectUrl =request.getParameter("redurl");
		if(redirectUrl != null && !redirectUrl.equals("")){
			return "redirectUrl";
		}else{
			response.setStatus(301);
			response.setHeader( "Location", "/adminindex.action");
			response.setHeader( "Connection", "close" );
			return null;
		}
		
	}
	/**
	 * @return the adminmenuList
	 */
	public List getAdminmenuList() {
		return adminmenuList;
	}

	/**
	 * @param adminmenuList the adminmenuList to set
	 */
	public void setAdminmenuList(List adminmenuList) {
		this.adminmenuList = adminmenuList;
	}

	/**
	 * @return the membermenuList
	 */
	public List getMembermenuList() {
		return membermenuList;
	}

	/**
	 * @param membermenuList the membermenuList to set
	 */
	public void setMembermenuList(List membermenuList) {
		this.membermenuList = membermenuList;
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
	 * @return the sysmenu
	 */
	public Sysmenu getSysmenu() {
		return sysmenu;
	}

	public List getB2cmenuList() {
		return b2cmenuList;
	}

	public void setB2cmenuList(List list) {
		b2cmenuList = list;
	}

	/**
	 * @param sysmenu
	 *            the sysmenu to set
	 */
	public void setSysmenu(Sysmenu sysmenu) {
		this.sysmenu = sysmenu;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(sysmenu == null){
			sysmenu = new Sysmenu();
		}
		String id = sysmenu.getMenu_id();
		if(!ValidateUtil.isRequired(id)){
			sysmenu = this.sysmenuService.get(id);
		}
	}

}
