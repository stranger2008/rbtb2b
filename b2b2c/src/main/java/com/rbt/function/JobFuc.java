package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.service.IJobService;

public class JobFuc extends CreateSpringContext{
	//根据所在地区获取招聘集合信息
	public static List getJobList(String area_id){
		Map jobMap = new HashMap();
		jobMap.put("area_attr", area_id);
		jobMap.put("info_state", "1");
		jobMap.put("limit", "5");
		jobMap.put("start", "0");
		String cat_attr = "",cat_name="",area_attr="",area_name="";;
		Map sMap = new HashMap();
		List jopList = getJobObj().getList(jobMap);
		if(jopList != null && jopList.size()>0){
			for(int i=0;i<jopList.size();i++){
				sMap = (HashMap) jopList.get(i);
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
				jopList.set(i, sMap);
			}
				
		}
		return jopList;
	}
	
	//从Spring容器中获取招聘业务Bean
	public static IJobService getJobObj(){
		return (IJobService)getContext().getBean("jobService");
	}
}
