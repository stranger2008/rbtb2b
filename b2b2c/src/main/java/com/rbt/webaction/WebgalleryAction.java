/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: GalleryAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.browseengine.bobo.api.BrowseException;
import com.rbt.function.ToolsFuc;
import com.rbt.index.SearchIndex;
import com.rbt.service.IGalleryService;

/**
 * @function 功能 前台图库列表页
 * @author 创建人 林俊钦
 * @date 创建日期 Sep 1, 2011 5:34:10 PM
 */
@Controller
public class WebgalleryAction extends WebbaseAction {

	private static final long serialVersionUID = 7320999345581286958L;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IGalleryService galleryService;
	
	/*
	 * 图库表信息集合
	 */
	public List galleryList;
	public List galleryTopList;
	public List galleryRecList;
	
	/*
	 * 图库模块名称
	 */
	private String module_type = "gallery";

	/**
	 * @Method Description : 图库前台的列表
	 * @author : 林俊钦
	 * @throws com.browseengine.bobo.api.BrowseException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @throws java.io.IOException
	 * @date : Nov 1, 2011 4:07:25 PM
	 */
	public String list() throws IOException, ParseException, BrowseException {
		// 设置网页位置
		super.setPage_position(this.module_type);
		
		//构造list列表搜索条件
		List shList = new ArrayList();
		// 找通过审核的记录
		shList = normalSearch("info_state","1",shList);
		Sort sort=new  Sort();  
		if (!reqKeyword("title",module_type, shList)) {
			// 按分类列表选择列表
			shList = this.normalSearch("cat_attr", cat_id, shList);
			// 按地区选择列表
			shList = this.normalSearch("area_attr", area_id, shList);
			// 搜索内容
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
			SortField sf=new  SortField("lu_in_date", SortField.STRING,true);//降序
			sort.setSort(new  SortField[]{sf}); 

		}
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
			galleryList = si.search(shList, sort , pages_s, pageSize_s);  
			// 替换list表中字段的处理
			galleryList = ToolsFuc.replaceList(galleryList);
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
		// 图库排行版
		SearchIndex si=new SearchIndex(module_type);
		List topMapList = new ArrayList();
		topMapList = normalSearch("info_state","1",topMapList);
		Sort sort1=new  Sort();  
		SortField sf1=new  SortField("clicknum", SortField.STRING,true);//降序
		sort1.setSort(new  SortField[]{sf1}); 
		galleryTopList = si.search(topMapList, sort1 , 1, 10); 
		
		
		// 推荐图库
		List recomList = new ArrayList();
		SearchIndex si1=new SearchIndex(module_type);
		recomList = normalSearch("info_state","1",recomList);
		recomList = normalSearch("is_recom","1",recomList);
		galleryRecList = si1.search(recomList, null , 1, 10); 
		
		
		return goUrl("galleryList");
	}
	

	
}






