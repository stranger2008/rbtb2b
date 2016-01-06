/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MemberinboxService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IAboutusDao;
import com.rbt.dao.IMemberinboxDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Memberinbox;
import com.rbt.service.IMemberinboxService;

/**
 * @function 功能 会员收件信息表Service层业务接口实现
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Aug 05 14:23:14 CST 2011
 */
@Service
public class MemberinboxService extends GenericService<Memberinbox,String> implements IMemberinboxService {

	/*
	 * 会员收件信息表Dao层接口
	 */
	IMemberinboxDao memberinboxDao;

	@Autowired
	public MemberinboxService(IMemberinboxDao memberinboxDao) {
		super(memberinboxDao);
		this.memberinboxDao = memberinboxDao;
	}
	
	

	/* (non-Javadoc)
	 * @see com.rbt.service.IMemberinboxService#updateIsdelete(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public void updateIsdelete(List list) {
		this.memberinboxDao.updateIsdelete(list);
	}

}

