/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: EmailhistoryAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.MailUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Emailhistory;
import com.rbt.model.Emailtemplate;
import com.rbt.model.Member;
import com.rbt.service.IEmailhistoryService;
import com.rbt.service.IEmailtemplateService;
import com.rbt.service.IMemberService;

/**
 * @function 功能 记录邮件发送历史记录action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Jul 15 09:47:57 CST 2011
 */
@Controller
public class EmailhistoryAction extends BaseAction implements Preparable{

	private static final long serialVersionUID = 1L;
	/*
	 * 记录邮件发送历史记录对象
	 */
	public Emailhistory emailhistory;
	/*
	 * 会员对象
	 */
	public Member member;
	public JavaMailSenderImpl mailSender;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IEmailhistoryService emailhistoryService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IEmailtemplateService emailtemplateService;
	/*
	 * 记录邮件发送历史记录信息集合
	 */
	public List emailhistoryList;
	/*
	 * 邮件模板集合
	 */
	public List emailtemplateList;
	/*
	 * 会员集合
	 */
	public List memberList;
	/*
	 * 搜索字段
	 */
	public String temp_id_s;
	public String user_name_s;
	public String title_s;
	public String starttime_s;
	public String endtime_s;
	public Object lock;
	static String[] arr;


	/**
	 * 方法描述：返回新增记录邮件发送历史记录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 获取列表
		Map tempMap = new HashMap();
		emailtemplateList = this.emailtemplateService
				.getList(tempMap);
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录邮件发送历史记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {

		String[] arr1 = this.emailhistory.getGet_email().split(",");
		for (int i = 0; i < arr1.length; i++) {
			if("".equals(arr1[i])) continue;
			if (ValidateUtil.isEmail(arr1[i].toString())) {
				this.addFieldError("emailhistory.get_email", "邮件地址格式错误");
				return add();
			}
		}
		String title = this.emailhistory.getTitle();
		String content = this.emailhistory.getContent();
		String toMailAddr = this.emailhistory.getGet_email();
		MailUtil mailUtil = new MailUtil();
		String finalContent = "";
		
		//获得模板标识
		String temp_id = this.emailhistory.getTemp_id();
		if(!"0".equals(temp_id)){
			Emailtemplate emailTemp = this.emailtemplateService.get(temp_id);
			//获取模板内容
			String temp_con = emailTemp.getTemp_con();
			//{content}:手动发送邮件的正文内容
			//用正文的内容替换模板中的标签，以达到最终的邮件正文
			finalContent = temp_con.replace("{content}", content);
		}else{
			finalContent = content;
		}

		// 批量发送邮件
		if (toMailAddr.indexOf(",") > -1) {
			mailUtil.sendBatchMail(title, finalContent, toMailAddr);
		} else {
			mailUtil.sendMail(title, finalContent, toMailAddr);
		}
		
		//从邮件发送工具里找出发送的邮箱名和发件人用户名
		emailhistory.setSend_email(mailUtil.getFromMailAddr());
		emailhistory.setSend_name(mailUtil.getUser_name());
		emailhistory.setContent(finalContent);
		emailhistory.setUser_id(this.session_user_id);
		//字段验证
		super.commonValidateField(emailhistory);
		if(super.ifvalidatepass){
			return add();
		}
		this.emailhistoryService.insert(emailhistory);
		
		this.addActionMessage("邮件发送成功");
		this.emailhistory = null;
		return list();
	}

	/**
	 * 方法描述：修改记录邮件发送历史记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.emailhistoryService.update(emailhistory);
		this.addActionMessage("修改邮件发送历史成功");
		return list();
	}

	/**
	 * 方法描述：删除记录邮件发送历史记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.emailhistory.getTrade_id();
		id = id.replace(" ", "");
		this.emailhistoryService.delete(id);
		this.addActionMessage("删除邮件发送历史成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {

		// 获取列表
		Map tempMap = new HashMap();
		emailtemplateList = this.emailtemplateService
				.getList(tempMap);

		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (temp_id_s != null && !"".equals(temp_id_s))
			pageMap.put("temp_id", temp_id_s);
		if (title_s != null && !title_s.equals(""))
			pageMap.put("title", title_s);
		if (starttime_s != null && !starttime_s.equals(""))
			pageMap.put("starttime", starttime_s);
		if (endtime_s != null && !endtime_s.equals(""))
			pageMap.put("endtime", endtime_s);
		
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.emailhistoryService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		emailhistoryList = this.emailhistoryService
				.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录邮件发送历史记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		// 获取列表
		Map tempMap = new HashMap();
		emailtemplateList = this.emailtemplateService.getList(tempMap);
		return goUrl(VIEW);
	}

	public String indexuser() throws Exception {
		Map pageMap = new HashMap();
		// 根据页面提交的条件找出信息总数
		if (user_name_s != null && !user_name_s.equals("")){
			pageMap.put("cust_name", user_name_s);
		}	
		
		int count = this.memberService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 获取列表
		memberList = this.memberService.getList(pageMap);
		return INPUT;
	}

	public String getemail() throws Exception {
		String email = this.member.getEmail();
		email = email.replace(" ", "");
		emailhistory = new Emailhistory();
		emailhistory.setGet_email(email);
		return add();
	}

	/**
	 * @return the EmailhistoryList
	 */
	public List getEmailhistoryList() {
		return emailhistoryList;
	}

	/**
	 * @param emailhistoryList
	 *            the EmailhistoryList to set
	 */
	public void setEmailhistoryList(List emailhistoryList) {
		this.emailhistoryList = emailhistoryList;
	}

	public String getTemp_id_s() {
		return temp_id_s;
	}

	public void setTemp_id_s(String temp_id_s) {
		this.temp_id_s = temp_id_s;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public List getEmailtemplateList() {
		return emailtemplateList;
	}

	public void setEmailtemplateList(List emailtemplateList) {
		this.emailtemplateList = emailtemplateList;
	}

	public List getMemberList() {
		return memberList;
	}

	public void setMemberList(List memberList) {
		this.memberList = memberList;
	}

	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getUser_name_s() {
		return user_name_s;
	}

	public void setUser_name_s(String user_name_s) {
		this.user_name_s = user_name_s;
	}
	
	/**
	 * @return the emailhistory
	 */
	public Emailhistory getEmailhistory() {
		return emailhistory;
	}

	/**
	 * @param Emailhistory
	 *            the emailhistory to set
	 */
	public void setEmailhistory(Emailhistory emailhistory) {
		this.emailhistory = emailhistory;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(emailhistory == null){
			emailhistory = new Emailhistory();
		}
		String id = emailhistory.getTrade_id();
		if(!ValidateUtil.isDigital(id)){
			emailhistory = this.emailhistoryService.get(id);
		}
		System.out.println(emailhistory);
	}

}
