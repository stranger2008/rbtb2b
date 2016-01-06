/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: BuyAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.browseengine.bobo.api.BrowseException;
import com.rbt.function.CategoryFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.index.SearchIndex;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberlevelService;

/**
 * @function 功能 前台公司列表页
 * @author 创建人 林俊钦
 * @date 创建日期 Sep 1, 2011 2:20:13 PM
 */

@Controller
public class WebcompanyAction extends WebbaseAction {

	private static final long serialVersionUID = 2750511973949123601L;
	
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMemberlevelService memberlevelService;
	@Autowired
	public ICommparaService commparaService;

	/*
	 * 企业表信息集合
	 */
	public List companyList;
	public List topList;
	public List levelList;
	public List clientList;
	
	/*
	 * 接收前台参数值
	 */
	public String sortby;
	public String client_status;
	public String date_in_date;
	public String level_code;
	public String cat_name;
	
	/*
	 * 公司模块名称
	 */
	public String module_type = "company";

	/**
	 * @Method Description :前台公司列表数据
	 * @author : 林俊钦
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @throws java.io.IOException
	 * @throws com.browseengine.bobo.api.BrowseException
	 * @date : Dec 13, 2011 2:27:33 PM
	 */
	public String list() throws IOException, ParseException, BrowseException {
		// 设置网页位置
		super.setPage_position(this.module_type);
		HttpServletRequest request = getRequest();
		//构造list列表搜索条件
		List shList = new ArrayList();
		
		Sort sort=new  Sort();  
		if (!reqKeyword("cust_name","member", shList)) {
			// 按分类列表选择列表
			shList = normalSearch("cat_attr", cat_id, shList);
			// 按地区选择列表
			shList = normalSearch("area_attr", area_id, shList);
			// 选出是所有会员的信息数据
			shList = normalSearch("level_code", level_code, shList);
			
			// 选出是几天内发布的信息数据
			if(date_in_date!=null && !date_in_date.equals("")){
			   int int_year = Integer.parseInt(date_in_date);
			   String start_month="",end_month="";
			   if(int_year==0){
				   start_month="0";
				   end_month="12";
			   }else if(int_year==1){
				   start_month="12";
				   end_month="10000";
			   }else if(int_year==3){
				   start_month="36";
				   end_month="10000";
			   }else if(int_year==5){
				   start_month="60";
				   end_month="10000";
			   }
			   start_month=fullBit(start_month);
			   end_month=fullBit(end_month);
			   shList = rangeSearch("nMonth",start_month,end_month,shList);
			}
			// 选出是经营模式信息数据
			shList = normalSearch("client_status",client_status,shList);
            
			// 按条件进行排序
			if (sortby != null && sortby.equals("1")) {
				SortField sf=new  SortField("lu_in_date", SortField.STRING,true);//降序
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("2")) {
				SortField sf=new  SortField("lu_in_date", SortField.STRING,false);//升序
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("3")) {
				SortField sf=new  SortField("c_num", SortField.STRING,true);//降序
				sort.setSort(new  SortField[]{sf}); 
			} else if (sortby != null && sortby.equals("4")) {	
				SortField sf=new  SortField("c_num", SortField.STRING,false);//升序
				sort.setSort(new  SortField[]{sf}); 
			} 
			else {
				// ORDER BY level_code DESC,fund_num DESC,member.in_date DESC 
				SortField sf=new  SortField("level_code", SortField.STRING,true);//降序
				SortField sf1=new  SortField("fund_num", SortField.STRING,true);//降序
				SortField sf2=new  SortField("lu_in_date", SortField.STRING,true);//降序
				sort.setSort(new  SortField[]{sf,sf1,sf2}); 
			}
			
			//获取绑定会员类型参数，如果有传参数memtype进来的话，就进行过滤，负责取全部会员显示，
			if(request.getParameter("memtype")!=null&&!request.getParameter("memtype").equals("")){
	            String memtypes=request.getParameter("memtype").toString();
	            shList =normalSearch("mem_type", memtypes, shList);
			}
		}
		// 找通过审核的记录
		shList = normalSearch("info_state","1",shList);
		// 如果搜索内容为空则不搜索
		if (is_souch) {
			//过滤地区未实现
			//pageMap=super.portalAreaFilter(pageMap);
			//过滤地区
			shList=portalAreaFilterList(shList);
			SearchIndex si=new SearchIndex("member");
			//计算符合条件条数
			int count=si.getCount(shList);
			infoCount=count;
			//lucene的分页插件
			lucenePageTool(count);
			//分类信息分组
			cateList = si.catInfoNum(shList);
			
			//地区信息分组
			areaList = si.areaInfoNum(shList);
			//搜索符合条件的list
			companyList = si.search(shList, sort , pages_s, pageSize_s);  
			// 替换list表中字段的处理
			companyList = ToolsFuc.replaceList(companyList);

			
		} 
		// 绑定公司推荐企业
		// 供应推荐信息
		SearchIndex si=new SearchIndex("member");
		List recomList = new ArrayList();
		recomList = normalSearch("info_state","1",recomList);
		recomList = normalSearch("recommend","1",recomList);
		recomList = normalSearch("mem_type","0",recomList);// 0代表企业
		if(level_code!=null&&!"".equals(level_code))
		{
			recomList = normalSearch("level_code",level_code,recomList);// 0代表企业
		}
		topList = si.search(recomList, null , 1, 10); 
		
		
		// 绑定前台经营模式
		Map clientMap = new HashMap();
		clientMap.put("para_code", "client_status");
		clientList = this.commparaService.getWebCommparaList(clientMap);
		// 绑定会员级别
		Map levelMap = new HashMap();
		levelList = this.memberlevelService.getList(levelMap);
		CategoryFuc cf=new CategoryFuc();
		cat_name=cf.getCateName(cat_id);
		return goUrl("companyList");
	}
}
