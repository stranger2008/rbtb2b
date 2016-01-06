/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: GoodsAction.java 
 */
package com.rbt.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.function.CategoryFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Goods;
import com.rbt.model.Membercat;
import com.rbt.model.Shopconfig;
import com.rbt.model.Sysmodule;
import com.rbt.service.ICommparaService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsbrandService;
import com.rbt.service.IMalllevelsetService;
import com.rbt.service.IMembercatService;
import com.rbt.service.IShopconfigService;
import com.rbt.service.ISysmoduleService;
import com.opensymphony.xwork2.Preparable;
import com.rbt.service.ICategoryattrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 商品表action类
 * @author 创建人 林俊钦
 * @date 创建日期 Mon Feb 27 11:28:48 CST 2012
 */
@Controller
public class GoodsAction extends baseModelAction implements Preparable{
	
	private static final long serialVersionUID = 1L;	
	
	/*
	 * 商品表对象
	 */
	private Goods goods;
	
	/*
	 * 商品表业务层接口
	 */
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IGoodsbrandService goodsbrandService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public ICategoryattrService categoryattrService;
	@Autowired
	private IMalllevelsetService malllevelsetService;
	@Autowired
	private IMembercatService membercatService;
	@Autowired
	private IShopconfigService shopconfigService;
	@Autowired
	public ISysmoduleService sysmoduleService;

	
	public Sysmodule sysmodule;
	public CategoryFuc categoryFuc;
	public List brandList;
	public List malllevelList;
	public List commparalist;
	public List relateList;
	public List giveList;
	public String noReasonKey;
	private Shopconfig shopconfig;
	/*
	 * 会员自定义分类表信息集合
	 */
	public List membercatList;
	
	
	/*
	 * 定义常量字段,用于从基本参数中找出相应的模块值
	 */
	public String para_code = "glable";
	
	
	/*
	 * 商品表信息集合
	 */
	public List goodsList;
	public String is_select;
	public String update_value;
	public String cate_name;
	public String attr_value;
	public String product_attr_id;
	public String product_attr_is_must;
	public String product_attr_name;
	public String product_attr_type;
	public String memName;
	public String memPrice;
	public String priceValue;
	public String cust_name_s;
	public String goodsno_s;
	public String name_s;
	public String brand_s;
	public String ship_s;
	public String state_s;
	public String relate_name;
	public String relate_id;
	public String give_name;
	public String give_id;
	public String if_calcu;
	public String relate_id_str;
	public String give_id_str;
	public String m_discount;
	public String s_discount;
	public String mem_discount;
	public String down_date;
	public String up_date_s;
	//判断待上架的商品
	public String upId;
	public String  tuiId;
	public String  cuId;
	public String hidden_membercat_value;
	public String membercat_attr;
	public String membercat_attr_name;
	public String modType="goods";
	//查看是否网站默认主销售为虚拟商品
	public String virtual_type = "";	
	
	/**
	 * @return the goods
	 */
	public Goods getGoods() {
		return goods;
	}
	
