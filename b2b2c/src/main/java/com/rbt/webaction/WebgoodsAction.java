package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.function.AreaFuc;
import com.rbt.function.KeyWordInsertFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.index.SearchIndex;
import com.rbt.model.Area;
import com.rbt.model.Buyeraddr;
import com.rbt.model.Category;
import com.rbt.model.Goods;
import com.rbt.model.Goodsask;
import com.rbt.model.Goodsbrand;
import com.rbt.model.Goodsorder;
import com.rbt.model.Groupgoods;
import com.rbt.model.Member;
import com.rbt.model.Memberuser;
import com.rbt.model.Orderdetail;
import com.rbt.model.Sendmode;
import com.rbt.model.Shiptemplate;
import com.rbt.model.Shopconfig;
import com.rbt.service.IAreasetService;
import com.rbt.model.News;
import com.rbt.service.IAreaService;
import com.rbt.service.IBuyeraddrService;
import com.rbt.service.IAboutusService;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICategoryattrService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsaskService;
import com.rbt.service.IGoodsbrandService;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IGoodsorderService;
import com.rbt.service.IGroupgoodsService;
import com.rbt.service.IInfoattrService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercatService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.INavService;
import com.rbt.service.INewsService;
import com.rbt.service.IOrderdetailService;
import com.rbt.service.IPaymentService;
import com.rbt.service.ISellerscoreService;
import com.rbt.service.ISendmodeService;
import com.rbt.service.IShiptemplateService;
import com.rbt.service.IShopconfigService;
import com.rbt.model.Aboutus;
import java.util.Map;
import java.util.Set;

public class WebgoodsAction  extends WebbaseAction implements Preparable {
	
	/*
	 * 业务层接口
	 * */
	@Autowired
	public IGoodsService goodsService;
	@Autowired
	public IGoodsbrandService goodsbrandService;
	@Autowired
	public ICategoryService categoryService; 
	@Autowired
	public ICategoryattrService categoryattrService;
	@Autowired
	public IBuyeraddrService buyeraddrService;
	@Autowired
	public IPaymentService paymentService;
	@Autowired
	public IGoodsevalService goodsevalService;
	@Autowired
	public IOrderdetailService orderdetailService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public ISellerscoreService sellerscoreService;
	@Autowired
	public IMembercatService membercatService;
	@Autowired
	public IGoodsaskService goodsaskService;
	@Autowired
	public IShiptemplateService shiptemplateService;
	@Autowired
	public IInfoattrService infoattrService;
	

	public JsonUtil jsonUtil;
	public Member member;
	public Memberuser memberuser;
	private AreaFuc areaFuc;
	public List allcateList;
	public List allfirstList;
	public List attrvalueList;
	public List brandList;
	public List recomList;
	public String en;
	public List scoreList;
	public List avgscoreList;
	public List custCatList;
	public List recomGoodsList;
	public String topCat;
	public News news;	
	public List goodsaskList;
	public List attrList;
	//文章列表页 最新活动ID
	public String nid;
	public List  articleList;
	public String num;
	//上篇
	public String oneID = "";
	public String oneTitle = "";
	//下篇
	public String twoID = "";
	public String twoTitle = "";
	public int id1 = 0;
	public int id2 = 0;
	public int ct=0;
	
	
	/*
	 * 接收前台信息
	 * */
	public String gid;
	public String bid;
	public Goods goods;
	public Goodsbrand goodsbrand;
	//商品列表页面用到的字段 开始******************
	public static String catlist_id;
	public Category category;
	//树列表
	public List cattreeList;
	//热门商品列表
	public List hotList;
	StringBuilder sb=new StringBuilder();
	//定义热门商品显示条数
	public static final String HOTLT="4";
	//定义静态变量存放初始分类ID号
	public static String beigincat_id="";
	public static String beiginen_name="";
	//商品列表
	public List goodsList;
	//定义商品的搜索条件
	public static String type="";
	//分类品牌列表
	public List goodsBrandList;
	//定义品搜索条件
	public static String brandid="";
	public static String brandname="";
	//分类属性列表
	public List categoryattrList;
	//定义商品属性筛选条件
	public static StringBuilder attrsb=new StringBuilder();
	StringBuilder sbattr=new StringBuilder();
	public String attr="";
	//定义商品展示样式
	public static String viewtype;
	//相关商品数
	public int count;
	//定义品牌高亮字符串
	public static String brandlight;
	//定义价格排序字段
	public String ordersales;
	//分类列表
	public List categoryList;
	public String caten_name;
	//导航
	public String postName;
	//获取分类名称
	public String catName;
	//获取展示样式
	public static String show="";
	//搜索字段
	public static String selvaluep;
	//品牌搜索
	public static String selvalueb;
	//定义商品分类id串
	public String catstr;
	public String catString;
	//判断分类ID是否一样
	public static String state="";
	//首页链接地址
	public String indexName;
	//商品列表页面用到的字段 结束*******************
	
	
	//订单页面使用的字段  开始*********************
	//收货地址列表
	public List buyeraddrList;
	CategoryFuc categoryFuc;
	//定义收货地址串
	public String addrString;
	public Buyeraddr buyeraddr;
	public String topareaid = SysconfigFuc.getSysValue("cfg_topareaid");
	//定义支付方式列表
	public List paymentList;
	//订单页面使用的字段  结束*********************
	public int evalCount=0;
	public int totalevalCount=0;
	public List saleList;
	public String singleImg;
	public List evalList;
	public List dealList;
	/*
	 * 胡惜坤添加前台首页
	 */
	@Autowired
	public INewsService newsService;
	@Autowired
	public IAdvinfoService advinfoService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IAboutusService aboutusService;
	@Autowired
	public INavService navService;
	public List goodbrandIndexList;
	//商城底部版权信息
	private String cfg_powerby="cfg_powerby";
	//商城ICP备案号
	private String cfg_beian="cfg_beian";
	//前台页面高亮获取代码
	public static String topString="";
	public String sysconfig_cfg_powerby;
	public String sysconfig_cfg_beian;
	public List newsIndexList;
	public List categoryIndexList;
	public List goodsIndexList;
	public List twocategoryIndexList;
	public List threecategoryIndexList;
	public List advinfoIndexList;
	public List navList;
	public List bottomnavList;
	public List commpareaIndexList;
	public List aboutusIndexList;
	public Aboutus aboutus;
	public String aboutus_id;
	public String user_name;
	//商品分类所属模块
	public static final String module_type="goods"; 
	//商城首页放灯片广告位ID
	private  String top_adv_post_id="128";
	StringBuilder postsb;
	//属性分类
	public String cat_name;
	public List getNavList() {
		return navList;
	}
	public void setNavList(List navList) {
		this.navList = navList;
	}
	
	public static String getTopString() {
		return topString;
	}
	public static void setTopString(String topString) {
		WebgoodsAction.topString = topString;
	}
	public Aboutus getAboutus() {
		return aboutus;
	}
	public void setAboutus(Aboutus aboutus) {
		this.aboutus = aboutus;
	}
	//订单信息
	@Autowired
	public ISendmodeService sendmodeService;
	public List sendmodeList;
	//地区运费设置
	@Autowired
	public IAreasetService areasetService;
	public List areasetList;
	@Autowired
	public IShopconfigService shopconfigService;
	public List shopList;
	
	
	/*
	 * 前提订单处理信息
	 */
	
	//收获地址信息
	public String hidden_sname;//收货人名称
	public String hidden_area_value;//收货地址省份
	public String hidden_sdirarea;//收货地址
	public String hidden_scell_phone;//收货人手机
	public String hidden_sphone;//电话号码
	public String hidden_spost;//邮编
	//配送方式以及支付方式
	public String hidden_paytype;//支付方式
	public String hidden_Send_mode;//配送方式
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
	//商品信息
	public String hidden_cust_id_value;//店铺cust_id串
	public String hidden_goods_id_value;//商品ID串
	
	public String hidden_if_insured;//商品是否保价：0：支持保价，1：不支持保价
	
	
	@Autowired
	public IGoodsorderService goodsorderService;
	public List goodsOrderList;
	public Goodsorder goodsorder;
	
	public List orderDetailList;
	public Orderdetail orderdetail;
	
	//订单提交成功的订单号
	public String newOrderIdAttr="";
	//订单提交成功的订单价格
	public String newOrderPriceAttrString="";
	
	//判断买家是否购买自己发布的商品，如果存在，则修改此值为1
	//如此变量值为1，在对应的ftl模版中显示“已清除”提示。
	public String isSelfGoodString = "0";
	
	//订单提交成功的订单列表
	public List orderSuccessList;
	
	/****************团购定义变量开始******************/
	//获取分类的顶级ID
	public String cattopid = SysconfigFuc.getSysValue("cfg_topcatid");
	//定义团购列表
	public List groupgoodsList;
	//定义团购对象
	public Groupgoods groupgoods;
	@Autowired
	public IGroupgoodsService groupgoodsService;
	//定义分类ID
	public String cat_id;
	//价格高亮
	public String hight;
	//价格区间 最低
	public String pricedown;
	//价格区间 最高
	public String priceup;
	//团购详细页面属性定义
	public double saleprice;
	public double groupprice;
	public double pyrprice;
	public String shipprice;
	public List imglist;
	public String lastpic;
	//今日热门团购
	public List hotgroupList;
	//商品属性
	public String good_attr;
	//商品名称
	public String goods_name;
	//现有库存
	public String now_stock;
	//发布商品的用户ID和用户名称
	public String cust_id;
	public String cust_name;
	//是否开启团购地区过滤 0标识开启地区过滤  1则关闭
	public String cfg_area_group = SysconfigFuc.getSysValue("cfg_area_group");
	//定义评论数字段
	public int evalcount;
	//定义商品id号字段
	public String goods_id;
	/****************团购定义变量结束******************/
	//定义地区对象
	public Area area;
	//存放省份地区串
	public String carea_name;
	//存放省份子集地区串
	public String sarea_name;
	//定义省、直辖市地区列表
	public List careaList;
	//定义省、直辖市子集地区列表
	public List sareaList;
	@Autowired
	public IAreaService areaService;
	//定义商品咨询类型列表
	public List commparaList;
	//定义初始化地区
	public String areaipName;
	public String sareaid;
	//定义初始化地区id上级
	public String shipareaid;
	//定义初始化地区id
	public String areaid;
	//定义运送模板对象
	public Shiptemplate shiptemplate;
	//获取地区名称
	public String area_name;
	 //获取国家名称
	public String country;
	// 保留地区串
	public static String area_level;
	// 保留分类串
	public static String cat_level;
	// 地区隐藏域
	public String hidden_area_id;
	// 地区名称
	public String charea_name;
	//获取地区拼音
	public String en_name;
	public List searchList;
	public List areacountryList;
	public List areacharList;
	public List countrycharList;
	public List arealevellist;
	public String getNewOrderIdAttr() {
		return newOrderIdAttr;
	}
	public void setNewOrderIdAttr(String newOrderIdAttr) {
		this.newOrderIdAttr = newOrderIdAttr;
	}
	
