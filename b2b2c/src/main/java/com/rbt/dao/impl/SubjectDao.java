/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: SubjectDao.java 
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
import com.rbt.dao.ISubjectDao;
import com.rbt.model.Infoattr;
import com.rbt.model.Member;
import com.rbt.model.Resume;
import com.rbt.model.Showinfo;
import com.rbt.model.Subject;

/**
 * @function 功能 专题信息dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:19:02 CST 2011
 */
@Repository
public class SubjectDao extends GenericDao<Subject, String> implements
		ISubjectDao {

	@Autowired
	private IInfoattrDao infoattrDao;
	public SubjectDao() {
		super(Subject.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.ISubjectDao#updateSubjectState(com.rbt.model.Subject)
	 */
	public void updateIsrecom(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if (list != null && list.size() > 0) {
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap) iter.next();
						executor.update("subject.updateisrecom", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.ISubjectDao#getWebSubjectCount(java.util.Map)
	 */
	public int getWebSubjectCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"subject.getWebSubjectCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rbt.dao.ISubjectDao#getWebSubjectList(java.util.Map)
	 */
	public List getWebSubjectList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(
				"subject.getWebSubjectList", map);
	}
	
	
	public String insertGetPk(Subject t, List objList) {
		if(objList != null && objList.size()>0){
			for(int i = 0;i<objList.size();i++){
				this.infoattrDao.insert((Infoattr) objList.get(i));
			}
		}
		return super.insertGetPk(t);
	}

	public void update(Subject t, List objList, String id) {
		if(id !=null && "".equals(id)){
			this.infoattrDao.delete(id);
		}
		if(objList != null && objList.size()>0){
			for(int i= 0;i<objList.size();i++){
				this.infoattrDao.insert((Infoattr) objList.get(i));
			}
		}
		super.update(t);
	}
	

	
	
	@Override
	public void delete(String id) {
		super.delete(id);
		String[] ids=id.split(",");
		for(int i=0;i<ids.length;i++){
			Subject subject = super.get(ids[i].trim());
			if(subject!=null&&subject.getInfoattr_id()!=null&&!subject.getInfoattr_id().equals("")){
				String infoattr_id = subject.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}

}
