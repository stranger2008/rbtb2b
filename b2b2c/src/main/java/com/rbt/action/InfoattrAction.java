/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: InfoattrAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Infoattr;
import com.rbt.service.IInfoattrService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 信息属性action类
 * @author 创建人 邱景岩
 * @date 创建日期 Sat Mar 17 10:32:08 CST 2012
 */
@Controller
public class InfoattrAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 信息属性对象
	 */
	private Infoattr infoattr;
	/*
	 * 信息属性业务层接口
	 */
	@Autowired
	private IInfoattrService infoattrService;
	/*
	 * 信息属性信息集合
	 */
	public List infoattrList;

	/**
	 * @return the infoattr
	 */
	public Infoattr getInfoattr() {
		return infoattr;
	}
	/**
	 * @param Infoattr
	 *            the infoattr to set
	 */
	public void setInfoattr(Infoattr infoattr) {
		this.infoattr = infoattr;
	}
	
	/**
	 * 方法描述：返回新增信息属性页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增信息属性
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.infoattrService.insert(infoattr);
		this.addActionMessage("新增信息属性成功！");
		this.infoattr = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改信息属性信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.infoattrService.update(infoattr);
		this.addActionMessage("修改信息属性成功！");
		return list();
	}
	/**
	 * 方法描述：删除信息属性信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.infoattr.getInfoattr_id();
		id = id.replace(" ", "");
		this.infoattrService.delete(id);
		this.addActionMessage("删除信息属性成功！");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		
		//根据页面提交的条件找出信息总数
		int count=this.infoattrService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		infoattrList = this.infoattrService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出信息属性信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.infoattr.getInfoattr_id();
		if(id==null || id.equals("")){
			infoattr = new Infoattr();
		}else{
			infoattr = this.infoattrService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the InfoattrList
	 */
	public List getInfoattrList() {
		return infoattrList;
	}
	/**
	 * @param infoattrList
	 *  the InfoattrList to set
	 */
	public void setInfoattrList(List infoattrList) {
		this.infoattrList = infoattrList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(infoattr == null){
			infoattr = new Infoattr();
		}
		String id = this.infoattr.getInfoattr_id();
		if(!this.validateFactory.isDigital(id)){
			infoattr = this.infoattrService.get(id);
		}
	}

}

