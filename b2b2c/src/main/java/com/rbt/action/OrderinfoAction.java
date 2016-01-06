/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: OrderinfoAction.java 
 */
package com.rbt.action;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.Md5;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Fundhistory;
import com.rbt.model.Member;
import com.rbt.model.Memberfund;
import com.rbt.model.Memberuser;
import com.rbt.model.Orderhistory;
import com.rbt.model.Orderinfo;
import com.rbt.model.Sendmode;
import com.rbt.model.Supply;
import com.rbt.model.Tradecomment;
import com.rbt.service.ICommparaService;
import com.rbt.service.IFundhistoryService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberfundService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IOrderhistoryService;
import com.rbt.service.IOrderinfoService;
import com.rbt.service.ISendmodeService;
import com.rbt.service.ISupplyService;
import com.rbt.service.ITradecommentService;

/**
 * @function 功能 记录平台支付方式信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Oct 25 17:06:38 CST 2011
 */
@Controller
public class OrderinfoAction extends BaseAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	/*
	 * 隐藏字段message默认值
	 */
	private static final String MESSAGE_VALUE = "1";
	/*
	 * 记录平台支付方式信息对象
	 */
	public Orderinfo orderinfo;
	/*
	 * 客户对象
	 */
	public Member member;
	/*
	 * 客户对象
	 */
	public Supply supply;
	/*
	 * 卖家
	 */
	public Member seller;
	/*
	 * 客户对象
	 */
	public Memberuser memberuser;
	
	//配送对象
	public Sendmode sendmode;
	// 订单历史记录对象
	public Orderhistory orderhistory;
	// 评价表对象
	public Tradecomment tradecomment;
	// 用户资金对象
	public Memberfund memberfund;
	// 历史记录列表
	public List orderhistoryList;
	// 资金异动表对象
	public Fundhistory fundhistory;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IOrderinfoService orderinfoService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public ISupplyService supplyService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IOrderhistoryService orderhistoryService;
	@Autowired
	public IMemberfundService memberfundService;
	@Autowired
	public IFundhistoryService fundhistoryService;
	@Autowired
	public ITradecommentService tradecommentService;
	@Autowired
	public ISendmodeService sendmodeService;

	/*
	 * 记录平台支付方式信息集合
	 */
	public List orderinfoList;
	/*
	 * 订单信息集合
	 */
	public List ordercommparaList;
	public List buycommList;
	public List sellcommList;
	/*
	 * 买家卖家用户名称
	 */
	public String custname;
	public String sellername;
	public String username;

	/*
	 * 搜索字段
	 */
	//地区
	public String area_attr_s;
	public String order_id_s;
	public String sellername_s;
	public String buyname_s;
	public String custname_s;
	public String title_s;
	public String order_state_s;
	public String order_type;
	public String starttime_s;
	public String endtime_s;
	public String title;
	/*
	 * 隐藏地区值
	 */
	public String member_orderinfo_id;

	public String carriage;

	public String area_attr;

	public String hidden_up_area_id;

	// 消息
	public String message;
	// 支付密码
	public String passwd;
	public String paypasswd;
	// 资金列表
	public List memberfundList;
	// 总运费
	public double allcarr_fee;
	// 总价格
	public double total_price;
	// 获取评价状态
	public String commentState="";
	// 获取买家评价状态
	public String buycommentState;
	// 获取卖家评价状态
	public String sellcommentState;
	// 判断是卖家还是买家
	public String ordertype;
	// 每个月最多能获得好评的加分数
	public String cfg_com_month;
	
	/**
	 * 方法描述：返回新增订单信息成功
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
		this.orderinfoService.insert(orderinfo);
		this.addActionMessage("新增订单信息成功");
		return INPUT;
	}

	/**
	 * 方法描述：修改订单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		// 用于所属地区的回选开始
		loadArea();		
		if (ValidateUtil.isRequired(area_attr) || hidden_area_value.indexOf("0")>-1) {
			this.addFieldError("area_attr", "所属地区不能为空,请选择");
		}
		this.orderinfo.setArea_attr(area_attr);
		if (ValidateUtil.isRequired(this.orderinfo.getPhone()) && ValidateUtil.isRequired(this.orderinfo.getCell_phone())) {
			this.addFieldError("orderinfo.phone", "电话或手机至少一个不为空");
		}
		if (!ValidateUtil.isRequired(this.orderinfo.getPhone())) {
			if (ValidateUtil.isTelephone(this.orderinfo.getPhone())) {
				this.addFieldError("orderinfo.phone", "电话格式不正确");
			}
		}
		if (!ValidateUtil.isRequired(this.orderinfo.getCell_phone())) {
			if (ValidateUtil.isMobile(this.orderinfo.getCell_phone())) {
				this.addFieldError("orderinfo.cell_phone", "手机格式不正确");
			}
		}
		if (request.getParameter("ordertype") != null) {
			order_type = request.getParameter("ordertype");
		}
		//字段验证
		super.commonValidateField(orderinfo);
		if(super.ifvalidatepass){
			return view();
		}
		this.orderinfoService.update(orderinfo);
		this.addActionMessage("修改订单信息成功");
		return list();
	}

	/**
	 * 方法描述：删除记录平台支付方式信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.orderinfo.getOrder_id();
		id = id.replace(" ", "");
		this.orderinfoService.delete(id);
		this.addActionMessage("删除订单信息成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		// 订单参数下拉列表绑定
		Map map = new HashMap();
		map.put("para_code", "order_type");
		ordercommparaList = this.commparaService.getList(map);
		Map pageMap = new HashMap();
		// 搜索订单id字段
		if (order_id_s != null && !order_id_s.equals("")) {
			pageMap.put("order_id", order_id_s);
		}
		// 搜索标题字段
		if (title_s != null && !title_s.equals("")) {
			pageMap.put("title", title_s);
		}
		// 搜索卖家字段
		if (sellername_s != null && !sellername_s.equals("")) {
			pageMap.put("seller", sellername_s);
		}
		// 搜索买家字段
		if (buyname_s != null && !buyname_s.equals("")) {
			pageMap.put("buyname", buyname_s);
		}
		// 收货人姓名
		if (custname_s != null && !custname_s.equals("")) {
			pageMap.put("consignee", custname_s);
		}
		// 订单状态
		if (order_state_s != null && !order_state_s.equals("")) {
			pageMap.put("order_state", order_state_s);
		}
		// 获取搜索的所属地区
		if (request.getParameter("search_area_attr") != null && !"".equals(request.getParameter("search_area_attr"))) {
			String area_attr = request.getParameter("search_area_attr");
			pageMap.put("area_attr", area_attr);
		}
		if (area_attr_s != null && !area_attr_s.equals("")){
			pageMap.put("area_attr", area_attr_s);
		}
		if (starttime_s != null && !starttime_s.equals(""))
			pageMap.put("starttime", starttime_s);
		if (endtime_s != null && !endtime_s.equals(""))
			pageMap.put("endtime", endtime_s);
		if (request.getParameter("ordertype") != null) {
			order_type = request.getParameter("ordertype");
		}
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if ("buy".equals(order_type)) {
				pageMap.put("cust_id", this.session_cust_id);
			}
			if ("sell".equals(order_type)) {
				pageMap.put("seller_id", this.session_cust_id);
			}
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.orderinfoService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		orderinfoList = this.orderinfoService.getList(pageMap);
		Map comMap = new HashMap();
		Map listMap = new HashMap();
		for (int i = 0; i < orderinfoList.size(); i++) {
			Map orderMap = (HashMap) orderinfoList.get(i);
			// 找出是否已经评价的内容
			String order_id = orderMap.get("order_id").toString();// 获取订单号
			comMap.put("order_id", order_id);
			// 查询交易评价表，是否已评价
			List list = this.tradecommentService.getList(comMap);
			// 找出数据库是否存在相同的两条同一订单号
			if (list.size() == 2) {
				orderMap.put("commentState", "2");// 2代表双方已评
			} else if (list.size() > 0) {// 如果存在该记录则说明该会员已评论
				// 判断数据库评价人的ID是否与session中cust_id一样
				
				listMap = (HashMap) list.get(0);
			    String set_cust_id = listMap.get("set_cust_id").toString();// 获取评价人的cust_id
				if (set_cust_id.equals(this.session_cust_id)) {
					orderMap.put("commentState", "1");
				}else{
					orderMap.put("commentState", "0");
				}
			}
			orderinfoList.set(i, orderMap);
		}
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录平台支付方式信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {		
		getRequest().setCharacterEncoding("UTF-8");
		String id = this.orderinfo.getOrder_id();
		if (id == null || "".equals(id)) {
			return list();
		}
		if (getRequest().getParameter("ordertype") != null) {
			order_type = getRequest().getParameter("ordertype");
		}
		if (orderinfo != null) {
			supply = this.supplyService.get(orderinfo.getSupply_id());
			// 卖家
			seller = this.memberService.get(orderinfo.getSeller_id());
			// 买家
			member = this.memberService.get(orderinfo.getCust_id());
			memberuser = this.memberuserService.get(orderinfo.getUser_id());
			String area_name = "";
			// 找出地区字段的存入隐藏值中
			backArea(orderinfo.getArea_attr());
		    //根据订单ID找出对应的订单历史记录
		    Map map = new HashMap();
			map.put("order_id", id);
			orderhistoryList = this.orderhistoryService.getList(map);
		}
		return goUrl(VIEW);
	}

	/**
	 * @return the OrderinfoList
	 */
	public List getOrderinfoList() {
		return orderinfoList;
	}

	/**
	 * 方法描述：买家付款
	 * 
	 * @return
	 * @throws Exception
	 */
	public String viewpay() throws Exception {
		return INPUT;
	}

	/**
	 * 方法描述：买家付款
	 * 
	 * @return
	 * @throws Exception
	 */
	public String payindex() throws Exception {

		String id = this.orderinfo.getOrder_id();
		if (id == null || "".equals(id)) {
			return list();
		}

		// 识别买家卖家
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("ordertype") != null) {
			order_type = request.getParameter("ordertype");
		}
		// 获取用户资金
		if (memberfund == null) {
			memberfund = new Memberfund();
		}
		memberfund = this.memberfundService.get(this.session_cust_id);

		// 获取商品信息
		allcarr_fee = 0;
		total_price = 0;

		id = id.replace(" ", "");
		orderinfoList = this.orderinfoService.getpayOrderinfoList(id);
		if (orderinfoList != null && orderinfoList.size() > 0) {
			for (int i = 0; i < orderinfoList.size(); i++) {
				// 获取总运费
				HashMap map_car = new HashMap();
				map_car = (HashMap) orderinfoList.get(i);
				allcarr_fee += Double.parseDouble(map_car.get("carriage_fee").toString());
				HashMap map_price = new HashMap();
				map_price = (HashMap) orderinfoList.get(i);
				total_price += Double.parseDouble(map_car.get("total_fee").toString());
			}
		}
		// 总共支付
		//total_price = total_price + allcarr_fee;

		if (ValidateUtil.isRequired(this.message)) {
			this.setMessage(MESSAGE_VALUE);
			return INPUT;
		}

		if (ValidateUtil.isRequired(this.passwd)) {
			this.addFieldError("passwd", "支付密码不能为空");
			return INPUT;
		}
		// 判断输入的密码是否正确
		HashMap map = new HashMap();
		paypasswd = Md5.getMD5(passwd.getBytes());
		map.put("pay_passwd", paypasswd);
		map.put("cust_id", this.session_cust_id);
		memberfundList = this.memberfundService.getList(map);
		if (memberfundList == null || memberfundList.size() == 0) {
			this.addFieldError("passwd", "密码输入不正确");
			return INPUT;
		} else {
			if (memberfund != null && orderinfo != null) {
				if (Double.parseDouble(memberfund.getUse_num()) <= total_price) {
					this.addFieldError("litlemomey", "您资金余额不够，请充值");
					return INPUT;
				} else {
					// 修改资金变动
					String use_num = Double.toString(Double.parseDouble(memberfund.getUse_num()) - total_price);
					memberfund.setUse_num(use_num);
					String freeze = Double.toString(Double.parseDouble(memberfund.getFreeze_num()) + total_price);
					memberfund.setFreeze_num(freeze);
					this.memberfundService.update(memberfund);

					// 批量修改订单状态
					HashMap mapstate = new HashMap();
					mapstate.put("order_state", "1");
					mapstate.put("order_id", id);
					this.orderinfoService.UpdateOrderState(mapstate);

					// 插入资金异动记录
					if (fundhistory == null) {
						fundhistory = new Fundhistory();
					}
					fundhistory.setCust_id(this.session_cust_id);
					fundhistory.setFund_in("0");
					fundhistory.setFund_out(Double.toString(total_price));
					fundhistory.setBalance(memberfund.getUse_num());
					fundhistory.setUser_id(this.session_user_id);
					fundhistory.setReason("订单号 " + id + " 付款");
					this.fundhistoryService.insert(fundhistory);

					String strids[] = id.split(",");
					for (int i = 0; i < strids.length; i++) {
						if (strids[i] != null) {
							String ss = strids[i].toString();
							// 插入订单历史记录
							orderhistory = new Orderhistory();
							if (orderinfo != null) {
								orderhistory.setOrder_id(ss);
							}
							orderhistory.setCust_id(this.session_cust_id);
							orderhistory.setUser_id(this.session_user_id);
							orderhistory.setAction_name("买家付款");
							this.orderhistoryService.insert(orderhistory);
						}
					}

				}
			}
		}
		this.addActionMessage("支付成功");
		return list();

	}

	/**
	 * 方法描述： 取消订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception {
		String id = this.orderinfo.getOrder_id();
		if (id == null || "".equals(id)) {
			return list();
		}
		orderinfo.setOrder_state("4");
		this.orderinfoService.update(orderinfo);

		// 插入订单历史记录
		orderhistory = new Orderhistory();
		if (orderinfo != null) {
			orderhistory.setOrder_id(orderinfo.getOrder_id());
		}
		orderhistory.setCust_id(this.session_cust_id);
		orderhistory.setUser_id(this.session_user_id);
		orderhistory.setAction_name("买家取消订单");
		this.orderhistoryService.insert(orderhistory);

		return list();
	}

	/**
	 * 方法描述： 卖家发货
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delivergoods() throws Exception {
		String id = this.orderinfo.getOrder_id();
		if (id == null || "".equals(id)) {
			return list();
		}
		orderinfo.setOrder_state("2");
		this.orderinfoService.update(orderinfo);
		this.addActionMessage("发货成功");

		// 插入订单历史记录
		orderhistory = new Orderhistory();
		if (orderinfo != null) {
			orderhistory.setOrder_id(orderinfo.getOrder_id());
		}

		orderhistory.setCust_id(this.session_cust_id);
		orderhistory.setUser_id(this.session_user_id);
		orderhistory.setAction_name("卖家发货");
		this.orderhistoryService.insert(orderhistory);
		return list();
	}

	/**
	 * 方法描述： 买家确认收货
	 * 
	 * @return
	 * @throws Exception
	 */
	public String takedeliver() throws Exception {

		String id = this.orderinfo.getOrder_id();
		if (id == null || "".equals(id)) {
			return list();
		}

		// 获取用户资金
		if (memberfund == null) {
			memberfund = new Memberfund();
		}

		String sellerid = "";
		// 获取卖家cust_id
		sellerid = orderinfo.getSeller_id();

		// 修改买家冻结资金
		memberfund = this.memberfundService.get(this.session_cust_id);
		String freeze = Double.toString(Double.parseDouble(memberfund.getFreeze_num()) - Double.parseDouble(orderinfo.getTotal_fee()));
		memberfund.setFreeze_num(freeze);
		this.memberfundService.update(memberfund);

		memberfund = this.memberfundService.get(sellerid);

		// 修改卖家资金变动
		String use_num = Double.toString(Double.parseDouble(memberfund.getUse_num()) + Double.parseDouble(orderinfo.getTotal_fee()));
		memberfund.setUse_num(use_num);
		String fund_num = Double.toString(Double.parseDouble(memberfund.getFund_num()) + Double.parseDouble(orderinfo.getTotal_fee()));
		memberfund.setFund_num(fund_num);
		this.memberfundService.update(memberfund);

		// 插入资金异动记录
		if (fundhistory == null) {
			fundhistory = new Fundhistory();
		}
		fundhistory.setCust_id(sellerid);
		fundhistory.setFund_in(orderinfo.getTotal_fee());
		fundhistory.setFund_out("0");
		fundhistory.setBalance(memberfund.getUse_num());
		fundhistory.setUser_id(this.session_user_id);
		fundhistory.setReason("订单号 " + orderinfo.getOrder_id() + "  确认收货");

		this.fundhistoryService.insert(fundhistory);

		// 设置订单状态
		orderinfo.setOrder_state("3");
		this.orderinfoService.update(orderinfo);
		this.addActionMessage("确认收货");

		// 插入订单历史记录
		orderhistory = new Orderhistory();
		if (orderinfo != null) {
			orderhistory.setOrder_id(orderinfo.getOrder_id());
		}
		orderhistory.setCust_id(this.session_cust_id);
		orderhistory.setUser_id(this.session_user_id);
		orderhistory.setAction_name("买家确认收货");
		this.orderhistoryService.insert(orderhistory);
		return list();
	}

	/**
	 * 方法描述： 查看订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String check() throws Exception {
		String id = this.orderinfo.getOrder_id();
		if (id == null || "".equals(id)) {
			return list();
		}
		
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		//如果不是卖家也不是买家则跳转到列表页
		String ordertype="";
		if(request.getParameter("ordertype")!=null){
			ordertype=request.getParameter("ordertype") ;
		}		
		if(!ordertype.equals("sell") && !ordertype.equals("buy")){
			getResponse().sendRedirect("/member_Orderinfo_list.action");
		}else{
			//判断卖家不能进买家评价页面，买家不能进卖家评价页面
			if(ordertype.equals("sell")){
				if(this.session_cust_id.equals(orderinfo.getCust_id())){
					getResponse().sendRedirect("/member_Orderinfo_list.action?ordertype=buy");
				}
			}else if(ordertype.equals("buy")){
				if(this.session_cust_id.equals(orderinfo.getSeller_id())){
					getResponse().sendRedirect("/member_Orderinfo_list.action?ordertype=sell");
				}
			}
		}		

		if (orderinfo != null) {
			//获取商品信息
			supply = this.supplyService.get(orderinfo.getSupply_id());
			// 卖家
			seller = this.memberService.get(orderinfo.getSeller_id());			
			// 买家
			member = this.memberService.get(orderinfo.getCust_id());
			// 获取商铺信息
			memberuser = this.memberuserService.get(orderinfo.getUser_id());
			//获取配送方式
			if(orderinfo.getSmode_id()!=null && !"".equals(orderinfo.getSmode_id())){
			sendmode = this.sendmodeService.get(orderinfo.getSmode_id());
			}
			// 找出地区字段的存入隐藏值中
			if (orderinfo.getArea_attr() != null) {
				this.setHidden_area_value(orderinfo.getArea_attr());
			}
		}
		HashMap map = new HashMap();
		map.put("order_id", id);
		orderhistoryList = this.orderhistoryService.getList(map);
		// 找出是否已经评价成功
		Map comMap = new HashMap();
		comMap.put("order_id", id);
		// 查询交易评价表，是否已评价
		List list = this.tradecommentService.getList(comMap);
		// 找出数据库是否存在相同的两条同一订单号
		if (list.size() == 2) {
			commentState = "2";// 2代表双方已评
		} else if (list.size() == 1) {// 如果存在该记录则说明有会员已评论
			// 判断数据库评价人的ID是否与session中cust_id一样
			Map listMap = new HashMap();
			listMap = (HashMap) list.get(0);
			String set_cust_id = listMap.get("set_cust_id").toString();// 获取评价人的cust_id
			String com_way = listMap.get("com_way").toString();// 1：买家对卖家 2：卖家对买家
			if (set_cust_id.equals(this.session_cust_id)) {				
				commentState = "1";// 买家已评
			}else{				
				commentState = "0";// 卖家已评
			}
		}else{
			commentState="3";//表示卖家或买家未评
		}
		//获取评价表中的订单号与CUST_ID的数据
		Map commMap=new HashMap();
		Map sellMap=new HashMap();
		commMap.put("order_id",id);
		sellMap.put("order_id",id);
		if(ordertype.equals("sell")){
			commMap.put("get_cust_id",this.session_cust_id);
			sellMap.put("set_cust_id",this.session_cust_id);
			//如果是卖家进入，buycommList获取买家的评价，sellcommList卖家的评价
			buycommList =this.tradecommentService.getList(commMap);
		    sellcommList=this.tradecommentService.getList(sellMap);
		}else if(ordertype.equals("buy")){
			commMap.put("set_cust_id",this.session_cust_id);
			sellMap.put("get_cust_id",this.session_cust_id);
			//如果是买家进入，buycommList获取卖家的评价，sellcommList买家的评价
		    buycommList =this.tradecommentService.getList(sellMap);
		    sellcommList=this.tradecommentService.getList(commMap);
		}
		// 每个月最多能获得好评的分数值
		cfg_com_month = SysconfigFuc.getSysValue("cfg_com_month");		
		return INPUT;
	}

	// Ajax更新运费
	public void updatecarriage() throws Exception {
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = getResponse().getWriter();
		String orderid = "";
		if (request.getParameter("orderid") != null) {
			orderid = request.getParameter("orderid");
		}
		String carriage = "";
		if (request.getParameter("carriage") != null) {
			carriage = request.getParameter("carriage");
		}
		if (!"".equals(orderid)) {
			orderinfo = this.orderinfoService.get(orderid);
		}
		if (!"".equals(carriage)) {
			orderinfo.setCarriage_fee(carriage);
		}
		String all = "";
		if (!("".equals(orderinfo.getGoods_fee()) || "".equals(carriage))) {
			all = Double.toString(Double.parseDouble(orderinfo.getGoods_fee()) + Double.parseDouble(carriage));
			orderinfo.setTotal_fee(all);
		}
		this.orderinfoService.update(orderinfo);
		out.write(all);
	}

	/**
	 * @param orderinfoList
	 *            the OrderinfoList to set
	 */
	public void setOrderinfoList(List orderinfoList) {
		this.orderinfoList = orderinfoList;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrder_id_s() {
		return order_id_s;
	}

	public void setOrder_id_s(String order_id_s) {
		this.order_id_s = order_id_s;
	}

	public String getSellername_s() {
		return sellername_s;
	}

	public void setSellername_s(String sellername_s) {
		this.sellername_s = sellername_s;
	}

	public String getBuyname_s() {
		return buyname_s;
	}

	public void setBuyname_s(String buyname_s) {
		this.buyname_s = buyname_s;
	}

	public String getCustname_s() {
		return custname_s;
	}

	public void setCustname_s(String custname_s) {
		this.custname_s = custname_s;
	}


	public String getStarttime_s() {
		return starttime_s;
	}

	public void setStarttime_s(String starttime_s) {
		this.starttime_s = starttime_s;
	}

	public String getEndtime_s() {
		return endtime_s;
	}

	public void setEndtime_s(String endtime_s) {
		this.endtime_s = endtime_s;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getOrder_state_s() {
		return order_state_s;
	}

	public void setOrder_state_s(String order_state_s) {
		this.order_state_s = order_state_s;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Supply getSupply() {
		return supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
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

	public Member getSeller() {
		return seller;
	}

	public void setSeller(Member seller) {
		this.seller = seller;
	}

	public String getHidden_area_value() {
		return hidden_area_value;
	}

	public void setHidden_area_value(String hidden_area_value) {
		this.hidden_area_value = hidden_area_value;
	}

	public String getMember_orderinfo_id() {
		return member_orderinfo_id;
	}

	public void setMember_orderinfo_id(String member_orderinfo_id) {
		this.member_orderinfo_id = member_orderinfo_id;
	}

	public String getCarriage() {
		return carriage;
	}

	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}

	public String getArea_attr() {
		return area_attr;
	}

	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}

	public String getHidden_up_area_id() {
		return hidden_up_area_id;
	}

	public void setHidden_up_area_id(String hidden_up_area_id) {
		this.hidden_up_area_id = hidden_up_area_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPaypasswd() {
		return paypasswd;
	}

	public void setPaypasswd(String paypasswd) {
		this.paypasswd = paypasswd;
	}

	public Memberfund getMemberfund() {
		return memberfund;
	}

	public void setMemberfund(Memberfund memberfund) {
		this.memberfund = memberfund;
	}

	public double getAllcarr_fee() {
		return allcarr_fee;
	}

	public void setAllcarr_fee(double allcarr_fee) {
		this.allcarr_fee = allcarr_fee;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	/**
	 * @return the orderinfo
	 */
	public Orderinfo getOrderinfo() {
		return orderinfo;
	}

	/**
	 * @param Orderinfo
	 *            the orderinfo to set
	 */
	public void setOrderinfo(Orderinfo orderinfo) {
		this.orderinfo = orderinfo;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if (orderinfo == null) {
			orderinfo = new Orderinfo();
		}
		String id = orderinfo.getOrder_id();
		if (!ValidateUtil.isDigital(id)) {
			orderinfo = this.orderinfoService.get(id);
		}
	}

	public ITradecommentService getTradecommentService() {
		return tradecommentService;
	}

	public void setTradecommentService(ITradecommentService tradecommentService) {
		this.tradecommentService = tradecommentService;
	}
}
