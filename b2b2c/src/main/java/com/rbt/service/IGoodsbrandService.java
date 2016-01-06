/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie
 * FileName: IGoodsbrandService.java 
 */
package com.rbt.service;

import java.util.List;
import java.util.Map;

import com.rbt.model.Goodsbrand;

/**
 * @function 功能 商品品牌表Service层业务接口实现类
 * @author  创建人 胡惜坤
 * @date  创建日期 Mon Feb 27 12:42:32 CST 2012
 */

public interface IGoodsbrandService extends IGenericService<Goodsbrand,String>{
	/**
	 * 方法描述：批量修改排序
	 * @param pk
	 * @return java.util.Map
	 */
	public void updatesort_no(List list);
}

