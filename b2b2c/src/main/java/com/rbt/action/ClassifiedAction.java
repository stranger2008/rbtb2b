/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: ClassifiedAction.java 
 */
package com.rbt.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Category;
import com.rbt.model.Categoryattr;
import com.rbt.model.Classified;
import com.rbt.model.Member;
import com.rbt.model.Sysmodule;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICategoryattrService;
import com.rbt.service.IChannelService;
import com.rbt.service.IClassifiedService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercatService;
import com.rbt.service.ISysmoduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录动态分类信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Oct 14 08:59:55 CST 2011
 */
@Controller
public class ClassifiedAction extends baseModelAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 记录动态分类信息对象
	 */
	public Classified classified;
	//分类属性对象
	public Categoryattr categoryattr;
	//分类对象
	public Category category;
	//系统模块
	public Sysmodule sysmodule;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IClassifiedService classifiedService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMembercatService membercatService;
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public IChannelService channelService;
	@Autowired
	public ICategoryattrService categoryattrService;
	@Autowired
	public ISysmoduleService sysmoduleService;
	@Autowired
	public ICommparaService commparaService;
	/*
	 * 记录动态分类信息信息集合
	 */
	public List classifiedList;
	public List selfCatList;
	public String update_value;
	public String is_member;
	public String today;
	public String noReasonKey;
	public String is_select;
	public String modType = "classified";
	public List commparalist;
	/*
	 * 定义常量字段,用于从基本参数中找出相应的模块值
	 */
	public String para_code = "classified_type";
	/*
	 * 搜索字段
	 */
	public String title_s;
	public String cust_name_s;
	public String is_recom_s;
	public String info_state_s;
	public String starttime_s;
	public String endtime_s;
	public String area_attr_s;
	public String cat_attr_s;

	/**
	 * @author : 林俊钦
	 * @date : Jul 13, 2012 1:42:18 PM
	 * @Method Description : 会员发布条数的判断
	 */
	public boolean controlInfoNum(){
		//查出已发布的信息个数
		Map tmap = new HashMap();
		tmap.put("cust_id", this.session_cust_id);
		int count = this.classifiedService.getCount(tmap);
		if(controlMsgNum(count,modType)){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * @MethodDescribe 方法描述    跳转到新增供应页面
	 * @author  创建人  林俊钦
	 * @date  创建日期  Oct 21, 2011 11:14:29 AM
	 */

	public String cate() {
		// 找出参数类型
		Map paramap = new HashMap();
		paramap.put("para_code", para_code);
		commparalist = commparaService.getList(paramap);
		//获取所属模块名是否支持分类属性
		sysmodule = this.sysmoduleService.get("classified");
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
					classified = new Classified();
					classified.setInfo_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		} else {
			if (this.classified != null && this.classified.getInfo_id() != null && !this.classified.getInfo_id().equals("")){
				return view();
			} else {
				return cate();
			}
		}
	}

	/**
	 * 方法描述：返回新增记录动态分类信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		
		if(cat_attr == null){
			return goUrl("cate");
		}
		//找出相应的产品属性
		Map map = new HashMap();
		loadCategory();
		//返回产品属性列表
		map.put("cat_attr", cat_attr);
		attrList = categoryattrService.getList(map);
		//设置分类串cat_attr处理后的值,将得到的供应分类代码转换成名称	
		cate_name = CategoryFuc.getCateNameByMap(hidden_cat_value);
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
				Map catMap = new HashMap();
				catMap.put("cust_id", this.session_cust_id);
				catMap.put("cat_type", "0");
				selfCatList = this.membercatService.getList(catMap);
				// 操作者为会员见则从会员表中取出地区数据
				Member mem = this.memberService.get(this.session_cust_id);
			if (hidden_area_value != null && "".equals(hidden_area_value) && mem != null && mem.getArea_attr() != null) {
				// 找出地区字段的存入隐藏值中
				backArea(mem.getArea_attr());
			}
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录动态分类信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
        // 用于所属分类的回选开始
		loadCategory();
		// 用于所属地区的回选开始
		loadArea();
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("classified");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.classified.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}
		//验证所属地区是否有选择
		if (ValidateUtil.isRequired(area_attr) || hidden_area_value.indexOf("0")>-1) {
			this.addFieldError("area_attr", "请选择地区");
		}
		this.classified.setArea_attr(area_attr);
		if ((!ValidateUtil.isRequired(this.classified.getPhone())) && ValidateUtil.isDigital(this.classified.getPhone())
				&& (ValidateUtil.isMobile(this.classified.getPhone()) && ValidateUtil.isTelephone(this.classified.getPhone()))) {
			this.addFieldError("classified.phone", "输入的格式不正确");
		}

		if ((!ValidateUtil.isRequired(this.classified.getQqmsn())) && ValidateUtil.isDigital(this.classified.getQqmsn()) && (ValidateUtil.isEmail(this.classified.getQqmsn()))) {
			this.addFieldError("classified.qqmsn", "输入的格式不正确");
		}
		//验证所属分类是否有选择
		if (ValidateUtil.isRequired(cat_attr) || cat_attr.indexOf("0")>-1) {
			this.addFieldError("cate_attr", "请选择分类");
		}
		// 将处理后的所属分类串注入到对象中
		this.classified.setCat_attr(cat_attr);
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			classified.setInfo_state("0");
		} else {
			classified.setInfo_state("1");
		}
		classified.setCust_id(this.session_cust_id);
		classified.setNo_reason("");
		classified.setUser_id(this.session_user_id);
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return cate();
			}
		}
		//字段验证
		super.commonValidateField(classified);
		if(super.ifvalidatepass){
			return cate();
		}
		
		this.classifiedService.insertGetPk(classified,objList);	//DAO层重载insertGetPk方法	
		this.addActionMessage("新增分类信息成功");
		this.classified = null;
		is_crotorl=true;
		return cate();
	}

	/**
	 * 方法描述：修改记录动态分类信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 用于所属分类的回选开始
		loadCategory();
		// 用于所属地区的回选开始
		loadArea();
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("classified");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.classified.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}
		//验证所属地区是否有选择
		if (ValidateUtil.isRequired(area_attr) || hidden_area_value.indexOf("0")>-1) {
			this.addFieldError("area_attr", "请选择地区");
		}
		
		this.classified.setArea_attr(area_attr);
		
		
		if ((!ValidateUtil.isRequired(this.classified.getPhone())) && ValidateUtil.isDigital(this.classified.getPhone())
				&& (ValidateUtil.isMobile(this.classified.getPhone()) && ValidateUtil.isTelephone(this.classified.getPhone()))) {
			this.addFieldError("classified.phone", "输入的格式不正确");
		}

		if ((!ValidateUtil.isRequired(this.classified.getQqmsn())) && ValidateUtil.isDigital(this.classified.getQqmsn()) && (ValidateUtil.isEmail(this.classified.getQqmsn()))) {
			this.addFieldError("classified.qqmsn", "输入的格式不正确");
		}
		//验证所属分类是否有选择
		if("".equals(cat_attr) || cat_attr==null ){
			cat_attr=this.classified.getCat_attr();
		}
		if (ValidateUtil.isRequired(cat_attr)) {
			this.addFieldError("cat_attr", "请选择分类");
		}else{
			classified.setCat_attr(cat_attr);
		}
		
		classified.setCust_id(this.session_cust_id);
		classified.setNo_reason("");
		classified.setUser_id(this.session_user_id);
		//字段验证
		super.commonValidateField(classified);
		if(super.ifvalidatepass){
			return view();
		}
		// 修改数据
		this.classifiedService.update(classified,objList,info_infoattr_id);//DAO层重载update方法

		
		this.addActionMessage("修改分类信息成功");
		return list();
	}

	/**
	 * 方法描述：删除记录动态分类信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		del();
		return list();
	}
	
	public void del(){
		String id = this.classified.getInfo_id();
		id = id.replace(" ", "");
		if (id!=null&&!"".equals(id)) {
			id = id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				Classified classified=this.classifiedService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],classified.getIn_date());
				this.classifiedService.delete(ids[i]);
			}
			this.addActionMessage("删除分类信息成功");	
		}
	}
	/**
	 * 方法描述：审核分类信息批量删除
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 */
	public String checkDel() throws Exception {
		del();
		return   auditList();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		Map pageMap = new HashMap();
		// 找出当前登录会员的标识
		String org_id = "";
		//找出当前用户的ORG_ID,如果是系统管理员则不过滤,做为过滤条件
		if ((this.getSession().getAttribute(Constants.ORG_ID) != null) && !"".equals(this.getSession().getAttribute(Constants.ORG_ID))
				&& !"3".equals(this.getSession().getAttribute(Constants.USER_TYPE))) {
			org_id = this.getSession().getAttribute(Constants.ORG_ID).toString();
			pageMap.put("area_attr", org_id);
		}
		if (title_s != null && !"".equals(title_s)) {
			pageMap.put("title", title_s);
		}
		if (cust_name_s != null && !"".equals(cust_name_s)) {
			pageMap.put("cust_name", cust_name_s);
		}
		if (is_recom_s != null && !"".equals(is_recom_s)) {
			pageMap.put("is_recom", is_recom_s);
		}
		// 获取搜索的所在地区
		if (area_attr_s != null && !area_attr_s.equals("")) {
			pageMap.put("area_attr", area_attr_s);
		}
		// 获取搜索的所属分类
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("cat_attr", cat_attr_s);
		}
		if (starttime_s != null && !"".equals(starttime_s))
			pageMap.put("starttime", starttime_s);
		if (endtime_s != null && !"".equals(endtime_s))
			pageMap.put("endtime", endtime_s);
		if (today != null && !"".equals(today)) {
			pageMap.put("today", this.today);
		} else {
			if (info_state_s != null && !"".equals(info_state_s) && !"4".equals(info_state_s)) {
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

		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		
		//过滤地区
		pageMap=super.areafilter(pageMap);

		// 根据页面提交的条件找出信息总数
		int count = this.classifiedService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count, pageMap);
		classifiedList = this.classifiedService.getList(pageMap);
		classifiedList = CategoryFuc.replaceList(classifiedList, "");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录动态分类信息信息
	 * 
	 * @return
	 * @throws Exception 
	 */

	public String view() throws Exception {
		if(classified.getCust_id()!=null){
			if(accessControl(classified.getCust_id())){
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
			if (classified.getCust_id() != null) {
				Member mem = this.memberService.get(classified.getCust_id());
				if (mem != null) {
					if (mem.getArea_attr() != null) {
						backArea(mem.getArea_attr());
					}
				}
			}
		} else {
			// 根据ID获取所属地区的串
			backArea(this.classified.getArea_attr());
		}
		// 获取参数表的列表
		Map paramap = new HashMap();
		paramap.put("para_code", para_code);
		commparalist = commparaService.getList(paramap);

		//分类ID转名称
		catIdTocatName(this.classified.getCat_attr());
		//根据模块是否启动分类属性
		sysmodule = this.sysmoduleService.get("classified");
		if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){			
			if(ischange==null){//第一次加载页面
				info_infoattr_id = this.classified.getInfoattr_id();
			}
			getIsCatAttr(info_infoattr_id,classified.getCat_attr());		
		}else{
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(classified.getCat_attr());	
		}
		
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：审核图片列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditList() throws Exception {
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		pageMap.put("info_state_in", "0,2");
		String org_id = "";		
		//找出当前用户的ORG_ID,做为过滤条件
		if ((this.getSession().getAttribute(Constants.ORG_ID) != null) && !this.getSession().getAttribute(Constants.ORG_ID).equals("")) {
			
		}
		if (title_s != null && !title_s.equals("")) {
			pageMap.put("title", title_s);
		}
		if (cust_name_s != null && !cust_name_s.equals("")) {
			pageMap.put("cust_name", cust_name_s);
		}
		if (is_recom_s != null && !is_recom_s.equals("")) {
			pageMap.put("is_recom", is_recom_s);
		}
		// 获取搜索的所在地区
		if (area_attr_s != null && !area_attr_s.equals("")) {
			pageMap.put("area_attr", area_attr_s);
		}
		// 获取搜索的所属分类
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("cat_attr", cat_attr_s);
		}
		if (starttime_s != null && !starttime_s.equals("")){
			pageMap.put("starttime", starttime_s);
		}
			
		if (endtime_s != null && !endtime_s.equals("")){
			pageMap.put("endtime", endtime_s);
		}
		pageMap.put("info_state_in", "0,2");
		if (info_state_s != null && !info_state_s.equals(""))
			pageMap.put("info_state", info_state_s);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count = this.classifiedService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count, pageMap);
		classifiedList = this.classifiedService.getList(pageMap);
		classifiedList = CategoryFuc.replaceList(classifiedList, "");
		return goUrl(AUDITLIST);
	}

	/**
	 * 方法描述：审核图片
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		
		//进入审核页面时候的加载方法
		auditView("classified",classified.getInfo_id());
		
		if (classified.getInfo_state() != null) {
			noReasonKey = classified.getInfo_state();
		} else {
			noReasonKey = "";
		}
		String cat_attr="";
		if(classified.getCat_attr() != null){
			cat_attr = CategoryFuc.getCateNameByMap(classified.getCat_attr());
			classified.setCat_attr(cat_attr);
		}
		
		
		
		// 操作者为会员见则从会员表中取出地区数据
		if (Constants.MEMBER_TYPE.equals(this.session_cust_type)) {
			if (this.classified.getCust_id() != null) {
				Member mem = this.memberService.get(classified.getCust_id());
				if (mem != null) {
					if (mem.getArea_attr() != null) {
						backArea(mem.getArea_attr());
					}
				}
			}
		} else {
			//找出地区字段的存入隐藏值中
			if (classified.getArea_attr() != null) {
				String area_attr = AreaFuc.getAreaNameByMap(classified.getArea_attr());
				classified.setArea_attr(area_attr);
			}
		}
		Map map = new HashMap();
		map.put("cat_attr", cat_attr);
		//返回产品属性列表	
		attrList = categoryattrService.getList(map);
		
		// 找出属性列表
		String infoattr_id = classified.getInfoattr_id();
		sysmodule = this.sysmoduleService.get("classified");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			backAttr(infoattr_id,classified.getCat_attr());
		}		
		return goUrl(AUDIT);
	}

	/**
	 * 方法描述：审核图片
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditstate() throws Exception {
		
		//启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("classified",classified.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}

		if (this.classified.getInfo_state() == null || "".equals(this.classified.getInfo_state())) {
			this.addFieldError("classified.info_state", "请选择审核状态");
			return audit();
		}

		if ("2".equals(classified.getInfo_state())) {
			if (ValidateUtil.isRequired(classified.getNo_reason())) {
				this.addFieldError("classified.no_reason", "请输入拒绝理由");
				noReasonKey = "2";
				return audit();
			}
		}

		classified.setInfo_state(this.classified.getInfo_state());
		classified.setNo_reason(this.classified.getNo_reason());
		classified.setUser_id(this.session_user_id);
		this.classifiedService.updateauditClassified(classified);
		this.addActionMessage("审核分类信息成功");
		return auditList();
	}

	/**
	 * 方法描述：批量修改推荐
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String classid = this.classified.getInfo_id();
		String isrecom = this.classified.getIs_recom();
		classid = classid.replace(" ", "");
		String classStr[] = classid.split(",");
		List classifiedList = new ArrayList();
		if (classStr.length > 0) {
			for (int i = 0; i < classStr.length; i++) {
				HashMap classMap = new HashMap();
				classMap.put("is_recom", isrecom);
				classMap.put("info_id", classStr[i]);
				classifiedList.add(classMap);
			}
		}
		this.classifiedService.updateisrecom(classifiedList);
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
	 * @return the ClassifiedList
	 */
	public List getClassifiedList() {
		return classifiedList;
	}

	/**
	 * @param classifiedList
	 *  the ClassifiedList to set
	 */
	public void setClassifiedList(List classifiedList) {
		this.classifiedList = classifiedList;
	}



	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getInfo_state_s() {
		return info_state_s;
	}

	public void setInfo_state_s(String info_state_s) {
		this.info_state_s = info_state_s;
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

	public List getSelfCatList() {
		return selfCatList;
	}

	public void setSelfCatList(List selfCatList) {
		this.selfCatList = selfCatList;
	}

	public String getHidden_area_value() {
		return hidden_area_value;
	}

	public void setHidden_area_value(String hidden_area_value) {
		this.hidden_area_value = hidden_area_value;
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

	public String getNoReasonKey() {
		return noReasonKey;
	}

	public void setNoReasonKey(String noReasonKey) {
		this.noReasonKey = noReasonKey;
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
	 * @return the classified
	 */
	public Classified getClassified() {
		return classified;
	}

	/**
	 * @param Classified
	 *            the classified to set
	 */
	public void setClassified(Classified classified) {
		this.classified = classified;
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
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if (classified == null) {
			classified = new Classified();
		}
		String id = classified.getInfo_id();
		if (!ValidateUtil.isDigital(id)) {
			classified = this.classifiedService.get(id);
		}
		System.out.println(classified);
	}

	public Sysmodule getSysmodule() {
		return sysmodule;
	}

	public void setSysmodule(Sysmodule sysmodule) {
		this.sysmodule = sysmodule;
	}
	
	

}
