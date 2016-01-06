/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: BuyercouponAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.model.Buyercoupon;
import com.rbt.service.IBuyercouponService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 买家优惠卷action类
 * @author 创建人 邱景岩
 * @date 创建日期 Thu Mar 29 10:41:16 CST 2012
 */
@Controller
public class BuyercouponAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 搜索字段
	 */
	private String is_used_s;
	
	/*
	 * 买家优惠卷对象
	 */
	private Buyercoupon buyercoupon;
	/*
	 * 买家优惠卷业务层接口
	 */
	@Autowired
	private IBuyercouponService buyercouponService;
	/*
	 * 买家优惠卷信息集合
	 */
	public List buyercouponList;
	
	/**
	 * 方法描述：返回新增买家优惠卷页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增买家优惠卷
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(buyercoupon);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.buyercouponService.insert(buyercoupon);
		this.addActionMessage("新增买家优惠卷成功！");
		this.buyercoupon = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改买家优惠卷信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(buyercoupon);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.buyercouponService.update(buyercoupon);
		this.addActionMessage("修改买家优惠卷成功！");
		return list();
	}
	/**
	 * 方法描述：删除买家优惠卷信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.buyercoupon.getId();
		id = id.replace(" ", "");
		this.buyercouponService.delete(id);
		this.addActionMessage("删除买家优惠卷成功！");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("is_every", "0");//是否启用 0:启用 
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("give_cust_id", this.session_cust_id);
		}
		if(is_used_s != null && !"".equals(is_used_s)){
			if(is_used_s.equals("1")){
				pageMap.put("coupon_times", is_used_s);
			}
			if(is_used_s.equals("2")){
				pageMap.put("coupon_num", is_used_s);
			}
			if(is_used_s.equals("3")){
				pageMap.put("expiry_date", is_used_s);
			}
			
		}
		//根据页面提交的条件找出信息总数
		int count=this.buyercouponService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		buyercouponList = this.buyercouponService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出买家优惠卷信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.buyercoupon.getId();
		if(buyercoupon.getCust_id()!=null){
			if(accessControl(buyercoupon.getCust_id())){
				return list();
			}
		}
		if(id==null || id.equals("")){
			buyercoupon = new Buyercoupon();
		}else{
			buyercoupon = this.buyercouponService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the BuyercouponList
	 */
	public List getBuyercouponList() {
		return buyercouponList;
	}
	/**
	 * @param buyercouponList
	 *  the BuyercouponList to set
	 */
	public void setBuyercouponList(List buyercouponList) {
		this.buyercouponList = buyercouponList;
	}
	
	/**
	 * @return the buyercoupon
	 */
	public Buyercoupon getBuyercoupon() {
		return buyercoupon;
	}
	/**
	 * @param Buyercoupon
	 *            the buyercoupon to set
	 */
	public void setBuyercoupon(Buyercoupon buyercoupon) {
		this.buyercoupon = buyercoupon;
	}
	
	public String getIs_used_s() {
		return is_used_s;
	}

	public void setIs_used_s(String is_used_s) {
		this.is_used_s = is_used_s;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(buyercoupon == null){
			buyercoupon = new Buyercoupon();
		}
		String id = this.buyercoupon.getId();
		if(!this.validateFactory.isDigital(id)){
			buyercoupon = this.buyercouponService.get(id);
		}
	}

}

