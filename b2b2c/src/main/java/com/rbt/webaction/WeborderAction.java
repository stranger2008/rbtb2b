/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.webaction
 * FileName: WeborderAction.java 
 */
package com.rbt.webaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.function.MemberuserFuc;
import com.rbt.model.Buyeraddr;
import com.rbt.model.Member;
import com.rbt.model.Memberuser;
import com.rbt.model.Orderhistory;
import com.rbt.model.Orderinfo;
import com.rbt.model.Supply;
import com.rbt.service.IBuyeraddrService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IOrderhistoryService;
import com.rbt.service.IOrderinfoService;
import com.rbt.service.IPaymentService;
import com.rbt.service.ISendmodeService;
import com.rbt.service.ISupplyService;

/**
 * @function 功能 会员action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Oct 16 10:48:07 CST 2011
 */
@Controller
public class WeborderAction extends WebbaseAction {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 4188151694946837175L;
	@Autowired
	public IPaymentService paymentService;
	@Autowired
	public ISupplyService supplyService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IBuyeraddrService buyeraddrService;
	@Autowired
	public IOrderinfoService orderinfoService;
	@Autowired
	public IOrderhistoryService orderhistoryService;
	@Autowired
	public ISendmodeService sendmodeService;
	// 定义支付方式列表
	public List paymentList;
	// 商品对象
	public Supply supply;
	// 用户对象
	public Member member;
	// 用户对象
	public Memberuser memberuser;
	// 订单对象
	public Orderinfo orderinfo;
	// 订单历史记录
	public Orderhistory orderhistory;
	// 收货地址对象
	public Buyeraddr buyeraddr;
	// 收货地址列表
	public List buyeraddrList;
	public List memberuserList;
	public List orderinfoList;
	public CategoryFuc categoryFuc;
	public String username;
	// 收货地址ID号
	public String checked_id;
	// 收货人
	public String order_cust_name;
	// 地址
	public String order_address;
	// 邮政编码
	public String order_post_code;
	// 电话
	public String order_phone;
	// 手机
	public String order_cell_phone;
	// 商品数量
	public String ord_num;
	// 商品总价
	public String all_price;
	// 买家留言
	public String order_remark;
	// 支付方式
	public String pay_type;
	// 收货字段选择
	public String radioaddr;
	//物流方式列表
	public List sendmodeList;
	//配送方式
	public String smode_id;
	// 下订单
	//发票信息
	public String hidden_fp_type;//发票类型
	public String hidden_fp_title;//发票抬头
	public String hidden_fp_content;//发票内容
	public String hidden_nasuirenhao;//纳税人识别
	public String hidden_registeraddr;//注册地址
	public String hidden_registertel;//注册电话
	public String hidden_openbank;//开户银行
	public String hidden_bankacount;//银行帐户
	public String hidden_mcompany_name;//普通发票单位名称
	public String hidden_zcompany_name;//增值发票单位名称
	private String str_fp_type_ordinary="0";
	private String str_fp_type_increment="1";
	public String execute() throws Exception {
		String id = "";
		// 获取url参数
		if (getRequest().getParameter("supplyid") != null) {
			id = getRequest().getParameter("supplyid");
		}
		if (this.session_cust_id.equals("")) {
			getResponse().sendRedirect("/login.html?loc=/order_" + id + ".html");
		}
		// 绑定支付方式
		HashMap map = new HashMap();
		paymentList = this.paymentService.getList(map);
		// 获取选中的商品
		supply = this.supplyService.get(id);
		if (supply != null) {
			member = this.memberService.get(supply.getCust_id());
		}
		// 卖家
		if (member != null) {
			memberuser = MemberuserFuc.getuserName(member.getCust_id());
		}
		
		//配送方式
		HashMap sendmap = new HashMap();
		sendmodeList = sendmodeService.getList(sendmap);

		HashMap addrMap = new HashMap();
		addrMap.put("cust_id", this.session_cust_id);
		buyeraddrList = this.buyeraddrService.getBuyeraddrList(addrMap);
		buyeraddrList = categoryFuc.replaceList(buyeraddrList, "");
		return goUrl("order_information");
	}

