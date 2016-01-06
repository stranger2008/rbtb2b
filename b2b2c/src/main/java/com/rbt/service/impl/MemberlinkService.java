/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MemberlinkService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IMemberlinkDao;
import com.rbt.model.Memberlink;
import com.rbt.service.IMemberlinkService;

/**
 * @function 功能 企业友情链接信息Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 20 13:16:25 CST 2011
 */
@Service
public class MemberlinkService extends GenericService<Memberlink, String>
		implements IMemberlinkService {

	/*
	 * 企业友情链接信息Dao层接口
	 */
	IMemberlinkDao memberlinkDao;

	@Autowired
	public MemberlinkService(IMemberlinkDao memberlinkDao) {
		super(memberlinkDao);
		this.memberlinkDao = memberlinkDao;
	}

	public void auditMemberlink(Map map) {
		this.memberlinkDao.auditMemberlink(map);
	}

}
