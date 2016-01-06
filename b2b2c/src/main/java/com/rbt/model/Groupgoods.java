/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.model
 * FileName: Groupgoods.java 
 */
package com.rbt.model;

import java.io.Serializable;
/**
 * @function 功能 团购商品表实体
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Mar 16 09:59:24 CST 2012
 */
public class Groupgoods implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String group_id;
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	
	private String goods_id;
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String group_title;
	public String getGroup_title() {
		return group_title;
	}
	public void setGroup_title(String group_title) {
		this.group_title = group_title;
	}
	
	private String cat_attr;
	

	private String bond;
	public String getBond() {
		return bond;
	}
	public void setBond(String bond) {
		this.bond = bond;
	}
	
	private String group_price;
	public String getGroup_price() {
		return group_price;
	}
	public void setGroup_price(String group_price) {
		this.group_price = group_price;
	}
	
	private String cust_maxbuy;
	public String getCust_maxbuy() {
		return cust_maxbuy;
	}
	public void setCust_maxbuy(String cust_maxbuy) {
		this.cust_maxbuy = cust_maxbuy;
	}
	
	private String min_buy;
	public String getMin_buy() {
		return min_buy;
	}
	public void setMin_buy(String min_buy) {
		this.min_buy = min_buy;
	}
	
	private String max_buy;
	public String getMax_buy() {
		return max_buy;
	}
	public void setMax_buy(String max_buy) {
		this.max_buy = max_buy;
	}
	
	private String buy_num;
	public String getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(String buy_num) {
		this.buy_num = buy_num;
	}
	
	private String already_buy;
	public String getAlready_buy() {
		return already_buy;
	}
	public void setAlready_buy(String already_buy) {
		this.already_buy = already_buy;
	}
	
	private String reach_time;
	public String getReach_time() {
		return reach_time;
	}
	public void setReach_time(String reach_time) {
		this.reach_time = reach_time;
	}
	
	private String saler_address;
	public String getSaler_address() {
		return saler_address;
	}
	public void setSaler_address(String saler_address) {
		this.saler_address = saler_address;
	}
	
	private String contact_way;
	public String getContact_way() {
		return contact_way;
	}
	public void setContact_way(String contact_way) {
		this.contact_way = contact_way;
	}
	
	private String group_desc;
	public String getGroup_desc() {
		return group_desc;
	}
	public void setGroup_desc(String group_desc) {
		this.group_desc = group_desc;
	}
	
	private String group_img;
	public String getGroup_img() {
		return group_img;
	}
	public void setGroup_img(String group_img) {
		this.group_img = group_img;
	}
	
	private String is_seven;
	public String getIs_seven() {
		return is_seven;
	}
	public void setIs_seven(String is_seven) {
		this.is_seven = is_seven;
	}
	
	private String is_overdue;
	public String getIs_overdue() {
		return is_overdue;
	}
	public void setIs_overdue(String is_overdue) {
		this.is_overdue = is_overdue;
	}
	
	private String is_ship;
	public String getIs_ship() {
		return is_ship;
	}
	public void setIs_ship(String is_ship) {
		this.is_ship = is_ship;
	}
	
	private String start_date;
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	
	private String end_date;
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
	private String sort_no;
	public String getSort_no() {
		return sort_no;
	}
	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}
	
	private String give_inter;
	public String getGive_inter() {
		return give_inter;
	}
	public void setGive_inter(String give_inter) {
		this.give_inter = give_inter;
	}
	
	private String give_money;
	public String getGive_money() {
		return give_money;
	}
	public void setGive_money(String give_money) {
		this.give_money = give_money;
	}
	
	private String info_state;
	public String getInfo_state() {
		return info_state;
	}
	public void setInfo_state(String info_state) {
		this.info_state = info_state;
	}
	
	private String user_id;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	private String oper_time;
	public String getOper_time() {
		return oper_time;
	}
	public void setOper_time(String oper_time) {
		this.oper_time = oper_time;
	}
	
	private String no_reason;
	public String getNo_reason() {
		return no_reason;
	}
	public void setNo_reason(String no_reason) {
		this.no_reason = no_reason;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Groupgoods[");
		builder.append(", group_id=");
		builder.append(this.group_id);
		builder.append(", goods_id=");
		builder.append(this.goods_id);
		builder.append(", cust_id=");
		builder.append(this.cust_id);
		builder.append(", group_title=");
		builder.append(this.group_title);
		builder.append(", cat_attr=");
		builder.append(this.cat_attr);
		builder.append(", bond=");
		builder.append(this.bond);
		builder.append(", group_price=");
		builder.append(this.group_price);
		builder.append(", cust_maxbuy=");
		builder.append(this.cust_maxbuy);
		builder.append(", min_buy=");
		builder.append(this.min_buy);
		builder.append(", max_buy=");
		builder.append(this.max_buy);
		builder.append(", buy_num=");
		builder.append(this.buy_num);
		builder.append(", already_buy=");
		builder.append(this.already_buy);
		builder.append(", reach_time=");
		builder.append(this.reach_time);
		builder.append(", saler_address=");
		builder.append(this.saler_address);
		builder.append(", contact_way=");
		builder.append(this.contact_way);
		builder.append(", group_desc=");
		builder.append(this.group_desc);
		builder.append(", group_img=");
		builder.append(this.group_img);
		builder.append(", is_seven=");
		builder.append(this.is_seven);
		builder.append(", is_overdue=");
		builder.append(this.is_overdue);
		builder.append(", is_ship=");
		builder.append(this.is_ship);
		builder.append(", start_date=");
		builder.append(this.start_date);
		builder.append(", end_date=");
		builder.append(this.end_date);
		builder.append(", sort_no=");
		builder.append(this.sort_no);
		builder.append(", give_inter=");
		builder.append(this.give_inter);
		builder.append(", give_money=");
		builder.append(this.give_money);
		builder.append(", info_state=");
		builder.append(this.info_state);
		builder.append(", user_id=");
		builder.append(this.user_id);
		builder.append(", oper_time=");
		builder.append(this.oper_time);
		builder.append("]");
		return builder.toString();
	}
	public String getCat_attr() {
		return cat_attr;
	}
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}

}

