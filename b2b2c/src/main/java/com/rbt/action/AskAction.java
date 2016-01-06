/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: AskAction.java 
 */
package com.rbt.action;

import java.util.*;

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
import com.rbt.model.Ask;
import com.rbt.model.Memberinter;
import com.rbt.model.Resume;
import com.rbt.service.IAnswerService;
import com.rbt.service.IAskService;
import com.rbt.service.ICategoryService;
import com.rbt.service.IChannelService;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberinterService;

/**
 * @function 功能 问题action类
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:25:38 CST 2011
 */
@Controller
public class AskAction extends baseModelAction implements Preparable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2597598240365683697L;
	/*
	 * 信息状态标识
	 */
	public String infostateTip = "";
	/*
	 * 搜索字段 title_s：问题标题 integral_s:悬赏积分 is_recom_s：是否推荐 info_state_s：信息状态 answer_state_s：回答状态 in_date_s：发布时间 end_date_s:发布时间的最大时间 fare_s：收费
	 */
	public String title_s;
	public String integral_s;
	public String is_recom_s;
	public String info_state_s;
	public String answer_state_s;
	public String in_date_s;
	public String end_date_s;
	public String fare_s;
	public String askid;
	public String content;
	public String today;
	public String cat_attr_s;
	/*
	 * 模块类型
	 */
	private String modType = "know";
	/*
	 * 问题对象
	 */
	public Ask ask;
	/*
	 * 积分对象
	 */
	public Memberinter memberinter;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IAskService askService;
	@Autowired
	public IAnswerService answerService;
	@Autowired
	public IChannelService channelService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public IMemberinterService memberinterService;

	/*
	 * 问题信息集合
	 */
	public List askList;
	/*
	 * 悬赏积分集合
	 */
	public List integralList;
	/*
	 * 答案集合
	 */
	public List answerList;


	/**
	 * @author : 林俊钦
	 * @date : Jul 13, 2012 1:42:18 PM
	 * @Method Description : 会员发布条数的判断
	 */
	public boolean controlInfoNum(){
		//查出已发布的信息个数
		Map tmap = new HashMap();
		tmap.put("cust_id", this.session_cust_id);
		int count = this.askService.getCount(tmap);
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
		setIntegral();
		//获取所属模块名是否支持分类属性
		sysmodule = this.sysmoduleService.get("know");
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
					ask = new Ask();
					ask.setAsk_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		} else {
			if (this.ask != null && this.ask.getAsk_id() != null && !this.ask.getAsk_id().equals("")){
				return view();
			} else {
				return cate();
			}
		}
	}
	/**
	 * 方法描述：新增问题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 用于所属分类的回选开始
		loadCategory();
		//验证分类是选择
		validateCategoryIfSelect();
		this.ask.setCat_attr(cat_attr);
		// 用于所属分类结束
		// 根据用户id找出对应的积分数
		Integer fund_num = 0;
		memberinter = this.memberinterService.get(this.session_cust_id);
		if (memberinter != null) {
			fund_num = Integer.parseInt(memberinter.getFund_num());
		}
		if(ask.getIntegral()!=null&&!ask.getIntegral().equals("")&&!ask.getIntegral().equals("0")){
			Integer integral = 0;
			integral = Integer.parseInt(ask.getIntegral());
			if (fund_num < integral) {
				this.addFieldError("ask.integral", "您的积分不足，请从新选择");
			}
			
		}
		
		ask.setCust_id(this.session_cust_id);
		ask.setUser_id(this.session_user_id);
		
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("know");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.ask.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return cate();
			}
		}
		//字段验证
		super.commonValidateField(ask);
		if(super.ifvalidatepass){
			return cate();
		}
		String ask_id=this.askService.insertGetPk(ask, objList);// 获取刚刚插入成功的记录的ID		
		// 对插入成功的信息生成HTML静态页面
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, ask_id);	
		this.addActionMessage("新增问题成功");
		this.ask = null;
		is_crotorl=true;
		return cate();
	}

	/**
	 * 方法描述：修改问题信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = ask.getAsk_id();
		// 判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if (ValidateUtil.isDigital(id)) {
			return list();
		}
		// 用于所属分类的回选开始
		loadCategory();
		// 验证所属分类是否有选择
		if (ValidateUtil.isRequired(cat_attr) || cat_attr.indexOf("0") > -1) {
			this.addFieldError("cate_attr", "请选择分类");
		}
		// 用于所属分类结束
		// 将处理后的所属分类串注入到subject对象中
		this.ask.setCat_attr(cat_attr);
		// 根据用户id找出对应的积分数
		Integer fund_num = 0;
		memberinter = this.memberinterService.get(this.session_cust_id);
		if (memberinter != null) {
			fund_num = Integer.parseInt(memberinter.getFund_num());
		}
		Integer integral = 0;
		integral = Integer.parseInt(ask.getIntegral());
		if (fund_num < integral) {
			this.addFieldError("ask.integral", "您的积分不足，请从新选择");
		}
		ask.setCust_id(this.session_cust_id);
		ask.setUser_id(this.session_user_id);
		// 当前登录的用户是会员时，修改信息时，信息状态更新成未审核 info_state:0 表示未审核
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			ask.setInfo_state("0");
		}
		
		// 数据库原有的分类串	
		supply_infoattr_id = ask.getInfoattr_id();	
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("know");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.ask.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}else{
			supply_infoattr_id=null;
		}					
		
		//字段验证
		super.commonValidateField(ask);
		if(super.ifvalidatepass){
			return view();
		}
		
		this.askService.update(ask, objList, supply_infoattr_id);
		
		this.askService.update(ask);
		if (this.ask.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.ask.getAsk_id());
		}
		this.addActionMessage("修改问题成功");
		return list();
	}

	/**
	 * 方法描述：删除问题信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		del();
		return list();
	}
	
	public void del(){
		String id = this.ask.getAsk_id();
		id = id.replace(" ", "");
		if (id!=null&&!"".equals(id)) {
			id = id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				Ask ask=this.askService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],ask.getIn_date());
				this.askService.delete(ids[i]);
			}
			this.addActionMessage("删除问题成功");	
		}
	}
	/**
	 * 方法描述：审核知道批量删除
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 */
	 public String checkDel() throws Exception {
		 	del();
			return  auditList();
		}

	/**
	 * 方法描述：根据搜索条件列出审核通过和禁用信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 获得悬赏积分
		setIntegral();
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		} else {
			if (today != null && !today.equals("")) {
				pageMap.put("today", this.today);
			} else {
				pageMap.put("infostate", "1,3");
			}
		}
		if (title_s != null && !title_s.equals("")) {
			pageMap.put("title", title_s);
		}
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("cat_attr", cat_attr_s);
		}
		if (integral_s != null && !integral_s.equals("")) {
			pageMap.put("integral", integral_s);
		}
		if (is_recom_s != null && !is_recom_s.equals("")) {
			pageMap.put("is_recom", is_recom_s);
		}
		if (info_state_s != null && !info_state_s.equals("")) {
			pageMap.put("info_state", info_state_s);
		}
		if (answer_state_s != null && !answer_state_s.equals("")) {
			pageMap.put("answer_state", answer_state_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (end_date_s != null && !end_date_s.equals("")) {
			pageMap.put("end_date", end_date_s);
		}
		
		//过滤地区
		pageMap=super.areafilter(pageMap);
		
		// 根据页面提交的条件找出信息总数
		int count = this.askService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		askList = this.askService.getList(pageMap);
		// 分类代码转换成中文字符
		askList = CategoryFuc.replaceList(askList, "");
		// 找出问题对应的答案数量
		if (askList != null && askList.size() > 0) {
			int answernum = 0;
			Map aMap = new HashMap();
			Map<String,	Integer> bMap = new HashMap<String,Integer>();
			for (int i = 0; i < askList.size(); i++) {
				bMap = (Map) askList.get(i);
				if (bMap.get("ask_id") != null) {
					aMap.put("ask_id", bMap.get("ask_id").toString());
					answernum = this.answerService.getCount(aMap);
				}
				bMap.put("answernum", answernum);
				askList.set(i, bMap);
			}
		}
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据搜索条件列出未审核和审核不通过信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditList() throws Exception {
		// 获得悬赏积分
		setIntegral();
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		pageMap.put("infostate", "0,2");

		if (title_s != null && !title_s.equals("")) {
			pageMap.put("title", title_s);
		}
		if (cat_attr_s != null && !cat_attr_s.equals("")) {
			pageMap.put("cat_attr", cat_attr_s);
		}
		if (integral_s != null && !integral_s.equals("")) {
			pageMap.put("integral", integral_s);
		}
		if (is_recom_s != null && !is_recom_s.equals("")) {
			pageMap.put("is_recom", is_recom_s);
		}
		if (info_state_s != null && !info_state_s.equals("")) {
			pageMap.put("info_state", info_state_s);
		}
		if (answer_state_s != null && !answer_state_s.equals("")) {
			pageMap.put("answer_state", answer_state_s);
		}
		if (in_date_s != null && !in_date_s.equals("")) {
			pageMap.put("in_date", in_date_s);
		}
		if (end_date_s != null && !end_date_s.equals("")) {
			pageMap.put("end_date", end_date_s);
		}
		// 根据页面提交的条件找出信息总数
		int count = this.askService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		askList = this.askService.getList(pageMap);
		// 分类代码转换成中文字符
		askList = CategoryFuc.replaceList(askList, "");
		return goUrl(AUDITLIST);
	}

	/**
	 * 方法描述：根据主键找出问题信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(ask.getCust_id()!=null){
			if(accessControl(ask.getCust_id())){
				return list();
			}
		}
		setIntegral();
		String cate_attr = "";			
		// 判断用户是否重选所属分类
		if (ValidateUtil.isRequired(cat_attr)) {
			// 根据ID获取所属分类串					
			cate_attr = this.ask.getCat_attr();	
			cat_attr = cate_attr;
		} else {
			cate_attr = cat_attr.replace(" ", "");						
		}
		//分类ID转名称
		catIdTocatName(this.ask.getCat_attr());
		//根据模块是否启动分类属性
		sysmodule = this.sysmoduleService.get("know");
		if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){			
			if(ischange==null){//第一次加载页面
				info_infoattr_id = this.ask.getInfoattr_id();
			}
			getIsCatAttr(info_infoattr_id,ask.getCat_attr());			
		}else{
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(ask.getCat_attr());	
		}		
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：积分信息集合
	 * 
	 * @return
	 * @throws Exception
	 */
	public void setIntegral(){
		Map integralMap = new HashMap();
		integralMap.put("para_code", "integral_type");
		integralList = this.commparaService.getList(integralMap);
	}
	/**
	 * 方法描述：根据主键找出问题信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		
		//进入审核页面时候的加载方法
		auditView("know",ask.getAsk_id());
		
		String infoattr_id = ask.getInfoattr_id();
		sysmodule = this.sysmoduleService.get("know");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			backAttr(infoattr_id,ask.getCat_attr());
		}		
		// 找出积分和分类
		if (ask.getIntegral() != null) {
			String integral = CommparaFuc.get_commparakey_By_value(ask.getIntegral(), "integral_type");
			ask.setIntegral(integral);
		}
		if (ask.getCat_attr() != null) {
			String cat_attr = CategoryFuc.getCateNameByMap(ask.getCat_attr());
			ask.setCat_attr(cat_attr);
		}
		if (ask.getInfo_state() != null) {
			setInfostateTip(ask.getInfo_state());
		}
		return goUrl(AUDIT);
	}

	/**
	 * @function 功能 审核问题
	 * @author 创建人 邱景岩
	 * @date 创建日期 Jul 19, 2011 2:08:39 PM
	 */
	public String auditState() throws Exception {

		String id = ask.getAsk_id();
		// 判断实体ID是否存在,若不存在该实体，返回到审核列表页，不进行任何操作
		if (ValidateUtil.isDigital(id)) {
			return auditList();
		}
		//启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("know",ask.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		if (ask.getInfo_state().equals("2") && ValidateUtil.isRequired(ask.getNo_reason())) {
			this.addFieldError("ask.no_reason", "请输入拒绝理由");
			return audit();
		}
		Map stateMap = new HashMap();
		if (!ask.getInfo_state().equals("2")) {
			stateMap.put("no_reason", "");
		} else {
			stateMap.put("no_reason", ask.getNo_reason());
		}
		stateMap.put("ask_id", ask.getAsk_id());
		stateMap.put("info_state", ask.getInfo_state());
		this.askService.updateAskState(stateMap);
		if (this.ask.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.ask.getAsk_id());
		}
		this.addActionMessage("已审核知道");
		return auditList();
	}

	/**
	 * 方法描述：是否推荐批量修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String askid = this.ask.getAsk_id();
		String isrecom = this.ask.getIs_recom();
		askid = askid.replace(" ", "");
		String askStr[] = askid.split(",");
		List askList = new ArrayList();
		if (askStr.length > 0) {
			for (int i = 0; i < askStr.length; i++) {
				Map askMap = new HashMap();
				askMap.put("is_recom", isrecom);
				askMap.put("ask_id", askStr[i]);
				askList.add(askMap);
			}
		}
		this.askService.updateIsrecom(askList);
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
	 * 方法描述：选择最佳答案
	 * 
	 * @return
	 * @throws Exception
	 */
	public String selectgood() throws Exception {
		return list();
	}

	/**
	 * @return the ask
	 */
	public Ask getAsk() {
		return ask;
	}

	/**
	 * @param Ask
	 *            the ask to set
	 */
	public void setAsk(Ask ask) {
		this.ask = ask;
	}

	/**
	 * @return the AskList
	 */
	public List getAskList() {
		return askList;
	}

	/**
	 * @param askList
	 *            the AskList to set
	 */
	public void setAskList(List askList) {
		this.askList = askList;
	}

	/**
	 * @return the integralList
	 */
	public List getIntegralList() {
		return integralList;
	}

	/**
	 * @param integralList
	 *            the integralList to set
	 */
	public void setIntegralList(List integralList) {
		this.integralList = integralList;
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
	 * @return the integral_s
	 */
	public String getIntegral_s() {
		return integral_s;
	}

	/**
	 * @param integral_s
	 *            the integral_s to set
	 */
	public void setIntegral_s(String integral_s) {
		this.integral_s = integral_s;
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
	 * @return the answer_state_s
	 */
	public String getAnswer_state_s() {
		return answer_state_s;
	}

	/**
	 * @param answer_state_s
	 *            the answer_state_s to set
	 */
	public void setAnswer_state_s(String answer_state_s) {
		this.answer_state_s = answer_state_s;
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
	 * @return the infostateTip
	 */
	public String getInfostateTip() {
		return infostateTip;
	}

	/**
	 * @param infostateTip
	 *            the infostateTip to set
	 */
	public void setInfostateTip(String infostateTip) {
		this.infostateTip = infostateTip;
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

	public String getAskid() {
		return askid;
	}

	public void setAskid(String askid) {
		this.askid = askid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List answerList) {
		this.answerList = answerList;
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

	public Memberinter getMemberinter() {
		return memberinter;
	}

	public void setMemberinter(Memberinter memberinter) {
		this.memberinter = memberinter;
	}

	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if (ask == null) {
			ask = new Ask();
		}
		String id = this.ask.getAsk_id();
		if (!ValidateUtil.isDigital(id)) {
			ask = this.askService.get(id);
		}
	}

}