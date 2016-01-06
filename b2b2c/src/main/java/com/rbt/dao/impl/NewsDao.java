/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: NewsDao.java 
 */

package com.rbt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rbt.dao.IInfoattrDao;
import com.rbt.dao.INewsDao;
import com.rbt.model.Brand;
import com.rbt.model.Infoattr;
import com.rbt.model.Job;
import com.rbt.model.News;
/**
 * @function 功能 资讯管理dao层接口实现
 * @author  创建人 胡惜坤
 * @date  创建日期  July 8, 2011
 */
@Repository
public class NewsDao extends GenericDao<News,String> implements INewsDao {

	
	@Autowired
	private IInfoattrDao infoattrDao;
	
	public NewsDao() {
		super(News.class);
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.dao.INewsDao#getNewsList(java.util.Map)
	 */
	public List getMemberNewsList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("news.getMemberList", map);
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.dao.INewsDao#deleteInRecycle(java.util.Map)
	 */
    public  void deleteNewsInRecycle(String pk)
    {
    	this.getSqlMapClientTemplate().delete("news.delete", pk);
    }
    /**
	 * 
	 * @param map
	 */
	public void updateState(Map map) {
		this.getSqlMapClientTemplate().update("news.updatestate", map);
	}
	public void updateClickNum(String pk)
    {
      this.getSqlMapClientTemplate().update("news.updateclicknum", pk);
    }

	/* (non-Javadoc)
	 * @see com.rbt.dao.INewsDao#getWebNewsCount(java.util.Map)
	 */
	public int getWebNewsCount(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("news.getWebNewsCount", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.INewsDao#getWebNewsList(java.util.Map)
	 */
	public List getWebNewsList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("news.getWebNewsList", map);
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.dao.INewsDao#getInfoCount(java.util.Map)
	 */
	public int getInfoCount(Map map) {
		Map infoMap = (Map)this.getSqlMapClientTemplate().queryForObject("news.getcountnum", map);
		return (infoMap!=null && infoMap.get("ct")!=null)?Integer.parseInt(infoMap.get("ct").toString()):0;
	}
	/*
	 * 重载InsertGetPk方法（重载：方法名相同，参数不同）
	 * @see com.rbt.dao.impl.GenericDao#insertGetPk(java.lang.Object)
	 */
	public String insertGetPk(News t,List objList) {
		if(objList != null && objList.size()>0){
			for(int i = 0;i<objList.size();i++){
				this.infoattrDao.insert((Infoattr) objList.get(i));
			}
		}
		return super.insertGetPk(t);
	}
	
	/*
	 * 重载update方法（重载：方法名相同，参数不同）
	 * @see com.rbt.dao.impl.GenericDao#update(java.lang.Object)
	 */
	public void update(News t,List objList,String infoid){
		if(infoid !=null && !"".equals(infoid)){
			this.infoattrDao.delete(infoid);
		}
		if(objList != null && objList.size()>0){
			for(int i= 0;i<objList.size();i++){
				this.infoattrDao.insert((Infoattr) objList.get(i));
			}
		}
		super.update(t);
	}
	/*
	 * 重写delete方法（重写：子类继承父类中）
	 * @see com.rbt.dao.impl.GenericDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(String id) {
		super.delete(id);
		String[] ids=id.split(",");
		for(int i=0;i<ids.length;i++){
			News news = super.get(ids[i].trim());
			if(news!=null&&news.getInfoattr_id()!=null&&!news.getInfoattr_id().equals("")){
				String infoattr_id = news.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}
	@SuppressWarnings("unchecked")
	public List getNewsalumniList(Map map){
		return this.getSqlMapClientTemplate().queryForList("news.getNewsalumniList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List getalumnicharList(Map map){
		return this.getSqlMapClientTemplate().queryForList("news.getalumnicharList", map);
	}

}
