/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: JobDao.java 
 */
package com.rbt.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.rbt.dao.IInfoattrDao;
import com.rbt.dao.IJobDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Gallery;
import com.rbt.model.Infoattr;
import com.rbt.model.Job;
import com.rbt.model.Product;
import com.rbt.model.Showinfo;

/**
 * @function 功能 招聘信息dao层业务接口实现类
 * @author 创建人 胡惜坤
 * @date 创建日期 Tue Jul 12 15:29:27 CST 2011
 */
@Repository
public class JobDao extends GenericDao<Job,String> implements IJobDao {
	@Autowired
	private IInfoattrDao infoattrDao;
	public JobDao() {
		super(Job.class);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IJobDao#getJobMemberCount(java.util.Map)
	 */
	public int getJobMemberCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"job.getMemberCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.IJobDao#getJobMemberList(java.util.Map)
	 */
	public List getJobMemberList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("job.getMemberList", map);
	}

	/**
	 * 
	 * @param map
	 */
	public void updateState(Map map) {
		this.getSqlMapClientTemplate().update("job.updatestate", map);
	}
	public void updateClickNum(String pk)
    {
      this.getSqlMapClientTemplate().update("job.updateclicknum", pk);
    }

	/* (non-Javadoc)
	 * @see com.rbt.dao.IJobDao#getWebJobCount(java.util.Map)
	 */
	public int getWebJobCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"job.getWebJobCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.IJobDao#getWebJobList(java.util.Map)
	 */
	public List getWebJobList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("job.getWebJobList", map);
	}
	public void updateRecommendState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("job.updaterecommend", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});

	}
	
	/*
	 * 重载InsertGetPk方法（重载：方法名相同，参数不同）
	 * @see com.rbt.dao.impl.GenericDao#insertGetPk(java.lang.Object)
	 */
	public String insertGetPk(Job t,List objList) {
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
	public void update(Job t,List objList,String infoid){
		if(infoid !=null && "".equals(infoid)){
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
			Job job = super.get(ids[i].trim());
			if(job!=null&&job.getInfoattr_id()!=null&&!job.getInfoattr_id().equals("")){
				String infoattr_id = job.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}
}
