/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: CollectService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.ICollectDao;
import com.rbt.model.Collect;
import com.rbt.service.ICollectService;

/**
 * @function 功能 记录会员商机收藏信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Wed Jul 20 14:27:09 CST 2011
 */
@Service
public class CollectService extends GenericService<Collect,String> implements ICollectService {

	
	ICollectDao collectDao;

	@Autowired
	public CollectService(ICollectDao collectDao) {
		super(collectDao);
		this.collectDao = collectDao;
	}
}

