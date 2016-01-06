/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MembercommentService.java 
 */
package com.rbt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IMembercommentDao;
import com.rbt.model.Membercomment;
import com.rbt.service.IMembercommentService;

/**
 * @function 功能 记录会员信息评论信息Service层业务接口实现
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Jul 22 14:21:40 CST 2011
 */
@Service
public class MembercommentService extends GenericService<Membercomment,String> implements IMembercommentService {

	IMembercommentDao membercommentDao;

	@Autowired
	public MembercommentService(IMembercommentDao membercommentDao) {
		super(membercommentDao);
		this.membercommentDao = membercommentDao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.IMembercommentService#updateUpNum(java.lang.String)
	 */
	public void updateUpNum(String pk)
	{
		this.membercommentDao.updateUpNum(pk);
	}
    /*
     * (non-Javadoc)
     * @see com.rbt.service.IMembercommentService#updateDownNum(java.lang.String)
     */
	public void updateDownNum(String pk)
	{
		this.membercommentDao.updateDownNum(pk);
	}

}

