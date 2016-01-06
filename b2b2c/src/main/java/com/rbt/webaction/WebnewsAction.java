/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rbt.function.CategoryFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.index.SearchIndex;
import com.rbt.service.INewsService;

/**
 * @function 功能 前台资讯列表页
 * @author 创建人 林俊钦
 * @date 创建日期 Sep 1, 2011 8:59:52 AM
 */
@Controller
public class WebnewsAction extends WebbaseAction {

	private static final long serialVersionUID = -176840890310198868L;
	/*
	 * 业务层接口
	 */
	@Autowired
	public INewsService newsService;
	/*
	 * 资讯表信息集合
	 */
	public List newsTopList;
	public List newList;
	public List recomList;
	
	/*
	 * 接收前台参数值
	 */
	public String cateNameString;
	
	/*
	 * 资讯模块名称
	 */
	private String module_type = "news";

	/**
	 * @Method Description : 前台资讯信息列表
	 * @author : 林俊钦
	 * @date : Nov 1, 2011 3:11:18 PM
	 */
	public String list() throws Exception {
		// 设置网页位置
		super.setPage_position(this.module_type);
		
		//构造list列表搜索条件
		List shList = new ArrayList();
		Sort sort=new  Sort();  
		if (!reqKeyword("title",module_type, shList)) {
			// 按分类列表选择列表			
			shList = this.normalSearch("cat_attr", cat_id, shList);
			// 按搜索内容选择列表
			shList = this.normalSearch("title", searchText, shList);
			// 按地区选择列表
			shList = this.normalSearch("area_attr", area_id, shList);
			// 分类属性的搜索
			if(attrString!=null && !attrString.equals("")){
				String attrs[] = attrString.split(",");
				for(int i=0;i<attrs.length;i++){
					if(attrs[i]!=null && !attrs[i].equals("")){
						String vals[] = attrs[i].split("\\|");
						if(vals!=null && vals.length>1 && !vals[1].equals("none")){
							shList = normalSearch("attr_desc",vals[1],shList);
						}
					}
				}
			}
			SortField sf=new  SortField("lu_in_date", SortField.STRING,true);//降序  
			sort.setSort(new  SortField[]{sf}); 
		}
		// 找通过审核的记录
		shList = normalSearch("info_state","1",shList);
		cateNameString = CategoryFuc.getCateName(cat_id);
		// 如果搜索内容为空则不搜索
		if (is_souch) {
			//过滤地区未实现
			//pageMap=super.portalAreaFilter(pageMap);
			//过滤地区
			shList=portalAreaFilterList(shList);
			SearchIndex si=new SearchIndex(module_type);
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
			newList = si.search(shList, sort , pages_s, pageSize_s);  
			if(cateList!=null &&cateList.size()==0){
				if(cat_id!=null && !cat_id.equals("")){
					//分类属性信息开始
					SearchIndex attrsi=new SearchIndex("categoryattr");
					List aList = new ArrayList();
					aList = normalSearch("cat_attr",cat_id,aList);
					aList = normalSearch("attr_type","2",aList);
					aList = normalSearch("is_must","1",aList);
					attrList = attrsi.search(aList,null,0,0);
					//分类属性信息结束
					//分类属性值信息开始
					SearchIndex attrvalue=new SearchIndex("attrvalue");
					List vList = new ArrayList();
					vList = normalSearch("cat_attr",cat_id,vList);
					vList = normalSearch("attr_type","2",vList);
					vList = normalSearch("is_must","1",vList);
					attrvalueList = attrvalue.search(aList,null,0,0);
					//分类属性值信息结束
				}
			}
		}
		
		// 绑定前台热门资讯
		SearchIndex si=new SearchIndex(module_type);
		List topList = new ArrayList();
		topList = normalSearch("info_state","1",topList);
		Sort sort2=new  Sort();  
		SortField sf2=new  SortField("clicknum", SortField.STRING,true);//降序  
		sort2.setSort(new  SortField[]{sf2}); 
		newsTopList = si.search(topList, sort2 , 1, 10); 
		
		// 绑定前台推荐资讯
		SearchIndex si1=new SearchIndex(module_type);
		List reList = new ArrayList();
		reList = normalSearch("info_state","1",reList);
		Sort sort1=new  Sort();  
		SortField sf1=new  SortField("lu_in_date", SortField.STRING,true);//降序  
		sort1.setSort(new  SortField[]{sf1}); 
		reList = normalSearch("news_attr","c",reList);
		recomList = si1.search(reList, sort1 , 1, 10); 
		return goUrl("newsList");
	}
	
	//ajax获取委托求职、委托招聘说明
	public void getexec() throws IOException{
		//AJAX获取操作获取运费
		String content = "";
		
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String cat_id = request.getParameter("cat_id");
		Map map = new HashMap();
		map.put("cat_attr", cat_id);
		List anewsList = newsService.getList(map);
		if(anewsList!=null){
			Map newMap = new HashMap();
			 newMap=(HashMap)anewsList.get(0);
			 if(!"".equals(newMap.get("content"))){
				 content = newMap.get("content").toString();
			 }
		}
		out.write(content); 
		
	}
}