	public String orderUnder() throws Exception {
		// 用于所属地区的回选开始
		loadArea();		
		// 获取url参数
		String supplyid = "";
		if (getRequest().getParameter("supplyid") != null) {
			supplyid = getRequest().getParameter("supplyid");
		}
		supply = this.supplyService.get(supplyid);
		// 获取系统时间精确到毫秒
		String dataid = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		orderinfo = new Orderinfo();
		orderinfo.setOrder_id(dataid);
		if (supply != null) {
			orderinfo.setSeller_id(supply.getCust_id());
		}
		orderinfo.setSupply_id(supplyid);
		if ("0".equals(this.checked_id) || this.checked_id == null) {		
			if (ValidateUtil.isRequired(this.order_cust_name)) {
				this.addFieldError("order_cust_name", "收货人不能为空");
				return execute();
			}
			if (ValidateUtil.isRequired(area_attr) || area_attr.indexOf("0")>-1) {
				this.addFieldError("area_attr", "请选择地区");
				return execute();
			}
			if (ValidateUtil.isRequired(this.order_address)) {
				this.addFieldError("order_address", "详细地址不能为空");
				return execute();
			}
			if (ValidateUtil.isRequired(this.order_post_code)) {
				this.addFieldError("order_post_code", "邮政编码不能为空");
				return execute();
			}
			if (ValidateUtil.isDigital(this.order_post_code)) {
				this.addFieldError("order_post_code", "邮政编码只能是数字");
				return execute();
			}
			if (ValidateUtil.isRequired(this.order_phone) && ValidateUtil.isRequired(this.order_cell_phone)) {
				this.addFieldError("order_phone", "电话或手机至少一个不为空");
				return execute();
			}
			if (!ValidateUtil.isRequired(this.order_phone)) {
				if (ValidateUtil.isTelephone(this.order_phone)) {
					this.addFieldError("order_phone", "电话格式不正确");
					return execute();
				}
			}
			if (!ValidateUtil.isRequired(this.order_cell_phone)) {
				if (ValidateUtil.isMobile(this.order_cell_phone)) {
					this.addFieldError("order_cell_phone", "手机格式不正确");
					return execute();
				}
			}
			this.orderinfo.setCust_name(order_cust_name);
			// 将处理后的所属属性串注入到buy对象中
			this.orderinfo.setArea_attr(area_attr);
			this.orderinfo.setAddress(order_address);
			this.orderinfo.setPost_code(order_post_code);
			this.orderinfo.setPhone(order_phone);
			this.orderinfo.setCell_phone(order_cell_phone);
		} else {
			buyeraddr = this.buyeraddrService.getBuyeraddrByPk(this.checked_id);
			if (buyeraddr != null) {
				this.orderinfo.setCust_name(buyeraddr.getCust_name());
				// 将处理后的所属属性串注入到buy对象中
				this.orderinfo.setArea_attr(buyeraddr.getArea_attr());
				this.orderinfo.setAddress(buyeraddr.getAddress());
				this.orderinfo.setPost_code(buyeraddr.getPost_code());
				this.orderinfo.setPhone(buyeraddr.getPhone());
				this.orderinfo.setCell_phone(buyeraddr.getCell_phone());
			}
		}
		if (supply != null) {
			this.orderinfo.setPrice(supply.getPrice());
			this.orderinfo.setCarriage_fee(supply.getShipfee());
		}

		this.orderinfo.setGoods_num(ord_num);
		this.orderinfo.setGoods_fee(all_price);
		this.orderinfo.setTotal_fee(Double.toString(Double.parseDouble(supply.getShipfee()) + Double.parseDouble(all_price)));
		this.orderinfo.setOrder_state("0");
		this.orderinfo.setRemark(order_remark);

		// 获取客户标识
		String cust_id = "";
		if (this.session_cust_id != null) {
			this.orderinfo.setCust_id(this.session_cust_id);
			cust_id = this.session_cust_id;
		}

		if (cust_id.equals("")) {
			getResponse().sendRedirect("/login.html?loc=/order_" + supplyid + ".html");
		}

		// 获取session中的值
		String user_id = "";
		if (!this.session_user_id.equals("")) {
			this.orderinfo.setUser_id(this.session_user_id);
			user_id = this.session_user_id;
		}
		if (this.pay_type == null) {
			this.addFieldError("pay_type", "请选择交易方式");
			return execute();
		}
		
		//插入配送方式
		if(smode_id!=null && !"".equals(smode_id)){
			orderinfo.setSmode_id(smode_id);
		}
	
		//插入发票
		if(hidden_fp_type!=null&&hidden_fp_type.equals(str_fp_type_ordinary)){
			orderinfo.setCompany_name(hidden_mcompany_name);
		}else if(hidden_fp_type!=null&&hidden_fp_type.equals(str_fp_type_increment)){
		    orderinfo.setCompany_name(hidden_zcompany_name);
		}
		orderinfo.setInvoice_type(hidden_fp_type);
		orderinfo.setInvoice_top(hidden_fp_title);
		orderinfo.setInvoice_content(hidden_fp_content);
		orderinfo.setIdent_number(hidden_nasuirenhao);	
		orderinfo.setRegis_address(hidden_registeraddr);
		orderinfo.setRegis_tel(hidden_registertel);
		orderinfo.setOpen_bank(hidden_openbank);
		orderinfo.setBank_account(hidden_bankacount);
		// 插入订单
		this.orderinfoService.insert(orderinfo);

		// 插入订单历史记录
		orderhistory = new Orderhistory();
		if (orderinfo != null) {
			orderhistory.setOrder_id(orderinfo.getOrder_id());
		}
		orderhistory.setCust_id(cust_id);
		orderhistory.setUser_id(user_id);
		orderhistory.setAction_name("买家下订单");

		this.orderhistoryService.insert(orderhistory);

		getResponse().sendRedirect("/portal/order!orderUnderlist.action");
		return execute();
	}

