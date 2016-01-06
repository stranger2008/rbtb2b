package com.rbt.function;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rbt.common.util.IpSeekerInit;
import com.rbt.service.IAreaService;
import com.rbt.service.IClassifiedService;
public class ClassifiedFuc extends CreateSpringContext{
	@SuppressWarnings("unchecked")
	public static List getClassifiedList(String area_id,String cate_id){
		Map classifiedMap = new HashMap();
		classifiedMap.put("area_attr", area_id);
		classifiedMap.put("cat_attr", cate_id);
		classifiedMap.put("info_state", "1");
		classifiedMap.put("limit", "8");
		classifiedMap.put("start", "0");
		List classifiedList = getClassifieldObj().getWebClassifiedList(classifiedMap);
		return classifiedList;
	}
	public static List getWebClassified(String area_id,String cate_id,String limit){
		Map classifiedMap = new HashMap();
		classifiedMap.put("area_attr", area_id);
		classifiedMap.put("cat_attr", cate_id);
		classifiedMap.put("info_state", "1");
		classifiedMap.put("limit", limit);
		classifiedMap.put("start", "0");
		List classifiedList = getClassifieldObj().getWebClassifiedList(classifiedMap);
		return classifiedList;
	}
	public static List getWebClassified(String area_id,String cate_id,String limit,String isrecommend){
		Map classifiedMap = new HashMap();
		classifiedMap.put("area_attr", area_id);
		classifiedMap.put("cat_attr", cate_id);
		classifiedMap.put("info_state", "1");
		classifiedMap.put("limit", limit);
		classifiedMap.put("start", "0");
		classifiedMap.put("is_recom", isrecommend);
		List classifiedList = getClassifieldObj().getWebClassifiedList(classifiedMap);
		return classifiedList;
	}
	//从Spring容器中获取分类信息业务Bean
	public static IClassifiedService getClassifieldObj(){
		return (IClassifiedService)getContext().getBean("classifiedService");
	}
	
	
}
