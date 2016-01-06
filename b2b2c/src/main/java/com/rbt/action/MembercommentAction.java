/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MembercommentAction.java 
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
import com.rbt.model.Membercomment;
import com.rbt.service.IMembercommentService;
import com.rbt.service.IMemberuserService;

/**
 * @function 功能 记录会员信息评论信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Jul 22 14:21:40 CST 2011
 */
@Controller
public class MembercommentAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 记录会员信息评论信息对象
	 */
	public Membercomment membercomment;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMembercommentService membercommentService;
	@Autowired
	public IMemberuserService memberuserService;

	/*
	 * 记录会员信息评论信息信息集合
	 */
	public List membercommentList;
	/*
	 * 搜索字段
	 */
	public String comm_state_s;
	public String starttime_s;
	public String endtime_s;
	public String info_title_s;
	public String IP_s;
	public String cust_name_s;
	public String today;
	/*
	 * 获取url
	 */
	public String page_url;
	/*
	 * 获取info_title
	 */
	public String info_title;
	/*
	 * 获取comm_num
	 */
	public String comm_num;

	public String infoid;
	public String infotype;
	public String content;
	public String rdvalue;
	public String commtitle;
	public String comtype;
	public String infoids;
	public String urls;
	public String iflogin;
	public String commentrand;
	public List allcommentList;
	public String commid;

	/**
	 * @return the membercomment
	 */
	public Membercomment getMembercomment() {
		return membercomment;
	}

	/**
	 * @param Membercomment
	 *            the membercomment to set
	 */
	public void setMembercomment(Membercomment membercomment) {
		this.membercomment = membercomment;
	}

	/**
	 * @param membercommentService
	 *            the membercommentService to set
	 */
	public void setMembercommentService(
			IMembercommentService membercommentService) {
		this.membercommentService = membercommentService;
	}

	/**
	 * 方法描述：返回新增记录会员信息评论信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}
	/**
	 * 方法描述：新增记录会员信息评论信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {

		membercomment.setCust_id((String) getSession().getAttribute(
				Constants.CUST_ID));
		String requestIp = getRequest().getRemoteAddr();
		membercomment.setComm_num(comm_num);
		// 系统集成替换
		membercomment.setInfo_id("1");
		membercomment.setIp(requestIp);
		membercomment.setBack_con("");
		membercomment.setComm_state("0");
		membercomment.setUp_num("0");
		membercomment.setDown_num("0");
		membercomment.setInfo_id("1");
		membercomment.setInfo_url(page_url);
		//字段验证
		super.commonValidateField(membercomment);
		if(super.ifvalidatepass){
			return add();
		}
		this.membercommentService.insert(membercomment);
		this.addActionMessage("新增会员信息评论成功");
		this.membercomment = null;
		return INPUT;
	}
	/**
	 * 方法描述：修改记录会员信息评论信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {

		//字段验证
		super.commonValidateField(membercomment);
		if(super.ifvalidatepass){
			return view();
		}
		this.membercommentService.update(membercomment);
		this.addActionMessage("修改会员信息评论成功");
		return list();
	}

	/**
	 * 方法描述：删除记录会员信息评论信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.membercomment.getComm_id();
		id = id.replace(" ", "");
		this.membercommentService.delete(id);
		this.addActionMessage("删除会员信息评论成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {

		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (starttime_s != null && !starttime_s.equals(""))
			pageMap.put("starttime", starttime_s);
		if (endtime_s != null && !endtime_s.equals(""))
			pageMap.put("endtime", endtime_s);
		if (info_title_s != null && !info_title_s.equals(""))
			pageMap.put("info_title", info_title_s);
		if (IP_s != null && !IP_s.equals(""))
			pageMap.put("ip", IP_s);
		if (comm_state_s != null && !comm_state_s.equals(""))
			pageMap.put("comm_state", comm_state_s);
		if(today != null && !"".equals(today)){
			pageMap.put("today", today);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.membercommentService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		membercommentList = this.membercommentService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录会员信息评论信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(membercomment.getCust_id()!=null){
			if(accessControl(membercomment.getCust_id())){
				return list();
			}
		}
		String rbtid = this.membercomment.getComm_id();
		if (ValidateUtil.isDigital(rbtid)) {
			return list();
		}
		return goUrl(VIEW);
	}

	/**
	 * @return the MembercommentList
	 */
	public List getMembercommentList() {
		return membercommentList;
	}

	/**
	 * @param membercommentList
	 *            the MembercommentList to set
	 */
	public void setMembercommentList(List membercommentList) {
		this.membercommentList = membercommentList;
	}

	public String getComm_state_s() {
		return comm_state_s;
	}

	public void setComm_state_s(String comm_state_s) {
		this.comm_state_s = comm_state_s;
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

	public String getInfo_title_s() {
		return info_title_s;
	}

	public void setInfo_title_s(String info_title_s) {
		this.info_title_s = info_title_s;
	}

	public String getIP_s() {
		return IP_s;
	}

	public void setIP_s(String ip_s) {
		IP_s = ip_s;
	}

	public String getCust_name_s() {
		return cust_name_s;
	}

	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}

	public String getPage_url() {
		return page_url;
	}

	public void setPage_url(String page_url) {
		this.page_url = page_url;
	}

	public String getInfo_title() {
		return info_title;
	}

	public void setInfo_title(String info_title) {
		this.info_title = info_title;
	}

	public String getComm_num() {
		return comm_num;
	}

	public void setComm_num(String comm_num) {
		this.comm_num = comm_num;
	}

	public String getInfoid() {
		return infoid;
	}

	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}

	public String getInfotype() {
		return infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRdvalue() {
		return rdvalue;
	}

	public void setRdvalue(String rdvalue) {
		this.rdvalue = rdvalue;
	}

	public String getCommtitle() {
		return commtitle;
	}

	public void setCommtitle(String commtitle) {
		this.commtitle = commtitle;
	}

	public String getComtype() {
		return comtype;
	}

	public void setComtype(String comtype) {
		this.comtype = comtype;
	}

	public String getInfoids() {
		return infoids;
	}

	public void setInfoids(String infoids) {
		this.infoids = infoids;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getIflogin() {
		return iflogin;
	}

	public void setIflogin(String iflogin) {
		this.iflogin = iflogin;
	}

	public String getCommentrand() {
		return commentrand;
	}

	public void setCommentrand(String commentrand) {
		this.commentrand = commentrand;
	}

	public List getAllcommentList() {
		return allcommentList;
	}

	public void setAllcommentList(List allcommentList) {
		this.allcommentList = allcommentList;
	}

	public String getCommid() {
		return commid;
	}

	public void setCommid(String commid) {
		this.commid = commid;
	}

	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if (membercomment == null) {
			membercomment = new Membercomment();
		}
		String id = membercomment.getComm_id();
		if (!ValidateUtil.isDigital(id)) {
			membercomment = this.membercommentService.get(id);
		}
	}

}
