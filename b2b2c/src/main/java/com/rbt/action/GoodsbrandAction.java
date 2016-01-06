/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: GoodsbrandAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.BaseAction;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Goodsbrand;
import com.rbt.service.IGoodsbrandService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 商品品牌表action类
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Feb 27 12:42:32 CST 2012
 */
@Controller
public class GoodsbrandAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 商品品牌表对象
	 */
	private Goodsbrand goodsbrand;
	/*
	 * 商品品牌表业务层接口
	 */
	@Autowired
	private IGoodsbrandService goodsbrandService;
	/*
	 * 商品品牌表信息集合
	 */
	public List goodsbrandList;
	/*
	 * 商品品牌商品关系信息串
	 */
    public String cat_attr_str;
    /*
	 * 分类信息集合
	 */
	public List cat_attr_list;
	/*
	 * 品牌标题搜索
	 */
	public String title_s;
	public String goodsbrand_sortno_id;
	public String isort_no;
	/**
	 * @return the goodsbrand
	 */
	public Goodsbrand getGoodsbrand() {
		return goodsbrand;
	}
	/**
	 * @param Goodsbrand
	 *            the goodsbrand to set
	 */
	public void setGoodsbrand(Goodsbrand goodsbrand) {
		this.goodsbrand = goodsbrand;
	}
	
	/**
	 * 方法描述：返回新增商品品牌表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if(cat_attr_str!=null&&!"".equals(cat_attr_str))
		{
			cat_attr_list(cat_attr_str);
		}
		return goUrl(ADD);
	}
	/**
	 * 方法描述：批量排序
	 * @return
	 * @throws Exception
	 */
	public String updatesortno() throws Exception { 
		String id = this.goodsbrand_sortno_id;
		String sort_no =isort_no;
		id = id.replace(" ", "");
		sort_no=sort_no.replace(" ", "");
		String paraidStr[] = id.split(",");
		String parasortStr[] = sort_no.split(",");
		List paraList = new ArrayList();
		if(paraidStr.length>0){
			for(int i=0;i<paraidStr.length;i++){	
				Map sortMap = new HashMap();
				sortMap.put("sort_no", parasortStr[i]);
				sortMap.put("brand_id", paraidStr[i]);
				paraList.add(sortMap);
			}
		}
		this.goodsbrandService.updatesort_no(paraList);
		this.addActionMessage("参数批量排序成功");
		return list();
	}
	/**
	 * 方法描述：新增商品品牌表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if(cat_attr_str!=null &&!"".equals(cat_attr_str))
		{
			goodsbrand.setGoods_attr(cat_attr_str);
		}
		else {
			goodsbrand.setGoods_attr("");
		}
		super.commonValidateField(goodsbrand);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.goodsbrandService.insert(goodsbrand);
		this.addActionMessage("新增商品品牌表成功！");
		this.goodsbrand = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改商品品牌表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(cat_attr_str!=null &&!"".equals(cat_attr_str))
		{
			goodsbrand.setGoods_attr(cat_attr_str);
		}
		else {
			goodsbrand.setGoods_attr("");
		}
		super.commonValidateField(goodsbrand);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.goodsbrandService.update(goodsbrand);
		this.addActionMessage("修改商品品牌表成功！");
		return list();
	}
	/**
	 * 方法描述：删除商品品牌表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.goodsbrand.getBrand_id();
		id = id.replace(" ", "");
		this.goodsbrandService.delete(id);
		this.addActionMessage("删除商品品牌表成功！");
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
		if(title_s!=null&&!"".equals(title_s))
		{
			pageMap.put("brand_name", title_s);
		}
		//根据页面提交的条件找出信息总数
		int count=this.goodsbrandService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		goodsbrandList = this.goodsbrandService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出商品品牌表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		
		String id = this.goodsbrand.getBrand_id();
		if(id==null || id.equals("")){
			goodsbrand = new Goodsbrand();
		}else{
			goodsbrand = this.goodsbrandService.get(id);
		}
		cat_attr_str=goodsbrand.getGoods_attr();
		cat_attr_list(cat_attr_str);
		return goUrl(VIEW);
	}
	/**
	 * 方法描述：构造LIST用于信息的回选
	 * @return
	 * @throws Exception
	 */
	private void cat_attr_list(String cat_ids){
		String ids[]=cat_ids.split("\\|");
		cat_attr_list=new ArrayList();		
		for(int i=0;i<ids.length;i++){
			Map listMap=new HashMap();
			String id=ids[i].replace(" ","");
			listMap.put("id",id);
			String catName=CategoryFuc.getCateNameByMap(id);
			listMap.put("val", catName);
			if(!id.equals("")&&!catName.equals("")){
				cat_attr_list.add(i,listMap);
			}
		}
	}
	/**
	 * @return the GoodsbrandList
	 */
	public List getGoodsbrandList() {
		return goodsbrandList;
	}
	/**
	 * @param goodsbrandList
	 *  the GoodsbrandList to set
	 */
	public void setGoodsbrandList(List goodsbrandList) {
		this.goodsbrandList = goodsbrandList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(goodsbrand == null){
			goodsbrand = new Goodsbrand();
		}
		String id = this.goodsbrand.getBrand_id();
		if(!this.validateFactory.isDigital(id)){
			goodsbrand = this.goodsbrandService.get(id);
		}
	}
	public String getCat_attr_str() {
		return cat_attr_str;
	}
	public void setCat_attr_str(String cat_attr_str) {
		this.cat_attr_str = cat_attr_str;
	}
	public List getCat_attr_list() {
		return cat_attr_list;
	}
	public void setCat_attr_list(List cat_attr_list) {
		this.cat_attr_list = cat_attr_list;
	}
	public String getTitle_s() {
		return title_s;
	}
	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}
	public String getGoodsbrand_sortno_id() {
		return goodsbrand_sortno_id;
	}
	public void setGoodsbrand_sortno_id(String goodsbrand_sortno_id) {
		this.goodsbrand_sortno_id = goodsbrand_sortno_id;
	}
	public String getIsort_no() {
		return isort_no;
	}
	public void setIsort_no(String isort_no) {
		this.isort_no = isort_no;
	}
	

}

