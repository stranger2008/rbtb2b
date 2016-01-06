/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: SubscribeinfoAction.java 
 */
package com.rbt.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Subscribeinfo;
import com.rbt.service.ISubscribeService;
import com.rbt.service.ISubscribeinfoService;
/**
 * @function 功能 记录会员商机订阅信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 21 14:04:42 CST 2011
 */
@Controller
public class SubscribeinfoAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录会员商机订阅信息对象
	 */
	public Subscribeinfo subscribeinfo;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ISubscribeinfoService subscribeinfoService;
	@Autowired
	public ISubscribeService subscribeService;
	/*
	 * 记录会员商机订阅信息信息集合
	 */
	public List subscribeinfoList;
	/*
	 * 记录会员商机订阅信息信息集合
	 */
	public List subscribeList;
	/*
	 * 搜索字段
	 */
	public String starttime_s;
	public String endtime_s;
	public String cust_name_s;
	public String keyword_s;
	public String info_type_s;
	

	/**
	 * 方法描述：返回新增记录会员商机订阅信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员商机订阅信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.subscribeinfoService.insert(subscribeinfo);
		this.addActionMessage("新增商机订阅成功");
		this.subscribeinfo = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员商机订阅信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.subscribeinfoService.update(subscribeinfo);
		this.addActionMessage("修改商机订阅成功");
		return list();
	}
	/**
	 * 方法描述：删除记录会员商机订阅信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.subscribeinfo.getSend_id();
		id = id.replace(" ", "");
		this.subscribeinfoService.delete(id);
		this.addActionMessage("删除商机订阅成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		 if(info_type_s!=null && !info_type_s.equals("")) pageMap.put("info_type", info_type_s);
		 if(cust_name_s!=null && !cust_name_s.equals("")) pageMap.put("cust_name", cust_name_s);
	        if(keyword_s!=null && !keyword_s.equals("")) pageMap.put("keyword", keyword_s);
	        if (starttime_s != null && !starttime_s.equals("")) {
				pageMap.put("starttime", starttime_s);
				if (endtime_s == null || endtime_s.equals("")) {
					Date mydate = new Date();
					@SuppressWarnings("unused")
					String end_time = mydate.toLocaleString().substring(0, 8);
					pageMap.put("endtime", end_time);
				} else {
					pageMap.put("endtime", endtime_s);
				}
			}
	       //过滤地区
			pageMap=super.areafilter(pageMap);
	        //根据页面提交的条件找出信息总数
			int count =this.subscribeinfoService.getCount(pageMap);
			//分页插件
			pageMap = super.pageTool(count,pageMap);
		subscribeinfoList = this.subscribeinfoService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录会员商机订阅信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(subscribeinfo.getCust_id()!=null){
			if(accessControl(subscribeinfo.getCust_id())){
				return list();
			}
		}
		return goUrl(VIEW);
	}

	/**
	 * @return the SubscribeinfoList
	 */
	public List getSubscribeinfoList() {
		return subscribeinfoList;
	}
	/**
	 * @param subscribeinfoList
	 *  the SubscribeinfoList to set
	 */
	public void setSubscribeinfoList(List subscribeinfoList) {
		this.subscribeinfoList = subscribeinfoList;
	}
	
	public List getSubscribeList() {
		return subscribeList;
	}
	public void setSubscribeList(List subscribeList) {
		this.subscribeList = subscribeList;
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
	public String getCust_name_s() {
		return cust_name_s;
	}
	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}
	public String getKeyword_s() {
		return keyword_s;
	}
	public void setKeyword_s(String keyword_s) {
		this.keyword_s = keyword_s;
	}
	public String getInfo_type_s() {
		return info_type_s;
	}
	public void setInfo_type_s(String info_type_s) {
		this.info_type_s = info_type_s;
	}
	
	/**
	 * @return the subscribeinfo
	 */
	public Subscribeinfo getSubscribeinfo() {
		return subscribeinfo;
	}
	/**
	 * @param Subscribeinfo
	 *            the subscribeinfo to set
	 */
	public void setSubscribeinfo(Subscribeinfo subscribeinfo) {
		this.subscribeinfo = subscribeinfo;
	}
	public void prepare() throws Exception { super.saveRequestParameter();
		if(subscribeinfo == null){
			subscribeinfo = new Subscribeinfo();
		}
		String id = subscribeinfo.getSend_id();
		if(!ValidateUtil.isDigital(id)){
			subscribeinfo = this.subscribeinfoService.get(id);
		}
	}
}


