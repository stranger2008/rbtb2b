/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: BrandAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.function.AreaFuc;
import com.rbt.function.AuditModelFuc;
import com.rbt.function.CategoryAttrFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Brand;
import com.rbt.model.Download;
import com.rbt.model.Member;
import com.rbt.service.IBrandService;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberService;
import com.rbt.model.Sysmodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 品牌信息action类
 * @author 创建人 邱景岩
 * @date 创建日期 Tue Nov 08 09:15:10 CST 2011
 */
@Controller
public class BrandAction extends baseModelAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 搜索字段 title_s:品牌名称 cust_name_s:会员名称 in_date_s:最小发布日期 end_date_s:最大发布日期
	 * info_state_s:品牌状态 is_recom_s:推荐 search_area_attr:搜索地区字段
	 * search_cat_attr：搜索分类字段
	 */
	public String title_s;
	public String cust_name_s;
	public String is_recom_s;
	public String info_state_s;
	public String in_date_s;
	public String end_date_s;
	public String area_attr_s;
	public String cat_attr_s;
	public String modType="brand";
	/*
	 * 信息状态标识
	 */
	public String infostateTip;
	/*
	 * 品牌信息对象
	 */
	public Brand brand;
	/*
	 * 会员信息对象
	 */
	public Member member;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IBrandService brandService;
	/*
	 * 分类业务层接口
	 */
	@Autowired
	public ICategoryService categoryService;
	/*
	 * 会员业务层接口
	 */
	@Autowired
	public IMemberService memberService;
	@Autowired
	public ICommparaService commparaService;
	/*
	 * 品牌信息信息集合
	 */
	public List brandList;
	public String today;
	/*
	 * 系统模块对象
	 */
	public Sysmodule sysmodule;
	/*
	 * 定义常量字段,用于从基本参数中找出相应的模块值
	 */
	public String para_code = "brand_type";
	public List commparalist;

	/**
	 * 方法描述：返回新增品牌信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增品牌信息
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
		this.brand.setCat_attr(cat_attr);
		if (!this.session_cust_id.equals("")) {
			member = this.memberService.get(this.session_cust_id);
		}
		brand.setCust_id(this.session_cust_id);
		//判断会员后台还是管理员后台类型
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			//验证地区是选择
			validateAreaIfSelect();
			this.brand.setArea_attr(area_attr);
		} else {
			this.brand.setArea_attr(member.getArea_attr());
			if(controlInfoNum()){
				return cate();
			}
		}
		brand.setUser_id(this.session_user_id);
		
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("brand");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.brand.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}
		//字段验证
		super.commonValidateField(brand);
		if(super.ifvalidatepass){
			return cate();
		}
		this.brandService.insertGetPk(brand,objList);	//DAO层重载insertGetPk方法	
		this.addActionMessage("新增品牌信息成功");
		this.brand = null;
		is_crotorl=true;
		return cate();
	}

	/**
	 * 方法描述：修改品牌信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = this.brand.getBrand_id();
		if (ValidateUtil.isDigital(id)) {
			return list();
		}
        // 用于所属分类的回选开始
		loadCategory();
		// 用于所属地区的回选开始
		loadArea();		
		//验证分类是选择
		validateCategoryIfSelect();
		// 将处理后的所属分类串注入到member对象中
		this.brand.setCat_attr(cat_attr);
		// 用于所属分类结束
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			//验证地区是选择
			validateAreaIfSelect();			
		} else {//后台类型为会员后台类型时执行的操作
			if(!ValidateUtil.isRequired(brand.getArea_attr())) {
				area_attr = brand.getArea_attr();
			}
			brand.setInfo_state("0");
		}
		// 将处理后的所属地区串注入到brand对象中
		this.brand.setArea_attr(area_attr);
		
		// 数据库原有的分类串	
		supply_infoattr_id = brand.getInfoattr_id();	
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("brand");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.brand.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}else{
			supply_infoattr_id=null;
		}					
		// 用于所属地区的回选结束
		//字段验证
		super.commonValidateField(brand);
		if(super.ifvalidatepass){
			return view();
		}
		// 修改数据
		this.brandService.update(brand,objList,supply_infoattr_id);//DAO层重载update方法
		this.addActionMessage("修改品牌信息成功");
		return list();
	}

	/**
	 * 方法描述：删除品牌信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		del();
		return list();
	}
	public  void  del(){
		String id = this.brand.getBrand_id();
		id = id.replace(" ", "");
		if (id!=null&&!"".equals(id)) {
			id = id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				Brand brand=this.brandService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],brand.getIn_date());
				this.brandService.delete(ids[i]);
			}
			this.addActionMessage("删除品牌信息成功");	
		}
	}
	/**
	 * 方法描述：审核品牌批量删除信息
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 */
	public String checkDel() throws Exception {
		del();
		return  auditList();
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
		} else {
			// 判断今天新增供应
			if (today != null && !"".equals(today)) {
				pageMap.put("today", this.today);
			} else {
				// 品牌列表取信息状态为正常和禁用
				StringBuffer sb = new StringBuffer();
				sb.append("1,3");
				pageMap.put("infostate", sb);
			}
		}
		if (title_s != null && !title_s.equals("")) {
			pageMap.put("title", title_s);
		}
		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		// 获取搜索的所在地区
		if (area_attr_s != null && !area_attr_s.equals("")) {
			pageMap.put("area_attr", area_attr_s);
		}
		// 获取搜索的所属分类
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("cat_attr", cat_attr_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (end_date_s != null && !end_date_s.equals("")) {
			pageMap.put("end_date", end_date_s);
		}
		if (info_state_s != null && !info_state_s.equals("")) {
			pageMap.put("info_state", info_state_s);
		}
		if (is_recom_s != null && !is_recom_s.equals("")) {
			pageMap.put("is_recom", is_recom_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.brandService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		brandList = this.brandService.getList(pageMap);
		// 转换地区和分类为中文字符
		brandList = CategoryFuc.replaceList(brandList, "");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出品牌信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		// 找出地区字段的存入隐藏值中
		//backArea(brand.getArea_attr());
		// 将从数据库中查询的所属分类存入分类隐藏域中
		//backCategory(brand.getCat_attr());
		// 操作者为会员则默认加入搜索条件
		if(brand.getCust_id()!=null){
			if(accessControl(brand.getCust_id())){
				return list();
			}
		}
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			Map catMap = new HashMap();
			catMap.put("cust_id", this.session_cust_id);
			catMap.put("cat_type", 0);
			selfCatList = this.membercatService.getList(catMap);
			// 当操作类型为会员时，直接从会员表中读取地区信息
			if (brand.getCust_id() != null) {
				Member mem = this.memberService.get(brand.getCust_id());
				if (mem != null) {
					if (mem.getArea_attr() != null) {
						backArea(mem.getArea_attr());
					}
				}
			}
		} else {
			// 根据ID获取所属地区的串
			backArea(this.brand.getArea_attr());
		}
		// 获取参数表的列表
		Map paramap = new HashMap();
		paramap.put("para_code", para_code);
		commparalist = commparaService.getList(paramap);
		//分类ID转名称
		catIdTocatName(this.brand.getCat_attr());
		//根据模块是否启动分类属性
		sysmodule = this.sysmoduleService.get("brand");
		if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){			
			if(ischange==null){//第一次加载页面
				info_infoattr_id = this.brand.getInfoattr_id();
			}
			getIsCatAttr(info_infoattr_id,brand.getCat_attr());			
		}else{
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(brand.getCat_attr());	
		}	
		
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：根据搜索条件列出品牌信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditList() throws Exception {
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		// 品牌列表取信息状态为正常和禁用
		pageMap.put("infostate", "0,2");
		if (title_s != null && !title_s.equals("")) {
			pageMap.put("title", title_s);
		}
		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		// 获取搜索的所在地区
		if (area_attr_s != null && !area_attr_s.equals("")) {
			pageMap.put("area_attr", area_attr_s);
		}
		// 获取搜索的所属分类
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("cat_attr", cat_attr_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (end_date_s != null && !end_date_s.equals("")) {
			pageMap.put("end_date", end_date_s);
		}
		if (info_state_s != null && !info_state_s.equals("")) {
			pageMap.put("info_state", info_state_s);
		}
		if (is_recom_s != null && !is_recom_s.equals("")) {
			pageMap.put("is_recom", is_recom_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.brandService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		brandList = this.brandService.getList(pageMap);
		// 转换地区和分类为中文字符
		brandList = CategoryFuc.replaceList(brandList, "");
		return goUrl(AUDITLIST);
	}

	/**
	 * 方法描述：根据主键找出品牌信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		
		//进入审核页面时候的加载方法
		auditView("brand",brand.getBrand_id());
		
		// 获取地区名称
		String area_name = "";
		if (brand.getArea_attr() != null) {
			area_name = AreaFuc.getAreaNameByMap(brand.getArea_attr());
		}
		brand.setArea_attr(area_name);
		// 获取分类名称
		String cat_name = "";
		if (brand.getCat_attr() != null) {
			cat_name = CategoryFuc.getCateNameByMap(brand.getCat_attr());
		}
		brand.setCat_attr(cat_name);
		setInfostateTip(brand.getInfo_state());
		// 找出属性列表
		String infoattr_id = brand.getInfoattr_id();
		sysmodule = this.sysmoduleService.get("classified");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			backAttr(infoattr_id,brand.getCat_attr());
		}		
		return goUrl(AUDIT);
	}

	/**
	 * 方法描述：审核品牌信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditState() throws Exception {
		
		
		String id = this.brand.getBrand_id();
		if (ValidateUtil.isDigital(id)) {
			return auditList();
		}
		//启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("brand",brand.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		if (ValidateUtil.isRequired(brand.getInfo_state())) {
			this.addFieldError("brand.info_state", "请选择审核状态");
			return audit();
		}
		if (brand.getInfo_state().equals("2") && ValidateUtil.isRequired(brand.getNo_reason())) {
			this.addFieldError("brand.no_reason", "请输入拒绝理由");
			setInfostateTip("2");
			return audit();
		}
		Map stateMap = new HashMap();
		if (!brand.getInfo_state().equals("2")) {
			stateMap.put("no_reason", "");
		} else {
			stateMap.put("no_reason", brand.getNo_reason());
		}
		stateMap.put("brand_id", brand.getBrand_id());
		stateMap.put("info_state", brand.getInfo_state());
		this.brandService.updateBrandState(stateMap);
		this.addActionMessage("已审核品牌信息");
		return auditList();
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
		int count = this.brandService.getCount(tmap);
		if(controlMsgNum(count,modType)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @MethodDescribe 方法描述 根据系统模块中对应的模块是否支持分类属性跳转到新增供应页面
	 * @author 创建人 林俊钦
	 * @date 创建日期 Oct 21, 2011 11:14:29 AM
	 */
	public String cate() throws Exception{
		// 找出参数类型
		Map paramap = new HashMap();
		paramap.put("para_code", para_code);
		commparalist = commparaService.getList(paramap);
		//获取所属模块名是否支持分类属性
		sysmodule = this.sysmoduleService.get("brand");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			if(cat_attr == null||cat_attr.equals("0")){
				return goUrl("cate");
			}
			checkIsAttr();				
		}

		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			controlInfoNum();
		}
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
					brand = new Brand();
					brand.setBrand_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		} else {
			if (this.brand != null && this.brand.getBrand_id() != null && !this.brand.getBrand_id().equals("")){
				return view();
			} else {
				return cate();
			}
		}
	}

	/**
	 * 方法描述：是否推荐批量修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String brandid = this.brand.getBrand_id();
		String isrecom = this.brand.getIs_recom();
		brandid = brandid.replace(" ", "");
		String brandStr[] = brandid.split(",");
		List brandList = new ArrayList();
		if (brandStr.length > 0) {
			for (int i = 0; i < brandStr.length; i++) {
				Map memberMap = new HashMap();
				memberMap.put("is_recom", isrecom);
				memberMap.put("brand_id", brandStr[i]);
				brandList.add(memberMap);
			}
		}
		this.brandService.updateIsrecom(brandList);
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
	 * @return the brand
	 */
	public Brand getBrand() {
		return brand;
	}

	/**
	 * @param Brand
	 *            the brand to set
	 */
	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	/**
	 * @return the BrandList
	 */
	public List getBrandList() {
		return brandList;
	}

	/**
	 * @param brandList
	 *            the BrandList to set
	 */
	public void setBrandList(List brandList) {
		this.brandList = brandList;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getCust_name_s() {
		return cust_name_s;
	}

	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}

	public String getIs_recom_s() {
		return is_recom_s;
	}

	public void setIs_recom_s(String is_recom_s) {
		this.is_recom_s = is_recom_s;
	}

	public String getInfo_state_s() {
		return info_state_s;
	}

	public void setInfo_state_s(String info_state_s) {
		this.info_state_s = info_state_s;
	}

	public String getIn_date_s() {
		return in_date_s;
	}

	public void setIn_date_s(String in_date_s) {
		this.in_date_s = in_date_s;
	}

	public String getEnd_date_s() {
		return end_date_s;
	}

	public void setEnd_date_s(String end_date_s) {
		this.end_date_s = end_date_s;
	}

	public String getArea_attr_s() {
		return area_attr_s;
	}

	public void setArea_attr_s(String area_attr_s) {
		this.area_attr_s = area_attr_s;
	}

	public String getCat_attr_s() {
		return cat_attr_s;
	}

	public void setCat_attr_s(String cat_attr_s) {
		this.cat_attr_s = cat_attr_s;
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

	public String getInfostateTip() {
		return infostateTip;
	}

	public void setInfostateTip(String infostateTip) {
		this.infostateTip = infostateTip;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	

	public Sysmodule getSysmodule() {
		return sysmodule;
	}

	public void setSysmodule(Sysmodule sysmodule) {
		this.sysmodule = sysmodule;
	}

	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if (brand == null) {
			brand = new Brand();
		}
		String id = this.brand.getBrand_id();
		if (!ValidateUtil.isDigital(id)) {
			brand = this.brandService.get(id);
		}
	}

}
