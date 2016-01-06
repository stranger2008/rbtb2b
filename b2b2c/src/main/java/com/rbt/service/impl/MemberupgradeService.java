/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MemberupgradeService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IMemberupgradeDao;
import com.rbt.model.Memberupgrade;
import com.rbt.service.IMemberupgradeService;

/**
 * @function 功能 会员升级记录信息Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Fri Jul 29 16:37:21 CST 2011
 */
@Service
public class MemberupgradeService extends GenericService<Memberupgrade, String>
		implements IMemberupgradeService {

	/*
	 * 会员升级记录信息Dao层接口
	 */
	IMemberupgradeDao memberupgradeDao;

	@Autowired
	public MemberupgradeService(IMemberupgradeDao memberupgradeDao) {
		super(memberupgradeDao);
		this.memberupgradeDao = memberupgradeDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberupgradeService#auditMemberupgrade(java.util.Map)
	 */
	public void auditMemberupgrade(Map map) {
		this.memberupgradeDao.auditMemberupgrade(map);
	}

	public Memberupgrade getMemberupgradeByName(Map map) {
		return this.memberupgradeDao.getMemberupgradeByName(map);
	}

}
