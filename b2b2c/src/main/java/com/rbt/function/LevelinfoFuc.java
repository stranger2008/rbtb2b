/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.function
 * FileName: LevelinfoFuc.java 
 */
package com.rbt.function;

import com.rbt.model.Levelinfo;
import com.rbt.service.ILevelinfoService;

/**
 * @function 功能 根据会员级别信息对象
 * @author  创建人 邱景岩
 * @date  创建日期 Feb 23, 2012 10:05:00 AM
 */
public class LevelinfoFuc extends CreateSpringContext{
	
	//根据cust_id获取级别信息对象
	public static Levelinfo getLevelinfoById(String cust_id){
		return getLevelinfoObj().get(cust_id);
	}
	
	//从Spring容器中获取招聘业务Bean
	public static ILevelinfoService getLevelinfoObj(){
		return (ILevelinfoService)getContext().getBean("levelinfoService");
	}
}
