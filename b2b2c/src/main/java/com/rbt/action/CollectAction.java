/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: CollectAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Collect;
import com.rbt.service.ICollectService;
import com.rbt.service.IMembercatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * @function 功能 记录会员商机收藏信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Jul 20 14:27:09 CST 2011
 */
@Controller
public class CollectAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录会员商机收藏信息对象
	 */
	public Collect collect;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ICollectService collectService;
	@Autowired
	private IMembercatService membercatService;
	/*
	 * 记录会员商机收藏信息信息集合
	 */
	public List collectList;
	/*
	 * 会员产品自定义分类List
	 */
	public List membercatList;
	/*
	 * url值
	 */
	public String page_url;
	/*
	 * 搜索字段
	 */
	public String starttime_s;
	public String endtime_s;
	public String title_s;
	public String cust_name_s;
	/*
	 * 按照自定义分类搜索
	 */
	public String cat_id_s;
	
	public void setPlatType(){
		mall_type =Constants.MALL_TYPE_B2C;
	}

	//为操作准备会员自定义分类下拉框
	public void setMemberCatList(){
		Map map = new HashMap();
		// 操作者为会员则默认加入搜索条件，绑定会员自定义产品分类下拉框的绑定
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			map.put("cust_id", this.session_cust_id);
			//cat_type  0：产品 1：收藏 2：商友
			map.put("cat_type","1");
		}
		membercatList = this.membercatService.getList(map);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 13, 2012 1:42:18 PM
	 * @Method Description : 会员发布条数的判断
	 */
	public boolean controlInfoNum(){
		//查出已发布的信息个数
		Map tmap = new HashMap();
		tmap.put("cust_id", this.session_cust_id);
		int count = this.collectService.getCount(tmap);
		if(controlMsgNum(count,"collect")){
			return true;
		}else{
			return false;
		}
	}

	
	
	/**
	 * 方法描述：返回新增记录会员商机收藏信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//为操作准备会员自定义分类下拉框
		setMemberCatList();
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			controlInfoNum();
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员商机收藏信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//collect.setLink_url(page_url);
		//获取客户标识
		setPlatType();
		this.collect.setCust_id(this.session_cust_id);
		collect.setPlat_type(mall_type);
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return add();
			}
		}
		//字段验证
		super.commonValidateField(collect);
		if(super.ifvalidatepass){
			return view();
		}
		this.collectService.insert(collect);
		this.addActionMessage("收藏成功");
		this.collect = null;
		is_crotorl=true;
		return add();
	}
	
	/**
	 * 方法描述：新增记录会员商机收藏信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String inserts() throws Exception {
		if(collect.getCat_id()==null && "0".equals(collect.getCat_id()))
		{
			this.addFieldError("collect.cat_id", "分类不能为空");
		}
		setPlatType();
		collect.setCust_id(this.session_cust_id);
		collect.setPlat_type(mall_type);
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return add();
			}
		}
		//字段验证
		super.commonValidateField(collect);
		if(super.ifvalidatepass){
			return add();
		}
		this.collectService.insert(collect);
		this.addActionMessage("商机收藏成功");
		this.collect = null;
		is_crotorl=true;
		return add();
	}
	
	public void ajxinsert() throws IOException{
		//AJAX获取操作获取运费
		
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if("".equals(this.session_cust_id)){
	           out.write("1");
		}else{
		//colltype 0标识商品  1标识店铺
		String colltype=request.getParameter("colltype");
		
		String title =  URLDecoder.decode(request.getParameter("title"), "UTF-8");
		String link_url = request.getParameter("link_url");
		setPlatType();
		collect.setTitle(title);
		collect.setLink_url(link_url);
		collect.setCust_id(this.session_cust_id);
		collect.setPlat_type(mall_type);
		collect.setCat_id(colltype);
		collectService.insert(collect);
		out.write("0");
		}
	}

	/**
	 * 方法描述：修改记录会员商机收藏信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证
		super.commonValidateField(collect);
		if(super.ifvalidatepass){
			return view();
		}
		this.collectService.update(collect);
		this.addActionMessage("修改收藏成功");
		return list();
	}
	/**
	 * 方法描述：删除记录会员商机收藏信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.collect.getColl_id();
		id = id.replace(" ", "");
		this.collectService.delete(id);
		this.addActionMessage("删除收藏成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		
		setMemberCatList();
		setPlatType();
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
			pageMap.put("plat_type", mall_type);
		}
	

		if(title_s!=null && !title_s.equals("")) pageMap.put("title", title_s);
		if(cust_name_s!=null && !cust_name_s.equals("")) pageMap.put("cust_name", cust_name_s);
		if(starttime_s!=null && !starttime_s.equals("")) pageMap.put("starttime", starttime_s);
		if(endtime_s!=null && !endtime_s.equals("")) pageMap.put("endtime", endtime_s);
		if(cat_id_s!=null && !cat_id_s.equals("")) pageMap.put("cat_id", cat_id_s);
		
		//过滤地区
		pageMap=super.areafilter(pageMap);
		
		//根据页面提交的条件找出信息总数
		int count=this.collectService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		collectList = this.collectService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录会员商机收藏信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(collect.getCust_id()!=null){
			if(accessControl(collect.getCust_id())){
				return list();
			}
		}
		setMemberCatList();
		String id = collect.getColl_id();
		if(!ValidateUtil.isDigital(id)){
			collect = this.collectService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the CollectList
	 */
	public List getCollectList() {
		return collectList;
	}
	/**
	 * @param collectList
	 *  the CollectList to set
	 */
	public void setCollectList(List collectList) {
		this.collectList = collectList;
	}
	public String getPage_url() {
		return page_url;
	}
	public void setPage_url(String page_url) {
		this.page_url = page_url;
	}

	public String getStarttime_s() {
		return starttime_s;
	}


	public void setStarttime_s(String starttime_s) {
		this.starttime_s = starttime_s;
	}


	public String getEndtime_s() {
		return endtime_s;
	}


	public void setEndtime_s(String endtime_s) {
		this.endtime_s = endtime_s;
	}


	public String getTitle_s() {
		return title_s;
	}
	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}
	public String getCust_name_s() {
		return cust_name_s;
	}
	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}
	public List getMembercatList() {
		return membercatList;
	}
	public void setMembercatList(List membercatList) {
		this.membercatList = membercatList;
	}
	public String getCat_id_s() {
		return cat_id_s;
	}
	public void setCat_id_s(String cat_id_s) {
		this.cat_id_s = cat_id_s;
	}
	
	/**
	 * @return the collect
	 */
	public Collect getCollect() {
		return collect;
	}
	/**
	 * @param Collect
	 *            the collect to set
	 */
	public void setCollect(Collect collect) {
		this.collect = collect;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if(collect == null){
			collect = new Collect();
		}
		String id = collect.getColl_id();
		if(!ValidateUtil.isDigital(id)){
			collect = this.collectService.get(id);
		}
		System.out.println(collect);
	}
}

