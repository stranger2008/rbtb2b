/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: CouponAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.model.Coupon;
import com.rbt.service.ICouponService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录优惠卷表信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Mar 22 13:38:28 CST 2012
 */
@Controller
public class CouponAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录优惠卷表信息对象
	 */
	private Coupon coupon;
	/*
	 * 记录优惠卷表信息业务层接口
	 */
	@Autowired
	private ICouponService couponService;
	/*
	 * 记录优惠卷表信息信息集合
	 */
	public List couponList;
	/*
	 * 搜索字段
	 */
	public String c_name_s;
	public String c_type_s;
	public String is_every_s;
	//开始时间段
	public String start_time_s;
	public String start_time_e_s;
	//结束时间段
	public String end_time_s;
	public String end_time_e_s;
	public String is_enable_s;
	

	/**
	 * @return the coupon
	 */
	public Coupon getCoupon() {
		return coupon;
	}
	/**
	 * @param Coupon
	 *            the coupon to set
	 */
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
	
	/**
	 * 方法描述：返回新增记录优惠卷表信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			controlInfoNum();
		}
		return goUrl(ADD);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 13, 2012 1:42:18 PM
	 * @Method Description : 会员发布条数的判断
	 */
	public boolean controlInfoNum(){
		//查出已发布的信息个数
		Map tmap = new HashMap();
		tmap.put("cust_id", this.session_cust_id);
		int count = this.couponService.getCount(tmap);
		if(controlMsgNum(count,"coupon")){
			return true;
		}else{
			return false;
		}
	}

	

	/**
	 * 方法描述：新增记录优惠卷表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		coupon.setCust_id(this.session_cust_id);
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return add();
			}
		}
		super.commonValidateField(coupon);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.couponService.insert(coupon);
		this.addActionMessage("新增记录优惠卷表信息成功！");
		this.coupon = null;
		is_crotorl=true;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录优惠卷表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		super.commonValidateField(coupon);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.couponService.update(coupon);
		this.addActionMessage("修改记录优惠卷表信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录优惠卷表信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.coupon.getC_id();
		id = id.replace(" ", "");
		this.couponService.delete(id);
		this.addActionMessage("删除记录优惠卷表信息成功！");
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
		if (c_name_s != null && !c_name_s.equals("")) {
			pageMap.put("c_name", c_name_s);
		}
		if (c_type_s != null && !c_type_s.equals("")) {
			pageMap.put("c_type", c_type_s);
		}
		if (is_every_s != null && !is_every_s.equals("")) {
			pageMap.put("is_every", is_every_s);
		}
		//搜索开始时间段
		if (start_time_s != null && !start_time_s.equals("")) {
			pageMap.put("start_time", start_time_s);
		}
		if (start_time_e_s != null && !start_time_e_s.equals("")) {
			pageMap.put("start_time_e_s", start_time_e_s);
		}
		//搜索结束时间段
		if (end_time_s != null && !end_time_s.equals("")) {
			pageMap.put("end_time", end_time_s);
		}
		if (end_time_e_s != null && !end_time_e_s.equals("")) {
			pageMap.put("end_time_e_s", end_time_e_s);
		}
		//
		if (is_enable_s != null && !is_enable_s.equals("")) {
			pageMap.put("is_enable", is_enable_s);
		}
		
		if(this.session_cust_id!=null && !this.session_cust_id.equals(""))
		{
			pageMap.put("cust_id", session_cust_id);
		}
		
		//根据页面提交的条件找出信息总数
		int count=this.couponService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		couponList = this.couponService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录优惠卷表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(coupon.getCust_id()!=null){
			if(accessControl(coupon.getCust_id())){
				return list();
			}
		}
		String id = this.coupon.getC_id();
		if(id==null || id.equals("")){
			coupon = new Coupon();
		}else{
			coupon = this.couponService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the CouponList
	 */
	public List getCouponList() {
		return couponList;
	}
	/**
	 * @param couponList
	 *  the CouponList to set
	 */
	public void setCouponList(List couponList) {
		this.couponList = couponList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(coupon == null){
			coupon = new Coupon();
		}
		String id = this.coupon.getC_id();
		if(!this.validateFactory.isDigital(id)){
			coupon = this.couponService.get(id);
		}
	}
	
	/**
	 * 方法描述：批量修改推荐
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String cid = this.coupon.getC_id();
		String is_every = this.coupon.getIs_every();
		cid = cid.replace(" ", "");
		String downStr[] = cid.split(",");
		List couponList = new ArrayList();
		if (downStr.length > 0) {
			for (int i = 0; i < downStr.length; i++) {
				Map couponMap = new HashMap();
				couponMap.put("is_every", is_every);
				couponMap.put("c_id", downStr[i]);
				couponList.add(couponMap);
			}
		}
		this.couponService.updateCouponState(couponList);
		String tip = "";
		if (is_every.equals("0")) {
			tip = "取消通用成功";
		} else if (is_every.equals("1")) {
			tip = "设置通用成功";
		}
		this.addActionMessage(tip);
		return list();
	}

}

