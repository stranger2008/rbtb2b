/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberupgradeAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Levelinfo;
import com.rbt.model.Member;
import com.rbt.model.Memberupgrade;
import com.rbt.service.ILevelinfoService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberlevelService;
import com.rbt.service.IMemberupgradeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 会员升级记录信息action类
 * @author 创建人 邱景岩
 * @date 创建日期 Fri Jul 29 16:37:21 CST 2011
 */
@Controller
public class MemberupgradeAction extends BaseAction implements Preparable {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6798280485020940634L;
	/*
	 * 在线升级审核状态默认值
	 * 
	 */
	private static final String AUDIT_STATE_VALUE = "0";
	/*
	 * 审核状态标识
	 */
	public String auditstateTip = "";
	/*
	 * 当前级别名称 levelname
	 */
	public String levelname = "";
	/*
	 * 搜索字段： now_level_s:当前级别 apply_level_s:申请级别 in_date_s：申请时间 out_date_s：申请时间
	 * user_name_s：申请用户名 audit_state_s：审核状态 audit_date_s：审核时间 date_s：审核时间
	 * audit_user_s：审核用户名
	 */
	public String now_level_s;
	public String apply_level_s;
	public String in_date_s;
	public String out_date_s;
	public String user_name_s;
	public String audit_state_s;
	public String audit_date_s;
	public String audit_date_end_s;
	public String date_s;
	public String audit_user_s;
	public String grade_s;
	public String today;
	/*
	 * 会员升级记录信息对象
	 */
	public Memberupgrade memberupgrade;
	/*
	 * 会员信息对象
	 */
	public Member member;
	/*
	 * 会员级别信息对象
	 */
	public Levelinfo levelinfo;
	/*
	 * 会员升级业务层接口
	 */
	@Autowired
	public IMemberupgradeService memberupgradeService;
	/*
	 * 会员业务接口
	 */
	@Autowired
	public IMemberService memberService;
	/*
	 * 会员级别信息业务接口
	 */
	@Autowired
	public ILevelinfoService levelinfoService;
	/*
	 * 会员级别配置业务层接口
	 */
	@Autowired
	public IMemberlevelService memberlevelService;
	/*
	 * 会员升级记录信息信息集合
	 */
	public List memberupgradeList;
	/*
	 * 系统默认的会员级别信息集合
	 */
	public List memberlevelList;

