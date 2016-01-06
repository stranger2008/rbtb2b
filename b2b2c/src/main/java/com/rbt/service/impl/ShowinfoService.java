/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.servie.impl
 * FileName: ExhibitionService.java 
 */
package com.rbt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbt.dao.IMembercatDao;
import com.rbt.dao.IShowinfoDao;
import com.rbt.model.Product;
import com.rbt.model.Resume;
import com.rbt.model.Showinfo;
import com.rbt.service.IShowinfoService;

/**
 * @function 功能 展会表Service层业务接口实现
 * @author 创建人 胡惜坤
 * @date 创建日期 Thu Jul 28 09:17:39 CST 2011
 */
@Service
public class ShowinfoService extends GenericService<Showinfo,String> implements IShowinfoService {

	/*
	 * 展会表Dao层接口
	 */
	IShowinfoDao showinfoDao;
	@Autowired
	public ShowinfoService(IShowinfoDao showinfoDao) {
		super(showinfoDao);
		this.showinfoDao = showinfoDao;
	}

	public void updateState(Map map) {
		this.showinfoDao.updateState(map);
	}
	public void updateClickNum(String pk)
	{
	  this.showinfoDao.updateClickNum(pk);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IshowinfoService#getWebshowinfoCount(java.util.Map)
	 */
	public int getWebShowinfoCount(Map map) {
		return this.showinfoDao.getWebShowinfoCount(map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.service.IshowinfoService#getWebshowinfoList(java.util.Map)
	 */
	public List getWebShowinfoList(Map map) {
		return this.showinfoDao.getWebShowinfoList(map);
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.service.IshowinfoService#updateRecommendState(java.util.List)
	 */
	 public void updateRecommendState(List list)
	 {
		 this.showinfoDao.updateRecommendState(list);
	 }
	 public String insertGetPk(Showinfo t,List objList){
			return this.showinfoDao.insertGetPk(t, objList);
		}
		
	public void update(Showinfo t,List objList,String id){
		this.showinfoDao.update(t,objList,id);
	}
}
