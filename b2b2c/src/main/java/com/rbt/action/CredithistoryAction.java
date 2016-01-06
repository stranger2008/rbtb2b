/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: CredithistoryAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Credithistory;
import com.rbt.service.ICredithistoryService;

/**
 * @function 功能 记录会员信用指数历史action类
 * @author 创建人 林俊钦
 * @date 创建日期 Wed Nov 30 13:35:49 CST 2011
 */
@Controller
public class CredithistoryAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录会员信用指数历史对象
	 */
	public Credithistory credithistory;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ICredithistoryService credithistoryService;
	/*
	 * 记录会员信用指数历史信息集合
	 */
	public List credithistoryList;
	public String cust_name_s;
	public String cre_id;

	
	
	/**
	 * 方法描述：返回新增记录会员信用指数历史页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员信用指数历史
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.credithistoryService.insert(credithistory);
		this.addActionMessage("新增信用指数历史信息成功");
		this.credithistory = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员信用指数历史信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.credithistoryService.update(credithistory);
		this.addActionMessage("修改信用指数历史信息成功");
		return list();
	}
	/**
	 * 方法描述：删除记录会员信用指数历史信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.credithistory.getTrade_id();
		id = id.replace(" ", "");
		this.credithistoryService.delete(id);
		this.addActionMessage("删除信用指数历史信息成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		// 从Session中获取会员标识
		if(cre_id!=null&&!cre_id.equals("")){
			pageMap.put("cust_id", cre_id);
		}else{
			if(this.session_cust_type.equals(Constants.MEMBER_TYPE)){
				pageMap.put("cust_id", this.session_cust_id);
			}
		}
		if(!ValidateUtil.isRequired(cust_name_s)){
			pageMap.put("cust_name", cust_name_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.credithistoryService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		credithistoryList = this.credithistoryService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录会员信用指数历史信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(credithistory.getCust_id()!=null){
			if(accessControl(credithistory.getCust_id())){
				return list();
			}
		}
		String id = this.credithistory.getTrade_id();
		if(id==null || id.equals("")){
			credithistory = new Credithistory();
		}else{
			credithistory = this.credithistoryService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the CredithistoryList
	 */
	public List getCredithistoryList() {
		return credithistoryList;
	}
	/**
	 * @param credithistoryList
	 *  the CredithistoryList to set
	 */
	public void setCredithistoryList(List credithistoryList) {
		this.credithistoryList = credithistoryList;
	}
	
	/**
	 * @return the credithistory
	 */
	public Credithistory getCredithistory() {
		return credithistory;
	}
	/**
	 * @param Credithistory
	 *            the credithistory to set
	 */
	public void setCredithistory(Credithistory credithistory) {
		this.credithistory = credithistory;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(credithistory == null){
			credithistory = new Credithistory();
		}
		String id = this.credithistory.getTrade_id();
		if(!ValidateUtil.isDigital(id)){
			credithistory = this.credithistoryService.get(id);
		}
	}

}