	/**
	 * 方法描述：返回新增会员升级记录信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		member = this.memberService.get(this.session_cust_id);
		levelinfo = this.levelinfoService.get(this.session_cust_id);
		if (member.getMem_type().equals("0")) {
			if (levelinfo.getLevel_code().equals("3")) {
				setLevelname("企业普通会员");
			} else {
				setLevelname("企业VIP会员");
			}
		} else {
			if (levelinfo.getLevel_code().equals("1")) {
				setLevelname("个人普通会员");
			} else {
				setLevelname("个人VIP会员");
			}
		}
		setUser_name_s(this.session_user_name);
		// 根据用户名判断该会员升级申请是否存在，并且审核状态是否通过
		Map gradeMap = new HashMap();
		gradeMap.put("user_name", this.session_user_name);
		List gradelist = this.memberupgradeService.getList(gradeMap);
		if (gradelist != null && gradelist.size() > 0) {
			Map map = (Map) gradelist.get(0);
			if (map.get("audit_state") != null) {
				setAudit_state_s(map.get("audit_state").toString());
			}
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员升级记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		memberupgrade.setCust_id(this.session_cust_id);
		memberupgrade.setUser_name(this.session_user_name);
		memberupgrade.setAudit_state(AUDIT_STATE_VALUE);
		// 字段验证
		super.commonValidateField(memberupgrade);
		if (super.ifvalidatepass) {
			return add();
		}
		this.memberupgradeService.insert(memberupgrade);
		this.addActionMessage("新增会员升级申请成功");
		this.memberupgrade = null;
		return add();
	}

	/**
	 * 方法描述：修改会员升级记录信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String idString = memberupgrade.getGrade_id();
		if (ValidateUtil.isDigital(idString)) {
			return list();
		}
		memberupgrade.setCust_id(this.session_cust_id);
		memberupgrade.setUser_name(this.session_user_name);
		memberupgrade.setAudit_state(AUDIT_STATE_VALUE);
		// 字段验证
		super.commonValidateField(memberupgrade);
		if (super.ifvalidatepass) {
			return view();
		}
		this.memberupgradeService.update(memberupgrade);
		this.addActionMessage("修改会员升级申请成功");
		return view();
	}

	/**
	 * 方法描述：删除会员升级记录信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.memberupgrade.getGrade_id();
		id = id.replace(" ", "");
		this.memberupgradeService.delete(id);
		this.addActionMessage("删除会员升级申请成功");
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
		if (now_level_s != null && !now_level_s.equals("")) {
			pageMap.put("now_level", now_level_s);
		}
		if (apply_level_s != null && !apply_level_s.equals("")) {
			pageMap.put("apply_level", apply_level_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (out_date_s != null && !out_date_s.equals("")) {
			pageMap.put("out_date", out_date_s);
		}
		if (user_name_s != null && !user_name_s.equals("")) {
			pageMap.put("user_name", user_name_s);
		}
		if (audit_state_s != null && !audit_state_s.equals("")) {
			pageMap.put("audit_state", audit_state_s);
		}
		//审核时间搜索 开始时间
		if (audit_date_s != null && !audit_date_s.equals("")) {
			pageMap.put("audit_date", audit_date_s);
		}
		//审核时间搜索  结束时间
		if(audit_date_end_s != null && !audit_date_end_s.equals("")){
			pageMap.put("audit_date_end", audit_date_end_s);
		}
		if (date_s != null && !date_s.equals("")) {
			pageMap.put("date", date_s);
		}
		if (audit_user_s != null && !audit_user_s.equals("")) {
			pageMap.put("audit_user", audit_user_s);
		}
		if(today != null && !"".equals(today)){
			pageMap.put("today", today);
		}
		// 过滤地区
		pageMap = super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.memberupgradeService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		memberupgradeList = this.memberupgradeService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出会员升级记录信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(memberupgrade.getCust_id()!=null){
			if(accessControl(memberupgrade.getCust_id())){
				return list();
			}
		}
		memberlevelList = this.memberlevelService.getList(new HashMap());
		Map map = new HashMap();
		map.put("cust_id", this.session_cust_id);
		map.put("user_name", this.session_user_name);
		memberupgrade = this.memberupgradeService.getMemberupgradeByName(map);
		return goUrl(VIEW);
	}

	/**
	 * @function 功能 根据主键找出会员升级记录信息信息
	 * @author 创建人 邱景岩
	 * @date 创建日期 Aug 4, 2011 2:54:09 PM
	 */
	public String audit() throws Exception {
		setAuditstateTip(memberupgrade.getAudit_state());
		return goUrl(AUDIT);
	}

	public String auditstate() throws Exception {
		// audit_state: 1：审核通过 2：审核不通过
		if (memberupgrade.getAudit_state().equals("2")
				&& ValidateUtil.isRequired(memberupgrade.getReason())) {
			this.addFieldError("memberupgrade.reason", "请输入拒绝理由");
			return audit();
		}
		Map stateMap = new HashMap();
		if (!memberupgrade.getAudit_state().equals("2")) {
			stateMap.put("reason", "");
		} else {
			stateMap.put("reason", memberupgrade.getReason());
		}
		stateMap.put("audit_user", this.session_user_name);
		stateMap.put("grade_id", memberupgrade.getGrade_id());
		stateMap.put("audit_state", memberupgrade.getAudit_state());
		// 修改会员提交的会员升级信息
		this.memberupgradeService.auditMemberupgrade(stateMap);
		// 当审核通过时，更新级别信息表中的会员级别
		update_levelinfo();
		this.addActionMessage("已审核会员升级信息");
		return list();
	}

	/**
	 * 方法描述：更新会员级别信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void update_levelinfo() {
		// 当审核通过时，更新级别信息表中的会员级别
		if (memberupgrade != null && memberupgrade.getAudit_state().equals("1")) {
			Levelinfo levelinfo = new Levelinfo();
			levelinfo.setLevel_code(memberupgrade.getApply_level());
			levelinfo.setCust_id(memberupgrade.getCust_id());
			levelinfoService.update(levelinfo);
		}
	}

	/**
	 * @param memberupgradeService
	 *            the memberupgradeService to set
	 */
	public void setMemberupgradeService(
			IMemberupgradeService memberupgradeService) {
		this.memberupgradeService = memberupgradeService;
	}

	/**
	 * @param levelinfoService
	 *            the levelinfoService to set
	 */
	public void setLevelinfoService(ILevelinfoService levelinfoService) {
		this.levelinfoService = levelinfoService;
	}

	/**
	 * @param memberlevelService
	 *            the memberlevelService to set
	 */
	public void setMemberlevelService(IMemberlevelService memberlevelService) {
		this.memberlevelService = memberlevelService;
	}

