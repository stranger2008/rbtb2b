/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: EmailtemplateAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Emailtemplate;
import com.rbt.service.IEmailtemplateService;
/**
 * @function 功能 记录会员邮件发送模板信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Jul 15 09:13:20 CST 2011
 */
@Controller
public class EmailtemplateAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录会员邮件发送模板信息对象
	 */
	public Emailtemplate emailtemplate;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IEmailtemplateService emailtemplateService;
	/*
	 * 记录会员邮件发送模板信息信息集合
	 */
	public List emailtemplateList;
	/*
	 * 记录会员邮件发送模板信息信息集合
	 */
	public String temp_name_s;
	
	
	/**
	 * 方法描述：返回新增记录会员邮件发送模板信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员邮件发送模板信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {

		//字段验证
		super.commonValidateField(emailtemplate);
		if(super.ifvalidatepass){
			return add();
		}
		this.emailtemplateService.insert(emailtemplate);
		this.addActionMessage("新增邮件发送模板成功");
		this.emailtemplate = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员邮件发送模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		super.commonValidateField(emailtemplate);
		if(super.ifvalidatepass){
			return view();
		}
		this.emailtemplateService.update(emailtemplate);
		this.addActionMessage("修改邮件发送模板成功");
		return list();
	}
	/**
	 * 方法描述：删除记录会员邮件发送模板信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.emailtemplate.getTemp_id();
		id = id.replace(" ", "");
		this.emailtemplateService.delete(id);
		this.addActionMessage("删除邮件发送模板成功");
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
		if(temp_name_s!=null && !temp_name_s.equals("")) pageMap.put("temp_name", temp_name_s);
		
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.emailtemplateService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		emailtemplateList = this.emailtemplateService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录会员邮件发送模板信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}
	/**
	 * @return the EmailtemplateList
	 */
	public List getEmailtemplateList() {
		return emailtemplateList;
	}
	/**
	 * @param emailtemplateList
	 *  the EmailtemplateList to set
	 */
	public void setEmailtemplateList(List emailtemplateList) {
		this.emailtemplateList = emailtemplateList;
	}
	public String getTemp_name_s() {
		return temp_name_s;
	}
	public void setTemp_name_s(String temp_name_s) {
		this.temp_name_s = temp_name_s;
	}
	
	/**
	 * @return the emailtemplate
	 */
	public Emailtemplate getEmailtemplate() {
		return emailtemplate;
	}
	/**
	 * @param Emailtemplate
	 *            the emailtemplate to set
	 */
	public void setEmailtemplate(Emailtemplate emailtemplate) {
		this.emailtemplate = emailtemplate;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(emailtemplate == null){
			emailtemplate = new Emailtemplate();
		}
		String id = emailtemplate.getTemp_id();
		if(!ValidateUtil.isDigital(id)){
			emailtemplate = this.emailtemplateService.get(id);
		}
		System.out.println(emailtemplate);
	}

}

