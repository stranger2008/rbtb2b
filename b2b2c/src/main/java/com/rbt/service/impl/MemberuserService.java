/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MemberuserService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IMemberuserDao;
import com.rbt.model.Memberuser;
import com.rbt.service.IMemberuserService;

/**
 * @function 功能 用户账号Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Tue Jul 19 09:37:16 CST 2011
 */
@Service
public class MemberuserService extends GenericService<Memberuser,String> implements IMemberuserService {

	/*
	 * 用户账号Dao层接口
	 */
	IMemberuserDao memberuserDao;

	@Autowired
	public MemberuserService(IMemberuserDao memberuserDao) {
		super(memberuserDao);
		this.memberuserDao = memberuserDao;
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberuserService#getMemberuserByusername(java.lang.String)
	 */
	public Memberuser getMemberuserByusername(String username) {
		return this.memberuserDao.getMemberuserByname(username);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberuserService#updatePassword(com.rbt.model.Memberuser)
	 */
	public void updatePassword(Memberuser memberuser) {
		this.memberuserDao.updatePassword(memberuser);
	}

	public void updatePasswdBatch(List list) {
		this.memberuserDao.updatePasswdBatch(list);
	}

	public String insertMemberuser(Memberuser memberuser) {
		return this.memberuserDao.insertMemberuser(memberuser);
	}

}

