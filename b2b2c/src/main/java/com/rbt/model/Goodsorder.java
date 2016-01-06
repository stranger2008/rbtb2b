/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Goodsorder.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 订单商品表实体
 * @author 创建人 林俊钦
 * @date 创建日期 Mon Mar 19 15:53:24 CST 2012
 */
public class Goodsorder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String buy_cust_id;
	public String getBuy_cust_id() {
		return buy_cust_id;
	}
	public void setBuy_cust_id(String buy_cust_id) {
		this.buy_cust_id = buy_cust_id;
	}
	
	private String sale_cust_id;
	public String getSale_cust_id() {
		return sale_cust_id;
	}
	public void setSale_cust_id(String sale_cust_id) {
		this.sale_cust_id = sale_cust_id;
	}
	
	private String consignee;
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	
	private String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	private String zip_code;
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	
	private String telephone;
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	private String mobile;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	private String goods_amount;
	public String getGoods_amount() {
		return goods_amount;
	}
	public void setGoods_amount(String goods_amount) {
		this.goods_amount = goods_amount;
	}
	
	private String ship_free;
	public String getShip_free() {
		return ship_free;
	}
	public void setShip_free(String ship_free) {
		this.ship_free = ship_free;
	}
	
	private String tax_invoice;
	public String getTax_invoice() {
		return tax_invoice;
	}
	public void setTax_invoice(String tax_invoice) {
		this.tax_invoice = tax_invoice;
	}
	
	private String discount;
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	private String discount_money;
	public String getDiscount_money() {
		return discount_money;
	}
	public void setDiscount_money(String discount_money) {
		this.discount_money = discount_money;
	}
	
	private String dis_explain;
	public String getDis_explain() {
		return dis_explain;
	}
	public void setDis_explain(String dis_explain) {
		this.dis_explain = dis_explain;
	}
	
	private String insured;
	public String getInsured() {
		return insured;
	}
	public void setInsured(String insured) {
		this.insured = insured;
	}
	
	private String tatal_amount;
	public String getTatal_amount() {
		return tatal_amount;
	}
	public void setTatal_amount(String tatal_amount) {
		this.tatal_amount = tatal_amount;
	}
	
	private String inter_money;
	public String getInter_money() {
		return inter_money;
	}
	public void setInter_money(String inter_money) {
		this.inter_money = inter_money;
	}
	
	private String buy_mode;
	public String getBuy_mode() {
		return buy_mode;
	}
	public void setBuy_mode(String buy_mode) {
		this.buy_mode = buy_mode;
	}
	
	private String pay_id;
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	
	private String send_mode;
	public String getSend_mode() {
		return send_mode;
	}
	public void setSend_mode(String send_mode) {
		this.send_mode = send_mode;
	}
	
	private String order_state;
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	
	private String pay_state;
	public String getPay_state() {
		return pay_state;
	}
	public void setPay_state(String pay_state) {
		this.pay_state = pay_state;
	}
	
	private String send_state;
	public String getSend_state() {
		return send_state;
	}
	public void setSend_state(String send_state) {
		this.send_state = send_state;
	}
	
	private String order_time;
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	
	private String pay_time;
	public String getPay_time() {
		return pay_time;
	}
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	
	private String send_time;
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	
	private String mem_remark;
	public String getMem_remark() {
		return mem_remark;
	}
	public void setMem_remark(String mem_remark) {
		this.mem_remark = mem_remark;
	}
	
	private String invoice_type;
	public String getInvoice_type() {
		return invoice_type;
	}
	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}
	
	private String invoice_top;
	public String getInvoice_top() {
		return invoice_top;
	}
	public void setInvoice_top(String invoice_top) {
		this.invoice_top = invoice_top;
	}
	
	private String company_name;
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	
	private String ident_number;
	public String getIdent_number() {
		return ident_number;
	}
	public void setIdent_number(String ident_number) {
		this.ident_number = ident_number;
	}
	
	private String regis_address;
	public String getRegis_address() {
		return regis_address;
	}
	public void setRegis_address(String regis_address) {
		this.regis_address = regis_address;
	}
	
	private String regis_tel;
	public String getRegis_tel() {
		return regis_tel;
	}
	public void setRegis_tel(String regis_tel) {
		this.regis_tel = regis_tel;
	}
	
	private String open_bank;
	public String getOpen_bank() {
		return open_bank;
	}
	public void setOpen_bank(String open_bank) {
		this.open_bank = open_bank;
	}
	
	private String bank_account;
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	
	private String invoice_content;
	public String getInvoice_content() {
		return invoice_content;
	}
	public void setInvoice_content(String invoice_content) {
		this.invoice_content = invoice_content;
	}
	
	private String give_inter;
	public String getGive_inter() {
		return give_inter;
	}
	public void setGive_inter(String give_inter) {
		this.give_inter = give_inter;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goodsorder[");
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", buy_cust_id=");
		builder.append(this.buy_cust_id);
		builder.append(", sale_cust_id=");
		builder.append(this.sale_cust_id);
		builder.append(", consignee=");
		builder.append(this.consignee);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", address=");
		builder.append(this.address);
		builder.append(", zip_code=");
		builder.append(this.zip_code);
		builder.append(", telephone=");
		builder.append(this.telephone);
		builder.append(", mobile=");
		builder.append(this.mobile);
		builder.append(", goods_amount=");
		builder.append(this.goods_amount);
		builder.append(", ship_free=");
		builder.append(this.ship_free);
		builder.append(", tax_invoice=");
		builder.append(this.tax_invoice);
		builder.append(", discount=");
		builder.append(this.discount);
		builder.append(", discount_money=");
		builder.append(this.discount_money);
		builder.append(", dis_explain=");
		builder.append(this.dis_explain);
		builder.append(", insured=");
		builder.append(this.insured);
		builder.append(", tatal_amount=");
		builder.append(this.tatal_amount);
		builder.append(", inter_money=");
		builder.append(this.inter_money);
		builder.append(", buy_mode=");
		builder.append(this.buy_mode);
		builder.append(", pay_id=");
		builder.append(this.pay_id);
		builder.append(", send_mode=");
		builder.append(this.send_mode);
		builder.append(", order_state=");
		builder.append(this.order_state);
		builder.append(", pay_state=");
		builder.append(this.pay_state);
		builder.append(", send_state=");
		builder.append(this.send_state);
		builder.append(", order_time=");
		builder.append(this.order_time);
		builder.append(", pay_time=");
		builder.append(this.pay_time);
		builder.append(", send_time=");
		builder.append(this.send_time);
		builder.append(", mem_remark=");
		builder.append(this.mem_remark);
		builder.append(", invoice_type=");
		builder.append(this.invoice_type);
		builder.append(", invoice_top=");
		builder.append(this.invoice_top);
		builder.append(", company_name=");
		builder.append(this.company_name);
		builder.append(", ident_number=");
		builder.append(this.ident_number);
		builder.append(", regis_address=");
		builder.append(this.regis_address);
		builder.append(", regis_tel=");
		builder.append(this.regis_tel);
		builder.append(", open_bank=");
		builder.append(this.open_bank);
		builder.append(", bank_account=");
		builder.append(this.bank_account);
		builder.append(", invoice_content=");
		builder.append(this.invoice_content);
		builder.append(", give_inter=");
		builder.append(this.give_inter);
		builder.append("]");
		return builder.toString();
	}
	
}

