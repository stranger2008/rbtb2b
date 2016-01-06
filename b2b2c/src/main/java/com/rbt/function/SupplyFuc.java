package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.service.ISupplyService;

public class SupplyFuc extends CreateSpringContext{
	//根据分类ID 和 地区 获取推荐供应集合信息
	public static List getSuprecomList(String cat_id,String area_id){
		Map supMap = new HashMap();
		supMap.put("cat_attr", cat_id);
		supMap.put("area_attr", area_id);
		supMap.put("is_recom", "1");
		supMap.put("limit", "4");
		supMap.put("start", "0");
		return getSupplyObj().getList(supMap);
	}
	
	//根据分类ID 和 地区 获取最新供应集合信息
	public static List getSupDateList(String cat_id,String area_id){
		Map supMap = new HashMap();
		supMap.put("cat_attr", cat_id);
		supMap.put("area_attr", area_id);
		supMap.put("limit", "5");
		supMap.put("start", "0");
		String cat_attr = "",cat_name="";
		Map sMap = new HashMap();
		List supdateList = getSupplyObj().getList(supMap);
		if(supdateList != null && supdateList.size()>0){
			for(int i=0;i<supdateList.size();i++){
				sMap = (HashMap) supdateList.get(i);
				if(sMap.get("cat_attr") != null){
					cat_attr = sMap.get("cat_attr").toString();
					cat_name = CategoryFuc.getLastCateName(cat_attr);
				}
				sMap.put("cat_attr", cat_name);
				supdateList.set(i, sMap);
			}
				
		}
		return supdateList;
	}
	
	//从Spring容器中获取供应业务Bean
	public static ISupplyService getSupplyObj(){
		return (ISupplyService)getContext().getBean("supplyService");
	}
}
