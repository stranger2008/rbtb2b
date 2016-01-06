/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberAction.java 
 */
package com.rbt.webaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.rbt.function.ToolsFuc;
import com.rbt.index.SearchIndex;
import com.rbt.service.ISubjectService;

/**
 * @function 功能 前台专题列表页
 * @author 创建人 林俊钦
 * @date 创建日期 Sep 1, 2011 8:59:52 AM
 */
@Controller
public class WebsubjectAction extends WebbaseAction {

	private static final long serialVersionUID = 7566679645472576462L;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ISubjectService subjectService;
	/*
	 * 专题表信息集合
	 */
	public List recomList;
	public List subjectList;
	public List topList;
	/*
	 * 定义全局变量
	 */
	private String module_type = "subject";

	/**
	 * @Method Description : 绑定前台专题列表
	 * @author : 林俊钦
	 * @date : Nov 1, 2011 3:59:44 PM
	 */
	public String list() throws Exception {
		// 设置网页位置
		super.setPage_position(this.module_type);
		
		//构造list列表搜索条件
		List shList = new ArrayList();
		Sort sort=new  Sort();  
		SortField sf=new  SortField("lu_in_date", SortField.STRING,true);//降序  
		sort.setSort(new  SortField[]{sf});
		// 按分类列表选择列表
		shList = this.normalSearch("cat_attr", cat_id, shList);
		// 按关键字查找列表
		if (!reqKeyword("title",module_type, shList)) {			
			// 按搜索内容
			shList = this.normalSearch("title", searchText, shList);	
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
		}
		// 找通过审核的记录
		shList = normalSearch("info_state","1",shList);
		// 如果搜索内容为空则不搜索
		if (is_souch) {
			SearchIndex si=new SearchIndex(module_type);
			//计算符合条件条数
			int count=si.getCount(shList);
			infoCount=count;
			//lucene的分页插件
			lucenePageTool(count);
			//分类信息分组
			cateList = si.catInfoNum(shList);
			//搜索符合条件的list
			subjectList = si.search(shList, sort , pages_s, pageSize_s);  
			// 替换list表中字段的处理
			subjectList = ToolsFuc.replaceList(subjectList);
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
		// 热门专题绑定
		SearchIndex si=new SearchIndex(module_type);
		List topMapList = new ArrayList();
		topMapList = normalSearch("info_state","1",topMapList);
		Sort sort1=new  Sort();  
		SortField sf1=new  SortField("clicknum", SortField.STRING,true);//降序  
		sort1.setSort(new  SortField[]{sf1});
		topList = si.search(topMapList, sort1 , 1, 10); 
		
		
		// 专题推荐前十条
		SearchIndex si1=new SearchIndex(module_type);
		List recomMapList = new ArrayList();
		recomMapList = normalSearch("info_state","1",recomMapList);
		recomMapList = normalSearch("is_recom","1",recomMapList);
		recomList = si1.search(recomMapList, null , 1, 10); 
		
		return goUrl("subjectList");
	}
}
