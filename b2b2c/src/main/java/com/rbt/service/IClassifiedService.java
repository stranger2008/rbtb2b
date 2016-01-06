/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IClassifiedService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;
import com.rbt.model.Classified;

/**
 * @function 功能 记录动态分类信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Fri Oct 14 08:59:55 CST 2011
 */

public interface IClassifiedService extends IGenericService<Classified,String>{

	/**
	 * 方法描述：修改信息
	 * @param Classified
	 */
	public void updateauditClassified(Classified classified);
	
	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateisrecom(List list);
	/**
	 * 方法描述：按照map中的条件找出记录动态分类信息的数量
	 * 
	 * @param map
	 * @return int
	 */
	public int getWebClassifiedCount(Map map);

	/**
	 * 方法描述：按照map中的条件找出记录动态分类信息信息列表
	 * 
	 * @param map
	 * @return java.util.List
	 */
	public List getWebClassifiedList(Map map);
	
	/**
	 * 方法描述：分类按照map中的条件找出记录动态分类信息信息列表
	 * 
	 * @param map
	 * @return java.util.List
	 */
	public List getCatClassifiedList(Map map);
	
	/**
	 * 方法描述：浏览次数累加
	 * 
	 * @param map
	 * @return java.util.List
	 */
	public void updateclicknum(String info_id);
	
	/**
	 * 方法描述：分类按照map中的条件找信息分类列表
	 * 
	 * @param map
	 * @return java.util.List
	 */
	public List getSearchclassList(Map map);
	/**
	 * 方法描述：插入infoattr表
	 * 
	 * @param map
	 * @return java.util.List
	 */
	public String insertGetPk(Classified t, List objList);
	
	public void update(Classified t, List objList, String id);
}

