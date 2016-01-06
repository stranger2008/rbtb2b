/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: ResumeService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IMembercatDao;
import com.rbt.dao.IResumeDao;
import com.rbt.model.Membercat;
import com.rbt.model.Product;
import com.rbt.model.Resume;
import com.rbt.service.IResumeService;

/**
 * @function 功能 简历信息Service层业务接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Wed Jul 13 16:14:17 CST 2011
 */
@Service
public class ResumeService extends GenericService<Resume,String> implements IResumeService {

	/*
	 * 简历信息Dao层接口
	 */
    IResumeDao resumeDao;
	@Autowired
	public ResumeService(IResumeDao resumeDao) {
		super(resumeDao);
		this.resumeDao = resumeDao;
	}
	
	public void updateState(Map map)
	{
		this.resumeDao.updateState(map);
	}
	public void updateClickNum(String pk)
	{
	  this.resumeDao.updateClickNum(pk);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IResumeService#getWebResumeCount(java.util.Map)
	 */
	public int getWebResumeCount(Map map) {
		return this.resumeDao.getWebResumeCount(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IResumeService#getWebResumeList(java.util.Map)
	 */
	public List getWebResumeList(Map map) {
		return this.resumeDao.getWebResumeList(map);
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.IResumeService#updateRecommendState(java.util.List)
	 */
	 public void updateRecommendState(List list)
	 {
		 this.resumeDao.updateRecommendState(list);
	 }
	 public String insertGetPk(Resume t,List objList){
			return this.resumeDao.insertGetPk(t, objList);
		}
		
	public void update(Resume t,List objList,String id){
		this.resumeDao.update(t,objList,id);
	}

}

