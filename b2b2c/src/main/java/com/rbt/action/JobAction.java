/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: JobAction.java 
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
import com.rbt.function.CategoryFuc;
import com.rbt.model.Category;
import com.rbt.model.Job;
import com.rbt.model.Member;
import com.rbt.model.Sysmodule;
import com.rbt.service.IAreaService;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICategoryattrService;
import com.rbt.service.IChannelService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 招聘信息action类
 * @author 创建人 胡惜坤
 * @date 创建日期 Tue Jul 12 15:29:27 CST 2011
 */
@Controller
public class JobAction extends baseModelAction implements Preparable{

	private static final long serialVersionUID = 1L; 
	/*
	 * 业务层接口
	 */
	@Autowired
	public IJobService jobService;
	@Autowired
	public IChannelService channelService;
	@Autowired
	public ICategoryattrService categoryattrService;
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public IAreaService areaService;
	@Autowired
	public ICommparaService commparaService;
	
	/*
	 * 招聘信息对象
	 */
	public Job job;
	/*
	 * 用户对象
	 */
	public Member member;
	/*
	 * 定义供应类别表对象
	 */
	public Category category;
	/*
	 * 招聘信息信息集合
	 */
	public List jobList;
	/*
	 * 参数对象
	 */
	public List commparaList;
	/*
	 * 搜索字段 cust_id_s:客户标识
	 */
	public String cust_id_s;
	/*
	 * 搜索字段 title_s:招聘标题
	 */
	public String title_s;
	/*
	 * 搜索字段 cat_attr_s:所属分类
	 */
	public String cat_attr_s;
	/*
	 * 搜索字段 org_name_s:招聘部门
	 */
	public String org_name_s;
	/*
	 * 搜索字段 job_num_s:招聘人数
	 */
	public String job_num_s;
	/*
	 * 搜索字段 end_date_s:过期时间
	 */
	public String end_date_s;
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
	/*
	 * 搜索字段 user_name_s:发布人
	 */
	public String user_name_s;
	/*
	 * 年龄要求的开始字段
	 */
	public String ageStartString;
	/*
	 * 年龄要求的结束字段
	 */
	public String ageEndString;
	/*
	 * 工资要求的开始字段
	 */
	public String salaryStartString;
	/*
	 * 工资要求的结束字段
	 */
	public String salaryEndString;
	/*
	 * 显示审核不通过的理由输入框
	 */
	public String noReasonKey;
	/*
	 * 居住所在地
	 */
	public String area_attr_s;
	//定义用户类型
	public String memtype;
	/*
	 * 搜索发布时间的最大时间
	 */
	public String enddateString_s;
	public String today;
	private String modType="job";
	public String info_state_s;
	
	
	/*
	 * 用于接收简历收件箱传过来查看职位信息的字段
	 * 如果flageJob的值为0的时候，表示不是简历收件箱的请求，如果flageJob的值为1，表示是简历收件箱请求的动作；
	 * 如果是简历收件箱的请求的话，将审核按钮给隐藏不显示，而且，返回列表页也是返回简历收件箱列表页面
	 */
	public String flageJob = "0";
	public String jobid;

