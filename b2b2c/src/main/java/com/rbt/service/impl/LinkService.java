/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: LinkService.java 
 */
package com.rbt.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.ILinkDao;
import com.rbt.model.Link;
import com.rbt.service.ILinkService;

@Service
public class LinkService extends GenericService<Link,String> implements ILinkService {
	
	ILinkDao linkDao;

	@Autowired
	public LinkService(ILinkDao linkDao) {
		super(linkDao);
		this.linkDao = linkDao;
	}

	/**
	 * 批量更新Link
	 */
	public void updateLinkState(List list) {
		this.linkDao.updateLinkState(list);
	}
}
