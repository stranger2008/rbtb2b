/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: AreasetAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.BaseAction;
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Areaset;
import com.rbt.service.IAreasetService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录区域设置信息action类
 * @author 创建人 胡惜坤
 * @date 创建日期 Wed Mar 28 13:22:27 CST 2012
 */
@Controller
public class AreasetAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录区域设置信息对象
	 */
	private Areaset areaset;
	/*
	 * 记录区域设置信息业务层接口
	 */
	@Autowired
	private IAreasetService areasetService;
	/*
	 * 记录区域设置信息信息集合
	 */
	public List areasetList;
	public String smode_id;
	public String area_attr_str;
	public List area_attr_list;
	public String area_attr_str_first;
	public String  area_attr_str_first_name;
	public String areaset_name_s;
	/**
	 * @return the areaset
	 */
	public Areaset getAreaset() {
		return areaset;
	}
	/**
	 * @param Areaset
	 *            the areaset to set
	 */
	public void setAreaset(Areaset areaset) {
		this.areaset = areaset;
	}
	
	/**
	 * 方法描述：返回新增记录区域设置信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if(area_attr_str!=null&&!"".equals(area_attr_str))
		{
			area_attr_lists(area_attr_str);
		}
		if(area_attr_str_first!=null&&!"".equals(area_attr_str_first))
		{
			area_attr_str_first_name=AreaFuc.getAreaNameByMap(area_attr_str_first);
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录区域设置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		areaset.setSmode_id(smode_id);
		if(area_attr_str!=null&&!"".equals(area_attr_str))
		{
			area_attr_str=area_attr_str.replace(" ", "");
			areaset.setEnd_area(area_attr_str);
		}
		else {
		  this.addFieldError("end_arear","请选择到达地区");
		}
		if(area_attr_str_first!=null&&!"".equals(area_attr_str_first))
		{
			area_attr_str_first=area_attr_str_first.replace(" ", "");
		}
		else {
		  this.addFieldError("start_area","请选择开始地区");
		}
		super.commonValidateField(areaset);
		if(super.ifvalidatepass){
			return add();
		}
		
		this.areasetService.insert(areaset);
		this.addActionMessage("新增记录区域设置信息成功！");
		this.areaset = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录区域设置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(area_attr_str!=null&&!"".equals(area_attr_str))
		{
			area_attr_str=area_attr_str.replace(" ", "");
			areaset.setEnd_area(area_attr_str);
		}
		else {
		  this.addFieldError("end_arear","请选择到达地区");
		}
		if(area_attr_str_first!=null&&!"".equals(area_attr_str_first))
		{
			area_attr_str_first=area_attr_str_first.replace(" ", "");
		}
		else {
		  this.addFieldError("start_area","请选择开始地区");
		}
		super.commonValidateField(areaset);
		if(super.ifvalidatepass){
			return view();
		}
		
		areaset.setSmode_id(smode_id);
		this.areasetService.update(areaset);
		this.addActionMessage("修改记录区域设置信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录区域设置信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.areaset.getAreaset_id();
		id = id.replace(" ", "");
		this.areasetService.delete(id);
		this.addActionMessage("删除记录区域设置信息成功！");
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
		pageMap.put("smode_id", smode_id);
		if(areaset_name_s!=null&&!"".equals(areaset_name_s))
		{
			pageMap.put("areaset_name", areaset_name_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.areasetService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		areasetList = this.areasetService.getList(pageMap);
		if(areasetList!=null&&areasetList.size()>0)
		{
			for(int i=0;i<areasetList.size();i++)
			{
				HashMap map=new HashMap ();
				map=(HashMap)areasetList.get(i);
				if(map.get("end_area")!=null)
				{
					map.put("end_area", replace_area_attr_name(map.get("end_area").toString()));
				}
				if(map.get("start_area")!=null)
				{
					map.put("start_area", AreaFuc.getAreaNameByMap(map.get("start_area").toString()));
				}
			}
		}
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：地区ID替换成名称
	 * @return
	 * @throws Exception
	 */
	private String  replace_area_attr_name(String area_ids){
		String area_name_attr="";
		String ids[]=area_ids.split("\\|");
		area_attr_list=new ArrayList();		
		for(int i=0;i<ids.length;i++){
			Map listMap=new HashMap();
			String id=ids[i].replace(" ","");
			area_name_attr+=AreaFuc.getAreaNameByMap(id)+"|";
		}
		 if(area_name_attr!=null&&!"".equals(area_name_attr)&&area_name_attr.contains("|"))
		 {
			 area_name_attr=area_name_attr.substring(0, area_name_attr.length()-1);
		 }
		 return area_name_attr;
	}
	/**
	 * 方法描述：根据主键找出记录区域设置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.areaset.getAreaset_id();
		if(id==null || id.equals("")){
			areaset = new Areaset();
		}else{
			areaset = this.areasetService.get(id);
		}
		area_attr_str=areaset.getEnd_area();
		area_attr_lists(area_attr_str);
		area_attr_str_first_name=AreaFuc.getAreaNameByMap(area_attr_str_first);
		return goUrl(VIEW);
	}
	/**
	 * 方法描述：构造LIST用于信息的回选
	 * @return
	 * @throws Exception
	 */
	private void area_attr_lists(String area_ids){
		String ids[]=area_ids.split("\\|");
		area_attr_list=new ArrayList();		
		for(int i=0;i<ids.length;i++){
			Map listMap=new HashMap();
			String id=ids[i].replace(" ","");
			listMap.put("id",id);
			String areaName=AreaFuc.getAreaNameByMap(id);
			listMap.put("val", areaName);
			if(!id.equals("")&&!areaName.equals("")){
				area_attr_list.add(i,listMap);
			}
		}
	}
	/**
	 * @return the AreasetList
	 */
	public List getAreasetList() {
		return areasetList;
	}
	/**
	 * @param areasetList
	 *  the AreasetList to set
	 */
	public void setAreasetList(List areasetList) {
		this.areasetList = areasetList;
	}
	
	public List getArea_attr_list() {
		return area_attr_list;
	}
	public void setArea_attr_list(List area_attr_list) {
		this.area_attr_list = area_attr_list;
	}
	public String getArea_attr_str() {
		return area_attr_str;
	}
	public void setArea_attr_str(String area_attr_str) {
		this.area_attr_str = area_attr_str;
	}
	
	public String getArea_attr_str_first() {
		return area_attr_str_first;
	}
	public void setArea_attr_str_first(String area_attr_str_first) {
		this.area_attr_str_first = area_attr_str_first;
	}
	public String getArea_attr_str_first_name() {
		return area_attr_str_first_name;
	}
	public void setArea_attr_str_first_name(String area_attr_str_first_name) {
		this.area_attr_str_first_name = area_attr_str_first_name;
	}
	
	public String getAreaset_name_s() {
		return areaset_name_s;
	}
	public void setAreaset_name_s(String areaset_name_s) {
		this.areaset_name_s = areaset_name_s;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(areaset == null){
			areaset = new Areaset();
		}
		String id = this.areaset.getAreaset_id();
		if(!this.validateFactory.isDigital(id)){
			areaset = this.areasetService.get(id);
		}
	}

}

