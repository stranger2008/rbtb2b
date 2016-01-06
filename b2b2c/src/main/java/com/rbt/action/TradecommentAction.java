/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: TradecommentAction.java 
 */
package com.rbt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Tradecomment;
import com.rbt.service.ICertificationService;
import com.rbt.service.ITradecommentService;

/**
 * @function 功能 记录交易评价action类
 * @author 创建人 林俊钦
 * @date 创建日期 Sat Nov 26 13:03:51 CST 2011
 */
@Controller
public class TradecommentAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录交易评价对象
	 */
	public Tradecomment tradecomment;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ITradecommentService tradecommentService;
	@Autowired
	public ICertificationService certificationService;
	
	/*
	 * 记录交易评价信息集合
	 */
	public List tradecommentList;	
	
	// 判断是卖家还是买家
	public String ordertype;

	
	
	/**
	 * 方法描述：返回新增记录交易评价页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录交易评价
	 * 
	 * @return
	 * @throws Exception
	 */
	public void insert() throws Exception {		
		//如果不是卖家也不是买家则跳转到列表页
		if(!"sell".equals(ordertype) && !"buy".equals(ordertype)){
			getResponse().sendRedirect("/member_Orderinfo_list.action");
		}			
		// 找出这个月是卖家对买家评论的次数
		Map map=new HashMap();
		map.put("month", "month");
		map.put("com_type", "1");
		map.put("set_cust_id", tradecomment.getSet_cust_id());
		map.put("get_cust_id", tradecomment.getGet_cust_id());		
		int count=this.tradecommentService.getCount(map);
		//因为还有一条是未插入的,所以是要大于设置的好评数-1
		String cfg_com_month= SysconfigFuc.getSysValue("cfg_com_month");
		int com_month=Integer.parseInt(cfg_com_month);		
		if(count>(com_month-1)){
			tradecomment.setIs_score("0");
		}else{
			tradecomment.setIs_score("1");
		}				
		//判断是否已经对该订单评过分,更新还是插入
		Map commMap=new HashMap();
		commMap.put("order_id",tradecomment.getOrder_id());
		commMap.put("set_cust_id",this.session_cust_id);
		List list =this.tradecommentService.getList(commMap);
		Map listMap=new HashMap();//获取
		if(list.size()>0){
			listMap=(HashMap)list.get(0);
			String trade_id="";
			trade_id=listMap.get("trade_id").toString();
			tradecomment.setTrade_id(trade_id);
			this.tradecommentService.update(tradecomment);
			this.addActionMessage("更新交易评价信息成功");	
		}else{
			this.tradecommentService.insert(tradecomment);
			interComment(this.session_cust_id,this.session_user_id);			
			this.addActionMessage("新增交易评价信息成功");	
		}	
		//对评价后加入好评分的信用指数
		this.certificationService.creditChangeNum(this.session_cust_id, 1,tradecomment.getCom_type(),"c","评价成功","");
		//评价成功后跳转到list页面
		if(tradecomment.getCom_way()!=null&&tradecomment.getCom_way().equals("1")){
			getResponse().sendRedirect("/member_Orderinfo_list.action?ordertype=buy");
		}else{
			getResponse().sendRedirect("/member_Orderinfo_list.action?ordertype=sell");
		}	
	}

	
	/**
	 * @Method Description :积分规则设置的方法是否符合设置的规则
	 * @author : 林俊钦
	 * @date : Nov 11, 2011 2:01:09 PM
	 */
	public void interComment(String cust_id,String user_id) {
		// 获取系统变量中积分规则是否开启开关，1关闭，0开启
		String scoreSwitch = SysconfigFuc.getSysValue("cfg_sc_switch");
		// 获取每天的最高上限积分
		String scoreToday = SysconfigFuc.getSysValue("cfg_sc_dayup");
		// 获取评价时应要获得的积分
		String comment_score_num = SysconfigFuc.getSysValue("cfg_comment");
		
		int comment_score=Integer.parseInt(comment_score_num);
		int int_scoreToday = Integer.parseInt(scoreToday);
		int numToday=0;
		// 获取每天会员获取的积分数
		if (cust_id != null && !cust_id.equals("")) {
			Map map = new HashMap();
			map.put("cust_id", cust_id);
			numToday = this.interhistoryService.getInterhistorySumScore(map);
		}
		// 计算当前会员当天所获得的积分数
		if (scoreSwitch.equals("0") && numToday <= int_scoreToday) {
			interhistory(null,comment_score,cust_id,user_id,int_scoreToday);
		}
	}
		
	
	/**
	 * 方法描述：修改记录交易评价信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.tradecommentService.update(tradecomment);
		this.addActionMessage("修改交易评价信息成功");
		return list();
	}
	/**
	 * 方法描述：删除记录交易评价信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.tradecomment.getTrade_id();
		id = id.replace(" ", "");
		this.tradecommentService.delete(id);
		this.addActionMessage("删除交易评价信息成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.tradecommentService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		tradecommentList = this.tradecommentService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录交易评价信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.tradecomment.getTrade_id();
		if(id==null || id.equals("")){
			tradecomment = new Tradecomment();
		}else{
			tradecomment = this.tradecommentService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the TradecommentList
	 */
	public List getTradecommentList() {
		return tradecommentList;
	}
	/**
	 * @param tradecommentList
	 *  the TradecommentList to set
	 */
	public void setTradecommentList(List tradecommentList) {
		this.tradecommentList = tradecommentList;
	}
	
	/**
	 * @return the tradecomment
	 */
	public Tradecomment getTradecomment() {
		return tradecomment;
	}
	/**
	 * @param Tradecomment
	 *            the tradecomment to set
	 */
	public void setTradecomment(Tradecomment tradecomment) {
		this.tradecomment = tradecomment;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(tradecomment == null){
			tradecomment = new Tradecomment();
		}
		String id = this.tradecomment.getTrade_id();
		if(!ValidateUtil.isDigital(id)){
			tradecomment = this.tradecommentService.get(id);
		}
	}

}

