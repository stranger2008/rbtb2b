package com.rbt.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.model.Vote_option;
import com.rbt.service.IVote_optionService;
import com.rbt.dao.IVote_optionDao;

@Service
public class Vote_optionService extends GenericService<Vote_option,String> implements IVote_optionService {
	
	
	IVote_optionDao vote_optionDao;

	@Autowired
	public Vote_optionService(IVote_optionDao vote_optionDao) {
		super(vote_optionDao);
		this.vote_optionDao = vote_optionDao;
	}

	public void update_optioncount(List list) {
		this.vote_optionDao.update_optioncount(list);
	}

}