	/**
	 * @param Goods
	 *            the goods to set
	 */
	public void setGoods(Goods goods) {
		this.goods = goods;
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
		int count = this.goodsService.getCount(tmap);
		if(controlMsgNum(count,modType)){
			return true;
		}else{
			return false;
		}
	}

	
	
		
	/**
	 * @author : 林俊钦
	 * @date : Mar 29, 2012 8:13:30 PM
	 * @Method Description :添加商品分类选择方法
	 */
	public String cate() {
		// 获取系统时间精确到毫秒产生商品编号
		String autoGoodsNumber ="GN-"+(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		this.goods.setGoods_no(autoGoodsNumber);
		//获取所属模块名是否支持分类属性开始
		sysmodule = this.sysmoduleService.get("goods");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			if(cat_attr == null||cat_attr.equals("0")){
				return goUrl("cate");
			}
			checkIsAttr();
		}
		getmemList();
		getdiscountPrice();
		//获取店铺配置信息
		shopconfig=this.shopconfigService.GetByCustId(this.session_cust_id);
		return goUrl(ADD);
	}
	
	
	/**
	 * @MethodDescribe 方法描述 跳转到新增页面还是更新页面的判断
	 * @author 创建人 林俊钦
	 * @throws Exception
	 * @date 创建日期 Oct 24, 2011 11:15:41 AM
	 */
	public String gopage() throws Exception {
		 if (ValidateUtil.isRequired(cat_attr)) {
				this.addFieldError("cate_attr", "分类不能为空");
				return goUrl("cate");	
		 }
		 if (cat_attr.indexOf("0") > -1) {
				if (update_value != null && !update_value.equals("")) {					
					is_select = "1";
					goods = new Goods();
					goods.setGoods_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		 } else {
			if (this.goods != null && this.goods.getGoods_id() != null && !this.goods.getGoods_id().equals("")){
				return view();
			} else {
				return cate();
			}
		 }
	}
	
	
	
	/**
	 * 方法描述：返回新增商品表页面
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			controlInfoNum();
		}
		if(cat_attr == null){
			return goUrl("cate");
		}
		getmemList();
		getdiscountPrice();
		//获取店铺配置信息
		shopconfig=this.shopconfigService.GetByCustId(this.session_cust_id);
		return goUrl(ADD);
	}

	
	/**
	 * @author : 林俊钦
	 * @date : Mar 29, 2012 8:17:46 PM
	 * @Method Description :获取不同会员，不同销售，市场价的价格
	 */
	public void getdiscountPrice(){
		// 获取市场价与销售价的
		m_discount = SysconfigFuc.getSysValue("cfg_marketprice");
		s_discount = SysconfigFuc.getSysValue("cfg_saleprice");
		// 获取不同等级会员的价格
		Map map=new HashMap();
		map.put("mem_type","1");
		StringBuffer sb=new StringBuffer();
		List list=this.malllevelsetService.getList(map);
		for(int i=0;i<list.size();i++){
			String discount="",level_name="";
			HashMap listMap=new HashMap(); 
			listMap=(HashMap)list.get(i);
			if(listMap.get("level_name")!=null){
				level_name=listMap.get("level_name").toString();
			}
			if(listMap.get("discount")!=null){
				discount=listMap.get("discount").toString();
			}
			sb.append(level_name);
			sb.append(":");
			sb.append(discount);
			if(i!=list.size()-1){
				sb.append(",");
			}
		}
		mem_discount=sb.toString();
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Mar 1, 2012 4:05:27 PM
	 * @Method Description :初始化信息集合
	 */
	public void getmemList(){
		// 获取会员等级列表
		Map levelMap=new HashMap();
		levelMap.put("mem_type","1");
		malllevelList=this.malllevelsetService.getList(levelMap);		
		// 分类ID串转成名称
		cat_attr = cat_attr.replace(" ", "");
		cate_name = categoryFuc.getCateNameByMap(cat_attr).replace(" ", "");		
		// 绑定品牌列表
		Map brandMap=new HashMap();
		brandMap.put("info_state", "0");
		brandList=this.goodsbrandService.getList(brandMap);
		// 加载商品类型
		Map map = new HashMap();
		map.put("para_code", para_code);
		commparalist = commparaService.getList(map);		
	}
	

	/**
	 * 方法描述：新增商品表
	 * 
	 * @return
	 * @throws Exception
	 */

	public String insert() throws Exception {
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return cate();
			}
		}
     	loadMemberCat();
		priceValue=getMemPrice("","");
		goods.setCust_id(this.session_cust_id);
		goods.setUser_id(this.session_user_id);
		goods.setMem_price(priceValue);
		if(membercat_attr!=null&&!"".equals(membercat_attr))
		{
			membercat_attr=membercat_attr.replace(" ", "");
		}
		//商品自定义分类
		goods.setSelf_cat(membercat_attr);
		String relate_str=this.relate_id;
		String give_str=this.give_id;
		//获取相关商品
		if(relate_str.lastIndexOf(",")>-1){
			relate_str=relate_str.replace(" ","");
			String lashchar=relate_str.substring((relate_str.length()-1),relate_str.length());
			int len=0;
			if(lashchar.equals(",")){
				len=relate_str.length()-1;
			}			
			relate_str=relate_str.substring(0,len);
		}
		goods.setRelate_goods(relate_str);
		//获取赠品相关
		if(give_str.lastIndexOf(",")>-1){
			give_str=give_str.replace(" ","");
			String lashchar=give_str.substring((give_str.length()-1),give_str.length());
			int len=0;
			if(lashchar.equals(",")){
				len=give_str.length()-1;
			}			
			give_str=give_str.substring(0,len);
		}
		goods.setGive(give_str);
		// 存入商品的分类
		this.goods.setCat_attr(cat_attr);	
		//上架时间
		if(goods.getUp_date()==null||goods.getUp_date().equals("")){
			String datenow =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			goods.setUp_date(datenow);
		}
		//下架时间
		if(goods.getDown_date()==null||goods.getDown_date().equals("")){
			goods.setDown_date("3000-10-17");
		}
		//总库存数
		if(goods.getTotal_stock()==null||goods.getTotal_stock().equals("")){
			goods.setTotal_stock("0");
		}
		//当前库存数
		if(goods.getNow_stock()==null||goods.getNow_stock().equals("")){
			goods.setNow_stock("0");
		}
		//警告库存数
		if(goods.getWarn_stock()==null||goods.getWarn_stock().equals("")){
			goods.setWarn_stock("0");
		}
		//销售量
		if(goods.getSaled_num()==null||goods.getSaled_num().equals("")){
			goods.setSaled_num("0");
		}
		//赠送积分
		if(goods.getGive_inter()==null||goods.getGive_inter().equals("")){
			goods.setGive_inter("0");
		}
		//使用积分购买数
		if(goods.getInterbuy()==null||goods.getInterbuy().equals("")){
			goods.setInterbuy("0");
		}
		//商品重量
		if(goods.getWeight()==null||goods.getWeight().equals("")){
			goods.setWeight("0");
		}
		//成本价
		if(goods.getCost_price()==null||goods.getCost_price().equals("")){
			goods.setCost_price("0");
		}
		//市场价
		if(goods.getMarket_price()==null||goods.getMarket_price().equals("")){
			goods.setMarket_price("0");
		}
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("goods");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.goods.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}

