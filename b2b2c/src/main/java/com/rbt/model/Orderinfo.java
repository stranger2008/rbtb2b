/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Orderinfo.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 记录平台支付方式信息实体
 * @author 创建人 订单管理
 * @date 创建日期 Tue Oct 25 17:06:38 CST 2011
 */
public class Orderinfo implements Serializable {

	static final long serialVersionUID = 1L;
	
	
	String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	String seller_id;
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	
	String supply_id;
	public String getSupply_id() {
		return supply_id;
	}
	public void setSupply_id(String supply_id) {
		this.supply_id = supply_id;
	}
	
	String cust_name;
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	String area_attr;
	public String getArea_attr() {
		return area_attr;
	}
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	
	String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	String post_code;
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	
	String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	String cell_phone;
	public String getCell_phone() {
		return cell_phone;
	}
	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}
	
	String price;
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	String goods_num;
	public String getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(String goods_num) {
		this.goods_num = goods_num;
	}
	
	String carriage_fee;
	public String getCarriage_fee() {
		return carriage_fee;
	}
	public void setCarriage_fee(String carriage_fee) {
		this.carriage_fee = carriage_fee;
	}
	
	String goods_fee;
	public String getGoods_fee() {
		return goods_fee;
	}
	public void setGoods_fee(String goods_fee) {
		this.goods_fee = goods_fee;
	}
	
	String total_fee;
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	
	String order_state;
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	
	String remark;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	String in_date;
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	
	
	String invoice_type;
	String invoice_top;
	String company_name;
	String ident_number;
	String regis_address;
	String regis_tel;
	String open_bank;
	String bank_account;
	String invoice_content;
	String smode_id;
	
	
	
	public String getSmode_id() {
		return smode_id;
	}
	public void setSmode_id(String smode_id) {
		this.smode_id = smode_id;
	}
	public String getInvoice_type() {
		return invoice_type;
	}
	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}
	public String getInvoice_top() {
		return invoice_top;
	}
	public void setInvoice_top(String invoice_top) {
		this.invoice_top = invoice_top;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getIdent_number() {
		return ident_number;
	}
	public void setIdent_number(String ident_number) {
		this.ident_number = ident_number;
	}
	public String getRegis_address() {
		return regis_address;
	}
	public void setRegis_address(String regis_address) {
		this.regis_address = regis_address;
	}
	public String getRegis_tel() {
		return regis_tel;
	}
	public void setRegis_tel(String regis_tel) {
		this.regis_tel = regis_tel;
	}
	public String getOpen_bank() {
		return open_bank;
	}
	public void setOpen_bank(String open_bank) {
		this.open_bank = open_bank;
	}
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	public String getInvoice_content() {
		return invoice_content;
	}
	public void setInvoice_content(String invoice_content) {
		this.invoice_content = invoice_content;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Orderinfo[");
		builder.append(", order_id=");
		builder.append(this.order_id);
		builder.append(", seller_id=");
		builder.append(this.seller_id);
		builder.append(", supply_id=");
		builder.append(this.supply_id);
		builder.append(", cust_name=");
		builder.append(this.cust_name);
		builder.append(", area_attr=");
		builder.append(this.area_attr);
		builder.append(", address=");
		builder.append(this.address);
		builder.append(", post_code=");
		builder.append(this.post_code);
		builder.append(", phone=");
		builder.append(this.phone);
		builder.append(", cell_phone=");
		builder.append(this.cell_phone);
		builder.append(", price=");
		builder.append(this.price);
		builder.append(", goods_num=");
		builder.append(this.goods_num);
		builder.append(", carriage_fee=");
		builder.append(this.carriage_fee);
		builder.append(", goods_fee=");
		builder.append(this.goods_fee);
		builder.append(", total_fee=");
		builder.append(this.total_fee);
		builder.append(", order_state=");
		builder.append(this.order_state);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", in_date=");
		builder.append(this.in_date);
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
		builder.append(", smode_id=");
		builder.append(this.smode_id);
		builder.append("]");
		return builder.toString();
	}

}

