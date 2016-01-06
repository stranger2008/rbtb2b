/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: InterhistoryAction.java 
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
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Fundhistory;
import com.rbt.model.Interhistory;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IInterhistoryService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
/**
 * @function 功能 记录会员积分异动历史action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 14 15:01:09 CST 2011
 */
@Controller
public class InterhistoryAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录会员积分异动历史对象
	 */
	public Interhistory interhistory;
	/*
	 * 记录会员资金对象
	 */
	public Memberfund memberfund;
	/*
	 * 记录会员资金异动历史对象
	 */
	public Fundhistory fundhistory;
	/*
	 * 记录会员积分对象
	 */
	public Memberinter memberinter;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IInterhistoryService interhistoryService;
	@Autowired
	public IMemberinterService memberinterService;
	@Autowired
	public IMemberfundService memberfundService;
	@Autowired
	public IFundhistoryService fundhistoryService;
	/*
	 * 记录会员积分异动历史信息集合
	 */
	public List interhistoryList;
	/*
	 * 搜索字段
	 */
	public String cust_name_s;
	public String user_name_s;
	public String cust_id_s;
	public String in_date_s;
	public String enddateString;
	/*
	 * 兑换金额
	 */
	public String rech_fund;
	/*
	 * 可用金额
	 */
	public String use_fund;
	/*
	 * 剩余的积分数
	 */
	public String integer_num;
	/*
	 * 积分兑换规矩
	 */
	public String rech_value;
	/*
	 * 支付密码
	 */
	public String password;
	
	public void setPlatType(){
		mall_type =Constants.MALL_TYPE_B2C;
	}
	
	
	//商城获取列表
	public String bmalllist() throws Exception{
		setPlatType();
		return list();
	}
	
	/**
	 * 方法描述：返回新增记录会员积分异动历史页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员积分异动历史
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.interhistoryService.insert(interhistory);
		this.addActionMessage("新增积分异动历史记录成功");
		this.interhistory = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员积分异动历史信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(ValidateUtil.isDigital(rech_fund)){
			this.addFieldError("rech_fund", "请输入整数");
			return view();
		}
		if("0".equals(rech_fund)){
			return view();
		}
		if(ValidateUtil.isRequired(password)){
			this.addFieldError("password", "支付密码不能为空");
			return view();
		}
		//积分兑换规则
		int rechange_value=Integer.parseInt(SysconfigFuc.getSysValue("cfg_sc_exchscale"));
		//查询用户的可用金额
		String use_num="";
		String fund_num="";
		//获取用户资金对象
		memberfund=this.memberfundService.get(this.session_cust_id);
		//获取用户积分对象
		memberinter=this.memberinterService.get(this.session_cust_id);
		
		//判断输入的密码是否正确
		String paypasswd=Md5.getMD5(password.getBytes());
		if(memberfund!=null){
			String passwd=memberfund.getPay_passwd();
			use_num=memberfund.getUse_num();
			if(Double.parseDouble(use_num)<Double.parseDouble(rech_fund)){
				this.addActionMessage("您的可用金额不足");
				return view();
			}else if(!passwd.equals(paypasswd)){
				this.addActionMessage("您的支付密码输入有误");
				return view();
			}
			else{
				//金额兑换积分
				interhistoryService.optioninter(memberfund,use_num,this.session_cust_id,this.session_user_id,memberinter,rech_fund,rechange_value);
				this.addActionMessage("成功兑换 "+Integer.parseInt(rech_fund)*rechange_value+" 积分");
				
			}
		}
		
		//是否兑换金额大于可以用金额，如果是提示可用金额不足，如何否修改相关表
		
		return view();
	}
	/**
	 * 方法描述：删除记录会员积分异动历史信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.interhistory.getTrade_id();
		id = id.replace(" ", "");
		this.interhistoryService.delete(id);
		this.addActionMessage("删除会员积分异动历史信息成功");
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
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
			pageMap.put("plat_type", mall_type);
		}
		if(cust_name_s!=null && !cust_name_s.equals("")) pageMap.put("cust_name", cust_name_s);
		if(user_name_s!=null && !user_name_s.equals("")) pageMap.put("user_name", user_name_s);
		if(cust_id_s!=null && !cust_id_s.equals("")) pageMap.put("cust_id", cust_id_s);
		if(in_date_s!=null && !in_date_s.equals("")) pageMap.put("in_date", in_date_s);
		if(enddateString!=null && !enddateString.equals("")) pageMap.put("enddate", enddateString);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.interhistoryService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		interhistoryList = this.interhistoryService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录会员积分异动历史信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//积分兑换规则
		rech_value=SysconfigFuc.getSysValue("cfg_sc_exchscale");

		//获取用户资金对象
		memberfund=this.memberfundService.get(this.session_cust_id);
		//获取用户积分对象
		memberinter=this.memberinterService.get(this.session_cust_id);
		if(memberfund!=null){
		use_fund=memberfund.getUse_num();
		}
		if(memberinter!=null){
			integer_num=memberinter.getFund_num();
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the InterhistoryList
	 */
	public List getInterhistoryList() {
		return interhistoryList;
	}
	/**
	 * @param interhistoryList
	 *  the InterhistoryList to set
	 */
	public void setInterhistoryList(List interhistoryList) {
		this.interhistoryList = interhistoryList;
	}
	public String getCust_name_s() {
		return cust_name_s;
	}
	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}
	public String getCust_id_s() {
		return cust_id_s;
	}
	public void setCust_id_s(String cust_id_s) {
		this.cust_id_s = cust_id_s;
	}
	public String getIn_date_s() {
		return in_date_s;
	}
	public void setIn_date_s(String in_date_s) {
		this.in_date_s = in_date_s;
	}
	public String getEnddateString() {
		return enddateString;
	}
	public void setEnddateString(String enddateString) {
		this.enddateString = enddateString;
	}
	
	/**
	 * @return the interhistory
	 */
	public Interhistory getInterhistory() {
		return interhistory;
	}
	/**
	 * @param Interhistory
	 *            the interhistory to set
	 */
	public void setInterhistory(Interhistory interhistory) {
		this.interhistory = interhistory;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(interhistory == null){
			interhistory = new Interhistory();
		}
		String id = interhistory.getTrade_id();
		if(!ValidateUtil.isDigital(id)){
			interhistory = this.interhistoryService.get(id);
		}
		System.out.println(interhistory);
	}

	public String getRech_fund() {
		return rech_fund;
	}

	public void setRech_fund(String rech_fund) {
		this.rech_fund = rech_fund;
	}

	public String getUse_fund() {
		return use_fund;
	}

	public void setUse_fund(String use_fund) {
		this.use_fund = use_fund;
	}

	public String getInteger_num() {
		return integer_num;
	}

	public void setInteger_num(String integer_num) {
		this.integer_num = integer_num;
	}

	public String getRech_value() {
		return rech_value;
	}

	public void setRech_value(String rech_value) {
		this.rech_value = rech_value;
	}


	public String getUser_name_s() {
		return user_name_s;
	}


	public void setUser_name_s(String user_name_s) {
		this.user_name_s = user_name_s;
	}

}

