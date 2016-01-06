/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: ResumeDao.java 
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
import com.rbt.dao.IResumeDao;
import com.rbt.model.Aboutus;
import com.rbt.model.Infoattr;
import com.rbt.model.Job;
import com.rbt.model.Product;
import com.rbt.model.Resume;

/**
 * @function 功能  简历信息dao层业务接口实现类
 * @author 创建人 胡惜坤
 * @date 创建日期 Wed Jul 13 16:14:17 CST 2011
 */
@Repository
public class ResumeDao extends GenericDao<Resume,String> implements IResumeDao {

	
	
	@Autowired
	private IInfoattrDao infoattrDao;
	
	public ResumeDao() {
		super(Resume.class);
	}
	/**
	 * 
	 * @param map
	 */
	public void updateState(Map map) {
		this.getSqlMapClientTemplate().update("resume.updatestate", map);
	}
	public void updateClickNum(String pk)
    {
      this.getSqlMapClientTemplate().update("resume.updateclicknum", pk);
    }

	/* (non-Javadoc)
	 * @see com.rbt.dao.IResumeDao#getWebResumeCount(java.util.Map)
	 */
	public int getWebResumeCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"resume.getWebResumeCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.IResumeDao#getWebResumeList(java.util.Map)
	 */
	public List getWebResumeList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("resume.getWebResumeList",
				map);
	}
	public void updateRecommendState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("resume.updaterecommend", temp);
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
	public String insertGetPk(Resume t,List objList) {
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
	public void update(Resume t,List objList,String infoid){
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
			Resume resume = super.get(ids[i].trim());
			if(resume!=null&&resume.getInfoattr_id()!=null&&!resume.getInfoattr_id().equals("")){
				String infoattr_id = resume.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}

}

