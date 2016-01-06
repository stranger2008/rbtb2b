/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MembercreditAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Membercredit;
import com.rbt.service.IMembercreditService;

/**
 * @function 功能 记录会员信用指数action类
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Nov 30 13:37:20 CST 2011
 */
@Controller
public class MembercreditAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录会员信用指数对象
	 */
	public Membercredit membercredit;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMembercreditService membercreditService;
	/*
	 * 记录会员信用指数信息集合
	 */
	public List membercreditList;

	
	
	/**
	 * 方法描述：返回新增记录会员信用指数页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员信用指数
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.membercreditService.insert(membercredit);
		this.addActionMessage("新增信用指数信息成功");
		this.membercredit = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员信用指数信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.membercreditService.update(membercredit);
		this.addActionMessage("修改信用指数信息成功");
		return list();
	}
	/**
	 * 方法描述：删除记录会员信用指数信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.membercredit.getCust_id();
		id = id.replace(" ", "");
		this.membercreditService.delete(id);
		this.addActionMessage("删除信用指数信息成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.membercreditService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		membercreditList = this.membercreditService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录会员信用指数信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.membercredit.getCust_id();
		if(id==null || id.equals("")){
			membercredit = new Membercredit();
		}else{
			membercredit = this.membercreditService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the MembercreditList
	 */
	public List getMembercreditList() {
		return membercreditList;
	}
	/**
	 * @param membercreditList
	 *  the MembercreditList to set
	 */
	public void setMembercreditList(List membercreditList) {
		this.membercreditList = membercreditList;
	}
	
	/**
	 * @return the membercredit
	 */
	public Membercredit getMembercredit() {
		return membercredit;
	}
	/**
	 * @param Membercredit
	 *            the membercredit to set
	 */
	public void setMembercredit(Membercredit membercredit) {
		this.membercredit = membercredit;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(membercredit == null){
			membercredit = new Membercredit();
		}
		String id = this.membercredit.getCust_id();
		if(!ValidateUtil.isDigital(id)){
			membercredit = this.membercreditService.get(id);
		}
	}

}

