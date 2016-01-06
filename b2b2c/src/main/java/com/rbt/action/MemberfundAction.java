/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberfundAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Fundhistory;
import com.rbt.model.Member;
import com.rbt.model.Memberfund;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberfundService;

/**
 * @function 功能 会员资金action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 12 09:26:58 CST 2011
 */
@Controller
public class MemberfundAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 隐藏字段message默认值
	 */
	private static final String MESSAGE_VALUE = "1";
	/*
	 * 会员资金对象
	 */
	public Memberfund memberfund;
	/*
	 * 会员对象
	 */
	public Member member;
	/*
	 * 会员资金对象
	 */
	public Fundhistory fundhistory;

	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberfundService memberfundService;
	@Autowired
	public IFundhistoryService fundhistoryService;
	@Autowired
	public IMemberService memberService;
	/*
	 * 会员资金信息集合
	 */
	public List memberfundList;
	/*
	 * 会员资金信息
	 */
	public List smemberfundList;
	
	//用户id
	public String cust_id;
	//搜索字段
	public String memberfund_name_s;
	//选择收入支出字段
	public String radiochecked;
	//资金字段
	public String fund_num;
	//事由字段
	public String reason;
	//备注字段 
	public String remark;
	//旧密码
	public String oldpasswd;
	//新密码
	public String newpasswd;
	//确认密码
	public String confirmpasswd;
	//消息
	public String message;
	//会员资金字段
	public String cust_name;


	

	/**
	 * 方法描述：返回新增会员资金页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		if (!"".equals(this.memberfund.getCust_id())) {
			memberfund = this.memberfundService.get(this.memberfund.getCust_id());
			member=this.memberService.get(this.memberfund.getCust_id());
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员资金
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
        if(ValidateUtil.isDigital(this.fund_num)){
        	this.addFieldError("fundrecharge.fund_num", "请输入金额");
        	return add();
        }	
		this.memberfundService.insertfundoption(this.memberfund.getCust_id(), this.session_user_id, fund_num);
		this.addActionMessage("充值会员资金信息成功");
		this.memberfund = null;
		return list();
	}

	/**
	 * 方法描述：修改会员资金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {

		if (ValidateUtil.isRequired(fund_num) || ValidateUtil.isDigital(fund_num)) {
			this.addFieldError("fund_num", "不能为空且只能输入数字");
			return view();
		}
		if (ValidateUtil.isRequired(reason)) {
			this.addFieldError("reason", "事由不能为空");
			return view();
		}
		// 实例化fundhistory
		fundhistory = new Fundhistory();
		if (!"".equals(this.memberfund.getCust_id())) {
			memberfund = this.memberfundService.get(this.memberfund.getCust_id());
		}
		fundhistory.setCust_id(this.memberfund.getCust_id());
		// 判断收入支出"0"表示收入
		if (radiochecked.equals("0")) {
			fundhistory.setFund_in(fund_num);
			fundhistory.setFund_out("0");
			Double value_fund = Double.valueOf(memberfund.getFund_num()) + Double.valueOf(fund_num);
			Double value_usefund = Double.valueOf(memberfund.getUse_num()) + Double.valueOf(fund_num);
			memberfund.setUse_num(String.valueOf(value_usefund));
			memberfund.setFund_num(String.valueOf(value_fund));
			fundhistory.setBalance(String.valueOf(value_usefund));
		} else {
			fundhistory.setFund_in("0");
			fundhistory.setFund_out(fund_num);
			Double value_fund = Double.valueOf(memberfund.getFund_num()) - Double.valueOf(fund_num);
			Double value_usefund = Double.valueOf(memberfund.getUse_num()) - Double.valueOf(fund_num);
			memberfund.setUse_num(String.valueOf(value_usefund));
			memberfund.setFund_num(String.valueOf(value_fund));
			fundhistory.setBalance(String.valueOf(value_usefund));
		}
		fundhistory.setUser_id(this.session_user_id);
		fundhistory.setReason(reason);
		fundhistory.setRemark(remark);
		//字段验证
		super.commonValidateField(memberfund);
		if(super.ifvalidatepass){
			return view();
		}
		// 更新资金表
		this.memberfundService.update(memberfund);
		this.fundhistoryService.insert(fundhistory);
		this.addActionMessage("修改会员资金流信息成功");
		return list();
	}

	/**
	 * 方法描述：删除会员资金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.memberfund.getCust_id();
		id = id.replace(" ", "");
		this.memberfundService.delete(id);
		this.addActionMessage("删除会员资金成功");
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
		if (memberfund_name_s != null && !memberfund_name_s.equals(""))
			pageMap.put("cust_name", memberfund_name_s);

		//过滤地区
		pageMap=super.areafilter(pageMap); 
		// 根据页面提交的条件找出信息总数
		int count = this.memberfundService.getCount(pageMap);
 
		//分页插件
		pageMap = super.pageTool(count, pageMap);

		memberfundList = this.memberfundService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出会员资金信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(memberfund.getCust_id()!=null){
			if(accessControl(memberfund.getCust_id())){
				return list();
			}
		}
		// 当前登录的用户是管理员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			if (!"".equals(this.memberfund.getCust_id())) {
				memberfund = this.memberfundService.get(this.memberfund.getCust_id());
			}
			Map map = new HashMap();
			map.put("cust_id", this.memberfund.getCust_id());
			smemberfundList = this.memberfundService.getList(map);
			if (smemberfundList.size() > 0 && smemberfundList != null) {
				Map getmap = (HashMap) smemberfundList.get(0);
				cust_name = getmap.get("cust_name").toString();
			}
		} else {
			if (!this.session_cust_id.equals("")) {
				memberfund = this.memberfundService.get(this.session_cust_id);
			}
		}
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：支付宝接口代码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String Izhifubao() throws Exception {
		if (ValidateUtil.isRequired(this.memberfund.getFund_num())) {
			this.addFieldError("fundrecharge.fund_num", "支付金额不能为空");
			return add();
		}
		return "";
	}

	/**
	 * @function 功能 跳转到支付密码修改页
	 * @author 创建人 蔡毅存
	 * @date 创建日期 Nov 4, 2011 11:25:22 AM
	 */
	public String uppaypasswd() throws Exception {
		return INPUT;
	}

	/**
	 * @function 功能 设置支付密码
	 * @author 创建人 蔡毅存
	 * @date 创建日期 Nov 4, 2011 11:20:22 AM
	 */
	public String updatepasswd() throws Exception {
		if (ValidateUtil.isRequired(this.message)) {
			this.setMessage(MESSAGE_VALUE);
			return INPUT;
		}
		if (ValidateUtil.isRequired(this.getOldpasswd())) {
			this.addFieldError("oldpasswd", "旧密码不能为空");
			return uppaypasswd();
		}
		Map pageMap = new HashMap();
		// 查找密码
		oldpasswd = Md5.getMD5(oldpasswd.getBytes());
		pageMap.put("pay_passwd", oldpasswd);
		pageMap.put("cust_id", this.session_cust_id);
		memberfundList = this.memberfundService.getList(pageMap);
		if (memberfundList == null || memberfundList.size() == 0) {
			this.addFieldError("oldpasswd", "旧密码不正确，请重新输入");
			return uppaypasswd();
		}
		if (ValidateUtil.isRequired(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "新密码不能为空");
			return uppaypasswd();
		}
		if (ValidateUtil.isRequired(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码不能为空");
			return uppaypasswd();
		}
		if (ValidateUtil.isAlphas(this.getNewpasswd())) {
			this.addFieldError("newpasswd", "密码只能由数字、字母或者下划线组成，请重新输入");
			return uppaypasswd();
		}
		if (!this.getNewpasswd().equals(this.getConfirmpasswd())) {
			this.addFieldError("confirmpasswd", "确认密码与新密码不一致，请重新输入");
			return uppaypasswd();
		}
		memberfund = this.memberfundService.get(this.session_cust_id);
		// 对密码加密
		if (newpasswd != null && !newpasswd.equals("")) {
			newpasswd = Md5.getMD5(newpasswd.getBytes());
		} else {
			newpasswd = null;
		}
		memberfund.setPay_passwd(newpasswd);
		this.memberfundService.update(memberfund);
		this.addActionMessage("设置密码成功");
		return uppaypasswd();
	}


	/**
	 * @return the MemberfundList
	 */
	public List getMemberfundList() {
		return memberfundList;
	}

	/**
	 * @param memberfundList
	 *            the MemberfundList to set
	 */
	public void setMemberfundList(List memberfundList) {
		this.memberfundList = memberfundList;
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getMemberfund_name_s() {
		return memberfund_name_s;
	}

	public void setMemberfund_name_s(String memberfund_name_s) {
		this.memberfund_name_s = memberfund_name_s;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public Fundhistory getFundhistory() {
		return fundhistory;
	}

	public void setFundhistory(Fundhistory fundhistory) {
		this.fundhistory = fundhistory;
	}

	public String getRadiochecked() {
		return radiochecked;
	}

	public void setRadiochecked(String radiochecked) {
		this.radiochecked = radiochecked;
	}

	public String getFund_num() {
		return fund_num;
	}

	public void setFund_num(String fund_num) {
		this.fund_num = fund_num;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOldpasswd() {
		return oldpasswd;
	}

	public void setOldpasswd(String oldpasswd) {
		this.oldpasswd = oldpasswd;
	}

	public String getNewpasswd() {
		return newpasswd;
	}

	public void setNewpasswd(String newpasswd) {
		this.newpasswd = newpasswd;
	}

	public String getConfirmpasswd() {
		return confirmpasswd;
	}

	public void setConfirmpasswd(String confirmpasswd) {
		this.confirmpasswd = confirmpasswd;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	/**
	 * @return the memberfund
	 */
	public Memberfund getMemberfund() {
		return memberfund;
	}

	/**
	 * @param Memberfund
	 *            the memberfund to set
	 */
	public void setMemberfund(Memberfund memberfund) {
		this.memberfund = memberfund;
	}

	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if (memberfund == null) {
			memberfund = new Memberfund();
		}
		if (!ValidateUtil.isDigital(this.session_cust_id)) {
			memberfund = this.memberfundService.get(this.session_cust_id);
		}
	}
	
}
