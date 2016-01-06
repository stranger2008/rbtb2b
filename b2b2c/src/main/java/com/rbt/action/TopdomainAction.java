/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: TopdomainAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Topdomain;
import com.rbt.service.IMemberuserService;
import com.rbt.service.ITopdomainService;
/**
 * @function 功能 记录会员申请的顶级域名申请信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Aug 01 14:24:27 CST 2011
 */
@Controller
public class TopdomainAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录会员申请的顶级域名申请信息对象
	 */
	public Topdomain topdomain;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ITopdomainService topdomainService;
	@Autowired
	public IMemberuserService memberuserService;
	/*
	 * 记录会员申请的顶级域名申请信息信息集合
	 */
	public List topdomainList;
	/*
	 * 记录会员信息集合
	 */
	public List memberuserList;
	/*
	 * url地址
	 */
	public String domain_url_s;
	/*
	 * 用户名称
	 */
	public String cust_name;
	/*
	 * 搜索字段
	 */
	public String cust_name_s;
	public String starttime_s;
	public String endtime_s;
	public String enabled_s;
	public String membertype_s;
	public String domaintype_s;
	public String membert_type;
	public String today;

	/**
	 * 方法描述：返回新增记录会员申请的顶级域名申请信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		
		// 当前登录的用户是会员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			Map pageMap = new HashMap();
			pageMap.put("cust_id", this.session_cust_id);
			List topdomainList = this.topdomainService.getList(pageMap);
			if(topdomainList!=null && topdomainList.size()>0){
				Map tMap = (HashMap)topdomainList.get(0);
				String info_id = "";
				if(tMap.get("info_id")!=null){
					info_id = tMap.get("info_id").toString();
				}
				if(!info_id.equals("")){
					this.topdomain = this.topdomainService.get(info_id);
				}
			}
			//获取店铺名称
			Map userMap=new HashMap();
			userMap.put("cust_id", this.session_cust_id);
			userMap.put("user_type", "1");
			memberuserList=this.memberuserService.getList(userMap);
			if(memberuserList!=null&&memberuserList.size()>0){
				Map mapName = (HashMap)memberuserList.get(0);
				cust_name=mapName.get("user_name").toString();
			}
		}
		
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员申请的顶级域名申请信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		if(ValidateUtil.isRequired(this.topdomain.getDomain_url())){
			this.addFieldError("topdomain.domain_url", "域名不能为空");
			return add();
		}
		if(ValidateUtil.isRequired(this.topdomain.getGo_url())){
			this.addFieldError("topdomain.go_url", "域名转向地址不能为空");
			return add();
		}
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			if (existsTitle(topdomain.getDomain_url(),"","topdomain","domain_url")) {
				this.addFieldError("topdomain.domain_url", "该域名已经被申请绑定了,请换其它域名申请绑定！");
				return add();
			}
		}
		// 当前登录的用户是会员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			Map pageMap = new HashMap();
			pageMap.put("cust_id", this.session_cust_id);
			List topdomainList = this.topdomainService.getList(pageMap);
			if(topdomainList!=null && topdomainList.size()>0){
				
				if (existsTitle(topdomain.getDomain_url(),oldinfotitle,"topdomain","domain_url")) {
					this.addFieldError("topdomain.domain_url", "该域名已经被申请绑定了,请换其它域名申请绑定！");
					return add();
				}
				
				Map tMap = (HashMap)topdomainList.get(0);
				String info_id="";
				if(tMap.get("info_id")!=null){
					info_id = tMap.get("info_id").toString();
				}
				topdomain.setInfo_id(info_id);
				//修改会让域名绑定无效
				topdomain.setEnabled("0");
				this.topdomainService.update(topdomain);
				if(!info_id.equals("")){
					this.topdomain = this.topdomainService.get(info_id);
				}
				this.addActionMessage("修改域名绑定成功！请等待审核...");	
			}else{
				if (existsTitle(topdomain.getDomain_url(),"","topdomain","domain_url")) {
					this.addFieldError("topdomain.domain_url", "该域名已经被申请绑定了,请换其它域名申请绑定！");
					return add();
				}
				//此处为第一次申请
				topdomain.setCust_id(this.session_cust_id);
				//第一次申请状态为无效
				topdomain.setEnabled("0");
				this.topdomainService.insert(topdomain);
				this.addActionMessage("申请域名绑定成功！请等待审核...");
			}
		}
		else {
			Map pageMap = new HashMap();
			if(topdomain.getCust_id()!=null&&!topdomain.getCust_id().equals("")&&!topdomain.getCust_id().equals("0"))
			{
			  pageMap.put("cust_id", topdomain.getCust_id());
			  List topdomainList = this.topdomainService.getList(pageMap);
				if(topdomainList!=null && topdomainList.size()>0){
					this.addFieldError("topdomain.cust_id", "该会员已经申请了域名绑定，无需重复申请！");
					return add();
				}
			}
			else {
				topdomain.setCust_id("0");
			}
			topdomain.setEnabled("1");
			this.topdomainService.insert(topdomain);
			this.addActionMessage("申请域名绑定成功");
		}
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员申请的顶级域名申请信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		
		if(ValidateUtil.isRequired(this.topdomain.getDomain_url())){
			this.addFieldError("topdomain.domain_url", "域名不能为空");
			return view();
		}
		// 当前登录的用户是会员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			if(ValidateUtil.isRequired(this.topdomain.getGo_url())){
				this.addFieldError("topdomain.go_url", "域名转向地址不能为空");
				return view();
			}
			if (existsTitle(topdomain.getDomain_url(),oldinfotitle,"topdomain","domain_url")) {
				this.addFieldError("topdomain.domain_url", "域名已经存在不能重复!");
				return view();
			}
			if(topdomain.getCust_id()!=null&&!topdomain.getCust_id().equals("")&&!topdomain.getCust_id().equals("0")){
				if (existsTitle(topdomain.getCust_id(),membert_type,"topdomain","cust_id")) {
					this.addFieldError("topdomain.cust_id", "该会员已经申请过了,无需重复申请!");
					return view();
				}
			}
			if(topdomain.getCust_id()!=null&&!topdomain.getCust_id().equals(""))
			{
				topdomain.setCust_id("0");
			}
		}
		this.topdomainService.update(topdomain);
		this.addActionMessage("修改域名成功");
		return list();
	}
	
	/**
	 * 方法描述：修改记录会员申请顶级域名是否可用
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateenabled() throws Exception {
		this.topdomainService.updateenabled(topdomain);
		String enabled = topdomain.getEnabled();
		String tip = "";
		if (enabled.equals("1")) {
			tip = "启用成功";
		} else if (enabled.equals("0")) {
			tip = "禁用成功";
		}
		this.addActionMessage(tip);
		return list();
	}
	
	/**
	 * 方法描述：删除记录会员申请的顶级域名申请信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.topdomain.getInfo_id();
		id = id.replace(" ", "");
		this.topdomainService.delete(id);
		this.addActionMessage("删除顶级域名申请成功");
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
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		if(domain_url_s!=null && !domain_url_s.equals("")) pageMap.put("domain_url", domain_url_s);
		if(cust_name_s!=null && !cust_name_s.equals("")) pageMap.put("cust_name", cust_name_s);
		if(starttime_s!=null && !starttime_s.equals("")) pageMap.put("starttime", starttime_s);
		if(endtime_s!=null && !endtime_s.equals("")) pageMap.put("endtime", endtime_s);
		if(enabled_s!=null && !enabled_s.equals("")) pageMap.put("enabled", enabled_s);
		if(domaintype_s!=null && !domaintype_s.equals("")) pageMap.put("domain_type", domaintype_s);
		if(membertype_s!=null && !membertype_s.equals("")) {
			if(membertype_s.equals("0")){
				pageMap.put("cust_id", membertype_s);
			}
			else {
				pageMap.put("is_no_cust_id", "0");
			}
		}
		if(today!=null && !"".equals(today)){
			pageMap.put("today", today);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.topdomainService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		topdomainList = this.topdomainService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录会员申请的顶级域名申请信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(topdomain.getCust_id()!=null){
			if(accessControl(topdomain.getCust_id())){
				return list();
			}
		}
		cust_name=this.session_user_name;
		return goUrl(VIEW);
	}
	
	public void Bingdomain() throws Exception{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		String face_url=request.getParameter("drss");
		String url_value=face_url.substring(0,face_url.length()-1);
		Map pageMap = new HashMap();
		pageMap.put("resume_id", url_value);
		topdomainList = this.topdomainService.getList(pageMap);
		String redurl= "";
		if(topdomainList!=null&&topdomainList.size()>0){
			Map map_value = (HashMap)topdomainList.get(0);
			String cust_id = map_value.get("cust_id").toString();
			Map map_name=new HashMap();
			map_name.put("cust_id", cust_id);
			memberuserList=this.memberuserService.getList(map_name);
			String user_name="";
			String user_id="";
			if(memberuserList!=null&&memberuserList.size()>0){
				Map mapName = (HashMap)memberuserList.get(0);
				user_name=mapName.get("user_name").toString();
				user_id=mapName.get("user_id").toString();
			}
			getSession().setAttribute(Constants.CUST_ID, cust_id);
			getSession().setAttribute(Constants.CUST_TYPE, Constants.MEMBER_TYPE);
			getSession().setAttribute(Constants.USER_NAME, user_name);
			getSession().setAttribute(Constants.USER_ID, user_id);
		    redurl=face_url+"memberindex.action?syscode=com";
		  
		}
		PrintWriter out = response.getWriter();
	    out.write(redurl);
	}
	
	/**
	 * @return the TopdomainList
	 */
	public List getTopdomainList() {
		return topdomainList;
	}
	/**
	 * @param topdomainList
	 *  the TopdomainList to set
	 */
	public void setTopdomainList(List topdomainList) {
		this.topdomainList = topdomainList;
	}

	
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public List getMemberuserList() {
		return memberuserList;
	}
	public void setMemberuserList(List memberuserList) {
		this.memberuserList = memberuserList;
	}
	public String getCust_name_s() {
		return cust_name_s;
	}
	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
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
	public String getDomain_url_s() {
		return domain_url_s;
	}
	public void setDomain_url_s(String domain_url_s) {
		this.domain_url_s = domain_url_s;
	}
	/**
	 * @return the topdomain
	 */
	public Topdomain getTopdomain() {
		return topdomain;
	}
	/**
	 * @param Topdomain
	 *            the topdomain to set
	 */
	public void setTopdomain(Topdomain topdomain) {
		this.topdomain = topdomain;
	}
	public void prepare() throws Exception { super.saveRequestParameter();
		if(topdomain == null){
			topdomain = new Topdomain();
		}
		String id = topdomain.getInfo_id();
		if(!ValidateUtil.isDigital(id)){
			topdomain = this.topdomainService.get(id);
		}
	}
	
	

}

