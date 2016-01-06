/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: ResumeAction.java 
 */
package com.rbt.action;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.function.AreaFuc;
import com.rbt.function.AuditModelFuc;
import com.rbt.function.CategoryAttrFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Category;
import com.rbt.model.Job;
import com.rbt.model.Member;
import com.rbt.model.Resume;
import com.rbt.model.Sysmodule;
import com.rbt.service.IAreaService;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICategoryattrService;
import com.rbt.service.IChannelService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IResumeService;

/**
 * @function 功能 简历信息action类
 * @author 创建人 胡惜坤
 * @date 创建日期 Wed Jul 13 16:14:17 CST 2011
 */
@Controller
public class ResumeAction extends baseModelAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 简历信息对象
	 */
	private Resume resume;
	/*
	 * 简历信息对象
	 */
	private Member member;
	
	/*
	 * 业务层接口
	 */
	@Autowired
	public IResumeService resumeService;
	@Autowired
	public IChannelService channelService;
	/*
	 * 简历信息信息集合
	 */
	public List resumeList;
	
	public List commparaList;
	/*
	 * 搜索字段 cust_id_s:客户标识
	 */
	public String cust_id_s;
	/*
	 * 搜索字段 resume_name_s:简历名称
	 */
	public String resume_name_s;
	/*
	 * 搜索字段 cat_attr_s:所属分类
	 */
	public String cat_attr_s;
	/*
	 * 搜索字段 real_name_s:真实姓名
	 */
	public String real_name_s;
	/*
	 * 搜索字段 sex_s:性别
	 */
	public String sex_s;
	/*
	 * 搜索字段 education_s:学历
	 */
	public String education_s;
	/*
	 * 搜索字段 is_recom_s:是否推荐
	 */
	public String is_recom_s;
	/*
	 * 搜索字段 job_state_s:信息状态
	 */
	public String job_state_s;
	/*
	 * 搜索字段 in_date_s:发布时间
	 */
	public String in_date_s;
	public String end_date_s;
	/*
	 * 搜索字段 user_name_s:发布人
	 */
	public String user_name_s;
	/*
	 * 显示审核不通过的理由输入框
	 */
	private String noReasonKey;
	/*
	 * 工资要求的开始字段
	 */
	public String salaryStartString;
	/*
	 * 工资要求的结束字段
	 */
	public String salaryEndString;
	/*
	 * 搜索发布时间的最大时间
	 */
	public String enddateString_s;
	/*
	 * 婚姻状态
	 */
	public String marry_s;
	/*
	 * 居住所在地
	 */
	public String area_attr_s;
	/*
	 * 学历
	 */
	public String college_s;
	/*
	 * 所学专业
	 */
	public String spec_s;
	/*
	 * 工作性质
	 */
	public String job_type_s;
	/*
	 * 工作经验
	 */
	public String work_exper_s;
	/*
	 * 专业技能
	 */
	public String technical_s;
	/*
	 * 语言水平
	 */
	public String language_s;
	public String today;
	public String modType="resume";
	public String info_state_s;
	public Sysmodule sysmodule;
	private String resume_infoattr_id;
	//定义用户类型
	public String memtype;
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
	public ICommparaService commparaService;	
	/*
	 * 如果flageResume的值为0的时候，表示审核简历的请求，如果flageResume的值为1，表示是简历收件箱请求的动作，如果flageResume为2，表示是人才库请求的动作；
	 * 如果是简历收件箱的请求的话，将审核按钮给隐藏不显示，而且，返回列表页也是返回简历收件箱列表页面
	 */
	public String flageResume = "0";
	public String resumeid;
	//地区搜索
	public String search_area_attr;
    //分类搜索
	public String search_cat_attr;

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
					resume = new Resume();
					resume.setResume_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		} else {
			if (this.resume != null && this.resume.getResume_id() != null && !this.resume.getResume_id().equals("")){
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
		int count = this.resumeService.getCount(tmap);
		if(controlMsgNum(count,modType)){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 方法描述：返回新增简历信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cate() throws Exception {
		bindsalary();
		//获取所属模块名是否支持分类属性
		sysmodule = this.sysmoduleService.get("resume");
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
	 * 方法描述：绑定工资下拉框
	 */
	public void bindsalary() {
		HashMap map = new HashMap();
		map.put("para_code", "salar_type");
		commparaList = commparaService.getList(map);
	}
	/**
	 * 方法描述：是否推荐批量修改
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String subid = this.resume.getResume_id();
		String isrecom = this.resume.getIs_recom();
		subid = subid.replace(" ", "");
		String subStr[] = subid.split(",");
		List subList = new ArrayList();
		if(subStr.length>0){
			for(int i=0;i<subStr.length;i++){
				Map subMap = new HashMap();
				subMap.put("is_recom", isrecom);
				subMap.put("resume_id", subStr[i]);
				subList.add(subMap);
			}
		}
		this.resumeService.updateRecommendState(subList);
		String tip = "";
		if(isrecom.equals("0")){
			tip = "取消推荐成功";
		}else if(isrecom.equals("1")){
			tip = "推荐成功";
		}
		this.addActionMessage(tip);
		return list();
	}
	/**
	 * 方法描述：新增简历信息
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
		// 将处理后的所属分类串注入到buy对象中
		this.resume.setCat_attr(cat_attr);
		//验证地区是选择
		validateAreaIfSelect();
		if (ValidateUtil.isRequired(resume.getSalary()) || resume.getSalary().equals("请选择") || resume.getSalary().equals("0")) {
			this.addFieldError("resume.salary", "请选择期望月薪");
		}
		this.resume.setArea_attr(area_attr);
		resume.setUser_id(this.session_user_id);
		resume.setCust_id(this.session_cust_id);
		resume.setNo_reason("");
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("resume");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.resume.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}

		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return cate();
			}
		}
		
		//字段验证
		super.commonValidateField(resume);
		if(super.ifvalidatepass){
			return cate();
		}
		String resume_id = this.resumeService.insertGetPk(resume,objList);// 获取刚刚插入成功的记录的ID	
		// 对插入成功的信息生成HTML静态页面
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, resume_id);
		this.addActionMessage("新增简历信息成功");
		this.resume = null;
		is_crotorl=true;
		return cate();
	}

	/**
	 * 方法描述：审核招聘信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditState() throws Exception {
        String resumeidString=resume.getResume_id();
        if(ValidateUtil.isDigital(resumeidString))
        {
        	return list();
        }
      //启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("resume",resume.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		if (resume.getInfo_state() == null && resume.getInfo_state().equals("")) {
			this.addFieldError("resume.info_state", "请选择审核状态");
			return goUrl(AUDIT);
		}
		if (resume.getInfo_state().equals("2") && ValidateUtil.isRequired(resume.getNo_reason())) {
			this.addFieldError("resume.no_reason", "请输入拒绝理由");
			noReasonKey = "2";
			return audit();
		}
		Map pageMap = new HashMap();
		if (!resume.getInfo_state().equals("2")) {
			pageMap.put("no_reason", "");
		}
		else {
			pageMap.put("no_reason", resume.getNo_reason());
		}
		pageMap.put("resume_id", resume.getResume_id());
		pageMap.put("info_state", resume.getInfo_state());
		this.resumeService.updateState(pageMap);
		if (this.resume.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.resume.getResume_id());
		}
		this.addActionMessage("审核简历信息成功");
		return auditList();
	}

	/**
	 * 方法描述：修改简历信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String resumeidString=resume.getResume_id();
	    if(ValidateUtil.isDigital(resumeidString))
	    {
	       return list();
	    }
		// 用于所属分类的回选开始
		loadCategory();
		// 用于所属地区的回选开始
		loadArea();
		//验证分类是选择
		validateCategoryIfSelect();
		// 将处理后的所属分类串注入到buy对象中
		this.resume.setCat_attr(cat_attr);
		//验证地区是选择
		validateAreaIfSelect();
		this.resume.setArea_attr(area_attr);
		if (ValidateUtil.isRequired(resume.getSalary())
				|| resume.getSalary().equals("请选择")
				|| resume.getSalary().equals("0")) {
			this.addFieldError("resume.salary", "请选择期望月薪");
		}
		// 当前登录的用户是管理员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)
				&& resume.getInfo_state().equals("2")
				&& ValidateUtil.isRequired(resume.getNo_reason())) {
			this.addFieldError("resume.no_reason", "请输入拒绝理由");
			noReasonKey = "2";
		}
		if (!resume.getInfo_state().equals("2")) {
			resume.setNo_reason("");
		}
		resume.setUser_id(this.session_user_id);
		area_attr = area_attr.replace(" ", "");// 去掉地区串中的空格
		resume.setArea_attr(area_attr);
		resume.setCat_attr(cat_attr);
		
		// 数据库原有的分类串	
		resume_infoattr_id = resume.getInfoattr_id();	
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("resume");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.resume.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}else{
			resume_infoattr_id=null;
		}			
		this.resume.setCat_attr(cat_attr);
		
		//字段验证
		super.commonValidateField(resume);
		if(super.ifvalidatepass){
			return view();
		}
		this.resumeService.update(resume,objList,resume_infoattr_id);
		if (this.resume.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.resume.getResume_id());
		}
		this.addActionMessage("修改简历信息成功");
		return list();
	}

	/**
	 * 方法描述：删除简历信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		del();
		return list();
	}
	
	public void del(){
		String id = this.resume.getResume_id();
		id = id.replace(" ", "");
		if (id!=null&&!"".equals(id)) {
			id = id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				Resume resume=this.resumeService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],resume.getIn_date());
				this.resumeService.delete(ids[i]);
			}
			this.addActionMessage("删除简历信息成功");	
		}
	}
	/**
	 * 方法描述：审核简历批量删除
	 * @author 陈晓艺
	 * @throws Exception
	 * @date : Jul 9, 2012 10:32:55 AM
	 */
	public String checkDel() throws Exception {
		del();
		return   auditList();
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

		if (resume_name_s != null && !resume_name_s.equals(""))
			pageMap.put("resume_name", resume_name_s);
		if (real_name_s != null && !real_name_s.equals(""))
			pageMap.put("real_name", real_name_s);
		if (sex_s != null && !sex_s.equals("") && !sex_s.equals("请选择"))
			pageMap.put("sex", sex_s);
		if (education_s != null && !education_s.equals("") && !education_s.equals("请选择"))
			pageMap.put("education", education_s);
		if (is_recom_s != null && !is_recom_s.equals("") && !is_recom_s.equals("请选择"))
			pageMap.put("is_recom", is_recom_s);
		if (in_date_s != null && !in_date_s.equals(""))
			pageMap.put("in_date", in_date_s);
		if (user_name_s != null && !user_name_s.equals(""))
			pageMap.put("user_name", user_name_s);
		if (enddateString_s != null && !enddateString_s.equals(""))
			pageMap.put("enddate", enddateString_s);
		// //////////////////////会员简历搜索条件
		if (marry_s != null && !marry_s.equals("") && !marry_s.equals("请选择"))
			pageMap.put("marry", marry_s);
		if (area_attr_s != null && !area_attr_s.equals(""))
			pageMap.put("area_attr", area_attr_s);
		if (college_s != null && !college_s.equals("") && !college_s.equals("请选择"))
			pageMap.put("college", college_s);
		if (spec_s != null && !spec_s.equals(""))
			pageMap.put("spec", spec_s);
		if (job_type_s != null && !job_type_s.equals("") && !job_type_s.equals("请选择"))
			pageMap.put("job_type", job_type_s);
		if (work_exper_s != null && !work_exper_s.equals(""))
			pageMap.put("work_exper", work_exper_s);
		if (technical_s != null && !technical_s.equals(""))
			pageMap.put("technical", technical_s);
		if (job_state_s != null && !job_state_s.equals("") && !job_state_s.equals("请选择")) {
			pageMap.put("info_state", job_state_s);
		} else {
			if(today!=null&&!today.equals("")){
				pageMap.put("today",this.today);
			}else{
				// 操作者为会员则默认加入搜索条件
				if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
					pageMap.put("resume_state_in", "0,1,2,3");// 审核通过状态
				} else {
					pageMap.put("resume_state_in", "1,3");// 审核通过状态
				}
			}
		}
		if(area_attr_s!=null&&!area_attr_s.equals(""))
			pageMap.put("area_attr", area_attr_s);
		if(cat_attr_s!=null&&!cat_attr_s.equals(""))
			pageMap.put("cat_attr", cat_attr_s);

		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.resumeService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		resumeList = this.resumeService.getList(pageMap);
		resumeList=com.rbt.function.CategoryFuc.replaceList(resumeList,"");
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditList() throws Exception {
		Map pageMap = new HashMap();

		if (cust_id_s != null && !cust_id_s.equals(""))
			pageMap.put("cust_id", cust_id_s);
		if (resume_name_s != null && !resume_name_s.equals(""))
			pageMap.put("resume_name", resume_name_s);
		if (cat_attr_s != null && !cat_attr_s.equals(""))
			pageMap.put("cat_attr", cat_attr_s);
		if (real_name_s != null && !real_name_s.equals(""))
			pageMap.put("real_name", real_name_s);
		if (sex_s != null && !sex_s.equals("") && !sex_s.equals("请选择"))
			pageMap.put("sex", sex_s);
		if (education_s != null && !education_s.equals("") && !education_s.equals("请选择"))
			pageMap.put("education", education_s);
		if (is_recom_s != null && !is_recom_s.equals("") && !is_recom_s.equals("请选择"))
			pageMap.put("is_recom", is_recom_s);
		if (in_date_s != null && !in_date_s.equals(""))
			pageMap.put("in_date", in_date_s);
		if (info_state_s != null && !info_state_s.equals("") )
			pageMap.put("info_state", info_state_s);
		if (user_name_s != null && !user_name_s.equals(""))
			pageMap.put("user_name", user_name_s);
		if (enddateString_s != null && !enddateString_s.equals(""))
			pageMap.put("end_date", enddateString_s);
		if (job_state_s != null && !job_state_s.equals("") && !job_state_s.equals("请选择")) {
			pageMap.put("info_state", job_state_s);
		} else {
			pageMap.put("resume_state_in", "0,2");// 未审核状态
		}
		if(search_area_attr!=null&&!search_area_attr.equals(""))
			pageMap.put("area_attr", search_area_attr);
		if(search_cat_attr!=null&&!search_cat_attr.equals(""))
			pageMap.put("cat_attr", search_cat_attr);
		// 根据页面提交的条件找出信息总数
		int count = this.resumeService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		resumeList = this.resumeService.getList(pageMap);
		resumeList=com.rbt.function.CategoryFuc.replaceList(resumeList,"");
		return goUrl(AUDITLIST);
	}

	/**
	 * 方法描述：会员后台搜索简历
	 * 
	 * @return
	 * @throws Exception
	 */
	public String resumeSearch() throws Exception{
		Map pageMap = new HashMap();
		//信息状态
		pageMap.put("info_state", "1");//信息状态info_state 	1：正常
		//性别
		if (sex_s != null && !sex_s.equals("") && !sex_s.equals("请选择")){
			pageMap.put("sex", sex_s);
		}
		// 获取搜索的所属分类
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("cat_attr", cat_attr_s);
		}
		//婚姻状况	
		if (marry_s != null && !marry_s.equals("") && !marry_s.equals("请选择")){
			pageMap.put("marry", marry_s);
		}
		//现居住地
		if (area_attr_s != null && !area_attr_s.equals("")){
			pageMap.put("area_attr", area_attr_s);
		}
		//学历
		if (education_s != null && !education_s.equals("") && !education_s.equals("请选择")){
			pageMap.put("education", education_s);
		}
		//毕业院校	
		if (college_s != null && !college_s.equals("") && !college_s.equals("请选择")){
			pageMap.put("college", college_s);
		}
		//所学专业
		if (spec_s != null && !spec_s.equals("")){
			pageMap.put("spec", spec_s);
		}
		//工作性质
		if (job_type_s != null && !job_type_s.equals("") && !job_type_s.equals("请选择")){
			pageMap.put("job_type", job_type_s);
		}
		//工作经验
		if (work_exper_s != null && !work_exper_s.equals("")){
			pageMap.put("work_exper", work_exper_s);
		}
		//专业技能
		if (technical_s != null && !technical_s.equals("")){
			pageMap.put("technical", technical_s);
		}
		//语言水平
		if(language_s != null && !language_s.equals("")){
			pageMap.put("language", language_s);
		}
		//发布时间
		if(in_date_s != null && !in_date_s.equals("")){
			pageMap.put("in_date", in_date_s);
		}
		if(end_date_s != null && !end_date_s.equals("")){
			pageMap.put("end_date", end_date_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.resumeService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		resumeList = this.resumeService.getList(pageMap);
		resumeList=com.rbt.function.CategoryFuc.replaceList(resumeList,"");
		return goUrl("resumelist");
	}
	
	/**
	 * 方法描述：根据主键找出简历信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(resume.getCust_id()!=null){
			if(accessControl(resume.getCust_id())){
				return list();
			}
		}
		bindsalary();//绑定工资待遇
		// 将从数据库中查询的所属分类存入分类隐藏域中
		backCategory(resume.getCat_attr());	
		// 找出地区字段的存入隐藏值中
		backArea(resume.getArea_attr());		
		noReasonKey = resume.getInfo_state();
		
		
		//分类ID转名称
		catIdTocatName(this.resume.getCat_attr());
		//根据模块是否启动分类属性
		sysmodule = this.sysmoduleService.get("resume");
		if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){			
			if(ischange==null){//第一次加载页面
				info_infoattr_id = this.resume.getInfoattr_id();
			}
			getIsCatAttr(info_infoattr_id,resume.getCat_attr());	
		}else{
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(resume.getCat_attr());	
		}		
		
		
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：根据主键找出简历信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		
		//进入审核页面时候的加载方法
		auditView("resume",resume.getResume_id());
		
		noReasonKey = resume.getInfo_state();
		//获取地区中文字符
		String area_name = "";
		if(resume.getArea_attr() != null){
			area_name = AreaFuc.getAreaNameByMap(resume.getArea_attr());
		}
		resume.setArea_attr(area_name);
		//获取分类中文字符
		String cat_name = "";
		if(resume.getCat_attr() != null){
			cat_name = CategoryFuc.getCateNameByMap(resume.getCat_attr());
		}
		resume.setCat_attr(cat_name);
		// 找出属性列表
		String infoattr_id = resume.getInfoattr_id();
		sysmodule = this.sysmoduleService.get("product");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			backAttr(infoattr_id,resume.getCat_attr());
		}	
		member = memberService.get(this.session_cust_id);
		if(member!=null){
			memtype = member.getMem_type();
		}
		return goUrl(AUDIT);
	}

	public String searchresume() throws Exception {
		return INPUT;
	}

	/**
	 * @return the ResumeList
	 */
	public List getResumeList() {
		return resumeList;
	}

	/**
	 * @param resumeList
	 *            the ResumeList to set
	 */
	public void setResumeList(List resumeList) {
		this.resumeList = resumeList;
	}

	public String getCust_id_s() {
		return cust_id_s;
	}

	public void setCust_id_s(String cust_id_s) {
		this.cust_id_s = cust_id_s;
	}

	public String getResume_name_s() {
		return resume_name_s;
	}

	public void setResume_name_s(String resume_name_s) {
		this.resume_name_s = resume_name_s;
	}

	public String getCat_attr_s() {
		return cat_attr_s;
	}

	public void setCat_attr_s(String cat_attr_s) {
		this.cat_attr_s = cat_attr_s;
	}

	public String getReal_name_s() {
		return real_name_s;
	}

	public void setReal_name_s(String real_name_s) {
		this.real_name_s = real_name_s;
	}

	public String getSex_s() {
		return sex_s;
	}

	public void setSex_s(String sex_s) {
		this.sex_s = sex_s;
	}

	public String getEducation_s() {
		return education_s;
	}

	public void setEducation_s(String education_s) {
		this.education_s = education_s;
	}

	public String getIs_recom_s() {
		return is_recom_s;
	}

	public void setIs_recom_s(String is_recom_s) {
		this.is_recom_s = is_recom_s;
	}

	public String getJob_state_s() {
		return job_state_s;
	}

	public void setJob_state_s(String job_state_s) {
		this.job_state_s = job_state_s;
	}

	public String getIn_date_s() {
		return in_date_s;
	}

	public void setIn_date_s(String in_date_s) {
		this.in_date_s = in_date_s;
	}

	public String getUser_name_s() {
		return user_name_s;
	}

	public void setUser_name_s(String user_name_s) {
		this.user_name_s = user_name_s;
	}

	public String getNoReasonKey() {
		return noReasonKey;
	}

	public void setNoReasonKey(String noReasonKey) {
		this.noReasonKey = noReasonKey;
	}

	public String getSalaryStartString() {
		return salaryStartString;
	}

	public void setSalaryStartString(String salaryStartString) {
		this.salaryStartString = salaryStartString;
	}

	public String getSalaryEndString() {
		return salaryEndString;
	}

	public void setSalaryEndString(String salaryEndString) {
		this.salaryEndString = salaryEndString;
	}

	public void setAreaService(IAreaService areaService) {
		this.areaService = areaService;
	}

	

	public String getEnddateString_s() {
		return enddateString_s;
	}
	public void setEnddateString_s(String enddateString_s) {
		this.enddateString_s = enddateString_s;
	}
	public String getEnd_date_s() {
		return end_date_s;
	}

	public void setEnd_date_s(String end_date_s) {
		this.end_date_s = end_date_s;
	}

	public String getMarry_s() {
		return marry_s;
	}

	public void setMarry_s(String marry_s) {
		this.marry_s = marry_s;
	}

	public String getArea_attr_s() {
		return area_attr_s;
	}

	public void setArea_attr_s(String area_attr_s) {
		this.area_attr_s = area_attr_s;
	}

	public String getCollege_s() {
		return college_s;
	}

	public void setCollege_s(String college_s) {
		this.college_s = college_s;
	}

	public String getSpec_s() {
		return spec_s;
	}

	public void setSpec_s(String spec_s) {
		this.spec_s = spec_s;
	}

	public String getJob_type_s() {
		return job_type_s;
	}

	public void setJob_type_s(String job_type_s) {
		this.job_type_s = job_type_s;
	}

	public String getWork_exper_s() {
		return work_exper_s;
	}

	public void setWork_exper_s(String work_exper_s) {
		this.work_exper_s = work_exper_s;
	}

	public String getTechnical_s() {
		return technical_s;
	}

	public void setTechnical_s(String technical_s) {
		this.technical_s = technical_s;
	}

	public String getLanguage_s() {
		return language_s;
	}

	public void setLanguage_s(String language_s) {
		this.language_s = language_s;
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

	public String getFlageResume() {
		return flageResume;
	}

	public void setFlageResume(String flageResume) {
		this.flageResume = flageResume;
	}

	public String getResumeid() {
		return resumeid;
	}

	public void setResumeid(String resumeid) {
		this.resumeid = resumeid;
	}

	public List getCommparaList() {
		return commparaList;
	}

	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
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
	/**
	 * @return the resume
	 */
	public Resume getResume() {
		return resume;
	}

	/**
	 * @param Resume
	 *            the resume to set
	 */
	public void setResume(Resume resume) {
		this.resume = resume;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
	if(resume == null){
		resume = new Resume();
	}
	String id = resume.getResume_id();
	if(!ValidateUtil.isDigital(id)){
		resume = this.resumeService.get(id);
	}
}

}