	/**
	 * @return the memberupgrade
	 */
	public Memberupgrade getMemberupgrade() {
		return memberupgrade;
	}

	/**
	 * @param Memberupgrade
	 *            the memberupgrade to set
	 */
	public void setMemberupgrade(Memberupgrade memberupgrade) {
		this.memberupgrade = memberupgrade;
	}

	/**
	 * @return the MemberupgradeList
	 */
	public List getMemberupgradeList() {
		return memberupgradeList;
	}

	/**
	 * @param memberupgradeList
	 *            the MemberupgradeList to set
	 */
	public void setMemberupgradeList(List memberupgradeList) {
		this.memberupgradeList = memberupgradeList;
	}

	/**
	 * @return the now_level_s
	 */
	public String getNow_level_s() {
		return now_level_s;
	}

	/**
	 * @param now_level_s
	 *            the now_level_s to set
	 */
	public void setNow_level_s(String now_level_s) {
		this.now_level_s = now_level_s;
	}

	/**
	 * @return the apply_level_s
	 */
	public String getApply_level_s() {
		return apply_level_s;
	}

	/**
	 * @param apply_level_s
	 *            the apply_level_s to set
	 */
	public void setApply_level_s(String apply_level_s) {
		this.apply_level_s = apply_level_s;
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
	 * @return the out_date_s
	 */
	public String getOut_date_s() {
		return out_date_s;
	}

	/**
	 * @param out_date_s
	 *            the out_date_s to set
	 */
	public void setOut_date_s(String out_date_s) {
		this.out_date_s = out_date_s;
	}

	/**
	 * @return the user_name_s
	 */
	public String getUser_name_s() {
		return user_name_s;
	}

	/**
	 * @param user_name_s
	 *            the user_name_s to set
	 */
	public void setUser_name_s(String user_name_s) {
		this.user_name_s = user_name_s;
	}

	/**
	 * @return the audit_state_s
	 */
	public String getAudit_state_s() {
		return audit_state_s;
	}

	/**
	 * @param audit_state_s
	 *            the audit_state_s to set
	 */
	public void setAudit_state_s(String audit_state_s) {
		this.audit_state_s = audit_state_s;
	}

	/**
	 * @return the audit_date_s
	 */
	public String getAudit_date_s() {
		return audit_date_s;
	}

	/**
	 * @param audit_date_s
	 *            the audit_date_s to set
	 */
	public void setAudit_date_s(String audit_date_s) {
		this.audit_date_s = audit_date_s;
	}

	/**
	 * @return the date_s
	 */
	public String getDate_s() {
		return date_s;
	}

	/**
	 * @param date_s
	 *            the date_s to set
	 */
	public void setDate_s(String date_s) {
		this.date_s = date_s;
	}

	/**
	 * @return the audit_user_s
	 */
	public String getAudit_user_s() {
		return audit_user_s;
	}

	/**
	 * @param audit_user_s
	 *            the audit_user_s to set
	 */
	public void setAudit_user_s(String audit_user_s) {
		this.audit_user_s = audit_user_s;
	}

	/**
	 * @return the memberlevelList
	 */
	public List getMemberlevelList() {
		return memberlevelList;
	}

	/**
	 * @param memberlevelList
	 *            the memberlevelList to set
	 */
	public void setMemberlevelList(List memberlevelList) {
		this.memberlevelList = memberlevelList;
	}

	/**
	 * @return the auditstateTip
	 */
	public String getAuditstateTip() {
		return auditstateTip;
	}

	/**
	 * @param auditstateTip
	 *            the auditstateTip to set
	 */
	public void setAuditstateTip(String auditstateTip) {
		this.auditstateTip = auditstateTip;
	}

	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * @param member
	 *            the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * @return the levelname
	 */
	public String getLevelname() {
		return levelname;
	}

	/**
	 * @param levelname
	 *            the levelname to set
	 */
	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	/**
	 * @return the levelinfo
	 */
	public Levelinfo getLevelinfo() {
		return levelinfo;
	}

	/**
	 * @param levelinfo
	 *            the levelinfo to set
	 */
	public void setLevelinfo(Levelinfo levelinfo) {
		this.levelinfo = levelinfo;
	}

	public String getGrade_s() {
		return grade_s;
	}

	public void setGrade_s(String grade_s) {
		this.grade_s = grade_s;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (memberupgrade == null) {
			memberupgrade = new Memberupgrade();
		}
		String id = memberupgrade.getGrade_id();
		if (!ValidateUtil.isDigital(id)) {
			memberupgrade = this.memberupgradeService.get(id);
		}
	}

}
