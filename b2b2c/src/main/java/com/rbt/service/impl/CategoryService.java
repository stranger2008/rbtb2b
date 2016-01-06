/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: CategoryService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAboutusDao;
import com.rbt.dao.ICategoryDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Category;
import com.rbt.service.ICategoryService;

/**
 * @function 功能 分类信息表Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Tue Jul 12 13:04:58 CST 2011
 */
@Service
public class CategoryService extends GenericService<Category,String> implements ICategoryService {

	/*
	 * 分类信息表Dao层接口
	 */
    ICategoryDao categoryDao;

	@Autowired
	public CategoryService(ICategoryDao categoryDao) {
		super(categoryDao);
		this.categoryDao = categoryDao;
	}




	/* (non-Javadoc)
	 * @see com.rbt.service.ICategoryService#getCategoryIndexList(java.util.Map)
	 */
	public List getAreaCategoryList(Map map) {
		return this.categoryDao.getAreaCategoryList(map);
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.service.ICategoryService#getCategoryIndexList(java.util.Map)
	 */
	public List getTwoAreaCategoryList(Map map) {
		return this.categoryDao.getTwoAreaCategoryList(map);
	}
	
	/* (non-Javadoc)
	 * @see com.rbt.service.ICategoryService#getCategoryIndexList(java.util.Map)
	 */
	public List getCategoryIndexList(Map map) {
		return this.categoryDao.getCategoryIndexList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ICategoryService#getWebCategroyList(java.util.Map)
	 */
	public List getWebCategroyList(Map map) {
		return this.categoryDao.getWebCategroyList(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ICategoryService#getAll()
	 */
	public List getAll() {
		return this.categoryDao.getAll();
	}

	public void updateSort(List list) {
		this.categoryDao.updateSort(list);
	}


	public void updateDisplay(List list) {
		this.categoryDao.updateDisplay(list);
	}
	
}

