/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: OrderdetailAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.model.Orderdetail;
import com.rbt.service.IOrderdetailService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 订单商品详细action类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Mar 28 17:27:56 CST 2012
 */
@Controller
public class OrderdetailAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 订单商品详细对象
	 */
	private Orderdetail orderdetail;
	
	/*
	 * 订单商品详细业务层接口
	 */
	@Autowired
	private IOrderdetailService orderdetailService;
	
	/*
	 * 订单商品详细信息集合
	 */
	public List orderdetailList;

	/**
	 * @return the orderdetail
	 */
	public Orderdetail getOrderdetail() {
		return orderdetail;
	}
	/**
	 * @param Orderdetail
	 *            the orderdetail to set
	 */
	public void setOrderdetail(Orderdetail orderdetail) {
		this.orderdetail = orderdetail;
	}
	
	/**
	 * 方法描述：返回新增订单商品详细页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增订单商品详细
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(orderdetail);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.orderdetailService.insert(orderdetail);
		this.addActionMessage("新增订单商品详细成功！");
		this.orderdetail = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改订单商品详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(orderdetail);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.orderdetailService.update(orderdetail);
		this.addActionMessage("修改订单商品详细成功！");
		return list();
	}
	/**
	 * 方法描述：删除订单商品详细信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.orderdetail.getTrade_id();
		id = id.replace(" ", "");
		this.orderdetailService.delete(id);
		this.addActionMessage("删除订单商品详细成功！");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		
		//根据页面提交的条件找出信息总数
		int count=this.orderdetailService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		orderdetailList = this.orderdetailService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出订单商品详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.orderdetail.getTrade_id();
		if(id==null || id.equals("")){
			orderdetail = new Orderdetail();
		}else{
			orderdetail = this.orderdetailService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the OrderdetailList
	 */
	public List getOrderdetailList() {
		return orderdetailList;
	}
	/**
	 * @param orderdetailList
	 *  the OrderdetailList to set
	 */
	public void setOrderdetailList(List orderdetailList) {
		this.orderdetailList = orderdetailList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(orderdetail == null){
			orderdetail = new Orderdetail();
		}
		String id = this.orderdetail.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			orderdetail = this.orderdetailService.get(id);
		}
	}

}

