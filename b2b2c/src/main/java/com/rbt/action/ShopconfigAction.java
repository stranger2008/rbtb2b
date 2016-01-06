/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: ShopconfigAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.JsonUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.MemberFuc;
import com.rbt.model.Memberconfig;
import com.rbt.model.Shopconfig;
import com.rbt.service.ICommparaService;
import com.rbt.service.ISendmodeService;
import com.rbt.service.IMemberchannelService;
import com.rbt.service.IMemberconfigService;
import com.rbt.service.IShopconfigService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 商店设置action类
 * @author 创建人 hxk
 * @date 创建日期 Tue Feb 28 10:24:54 CST 2012
 */
@Controller
public class ShopconfigAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 商店设置对象
	 */
	private Shopconfig shopconfig;
	
	private Memberconfig memberconfig;
	/*
	 * 商店设置业务层接口
	 */
	@Autowired
	private IShopconfigService shopconfigService;
	@Autowired
	private IMemberconfigService memberconfigService;
	@Autowired
	private IMemberchannelService memberchannelService;
	/*
	 * 参数业务接口
	 */
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public ISendmodeService sendmodeService;	
	
	/*
	 * 商店设置信息集合
	 */
	public List shopconfigList;
	public List sendmodeList;
	public String shop_name_s;
	public String contant_man_s;
	public String is_colse_s;
	public String info_state_s;
	public String cat_attr_s;
	public String cust_name;
	public String bus_model;
	public String area_name;
	public String start_area_str;
	public String end_area_str;
	public String mode_type;
	public String start_area_name;
	public String area_attr_s;
	
	//经营模式
	public List commparaList;
	
	/*
	 * 回显经营模式选中的值集合
	 */
	public List clientList;
	
	/*
	 * 用于回选经营模式属性的绑定
	 */
	public List clientStrList;
	
	/**
	 * @return the shopconfig
	 */
	public Shopconfig getShopconfig() {
		return shopconfig;
	}
	/**
	 * @param Shopconfig
	 *            the shopconfig to set
	 */
	public void setShopconfig(Shopconfig shopconfig) {
		this.shopconfig = shopconfig;
	}
	
	/**
	 * 方法描述：返回新增商店设置页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}
	
	public void setPlatType(){
		mall_type =Constants.MALL_TYPE_B2C;
	}

	/**
	 * 方法描述：新增商店设置
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		super.commonValidateField(shopconfig);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.shopconfigService.insert(shopconfig);
		this.addActionMessage("新增商店设置成功！");
		this.shopconfig = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改商店设置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.shopconfigService.update(shopconfig);
		this.addActionMessage("修改商店设置信息成功！");
		return list();
	}
	
	/**
	 * @author : 林俊钦
	 * @throws Exception 
	 * @date : May 16, 2012 1:51:25 PM
	 * @Method Description :更新物流模板设置页面
	 */
	public String sendset() throws Exception{
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			shopconfig=this.shopconfigService.GetByCustId(this.session_cust_id);
		}else{
			shopconfig=this.shopconfigService.get(shopconfig.getShop_id());
		}		
		if(shopconfig==null){
			if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
				return bmallactive();
			}else{
				return list();
			}
		}
		Map sendMap=new HashMap();
		sendmodeList=this.sendmodeService.getList(sendMap);

		return goUrl("upsendset");
	}
	
	/**
	 * @author : 林俊钦
	 * @date : May 17, 2012 10:13:58 AM
	 * @Method Description : 运营商到达地区串设置方法
	 */
	public String modeset() throws Exception{
		shopconfig=this.shopconfigService.GetByCustId(this.session_cust_id);
		if(shopconfig==null){
			shopconfig=new Shopconfig();
			shopconfig.setCust_id(this.session_cust_id);
			shopconfig.setShop_name("");
			shopconfig.setBusi_mode("");
			shopconfig.setInfo_state("1");
			shopconfig.setIs_colse("1");
			shopconfig.setUser_id(this.session_user_id);
			this.shopconfigService.insert(shopconfig);
		}
		//数据插入成功后重新获取对象
		shopconfig=this.shopconfigService.GetByCustId(this.session_cust_id);
		Map sendMap=new HashMap();
		sendmodeList=this.sendmodeService.getList(sendMap);
		//转换开始地区名称
		return goUrl("modeset");
		
	}
	
	
	/**
	 * @author : 林俊钦
	 * @throws Exception 
	 * @date : May 16, 2012 10:24:05 AM
	 * @Method Description :物流配置方法
	 */
	public String set() throws Exception{
		Shopconfig shop=new Shopconfig();
		if(shopconfig.getShop_id()!=null){
			shop=this.shopconfigService.get(shopconfig.getShop_id());			
		}
		//更新到店铺信息
		this.shopconfigService.update(shop);
		this.addActionMessage("物流模板设置成功！");
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			return sendset();
		}else{
			return modeset();
		}		
	}
	
	/**
	 * 方法描述：修改商店设置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String bmallupdate() throws Exception {
		
		validateAreaIfSelect();
		super.commonValidateField(shopconfig);
		if(super.ifvalidatepass){
			return bmalllist();
		}
		area_attr=area_attr.replace(" ", "");
		shopconfig.setArea_attr(area_attr);
		this.shopconfigService.update(shopconfig);
		this.addActionMessage("店铺信息设置成功！");
		return bmalllist();
	}
	/**
	 * 方法描述：删除商店设置信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.shopconfig.getShop_id();
		id = id.replace(" ", "");
		this.shopconfigService.delete(id);
		this.addActionMessage("删除商店设置成功！");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		//搜索店铺名称
		if(shop_name_s!=null&&!shop_name_s.equals(""))
		{
			pageMap.put("shop_name", shop_name_s);
		}
		//搜索联系人
		if(contant_man_s!=null&&!contant_man_s.equals(""))
		{
			pageMap.put("contant_man", contant_man_s);
		}
		if(is_colse_s!=null&&!is_colse_s.equals(""))
		{
			pageMap.put("is_colse", is_colse_s);
		}
		if(info_state_s!=null&&!info_state_s.equals(""))
		{
			pageMap.put("info_state", info_state_s);
		}
		if(area_attr_s!=null&&!area_attr_s.equals(""))
		{
			pageMap.put("area_attr", area_attr_s);
		}
		if(cat_attr_s!=null&&!cat_attr_s.equals("")){
			pageMap.put("area_attr", cat_attr_s);
		}
		pageMap.put("infostate", "1,3");
		//商城地区过滤
		pageMap=super.shopareafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.shopconfigService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		shopconfigList = this.shopconfigService.getList(pageMap);
		shopconfigList=com.rbt.function.CategoryFuc.replaceList(shopconfigList,"");
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
		//搜索店铺名称
		if(shop_name_s!=null&&!shop_name_s.equals(""))
		{
			pageMap.put("shop_name", shop_name_s);
		}
		//搜索联系人
		if(contant_man_s!=null&&!contant_man_s.equals(""))
		{
			pageMap.put("contant_man", contant_man_s);
		}
		if(is_colse_s!=null&&!is_colse_s.equals(""))
		{
			pageMap.put("is_colse", is_colse_s);
		}
		if(info_state_s!=null&&!info_state_s.equals(""))
		{
			pageMap.put("info_state", info_state_s);
		}
		if(area_attr_s!=null&&!area_attr_s.equals(""))
		{
			pageMap.put("area_attr", area_attr_s);
		}
		if(cat_attr_s!=null&&!cat_attr_s.equals(""))
		{
			pageMap.put("area_attr", cat_attr_s);
		}
		pageMap.put("infostate", "0,2");
		//商城地区过滤
		pageMap=super.shopareafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.shopconfigService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		shopconfigList = this.shopconfigService.getList(pageMap);
		shopconfigList=com.rbt.function.CategoryFuc.replaceList(shopconfigList,"");
		return goUrl(AUDITLIST);
	}
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : May 17, 2012 4:14:21 PM
	 * @Method Description : 根据会员标识找出相应的运费价格
	 */
	public void getModePrice() throws IOException{
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		shopconfig=this.shopconfigService.GetByCustId(this.session_cust_id);
		JsonUtil json=new JsonUtil();
		String shopStr = json.object2json(shopconfig);
		out.print(shopStr);		
	}
	
	
	/**
	 * 方法描述：根据主键找出会员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		String id = this.shopconfig.getShop_id();
		if(id==null || id.equals("")){
			shopconfig = new Shopconfig();
		}else{
			shopconfig = this.shopconfigService.get(id);
		}
		if(shopconfig.getArea_attr()!=null)
		{
			area_name=AreaFuc.getAreaNameByMap(shopconfig.getArea_attr());
		}
		if(shopconfig.getCust_id()!=null&&!shopconfig.getCust_id().equals(""))
		{
			cust_name=MemberFuc.getMemberByPk(shopconfig.getCust_id()).getCust_name();
		}
		if(shopconfig.getBusi_mode()!=null)
		{
			bus_model=MemberFuc.getStatusName(shopconfig.getBusi_mode());
		}
		return goUrl(AUDIT);
	}

	/**
	 * @function 功能 审核店铺
	 * @author 创建人 胡惜坤
	 * @date 创建日期 Jul 19, 2011 2:08:39 PM
	 */
	public String auditstate() throws Exception {
		this.shopconfigService.update(shopconfig);
		this.addActionMessage("审核商店设置信息成功！");
		return auditList();
	}
	/**
	 * 方法描述：会员获取商店配置信息
	 * @return
	 * @throws Exception
	 */
	public String bmalllist() throws Exception {
		if(this.session_cust_id!=null&&!session_cust_id.equals(""))
		{
			shopconfig=shopconfigService.GetByCustId(session_cust_id);
			//找出企业经营模式
			Map comMap = new HashMap();
			comMap.put("para_code", "bmall_busi_mode");
			clientStrList = this.commparaService.getList(comMap);
		}
		if(shopconfig!=null && shopconfig.getArea_attr()!=null&&!"".equals(shopconfig.getArea_attr())){
			// 找出地区字段的存入隐藏值中
			backArea(shopconfig.getArea_attr());
		}
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出商店设置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(shopconfig.getCust_id()!=null){
			if(accessControl(shopconfig.getCust_id())){
				return list();
			}
		}
		String id = this.shopconfig.getShop_id();
		if(id==null || id.equals("")){
			 if(shopconfig.getCust_id()!=null&&!shopconfig.getCust_id().equals(""))
			 {
				 shopconfig = this.shopconfigService.GetByCustId(shopconfig.getCust_id());
			 }
			 else {
				 shopconfig = new Shopconfig();
			}
		}else{
			shopconfig = this.shopconfigService.get(id);
		}
		if(shopconfig.getArea_attr()!=null)
		{
			area_name=AreaFuc.getAreaNameByMap(shopconfig.getArea_attr());
		}
		if(shopconfig.getCust_id()!=null&&!shopconfig.getCust_id().equals(""))
		{
			cust_name=MemberFuc.getMemberByPk(shopconfig.getCust_id()).getCust_name();
		}
		if(shopconfig.getBusi_mode()!=null)
		{
			bus_model=MemberFuc.getStatusName(shopconfig.getBusi_mode());
		}
		return goUrl(VIEW);
	}
	

	public String bmallactive() throws Exception{
		String cust_id=this.session_cust_id;
		Map map=new HashMap();
		map.put("cust_id", cust_id);
		shopconfigList=shopconfigService.getList(map);
		Map cmap=new HashMap();
		cmap.put("para_code", "bmall_busi_mode");
		commparaList=commparaService.getList(cmap);
		if(shopconfigList==null || shopconfigList.size()==0){
			return goUrl("activation");
		}else{
			return bmalllist();
		}
		
	}
	
	public String bmallactivation() throws Exception{
		if(this.validateFactory.isRequired(shopconfig.getShop_name())){
			this.addFieldError("shopconfig.shop_name", "店铺名称不能为空");
			return bmallactive();
		}
		setPlatType();
		//批量插入系统菜单栏目
		Map map=new HashMap();
		map.put("cust_id", this.session_cust_id);
		map.put("plat_type", mall_type);
		this.memberchannelService.insertintoMemberchannel(map);
		
		shopconfig.setCust_id(this.session_cust_id);
		shopconfig.setInfo_state("0");
		shopconfig.setIs_colse("0");
		shopconfig.setUser_id(this.session_user_id);
		shopconfigService.insert(shopconfig);

		HttpServletResponse response = getResponse();
		response.sendRedirect("/bmall_Shopconfig_bmallactive.action");
		return "";
	}
	
	/**
	 * @return the ShopconfigList
	 */
	public List getShopconfigList() {
		return shopconfigList;
	}
	/**
	 * @param shopconfigList
	 *  the ShopconfigList to set
	 */
	public void setShopconfigList(List shopconfigList) {
		this.shopconfigList = shopconfigList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(shopconfig == null){
			shopconfig = new Shopconfig();
		}
		String id = this.shopconfig.getShop_id();
		if(!this.validateFactory.isDigital(id)){
			shopconfig = this.shopconfigService.get(id);
		}
	}
	public String getShop_name_s() {
		return shop_name_s;
	}
	public void setShop_name_s(String shop_name_s) {
		this.shop_name_s = shop_name_s;
	}
	public String getContant_man_s() {
		return contant_man_s;
	}
	public void setContant_man_s(String contant_man_s) {
		this.contant_man_s = contant_man_s;
	}
	public String getIs_colse_s() {
		return is_colse_s;
	}
	public void setIs_colse_s(String is_colse_s) {
		this.is_colse_s = is_colse_s;
	}
	public String getInfo_state_s() {
		return info_state_s;
	}
	public void setInfo_state_s(String info_state_s) {
		this.info_state_s = info_state_s;
	}
	public String getCat_attr_s() {
		return cat_attr_s;
	}
	public void setCat_attr_s(String cat_attr_s) {
		this.cat_attr_s = cat_attr_s;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getBus_model() {
		return bus_model;
	}
	public void setBus_model(String bus_model) {
		this.bus_model = bus_model;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public List getClientStrList() {
		return clientStrList;
	}
	public void setClientStrList(List clientStrList) {
		this.clientStrList = clientStrList;
	}
	public List getClientList() {
		return clientList;
	}
	public void setClientList(List clientList) {
		this.clientList = clientList;
	}
	public List getCommparaList() {
		return commparaList;
	}
	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}
	public String getArea_attr_s() {
		return area_attr_s;
	}
	public void setArea_attr_s(String area_attr_s) {
		this.area_attr_s = area_attr_s;
	}

}

