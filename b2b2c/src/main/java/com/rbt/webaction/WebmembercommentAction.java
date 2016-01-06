/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MembercommentAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import com.rbt.model.Membercomment;
import com.rbt.service.IMembercommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录会员信息评论信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Jul 22 14:21:40 CST 2011
 */
@Controller
public class WebmembercommentAction extends WebbaseAction  {

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

	/*
	 * 记录会员信息评论信息信息集合
	 */
	public List membercommentList;
	/*
	 * 搜索字段
	 */
	public String comm_state_s;
	/*
	 * 开始时间值
	 */
	public String starttime;
	/*
	 * 结束时间值
	 */
	public String endtime;
	/*
	 * 搜索字段
	 */
	public String info_title_s;
	/*
	 * 搜索字段
	 */
	public String IP_s;
	/*
	 * 搜索字段
	 */
	public String cust_name_s;
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
	

	/*
	 * 方法描述：获取评论数字
	 */
	public Integer getinfocount(String commul) {
		Integer countInteger = 0;
		HashMap hMap = new HashMap();
		hMap.put("info_id", infoid);
		hMap.put("info_type", infotype);
		if (!"".equals(commul)) {
			hMap.put("comm_num", commul);
		}
		countInteger = membercommentService.getCount(hMap);
		return countInteger;
	}

	// 获取评论条数
	public void getCountComments() throws Exception {
		PrintWriter out = getResponse().getWriter();
		HashMap map = new HashMap();
		map.put("info_id", infoid);
		map.put("info_type", infotype);
		float pergoodcomment = 0, permicomment = 0, perbadcomment = 0;
		Integer allcount = getinfocount("");// 获取所有的记录
		Integer goodcomment = getinfocount("1");// 获取好评的记录
		Integer midcomment = getinfocount("0");// 获取中评的记录
		Integer badcomment = getinfocount("-1");// 获取差评的记录
		if (goodcomment != null && allcount!=null) {
			pergoodcomment = (((float) goodcomment) / ((float) allcount)) * 100;// 算出好评所占的比例
		}
		if (midcomment != null && allcount!=null) {
			permicomment = (((float) midcomment) / ((float) allcount)) * 100;// 算出中评所占的比例
		}
		if (badcomment != null && allcount!=null) {
			perbadcomment = (((float) badcomment) / ((float) allcount)) * 100;// 算出差评所占的比例
		}
		String coutString="";
		if(allcount!=null){
		coutString = allcount.toString() + "&#" + goodcomment.toString() + "&#"
				+ midcomment.toString() + "&#" + badcomment.toString() + "&#"
				+ pergoodcomment + "&#" + permicomment + "&#" + perbadcomment;
		}
		out.write(coutString);

	}

	public void checkLogin() throws IOException{
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = getResponse().getWriter();
		String data="0";
		if (this.session_user_id!=null&& !this.session_user_id.equals("")) {
			data="1";
		}
		out.write(data);
	}
	
	/**
	 * 方法描述：验证评论用户是否登录与验证码输入是否正确
	 * 
	 * @throws Exception
	 */
	public void checkCommentInfo() throws Exception {
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = getResponse().getWriter();
		String outputString = "0";// 1:验证通过，2.验证码错误，3.用户未登录
		String sysrand = "";
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		if (!this.session_user_id.equals("")) {
			if (sysrand.equals(commentrand)) {
				outputString = "1";// 验证通过
			} else {
				outputString = "2";// 验证码错误
			}
		}
		out.write(outputString);
	}

	/**
	 * 方法描述：AJAx支持评论
	 * 
	 * @throws Exception
	 */
	public void addSupport() throws Exception {
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = getResponse().getWriter();
		String outputString = "1";
		try {
			this.membercommentService.updateUpNum(commid);
		} catch (Exception e) {
			outputString = "2";
		}
		out.write(outputString);
	}

	/**
	 * 方法描述：AJAX反对评论
	 * 
	 * @throws Exception
	 */
	public void addOpposition() throws Exception {
		getRequest().setCharacterEncoding("UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = getResponse().getWriter();
		String outputString = "1";
		try {
			this.membercommentService.updateDownNum(commid);
		} catch (Exception e) {
			outputString = "2";
		}
		out.write(outputString);
	}

	/**
	 * AJAX添加评论
	 * 
	 * @throws Exception
	 */
	public String addcomments() throws Exception {
		if(membercomment==null)
		{
			membercomment=new Membercomment();
		}
		if(infoids!=null&&!infoids.equals(""))
		{
			membercomment.setComm_state("0");
			membercomment.setBack_con("");
			membercomment.setContent(content);
			membercomment.setCust_id(this.session_cust_id);
			membercomment.setDown_num("0");
			membercomment.setInfo_id(infoids);
			//当信息标题太长的时候，截取前95个字符
			if(commtitle!=null&&!"".equals(commtitle)&&commtitle.length()>100)
			{
				commtitle=commtitle.substring(0,95);
			}
			membercomment.setUser_name(this.session_user_name);
			membercomment.setInfo_title(commtitle);
			membercomment.setInfo_type(infotype);
			membercomment.setInfo_url(urls);
			membercomment.setUp_num("0");
			membercomment.setComm_num(comm_num);
			String requestIp = getRequest().getRemoteAddr();
			membercomment.setIp(requestIp);
			String sysrand = "";
			// 获取系统生成的验证码
			if (getSession().getAttribute("sysrand") != null) {
				sysrand = getSession().getAttribute("sysrand").toString();
			}
			if (sysrand.equals(commentrand)) {
				if (!this.session_cust_id.equals("")) {
					this.membercommentService.insert(membercomment);
				}
			}		
		}
		return allCommentInfo();
	}

	/**
	 * 方法描述：绑定评论信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String allCommentInfo() throws Exception {
		if(!validateFactory.isDigital(infoids))
		{
		 Map pageMap=new HashMap();
		 pageMap.put("info_id", infoids);
		 pageMap.put("info_type", infotype);
		//根据页面提交的条件找出信息总数
		int count = this.membercommentService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		 allcommentList = this.membercommentService.getList(pageMap);
		}
		return goUrl("comments");
	}
	
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

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
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

}
