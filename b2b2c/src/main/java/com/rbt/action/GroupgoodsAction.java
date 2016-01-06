/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: GroupgoodsAction.java 
 */
package com.rbt.action;

import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Goods;
import com.rbt.model.Groupgoods;
import com.rbt.model.Supply;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGroupgoodsService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 团购商品表action类
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Mar 16 09:59:24 CST 2012
 */
@Controller
public class GroupgoodsAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 团购商品表对象
	 */
	private Groupgoods groupgoods;
	private Goods goods;
	/*
	 * 团购商品表业务层接口
	 */
	@Autowired
	private IGroupgoodsService groupgoodsService;
	@Autowired
	private IGoodsService goodsService;
	
	public ValidateUtil validate;
	/*
	 * 团购商品表信息集合
	 */
	public CategoryFuc categoryFuc;
	public List groupgoodsList;
	public String groupname;
	public String cust_name_s;
	public String name_s;
	public String ship_s;
	public String state_s;
	public String title_s;

	/**
	 * @return the groupgoods
	 */
	public Groupgoods getGroupgoods() {
		return groupgoods;
	}
	/**
	 * @param Groupgoods
	 *            the groupgoods to set
	 */
	public void setGroupgoods(Groupgoods groupgoods) {
		this.groupgoods = groupgoods;
	}
	
	/**
	 * 方法描述：返回新增团购商品表页面
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
		int count = this.groupgoodsService.getCount(tmap);
		if(controlMsgNum(count,"groupgoods")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 方法描述：新增团购商品表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 用于所属分类的回选开始
		loadCategory();
		//验证分类是选择
		validateCategoryIfSelect();
		// 将处理后的所属分类串注入到buy对象中
		groupgoods.setCat_attr(cat_attr);
		groupgoods.setCust_id(this.session_cust_id);
		groupgoods.setUser_id(this.session_user_id);
		
		if(groupgoods.getContact_way()!=null&&!groupgoods.equals("")){
			String c_way=groupgoods.getContact_way();
			if(validate.isMobile(c_way)&& validate.isTelephone(c_way)){
				this.addFieldError("groupgoods.contact_way", "请输入正确的联系方式");
			}
		}
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return add();
			}
		}

		super.commonValidateField(groupgoods);
		if(super.ifvalidatepass){
			return add();
		}	
		this.groupgoodsService.insert(groupgoods);
		this.addActionMessage("新增团购商品表成功！");
		this.groupgoods = null;
		is_crotorl=true;
		return INPUT;
	}

	/**
	 * 方法描述：修改团购商品表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 将处理后的所属分类串注入到buy对象中
		groupgoods.setCat_attr(cat_attr);
		groupgoods.setCust_id(this.session_cust_id);
		groupgoods.setUser_id(this.session_user_id);
		super.commonValidateField(groupgoods);
		if(super.ifvalidatepass){
			return view();
		}
	
		this.groupgoodsService.update(groupgoods);
		this.addActionMessage("修改团购商品表成功！");
		return list();
	}
	

	
	/**
	 * 方法描述：删除团购商品表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.groupgoods.getGroup_id();
		id = id.replace(" ", "");
		this.groupgoodsService.delete(id);
		this.addActionMessage("删除团购商品表成功！");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		pageMap=setGoodsMap(pageMap);
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.ADMIN_TYPE)) {
			pageMap.put("state_in","1,3");
		}	
		//团购地区过滤
		if("0".equals(cfg_area_group)&&this.session_cust_type.equals(Constants.ADMIN_TYPE)){
			//获取地区定位id
			String area_id = AreaFuc.getAreaidByIpaddr(getRequest());
			pageMap.put("area_attr", area_id);
		}
		//根据页面提交的条件找出信息总数
		int count=this.groupgoodsService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		groupgoodsList = this.groupgoodsService.getList(pageMap);
		groupgoodsList = categoryFuc.replaceList(groupgoodsList, "");
		return goUrl(INDEXLIST);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Mar 19, 2012 11:30:01 AM
	 * @Method Description :审核团购列表
	 */
	public String auditList() throws Exception {
		Map pageMap = new HashMap();	
		pageMap=setGoodsMap(pageMap);
		pageMap.put("state_in","0,2");
		//团购地区过滤
		if("0".equals(cfg_area_group)&&this.session_cust_type.equals(Constants.ADMIN_TYPE)){
			//获取地区定位id
			String area_id = AreaFuc.getAreaidByIpaddr(getRequest());
			pageMap.put("area_attr", area_id);
		}
		//根据页面提交的条件找出信息总数
		int count=this.goodsService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		groupgoodsList = this.groupgoodsService.getList(pageMap);
		groupgoodsList = categoryFuc.replaceList(groupgoodsList, "");
		return goUrl(AUDITLIST);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Mar 19, 2012 12:53:59 PM
	 * @Method Description :审核团购
	 */
	public String audit() throws Exception {
		String id = this.groupgoods.getGroup_id();
		
		if(id==null || id.equals("")){
			groupgoods = new Groupgoods();
		}else{
			groupgoods = this.groupgoodsService.get(id);
		}
		
		if(groupname==null||groupname.equals("")){
			goods=this.goodsService.get(groupgoods.getGoods_id());
			if(goods.getGoods_name()!=null){
				groupname=goods.getGoods_name();
			}			
		}
		
		// 将从数据库中查询的所属分类存入分类隐藏域中
		backCategory(groupgoods.getCat_attr());	
		return goUrl(AUDIT);
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Mar 17, 2012 1:38:24 PM
	 * @Method Description :
	 */
	private Map setGoodsMap(Map pageMap) throws UnsupportedEncodingException{
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		// 搜索会员名称
		if (this.cust_name_s != null && !this.cust_name_s.equals("")) {
			pageMap.put("cust_name_s", this.cust_name_s);
		}
		// 搜索的商品名称
		if (this.name_s != null && !this.name_s.equals("")) {
			pageMap.put("name_s", this.name_s);
		}
		// 搜索的团购标题
		if (this.title_s != null && !this.title_s.equals("")) {
			pageMap.put("title_s", this.title_s);
		}
		// 是否免运费
		if (this.ship_s != null && !this.ship_s.equals("")) {
			pageMap.put("ship_s", this.ship_s);
		}
		// 搜索的状态
		if (this.state_s != null && !this.state_s.equals("")) {
			pageMap.put("state_s", this.state_s);
		}
		// 获取搜索的所属分类
		if (request.getParameter("cat_attr_s") != null) {
			String cat_attr = request.getParameter("cat_attr_s");
			pageMap.put("cat_attr", cat_attr);
		}
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}		
		return pageMap;
	}
	
	/**
	 * 方法描述：根据主键找出团购商品表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(groupgoods.getCust_id()!=null){
			if(accessControl(groupgoods.getCust_id())){
				return list();
			}
		}
		String id = this.groupgoods.getGroup_id();
		if(id==null || id.equals("")){
			groupgoods = new Groupgoods();
		}else{
			groupgoods = this.groupgoodsService.get(id);
		}
		
		if(groupname==null||groupname.equals("")){
			goods=this.goodsService.get(groupgoods.getGoods_id());
			if(goods!=null&&goods.getGoods_name()!=null){
				groupname=goods.getGoods_name();
			}			
		}
		
		// 将从数据库中查询的所属分类存入分类隐藏域中
		backCategory(groupgoods.getCat_attr());	
		return goUrl(VIEW);
	}
	

	
	
	/**
	 * @author : 林俊钦
	 * @date : Mar 19, 2012 1:31:20 PM
	 * @Method Description :审核状态
	 */
	public String auditState() throws Exception {
		String info_state = "", no_reason = "";
		String id = this.groupgoods.getGroup_id();
		if (id == null || id.equals("")) {
			return auditList();
		}
		// 获取数据库对象
		Groupgoods gd = this.groupgoodsService.get(id);
		if (this.groupgoods.getInfo_state() != null && !this.groupgoods.getInfo_state().equals("")) {
			info_state = this.groupgoods.getInfo_state();
			// 设置状态值
			gd.setInfo_state(info_state);
		}
		if (this.groupgoods.getNo_reason() != null) {
			no_reason = this.groupgoods.getNo_reason();
			// 设置拒绝理由
			gd.setNo_reason(no_reason);
		}
		// 更新数据库供应列表
		this.groupgoodsService.update(gd);
		this.addActionMessage("审核团购商品信息成功");
		return auditList();
	}
	
	/**
	 * @return the GroupgoodsList
	 */
	public List getGroupgoodsList() {
		return groupgoodsList;
	}
	/**
	 * @param groupgoodsList
	 *  the GroupgoodsList to set
	 */
	public void setGroupgoodsList(List groupgoodsList) {
		this.groupgoodsList = groupgoodsList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(groupgoods == null){
			groupgoods = new Groupgoods();
		}
		String id = this.groupgoods.getGroup_id();
		if(!this.validateFactory.isDigital(id)){
			groupgoods = this.groupgoodsService.get(id);
		}
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}

}

