/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao
 * FileName: IGoodsbrandDao.java 
 */
package com.rbt.dao;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodsbrand;

/**
 * @function 功能 商品品牌表dao层业务接口
 * @author  创建人胡惜坤
 * @date  创建日期 Mon Feb 27 12:42:32 CST 2012
 */

public interface IGoodsbrandDao extends IGenericDao<Goodsbrand,String>{
	/**
	 * 方法描述：批量修改排序
	 * @param pk
	 * @return java.util.Map
	 */
	@SuppressWarnings("unchecked")
	public void updateGoodsbrand_sort(List lists);
}

