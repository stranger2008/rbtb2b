/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: GoodsorderAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.model.Goodseval;
import com.rbt.model.Goodsorder;
import com.rbt.model.Member;
import com.rbt.model.Payment;
import com.rbt.model.Sellerscore;
import com.rbt.model.Sendmode;
import com.rbt.model.Shopconfig;
import com.rbt.service.ICommparaService;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IPaymentService;
import com.rbt.service.ISellerscoreService;
import com.rbt.service.ISendmodeService;
import com.rbt.service.IShopconfigService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 订单商品表action类
 * @author 创建人 林俊钦
 * @date 创建日期 Mon Mar 19 15:53:24 CST 2012
 */
@Controller
public class GoodsorderAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * 搜索字段
	 */
	private String order_id_s;
	private String goods_name_s;
	private String consignee_s;
	private String goods_amount_s;
	private String order_time_s;
	private String order_state_s;
	private String my_order_s;
	private String pay_state_s;
	private String send_state_s;
	public  String buy_cust_name;
	public Member saler;
	public Member buyer;
	public String area_name;
	public String payName;
	public String sendModeName;
	public String orderStateName;
	public String payStateName;
	public String sendStateName;
	public List detailList;
	public List orderList;
	public String oid;
	public String comfirmpwd;
	public List scoreList;
	//待发货订单
	public String daiorderId;
	//未处理订单
	public String weiId;
	//今天订单
	public String jinId;
	//待付款订单
	public String daiId;
	public String up_ship;
	public String up_tax;
	public String up_insured;
	//近一个月的订单
	public String timeOrder;
	
	public String order_pay_state;
	public String order_order_state;
	public String order_send_state;
	//地址字符串
	public String sale_area_String;
	
	/*
	 * 订单商品表对象
	 */
	private Goodsorder goodsorder;
	public  Member saleMember;
	public  Goodseval goodseval;
	public  Sellerscore sellerscore;
	public  ValidateUtil validateUtil;
	public  Shopconfig shopconfig;
	public  AreaFuc areaFuc;
	public  Payment  payment;
	/*
	 * 订单商品表业务层接口
	 */
	@Autowired
	private IGoodsorderService goodsorderService;
	@Autowired
	private IGoodsevalService goodsevalService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IPaymentService paymentService;
	@Autowired
	private ISendmodeService sendmodeService;
	@Autowired
	private ICommparaService commparaService;
	@Autowired
	private IOrderdetailService orderdetailService;
	@Autowired
	private IMemberfundService memberfundService;
	@Autowired
	public ISellerscoreService sellerscoreService;
	@Autowired
	public IShopconfigService shopconfigService;
	/*
	 * 订单商品表信息集合
	 */
	public List goodsorderList;

	public List buyoutmemberList;
	//获取订单状态参数列表
	public List orStateList;
	//获取付款状态参数列表
	public List payList;
	//获取发货状态参数列表
	public List sendList;
	
	//找出订单状态参数
	public void getOrStateList(){
		Map stateMap = new HashMap();
		stateMap.put("para_code", "order_state");
		orStateList = this.commparaService.getList(stateMap);
	}
	//找出付款状态参数
	public void getOrPayList(){
		Map pageMap = new HashMap();
		pageMap.put("para_code", "pay_state");
		payList = this.commparaService.getList(pageMap);
	}
	//找出发货状态参数
	public void getOrSendList(){
		Map pageMap = new HashMap();
		pageMap.put("para_code", "send_state");
		sendList = this.commparaService.getList(pageMap);
	}
	/**
	 * 方法描述：返回新增订单商品表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 24, 2012 3:41:13 PM
	 * @Method Description :确认收货的页面
	 */
	public String comfirmgoods(){	
		getOrderDetail();
		//获取卖家会员信息
		if(goodsorder!=null&&goodsorder.getSale_cust_id()!=null){
			saleMember=this.memberService.get(goodsorder.getSale_cust_id());
		}		
		return goUrl("confirmgoods");
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 25, 2012 4:44:32 PM
	 * @Method Description : 获取订单详细
	 */
	public void getOrderDetail(){
		//获取订单商品详细信息
		Map orderMap=new HashMap();
		if(oid!=null&&!oid.equals("")){
			orderMap.put("order_id", oid); 
		}
		detailList=this.orderdetailService.getList(orderMap);
		//获取订单详细
		goodsorder=this.goodsorderService.get(oid);	
		String pay_id=goodsorder.getPay_id();
		payment=paymentService.get(pay_id);
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 25, 2012 3:34:53 PM
	 * @Method Description :为商品评价，为卖家打分
	 */
	public String eval(){
		getOrderDetail();
		//获取卖家会员信息
		if(goodsorder!=null&&goodsorder.getSale_cust_id()!=null){
			String sale_cust_id=goodsorder.getSale_cust_id();
			saleMember=this.memberService.get(sale_cust_id);
			// 获取对店铺动态评分列表			
			Map scoreMap=new HashMap();
			scoreMap.put("cust_id", sale_cust_id);
			scoreList=this.sellerscoreService.getCountList(scoreMap);
		}		

		return goUrl("eval");
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 25, 2012 1:14:31 PM
	 * @Method Description ：确认付款
	 */
	public String comfirmpay(){
		if(comfirmpwd==null||comfirmpwd.equals("")){			
			this.addActionMessage("支付密码不能为空!");
			return comfirmgoods();
		}
		Map payMap=new HashMap();
		payMap.put("cust_id", this.session_cust_id);
		String pwd= Md5.getMD5(comfirmpwd.getBytes());
		payMap.put("pay_passwd", pwd);
		List list =this.memberfundService.getList(payMap);
		if(list!=null&&list.size()>0){
			//更新确认收货成功，交易成功操作
			Map map=new HashMap();
			map.put("order_state","5");
			map.put("order_id", oid);
			this.goodsorderService.update(map);
			return goUrl("confirmsuccess"); 
		}else{
			this.addActionMessage("支付密码不正确,重新输入!");
			return comfirmgoods();
		}
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 25, 2012 7:44:03 PM
	 * @Method Description :商品评价与店铺的动态评分
	 */
	public String comfirmeval(){
		if(goodseval==null){
			return eval();
		}
		String[] gids=goodseval.getGoods_id().split(",");
		String[] evals=goodseval.getG_eval().split(",");
		String[] ments=goodseval.getG_comment().split("##########");
		
		List list=new ArrayList();
		for(int i=0;i<gids.length;i++){
			String gid=gids[i].trim();
			String eval=evals[i].trim();
			String ment=ments[i].trim();
			String cust_id=this.session_cust_id;
			String user_id=this.session_user_id;
			
			Goodseval gs=new Goodseval();
			gs.setGoods_id(gid);
			gs.setG_eval(eval);
			gs.setG_comment(ment);
			gs.setCust_id(cust_id);
			gs.setUser_id(user_id);
			gs.setIs_two("0");
			list.add(gs);			
		}
		
		sellerscore.setSelf_cust_id(this.session_cust_id);
		sellerscore.setUser_id(this.session_user_id);
		this.goodsevalService.insertGoodsevalrecord(list,sellerscore,oid);
		return goUrl("evalsuccess");
		
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 26, 2012 5:01:58 PM
	 * @Method Description :买家查看订单详情
	 */
	public String orderdetail(){
		getOrderDetail();
		saleMember=this.memberService.get(goodsorder.getSale_cust_id());
		//查找店铺名称、地址、邮箱
		shopconfig=this.shopconfigService.GetByCustId(goodsorder.getSale_cust_id());
		if(shopconfig==null){
			shopconfig=new Shopconfig();
		}
		//得到店铺字符串
		String area_id = shopconfig.getArea_attr();
		//转化为具体的地区
		sale_area_String = areaFuc.getAreaNameByMap(area_id);
		msgByOrderid();
		return goUrl("orderdetail");
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 27, 2012 12:52:04 PM
	 * @Method Description :商品订单的搜索条件
	 */
	private Map setOrderMap(Map pageMap){
		 if(order_id_s != null && !"".equals(my_order_s)){
		    	pageMap.put("my_order",my_order_s);
		    }
		    if(goods_name_s != null && !"".equals(my_order_s)){
		    	pageMap.put("my_order",my_order_s);
		    }
		    if(consignee_s != null && !"".equals(consignee_s)){
		    	pageMap.put("consignee",consignee_s);
		    }
		    if(order_time_s != null && !"".equals(order_time_s)){
		    	pageMap.put("order_time",order_time_s);
		    }
		    if(goods_amount_s != null && !"".equals(goods_amount_s)){
		    	pageMap.put("goods_amount",goods_amount_s);
		    }
		    if(order_state_s != null && !"".equals(order_state_s)){
		    	pageMap.put("order_state",order_state_s);
		    }
		return pageMap;
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 26, 2012 9:48:11 PM
	 * @Method Description :我是卖家订单管理
	 */
	public String manaorder(){
		getOrStateList();
		getOrPayList();
		getOrSendList();
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("sale_cust_id", this.session_cust_id);
		}
	    pageMap=setOrderMap(pageMap);
	  //搜索待发货订单
	    if(daiorderId!=null && !"".equals(daiorderId))
	    {
	    	pageMap.put("order_state_r","1");
	    	pageMap.put("pay_state","1");
	    	pageMap.put("send_state","0");
	    }
	    //搜索未处理订单
	    if(weiId!=null && !"".equals(weiId))
	    {
	    	pageMap.put("order_state_w","1");
	    }
	    //搜索今天订单
	    if(jinId!=null && !"".equals(jinId))
	    {
	    	pageMap.put("today", "0");
	    }
	    //搜索待处理订单
	    if(daiId!=null && !"".equals(daiId))
	    {
	    	pageMap.put("pay_state", "0");
	    }
	  //通过订单ID搜索
	    if(order_id_s!=null && !"".equals(order_id_s))
	    {
	    	pageMap.put("order_id", order_id_s);
	    }
	    //订单状态搜索
	    if(order_state_s!=null && !"".equals(order_state_s))
	    {
	    	pageMap.put("order_state", order_state_s);
	    	
	    }
	    //付款状态搜索
	    if(pay_state_s!=null && !"".equals(pay_state_s))
	    {
	    	pageMap.put("pay_state", pay_state_s);
	    	
	    }
	    //发货状态搜索
	    if(send_state_s!=null && !"".equals(send_state_s))
	    {
	    	pageMap.put("send_state", send_state_s);
	    	
	    }
	    //商品名称搜索
	    if(goods_name_s!=null && !"".equals(goods_name_s))
	    {	    	
	    	pageMap.put("goods_name", goods_name_s);
	    }
	  //买家会员名称搜索
	    if(buy_cust_name!=null && !"".equals(buy_cust_name))
	    {	    	
	    	pageMap.put("cust_name", buy_cust_name);
	    }
	    //商城地区过滤
		pageMap=super.shopareafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.goodsorderService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		getOrder(pageMap);
		return goUrl("manaorder");		
	 }
	
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 27, 2012 11:01:19 AM
	 * @Method Description :我是卖家查看订单详情
	 */
	public String manaorderdetail(){
		if(oid==null||oid.equals("")){
			return goUrl("manaorder");
		}else{
			goodsorder = this.goodsorderService.get(oid);
		}
		if(goodsorder==null){
			goodsorder=new Goodsorder();
		}
		msgByOrderid();
		//获取订单详细
		getOrderDetail();
		//获取卖家会员信息
		if(goodsorder!=null&&goodsorder.getSale_cust_id()!=null){
			saleMember=this.memberService.get(goodsorder.getSale_cust_id());
		}
		//查找店铺名称、地址、邮箱
		shopconfig=this.shopconfigService.GetByCustId(goodsorder.getSale_cust_id());
		if(shopconfig==null){
			shopconfig=new Shopconfig();
		}
		//得到店铺字符串
		String area_id = shopconfig.getArea_attr();
		//转化为具体的地区
		sale_area_String = areaFuc.getAreaNameByMap(area_id);		
		//查找出订单与商品的详细信息
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("sale_cust_id", this.session_cust_id);
		}	
		return goUrl("manaorderdetail");
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 27, 2012 11:15:15 AM
	 * @Method Description :查找出订单对应商品的公共方法
	 */
	private void getOrder(Map pageMap){		
		goodsorderList = this.goodsorderService.getList(pageMap);
		//通过解析goodsorderList找出相关的商品信息
		String orderidStr="";
		if(goodsorderList!=null&&goodsorderList.size()>0){
			for(int i=0;i<goodsorderList.size();i++){
				Map listMap=new HashMap();
				listMap=(HashMap)(goodsorderList).get(i);
				if(listMap.get("order_id")!=null){
					orderidStr+=listMap.get("order_id").toString()+",";
				}
			}
		}
		//去掉最后的逗号
		if(orderidStr.indexOf(",")>0){
			int len=orderidStr.lastIndexOf(",");
			orderidStr=orderidStr.substring(0,len);
		}
		Map detailMap=new HashMap();
		detailMap.put("order_id_str", orderidStr);
		detailList=this.orderdetailService.getList(detailMap);
	}
	
	/**
	 * 方法描述：新增订单商品表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(goodsorder);
		if(super.ifvalidatepass){
			return add();
		}
		this.goodsorderService.insert(goodsorder);
		this.addActionMessage("新增订单商品表成功！");
		this.goodsorder = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改订单商品表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(goodsorder);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.goodsorderService.update(goodsorder);
		this.addActionMessage("修改订单商品表成功！");
		return list();
	}
	/**
	 * 方法描述：删除订单商品表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.goodsorder.getOrder_id();
		id = id.replace(" ", "");
		this.goodsorderService.delete(id);
		this.addActionMessage("删除订单商品表成功！");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		getOrStateList();
		getOrPayList();
		getOrSendList();
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("buy_cust_id", this.session_cust_id);
		}
		pageMap=setOrderMap(pageMap);
	    //搜索待处理订单
	    if(daiId!=null && !"".equals(daiId))
	    {
	    	pageMap.put("pay_state", "0");
	    }
	    //通过订单ID搜索
	    if(order_id_s!=null && !"".equals(order_id_s))
	    {
	    	pageMap.put("order_id", order_id_s);
	    }
	    //订单状态搜索
	    if(order_state_s!=null && !"".equals(order_state_s))
	    {
	    	pageMap.put("order_state", order_state_s);
	    	
	    }
	    //付款状态搜索
	    if(pay_state_s!=null && !"".equals(pay_state_s))
	    {
	    	pageMap.put("pay_state", pay_state_s);
	    	
	    }
	    //发货状态搜索
	    if(send_state_s!=null && !"".equals(send_state_s))
	    {
	    	pageMap.put("send_state", send_state_s);
	    	
	    }
	    //商品名称搜索
	    if(goods_name_s!=null && !"".equals(goods_name_s))
	    {	    	
	    	pageMap.put("goods_name", goods_name_s);
	    }
	  
	    if(timeOrder!=null && !"".equals(timeOrder))
	    {
	    	//近一个月订单搜索
	    	if("0".equals(timeOrder))
		    {	    	
		    	pageMap.put("time_order", "0");
		    }
	    	 //一个月前订单搜索
		    if("1".equals(timeOrder))
		    {	    	
		    	pageMap.put("time_order_t", "0");
		    }
	    		    	
	    }
	 	    	    
		//根据页面提交的条件找出信息总数
		int count=this.goodsorderService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		getOrder(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String buyoutmemberlist() throws Exception {
		Map pageMap = new HashMap();
		if (session_cust_id!=null&&!"".equals(session_cust_id)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
	    pageMap.put("order_state","1");
		//根据页面提交的条件找出信息总数
		int count=this.goodsorderService.getCountBuyOut(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		buyoutmemberList = this.goodsorderService.getListBuyOut(pageMap);
		return goUrl("buyoutmemberlist");
	}
	/**
	 * 方法描述：根据主键找出订单商品表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.goodsorder.getOrder_id();
		if(id==null || id.equals("")){
			goodsorder = new Goodsorder();
		}else{
			goodsorder = this.goodsorderService.get(id);
		}
		return goUrl(VIEW);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Mar 20, 2012 10:50:52 AM
	 * @Method Description : 订单查看
	 */
	public String audit(){
		//显示订单详细信息
		String id = this.goodsorder.getOrder_id();
		if(id==null || id.equals("")){
			goodsorder = new Goodsorder();
		}else{
			goodsorder = this.goodsorderService.get(id);
		}
		msgByOrderid();
		return goUrl(AUDIT);
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Apr 26, 2012 21:10:51 PM
	 * @Method Description :四舍五入
	 */
	public double myRound(double d, int n) 
	{
		d = d * Math.pow(10, n);
		d += 0.5d;
		d = (long)d;
		d = d / Math.pow(10d, n);
		return d;
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 27, 2012 3:43:49 PM
	 * @Method Description : 更新运费，保价费，税额
	 */
	public String upPrice(){
		if(oid==null||oid.equals("")){
			return goUrl("manaorder");
		}else{
			goodsorder = this.goodsorderService.get(oid);
		}
		if(goodsorder==null){
			goodsorder=new Goodsorder();
		}
		//处理运费，保价，税额价格数据操作开始
		if(validateUtil.isDouble(up_ship)){
			this.addActionMessage("运费输入格式不正确，修改失败!");
			return manaorderdetail();
		}
		if(validateUtil.isDouble(up_tax)){
			this.addActionMessage("发票税额输入格式不正确，修改失败!");
			return manaorderdetail();
		}
		if(validateUtil.isDouble(up_insured)){
			this.addActionMessage("保价费用输入格式不正确，修改失败!");
			return manaorderdetail();
		}
		String goods_amount="0";
		if(goodsorder.getGoods_amount()!=null&&!goodsorder.getGoods_amount().equals("")){
			goods_amount=goodsorder.getGoods_amount();
		}
		goodsorder.setShip_free(up_ship);
		goodsorder.setTax_invoice(up_tax);
		goodsorder.setInsured(up_insured);	
		float totalcount=0;		
		totalcount=Float.parseFloat(up_ship)+Float.parseFloat(up_tax)+Float.parseFloat(up_insured)+Float.parseFloat(goods_amount);
		totalcount=(float) myRound(totalcount,2);
		goodsorder.setTatal_amount(String.valueOf(totalcount));
		this.goodsorderService.update(goodsorder);
		//重新获取订单的信息
		goodsorder = this.goodsorderService.get(oid);
		//处理运费，保价，税额价格数据操作结束
		msgByOrderid();
		//获取订单详细
		getOrderDetail();
		//获取卖家会员信息
		if(goodsorder!=null&&goodsorder.getSale_cust_id()!=null){
			saleMember=this.memberService.get(goodsorder.getSale_cust_id());
		}	
		//查找出订单与商品的详细信息
		Map pageMap = new HashMap();
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("sale_cust_id", this.session_cust_id);
		}	
		getOrder(pageMap);
		this.addActionMessage("订单修改成功");
		return manaorderdetail();
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 27, 2012 1:18:24 PM
	 * @Method Description :根据订单号获取订单的其它信息
	 */
	public void msgByOrderid(){
		//地区ID转换成名称
		area_name=AreaFuc.getAreaNameByMap(goodsorder.getArea_attr());
		//获取卖家信息
		String sale_cust_id=goodsorder.getSale_cust_id();
		saler=this.memberService.get(sale_cust_id);
		//获取买家信息
		String buy_cust_id=goodsorder.getBuy_cust_id();
		buyer=this.memberService.get(buy_cust_id);
		//获取订单的付款方式
		if(goodsorder.getPay_id()!=null){
			Payment payment=this.paymentService.get(goodsorder.getPay_id());
			if(payment!=null){
				payName=payment.getPay_name();
			}			
		}		
		//获取订单的配送方式
		if(goodsorder.getBuy_mode()!=null){
			Sendmode sendmode=this.sendmodeService.get(goodsorder.getBuy_mode());
			if(sendmode!=null){
				sendModeName=sendmode.getSmode_name();
			}
		}
		//获取订单状态
		orderStateName=getStateName(this.goodsorder.getOrder_state(),"order_state");
		payStateName=getStateName(this.goodsorder.getPay_state(),"pay_state");
		sendStateName=getStateName(this.goodsorder.getSend_state(),"send_state");		
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 19, 2012 4:28:47 PM
	 * @Method Description :根据参数代码和参数值获取参数名称
	 */
	private String getStateName(String key,String valcode){
		String stateName="";
		if(key!=null && !"".equals(key)){			
			Map paraMap=new HashMap();
			paraMap.put("para_value",key);
			paraMap.put("para_code", valcode);
			List list=this.commparaService.getList(paraMap);
			if(list!=null&&list.size()>0){
				HashMap listMap=new HashMap();
				listMap=(HashMap)list.get(0);
				if(listMap.get("para_key")!=null){
					stateName=listMap.get("para_key").toString();
				}
			}
		}	
		return stateName;
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 27, 2012 8:02:52 PM
	 * @Method Description : 更新订单状态方法
	 */
	public String subOrder(){
		Map orderMap=new HashMap();
		orderMap.put("order_state", order_order_state);
		orderMap.put("order_id",oid);
		this.goodsorderService.update(orderMap);
		return manaorderdetail();
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 27, 2012 8:02:52 PM
	 * @Method Description : 更新付款状态方法
	 */
	public String subPay(){
		Map payMap=new HashMap();
		if(!validateUtil.isRequired(order_order_state)&&!validateUtil.isRequired(order_pay_state)&&!validateUtil.isRequired(order_send_state)){
			payMap.put("order_state", order_order_state);
			payMap.put("pay_state", order_pay_state);
			payMap.put("send_state", order_send_state);
			payMap.put("pay_state_pos", "1");
			payMap.put("send_state_pos", "1");
		}else if(!validateUtil.isRequired(order_order_state)&&!validateUtil.isRequired(order_pay_state)){
			payMap.put("order_state", order_order_state);
			payMap.put("pay_state", order_pay_state);
			payMap.put("pay_state_pos", "1");		
		}else{
			payMap.put("pay_state", order_pay_state);
		}
		if(order_pay_state.equals("1")){
			payMap.put("pay_time","1");
		}
		payMap.put("order_id",oid);
		this.goodsorderService.update(payMap);
		return manaorderdetail();
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Apr 27, 2012 8:02:52 PM
	 * @Method Description : 更新发货状态方法
	 */
	public String subSend(){
		Map sendMap=new HashMap();
		if(!validateUtil.isRequired(order_order_state)&&!validateUtil.isRequired(order_pay_state)&&!validateUtil.isRequired(order_send_state)){
			sendMap.put("order_state", order_order_state);
			sendMap.put("pay_state", order_pay_state);
			sendMap.put("send_state", order_send_state);
			sendMap.put("pay_state_pos", "1");
			sendMap.put("send_state_pos", "1");
		}else {
			sendMap.put("send_state", order_send_state);	
		}		
		if(order_send_state.equals("1")){
			sendMap.put("send_time","1");
		}
		sendMap.put("order_id",oid);
		this.goodsorderService.update(sendMap);
		return manaorderdetail();
	}
	
	/**
	 * @author : 林俊钦
	 * @throws Exception 
	 * @date : Apr 27, 2012 10:14:19 PM
	 * @Method Description :买家退货与退款方法
	 */
	public String buyReturnPay() throws Exception{
		Map psMap=new HashMap();
		if(!validateUtil.isRequired(order_order_state)&&!validateUtil.isRequired(order_pay_state)&&!validateUtil.isRequired(order_send_state)){
			psMap.put("order_state", order_order_state);
			psMap.put("pay_state", order_pay_state);
			psMap.put("send_state", order_send_state);
			psMap.put("pay_state_pos", "1");
			psMap.put("send_state_pos", "1");
			psMap.put("order_id",oid);
			this.goodsorderService.update(psMap);
		}
		return list();
	}
	
	
	
	/**
	 * @return the GoodsorderList
	 */
	public List getGoodsorderList() {
		return goodsorderList;
	}
	/**
	 * @param goodsorderList
	 *  the GoodsorderList to set
	 */
	public void setGoodsorderList(List goodsorderList) {
		this.goodsorderList = goodsorderList;
	}
	
	/**
	 * @return the goodsorder
	 */
	public Goodsorder getGoodsorder() {
		return goodsorder;
	}
	/**
	 * @param Goodsorder
	 *            the goodsorder to set
	 */
	public void setGoodsorder(Goodsorder goodsorder) {
		this.goodsorder = goodsorder;
	}
	
	public String getOrder_id_s() {
		return order_id_s;
	}

	public void setOrder_id_s(String order_id_s) {
		this.order_id_s = order_id_s;
	}

	public String getGoods_name_s() {
		return goods_name_s;
	}

	public void setGoods_name_s(String goods_name_s) {
		this.goods_name_s = goods_name_s;
	}

	public String getConsignee_s() {
		return consignee_s;
	}

	public void setConsignee_s(String consignee_s) {
		this.consignee_s = consignee_s;
	}

	public String getGoods_amount_s() {
		return goods_amount_s;
	}

	public void setGoods_amount_s(String goods_amount_s) {
		this.goods_amount_s = goods_amount_s;
	}

	public String getOrder_time_s() {
		return order_time_s;
	}

	public void setOrder_time_s(String order_time_s) {
		this.order_time_s = order_time_s;
	}

	public String getOrder_state_s() {
		return order_state_s;
	}

	public void setOrder_state_s(String order_state_s) {
		this.order_state_s = order_state_s;
	}

	public String getMy_order_s() {
		return my_order_s;
	}

	public void setMy_order_s(String my_order_s) {
		this.my_order_s = my_order_s;
	}
    
	public List getBuyoutmemberList() {
		return buyoutmemberList;
	}

	public void setBuyoutmemberList(List buyoutmemberList) {
		this.buyoutmemberList = buyoutmemberList;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(goodsorder == null){
			goodsorder = new Goodsorder();
		}
		String id = this.goodsorder.getOrder_id();
		if(!this.validateFactory.isDigital(id)){
			goodsorder = this.goodsorderService.get(id);
		}
	}

	public Goodseval getGoodseval() {
		return goodseval;
	}

	public void setGoodseval(Goodseval goodseval) {
		this.goodseval = goodseval;
	}

	public Member getSaleMember() {
		return saleMember;
	}

	public void setSaleMember(Member saleMember) {
		this.saleMember = saleMember;
	}

	public Sellerscore getSellerscore() {
		return sellerscore;
	}

	public void setSellerscore(Sellerscore sellerscore) {
		this.sellerscore = sellerscore;
	}
	public void setOrStateList(List orStateList) {
		this.orStateList = orStateList;
	}
	public String getPay_state_s() {
		return pay_state_s;
	}
	public void setPay_state_s(String pay_state_s) {
		this.pay_state_s = pay_state_s;
	}
	public String getSend_state_s() {
		return send_state_s;
	}
	public void setSend_state_s(String send_state_s) {
		this.send_state_s = send_state_s;
	}
	public String getUp_ship() {
		return up_ship;
	}
	public void setUp_ship(String up_ship) {
		this.up_ship = up_ship;
	}
	public String getUp_tax() {
		return up_tax;
	}
	public void setUp_tax(String up_tax) {
		this.up_tax = up_tax;
	}
	public String getUp_insured() {
		return up_insured;
	}
	public void setUp_insured(String up_insured) {
		this.up_insured = up_insured;
	}

}

