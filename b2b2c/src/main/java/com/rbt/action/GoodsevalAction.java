/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: GoodsevalAction.java 
 */
package com.rbt.action;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.function.AreaFuc;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Goods;
import com.rbt.model.Goodseval;
import com.rbt.model.Member;
import com.rbt.service.IGoodsService;
import com.rbt.service.IGoodsevalService;
import com.rbt.service.IMemberService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录商品评价表信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Mar 27 09:32:47 CST 2012
 */
@Controller
public class GoodsevalAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录商品评价表信息对象
	 */
	private Goodseval goodseval;
	//商品对象
	private Goods goods;
	//客户对象
	private Member member;
	//判断是买家还是卖家
	private String type;
	//保存商铺ID
	private static String shop_cust_id;
	/*
	 * 记录商品评价表信息业务层接口
	 */
	@Autowired
	private IGoodsevalService goodsevalService;
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private IMemberService memberService;
	/*
	 * 记录商品评价表信息信息集合
	 */
	public List goodsevalList;
	/*
	 * 搜索字段
	 */
	public String goods_name_s;
	public String g_eval_s;
	public String is_enable_s;
	public String start_time_s;
	public String end_time_s;
	public String rstart_time_s;
	public String rend_time_s;
	
	//获取评价过期时间
	public String cfg_evalouttime = SysconfigFuc.getSysValue("cfg_evalouttime");
	
	

	/**
	 * @return the goodseval
	 */
	public Goodseval getGoodseval() {
		return goodseval;
	}
	/**
	 * @param Goodseval
	 *            the goodseval to set
	 */
	public void setGoodseval(Goodseval goodseval) {
		this.goodseval = goodseval;
	}
	
	/**
	 * 方法描述：返回新增记录商品评价表信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录商品评价表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		goodseval.setCust_id(this.session_cust_id);
		goodseval.setUser_id(this.session_user_id);
		goodseval.setIs_two("0");
		super.commonValidateField(goodseval);
		if(super.ifvalidatepass){
			return add();
		}
	
		this.goodsevalService.insert(goodseval);
		this.addActionMessage("新增记录商品评价表信息成功！");
		this.goodseval = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录商品评价表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		
		if(this.session_cust_id.equals(shop_cust_id)){
	    goodseval.setExplan_cust_id(this.session_cust_id);
	    goodseval.setExplan_man(this.session_user_id);
		}
		//判断是否是买家，买家不需要评论内容验证
	    if(this.validateFactory.isRequired(goodseval.getExplan_content())){
	    	this.addFieldError("goodseval.explan_content", "回复内容不能为空");
			return view();
	    }
        
		this.goodsevalService.update(goodseval);
		this.addActionMessage("修改记录商品评价表信息成功！");
		return list();
	}
	/**
	 * 方法描述：删除记录商品评价表信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.goodseval.getTrade_id();
		id = id.replace(" ", "");
		this.goodsevalService.delete(id);
		this.addActionMessage("删除记录商品评价表信息成功！");
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
		if (goods_name_s != null && !goods_name_s.equals("")) {
			pageMap.put("goods_name", goods_name_s);
		}
		if (g_eval_s != null && !g_eval_s.equals("")) {
			pageMap.put("g_eval", g_eval_s);
		}
		if (is_enable_s != null && !is_enable_s.equals("")) {
			pageMap.put("is_enable", is_enable_s);
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
		
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		//商城地区过滤
		pageMap=super.shopareafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.goodsevalService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		goodsevalList = this.goodsevalService.getList(pageMap);
		if(goodsevalList!=null&&goodsevalList.size()>0){
			for(int i=0;i<goodsevalList.size();i++){
				Map valueMap=(Map) goodsevalList.get(i);
				String explan_cust_id = "";
				if(valueMap.get("explan_cust_id")!=null){
					explan_cust_id = valueMap.get("explan_cust_id").toString();
					member=memberService.get(explan_cust_id);
				}
				if(member!=null){
					valueMap.put("explan_cust_id", member.getCust_name());
				}
				
			}
		}
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录商品评价表信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		
		HttpServletRequest request = getRequest();
		if (request.getParameter("type") != null) {
			type=request.getParameter("type");
		}
		
		String id = this.goodseval.getTrade_id();
		if(id==null || id.equals("")){
			goodseval = new Goodseval();
		}else{
			goodseval = this.goodsevalService.get(id);
		}
		if(goodseval!=null && goodseval.getGoods_id()!=null && !goodseval.getGoods_id().equals("")){
		    goods=goodsService.get(goodseval.getGoods_id());
		    if(goods!=null && goods.getCust_id()!=null && !goods.getCust_id().equals("")){
		    	shop_cust_id=goods.getCust_id();
		    }
		}
		return goUrl(VIEW);
	}
	
	public String audit() throws Exception {
		//获取评价ID号
		String id = this.goodseval.getTrade_id();
		if(id==null || id.equals("")){
			goodseval = new Goodseval();
		}else{
			goodseval = this.goodsevalService.get(id);
		}
		//获取被评价商品和评价人cust_id
		if(goodseval!=null){
		    goods=goodsService.get(goodseval.getGoods_id());
		    shop_cust_id=goods.getCust_id();
		    //判断已评价的商品是否过期
		    if("0".equals(goodseval.getIs_two())){
			    //获取系统当前时间
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    String ly_time = sdf.format(new Date());
			    //取出上次评价时间
			    String eval_date=goodseval.getEval_date();
			    Date d1 = sdf.parse(ly_time);
			    Date d2 = sdf.parse(eval_date);
			    long diff = d1.getTime() - d2.getTime();
			    long days = diff / (1000 * 60 * 60 * 24);
			    if(Integer.parseInt(cfg_evalouttime)-days<0){
			    	goodseval.setIs_two("1");
			    	goodsevalService.updateGoodsevalIstwo(goodseval);
			    }
		    }
		}
		return goUrl(AUDIT);
	}
	
     public String auditList() throws Exception {
 		Map pageMap = new HashMap();
 		if (goods_name_s != null && !goods_name_s.equals("")) {
 			pageMap.put("goods_name", goods_name_s);
 		}
 		if (g_eval_s != null && !g_eval_s.equals("")) {
 			pageMap.put("g_eval", g_eval_s);
 		}
 		if (is_enable_s != null && !is_enable_s.equals("")) {
 			pageMap.put("is_enable", is_enable_s);
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
 		
 		// 操作者为会员则默认加入搜索条件
 		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
 		    //加入买家的搜索条件
 			pageMap.put("buy_cust_id", this.session_cust_id);
 		}
 		
 		//根据页面提交的条件找出信息总数
 		int count=this.goodsevalService.getCount(pageMap);
 		
 		//分页插件
 		pageMap = super.pageTool(count,pageMap);
 		
 		goodsevalList = this.goodsevalService.getList(pageMap);
 		if(goodsevalList!=null&&goodsevalList.size()>0){
 			for(int i=0;i<goodsevalList.size();i++){
 				Map valueMap=(Map) goodsevalList.get(i);
 				String explan_cust_id = "";
 				if(valueMap.get("explan_cust_id")!=null){
 					explan_cust_id = valueMap.get("explan_cust_id").toString();
 					member=memberService.get(explan_cust_id);
 				}
 				if(member!=null){
 					valueMap.put("explan_cust_id", member.getCust_name());
 				}
 				
 			}
 		}
 		return goUrl(AUDITLIST);
		
	}
     
     public String auditState() throws Exception {

 		if(this.session_cust_id.equals(shop_cust_id)){
 	    goodseval.setExplan_cust_id(this.session_cust_id);
 	    goodseval.setExplan_man(this.session_user_id);
 		}
 		goodseval.setIs_two("1");
 		this.goodsevalService.update(goodseval);
 		this.addActionMessage("修改记录商品评价表信息成功！");
    	 return auditList();
     }
	/**
	 * @return the GoodsevalList
	 */
	public List getGoodsevalList() {
		return goodsevalList;
	}
	/**
	 * @param goodsevalList
	 *  the GoodsevalList to set
	 */
	public void setGoodsevalList(List goodsevalList) {
		this.goodsevalList = goodsevalList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(goodseval == null){
			goodseval = new Goodseval();
		}
		String id = this.goodseval.getTrade_id();
		if(!this.validateFactory.isDigital(id)){
			goodseval = this.goodsevalService.get(id);
		}
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}

