/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: WebmallshopAction.java 
 */
package com.rbt.webaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.function.AreaFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Busimes;
import com.rbt.model.Collect;
import com.rbt.model.Goods;
import com.rbt.model.Member;
import com.rbt.model.Membercat;
import com.rbt.model.Memberchannel;
import com.rbt.model.Memberlink;
import com.rbt.model.Memberuser;
import com.rbt.model.Shopconfig;
import com.rbt.service.IAboutusService;
import com.rbt.service.IBusimesService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercatService;
import com.rbt.service.IMemberchannelService;
import com.rbt.service.IMemberlinkService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.INavService;
import com.rbt.service.ISellerscoreService;
import com.rbt.service.IShopconfigService;
/**
 * @function 功能 商城店铺信息action类
 * @author 创建人 胡惜坤
 * @date 创建日期 05 10 15:55:21 CST 2012
 */
@Controller
public class WebshopAction extends WebbaseAction implements Preparable{
	
	
	private static final long serialVersionUID = -4371080487351098292L;
	
	@Autowired
	public IShopconfigService shopconfigService;
	@Autowired
	public IMemberlinkService memberlinkService;
	@Autowired
	public IMembercatService membercatService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IMemberchannelService memberchannelService;
	@Autowired
	public INavService navService;
	@Autowired
	public IAboutusService aboutusService;
	@Autowired
	public IGoodsService goodsService;
	@Autowired
	public ISellerscoreService sellerscoreService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IGoodsevalService goodsevalService;
	@Autowired
	public IBusimesService busimesService;
	
	
	public Shopconfig shopconfig;
	public Memberlink memberlink;
	public Membercat membercat;
	public Memberuser memberuser;
	public Memberchannel memberchannel;
	public Goods goods;
	public Member member;
	public Collect collect;
	private AreaFuc areaFuc;
	public Busimes busimes;
	
