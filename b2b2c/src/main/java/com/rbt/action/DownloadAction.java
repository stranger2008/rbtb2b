/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: DownloadAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.rbt.function.CommparaFuc;
import com.rbt.model.Download;
import com.rbt.model.Sysmodule;
import com.rbt.model.Video;
import com.rbt.service.ICategoryService;
import com.rbt.service.IChannelService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IDownloadService;
import com.rbt.service.IMemberService;
import com.rbt.service.ISysuserService;

/**
 * @function 功能 记录下载信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 28 13:55:21 CST 2011
 */
@Controller
public class DownloadAction extends baseModelAction implements Preparable{

	private static final long serialVersionUID = 1L;
	/*
	 * 记录下载信息对象
	 */
	public Download download;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IDownloadService downloadService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public ISysuserService sysuserService;
	@Autowired
	public IChannelService channelService;
	@Autowired
	public IMemberService memberService;
	public CategoryFuc categoryFuc;
	/*
	 * 记录下载信息信息集合
	 */
	public List downloadList;
	/*
	 * 记录用户信息集合
	 */
	public List sysuserList;
	/*
	 * 类型模板集合
	 */
	public List commparaList;
	/*
	 * 参数匹配列表
	 */
	public List commparaList_value;
	
	public List commparalist;
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
	public String file_type_s;
	public String download_infoattr_id;
	/*
	 * 系统模块对象
	 */
	public Sysmodule sysmodule;
	
	/*
	 * 显示审核不通过的理由输入框
	 */
	public String noReasonKey;
	public String downloadid;
	private String modType = "download";
	private String today;
	
	/*
	 * 定义常量字段,用于从基本参数中找出相应的模块值
	 */
	public String para_code = "classified_type";

	

	
	/**
	 * 方法描述：返回新增记录下载信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//绑定下拉列表
		getcommpara();
		return goUrl(ADD);
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
		int count = this.downloadService.getCount(tmap);
		if(controlMsgNum(count,modType)){
			return true;
		}else{
			return false;
		}
	}
	

	

	/**
	 * 方法描述：AJAX更新浏览次数
	 * @return
	 * @throws Exception
	 */
	@org.apache.struts2.convention.annotation.Action("downloadclick")
	public void updateClickNum() throws Exception {
		PrintWriter out = getResponse().getWriter();
		this.downloadService.updateClickNum(downloadid);
		Download sy = new Download();
		sy = this.downloadService.get(downloadid);
		out.write(sy.getClicknum());
	}

	
	