	public String orderUnderlist() throws Exception {
		// 绑定用户未付款订单
		HashMap map = new HashMap();
		map.put("cust_id", this.session_cust_id);
		map.put("order_state", "0");
		orderinfoList = this.orderinfoService.getList(map);
		return goUrl("order_succeed");
	}

	public Supply getSupply() {
		return supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	public List getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List paymentList) {
		this.paymentList = paymentList;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Memberuser getMemberuser() {
		return memberuser;
	}

	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List getBuyeraddrList() {
		return buyeraddrList;
	}

	public void setBuyeraddrList(List buyeraddrList) {
		this.buyeraddrList = buyeraddrList;
	}

	public String getChecked_id() {
		return checked_id;
	}

	public void setChecked_id(String checked_id) {
		this.checked_id = checked_id;
	}

	public String getOrder_cust_name() {
		return order_cust_name;
	}

	public void setOrder_cust_name(String order_cust_name) {
		this.order_cust_name = order_cust_name;
	}

	public String getOrder_address() {
		return order_address;
	}

	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}

	public String getOrder_post_code() {
		return order_post_code;
	}

	public void setOrder_post_code(String order_post_code) {
		this.order_post_code = order_post_code;
	}

	public String getOrder_phone() {
		return order_phone;
	}

	public void setOrder_phone(String order_phone) {
		this.order_phone = order_phone;
	}

	public String getOrder_cell_phone() {
		return order_cell_phone;
	}

	public void setOrder_cell_phone(String order_cell_phone) {
		this.order_cell_phone = order_cell_phone;
	}


	public String getOrd_num() {
		return ord_num;
	}

	public void setOrd_num(String ord_num) {
		this.ord_num = ord_num;
	}

	public String getAll_price() {
		return all_price;
	}

	public void setAll_price(String all_price) {
		this.all_price = all_price;
	}

	public String getOrder_remark() {
		return order_remark;
	}

	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getRadioaddr() {
		return radioaddr;
	}

	public void setRadioaddr(String radioaddr) {
		this.radioaddr = radioaddr;
	}

	public List getOrderinfoList() {
		return orderinfoList;
	}

	public void setOrderinfoList(List orderinfoList) {
		this.orderinfoList = orderinfoList;
	}

}