	public List  shopconfigList;
	public List memberlinkList;
	public List membercatList;
	public List memberuserList;
	public List memberchanneList;
	public List bottomnavList;
	public List aboutusIndexList;
	public List goodsList;
	public List goodsRecomList;
	public List membercatRecomList;
	public List scoreList;
	public List buygoodsevalList;
	public List sellergoodsevalList;
	public List busimesList;
	
	
	//商城底部版权信息
	private String cfg_powerby="cfg_powerby";
	//商城ICP备案号
	private String cfg_beian="cfg_beian";
	public String sysconfig_cfg_powerby;
	public String sysconfig_cfg_beian;
	//商店ID
	public String shop_id;
	//用户名称
	public String user_name;
	public String para_temp_code;
	//商店客户ID
	public String shop_cust_id;
	//会员自定义商品分类推荐的个数
	public Integer membercatrecomcount;
	public String serarch_type;
	public String search_price_state;
	public String search_show;
	//相关商品数
	public int count;
	public String cat_id;
	public String weekNum;//一周之内
	public String monthNum;//一个月之内
	public String halfYearNum;//半年之内
	public String halfYearBeforNum;//半年之前
	public double goodComment; 
	//搜索条件
	public String selName;
	public String uppri;
	public String downpri;
	public String selValue;

	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :店铺首页绑定
	 */
	@Action(value = "shopindex", results = {@Result(name = "index", location = "/WEB-INF/template/shop/index.ftl")})
	public String index() throws Exception{

		if(shop_cust_id!=null&&!"".equals(shop_cust_id))
		{
		  //获取商店自定义推荐分类信息
		  memberCatRecomInfo(shop_cust_id);
		  //获取精品推荐的商品
		  goodsRecomInfo(shop_cust_id);
		}
		return "index";
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :店铺列表页绑定
	 */
	@Action(value = "goodslist", results = {@Result(name = "goodslist", location = "/WEB-INF/template/shop/goodslist.ftl")})
	public String goodslist() throws Exception{

		if(shop_cust_id!=null&&!"".equals(shop_cust_id))
		{
			HttpServletRequest request = getRequest();
			//搜索条件
			selName = request.getParameter("selName");
			uppri = request.getParameter("uppri");
			downpri = request.getParameter("downpri");
			if(shop_cust_id!=null&&!"".equals(shop_cust_id))
			{
			  Map gMap=new HashMap();
			  gMap.put("info_state", "1");
			  gMap.put("is_del", "1");
			  gMap.put("cust_id", shop_cust_id);
			  if(selName!=null && !"".equals(selName)){
			      gMap.put("goods_name", selName);
			  }
			  if(uppri!=null && !"".equals(uppri)){
				  gMap.put("usale_price", uppri);
			  }
			  if(downpri!=null && !"".equals(downpri)){
				  gMap.put("dsale_price", downpri);
			  }
			  search_price_state=null;
				//获取隐藏域type值
			  if(request.getParameter("serarch_type")!=null&&!"mr".equals(request.getParameter("serarch_type"))){
				  serarch_type=request.getParameter("serarch_type");
			  }
			  //默认排序对type清空
			  if("mr".equals(request.getParameter("serarch_type"))){
				  serarch_type="";
			  }
			  //默认排序对type清空
			  if("mr".equals(serarch_type)){
				  serarch_type="";
			  }
			  //按时间降序
			  if("time".equals(serarch_type)){
				  gMap.put("in_date",serarch_type);
			  }
			  //按销售量降序
			  if("sales".equals(serarch_type)){
				  gMap.put("saled_num", serarch_type);
			  }
			  //按价格升序排列
			  if("salesup".equals(serarch_type)){
				  search_price_state="up";
				  gMap.put("salesup", serarch_type);
			  }
			  //按价格降序排列
			  if("salesdown".equals(serarch_type)){
				  search_price_state="down";
				  gMap.put("salesdown", serarch_type);
			  }
			  if(search_show==null||"".equals(search_show))
			  {
				  search_show="bigimg";//默认大图显示商品
			  }
			  if(cat_id!=null&&!"".equals(cat_id))
			  {
				  gMap.put("self_cat", cat_id);
			  }
			  if(selValue!=null && !"".equals(selValue)){
				  gMap.put("name_s", selValue);
			  }
			//根据页面提交的条件找出信息总数
			count = this.goodsService.getCount(gMap);
				//分页插件
			gMap = super.pageTool(count, gMap);
			goodsList=goodsService.getList(gMap);
			}
		}
		return "goodslist";
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :店铺全部分类
	 */
	@Action(value = "shopcategory", results = {@Result(name = "shopcategory", location = "/WEB-INF/template/shop/goodscategory.ftl")})
	public String shopcategory() throws Exception{
		if(shop_cust_id!=null&&!"".equals(shop_cust_id))
		{
			//获取商店自定义分类信息
			memberCatnfo(shop_cust_id);
		}
		return "shopcategory";
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :店铺信用评价-买家的评价 
	 */
	@Action(value = "shopcredit", results = {@Result(name = "shopcredit", location = "/WEB-INF/template/shop/credit.ftl")})
	public String shopcredit() throws Exception{
		
		if(shop_cust_id!=null&&!"".equals(shop_cust_id))
		{
			//获取买家评论信息
			buyCommemtInfo(shop_cust_id);
			//店铺信誉基本信息
			creditInfo();
		}
		
		return "shopcredit";
	}
	
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :店铺信用评价-给他人的评价
	 */
	@Action(value = "shopcreditofbuy", results = {@Result(name = "shopcreditofbuy", location = "/WEB-INF/template/shop/creditofbuy.ftl")})
	public String shopcreditofbuy() throws Exception{
		
		if(shop_cust_id!=null&&!"".equals(shop_cust_id))
		{
			//获取卖家评论信息
			sellerCommemtInfo(shop_cust_id);
			//店铺信誉基本信息
			creditInfo();
		}
		
		return "shopcreditofbuy";
	}
	
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :店铺信誉基本信息
	 */
	public void creditInfo()
	{
		if(shop_cust_id!=null&&!"".equals(shop_cust_id))
		{
			
			
			//获取一周之内的评论信息
			weekNum=goodsEvalNumber(shop_cust_id,7,"2");
			//获取一个月之内的评论信息
			monthNum=goodsEvalNumber(shop_cust_id,30,"2");
			//获取半年之内的评论信息
			halfYearNum=goodsEvalNumber(shop_cust_id,180,"2");
			//获取半年之前的评论信息
			halfYearBeforNum=goodsEvalNumber(shop_cust_id,181,"1");
			//获取好评率
			goodComment=countGoodCom(sellergoodsevalList);
			//获取好评率
			goodComment=countGoodCom(sellergoodsevalList);
		}
		
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :AJAX提交店铺留条
	 */
	@Action(value = "insertBusinmes")
	public void insertBusinmes() throws IOException{
		
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if("".equals(this.session_cust_id)){
	           out.write("1");
		}else{
		String mcontent =  URLDecoder.decode(request.getParameter("mescotent"), "UTF-8");
		String shop_id = request.getParameter("shop_id");
		busimes=new Busimes();
		if(mcontent!=null&&!"".equals(mcontent))
		{
			mcontent=mcontent.replace("<", "");
			mcontent=mcontent.replace(">", "");
		}
		busimes.setMsg_content(mcontent);
		busimes.setGet_cust_id(shop_id);
		busimes .setSelf_cust_id(session_cust_id);
		busimes.setSelf_user_id(this.session_user_id);
		busimes.setIs_enable("0");
		busimesService.insert(busimes);
		out.write("0");
		}
	}
	
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :店铺介绍
	 */
	@Action(value = "shopintro", results = {@Result(name = "shopintro", location = "/WEB-INF/template/shop/introduce.ftl")})
	public String shopintro() throws Exception{
		
		if(shop_cust_id!=null&&!"".equals(shop_cust_id))
		{	
		}
		
		return "shopintro";
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :店铺留言
	 */
	@Action(value = "shopcomm", results = {@Result(name = "shopcomm", location = "/WEB-INF/template/shop/shopcomm.ftl")})
	public String shopcomm() throws Exception{
		
		if(shop_cust_id!=null&&!"".equals(shop_cust_id))
		{
			Map busimesMap=new HashMap ();
			busimesMap.put("get_cust_id", shop_cust_id);
			busimesMap.put("is_enable", "0");
			//根据页面提交的条件找出信息总数
			count = this.goodsService.getCount(busimesMap);
				//分页插件
			busimesMap = super.pageTool(count, busimesMap);
			busimesList=busimesService.getList(busimesMap);
		}
	
		return "shopcomm";
	}
	
	
	/**
	 * @author : 蔡毅存
	 * @throws java.io.IOException
	 * @Method Description :自定义页面
	 */
	@Action(value = "myself", results = {@Result(name = "myself", location = "/WEB-INF/template/shop/myself.ftl")})
	public String myself() throws Exception{
        //获取栏目ID 
		HttpServletRequest request = getRequest();
		String ch_id = request.getParameter("ch_id");
		if(ch_id!=null && !"".equals(ch_id))
		memberchannel = memberchannelService.get(ch_id);
		return "myself";
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :统计好评率
	 */
	public double countGoodCom(List gsevaList)
	{
		double goodcomFloat=0; 
		String strcout="0,0,0";
		strcout=countCommentInfo(gsevaList);
		if(strcout!=null&&!strcout.equals("0,0,0"))
		{
			String counts[]=strcout.split(",");
			if(!counts[0].equals("0"))
			{
				goodcomFloat=(Double.parseDouble(counts[0])/(Double.parseDouble(counts[0])+Double.parseDouble(counts[1])+Double.parseDouble(counts[2])))*100;
				goodcomFloat=myRound(goodcomFloat,2);
			}
		}
		return goodcomFloat;
		
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :四舍五入
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
	 * @Method Description :获取评价信息数据串格式为：返回数据的格式为："好评,中评,差评"
	 */
	public String countCommentInfo(List gsevaList)
	{
		String strcount="0,0,0";
		Integer strgood=0,strmid=0,strbad=0;
		if(gsevaList!=null&&gsevaList.size()>0)
		{
			for(int i=0;i<gsevaList.size();i++)
			{
				HashMap sMap =new HashMap ();
				sMap=(HashMap)gsevaList.get(i);
				if(sMap.get("g_eval")!=null)
				{
					if(sMap.get("g_eval").toString().equals("1"))
					{
						strgood=strgood+1;
					}
					if(sMap.get("g_eval").toString().equals("0"))
					{
						strmid=strmid+1;
					}
					if(sMap.get("g_eval").toString().equals("-1"))
					{
						strbad=strbad+1;
					}
				}
			}
		}
		//返回数据的格式为："好评,中评,差评"
		strcount=strgood.toString()+","+strmid.toString()+","+strbad.toString();
		return strcount;
	}
	
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :获取评价信息数据
	 * state:为查找的状态，如果为：1表示半年之前的信息，2表示半年以内的信息
	 */
	public String  goodsEvalNumber(String cust_id,Integer dates,String state)
	{
		String strcount="0,0,0";
		Integer strgood=0,strmid=0,strbad=0;
		List countgoodsevalList=new ArrayList();
		HashMap sellercoMap = new HashMap ();
		sellercoMap.put("buy_cust_id", cust_id);
		//1表示半年之前的信息
		if(state.equals("1"))
		{
			sellercoMap.put("end_time", getDateBefore(dates));
		}
		//2表示半年以内的信息
		else {
			sellercoMap.put("start_time", getDateBefore(dates));
		}
		countgoodsevalList=goodsevalService.getList(sellercoMap);
		strcount=countCommentInfo(countgoodsevalList);
		return strcount;
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :计算出多少天之前的日期
	 */
	public  String getDateBefore(int day){
		
	   Date dates = new Date();
	   Calendar now =Calendar.getInstance();
	   now.setTime(dates);
	   now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
	   DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	   return df.format(now.getTime());
	  }
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :获取卖家的评价信息
	 */
	public void sellerCommemtInfo(String cust_id)
	{
		Map sellercoMap = new HashMap ();
		sellercoMap.put("buy_cust_id", cust_id);
		//根据页面提交的条件找出信息总数
		count = this.goodsevalService.getCount(sellercoMap);
			//分页插件
		sellercoMap = super.pageTool(count, sellercoMap);
		sellergoodsevalList=goodsevalService.getList(sellercoMap);
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :获取给买家的评价信息
	 */
	public void buyCommemtInfo(String cust_id)
	{
		// 获取对店铺动态评分列表			
		Map buycoMap = new HashMap ();
		buycoMap.put("cust_id", cust_id);
		//根据页面提交的条件找出信息总数
		count = this.goodsevalService.getCount(buycoMap);
			//分页插件
		buycoMap = super.pageTool(count, buycoMap);
		buygoodsevalList=goodsevalService.getList(buycoMap);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :获取会员会员信息
	 */
	public void memberInfo(String cust_id)
	{
		member=this.memberService.get(cust_id);
		//转换地区名称
		if(member!=null && member.getArea_attr()!=null){
			String area_name=areaFuc.getAreaNameByMap(member.getArea_attr());
			member.setArea_attr(area_name);					
		}
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :获取用户名通过CUST_ID信息
	 */
	public String getUserNameByCust_id(String cust_id)
	{
		String username="";
		HashMap  muMap=new HashMap ();
		muMap.put("user_type", "1");
		muMap.put("cust_id", cust_id);
		memberuserList=memberuserService.getList(muMap);
		if(memberuserList!=null&&memberuserList.size()>0)
		{
			HashMap mapsMap =new HashMap ();
			mapsMap=(HashMap)memberuserList.get(0);
			if(mapsMap.get("user_name")!=null)
			{
				username=mapsMap.get("user_name").toString();
			}
		}
		return username;
	}
	
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :获取店铺动态评分
	 */
	public void scoreInfo(String cust_id)
	{
		// 获取对店铺动态评分列表			
		Map scoreMap=new HashMap();
		scoreMap.put("cust_id", cust_id);
		scoreList=this.sellerscoreService.getCountList(scoreMap);
	}
	
	
	
	
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :获取会员自定义分类
	 */
	public void memberCatnfo(String cust_id)
	{
		if(cust_id!=null)
		{
			HashMap mcMap =new HashMap ();
			mcMap.put("cust_id",cust_id);
			mcMap.put("cat_type", "3");
			membercatList=membercatService.getList(mcMap );
		}
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :获取会员推荐自定义分类
	 */
	public void memberCatRecomInfo(String cust_id)
	{
		if(cust_id!=null)
		{
			HashMap mcMap =new HashMap ();
			mcMap.put("cust_id",cust_id);
			mcMap.put("is_recom", "1");
			mcMap.put("cat_type", "3");
			mcMap.put("cat_level", "1");
			membercatRecomList=membercatService.getList(mcMap );
			//推荐自定义商品分类个数
			if(membercatRecomList!=null&&membercatRecomList.size()>0)
			{
				membercatrecomcount=membercatRecomList.size();
			}
			else
			{
				membercatrecomcount=0;
			}
		}
		
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :获取商品
	 */
	public void goodsInfo(String cust_id)
	{
		if(cust_id!=null)
		{
			HashMap gMap =new HashMap ();
			gMap.put("cust_id",cust_id);
			gMap.put("info_state", "1");
			goodsList=goodsService.getList(gMap );
		}
		
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :获取精品推荐的商品
	 */
	public void goodsRecomInfo(String cust_id)
	{
		if(cust_id!=null)
		{
			HashMap gMap =new HashMap ();
			gMap.put("cust_id",cust_id);
			gMap.put("info_state", "1");
			gMap.put("label", "c");
			goodsRecomList=goodsService.getList(gMap );
		}
		
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :店铺获取友情链接
	 */
	public void memberLinkInfo(String cust_id)
	{
		if(cust_id!=null)
		{
			HashMap linkMap =new HashMap ();
			linkMap.put("cust_id",cust_id);
			linkMap.put("link_state", "0");
			linkMap.put("plat_type", "b2c");
			memberlinkList=memberlinkService.getList(linkMap );
		}
		
	}

	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :url获取cust_id信息
	 */
	public String getcustid() throws Exception{
		
		HttpServletRequest request = getRequest();
		//获取url参数
		user_name = request.getParameter("user_name");
	    this.para_temp_code = request.getParameter("para_temp_code");
		//根据user_name查询cust_id
		String cust_id="";
		memberuser = this.memberuserService.getMemberuserByusername(user_name);
		if(memberuser!=null&&memberuser.getCust_id()!=null)
		{
			cust_id=memberuser.getCust_id();
		}
		return cust_id;
	}
    
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :绑定店铺头部信息
	 */
	public void getIndexTop(String cust_id)
	{
		HashMap mchannelMap =new HashMap ();
		mchannelMap .put("cust_id", cust_id);
		mchannelMap .put("plat_type", "b2c");
		memberchanneList=memberchannelService.getList(mchannelMap);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :获取帮助中心信息列表
	 */
	public void gethelplist()
	{
		//获取商城底部帮助中心信息
		HashMap aboutusMap=new HashMap ();
		aboutusMap.put("plat_type", "b2c");
		aboutusMap.put("para_code", "help_type");
		aboutusIndexList=aboutusService.getList(aboutusMap);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @Method Description :绑定店铺底部信息
	 */
	public void getIndexBottom()
	{
		//获取商城底部信息
		HashMap navMap=new HashMap ();
		navMap.put("plat_type", "b2c");
		navMap.put("isshow", "0");
		navMap.put("nav_post", "3");
		bottomnavList=navService.getList(navMap);
		//获取商品版权信息
		sysconfig_cfg_powerby=SysconfigFuc.getSysValue(cfg_powerby);
		//获取商城ICP备案信息
		sysconfig_cfg_beian=SysconfigFuc.getSysValue(cfg_beian);
		//获取商城底部帮助中心信息
		gethelplist();
	}
	public void prepare() throws Exception {
		
		shop_cust_id=getcustid();
		if(shop_cust_id!=null&&!"".equals(shop_cust_id))
		{
			//取出店铺信息
			shopconfig=shopconfigService.GetByCustId(shop_cust_id);
			if(shopconfig!=null)
			{
			  //绑定店铺头部信息
			  getIndexTop(shopconfig.getCust_id());
			  //绑定店铺底部信息
			  getIndexBottom();
			//获取友情链接
			  memberLinkInfo(shop_cust_id);
			  //获取商店自定义分类信息
			  memberCatnfo(shop_cust_id);
			  //获取店铺评分信息
			  scoreInfo(shop_cust_id);
			  //获取店铺信息
			  memberInfo(shop_cust_id);
			}
		}
	}
	
	
	
	
}
