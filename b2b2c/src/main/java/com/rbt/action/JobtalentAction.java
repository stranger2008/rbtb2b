/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: JobtalentAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Jobtalent;
import com.rbt.service.IJobtalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * @function 功能 人才库表action类
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 01 10:39:25 CST 2011
 */
@Controller
public class JobtalentAction extends BaseAction  implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IJobtalentService jobtalentService;
	/*
	 * 人才库表对象
	 */
	public Jobtalent jobtalent;
	/*
	 * 人才库表信息集合
	 */
	public List jobtalentList;
	/*
	 * 搜索字段：添加时间的开始搜索时间
	 */
	public String in_date_s;
    /*
	 * 搜索字段：添加时间的结束搜索时间
	 */
	public String enddateString_s;
	/**
	 * 方法描述：返回新增人才库表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}
	/**
	 * 方法描述：新增人才库表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.jobtalentService.insert(jobtalent);
		this.addActionMessage("新增人才库信息成功");
		this.jobtalent = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改人才库表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.jobtalentService.update(jobtalent);
		this.addActionMessage("修改人才库信息成功");
		return list();
	}
	/**
	 * 方法描述：删除人才库表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.jobtalent.getInbox_id();
		id = id.replace(" ", "");
		this.jobtalentService.delete(id);
		this.addActionMessage("删除人才库信息成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if (in_date_s != null && !in_date_s.equals(""))
			pageMap.put("in_date", in_date_s);
		if (enddateString_s != null && !enddateString_s.equals(""))
			pageMap.put("enddate", enddateString_s);
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.jobtalentService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		jobtalentList = this.jobtalentService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出人才库表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//判断id的值是否符合条件，不符合的话返回到列表
		if(ValidateUtil.isDigital(jobtalent.getInbox_id())){
			return list();
		}	
		return goUrl(VIEW);
	}
	/**
	 * @return the JobtalentList
	 */
	public List getJobtalentList() {
		return jobtalentList;
	}
	/**
	 * @param jobtalentList
	 *  the JobtalentList to set
	 */
	public void setJobtalentList(List jobtalentList) {
		this.jobtalentList = jobtalentList;
	}
	public String getIn_date_s() {
		return in_date_s;
	}
	public void setIn_date_s(String in_date_s) {
		this.in_date_s = in_date_s;
	}
	
	public String getEnddateString_s() {
		return enddateString_s;
	}
	public void setEnddateString_s(String enddateString_s) {
		this.enddateString_s = enddateString_s;
	}
	/**
	 * @return the jobtalent
	 */
	public Jobtalent getJobtalent() {
		return jobtalent;
	}
	/**
	 * @param Jobtalent
	 *            the jobtalent to set
	 */
	public void setJobtalent(Jobtalent jobtalent) {
		this.jobtalent = jobtalent;
	}
	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(jobtalent == null){
			jobtalent = new Jobtalent();
		}
		String id = jobtalent.getInbox_id();
		if(!ValidateUtil.isDigital(id)){
			jobtalent = this.jobtalentService.get(id);
		}	
	}
}

