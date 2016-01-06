/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IGalleryService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Gallery;
import com.rbt.model.Member;

/**
 * @function 功能 记录图库信息Service层业务接口实现类
 * @author  创建人 蔡毅存
 * @date  创建日期 Mon Jul 25 14:50:37 CST 2011
 */

public interface IGalleryService extends IGenericService<Gallery,String>{
	
	/**
	 * 方法描述：修改记录图库信息
	 * @param gallery
	 */
	public void updateauditGallery(Gallery gallery);

	/**
	 * 方法描述：按照map中的条件找出广告的信息
	 * @param map
	 * @return java.util.List
	 */
	@SuppressWarnings("unchecked")
	public List getGalleryIDList(Map map);

	
	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateisrecom(List list);
	
	/**
	 * @MethodDescribe 方法描述    前台图库数据绑定列表
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 29, 2011 3:32:52 PM
	 */
    public List getWebGalleryList(Map map);
    /**
	 * @MethodDescribe 方法描述    前台找出图库表的记录数
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 29, 2011 3:44:40 PM
	 */
	public int getWebGalleryCount(Map map);
	
	

    public String insertGetPk(Gallery t, List objList);
    
    public void update(Gallery t, List objList, String id);
	
}

