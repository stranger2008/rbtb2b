/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: GalleryAction.java 
 */
package com.rbt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.function.AuditModelFuc;
import com.rbt.function.CategoryAttrFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Buy;
import com.rbt.model.Gallery;
import com.rbt.model.Subject;
import com.rbt.service.ICategoryService;
import com.rbt.service.IChannelService;
import com.rbt.service.IGalleryService;
import com.rbt.service.IGallerypicService;
import com.rbt.service.ISysuserService;

/**
 * @function 功能 记录图库信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Mon Jul 25 14:50:37 CST 2011
 */
@Controller
public class GalleryAction extends baseModelAction implements Preparable{

	private static final long serialVersionUID = 1L;
	/*
	 * 记录图库信息对象
	 */
	public Gallery gallery;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IGalleryService galleryService;
	@Autowired
	public IGallerypicService gallerypicService;
	@Autowired
	public ISysuserService sysuserService;
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public IChannelService channelService;

	/*
	 * 记录图库信息信息集合
	 */
	public List galleryList;
	/*
	 * 记录图片信息信息集合
	 */
	public List gallerypciList;
	/*
	 * 记录用户信息集合
	 */
	public List sysuserList;
	/*
	 * 搜索字段
	 */
	public String cust_name_s;
	public String title_s;
	public String user_name;
	public String is_recom_s;
	public String info_state_s;
	public String starttime_s;
	public String endtime_s;
	public String cat_attr_s;
	/*
	 * 显示审核不通过的理由输入框
	 */
	public String noReasonKey;
	/*
	 * 定义隐藏的搜索字段
	 */
	public String search_cat_attr;	
	/*
	 * 类别字段
	 */
	private String today;
	private String modType="gallery";

	
	/**
	 * @author : 林俊钦
	 * @date : Jul 13, 2012 1:42:18 PM
	 * @Method Description : 会员发布条数的判断
	 */
	public boolean controlInfoNum(){
		//查出已发布的信息个数
		Map tmap = new HashMap();
		tmap.put("cust_id", this.session_cust_id);
		int count = this.galleryService.getCount(tmap);
		if(controlMsgNum(count,modType)){
			return true;
		}else{
			return false;
		}
	}


	
	/**
	 * 方法描述：返回新增记录图库信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cate() throws Exception {
		//获取所属模块名是否支持分类属性
		sysmodule = this.sysmoduleService.get("gallery");
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
					gallery = new Gallery();
					gallery.setGal_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		} else {
			if (this.gallery != null && this.gallery.getGal_id() != null && !this.gallery.getGal_id().equals("")){
				return view();
			} else {
				return cate();
			}
		}
	}
	
	

	/**
	 * 方法描述：新增记录图库信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 用于所属分类的回选开始
		loadCategory();
		//验证分类是选择
		validateCategoryIfSelect();
		// 将处理后的所属分类串注入到buy对象中
		gallery.setCust_id(this.session_cust_id);
		gallery.setCat_attr(cat_attr);
		gallery.setNo_reason("");
		gallery.setClicknum("0");
		gallery.setFare("0");
		gallery.setUser_id("0");
		
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			gallery.setIs_recom("0");
			gallery.setInfo_state("0");
		}else{
			gallery.setInfo_state("1");
		}
		
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("gallery");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.gallery.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return cate();
			}
		}
		//字段验证
		super.commonValidateField(gallery);
		if(super.ifvalidatepass){
			return cate();
		}
		String gal_id=this.galleryService.insertGetPk(gallery, objList);;// 获取刚刚插入成功的记录的ID
		// 对插入成功的信息生成HTML静态页面
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, gal_id);		
		this.addActionMessage("新增图库信息成功");
		// 找出当前登录会员的标识
        if(this.session_cust_type.equals(Constants.ADMIN_TYPE)){
        	getResponse().sendRedirect("/admin_Gallerypic_add.action?gallerypic.gal_id="+gal_id);	
        }else{
        	getResponse().sendRedirect("/member_Gallerypic_add.action?gallerypic.gal_id="+gal_id);	
        }			
		this.gallery = null;
		is_crotorl=true;
		return goUrl("cate");
	}

	/**
	 * 方法描述：修改记录图库信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 用于所属分类的回选开始
		loadCategory();
		//验证所属分类是否有选择
		if (ValidateUtil.isRequired(cat_attr) || cat_attr.indexOf("0")>-1) {
			this.addFieldError("cate_attr", "请选择分类");
		}
		// 将处理后的所属分类串注入到buy对象中
		this.gallery.setCat_attr(cat_attr);
		gallery.setUser_id(this.session_user_id);
		// 数据库原有的分类串	
		supply_infoattr_id = gallery.getInfoattr_id();	
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("gallery");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.gallery.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}else{
			supply_infoattr_id=null;
		}					

		//字段验证
		super.commonValidateField(gallery);
		if(super.ifvalidatepass){
			return view();
		}
		this.galleryService.update(gallery, objList, supply_infoattr_id);
		if ("1".equals(this.gallery.getInfo_state())) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.gallery.getGal_id());
		}
		this.addActionMessage("修改图库信息成功");
		return list();
	}

	/**
	 * 方法描述：删除记录图库信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		del();
		return list();
	}
	
	public void del(){
		String id = this.gallery.getGal_id();
		id = id.replace(" ", "");
		if (id!=null&&!"".equals(id)) {
			id = id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				Gallery gallery=this.galleryService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],gallery.getIn_date());
				this.galleryService.delete(ids[i]);
			}
			this.addActionMessage("删除图库信息成功");	
		}
	}
	/**
	 * 方法描述：审核记录图库信息批量删除
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
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");		
		
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (cust_name_s != null && !cust_name_s.equals(""))pageMap.put("cust_name", cust_name_s);
		if (title_s != null && !title_s.equals(""))pageMap.put("title", title_s);
		if (starttime_s != null && !starttime_s.equals(""))pageMap.put("starttime", starttime_s);
		if (endtime_s != null && !endtime_s.equals(""))pageMap.put("endtime", endtime_s);
		if (is_recom_s != null && !is_recom_s.equals(""))pageMap.put("is_recom", is_recom_s);
		if(today!=null&&!today.equals("")){pageMap.put("today",this.today);
		}else{
			if (info_state_s != null && !info_state_s.equals("") && !info_state_s.equals("4")) {
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
		
		// 获取搜索的所属分类
		if (request.getParameter("search_cat_attr") != null) {
			String cat_attr = request.getParameter("search_cat_attr");
			pageMap.put("cat_attr", cat_attr);
		}
		if (request.getParameter("cat_attr_s") != null) {
			String cat_attr_s = request.getParameter("cat_attr_s");
			pageMap.put("cat_attr", cat_attr_s);
		}
		
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.galleryService.getCount(pageMap);

		//分页插件
		pageMap = super.pageTool(count,pageMap);
		galleryList = this.galleryService.getList(pageMap);
		galleryList = CategoryFuc.replaceList(galleryList,"");

		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录图库信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(gallery.getCust_id()!=null){
			if(accessControl(gallery.getCust_id())){
				return list();
			}
		}
		noReasonKey = gallery.getInfo_state();
		//分类ID转名称
		catIdTocatName(this.gallery.getCat_attr());
		//根据模块是否启动分类属性
		sysmodule = this.sysmoduleService.get("gallery");
		if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){			
			if(ischange==null){//第一次加载页面
				info_infoattr_id = this.gallery.getInfoattr_id();
			}
			getIsCatAttr(info_infoattr_id,gallery.getCat_attr());				
		}else{
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(gallery.getCat_attr());	
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
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (cust_name_s != null && !cust_name_s.equals(""))
			pageMap.put("cust_name", cust_name_s);
		if (title_s != null && !title_s.equals(""))
			pageMap.put("title", title_s);
		if (starttime_s != null && !starttime_s.equals(""))
			pageMap.put("starttime", starttime_s);
		if (endtime_s != null && !endtime_s.equals(""))
			pageMap.put("endtime", endtime_s);
		if (is_recom_s != null && !is_recom_s.equals(""))
			pageMap.put("is_recom", is_recom_s);
		if (info_state_s != null && !info_state_s.equals("")){
			pageMap.put("info_state", info_state_s);		    
		}	
		pageMap.put("info_state_in", "0,2");
		// 获取搜索的所属分类
		if (request.getParameter("search_cat_attr") != null) {
			String cat_attr = request.getParameter("search_cat_attr");
			pageMap.put("cat_attr", cat_attr);
		}
		if (request.getParameter("cat_attr_s") != null) {
			String cat_attr_s = request.getParameter("cat_attr_s");
			pageMap.put("cat_attr", cat_attr_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.galleryService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		galleryList = this.galleryService.getList(pageMap);
		galleryList = CategoryFuc.replaceList(galleryList,"");
		return goUrl(AUDITLIST);
	}

	/**
	 * 方法描述：审核图片
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		String id=gallery.getGal_id();
		if(id == null || "".equals(id)){
			return auditList();
		}
		
		//进入审核页面时候的加载方法
		auditView("gallery",gallery.getGal_id());
		
		String infoattr_id = gallery.getInfoattr_id();
		sysmodule = this.sysmoduleService.get("gallery");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			backAttr(infoattr_id,gallery.getCat_attr());
		}		
		noReasonKey = gallery.getInfo_state();
		if(gallery.getCat_attr() != null){
			String cat_attr = CategoryFuc.getCateNameByMap(gallery.getCat_attr());
			gallery.setCat_attr(cat_attr);
		}
		
		Map pageMap = new HashMap();
		pageMap.put("gal_id", id);
		// 根据页面提交的条件找出信息总数
		int count = this.galleryService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		gallerypciList = this.gallerypicService.getList(pageMap);
		return goUrl(AUDIT);
	}

	/**
	 * 方法描述：审核图片
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditState() throws Exception {
		
		//启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("gallery",gallery.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		
		if (this.gallery.getInfo_state() == null || this.gallery.getInfo_state().equals("")) {
			this.addFieldError("gallery.info_state", "请选择审核状态");
			return audit();
		}
		if (gallery.getInfo_state().equals("2")) {
			if (ValidateUtil.isRequired(gallery.getNo_reason())) {
				this.addFieldError("download.no_reason", "请输入拒绝理由");
				setNoReasonKey("2");
				return audit();
			}
		}
		gallery.setInfo_state(this.gallery.getInfo_state());
		gallery.setNo_reason(this.gallery.getNo_reason());
		gallery.setUser_id(this.session_user_id);
		this.galleryService.updateauditGallery(gallery);
		if ("1".equals(this.gallery.getInfo_state())) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.gallery.getGal_id());
		}
		this.addActionMessage("审核图库信息成功");
		return auditList();
	}
	/**
	 * 方法描述：批量修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String galid = this.gallery.getGal_id();
		String isrecom = this.gallery.getIs_recom();
		galid = galid.replace(" ", "");
		String galidStr[] = galid.split(",");
		List galList = new ArrayList();
		if (galidStr.length > 0) {
			for (int i = 0; i < galidStr.length; i++) {
				Map galleryMap = new HashMap();
				galleryMap.put("is_recom", isrecom);
				galleryMap.put("gal_id", galidStr[i]);
				galList.add(galleryMap);
			}
		}
		this.galleryService.updateisrecom(galList);
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
	 * @return the GalleryList
	 */
	public List getGalleryList() {
		return galleryList;
	}

	/**
	 * @param galleryList
	 *            the GalleryList to set
	 */
	public void setGalleryList(List galleryList) {
		this.galleryList = galleryList;
	}

	public List getSysuserList() {
		return sysuserList;
	}

	public void setSysuserList(List sysuserList) {
		this.sysuserList = sysuserList;
	}

	public String getCust_name_s() {
		return cust_name_s;
	}

	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

	public List getGallerypciList() {
		return gallerypciList;
	}

	public void setGallerypciList(List gallerypciList) {
		this.gallerypciList = gallerypciList;
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
	

	public String getSearch_cat_attr() {
		return search_cat_attr;
	}

	public void setSearch_cat_attr(String search_cat_attr) {
		this.search_cat_attr = search_cat_attr;
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
	 * @return the gallery
	 */
	public Gallery getGallery() {
		return gallery;
	}

	/**
	 * @param Gallery
	 *            the gallery to set
	 */
	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(gallery == null){
			gallery = new Gallery();
		}
		String id = gallery.getGal_id();
		if(!ValidateUtil.isDigital(id)){
			gallery = this.galleryService.get(id);
		}
		System.out.println(gallery);
	}

}