		super.commonValidateField(goods);
		if(super.ifvalidatepass){
			return cate();
		}	
		this.goodsService.insertGetPk(goods, objList);
		is_crotorl=true;
		goods=new Goods();
		priceValue="";
		this.addActionMessage("新增商品表成功！");
		is_crotorl=true;
		return cate();
		
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Feb 29, 2012 4:17:35 PM
	 * @Method Description :
	 */
	public String getMemPrice(String memNameStr,String memPriceStr){
		StringBuffer sb=new StringBuffer();
		if(memNameStr!=null&&!memNameStr.equals("")&&memPriceStr!=null&&!memPriceStr.equals("")){
			memName=memNameStr;
			memPrice=memPriceStr;
		}
		if(memName!=null&&!memName.equals("")&&memPrice!=null&&!memPrice.equals("")){
			memName=memName.replace(" ","");
			String[] m_name=memName.split(",");
			String[] m_price=memPrice.split(",");
			for (int i = 0; i < m_name.length; i++) {
				String id="",val="";
				if(m_name[i]!=null){
					id = m_name[i];
				}
				if(m_price[i]!=null){
					val = m_price[i];
				}
				if(i==m_name.length-1){
					sb.append(id + "|" + val);	
				}else{
					sb.append(id + "|" + val+",");	
				}							
			}			
		}
		return sb.toString();
	}
	

	
	/**
	 * @author : 林俊钦  通过成本价计算会员价格，市场价格，销售价
	 * @date : Mar 1, 2012 10:24:59 AM
	 * @Method Description :
	 */
	public String calculate() throws Exception{
		String cost_price=goods.getCost_price();
		if(cost_price==null){
			if(this.goods.getGoods_id()!=null&&!this.goods.getGoods_id().equals("")){
				return view();
			}else{
				return add();
			}		
		}
		if(cost_price.indexOf(",")>-1){
			int len=cost_price.indexOf(",");
			cost_price=cost_price.substring(0,len);
		}
		float c_price=Float.parseFloat(cost_price);
		float m_price=0.2f,s_price=0.2f;
		m_price=(float) (c_price*Integer.parseInt(m_discount)*0.01);
		s_price=(float) (c_price*Integer.parseInt(s_discount)*0.01);
		goods.setMarket_price(String.valueOf(m_price));
		goods.setSale_price(String.valueOf(s_price));
		Map map=new HashMap();
		map.put("mem_type","1");
		StringBuffer sb=new StringBuffer();
		StringBuffer sn=new StringBuffer();
		List list=this.malllevelsetService.getList(map);
		for(int i=0;i<list.size();i++){
			String discount="",level_name="";
			HashMap listMap=new HashMap();
			listMap=(HashMap)list.get(i);
			if(listMap.get("level_name")!=null){
				level_name=listMap.get("level_name").toString();
			}
			if(listMap.get("discount")!=null){
				discount=listMap.get("discount").toString();
			}
			float mem_price = 0.2f;
			mem_price=(float) (s_price*Integer.parseInt(discount)*0.01);
			sn.append(level_name);
			sb.append(mem_price);
			if(i!=list.size()-1){
				sn.append(",");
				sb.append(",");
			}
		}
		priceValue=getMemPrice(sn.toString(),sb.toString());
		if(this.goods.getGoods_id()!=null&&!this.goods.getGoods_id().equals("")){
			return view();
		}else{
			return add();
		}		
	}

