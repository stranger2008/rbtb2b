/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.function
 * FileName: FilterWordFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rbt.service.IFilterwordService;

/**
 * @function 功能  敏感字过滤
 * @author  创建人 李良林
 * @date  创建日期  2011-09-21
 */
public class FilterWordFuc extends CreateSpringContext{
	
	//获取数据库中的敏感字数据，放入map
	public static Map getFilterMap() {  
		
		Map map = new HashMap();
		
		IFilterwordService filterword_service = (IFilterwordService)CreateSpringContext.getContext().getBean("filterwordService");
		List filterList = filterword_service.getList(new HashMap());
		if(filterList!=null && filterList.size()>0){
			for(Iterator it = filterList.iterator();it.hasNext();){
				HashMap filterMap = (HashMap)it.next();
				String name="",rep_name="";
				if(filterMap.get("name")!=null) name = filterMap.get("name").toString();
				if(filterMap.get("rep_name")!=null) rep_name = filterMap.get("rep_name").toString();
				map.put(name, rep_name);
			}
		}
		return map;
    }   
  
	//根据传入的敏感字替换，返回替换后的关键字
    public static String replaceCheck(Map map,String name) {   
        Set<String> keys = map.keySet();   
        Iterator<String> iter = keys.iterator();   
        while (iter.hasNext()) {   
            String key = iter.next();   
            String value = (String) map.get(key);   
            if (name.contains(key)) {   
                name=name.replace(key, value);//对于符合map中的key值实现替换功能   
                   
            }   
        }   
        return name;   
    }   

}
