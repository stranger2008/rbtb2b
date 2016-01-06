/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: SupplyAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.function.CommparaFuc;
import com.rbt.model.Infoattr;
import com.rbt.model.Member;
import com.rbt.model.Supply;
import com.rbt.service.ICategoryService;
import com.rbt.service.IChannelService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IInfoattrService;
import com.rbt.service.IMemberService;
import com.rbt.service.ISupplyService;
import com.rbt.service.ISysmoduleService;

/**
 * @function 功能 供应表action类
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Jul 21 17:15:43 CST 2011
 */
@Controller
public class SupplyAction extends baseModelAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 供应表对象
	 */
	public Supply supply;
	/*
	 * 信息属性对象
	 */
	public Infoattr infoattr;

	/*
	 * 定义隐藏地区的数值
	 */
	public String hidden_up_area_id;
	public String noReasonKey;
	public String other_unit;
	

	/*
	 * 定义搜索字段的区域get set
	 */
	public String title_s;
	public String type_s;
	public String brand_s;
	public String is_recom_s;
	public String mem_recom_s;
	public String fare_start_s;
	public String fare_last_s;
	public String starttime_s;
	public String endtime_s;
	public String area_attr_s;
	public String cat_attr_s;
	public String state_s;
	public String supplyid;
	public String today;
	public String is_member;// 判断是不是会员的操作
	public String modType = "supply";
	
	/*
	 * 定义常量字段,用于从基本参数中找出相应的模块值
	 */
	public String para_code = "supply_type";
	
	/*
	 * 业务层接口
	 */
	@Autowired
	public ISupplyService supplyService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public IChannelService channelService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IInfoattrService infoattrService;
	@Autowired
	public ISysmoduleService sysmoduleService;

	/*
	 * 供应表信息集合
	 */
	public List supplyList;
	public List commparalist;
	public List infoattrList;

	
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 13, 2012 1:42:18 PM
	 * @Method Description : 会员发布条数的判断
	 */
	public boolean controlInfoNum(){
		//查出已发布的信息个数
		Map tmap = new HashMap();
		tmap.put("cust_id", this.session_cust_id);
		int count = this.supplyService.getCount(tmap);
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
		//计量单位
		Map unitMap = new HashMap();
		unitMap.put("para_code", "unit");
		unitList = commparaService.getList(unitMap);
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			Map catMap = new HashMap();
			catMap.put("cust_id", this.session_cust_id);
			catMap.put("cat_type", 0);
			selfCatList = this.membercatService.getList(catMap);
			// 操作者为会员见则从会员表中取出地区数据
			Member mem = this.memberService.get(this.session_cust_id);
			if (mem != null) {
				if (mem.getArea_attr() != null) {
					backArea(mem.getArea_attr());
				}
			}
		}
		//获取所属模块名是否支持分类属性开始
		sysmodule = this.sysmoduleService.get("supply");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			if(cat_attr == null||cat_attr.equals("0")){
				return goUrl("cate");
			}
			checkIsAttr();				
		}
		//获取所属模块名是否支持分类属性结束
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			//发布信息数量控制
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
					supply = new Supply();
					supply.setSupply_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		} else {
			if (this.supply != null && this.supply.getSupply_id() != null && !this.supply.getSupply_id().equals("")){
				return view();
			} else {
				return cate();
			}
		}
	}

	/**
	 * 方法描述：新增供应表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception 
	{
		// 未成功插入时，回选地区
		loadArea();
		// 用于所属分类的回选开始
		loadCategory();
		// 验证分类是选择
		validateCategoryIfSelect();
		// 验证地区是选择
		//validateAreaIfSelect();
		this.supply.setCust_id(this.session_cust_id);
		this.supply.setUser_id(this.session_user_id);
		// 存入产品的分类
		this.supply.setCat_attr(cat_attr);
		this.supply.setArea_attr(area_attr);
		// other:计量单位类型
		if(supply.getUnit().equals("other")){
			supply.setUnit(other_unit);
		}
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("supply");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.supply.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}
		//判断如果是会员的话，发布信息数量控制
		if(this.session_cust_type.equals(Constants.MEMBER_TYPE)){
			//发布信息数量控制
			if(controlInfoNum()){
				return cate();
			}
		}
		//字段验证
		super.commonValidateField(supply);
		if(super.ifvalidatepass){
			return cate();
		}		
		String supply_id = this.supplyService.insertGetPk(supply,objList);	//DAO层重载insertGetPk方法	
		// 对插入成功的信息生成HTML静态页面
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, supply_id);
		this.addActionMessage("新增供应信息成功");
		this.supply = null;
		is_crotorl=true;
		return cate();
	}

	/**
	 * 方法描述：修改供应表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {		
		// 用于所属分类的回选开始
		loadCategory();
		// 用于所属地区的回选开始
		loadArea();
		//验证分类是选择
		validateCategoryIfSelect();
		//验证地区是选择
		validateAreaIfSelect();
		this.supply.setCust_id(this.session_cust_id);
		this.supply.setUser_id(this.session_user_id);
		
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("supply");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.supply.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}				

		if(supply.getUnit().equals("other")){
			supply.setUnit(other_unit);
		}		
		//字段验证
		super.commonValidateField(supply);
		if(super.ifvalidatepass){
			return view();
		}		
	    this.supply.setCat_attr(cat_attr);
	    this.supply.setArea_attr(area_attr);	
		// 修改数据
		this.supplyService.update(supply,objList,info_infoattr_id);//DAO层重载update方法
		// 当审核通过时更新页面
		if (this.supply.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.supply.getSupply_id());
		}
		this.addActionMessage("修改供应信息成功");
		return list();
	}

	/**
	 * 方法描述：删除供应表信息，需移到DAO层里进行重写
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
			del();
			return list();	
	}
	
	public void del(){
		if (this.supply.getSupply_id() != null) {
			String id = this.supply.getSupply_id();
			id = id.replace(" ", "");
			if (id!=null&&!"".equals(id)) {
				id = id.replace(" ", "");
				String[] ids=id.split(",");
				DoHtml dohtml=new DoHtml();
				for(int i =0;i<ids.length;i++){
					//获取当前ID的对象
					Supply supply=this.supplyService.get(ids[i]);
					dohtml.delArticeHtml(modType,ids[i],supply.getIn_date());
					this.supplyService.delete(ids[i]);
				}
				this.addActionMessage("删除供应信息成功");	
			}
		}
	}
	/**
	 * 方法描述：审核供应批量删除信息
	 * 
	 * @author 陈晓艺
	 * @throws Exception
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
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		// 获取搜索的标题
		if (this.title_s != null && !this.title_s.equals("")) {
			pageMap.put("title", this.title_s);
		}
		// 获取搜索的产品品牌
		if (this.brand_s != null && !this.brand_s.equals("")) {
			pageMap.put("brand", this.brand_s);
		}
		// 获取搜索的供应类型
		if (this.type_s != null && !this.type_s.equals("")) {
			pageMap.put("supply_type", this.type_s);
		}
		// 获取搜索的是否推荐
		if (this.is_recom_s != null && !this.is_recom_s.equals("")) {
			pageMap.put("is_recom", this.is_recom_s);
		}
		// 获取搜索的审核状态
		if (this.state_s != null && !this.state_s.equals("")) {
			pageMap.put("info_state", this.state_s);
		}
		if (this.info_state_s != null && !this.info_state_s.equals("")) {
			pageMap.put("info_state", this.info_state_s);
		}
		// 获取搜索的会员推荐
		if (this.mem_recom_s != null && !this.mem_recom_s.equals("")) {
			pageMap.put("mem_recom", this.mem_recom_s);
		}
		// 获取搜索的内容收费
		if (this.fare_start_s != null && !this.fare_start_s.equals("") && this.fare_last_s != null && !this.fare_last_s.equals("")) {
			pageMap.put("search_fare_start", this.fare_start_s);
			pageMap.put("search_fare_last", this.fare_last_s);
		}
		// 获取搜索的所属地区
		if (request.getParameter("area_attr_s") != null) {
			String area_attr = request.getParameter("area_attr_s");
			pageMap.put("area_attr", area_attr);
		}
		// 获取搜索的所属分类
		if (request.getParameter("cat_attr_s") != null) {
			String cat_attr = request.getParameter("cat_attr_s");
			pageMap.put("cat_attr", cat_attr);
		}
		// 获取搜索的发布时间
		if (this.starttime_s != null && !this.starttime_s.equals("")) {
			pageMap.put("search_starttime", this.starttime_s);
			if (this.endtime_s == null || this.endtime_s.equals("")) {
				Date mydate = new Date();
				if(this.endtime_s.length()>8){
					String end_time = mydate.toLocaleString().substring(0, 8);
					pageMap.put("search_endtime", end_time);
				}				
			} else {
				pageMap.put("search_endtime", this.endtime_s);
			}
		}
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		} else {
			// 判断今天新增供应
			if (today != null && !today.equals("")) {
				pageMap.put("today", this.today);
			} else {
				pageMap.put("info_state_in", "1,3");
			}
		}
		// 加载供应类型
		Map map = new HashMap();
		map.put("para_code", para_code);
		commparalist = commparaService.getList(map);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.supplyService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		// 找出信息列表，放入list
		supplyList = this.supplyService.getList(pageMap);
		supplyList = categoryFuc.replaceList(supplyList, "supply_type");
		return goUrl(INDEXLIST);
	}

	/**
	 * @MethodDescribe 方法描述 找出审核的列表
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 29, 2011 9:49:19 AM
	 */
	public String auditList() throws Exception {
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		// 取出未审核与未通过列表
		pageMap.put("info_state_in", "0,2");
		// 获取搜索的标题
		if (this.title_s != null && !this.title_s.equals("")) {
			pageMap.put("title", this.title_s);
		}
		// 获取搜索的产品品牌
		if (this.brand_s != null && !this.brand_s.equals("")) {
			pageMap.put("brand", this.brand_s);
		}
		// 获取搜索的供应类型
		if (this.type_s != null && !this.type_s.equals("")) {
			pageMap.put("supply_type", this.type_s);
		}
		// 获取搜索的是否推荐
		if (this.is_recom_s != null && !this.is_recom_s.equals("")) {
			pageMap.put("is_recom", this.is_recom_s);
		}
		// 获取搜索的审核状态
		if (this.state_s != null && !this.state_s.equals("")) {
			pageMap.put("info_state", this.state_s);
		}
		// 获取搜索的会员推荐
		if (this.mem_recom_s != null && !this.mem_recom_s.equals("")) {
			pageMap.put("mem_recom", this.mem_recom_s);
		}
		// 获取搜索的内容收费
		if (this.fare_start_s != null && !this.fare_start_s.equals("") && this.fare_last_s != null && !this.fare_last_s.equals("")) {
			pageMap.put("search_fare_start", this.fare_start_s);
			pageMap.put("search_fare_last", this.fare_last_s);
		}
		// 获取搜索的所属地区
		if (request.getParameter("area_attr_s") != null) {
			String area_attr = request.getParameter("area_attr_s");
			pageMap.put("area_attr", area_attr);
		}
		// 获取搜索的所属分类
		if (request.getParameter("cat_attr_s") != null) {
			String cat_attr = request.getParameter("cat_attr_s");
			pageMap.put("cat_attr", cat_attr);
		}
		// 获取搜索的发布时间
		if (this.starttime_s != null && !this.starttime_s.equals("")) {
			pageMap.put("search_starttime", this.starttime_s);
			if (this.endtime_s == null || this.endtime_s.equals("")) {
				Date mydate = new Date();
				if(this.endtime_s.length()>8){
					String end_time = mydate.toLocaleString().substring(0, 8);
					pageMap.put("search_endtime", end_time);
				}
			} else {
				pageMap.put("search_endtime", this.endtime_s);
			}
		}
		// 加载供应类型
		Map map = new HashMap();
		map.put("para_code", para_code);
		commparalist = commparaService.getList(map);
		// 根据页面提交的条件找出信息总数
		int count = this.supplyService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		// 找出信息列表，放入list
		supplyList = this.supplyService.getList(pageMap);
		// 把数据库中的ID替换成名称
		supplyList = categoryFuc.replaceList(supplyList, "supply_type");
		return goUrl(AUDITLIST);
	}

	/**
	 * @MethodDescribe 方法描述 审核的状态
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 29, 2011 9:49:11 AM
	 */
	public String auditState() throws Exception {
		String info_state = "", no_reason = "";
		String id = this.supply.getSupply_id();
		if (id == null || id.equals("")) {
			return auditList();
		}
		//启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("supply",supply.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		// 获取数据库对象
		Supply supplyAudit = this.supplyService.get(id);
		if (this.supply.getInfo_state() != null && !this.supply.getInfo_state().equals("")) {
			info_state = this.supply.getInfo_state();
			// 当选择审核未通过，拒绝理由不能为空
			if (supply.getInfo_state().equals("2")) {
				if (ValidateUtil.isRequired(supply.getNo_reason())) {
					this.addFieldError("supply.no_reason", "请输入拒绝理由");
					noReasonKey = "2";
					return audit();
				}
			}
			// 设置状态值
			supplyAudit.setInfo_state(info_state);
		}
		if (this.supply.getNo_reason() != null) {
			no_reason = this.supply.getNo_reason();
			// 设置拒绝理由
			supplyAudit.setNo_reason(no_reason);
		}
		// 更新数据库供应列表
		this.supplyService.update(supplyAudit);
		// 当审核通过时更新页面
		if (this.supply.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.supply.getSupply_id());
		}
		this.addActionMessage("审核供应信息成功");
		return auditList();
	}

	/**
	 * @MethodDescribe 方法描述 根据主键找出供应信息
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 29, 2011 9:33:57 AM
	 */
	public String audit() throws Exception {
		// 进入审核页面时候的加载方法
		auditView("supply",supply.getSupply_id());
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		noReasonKey = supply.getInfo_state();
		// 获取参数表的列表
		Map paramap = new HashMap();
		paramap.put("para_code", para_code);
		commparalist = commparaService.getList(paramap);
		// 定义所属分类串，所属属性串，所属地区串的常量
		String cate_attr = "";
		// 根据ID获取所属的分类串
		cate_attr = this.supply.getCat_attr();
		backCategory(cate_attr);
		// 根据ID获取所属地区的串
		backArea(this.supply.getArea_attr());
		// 找出属性列表
		String infoattr_id = supply.getInfoattr_id();
		sysmodule = this.sysmoduleService.get("supply");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			backAttr(infoattr_id,supply.getCat_attr());
		}		
		if(supply.getSupply_type()!=null)
			supply.setSupply_type(CommparaFuc.get_commparakey_By_value(supply.getSupply_type(), para_code));
		if(supply.getArea_attr()!=null)
			supply.setArea_attr(AreaFuc.getAreaNameByMap(supply.getArea_attr()));
	    if(supply.getCat_attr()!=null)
			supply.setCat_attr(CategoryFuc.getCateNameByMap(supply.getCat_attr()));
		return goUrl(AUDIT);
	}
	

	/**
	 * 方法描述：根据主键找出供应表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(supply.getCust_id()!=null){
			if(accessControl(supply.getCust_id())){
				return list();
			}
		}
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			Map catMap = new HashMap();
			catMap.put("cust_id", this.session_cust_id);
			catMap.put("cat_type", 0);
			selfCatList = this.membercatService.getList(catMap);
			// 当操作类型为会员时，直接从会员表中读取地区信息
			if (supply.getCust_id() != null) {
				Member mem = this.memberService.get(supply.getCust_id());
				if (mem != null) {
					if (mem.getArea_attr() != null) {
						backArea(mem.getArea_attr());
					}
				}
			}
		} else {
			// 根据ID获取所属地区的串
			backArea(this.supply.getArea_attr());
		}
		// 获取参数表的列表
		Map paramap = new HashMap();
		paramap.put("para_code", para_code);
		commparalist = commparaService.getList(paramap);
		Map unitMap = new HashMap();
		unitMap.put("para_code", "unit");
		unitList= commparaService.getList(unitMap);
		other_unit=supply.getUnit();
		if(!ValidateUtil.isRequired(supply.getUnit())){
			boolean flag=false;
			if(unitList!= null && unitList.size()>0){
				Map otherMap = new HashMap();
				for(int i=0;i<unitList.size();i++){
					otherMap = (Map) unitList.get(i);
					if(other_unit.equals(otherMap.get("para_value").toString())){
						flag=true;
						break;
					}
				}
				other_unit = "";
			}
			if(flag==false){
				other_unit = supply.getUnit();
				supply.setUnit("other");
			}
		}
		//分类ID转名称
		catIdTocatName(this.supply.getCat_attr());
		//根据模块是否启动分类属性
		sysmodule = this.sysmoduleService.get("supply");
		if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){	
			if(ischange==null){//第一次加载页面
				info_infoattr_id = this.supply.getInfoattr_id();
			}
			getIsCatAttr(info_infoattr_id,supply.getCat_attr());			
		}else{
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(supply.getCat_attr());	
		}		
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：批量修改推荐
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String supplyid = this.supply.getSupply_id();
		String isrecom = this.supply.getIs_recom();
		supplyid = supplyid.replace(" ", "");
		String downStr[] = supplyid.split(",");
		List supplyList = new ArrayList();
		if (downStr.length > 0) {
			for (int i = 0; i < downStr.length; i++) {
				Map galleryMap = new HashMap();
				galleryMap.put("is_recom", isrecom);
				galleryMap.put("supply_id", downStr[i]);
				supplyList.add(galleryMap);
			}
		}
		this.supplyService.updateSupplyState(supplyList);
		String tip = "";
		if (isrecom.equals("0")) {
			tip = "取消推荐成功";
		} else if (isrecom.equals("1")) {
			tip = "推荐成功";
		}
		this.addActionMessage(tip);
		return list();
	}



	public void prepare() throws Exception { super.saveRequestParameter();
		if (supply == null) {
			supply = new Supply();
		}
		String id = supply.getSupply_id();
		if (!ValidateUtil.isDigital(id)) {
			supply = this.supplyService.get(id);
		}
	}
	
	
	public String getNoReasonKey() {
		return noReasonKey;
	}

	public void setNoReasonKey(String noReasonKey) {
		this.noReasonKey = noReasonKey;
	}

	public String getSupplyid() {
		return supplyid;
	}

	public void setSupplyid(String supplyid) {
		this.supplyid = supplyid;
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

	/**
	 * @return the selfCatList
	 */
	public List getSelfCatList() {
		return selfCatList;
	}

	/**
	 * @param selfCatList
	 *            the selfCatList to set
	 */
	public void setSelfCatList(List selfCatList) {
		this.selfCatList = selfCatList;
	}

	/**
	 * @return the hidden_up_area_id
	 */
	public String getHidden_up_area_id() {
		return hidden_up_area_id;
	}

	/**
	 * @param hidden_up_area_id
	 *            the hidden_up_area_id to set
	 */
	public void setHidden_up_area_id(String hidden_up_area_id) {
		this.hidden_up_area_id = hidden_up_area_id;
	}


	public String getUpdate_value() {
		return update_value;
	}

	public void setUpdate_value(String update_value) {
		this.update_value = update_value;
	}

	public String getIs_member() {
		return is_member;
	}

	public void setIs_member(String is_member) {
		this.is_member = is_member;
	}

	/**
	 * @return the SupplyList
	 */
	public List getSupplyList() {
		return supplyList;
	}

	/**
	 * @param supplyList
	 *            the SupplyList to set
	 */
	public void setSupplyList(List supplyList) {
		this.supplyList = supplyList;
	}

	/**
	 * @return the cat_attr
	 */
	public String getCat_attr() {
		return cat_attr;
	}

	/**
	 * @param cat_attr
	 *            the cat_attr to set
	 */
	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}

	/**
	 * @return the commparalist
	 */
	public List getCommparalist() {
		return commparalist;
	}

	/**
	 * @param commparalist
	 *            the commparalist to set
	 */
	public void setCommparalist(List commparalist) {
		this.commparalist = commparalist;
	}

	
	/**
	 * @return the area_attr
	 */
	public String getArea_attr() {
		return area_attr;
	}

	/**
	 * @param area_attr
	 *            the area_attr to set
	 */
	public void setArea_attr(String area_attr) {
		this.area_attr = area_attr;
	}



	/**
	 * @return the supply
	 */
	public Supply getSupply() {
		return supply;
	}

	/**
	 * @param Supply
	 *            the supply to set
	 */
	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getType_s() {
		return type_s;
	}

	public void setType_s(String type_s) {
		this.type_s = type_s;
	}

	public String getBrand_s() {
		return brand_s;
	}

	public void setBrand_s(String brand_s) {
		this.brand_s = brand_s;
	}

	public String getIs_recom_s() {
		return is_recom_s;
	}

	public void setIs_recom_s(String is_recom_s) {
		this.is_recom_s = is_recom_s;
	}

	public String getMem_recom_s() {
		return mem_recom_s;
	}

	public void setMem_recom_s(String mem_recom_s) {
		this.mem_recom_s = mem_recom_s;
	}

	public String getFare_start_s() {
		return fare_start_s;
	}

	public void setFare_start_s(String fare_start_s) {
		this.fare_start_s = fare_start_s;
	}

	public String getFare_last_s() {
		return fare_last_s;
	}

	public void setFare_last_s(String fare_last_s) {
		this.fare_last_s = fare_last_s;
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

	public String getState_s() {
		return state_s;
	}

	public void setState_s(String state_s) {
		this.state_s = state_s;
	}

	public List getUnitList() {
		return unitList;
	}

	public void setUnitList(List unitList) {
		this.unitList = unitList;
	}

	public String getOther_unit() {
		return other_unit;
	}

	public void setOther_unit(String other_unit) {
		this.other_unit = other_unit;
	}

	public Infoattr getInfoattr() {
		return infoattr;
	}

	public void setInfoattr(Infoattr infoattr) {
		this.infoattr = infoattr;
	}

}
