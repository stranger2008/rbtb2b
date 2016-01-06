/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: MembercommentDao.java 
 */
package com.rbt.dao.impl;

import org.springframework.stereotype.Repository;
import com.rbt.dao.IMembercommentDao;
import com.rbt.model.Membercomment;

/**
 * @function 功能  记录会员信息评论信息dao层业务接口实现类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Jul 22 14:21:40 CST 2011
 */
@Repository
public class MembercommentDao extends GenericDao<Membercomment,String> implements IMembercommentDao {

	public MembercommentDao() {
		super(Membercomment.class);
	}
	
    /*
     * (non-Javadoc)
     * @see com.rbt.dao.IMembercommentDao#updateUpNum(java.lang.String)
     */
	public void updateUpNum(String pk)
	{
		this.getSqlMapClientTemplate().update("membercomment.updateupnum", pk);
	}
    /*
     * (non-Javadoc)
     * @see com.rbt.dao.IMembercommentDao#updateDownNum(java.lang.String)
     */
	public void updateDownNum(String pk)
	{
		this.getSqlMapClientTemplate().update("membercomment.updatedownnum", pk);
	}

}

