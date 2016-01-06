/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: JobService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbt.dao.IJobDao;
import com.rbt.model.Job;
import com.rbt.model.Showinfo;
import com.rbt.service.IJobService;

/**
 * @function 功能 招聘信息Service层业务接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Tue Jul 12 15:29:27 CST 2011
 */
@Service
public class JobService extends GenericService<Job,String> implements IJobService {

	private IJobDao jobDao;
	@Autowired
	public JobService(IJobDao jobDao) {
		super(jobDao);
		this.jobDao = jobDao;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IJobService#getJobMemberCount(java.util.Map)
	 */
	public int getJobMemberCount(Map map) {
		return this.jobDao.getJobMemberCount(map);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.service.IJobService#getJobMemberList(java.util.Map)
	 */
	public List getJobMemberList(Map map) {
		return this.jobDao.getJobMemberList(map);
	}
	public void updateState(Map map)
	{
		this.jobDao.updateState(map);
	}
	public void updateClickNum(String pk)
	{
	  this.jobDao.updateClickNum(pk);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IJobService#getWebJobCount(java.util.Map)
	 */
	public int getWebJobCount(Map map) {
		return this.jobDao.getWebJobCount(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IJobService#getWebJobList(java.util.Map)
	 */
	public List getWebJobList(Map map) {
		return this.jobDao.getWebJobList(map);
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.IJobService#updateRecommendState(java.util.List)
	 */
	 public void updateRecommendState(List list)
	 {
		 this.jobDao.updateRecommendState(list);
	 }
	 public String insertGetPk(Job t,List objList){
			return this.jobDao.insertGetPk(t, objList);
		}
		
	public void update(Job t,List objList,String id){
		this.jobDao.update(t,objList,id);
	}
}

