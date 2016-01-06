/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: CategoryattrService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.ICategoryattrDao;
import com.rbt.model.Categoryattr;
import com.rbt.service.ICategoryattrService;

/**
 * @function 功能 产品属性列表Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Tue Jul 19 08:48:08 CST 2011
 */
@Service
public class CategoryattrService  extends GenericService<Categoryattr,String> implements ICategoryattrService {

	/*
	 * 产品属性列表Dao层接口
	 */
	ICategoryattrDao categoryattrDao;

	@Autowired
	public CategoryattrService(ICategoryattrDao categoryattrDao) {
		super(categoryattrDao);
		this.categoryattrDao = categoryattrDao;
	}

	public List getCatAttrList(Map map){
		return this.categoryattrDao.getCatAttrList(map);
	}

	public void deleteAttr_id(String id){
		this.categoryattrDao.deleteAttr_id(id);
	}
	
}

