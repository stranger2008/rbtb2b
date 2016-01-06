/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberreportAction.java 
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
import com.rbt.function.CategoryFuc;
import com.rbt.function.MemberuserFuc;
import com.rbt.model.Memberreport;
import com.rbt.model.Memberuser;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberreportService;

/**
 * @function 功能 会员举报信息表action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Nov 30 14:19:40 CST 2011
 */
@Controller
public class MemberreportAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 会员举报信息表对象
	 */
	public Memberreport memberreport;
	/*
	 * 会员举报信息表对象
	 */
	public Memberuser memberuser;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberreportService memberreportService;
	@Autowired
	public ICommparaService commparaService;
	//参数列表
	public List CommparaList;
    //图片串
	public String img_path;
	/*
	 * 搜索字段
	 */
	public String info_state_s;
	public String starttime_s;
	public String endtime_s;
	//举报类型
	public String report_type_s;
	//举报人
	public String user_name_s;
	//参数类型
	public static final String report_type="report_type";
	
	/*
	 * 会员举报信息表信息集合
	 */
	public List memberreportList;

	
	
	/**
	 * 方法描述：返回新增会员举报信息表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//绑定举报类型下拉类别
		getcommpara();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员举报信息表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		if("0".equals(this.memberreport.getReport_type())){
			this.addFieldError("memberreport.report_type", "举报类型不能为空");
		}
		memberreport.setCust_id(this.session_cust_id);
		memberreport.setUser_id(this.session_user_id);
		//插入初始状态为等待处理状态
		memberreport.setInfo_state("0");
		//字段验证
		super.commonValidateField(memberreport);
		if(super.ifvalidatepass){
			return add();
		}
		this.memberreportService.insert(memberreport);
		this.addActionMessage("新增举报信息成功");
		this.memberreport = null;
		return list();
	}

	/**
	 * 方法描述：修改会员举报信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if("0".equals(this.memberreport.getReport_type())){
				this.addFieldError("memberreport.report_type", "举报类型不能为空");
			}
			//字段验证
			super.commonValidateField(memberreport);
			if(super.ifvalidatepass){
				return view();
			}
		}
		this.memberreportService.update(memberreport);
		this.addActionMessage("修改举报信息成功");
		return list();
	}
	/**
	 * 方法描述：删除会员举报信息表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.memberreport.getReport_id();
		id = id.replace(" ", "");
		this.memberreportService.delete(id);
		this.addActionMessage("删除举报信息成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		//绑定举报类型下拉类别
		getcommpara();
		Map pageMap = new HashMap();
		if (info_state_s != null && !info_state_s.equals("")) {
			pageMap.put("info_state", info_state_s);
		}
		if (starttime_s != null && !starttime_s.equals("")) {
			pageMap.put("starttime", starttime_s);
		}
		if (endtime_s != null && !endtime_s.equals("")) {
			pageMap.put("endtime", endtime_s);
		}
		if (report_type_s != null && !report_type_s.equals("")) {
			pageMap.put("report_type", report_type_s);
		}
		if (user_name_s != null && !user_name_s.equals("")) {
			pageMap.put("user_name", user_name_s);
		}
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.memberreportService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		memberreportList = this.memberreportService.getList(pageMap);
		memberreportList = CategoryFuc.replaceList(memberreportList, "report_type");
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出会员举报信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(memberreport.getCust_id()!=null){
			if(accessControl(memberreport.getCust_id())){
				return list();
			}
		}
		String id = this.memberreport.getReport_id();
		if(id==null || id.equals("")){
			memberreport = new Memberreport();
		}else{
			memberreport = this.memberreportService.get(id);
			if(memberreport==null){
				return list();
			}
		}
		//绑定举报类型下拉类别
		getcommpara();
		StringBuilder sb=new StringBuilder();
		img_path="";
		if (!this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			memberuser=MemberuserFuc.getuserName(memberreport.getCust_id());
			if(memberreport!=null&&!"".equals(memberreport.getImg_path())){
				String img_url=memberreport.getImg_path();
				String url[]=img_url.split(",");
				for(int i=0;i<url.length;i++){
					sb.append("<li style='float:left'><img src='"+ url[i] +"' width='90' height='100' border='0'/></li>&nbsp;");
				}
			}
			if(sb!=null){
			img_path=sb.toString();
			}
		}
		
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：审核图片
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		String id = this.memberreport.getReport_id();
		if(id==null || id.equals("")){
			memberreport = new Memberreport();
		}else{
			memberreport = this.memberreportService.get(id);
		}
		//绑定举报类型下拉类别
		getcommpara();
		StringBuilder sb=new StringBuilder();
			memberuser=MemberuserFuc.getuserName(memberreport.getCust_id());
			if(memberreport!=null){
			String img_url=memberreport.getImg_path();
			String url[]=img_url.split(",");
			for(int i=0;i<url.length;i++){
				sb.append("<li style='float:left'><img src='"+ url[i] +"' width='90' height='100' border='0'/></li>&nbsp;");
			}
			}
			if(sb!=null){
			img_path=sb.toString();
			}

		return goUrl(AUDIT);
	}
	
	//绑定举报类型下拉类别
	public void getcommpara(){
		Map map=new HashMap();
		map.put("para_code", report_type);
		CommparaList=this.commparaService.getList(map);
	}
	
	/**
	 * @return the memberreport
	 */
	public Memberreport getMemberreport() {
		return memberreport;
	}
	/**
	 * @param Memberreport
	 *            the memberreport to set
	 */
	public void setMemberreport(Memberreport memberreport) {
		this.memberreport = memberreport;
	}
	
	/**
	 * @return the MemberreportList
	 */
	public List getMemberreportList() {
		return memberreportList;
	}
	/**
	 * @param memberreportList
	 *  the MemberreportList to set
	 */
	public void setMemberreportList(List memberreportList) {
		this.memberreportList = memberreportList;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(memberreport == null){
			memberreport = new Memberreport();
		}
		String id = this.memberreport.getReport_id();
		if(!ValidateUtil.isDigital(id)){
			memberreport = this.memberreportService.get(id);
		}
	}
	public Memberuser getMemberuser() {
		return memberuser;
	}
	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String getInfo_state_s() {
		return info_state_s;
	}
	public void setInfo_state_s(String info_state_s) {
		this.info_state_s = info_state_s;
	}
	public String getReport_type_s() {
		return report_type_s;
	}
	public void setReport_type_s(String report_type_s) {
		this.report_type_s = report_type_s;
	}
	public String getUser_name_s() {
		return user_name_s;
	}
	public void setUser_name_s(String user_name_s) {
		this.user_name_s = user_name_s;
	}
	
}

