/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: NewsAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.rbt.function.AuditModelFuc;
import com.rbt.function.CategoryAttrFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Category;
import com.rbt.model.Gallerypic;
import com.rbt.model.Job;
import com.rbt.model.Member;
import com.rbt.model.News;
import com.rbt.model.Sysmodule;
import com.rbt.model.Sysuser;
import com.rbt.model.Vote;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICategoryattrService;
import com.rbt.service.IChannelService;
import com.rbt.service.INewsService;
import com.rbt.service.ISysuserService;
import com.rbt.service.IVoteService;

/**
 * @function 功能 资讯管理action类
 * @author 创建人 胡惜坤
 * @date 创建日期 July 8, 2011
 */
@Controller
public class NewsAction extends baseModelAction implements Preparable{

	private static final long serialVersionUID = -6527854462792296077L;
	private static final String type_HotTitle = "'头条'";// 资讯自定义属性的常量值
	private static final String type_Recommend = "'推荐'";// 资讯自定义属性的常量值
	private static final String type_Filmslide = "'幻灯'";// 资讯自定义属性的常量值
	private static final String type_Roll = "'滚动'";// 资讯自定义属性的常量值
	private static final String type_Bold = "'加粗'";// 资讯自定义属性的常量值
	private String modType="news";
	public String isrecom="";
	/*
	 * 搜索区域字段 
	 */
	public String title_s;
	public String news_attr_s;
	public String source_s;
	public String cat_attr_s;
	public String in_date_s;
	public String state_code_s;
	public String cust_id_s;
	public String comment_s;
	public String fare_s;
	public String author_s;
	public String clicknum_s;	
	public String search_area_attr;
	public String search_cat_attr;
	public String info_state_s;
	public String area_attr_s;
	/*
	 * 实体类对象 
	 */
	public News news;
	public Vote vote;
	/*
	 * 用于判断是否需要查找今天的内容
	 */
	public String today;	
	public String newsid;
	/*
	 * 列表页资讯信息集合
	 */
	public List newsList;
	/*
	 * 获取自定义属性的字符串
	 */
	public String[] attStr;
	/*
	 * 用于前台回选自定义属性的绑定
	 */
	public List attrStrList;
	/*
	 * 投票选择列表
	 */
	public List voteList;
	/*
	 * 显示审核不通过的理由输入框
	 */
	public String noReasonKey;
	/*
	 * 审核属性串
	 */
	public String auditattrString;
	/*
	 * 搜索发布时间的最大时间
	 */
	public String enddateString_s;	
	/*
	 * 定义供应类别表对象
	 */
	public Category category;
	
	/*
	 * 商城、团购公告
	 */
	public List  goods_newsList;
	//判断 商城、团购公告 类型 0：资讯 1：商城最新公告  2：团购公告
	public String news_mold;
	//公告类型
	public String mold;
	