	/**
	 * 方法描述：新增记录下载信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public String insert() throws Exception {
		//字符编码
		getRequest().setCharacterEncoding("UTF-8");
        // 用于所属分类的回选开始
		loadCategory();
		//验证分类是选择
		validateCategoryIfSelect();
		if ("0".equals(this.download.getSize_unit())) {
			this.addFieldError("download.size_unit", "文件大小单位不能为空");
		}

		if ("0".equals(this.download.getFile_type())) {
			this.addFieldError("download.file_type", "请选择文件类型");
		}
		
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("download");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.download.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}

		download.setCust_id(this.session_cust_id);
		download.setUser_id(this.session_user_id);
		download.setCat_attr(cat_attr);
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return cate();
			}
		}
		//字段验证
		super.commonValidateField(download);
		if(super.ifvalidatepass){
			return cate();
		}
		String down_id = this.downloadService.insertGetPk(download,objList);//DAO层重载insertGetPk方法	
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, down_id);
		this.addActionMessage("新增下载信息成功");
		this.download = null;
		is_crotorl=true;
		return cate();
	}

	/**
	 * 方法描述：修改记录下载信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
        // 用于所属分类的回选开始
		loadCategory();

		if ("0".equals(this.download.getSize_unit())) {
			this.addFieldError("download.size_unit", "文件大小单位不能为空");
		}

		if ("0".equals(this.download.getFile_type())) {
			this.addFieldError("download.file_type", "请选择文件类型");
		}
		download.setUser_id(this.session_user_id);
		
		// 数据库原有的分类串	
		download_infoattr_id = download.getInfoattr_id();	
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("download");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.download.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}else{
			download_infoattr_id=null;
		}					
		this.download.setCat_attr(cat_attr);
		//字段验证
		super.commonValidateField(download);
		if(super.ifvalidatepass){
			return view();
		}
		// 修改数据
		this.downloadService.update(download,objList,download_infoattr_id);//DAO层重载update方法
		if ("1".equals(this.download.getInfo_state())) {
			//对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.download.getDown_id());
		}
		this.addActionMessage("修改下载信息成功");
		return list();
	}

	/**
	 * 方法描述：删除记录下载信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		del();
		return list();
	}
	
	public void del(){
		String id = this.download.getDown_id();
		id = id.replace(" ", "");
		if (id!=null&&!"".equals(id)) {
			id = id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				Download download=this.downloadService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],download.getIn_date());
				this.downloadService.delete(ids[i]);
			}
			this.addActionMessage("删除下载信息成功");	
		}
	}
	/**
	 * 方法描述：审核下载批量删除信息
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
		//绑定文件类型下拉列表
		getcommpara();
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		if (cust_name_s != null && !"".equals(cust_name_s)) {
			pageMap.put("cust_name", cust_name_s);
		}
		if (title_s != null && !"".equals(title_s)) {
			pageMap.put("title", title_s);
		}
		if (starttime_s != null && !"".equals(starttime_s)) {
			pageMap.put("starttime", starttime_s);
		}
		if (endtime_s != null && !"".equals(endtime_s)) {
			pageMap.put("endtime", endtime_s);
		}
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
		//表字段的转换
		if (file_type_s != null && !"".equals(file_type_s)) {
				pageMap.put("file_type", file_type_s);
		}
		if (is_recom_s != null && !"".equals(is_recom_s)) {
			pageMap.put("is_recom", is_recom_s);
		}
		// 获取搜索的所属分类
		if (request.getParameter("cat_attr_s") != null && !"".equals(request.getParameter("cat_attr_s"))) {
			String cat_attr = request.getParameter("cat_attr_s");
			pageMap.put("cat_attr", cat_attr);
		}
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}

		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count = this.downloadService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		downloadList = this.downloadService.getList(pageMap);
		downloadList = categoryFuc.replaceList(downloadList, "file_type");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录下载信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(download.getCust_id()!=null){
			if(accessControl(download.getCust_id())){
				return list();
			}
		}
		//下拉列表绑定
		getcommpara();
		// 获取参数表的列表
		Map paramap = new HashMap();
		paramap.put("para_code", para_code);
		commparalist = commparaService.getList(paramap);
		//分类ID转名称
		catIdTocatName(this.download.getCat_attr());
		//根据模块是否启动分类属性
		sysmodule = this.sysmoduleService.get("download");
		if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){			
			if(ischange==null){//第一次加载页面
				info_infoattr_id = this.download.getInfoattr_id();
			}
			getIsCatAttr(info_infoattr_id,download.getCat_attr());			
		}else{
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(download.getCat_attr());	
		}		
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：批量修改
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String downid = this.download.getDown_id();
		String isrecom = this.download.getIs_recom();
		downid = downid.replace(" ", "");
		String downStr[] = downid.split(",");
		List downList = new ArrayList();
		if (downStr.length > 0) {
			for (int i = 0; i < downStr.length; i++) {
				Map galleryMap = new HashMap();
				galleryMap.put("is_recom", isrecom);
				galleryMap.put("down_id", downStr[i]);
				downList.add(galleryMap);
			}
		}
		this.downloadService.updateisrecom(downList);
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
	 * 方法描述：审核下载列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditList() throws Exception {
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		//绑定文件类型下拉列表
		getcommpara();
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		pageMap.put("info_state_in", "0,2");//未通过，未审核状态
		if (cust_name_s != null && !"".equals(cust_name_s)){
			pageMap.put("cust_name", cust_name_s);
		}
		if (title_s != null && !"".equals(title_s)){
			pageMap.put("title", title_s);
		}
		//表字段的转换
		if (file_type_s != null && !"".equals(file_type_s)) {
				pageMap.put("file_type", file_type_s);
		}
		if (starttime_s != null && !"".equals(starttime_s)){
			pageMap.put("starttime", starttime_s);
		}
		if (endtime_s != null && !"".equals(endtime_s)){
			pageMap.put("endtime", endtime_s);
		}
		
		if (is_recom_s != null && !"".equals(is_recom_s)){
			pageMap.put("is_recom", is_recom_s);
		}
		if (info_state_s != null && !"".equals(info_state_s)){
			pageMap.put("info_state", info_state_s);
		}
		// 获取搜索的所属分类
		if (request.getParameter("search_cat_attr") != null) {
			String cat_attr = request.getParameter("search_cat_attr");
			pageMap.put("cat_attr", cat_attr);
		}
		// 获取搜索的所属分类
		if (request.getParameter("cat_attr_s") != null && !"".equals(request.getParameter("cat_attr_s"))) {
			String cat_attr = request.getParameter("cat_attr_s");
			pageMap.put("cat_attr", cat_attr);
		}
		//根据页面提交的条件找出信息总数
		int count = this.downloadService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		downloadList = this.downloadService.getList(pageMap);
		downloadList = categoryFuc.replaceList(downloadList, "file_type");
		return goUrl(AUDITLIST);
	}

	/**
	 * 方法描述：审核下载页面回显
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {

		//进入审核页面时候的加载方法
		auditView("download",download.getDown_id());
		
		
		//绑定文件类型下拉列表
		getcommpara();
		if(download.getInfo_state()!=null){
		noReasonKey = download.getInfo_state();
		}else{
			noReasonKey="";
		}
		//获取分类和文件类型的中文字符
		if(download.getCat_attr() != null){
			String cat_attr = CategoryFuc.getCateNameByMap(download.getCat_attr().toString());
		   download.setCat_attr(cat_attr);
		}
		if(download.getFile_type() != null){
			String file_type = CommparaFuc.get_commparakey_By_value(download.getFile_type(), "file_type");
			download.setFile_type(file_type);
		}
		
		// 找出属性列表
		String infoattr_id = download.getInfoattr_id();
		sysmodule = this.sysmoduleService.get("classified");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			backAttr(infoattr_id,download.getCat_attr());
		}		
		return goUrl(AUDIT);
	}
	
	public void getcommpara(){
		Map map_list = new HashMap();
		map_list.put("para_code", "file_type");
		commparaList = commparaService.getList(map_list);
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
		if(auditInfoState("download",download.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		if (this.download.getInfo_state() == null || "".equals(this.download.getInfo_state())) {
			this.addFieldError("download.info_state", "请选择审核状态");
			return audit();
		}

		download.setInfo_state(this.download.getInfo_state());
		download.setUser_id(this.session_user_id);
		this.downloadService.updateauditDownload(download);
		if ("1".equals(this.download.getInfo_state())) {
			//对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.download.getDown_id());
		}
		this.addActionMessage("审核图库信息成功");
		return auditList();
	}
	
	/**
	 * @MethodDescribe 方法描述 根据系统模块中对应的模块是否支持分类属性跳转到新增供应页面
	 * @author 创建人 林俊钦
	 * @date 创建日期 Oct 21, 2011 11:14:29 AM
	 */
	public String cate() throws Exception{
		//绑定下拉列表
		getcommpara();
		// 找出参数类型
		Map paramap = new HashMap();
		paramap.put("para_code", para_code);
		commparalist = commparaService.getList(paramap);
		//获取所属模块名是否支持分类属性
		sysmodule = this.sysmoduleService.get("download");
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
					download = new Download();
					download.setDown_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		} else {
			if (this.download != null && this.download.getDown_id() != null && !this.download.getDown_id().equals("")){
				return view();
			} else {
				return cate();
			}
		}
	}
	

	/**
	 * @return the DownloadList
	 */
	public List getDownloadList() {
		return downloadList;
	}

	/**
	 * @param downloadList
	 *  the DownloadList to set
	 */
	public void setDownloadList(List downloadList) {
		this.downloadList = downloadList;
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

	public List getSysuserList() {
		return sysuserList;
	}

	public void setSysuserList(List sysuserList) {
		this.sysuserList = sysuserList;
	}

	public List getCommparaList() {
		return commparaList;
	}

	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}

	public String getCat_attr() {
		return cat_attr;
	}

	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}

	public String getFile_type_s() {
		return file_type_s;
	}

	public void setFile_type_s(String file_type_s) {
		this.file_type_s = file_type_s;
	}

	public String getNoReasonKey() {
		return noReasonKey;
	}

	public void setNoReasonKey(String noReasonKey) {
		this.noReasonKey = noReasonKey;
	}

	public String getDownloadid() {
		return downloadid;
	}

	public void setDownloadid(String downloadid) {
		this.downloadid = downloadid;
	}

	/**
	 * @return the today
	 */
	public String getToday() {
		return today;
	}

	/**
	 * @param today the today to set
	 */
	public void setToday(String today) {
		this.today = today;
	}
	
	/**
	 * @return the download
	 */
	public Download getDownload() {
		return download;
	}

	/**
	 * @param Download
	 *            the download to set
	 */
	public void setDownload(Download download) {
		this.download = download;
	}
	
	/**
	 * @param downloadService
	 *            the downloadService to set
	 */
	public void setDownloadService(IDownloadService downloadService) {
		this.downloadService = downloadService;
	}

	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(download == null){
			download = new Download();
		}
		String id = download.getDown_id();
		if(!ValidateUtil.isDigital(id)){
			download = this.downloadService.get(id);
		}
		System.out.println(download);
	}

	public Sysmodule getSysmodule() {
		return sysmodule;
	}

	public void setSysmodule(Sysmodule sysmodule) {
		this.sysmodule = sysmodule;
	}
	
	

}
