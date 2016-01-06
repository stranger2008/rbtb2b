/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: SellerscoreAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.function.AreaFuc;
import com.rbt.model.Sellerscore;
import com.rbt.service.ISellerscoreService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商家打分信息action类
 * @author 创建人 胡惜坤
 * @date 创建日期 Fri Mar 30 11:03:31 CST 2012
 */
@Controller
public class SellerscoreAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录商家打分信息对象
	 */
	private Sellerscore sellerscore;
	/*
	 * 记录商家打分信息业务层接口
	 */
	@Autowired
	private ISellerscoreService sellerscoreService;
	/*
	 * 记录商家打分信息信息集合
	 */
	public List sellerscoreList;
	public List sellerscoreCountList;
    public String cust_name_s;
    public String start_date_s;
    public String end_date_s;
    public String cust_id_s;
	/**
	 * @return the sellerscore
	 */
	public Sellerscore getSellerscore() {
		return sellerscore;
	}
	/**
	 * @param Sellerscore
	 *            the sellerscore to set
	 */
	public void setSellerscore(Sellerscore sellerscore) {
		this.sellerscore = sellerscore;
	}
	
	/**
	 * 方法描述：返回新增记录商家打分信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录商家打分信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(sellerscore);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.sellerscoreService.insert(sellerscore);
		this.addActionMessage("新增记录商家打分信息成功！");
		this.sellerscore = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录商家打分信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(sellerscore);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.sellerscoreService.update(sellerscore);
		this.addActionMessage("修改记录商家打分信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录商家打分信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.sellerscore.getTrade_id();
		id = id.replace(" ", "");
		this.sellerscoreService.delete(id);
		this.addActionMessage("删除记录商家打分信息成功！");
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
		if(cust_name_s!=null&&!"".equals(cust_name_s))
		{
			pageMap.put("cust_name", cust_name_s);
		}
		if(start_date_s!=null&&!"".equals(start_date_s))
		{
			pageMap.put("start_date", start_date_s);
		}
		if(end_date_s!=null&&!"".equals(end_date_s))
		{
			pageMap.put("end_date", end_date_s);
		}
		if(cust_id_s!=null&&!"".equals(cust_id_s))
		{
			pageMap.put("get_cust_id", cust_id_s);
		}
		
		//根据页面提交的条件找出信息总数
		int count=this.sellerscoreService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		sellerscoreList = this.sellerscoreService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据搜索条件列出会员后台信息列表
	 * @return
	 * @throws Exception
	 */
	public String bmalllist() throws Exception {
		Map pageMap = new HashMap();
		
		if(this.session_cust_id!=null&&!"".equals(session_cust_id))
		{
			pageMap.put("get_cust_id", session_cust_id);
		
			//根据页面提交的条件找出信息总数
			int count=this.sellerscoreService.getCount(pageMap);
			
			//分页插件
			pageMap = super.pageTool(count,pageMap);
			sellerscoreList = this.sellerscoreService.getList(pageMap);
			Map pagecountMap=new HashMap ();
			pagecountMap.put("cust_id", session_cust_id);
			sellerscoreCountList=this.sellerscoreService.getCountList(pagecountMap);
		}
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String countindex()throws Exception {
	
		Map pageMap = new HashMap();
		if(cust_name_s!=null&&!"".equals(cust_name_s))
		{
			pageMap.put("cust_name", cust_name_s);
		}
		pageMap.put("mem_type", "0");
		//商城地区过滤
		pageMap=super.shopareafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.sellerscoreService.getIndexCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		sellerscoreList = this.sellerscoreService.getCountList(pageMap);
		return goUrl("countindex");
	}
	/**
	 * 方法描述：根据主键找出记录商家打分信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.sellerscore.getTrade_id();
		if(id==null || id.equals("")){
			sellerscore = new Sellerscore();
		}else{
			sellerscore = this.sellerscoreService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the SellerscoreList
	 */
	public List getSellerscoreList() {
		return sellerscoreList;
	}
	/**
	 * @param sellerscoreList
	 *  the SellerscoreList to set
	 */
	public void setSellerscoreList(List sellerscoreList) {
		this.sellerscoreList = sellerscoreList;
	}
	
	public String getCust_name_s() {
		return cust_name_s;
	}
	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}
	public String getStart_date_s() {
		return start_date_s;
	}
	public void setStart_date_s(String start_date_s) {
		this.start_date_s = start_date_s;
	}
	public String getEnd_date_s() {
		return end_date_s;
	}
	public void setEnd_date_s(String end_date_s) {
		this.end_date_s = end_date_s;
	}
	public String getCust_id_s() {
		return cust_id_s;
	}
	public void setCust_id_s(String cust_id_s) {
		this.cust_id_s = cust_id_s;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(sellerscore == null){
			sellerscore = new Sellerscore();
		}
		String id = this.sellerscore.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			sellerscore = this.sellerscoreService.get(id);
		}
	}

}

