/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: ShowinfoAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.model.Category;
import com.rbt.model.Job;
import com.rbt.model.Product;
import com.rbt.model.Showinfo;
import com.rbt.model.Sysmodule;
import com.rbt.service.IAreaService;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICategoryattrService;
import com.rbt.service.IChannelService;
import com.rbt.service.IShowinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rbt.function.*;

/**
 * @function 功能 展会表action类
 * @author 创建人 胡惜坤
 * @date 创建日期 Thu Jul 28 09:17:39 CST 2011
 */
@Controller
public class ShowinfoAction extends baseModelAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 展会表对象
	 */
	public Showinfo showinfo;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IShowinfoService showinfoService;
	/*
	 * 展会表信息集合
	 */
	public List showList;
	/*
	 * 搜索字段 title_s:展会标题
	 */
	public String title_s;
	/*
	 * 搜索字段 host_unit_s ：主办单位
	 */
	public String host_unit_s;
	/*
	 * 搜索字段 cat_attr_s：所属分类
	 */
	public String cat_attr_s;
	/*
	 * 搜索字段 hall_name_s：展馆名称
	 */
	public String hall_name_s;
	/*
	 * 搜索字段 sponsor_s：承办单位
	 */
	public String sponsor_s;
	/*
	 * 搜索字段 in_date_s：发布时间
	 */
	public String in_date_s;
	/*
	 * 搜索字段 enddate_s：筛选发布时间上限时间
	 */
	public String enddate_s;
	/*
	 * 搜索字段 info_state_s：信息状态0：未审核 1：审核通过 2：审核不通过 3：禁用
	 */
	public String info_state_s;
	/*
	 * 搜索字段 cust_id_s：客户标识
	 */
	public String cust_id_s;
	/*
	 * 搜索字段 fare_s：内容收费
	 */
	public String fare_s;
	/*
	 * 搜索字段 clicknum_s：点击量
	 */
	public String clicknum_s;
	/*
	 * 搜索字段 user_name_s：发布人名称
	 */
	public String user_name_s;
	/*
	 * 搜索字段 start_date_s：展会开始时间
	 */
	public String start_date_s;
	/*
	 * 搜索字段 endstart_date_s：展会开始时间搜索上限时间
	 */
	public String endstart_date_s;
	/*
	 * 搜索字段 end_date_s：展会结束时间
	 */
	public String end_date_s;
	/*
	 * 搜索字段 endend_date_s：展会结束时间搜索上限时间
	 */
	public String endend_date_s;
	/*
	 * 显示审核不通过的理由输入框
	 */
	public String noReasonKey;
	/*
	 * 定义上一级级别ID
	 */
	public String showid;
	public String hidden_up_cate_id;
	public String today;
	public String modType = "showinfo";
	public Sysmodule sysmodule;
	private String showinfo_infoattr_id;
	/*
	 * 定义供应类别表对象
	 */
	public Category category;
	/*
	 * 定义分类属性的Service接口
	 */
	@Autowired
	public ICategoryattrService categoryattrService;
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public IAreaService areaService;
	@Autowired
	public IChannelService channelService;
	// 地区搜索
	public String search_area_attr;
	// 分类搜索
	public String search_cat_attr;
	/*
	 * 默认顶级
	 */
	public String up_levle_area = cfg_topareaid + ",";
	public String up_levle = "1111111111,";

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
					showinfo = new Showinfo();
					showinfo.setExh_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		} else {
			if (this.showinfo != null && this.showinfo.getExh_id() != null && !this.showinfo.getExh_id().equals("")){
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
		int count = this.showinfoService.getCount(tmap);
		if(controlMsgNum(count,modType)){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	
	/**
	 * 方法描述：返回新增展会表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cate() throws Exception {
		//获取所属模块名是否支持分类属性
		sysmodule = this.sysmoduleService.get("showinfo");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			if(cat_attr == null||cat_attr.equals("0")){
				return goUrl("cate");
			}
			checkIsAttr();				
		}
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			controlInfoNum();
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：是否推荐批量修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String subid = this.showinfo.getExh_id();
		String isrecom = this.showinfo.getIs_recom();
		subid = subid.replace(" ", "");
		String subStr[] = subid.split(",");
		List subList = new ArrayList();
		if (subStr.length > 0) {
			for (int i = 0; i < subStr.length; i++) {
				Map subMap = new HashMap();
				subMap.put("is_recom", isrecom);
				subMap.put("exh_id", subStr[i]);
				subList.add(subMap);
			}
		}
		this.showinfoService.updateRecommendState(subList);
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
	 * 方法描述：新增展会表
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
		//验证地区是选择
		validateAreaIfSelect();
		// 将处理后的所属分类串注入到buy对象中
		this.showinfo.setCat_attr(cat_attr);
		this.showinfo.setArea_attr(area_attr);
		showinfo.setUser_id(this.session_user_id);
		showinfo.setCust_id(this.session_cust_id);
		showinfo.setNo_reason("");
		showinfo.setArea_attr(area_attr);
		showinfo.setCat_attr(cat_attr);
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("showinfo");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.showinfo.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return cate();
			}
		}
		//字段验证
		super.commonValidateField(showinfo);
		if(super.ifvalidatepass){
			return cate();
		}
		String exh_id=this.showinfoService.insertGetPk(showinfo,objList);// 获取刚刚插入成功的记录的ID		
		// 对插入成功的信息生成HTML静态页面
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, exh_id);		
		this.addActionMessage("新增展会信息成功");
		this.showinfo = null;
		is_crotorl=true;
		return  cate();
	}

	/**
	 * 方法描述：修改展会表信息
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
		// 当前登录的用户是管理员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)
				&& showinfo.getInfo_state().equals("2")
				&& ValidateUtil.isRequired(showinfo.getNo_reason())) {
			this.addFieldError("showinfo.no_reason", "请输入审核未通过的理由");
			noReasonKey = "2";
		}
		if (!showinfo.getInfo_state().equals("2")) {
			showinfo.setNo_reason("");
		}
		showinfo.setUser_id(this.session_user_id);	
		
		// 数据库原有的分类串	
		showinfo_infoattr_id = showinfo.getInfoattr_id();	
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("showinfo");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.showinfo.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}else{
			showinfo_infoattr_id=null;
		}			
		this.showinfo.setCat_attr(cat_attr);
		
		
		//字段验证
		super.commonValidateField(showinfo);
		if(super.ifvalidatepass){
			return view();
		}
		cat_attr = cat_attr.replace(" ", "");
		this.showinfo.setCat_attr(cat_attr);
		this.showinfo.setArea_attr(area_attr);
		this.showinfoService.update(showinfo,objList,showinfo_infoattr_id);
		// 当更新审核通过时更新展会信息
		if (this.showinfo.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.showinfo.getExh_id());
		}
		this.addActionMessage("修改展会信息成功");
		return list();
	}

	/**
	 * 方法描述：删除展会表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		del();
		return list();
	}
	
	public void del(){
		String id = this.showinfo.getExh_id();
		id = id.replace(" ", "");
		if (id!=null&&!"".equals(id)) {
			id = id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				Showinfo showinfo=this.showinfoService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],showinfo.getIn_date());
				this.showinfoService.delete(ids[i]);
			}
			this.addActionMessage("删除展会信息成功");	
		}
	}
	/**
	 * 方法描述：审核展会批量删除
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 * @date : Jul 9, 2012 9:13:48 AM
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
		if (host_unit_s != null && !host_unit_s.equals(""))
			pageMap.put("host_unit", host_unit_s);
		if (cat_attr_s != null && !cat_attr_s.equals(""))
			pageMap.put("cat_attr", cat_attr_s);
		if (hall_name_s != null && !hall_name_s.equals(""))
			pageMap.put("hall_name", hall_name_s);
		if (sponsor_s != null && !sponsor_s.equals(""))
			pageMap.put("sponsor", sponsor_s);
		if (in_date_s != null && !in_date_s.equals(""))
			pageMap.put("in_date", in_date_s);
		if (enddate_s != null && !enddate_s.equals(""))
			pageMap.put("enddate", enddate_s);
		if (fare_s != null && !fare_s.equals(""))
			pageMap.put("fare", fare_s);
		if (cust_id_s != null && !cust_id_s.equals(""))
			pageMap.put("cust_id", cust_id_s);
		if (clicknum_s != null && !clicknum_s.equals(""))
			pageMap.put("clicknum", clicknum_s);
		if (user_name_s != null && !user_name_s.equals(""))
			pageMap.put("user_name", user_name_s);
		if (start_date_s != null && !start_date_s.equals(""))
			pageMap.put("start_date", start_date_s);
		if (endstart_date_s != null && !endstart_date_s.equals(""))
			pageMap.put("endstart_date", endstart_date_s);
		if (end_date_s != null && !end_date_s.equals(""))
			pageMap.put("end_date", end_date_s);
		if (endend_date_s != null && !endend_date_s.equals(""))
			pageMap.put("endend_date", endend_date_s);
		if (search_area_attr != null && !search_area_attr.equals(""))
			pageMap.put("area_attr", search_area_attr);
		if (search_cat_attr != null && !search_cat_attr.equals(""))
			pageMap.put("cat_attr", search_cat_attr);
		if (info_state_s != null && !info_state_s.equals("") && !info_state_s.equals("4")) {
			pageMap.put("info_state", info_state_s);
		}
		// 操作者为会员则获取所有审核状态的信息，否则获取审核通过与禁用状态的信息
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("info_state_in", "0,1,2,3");// 审核通过状态
		} else {
			if (today != null && !today.equals("")) {
				pageMap.put("today", this.today);
			} else {
				if (info_state_s != null && !info_state_s.equals("") && !info_state_s.equals("4")) {
					pageMap.put("info_state", info_state_s);
				} else {
					pageMap.put("info_state_in", "1,3");
				}
			}
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.showinfoService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		showList = this.showinfoService.getList(pageMap);
		showList = com.rbt.function.CategoryFuc.replaceList(showList, "");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出展会表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(showinfo.getCust_id()!=null){
			if(accessControl(showinfo.getCust_id())){
				return list();
			}
		}
		noReasonKey = showinfo.getInfo_state();
		// 将从数据库中查询的所属分类存入分类隐藏域中
		backCategory(showinfo.getCat_attr());	
		// 找出地区字段的存入隐藏值中
		backArea(showinfo.getArea_attr());	
		
		//分类ID转名称
		catIdTocatName(this.showinfo.getCat_attr());
		//根据模块是否启动分类属性
		sysmodule = this.sysmoduleService.get("showinfo");
		if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){			
			if(ischange==null){//第一次加载页面
				info_infoattr_id = this.showinfo.getInfoattr_id();
			}
			getIsCatAttr(info_infoattr_id,showinfo.getCat_attr());		
		}else{
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(showinfo.getCat_attr());	
		}		
		
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：审核展会信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditState() throws Exception {
		
		//启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("showinfo",showinfo.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		if (showinfo.getInfo_state() == null && showinfo.getInfo_state().equals("")) {
			this.addFieldError("showinfo.info_state", "请选择审核状态");
			return audit();
		}
		if (showinfo.getInfo_state().equals("2")
				&& ValidateUtil.isRequired(showinfo.getNo_reason())) {
			this.addFieldError("showinfo.no_reason", "请输入拒绝理由");
			noReasonKey = "2";
			return audit();
		}
		Map pageMap = new HashMap();
		if (!showinfo.getInfo_state().equals("2")) {
			pageMap.put("no_reason", "");
		} else {
			pageMap.put("no_reason", showinfo.getNo_reason());
		}
		pageMap.put("exh_id", showinfo.getExh_id());
		pageMap.put("info_state", showinfo.getInfo_state());
		this.showinfoService.updateState(pageMap);
		// 当更新审核通过时更新展会信息
		if (this.showinfo.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.showinfo.getExh_id());
		}
		this.addActionMessage("审核展会信息成功");
		return auditList();
	}

	/**
	 * 方法描述：审核展会信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		
		
		//进入审核页面时候的加载方法
		auditView("showinfo",showinfo.getExh_id());
		
		
		
		noReasonKey = showinfo.getInfo_state();
		//获取地区中文字符
		String area_name = "";
		if (showinfo.getArea_attr() != null) {
			area_name = AreaFuc.getAreaNameByMap(showinfo.getArea_attr());
		}
		showinfo.setArea_attr(area_name);
		//获取分类中文字符
		String cat_name = "";
		if (showinfo.getCat_attr() != null) {
			cat_name = CategoryFuc.getCateNameByMap(showinfo.getCat_attr());
		}
		showinfo.setCat_attr(cat_name);
		// 找出属性列表
		String infoattr_id = showinfo.getInfoattr_id();
		sysmodule = this.sysmoduleService.get("product");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			backAttr(infoattr_id,showinfo.getCat_attr());
		}		
		return goUrl(AUDIT);
	}

	/**
	 * 方法描述：获取未审核的展会
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
		if (title_s != null && !title_s.equals(""))
			pageMap.put("title", title_s);
		if (host_unit_s != null && !host_unit_s.equals(""))
			pageMap.put("host_unit", host_unit_s);
		if (cat_attr_s != null && !cat_attr_s.equals(""))
			pageMap.put("cat_attr", cat_attr_s);
		if (hall_name_s != null && !hall_name_s.equals(""))
			pageMap.put("hall_name", hall_name_s);
		if (sponsor_s != null && !sponsor_s.equals(""))
			pageMap.put("sponsor", sponsor_s);
		if (in_date_s != null && !in_date_s.equals(""))
			pageMap.put("in_date", in_date_s);
		if (enddate_s != null && !enddate_s.equals(""))
			pageMap.put("enddate", enddate_s);
		if (fare_s != null && !fare_s.equals(""))
			pageMap.put("fare", fare_s);
		if (info_state_s != null && !info_state_s.equals("") && !info_state_s.equals("4")) {
			pageMap.put("info_state", info_state_s);
		} else {
			pageMap.put("info_state_in", "0,2");// 未审核状态
		}
		if (cust_id_s != null && !cust_id_s.equals(""))
			pageMap.put("cust_id", cust_id_s);
		if (clicknum_s != null && !clicknum_s.equals(""))
			pageMap.put("clicknum", clicknum_s);
		if (user_name_s != null && !user_name_s.equals(""))
			pageMap.put("user_name", user_name_s);
		if (start_date_s != null && !start_date_s.equals(""))
			pageMap.put("start_date", start_date_s);
		if (endstart_date_s != null && !endstart_date_s.equals(""))
			pageMap.put("endstart_date", endstart_date_s);
		if (end_date_s != null && !end_date_s.equals(""))
			pageMap.put("end_date", end_date_s);
		if (endend_date_s != null && !endend_date_s.equals(""))
			pageMap.put("endend_date", endend_date_s);
		if (search_area_attr != null && !search_area_attr.equals(""))
			pageMap.put("area_attr", search_area_attr);
		if (search_cat_attr != null && !search_cat_attr.equals(""))
			pageMap.put("cat_attr", search_cat_attr);
		// 根据页面提交的条件找出信息总数
		int count = this.showinfoService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		showList = this.showinfoService.getList(pageMap);
		showList = com.rbt.function.CategoryFuc.replaceList(showList, "");
		return goUrl(AUDITLIST);
	}

	/**
	 * @return the showList
	 */
	public List getshowList() {
		return showList;
	}

	/**
	 * @param showList
	 *            the showList to set
	 */
	public void setshowList(List showList) {
		this.showList = showList;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getHost_unit_s() {
		return host_unit_s;
	}

	public void setHost_unit_s(String host_unit_s) {
		this.host_unit_s = host_unit_s;
	}

	public String getCat_attr_s() {
		return cat_attr_s;
	}

	public void setCat_attr_s(String cat_attr_s) {
		this.cat_attr_s = cat_attr_s;
	}

	public String getHall_name_s() {
		return hall_name_s;
	}

	public void setHall_name_s(String hall_name_s) {
		this.hall_name_s = hall_name_s;
	}

	public String getSponsor_s() {
		return sponsor_s;
	}

	public void setSponsor_s(String sponsor_s) {
		this.sponsor_s = sponsor_s;
	}

	public String getIn_date_s() {
		return in_date_s;
	}

	public void setIn_date_s(String in_date_s) {
		this.in_date_s = in_date_s;
	}

	public String getEnddate_s() {
		return enddate_s;
	}

	public void setEnddate_s(String enddate_s) {
		this.enddate_s = enddate_s;
	}

	public String getInfo_state_s() {
		return info_state_s;
	}

	public void setInfo_state_s(String info_state_s) {
		this.info_state_s = info_state_s;
	}

	public String getCust_id_s() {
		return cust_id_s;
	}

	public void setCust_id_s(String cust_id_s) {
		this.cust_id_s = cust_id_s;
	}

	public String getFare_s() {
		return fare_s;
	}

	public void setFare_s(String fare_s) {
		this.fare_s = fare_s;
	}

	public String getClicknum_s() {
		return clicknum_s;
	}

	public void setClicknum_s(String clicknum_s) {
		this.clicknum_s = clicknum_s;
	}

	public String getUser_name_s() {
		return user_name_s;
	}

	public void setUser_name_s(String user_name_s) {
		this.user_name_s = user_name_s;
	}

	public String getStart_date_s() {
		return start_date_s;
	}

	public void setStart_date_s(String start_date_s) {
		this.start_date_s = start_date_s;
	}

	public String getEndstart_date_s() {
		return endstart_date_s;
	}

	public void setEndstart_date_s(String endstart_date_s) {
		this.endstart_date_s = endstart_date_s;
	}

	public String getEnd_date_s() {
		return end_date_s;
	}

	public void setEnd_date_s(String end_date_s) {
		this.end_date_s = end_date_s;
	}

	public String getEndend_date_s() {
		return endend_date_s;
	}

	public void setEndend_date_s(String endend_date_s) {
		this.endend_date_s = endend_date_s;
	}

	public String getNoReasonKey() {
		return noReasonKey;
	}

	public void setNoReasonKey(String noReasonKey) {
		this.noReasonKey = noReasonKey;
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


	public String getHidden_up_cate_id() {
		return hidden_up_cate_id;
	}

	public void setHidden_up_cate_id(String hidden_up_cate_id) {
		this.hidden_up_cate_id = hidden_up_cate_id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the showinfoService
	 */
	public IShowinfoService getshowinfoService() {
		return showinfoService;
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

	public Showinfo getShowinfo() {
		return showinfo;
	}

	public void setShowinfo(Showinfo showinfo) {
		this.showinfo = showinfo;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if (showinfo == null) {
			showinfo = new Showinfo();
		}
		String id = showinfo.getExh_id();
		if (!ValidateUtil.isDigital(id)) {
			showinfo = this.showinfoService.get(id);
		}
	}
}
