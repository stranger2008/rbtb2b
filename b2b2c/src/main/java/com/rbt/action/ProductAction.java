/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: ProductAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Category;
import com.rbt.model.Member;
import com.rbt.model.Membercat;
import com.rbt.model.Product;
import com.rbt.model.Sysmodule;
import com.rbt.service.IAreaService;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICategoryattrService;
import com.rbt.service.IChannelService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercatService;
import com.rbt.service.IProductService;
import com.rbt.service.ISysmoduleService;

/**
 * @function 功能 产品表action类
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Jul 25 17:02:42 CST 2011
 */
@Controller
public class ProductAction extends  baseModelAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 产品表对象
	 */
	public Product product;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IProductService productService;
	/*
	 * 产品表信息集合
	 */
	public List productList;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMembercatService membercatService;
	@Autowired
	public ISysmoduleService sysmoduleService;
	/*
	 * 产品标题
	 */
	public String title_s;
	/*
	 * 产品型号
	 */
	public String model_s;
	/*
	 * 产品品牌
	 */
	public String brand_s;
	/*
	 * 内容收费
	 */
	public String fare_s;
	/*
	 * 信息状态
	 */
	public String info_state_s;
	/*
	 * 点击量
	 */
	public String clicknum_s;
	/*
	 * 搜索开始的发布时间
	 */
	public String in_date_s;
	/*
	 * 搜索结束的发布时间
	 */
	public String enddateString_s;
	/*
	 * 显示审核不通过的理由输入框
	 */
	public String noReasonKey;
	/*
	 * 审核产品的属性字段
	 */
	public String auditattrString;
	/*
	 * 会员产品自定义分类List
	 */
	public List membercatList;
	public String productid;
	public String modType = "product";
	/*
	 * 定义今天的日期
	 */
	public String today;
	public String cat_attr_s;
	/*
	 * 定义供应类别表对象
	 */
	public Category category;
	/*
	 * 定义分类属性的Service接口
	 */
	@Autowired
	public ICategoryattrService categoryattrService;
	/*
	 * 定义分类的Service接口属性
	 */
	@Autowired
	public ICategoryService categoryService;
	/*
	 * 定义地区的Service接口属性
	 */
	@Autowired
	public IAreaService areaService;
	@Autowired
	public IChannelService channelService;
	@Autowired
	public IMemberService memberService;


	// 地区搜索
	public String search_area_attr;
	// 分类搜索
	public String search_cat_attr;

	public Member member;
	public String areaname;
	public String cat_name;
	/*
	 * 默认顶级
	 */
	public String up_levle = "1111111111,";
	public CategoryFuc categoryFuc;	
	public Sysmodule sysmodule;
	private String product_infoattr_id;

	
	/**
	 * @MethodDescribe 方法描述 跳转到新增页面还是更新页面的判断
	 * @author 创建人 胡惜坤
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
					product = new Product();
					product.setProduct_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		} else {
			if (this.product != null && this.product.getProduct_id() != null && !this.product.getProduct_id().equals("")){
				return view();
			} else {
				return cate();
			}
		}
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
		int count = this.productService.getCount(tmap);
		if(controlMsgNum(count,modType)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 方法描述：返回新增产品表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cate() throws Exception {
		// 操作者为会员则默认加入搜索条件，绑定会员自定义产品分类下拉框的绑定
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			Map map = new HashMap();
			map.put("cust_id", this.session_cust_id);
			map.put("cat_type", "0");
			membercatList = this.membercatService.getList(map);			
			// 操作者为会员见则从会员表中取出地区数据
			Member mem = this.memberService.get(this.session_cust_id);
			if (mem != null && mem.getArea_attr() != null) {
				backArea(mem.getArea_attr());
			}
			
			//发布信息数量控制
			controlInfoNum();
			
		}
		member = this.memberService.get(this.session_cust_id);
		String areaid = "";
		if (member != null && member.getArea_attr() != null) {
			areaid = member.getArea_attr();
		}		
		if (areaid != null && !areaid.equals("")) {
			areaname = AreaFuc.getAreaNameByMap(areaid);
		} else {
			areaname = "获取地区失败";
		}
		//获取所属模块名是否支持分类属性
		sysmodule = this.sysmoduleService.get("product");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			if(cat_attr == null||cat_attr.equals("0")){
				return goUrl("cate");
			}
			checkIsAttr();				
		}
		return goUrl(ADD);
	}
	
	
	/**
	 * 方法描述：新增产品表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 用于所属分类的回选开始
		loadCategory();
		// 用于所属地区的回选开始
		loadArea();
		//验证分类是选择
		validateCategoryIfSelect();
		this.product.setCat_attr(cat_attr);
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			String areaid = "";
			member = this.memberService.get(this.session_cust_id);
			if (member != null && member.getArea_attr() != null) {
				areaid = member.getArea_attr();
			}
			product.setArea_attr(areaid);
			
			//发布信息数量控制
			if(controlInfoNum()){
				return cate();
			}
		} else {
			//验证地区是选择
			validateAreaIfSelect();
		}
		this.product.setArea_attr(area_attr);
		product.setUser_id(this.session_user_id);
		product.setCust_id(this.session_cust_id);
		product.setNo_reason("");
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("product");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.product.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}
		// 字段验证
		super.commonValidateField(product);
		if (super.ifvalidatepass) {
			return cate();
		}
		String product_id = this.productService.insertGetPk(product,objList);// 获取刚刚插入成功的记录的ID
		// 对插入成功的信息生成HTML静态页面
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, product_id);
		this.addActionMessage("新增产品信息成功");
		this.product = null;

		// 添加商品后，页面留在新增页面，重新加载自定义分类
		// 操作者为会员则默认加入搜索条件，绑定会员自定义产品分类下拉框的绑定
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			Map catmap = new HashMap();
			catmap.put("cust_id", this.session_cust_id);
			catmap.put("cat_type", "0");
			membercatList = this.membercatService.getList(catmap);
		}
		is_crotorl=true;
		return cate();
	}
	

	/**
	 * 方法描述：是否推荐批量修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String subid = this.product.getProduct_id();
		String isrecom = this.product.getIs_recom();
		subid = subid.replace(" ", "");
		String subStr[] = subid.split(",");
		List subList = new ArrayList();
		if (subStr.length > 0) {
			for (int i = 0; i < subStr.length; i++) {
				Map subMap = new HashMap();
				subMap.put("is_recom", isrecom);
				subMap.put("product_id", subStr[i]);
				subList.add(subMap);
			}
		}
		this.productService.updateRecommendState(subList);
		String tip = "";
		if (isrecom.equals("0")) {
			tip = "取消推荐成功";
		} else if (isrecom.equals("1")) {
			tip = "推荐成功";
		}
		this.addActionMessage(tip);
		return list();
	}

	

	/**
	 * 方法描述：修改产品表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String proid = product.getProduct_id();
		if (ValidateUtil.isDigital(proid)) {
			return list();
		}
		// 用于所属分类的回选开始
		loadCategory();
		// 用于所属地区的回选开始
		loadArea();
		//验证分类是选择
		validateCategoryIfSelect();
		// 将处理后的所属分类串注入到buy对象中
		this.product.setCat_attr(cat_attr);
		this.product.setArea_attr(area_attr);
		// 当前登录的用户是管理员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			//验证地区是选择
			validateAreaIfSelect();
			if (product.getInfo_state().equals("2") && ValidateUtil.isRequired(product.getNo_reason())) {
				this.addFieldError("product.no_reason", "请输入审核未通过的理由");
				noReasonKey = "2";
			}
		}
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			String areaid = "";
			member = this.memberService.get(this.session_cust_id);
			if (member != null && member.getArea_attr() != null) {
				areaid = member.getArea_attr();
			}
			product.setArea_attr(areaid);
		}
		if (!product.getInfo_state().equals("2")) {
			product.setNo_reason("");
		}
		product.setUser_id(this.session_user_id);
		// 当前登录的用户是会员的时候，将信息的审核状态变为未审核
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			product.setInfo_state("0");
		}
		
		
		
		// 数据库原有的分类串	
		product_infoattr_id = product.getInfoattr_id();	
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("product");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.product.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}else{
			product_infoattr_id=null;
		}			
		this.product.setCat_attr(cat_attr);
		
		
		
		// 字段验证
		super.commonValidateField(product);
		if (super.ifvalidatepass) {
			return view();
		}
		this.productService.update(product,objList,product_infoattr_id);
		if (this.product.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.product.getProduct_id());
		}
		this.addActionMessage("修改产品信息成功");
		return list();
	}

	/**
	 * 方法描述：审核产品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditState() throws Exception {

		String proid = product.getProduct_id();
		if (ValidateUtil.isDigital(proid)) {
			return list();
		}
		//启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("product",product.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		if (product.getInfo_state() == null
				&& product.getInfo_state().equals("")) {
			this.addFieldError("product.info_state", "请选择审核状态");
			return goUrl(AUDIT);
		}
		if (product.getInfo_state().equals("2") && ValidateUtil.isRequired(product.getNo_reason())) {
			this.addFieldError("product.no_reason", "请输入拒绝理由");
			noReasonKey = "2";
			return audit();
		}
		Map pageMap = new HashMap();
		if (!product.getInfo_state().equals("2")) {
			pageMap.put("no_reason", "");
		} else {
			pageMap.put("no_reason", product.getNo_reason());
		}
		pageMap.put("product_id", product.getProduct_id());
		pageMap.put("info_state", product.getInfo_state());
		this.productService.updateState(pageMap);
		if (this.product.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.product.getProduct_id());
		}
		this.addActionMessage("审核产品信息成功");
		return auditList();
	}

	/**
	 * 方法描述：删除产品表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		del();
		return list();
	}
	public void del(){
		String id = this.product.getProduct_id();
		id = id.replace(" ", "");
		if (id!=null&&!"".equals(id)) {
			id = id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				Product product=this.productService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],product.getIn_date());
				this.productService.delete(ids[i]);
			}
			this.addActionMessage("删除产品信息成功");	
		}
	}
	/**
	 * 方法描述：审核产品 批量删除信息
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 * @date : Jul 9, 2012 9:07:31 AM
	 */
	public String checkDel() throws Exception {
		del();
		return auditList();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		if (title_s != null && !title_s.equals(""))
			pageMap.put("title", title_s);
		if (search_area_attr != null && !search_area_attr.equals(""))
			pageMap.put("area_attr", search_area_attr);
		if (search_cat_attr != null && !search_cat_attr.equals(""))
			pageMap.put("cat_attr", search_cat_attr);
		if (cat_attr_s != null && !cat_attr_s.equals(""))
			pageMap.put("cat_attr", cat_attr_s);
		if (model_s != null && !model_s.equals(""))
			pageMap.put("model", model_s);
		if (brand_s != null && !brand_s.equals(""))
			pageMap.put("brand", brand_s);
		if (fare_s != null && !fare_s.equals(""))
			pageMap.put("fare", fare_s);
		if (in_date_s != null && !in_date_s.equals(""))
			pageMap.put("in_date", in_date_s);
		if (clicknum_s != null && !clicknum_s.equals(""))
			pageMap.put("clicknum", clicknum_s);
		if (in_date_s != null && !in_date_s.equals(""))
			pageMap.put("in_date", in_date_s);
		if (enddateString_s != null && !enddateString_s.equals(""))
			pageMap.put("enddate", enddateString_s);
		if (today != null && !today.equals("")) {
			pageMap.put("today", this.today);
		} else {
			if (info_state_s != null && !info_state_s.equals("")
					&& !info_state_s.equals("4")) {
				pageMap.put("info_state", info_state_s);
			} else {
				// 操作者为会员则默认加入搜索条件
				if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
					pageMap.put("info_state_in", "0,1,2,3");// 审核通过状态
				} else {
					pageMap.put("info_state_in", "1,3");// 审核通过状态
				}
			}
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.productService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		// 找出信息列表，放入list
		productList = this.productService.getList(pageMap);
		productList = com.rbt.function.CategoryFuc.replaceList(productList, "");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出产品表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(product.getCust_id()!=null){
			if(accessControl(product.getCust_id())){
				return list();
			}
		}
		// 操作者为会员则默认加入搜索条件，绑定会员自定义产品分类下拉框的绑定
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			Map map = new HashMap();
			map.put("cust_id", product.getCust_id());
			map.put("cat_type", "0");
			membercatList = this.membercatService.getList(map);
		}
		noReasonKey = product.getInfo_state();
		// 将从数据库中查询的所属分类存入分类隐藏域中
		backCategory(product.getCat_attr());
		// 找出地区字段的存入隐藏值中
		backArea(product.getArea_attr());
		if (product != null && product.getArea_attr() != null
				&& !product.getArea_attr().equals("")) {
			areaname = AreaFuc.getAreaNameByMap(product.getArea_attr());
			area_attr = product.getArea_attr();
		} else {
			areaname = "";
		}
		
		//分类ID转名称
		catIdTocatName(this.product.getCat_attr());
		//根据模块是否启动分类属性
		sysmodule = this.sysmoduleService.get("product");
		if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){			
			if(ischange==null){//第一次加载页面
				info_infoattr_id = this.product.getInfoattr_id();
			}
			getIsCatAttr(info_infoattr_id,product.getCat_attr());	
		}else{
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(product.getCat_attr());	
		}		
		
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：根据主键找出产品审核信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		//进入审核页面时候的加载方法
		auditView("product",product.getProduct_id());
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			// 获取用户自己定义分类的分类名称
			String membercatname = "";
			Membercat membercat = new Membercat();
			// 通过产品的自定义ID到会员自定义分类中找分类名称
			if (product.getSelf_cat_id() != null
					&& !product.getSelf_cat_id().equals("")) {
				membercat = membercatService.get(product.getSelf_cat_id());
			}
			// 判断产品是否选择了自定义分类
			if (membercat != null && membercat.getCat_name() != null) {
				membercatname = membercat.getCat_name();
			}
			product.setSelf_cat_id(membercatname);
		}
		noReasonKey = product.getInfo_state();
		// 获取地区中文字符
		String area_name = "";
		if (product.getArea_attr() != null) {
			area_name = AreaFuc.getAreaNameByMap(product.getArea_attr());
		}
		product.setArea_attr(area_name);
		// 获取分类中文字符
		String cat_id="";
		if (product.getArea_attr() != null) {
			cat_id=product.getCat_attr();
			cat_name = CategoryFuc.getCateNameByMap(cat_id);
		}
		product.setCat_attr(cat_name);

		// 找出属性列表
		String infoattr_id = product.getInfoattr_id();
		sysmodule = this.sysmoduleService.get("product");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			backAttr(infoattr_id,cat_id);
		}		
		return goUrl(AUDIT);
	}

	/**
	 * 方法描述：获取未审核的资讯
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditList() throws Exception {
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (info_state_s != null && !info_state_s.equals("")
				&& !info_state_s.equals("4")) {
			pageMap.put("info_state", info_state_s);
		} else {
			pageMap.put("info_state_in", "0,2");
		}
		if (cat_attr_s != null && !cat_attr_s.equals(""))
			pageMap.put("cat_attr", cat_attr_s);
		if (title_s != null && !title_s.equals("")) {
			pageMap.put("title", title_s);
		}
		if (model_s != null && !model_s.equals("")) {
			pageMap.put("model", model_s);
		}
		if (brand_s != null && !brand_s.equals("")) {
			pageMap.put("brand", brand_s);
		}
		if (fare_s != null && !fare_s.equals("")) {
			pageMap.put("fare", fare_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (clicknum_s != null && !clicknum_s.equals("")) {
			pageMap.put("clicknum", clicknum_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (enddateString_s != null && !enddateString_s.equals("")) {
			pageMap.put("enddate", enddateString_s);
		}
		if (search_area_attr != null && !search_area_attr.equals(""))
			pageMap.put("area_attr", search_area_attr);
		if (search_cat_attr != null && !search_cat_attr.equals(""))
			pageMap.put("cat_attr", search_cat_attr);
		// 根据页面提交的条件找出信息总数
		int count = this.productService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		// 找出信息列表，放入list
		productList = this.productService.getList(pageMap);
		productList = com.rbt.function.CategoryFuc.replaceList(productList, "");
		return goUrl(AUDITLIST);
	}

	/**
	 * @return the ProductList
	 */
	public List getProductList() {
		return productList;
	}

	/**
	 * @param productList
	 *            the ProductList to set
	 */
	public void setProductList(List productList) {
		this.productList = productList;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getModel_s() {
		return model_s;
	}

	public void setModel_s(String model_s) {
		this.model_s = model_s;
	}

	public String getBrand_s() {
		return brand_s;
	}

	public void setBrand_s(String brand_s) {
		this.brand_s = brand_s;
	}

	public String getFare_s() {
		return fare_s;
	}

	public void setFare_s(String fare_s) {
		this.fare_s = fare_s;
	}

	public String getInfo_state_s() {
		return info_state_s;
	}

	public void setInfo_state_s(String info_state_s) {
		this.info_state_s = info_state_s;
	}

	public String getClicknum_s() {
		return clicknum_s;
	}

	public void setClicknum_s(String clicknum_s) {
		this.clicknum_s = clicknum_s;
	}

	public String getIn_date_s() {
		return in_date_s;
	}

	public void setIn_date_s(String in_date_s) {
		this.in_date_s = in_date_s;
	}

	public String getEnddateString_s() {
		return enddateString_s;
	}

	public void setEnddateString_s(String enddateString_s) {
		this.enddateString_s = enddateString_s;
	}

	public String getNoReasonKey() {
		return noReasonKey;
	}

	public void setNoReasonKey(String noReasonKey) {
		this.noReasonKey = noReasonKey;
	}

	public String getAuditattrString() {
		return auditattrString;
	}

	public void setAuditattrString(String auditattrString) {
		this.auditattrString = auditattrString;
	}

	public List getMembercatList() {
		return membercatList;
	}

	public void setMembercatList(List membercatList) {
		this.membercatList = membercatList;
	}

	public String getCat_attr() {
		return cat_attr;
	}

	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}

	public String getArea_attr() {
		return area_attr;
	}

	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}

	public String getHidden_area_value() {
		return hidden_area_value;
	}

	public void setHidden_area_value(String hidden_area_value) {
		this.hidden_area_value = hidden_area_value;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the today
	 */
	public String getToday() {
		return today;
	}

	/**
	 * @param today
	 *            the today to set
	 */
	public void setToday(String today) {
		this.today = today;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getSearch_area_attr() {
		return search_area_attr;
	}

	public void setSearch_area_attr(String search_area_attr) {
		this.search_area_attr = search_area_attr;
	}

	public String getSearch_cat_attr() {
		return search_cat_attr;
	}

	public void setSearch_cat_attr(String search_cat_attr) {
		this.search_cat_attr = search_cat_attr;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param Product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (product == null) {
			product = new Product();
		}
		String id = product.getProduct_id();
		if (!ValidateUtil.isDigital(id)) {
			product = this.productService.get(id);
		}
	}

}
