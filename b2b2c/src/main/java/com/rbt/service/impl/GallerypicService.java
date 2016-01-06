/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: GallerypicService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IGallerypicDao;
import com.rbt.model.Gallerypic;
import com.rbt.service.IGallerypicService;

/**
 * @function 功能 记录图库图片信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Tue Jul 26 10:31:40 CST 2011
 */
@Service
public class GallerypicService extends GenericService<Gallerypic,String> implements IGallerypicService {

	IGallerypicDao gallerypicDao;

	@Autowired
	public GallerypicService(IGallerypicDao gallerypicDao) {
		super(gallerypicDao);
		this.gallerypicDao = gallerypicDao;
	}
}

