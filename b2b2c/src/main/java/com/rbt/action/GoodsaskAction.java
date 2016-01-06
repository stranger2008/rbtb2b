/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: GoodsaskAction.java 
 */
package com.rbt.action;

import java.util.*;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.model.Goods;
import com.rbt.model.Goodsask;
import com.rbt.model.Member;
import com.rbt.service.ICommparaService;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsaskService;
import com.rbt.service.IMemberService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商品咨询信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Mar 23 11:24:44 CST 2012
 */
@Controller
public class GoodsaskAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录商品咨询信息对象
	 */
	private Goodsask goodsask;
	//商品对象
	private Goods goods;
	//回复人对象
	private Member member;
	//存放商品对应的店铺ID号
	private static String shop_cust_id;
	/*
	 * 记录商品咨询信息业务层接口
	 */
	@Autowired
	private IGoodsaskService goodsaskService;
	@Autowired
	private ICommparaService commparaService;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private IGoodsService goodsService;
	/*
	 * 记录商品咨询信息信息集合
	 */
	public List goodsaskList;
	public List commparaList;
	/*
	 * 查询字段
	 */
	public String goods_name_s;
	public String c_type_s;
	public String start_time_s;
	public String end_time_s;
	public String is_enable_s;
	public String rstart_time_s;
	public String rend_time_s;
	//今日咨询
	public String jinId;
	//待处理咨询
	public String daiId;
	//待处理咨询
	public String yiId;
	/**
	 * @return the goodsask
	 */
	public Goodsask getGoodsask() {
		return goodsask;
	}
	/**
	 * @param Goodsask
	 *            the goodsask to set
	 */
	public void setGoodsask(Goodsask goodsask) {
		this.goodsask = goodsask;
	}
	
	/**
	 * 方法描述：返回新增记录商品咨询信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获取商品咨询类型
		getCommparea();
		return goUrl(ADD);
	}
	

	/**
	 * 方法描述：新增记录商品咨询信息
	 * 
	 */
    public void getCommparea(){
    	Map map=new HashMap();
		map.put("para_code", "c_type");
		commparaList = commparaService.getList(map);
    }
	/**
	 * 方法描述：新增记录商品咨询信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		goodsask.setCust_id(this.session_cust_id);
		goodsask.setUser_id(this.session_user_id);
		goodsask.setIs_enable("0");
		super.commonValidateField(goodsask);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.goodsaskService.insert(goodsask);
		this.addActionMessage("新增记录商品咨询信息成功！");
		this.goodsask = null;
		return add();
	}

	/**
	 * 方法描述：修改记录商品咨询信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		
		if(this.session_cust_id.equals(shop_cust_id)){
			goodsask.setRe_cust_id(this.session_cust_id);
			goodsask.setRe_man(this.session_user_id);
		}
		if(ValidateUtil.isRequired(this.goodsask.getRe_content())){
			this.addFieldError("goodsask.re_content", "回复内容不能为空");
			return view();
		}
		this.goodsaskService.update(goodsask);
		this.addActionMessage("修改记录商品咨询信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录商品咨询信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.goodsask.getTrade_id();
		id = id.replace(" ", "");
		this.goodsaskService.delete(id);
		this.addActionMessage("删除记录商品咨询信息成功！");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		//咨询内容
		getCommparea();
		Map pageMap = new HashMap();

		if (goods_name_s != null && !goods_name_s.equals("")) {
			pageMap.put("goods_name", goods_name_s);
		}
		if (c_type_s != null && !c_type_s.equals("")) {
			pageMap.put("c_type", c_type_s);
		}
		if (start_time_s != null && !start_time_s.equals("")) {
			pageMap.put("start_time", start_time_s);
		}
		if (end_time_s != null && !end_time_s.equals("")) {
			pageMap.put("end_time", end_time_s);
		}
		if (rstart_time_s != null && !rstart_time_s.equals("")) {
			pageMap.put("rstart_time", rstart_time_s);
		}
		if (rend_time_s != null && !rend_time_s.equals("")) {
			pageMap.put("rend_time", rend_time_s);
		}
		if (is_enable_s != null && !is_enable_s.equals("")) {
			pageMap.put("is_enable", is_enable_s);
		}		
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		//搜索今日咨询
		if (jinId != null && !jinId.equals("")) {
			pageMap.put("today", "0");
		}
		//搜索待处理咨询
		if (daiId != null && !daiId.equals("")) {
			pageMap.put("re_date","0");
		}
		
		//商城地区过滤
		pageMap=super.shopareafilter(pageMap);
		
		//根据页面提交的条件找出信息总数
		int count=this.goodsaskService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		goodsaskList = this.goodsaskService.getList(pageMap);
		if(goodsaskList!=null&&goodsaskList.size()>0){
			for(int i=0;i<goodsaskList.size();i++){
				Map valueMap=(Map) goodsaskList.get(i);
				String c_type = "",re_cust_id="";
				//替换咨询类型ID为咨询类型名称
				if(valueMap.get("c_type")!=null){
					c_type = valueMap.get("c_type").toString();
				}
				//替换回复者ID为回复者名称或商铺名称
				if(valueMap.get("re_cust_id")!=null){
					re_cust_id = valueMap.get("re_cust_id").toString();
				}
				Map postMap = new HashMap();
				postMap.put("para_value", c_type);
				postMap.put("para_code", "c_type");
				List commparaList=this.commparaService.getList(postMap);
				String value="",namevalue="";
				if(commparaList!=null&&commparaList.size()>0){
				   Map paramap=(Map)commparaList.get(0);
				   value=paramap.get("para_key").toString();
				}
				if(!"".equals(re_cust_id)){
				    member=memberService.get(re_cust_id);
				}
				if(member!=null){
					namevalue=member.getCust_name();
				}
				valueMap.put("re_cust_id",namevalue);
				valueMap.put("c_type", value);
			}
		}
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录商品咨询信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(goodsask.getCust_id()!=null){
			if(accessControl(goodsask.getCust_id())){
				return list();
			}
		}
		//获取商品咨询类型
		getCommparea();
		String id = this.goodsask.getTrade_id();
		if(id==null || id.equals("")){
			goodsask = new Goodsask();
		}else{
			goodsask = this.goodsaskService.get(id);
		}
		if(goodsask!=null){
			goods=goodsService.get(goodsask.getGoods_id());
			if(goods==null){
				goods = new Goods();
			}
			shop_cust_id=goods.getCust_id();
		}
		
		return goUrl(VIEW);
	}
	
	public String audit() throws Exception {	
		//获取商品咨询类型
		getCommparea();
		String id = this.goodsask.getTrade_id();
		if(id==null || id.equals("")){
			goodsask = new Goodsask();
		}else{
			goodsask = this.goodsaskService.get(id);
		}
		if(goodsask!=null){
			goods=goodsService.get(goodsask.getGoods_id());
			shop_cust_id=goods.getCust_id();
		}
	     return goUrl(AUDIT);
	}
	
	 public String auditList() throws Exception {
		//咨询内容
			getCommparea();
			Map pageMap = new HashMap();

			if (goods_name_s != null && !goods_name_s.equals("")) {
				pageMap.put("goods_name", goods_name_s);
			}
			if (c_type_s != null && !c_type_s.equals("")) {
				pageMap.put("c_type", c_type_s);
			}
			if (start_time_s != null && !start_time_s.equals("")) {
				pageMap.put("start_time", start_time_s);
			}
			if (end_time_s != null && !end_time_s.equals("")) {
				pageMap.put("end_time", end_time_s);
			}
			if (rstart_time_s != null && !rstart_time_s.equals("")) {
				pageMap.put("rstart_time", rstart_time_s);
			}
			if (rend_time_s != null && !rend_time_s.equals("")) {
				pageMap.put("rend_time", rend_time_s);
			}
			if (is_enable_s != null && !is_enable_s.equals("")) {
				pageMap.put("is_enable", is_enable_s);
			}
			//搜索待处理咨询
			if (yiId != null && !yiId.equals("")) {
				pageMap.put("reg","0");
			}
			// 操作者为会员则默认加入搜索条件
			if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			    //加入买家的搜索条件
				pageMap.put("buy_cust_id", this.session_cust_id);
			}
			
			//根据页面提交的条件找出信息总数
			int count=this.goodsaskService.getCount(pageMap);
			
			//分页插件
			pageMap = super.pageTool(count,pageMap);
			
			goodsaskList = this.goodsaskService.getList(pageMap);
			if(goodsaskList!=null&&goodsaskList.size()>0){
				for(int i=0;i<goodsaskList.size();i++){
					Map valueMap=(Map) goodsaskList.get(i);
					String c_type = "",re_cust_id="";
					//替换咨询类型ID为咨询类型名称
					if(valueMap.get("c_type")!=null){
						c_type = valueMap.get("c_type").toString();
					}
					//替换回复者ID为回复者名称或商铺名称
					if(valueMap.get("re_cust_id")!=null){
						re_cust_id = valueMap.get("re_cust_id").toString();
					}
					Map postMap = new HashMap();
					postMap.put("para_value", c_type);
					postMap.put("para_code", "c_type");
					List commparaList=this.commparaService.getList(postMap);
					String value="",namevalue="";
					if(commparaList!=null&&commparaList.size()>0){
					   Map paramap=(Map)commparaList.get(0);
					   value=paramap.get("para_key").toString();
					}
					if(!"".equals(re_cust_id)){
					    member=memberService.get(re_cust_id);
					}
					if(member!=null){
						namevalue=member.getCust_name();
					}
					valueMap.put("re_cust_id",namevalue);
					valueMap.put("c_type", value);
				}
			}
		 return goUrl(AUDITLIST);
	 }
	 
	 public String auditState() throws Exception {
		 return auditList();
	 }
	/**
	 * @return the GoodsaskList
	 */
	public List getGoodsaskList() {
		return goodsaskList;
	}
	/**
	 * @param goodsaskList
	 *  the GoodsaskList to set
	 */
	public void setGoodsaskList(List goodsaskList) {
		this.goodsaskList = goodsaskList;
	}
	
	public List getCommparaList() {
		return commparaList;
	}
	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(goodsask == null){
			goodsask = new Goodsask();
		}
		String id = this.goodsask.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			goodsask = this.goodsaskService.get(id);
		}
	}
	public String getGoods_name_s() {
		return goods_name_s;
	}
	public void setGoods_name_s(String goods_name_s) {
		this.goods_name_s = goods_name_s;
	}
	public String getC_type_s() {
		return c_type_s;
	}
	public void setC_type_s(String c_type_s) {
		this.c_type_s = c_type_s;
	}
	public String getStart_time_s() {
		return start_time_s;
	}
	public void setStart_time_s(String start_time_s) {
		this.start_time_s = start_time_s;
	}
	public String getEnd_time_s() {
		return end_time_s;
	}
	public void setEnd_time_s(String end_time_s) {
		this.end_time_s = end_time_s;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
}

