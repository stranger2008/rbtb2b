/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: BuyAction.java 
 */
package com.rbt.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Buy;
import com.rbt.model.Member;
import com.rbt.service.IBuyService;
import com.rbt.service.ICommparaService;
import com.rbt.createHtml.DoHtml;
import com.rbt.function.AreaFuc;
import com.rbt.function.AuditModelFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.function.CommparaFuc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 求购表action类
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Jul 29 14:52:50 CST 2011
 */
@Controller
public class BuyAction  extends baseModelAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IBuyService buyService;
	@Autowired
	public ICommparaService commparaService;
	/*
	 * 定义搜索字段的区域get set
	 */
	public String title_s;
	public String type_s;
	public String brand_s;
	public String is_recom_s;
	public String fare_start_s;
	public String fare_last_s;
	public String starttime_s;
	public String area_attr_s;
	public String cat_attr_s;
	public String endtime_s;
	public String state_s;
	public String buyid;
	public String info_state_s;

	/*
	 * 求购表对象
	 */
	public Buy buy;
	/*
	 * 定义常量字段,用于从基本参数中找出相应的模块值
	 */
	private String para_code = "buy_type";
	public String modType = "buy";

	/*
	 * 系统参数表数据
	 */
	public List commparalist;	
	/*
	 * 当天日期
	 */
	public String today;
	/*
	 * 显示审核不通过的理由输入框
	 */
	public String noReasonKey;
	/*
	 * 求购表信息集合
	 */
	public List buyList;

	//获取参数列表
	public void getCommparalists()
	{
		Map map = new HashMap();
		map.put("para_code", para_code);
		commparalist = commparaService.getList(map);
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
		int count = this.buyService.getCount(tmap);
		if(controlMsgNum(count,modType)){
			return true;
		}else{
			return false;
		}
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
					buy = new Buy();
					buy.setBuy_id(update_value);
					return goUrl("cate");			
				} else {					
					is_select = "1";
					return goUrl("cate");		
				}
		} else {
			if (this.buy != null && this.buy.getBuy_id() != null && !this.buy.getBuy_id().equals("")){
				return view();
			} else {
				return cate();
			}
		}
	}
	
	
	/**
	 * @MethodDescribe 方法描述 根据系统模块中对应的模块是否支持分类属性跳转到新增供应页面
	 * @author 创建人 林俊钦
	 * @date 创建日期 Oct 21, 2011 11:14:29 AM
	 */
	public String cate() throws Exception{
		// 找出参数类型
		Map paramap = new HashMap();
		paramap.put("para_code", para_code);
		commparalist = commparaService.getList(paramap);
		Map unitMap = new HashMap();
		unitMap.put("para_code", "unit");
		unitList = commparaService.getList(unitMap);
		//获取所属模块名是否支持分类属性
		sysmodule = this.sysmoduleService.get("buy");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			if(cat_attr == null||cat_attr.equals("0")){
				return goUrl("cate");
			}
			checkIsAttr();				
		}
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			Member mem = this.memberService.get(this.session_cust_id);
			if (mem != null&&mem.getArea_attr()!= null) {
			  backArea(mem.getArea_attr());
			}
			//发布信息数量控制
			controlInfoNum();
		}
		return goUrl(ADD);
	}
	
	
	/**
	 * 方法描述：新增求购表
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
		//validateAreaIfSelect();
		// 将处理后的所属分类串注入到buy对象中
		this.buy.setCat_attr(cat_attr);
		this.buy.setArea_attr(area_attr);
		// 用于所属地区的回选结束		
		// 获取客户标识
		this.buy.setCust_id(this.session_cust_id);
		this.buy.setUser_id(this.session_user_id);		
		
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("buy");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			//信息属性的处理
			String infoattr_id = RandomStrUtil.getNumberRand();		
			this.buy.setInfoattr_id(infoattr_id);
			super.checkMust(infoattr_id);
		}
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			//发布信息数量控制
			if(controlInfoNum()){
				return cate();
			}
		}
		//字段验证
		super.commonValidateField(buy);
		if(super.ifvalidatepass){
			return cate();
		}
		// 获取刚刚插入成功的记录的ID	
		String buy_id=this.buyService.insertGetPk(buy, objList);
		// 对插入成功的信息生成HTML静态页面
		DoHtml doHtml = new DoHtml();
		doHtml.doArticleHtml(modType, buy_id);	
		this.addActionMessage("新增求购信息成功");
		this.buy = null;
		is_crotorl=true;
		return cate();
	}

	/**
	 * 方法描述：修改求购表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = buy.getBuy_id();
		//判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if (ValidateUtil.isDigital(id)) {
			return list();
		}
		// 用于所属分类的回选开始
		loadCategory();
		// 用于所属地区的回选开始
		loadArea();
		//验证分类是选择
		validateCategoryIfSelect();
		//验证地区是选择
		validateAreaIfSelect();
		// 将处理后的所属分类串注入到buy对象中
		this.buy.setCat_attr(cat_attr);
		this.buy.setArea_attr(area_attr);
		//获取会员的cust_id
		String cust_id = "";
		if (this.buy.getBuy_id() != null&&!"".equals(this.buy.getBuy_id())) {
			Buy buy_class = this.buyService.get(this.buy.getBuy_id());
			if (buy_class != null&&buy_class.getCust_id() != null) {
					cust_id = buy_class.getCust_id();
			}
		}
		// 获取客户标识
		this.buy.setCust_id(cust_id);
		this.buy.setUser_id(this.session_user_id);
		// 数据库原有的分类串	
		supply_infoattr_id = buy.getInfoattr_id();	
		// 判断是否为支持分类属性，如果是的话则处理属性信息
		sysmodule = this.sysmoduleService.get("buy");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			String infoattr_id = RandomStrUtil.getNumberRand();
			this.buy.setInfoattr_id(infoattr_id);
			//信息属性的处理
			super.checkMust(infoattr_id);	
		}else{
			supply_infoattr_id=null;
		}					

		//字段验证
		super.commonValidateField(buy);
		if(super.ifvalidatepass){
			return view();		
		}		
		this.buyService.update(buy, objList, supply_infoattr_id);
		// 当审核通过时更新页面
		if ("1".equals(this.buy.getInfo_state())) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.buy.getBuy_id());
		}
		this.addActionMessage("修改求购信息成功");
		return list();
	}

	/**
	 * 方法描述：删除求购表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		// 判断需要删除的ID是否为空
		del();
		return list();
	}
	
	public void del(){
		String id=this.buy.getBuy_id();
		if(id!=null && !"".equals(id)){
			id=id.replace(" ", "");
			String[] ids=id.split(",");
			DoHtml dohtml=new DoHtml();
			for(int i =0;i<ids.length;i++){
				//获取当前ID的对象
				Buy buy=this.buyService.get(ids[i]);
				dohtml.delArticeHtml(modType,ids[i],buy.getIn_date());
				this.buyService.delete(ids[i]);
			}
			this.addActionMessage("删除求购信息成功");
		}
	}
	/**
	 * 方法描述：审核求购 批量删除信息
	 * 
	 * @author 陈晓艺
	 * @throws Exception
	 * @date : Jul 9, 2012 8:58:01 AM
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
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		// 获取搜索的标题
		if (this.title_s != null && !this.title_s.equals("")) {
			pageMap.put("title", this.title_s);
		}
		// 获取搜索的产品品牌
		if (this.brand_s != null && !this.brand_s.equals("")) {
			pageMap.put("brand", this.brand_s);
		}
		// 获取搜索的求购类型
		if (this.type_s != null && !this.type_s.equals("")) {
			pageMap.put("buy_type", this.type_s);
		}
		// 获取搜索的审核状态
		if (this.state_s != null && !this.state_s.equals("")) {
			pageMap.put("info_state", this.state_s);
		}
		if (this.info_state_s != null && !this.info_state_s.equals("")) {
			pageMap.put("info_state", this.info_state_s);
		}		
		// 获取搜索的是否推荐
		if (this.is_recom_s != null && !this.is_recom_s.equals("")) {
			pageMap.put("is_recom", this.is_recom_s);
		}
		// 获取搜索的所属地区
		if (request.getParameter("area_attr_s") != null) {
			String area_attr = request.getParameter("area_attr_s");
			pageMap.put("area_attr", area_attr);
		}
		// 获取搜索的所属分类
		if (request.getParameter("cat_attr_s") != null) {
			String cat_attr = request.getParameter("cat_attr_s");			
			pageMap.put("cat_attr", cat_attr);
		}
		// 获取搜索的内容收费
		if (this.fare_start_s != null && !this.fare_start_s.equals("") && this.fare_last_s != null && !this.fare_last_s.equals("")) {
			pageMap.put("search_fare_start", this.fare_start_s);
			pageMap.put("search_fare_last", this.fare_last_s);
		}
		
		if (this.starttime_s != null && !this.starttime_s.equals("")) {
			pageMap.put("search_starttime", this.starttime_s);
		}
		
		if (this.endtime_s != null && !this.endtime_s.equals("")) {
			pageMap.put("search_endtime", this.endtime_s);
		}
		
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		} else {
			// 判断今天新增供应
			if (today != null && !"".equals(today)) {
				pageMap.put("today", this.today);
			} else {
				pageMap.put("info_state_in", "1,3");
			}
		}
		getCommparalists();
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.buyService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		// 找出信息列表，放入list
		buyList = this.buyService.getList(pageMap);
		// 把数据库中的ID替换成名称
		buyList = CategoryFuc.replaceList(buyList, para_code);
		return goUrl(INDEXLIST);
	}

	/**
	 * @MethodDescribe 方法描述 根据条件找出审核列表页
	 * @author 创建人 林俊钦
	 * @date 创建日期 Aug 1, 2011 1:26:06 PM
	 */
	public String auditList() throws Exception {
		HttpServletRequest request =getRequest();
		request.setCharacterEncoding("UTF-8");
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		// 找出审核页面的状态
		pageMap.put("info_state_in", "0,2");
		// 获取搜索的标题
		if (this.title_s != null && !this.title_s.equals("")) {
			pageMap.put("title", this.title_s);
		}
		// 获取搜索的产品品牌
		if (this.brand_s != null && !this.brand_s.equals("")) {
			pageMap.put("brand", this.brand_s);
		}
		// 获取搜索的求购类型
		if (this.type_s != null && !this.type_s.equals("")) {
			pageMap.put("buy_type", this.type_s);
		}
		// 获取搜索的审核状态
		if (this.state_s != null && !this.state_s.equals("")) {
			pageMap.put("info_state", this.state_s);
		}
		if (this.info_state_s != null && !this.info_state_s.equals("")) {
			pageMap.put("info_state", this.info_state_s);
		}
		// 获取搜索的是否推荐
		if (this.is_recom_s != null && !this.is_recom_s.equals("")) {
			pageMap.put("is_recom", this.is_recom_s);
		}
		// 获取搜索的内容收费
		if (this.fare_start_s != null && !this.fare_start_s.equals("") && this.fare_last_s != null && !this.fare_last_s.equals("")) {
			pageMap.put("search_fare_start", this.fare_start_s);
			pageMap.put("search_fare_last", this.fare_last_s);
		}
		// 获取搜索的发布时间
		if (this.starttime_s != null && !this.starttime_s.equals("")) {
			pageMap.put("search_starttime", this.starttime_s);
			
		}
		if (this.endtime_s != null && !this.endtime_s.equals("")) {
			pageMap.put("search_endtime", endtime_s);
		} 
		// 获取搜索的所属地区
		if (request.getParameter("area_attr_s") != null) {
			pageMap.put("area_attr", request.getParameter("area_attr_s"));
		}
		// 获取搜索的所属分类
		if (request.getParameter("cat_attr_s") != null) {
			pageMap.put("cat_attr", request.getParameter("cat_attr_s"));
		}
		// 根据页面提交的条件找出信息总数
		int count = this.buyService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		getCommparalists();
		buyList = this.buyService.getList(pageMap);
		// 把数据库中的ID替换成名称
		buyList = CategoryFuc.replaceList(buyList, para_code);
		return goUrl(AUDITLIST);
	}

	/**
	 * @MethodDescribe 方法描述 根据主键找出求购表的详细信息
	 * @author 创建人 林俊钦
	 * @date 创建日期 Aug 1, 2011 1:28:15 PM
	 */
	public String audit() throws Exception {
		
		//进入审核页面时候的加载方法
		auditView("buy",buy.getBuy_id());

		
		String infoattr_id = buy.getInfoattr_id();
		sysmodule = this.sysmoduleService.get("buy");
		if(sysmodule != null && "0".equals(sysmodule.getIs_catattr())){
			backAttr(infoattr_id,buy.getCat_attr());
		}		
		noReasonKey = buy.getInfo_state();
		// 获取求购类型，分类和地区的中文字符
		if (buy.getBuy_type() != null) {
			String buy_type = CommparaFuc.get_commparakey_By_value(buy.getBuy_type(), para_code);
			buy.setBuy_type(buy_type);
		}
		if (buy.getCat_attr() != null) {
			String cat_attr = CategoryFuc.getCateNameByMap(buy.getCat_attr());
			buy.setCat_attr(cat_attr);
		}
		if (buy.getArea_attr() != null) {
			String area_attr = AreaFuc.getAreaNameByMap(buy.getArea_attr());
			buy.setArea_attr(area_attr);
		}
		return goUrl(AUDIT);
	}

	/**
	 * @MethodDescribe 方法描述 运营商审核方法
	 * @author 创建人 林俊钦
	 * @date 创建日期 Aug 1, 2011 1:46:10 PM
	 */
	public String auditState() throws Exception {
		String buyid = buy.getBuy_id();
		//判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if (ValidateUtil.isDigital(buyid)) {
			return auditList();
		}
		//启用审核流程操作
		//判断当前操作是插入审核历史表信息，还是改变主信息表的审核状态
		if(auditInfoState("buy",buy.getInfo_state()).equals("1")){
			this.addActionMessage("审核信息成功");
			return audit();
		}
		// 定义需要修改状态中所需的字段
		String id = "", info_state = "", no_reason = "";
		if (this.buy.getBuy_id() != null) {
			id = this.buy.getBuy_id();
		}
		if (buy.getInfo_state() == null && buy.getInfo_state().equals("")) {
			this.addFieldError("buy.info_state", "请选择审核状态");
			return goUrl(AUDIT);
		}else {
			info_state = this.buy.getInfo_state();
		}
		if (buy.getInfo_state().equals("2")) {
			if (ValidateUtil.isRequired(buy.getNo_reason())) {
				this.addFieldError("buy.no_reason", "请输入拒绝理由");
				noReasonKey ="2";
				return audit();
			}
		}
		no_reason = this.buy.getNo_reason();		
		// 通过ID查找出相应的求购表记录的对象
		buy = this.buyService.get(id);
		// 重新设置状态
		this.buy.setInfo_state(info_state);
		// 重新设置理由
		this.buy.setNo_reason(no_reason);
		// 更新对象的操作
		this.buyService.update(buy);
		// 当审核通过时更新页面
		if (this.buy.getInfo_state().equals("1")) {
			// 对插入成功的信息生成HTML静态页面
			DoHtml doHtml = new DoHtml();
			doHtml.doArticleHtml(modType, this.buy.getBuy_id());
		}
		this.addActionMessage("审核求购信息成功");
		return auditList();
	}

	/**
	 * 方法描述：根据主键找出求购表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.buy.getBuy_id();
		if(buy.getCust_id()!=null){
			if(accessControl(buy.getCust_id())){
				return list();
			}
		}
		// 判断需要查看的求购表中传的ID是否为空
		if (id!= null && !"".equals(id)) {
			getCommparalists();
			// 获取传进来的ID值
			buy = this.buyService.get(id);
			// 将从数据库中查询的所属分类存入分类隐藏域中
			backCategory(buy.getCat_attr());	
			// 找出地区字段的存入隐藏值中
			backArea(buy.getArea_attr());
			
			//分类ID转名称
			catIdTocatName(this.buy.getCat_attr());
			//根据模块是否启动分类属性
			sysmodule = this.sysmoduleService.get("buy");
			if(sysmodule!=null&&"0".equals(sysmodule.getIs_catattr())){			
				if(ischange==null){//第一次加载页面
					info_infoattr_id = this.buy.getInfoattr_id();
				}
				getIsCatAttr(info_infoattr_id,buy.getCat_attr());		
			}else{
				// 将从数据库中查询的所属分类存入分类隐藏域中
				backCategory(buy.getCat_attr());	
			}		
		}
		
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：批量修改推荐
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateisrecom() throws Exception {
		String buyid = this.buy.getBuy_id();
		String isrecom = this.buy.getIs_recom();
		buyid = buyid.replace(" ", "");
		String downStr[] = buyid.split(",");
		List buyList = new ArrayList();
		if (downStr.length > 0) {
			for (int i = 0; i < downStr.length; i++) {
				Map galleryMap = new HashMap();
				galleryMap.put("is_recom", isrecom);
				galleryMap.put("buy_id", downStr[i]);
				buyList.add(galleryMap);
			}
		}
		this.buyService.updateBuyState(buyList);
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
	 * @return the BuyList
	 */
	public List getBuyList() {
		return buyList;
	}

	/**
	 * @param buyList
	 *            the BuyList to set
	 */
	public void setBuyList(List buyList) {
		this.buyList = buyList;
	}

	/**
	 * @return the commparalist
	 */
	public List getCommparalist() {
		return commparalist;
	}

	/**
	 * @param commparalist
	 *            the commparalist to set
	 */
	public void setCommparalist(List commparalist) {
		this.commparalist = commparalist;
	}

	/**
	 * @return the buyService
	 */
	public IBuyService getBuyService() {
		return buyService;
	}

	/**
	 * @return the buy
	 */
	public Buy getBuy() {
		return buy;
	}

	/**
	 * @param Buy
	 *            the buy to set
	 */
	public void setBuy(Buy buy) {
		this.buy = buy;
	}


	/**
	 * @return the hidden_area_value
	 */
	public String getHidden_area_value() {
		return hidden_area_value;
	}

	/**
	 * @param hidden_area_value
	 *            the hidden_area_value to set
	 */
	public void setHidden_area_value(String hidden_area_value) {
		this.hidden_area_value = hidden_area_value;
	}


	public String getNoReasonKey() {
		return noReasonKey;
	}

	public void setNoReasonKey(String noReasonKey) {
		this.noReasonKey = noReasonKey;
	}

	public String getBuyid() {
		return buyid;
	}

	public void setBuyid(String buyid) {
		this.buyid = buyid;
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
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if (buy == null) {
			buy = new Buy();
		}
		String id = this.buy.getBuy_id();
		if (!ValidateUtil.isDigital(id)) {
			buy = this.buyService.get(id);
		}
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getType_s() {
		return type_s;
	}

	public void setType_s(String type_s) {
		this.type_s = type_s;
	}

	public String getBrand_s() {
		return brand_s;
	}

	public void setBrand_s(String brand_s) {
		this.brand_s = brand_s;
	}

	public String getIs_recom_s() {
		return is_recom_s;
	}

	public void setIs_recom_s(String is_recom_s) {
		this.is_recom_s = is_recom_s;
	}

	public String getFare_start_s() {
		return fare_start_s;
	}

	public void setFare_start_s(String fare_start_s) {
		this.fare_start_s = fare_start_s;
	}

	public String getFare_last_s() {
		return fare_last_s;
	}

	public void setFare_last_s(String fare_last_s) {
		this.fare_last_s = fare_last_s;
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

	public String getState_s() {
		return state_s;
	}

	public void setState_s(String state_s) {
		this.state_s = state_s;
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

}
