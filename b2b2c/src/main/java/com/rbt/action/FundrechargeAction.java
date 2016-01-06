/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: FundrechargeAction.java 
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
import com.rbt.model.Fundrecharge;
import com.rbt.model.Payment;
import com.rbt.service.IFundrechargeService;
import com.rbt.service.IPaymentService;
/**
 * @function 功能 会员资金充值记录action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 12 13:10:48 CST 2011
 */
@Controller
public class FundrechargeAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 会员资金充值记录对象
	 */
	public Fundrecharge fundrecharge;
	/*
	 * 支付平台对象
	 */
	public Payment payment;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IFundrechargeService fundrechargeService;
	@Autowired
	public IPaymentService paymentService;
	/*
	 * 会员资金充值记录信息集合
	 */
	public List fundrechargeList;
	/*
	 *支付平台信息集合
	 */
	public List paymentList;
	/*
	 * 搜索字段
	 */
	public String order_state_s;
	public String cuts_name_s;
	public String payplat_s;
	public String starttime_s;
	public String endtime_s;
	/*
	 * 客户名字
	 */
	public String cust_name;
	
	/**
	 * 方法描述：返回新增会员资金充值记录页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		HashMap map=new HashMap();
		map.put("enable", "0");
		paymentList=this.paymentService.getList(map);
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员资金充值记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		if("0".equals(fundrecharge.getPayplat())){
			this.addFieldError("fundrecharge.payplat", "请选择支付平台");
		}
		String payvalue=fundrecharge.getFund_num();
		//获取支付平台对象
		String payplat = fundrecharge.getPayplat();
		//字段验证
		super.commonValidateField(fundrecharge);
		if(super.ifvalidatepass){
			return add();
		}
		//跳转到相关平台支付  支付后返回触发一个方法对fundrecharge进行插入一条未审核的数据 ，等待管理员审核后才生效
		getResponse().sendRedirect("/include/components/payment/"+payplat+"/index.jsp?cust_id="+ this.session_cust_id +"&payvalue="+ payvalue+"");
		
		return add();
	}

	/**
	 * 方法描述：修改会员资金充值记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		if(fundrecharge.getTrade_id()!=null&&!"".equals(fundrecharge.getTrade_id())){
		this.fundrechargeService.update(fundrecharge);
		this.addActionMessage("修改资金充值记录成功");
		}
		return list();
	}
	/**
	 * 方法描述：删除会员资金充值记录信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.fundrecharge.getTrade_id();
		id = id.replace(" ", "");
		this.fundrechargeService.delete(id);
		this.addActionMessage("删除资金充值记录成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	
	public String list() throws Exception {
		
		//绑定下拉列表
		HashMap map=new HashMap();
		map.put("enable", "0");
		paymentList=this.paymentService.getList(map);
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		if(order_state_s!=null && !order_state_s.equals("")) pageMap.put("order_state", order_state_s);
		if(cuts_name_s!=null && !cuts_name_s.equals("")) pageMap.put("cust_name", cuts_name_s);
		if(payplat_s!=null && !payplat_s.equals("")&&!payplat_s.equals("0")) pageMap.put("payplat", payplat_s);
		if(starttime_s!=null && !starttime_s.equals("")&&!starttime_s.equals("0")) pageMap.put("starttime", starttime_s);
		if(endtime_s!=null && !endtime_s.equals("")&&!endtime_s.equals("0")) pageMap.put("endtime", endtime_s);
		
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.fundrechargeService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		fundrechargeList = this.fundrechargeService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出会员资金充值记录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		Map map=new HashMap();
		map.put("cust_id", fundrecharge.getCust_id());
		fundrechargeList=this.fundrechargeService.getList(map);
		Map mapvalue=(HashMap)fundrechargeList.get(0);
		if(mapvalue.get("cust_name")!=null){
		cust_name=mapvalue.get("cust_name").toString();
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the FundrechargeList
	 */
	public List getFundrechargeList() {
		return fundrechargeList;
	}
	/**
	 * @param fundrechargeList
	 *  the FundrechargeList to set
	 */
	public void setFundrechargeList(List fundrechargeList) {
		this.fundrechargeList = fundrechargeList;
	}
	public String getOrder_state_s() {
		return order_state_s;
	}
	public void setOrder_state_s(String order_state_s) {
		this.order_state_s = order_state_s;
	}
	public String getCuts_name_s() {
		return cuts_name_s;
	}
	public void setCuts_name_s(String cuts_name_s) {
		this.cuts_name_s = cuts_name_s;
	}
	public String getPayplat_s() {
		return payplat_s;
	}
	public void setPayplat_s(String payplat_s) {
		this.payplat_s = payplat_s;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
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

	/**
	 * @return the fundrecharge
	 */
	public Fundrecharge getFundrecharge() {
		return fundrecharge;
	}
	/**
	 * @param Fundrecharge
	 *            the fundrecharge to set
	 */
	public void setFundrecharge(Fundrecharge fundrecharge) {
		this.fundrecharge = fundrecharge;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(fundrecharge == null){
			fundrecharge = new Fundrecharge();
		}
		String id = fundrecharge.getTrade_id();
		if(!ValidateUtil.isDigital(id)){
			fundrecharge = this.fundrechargeService.get(id);
		}
		System.out.println(fundrecharge);
	}
}

