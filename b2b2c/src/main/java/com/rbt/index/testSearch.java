package com.rbt.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import com.browseengine.bobo.api.BrowseException;

public class testSearch {
	
	public static void main(String args[]) throws IOException, ParseException, InvalidTokenOffsetsException, BrowseException {
		
		List paraList = new ArrayList();
		
		ParaModel pm = new ParaModel();
		
		/*
		String[] fields = {"title","attr_desc"};
		pm.setSearch_type(Constants.MULTI);
		pm.setFields(fields);
		pm.setSearch_value("2346564772");
		*/

		pm.setSearch_key("title");
		pm.setSearch_type(Constants.NORMAL);
		pm.setSearch_value("同学会");
		
		paraList.add(pm);
		
		
		
		// lucene排序
	    // Sort sort=new  Sort();  
	    // SortField sf=new  SortField("supply_id", SortField.STRING,true);//升序  
		// sort.setSort(new  SortField[]{sf}); 
		
		List list = new SearchIndex("subject").search(paraList, null, 1, 20);
		System.out.print(list.size()+"===========");
		// 循环输出
		// List list = new SearchIndex("categoryattr").search(paraList, null, 0, 0);
		System.out.println("我的查询结果列表是:");
		// System.out.println(list.toString());
		
		for (int i = 0; i < list.size(); i++) {
			HashMap listMap=(HashMap)list.get(i);
			String attr_desc="",cat_attr="",supply_id="",title="",area_attr="";
			if(listMap.get("area_attr")!=null){
				area_attr=listMap.get("area_attr").toString();
			}
			if(listMap.get("cat_attr")!=null){
				cat_attr=listMap.get("cat_attr").toString();
			}
			if(listMap.get("title")!=null){
				title=listMap.get("title").toString();
			}
			if(listMap.get("attr_desc")!=null){
				attr_desc=listMap.get("attr_desc").toString();
			}
			System.out.println(title+"========="+cat_attr+"======"+area_attr+"========"+attr_desc);
		}
		
		//new SearchIndex("supply").catInfoNum(paraList);
		//new SearchIndex("supply").areaInfoNum(paraList,"1111111111");
	}
}
