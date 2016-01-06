/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberleaveAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Memberleave;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberleaveService;
/**
 * @function 功能 记录会员留言信息表action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Jul 25 08:40:47 CST 2011
 */
@Controller
public class MemberleaveAction extends BaseAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录会员留言信息表对象
	 */
	public Memberleave memberleave;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberleaveService memberleaveService;
	@Autowired
	public IMemberService memberService;
	/*
	 * 记录会员留言信息表信息集合
	 */
	public List memberleaveList;
	/*
	 * 记录会员信息集合
	 */
	public List memberList;
	/*
	 * 搜索字段
	 */
	public String is_del_s;
	public String cust_name_s;
	public String send_user_name_s;
	public String get_user_name_s;
	public String starttime_s;
	public String endtime_s;
	public String today;
	/*
	 * 接收人
	 */
	public String cust_name;
	/*
	 * model发送人
	 */
	public String send_cust_name;
	/*
	 * model接收人
	 */
	public String get_cust_name;
	/**
	 * 方法描述：返回新增记录会员留言信息表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员留言信息表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		if(ValidateUtil.isRequired(this.memberleave.getTitle())){
			this.addFieldError("memberleave.title", "标题不能为空");
			return add();
		}
		
		if(ValidateUtil.isRequired(cust_name)){
			this.addFieldError("cust_name", "接收人不能为空");
			return add();
		}
		
		if(ValidateUtil.isRequired(this.memberleave.getContent())){
			this.addFieldError("memberleave.content", "留言内容不能为空");
			return add();
		}
		memberleave.setSend_cust_id((String) getSession().getAttribute(
				Constants.CUST_ID));
		memberleave.setSend_user_name((String) getSession().getAttribute(
				Constants.USER_NAME));
		
		Map map=new HashMap();
		map.put("cust_name", cust_name);
		List userList = this.memberService.getList(map);
		if(userList==null || userList.size()==0){
			this.addFieldError("cust_name", "会员不存在，请重新输入");
			return INPUT;
		}else{
			Map map_value=(HashMap)userList.get(0);
			if(map_value.get("cust_id")!=null){
				String cust_id=map_value.get("cust_id").toString();
				memberleave.setGet_cust_id(cust_id);
			}
		}
		memberleave.setIs_del("0");
		this.memberleaveService.insert(memberleave);
		this.addActionMessage("新增会员留言信息成功");
		this.memberleave = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员留言信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String leval_idString=memberleave.getLeave_id();
		if(ValidateUtil.isDigital(leval_idString))
		{
		 return list();
		}
		this.memberleaveService.update(memberleave);
		this.addActionMessage("修改会员留言成功");
		return list();
	}
	/**
	 * 方法描述：删除记录会员留言信息表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.memberleave.getLeave_id();
		id = id.replace(" ", "");
		this.memberleaveService.delete(id);
		this.addActionMessage("删除会员留言成功");
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
		if(is_del_s!=null && !is_del_s.equals("")) pageMap.put("is_del", is_del_s);
		if(cust_name_s!=null && !cust_name_s.equals("")) pageMap.put("cust_name", cust_name_s);
		if(send_user_name_s!=null && !send_user_name_s.equals("")) pageMap.put("send_user_name", send_user_name_s);
		if(starttime_s!=null && !starttime_s.equals("")) pageMap.put("starttime", starttime_s);
		if(endtime_s!=null && !endtime_s.equals("")) pageMap.put("endtime", endtime_s);
		if(today !=null && !"".equals(today)){
			pageMap.put("today", today);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.memberleaveService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		memberleaveList = this.memberleaveService.getList(pageMap);
		if(memberleaveList!=null&&memberleaveList.size()>0)
		{
	    Map map=new HashMap();
		for(int i=0;i<memberleaveList.size();i++){
			map=(HashMap)memberleaveList.get(i);
			String get_name = "";
			if (map.get("get_cust_id") != null){
				get_name = map.get("get_cust_id").toString();
				Map map_value=new HashMap();
				map_value.put("cust_id", get_name);
				memberList=this.memberService.getList(map_value);
				Map map_name=new HashMap();
				if(memberList!=null&&memberList.size()>0){
				map_name=(HashMap)memberList.get(0);
				}
				map.put("get_cust_id", map_name.get("cust_name"));
			}
				
		}
		}
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录会员留言信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String send_cust_id=memberleave.getSend_cust_id();
		if(send_cust_id!=null&&!send_cust_id.equals("0")){
			Map mapid=new HashMap();
			mapid.put("cust_id", send_cust_id);
			List sendcustList=memberService.getList(mapid);
			if(sendcustList!=null&&sendcustList.size()>0)
			{
				Map mapvalue=(HashMap)sendcustList.get(0);
				if(mapvalue.get("cust_name")!=null)
				{
					send_cust_name=mapvalue.get("cust_name").toString();
				}
			}
		}
		String get_cust_id=memberleave.getGet_cust_id();
		if(get_cust_id!=null&&!get_cust_id.equals("0")){
		Map mapid=new HashMap();
		mapid.put("cust_id", get_cust_id);
		List sendcustList=memberService.getList(mapid);
		if(sendcustList!=null&&sendcustList.size()>0)
		{
		  Map mapvalue=(HashMap)sendcustList.get(0);
		  if(mapvalue.get("cust_name")!=null)
		  {
			  get_cust_name=mapvalue.get("cust_name").toString();
		  }
		}
		
		}
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：批量修改
	 * @return
	 * @throws Exception
	 */
	public String updateisdel() throws Exception {
		String leaveid = this.memberleave.getLeave_id();
		if(leaveid!=null){
			String isdel = this.memberleave.getIs_del();
			leaveid = leaveid.replace(" ", "");
			String leaveidStr[] = leaveid.split(",");
			List leaveList = new ArrayList();
			if(leaveList!=null&&leaveidStr.length>0){
				for(int i=0;i<leaveidStr.length;i++){
					HashMap leaveMap = new HashMap();
					leaveMap.put("is_del", isdel);
					leaveMap.put("leave_id", leaveidStr[i]);
					leaveList.add(leaveMap);
				}
			}
			this.memberleaveService.updateisdel(leaveList);
			String tip = "";
			if(isdel!=null&&isdel.equals("0")){
				tip = "修改有效成功";
			}else if(isdel!=null&&isdel.equals("1")){
				tip = "放入回收站成功";
			}
			this.addActionMessage(tip);
		}
		return list();
	}
	
	/**
	 * @return the MemberleaveList
	 */
	public List getMemberleaveList() {
		return memberleaveList;
	}
	/**
	 * @param memberleaveList
	 *  the MemberleaveList to set
	 */
	public void setMemberleaveList(List memberleaveList) {
		this.memberleaveList = memberleaveList;
	}
	public String getIs_del_s() {
		return is_del_s;
	}
	public void setIs_del_s(String is_del_s) {
		this.is_del_s = is_del_s;
	}
	public String getCust_name_s() {
		return cust_name_s;
	}
	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}
	public String getSend_user_name_s() {
		return send_user_name_s;
	}
	public void setSend_user_name_s(String send_user_name_s) {
		this.send_user_name_s = send_user_name_s;
	}
	public String getGet_user_name_s() {
		return get_user_name_s;
	}
	public void setGet_user_name_s(String get_user_name_s) {
		this.get_user_name_s = get_user_name_s;
	}
	public List getMemberList() {
		return memberList;
	}
	public void setMemberList(List memberList) {
		this.memberList = memberList;
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
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getSend_cust_name() {
		return send_cust_name;
	}
	public void setSend_cust_name(String send_cust_name) {
		this.send_cust_name = send_cust_name;
	}
	public String getGet_cust_name() {
		return get_cust_name;
	}
	public void setGet_cust_name(String get_cust_name) {
		this.get_cust_name = get_cust_name;
	}
	
	/**
	 * @return the memberleave
	 */
	public Memberleave getMemberleave() {
		return memberleave;
	}
	/**
	 * @param Memberleave
	 *            the memberleave to set
	 */
	public void setMemberleave(Memberleave memberleave) {
		this.memberleave = memberleave;
	}
	public void prepare() throws Exception { super.saveRequestParameter();
		if(memberleave==null)
		{
			memberleave=new Memberleave();
		}
		String id=this.memberleave.getLeave_id();
		if(!ValidateUtil.isDigital(id))
		{
			memberleave=this.memberleaveService.get(id);
		}
	}


}

