/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: PaymentAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Payment;
import com.rbt.service.IPaymentService;

/**
 * @function 功能 记录平台支付方式信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Oct 24 10:57:44 CST 2011
 */
@Controller
public class PaymentAction extends BaseAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录平台支付方式信息对象
	 */
	public Payment payment;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IPaymentService paymentService;
	/*
	 * 记录平台支付方式信息信息集合
	 */
	public List paymentList;
	/*
	 * 搜索字段
	 */
	public String pay_code_s;
	public String pay_name_s;
	public String pay_account_s;
	public String enabled_s;
	//新密钥
	public String passwd;
	//旧密钥
	public String oldpasswd;
	public void prepare() throws Exception { super.saveRequestParameter();
		if(payment == null){
			payment = new Payment();
		}
		String id = payment.getPay_id();
		if(!ValidateUtil.isDigital(id)){
			payment = this.paymentService.get(id);
		}
	}
	
	
	/**
	 * 方法描述：返回新增记录平台支付方式信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录平台支付方式信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		//验证支付编码是否存在
		String paycode = this.payment.getPay_code();
		Map pageMap = new HashMap();
		pageMap.put("pay_code", paycode);

		// 通过用户名找出用户信息
		List userList = this.paymentService.getList(pageMap);
		if (userList != null && userList.size() > 0) {
			this.addFieldError("payment.pay_code", "支付接口编码已存在,请重新输入");
		}
		//字段验证
		super.commonValidateField(payment);
		if(super.ifvalidatepass){
			return add();
		}
		this.paymentService.insert(payment);
		this.addActionMessage("新增平台支付方式成功");
		this.payment = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录平台支付方式信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证
		super.commonValidateField(payment);
		if(super.ifvalidatepass){
			return view();
		}
		//新旧密钥判断
		if((!"".equals(passwd)) && ("".equals(oldpasswd))){
			this.addFieldError("oldpasswd", "请输入旧密钥");
			return view();
		}
		//获取旧密钥判断
		if(oldpasswd!=null && !"".equals(oldpasswd)){
			//获取数据库密码与就密钥对比
			if(oldpasswd.equals(this.payment.getPasswd())){
				//获取新密钥
				if(passwd!=null && !"".equals(passwd)){
					this.payment.setPasswd(passwd);
				}
				else{
					this.addFieldError("passwd", "请输入新密钥");
					return view();
				}
			}
			else{
				this.addFieldError("oldpasswd", "旧密钥输入错误，请重新输入");
				return view();
			}
		}	
		this.paymentService.update(payment);
		this.addActionMessage("修改平台支付方式成功");
		return list();
	}
	/**
	 * 方法描述：删除记录平台支付方式信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.payment.getPay_id();
		id = id.replace(" ", "");
		this.paymentService.delete(id);
		this.addActionMessage("删除平台支付方式成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if(pay_code_s!=null && !pay_code_s.equals("")) pageMap.put("pay_code", pay_code_s);
		if(pay_name_s!=null && !pay_name_s.equals("")) pageMap.put("pay_name", pay_name_s);
		if(pay_account_s!=null && !pay_account_s.equals("")) pageMap.put("pay_account", pay_account_s);
		if(enabled_s!=null && !enabled_s.equals("")) pageMap.put("enabled", enabled_s);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.paymentService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		paymentList = this.paymentService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录平台支付方式信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：批量显示/不显示导航
	 * @return
	 * @throws Exception
	 */
	public String updateenabled() throws Exception {
		String payid = this.payment.getPay_id();
		String enabled = this.payment.getEnabled();
		payid = payid.replace(" ", "");
		String payidStr[] = payid.split(",");
		List payList = new ArrayList();
		if(payidStr.length>0){
			for(int i=0;i<payidStr.length;i++){
				HashMap payMap = new HashMap();
				payMap.put("enabled", enabled);
				payMap.put("pay_id", payidStr[i]);
				payList.add(payMap);
			}
		}
		this.paymentService.updateEnabled(payList);
		String tip = "";
		if(enabled.equals("0")){
			tip = "启用成功";
		}else if(enabled.equals("1")){
			tip = "不启用成功";
		}
		this.addActionMessage(tip);
		return list();
	}
	
	
	/**
	 * @return the PaymentList
	 */
	public List getPaymentList() {
		return paymentList;
	}
	/**
	 * @param paymentList
	 *  the PaymentList to set
	 */
	public void setPaymentList(List paymentList) {
		this.paymentList = paymentList;
	}
	public String getPay_code_s() {
		return pay_code_s;
	}
	public void setPay_code_s(String pay_code_s) {
		this.pay_code_s = pay_code_s;
	}
	public String getPay_name_s() {
		return pay_name_s;
	}
	public void setPay_name_s(String pay_name_s) {
		this.pay_name_s = pay_name_s;
	}
	public String getPay_account_s() {
		return pay_account_s;
	}
	public void setPay_account_s(String pay_account_s) {
		this.pay_account_s = pay_account_s;
	}
	public String getEnabled_s() {
		return enabled_s;
	}
	public void setEnabled_s(String enabled_s) {
		this.enabled_s = enabled_s;
	}
	/**
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}
	/**
	 * @param Payment
	 *            the payment to set
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}

