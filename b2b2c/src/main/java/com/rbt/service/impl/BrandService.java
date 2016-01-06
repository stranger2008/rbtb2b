/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: BrandService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import com.rbt.dao.IBrandDao;
import com.rbt.model.Brand;
import com.rbt.model.Supply;
import com.rbt.service.IBrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @function 功能 品牌信息Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Tue Nov 08 09:15:10 CST 2011
 */
@Service
public class BrandService extends GenericService<Brand,String> implements IBrandService {

	IBrandDao brandDao;

	@Autowired
	public BrandService(IBrandDao brandDao) {
		super(brandDao);
		this.brandDao = brandDao;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBrandService#updateBrandState(java.util.Map)
	 */
	public void updateBrandState(Map map) {
		this.brandDao.updateBrandState(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBrandService#updateIsrecom(java.util.List)
	 */
	public void updateIsrecom(List list) {
		this.brandDao.updateIsrecom(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBrandService#getWebBrandCount(java.util.Map)
	 */
	public int getWebBrandCount(Map map) {
		return this.brandDao.getWebBrandCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IBrandService#getWebBrandList(java.util.Map)
	 */
	public List getWebBrandList(Map map) {
		return this.brandDao.getWebBrandList(map);
	}
	
	public String insertGetPk(Brand t,List objList){
		return this.brandDao.insertGetPk(t, objList);
	}
	
	public void update(Brand t,List objList,String id){
		this.brandDao.update(t,objList,id);
	}

}
