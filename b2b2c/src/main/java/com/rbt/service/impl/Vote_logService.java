/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: Vote_logService.java 
 */
package com.rbt.service.impl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.model.Vote_log;
import com.rbt.service.IVote_logService;
import com.rbt.dao.IVote_logDao;

@Service
public class Vote_logService extends GenericService<Vote_log,String> implements IVote_logService {
	
	IVote_logDao vote_logDao;

	@Autowired
	public Vote_logService(IVote_logDao vote_logDao) {
		super(vote_logDao);
		this.vote_logDao = vote_logDao;
	}

	public List getVoteoption(Map map) {
		return this.vote_logDao.Viewoption(map);
	}

}
