/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: WebbrandAction.java 
 */
package com.rbt.webaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rbt.function.ToolsFuc;
import com.rbt.index.SearchIndex;
import com.rbt.service.IBrandService;

/**
 * @function 功能 前台品牌模块的操作action类
 * @author 创建人 邱景岩
 * @date 创建日期 Nov 10, 2011 9:38:14 AM
 */
@Controller
public class WebbrandAction extends WebbaseAction {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2078958208827090004L;
	
	/*
	 * 业务层接口
	 */
	@Autowired
	public IBrandService brandService;
	
	/*
	 * 列表信息集合
	 */
	public List brandList;
	public List brandrecomList;
	public List brandrankList;
	
	/*
	 * 品牌模块名称
	 */
	private String module_type = "brand";

	public String list() throws Exception {
		// 设置网页位置
		super.setPage_position(this.module_type);
		
		//构造list列表搜索条件
		List shList = new ArrayList();
		// 找通过审核的记录
		shList = normalSearch("info_state","1",shList);
		Sort sort=new  Sort();  
		if (!reqKeyword("title",module_type, shList)) {

			// 按分类列表选择列表
			shList = normalSearch("cat_attr", cat_id, shList);
			// 按地区选择列表
			shList = normalSearch("area_attr", area_id, shList);
			// 搜索标题
			shList =normalSearch("title", searchText, shList);
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
		// 如果搜索内容为空则不搜索
		if (is_souch) {
			//过滤地区未实现
			//pageMap=super.portalAreaFilter(pageMap);
			//过滤地区
			shList=portalAreaFilterList(shList);
			//计算符合条件条数
			SearchIndex si=new SearchIndex(module_type);
			int count=si.getCount(shList);
			infoCount=count;
			//lucene的分页插件
			lucenePageTool(count);
			//分类信息分组
			cateList = si.catInfoNum(shList);
			//地区信息分组
			areaList = si.areaInfoNum(shList);
			//搜索符合条件的list
			brandList = si.search(shList, sort , pages_s, pageSize_s);  
			// 替换list表中字段的处理
			brandList = ToolsFuc.replaceList(brandList);
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
		// 推荐品牌
		SearchIndex si=new SearchIndex(module_type);
		List recomList = new ArrayList();
		recomList = normalSearch("info_state","1",recomList);
		recomList = normalSearch("is_recom","1",recomList);
		brandrecomList = si.search(recomList, null , 1, 6); 
		
		// 品牌搜索排行
		SearchIndex si1=new SearchIndex(module_type);
		List rankList = new ArrayList();
		rankList = normalSearch("info_state","1",rankList);
		Sort sortr=new  Sort();  
		SortField sf=new  SortField("clicknum", SortField.STRING,true);//升序
		sortr.setSort(new  SortField[]{sf}); 
		brandrankList = si1.search(rankList, sortr , 1, 10); 
		
		//绑定当前的和行业	
		return goUrl("brandList");
	}
	

}








