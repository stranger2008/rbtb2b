/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: LevelinfoService.java 
 */
package com.rbt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.ILevelinfoDao;
import com.rbt.model.Levelinfo;
import com.rbt.service.ILevelinfoService;

/**
 * @function 功能 会员级别信息Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Tue Jul 19 14:31:17 CST 2011
 */
@Service
public class LevelinfoService extends GenericService<Levelinfo, String>
		implements ILevelinfoService {

	/*
	 * 会员级别信息Dao层接口
	 */
	ILevelinfoDao levelinfoDao;

	@Autowired
	public LevelinfoService(ILevelinfoDao levelinfoDao) {
		super(levelinfoDao);
		this.levelinfoDao = levelinfoDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.ILevelinfoService#getLevelinfoByPk(java.lang.String)
	 */
	public List getLevelinfoByTime(String pk) {
		return this.levelinfoDao.getLevelinfoByTime(pk);
	}

}
