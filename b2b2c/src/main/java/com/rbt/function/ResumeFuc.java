package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rbt.model.Resume;
import com.rbt.service.IResumeService;


public class ResumeFuc extends CreateSpringContext{
	public static List getResumeList(String area_id){
		Map resumeMap = new HashMap();
		resumeMap.put("area_attr", area_id);
		resumeMap.put("info_state", "1");
		resumeMap.put("limit", "5");
		resumeMap.put("start", "0");
		String cat_attr = "",cat_name="",area_attr="",area_name="";
		Map sMap = new HashMap();
		List resumeList = getResumeObj().getList(resumeMap);
		if(resumeList != null && resumeList.size()>0){
			for(int i=0;i<resumeList.size();i++){
				sMap = (HashMap) resumeList.get(i);
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
				resumeList.set(i, sMap);
			}
				
		}
		return resumeList;
	}
	
	//根据简历ID获取Resume实体
	public static Resume getResumeByPk(String resume_id){
		return  getResumeObj().get(resume_id);
	}
	
	//从Spring容器中获取招聘业务Bean
	public static IResumeService getResumeObj(){
		return (IResumeService)getContext().getBean("resumeService");
	}
}
