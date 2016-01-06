/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MemberlevelService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IMemberlevelDao;
import com.rbt.model.Memberlevel;
import com.rbt.service.IMemberlevelService;

/**
 * @function 功能 会员级别配置Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 13 15:08:05 CST 2011
 */
@Service
public class MemberlevelService extends GenericService<Memberlevel, String>
		implements IMemberlevelService {

	/*
	 * 会员级别配置Dao层接口
	 */
	IMemberlevelDao memberlevelDao;

	@Autowired
	public MemberlevelService(IMemberlevelDao memberlevelDao) {
		super(memberlevelDao);
		this.memberlevelDao = memberlevelDao;
	}

}
