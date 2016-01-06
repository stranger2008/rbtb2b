/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: MemberService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IMemberDao;
import com.rbt.model.Member;
import com.rbt.service.IMemberService;

/**
 * @function 功能 会员Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 13 08:48:07 CST 2011
 */
@Service
public class MemberService extends GenericService<Member,String> implements IMemberService {

	/*
	 * 会员Dao层接口
	 */
	IMemberDao memberDao;

	@Autowired
	public MemberService(IMemberDao memberDao) {
		super(memberDao);
		this.memberDao = memberDao;
	}

	/*
	 * 
	 */
	public String insertMember(Member member){
		return this.memberDao.insertMember(member);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberService#updateMemberState(com.rbt.model.Member)
	 */
	public void updateMemberState(Map map) {
		this.memberDao.updateMemberState(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.ISupplyService#getIndexList(java.util.Map)
	 */
	public List getIndexList(Map map) {
		return this.memberDao.getIndexList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberService#getWebMemberCount(java.util.Map)
	 */
	public int getWebMemberCount(Map map) {
		return this.memberDao.getWebMemberCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberService#getWebMemberList(java.util.Map)
	 */
	public List getWebMemberList(Map map) {
		return this.memberDao.getWebMemberList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberService#getWebMemberHomeList(java.util.Map)
	 */
	public List getWebMemberHomeList(Map map) {
		return this.memberDao.getWebMemberHomeList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberService#updateIsrecom(java.util.List)
	 */
	public void updateIsrecom(List list) {
		this.memberDao.updateIsrecom(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IMemberService#updateContactInfo(com.rbt.model.Member)
	 */
	public void updateContactInfo(Member member) {
		this.memberDao.updateContactInfo(member);
	}

	public List getWebMemberModCountList(Map map) {
		return this.memberDao.getWebMemberModCountList(map);
	}

	public void deleteRelate(String id) {
	     this.memberDao.deleteRelate(id);
	}

}
