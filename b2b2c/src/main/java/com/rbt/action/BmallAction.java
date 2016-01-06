package com.rbt.action;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.Preparable;
import com.rbt.model.Memberuser;
import com.rbt.service.IMemberuserService;


import com.rbt.function.AreaFuc;
import com.rbt.function.MemberuserFuc;
import com.rbt.model.Busimes;
import com.rbt.model.Buyercoupon;
import com.rbt.model.Goods;
import com.rbt.model.Goodsask;
import com.rbt.model.Goodseval;
import com.rbt.model.Goodsorder;
import com.rbt.model.Member;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberinter;
import com.rbt.model.Orderdetail;

import com.rbt.model.Shopconfig;
import com.rbt.service.IBusimesService;
import com.rbt.service.IBuyercouponService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsaskService;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.ILogsService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberinterService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.ISellerscoreService;
import com.rbt.service.IShopconfigService;

public class BmallAction  extends BaseAction implements Preparable{	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	//定义账号对象
    Memberuser memberuser;
    /*
	 * 业务层接口
	 */
	@Autowired
	public IMemberuserService menberuserService;
    //定义账号字段
	public String user_name="";
	//定义邮箱字段
    public String email="";
    //定义是否设置密保
    public String isque="0";
    //定义密码强度字段
    public String pass_str="";
	//会员设置对象
	public Member member;
	//会员设置业务层接口
	@Autowired
	public IMemberService memberService;
	// 商店设置对象
	public Shopconfig shopconfig;
	// 商店设置业务层接口
	@Autowired
	public IShopconfigService shopconfigService;	
	public AreaFuc areaFuc;
	//定义所在地区
	public String area_String=""; 	
	public List scoreList;
	public List avgscoreList;
	@Autowired
	public ISellerscoreService sellerscoreService;
	//留言
	public Busimes busimes;
	@Autowired
	public IBusimesService busimesService;
	//咨询
	public  Goodsask  goodsask;
	@Autowired
	public IGoodsaskService goodsaskService;
	//今日留言条数
	public int countNow=0;
	//未处理留言条数
	public int notcount=0;
	public String today="1";
	//今日咨询条数
	public int  countSask=0;
	//待处理咨询条数
	public int notSask=0;
	
