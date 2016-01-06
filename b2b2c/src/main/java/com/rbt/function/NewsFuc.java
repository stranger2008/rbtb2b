package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.model.News;
import com.rbt.service.INewsService;


public class NewsFuc extends CreateSpringContext{
	public static List getNewsList(String area_id,String cat_id){
		Map newsMap = new HashMap();
		newsMap.put("area_attr", area_id);
		newsMap.put("cat_attr", cat_id);
		newsMap.put("info_state", "1");
		newsMap.put("limit", "10");
		newsMap.put("start", "0");
		String cat_attr = "",cat_name="",area_attr="",area_name="";
		Map sMap = new HashMap();
		List newsList = getNewsObj().getList(newsMap);
		if(newsList != null && newsList.size()>0){
			for(int i=0;i<newsList.size();i++){
				sMap = (HashMap) newsList.get(i);
				if(sMap.get("cat_attr") != null){
					cat_attr = sMap.get("cat_attr").toString();
					cat_name = CategoryFuc.getLastCateName(cat_attr);
				}
				if(sMap.get("area_attr") != null){
					area_attr = sMap.get("area_attr").toString();
					area_name = AreaFuc.getFiresAreaName(area_attr);
				}
				sMap.put("cat_attr", cat_name);
				sMap.put("area_attr", area_name);
				newsList.set(i, sMap);
			}
				
		}
		return newsList;
	}
	//获取资讯的数量
	public static Integer getWebNewsCounts(Map hmap)
	{
	  return getNewsObj().getWebNewsCount(hmap);
	}
	//获取资讯实体
	public static News getnewsmodel(String pk)
	{
	 return getNewsObj().get(pk);
	}
	//获取资讯列表
	public static List getWebNewsList(Map hmap)
	{
	  return getNewsObj().getWebNewsList(hmap);
	}
	//从Spring容器中获取招聘业务Bean
	public static INewsService getNewsObj(){
		return (INewsService)getContext().getBean("newsService");
	}
}
