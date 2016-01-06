/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: SubscribeAction.java 
 */
package com.rbt.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Category;
import com.rbt.model.Member;
import com.rbt.model.Subscribe;
import com.rbt.service.ICategoryService;
import com.rbt.service.IMemberService;
import com.rbt.service.ISubscribeService;

/**
 * @function 功能 记录会员商机订阅信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Thu Jul 21 08:53:30 CST 2011
 */
@Controller
public class SubscribeAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 记录会员商机订阅信息对象
	 */
	public Subscribe subscribe;
	public Member member;
	/*
	 * 定义供应类别表对象
	 */
	public Category category;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ISubscribeService subscribeService;
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public IMemberService memberService;
	/*
	 * 记录会员商机订阅信息信息集合
	 */
	public List subscribeList;
	/*
	 * 搜索字段
	 */
	public String info_type_s;
	public String send_type_s;
	public String enabled_s;
	public String starttime_s;
	public String endtime_s;
	public String cust_name_s;
	public String keyword_s;
	public String today;

	/*
	 * 定义供应类别表对象
	 */
	public String catt;

	/**
	 * 方法描述：返回新增记录会员商机订阅信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
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
		int count = this.subscribeService.getCount(tmap);
		if(controlMsgNum(count,"subscribe")){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	/**
	 * 方法描述：新增记录会员商机订阅信息
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
		// 将处理后的所属分类串注入到对象中
		this.subscribe.setCat_attr(cat_attr);
		// 将处理后的所属分类串注入到对象中
		this.subscribe.setArea_attr(area_attr);
		// 获取客户标识
		this.subscribe.setCust_id(this.session_cust_id);
		subscribe.setEnabled("0");
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return add();
			}
		}

		//字段验证
		super.commonValidateField(subscribe);
		if(super.ifvalidatepass){
			return add();
		}
		this.subscribeService.insert(subscribe);
		this.addActionMessage("新增会员商机订阅信息成功");
		is_crotorl=true;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员商机订阅信息信息
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
		if (ValidateUtil.isRequired(cat_attr) || cat_attr.indexOf("0") > -1) {
			this.addFieldError("cate_attr", "请选择分类");
		}
		// 将处理后的所属分类串注入到对象中
		this.subscribe.setCat_attr(cat_attr);
		//验证所属地区是否有选择
		if (ValidateUtil.isRequired(area_attr) || area_attr.indexOf("0") > -1) {
			this.addFieldError("area_attr", "请选择地区");
		}
		this.subscribe.setArea_attr(area_attr);
		// 获取客户标识
		if (this.session_cust_id != null) {
			this.subscribe.setCust_id(this.session_cust_id);
		}
		//字段验证
		super.commonValidateField(subscribe);
		if(super.ifvalidatepass){
			return view();
		}
		this.subscribeService.update(subscribe);
		this.addActionMessage("修改会员商机订阅信息成功");
		return list();
	}

	/**
	 * 方法描述：删除记录会员商机订阅信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.subscribe.getSub_id();
		id = id.replace(" ", "");
		this.subscribeService.delete(id);
		this.addActionMessage("删除商机订阅成功");
		return list();
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
		if (info_type_s != null && !info_type_s.equals(""))
			pageMap.put("info_type", info_type_s);
		if (send_type_s != null && !send_type_s.equals(""))
			pageMap.put("send_type", send_type_s);
		if (enabled_s != null && !enabled_s.equals(""))
			pageMap.put("enabled", enabled_s);
		if (cust_name_s != null && !cust_name_s.equals(""))
			pageMap.put("cust_name", cust_name_s);
		if (keyword_s != null && !keyword_s.equals(""))
			pageMap.put("keyword", keyword_s);
		if (starttime_s != null && !starttime_s.equals("")) {
			pageMap.put("starttime", starttime_s);
			if (endtime_s == null || endtime_s.equals("")) {
				Date mydate = new Date();
				@SuppressWarnings("unused")
				String end_time = mydate.toLocaleString().substring(0, 8);
				pageMap.put("endtime", end_time);
			} else {
				pageMap.put("endtime", endtime_s);
			}
		}
		if(today != null && !"".equals(today)){
			pageMap.put("today", today);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.subscribeService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		subscribeList = this.subscribeService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录会员商机订阅信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(subscribe.getCust_id()!=null){
			if(accessControl(subscribe.getCust_id())){
				return list();
			}
		}
		catt = subscribe.getInfo_type();
		backCategory(subscribe.getCat_attr());	
	    // 找出地区字段的存入隐藏值中
		if (subscribe.getArea_attr() != null) {
			this.setHidden_area_value(subscribe.getArea_attr());
			backArea(subscribe.getArea_attr());
		}
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：批量显示/不显示导航
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateenabled() throws Exception {
		String subid = this.subscribe.getSub_id();
		String isenabled = this.subscribe.getEnabled();
		subid = subid.replace(" ", "");
		String subidStr[] = subid.split(",");
		List subList = new ArrayList();
		if (subidStr.length > 0) {
			for (int i = 0; i < subidStr.length; i++) {
				Map subMap = new HashMap();
				subMap.put("enabled", isenabled);
				subMap.put("sub_id", subidStr[i]);
				subList.add(subMap);
			}
		}
		this.subscribeService.updateenabled(subList);
		String tip = "";
		if (isenabled.equals("0")) {
			tip = "修改有效成功";
		} else if (isenabled.equals("1")) {
			tip = "放入回收站成功";
		}
		this.addActionMessage(tip);
		return list();
	}

	/**
	 * @return the SubscribeList
	 */
	public List getSubscribeList() {
		return subscribeList;
	}

	/**
	 * @param subscribeList
	 *            the SubscribeList to set
	 */
	public void setSubscribeList(List subscribeList) {
		this.subscribeList = subscribeList;
	}

	public String getInfo_type_s() {
		return info_type_s;
	}

	public void setInfo_type_s(String info_type_s) {
		this.info_type_s = info_type_s;
	}

	public String getSend_type_s() {
		return send_type_s;
	}

	public void setSend_type_s(String send_type_s) {
		this.send_type_s = send_type_s;
	}

	public String getEnabled_s() {
		return enabled_s;
	}

	public void setEnabled_s(String enabled_s) {
		this.enabled_s = enabled_s;
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

	public String getCust_name_s() {
		return cust_name_s;
	}

	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}

	public String getKeyword_s() {
		return keyword_s;
	}

	public void setKeyword_s(String keyword_s) {
		this.keyword_s = keyword_s;
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

	public String getCatt() {
		return catt;
	}

	public void setCatt(String catt) {
		this.catt = catt;
	}

	public void prepare() throws Exception { super.saveRequestParameter();
		if (subscribe == null) {
			subscribe = new Subscribe();
		}
		String id = subscribe.getSub_id();
		if (!ValidateUtil.isDigital(id)) {
			subscribe = this.subscribeService.get(id);
		}
	}

	/**
	 * @return the subscribe
	 */
	public Subscribe getSubscribe() {
		return subscribe;
	}

	/**
	 * @param Subscribe
	 *            the subscribe to set
	 */
	public void setSubscribe(Subscribe subscribe) {
		this.subscribe = subscribe;
	}
}
