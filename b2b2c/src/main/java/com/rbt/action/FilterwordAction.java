/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: FilterwordAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Filterword;
import com.rbt.service.IFilterwordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 敏感字Action层实现
 * @author  创建人 邱景岩
 * @date  创建日期 Jul 7, 2011 3:40:23 PM
 */
@Controller
public class FilterwordAction extends BaseAction implements Preparable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1048476386272720275L;

	/*
	 * 搜索字段 name_s:敏感字 rep_name_s:替换字
	 */
	public String name_s;
	public String rep_name_s;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IFilterwordService filterwordService;

	/*
	 * 敏感字信息集合
	 */
	public List filterwordList;

	/*
	 * 敏感字对象
	 */
	public Filterword filterword;

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (name_s != null && !name_s.equals("")) {
			pageMap.put("name", name_s);
		}
		if (rep_name_s != null && !rep_name_s.equals("")) {
			pageMap.put("rep_name", rep_name_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.filterwordService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		filterwordList = this.filterwordService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增敏感字信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//字段验证
		super.commonValidateField(filterword);
		if(super.ifvalidatepass){
			return add();
		}
		this.filterwordService.insert(filterword);
		this.addActionMessage("新增敏感字成功");
		this.filterword = null;
		return INPUT;
	}

	/**
	 * 方法描述：根据主键找出敏感字信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：修改敏感字信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证
		super.commonValidateField(filterword);
		if(super.ifvalidatepass){
			return view();
		}
		this.filterwordService.update(filterword);
		this.addActionMessage("修改敏感字成功");
		return list();
	}

	/**
	 * 方法描述：删除敏感字信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		this.filterwordService.delete(this.filterword.getWord_id());
		this.addActionMessage("删除敏感字成功");
		return list();
	}

	/**
	 * @return the filterwordList
	 */
	public List getFilterwordList() {
		return filterwordList;
	}

	/**
	 * @param filterwordList
	 *            the filterwordList to set
	 */
	public void setFilterwordList(List filterwordList) {
		this.filterwordList = filterwordList;
	}

	/**
	 * @return the filterword
	 */
	public Filterword getFilterword() {
		return filterword;
	}

	/**
	 * @param filterword
	 *            the filterword to set
	 */
	public void setFilterword(Filterword filterword) {
		this.filterword = filterword;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(filterword == null){
			filterword = new Filterword();
		}
		String id = filterword.getWord_id();
		if(!ValidateUtil.isDigital(id)){
			filterword = this.filterwordService.get(id);
		}
	}
}
