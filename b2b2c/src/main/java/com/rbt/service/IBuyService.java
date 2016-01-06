/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IBuyService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;
import com.rbt.model.Buy;
import com.rbt.model.Supply;

/**
 * @function 功能 求购表Service层业务接口实现类
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Jul 29 14:52:50 CST 2011
 */

public interface IBuyService extends IGenericService<Buy,String>{
	/**
	 * @MethodDescribe 方法描述 前台求购数据绑定列表
	 * @author 创建人 林俊钦
	 * @date 创建日期 Aug 29, 2011 3:32:52 PM
	 */
	public List getWebBuyList(Map map);

	/**
	 * @MethodDescribe 方法描述 前台找出求购表的记录数
	 * @author 创建人 林俊钦
	 * @date 创建日期 Aug 29, 2011 3:44:40 PM
	 */
	public int getWebBuyCount(Map map);

	public void updateClickNum(String pk);
	
	/**
	 * @MethodDescribe 方法描述    前台求购表数据
	 * @author  创建人  蔡毅存
	 * @date  创建日期  Aug 29, 2011 3:31:13 PM
	 */
    public List getCatBuyList(Map map);
    
	/**
	 * 方法描述：批量修改推荐
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateBuyState(List list);
	
	public String insertGetPk(Buy t, List objList);
	public void update(Buy t, List objList, String id);
}
