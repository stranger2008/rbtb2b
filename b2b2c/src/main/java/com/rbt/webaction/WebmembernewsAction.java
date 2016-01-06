/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: WebmembernewsAction.java 
 */
package com.rbt.webaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembernewsService;


/**
 * @function 功能 企业新闻action类
 * @author 创建人 林俊钦
 * @date 创建日期  Nov 4, 2011 10:50:27 AM
 */
@Controller
public class WebmembernewsAction extends WebbaseAction {
	private static final long serialVersionUID = -7688476651091255589L;

	/*
	 * 业务层接口
	 */
	@Autowired
	public IMembernewsService membernewsService;
	@Autowired
	public IMemberService memberService;
	/*
	 * 企业新闻列表
	*/
	public List membernewsList;
	public List newsTopList;
	public List recomList;
	
	/**
	 * @Method Description : 前台资讯信息列表
	 * @author : 林俊钦
	 * @date : Nov 1, 2011 3:11:18 PM
	 */
	public String list() throws Exception {		
        Map pageMap= new HashMap();        
        pageMap.put("news_state", "0");
		// 根据页面提交的条件找出信息总数
		int count = this.membernewsService.getWebMembernewsCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		membernewsList=this.membernewsService.getWebMembernewsList(pageMap);
		//绑定公司推荐企业
		Map recomMap=new HashMap();
		recomMap.put("info_state","1");
		recomMap.put("recommend","1");
		recomMap.put("start","0");
		recomMap.put("limit","10");
		recomMap.put("mem_type","0");//0代表企业
		recomList=this.memberService.getWebMemberList(recomMap);
		//绑定最新企业
		Map topMap=new HashMap();
		topMap.put("info_state","1");
		topMap.put("start","0");
		topMap.put("limit","10");
		topMap.put("mem_type","0");//0代表企业
		newsTopList=this.memberService.getWebMemberList(topMap);
		
		return goUrl("membernewsList");
	}
	
}
