/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: ProductService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IMembercatDao;
import com.rbt.dao.IProductDao;
import com.rbt.model.Membercat;
import com.rbt.model.Product;
import com.rbt.model.Supply;
import com.rbt.service.IProductService;

/**
 * @function 功能 产品表Service层业务接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Jul 25 17:02:42 CST 2011
 */
@Service
public class ProductService extends GenericService<Product,String> implements IProductService {

	/*
	 * 产品表Dao层接口
	 */
	IProductDao productDao;
	@Autowired
	public ProductService(IProductDao productDao) {
		super(productDao);
		this.productDao = productDao;
	}
	public void updateState(Map map)
	{
		this.productDao.updateState(map);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IProductService#getProductList(java.util.Map)
	 */
	public List getMemberCatList(String custid) {
		return this.productDao.getMemberCatList(custid);
	}

	public void updateClickNum(String pk)
	{
	  this.productDao.updateClickNum(pk);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IProductService#getWebProductCount(java.util.Map)
	 */
	public int getWebProductCount(Map map) {
		return this.productDao.getWebProductCount(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IProductService#getWebProductList(java.util.Map)
	 */
	public List getWebProductList(Map map) {
		return this.productDao.getWebProductList(map);
	}
	/* (non-Javadoc)
	 * @see com.rbt.service.ISupplyService#getCatProductList(java.util.Map)
	 */
	public List getCatProductList(Map map) {
		return this.productDao.getCatProductList(map);
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.IProductService#updateRecommendState(java.lang.String)
	 */
	 public void updateRecommendState(List list)
	 {
		 this.productDao.updateRecommendState(list);
	 }
	 public String insertGetPk(Product t,List objList){
			return this.productDao.insertGetPk(t, objList);
		}
		
	public void update(Product t,List objList,String id){
		this.productDao.update(t,objList,id);
	}
}

