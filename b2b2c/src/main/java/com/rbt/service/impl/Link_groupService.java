/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: Link_groupService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAboutusDao;
import com.rbt.dao.ILink_groupDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Link_group;
import com.rbt.service.IAboutusService;
import com.rbt.service.ILink_groupService;

@Service
public class Link_groupService extends GenericService<Link_group,String> implements ILink_groupService {

	
	ILink_groupDao link_groupDao;

	@Autowired
	public Link_groupService(ILink_groupDao link_groupDao) {
		super(link_groupDao);
		this.link_groupDao = link_groupDao;
	}

	/**
	 * 批量更新List_group name字段
	 */
	public void updateLinkgroup_name(List list) {
		this.link_groupDao.updateLinkgroup_name(list);
	}
	

}
