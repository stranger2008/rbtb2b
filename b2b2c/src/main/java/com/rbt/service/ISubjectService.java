/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: ISubjectService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Ask;
import com.rbt.model.Subject;

/**
 * @function 功能 专题信息Service层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:19:02 CST 2011
 */

public interface ISubjectService extends IGenericService<Subject, String> {

	/**
	 * 方法描述：批量修改推荐
	 * 
	 * @param map
	 * @return java.util.List
	 */
	public void updateIsrecom(List list);

	/**
	 * @MethodDescribe 方法描述 前台的专题绑定
	 * @author 创建人 林俊钦
	 * @date 创建日期 Sep 2, 2011 1:44:24 PM
	 */
	@SuppressWarnings("unchecked")
	public List getWebSubjectList(Map map);

	/**
	 * @MethodDescribe 方法描述 前台的专题绑定的数据条数
	 * @author 创建人 林俊钦
	 * @date 创建日期 Sep 2, 2011 1:44:24 PM
	 */
	@SuppressWarnings("unchecked")
	public int getWebSubjectCount(Map map);
	
    public String insertGetPk(Subject t, List objList);
    
    public void update(Subject t, List objList, String id);
}
