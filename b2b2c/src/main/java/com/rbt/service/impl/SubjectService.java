/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: SubjectService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.ISubjectDao;
import com.rbt.model.Subject;
import com.rbt.service.ISubjectService;

/**
 * @function 功能 专题信息Service层业务接口实现
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:19:02 CST 2011
 */
@Service
public class SubjectService extends GenericService<Subject, String> implements
		ISubjectService {

	/*
	 * 专题信息Dao层接口
	 */
	ISubjectDao subjectDao;

	@Autowired
	public SubjectService(ISubjectDao subjectDao) {
		super(subjectDao);
		this.subjectDao = subjectDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.ISubjectService#getWebSubjectCount(java.util.Map)
	 */
	public int getWebSubjectCount(Map map) {
		return this.subjectDao.getWebSubjectCount(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.ISubjectService#getWebSubjectList(java.util.Map)
	 */
	public List getWebSubjectList(Map map) {
		return this.subjectDao.getWebSubjectList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.ISubjectService#updateSubjectState(java.util.List)
	 */
	public void updateIsrecom(List list) {
		this.subjectDao.updateIsrecom(list);
	}

	public String insertGetPk(Subject t, List objList) {
		return this.subjectDao.insertGetPk(t, objList);
	}

	public void update(Subject t, List objList, String id) {
		this.subjectDao.update(t, objList, id);
	}



}