	//地区搜索
	public String search_area_attr;
    //分类搜索
	public String search_cat_attr;
	public Sysmodule sysmodule;
	private String job_infoattr_id;


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
					job = new Job();
					job.setJob_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		} else {
			if (this.job != null && this.job.getJob_id() != null && !this.job.getJob_id().equals("")){
				return view();
			} else {
				return cate();
			}
		}
	}
	
	/**
	 * 方法描述：返回新增招聘信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cate() throws Exception {
		bindsalary();
		//获取所属模块名是否支持分类属性
		sysmodule = this.sysmoduleService.get("job");
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
	 * @author : 林俊钦
	 * @date : Jul 13, 2012 1:42:18 PM
	 * @Method Description : 会员发布条数的判断
	 */
	public boolean controlInfoNum(){
		//查出已发布的信息个数
		Map tmap = new HashMap();
		tmap.put("cust_id", this.session_cust_id);
		int count = this.jobService.getCount(tmap);
		if(controlMsgNum(count,modType)){
			return true;
		}else{
			return false;
		}
	}
	

	/**
	 * 方法描述：是否推荐批量修改
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String subid = this.job.getJob_id();
		String isrecom = this.job.getIs_recom();
		subid = subid.replace(" ", "");
		String subStr[] = subid.split(",");
		List subList = new ArrayList();
		if(subStr.length>0){	
			for(int i=0;i<subStr.length;i++){
				Map subMap = new HashMap();
				subMap.put("is_recom", isrecom);
				subMap.put("job_id", subStr[i]);
				subList.add(subMap);
			}
		}
		this.jobService.updateRecommendState(subList);
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
	 * 方法描述：新增招聘信息
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
		this.job.setCat_attr(cat_attr);
		this.job.setArea_attr(area_attr);
		if (ValidateUtil.isRequired(job.getSalary()) || job.getSalary().equals("请选择") || job.getSalary().equals("0")) {
			this.addFieldError("job.salary", "请选择待遇水平");
		}
		if (!ValidateUtil.isRequired(ageStartString)&&ValidateUtil.isDigital(ageStartString)) {
			this.addFieldError("ageStartString", "年龄只能为数字'0~9'");
		}
		if (!ValidateUtil.isRequired(ageEndString)&&ValidateUtil.isDigital(ageEndString)) {
			this.addFieldError("ageEndString", "年龄只能为数字'0~9'");
		}
		job.setUser_id(this.session_user_id);
		job.setCust_id(this.session_cust_id);
		job.setNo_reason("");
		if(ageStartString!=null&&!"".equals(ageStartString)&&ageEndString!=null&&!"".equals(ageEndString))
		{
			job.setBirth(ageStartString + "-" + ageEndString);
		}
		else if(!ageStartString.equals("")&&ageEndString.equals(""))
		{
			job.setBirth(ageStartString);
		}
		else if(ageStartString.equals("")&&!ageEndString.equals(""))
		{
			job.setBirth(ageEndString);
		}
		else
		{
			job.setBirth("");
		}
		
		job.setArea_attr(area_attr);
		job.setCat_attr(cat_attr);
		// 当前登录的用户是管理员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			job.setIs_recom("0");
			job.setClicknum("0");
			job.setFare("0");
		}
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("job");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.job.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return cate();
			}
		}
		//字段验证
		super.commonValidateField(job);
		if(super.ifvalidatepass){
			return cate();
		}
		String job_id=this.jobService.insertGetPk(job,objList);// 获取刚刚插入成功的记录的ID		
		// 对插入成功的信息生成HTML静态页面
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, job_id);		
		this.addActionMessage("新增招聘信息成功");
		this.job = null;
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
		
		//启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("job",job.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		if (job.getInfo_state() == null && job.getInfo_state().equals("")) {
			this.addFieldError("job.info_state", "请选择审核状态");
			return goUrl(AUDIT);
		}
		if (job.getInfo_state().equals("2") && ValidateUtil.isRequired(job.getNo_reason())) {
			this.addFieldError("job.no_reason", "请输入拒绝理由");
			noReasonKey = "2";
			return audit();
		}
		Map pageMap = new HashMap();
		if (!job.getInfo_state().equals("2")) {
			pageMap.put("no_reason", "");
		}
		else {
			pageMap.put("no_reason", job.getNo_reason());
		}
		pageMap.put("job_id", job.getJob_id());
		pageMap.put("info_state", job.getInfo_state());
		this.jobService.updateState(pageMap);
		if (this.job.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.job.getJob_id());
		}
		this.addActionMessage("审核招聘信息成功");
		return auditList();
	}

	/**
	 * 方法描述：修改招聘信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 用于所属分类的回选开始
		loadCategory();
		// 用于所属地区的回选开始
		loadArea();
		//验证所属分类是否有选择
		validateCategoryIfSelect();
		// 将处理后的所属分类串注入到buy对象中
		this.job.setCat_attr(cat_attr);
		//验证所属分类是否有选择
		validateAreaIfSelect();
		this.job.setArea_attr(area_attr);
		if (ValidateUtil.isRequired(job.getSalary()) || job.getSalary().equals("请选择") || job.getSalary().equals("0")) {
			this.addFieldError("job.salary", "请选择待遇水平");
		}
		if (!ValidateUtil.isRequired(ageStartString)&&ValidateUtil.isDigital(ageStartString)) {
			this.addFieldError("ageStartString", "年龄只能为数字'0~9'");
		}
		if (!ValidateUtil.isRequired(ageEndString)&&ValidateUtil.isDigital(ageEndString)) {
			this.addFieldError("ageEndString", "年龄只能为数字'0~9'");
		}
		// 当前登录的用户是管理员的时候，才执行审核动作
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)&&job.getInfo_state().equals("2")&&ValidateUtil.isRequired(job.getNo_reason())) {
			this.addFieldError("job.no_reason", "请输入拒绝理由");
			noReasonKey = "2";
		}
		if (!job.getInfo_state().equals("2")) {
			job.setNo_reason("");
		}
		job.setUser_id(this.session_user_id);
		job.setBirth(ageStartString + "-" + ageEndString);

		// 数据库原有的分类串	
		job_infoattr_id = job.getInfoattr_id();	
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("job");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.job.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}else{
			job_infoattr_id=null;
		}			
		this.job.setCat_attr(cat_attr);
		
		//字段验证
		super.commonValidateField(job);
		if(super.ifvalidatepass){
			return view();
		}
		
		this.jobService.update(job,objList,job_infoattr_id);
		if (this.job.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.job.getJob_id());
		}
		this.addActionMessage("修改招聘信息成功");
		return list();
	}

	/**
	 * 方法描述：删除招聘信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		del();
		return list();
	}
	
	public void del(){
		String id = this.job.getJob_id();
		id = id.replace(" ", "");
		if (id!=null&&!"".equals(id)) {
			id = id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				Job job=this.jobService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],job.getIn_date());
				this.jobService.delete(ids[i]);
			}
			this.addActionMessage("删除招聘信息成功");	
		}
	}
	/**
	 * 方法描述：审核招聘 批量删除
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 * @date : Jul 9, 2012 9:25:16 AM
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
		if (cust_id_s != null && !cust_id_s.equals("")){
			pageMap.put("cust_id", cust_id_s);
		}			
		if (title_s != null && !title_s.equals("")){
			pageMap.put("title", title_s);
		}
		if (cat_attr_s != null && !cat_attr_s.equals("")){
			pageMap.put("cat_attr", cat_attr_s);
		}		
		if (area_attr_s != null && !area_attr_s.equals("")){
			pageMap.put("area_attr", area_attr_s);
		}		
		if (org_name_s != null && !org_name_s.equals("")){
			pageMap.put("org_name", org_name_s);
		}
		if (job_num_s != null && !job_num_s.equals("")){
			pageMap.put("job_num", job_num_s);
		}
		if (end_date_s != null && !end_date_s.equals("")){
			pageMap.put("end_date", end_date_s);
		}
		if (is_recom_s != null && !is_recom_s.equals("")){
			pageMap.put("is_recom", is_recom_s);
		}
		if (in_date_s != null && !in_date_s.equals("")){
			pageMap.put("in_date", in_date_s);
		}
		if (user_name_s != null && !user_name_s.equals("")){
			pageMap.put("user_name", user_name_s);
		}
		if (enddateString_s != null && !enddateString_s.equals("")){
			pageMap.put("enddate", enddateString_s);
		}
		if(search_area_attr!=null&&!search_area_attr.equals("")){
			pageMap.put("area_attr", search_area_attr);
		}
		if(search_cat_attr!=null&&!search_cat_attr.equals("")){
			pageMap.put("cat_attr", search_cat_attr);
		}
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		//判断未审核的条数和今日新增的条数
		if (job_state_s != null && !job_state_s.equals("") && !job_state_s.equals("4")) {
			pageMap.put("info_state", job_state_s);
		} else {
			if(today!=null&&!today.equals("")){
				pageMap.put("today",this.today);
			}else{
				// 操作者为会员则获取所有审核状态的信息，否则获取审核通过与禁用状态的信息
				if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
					pageMap.put("job_state_in", "0,1,2,3");// 审核通过状态
				} else {

					pageMap.put("job_state_in", "1,3");
				}
			}		
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		int count = 0;
		// 如果用户类型为会员的话，就查询会员所对应的数据，否则是管理员的话，查询所有数据
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			// 根据页面提交的条件找出信息总数
			count = this.jobService.getJobMemberCount(pageMap);
		} else {
			// 根据页面提交的条件找出信息总数
			count = this.jobService.getCount(pageMap);
		}
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 找出信息列表，放入list
		jobList = this.jobService.getList(pageMap);
		jobList=com.rbt.function.CategoryFuc.replaceList(jobList,"");
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
		if (title_s != null && !title_s.equals(""))
			pageMap.put("title", title_s);
		if (cat_attr_s != null && !cat_attr_s.equals(""))
			pageMap.put("cat_attr", cat_attr_s);
		if (org_name_s != null && !org_name_s.equals(""))
			pageMap.put("org_name", org_name_s);
		if (job_num_s != null && !job_num_s.equals(""))
			pageMap.put("job_num", job_num_s);
		if (end_date_s != null && !end_date_s.equals(""))
			pageMap.put("end_date", end_date_s);
		if (is_recom_s != null && !is_recom_s.equals(""))
			pageMap.put("is_recom", is_recom_s);
		if (job_state_s != null && !job_state_s.equals("") && !job_state_s.equals("4"))
			pageMap.put("info_state", job_state_s);
		if (info_state_s != null && !info_state_s.equals("") )
			pageMap.put("info_state", info_state_s);
		if (in_date_s != null && !in_date_s.equals(""))
			pageMap.put("in_date", in_date_s);
		if (user_name_s != null && !user_name_s.equals(""))
			pageMap.put("user_name", user_name_s);
		if (enddateString_s != null && !enddateString_s.equals(""))
			pageMap.put("enddate", enddateString_s);
		if (job_state_s != null && !job_state_s.equals("") && !job_state_s.equals("4")) {
			pageMap.put("info_state", job_state_s);
		} else {
			pageMap.put("job_state_in", "0,2");// 未审核状态
		}
		if(search_area_attr!=null&&!search_area_attr.equals(""))
			pageMap.put("area_attr", search_area_attr);
		if(search_cat_attr!=null&&!search_cat_attr.equals(""))
			pageMap.put("cat_attr", search_cat_attr);
		// 根据页面提交的条件找出信息总数
		int count = this.jobService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 找出信息列表，放入list
		jobList = this.jobService.getList(pageMap);
		jobList=com.rbt.function.CategoryFuc.replaceList(jobList,"");
		return goUrl(AUDITLIST);
	}


	/**
	 * 方法描述：根据主键找出招聘信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(job.getCust_id()!=null){
			if(accessControl(job.getCust_id())){
				return list();
			}
		}
		//判断id的值是否符合条件，不符合的话返回到列表
		if(ValidateUtil.isDigital(job.getJob_id())){
			return list();
		}	
		//绑定工资框
		bindsalary();
		// 将从数据库中查询的所属分类存入分类隐藏域中
		backCategory(job.getCat_attr());	
		// 找出地区字段的存入隐藏值中
		backArea(job.getArea_attr());
		if(job.getBirth()!=null){
			String[] ageStrings = job.getBirth().split("-");
			if (ageStrings.length >= 1){
				ageStartString = ageStrings[0];
			}
			if (ageStrings.length >= 2){
				ageEndString = ageStrings[1];
			}
		}
		noReasonKey = job.getInfo_state();
				
		//分类ID转名称
		catIdTocatName(this.job.getCat_attr());
		//根据模块是否启动分类属性
		sysmodule = this.sysmoduleService.get("job");
		if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){			
			if(ischange==null){//第一次加载页面
				info_infoattr_id = this.job.getInfoattr_id();
			}
			getIsCatAttr(info_infoattr_id,job.getCat_attr());		
		}else{
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(job.getCat_attr());	
		}
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：绑定工资下拉框
	 */
	public void bindsalary() {
		Map map = new HashMap();
		map.put("para_code", "salar_type");
		commparaList = commparaService.getList(map);
	}
	
	/**
	 * 方法描述：根据主键找出简历信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		//判断id的值是否符合条件，不符合的话返回到列表
		if(ValidateUtil.isDigital(job.getJob_id())){
			return auditList();
		}	
		
		//进入审核页面时候的加载方法
		auditView("job",job.getJob_id());
		

		String[] ageStrings = job.getBirth().split(",");
		if (ageStrings.length >= 1)
			ageStartString = ageStrings[0];
		if (ageStrings.length >= 2)
			ageEndString = ageStrings[1];
		noReasonKey = job.getInfo_state();
		//获取地区中文字符
		String area_name = "";
		if(job.getArea_attr() != null){
			area_name = AreaFuc.getAreaNameByMap(job.getArea_attr());
		}
		job.setArea_attr(area_name);
		//获取分类中文字符
		String cat_name = "";
		if(job.getCat_attr() != null){
			cat_name = CategoryFuc.getCateNameByMap(job.getCat_attr());
		}
		job.setCat_attr(cat_name);
		// 找出属性列表
		String infoattr_id = job.getInfoattr_id();
		sysmodule = this.sysmoduleService.get("product");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			backAttr(infoattr_id,job.getCat_attr());
		}	
		member = memberService.get(this.session_cust_id);
		if(member!=null){
			memtype = member.getMem_type();
		}
		return goUrl(AUDIT);
	}

	/**
	 * @return the JobList
	 */
	public List getJobList() {
		return jobList;
	}

	/**
	 * @param jobList
	 *            the JobList to set
	 */
	public void setJobList(List jobList) {
		this.jobList = jobList;
	}

	public String getCust_id_s() {
		return cust_id_s;
	}

	public void setCust_id_s(String cust_id_s) {
		this.cust_id_s = cust_id_s;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getCat_attr_s() {
		return cat_attr_s;
	}

	public void setCat_attr_s(String cat_attr_s) {
		this.cat_attr_s = cat_attr_s;
	}

	public String getOrg_name_s() {
		return org_name_s;
	}

	public void setOrg_name_s(String org_name_s) {
		this.org_name_s = org_name_s;
	}

	public String getJob_num_s() {
		return job_num_s;
	}

	public void setJob_num_s(String job_num_s) {
		this.job_num_s = job_num_s;
	}

	public String getEnd_date_s() {
		return end_date_s;
	}

	public void setEnd_date_s(String end_date_s) {
		this.end_date_s = end_date_s;
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

	public String getAgeStartString() {
		return ageStartString;
	}

	public void setAgeStartString(String ageStartString) {
		this.ageStartString = ageStartString;
	}

	public String getAgeEndString() {
		return ageEndString;
	}

	public void setAgeEndString(String ageEndString) {
		this.ageEndString = ageEndString;
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



	public String getFlageJob() {
		return flageJob;
	}

	public void setFlageJob(String flageJob) {
		this.flageJob = flageJob;
	}

	public String getJobid() {
		return jobid;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}

	public ICommparaService getCommparaService() {
		return commparaService;
	}

	public void setCommparaService(ICommparaService commparaService) {
		this.commparaService = commparaService;
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
	public String getNoReasonKey() {
		return noReasonKey;
	}

	public void setNoReasonKey(String noReasonKey) {
		this.noReasonKey = noReasonKey;
	}

	/**
	 * @return the job
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * @param Job
	 *            the job to set
	 */
	public void setJob(Job job) {
		this.job = job;
	}
	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(job == null){
			job = new Job();
		}
		String id = job.getJob_id();
		if(!ValidateUtil.isDigital(id)){
			job = this.jobService.get(id);
		}
	}
}
