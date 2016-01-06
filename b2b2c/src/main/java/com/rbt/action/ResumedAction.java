/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: ResumeinboxAction.java 
 */
package com.rbt.action;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Member;
import com.rbt.model.Memberuser;
import com.rbt.model.Resumeinbox;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IResumeService;
import com.rbt.service.IResumeinboxService;

/**
 * @function 功能 简历收件箱表action类
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 01 09:17:11 CST 2011
 */
@Controller
public class ResumedAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 简历收件箱表对象
	 */
	private Resumeinbox resumeinbox;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IResumeinboxService resumeinboxService;
	@Autowired
	public IResumeService resumeService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMemberuserService memberuserService;
	/*
	 * 简历收件箱表信息集合
	 */
	public List resumeinboxList;
	public List resumeList;

	/*
	 * 搜索字段：职位名称
	 */
	public String title_s;
	/*
	 * 搜索字段：简历名称
	 */
	public String resume_name_s;
	/*
	 * 搜索字段：状态
	 */
	public String state_s;
	/*
	 * 搜索字段：面试邀请
	 */
	public String isinvite_s;
	/*
	 * 搜索字段：应聘时间的开始时间
	 */
	public String apply_date_s;
	/*
	 * 搜索字段：应聘时间的结束时间
	 */
	public String enddateString_s;
	public String resumeid;
	public String jobid;
	public String companyid;
	public String com_name;
	public String apply_name;

	/**
	 * 方法描述：返回新增简历收件箱表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增简历收件箱表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.resumeinboxService.insert(resumeinbox);
		this.addActionMessage("新增简历收件箱信息成功");
		this.resumeinbox = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改简历收件箱表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.resumeinboxService.update(resumeinbox);
		this.addActionMessage("修改简历收件箱信息成功");
		return list();
	}

	/**
	 * 方法描述：删除简历收件箱表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.resumeinbox.getInbox_id();
		id = id.replace(" ", "");
		this.resumeinboxService.delete(id);
		this.addActionMessage("删除简历收件箱信息成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if (title_s != null && !title_s.equals(""))
			pageMap.put("title", title_s);
		if (resume_name_s != null && !resume_name_s.equals(""))
			pageMap.put("resume_name", resume_name_s);
		if (state_s != null && !state_s.equals("") && !state_s.equals("2"))
			pageMap.put("state", state_s);
		if (isinvite_s != null && !isinvite_s.equals("") && !isinvite_s.equals("2"))
			pageMap.put("isinvite", isinvite_s);
		if (apply_date_s != null && !apply_date_s.equals(""))
			pageMap.put("apply_date", apply_date_s);
		if (enddateString_s != null && !enddateString_s.equals(""))
			pageMap.put("enddate", enddateString_s);

		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("apply_cust_id", this.session_user_id);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.resumeinboxService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		resumeinboxList = this.resumeinboxService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出简历收件箱表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(resumeinbox.getGet_cust_id()!=null){
			Member mem=this.memberService.get(resumeinbox.getGet_cust_id());
			if(mem!=null){
				com_name=mem.getCust_name();
			}			
		}
		if(resumeinbox.getApply_cust_id()!=null){
			Memberuser mem=this.memberuserService.get(resumeinbox.getApply_cust_id());
			if(mem!=null){
				apply_name=mem.getUser_name();
			}			
		}
		// 判断是否为会员账号登陆，如果是会员账号的话，点击查看简历收件箱的时候，查看的状态变为已查看
		if (this.session_cust_type != null && !this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			Map map = new HashMap();
			map.put("state", "1");
			map.put("inbox_id", resumeinbox.getInbox_id());
			this.resumeinboxService.updateResumeinboxState(map);// 更新查看状态
		}
		return goUrl(VIEW);
	}

	/**
	 * @return the ResumeinboxList
	 */
	public List getResumeinboxList() {
		return resumeinboxList;
	}

	/**
	 * @param resumeinboxList
	 *            the ResumeinboxList to set
	 */
	public void setResumeinboxList(List resumeinboxList) {
		this.resumeinboxList = resumeinboxList;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getResume_name_s() {
		return resume_name_s;
	}

	public void setResume_name_s(String resume_name_s) {
		this.resume_name_s = resume_name_s;
	}

	public String getState_s() {
		return state_s;
	}

	public void setState_s(String state_s) {
		this.state_s = state_s;
	}

	public String getIsinvite_s() {
		return isinvite_s;
	}

	public void setIsinvite_s(String isinvite_s) {
		this.isinvite_s = isinvite_s;
	}

	public String getApply_date_s() {
		return apply_date_s;
	}

	public void setApply_date_s(String apply_date_s) {
		this.apply_date_s = apply_date_s;
	}

	public String getEnddateString_s() {
		return enddateString_s;
	}

	public void setEnddateString_s(String enddateString_s) {
		this.enddateString_s = enddateString_s;
	}

	public String getResumeid() {
		return resumeid;
	}

	public void setResumeid(String resumeid) {
		this.resumeid = resumeid;
	}

	public String getJobid() {
		return jobid;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public IResumeService getResumeService() {
		return resumeService;
	}

	public List getResumeList() {
		return resumeList;
	}

	public void setResumeList(List resumeList) {
		this.resumeList = resumeList;
	}
	/**
	 * @return the resumeinbox
	 */
	public Resumeinbox getResumeinbox() {
		return resumeinbox;
	}

	/**
	 * @param Resumeinbox
	 *            the resumeinbox to set
	 */
	public void setResumeinbox(Resumeinbox resumeinbox) {
		this.resumeinbox = resumeinbox;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if (resumeinbox == null) {
			resumeinbox = new Resumeinbox();
		}
		String id = resumeinbox.getInbox_id();
		if (!ValidateUtil.isDigital(id)) {
			resumeinbox = this.resumeinboxService.get(id);
		}
	}

}
