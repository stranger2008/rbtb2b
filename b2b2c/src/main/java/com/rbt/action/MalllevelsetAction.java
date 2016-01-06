/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MalllevelsetAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Malllevelset;
import com.rbt.service.IMalllevelsetService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 会员等级设置表action类
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Feb 29 10:28:35 CST 2012
 */
@Controller
public class MalllevelsetAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 会员等级设置表对象
	 */
	private Malllevelset malllevelset;
	/*
	 * 会员等级设置表业务层接口
	 */
	@Autowired
	private IMalllevelsetService malllevelsetService;
	/*
	 * 会员等级设置表信息集合
	 */
	public List malllevelsetList;
	public String inter_lower_s;
	public String inter_height_s;
	public String mem_type_s;
	public String discount_s;
	public String level_name_s;

	/**
	 * @return the malllevelset
	 */
	public Malllevelset getMalllevelset() {
		return malllevelset;
	}
	/**
	 * @param Malllevelset
	 *            the malllevelset to set
	 */
	public void setMalllevelset(Malllevelset malllevelset) {
		this.malllevelset = malllevelset;
	}
	
	/**
	 * 方法描述：返回新增会员等级设置表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员等级设置表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(malllevelset);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.malllevelsetService.insert(malllevelset);
		this.addActionMessage("新增会员等级设置表成功！");
		this.malllevelset = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改会员等级设置表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(malllevelset);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.malllevelsetService.update(malllevelset);
		this.addActionMessage("修改会员等级设置表成功！");
		return list();
	}
	/**
	 * 方法描述：删除会员等级设置表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.malllevelset.getLevel_id();
		id = id.replace(" ", "");
		this.malllevelsetService.delete(id);
		this.addActionMessage("删除会员等级设置表成功！");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exceptionpublic 
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if(inter_height_s!=null&&!"".equals(inter_height_s))
		pageMap.put("inter_height",inter_height_s );
		
		if(inter_lower_s!=null&&!"".equals(inter_lower_s))
		pageMap.put("inter_lower", inter_lower_s);
		
		if(mem_type_s!=null&&!"".equals(mem_type_s)&&!mem_type_s.equals("4"))
		{
			pageMap.put("mem_type", mem_type_s);
		}
		if(discount_s!=null&&!"".equals(discount_s))
		pageMap.put("discount", discount_s);
		
		if(level_name_s!=null&&!"".equals(level_name_s))
		pageMap.put("level_name", level_name_s);
		//根据页面提交的条件找出信息总数
		int count=this.malllevelsetService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		malllevelsetList = this.malllevelsetService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出会员等级设置表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.malllevelset.getLevel_id();
		if(id==null || id.equals("")){
			malllevelset = new Malllevelset();
		}else{
			malllevelset = this.malllevelsetService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the MalllevelsetList
	 */
	public List getMalllevelsetList() {
		return malllevelsetList;
	}
	/**
	 * @param malllevelsetList
	 *  the MalllevelsetList to set
	 */
	public void setMalllevelsetList(List malllevelsetList) {
		this.malllevelsetList = malllevelsetList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(malllevelset == null){
			malllevelset = new Malllevelset();
		}
		String id = this.malllevelset.getLevel_id();
		if(!this.validateFactory.isDigital(id)){
			malllevelset = this.malllevelsetService.get(id);
		}
	}
	public String getInter_lower_s() {
		return inter_lower_s;
	}
	public void setInter_lower_s(String inter_lower_s) {
		this.inter_lower_s = inter_lower_s;
	}
	public String getInter_height_s() {
		return inter_height_s;
	}
	public void setInter_height_s(String inter_height_s) {
		this.inter_height_s = inter_height_s;
	}
	public String getMem_type_s() {
		return mem_type_s;
	}
	public void setMem_type_s(String mem_type_s) {
		this.mem_type_s = mem_type_s;
	}
	public String getDiscount_s() {
		return discount_s;
	}
	public void setDiscount_s(String discount_s) {
		this.discount_s = discount_s;
	}
	public String getLevel_name_s() {
		return level_name_s;
	}
	public void setLevel_name_s(String level_name_s) {
		this.level_name_s = level_name_s;
	}

}

