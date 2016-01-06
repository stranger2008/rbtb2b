/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: SysuserService.java 
 */

package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.ISysuserDao;
import com.rbt.model.Sysmenu;
import com.rbt.model.Sysuser;
import com.rbt.service.ISysuserService;

/**
 * @function 功能  系统管理员业务实现类
 * @author  创建人 李良林
 * @date  创建日期  Jun 13, 2011
 */
@Service
public class SysuserService extends GenericService<Sysuser,String> implements ISysuserService {
	
	ISysuserDao sysuserDao;
	@Autowired
	public SysuserService(ISysuserDao sysuserDao) {
		super(sysuserDao);
		this.sysuserDao = sysuserDao;
	}

	public void updateSysuserState(List list) {
		this.sysuserDao.updateSysuserState(list);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ISysuserService#getMsgCount(java.util.Map)
	 */
	public List getMsgCount(Map map) {
		return this.sysuserDao.getMsgCount(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.ISysuserService#updatelaststate(com.rbt.model.Sysuser)
	 */
	public void updatelaststate(Sysuser sysuser) {
		this.sysuserDao.updatelaststate(sysuser);		
	}

}
