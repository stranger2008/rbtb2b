/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberconfigAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Member;
import com.rbt.model.Memberconfig;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberchannelService;
import com.rbt.service.IMemberconfigService;
/**
 * @function 功能 记录会员企业站设置信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Aug 29 16:12:02 CST 2011
 */
@Controller
public class MemberconfigAction extends BaseAction  implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录会员企业站设置信息对象
	 */
	public Memberconfig memberconfig;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberconfigService memberconfigService;
	@Autowired
	public IMemberchannelService memberchannelService;
	@Autowired
	public IMemberService memberService;
	/*
	 * 记录会员企业站设置信息信息集合
	 */
	public List memberconfigList;
	/*
	 * 记录会员企业站栏目信息信息集合
	 */
	public List memberchannelList;
	/*
	 * 上传LOGO图片
	 */
	public String logo_img_s;
	public String temp_code;
	public String cust_name;
	//客服在线设置跳转
	public String point;
	
	//激活企业站跳转页面
	public static final String activate="/member_Memberconfig_add.action";
	
	/**
	 * 方法描述：返回新增记录会员企业站设置信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员企业站设置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.memberconfigService.insert(memberconfig);
		this.addActionMessage("新增会员企业站设置成功");
		this.memberconfig = null;
		return INPUT;
	}
	
	/**
	 * 方法描述：修改记录会员企业站设置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String kefuview() throws Exception {
		HttpServletResponse response= getResponse();
		if(memberconfig==null){
			response.sendRedirect(activate);
		}
		//判断id的值是否符合条件，不符合的话返回到列表
			
		Map pageMap = new HashMap();
		// 通过用户名找出用户信息
		pageMap.put("cust_id", this.session_cust_id);
		memberchannelList = this.memberchannelService.getList(pageMap);
		memberconfig = this.memberconfigService.get(this.session_cust_id);
		if(memberconfig.getWeb_name() == null || "".equals(memberconfig.getWeb_name()))
		{
		     Member member = this.memberService.get(this.session_cust_id);
		     String web_name = member.getCust_name();
		     memberconfig.setWeb_name(web_name);
		     memberconfig.setWeb_title(web_name);
		     this.memberconfigService.update(memberconfig);
		}
		return goUrl("kefuview");
	}

	/**
	 * 方法描述：修改记录会员企业站设置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证
		super.commonValidateField(memberconfig);
		if(super.ifvalidatepass){
			return view();
		}
		this.memberconfigService.update(memberconfig);		
		if(point!=null && !"".equals(point)){
			this.addActionMessage("修改在线客服设置成功");
			return kefuview();
		}
		else{
			this.addActionMessage("修改会员企业站设置成功");
			return view();
		}
		
	}
	/**
	 * 方法描述：删除记录会员企业站设置信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.memberconfig.getCust_id();
		id = id.replace(" ", "");
		this.memberconfigService.delete(id);
		this.addActionMessage("删除会员企业站设置成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		memberconfigList = this.memberconfigService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录会员企业站设置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		HttpServletResponse response= getResponse();
		if(memberconfig==null){
			response.sendRedirect(activate);
		}
		//判断id的值是否符合条件，不符合的话返回到列表
			
		Map pageMap = new HashMap();
		// 通过用户名找出用户信息
		pageMap.put("cust_id", this.session_cust_id);
		memberchannelList = this.memberchannelService.getList(pageMap);
		memberconfig = this.memberconfigService.get(this.session_cust_id);
		if(memberconfig.getWeb_name() == null || "".equals(memberconfig.getWeb_name()))
		{
		     Member member = this.memberService.get(this.session_cust_id);
		     String web_name = member.getCust_name();
		     memberconfig.setWeb_name(web_name);
		     memberconfig.setWeb_title(web_name);
		     this.memberconfigService.update(memberconfig);
		}
		return goUrl(VIEW);
	}

	
	/**
	 * @return the memberconfig
	 */
	public Memberconfig getMemberconfig() {
		return memberconfig;
	}
	/**
	 * @param Memberconfig
	 *            the memberconfig to set
	 */
	public void setMemberconfig(Memberconfig memberconfig) {
		this.memberconfig = memberconfig;
	}
	
	/**
	 * @return the MemberconfigList
	 */
	public List getMemberconfigList() {
		return memberconfigList;
	}
	/**
	 * @param memberconfigList
	 *  the MemberconfigList to set
	 */
	public void setMemberconfigList(List memberconfigList) {
		this.memberconfigList = memberconfigList;
	}
	public String getLogo_img_s() {
		return logo_img_s;
	}
	public void setLogo_img_s(String logo_img_s) {
		this.logo_img_s = logo_img_s;
	}
	public String getTemp_code() {
		return temp_code;
	}
	public void setTemp_code(String temp_code) {
		this.temp_code = temp_code;
	}
	public List getMemberchannelList() {
		return memberchannelList;
	}
	public void setMemberchannelList(List memberchannelList) {
		this.memberchannelList = memberchannelList;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(memberconfig == null){
			memberconfig = new Memberconfig();
		}
		if(!ValidateUtil.isDigital(this.session_cust_id)){
			memberconfig = this.memberconfigService.get(this.session_cust_id);
		}
	}

}