	/**
	 * 方法描述：修改商品表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		loadMemberCat();
		priceValue=getMemPrice("","");
		goods.setCust_id(this.session_cust_id);
		goods.setUser_id(this.session_user_id);
		goods.setSelf_cat(membercat_attr);
		goods.setMem_price(priceValue);
		// 存入商品的分类
		this.goods.setCat_attr(cat_attr);	
		goods.setRelate_goods(this.relate_id_str);
		goods.setGive(this.give_id_str);
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("goods");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.goods.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}				
		//上架时间
		if(goods.getUp_date()==null||goods.getUp_date().equals(""))
		{
			String datenow =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			goods.setUp_date(datenow);
		}
		//下架时间
		if(goods.getDown_date()==null||goods.getDown_date().equals(""))
		{
			goods.setDown_date("3000-10-17");
		}
		//总库存数
		if(goods.getTotal_stock()==null||goods.getTotal_stock().equals(""))
		{
			goods.setTotal_stock("0");
		}
		//当前库存数
		if(goods.getNow_stock()==null||goods.getNow_stock().equals(""))
		{
			goods.setNow_stock("0");
		}
		//警告库存数
		if(goods.getWarn_stock()==null||goods.getWarn_stock().equals(""))
		{
			goods.setWarn_stock("0");
		}
		//销售量
		if(goods.getSaled_num()==null||goods.getSaled_num().equals(""))
		{
			goods.setSaled_num("0");
		}
		//赠送积分
		if(goods.getGive_inter()==null||goods.getGive_inter().equals(""))
		{
			goods.setGive_inter("0");
		}
		//使用积分购买数
		if(goods.getInterbuy()==null||goods.getInterbuy().equals(""))
		{
			goods.setInterbuy("0");
		}
		//商品重量
		if(goods.getWeight()==null||goods.getWeight().equals(""))
		{
			goods.setWeight("0");
		}
		//成本价
		if(goods.getCost_price()==null||goods.getCost_price().equals(""))
		{
			goods.setCost_price("0");
		}
		//市场价
		if(goods.getMarket_price()==null||goods.getMarket_price().equals(""))
		{
			goods.setMarket_price("0");
		}
		
		super.commonValidateField(goods);
		if(super.ifvalidatepass){
			return view();
		}	
		this.goodsService.update(goods, objList, info_infoattr_id);
		goods=new Goods();
		priceValue="";
		this.addActionMessage("修改商品表成功！");
		return list();
	}
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.UnsupportedEncodingException
	 * @date : Apr 5, 2012 1:30:57 PM
	 * @Method Description :批量上架方法
	 */
	public String updateshelves() throws UnsupportedEncodingException{
		String goods_id=this.goods.getGoods_id();
		List list=new ArrayList();
		if(goods_id!=null&&!goods_id.equals("")&&up_date_s!=null&&!up_date_s.equals("")){
			String[] id_str=goods_id.split(",");			
			for(int i=0;i<id_str.length;i++){
				Map map = new HashMap();
				map.put("goods_id", id_str[i].trim());
				map.put("up_date", up_date_s);
				if(down_date!=null&&!down_date.equals("")){
					map.put("down_date", down_date);
				}else{
					map.put("down_date", "2050-12-31");
				}			
				list.add(map);
			}
		}
		this.down_date=null;
		this.goodsService.updateShelves(list);
		return shelvesList();		
	}
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.UnsupportedEncodingException
	 * @date : Apr 5, 2012 4:18:53 PM
	 * @Method Description :批量下架方法
	 */
	public String updatedownshelves() throws UnsupportedEncodingException{
		String goods_id=this.goods.getGoods_id();
		List list=new ArrayList();
		if(goods_id!=null&&!goods_id.equals("")){
			String[] id_str=goods_id.split(",");			
			for(int i=0;i<id_str.length;i++){
				Map map = new HashMap();
				map.put("goods_id", id_str[i].trim());	
				list.add(map);
			}
		}
		this.down_date=null;
		this.goodsService.updatedownshelves(list);
		return shelvesList();		
	}
	
	
	
	
	/**
	 * 方法描述：删除商品表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.goods.getGoods_id();
		id = id.replace(" ", "");
		this.goodsService.delete(id);
		this.addActionMessage("删除商品表成功！");
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
		pageMap=setGoodsMap(pageMap);
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("state_in","1,3");
		}
		// 搜索库存不足商品
		if (upId != null && !upId.equals("")) {
			pageMap.put("warn_stock", "0");
		}
		// 搜索推荐商品
		if (tuiId != null && !tuiId.equals("")) {
			pageMap.put("label","c");
		}
		// 搜索促销商品
		if (cuId != null && !cuId.equals("")) {
			pageMap.put("label","f");
		}
		//商城地区过滤
		pageMap=super.shopareafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.goodsService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		goodsList = this.goodsService.getList(pageMap);
		goodsList = categoryFuc.replaceList(goodsList, "");
		return goUrl(INDEXLIST);
	}
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.UnsupportedEncodingException
	 * @date : Apr 1, 2012 3:53:36 PM
	 * @Method Description : 上下架管理
	 */
	public String shelvesList() throws UnsupportedEncodingException{
		Map pageMap = new HashMap();	
		pageMap=setGoodsMap(pageMap);
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("state_in","1,3");
		}
		// 搜索待上架商品
		if (upId != null && !upId.equals("")) {
			pageMap.put("up_date", "0");
		}
		//根据页面提交的条件找出信息总数
		int count=this.goodsService.getCount(pageMap);		
		//分页插件shelvesList.ftl
		pageMap = super.pageTool(count,pageMap);		
		goodsList = this.goodsService.getList(pageMap);
		goodsList = categoryFuc.replaceList(goodsList, "");
		return goUrl("shelvesList");		
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Mar 15, 2012 2:25:01 PM
	 * @Method Description :审核商品列表
	 */
	public String auditList() throws Exception {
		Map pageMap = new HashMap();	
		pageMap=setGoodsMap(pageMap);
		pageMap.put("state_in","0,2");
		//商城地区过滤
		pageMap=super.shopareafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.goodsService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		goodsList = this.goodsService.getList(pageMap);
		goodsList = categoryFuc.replaceList(goodsList, "");
		return goUrl(AUDITLIST);
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Mar 15, 2012 2:25:19 PM
	 * @Method Description : 审核商品
	 */
	public String audit() throws Exception {
		String id = this.goods.getGoods_id();
		if(id==null || id.equals("")){
			goods = new Goods();
		}else{
			goods = this.goodsService.get(id);
		}		
		//进入审核页面时候的加载方法
		auditView("goods",goods.getGoods_id());
		String cate_attr = "";
		// 判断用户是否重选所属分类
		if (ValidateUtil.isRequired(cat_attr)) {
			// 根据ID获取所属分类串
			cate_attr =goods.getCat_attr();			
		} else {
			cate_attr = cat_attr.replace(" ", "");						
		}
		//找出属性值列表
		cat_attr=cate_attr;
		attr_value=goods.getSize_attr();
		priceValue=goods.getMem_price();
		give_id=goods.getGive();
		relate_id=goods.getRelate_goods();
		membercat_attr_name=getMemberCatName(goods.getSelf_cat());
		//当商品ID串为空时，则不搜索
		if(relate_id!=null&&!relate_id.equals("")){
			Map map=new HashMap();
			map.put("ids", relate_id);
			relateList=this.goodsService.getRelateList(map);
		}
		//当商品ID串为空时，则不搜索
		if(give_id!=null&&!give_id.equals("")){
			Map giveMap=new HashMap();
			giveMap.put("ids", give_id);
			giveList=this.goodsService.getRelateList(giveMap);
		}
		getmemList();
		// 找出属性列表
		String infoattr_id = goods.getInfoattr_id();
		sysmodule = this.sysmoduleService.get("goods");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			backAttr(infoattr_id,goods.getCat_attr());
		}	
		return goUrl(AUDIT);
	}
	/**
	 * 方法描述：根据自定分类ID串转换为自定义名称
	 * 胡惜坤
	 * @return
	 * @throws Exception
	 */
    public String getMemberCatName(String cat_id)
    {
    	String cat_nameString="";
    	
    	if(cat_id!=null&&!"".equals(cat_id)&&!"0".equals(cat_id))
    	{
    		String cat_ids[]=cat_id.split(",");
    		if(cat_ids!=null&&cat_ids.length>0)
    		{
    			for(int i=0;i<cat_ids.length;i++)
    			{
	    			 Membercat mc=new Membercat();
	    			 mc=membercatService.get(cat_ids[i]);
    			     if(mc!=null&&mc.getCat_name()!=null)
    			     {
    			    	 cat_nameString+=mc.getCat_name()+",";
    			     }
    			}
    		}
    	}
    	if(cat_nameString!=null&&!"".equals(cat_nameString))
    	{
    		cat_nameString=cat_nameString.substring(0, cat_nameString.length()-1);
    	}
    	return cat_nameString;
    }
	/**
	 * 方法描述：根据主键找出商品表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(goods.getCust_id()!=null){
			if(accessControl(goods.getCust_id())){
				return list();
			}
		}
		//获取商品对象
		String id = this.goods.getGoods_id();
		if(id==null || id.equals("")){
			goods = new Goods();
		}else{
			goods = this.goodsService.get(id);
		}		
		//获取店铺配置信息
		shopconfig=this.shopconfigService.GetByCustId(this.session_cust_id);		
		String cate_attr = "";
		// 判断用户是否重选所属分类
		if (ValidateUtil.isRequired(cat_attr)) {
			// 根据ID获取所属分类串
			cate_attr =goods.getCat_attr();			
		} else {
			cate_attr = cat_attr.replace(" ", "");						
		}
		// 找出地区字段的存入隐藏值中
		backMemberCat(goods.getSelf_cat());
		membercat_attr_name=getMemberCatName(goods.getSelf_cat());
		//分类ID转名称
		catIdTocatName(cate_attr);
		//根据模块是否启动分类属性
		sysmodule = this.sysmoduleService.get("goods");
		if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){	
			if(ischange==null){//第一次加载页面
				info_infoattr_id = this.goods.getInfoattr_id();
			}
			getIsCatAttr(info_infoattr_id,cate_attr);			
		}else{
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(cat_attr);	
		}		
		//会员价格
		if(if_calcu==null||if_calcu.equals("")){
			priceValue=goods.getMem_price();
		}		
		//赠品ID
		give_id=goods.getGive();
		//相关商品ID
		relate_id=goods.getRelate_goods();
		if(relate_id_str==null||relate_id_str.equals("")){
			relate_id_str=relate_id;			
		}
		//当相关商品ID串为空时，则不搜索
		if(relate_id_str!=null&&!relate_id_str.equals("")){
			Map map=new HashMap();
			map.put("ids", relate_id_str);
			relateList=this.goodsService.getRelateList(map);
		}
		//当赠品商品ID串为空时，则不搜索
		if(give_id_str==null||give_id_str.equals("")){
			give_id_str=give_id;
		}
		//当商品ID串为空时，则不搜索
		if(give_id_str!=null&&!give_id_str.equals("")){
			Map giveMap=new HashMap();
			giveMap.put("ids", give_id_str);
			giveList=this.goodsService.getRelateList(giveMap);
		}
		//获取会员等级列表
		getmemList();
		//获取折扣价格
		getdiscountPrice();
		return goUrl(VIEW);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Mar 15, 2012 5:15:27 PM
	 * @Method Description : 审核商品信息
	 */
	public String auditState() throws Exception {
		String info_state = "", no_reason = "";
		String id = this.goods.getGoods_id();
		if (id == null || id.equals("")) {
			return auditList();
		}
		//启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("goods",goods.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		// 获取数据库对象
		Goods gd = this.goodsService.get(id);
		if (this.goods.getInfo_state() != null && !this.goods.getInfo_state().equals("")) {
			info_state = this.goods.getInfo_state();
			// 设置状态值
			gd.setInfo_state(info_state);
		}
		if (this.goods.getNo_reason() != null) {
			no_reason = this.goods.getNo_reason();
			// 设置拒绝理由
			gd.setNo_reason(no_reason);
		}
		// 更新数据库供应列表
		this.goodsService.update(gd);
		this.addActionMessage("审核商品信息成功");
		return auditList();
	}
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.UnsupportedEncodingException
	 * @date : Mar 5, 2012 10:57:40 AM
	 * @Method Description :搜索相关商品的列表
	 */
	public String ralatelist() throws UnsupportedEncodingException{
		Map pageMap = new HashMap();	
		pageMap=setGoodsMap(pageMap);
		pageMap.put("state_in","1,3");
		//根据页面提交的条件找出信息总数
		int count=this.goodsService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		goodsList = this.goodsService.getList(pageMap);
		goodsList = categoryFuc.replaceList(goodsList, "");
		return INPUT;	
	}
	
