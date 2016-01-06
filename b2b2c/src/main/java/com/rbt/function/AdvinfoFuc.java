/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.function
 * FileName: AdvinfoFuc.java 
 */
package com.rbt.function;

import java.util.HashMap;
import java.util.List;

import com.rbt.model.Advpos;
import com.rbt.service.IAdvinfoService;
import com.rbt.service.IAdvposService;

/**
 * @function 功能  前台广告显示
 * @author  创建人 李良林
 * @date  创建日期  2011-09-22
 */
public class AdvinfoFuc extends CreateSpringContext{
	
	//根据广告位标识找出该广告位下的广告
	@SuppressWarnings("unchecked")
	public static List getDisplayAdv(String pos_id,String id,String type){
		IAdvinfoService advinfoService = (IAdvinfoService)getContext().getBean("advinfoService");
		HashMap map = new HashMap();
		map.put("pos_id", pos_id);
		if(!id.equals("")&&"area".equals(type)){
			map.put("area_attr", id);
		}
		if(!id.equals("")&&"cate".equals(type)){
			map.put("cat_attr", id);
		}
		//广告状态：0 已审核 1：未审核
		map.put("adv_state", "0");
		//取出正在播放的广告信息
		map.put("isshow", "0");
		return advinfoService.getList(map);
	}
	
	//取广告位信息
	public static Advpos getAdvposByPk(String pos_id){
		IAdvposService advposService = (IAdvposService)getContext().getBean("advposService");
		return advposService.get(pos_id);
	}
	
}