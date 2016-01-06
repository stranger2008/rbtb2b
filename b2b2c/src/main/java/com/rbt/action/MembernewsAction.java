/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MembernewsAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Membernews;
import com.rbt.service.IMembernewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 企业新闻信息action类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 20 13:14:34 CST 2011
 */
@Controller
public class MembernewsAction extends BaseAction implements Preparable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4558357690055047491L;
	/*
	 * 验证证书状态标识
	 */
	public String newsstateTip = "";
	/*
	 * 搜索字段 title_s:证书标题,
	 * cust_name_s:企业名称 添加时间段 in_date_s，end_date_s
	 * news_state_s：新闻状态
	 */
	public String title_s;
	public String cust_name_s;
	public String in_date_s;
	public String end_date_s;
	public String news_state_s;
	public String today;
	/*
	 * 企业新闻信息对象
	 */
	public Membernews membernews;
	/*
	 * 公司新闻业务层接口
	 */
	@Autowired
	public IMembernewsService membernewsService;
	/*
	 * 企业新闻信息信息集合
	 */
	public List membernewsList;

	/**
	 * 方法描述：返回新增企业新闻信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			controlInfoNum();
		}
		return goUrl(ADD);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 13, 2012 1:42:18 PM
	 * @Method Description : 会员发布条数的判断
	 */
	public boolean controlInfoNum(){
		//查出已发布的信息个数
		Map tmap = new HashMap();
		tmap.put("cust_id", this.session_cust_id);
		int count = this.membernewsService.getCount(tmap);
		if(controlMsgNum(count,"membernews")){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 方法描述：新增企业新闻信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 检查新闻标题是否已经存在
		if (existsTitle(membernews.getTitle(),"","membernews","title")) {
			this.addFieldError("membernews.title", "新闻标题不能重复");
		}
		membernews.setCust_id(this.session_cust_id);
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return add();
			}
		}
		//字段验证
		super.commonValidateField(membernews);
		if(super.ifvalidatepass){
			return add();
		}
		this.membernewsService.insert(membernews);
		this.addActionMessage("新增企业新闻信息成功");
		this.membernews = null;
		is_crotorl=true;
		return add();
	}

	/**
	 * 方法描述：修改企业新闻信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String membnewsidString=membernews.getCert_id();
		if(ValidateUtil .isDigital(membnewsidString)){
			return list();
		}
		// 检查该会员是否已经存在
		if (existsTitle(membernews.getTitle(),oldinfotitle,"membernews","title")) {
			this.addFieldError("membernews.title", "新闻标题不能重复");
		}
		// 当前登录的用户是会员的时候，才执行更新新闻状态
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			membernews.setNews_state("1");
		}
		//字段验证
		super.commonValidateField(membernews);
		if(super.ifvalidatepass){
			return view();
		}
		this.membernewsService.update(membernews);
		this.addActionMessage("修改企业新闻信息成功");
		return list();
	}

	/**
	 * 方法描述：删除企业新闻信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.membernews.getCert_id();
		id = id.replace(" ", "");
		this.membernewsService.delete(id);
		this.addActionMessage("删除企业新闻信息成功");
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
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		if (title_s != null && !title_s.equals("")) {
			pageMap.put("title", title_s);
		}
		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (end_date_s != null && !end_date_s.equals("")) {
			pageMap.put("end_date", end_date_s);
		}
		if (news_state_s != null && !news_state_s.equals("")) {
			pageMap.put("news_state", news_state_s);
		}
		if(today!=null && !"".equals(today)){
			pageMap.put("today", today);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.membernewsService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		membernewsList = this.membernewsService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出企业新闻信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(membernews.getCust_id()!=null){
			if(accessControl(membernews.getCust_id())){
				return list();
			}
		}
		setNewsstateTip(membernews.getNews_state());
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：根据主键找出企业新闻信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		setNewsstateTip(membernews.getNews_state());
		return goUrl(AUDIT);
	}

	/**
	 * @function 功能 审核企业新闻信息
	 * @author 创建人 邱景岩
	 * @date 创建日期 Jul 27, 2011 2:13:09 PM
	 */

	public String auditstate() throws Exception {
		String membnewsidString=membernews.getCert_id();
		if(ValidateUtil .isDigital(membnewsidString))
		{
			return list();
		}
		if (ValidateUtil.isRequired(membernews.getNews_state())) {
			this.addFieldError("membernews.news_state", "请选择审核状态");
			return audit();
		}
		if (membernews.getNews_state().equals("2") && ValidateUtil.isRequired(membernews.getNo_reason())) {
			this.addFieldError("membernews.no_reason", "请输入拒绝理由");
			setNewsstateTip("2");
			return audit();
		}
		Map stateMap = new HashMap();
		if (!membernews.getNews_state().equals("2")) {
			stateMap.put("no_reason", "");
		} else {
			stateMap.put("no_reason", membernews.getNo_reason());
		}
		stateMap.put("cert_id", membernews.getCert_id());
		stateMap.put("news_state", membernews.getNews_state());
		this.membernewsService.auditMembernews(stateMap);
		this.addActionMessage("已审核企业新闻信息");
		return list();
	}

	/**
	 * @return the MembernewsList
	 */
	public List getMembernewsList() {
		return membernewsList;
	}

	/**
	 * @param membernewsList
	 *            the MembernewsList to set
	 */
	public void setMembernewsList(List membernewsList) {
		this.membernewsList = membernewsList;
	}

	/**
	 * @return the newsstateTip
	 */
	public String getNewsstateTip() {
		return newsstateTip;
	}

	/**
	 * @param newsstateTip
	 *            the newsstateTip to set
	 */
	public void setNewsstateTip(String newsstateTip) {
		this.newsstateTip = newsstateTip;
	}

	/**
	 * @return the title_s
	 */
	public String getTitle_s() {
		return title_s;
	}

	/**
	 * @param title_s
	 *            the title_s to set
	 */
	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	/**
	 * @return the in_date_s
	 */
	public String getIn_date_s() {
		return in_date_s;
	}

	/**
	 * @param in_date_s
	 *            the in_date_s to set
	 */
	public void setIn_date_s(String in_date_s) {
		this.in_date_s = in_date_s;
	}

	/**
	 * @return the news_state_s
	 */
	public String getNews_state_s() {
		return news_state_s;
	}

	/**
	 * @param news_state_s
	 *            the news_state_s to set
	 */
	public void setNews_state_s(String news_state_s) {
		this.news_state_s = news_state_s;
	}

	/**
	 * @return the cust_name_s
	 */
	public String getCust_name_s() {
		return cust_name_s;
	}

	/**
	 * @param cust_name_s the cust_name_s to set
	 */
	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}

	/**
	 * @return the end_date_s
	 */
	public String getEnd_date_s() {
		return end_date_s;
	}

	/**
	 * @param end_date_s the end_date_s to set
	 */
	public void setEnd_date_s(String end_date_s) {
		this.end_date_s = end_date_s;
	}
	
	/**
	 * @return the membernews
	 */
	public Membernews getMembernews() {
		return membernews;
	}

	/**
	 * @param Membernews
	 *            the membernews to set
	 */
	public void setMembernews(Membernews membernews) {
		this.membernews = membernews;
	}
	public void prepare() throws Exception { super.saveRequestParameter();
	if(membernews==null)
	{
		membernews=new Membernews();
	}
	String id=this.membernews.getCert_id();
	if(!ValidateUtil.isDigital(id))
	{
		membernews=this.membernewsService.get(id);
	}
}

}
