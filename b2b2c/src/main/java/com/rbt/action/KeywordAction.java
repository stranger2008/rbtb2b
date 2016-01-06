/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: KeywordAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Keyword;
import com.rbt.service.ICommparaService;
import com.rbt.service.IKeywordService;
import com.rbt.service.ISysmoduleService;

/**
 * @function 功能 关键字action类
 * @author 创建人 蔡毅存
 * @date 创建日期 July 6, 2011
 */
@Controller
public class KeywordAction extends BaseAction  implements Preparable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4800729065173351640L;
	/*
	 * 列表页关键字对象
	 */
	public Keyword keyword;
	/*
	 * 注入keywordService
	 */
	@Autowired
	public IKeywordService keywordService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	
	/*
	 * 列表页关键字列表
	 */
	public List keywordList;
	/*
	 * 参数列表
	 */
	public List commparaList;
	/*
	 * 参数匹配列表
	 */
	public List commparaList_value;
	/*
	 * 搜索关键字
	 */
	public String key_name_s;
	public String module_type_s;

	/**
	 * 方法描述：根据主键找出导航链接信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//判断id的值是否符合条件，不符合的话返回到列表
		String rbtid=this.keyword.getKey_id();
		if(ValidateUtil.isDigital(rbtid)){
			return list();
		}	
		//绑定参数下拉列表
		getcommpara();
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：修改导航链接信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {		
		//字段验证
		super.commonValidateField(keyword);
		if(super.ifvalidatepass){
			return view();
		}
		if (keyword.getModule_type().equals("0")) {
			this.addFieldError("keyword.module_type", "请选择关键字类型");
			return add();
		}
		this.keywordService.update(keyword);
		this.addActionMessage("修改关键字成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		//绑定参数下拉列表
		getcommpara();
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (key_name_s != null && !key_name_s.equals("")){
			pageMap.put("key_name", key_name_s);
		}
		if (module_type_s != null && !module_type_s.equals("")){
			pageMap.put("module_type", module_type_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.keywordService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 找出信息列表，放入list
		keywordList = this.keywordService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：删除导航链接信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String keyid = this.keyword.getKey_id();
		keyid = keyid.replace(" ", "");
		this.keywordService.delete(keyid);
		this.addActionMessage("删除关键字成功");
		return list();
	}

	/**
	 * 方法描述：返回新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//绑定参数下拉列表
		getcommpara();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增关键字信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		String key_name = this.keyword.getKey_name();
		String module_type = this.keyword.getModule_type();
		String num=this.keyword.getNum();		
		//字段验证
		super.commonValidateField(keyword);
		if(super.ifvalidatepass){
			return add();
		}
		if (keyword.getModule_type().equals("0")) {
			this.addFieldError("keyword.module_type", "请选择关键字类型");
			return add();
		}
		Map pageMap = new HashMap();
		pageMap.put("keyword", key_name);
		pageMap.put("module_type", module_type);
		
		// 通过用户名找出用户信息
		List keywordList = this.keywordService.getList(pageMap);
		if (keywordList != null && keywordList.size() > 0) {
			Map map = (HashMap) keywordList.get(0);
			String keyid = "";
			if (map.get("key_id") != null){
				keyid = map.get("key_id").toString();
			}
			keyword = this.keywordService.get(keyid);
			if(keyword!=null){
				int nums = Integer.parseInt(keyword.getNum()) + 1;
				keyword.setNum(String.valueOf(nums));
				this.keywordService.update(keyword);
			}
		}else{
			keyword.setNum(num);
			this.keywordService.insert(keyword);
		}
		
		this.addActionMessage("新增关键字成功");
		this.keyword = null;
		return add();
	}
	
	//绑定参数下拉列表
    public void getcommpara(){
		Map pageMap = new HashMap();
    	pageMap.put("state_code", "0");
    	commparaList = this.sysmoduleService.getList(pageMap);
    }

	/**
	 * 方法描述：get/set方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public List getKeywordList() {
		return keywordList;
	}

	public void setKeywordList(List keywordList) {
		this.keywordList = keywordList;
	}

	public String getKey_name_s() {
		return key_name_s;
	}

	public void setKey_name_s(String key_name_s) {
		this.key_name_s = key_name_s;
	}

	public String getModule_type_s() {
		return module_type_s;
	}

	public void setModule_type_s(String module_type_s) {
		this.module_type_s = module_type_s;
	}

	public List getCommparaList() {
		return commparaList;
	}

	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}

	public List getCommparaList_value() {
		return commparaList_value;
	}

	public void setCommparaList_value(List commparaList_value) {
		this.commparaList_value = commparaList_value;
	}
	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		
		super.saveRequestParameter();
		
		if(keyword == null){
			keyword = new Keyword();
		}
		String id = keyword.getKey_id();
		if(!ValidateUtil.isDigital(id)){
			keyword = this.keywordService.get(id);
		}
	}


}
