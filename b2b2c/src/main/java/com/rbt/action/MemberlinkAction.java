/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberlinkAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Memberlink;
import com.rbt.service.IMemberlinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 企业友情链接信息action类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 20 13:16:25 CST 2011
 */
@Controller
public class MemberlinkAction extends BaseAction implements Preparable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 8753935563996105036L;
	/*
	 * 验证证书状态标识
	 */
	public String linkstateTip = "";
	/*
	 * 搜索字段 title_s:链接名称,
	 * cust_name_s:企业名称
	 * link_url_s:链接地址,link_state_s：链接状态
	 * 添加时间段：in_date_s，end_date_s
	 */
	public String title_s;
	public String cust_name_s;
	public String link_url_s;
	public String link_state_s;
	public String in_date_s;
	public String end_date_s;
	public String today;
	/*
	 * 企业友情链接信息对象
	 */
	public Memberlink memberlink;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberlinkService memberlinkService;
	/*
	 * 企业友情链接信息信息集合
	 */
	public List memberlinkList;

	public void setPlatType(){
		mall_type =Constants.MALL_TYPE_B2C;
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
		tmap.put("plat_type", mall_type);
		int count = this.memberlinkService.getCount(tmap);
		if(controlMsgNum(count,"memberlink")){
			return true;
		}else{
			return false;
		}
	}

	
	
	/**
	 * 方法描述：返回新增企业友情链接信息页面
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
	//商城获取列表
	public String bmalllist() throws Exception{
		setPlatType();
		return list();
	}
	//商城添加信息
    public String bmallinsert()throws Exception
    {
    	setPlatType();
    	return insert();
    }
    //商城查看信息
    public String  bmallupdate()throws Exception
    {
    	setPlatType();
    	return update();
    }
    //商城删除信息
    public String  bmalldelete() throws Exception
    {
    	setPlatType();
    	return delete();
    }

    
	/**
	 * 方法描述：新增企业友情链接信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		memberlink.setCust_id(this.session_cust_id);
		//字段验证
		memberlink.setPlat_type(mall_type);
		super.commonValidateField(memberlink);
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return add();
			}
		}
		if(super.ifvalidatepass){
			return add();
		}
		this.memberlinkService.insert(memberlink);
		this.addActionMessage("新增企业友情链接成功");
		this.memberlink = null;
		is_crotorl=true;
		return add();
	}

	/**
	 * 方法描述：修改企业友情链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String cert_id = memberlink.getCert_id();
		if(ValidateUtil.isDigital(cert_id)){
			return list();
		}
		// 当前登录的用户是管理员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			memberlink.setLink_state("1");
		}
		//字段验证
		super.commonValidateField(memberlink);
		if(super.ifvalidatepass){
			return view();
		}
		this.memberlinkService.update(memberlink);
		this.addActionMessage("修改企业友情链接成功");
		return list();
	}

	/**
	 * 方法描述：删除企业友情链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.memberlink.getCert_id();
		id = id.replace(" ", "");
		this.memberlinkService.delete(id);
		this.addActionMessage("删除企业友情链接成功");
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
			pageMap.put("plat_type", mall_type);
		}
			
		if (title_s != null && !title_s.equals("")) {
			pageMap.put("title", title_s);
		}
		
		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		if (link_url_s != null && !link_url_s.equals("")) {
			pageMap.put("link_url", link_url_s);
		}
		if (link_state_s != null && !link_state_s.equals("")) {
			pageMap.put("link_state", link_state_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (end_date_s != null && !end_date_s.equals("")) {
			pageMap.put("end_date", end_date_s);
		}
		if(today!=null && !"".equals(today)){
			pageMap.put("today", today);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.memberlinkService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		memberlinkList = this.memberlinkService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出企业友情链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(memberlink.getCust_id()!=null){
			if(accessControl(memberlink.getCust_id())){
				return list();
			}
		}
		setLinkstateTip(memberlink.getLink_state());
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：根据主键找出审核企业友情链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		setLinkstateTip(memberlink.getLink_state());
		return goUrl(AUDIT);
	}

	/**
	 * @function 功能 审核企业友情链接
	 * @author 创建人 邱景岩
	 * @date 创建日期 Jul 27, 2011 3:34:03 PM
	 */

	public String auditstate() throws Exception {
		String cert_id = memberlink.getCert_id();
		if(ValidateUtil.isDigital(cert_id)){
			return list();
		}
		if (ValidateUtil.isRequired(memberlink.getLink_state())) {
			this.addFieldError("memberlink.link_state", "请选择审核状态");
			return audit();
		}
		if (memberlink.getLink_state().equals("2") && ValidateUtil.isRequired(memberlink.getNo_reason())) {
			this.addFieldError("memberlink.no_reason", "请输入拒绝理由");
			setLinkstateTip("2");
			return audit();
		}
		Map stateMap = new HashMap();
		if (memberlink.getLink_state()!=null&&!memberlink.getLink_state().equals("2")) {
			stateMap.put("no_reason", "");
		} else {
			stateMap.put("no_reason", memberlink.getNo_reason());
		}
		stateMap.put("cert_id", memberlink.getCert_id());
		stateMap.put("link_state", memberlink.getLink_state());
		this.memberlinkService.auditMemberlink(stateMap);
		this.addActionMessage("已审核企业友情链接");
		return list();
	}

	/**
	 * @return the MemberlinkList
	 */
	public List getMemberlinkList() {
		return memberlinkList;
	}

	/**
	 * @param memberlinkList
	 *            the MemberlinkList to set
	 */
	public void setMemberlinkList(List memberlinkList) {
		this.memberlinkList = memberlinkList;
	}

	/**
	 * @return the linkstateTip
	 */
	public String getLinkstateTip() {
		return linkstateTip;
	}

	/**
	 * @param linkstateTip
	 *            the linkstateTip to set
	 */
	public void setLinkstateTip(String linkstateTip) {
		this.linkstateTip = linkstateTip;
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
	 * @return the link_url_s
	 */
	public String getLink_url_s() {
		return link_url_s;
	}

	/**
	 * @param link_url_s
	 *            the link_url_s to set
	 */
	public void setLink_url_s(String link_url_s) {
		this.link_url_s = link_url_s;
	}

	/**
	 * @return the link_state_s
	 */
	public String getLink_state_s() {
		return link_state_s;
	}

	/**
	 * @param link_state_s
	 *            the link_state_s to set
	 */
	public void setLink_state_s(String link_state_s) {
		this.link_state_s = link_state_s;
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
	 * @return the in_date_s
	 */
	public String getIn_date_s() {
		return in_date_s;
	}

	/**
	 * @param in_date_s the in_date_s to set
	 */
	public void setIn_date_s(String in_date_s) {
		this.in_date_s = in_date_s;
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
	 * @return the memberlink
	 */
	public Memberlink getMemberlink() {
		return memberlink;
	}

	/**
	 * @param Memberlink
	 *            the memberlink to set
	 */
	public void setMemberlink(Memberlink memberlink) {
		this.memberlink = memberlink;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if(memberlink==null)
		{
			memberlink=new Memberlink();
		}
		String id=this.memberlink.getCert_id();
		if(!ValidateUtil.isDigital(id))
		{
			memberlink=this.memberlinkService.get(id);
		}
	}

}
