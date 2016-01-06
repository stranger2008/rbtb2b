/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.function
 * FileName: SysmenuFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.rbt.service.ISysmenuService;

/**
 * @function 功能  菜单管理
 * @author  创建人 李良林
 * @date  创建日期  2011-09-28
 */

public class SysmenuFuc extends CreateSpringContext{
	@SuppressWarnings("unchecked")
	
	public static List getMenuListByUpmenuid(String up_menu_id,String syscode){
		Map map = new HashMap();
		map.put("up_menu_id", up_menu_id);
		map.put("enabled","0");
		map.put("syscode", syscode);
		List list = getSysmenuList(map);
		return list;
	}
	
	public static List getSysmenuList(Map map){
		ISysmenuService sysmenuService = (ISysmenuService)getContext().getBean("sysmenuService");
		return sysmenuService.getList(map);
	}
	
	
}
