/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: VoteService.java 
 */
package com.rbt.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IVoteDao;
import com.rbt.model.Vote;
import com.rbt.service.IVoteService;

@Service
public class VoteService extends GenericService<Vote,String> implements IVoteService {
	
	
	IVoteDao voteDao;

	@Autowired
	public VoteService(IVoteDao voteDao) {
		super(voteDao);
		this.voteDao = voteDao;
	}

	/**
	 * 更新投票次数
	 */
	public void updateVoteCountNum(String pk)
	{
		this.voteDao.updateVoteCountNum(pk);
	}
	/**
	 * 批量更新is_mutli单选多选
	 */
	public void updateis_multiState(List list) {
		this.voteDao.updateis_multiState(list);
	}
}
