/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: AdvinfoAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Advinfo;
import com.rbt.model.Advpos;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IAdvposService;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICommparaService;
import com.rbt.service.ISysmoduleService;

/**  
 * @function 功能 广告信息Action层实现  
 * @author 创建人 邱景岩
 * @date 创建日期 Jul 7, 2011 5:51:04 PM
 */
@Controller
public class AdvinfoAction extends BaseAction implements Preparable{

	/**
	 * 序列化          
	 */
	private static final long serialVersionUID = 7575836661236820120L;
          
	/*
	 * 搜索字段 adv_name_s:广告名称 adv_type_s:广告类型 start_date_s:广告名称 end_date_s:广告类型
	 * adv_state_s:审核状态
	 */
	public String adv_name_s;
	public String pos_type_s;
	public String start_date_s;
	public String end_date_s;
	public String adv_state_s;
	public String iscount_s;
	public String posid;
	/*
	 * 验证关键字标识
	 */
	public String keywordTip = "";
	public String moduletypeTip = "";
	public String catattrTip = "";
	public String is_page;
	/*
	 * 广告信息对象
	 */
	public Advinfo advinfo;
	/*
	 * 广告对象
	 */
	public Advpos advpos;
	/*
	 * 广告信息service业务层接口
	 */
	@Autowired
	public IAdvinfoService advinfoService;
	@Autowired
	public IAdvposService advposService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	/*
	 * 分类业务接口
	 */
	@Autowired
	public ICategoryService categoryService;
	/*
	 * 广告信息集合
	 */
	public List advinfoList;
	/*
	 * 信息集合
	 */
	public List intrList;
	/*
	 * 参数集合
	 */
	public List advcommparaList;
	/*
	 * 所属模块集合
	 */
	public List modulecommparaList;
	/*
	 * 广告位名称集合
	 */
	public List posnameList;
	/*
	 *存入所属分类串的隐藏域 
	 */
	public String hiddenvalue;
    /*
	 * 定义隐藏的等级
	 */
	public String hidden_up_level;
	/*
	 * 定义所属分类串的上一级ID
	 */
	public String hidden_up_cate_id; 
	/*
	 * 模板类型隐藏字段
	 */
	public String type;
	/*
	 * 单/多图片上传字段
	 */
	public String img_path;
	public String img_pathmulti;
	/*
	 * 搜索字段
	 */
	public String module_type_s;
	public String title_s;
	//模板类型
	public String module_type;
	public String area_attr_s;
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 搜索框下来列表的绑定
		setcom_or_mod();
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (adv_name_s != null && !adv_name_s.equals("")) {
			pageMap.put("adv_name", adv_name_s);
		}
		if (pos_type_s != null && !pos_type_s.equals("")&&!pos_type_s.equals("0")){
			pageMap.put("pos_type", pos_type_s);
		}
		if (start_date_s != null && !start_date_s.equals("")) {
			pageMap.put("start_date", start_date_s);
		}
		if (end_date_s != null && !end_date_s.equals("")) {
			pageMap.put("end_date", end_date_s);
		}
		if (posid != null && !posid.equals("")) {
			pageMap.put("pos_id", posid);
		}
		if (adv_state_s != null && !adv_state_s.equals("")) {
			pageMap.put("adv_state", adv_state_s);
		}
		if (iscount_s != null && !iscount_s.equals("")) {
			pageMap.put("iscount", iscount_s);
		}
		if (module_type_s != null && !module_type_s.equals("")) {
			pageMap.put("module_type", module_type_s);
		}
		if (area_attr_s != null && !area_attr_s.equals("")) {
			pageMap.put("area_attr", area_attr_s);
		}		
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.advinfoService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		advinfoList = this.advinfoService.getList(pageMap);
		advinfoList = CategoryFuc.replaceList(advinfoList,"module_type");
		if(advinfoList!=null&&advinfoList.size()>0){
			Map postMap=new HashMap();
			for(int i=0;i<advinfoList.size();i++){
				Map valueMap=(Map) advinfoList.get(i);
				String post_type = "";
				if(valueMap.get("pos_type")!=null){
					post_type = valueMap.get("pos_type").toString();
				}
				postMap.put("para_value", post_type);
				List commparaList=this.commparaService.getList(postMap);
				String value="";
				if(commparaList!=null&&commparaList.size()>0){
				   Map paramap=(Map)commparaList.get(0);
				   value=paramap.get("para_key").toString();
				}
				valueMap.put("pos_type", value);
			}
		}
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：跳转到新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		setcom_or_mod();
		Map posnameMap = new HashMap();
		posnameList = this.advposService.getPosNameList(posnameMap);
		advpos = this.advposService.get(this.advpos.getPos_id());	
		return goUrl(ADD);
	}
	/**
	 * 方法描述：新增广告信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 用于所属地区的回选开始
		loadArea();		
		String pos_type=getRequest().getParameter("type");
		if(pos_type!=null&&pos_type.equals("a")){
			if(ValidateUtil.isRequired(this.img_path)){
				this.addFieldError("advinfo.img_path", "请上传图片");
			}
			if(ValidateUtil.isRequired(this.advinfo.getAdv_code())){
				this.addFieldError("advinfo.adv_code", "请输入广告代码");
			}
			this.advinfo.setImg_path(img_path);
		}
		if(pos_type!=null&&pos_type.equals("b")){
			if(ValidateUtil.isRequired(this.advinfo.getLink_url())){
				this.addFieldError("advinfo.link_url", "请输入链接地址");
			}
			if(ValidateUtil.isRequired(this.advinfo.getTitle())){
				this.addFieldError("advinfo.title", "请输入显示标题");
			}	
			if(ValidateUtil.isRequired(this.advinfo.getContent())){
				this.addFieldError("advinfo.content", "请输入显示描述");
			}	
		}
		if(pos_type!=null&&pos_type.equals("c")){
			if(ValidateUtil.isRequired(this.img_path)){
				this.addFieldError("advinfo.img_path", "请上传图片");
			}
			if(ValidateUtil.isRequired(this.advinfo.getLink_url())){
				this.addFieldError("advinfo.link_url", "请输入链接地址");
			}
			if(ValidateUtil.isRequired(this.advinfo.getTitle())){
				this.addFieldError("advinfo.title", "请输入显示标题");
			}
			this.advinfo.setImg_path(img_path);	
		}
		if(pos_type!=null&&pos_type.equals("d")){
			if(ValidateUtil.isRequired(this.advinfo.getLink_url())){
				this.addFieldError("advinfo.link_url", "请输入链接地址");
			}
			if(ValidateUtil.isRequired(this.advinfo.getFlash_url())){
				this.addFieldError("advinfo.flash_url", "请输入flash链接地址");
			}
		}
		if(pos_type!=null&&pos_type.equals("e")){
			if(ValidateUtil.isRequired(this.img_pathmulti)){
				this.addFieldError("img_pathmulti", "请上传图片");
			}
			if(ValidateUtil.isRequired(this.advinfo.getLink_url())){
				this.addFieldError("advinfo.link_url", "请输入链接地址");
			}
			if(ValidateUtil.isRequired(this.advinfo.getTitle())){
				this.addFieldError("advinfo.title", "请输入显示标题");
			}
			this.advinfo.setImg_path(img_pathmulti);	
		}
		if(pos_type!=null&&pos_type.equals("f")){
			if(ValidateUtil.isRequired(this.advinfo.getKeyword())){
				this.addFieldError("advinfo.keyword", "请输入关键字");
			}
			if(ValidateUtil.isRequired(this.advinfo.getInfo_id())){
				this.addFieldError("advinfo.info_id", "请输入信息标识");
			}
			if(ValidateUtil.isDigital(this.advinfo.getInfo_id())){
				this.addFieldError("advinfo.info_id", "信息标识只能为数字");
			}
		}
		if(pos_type!=null&&pos_type.equals("g")){
			
			if(ValidateUtil.isRequired(this.advinfo.getLink_url())){
				this.addFieldError("advinfo.link_url", "请输入链接地址");
			}
			if(ValidateUtil.isRequired(this.advinfo.getTitle())){
				this.addFieldError("advinfo.title", "请输入显示标题");
			}
			if(ValidateUtil.isRequired(this.advinfo.getContent())){
				this.addFieldError("advinfo.content", "请输入显示描述");
			}	
			//验证分类是选择
			validateCategoryIfSelect();
		}	
		advinfo.setCat_attr(cat_attr);
		this.advinfo.setPos_id(getRequest().getParameter("advpos.pos_id"));
		this.advinfo.setArea_attr(area_attr);
		//字段验证
		super.commonValidateField(advinfo);
		if(super.ifvalidatepass){
			return add();
		}
		this.advinfoService.insert(advinfo);
		this.addActionMessage("新增广告成功");
		this.advinfo = null;
		return add();
	}
	/**
	 * 方法描述： 删除广告信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		this.advinfoService.delete(this.advinfo.getAdv_id());
		this.addActionMessage("删除广告成功");
		return list();
	}

	/**
	 * 方法描述：根据主键找出广告信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(advinfo.getCust_id()!=null){
			if(accessControl(advinfo.getCust_id())){
				return list();
			}
		}
		if (advinfo.getPos_id() != null) {
			advpos = this.advposService.get(advinfo.getPos_id());
		}
		if(advpos != null){
			if(advpos.getPos_type().equals("a")||advpos.getPos_type().equals("c")){
				this.img_path=advinfo.getImg_path();
			}
			if(advpos.getPos_type().equals("e")){
				this.img_pathmulti=advinfo.getImg_path();
			}
		}
		// 找出地区字段的存入隐藏值中
		backArea(advinfo.getArea_attr());	
		//判断advinfo对象是否为空
        backCategory(advinfo.getCat_attr());
		setcom_or_mod();
		Map posnameMap = new HashMap();
		posnameList = this.advposService.getPosNameList(posnameMap);
		HttpServletRequest request =getRequest();
		request.setCharacterEncoding("UTF-8");
		//显示信息
		Map pageMap = new HashMap();
		// 根据页面提交的条件找出信息总数
		String module_typename=request.getParameter("tablename");
		int count = this.advinfoService.getCount(pageMap);
		if (module_typename != null && !module_typename.equals("")){
			pageMap.put("tablename", module_typename);
		}
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 获取列表
		intrList = this.advinfoService.getAdvinfoIntr(pageMap);
		return goUrl(VIEW);
	}	
	/**
	 * 方法描述：
	 * 
	 * @return
	 * @throws Exception
	 */
	public void setcom_or_mod(){
		Map commodMap = new HashMap();
		commodMap.put("para_code", "adv_type");
		advcommparaList = this.commparaService.getList(commodMap);
		Map pageMap = new HashMap();
    	pageMap.put("state_code", "0");
    	modulecommparaList = this.sysmoduleService.getList(pageMap);	
	}
	/**
	 * 方法描述：修改广告信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 保存地区的串，用于回选地区
		loadArea();
		String id = advinfo.getAdv_id();
		//判断实体ID是否存在
		if(ValidateUtil.isDigital(id)){
			return list();
		}
		String pos_type=getRequest().getParameter("type");
		if(pos_type!=null&&pos_type.equals("a")){
			if(ValidateUtil.isRequired(this.img_path)){
				this.addFieldError("advinfo.img_path", "请上传图片");
			}
			if(ValidateUtil.isRequired(this.advinfo.getAdv_code())){
				this.addFieldError("advinfo.adv_code", "请输入广告代码");
			}
			this.advinfo.setImg_path(img_path);
		}
		if(pos_type!=null&&pos_type.equals("b")){
			if(ValidateUtil.isRequired(this.advinfo.getLink_url())){
				this.addFieldError("advinfo.link_url", "请输入链接地址");
			}
			if(ValidateUtil.isRequired(this.advinfo.getTitle())){
				this.addFieldError("advinfo.title", "请输入显示标题");
			}	
			if(ValidateUtil.isRequired(this.advinfo.getContent())){
				this.addFieldError("advinfo.content", "请输入显示描述");
			}	
		}
		if(pos_type!=null&&pos_type.equals("c")){
			if(ValidateUtil.isRequired(this.img_path)){
				this.addFieldError("advinfo.img_path", "请上传图片");
			}
			if(ValidateUtil.isRequired(this.advinfo.getLink_url())){
				this.addFieldError("advinfo.link_url", "请输入链接地址");
			}
			if(ValidateUtil.isRequired(this.advinfo.getTitle())){
				this.addFieldError("advinfo.title", "请输入显示标题");
			}
			this.advinfo.setImg_path(img_path);	
		}
		if(pos_type!=null&&pos_type.equals("d")){
			if(ValidateUtil.isRequired(this.advinfo.getLink_url())){
				this.addFieldError("advinfo.link_url", "请输入链接地址");
			}
			if(ValidateUtil.isRequired(this.advinfo.getFlash_url())){
				this.addFieldError("advinfo.flash_url", "请输入flash链接地址");
			}
		}
		if(pos_type!=null&&pos_type.equals("e")){
			if(ValidateUtil.isRequired(this.img_pathmulti)){
				this.addFieldError("img_pathmulti", "请上传图片");
			}
			if(ValidateUtil.isRequired(this.advinfo.getLink_url())){
				this.addFieldError("advinfo.link_url", "请输入链接地址");
			}
			if(ValidateUtil.isRequired(this.advinfo.getTitle())){
				this.addFieldError("advinfo.title", "请输入显示标题");
			}
			this.advinfo.setImg_path(img_pathmulti);	
		}
		if(pos_type!=null&&pos_type.equals("f")){
			if(ValidateUtil.isRequired(this.advinfo.getKeyword())){
				this.addFieldError("advinfo.keyword", "请输入关键字");
			}
			if(ValidateUtil.isRequired(this.advinfo.getInfo_id())){
				this.addFieldError("advinfo.info_id", "请输入信息标识");
			}
			if(ValidateUtil.isDigital(this.advinfo.getInfo_id())){
				this.addFieldError("advinfo.info_id", "信息标识只能为数字");
			}
		}
		if(pos_type!=null&&pos_type.equals("g")){	
			if(ValidateUtil.isRequired(this.advinfo.getLink_url())){
				this.addFieldError("advinfo.link_url", "请输入链接地址");
			}
			if(ValidateUtil.isRequired(this.advinfo.getTitle())){
				this.addFieldError("advinfo.title", "请输入显示标题");
			}
			if(ValidateUtil.isRequired(this.advinfo.getContent())){
				this.addFieldError("advinfo.content", "请输入显示描述");
			}	
			//验证分类是选择
			validateCategoryIfSelect();
		}
		advinfo.setCat_attr(cat_attr);
		this.advinfo.setArea_attr(area_attr);
		//字段验证
		super.commonValidateField(advinfo);
		if(super.ifvalidatepass){
			return view();
		}
		this.advinfoService.update(advinfo);
		this.addActionMessage("修改广告成功");
		return list();
	}
	/**
	 * 方法描述：找出广告位下的所有广告
	 * 
	 * @return
	 * @throws Exception
	 */
	public String indexinfo() throws Exception {
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		//显示信息
		Map pageMap = new HashMap();
		// 根据页面提交的条件找出信息总数
		String module_typename=request.getParameter("tablename");
		int count = this.advinfoService.getCount(pageMap);
		if (module_typename != null && !module_typename.equals("")){
			pageMap.put("tablename", module_typename);
		}
		if (title_s != null && !title_s.equals("")){
			pageMap.put("title", title_s);
		}
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 获取列表
		intrList = this.advinfoService.getAdvinfoIntr(pageMap);
		return INPUT;
	}
	
	

	
	/**
	 * @return the adv_name_s
	 */
	public String getAdv_name_s() {
		return adv_name_s;
	}

	/**
	 * @param adv_name_s
	 *            the adv_name_s to set
	 */
	public void setAdv_name_s(String adv_name_s) {
		this.adv_name_s = adv_name_s;
	}

	/**
	 * @return the pos_type_s
	 */
	public String getPos_type_s() {
		return pos_type_s;
	}

	/**
	 * @param pos_type_s
	 *            the pos_type_s to set
	 */
	public void setPos_type_s(String pos_type_s) {
		this.pos_type_s = pos_type_s;
	}

	/**
	 * @return the start_date_s
	 */
	public String getStart_date_s() {
		return start_date_s;
	}

	/**
	 * @param start_date_s
	 *            the start_date_s to set
	 */
	public void setStart_date_s(String start_date_s) {
		this.start_date_s = start_date_s;
	}

	/**
	 * @return the end_date_s
	 */
	public String getEnd_date_s() {
		return end_date_s;
	}

	/**
	 * @param end_date_s
	 *            the end_date_s to set
	 */
	public void setEnd_date_s(String end_date_s) {
		this.end_date_s = end_date_s;
	}

	/**
	 * @return the keywordTip
	 */
	public String getKeywordTip() {
		return keywordTip;
	}

	/**
	 * @param keywordTip
	 *            the keywordTip to set
	 */
	public void setKeywordTip(String keywordTip) {
		this.keywordTip = keywordTip;
	}

	/**
	 * @return the moduletypeTip
	 */
	public String getModuletypeTip() {
		return moduletypeTip;
	}

	/**
	 * @param moduletypeTip
	 *            the moduletypeTip to set
	 */
	public void setModuletypeTip(String moduletypeTip) {
		this.moduletypeTip = moduletypeTip;
	}

	/**
	 * @return the catattrTip
	 */
	public String getCatattrTip() {
		return catattrTip;
	}

	/**
	 * @param catattrTip
	 *            the catattrTip to set
	 */
	public void setCatattrTip(String catattrTip) {
		this.catattrTip = catattrTip;
	}

	/**
	 * @return the advinfoList
	 */
	public List getAdvinfoList() {
		return advinfoList;
	}

	/**
	 * @param advinfoList
	 *            the advinfoList to set
	 */
	public void setAdvinfoList(List advinfoList) {
		this.advinfoList = advinfoList;
	}

	/**
	 * @return the advinfo
	 */
	public Advinfo getAdvinfo() {
		return advinfo;
	}

	/**
	 * @param advinfo
	 *            the advinfo to set
	 */
	public void setAdvinfo(Advinfo advinfo) {
		this.advinfo = advinfo;
	}

	/**
	 * @return the advcommparaList
	 */
	public List getAdvcommparaList() {
		return advcommparaList;
	}

	/**
	 * @param advcommparaList
	 *            the advcommparaList to set
	 */
	public void setAdvcommparaList(List advcommparaList) {
		this.advcommparaList = advcommparaList;
	}

	/**
	 * @return the modulecommparaList
	 */
	public List getModulecommparaList() {
		return modulecommparaList;
	}

	/**
	 * @param modulecommparaList
	 *            the modulecommparaList to set
	 */
	public void setModulecommparaList(List modulecommparaList) {
		this.modulecommparaList = modulecommparaList;
	}

	/**
	 * @return the posnameList
	 */
	public List getPosnameList() {
		return posnameList;
	}

	/**
	 * @param posnameList
	 *            the posnameList to set
	 */
	public void setPosnameList(List posnameList) {
		this.posnameList = posnameList;
	}

	/**
	 * @return the advpos
	 */
	public Advpos getAdvpos() {
		return advpos;
	}

	/**
	 * @param advpos
	 *            the advpos to set
	 */
	public void setAdvpos(Advpos advpos) {
		this.advpos = advpos;
	}
	/**
	 * @return the adv_state_s
	 */
	public String getAdv_state_s() {
		return adv_state_s;
	}

	/**
	 * @param adv_state_s
	 *            the adv_state_s to set
	 */
	public void setAdv_state_s(String adv_state_s) {
		this.adv_state_s = adv_state_s;
	}

	/**
	 * @return the iscount_s
	 */
	public String getIscount_s() {
		return iscount_s;
	}

	/**
	 * @param iscount_s
	 *            the iscount_s to set
	 */
	public void setIscount_s(String iscount_s) {
		this.iscount_s = iscount_s;
	}

	/**
	 * @return the hiddenvalue
	 */
	public String getHiddenvalue() {
		return hiddenvalue;
	}

	/**
	 * @param hiddenvalue the hiddenvalue to set
	 */
	public void setHiddenvalue(String hiddenvalue) {
		this.hiddenvalue = hiddenvalue;
	}

	/**
	 * @return the hidden_up_level
	 */
	public String getHidden_up_level() {
		return hidden_up_level;
	}

	/**
	 * @param hidden_up_level the hidden_up_level to set
	 */
	public void setHidden_up_level(String hidden_up_level) {
		this.hidden_up_level = hidden_up_level;
	}

	/**
	 * @return the hidden_up_cate_id
	 */
	public String getHidden_up_cate_id() {
		return hidden_up_cate_id;
	}

	/**
	 * @param hidden_up_cate_id the hidden_up_cate_id to set
	 */
	public void setHidden_up_cate_id(String hidden_up_cate_id) {
		this.hidden_up_cate_id = hidden_up_cate_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public String getImg_pathmulti() {
		return img_pathmulti;
	}

	public void setImg_pathmulti(String img_pathmulti) {
		this.img_pathmulti = img_pathmulti;
	}

	public String getModule_type_s() {
		return module_type_s;
	}

	public void setModule_type_s(String module_type_s) {
		this.module_type_s = module_type_s;
	}

	public List getIntrList() {
		return intrList;
	}

	public void setIntrList(List intrList) {
		this.intrList = intrList;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}
	
	public String getModule_type() {
		return module_type;
	}

	public void setModule_type(String module_type) {
		this.module_type = module_type;
	}
	
	public String getPosid() {
		return posid;
	}

	public void setPosid(String posid) {
		this.posid = posid;
	}

	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(advinfo == null){
			advinfo = new Advinfo();
		}
		String id = this.advinfo.getAdv_id();
		if(!ValidateUtil.isDigital(id)){
			advinfo = this.advinfoService.get(id);
		}
	}
}
