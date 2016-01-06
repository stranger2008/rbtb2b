/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: SmshistoryAction.java 
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
import com.rbt.model.Member;
import com.rbt.model.Smshistory;
import com.rbt.service.IMemberService;
import com.rbt.service.ISmshistoryService;
/**
 * @function 功能 记录短信发送历史记录action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 19 16:23:30 CST 2011
 */
@Controller
public class SmshistoryAction extends BaseAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录短信发送历史记录对象
	 */
	public Smshistory smshistory;
	/*
	 * 会员对象
	 */
	public Member member;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberService memberService;
	@Autowired
	public ISmshistoryService smshistoryService;
	/*
	 * 记录短信发送历史记录信息集合
	 */
	public List smshistoryList;
	/*
	 * 搜索字段
	 */
	public String phomeattr_s;
	public String user_name_s;
	public String user_name;
	public List memberList;
	public String starttime_s;
	public String endtime_s;
	
	/**
	 * 方法描述：返回新增记录短信发送历史记录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录短信发送历史记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if( smshistory.getPhoneattr()!=null&& smshistory.getPhoneattr().equals(","))
		{
			String[] arr = this.smshistory.getPhoneattr().split(","); 
			for(int i=0;i<arr.length;i++){
				if(ValidateUtil.isMobile(arr[i].toString())){
					this.addFieldError("smshistory.phoneattr", "手机号格式出错");
				}
			}
		}
		smshistory.setUser_id((String) getSession().getAttribute(
				Constants.USER_ID));
		//字段验证
		super.commonValidateField(smshistory);
		if(super.ifvalidatepass){
			return add();
		}
		this.smshistoryService.insert(smshistory);
		this.addActionMessage("新增短信发送历史成功");
		return INPUT;
	}

	/**
	 * 方法描述：修改记录短信发送历史记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		
		if( smshistory.getPhoneattr()!=null&& smshistory.getPhoneattr().equals(","))
		{
			String[] arr = this.smshistory.getPhoneattr().split(","); 
			for(int i=0;i<arr.length;i++){
				if(ValidateUtil.isMobile(arr[i].toString())){
					this.addFieldError("smshistory.phoneattr", "手机号格式出错");
				}
			}
		}
		//字段验证
		super.commonValidateField(smshistory);
		if(super.ifvalidatepass){
			return view();
		}
		this.smshistoryService.update(smshistory);
		this.addActionMessage("修改短信发送历史成功");
		return list();
	}
	/**
	 * 方法描述：删除记录短信发送历史记录信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.smshistory.getTrade_id();
		id = id.replace(" ", "");
		this.smshistoryService.delete(id);
		this.addActionMessage("删除短信发送历史信息成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		if(phomeattr_s!=null && !phomeattr_s.equals("")) pageMap.put("phoneattr", phomeattr_s);
		if(user_name_s!=null && !user_name_s.equals("")) pageMap.put("user_name", user_name_s);
		if(starttime_s!=null && !starttime_s.equals("")) pageMap.put("starttime", starttime_s);
		if(endtime_s!=null && !endtime_s.equals("")) pageMap.put("endtime", endtime_s);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.smshistoryService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		smshistoryList = this.smshistoryService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录短信发送历史记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	
	private String id;
	
	public String view() throws Exception {
		Map map=new HashMap();
		map.put("trade_id", this.id);
		smshistoryList=this.smshistoryService.getList(map);
		Map mapName=(HashMap)smshistoryList.get(0);
		user_name=mapName.get("user_name").toString();
		return goUrl(VIEW);
	}
	
	
	
	
	public String indexuser() throws Exception{
		Map pageMap = new HashMap();
		//根据页面提交的条件找出信息总数
		if(user_name_s!=null && !user_name_s.equals("")) pageMap.put("cust_name", user_name_s);
		if(starttime_s!=null && !starttime_s.equals("")) pageMap.put("starttime", starttime_s);
		if(endtime_s!=null && !endtime_s.equals("")) pageMap.put("endtime", endtime_s);
		//分页插件
		int count=this.memberService.getCount(pageMap);
		pageMap = super.pageTool(count,pageMap);
		//获取列表
		memberList=this.memberService.getList(pageMap);
		return INPUT;
	}
	
	public String getemail() throws Exception{
		String cellphone = this.member.getContact_cellphone();
		cellphone = cellphone.replace(" ", "");
		smshistory=new Smshistory();
		smshistory.setPhoneattr(cellphone);
		return add();
	}
	
	/**
	 * @return the SmshistoryList
	 */
	public List getSmshistoryList() {
		return smshistoryList;
	}
	/**
	 * @param smshistoryList
	 *  the SmshistoryList to set
	 */
	public void setSmshistoryList(List smshistoryList) {
		this.smshistoryList = smshistoryList;
	}
	public String getPhomeattr_s() {
		return phomeattr_s;
	}
	public void setPhomeattr_s(String phomeattr_s) {
		this.phomeattr_s = phomeattr_s;
	}
	public String getUser_name_s() {
		return user_name_s;
	}
	public void setUser_name_s(String user_name_s) {
		this.user_name_s = user_name_s;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public List getMemberList() {
		return memberList;
	}
	public void setMemberList(List memberList) {
		this.memberList = memberList;
	}

	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	/**
	 * @return the smshistory
	 */
	public Smshistory getSmshistory() {
		return smshistory;
	}
	/**
	 * @param Smshistory
	 *            the smshistory to set
	 */
	public void setSmshistory(Smshistory smshistory) {
		this.smshistory = smshistory;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(smshistory == null){
			smshistory = new Smshistory();
		}
		//全局变量id
		id = smshistory.getTrade_id();
		if(!ValidateUtil.isDigital(id)){
			smshistory = this.smshistoryService.get(id);
		}
	}
    
}

