/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: WebcompanytemplateAction.java 
 */
package com.rbt.webaction;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.model.Buy;
import com.rbt.model.Certification;
import com.rbt.model.Gallery;
import com.rbt.model.Member;
import com.rbt.model.Membercert;
import com.rbt.model.Memberchannel;
import com.rbt.model.Memberconfig;
import com.rbt.model.Membercredit;
import com.rbt.model.Memberinbox;
import com.rbt.model.Membernews;
import com.rbt.model.Memberuser;
import com.rbt.model.Product;
import com.rbt.model.Supply;
import com.rbt.model.Video;
import com.rbt.model.Credithistory;
import com.rbt.service.IBuyService;
import com.rbt.service.ICertificationService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IGalleryService;
import com.rbt.service.IGallerypicService;
import com.rbt.service.ILevelinfoService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercatService;
import com.rbt.service.IMembercertService;
import com.rbt.service.IMemberchannelService;
import com.rbt.service.IMemberconfigService;
import com.rbt.service.IMembercreditService;
import com.rbt.service.IMemberinboxService;
import com.rbt.service.IMemberlinkService;
import com.rbt.service.IMembernewsService;
import com.rbt.service.IMemberuserService;
import com.rbt.service.IProductService;
import com.rbt.service.ISupplyService;
import com.rbt.service.IVideoService;
import com.rbt.service.ICredithistoryService;
/**
 * @function 功能 记录下载信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 31 13:55:21 CST 2011
 */