	/*
	 * Service接口
	 */
	@Autowired
	public ICategoryattrService categoryattrService;
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public IChannelService channelService;
	@Autowired
	public INewsService newsService;
	@Autowired
	public IVoteService voteService;
	@Autowired
	public ISysuserService sysuserService;
	public Sysmodule sysmodule;
	private String news_infoattr_id;

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
					news = new News();
					news.setNews_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		} else {
			if (this.news != null && this.news.getNews_id() != null && !this.news.getNews_id().equals("")){
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
		int count = this.newsService.getCount(tmap);
		if(controlMsgNum(count,modType)){
			return true;
		}else{
			return false;
		}
	}

	
	
	/**
	 * 方法描述：返回新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cate() throws Exception {
		Map map = new HashMap();
		voteList = this.voteService.getList(map);// 绑定投票选项的下拉框
		//系统管理员的匿名，在发布资讯的时候默认填入责任编辑的名字
		if(this.session_cust_type.equals(Constants.ADMIN_TYPE)){
			Sysuser sysuser = sysuserService.get(this.session_user_id);
			if(sysuser!=null && sysuser.getNike_name()!=null){
				this.news.setAuthor(sysuser.getNike_name());
			}
		}
		//获取所属模块名是否支持分类属性
		sysmodule = this.sysmoduleService.get("news");
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
	 * 方法描述：AJAX获取资讯的标题
	 * 
	 * @return
	 * @throws Exception
	 */
	@org.apache.struts2.convention.annotation.Action("subjectnews")
	public void getSubjectNews() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		News sy = new News();
		sy = this.newsService.get(newsid);
		out.write(sy.getTitle());
	}

	/**
	 * 方法描述：AJAX获取资讯的标题
	 * 
	 * @return
	 * @throws Exception
	 */
	@org.apache.struts2.convention.annotation.Action("subjectnewsimg")
	public void getSubjectNewsImg() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = getResponse().getWriter();
		News sy = new News();
		sy = this.newsService.get(newsid);
		out.write(sy.getTitle() + "&&" + sy.getLitpic());
	}
	/**
	 * 方法描述：新增资讯信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 用于所属分类的回选开始
		loadCategory();
		// 用于所属地区的回选开始
		loadArea();
		if (existsTitle(news.getTitle(),"","news","title")) {
			this.addFieldError("news.title", "标题已经存在不能重复");
		}
		//验证分类是选择
		validateCategoryIfSelect();
		// 将处理后的所属分类串注入到对象中
		this.news.setCat_attr(cat_attr);
		news.setCat_attr(cat_attr);
		news.setArea_attr(area_attr);
		if (news.getTitle_color() == null || news.getTitle_color().equals("")) {
			news.setTitle_color("");
		}
		news.setIs_delete("0");
		news.setUser_id(this.session_user_id);
		news.setCust_id(this.session_cust_id);
		news.setNo_reason("");
		// 当前登录的用户是会员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			news.setAuthor("");
			news.setClicknum("0");
			news.setComment("1");
			news.setFare("0");
			news.setKeyword("");
			news.setNews_attr("");
			news.setSource("");
			news.setVote_id("0");
			news.setOut_link("");
			news.setSort_data_number("0");
			news.setSourcelink("");
			news.setInfo_state("0");
		}
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("news");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.news.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return cate();
			}
		}
		//字段验证
		super.commonValidateField(news);
		if(super.ifvalidatepass){
			return cate();
		}
		String news_id = this.newsService.insertGetPk(news,objList);// 获取刚刚插入成功的记录的ID
		// 对插入成功的信息生成HTML静态页面
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, news_id);
		this.addActionMessage("新增资讯信息成功");
		is_crotorl=true;
		return cate();
	}

	/**
	 * 方法描述：审核资讯信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditState() throws Exception {
		
		//启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("news",news.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		
		
        String newsidString=news.getNews_id();
        if(ValidateUtil.isDigital(newsidString))
        {
        	return list();
        }
		if (news.getInfo_state() == null && news.getInfo_state().equals("")) {
			this.addFieldError("news.info_state", "请选择审核状态");
			return goUrl(AUDIT);
		}
		if (news.getInfo_state().equals("2") && ValidateUtil.isRequired(news.getNo_reason())) {
			this.addFieldError("news.no_reason", "请输入拒绝理由");
			noReasonKey = "2";
			return goUrl(AUDIT);
		}
		Map pageMap = new HashMap();
		if (!news.getInfo_state().equals("2")) {
			pageMap.put("no_reason", "");
		}
		else {
			pageMap.put("no_reason", news.getNo_reason());
		}
		pageMap.put("news_id", news.getNews_id());
		pageMap.put("info_state", news.getInfo_state());
		this.newsService.updateState(pageMap);
		if (this.news.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.news.getNews_id());
		}
		this.addActionMessage("审核资讯信息成功");
		return auditList();
	}
    
	
	
	/**
	 * 方法描述：修改资讯信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 用于所属分类的回选开始
		loadCategory();
		// 用于所属地区的回选开始
		loadArea();
		String newsidString=news.getNews_id();
        if(ValidateUtil.isDigital(newsidString))
        {
        	return list();
        }
        if (existsTitle(news.getTitle(),oldinfotitle,"news","title")) {
			this.addFieldError("news.title", "标题已经存在不能重复");
		}
        //验证分类是选择
		validateCategoryIfSelect();
		this.news.setArea_attr(area_attr);
		this.news.setCat_attr(cat_attr);
		// 当前登录的用户是管理员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)
				&& news.getInfo_state().equals("2")
				&& ValidateUtil.isRequired(news.getNo_reason())) {
			this.addFieldError("news.no_reason", "请输入拒绝理由");
			noReasonKey = "2";
		}
		if (!news.getInfo_state().equals("2")) {
			news.setNo_reason("");
		}
		//存入地区
		this.news.setArea_attr(area_attr);
		if (news.getTitle_color() == null || news.getTitle_color().equals("")) {
			news.setTitle_color("");
		}
		news.setIs_delete("0");
		// 当前登录的用户是会员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			news.setInfo_state("0");
		}
		
		// 数据库原有的分类串	
		news_infoattr_id = news.getInfoattr_id();	
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("news");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.news.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}else{
			news_infoattr_id=null;
		}			
		this.news.setCat_attr(cat_attr);
		
		//字段验证
		super.commonValidateField(news);
		if(super.ifvalidatepass){
			return view();
		}
		this.newsService.update(news,objList,news_infoattr_id);
		if (this.news.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.news.getNews_id());
		}
		
			this.addActionMessage("修改资讯信息成功");
			return list();
	}
	
	/**
	 * 方法描述：删除
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 */
	public void del(){
		String id = this.news.getNews_id();
		id = id.replace(" ", "");
		if (id!=null&&!"".equals(id)) {
			id = id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				News news=this.newsService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],news.getIn_date());
				this.newsService.delete(ids[i]);
			}	
		}
	}
	/**
	 * 方法描述：删除资讯信息，放入回收站中
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		del();
		//商城公告
		if (news_mold != null && "1".equals(news_mold.trim())){
			news_mold="1";
			mold="商城";
			this.addActionMessage("删除商城公告成功");
			return goodsNewsList();
		}
		//团购公告 2
		else if (news_mold != null && "2".equals(news_mold.trim())){
			news_mold="2";
			mold="团购";
			this.addActionMessage("删除团购公告成功");
			return goodsNewsList();
		}
		else{
			this.addActionMessage("删除资讯信息成功");
			return list();
		}
	}
	
	/**
	 * 方法描述：审核资讯批量删除
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 * @date : Jul 9, 2012 9:20:09 AM
	 */
	public String checkDel() throws Exception {
			del();
			this.addActionMessage("删除审核资讯信息成功");
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
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		if (title_s != null && !title_s.equals(""))
			pageMap.put("title", title_s);
		if (news_attr_s != null && !news_attr_s.equals(""))
			pageMap.put("news_attr", news_attr_s);
		if (source_s != null && !source_s.equals(""))
			pageMap.put("source", source_s);
		if (cat_attr_s != null && !cat_attr_s.equals(""))
			pageMap.put("cat_attr", cat_attr_s);
		if (in_date_s != null && !in_date_s.equals(""))
			pageMap.put("in_date", in_date_s);
		if (state_code_s != null && !state_code_s.equals("") && !state_code_s.equals("4"))
			pageMap.put("info_state", state_code_s);
		if (info_state_s != null && !info_state_s.equals(""))
			pageMap.put("info_state", info_state_s);
		if (cust_id_s != null && !cust_id_s.equals(""))
			pageMap.put("cust_id", cust_id_s);
		if (comment_s != null && !comment_s.equals("") && !comment_s.equals("3"))
			pageMap.put("comment", comment_s);
		if (fare_s != null && !fare_s.equals(""))
			pageMap.put("fare", fare_s);
		if (author_s != null && !author_s.equals(""))
			pageMap.put("author", author_s);
		if (clicknum_s != null && !clicknum_s.equals(""))
			pageMap.put("clicknum", clicknum_s);
		if (enddateString_s != null && !enddateString_s.equals(""))
			pageMap.put("enddate", enddateString_s);	
		if(search_cat_attr!=null&&!search_cat_attr.equals("")){
			pageMap.put("cat_attr", search_cat_attr);
		}		
		
		// 获取搜索的所属地区
		if (request.getParameter("area_attr_s") != null&&!request.getParameter("area_attr_s").equals("")) {
			String area_attr = request.getParameter("area_attr_s");
			pageMap.put("area_attr", area_attr);
		}
		//判断是否从今天新增中进入进行操作
		if(today!=null&&!today.equals("")){
			pageMap.put("today",this.today);
		}else{
			// 操作者为会员则获取所有审核状态的信息，否则获取审核通过与禁用状态的信息
			if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
				pageMap.put("state_code_in", "0,1,2,3");// 审核通过状态
			} else {
				pageMap.put("state_code_in", "1,3");// 审核通过状态
			}
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.newsService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 找出信息列表，放入list
		newsList = this.newsService.getList(pageMap);
		newsList=com.rbt.function.CategoryFuc.replaceList(newsList,"");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：获取未审核的资讯
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditList() throws Exception {
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (title_s != null && !title_s.equals(""))
			pageMap.put("title", title_s);
		if (news_attr_s != null && !news_attr_s.equals(""))
			pageMap.put("news_attr", news_attr_s);
		if (source_s != null && !source_s.equals(""))
			pageMap.put("source", source_s);
		if (cat_attr_s != null && !cat_attr_s.equals(""))
			pageMap.put("cat_attr", cat_attr_s);
		if (in_date_s != null && !in_date_s.equals(""))
			pageMap.put("in_date", in_date_s);
		if (cust_id_s != null && !cust_id_s.equals(""))
			pageMap.put("cust_id", cust_id_s);
		if (comment_s != null && !comment_s.equals("") && !comment_s.equals("3"))
			pageMap.put("comment", comment_s);
		if (fare_s != null && !fare_s.equals(""))
			pageMap.put("fare", fare_s);
		if (author_s != null && !author_s.equals(""))
			pageMap.put("author", author_s);
		if (clicknum_s != null && !clicknum_s.equals(""))
			pageMap.put("clicknum", clicknum_s);
		if (enddateString_s != null && !enddateString_s.equals(""))
			pageMap.put("enddate", enddateString_s);
		if (info_state_s != null && !info_state_s.equals("")) {
			pageMap.put("info_state", info_state_s);
		}
		if (state_code_s != null && !state_code_s.equals("") && !state_code_s.equals("4")) {
			pageMap.put("info_state", state_code_s);// 未审核状态
		} else {
			pageMap.put("state_code_in", "0,2");// 未审核状态
		}
		if(search_cat_attr!=null&&!search_cat_attr.equals(""))
		{
			pageMap.put("cat_attr", search_cat_attr);
		}
		// 获取搜索的所属地区
		if (request.getParameter("search_area_attr") != null&&!request.getParameter("search_area_attr").equals(""))
		{
			String area_attr = request.getParameter("search_area_attr");
			pageMap.put("area_attr", area_attr);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.newsService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 找出信息列表，放入list
		newsList = this.newsService.getList(pageMap);
		newsList=com.rbt.function.CategoryFuc.replaceList(newsList,"");
		return goUrl(AUDITLIST);
	}

	/**
	 * 方法描述：根据主键找出资讯信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(news.getCust_id()!=null){
			if(accessControl(news.getCust_id())){
				return list();
			}
		}
		Map map = new HashMap();		
		// 将从数据库中查询的所属分类存入分类隐藏域中
		backCategory(news.getCat_attr());	
		// 找出地区字段的存入隐藏值中
		backArea(news.getArea_attr());		
		noReasonKey = news.getInfo_state();
		if (news.getNews_attr() != null) {
			voteList = this.voteService.getList(map);
			String attrString = news.getNews_attr().replace(" ", "");
			attStr = attrString.split(",");
			attrStrList = new ArrayList();
			for (int i = 0; i < attStr.length; i++) {
				attrStrList.add(attStr[i].toString());
			}
			HttpServletRequest request = getRequest();
			request.setAttribute("attr", attrStrList);
		}
		
		//分类ID转名称
		catIdTocatName(this.news.getCat_attr());
		//根据模块是否启动分类属性
		sysmodule = this.sysmoduleService.get("news");
		if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){			
			if(ischange==null){//第一次加载页面
				info_infoattr_id = this.news.getInfoattr_id();
			}
			getIsCatAttr(info_infoattr_id,news.getCat_attr());		
		}else{
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(news.getCat_attr());	
		}
		//商城公告
		if (news_mold != null && "1".equals(news_mold.trim())){
			news_mold="1";
			mold="商城";
			return  goUrl("newsupdate");
		}
		//团购公告 2
		else if (news_mold != null && "2".equals(news_mold.trim())){
			news_mold="2";
			mold="团购";
			return  goUrl("newsupdate");
		}		
		else{
			return goUrl(VIEW);
		}
		
	}
	
	/**
	 * 方法描述：根据主键找出资讯信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		
		//进入审核页面时候的加载方法
		auditView("news",news.getNews_id());
		
		noReasonKey = news.getInfo_state();
		//获取资讯投票标题
		String votenameString="";
		Vote votemodel=new Vote();
		if(news.getVote_id()!=null)
		{
		 votemodel=voteService.get(news.getVote_id());
		}
		if(votemodel!=null&&votemodel.getVote_title()!=null)
		{
			votenameString=votemodel.getVote_title();
		}
		news.setVote_id(votenameString);
		
		if(news.getNews_attr()!=null&&!news.getNews_attr().equals(""))
		{
			String attrString = news.getNews_attr().replace(" ", "");
			attStr = attrString.split(",");
		}
		auditattrString = "";
		// 'h':'头条', 'c':'推荐', 'f':'幻灯','s':'滚动','b':'加粗'
		if(attStr!=null&&attStr.length>0)
		{
			for (int i = 0; i < attStr.length; i++) {
				if (attStr[i].toString().equals("h")) {
					auditattrString += "  " + type_HotTitle + "  ";
				} else if (attStr[i].toString().equals("c")) {
					auditattrString += "  " + type_Recommend + "  ";
				} else if (attStr[i].toString().equals("c")) {
					auditattrString += "  " + type_Filmslide + "  ";
				} else if (attStr[i].toString().equals("c")) {
					auditattrString += "  " + type_Roll + "  ";
				} else if (attStr[i].toString().equals("c")) {
					auditattrString += "  " + type_Bold + "  ";
				}
			}
		}
		this.setHidden_area_value(news.getArea_attr());
		//获取分类中文字符
		String cat_name = "";
		if(news.getCat_attr() != null){
			cat_name = CategoryFuc.getCateNameByMap(news.getCat_attr());
		}
		news.setCat_attr(cat_name);
		//获取地区中文字符
		String area_name = "";
		if(news.getArea_attr() != null){
			area_name = AreaFuc.getAreaNameByMap(news.getArea_attr());
		}
		news.setArea_attr(area_name);
		// 找出属性列表
		String infoattr_id = news.getInfoattr_id();
		sysmodule = this.sysmoduleService.get("product");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			backAttr(infoattr_id,news.getCat_attr());
		}
			return goUrl(AUDIT);
	}
	
	/**
	 * 方法描述：资讯批量推荐、取消推荐
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 * @date : Jul 4, 2012 9:30:45 AM
	 */
	public String updateisrecom() throws Exception {
		String tip = "";
		String news_id = this.news.getNews_id();
		news_id = news_id.replace(" ", "");
		String newsStr[] = news_id.split(",");
		News newss=new News();
		if (newsStr.length > 0) {
				for(int i = 0; i < newsStr.length; i++) {
					if(newsStr[i]!=null && !"".equals(newsStr[i]))
					{
							newss=newsService.get(newsStr[i]);
							String  news_attr =newss.getNews_attr();
							news_attr=news_attr.replace(" ","");
							//推荐
							if("1".equals(isrecom)){
								if(news_attr!=null && !"".equals(news_attr) ){
									if(news_attr.indexOf("c")>-1 ){
										news_attr=news_attr+"";
									}
									else{
										news_attr=news_attr+",c";
									}
								}
								else{
									news_attr=news_attr+"c";
								}
								//推荐成功
								tip="1";
							}
							//取消推荐  a,b,c   c,d,h    c 
							else{
								if(news_attr.indexOf(",c")>-1){
									news_attr=news_attr.replace(",c","");
								}
								else if(news_attr.indexOf("c,")>-1){
									news_attr=news_attr.replace("c,","");
								}
								else{									
									news_attr=news_attr.replace("c","");
								}
								//取消推荐成功
								tip="0";
							}					
							newss.setNews_attr(news_attr); 
							this.newsService.update(newss);								
					}
				}			
			}
		String temp="";
		if ("0".equals(tip)) {
			temp = "取消推荐成功";
		} else if ("1".equals(tip)) {
			temp = "推荐成功";
		}
		this.addActionMessage(temp);
		//商城公告
		if (news_mold != null && "1".equals(news_mold.trim())){
			news_mold="1";
			mold="商城";
			return goodsNewsList();
		}
		//团购公告 2
		else if (news_mold != null && "2".equals(news_mold.trim())){
			news_mold="2";
			mold="团购";
			return goodsNewsList();
		}
		else{
			return list();
		}
	}
	
	
	/**
	 * 方法描述：商城、团购 公告列表
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 */
	public String goodsNewsList() throws Exception {
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		if(news_mold==null || news_mold.equals("")){
			news_mold=request.getAttribute("news_mold").toString();
		}
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		if (title_s != null && !title_s.equals(""))
			pageMap.put("title", title_s);
		if (in_date_s != null && !in_date_s.equals(""))
			pageMap.put("in_date", in_date_s);
		if (state_code_s != null && !state_code_s.equals("") && !state_code_s.equals("4"))
			pageMap.put("info_state", state_code_s);
		if (enddateString_s != null && !enddateString_s.equals(""))
			pageMap.put("enddate", enddateString_s);
		
		//商城公告 1
		if (news_mold != null && "1".equals(news_mold.trim())){
			pageMap.put("news_type", news_mold);
			news_mold="1";
			mold="商城";
		}
		//团购公告 2
		if (news_mold != null && "2".equals(news_mold.trim())){
			pageMap.put("news_type", news_mold);
			news_mold="2";
			mold="团购";
		}
		
		// 根据页面提交的条件找出信息总数
		int count = this.newsService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 找出信息列表，放入list
		goods_newsList = this.newsService.getList(pageMap);
		return goUrl("newsindex");
	}
	public String groupNewsList() throws Exception {
		return goodsNewsList();
	}
	/**
	 * 方法描述：插入商城公告 模块
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 */
	public String newsinsert(){
		//商城公告
		if (news_mold != null && "1".equals(news_mold.trim())){
			news_mold="1";
			mold="商城";
		}
		//团购公告 2
		else if (news_mold != null && "2".equals(news_mold.trim())){
			news_mold="2";
			mold="团购";
		}
		return goUrl("newsinsert");
	}
	/**
	 * 方法描述：插入商城公告
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 */
	public String inseretnotice() throws Exception{
		news.setUser_id(this.session_user_id);
		news.setCust_id(this.session_cust_id);
		news.setNo_reason("");
		//字段验证
		super.commonValidateField(news);
		if(super.ifvalidatepass){
			return goUrl("newsinsert");
		}
		String news_id = this.newsService.insertGetPk(news,objList);// 获取刚刚插入成功的记录的ID
		// 对插入成功的信息生成HTML静态页面
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, news_id);
		this.addActionMessage("新增公告信息成功");
		is_crotorl=true;
		//商城公告
		if (news_mold != null && "1".equals(news_mold.trim())){
			news_mold="1";
			mold="商城";
		}
		//团购公告 2
		else if (news_mold != null && "2".equals(news_mold.trim())){
			news_mold="2";
			mold="团购";
		}
		return goodsNewsList();
	}
	/**
	 * 方法描述：插入商城公告
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 */
	public String updatenotice() throws Exception{	
		//字段验证
		super.commonValidateField(news);
		if(super.ifvalidatepass){
			return view();
		}
		this.newsService.update(news,objList,news_infoattr_id);
		if (this.news.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.news.getNews_id());
		}
		//商城公告
		if (news_mold != null && "1".equals(news_mold.trim())){
			news_mold="1";
			mold="商城";	
		}
		//团购公告 2
		else if (news_mold != null && "2".equals(news_mold.trim())){
			news_mold="2";
			mold="团购";
		}
		this.addActionMessage("修改公告成功");
		return goodsNewsList();
	}
		
	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public List getVoteList() {
		return voteList;
	}

	public void setVoteList(List voteList) {
		this.voteList = voteList;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getNews_attr_s() {
		return news_attr_s;
	}

	public void setNews_attr_s(String news_attr_s) {
		this.news_attr_s = news_attr_s;
	}

	public String getSource_s() {
		return source_s;
	}

	public void setSource_s(String source_s) {
		this.source_s = source_s;
	}

	public String getCat_attr_s() {
		return cat_attr_s;
	}

	public void setCat_attr_s(String cat_attr_s) {
		this.cat_attr_s = cat_attr_s;
	}

	public String getIn_date_s() {
		return in_date_s;
	}

	public void setIn_date_s(String in_date_s) {
		this.in_date_s = in_date_s;
	}

	public String getState_code_s() {
		return state_code_s;
	}

	public void setState_code_s(String state_code_s) {
		this.state_code_s = state_code_s;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public List getNewsList() {
		return newsList;
	}

	public void setNewsList(List newsList) {
		this.newsList = newsList;
	}

	public String getCust_id_s() {
		return cust_id_s;
	}

	public void setCust_id_s(String cust_id_s) {
		this.cust_id_s = cust_id_s;
	}

	public String getComment_s() {
		return comment_s;
	}

	public void setComment_s(String comment_s) {
		this.comment_s = comment_s;
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

	public String getAuthor_s() {
		return author_s;
	}

	public void setAuthor_s(String author_s) {
		this.author_s = author_s;
	}

	public String[] getAttStr() {
		return attStr;
	}

	public void setAttStr(String[] attStr) {
		this.attStr = attStr;
	}

	public List getAttrStrList() {
		return attrStrList;
	}

	public void setAttrStrList(List attrStrList) {
		this.attrStrList = attrStrList;
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

	public String getEnddateString_s() {
		return enddateString_s;
	}
	public void setEnddateString_s(String enddateString_s) {
		this.enddateString_s = enddateString_s;
	}
	public String getCat_attr() {
		return cat_attr;
	}

	public void setCat_attr(String cat_attr) {
		this.cat_attr = cat_attr;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
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
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(news == null){
			news = new News();
		}
		String id = news.getNews_id();
		if(!ValidateUtil.isDigital(id)){
			news = this.newsService.get(id);
		}
	}
}
