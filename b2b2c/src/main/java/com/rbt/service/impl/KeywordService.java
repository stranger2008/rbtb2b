/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: KeywordService.java 
 */
package com.rbt.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.model.Keyword;
import com.rbt.service.IKeywordService;
import com.rbt.dao.IKeywordDao;

@Service
public class KeywordService extends GenericService<Keyword,String> implements IKeywordService {
	
	IKeywordDao keywordDao;

	@Autowired
	public KeywordService(IKeywordDao keywordDao) {
		super(keywordDao);
		this.keywordDao = keywordDao;
	}

	public void updateKeyNums(Keyword keyword) {
		this.keywordDao.updateKeyNums(keyword);
	}

}
