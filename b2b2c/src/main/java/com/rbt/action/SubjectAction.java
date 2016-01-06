/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: SubjectAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.function.CategoryAttrFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Ask;
import com.rbt.model.Buy;
import com.rbt.model.News;
import com.rbt.model.Subject;
import com.rbt.service.ICategoryService;
import com.rbt.service.IChannelService;
import com.rbt.service.INewsService;
import com.rbt.service.ISubjectService;

/**
 * @function 功能 专题信息action类
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:19:02 CST 2011
 */
@Controller
public class SubjectAction extends baseModelAction implements Preparable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 742699792371898198L;
	/*
	 * 搜索字段 title_s：专题标题 is_comment_s：是否允许评论 is_recom_s：是否推荐 info_state_s：信息状态
	 * in_date_s：最小发布时间 last_date_s:最大发布时间 fare_s：内容收费
	 */
	public String title_s;
	public String is_comment_s;
	public String is_recom_s;
	public String info_state_s;
	public String in_date_s;
	public String last_date_s;
	public String fare_s;
	public String litpic_s;
	public String selecttype;
	public String modType = "subject";

	/*
	 * 专题信息对象
	 */
	public Subject subject;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ISubjectService subjectService;
	@Autowired 
	public INewsService newsService;
	/*
	 * 分类业务接口
	 */
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public IChannelService channelService;
	/*
	 * 专题信息信息集合
	 */
	public List subjectList;
	/*
	 * 搜索 所属分类隐藏字段
	 */
	public String cat_attr_s;
	public String hidden_up_cate_id;
	public List newsList;
	public News news;
	public String newsid;
	public String newspicid;
	public List cateList;
	public String title_id;
	public String image_id;


	/**
	 * 方法描述：返回新增专题信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cate() throws Exception {
		bandnewscate();		
		//获取所属模块名是否支持分类属性
		sysmodule = this.sysmoduleService.get("subject");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			if(cat_attr == null||cat_attr.equals("0")){
				return goUrl("cate");
			}
			checkIsAttr();				
		}
		return goUrl(ADD);
	}

	public String gopage() throws Exception {
		if (ValidateUtil.isRequired(cat_attr)) {
			this.addFieldError("cate_attr", "分类不能为空");
			return goUrl("cate");	
		}
		if (cat_attr.indexOf("0") > -1) {
				if (update_value != null && !update_value.equals("")) {					
					is_select = "1";
					subject = new Subject();
					subject.setSub_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		} else {
			if (this.subject != null && this.subject.getSub_id() != null && !this.subject.getSub_id().equals("")){
				return view();
			} else {
				return cate();
			}
		}
	}
	
	
	/**
	 * 方法描述：绑定关联栏目
	 */
	public void bandnewscate() {
		HashMap map = new HashMap();
		map.put("module_type", "news");
		cateList = categoryService.getList(map);
	}

	/**
	 * 方法描述：新增专题信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 用于所属分类的回选开始
		loadCategory();
		//验证分类是选择
		validateCategoryIfSelect();
		this.subject.setCat_attr(cat_attr);
		this.subject.setNews_attr(title_id);
		this.subject.setImg_news_attr(image_id);
		// 用于所属分类结束
		subject.setCust_id(this.session_cust_id);
		subject.setUser_id(this.session_user_id);
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("supply");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.subject.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}
		// 字段验证
		super.commonValidateField(subject);
		if (super.ifvalidatepass) {
			return cate();
		}
		String sub_id = this.subjectService.insertGetPk(subject,objList);// 获取刚刚插入成功的记录的ID
		// 对插入成功的信息生成HTML静态页面
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, sub_id);
		this.addActionMessage("新增专题信息成功");
		this.subject = null;
		this.title_id=null;
		this.image_id=null;
		return cate();
	}

	/*
	 * 方法描述：通过资讯ID，获取资讯标题
	 */
	public String gettitles(String id) {
		String titleString = "";
		titleString = newsService.get(id).getTitle();
		return titleString;
	}

	
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Jun 28, 2012 3:18:42 PM
	 * @Method Description :通过搜索专题标题查找记录
	 */
	public void getsearchtitle() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		title = URLDecoder.decode(title, "UTF-8");
		PrintWriter out = response.getWriter();
		Map map=new HashMap();
		map.put("title", title);
		map.put("info_state", "1");
		map=ajaxMap(map);
		int totalNum=this.newsService.getCount(map);
		List list=this.newsService.getList(map);
		String jsonStr=pageList(list,totalNum);
		out.write(jsonStr);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jun 29, 2012 10:50:26 AM
	 * @Method Description :搜索图片标题
	 */
	public void getsearchimage() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		title = URLDecoder.decode(title, "UTF-8");
		PrintWriter out = response.getWriter();
		Map map=new HashMap();
		map.put("title", title);
		map.put("info_state", "1");
		map.put("litpic","litpic");
		map=ajaxMap(map);
		int totalNum=this.newsService.getCount(map);
		List list=this.newsService.getList(map);
		String jsonStr=pageList(list,totalNum);
		out.write(jsonStr);
	}
	
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Jun 29, 2012 9:19:44 AM
	 * @Method Description : 获取对应的id串的数据
	 */
	public void getDataByIdStr() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String ids=request.getParameter("ids");
		PrintWriter out = response.getWriter();
		Map map=new HashMap();
		map.put("newsids", ids);
		List list=this.newsService.getList(map);
		String jsonStr=JsonUtil.list2json(list);
		out.write(jsonStr);
	}
	
	
	
	
	/**
	 * AJAX获取关联新闻标题
	 * 
	 * @throws Exception
	 */
	public void getnewstitle() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String outputString = "";
		String strnewtitle = "";
		String strnewpictitle = "";
		if (newsid != null && newsid != "") {
			if (newsid.contains(",")) {
				String[] newtitle = newsid.split(",");
				for (int i = 0; i < newtitle.length; i++) {
					strnewtitle += gettitles(newtitle[i]) + "#####";
				}
				int strtitlelen = strnewtitle.length();
				if (strtitlelen != 0 && strtitlelen > 5) {
					strnewtitle = strnewtitle.substring(0, strtitlelen - 5);
				}
			} else {
				strnewtitle = gettitles(newsid);
			}
		}
		if (newspicid != null && newspicid != "") {
			if (newspicid.contains(",")) {
				String[] newpictitle = newspicid.split(",");
				for (int j = 0; j < newpictitle.length; j++) {
					strnewpictitle += gettitles(newpictitle[j]) + "#####";
				}
				int strpiclen = strnewpictitle.length();
				if (strpiclen != 0 && strpiclen > 5) {
					strnewpictitle = strnewpictitle.substring(0, strpiclen - 5);
				}
			} else {
				strnewpictitle = gettitles(newspicid);
			}
		}
		outputString = strnewtitle + "&&&&&" + strnewpictitle;
		out.write(outputString);

	}

	/**
	 * 方法描述：修改专题信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 用于所属分类的回选开始
		loadCategory();
		//验证分类是选择
		validateCategoryIfSelect();
		this.subject.setCat_attr(cat_attr);
		// 将处理后的所属分类串注入到subject对象中
		this.subject.setCat_attr(cat_attr);
		this.subject.setNews_attr(title_id);
		this.subject.setImg_news_attr(image_id);
		this.subject.setUser_id(this.session_user_id);
		
		// 数据库原有的分类串	
		supply_infoattr_id = subject.getInfoattr_id();	
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("supply");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.subject.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}else{
			supply_infoattr_id=null;
		}					
		// 字段验证
		super.commonValidateField(subject);
		if (super.ifvalidatepass) {
			return view();
		}
		
		this.subjectService.update(subject,objList,supply_infoattr_id);;
		if (this.subject.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.subject.getSub_id());
		}
		this.addActionMessage("修改专题信息成功");
		return list();
	}

	/**
	 * 方法描述：删除专题信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.subject.getSub_id();
		id = id.replace(" ", "");
		if (id!=null&&!"".equals(id)) {
			id = id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				Subject subject=this.subjectService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],subject.getIn_date());
				this.subjectService.delete(ids[i]);
			}
			this.addActionMessage("删除专题信息成功");	
		}
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (title_s != null && !title_s.equals("")) {
			pageMap.put("title", title_s);
		}
		// 获取搜索的所在分类
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("cat_attr", cat_attr_s);
		}
		if (is_recom_s != null && !is_recom_s.equals("")) {
			pageMap.put("is_recom", is_recom_s);
		}
		if (info_state_s != null && !info_state_s.equals("")) {
			pageMap.put("info_state", info_state_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (last_date_s != null && !last_date_s.equals("")) {
			pageMap.put("last_date", last_date_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.subjectService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		subjectList = this.subjectService.getList(pageMap);
		// 分类代码转换成中文字符
		subjectList = CategoryFuc.replaceList(subjectList, "");
		return goUrl(INDEXLIST);
	}

	/**i
	 * 方法描述：找出专题相关的资讯新闻
	 * 
	 * @return
	 * @throws Exception
	 */
	public String selectnewslist() throws Exception {
		title_s = URLDecoder.decode(title_s, "UTF-8");
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (title_s != null && !title_s.equals("")) {
			pageMap.put("title", title_s);
		}
		pageMap.put("state_code_1", 0);// 审核通过状态
		pageMap.put("state_code_2", 0);// 审核通过状态
		pageMap.put("state_code_3", 0);// 审核通过状态
		pageMap.put("state_code_4", 0);// 审核通过状态
		if (litpic_s != null && !litpic_s.equals("")) {
			pageMap.put("litpic", litpic_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.newsService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		// 找出信息列表，放入list
		newsList = this.newsService.getList(pageMap);
		return INPUT;
	}

	/**
	 * 方法描述：根据主键找出专题信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(subject.getCust_id()!=null){
			if(accessControl(subject.getCust_id())){
				return list();
			}
		}
		//赋值反选
		title_id=subject.getNews_attr();
		image_id=subject.getImg_news_attr();
		bandnewscate();
		//分类ID转名称
		catIdTocatName(this.subject.getCat_attr());
		//根据模块是否启动分类属性
		sysmodule = this.sysmoduleService.get("subject");
		if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){			
			if(ischange==null){//第一次加载页面
				info_infoattr_id = this.subject.getInfoattr_id();
			}
			getIsCatAttr(info_infoattr_id,subject.getCat_attr());		
		}else{
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(subject.getCat_attr());	
		}		
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：是否推荐批量修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String subid = this.subject.getSub_id();
		String isrecom = this.subject.getIs_recom();
		subid = subid.replace(" ", "");
		String subStr[] = subid.split(",");
		List subList = new ArrayList();
		if (subStr.length > 0) {
			for (int i = 0; i < subStr.length; i++) {
				Map subMap = new HashMap();
				subMap.put("is_recom", isrecom);
				subMap.put("sub_id", subStr[i]);
				subList.add(subMap);
			}
		}
		this.subjectService.updateIsrecom(subList);
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
	 * @return the SubjectList
	 */
	public List getSubjectList() {
		return subjectList;
	}

	/**
	 * @param subjectList
	 *            the SubjectList to set
	 */
	public void setSubjectList(List subjectList) {
		this.subjectList = subjectList;
	}

	/**
	 * @return the title_s
	 */
	public String getTitle_s() {
		return title_s;
	}

	/**
	 * @param title_s
	 *            the title_s to set
	 */
	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	/**
	 * @return the is_comment_s
	 */
	public String getIs_comment_s() {
		return is_comment_s;
	}

	/**
	 * @param is_comment_s
	 *            the is_comment_s to set
	 */
	public void setIs_comment_s(String is_comment_s) {
		this.is_comment_s = is_comment_s;
	}

	/**
	 * @return the is_recom_s
	 */
	public String getIs_recom_s() {
		return is_recom_s;
	}

	/**
	 * @param is_recom_s
	 *            the is_recom_s to set
	 */
	public void setIs_recom_s(String is_recom_s) {
		this.is_recom_s = is_recom_s;
	}

	/**
	 * @return the info_state_s
	 */
	public String getInfo_state_s() {
		return info_state_s;
	}

	/**
	 * @param info_state_s
	 *            the info_state_s to set
	 */
	public void setInfo_state_s(String info_state_s) {
		this.info_state_s = info_state_s;
	}

	/**
	 * @return the in_date_s
	 */
	public String getIn_date_s() {
		return in_date_s;
	}

	/**
	 * @param in_date_s
	 *            the in_date_s to set
	 */
	public void setIn_date_s(String in_date_s) {
		this.in_date_s = in_date_s;
	}

	/**
	 * @return the last_date_s
	 */
	public String getLast_date_s() {
		return last_date_s;
	}

	/**
	 * @param last_date_s
	 *            the last_date_s to set
	 */
	public void setLast_date_s(String last_date_s) {
		this.last_date_s = last_date_s;
	}

	/**
	 * @return the fare_s
	 */
	public String getFare_s() {
		return fare_s;
	}

	/**
	 * @param fare_s
	 *            the fare_s to set
	 */
	public void setFare_s(String fare_s) {
		this.fare_s = fare_s;
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
	 * @return the hidden_up_cate_id
	 */
	public String getHidden_up_cate_id() {
		return hidden_up_cate_id;
	}

	/**
	 * @param hidden_up_cate_id
	 *            the hidden_up_cate_id to set
	 */
	public void setHidden_up_cate_id(String hidden_up_cate_id) {
		this.hidden_up_cate_id = hidden_up_cate_id;
	}

	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}

	public List getNewsList() {
		return newsList;
	}

	public void setNewsList(List newsList) {
		this.newsList = newsList;
	}

	public String getLitpic_s() {
		return litpic_s;
	}

	public void setLitpic_s(String litpic_s) {
		this.litpic_s = litpic_s;
	}

	public String getSelecttype() {
		return selecttype;
	}

	public void setSelecttype(String selecttype) {
		this.selecttype = selecttype;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getNewsid() {
		return newsid;
	}

	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}

	public String getNewspicid() {
		return newspicid;
	}

	public void setNewspicid(String newspicid) {
		this.newspicid = newspicid;
	}

	public List getCateList() {
		return cateList;
	}

	public void setCateList(List cateList) {
		this.cateList = cateList;
	}

	/**
	 * @return the cat_attr_s
	 */
	public String getCat_attr_s() {
		return cat_attr_s;
	}

	/**
	 * @param cat_attr_s
	 *            the cat_attr_s to set
	 */
	public void setCat_attr_s(String cat_attr_s) {
		this.cat_attr_s = cat_attr_s;
	}

	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param Subject
	 *            the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (subject == null) {
			subject = new Subject();
		}
		String id = subject.getSub_id();
		if (!ValidateUtil.isDigital(id)) {
			subject = this.subjectService.get(id);
		}
	}

}