	//商品
	public Goods goods;
	@Autowired
	public IGoodsService goodsService;
	//待上架的商品
	public int goodsCount;
	//库存不足的商品
	public int stockCount;
	//推荐的商品
	public int labelCount;
	//促销中的商品
	public int lbCount;
	//订单--我是卖家
	public  Goodsorder goodsorder;
	@Autowired
	public IGoodsorderService goodsorderService;	
	//待发货订单
	public int daiorderCount;
	//未处理订单
	public int orderCount;
	//今日订单
	public int todayOrder;
	//待付款订单
	public int payCount;
	/*我是买家*/
	//余额
	public Memberfund  memberfund;
	@Autowired
	public IMemberfundService memberfundService;
	//积分
	public Memberinter memberinter;
	@Autowired
	public IMemberinterService memberinterService;
	//优惠券
	public int couponCount;
	public Buyercoupon buyercoupon;
	@Autowired
	public IBuyercouponService buyercouponService;
	//我是买家-订单提醒-待处理订单
	public int buyerOrder;
	//待评价商品
	public int sevelCount;
	//评价
	public Goodseval  goodseval;
	@Autowired
	public IGoodsevalService goodsevalService;
	//我是买家咨询
	public int skCount;
	//最近一个月的订单量
	public 	Orderdetail orderdetail;
	@Autowired
	public  IOrderdetailService orderdetailService;
	/*
	 * 系统日志业务接口
	 * */
	@Autowired
	public ILogsService logsService;
	//定义系统日志列表
	public List logsList;
	//上次登录时间
	public String login_time="";
	public List  goodsorderList;
	public List detailList;
	/**
	 * @function 功能 商城后台首页
	 * @author 创建人 林俊钦
	 */
	public String index() throws Exception {					
		String cust_id = this.session_cust_id;
		//查找会员名称
		if(cust_id.equals("")){
			return SUCCESS;
		}
		member=this.memberService.get(cust_id);
		if(member==null){
			member=new Member();
		}
		//查找店铺名称、地址、邮箱
		shopconfig=this.shopconfigService.GetByCustId(cust_id);
		if(shopconfig==null){
			getResponse().sendRedirect("/bmall_Shopconfig_bmallactive.action");
		}
		//得到店铺字符串
		String area_id = shopconfig.getArea_attr();
		//转化为具体的地区
		area_String = areaFuc.getAreaNameByMap(area_id);		
		// 获取对店铺动态评分列表			
		Map scoreMap=new HashMap();
		scoreMap.put("cust_id", cust_id);
		scoreList=this.sellerscoreService.getCountList(scoreMap);
		// 取出行业平均的平均值,待做
		Map avgMap=new HashMap();
		avgMap.put("cat_attr", "");
		avgscoreList=null;		
		/*
		 * 店铺提醒
		 * */
		//商品提醒
		//待上架商品		
		Map goodsMap=new HashMap();
		goodsMap.put("cust_id", cust_id);		
		goodsMap.put("up_date","1");
		goodsCount = this.goodsService.getCount(goodsMap);
		
		//库存不足商品
		Map stockMap=new HashMap();
		stockMap.put("cust_id", cust_id);		
		stockMap.put("warn_stock","0");
		stockCount = this.goodsService.getCount(stockMap);
	
		//推荐的商品
		Map labelMap=new HashMap();
		labelMap.put("cust_id", cust_id);		
		labelMap.put("label","c");
		labelCount = this.goodsService.getCount(labelMap);
		
		//促销的商品
		Map lbMap=new HashMap();
		lbMap.put("cust_id", cust_id);		
		lbMap.put("label","f");
		lbCount = this.goodsService.getCount(lbMap);
		
		//订单管理
		//待发货订单
		Map daiMap=new HashMap();
		daiMap.put("sale_cust_id", cust_id);		
		daiMap.put("order_state_r","1");
		daiMap.put("pay_state","1");
		daiMap.put("send_state","0");
		daiorderCount = this.goodsorderService.getCount(daiMap);
		
		//未处理订单
		Map orderMap=new HashMap();
		orderMap.put("sale_cust_id", cust_id);		
		orderMap.put("order_state_w","1");
		orderCount = this.goodsorderService.getCount(orderMap);
		
		//今天订单
		Map orMap=new HashMap();
		orMap.put("sale_cust_id", cust_id);		
		orMap.put("today", this.today);
		todayOrder = this.goodsorderService.getCount(orMap);
		//待付款订单
		Map payMap=new HashMap();
		payMap.put("sale_cust_id", cust_id);		
		payMap.put("pay_state", "0");
		payCount = this.goodsorderService.getCount(payMap);
		
		//事务管理
		//今日留言		
		Map leaveMap=new HashMap();
		leaveMap.put("get_cust_id", cust_id);
		leaveMap.put("today", this.today);
		countNow = this.busimesService.getCount(leaveMap);
		//未处理留言
		Map notmagMap=new HashMap();
		notmagMap.put("get_cust_id", cust_id);		
		notmagMap.put("re_date","0");
		notcount = this.busimesService.getCount(notmagMap);
		//今日咨询
		Map saskMap=new HashMap();
		saskMap.put("cust_id", cust_id);
		saskMap.put("today", this.today);
		countSask = this.goodsaskService.getCount(saskMap);
		//待处理咨询
		Map notSaskMap=new HashMap();
		notSaskMap.put("cust_id", cust_id);		
		notSaskMap.put("re_date","0");
		notSask = this.goodsaskService.getCount(notSaskMap);				
		return SUCCESS;				
	}
	//我是买家
	public String buyer() throws Exception
	{
		String cust_id = this.session_cust_id;
		//查找会员名称
		if(cust_id.equals("")){
			return SUCCESS;
		}
		//查找会员
		member=this.memberService.get(cust_id);
		if(member==null){
			member=new Member();
		}
		//订单提醒
		//待处理订单
		Map buyerOrderMap=new HashMap();
		buyerOrderMap.put("buy_cust_id", cust_id);		
		buyerOrderMap.put("pay_state_r","0");
		buyerOrder = this.goodsorderService.getCount(buyerOrderMap);
		//待评价商品
		Map sevelMap=new HashMap();
		sevelMap.put("user_id", cust_id);		
		sevelMap.put("eval_date","0");
		sevelCount = this.goodsevalService.getCount(sevelMap);
		//已回复咨询
		Map skMap=new HashMap();
		skMap.put("user_id", cust_id);		
		skMap.put("reg","0");
		skCount = this.goodsaskService.getCount(skMap);		
		//余额
		memberfund=this.memberfundService.get(cust_id);		
		//积分		
		memberinter=this.memberinterService.get(cust_id);	
		//优惠券
		Map couponMap=new HashMap();
		couponMap.put("give_user_id", cust_id);
		couponCount = this.buyercouponService.getCount(couponMap);
		
		//一个月内订单
		Map pageMap = new HashMap();
		pageMap.put("buy_cust_id", cust_id);	
		pageMap.put("ortime", "0");
		goodsorderList = this.goodsorderService.getList(pageMap);
		
		//获取上次登录时间或是第一次登录时间
		Map timemap=new HashMap();
		timemap=MemberuserFuc.getlast_loginTime(this.session_user_id);
		if(timemap.get("in_date")!=null){
			login_time=timemap.get("in_date").toString();
		}
		return SUCCESS;	
		
	}
	/**
	 * @function 功能 商城后台首页  账户中心页面
	 * @author 创建人 蔡毅存
	 */
	public String accountCenter() throws Exception {	
		user_name=this.session_user_name;
		//查找会员名称
		if(user_name.equals("")){
			return SUCCESS;
		}
		memberuser=menberuserService.get(this.session_user_id);
		//显示目前使用的邮箱
		if(memberuser.getEmail()!=null){
			email=memberuser.getEmail();
			email=email.substring(0,4) + "****" +email.substring(email.lastIndexOf('@'));
		}
		//修改密码问题状态
		if(memberuser.getPasswd_ques()!=null && !"".equals(memberuser.getPasswd_ques())){
			isque="1";
		}
		//给定密码强度提示
		if(!"".equals(memberuser.getPass_strength())){
			pass_str=memberuser.getPass_strength();
		}
		
		//获取上次登录时间或是第一次登录时间
		Map timemap=new HashMap();
		timemap=MemberuserFuc.getlast_loginTime(this.session_user_id);
		if(timemap.get("in_date")!=null){
			login_time=timemap.get("in_date").toString();
		}
		return SUCCESS;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();

	}

}