	private Map setGoodsMap(Map pageMap) throws UnsupportedEncodingException{
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		// 搜索会员名称
		if (this.cust_name_s != null && !this.cust_name_s.equals("")) {
			pageMap.put("cust_name_s", this.cust_name_s);
		}
		// 搜索的商品编号
		if (this.goodsno_s != null && !this.goodsno_s.equals("")) {
			pageMap.put("goodsno_s", this.goodsno_s);
		}
		// 搜索的商品名称
		if (this.name_s != null && !this.name_s.equals("")) {
			pageMap.put("name_s", this.name_s);
		}
		// 搜索的品牌名称
		if (this.brand_s != null && !this.brand_s.equals("")) {
			pageMap.put("brand_s", this.brand_s);
		}
		// 是否免运费
		if (this.ship_s != null && !this.ship_s.equals("")) {
			pageMap.put("ship_s", this.ship_s);
		}
		// 搜索的状态
		if (this.state_s != null && !this.state_s.equals("")) {
			pageMap.put("state_s", this.state_s);
		}
		// 获取搜索的所属分类
		if (request.getParameter("cat_attr_s") != null) {
			String cat_attr = request.getParameter("cat_attr_s");
			pageMap.put("cat_attr", cat_attr);
		}
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}		
		return pageMap;
	}
	
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.UnsupportedEncodingException
	 * @date : Mar 5, 2012 10:57:40 AM
	 * @Method Description :搜索相关商品的列表
	 */
	public String givelist() throws UnsupportedEncodingException{
		Map pageMap = new HashMap();	
		pageMap=setGoodsMap(pageMap);
		pageMap.put("state_in","1,3");
		//根据页面提交的条件找出信息总数
		int count=this.goodsService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		goodsList = this.goodsService.getList(pageMap);
		goodsList = categoryFuc.replaceList(goodsList, "");
		return INPUT;	
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Mar 16, 2012 12:52:10 PM
	 * @Method Description :团购商品查找页
	 */
	public String grouplist() throws UnsupportedEncodingException{
		Map pageMap = new HashMap();	
		pageMap=setGoodsMap(pageMap);
		pageMap.put("state_in","1,3");
		//根据页面提交的条件找出信息总数
		int count=this.goodsService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		goodsList = this.goodsService.getList(pageMap);
		goodsList = categoryFuc.replaceList(goodsList, "");
		return INPUT;	
	}
	

	/**
	 * @return the GoodsList
	 */
	public List getGoodsList() {
		return goodsList;
	}
	
	/**
	 * @param goodsList
	 *  the GoodsList to set
	 */
	public void setGoodsList(List goodsList) {
		this.goodsList = goodsList;
	}
	
	public Shopconfig getShopconfig() {
		return shopconfig;
	}

	public void setShopconfig(Shopconfig shopconfig) {
		this.shopconfig = shopconfig;
	}

	public void prepare() throws Exception {
		virtual_type = SysconfigFuc.getSysValue("cfg_virtual_plat");	
		super.saveRequestParameter();
		if(goods == null){
			goods = new Goods();
		}
		String id = this.goods.getGoods_id();
		if(!this.validateFactory.isDigital(id)){
			goods = this.goodsService.get(id);
			if(goods.getIs_virtual()!=null && !goods.getIs_virtual().equals("")){
				virtual_type=goods.getIs_virtual();
			}
		}
	}
}