@Result(name = "company", location = "/WEB-INF/template/company/${temp_loc}/company_intro.ftl")
@Controller
public class WebcompanytemplateAction extends WebbaseAction implements Preparable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -4371080487351098292L;
	
	/*
	 * 新闻对象
	 */
	public Product product;
	/*
	 * 新闻对象
	 */
	public Membernews membernews;
	/*
	 * 会员对象
	 */
	public Member member;
	/*
	 * 会员对象
	 */
	public Member memberintro;
	/*
	 * 会员对象
	 */
	public Supply supply;
	/*
	 * 图片集
	 */
	public Gallery gallery;
	/*
	 * 视频对象
	 */
	public Video video;
	/*
	 * 证书对象
	 */
	public Membercert membercert;
	//企业认证对象
	public Certification certification;
	/*
	 * 求购表对象
	 */
	public Memberchannel memberchannel;
	/*
	 * 记录会员企业站栏目配置信息对象
	 */
	public Memberconfig memberconfig;
	/*
	 *  企业用户对象
	 */
	public Memberuser memberuser;
	public Memberuser memberuserlog;
	/*
	 * 求购表对象
	 */
	public Buy buy;
	/*
	 * 求留言对象
	 */
	public Memberinbox memberinbox;
	
	private String plat_type="b2b";//寻找平台类型
	
	//客服在线
	public String aliim;
	public String msn ;
	public String skype;
	public String qq;
	public List qqList=new ArrayList();
	public List aliimList=new ArrayList();
	
	/*
	 * 信用指数记录对象
	 */
	public Credithistory credithistory;
	
	/*
	 * 公司新闻业务层接口
	 */
	@Autowired
	public IMembernewsService membernewsService;
	@Autowired
	public IMemberconfigService memberconfigService;
	@Autowired
	public IMemberchannelService memberchannelService;
	@Autowired
	public IMemberlinkService memberlinkService;
	@Autowired
	public IGalleryService galleryService;
	@Autowired
	public IGallerypicService gallerypicService;
	@Autowired
	public IVideoService videoService;
	@Autowired
	public IMemberuserService memberuserService;
	@Autowired
	public IProductService productService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMembercertService membercertService;
	@Autowired
	public ISupplyService supplyService;
	@Autowired
	public IMembercatService membercatService; 
	@Autowired
	public IBuyService buyService;	
	@Autowired
	public ILevelinfoService levelinfoService;	
	@Autowired
	public IMembercreditService membercreditService;	
	@Autowired
	public ICertificationService certificationService;	
	@Autowired
	public ICommparaService commparaService;	
	@Autowired
	public IMemberinboxService memberinboxService;
	@Autowired
	public ICredithistoryService credithistoryService;
	
	public List levelinfoList;
	//参数列表
	public List commparaList;
	/*
	 * 首页栏目列表
	 */
	public List pagepartList;
	/*
	 * 侧栏列表
	 */
	public List SidebarList;
	/*
	 * 主栏列表
	 */
	public List mainbarList;
	/*
	 * 新闻列表
	 */
	public List newsList;
	/*
	 * 新闻列表
	 */
	public List allnewsList;
	/*
	 * 友情链接
	 */
	public List linkList;
	/*
	 * 产品推荐显示
	 */
	public List recommendProductList;
	/*
	 * 图库列表
	 */
	public List galleryList;
	/*
	 * 视频列表
	 */
	public List videoList;
	/*
	 * 产品列表
	 */
	public List productList;
	/*
	 *证书列表
	 */
	public List membercertList;
	/*
	 *证书列表
	 */
	public List membercertallList;
	/*
	 * 供应产品列表
	 */
	public List supplyList;
	/*
	 * 图片列表
	 */
	public List gallerycustList;
	/*
	 * 所有供应列表
	 */
	public List supplyAllList;
	/*
	 * 产品分类
	 */
	public List productCateList;
	/*
	 * 产品信息
	 */
	public List allproductList;
	/*
	 * 友情链接
	 */
	public List allLink;
	/*
	 * 获取9-16的菜单
	 */
	public List pagepartlastList;
	/*
	 * 获取企业留言
	 */
	public List memberinboxList;
	/*
	 * 配置表对象
	 */
	public String sysconfig;
	/*
	 * 采购列表
	 */
	public List allbuyList;
	/*
	 * 信用指数记录列表
	 */
	public List credithistoryList;
	//企业类型列表
	public List cust_type_List;
	/*
	 * 搜索字段
	 */
	public String selvalue;
	/*
	 *是否是VIP
	 */
	public String isvip;
	/*
	 *VIP年限
	 */
	public String years;
	/*
	 * 系统时间
	 */
	public String systime;
	//荣誉证书数量
	public int certnum;
	//用户名称
	public String user_name;
	/*
	 * 开始标记
	 */
	public static final String START="0";
	/*
	 * 结束标记
	 */
	public static final String LIMIT="6";
	/*
	 * 推荐产品结束标记
	 */
	public static final String FOURLIMIT="4";
	/*
	 * 供应产品结束标记
	 */
	public static final String EIGLIMIT="8";
	public static final String NINE="9";
	public static final String SIXTEEN="16";
	public static final String ELEVEN ="11";
	/*
	 * 首页导航结束标记
	 */
	public static final String EIGLIMITPAG="8";
	
	/*
	 * 记录图库图片信息信息集合
	 */
	public List gallerypicList;
	//个人企业站---查找栏目设置产品信息集合
	public List channelList;
	public static final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五","星期六" }; 
	//货币种类
	public String para_code = "currency_type";
	//企业类型
	public String cust_para_code="cust_type";
	//地区名称
	public String areaName;
	public String para_temp_code;
	public String cust_name_credit;
	private String company_cust_id; 
	private boolean is_shop_company;
	//留言标题
	public String title;
	//留言内容
	public String content;
	/**
	 * 存储验证码
	 */
	private String sysrand;
	
	//公司企业html过滤
	public String chinaDesc;
	//定义模板代码
	public String temp_loc;
	//定义是否显示谷歌地图
	public String ismap;
	/**
	 * 验证码
	 */
    public String commentrand;
	
	//企业站首页绑定
	@Action(value = "companytemplate", results = {@Result(name = "index", location = "/WEB-INF/template/company/${temp_loc}/index.ftl")})
	public String index() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		//获取企业推荐产品
		getproduct();
		//获取企业最新产品
		getnewsupply();
		//获取公司图片
		getgallerycust();
		//在线客服
		getkefu();
		return "index";
	}
	
	
	//供应列表页绑定
	@Action(value = "companysupply", results = { @Result(name = "supply", location = "/WEB-INF/template/company/${temp_loc}/supply.ftl")})
	public String supply() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		//获取所有供应信息
		getallsupply();
		return "supply";
	}
	
	//新闻列表页绑定
	@Action(value = "companynews", results = { @Result(name = "news", location = "/WEB-INF/template/company/${temp_loc}/news.ftl")})
	public String news() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		//获取所有新闻列表
		getallnews();
		return "news";
	}
   
	
	//新闻详细页绑定
	@Action(value = "companynewscontent", results = { @Result(name = "newscontent", location = "/WEB-INF/template/company/${temp_loc}/newscontent.ftl")})
	public String newscontent() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		//获取新闻详细页
		getnewcontent();
		return "newscontent";
	}
	
	//公司信用明细
	@Action(value = "companycreditdetail", results = { @Result(name = "creditdetail", location = "/WEB-INF/template/company/${temp_loc}/creditdetail.ftl")})
	public String creditdetail() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		//获取企业信用指数记录列表
		getcredithistory();
		return "creditdetail";
	}
	
	//公司简介页面绑定
	@Action(value = "companyprofile", results = { @Result(name = "profile", location = "/WEB-INF/template/company/${temp_loc}/profile.ftl")})
	public String profile() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		return "profile";
	}
	
	//根据供应ID号获得供应详细信息supplycontent
	@Action(value = "companysupplycontent", results = { @Result(name = "profile", location = "/WEB-INF/template/company/${temp_loc}/supplycontent.ftl")})
	public String supplycontent() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		//获取供应实体
		getsupply();
		return "profile";
	}
	
	//绑定公司相册
	@Action(value = "companygallery", results = { @Result(name = "gallery", location = "/WEB-INF/template/company/${temp_loc}/gallery.ftl")})
	public String gallery() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		//获取公司相册
		getgallery();
		return "gallery";
	}
	
	//绑定公司图库内容类表图片
	@Action(value = "companygallerypiccontent", results = { @Result(name = "gallerypic", location = "/WEB-INF/template/company/${temp_loc}/gallerypic.ftl")})
	public String gallerypic() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		HttpServletRequest request = getRequest();
		//获取url参数
		String gal_id = request.getParameter("gallerypic_id");
		this.getgallerypic(gal_id);
		return "gallerypic";
	}
	
	//绑定公司视频集
	@Action(value = "companyvideo", results = { @Result(name = "video", location = "/WEB-INF/template/company/${temp_loc}/video.ftl")})
	public String video() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		//获取视频集
		getvideo();
		return "video";
	}
	
	//绑定公司视频详细
	@Action(value = "companyvideocontent", results = { @Result(name = "videocontent", location = "/WEB-INF/template/company/${temp_loc}/videocontent.ftl")})
	public String videocontent() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
	    //获取视频对象
		getintrvideo();
		return "videocontent";
	}
	//绑定网站公告
	@Action(value = "companyannouncement", results = { @Result(name = "announcement", location = "/WEB-INF/template/company/${temp_loc}/announcement.ftl")})
	public String announcement() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		HttpServletRequest request = getRequest();
		//获取url参数
		String cust_id_s = request.getParameter("cust_id");
		if(cust_id_s!=null && !cust_id_s.equals("")){
			memberconfig=this.memberconfigService.get(cust_id_s);
		}
		return "announcement";
	}
	
	//获取产品中心产品信息
	@Action(value = "companyproduct", results = { @Result(name = "product", location = "/WEB-INF/template/company/${temp_loc}/product.ftl")})
	public String product() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		//产品自定义分类
		setSelfCatList();
		//根据用户ID号获取产品信息
		getallproduct();
		return "product";
	}
	
	//获取产品自定义分类
	public void setSelfCatList() throws Exception{
		HashMap map = new HashMap();
		//cat_type 0：产品分类
		map.put("cat_type", "0");
		map.put("cust_id", this.company_cust_id);
		this.productCateList = membercatService.getList(map);
	}
	
	//获取产品详细信息
	@Action(value = "companyproductcontent", results = { @Result(name = "productcontent", location = "/WEB-INF/template/company/${temp_loc}/productcontent.ftl")})
	public String productcontent() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		HttpServletRequest request =getRequest();
		//获取url参数
		String product_id = request.getParameter("product_id");
		if(product_id!=null && !product_id.equals("")){
			product=this.productService.get(product_id);
		}
	    return "productcontent";
	}
	
	//友情链接绑定
	@Action(value = "companylink", results = { @Result(name = "link", location = "/WEB-INF/template/company/${temp_loc}/link.ftl")})
	public String link() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		Map pageMap=new HashMap();
		pageMap.put("cust_id", this.company_cust_id);
		//审核通过
		pageMap.put("link_state", "0");
		//根据页面提交的条件找出信息总数
		int count=this.memberlinkService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		allLink=this.memberlinkService.getList(pageMap);
		return "link";
	}
    
	//资格证书绑定
	@Action(value = "companymembercert", results = { @Result(name = "membercert", location = "/WEB-INF/template/company/${temp_loc}/membercert.ftl")})
	public String membercert() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		Map pageMap=new HashMap();
		pageMap.put("cust_id", this.company_cust_id);
		pageMap.put("cert_state", "0");
		//根据页面提交的条件找出信息总数
		int count=this.membercertService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		//获取所有符合条件的证书信息
		membercertallList=this.membercertService.getList(pageMap);
		return "membercert";
	}
	
	//资格证书详情
	@Action(value = "companycertcontent", results = { @Result(name = "certcontent", location = "/WEB-INF/template/company/${temp_loc}/membercertcontent.ftl")})
	public String certcontent() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		HttpServletRequest request = getRequest();
		//获取url参数
		String cert_id = request.getParameter("cert_id");
		if(cert_id!=null && !cert_id.equals("")){
			membercert=this.membercertService.get(cert_id);
		}
		return "certcontent";
	}
	
	//跟据用户ID号获取采购清单
	@Action(value = "companybuy", results = { @Result(name = "buy", location = "/WEB-INF/template/company/${temp_loc}/buy.ftl")})
	public String buy() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		//根据用户ID号获取产品信息
		getallbuy();
		return "buy";
	}
	//获取采购详情
	@Action(value = "companybuycontent", results = { @Result(name = "buycontent", location = "/WEB-INF/template/company/${temp_loc}/buycontent.ftl")})
	public String buycontent() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		HashMap map=new HashMap();
		map.put("cust_id", this.company_cust_id);
		HttpServletRequest request = ServletActionContext.getRequest();
		//获取url参数
		String buy_id = request.getParameter("buy_id");
		if(buy_id!=null && !buy_id.equals("")){
			buy=this.buyService.get(buy_id);
		}
		return "buycontent";
	}
	
	//联系我们
	@Action(value = "companycontactus", results = { @Result(name = "contactus", location = "/WEB-INF/template/company/${temp_loc}/contactus.ftl")})
	public String contactus() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		getleave();
		return "contactus";
	}
	
	//给我留言
	@Action(value = "companyleave" , results = { @Result(name = "contactus", location = "/WEB-INF/template/company/${temp_loc}/contactus.ftl")})
	public String leave() throws Exception{
		getleave();
		if(ValidateUtil.isRequired(title)){
			this.addFieldError("title", "留言标题不能为空");
			return "contactus";
		}
		if(ValidateUtil.isRequired(content)){
			this.addFieldError("content", "留言内容不能为空");
			return "contactus";
		}
		
		// 获取系统生成的验证码
		if (getSession().getAttribute("sysrand") != null) {
			sysrand = getSession().getAttribute("sysrand").toString();
		}
		
		if(!sysrand.equals(commentrand)){
			this.addFieldError("commentrand", "对不起，您输入的验证码有错");
			return "contactus";
		}
		memberinbox=new Memberinbox();
		memberinbox.setCust_id(this.company_cust_id);
		memberinbox.setMess_type("4");
		memberinbox.setTitle(title);
		memberinbox.setContent(content);
		if(this.session_cust_id==null || "".equals(this.session_cust_id)){
			memberinbox.setSend_cust_id("-1");
		}else{
		    memberinbox.setSend_cust_id(this.session_cust_id);
		}
		memberinbox.setIs_read("0");
		this.memberinboxService.insert(memberinbox);
		this.addFieldError("submit", "提交成功");
		getleave();
		return "contactus";
	}
	
	//获取客户留言
	@Action(value = "getleave" , results = { @Result(name = "contact", location = "/WEB-INF/template/company/${temp_loc}/contactus.ftl")})
	public String getleave(){
		Map map=new HashMap();
		map.put("cust_id", this.company_cust_id);
		int count=this.memberinboxService.getCount(map);
		//分页插件
		map = super.pageTool(count,map);
		memberinboxList=memberinboxService.getList(map);
		return "contact";
	}
	

	//自定义绑定
	@Action(value = "companymyself", results = { @Result(name = "myself", location = "/WEB-INF/template/company/${temp_loc}/myself.ftl")})
	public String myself() throws Exception{
		HashMap map=new HashMap();
		map.put("cust_id", this.company_cust_id);
		HttpServletRequest request = ServletActionContext.getRequest();
		//获取url参数
		String ch_id = request.getParameter("ch_id");
		if(ch_id!=null && !ch_id.equals("")){
			memberchannel=this.memberchannelService.get(ch_id);
		}
		//获取企业信息
		getmember();
		//不显示地图
		ismap="1";
		return "myself";
	}
	
	//实名认证
	@Action(value = "companycertifiction", results = { @Result(name = "certifiction", location = "/WEB-INF/template/company/${temp_loc}/certifictioncontent.ftl")})
	public String certifictioncontent() throws Exception{
		if(!this.is_shop_company){
			return "company";
		}
		//获取企业认证信息
		certification= certificationService.get(this.company_cust_id);
		// 获取币种的参数
		Map paraMap=new HashMap();
		paraMap.put("para_code", para_code);
		commparaList=this.commparaService.getList(paraMap);
		// 获取企业类型
		Map custMap=new HashMap();
		custMap.put("para_code", cust_para_code);
		cust_type_List=this.commparaService.getList(custMap);
		if(certification!=null){
	    areaName=AreaFuc.getAreaNameByMap(certification.getArea_attr()); 
		}
		return "certifiction";
	}
	
	
	//判断用户是否存在企业站
	//false：没有企业站 true：存在企业站
	public boolean ifexistscompany(String cust_id) throws Exception{
		boolean ifexists=true;
		if("".equals(cust_id)){
			return false;
		}
		Map pageMap = new HashMap();
		pageMap.put("cust_id", cust_id);
		pageMap.put("plat_type", plat_type);
		// 通过用户名找出用户信息
		List memberchannelList = this.memberchannelService.getList(pageMap);
		if (memberchannelList!=null && memberchannelList.size()>0) {
		}else{
			ifexists=false;
			if(cust_id!=null && !cust_id.equals("")){
				memberintro=this.memberService.get(cust_id);
				if (memberintro != null) {
					//获取用户的经营方式
					if (memberintro.getClient_status() != null && !"".equals(memberintro.getClient_status())) {
						String statusname = com.rbt.function.MemberFuc.getStatusName(memberintro.getClient_status());
						memberintro.setClient_status(statusname);
					}
					//获取用户注册是填写的地区串
					String area_attr = "";
					area_attr = AreaFuc.getAreaNameByMap(memberintro.getArea_attr());
					memberintro.setArea_attr(area_attr);
				}
			}
		}
		return ifexists;
	}
	
	
	public void getchannel() throws Exception{
		
		if(this.company_cust_id == null || this.company_cust_id.equals("")) return;
		
		//点击企业站企业站点击数加1
	    this.memberconfigService.updatelognum(this.company_cust_id);
		//获取VIP会员设置
		sysconfig=SysconfigFuc.getSysValue("cfg_vipname");
		//获取会员级别以及VIP信息
		levelinfoList=this.levelinfoService.getLevelinfoByTime(this.company_cust_id);
		//判断会员级别
		String level="";
		if(levelinfoList!=null&&levelinfoList.size()>0){
			//判断是否是vip会员
			HashMap map=new HashMap();
			map=(HashMap)levelinfoList.get(0);
			if(map.get("level_code")!=null){
				level=map.get("level_code").toString();
			}
			if("1".equals(level)||"3".equals(level)){
				isvip="0";
			}else{
				isvip="1";
			}
			//VIP年限
			if(map.get("years")!=null){
				years=map.get("years").toString();
			}
		}
		//获取菜单列表1-11个菜单
		if("default".equals(temp_loc)){
		   pagepartList= getPart("0","0",START,ELEVEN);
		}
		if("tea".equals(temp_loc)){
		    pagepartList= getPart("0","0",START,NINE);
		}
		//获取菜单列表9-16个菜单
		//pagepartlastList= getPart("0","0",EIGLIMIT,SIXTEEN);
		//获取侧栏列表
		SidebarList=getPart("1","0",START,EIGLIMIT);
		//获取首页主栏列表
	    mainbarList=getPart("2","0",START,EIGLIMIT);
	    //获取企业站配置
		getconfig();
		//获取企业新闻
		getnews();
		//获取企业
		getproduct();
		//获取企业信息
		getmember();
		//获取企业荣誉资质
		getmembercert();
		//获取友情链接列表
		getlink();
		//获取系统时间
		getsystime();
		//获取荣誉证书
		Map map=new HashMap();
		map.put("cert_state", "0");
		map.put("cust_id", this.company_cust_id);
		certnum=this.membercertService.getCount(map);
		//获取企业认证信息
		certification= certificationService.get(this.company_cust_id);
		//获取企业站的信用指数
		Membercredit cre=this.membercreditService.get(this.company_cust_id);
		if(cre!=null){
		cust_name_credit=cre.getC_num();
		}
		//获取登陆人信息
		getuserName();
	}
	
	//获取登陆人信息
	public void getuserName(){
		if(this.session_user_id!=null && !"".equals(session_user_id)){
			memberuserlog=memberuserService.get(this.session_user_id);
		}
	}
	
	public void getsystime() throws Exception{
		 Calendar ca = Calendar.getInstance();  
         int year = ca.get(Calendar.YEAR);//获取年份  
         int month=ca.get(Calendar.MONTH)+1;//获取月份   
         int day=ca.get(Calendar.DATE);//获取日  
         Calendar calendar = Calendar.getInstance();  
         Date date = new Date();  
         calendar.setTime(date);  
         int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;  
         if(dayOfWeek <0)dayOfWeek=0;  
		 systime=year + "年" + month + "月"+ day + "日 " +dayNames[dayOfWeek];
	}
	
	//url获取cust_id***********************************************
	public String getcustid() throws Exception{
		HttpServletRequest request = getRequest();
		//获取url参数
		user_name = request.getParameter("user_name");
	    this.para_temp_code = request.getParameter("para_temp_code");
		//根据user_name查询cust_id
		String cust_id="";
		memberuser = this.memberuserService.getMemberuserByusername(user_name);
		if(memberuser!=null){
		cust_id=memberuser.getCust_id();
		}
		return cust_id;
	}
	
	//根据type参数 获取栏目列表
	public List getPart(String type,String is_dis,String satrt,String limit) throws Exception{
		Map map=new HashMap();
		map.put("cust_id", this.company_cust_id);
		map.put("ch_type", type);
		map.put("is_dis", is_dis);
		map.put("plat_type",mall_type);
		if(type.equals("0")){
			map.put("start",satrt);
			map.put("limit",limit);
		}
		List partList=this.memberchannelService.getList(map);
		return partList;
	}
	
	//根据用户ID号获取企业站配置
	public Memberconfig getconfig() throws Exception{
		if(this.company_cust_id!=null && !this.company_cust_id.equals("")){
			memberconfig=this.memberconfigService.get(this.company_cust_id);
		}
		if(this.para_temp_code !=null && !para_temp_code.equals("") && memberconfig!=null){
			memberconfig.setTemp_code(para_temp_code);
		}
		return memberconfig;
	}
	
	//根据用户ID号获取公司新闻信息列表
	public void getnews() throws Exception{
		Map map=new HashMap();
		map.put("cust_id", this.company_cust_id);
		map.put("news_state", 0);
		map.put("start",START);
		map.put("limit",LIMIT);
		newsList=this.membernewsService.getList(map);
	}
	
	//根据用户ID号获取所有新闻列表信息
	public void getallnews() throws Exception{
		Map pageMap=new HashMap();
		pageMap.put("cust_id", this.company_cust_id);
		pageMap.put("news_state", "0");
		//根据页面提交的条件找出信息总数
		int count=this.membernewsService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		allnewsList=this.membernewsService.getList(pageMap);
	}
	
	//根据用户ID号获取公司友情链接列表
	public void getlink() throws Exception{
	    HashMap map=new	HashMap();
		map.put("cust_id", this.company_cust_id);
		map.put("link_state", "0");
		map.put("start",START);
		map.put("limit",LIMIT);
		linkList=this.memberlinkService.getList(map);
	}
	
	//根据用户ID号获取公司相册列表
	public void getgallery() throws Exception{
		Map pageMap=new HashMap();
		pageMap.put("cust_id", this.company_cust_id);
		pageMap.put("info_state", "1");
		//根据页面提交的条件找出信息总数
		int count=this.galleryService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		galleryList=this.galleryService.getList(pageMap);
	}
	
	//根据图库ID号获取公司相册图片
	public List getgallerypic(String gal_id) throws Exception{
		HashMap map=new HashMap();
		map.put("gal_id", gal_id);
		if(gal_id!=null && !gal_id.equals("")){
			gallery=this.galleryService.get(gal_id);
		}
		gallerypicList=this.gallerypicService.getList(map);
		return gallerypicList;
	}
	
	//根据用户ID号获取公司相册图片
	public void getgallerycust() throws Exception{
		HashMap map=new HashMap();
		map.put("cust_id", this.company_cust_id);
		map.put("info_state", "1");
		map.put("start",START);
		if("tea".equals(temp_loc)){
			map.put("limit", EIGLIMIT);
		}
		if("default".equals(temp_loc)){
			map.put("limit",FOURLIMIT);
		}
		gallerycustList=this.galleryService.getList(map);
	}
	
	//根据用户ID号获取公司视频列表
	public void getvideo() throws Exception{
		Map pageMap=new HashMap();
		pageMap.put("cust_id", this.company_cust_id);
		pageMap.put("info_state", "1");
		//根据页面提交的条件找出信息总数
		int count=this.videoService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		videoList=this.videoService.getList(pageMap);
	}
	
	//根据用户ID号获取产品列表
	public void getproduct() throws Exception{	
		//个人企业站-查找栏目产品信息显示的数量
		HashMap countmap=new HashMap();
		countmap.put("cust_id", this.company_cust_id);
		countmap.put("ch_code", "cptj");
		channelList=this.memberchannelService.getList(countmap);
		Map ctmap= new HashMap();
		if(channelList!=null && channelList.size()>0){
			ctmap = (Map)channelList.get(0);
		}
		int  ch_num = 0;
		if(ctmap.get("ch_num")!=null && !ctmap.get("ch_num").toString().equals("")){
			ch_num = Integer.parseInt(ctmap.get("ch_num").toString());
		}
		if(ch_num == 0){
			ch_num = 8;
		}
		HashMap map=new HashMap();
		map.put("cust_id", this.company_cust_id);
		map.put("info_state", "1");
		map.put("start", 0);
		map.put("limit", ch_num);
		recommendProductList=this.productService.getList(map);
		recommendProductList=ToolsFuc.getlastpic(recommendProductList);
		
	}
	
	//根据用户ID号获取企业联系方式
	public Member getmember() throws Exception{
		if(this.company_cust_id!=null && !this.company_cust_id.equals("")){
			member=this.memberService.get(this.company_cust_id);
			if (member.getClient_status() != null && !"".equals(member.getClient_status())) {
				String statusname = com.rbt.function.MemberFuc
						.getStatusName(member.getClient_status());
				member.setClient_status(statusname);
			}
			//企业简介html过滤
			if(member.getCust_desc()!=null && !"".equals(member.getCust_desc())){
				 chinaDesc = ToolsFuc.getChinese(member.getCust_desc());
			}
		}
		return member;
	}
	
	//根据用户ID号获取企业资格证书列表
	public void getmembercert() throws Exception{
		HashMap map=new HashMap();
		map.put("cust_id", this.company_cust_id);
		map.put("cert_state", "0");
		map.put("start",START);
		map.put("limit",LIMIT);
		membercertList=this.membercertService.getList(map);
	}
	
	//根据用户ID号获取企业信用指数记录列表
	public void getcredithistory() throws Exception{
		HashMap map=new HashMap();
		map.put("cust_id", this.company_cust_id);
		map.put("start",START);
		map.put("limit",LIMIT);
		credithistoryList=this.credithistoryService.getList(map);
	}
	
	//根据用户ID号获取最新供应产品
	public void getnewsupply() throws Exception{
		HashMap map=new HashMap();
		map.put("cust_id", this.company_cust_id);
		map.put("info_state", "1");
		map.put("start",START);
		map.put("limit",EIGLIMIT);
		supplyList=this.supplyService.getList(map);
	    supplyList=ToolsFuc.getlastpic(supplyList);
	}
	

	
	//根据用户ID号获取最新供应产品
	public void getallsupply() throws Exception{
		HttpServletRequest request = getRequest();
		//获取url参数
		Map pageMap=new HashMap();
		pageMap.put("cust_id", this.company_cust_id);
		if(selvalue!=null && !"".equals(selvalue)){
			pageMap.put("title", URLDecoder.decode(selvalue, "UTF-8"));
		}
		pageMap.put("info_state", "1");
		//根据页面提交的条件找出信息总数
		int count=this.supplyService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		supplyAllList=this.supplyService.getList(pageMap);
		supplyAllList=ToolsFuc.getlastpic(supplyAllList);
	}
	//根据用户ID号获取产品列表
	public void getallproduct() throws Exception{
		HttpServletRequest request = getRequest();
		//获取url参数
		String cat_id = request.getParameter("cat_id");
		Map pageMap=new HashMap();
		pageMap.put("cust_id", this.company_cust_id);
		if(cat_id!=null){
			pageMap.put("self_cat_id", cat_id);
		}
		if(selvalue!=null && !"".equals(selvalue)){
			pageMap.put("title", URLDecoder.decode(selvalue, "UTF-8"));
		}
		pageMap.put("info_state", "1");
		//根据页面提交的条件找出信息总数
		int count=this.productService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		allproductList=this.productService.getList(pageMap);
		allproductList=ToolsFuc.getlastpic(allproductList);
	}
	
	//根据用户ID号获取求购列表
	public void getallbuy() throws Exception{
		Map pageMap=new HashMap();
		pageMap.put("cust_id", this.company_cust_id);
		pageMap.put("info_state", "1");
		//根据页面提交的条件找出信息总数
		int count=this.buyService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		allbuyList=this.buyService.getList(pageMap);
		allbuyList=ToolsFuc.getlastpic(allbuyList);
	}
	
	//根据新闻ID号获取新闻内容
	public void getnewcontent() throws Exception{
		HttpServletRequest request = getRequest();
		//获取url参数
		String news_id = request.getParameter("news_id");
		if(news_id!=null && !news_id.equals("")){
			membernews=this.membernewsService.get(news_id);
		}
	}
	
	//通过供应ID号获取供应实体
	public void getsupply() throws Exception{
		HttpServletRequest request = getRequest();
		//获取url参数
		String supply_id = request.getParameter("supply_id");
		if(supply_id!=null && !supply_id.equals("")){
			supply=this.supplyService.get(supply_id);
		}
	}
	
	//获取视频详细信息
	public void getintrvideo() throws Exception{
		HttpServletRequest request = getRequest();
		//获取url参数
		String video_id = request.getParameter("video_id");
		if(video_id!=null && !video_id.equals("")){
			video=this.videoService.get(video_id);
		}
	}
	
	
	//在线客服信息
	public void getkefu(){
		memberconfig = memberconfigService.get(this.company_cust_id);
		if(memberconfig==null){
			memberconfig = new Memberconfig();
		}
		//阿里旺旺
		aliim=memberconfig.getAliim();
		if(aliim!=null && !"".equals(aliim)){
			aliim=aliim.replace(" ", "");
			String aliimStr[] = aliim.split(",");		
			if (aliimStr.length > 0) {
				for (int i = 0; i < aliimStr.length; i++) {
					aliimList.add(aliimStr[i]);				
				}
			}
		}
		//MSN
		msn = memberconfig.getMsn();
		//SKYPE
		skype = memberconfig.getSkype();
		//QQ
		qq=memberconfig.getQq();
		if(qq!=null && !"".equals(qq)){
			qq=qq.replace(" ", "");
			String qqStr[] = qq.split(",");		
			if (qqStr.length > 0) {
				for (int i = 0; i < qqStr.length; i++) {
					qqList.add(qqStr[i]);				
				}
			}
		}
		
	}
	
	//字段定义的get  set方法
	public List getNewsList() {
		return newsList;
	}

	public void setNewsList(List newsList) {
		this.newsList = newsList;
	}

	public List getLinkList() {
		return linkList;
	}

	public void setLinkList(List linkList) {
		this.linkList = linkList;
	}

	public List getGalleryList() {
		return galleryList;
	}

	public void setGalleryList(List galleryList) {
		this.galleryList = galleryList;
	}

	public List getGallerypicList() {
		return gallerypicList;
	}

	public void setGallerypicList(List gallerypicList) {
		this.gallerypicList = gallerypicList;
	}

	public List getVideoList() {
		return videoList;
	}

	public void setVideoList(List videoList) {
		this.videoList = videoList;
	}

	public List getPagepartList() {
		return pagepartList;
	}

	public void setPagepartList(List pagepartList) {
		this.pagepartList = pagepartList;
	}

	public List getSidebarList() {
		return SidebarList;
	}

	public void setSidebarList(List sidebarList) {
		SidebarList = sidebarList;
	}

	public Memberconfig getMemberconfig() {
		return memberconfig;
	}

	public void setMemberconfig(Memberconfig memberconfig) {
		this.memberconfig = memberconfig;
	}

	public List getProductList() {
		return productList;
	}

	public void setProductList(List productList) {
		this.productList = productList;
	}

	public IMemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List getMembercertList() {
		return membercertList;
	}

	public void setMembercertList(List membercertList) {
		this.membercertList = membercertList;
	}

	public List getMainbarList() {
		return mainbarList;
	}

	public void setMainbarList(List mainbarList) {
		this.mainbarList = mainbarList;
	}

	public List getSupplyList() {
		return supplyList;
	}

	public void setSupplyList(List supplyList) {
		this.supplyList = supplyList;
	}

	public List getGallerycustList() {
		return gallerycustList;
	}

	public void setGallerycustList(List gallerycustList) {
		this.gallerycustList = gallerycustList;
	}

	public List getSupplyAllList() {
		return supplyAllList;
	}

	public void setSupplyAllList(List supplyAllList) {
		this.supplyAllList = supplyAllList;
	}

	public List getAllnewsList() {
		return allnewsList;
	}

	public void setAllnewsList(List allnewsList) {
		this.allnewsList = allnewsList;
	}

	public Membernews getMembernews() {
		return membernews;
	}

	public void setMembernews(Membernews membernews) {
		this.membernews = membernews;
	}

	public Supply getSupply() {
		return supply;
	}

	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	public Gallery getGallery() {
		return gallery;
	}

	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List getProductCateList() {
		return productCateList;
	}

	public void setProductCateList(List productCateList) {
		this.productCateList = productCateList;
	}

	public List getAllproductList() {
		return allproductList;
	}

	public void setAllproductList(List allproductList) {
		this.allproductList = allproductList;
	}

	public List getAllLink() {
		return allLink;
	}

	public void setAllLink(List allLink) {
		this.allLink = allLink;
	}

	public Membercert getMembercert() {
		return membercert;
	}

	public void setMembercert(Membercert membercert) {
		this.membercert = membercert;
	}

	public List getMembercertallList() {
		return membercertallList;
	}

	public void setMembercertallList(List membercertallList) {
		this.membercertallList = membercertallList;
	}

	public Buy getBuy() {
		return buy;
	}

	public void setBuy(Buy buy) {
		this.buy = buy;
	}

	public List getAllbuyList() {
		return allbuyList;
	}

	public void setAllbuyList(List allbuyList) {
		this.allbuyList = allbuyList;
	}

	public Memberchannel getMemberchannel() {
		return memberchannel;
	}

	public void setMemberchannel(Memberchannel memberchannel) {
		this.memberchannel = memberchannel;
	}

	public String getSelvalue() {
		return selvalue;
	}

	public void setSelvalue(String selvalue) {
		this.selvalue = selvalue;
	}

	public Member getMemberintro() {
		return memberintro;
	}

	public void setMemberintro(Member memberintro) {
		this.memberintro = memberintro;
	}

	public String getSysconfig() {
		return sysconfig;
	}

	public void setSysconfig(String sysconfig) {
		this.sysconfig = sysconfig;
	}

	public String getIsvip() {
		return isvip;
	}

	public void setIsvip(String isvip) {
		this.isvip = isvip;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getSystime() {
		return systime;
	}

	public void setSystime(String systime) {
		this.systime = systime;
	}

	public int getCertnum() {
		return certnum;
	}

	public void setCertnum(int certnum) {
		this.certnum = certnum;
	}

	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	public List getCommparaList() {
		return commparaList;
	}

	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}

	public List getCust_type_List() {
		return cust_type_List;
	}

	public void setCust_type_List(List cust_type_List) {
		this.cust_type_List = cust_type_List;
	}
	
	public String getAreaName() {
		return areaName;
	}
	
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getSysrand() {
		return sysrand;
	}

	public void setSysrand(String sysrand) {
		this.sysrand = sysrand;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List getPagepartlastList() {
		return pagepartlastList;
	}

	public void setPagepartlastList(List pagepartlastList) {
		this.pagepartlastList = pagepartlastList;
	}

	public List getRecommendProductList() {
		return recommendProductList;
	}


	public void setRecommendProductList(List recommendProductList) {
		this.recommendProductList = recommendProductList;
	}

	public String getChinaDesc() {
		return chinaDesc;
	}


	public void setChinaDesc(String chinaDesc) {
		this.chinaDesc = chinaDesc;
	}

	

	public String getTemp_loc() {
		return temp_loc;
	}


	public void setTemp_loc(String temp_loc) {
		this.temp_loc = temp_loc;
	}


	public String getIsmap() {
		return ismap;
	}


	public void setIsmap(String ismap) {
		this.ismap = ismap;
	}


	public Memberuser getMemberuser() {
		return memberuser;
	}


	public void setMemberuser(Memberuser memberuser) {
		this.memberuser = memberuser;
	}


	public Memberuser getMemberuserlog() {
		return memberuserlog;
	}


	public void setMemberuserlog(Memberuser memberuserlog) {
		this.memberuserlog = memberuserlog;
	}


	public String getCommentrand() {
		return commentrand;
	}


	public void setCommentrand(String commentrand) {
		this.commentrand = commentrand;
	}


	public void prepare() throws Exception {
		//获取访问cust_id
		this.company_cust_id = getcustid();
		//是否有企业站
		this.is_shop_company = ifexistscompany(this.company_cust_id);
		//获取模板样式
		temp_loc="default";
		if(company_cust_id!=null && !"".equals(company_cust_id)){
			memberconfig = memberconfigService.get(company_cust_id);
			if(memberconfig!=null){
				temp_loc = memberconfig.getTemp_loc();
			}
		}
		this.setTemp_loc(temp_loc);
		//绑定栏目列表
		if(this.is_shop_company){
			getchannel();
		}

		
	}
	
}
