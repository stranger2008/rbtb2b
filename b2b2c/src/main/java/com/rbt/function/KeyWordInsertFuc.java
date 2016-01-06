/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.filter
 * FileName: KeyWordFilter.java
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.rbt.model.Keyword;
import com.rbt.service.IKeywordService;


/**
 * @function 功能  将搜索的词插入数据库
 * @author  创建人  林俊钦
 * @date  创建日期  Sep 22, 2011 3:40:04 PM
 */
public class KeyWordInsertFuc extends CreateSpringContext {
	/**
	 * @MethodDescribe 方法描述    将关键字插入数据库
	 * @author  创建人  林俊钦
	 * @date  创建日期  Sep 22, 2011 3:39:58 PM
	 */
	public static void wdInsert(String keyword,String modtype){
		//是否开启关键字插队功能 0:是 1：否
		String keyword_switch = SysconfigFuc.getSysValue("cfg_autokeyword");
		if(keyword_switch.equals("0")){
			IKeywordService keywordService = (IKeywordService)getContext().getBean("keywordService");
			Map map=new HashMap();
			map.put("keyword", keyword);
			map.put("module_type", modtype);
			List<Map<String,String>> list=keywordService.getList(map);
			Keyword word=new Keyword();
			//如果关键字已存在表中，就加一，否则就建立新的关键字
			if(list!=null&&list.size()>0){
                  String key_id="";
                  Map listMap = (HashMap)list.get(0);
                  if(listMap.get("key_id")!=null){
                	  key_id=listMap.get("key_id").toString();
                  }
                  word.setKey_id(key_id);
                  keywordService.updateKeyNums(word);
			}else{
				word.setKey_name(keyword);
				word.setModule_type(modtype);
				word.setNum("1");
				keywordService.insert(word);
			}
		}    	
    }
  
}
