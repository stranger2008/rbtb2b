/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.service.impl
 * FileName: NewsService.java 
 */

package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IMembercatDao;
import com.rbt.dao.INewsDao;
import com.rbt.model.Job;
import com.rbt.model.Membercat;
import com.rbt.model.News;
import com.rbt.service.INewsService;

/**
 * @function 功能 资讯管理实现类
 * @author 创建人 胡惜坤
 * @date 创建日期 July 8, 2011
 */
@Service
public class NewsService extends GenericService<News,String> implements INewsService {

	/**
	 * 资讯管理dao实现层
	 */
	INewsDao newsDao;
	@Autowired
	public NewsService(INewsDao newsDao) {
		super(newsDao);
		this.newsDao = newsDao;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.INewsService#getMemberNewsList(java.util.Map)
	 */
	public List getMemberNewsList(Map map) {
		return this.newsDao.getMemberNewsList(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.INewsService#deleteNewsInRecycle(java.util.Map)
	 */
	public void deleteNewsInRecycle(String pk) {
		this.newsDao.deleteNewsInRecycle(pk);
	}
	
	public void updateState(Map map)
	{
		this.newsDao.updateState(map);
	}
	public void updateClickNum(String pk)
	{
	  this.newsDao.updateClickNum(pk);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.INewsService#getWebNewsCount(java.util.Map)
	 */
	public int getWebNewsCount(Map map) {
		return this.newsDao.getWebNewsCount(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.INewsService#getWebNewsList(java.util.Map)
	 */
	public List getWebNewsList(Map map) {
		return this.newsDao.getWebNewsList(map);
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.INewsService#getInfoCount(java.util.Map)
	 */
	public int getInfoCount(Map map) {
		return this.newsDao.getInfoCount(map);
	}
	 public String insertGetPk(News t,List objList){
			return this.newsDao.insertGetPk(t, objList);
		}
		
	public void update(News t,List objList,String id){
		this.newsDao.update(t,objList,id);
	}
	/**
	 * @MethodDescribe 方法描述   各地校友按字母排序
	 * @author  创建人  蔡毅存
	 * @date  创建日期  Sep 2, 2011 9:06:31 AM
	 */
	@SuppressWarnings("unchecked")
	public List getNewsalumniList(Map map){
		return this.newsDao.getNewsalumniList(map);
	}
	
	@SuppressWarnings("unchecked")
	public List getalumnicharList(Map map){
		return this.newsDao.getalumnicharList(map);
	}
}