	//获取国家和地区
	public void getCountryArea(String areaid){
		area_name = AreaFuc.getAreaNameByMap(areaid);
		//通过areaid获取国家id
		if(cfg_mall_topareaid.equals(Constants.UP_COUNTRY_AREA_ID)||cfg_mall_topareaid.equals(Constants.UP_AREA_ID))
		{
			country=AreaFuc.getTopAreaName(areaid);
		}
		else {
			country=AreaFuc.getFiresAreaName(cfg_mall_topareaid);
		}
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Apr 26, 2012 21:10:51 PM
	 * @Method Description :前台订单-前台页面插入订单
	 */
	public String addGoodsOrder()
	{
	  //获取购买商品的CUST_ID,以逗号隔开
	  if(hidden_cust_id_value!=null&&!"".equals(hidden_cust_id_value)&&readcokes("cart_cookieNum")!=null&&!readcokes("cart_cookieNum").equals(""))
	  {
		  String str_cust_id[]=null;
		  String str_order_id[]=null;
		  String str_order_price[]=null;
		  str_cust_id=hidden_cust_id_value.split(",");
		  for(int i=0;i<str_cust_id.length;i++)
		  {
			 //处理自己不能购买自己商品
			  if(!this.session_cust_id.equals(str_cust_id[i]))
			  {
			    newOrderIdAttr+=insertOrderInfo(str_cust_id[i].toString())+",";
			  }else{
				isSelfGoodString = "1";
			  }
		  }
		  //获取成功定的订单号串
		  if(newOrderIdAttr!=null&&!newOrderIdAttr.equals(""))
		  {
			  newOrderIdAttr=newOrderIdAttr.substring(0, newOrderIdAttr.length()-1);
			  str_order_id=newOrderIdAttr.split(",");
		  }
		  //获取成功订单的价格串
		  if(newOrderPriceAttrString!=null&&!newOrderPriceAttrString.equals(""))
		  {
			  newOrderPriceAttrString=newOrderPriceAttrString.substring(0,newOrderPriceAttrString.length()-1);
			  str_order_price=newOrderPriceAttrString.split(",");
		  }
		  orderSuccessList=new ArrayList();
		  //将获取到的订单号和订单价格加入到列表中，前台订单成功页面绑定信息
		  for(int i=0;i<str_order_id.length;i++)
		  {
			  HashMap oMap=new HashMap ();
			  oMap.put("orderid", str_order_id[i]);
			  oMap.put("order_price", str_order_price[i]);
			  orderSuccessList.add(oMap);
		  }
		return orderSuccessInfo(newOrderIdAttr);
	  }
	  else {
		  //返回首页
		return goUrl("index");
	}
	 
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Apr 26, 2012 21:10:51 PM
	 * @Method Description :前台订单-订单提交成功页面跳转
	 */
	public String orderSuccessInfo(String orderid)
	{
		mallGetRecommendGoods();
		return goUrl("ordersuccess");
	}
	
	/**
	 * @author : 林俊钦
	 * @date : May 9, 2012 1:43:54 PM
	 * @Method Description :列出所有分类页
	 */
	public String allCate(){
		Map firstMap=new HashMap();
		firstMap.put("cat_level","1");
		firstMap.put("module_type", "goods");
		allfirstList=this.categoryService.getList(firstMap);
		
		Map map=new HashMap();
		map.put("module_type", "goods");
		allcateList=this.categoryService.getList(map);
		return goUrl("allCate");
	}

	/**
	 * @author : 林俊钦
	 * @date : May 9, 2012 2:42:56 PM
	 * @Method Description :列出所有的品牌页
	 */
	public String allBrand(){
		Map recomMap=new HashMap();
		recomMap.put("is_recom","1");
		recomList=this.goodsbrandService.getList(recomMap);
		Map catemap=new HashMap();
		catemap.put("module_type", "goods");
		catemap.put("cat_level", "1");
		allcateList=this.categoryService.getList(catemap);
		//取出第一条数据的分类ID
		if(bid==null){
			if(allcateList!=null&&allcateList.size()>0){
				Map hMap=new HashMap();	
				hMap=(HashMap)allcateList.get(0);
				if(hMap.get("cat_id")!=null){
					bid=hMap.get("cat_id").toString();
				}
			}	
		}	
		Map map=new HashMap();		
		if(bid!=null&&!bid.equals("")){
			map.put("goods_attr", bid);
		}
		//地区过滤
		map = getareamap(map);
		brandList=this.goodsbrandService.getList(map);
		return goUrl("allBrand");
	}
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Jun 20, 2012 3:06:09 PM
	 * @Method Description :根据不同的配送方式产生不同的
	 */
	public void modeinsured() throws IOException{
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//获取订单的价格与该订单的配送方式
		String order_price = request.getParameter("gprice");
		String smode_id=request.getParameter("sid");
		Map jsonMap=new HashMap();
		if(smode_id!=null&&!smode_id.equals("")){
			Sendmode smode=this.sendmodeService.get(smode_id);
			double rate=0.00,min_insure=0.00,max_insure=0.00;
			if(smode!=null){
				rate=Double.parseDouble(smode.getRate())*0.01;
				min_insure=Double.parseDouble(smode.getMix_insured());
				max_insure=Double.parseDouble(smode.getMax_insured());
				if(!order_price.equals("")){
					double o_price=Double.parseDouble(order_price);
					if(o_price<min_insure){
						jsonMap.put("jtag", "-1");
						jsonMap.put("jprice", "订单的价格小于当前选择配送方式保价的最低金额,无法保价");
					}else if(o_price>max_insure){
						jsonMap.put("jtag", "1");
						jsonMap.put("jprice", "订单的价格大于当前选择配送方式保价的最高金额,无法保价");
					}else if(o_price>=min_insure && o_price<=max_insure){
						jsonMap.put("jtag", "0");
						//计算出保价费率
						double rate_price=o_price*rate;
						DecimalFormat df=new DecimalFormat(".00");
						String rp=df.format(rate_price);
						jsonMap.put("jprice",rp);
					}
				}
			}
		}
		String jsonStr=JsonUtil.map2json(jsonMap);
		out.write(jsonStr);
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Apr 26, 2012 21:10:51 PM
	 * @Method Description :前台订单-新增订单信息
	 */
	public String insertOrderInfo(String cust_id)
	{
		
		 float allgoodsmoney=0,allsendmomey=0,allmomey=0,allinsuredmomey=0;
		 //计算商品总额
		 allgoodsmoney=AccountAllMoney(cust_id);
		 //订单总额
		 //allmomey=allgoodsmoney+allsendmomey+allinsuredmomey;
		 //newOrderPriceAttrString+=allmomey+",";
		 
		 // 获取系统时间精确到毫秒产生订单编号
		 String orderidofdate = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		 Goodsorder gorder=new Goodsorder();
		 gorder.setOrder_id(orderidofdate);
		 gorder.setAddress(hidden_sdirarea);
		 gorder.setArea_attr(hidden_area_value);
		 gorder.setBank_account(hidden_bankacount);
		 gorder.setBuy_cust_id(this.session_cust_id);
		 gorder.setBuy_mode("0");//团购或秒杀标识 0:正常购买   1:团购   2:秒杀
		 if(hidden_fp_type!=null&&hidden_fp_type.equals(str_fp_type_ordinary)){
		   gorder.setCompany_name(hidden_mcompany_name);
		 }else if(hidden_fp_type!=null&&hidden_fp_type.equals(str_fp_type_increment)){
			  gorder.setCompany_name(hidden_zcompany_name);
		 }
		 gorder.setConsignee(hidden_sname);
		 gorder.setDis_explain("");//优惠说明
		 gorder.setDiscount("0");//优惠折扣
		 gorder.setDiscount_money("0");//优惠折扣金额
		 gorder.setGoods_amount(String.valueOf(allgoodsmoney));//商品总额
		 gorder.setIdent_number(hidden_nasuirenhao);		  
		 gorder.setInter_money("0");//使用积分
		 gorder.setInvoice_content(hidden_fp_content);
		 gorder.setInvoice_top(hidden_fp_title);
		 gorder.setInvoice_type(hidden_fp_type);
		 gorder.setMobile(hidden_scell_phone);
		 gorder.setOpen_bank(hidden_openbank);
		 gorder.setOrder_state("1");//见参数配置表  0:未确认 1：已确认 2:取消 3：无效 4：退货
		 gorder.setPay_id(hidden_paytype);
		 gorder.setPay_state("0");//见参数配置表  0:未付款 1：已付款 2：付款中
		 gorder.setRegis_address(hidden_registeraddr);
		 gorder.setRegis_tel(hidden_registertel);
		 gorder.setSale_cust_id(cust_id);
		 
		 gorder.setSend_state("0");//见参数配置表 0:未发货 1：已发货 2：发货中 3：配货中4：收货确认
		 //订单的保价费用  
    	 if(readcokes("gd_insure_id"+cust_id)!=null)
    	 {
    	  String insprice=readcokes("gd_insure_id"+cust_id).toString();
    	  gorder.setInsured(insprice);
    	 }
    	 //订单的配送费用  
    	 if(readcokes("gd_smode_id"+cust_id)!=null)
    	 {
    	  String shipprice=readcokes("gd_smode_id"+cust_id).toString();
    	  gorder.setShip_free(shipprice);
    	 }
    	 //订单的配送方式id
    	 if(readcokes("smode_type"+cust_id)!=null)
    	 {
    	  String smode_type=readcokes("smode_type"+cust_id).toString();
    	  gorder.setSend_mode(smode_type);
    	 }
    	 //订单总费用
    	 if(readcokes("gd_cust_id"+cust_id)!=null)
    	 {
    		String total_price=readcokes("gd_cust_id"+cust_id).toString();
    		//拼出订单的总额
    		newOrderPriceAttrString+=total_price+",";
    		gorder.setTatal_amount(total_price);
    	 }
    	 //订单备注
    	 if(readcokes("order_comment"+cust_id)!=null)
    	 {
    		String remark=readcokes("order_comment"+cust_id).toString();
    		gorder.setMem_remark(remark);
    	 }
		 gorder.setTax_invoice("0");//发票税额
		 gorder.setTelephone(hidden_sphone);
		 gorder.setZip_code(hidden_spost);
		 gorder.setGive_inter(AccountSendIntegral(cust_id).toString());//赠送积分
		 goodsorderService.insert(gorder);
		 //新增订单详情
		 insertOrderDetailInfo(cust_id,orderidofdate);
		 
		 return orderidofdate;
	}
	
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Apr 26, 2012 21:10:51 PM
	 * @Method Description :前台订单-四舍五入
	 */
	public static double myRound(double d, int n) 
	{
		d = d * Math.pow(10, n);
		d += 0.5d;
		d = (long)d;
		d = d / Math.pow(10d, n);
		return d;
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Apr 26, 2012 21:10:51 PM
	 * @Method Description :前台订单-统计赠送的积分
	 */
	public Integer AccountSendIntegral(String cust_id)
	{
		Integer strIntegral=0,cart_cookieNum=0;
		 if(readcokes("cart_cookieNum")!=null)
		   {
			   cart_cookieNum=Integer.parseInt(readcokes("cart_cookieNum"));
			   for(int i=1;i<=cart_cookieNum;i++)
			   {
				   //根据COOKIES的值统计每个订单的赠送的积分总和
			      String goodsid=readcokes("goods_id"+i);
			      Integer countIntegral=0,gInteger=0,ggoodsnumInteger=0;
			      if(goodsid!=null&&!"".equals(goodsid)&&readcokes("goods_cust_id"+i).equals(cust_id))
			      {
			    	  if(readcokes("goods_give_inter"+i)!=null)
			    	  {
			    		  gInteger=Integer.parseInt(readcokes("goods_give_inter"+i));
			    		  ggoodsnumInteger=Integer.parseInt(readcokes("goods_Num"+i));
			    		  countIntegral=gInteger*ggoodsnumInteger;
			    	  }
			    	  strIntegral=strIntegral+countIntegral;
			      }
			      
			   }
		   }
		
		return strIntegral;
	}
	
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Apr 26, 2012 21:10:51 PM
	 * @Method Description :前台订单-统计商品价格总额
	 */
	public float AccountAllMoney(String cust_id)
	{
	   float strallmoney=0 ,strprice=0;
	   Integer cart_cookieNum=0,strbuynum=0;
	   if(readcokes("cart_cookieNum")!=null)
	   {
		   cart_cookieNum=Integer.parseInt(readcokes("cart_cookieNum"));
		   for(int i=1;i<=cart_cookieNum;i++)
		   {
		      String goodsid=readcokes("goods_id"+i);
		      if(goodsid!=null&&!"".equals(goodsid)&&readcokes("goods_cust_id"+i).equals(cust_id))
		      {
		    	  if(readcokes("goods_price"+i)!=null)
		    	  {
		    		  strprice=Float.parseFloat(readcokes("goods_price"+i));
		    	  }
		    	  if(readcokes("goods_Num"+i)!=null)
		    	  {
		    		  strbuynum=Integer.parseInt(readcokes("goods_Num"+i));
		    	  }
		    	  strallmoney=strallmoney+(strprice*strbuynum);
		      }
		   }
	   }
		return strallmoney;
	}
	
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Apr 26, 2012 21:10:51 PM
	 * @Method Description :前台订单-新增订单详细信息
	 */
	public void insertOrderDetailInfo(String cust_id,String dorderid)
	{
	   Integer cart_cookieNum=0;
	   if(readcokes("cart_cookieNum")!=null)
	   {
		   cart_cookieNum=Integer.parseInt(readcokes("cart_cookieNum"));
		   for(int i=1;i<=cart_cookieNum;i++)
		   {
		      String goodsid=readcokes("goods_id"+i);
		      if(goodsid!=null&&!"".equals(goodsid)&&readcokes("goods_cust_id"+i).equals(cust_id))
		      {
		    	  Orderdetail odtail=new Orderdetail();
		    	  String strgoods_id="",strgoods_attr="",strgoods_price="",strgoods_num="",strgoods_remark="";
		    	  //商品ID
		    	  if(readcokes("goods_id"+i)!=null)
		    	  {
		    		  strgoods_id=readcokes("goods_id"+i).toString();
		    	  }
		    	  //商品属性(未实现)
		    	  if(readcokes("goods_attr"+i)!=null)
		    	  {
		    		  strgoods_attr=readcokes("goods_attr"+i).toString();
		    	  }
		    	  //商品价格
		    	  if(readcokes("goods_price"+i)!=null)
		    	  {
		    		  strgoods_price=readcokes("goods_price"+i).toString();
		    	  }
		    	  //购买商品数量
		    	  if(readcokes("goods_Num"+i)!=null)
		    	  {
		    		  strgoods_num=readcokes("goods_Num"+i).toString();
		    	  }
		    	  //备注(未实现)
		    	  if(readcokes("goods_remark"+i)!=null)
		    	  {
		    		  strgoods_remark=readcokes("goods_remark"+i).toString();
		    	  }
		    	  odtail.setGoods_attr(strgoods_attr);//商品属性
		    	  odtail.setGoods_id(strgoods_id);
		    	  odtail.setOrder_id(dorderid);
		    	  odtail.setOrder_num(strgoods_num);
		    	  odtail.setOrder_price(strgoods_price);
		    	  odtail.setRemark(strgoods_remark);
		    	  orderdetailService.insert(odtail);
		      }
		   }
	   }
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Apr 26, 2012 21:10:51 PM
	 * @Method Description :前台订单-从cookies中读取信息
	 */
	public String readcokes(String cokesname) {
		HttpServletRequest request = getRequest();
		Cookie cokLoginName = this.getCookieByName(request, cokesname);
		String loginName = "";
		if (cokLoginName != null && cokLoginName.getValue() != null) {
			loginName = cokLoginName.getValue();
		}
		return loginName;
	}
	/**
	 * 根据名字获取cookie（接口方法）
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}
	/**
	 * 将cookie封装到Map里面（非接口方法）
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	
	
	
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Apr 26, 2012 21:10:51 PM
	 * @Method Description :前台订单-购物车
	 */
	public String mallcart()
	{
		mallGetRecommendGoods();
		return goUrl("cart");
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Apr 26, 2012 21:10:51 PM
	 * @Method Description :前台订单-读取推荐商品
	 */
	public void mallGetRecommendGoods()
	{
		 Map gMap=new HashMap ();
		 gMap .put("label", "c");
		 gMap .put("info_state", "1");
		 gMap .put("is_del", "1");
		 //地区过滤
		 gMap = getareamap(gMap);
		 goodsList=goodsService.getList(gMap);
	}
	
	/**
	 * 方法描述：文章列表页
	 * @author 陈晓艺
	 * @throws Exception
	 * @date : Apr 20, 2012 4:55:45 PM
	 */
	public String articleList(){ 		
		//获取文章列表--商城公告
		Map catMap=new HashMap ();
		//公告审核通过：1
		catMap.put("info_state","1");
		//商城公告类型：1
		catMap.put("news_type","1");
		//推荐 c
		catMap.put("news_attr","c");
		 //地区过滤
		//catMap = getareamap(catMap);
		// 根据页面提交的条件找出信息总数
		int count = this.newsService.getCount(catMap);
		//分页插件
		catMap = super.pageTool(count,catMap);	
		articleList = this.newsService.getList(catMap);
		
		//商品树的绑定
		Map treemap=new HashMap();
		treemap.put("module_type", module_type);
		cattreeList=categoryService.getList(treemap);

		 return goUrl("articleList");
	}
	
	/**
	 * 方法描述：点击文章列表页跳转到->某条资讯
	 * @author 陈晓艺
	 * @throws Exception
	 * @date : Apr 20, 2012 4:55:45 PM
	 */
	public String articleDetail(){						
		if(ValidateUtil.isDigital(nid)){
			return articleList();
		}
		
		//商品树的绑定
		Map treemap=new HashMap();
		treemap.put("module_type", module_type);
		cattreeList=categoryService.getList(treemap);
		
		if(nid!=null && !nid.equals("")){
			news = this.newsService.get(nid);			
			//获取文章列表--商城公告
			Map newsMap=new HashMap();
			//公告审核通过：1
			newsMap.put("info_state","1");
			//商城公告类型：1
			newsMap.put("news_type","1");
			//推荐 c
			newsMap.put("news_attr","c");
			//地区过滤
			//newsMap = getareamap(newsMap);
			ct = this.newsService.getCount(newsMap);
			articleList = this.newsService.getList(newsMap);
			Integer newsnum=0;
			if(num==null || "".equals(num))
			{
				return articleList();
			}else{
					newsnum = Integer.parseInt(num);
			}
			
			//判断上篇是否越界
			if((newsnum-1)>=0)
			{
				HashMap oneMap=(HashMap) articleList.get(newsnum-1);
				id1=newsnum-1;
				oneID = oneMap.get("news_id").toString();
				oneTitle=oneMap.get("title").toString();
			}
			else{
				return articleList();			
		
			}
			//判断下篇是否越界
			if((newsnum+1)<ct)
			{
				HashMap twoMap=(HashMap) articleList.get(newsnum+1);
				id2=newsnum+1;
				twoID = twoMap.get("news_id").toString();
				twoTitle=twoMap.get("title").toString();
			}
			else{
				return articleList();			
		
			}
			return goUrl("articleDetail");											
		}
		else{
				return articleList();			
		
			}
		
	}
	
	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 胡惜坤
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception 
	{ 
		super.saveRequestParameter();
		//获取头部导航信息
		HashMap navMap=new HashMap ();
		navMap.put("plat_type", "b2c");
		navMap.put("isshow", "0");
		navMap.put("nav_post", "2");
		navList=navService.getList(navMap);
		//商城头部导航高亮控制显示
		HttpServletRequest request = getRequest();
		if(request.getParameter("en_name")==null||request.getParameter("en_name").toString().equals(""))
		{
			topString="/mallindex";//高亮首页
				
		}else if(request.getParameter("en_name")!=null&&!request.getParameter("en_name").equals(""))
		{
			String cat_idString=getcat_id(request.getParameter("en_name"));
			String up_cat_idString=getTopID(cat_idString);
			if(!up_cat_idString.equals("") && !"1111111111".equals(up_cat_idString)){
				category=categoryService.get(up_cat_idString);
				if(category!=null){
					topString=category.getEn_name();
				}
			}
		}
		//获取商城底部信息
		navMap.put("nav_post", "3");
		bottomnavList=navService.getList(navMap);
		//获取商品版权信息
		sysconfig_cfg_powerby=SysconfigFuc.getSysValue(cfg_powerby);
		//获取商城ICP备案信息
		sysconfig_cfg_beian=SysconfigFuc.getSysValue(cfg_beian);
		//获取商城底部帮助中心信息
		gethelplist();
		//加载地区子站
		if("0".equals(cfg_area_shop))
		{
			areaShopValue();
		}
		//初始化StringBuilder
		postsb = new StringBuilder();
	}
	//国家地区切换
	public String countylist() {
		HttpServletRequest request = getRequest();
		if(request.getParameter("type")!=null){
			type=request.getParameter("type");
		}
        String areaid="";
		if(request.getParameter("en_name")!=null){
			areaid=AreaFuc.getparea_id(request.getParameter("en_name"));
			this.getSession().setAttribute("ses_area_id",areaid);
		}else{
			areaid=getareaidvalue();
		}
		en_name = AreaFuc.getAreaEnglishName(areaid);
		getCountryArea(areaid);
		// 设置网页位置
		HashMap map=new HashMap();
		//通过areaid获取国家id
		if(cfg_mall_topareaid.equals(Constants.UP_COUNTRY_AREA_ID)||cfg_mall_topareaid.equals(Constants.UP_AREA_ID))
		{
			String TopAreaId = AreaFuc.getTopAreaId(areaid);
			map.put("up_area_id", TopAreaId);
			areaList=areaService.getAreaCityList(map);
			areacharList=areaService.getCharacterList(map);
		}
		else {
			map.put("up_area_id", cfg_mall_topareaid);
			areaList=areaService.getList(map);
			areacharList=swichWords(areaList);
			
		}
        //查找出国家
		HashMap mapcountry=new HashMap();
		mapcountry.put("up_area_id", Constants.UP_COUNTRY_AREA_ID);
		areacountryList=areaService.getList(mapcountry);
		countrycharList=areaService.getCharacterList(mapcountry);
		return goUrl("mallAreaSelect");
	}
	//根据地区英文名称获取地区索引小写字母，返回List
	public List swichWords(List objlist)
	{
		List retList=new ArrayList();
		if(objlist!=null&&objlist.size()>0)
		{
			String strWord="A,B,C,D,F,G,H,I,J,K,L,N,M,O,P,Q,R,S,T,U,V,W,X,Y,Z";
			String strWords[]=strWord.split(",");
			for(int i=0;i<strWords.length;i++)
			{
				for(int j=0;j<objlist.size();j++)
				{
					String strw="";
					HashMap wordMap=new HashMap ();
					wordMap=(HashMap)objlist.get(j);
					strw=wordMap.get("en_name").toString().substring(0, 1).toUpperCase();
					if(strWords[i].equals(strw))
					{
						HashMap gMap=new HashMap();
						gMap.put("en_name", strWords[i].toLowerCase());
						retList.add(gMap);
						break;
					}
				}
			}
		}
		return retList;
	}
	
	
	// 分类页面
	public String arealist() throws Exception {
		HttpServletRequest request = getRequest();
		area_level = null;
		// 获取地区id
		if(request.getParameter("en_name")!=null){
		area_id=AreaFuc.getparea_id(request.getParameter("en_name"));
		}
		String areaid = getareaidvalue();
		// 获取地区id结果放隐藏域
		hidden_area_id = areaid;
		en_name = AreaFuc.getAreaEnglishName(areaid);
		this.getSession().setAttribute("ses_area_id", areaid);
		//获取国家和地区
		if(this.getSession().getAttribute("ses_area_id")!=null)
		getCountryArea(this.getSession().getAttribute("ses_area_id").toString());
		// 设置网页位置
		super.setPage_position(this.module_type);
		return indexlist();
	}
	/**
	 * 获取帮助中心信息列表
	 * 胡惜坤
	 * @return
	 */
	public void gethelplist()
	{
		//获取商城底部帮助中心信息
		Map aboutusMap=new HashMap ();
		aboutusMap.put("plat_type", "b2c");
		aboutusMap.put("para_code", "help_type");
		aboutusIndexList=aboutusService.getList(aboutusMap);
	}
	/**
	 * 商城首页绑定信息
	 * 胡惜坤
	 * @return
	 */
	public String indexlist()throws Exception 
	{
		HttpServletRequest request = getRequest();
		String gogo_url="";
		//获取品牌信息
		Map goodbrandMap=new HashMap ();
		goodbrandMap.put("info_state", "0");
		//地区过滤
		goodbrandMap = getareamap(goodbrandMap);
		goodbrandIndexList=goodsbrandService.getList(goodbrandMap);
		if(goodbrandIndexList!=null&&goodbrandIndexList.size()>11)
		{
			goodbrandIndexList=goodbrandIndexList.subList(0, 10);
		}
		//获取文章列表--商城公告
		Map newsMap=new HashMap();
		//公告审核通过：1
		newsMap.put("info_state","1");
		//商城公告类型：1
		newsMap.put("news_type","1");
		//推荐 c
		newsMap.put("news_attr","c");
		//地区过滤
		//newsMap = getareamap(newsMap);
		//只显示9条公告
		newsMap.put("start", "0");
		newsMap.put("limit", "9");
		newsIndexList=newsService.getList(newsMap);
		//获取商品分类信息
		Map goodsCategoryMap=new HashMap ();
		goodsCategoryMap.put("module_type", "goods");
		goodsCategoryMap.put("cat_level", "1");
		goodsCategoryMap.put("is_display", "1");
		categoryIndexList=categoryService.getList(goodsCategoryMap);
		//获取商品二级分类信息
		goodsCategoryMap.put("cat_level", "2");
		twocategoryIndexList=categoryService.getList(goodsCategoryMap);
		//获取商品三级分类信息
		goodsCategoryMap.put("cat_level", "3");
		threecategoryIndexList=categoryService.getList(goodsCategoryMap);
		//获取商品信息
		Map goodsMap=new HashMap ();
		goodsMap.put("is_del", "1");
		goodsMap.put("info_state", "1");
		//地区过滤
		goodsMap = getareamap(goodsMap);
		goodsIndexList=goodsService.getList(goodsMap);
		//获取首页广告位信息
		Map advinfoMap=new HashMap ();
		advinfoMap.put("adv_state", "0");
		advinfoMap.put("pos_id", top_adv_post_id);
		//地区过滤
		advinfoMap = getareamap(advinfoMap);
		advinfoIndexList=advinfoService.getList(advinfoMap);
		if(selfTemplate!=null&&!selfTemplate.equals(""))
		{
			gogo_url=selfTemplate;
		}
		else {
			gogo_url="index";
		}
		return goUrl(gogo_url);
	}

	public String areaShopValue()
	{
		String areaid = "";
		if ("0".equals(cfg_area_shop)) {
			areaid = getareaidvalue();
		}
		en_name = AreaFuc.getAreaEnglishName(areaid);
		// 读取下一级地区名称
		if ("0".equals(cfg_area_shop)) {
			HashMap arealevelMap = new HashMap();
			arealevelMap.put("up_area_id", areaid);
			arealevellist = areaService.getList(arealevelMap);
		}
		if ("0".equals(cfg_area_shop)) {
			// 获取地区编号
			if (area_level == null) {
				area_level = areaid;
			}
			//获取国家和地区
			if(this.getSession().getAttribute("ses_area_id")!=null&&!this.getSession().getAttribute("ses_area_id").equals(""))
			{
				getCountryArea(this.getSession().getAttribute("ses_area_id").toString());
			}
			// 获取地区id结果放隐藏域
			hidden_area_id = area_level;
			charea_name = AreaFuc.getAreaNameByMap(areaid);
			// 获取地区的下一级地区名称
			HashMap areaMap = new HashMap();
			areaMap.put("up_area_id", area_level);
			areaList = areaService.getList(areaMap);
		}
		return areaid;
	}
	
	
	// 通过城市标识获取城市名称
	public String getareaidvalue() {

		String areaid = "";
		if (area_id != null && !"".equals(area_id)) {
			areaid = area_id;
			getSession().setAttribute("ses_area_id", area_id);
		} 
		else if (!getSessionFieldValue("ses_area_id").equals("")){
			areaid = getSessionFieldValue("ses_area_id");
		}
		else{
			areaid = AreaFuc.getAreaidByIpaddr(getRequest());
		}
		return areaid;
	}
	//Ajax根据IP获取地区名称
	public void getareaName() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		//获取国家地区
		String areaid=getareaidvalue();
		String county = AreaFuc.getTopAreaName(areaid);
		String name = AreaFuc.getAreaNameByMap(areaid);
		String country_name=AreaFuc.getAreaEnglishName(AreaFuc.getTopAreaId(areaid));
		String area_name=AreaFuc.getAreaEnglishName(areaid);
		PrintWriter out = response.getWriter();
		out.write(county+","+name+","+country_name+","+area_name);
	}
	
	/**
	 * @author : 胡惜坤
	 * @Method Description :商城帮助中心
	 */
	public String  helplistIndex()
	{
		//获取帮助中心分类信息
		HashMap commparaMap=new HashMap();
		commparaMap.put("para_code", "help_type");
		commparaMap.put("enabled", "0");
		commpareaIndexList=commparaService.getList(commparaMap);
		//获取商城底部帮助中心信息
		gethelplist();
		//获取帮助中心详细内容
		aboutus=new Aboutus();
		if(aboutus_id!=null&&!"".equals(aboutus_id))
		{
			aboutus=aboutusService.get(aboutus_id);
		}else if(aboutusIndexList!=null&&aboutusIndexList.size()>0) 
		{	//用于获取帮助中心默认第一条信息
			HashMap  abMap =new HashMap ();
			abMap=(HashMap)aboutusIndexList.get(0);
			if(abMap.get("info_id")!=null)
			{
				aboutus.setInfo_id(abMap.get("info_id").toString());
			}
			if(abMap.get("title")!=null)
			{
				aboutus.setTitle(abMap.get("title").toString());
			}
			if(abMap.get("content")!=null)
			{
				aboutus.setContent(abMap.get("content").toString());
			}
		}
		return goUrl("help");
	}
	/**
	 * @author : 林俊钦
	 * @date : Apr 6, 2012 8:12:34 PM
	 * @Method Description :商品详细页方法
	 */
	@SuppressWarnings("unchecked")
	public String detail(){
		if(gid!=null&&!gid.equals("")){
			//获取商品对象
			goods=this.goodsService.get(gid);
			if(goods==null){
				return null;
			}
			//处理商品图片
			if(goods.getImg_path()!=null){
				String img_path=goods.getImg_path();
				if(img_path.indexOf(",")>-1){
					int len=img_path.indexOf(",");
					singleImg=img_path.substring(0,len);
				}else{
					singleImg=goods.getImg_path();
				}
			}
			String gcat_attr=goods.getCat_attr();
			//获取商城列表页面ID
			if(gcat_attr!=null && !"".equals(gcat_attr)){
			String postStrID =gcat_attr.substring(gcat_attr.lastIndexOf(",")+1,gcat_attr.length());
			postsb.setLength(0);
			getPathUrl(getpostID(postStrID));
			}
			
			//获取商品发布人信息
			member= new Member();
			if(goods.getCust_id()!=null&&!goods.getCust_id().equals("")){
				String cust_id=goods.getCust_id();
				member=this.memberService.get(cust_id);
				//转换地区名称
				if(member!=null && member.getArea_attr()!=null){
					String area_name=areaFuc.getAreaNameByMap(member.getArea_attr());
					member.setArea_attr(area_name);					
				}
				Map userMap=new HashMap();
				userMap.put("user_type","1");
				userMap.put("cust_id",cust_id);
				List list=this.memberuserService.getList(userMap);
				if(list!=null&&list.size()>0){
					 Map listMap=new HashMap();
					 listMap=(HashMap)list.get(0);
					 if(listMap.get("user_name")!=null){
						 String user_name=listMap.get("user_name").toString();
						 memberuser=new Memberuser();
						 memberuser.setUser_name(user_name);
					 }					 
				}
				// 获取对店铺动态评分列表			
				Map scoreMap=new HashMap();
				scoreMap.put("cust_id", cust_id);
				scoreList=this.sellerscoreService.getCountList(scoreMap);
				//获取咨询类型
				Map cmap =new HashMap();
				cmap.put("para_code", "c_type");
				commparaList = commparaService.getList(cmap);
				// 取出行业平均的平均值,待做
				Map avgMap=new HashMap();
				avgMap.put("cat_attr", "");
				avgscoreList=null;
				//获取商品咨询
				Map askMap=new HashMap();
				askMap.put("cust_id", cust_id);
				//根据页面提交的条件找出信息总数
				count = this.goodsaskService.getCount(askMap);
				//分页插件
				askMap = super.pageTool(count, askMap);
				goodsaskList = goodsaskService.getList(askMap);
				//获取商品分类的顶级分类串
				String topCatStr="";
				if(goods.getCat_attr()!=null){
					topCat=goods.getCat_attr();
					int len=topCat.indexOf(",");
					if(len>-1){
						topCat=topCat.substring(0,len);						
					}
				    topCatStr=getStringID(topCat);
					Map catMap=new HashMap();
					if(topCatStr==null||topCatStr.equals(""))
					{
						topCatStr=topCat;
					}
					catMap.put("cat_string", topCatStr);
					cattreeList=this.categoryService.getList(catMap);
				}
				//店铺商品的自定义分类
				Map custcatMap=new HashMap();
				custcatMap.put("cust_id",cust_id);
				custcatMap.put("cat_type","3");
				custCatList=this.membercatService.getList(custcatMap);
				//获取商品的属性列表
				if(goods.getInfoattr_id()!=null && !goods.getInfoattr_id().equals("")){
					Map attrMap = new HashMap();
					attrMap.put("infoattr_id", goods.getInfoattr_id());
					attrMap.put("is_must","1");
					attrMap.put("attr_type","3");
					attrvalueList = this.infoattrService.getDetailList(attrMap);
					System.out.println(attrList);
					attrList =  this.infoattrService.getList(attrMap);
				}
			}
			// 获取商品对应的品牌信息
			if(goods.getBrand_id()!=null&&!goods.getBrand_id().equals("")){
				goodsbrand=this.goodsbrandService.get(goods.getBrand_id());
				Map evalMap=new HashMap();
				evalMap.put("goods_id", gid);
				evalCount=this.goodsevalService.getevalCount(evalMap);
			}
			// 获取热销排行
			Map saleMap=new HashMap();
			saleMap.put("order_saled_num","1");
			saleMap.put("start", 0);
			saleMap.put("limit", 6);
			saleMap.put("info_state",1);
			//地区过滤
			saleMap = getareamap(saleMap);
			saleList=this.goodsService.getList(saleMap);	
			saleList = ToolsFuc.replaceList(saleList);
			
			//获取中国省份和直辖市
			StringBuilder Csb = new StringBuilder();
			Map careamap = new HashMap();
			careamap.put("up_area_id", topareaid);
			careaList = areaService.getList(careamap);
			if(careaList!=null && careaList.size()>0){
				Csb.append("<div id='careadiv'><ul>");
				for(int i=0;i<careaList.size();i++){
					Map cnamemap = new HashMap();
					cnamemap = (HashMap)careaList.get(i);
					String areaName = cnamemap.get("area_name").toString();
					String areaid = cnamemap.get("area_id").toString();
					Csb.append("<li id='"+ areaid +"'  class='firstarea' onclick=\"areafoc('" + areaid + "');\">"+ areaName +"</li>");
				}
				Csb.append("</ul></div>");
			}
			carea_name = Csb.toString();
			
			//页面初始化定位地区
		    areaid = AreaFuc.getAreaidByIpaddr(getRequest());
			area = areaService.get(areaid);
			if(area!=null){
				shipareaid = area.getUp_area_id();
				sareaid=shipareaid;
				areaipName = area.getArea_name();
			}
			
			
			// 获取商品推荐列表
			Map recomMap=new HashMap();
			if(topCat!=null&&!topCat.equals("")){
				recomMap.put("cat_attr",topCat);
			}
			recomMap.put("label","c");
			recomMap.put("start","0");
			recomMap.put("limit","3");
			recomMap.put("info_state",1);
			//地区过滤
			recomMap = getareamap(recomMap);
			recomGoodsList=this.goodsService.getList(recomMap);
			getSendModeInfo();
		}	
		return goUrl("detail");
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jun 8, 2012 5:02:29 PM
	 * @Method Description :ajax计算不同配送方式的运费
	 */
	public void  getshipprice() throws IOException{
		//构造存放配送方式名称，配送方式ID，对应此地区下配送方式价格的List
		List modepriceList=new ArrayList();		
		//AJAX提交对应的地区价格
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String area_id = request.getParameter("id");
		String c_id = request.getParameter("c_id");//获取发布商品的商家cust_id
		String t_id = request.getParameter("t_id");//获取商品对应的运费模板
		String g_id = request.getParameter("g_id");//获取商品的id
		String n_id ="1";
		n_id = request.getParameter("n_id");//需要购买的件数		
		//获取商品对应的模板记录
		Map shipMap=new HashMap();
		shipMap.put("ship_id",t_id);
		shipMap.put("cust_id",c_id);
		List shipList=this.shiptemplateService.getList(shipMap);
		String smode_attr="",v_id="";
		if(shipList!=null&&shipList.size()>0){
			Map listMap=(HashMap)shipList.get(0);
			if(listMap.get("smode_attr")!=null){
				smode_attr=listMap.get("smode_attr").toString();
			}
			if(listMap.get("valuation_mode")!=null){
				v_id=listMap.get("valuation_mode").toString();
			}
		}
		//计算总的数值
		String total_num="";
		if(v_id.equals("1")){//按件数
			total_num=n_id;
		}else if(v_id.equals("2")){//按重量
			Goods gd=this.goodsService.get(g_id);
			if(gd!=null&&gd.getWeight()!=null&&!gd.getWeight().equals("")){
				String weight=gd.getWeight();
				total_num=String.valueOf(((Double.parseDouble(n_id))*(Double.parseDouble(weight))));
			}		
		}else if(v_id.equals("3")){//按体积
			Goods gd=this.goodsService.get(g_id);
			if(gd!=null&&gd.getVolume()!=null&&!gd.getVolume().equals("")){
				String volume=gd.getVolume();
				total_num=String.valueOf(((Double.parseDouble(n_id))*(Double.parseDouble(volume))));
			}	
		}
		//获取区域配送模板记录
		Map areasetMap=new HashMap();
		areasetMap.put("ship_id",t_id);
		List areasetList=this.areasetService.getList(areasetMap);	
		//计算
		modepriceList = cucaltePrice(smode_attr,modepriceList,areasetList,total_num,area_id);
		modepriceList=categoryFuc.replaceList(modepriceList,"");
		String modepricestr=JsonUtil.list2json(modepriceList);
		out.print(modepricestr);
		
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jun 18, 2012 11:05:46 AM
	 * @Method Description : 获取商品的配送方式 
	 */
	public void AjaxGetSendModeSel() throws IOException {
	    HttpServletResponse response = getResponse();
	    HttpServletRequest request = getRequest();
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
	    String goods_ids = request.getParameter("gds");
	    String[] goods_id = goods_ids.split(",");

	    List modeList = new ArrayList();

	    double o_price = 0.0D;
	    for (int i = 0; i < goods_id.length; ++i)
	    {
	      Goods goods = (Goods)this.goodsService.get(goods_id[i]);
	      if ((goods != null) && (goods.getShip_price() != null) && (!(goods.getShip_price().equals("")))) {
		        if (goods.getShip_price().indexOf(",") > -1)
		        {
			          String[] sp = goods.getShip_price().split(",");
			          for (int j = 0; j < sp.length; ++j) {
			              Map listMap = new HashMap();
			              String[] sp_id = sp[j].split("\\|");
			              if (sp_id.length > 1) {
				              String mid = sp_id[0].toString();
				              String mprice = sp_id[1].toString();
			              if (o_price <= Double.parseDouble(mprice)) {
			                  o_price = Double.parseDouble(mprice);
			              }
			              Sendmode sm = (Sendmode)this.sendmodeService.get(mid);
			              if ((sm != null) && (sm.getSmode_name() != null)) {
			                 String mname = sm.getSmode_name();
			                 listMap.put("smode_id", mid);
			                 listMap.put("smode_name", mname);
			              }
			            }
			            modeList.add(listMap);
			          }
		        }else if (goods.getShip_price() != null) {
			          String stem_id = goods.getShip_price();
			          Shiptemplate spp = (Shiptemplate)this.shiptemplateService.get(stem_id);
			          if ((spp != null) && (spp.getSmode_attr() != null)) {
			            String[] smode = spp.getSmode_attr().split(",");
			            for (int j = 0; j < smode.length; ++j) {
			              Map listMap = new HashMap();
			              Sendmode sm = (Sendmode)this.sendmodeService.get(smode[j]);
			              if ((sm != null) && (sm.getSmode_name() != null)) {
			                String mname = sm.getSmode_name();
			                listMap.put("smode_id", smode[j]);
			                listMap.put("smode_name", mname);
			              }
			              modeList.add(listMap);
			            }
			            o_price = 1.0D;
			          }
		        }
	      }

	    }
	    if (o_price == 0.0D) {
	      out.write("0");
	    } else {
	      modeList = removeDuplicateRecords(modeList);
	      String modeStr = JsonUtil.list2json(modeList);
	      out.write(modeStr);
	    }
	  }
	
	  /**
	 * @author : 林俊钦
	 * @date : Jun 18, 2012 10:40:10 AM
	 * @Method Description :去除list中重复的记录
	 */
	public List removeDuplicateRecords(List list){
	    Set set = new HashSet();
	    List newList = new ArrayList();
	    for (Iterator iter = list.iterator(); iter.hasNext(); )
	    {
	      Object element = iter.next();
	      if (!(set.add(element)))
	        continue;
	      newList.add(element);
	    }
	    return newList;
	}
	  
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Jun 12, 2012 3:53:09 PM
	 * @Method Description :订单页面通过选择不同的配送方式计算不同的价格
	 */
	public void priceByGoodsid() throws IOException{
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String gids = request.getParameter("gds");
		String nids = request.getParameter("nid");
		String aid = request.getParameter("aid");
		String sid = request.getParameter("sid");
		
		//对订单拥有的商品最高的配送方式价格
		double highPrice=0;
		if(gids!=null&&!gids.equals("")){
			String[] gid=gids.split(",");
			String[] nid=nids.split(",");
			for(int i=0;i<gid.length;i++){
			    double singlePrice = singlePriceByGoodsId(gid[i],nid[i],aid,sid);
			    if(highPrice<=singlePrice){
			    	highPrice=singlePrice;
			    }
			}
		}
		out.write(String.valueOf(highPrice));
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jun 13, 2012 9:06:47 AM
	 * @Method Description : 计算商品价格
	 */
	private double singlePriceByGoodsId(String gid,String nid,String aid,String sid){
		double singlePirce=0.00;
		Goods goods=this.goodsService.get(gid);
		if(goods!=null&&goods.getIs_ship()!=""&&!goods.getIs_ship().equals("")){
			if(goods.getIs_ship().equals("0")){//免运费
				singlePirce=0.00;
			}else if(goods.getIs_ship().equals("1")){//半自动
				String ship_price=goods.getShip_price();
				if(ship_price.indexOf(sid)>-1){
					int len=ship_price.indexOf(sid);
					ship_price=ship_price.substring(len+11,ship_price.length());
					if(ship_price.indexOf(",")>-1){
						int gPos=ship_price.indexOf(",");
						ship_price=ship_price.substring(0,gPos);
						singlePirce=Double.parseDouble(ship_price);
					}
				}
			}else if(goods.getIs_ship().equals("2")){//全自动
				String t_id="";
				if(goods.getShip_price()!=null&&!goods.getShip_price().equals("")){
					t_id=goods.getShip_price();
				}
				//获取模板的对应的模板的信息
				Shiptemplate stp=this.shiptemplateService.get(t_id);
				String total_num="";
				String v_id="";
				if(stp!=null && stp.getValuation_mode()!=null){
					v_id=stp.getValuation_mode();
				}
				if(v_id.equals("1")){//按件数
					total_num=nid;
				}else if(v_id.equals("2")){//按重量					
					if(goods!=null&&goods.getWeight()!=null&&!goods.getWeight().equals("")){
						String weight=goods.getWeight();
						total_num=String.valueOf(((Double.parseDouble(nid))*(Double.parseDouble(weight))));
					}		
				}else if(v_id.equals("3")){//按体积
					if(goods!=null&&goods.getVolume()!=null&&!goods.getVolume().equals("")){
						String volume=goods.getVolume();
						total_num=String.valueOf(((Double.parseDouble(nid))*(Double.parseDouble(volume))));
					}	
				}else{
					total_num="1";
				}
				
				//获取区域配送模板记录
				Map areasetMap=new HashMap();
				areasetMap.put("ship_id",t_id);
				List areasetList=this.areasetService.getList(areasetMap);	
				Map priceMap=new HashMap();					
				for(int j=0;j<areasetList.size();j++){
					Map areaMap=(HashMap)areasetList.get(j);
					String end_area="",smode_id="";
					if(areaMap.get("end_area")!=null){
						end_area=areaMap.get("end_area").toString();
					}
					if(areaMap.get("smode_id")!=null){
						smode_id=areaMap.get("smode_id").toString();
					}
					//判断是否属于哪种配送方式且价格不能为空且存在该地区的记录时
					if(sid.equals(smode_id)&&singlePirce==0&&!end_area.equals("")&&end_area.indexOf(aid)>-1){
						String first_price="",first_weight="",cont_price="",cont_weight="";
						if(areaMap.get("first_weight")!=null){
							first_weight=areaMap.get("first_weight").toString();
						}
						if(areaMap.get("first_price")!=null){
							first_price=areaMap.get("first_price").toString();
						}							
						
						if(areaMap.get("cont_weight")!=null){
							cont_weight=areaMap.get("cont_weight").toString();
						}
						
						if(areaMap.get("cont_price")!=null){
							cont_price=areaMap.get("cont_price").toString();
						}
													
						if((Double.parseDouble(total_num))<=(Double.parseDouble(first_weight))){
							singlePirce=Double.parseDouble(first_price);
						}else{
							int leave_num=(int) (((Double.parseDouble(total_num))-(Double.parseDouble(first_weight)))/(Double.parseDouble(cont_weight)));
							if((((Double.parseDouble(total_num))-(Double.parseDouble(first_weight)))%(Double.parseDouble(cont_weight)))!=0){
								leave_num+=1;
							}
							singlePirce=Double.parseDouble(first_price)+leave_num*Double.parseDouble(cont_price);
						}
					}
				}
				//当不存在该地区时，选择默认运费
				if(singlePirce==0){
					for(int j=0;j<areasetList.size();j++){
						Map areaMap=(HashMap)areasetList.get(j);
						String default_ship="",smode_id="";
						if(areaMap.get("default_ship")!=null){
							default_ship=areaMap.get("default_ship").toString();
						}
						if(areaMap.get("smode_id")!=null){
							smode_id=areaMap.get("smode_id").toString();
						}
						if(sid.equals(smode_id)&&default_ship.equals("0")){
							if(areaMap.get("smode_id")!=null){
								smode_id=areaMap.get("smode_id").toString();
							}
							String first_price="",first_weight="",cont_price="",cont_weight="";
							if(areaMap.get("first_weight")!=null){
								first_weight=areaMap.get("first_weight").toString();
							}
							if(areaMap.get("first_price")!=null){
								first_price=areaMap.get("first_price").toString();
							}							
							
							if(areaMap.get("cont_weight")!=null){
								cont_weight=areaMap.get("cont_weight").toString();
							}
							
							if(areaMap.get("cont_price")!=null){
								cont_price=areaMap.get("cont_price").toString();
							}
							if((Double.parseDouble(total_num))<=(Double.parseDouble(first_weight))){
								singlePirce=Double.parseDouble(first_price);
							}else{
								int leave_num=(int) (((Double.parseDouble(total_num))-(Double.parseDouble(first_weight)))/(Double.parseDouble(cont_weight)));
								if((((Double.parseDouble(total_num))-(Double.parseDouble(first_weight)))%(Double.parseDouble(cont_weight)))!=0){
									leave_num+=1;
								}
								singlePirce=Double.parseDouble(first_price)+leave_num*Double.parseDouble(cont_price);
							}
						}
					}
				}
			}
		}		
		return singlePirce;
	}
	
	
	
	/**
	 * @author : 林俊钦
	 * @date : Jun 8, 2012 3:49:39 PM
	 * @Method Description :通过计算返回当前对应list下的不同的配送方式下的价格
	 */
	private List cucaltePrice(String smode_attr,List modepriceList,List areasetList,String total_num,String area_id){
		if(total_num.equals("")||total_num.equals("0")){
			total_num="1";
		}
		//匹配相应的配送方式ID找出相应的在此区域下的价格
		if(!smode_attr.equals("")){
			//找出相应的配送方式			
			String[] sa=smode_attr.split(",");	
			for(int i=0;i<sa.length;i++){
				double dealval=0;//得到计算后的价格	
				Map priceMap=new HashMap();					
				for(int j=0;j<areasetList.size();j++){
					Map areaMap=(HashMap)areasetList.get(j);
					String end_area="",smode_id="";
					if(areaMap.get("end_area")!=null){
						end_area=areaMap.get("end_area").toString();
					}
					if(areaMap.get("smode_id")!=null){
						smode_id=areaMap.get("smode_id").toString();
					}
					//判断是否属于哪种配送方式且价格不能为空且存在该地区的记录时
					if(sa[i].equals(smode_id)&&dealval==0&&!end_area.equals("")&&end_area.indexOf(area_id)>-1){
						String first_price="",first_weight="",cont_price="",cont_weight="";
						if(areaMap.get("first_weight")!=null){
							first_weight=areaMap.get("first_weight").toString();
						}
						if(areaMap.get("first_price")!=null){
							first_price=areaMap.get("first_price").toString();
						}							
						
						if(areaMap.get("cont_weight")!=null){
							cont_weight=areaMap.get("cont_weight").toString();
						}
						
						if(areaMap.get("cont_price")!=null){
							cont_price=areaMap.get("cont_price").toString();
						}
													
						if((Double.parseDouble(total_num))<=(Double.parseDouble(first_weight))){
							dealval=Double.parseDouble(first_price);
						}else{
							int leave_num=(int) (((Double.parseDouble(total_num))-(Double.parseDouble(first_weight)))/(Double.parseDouble(cont_weight)));
							if((((Double.parseDouble(total_num))-(Double.parseDouble(first_weight)))%(Double.parseDouble(cont_weight)))!=0){
								leave_num+=1;
							}
							dealval=Double.parseDouble(first_price)+leave_num*Double.parseDouble(cont_price);
						}
						priceMap.put("mode_price",dealval);
						priceMap.put("mode_id", sa[i]);
						priceMap.put("smode_attr", sa[i]);
						modepriceList.add(priceMap);
					}
				}	
				if(dealval==0){
					for(int j=0;j<areasetList.size();j++){
						Map areaMap=(HashMap)areasetList.get(j);
						String default_ship="",smode_id="";
						if(areaMap.get("default_ship")!=null){
							default_ship=areaMap.get("default_ship").toString();
						}
						if(areaMap.get("smode_id")!=null){
							smode_id=areaMap.get("smode_id").toString();
						}
						if(sa[i].equals(smode_id)&&default_ship.equals("0")){
							if(areaMap.get("smode_id")!=null){
								smode_id=areaMap.get("smode_id").toString();
							}
							String first_price="",first_weight="",cont_price="",cont_weight="";
							if(areaMap.get("first_weight")!=null){
								first_weight=areaMap.get("first_weight").toString();
							}
							if(areaMap.get("first_price")!=null){
								first_price=areaMap.get("first_price").toString();
							}							
							
							if(areaMap.get("cont_weight")!=null){
								cont_weight=areaMap.get("cont_weight").toString();
							}
							
							if(areaMap.get("cont_price")!=null){
								cont_price=areaMap.get("cont_price").toString();
							}
							if((Double.parseDouble(total_num))<=(Double.parseDouble(first_weight))){
								dealval=Double.parseDouble(first_price);
							}else{
								int leave_num=(int) (((Double.parseDouble(total_num))-(Double.parseDouble(first_weight)))/(Double.parseDouble(cont_weight)));
								if((((Double.parseDouble(total_num))-(Double.parseDouble(first_weight)))%(Double.parseDouble(cont_weight)))!=0){
									leave_num+=1;
								}
								dealval=Double.parseDouble(first_price)+leave_num*Double.parseDouble(cont_price);
							}
							priceMap.put("mode_price",dealval);
							priceMap.put("mode_id", sa[i]);
							priceMap.put("smode_attr", sa[i]);
							modepriceList.add(priceMap);
						}
					}
				}
			}		
		}
		return modepriceList;
	} 
	
	
	
	public void getshiparea() throws IOException{
		//AJAX获取地区
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String uparea = request.getParameter("shiparea");
		StringBuilder Ssb = new StringBuilder();
		Map sareamap = new HashMap();
		sareamap.put("up_area_id", uparea);
		sareaList = areaService.getList(sareamap);
		if(sareaList!=null && sareaList.size()>0){
			Ssb.append("<div id='sareadiv'><ul>");
			for(int i=0;i<sareaList.size();i++){
				Map cnamemap = new HashMap();
				cnamemap = (HashMap)sareaList.get(i);
				String areaName = cnamemap.get("area_name").toString();
				String areaid = cnamemap.get("area_id").toString();
				String up_areaid = cnamemap.get("up_area_id").toString();
				Ssb.append("<li id='"+ areaid +"'  class='shiparea' onclick=\"shipfoc('"+ areaid +"','" + up_areaid + "','"+ areaName +"');\">"+ areaName +"</li>");
			}
			Ssb.append("</ul></div>");
		}
		out.write(Ssb.toString());
	}
	
	
	public String getcat_id(String en_name){
		String cat_id="";
		Map map=new HashMap();
		map.put("en_name", en_name);
		categoryList=categoryService.getList(map);
		Map mapvalue=new HashMap();
		if(categoryList!=null && categoryList.size()>0){
			mapvalue=(HashMap)categoryList.get(0);
		}
		if(mapvalue.get("cat_id")!=null){
			cat_id=mapvalue.get("cat_id").toString();
		}
		
		return cat_id;
	}
	
	//获取导航
	public void getPathUrl(String postStrID){
		StringBuilder strsb=new StringBuilder();
		if(postStrID!=null && !postStrID.equals("")){
			postStrID=postStrID.substring(0,postStrID.length()-1);
			String str[]=postStrID.split(",");
			strsb.append("<a href=\""+ indexName +"\">首页 ></a>");
			for(int i=str.length-1;i>=0;i--){
				strsb.append("<a href=\"/mall-goodslist-"+ CategoryFuc.getenName(str[i]) +".html\">"+ CategoryFuc.getCateName(str[i]) +"</a>");
				strsb.append(" > ");
			}
		}
		postName=strsb.toString();
	}
	
	public void subgoodsask() throws IOException{
		//AJAX提交商品咨询
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if(this.session_user_id!=null && !"".equals(this.session_user_id)){
			String goodsid= request.getParameter("goodsid");
			//1 商品咨询   咨询会员标识cust_id  咨询人user_id  咨询时间  是否有效 0
			String ccontent = URLDecoder.decode(request.getParameter("ccontent"), "UTF-8");
			String c_type =request.getParameter("c_type");
			Goodsask goodsask=new Goodsask();
			goodsask.setGoods_id(goodsid);
			goodsask.setC_type(c_type);
			goodsask.setCust_id(this.session_cust_id);
			goodsask.setUser_id(this.session_user_id);
			goodsask.setC_content(ccontent);
			goodsask.setIs_enable("0");
			goodsaskService.insert(goodsask);
			out.write("0");
		}else{
			out.write("1");
		}
	}
	
	//商城地区过滤
	public Map getareamap(Map areamap){
		if("0".equals(cfg_area_shop)){
			String area_id_get="";
			//获取地区定位id
			if(this.getSession().getAttribute("ses_area_id")!=null&&!this.getSession().getAttribute("ses_area_id").equals(""))
			{
				area_id_get=this.getSession().getAttribute("ses_area_id").toString();
			}
			else {
				area_id_get= AreaFuc.getAreaidByIpaddr(getRequest());
			}
			areamap.put("area_attr", area_id_get);
		}
		return areamap;
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.UnsupportedEncodingException
	 * @throws java.io.IOException
	 * @date : Apr 26, 2012 21:10:51 PM
	 * @Method Description :前台店铺搜索列表页
	 */
	public String shopList() throws UnsupportedEncodingException
	{
		HttpServletRequest request = getRequest();
		//获取全局搜索关键字
		Map shopmap=new HashMap ();
		String pvalue=request.getParameter("p");
		if(pvalue!=null&&!pvalue.equals("")){
		    selvaluep=URLDecoder.decode(pvalue,"UTF-8");
		}
		else {
			selvaluep="";
		}
		if(selvaluep!=null&&!"".equals(selvaluep))
		{
			shopmap.put("shop_name", selvaluep);
		}
		//搜索高亮店铺
		mallsearchType="shop";
		shopmap.put("info_state", "1");
		//商店是否关闭：0：否，1：是
		shopmap.put("is_colse", "1");
		
		//地区过滤
		shopmap = getareamap(shopmap);
		
		//根据页面提交的条件找出信息总数
		count = this.shopconfigService.getCount(shopmap);
		//分页插件
		shopmap = super.pageTool(count, shopmap);
		shopList=shopconfigService.getList(shopmap);
		shopList = categoryFuc.replaceList(shopList, "area_attr");
		return goUrl("shopList");
	}
	/**
	 * @author : 蔡毅存
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @throws java.io.IOException
	 * @throws java.io.IOException
	 * @date : Apr 9, 2012 10:10:51 PM
	 * @Method Description : b2c商城列表页
	 */
	//商品列表页
	public String list() throws IOException, ParseException{
		
		HttpServletRequest request = getRequest();
		//根据标识符查找分类ID
		if(request.getParameter("en_name")!=null&&!"".equals(request.getParameter("en_name"))){
			caten_name=request.getParameter("en_name");
			catlist_id=getcat_id(caten_name);  
		}
		if(request.getParameter("cat_name")!=null && !"".equals(request.getParameter("cat_name"))){
			cat_name = request.getParameter("cat_name");
		}else{
			cat_name = caten_name;
		}
		//获取分类名称
		catName = CategoryFuc.getCateName(catlist_id);
		//获取商城列表页面ID
		postsb.setLength(0);
		String postStrID = getpostID(catlist_id);
		//获取首页的链接地址
		indexName = SysconfigFuc.getSysValue("cfg_mallindexurl");
		//列表页面导航串
		getPathUrl(postStrID);
		//判断是否是顶级ID串
		if(!"".equals(catlist_id) && catlist_id!=null){
			category= categoryService.get(catlist_id);
			if(category!=null){
				String up_catid = category.getUp_cat_id();
				//如果不是顶级ID串获取顶级ID串赋值给beigincat_id
				if(!"1111111111".equals(up_catid)){
					beigincat_id=getTopID(catlist_id);
					if(!beigincat_id.equals("")){
						category=categoryService.get(beigincat_id);
						//获取顶级ID串的名称
						if(category!=null){
							beiginen_name=category.getEn_name();
						}
					}
				//是顶级ID串对beigincat_id赋值、beiginen_name顶级ID名称赋值,selvaluep全局搜索、selvalueb品牌搜索清空
				}else{
					beigincat_id=catlist_id;
					category=categoryService.get(beigincat_id);
					if(category!=null){
						beiginen_name=category.getEn_name();
					}
					selvaluep=null;
					selvalueb=null;
				}
			}
		}
		
		//获取隐藏域type值
		if(request.getParameter("type")!=null&&!"mr".equals(request.getParameter("type"))){
			type=request.getParameter("type");
		}
		//默认排序对type清空
		if("ms".equals(request.getParameter("type"))){
			type="";
		}
		//根据分类属性查找商品
		//Map catmap=new HashMap();
		//构造list列表搜索条件
		List shList = new ArrayList();
		Sort sort=new  Sort();  
		//按时间降序
		if("time".equals(type)){
			SortField sf=new  SortField("lu_in_date", SortField.STRING,true);//降序
			sort.setSort(new  SortField[]{sf}); 
		}
		//按销售量降序
		if("sales".equals(type)){
			SortField sf=new  SortField("lu_saled_num", SortField.STRING,true);//降序
			sort.setSort(new  SortField[]{sf}); 
		}
		//按价格升序排列
		if("salesup".equals(type)){
			SortField sf=new  SortField("lpad_price", SortField.STRING,false);//升序  
			sort.setSort(new  SortField[]{sf}); 
		}
		//按价格降序排列
		if("salesdown".equals(type)){
			SortField sf=new  SortField("lpad_price", SortField.STRING,true);//降序  
			sort.setSort(new  SortField[]{sf}); 
		}
		//商品展示样式切换
		if(request.getParameter("show")!=null && !"".equals(request.getParameter("show"))){
			show=request.getParameter("show");
			viewtype=show;
		}
		//按品牌筛选
		String gsb=request.getParameter("gsb");
		if(gsb!=null && !"".equals(gsb)){
			gsb = URLDecoder.decode(gsb, "UTF-8");
			brandlight=gsb;
			brandid=gsb.replace("brand", "");
		}
		//根据状态筛选条件
		if("".equals(state)&&!"".equals(catlist_id)&&catlist_id!=null){
			state=catlist_id;
		}else if(state!=null && !state.equals(catlist_id)){
			state=catlist_id;
			attrsb=new StringBuilder();
			brandid="";
			brandlight="";
		}
		// 找通过审核的记录
		shList = normalSearch("info_state","1",shList);
		//搜索条件
		//获取全局搜索关键字
		String pvalue=request.getParameter("p");
		//获取品牌关键字
		String bvalue=request.getParameter("b");
		if(pvalue!=null){
		    selvaluep=URLDecoder.decode(pvalue,"UTF-8");
		    catlist_id=null;
		    selvalueb=null;
		    brandid="";
		    brandlight="";
		    attrsb=new StringBuilder();
		    brandname="";
		    postName="";
		    state="";
		 // 插入关键字表中
			KeyWordInsertFuc.wdInsert(selvaluep, "goods");
		}
		if(bvalue!=null){
		    selvalueb=URLDecoder.decode(bvalue,"UTF-8");
		    catlist_id=null;
		    selvaluep=null;
		    brandid="";
		    brandlight="";
		    attrsb=new StringBuilder();
		    postName="";
		    state="";
		 // 插入关键字表中
			KeyWordInsertFuc.wdInsert(selvaluep, "brand");
		}
		//列表页面品牌筛选
		if(!"".equals(brandid)){
			shList = normalSearch("brand_id",brandid,shList);
		}
		//分类筛选
		shList = normalSearch("cat_attr",catlist_id,shList);
		//全局关键字筛选条件
		if(selvaluep!=null && !"".equals(selvaluep)){
			beiginen_name=null;
			beigincat_id=null;
			shList = normalSearch("goods_name",selvaluep,shList);
		}
		//全局品牌关键字筛选条件
		if(selvalueb!=null && !"".equals(selvalueb)){
			beiginen_name=null;
			beigincat_id=null;
			goodsbrand=goodsbrandService.get(selvalueb);
			//获取品牌名称
			brandname=goodsbrand.getBrand_name();
			shList = normalSearch("brand_id",selvalueb,shList);
		}
		
		//商品属性筛选
		if(!"".equals(request.getParameter("attr")) && request.getParameter("attr")!=null){
			attr=URLDecoder.decode(request.getParameter("attr"), "UTF-8");
		}
		if(!"".equals(attr)){
			//获取商品属性条件ID串
			String attrid=attr.substring(0,attr.indexOf("|"));
			//attr是不限条件关键字 不予加入筛选条件
			if(attrid.indexOf("attr")>-1){
				attrid=attrid.replace("attr", "");
                if(attrsb.indexOf(attrid)>-1)
				attrsb.replace(attrsb.indexOf(attrid), attrsb.indexOf(attrid)+attrid.length(), "##");
                //替换其他筛选条件
			}else if(attrsb.indexOf(attrid)>-1){
				attrsb.replace(attrsb.indexOf(attrid), attrsb.indexOf(attrid)+attr.length(), attr);
				//加入筛选条件
			}else{
				attrsb.append(attr+",");
			}
		}
		//商品属性查询串 拼SQL串
		StringBuilder sbsizeString=new StringBuilder();
		if(attrsb!=null&&!"".equals(attrsb.toString())){
			String attr[]=attrsb.toString().split(",");
			if(attr!=null){
				for(int i=0;i<attr.length;i++){
					if(attr[i].indexOf("##")<=-1){
						sbsizeString.append(" and INSTR(g.size_attr,'" + attr[i] + "') >0");
						String selattr[]=attr[i].split("\\|");
						if(selattr!=null){
								sbattr.append("<a style='cursor:pointer;' onclick=\"attrselect('attr"+ selattr[0] +"|');\">"+ selattr[2] +":" + selattr[1] + "</a>");
						}
					}
				}
				//catmap.put("attrString", sbsizeString.toString());
			}
		}
		//地区过滤
		shList=portalAreaFilterList(shList);
		SearchIndex si=new SearchIndex("goods");
		//根据页面提交的条件找出信息总数
		count =si.getCount(shList);
		//分页插件
		lucenePageTool(count);
		//商品属性筛选条件
		goodsList = si.search(shList, sort , pages_s, pageSize_s); 
		if(goodsList!=null&&goodsList.size()>0){
			for(int i=0;i<goodsList.size();i++){
				Map map=new HashMap();
				map=(HashMap)goodsList.get(i);
					if(map.get("img_path")!=null){
						String img_path=map.get("img_path").toString();
						if(img_path.indexOf(",")>-1){
							img_path=img_path.substring(0,img_path.indexOf(","));
							map.put("img_path", img_path);
						}
				    }
					
			}
		}
		
		//获取所有商品的分类串 
		Map gcatMap= new HashMap();
		List strList = goodsService.getList(gcatMap);
		StringBuilder sb=new StringBuilder();
		if(strList!=null&&strList.size()>0){
			for(int i=0;i<strList.size();i++){
				Map map=new HashMap();
				map=(HashMap)strList.get(i);
				if(map.get("cat_attr")!=null && !"".equals(map.get("cat_attr"))){
					String cat_attr=map.get("cat_attr").toString();
					sb.append(cat_attr+",");
				}
			}
		}
		catstr=sb.toString();
		String[] test1=catstr.split(",");
        ArrayList list=new ArrayList();
        //去掉重复的商品分类串
        for(int i=0;i<test1.length;i++)  
        {
            if(!list.contains(test1[i]))
              list.add(test1[i]);      
        }  
        //把list转化为串
        StringBuilder sblist=new StringBuilder();
        for(int i=0;i<list.size();i++) {
        	sblist.append(list.get(i)+",");
        }
        
		//分类品牌绑定
		if(catlist_id!=null && !"".equals(catlist_id)){
			Map brandmap=new HashMap();
			brandmap.put("goods_attr", catlist_id);
			goodsBrandList=goodsbrandService.getList(brandmap);
		}
		
		//商品属性绑定
		if(cat_name!=null && !"".equals(cat_name)){
			Map goodsattrmap=new HashMap();
			postsb.setLength(0);
			String cat_attr = getcatID(getcat_id(cat_name));
			if(cat_attr!=null && !"".equals(cat_attr)){
				cat_attr = cat_attr.substring(0,cat_attr.length()-1);
			}
			goodsattrmap.put("cat_attrlev",cat_attr);
			categoryattrList=categoryattrService.getList(goodsattrmap);
		}
		
		//树的绑定
		String str="";
		if((selvaluep!=null && !"".equals(selvaluep)) || (selvalueb!=null && !"".equals(selvalueb))){
			beigincat_id="1111111111";
			str = sblist.toString();
		}else{
			str = getStringID(beigincat_id);
		}
	    
		if(!"".equals(str)&&!",".equals(str)){
			String StringID=str.substring(0,str.length()-1);
			Map treemap=new HashMap();
			treemap.put("cat_string", StringID);
			cattreeList=categoryService.getList(treemap);
		}
		//热门商品绑定
		Map hotmap=new HashMap();
		hotmap.put("cat_attr", catlist_id);
		hotmap.put("goods_name", selvaluep);
		hotmap.put("brand_id", selvalueb);
		hotmap.put("info_state", "1");
		hotmap.put("label", "c");
		//DOTO 热门条件
		hotmap.put("start",0);
		hotmap.put("limit",HOTLT);
		//地区过滤
		hotmap = getareamap(hotmap);
		hotList=goodsService.getList(hotmap);
		//截取图片串中的第一张图片
		if(hotList!=null&&hotList.size()>0){
			for(int i=0;i<hotList.size();i++){
				Map imgmap=new HashMap();
				imgmap=(HashMap)hotList.get(i);
					if(imgmap.get("img_path")!=null){
						String img_path=imgmap.get("img_path").toString();
						if(img_path.indexOf(",")>-1){
							img_path=img_path.substring(0,img_path.indexOf(","));
							imgmap.put("img_path", img_path);
						}
				    }
			}
		}
		
		return goUrl("goodsList");
	}
	
	/**
	 * @author : 蔡毅存
	 * @throws java.io.IOException
	 * @date : Apr 9, 2012 10:10:51 PM
	 * @Method Description : 根据分类ID递归查找分类ID的所有下一级ID串
	 */
	//递归查找指定分类ID的所有下一级ID串
	public String getStringID(String id){
		Map treemap=new HashMap();
		treemap.put("up_cat_id", id);
		cattreeList=categoryService.getList(treemap);	
		if(cattreeList!=null && cattreeList.size()>0){
			for(Iterator it = cattreeList.iterator();it.hasNext();){
				HashMap cMap = (HashMap)it.next();
				String cat_id = "";
				if(cMap.get("cat_id")!=null){ 
				cat_id = cMap.get("cat_id").toString(); 
				sb.append(cat_id+",");
				getStringID(cat_id);
				}
			}
		}
		String catestr="";
		if(sb.toString()!=null&&sb.toString().length()>1){
			catestr=sb.toString().substring(0,sb.toString().length()-1);
		}
		return catestr;
	}
	
	//递归查找指定分类ID的顶级ID
	public String getTopID(String id){
		if(id == null || id.equals("")) return "";
		category=categoryService.get(id);	
		String up_id = "";
		if(category!=null&&!"1111111111".equals(category.getUp_cat_id())){ 
		   up_id = category.getUp_cat_id();
		   getTopID(up_id);
		}else if("1111111111".equals(category.getUp_cat_id())){
		   cat_id=category.getCat_id();
		}
		return cat_id;
	}
	
	
	
	//递归查找 指定分类ID向顶级ID查找拼成ID串 导航
	public String getpostID(String id){
		if(id == null || id.equals("")) return "";
		category=categoryService.get(id);	
		String up_id = "";
		postsb.append(id+",");
		if(category!=null&&!"1111111111".equals(category.getUp_cat_id())){ 
		   up_id = category.getUp_cat_id();
		   getpostID(up_id);
		}
      return postsb.toString();
	}
	
	//递归查找 指定分类ID向顶级ID查找拼成ID串 分类属性
	public String getcatID(String id){
		if(id == null || id.equals("")) return "";
		category=categoryService.get(id);	
		String up_id = "";
		if(category!=null&&!"1111111111".equals(category.getUp_cat_id())){ 
		   up_id = category.getUp_cat_id();
		   getpostID(up_id);
		}
		postsb.append(id+",");
      return postsb.toString();
	}
	/**
	 * @author : 蔡毅存
	 * @throws java.io.IOException
	 * @throws java.io.IOException
	 * @date : Apr 11, 2012 16:10:51 PM
	 * @Method Description : 提交订单页面
	 */
	public String orderinfo() throws IOException{
		//获取登陆用户的cust_id、user_id
		String custid=this.session_cust_id;
		String userid=this.session_user_id;
		if(custid!=null&&!"".equals(custid))
		{
			Map map=new HashMap();
			map.put("cust_id", custid);
			buyeraddrList=buyeraddrService.getBuyeraddrList(map);
			Map onemap=new HashMap();
			if(buyeraddrList!=null && buyeraddrList.size() > 0){
				onemap=(HashMap)buyeraddrList.get(0);
			}
			if(onemap.get("addr_id")!=null){
				String addr_id=onemap.get("addr_id").toString();
			    if(addr_id!=null && !addr_id.equals("")){
			    	buyeraddr=buyeraddrService.getBuyeraddrByPk(addr_id);
			    	String areaattr="";
			        areaattr=AreaFuc.getAreaNameByMap(buyeraddr.getArea_attr());
			    	buyeraddr.setArea_attr(areaattr);
			    }
			}
			//获取支付方式
			Map paymap=new HashMap();
			paymentList=paymentService.getList(paymap);
			//获取配送方式
			getSendModeInfo();
			return goUrl("orderinfo");
		}
		else
		{
			this.getResponse().sendRedirect("/signin.html?loc=/mall/goods!orderinfo.action");
			return goUrl("signin");
		}
		
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Apr 11, 2012 16:10:51 PM
	 * @Method Description : 更多品牌
	 */
	public String brandInfo()
	{
		//获取品牌信息
		Map goodbrandMap=new HashMap ();
		goodbrandMap.put("info_state", "0");
		//地区过滤
		goodbrandMap = getareamap(goodbrandMap);
		goodbrandIndexList=goodsbrandService.getList(goodbrandMap);
		HashMap gcatMap=new HashMap ();
		gcatMap.put("cat_level", "1");
		gcatMap.put("module_type", "goods");
		categoryIndexList=categoryService.getList(gcatMap);
		return goUrl("brand");
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Apr 11, 2012 16:10:51 PM
	 * @Method Description : 获取配送方式
	 */
	public void getSendModeInfo()
	{
		HashMap sendmodeMap=new HashMap ();
		sendmodeList=sendmodeService.getList(sendmodeMap);
		
	}

	
	/**
	 * @author : 蔡毅存
	 * @throws java.io.IOException
	 * @throws java.io.IOException
	 * @date : Apr 11, 2012 16:10:51 PM
	 * @Method Description : 收货人信息修改
	 */
	public void updateaddr() throws IOException{
		//Ajax操作收货地址
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//根据addr_id号删除收货地址
		if(!"".equals(request.getParameter("addr_id"))){
			String addrid=request.getParameter("addr_id");
			buyeraddrService.deleteBuyeraddr(addrid);
		}
		//把保存到收货地址的信息拼串
		StringBuilder addsb=new StringBuilder();
		
		//判断收货地址个数是否超过限定条数
		Map map=new HashMap();
		map.put("cust_id", this .session_cust_id);
		count = this.buyeraddrService.getBuyeraddrCount(map);
		int addcount=Integer.parseInt(SysconfigFuc.getSysValue("cfg_maxaddressnumber"));
		
		
		
		//插入收货地址
		if(!"".equals(request.getParameter("instr")) && count<addcount){
			Buyeraddr buyeraddr=new Buyeraddr();
			String insertValue=URLDecoder.decode(request.getParameter("instr"), "UTF-8");
			String inst[]=insertValue.split("\\|");
			buyeraddr.setCust_id(this.session_cust_id);
			buyeraddr.setUser_id(this.session_user_id);
			buyeraddr.setCust_name(inst[0]);
			//去掉逗号
			String areaattr=inst[1];
			if(areaattr!=null&&areaattr.length()>1){
				areaattr=areaattr.substring(0, inst[1].length()-1);
			}
			//判断插入的收货地址是否已经存在
			Map ismap=new HashMap();
			ismap.put("cust_id", this .session_cust_id);
			ismap.put("cust_name",inst[0]);
			ismap.put("area_attr", areaattr);
			ismap.put("address", inst[2]);
			ismap.put("cell_phone", inst[3]);
			ismap.put("phone", inst[4]);
			ismap.put("post_code", inst[5]);
			List buyaddrList=buyeraddrService.getBuyeraddrList(ismap);
			if(buyaddrList==null || buyaddrList.size()==0){
				buyeraddr.setArea_attr(areaattr);
				buyeraddr.setAddress(inst[2]);
				buyeraddr.setCell_phone(inst[3]);
				buyeraddr.setPhone(inst[4]);
				buyeraddr.setPost_code(inst[5]);
				buyeraddrService.insertBuyeraddr(buyeraddr);
				addsb.append("0,");
			}else{
				addsb.append("2,");
			}
		}else{
			addsb.append("1,");
		}
		
		//读取收货地址串
		buyeraddrList=buyeraddrService.getBuyeraddrList(map);
		buyeraddrList=CategoryFuc.replaceList(buyeraddrList, "");
		if(buyeraddrList!=null&&buyeraddrList.size()>0){
			for(Iterator it = buyeraddrList.iterator();it.hasNext();){
				HashMap cMap = (HashMap)it.next();
				String addr_id = "";String cust_name="";String area_attr="";String address="";
				if(cMap.get("addr_id")!=null){ 
					addr_id = cMap.get("addr_id").toString(); 
				}
				if(cMap.get("cust_name")!=null){
					cust_name=cMap.get("cust_name").toString();
				}
				if(cMap.get("area_attr")!=null){
					area_attr=cMap.get("area_attr").toString();
				}
				if(cMap.get("address")!=null){
					address=cMap.get("address").toString();
				}
			    addsb.append(" <p class='f_left'>" +
			    		"<input type=\"radio\" id=\""+ addr_id +"\" onclick=\"addrselect('"+ addr_id +"');\"  class=\"addrgroup\" name=\"group\"/>"+ cust_name +" " +
			    		"<span class=\"bule\"> "+area_attr+address+" </span></p>" +
			    		"<p class='f_right'><a style=\"cursor:pointer;\" onclick=\"buyeraddr('address"+ addr_id +"');\" class=\"acle\">[删除]</a></p>" +
			    		" <div class='clear'></div>");
			}
		}
		addrString=addsb.toString();
		out.write(addrString); 
	}
	

	
	/**
	 * @author : 蔡毅存
	 * @throws java.io.IOException
	 * @throws java.io.IOException
	 * @date : Apr 11, 2012 16:10:51 PM
	 * @Method Description : 收货人信息修改
	 */
	public void selectaddr() throws IOException{
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		buyeraddr=buyeraddrService.getBuyeraddrByPk(id);
		StringBuilder addrbs=new StringBuilder();
		if(buyeraddr!=null){
			addrbs.append(buyeraddr.getAddr_id()+"|");
			addrbs.append(buyeraddr.getCust_name()+"|");
			addrbs.append(topareaid + "," + buyeraddr.getArea_attr().replace(" ", "")+"|");
			addrbs.append(buyeraddr.getAddress()+"|");
			addrbs.append(buyeraddr.getCell_phone()+"|");
			addrbs.append(buyeraddr.getPhone()+"|");
			addrbs.append(buyeraddr.getPost_code()+"|");
		}
		out.write(addrbs.toString()); 
	}
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Apr 9, 2012 1:17:51 PM
	 * @Method Description : 查找成功交易的商品
	 */
	public void dealTrade() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//成功交易的商品
		Map dealMap=new HashMap();
		if(gid!=null&&!gid.equals("")){
			dealMap.put("goods_id", gid);
		}
		dealMap=ajaxMap(dealMap);
		int totalNum=this.orderdetailService.getdetailCount(dealMap);
		dealList=this.orderdetailService.getdetailList(dealMap);
		String jsonStr=pageList(dealList,totalNum);
		
		PrintWriter out = response.getWriter();
		//如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);
	}	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Apr 12, 2012 10:32:42 AM
	 * @Method Description :查找商品交易评价记录
	 */
	public void evalComment() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//成功交易的商品
		Map evalMap=new HashMap();
		if(gid!=null&&!gid.equals("")){
			evalMap.put("goods_id", gid);
		}
		if(en!=null&&!en.equals("")){
			evalMap.put("ismentnull",en);
		}
		evalMap=ajaxMap(evalMap);
		int totalNum=this.goodsevalService.getCount(evalMap);
		List list=this.goodsevalService.getList(evalMap);
		String jsonStr=pageList(list,totalNum);
		PrintWriter out = response.getWriter();
		//如果列表存在数据则输出，否则则输出空
		out.write(jsonStr);		
	}
	
	/**
	 * @author : 蔡毅存
	 * @throws java.io.IOException
	 * @date : 5:22, 2012 10:32:42 AM
	 * @Method Description :团购列表页面
	 */
	public String groupList() throws IOException{
		//绑定团购分类
		Map cmap =new HashMap();
		cmap.put("up_cat_id", cattopid);
		cmap.put("module_type", "goods");
		categoryList = categoryService.getList(cmap);
		
		//获取文章列表--团购公告
		Map newsMap=new HashMap();
		//公告审核通过：1
		newsMap.put("info_state","1");
		//团购公告类型：2
		newsMap.put("news_type","2");
		//推荐 c
		newsMap.put("news_attr","c");
		//地区过滤
		//newsMap = getareamap(newsMap);
		//只显示8条公告
		newsMap.put("start", "0");
		newsMap.put("limit", "8");
		newsIndexList=newsService.getList(newsMap);
		
		HttpServletRequest request=getRequest();
		cat_id= request.getParameter("cat_id");
		//绑定产品
		Map promap = new HashMap();
		if(cat_id!=null && !"".equals(cat_id)){
			promap.put("cat_attr", cat_id);
		}
		//1标识审核通过
		promap.put("info_state", "1");
		//价格区间
		hight = request.getParameter("hight");
		//价格区间 最低
		pricedown = request.getParameter("pricedown");
		//价格区间 最高
		priceup = request.getParameter("priceup");
		if(priceup!=null && !"".equals(priceup)){
			promap.put("priceup" , priceup);
		}
		if(pricedown!=null && !"".equals(pricedown)){
			promap.put("pricedown" , pricedown);
		}
		//地区过滤
		promap = getareamap(promap);
		// 根据页面提交的条件找出信息总数
		int count = this.groupgoodsService.getCount(promap);
		//分页插件
		promap = super.pageTool(count,promap);	
		groupgoodsList = groupgoodsService.getList(promap);
		groupgoodsList = getList(groupgoodsList);
		gethotgroup();
		return goUrl("groupgoodsList");
	}
	
	//过滤多张图片串
	public List getList(List list){
		if(list!=null&&list.size()>0){
			String img="";
		    for(int i=0;i<list.size();i++){
		    	HashMap mapimg = (HashMap)list.get(i);
				if(mapimg.get("group_img")!=null){
				    String imgpath=mapimg.get("group_img").toString();
				    if(imgpath.indexOf(",")!=-1){
				    	//获取字符串最后一张图片名称（最新上传图片）
				    	img=imgpath.substring(imgpath.lastIndexOf(",")+1,imgpath.length());
				    	mapimg.put("group_img", img);
				    }
				}
		    }  
	    }
		return list;
	}
	
	/**
	 * @author : 蔡毅存
	 * @throws java.io.IOException
	 * @date : 5:22, 2012 10:32:42 AM
	 * @Method Description :团购详细页面
	 */
	public String groupdetail() throws IOException{
		HttpServletRequest request = getRequest();
		String group_id=request.getParameter("group_id");
		Map map = new HashMap();
		if(!"".equals(group_id)){
			map.put("group_id", group_id);
		}
		groupgoods = groupgoodsService.get(group_id);
		//团购图片串拆分成单个图片放入数组
		String strimg = ""; 
		if(groupgoods!=null){
			strimg = groupgoods.getGroup_img();
			if(!"".equals(strimg)){
				String str[] = strimg.split(",");
			    if(str!=null && str.length>0){
			    	lastpic = str[0];
			    	for(int i=0;i<str.length;i++){
			    		imglist = java.util.Arrays.asList(str);
			    	}
			    }
			}
			//获取商品评价条数
			goods_id = groupgoods.getGoods_id();
			Map evalmap = new HashMap();
			evalmap.put("goods_id", goods_id);
			evalcount = goodsevalService.getCount(evalmap);
		}
		//地区过滤
		map = getareamap(map);
		//获取团购商品基本信息
		groupgoodsList=groupgoodsService.getList(map);
		if(groupgoodsList!=null && groupgoodsList.size()>0){
			HashMap mapvalue = (HashMap)groupgoodsList.get(0);
			if(mapvalue.get("market_price")!=null)
			saleprice = Double.parseDouble(mapvalue.get("sale_price").toString());
			if(mapvalue.get("group_price")!=null)
			groupprice =Double.parseDouble(mapvalue.get("group_price").toString());
			if(mapvalue.get("size_attr")!=null)
			good_attr = mapvalue.get("size_attr").toString();
			if(mapvalue.get("goods_name")!=null)
			goods_name = mapvalue.get("goods_name").toString();
			if(mapvalue.get("now_stock")!=null)
			now_stock = mapvalue.get("now_stock").toString();
			if(mapvalue.get("cust_id")!=null)
			cust_id = mapvalue.get("cust_id").toString();
			if(mapvalue.get("cust_name")!=null)
			cust_name = mapvalue.get("cust_name").toString();
			if(mapvalue.get("user_name")!=null)
			user_name = mapvalue.get("user_name").toString();
			pyrprice = saleprice-groupprice;
			gethotgroup();
		}
		
		
		
		return goUrl("groupdetail");
	}
	
	public void gethotgroup(){
		//绑定今日热门团购
		Map hotmap = new HashMap();
		hotmap.put("already_buy", "already_buy");
		hotmap.put("info_state", "1");
		//显示6个
		//地区过滤
		hotmap = getareamap(hotmap);
		hotmap.put("start", "0");
		hotmap.put("limit", "6");
		hotgroupList = groupgoodsService.getList(hotmap);
		hotgroupList = getList(hotgroupList);
	}
	

	/**
	 * 方法描述：团购公告列表
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 */
	public  String noticeGroup(){
		//绑定今日热门团购
		gethotgroup();
		//获取文章列表--商城公告
		Map newsMap=new HashMap();
		//公告审核通过：1
		newsMap.put("info_state","1");
		//商城公告类型：1
		newsMap.put("news_type","2");
		//推荐 c
		newsMap.put("news_attr","c");
		//地区过滤
		//newsMap = getareamap(newsMap);
		// 根据页面提交的条件找出信息总数
		int count = this.newsService.getCount(newsMap);
		//分页插件
		newsMap = super.pageTool(count,newsMap);
		newsIndexList=newsService.getList(newsMap);
		return goUrl("noticesList");
	}
	/**
	 * 方法描述：团购公告列表
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 */
	public String noticeDetail(){
		//绑定今日热门团购
		gethotgroup();
		if(nid==null && "".equals(nid))
		{
			return noticeGroup();
		}
		news = newsService.get(nid);
		if(news==null){
			news = new News();
		}
		return goUrl("noticesDetail");
	}
	
	/**
	 * 方法描述：Ajax提交收货地址
	 * 
	 * @author 蔡毅存
	 * @throws java.io.IOException
	 * @throws Exception
	 */
	public void insertadd() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		Buyeraddr buyeraddr =new Buyeraddr();
		String wd = URLDecoder.decode(request.getParameter("addinfo"), "UTF-8");
		if(wd!=null && !"".equals(wd)){
			String[] str = wd.split("\\|");
			buyeraddr.setUser_id(this.session_user_id);
			buyeraddr.setCust_id(this.session_cust_id);
			buyeraddr.setCust_name(str[0]);
			buyeraddr.setArea_attr(str[1]);
			buyeraddr.setAddress(str[2]);
			buyeraddr.setPost_code(str[3]);
			buyeraddr.setPhone(str[4]);
			if(str.length>5){
				buyeraddr.setCell_phone(str[5]);
			}
			buyeraddrService.insertBuyeraddr(buyeraddr);
			out.write("1");
		}
	}
    
	public double getGroupprice() {
		return groupprice;
	}
	public void setGroupprice(double groupprice) {
		this.groupprice = groupprice;
	}
	public double getPyrprice() {
		return pyrprice;
	}
	public void setPyrprice(double pyrprice) {
		this.pyrprice = pyrprice;
	}
	public StringBuilder getSbattr() {
		return sbattr;
	}

	public void setSbattr(StringBuilder sbattr) {
		this.sbattr = sbattr;
	}

	public List getCattreeList() {
		return cattreeList;
	}

	public void setCattreeList(List cattreeList) {
		this.cattreeList = cattreeList;
	}

	public List getHotList() {
		return hotList;
	}

	public void setHotList(List hotList) {
		this.hotList = hotList;
	}
	public static String getBeigincat_id() {
		return beigincat_id;
	}

	public static void setBeigincat_id(String beigincat_id) {
		WebgoodsAction.beigincat_id = beigincat_id;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public static StringBuilder getAttrsb() {
		return attrsb;
	}

	public static void setAttrsb(StringBuilder attrsb) {
		WebgoodsAction.attrsb = attrsb;
	}

	public String getViewtype() {
		return viewtype;
	}

	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getBrandlight() {
		return brandlight;
	}

	public void setBrandlight(String brandlight) {
		this.brandlight = brandlight;
	}

	public String getOrdersales() {
		return ordersales;
	}

	public void setOrdersales(String ordersales) {
		this.ordersales = ordersales;
	}
	public static String getBeiginen_name() {
		return beiginen_name;
	}
	public static void setBeiginen_name(String beiginen_name) {
		WebgoodsAction.beiginen_name = beiginen_name;
	}
	public String getAddrString() {
		return addrString;
	}
	public void setAddrString(String addrString) {
		this.addrString = addrString;
	}

	/**
	 * @return the area_attr
	 */
	public String getArea_attr() {
		return area_attr;
	}

	
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	/**
	 * @param area_attr
	 *            the area_attr to set
	 */
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}
	public List getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List paymentList) {
		this.paymentList = paymentList;
	}
	public static String getType() {
		return type;
	}
	public static void setType(String type) {
		WebgoodsAction.type = type;
	}
	public String getCatstr() {
		return catstr;
	}
	public void setCatstr(String catstr) {
		this.catstr = catstr;
	}
	public String getCatString() {
		return catString;
	}
	public void setCatString(String catString) {
		this.catString = catString;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public static String getSelvaluep() {
		return selvaluep;
	}
	public static void setSelvaluep(String selvaluep) {
		WebgoodsAction.selvaluep = selvaluep;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public static String getBrandname() {
		return brandname;
	}
	public static void setBrandname(String brandname) {
		WebgoodsAction.brandname = brandname;
	}
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public String getHight() {
		return hight;
	}
	public void setHight(String hight) {
		this.hight = hight;
	}
	public String getPricedown() {
		return pricedown;
	}
	public void setPricedown(String pricedown) {
		this.pricedown = pricedown;
	}
	public String getPriceup() {
		return priceup;
	}
	public void setPriceup(String priceup) {
		this.priceup = priceup;
	}
	public Groupgoods getGroupgoods() {
		return groupgoods;
	}
	public void setGroupgoods(Groupgoods groupgoods) {
		this.groupgoods = groupgoods;
	}
	public List getImglist() {
		return imglist;
	}
	public void setImglist(List imglist) {
		this.imglist = imglist;
	}
	public String getGood_attr() {
		return good_attr;
	}
	public void setGood_attr(String good_attr) {
		this.good_attr = good_attr;
	}
	
}
