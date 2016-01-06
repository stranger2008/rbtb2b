/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: ExhibitionDao.java 
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
import com.rbt.dao.IShowinfoDao;
import com.rbt.model.Buy;
import com.rbt.model.Infoattr;
import com.rbt.model.Job;
import com.rbt.model.Resume;
import com.rbt.model.Showinfo;

/**
 * @function 功能  展会表dao层业务接口实现类
 * @author 创建人 胡惜坤
 * @date 创建日期 Thu Jul 28 09:17:39 CST 2011
 */
@Repository
public class ShowinfoDao extends GenericDao<Showinfo,String> implements IShowinfoDao {

	
	@Autowired
	private IInfoattrDao infoattrDao;
	
	public ShowinfoDao() {
		super(Showinfo.class);
	}
	 /**
	 * 
	 * @param map
	 */
	public void updateState(Map map) {
		this.getSqlMapClientTemplate().update("showinfo.updatestate", map);
	}
	/*
	 * (non-Javadoc)
	 * @see com.rbt.dao.IShowinfoDao#updateClickNum(java.lang.String)
	 */
	public void updateClickNum(String pk)
    {
      this.getSqlMapClientTemplate().update("showinfo.updateclicknum", pk);
    }

	/* (non-Javadoc)
	 * @see com.rbt.dao.IShowinfoDao#getWebShowinfoCount(java.util.Map)
	 */
	public int getWebShowinfoCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"showinfo.getWebShowinfoCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.IShowinfoDao#getWebShowinfoList(java.util.Map)
	 */
	public List getWebShowinfoList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("showinfo.getWebShowinfoList",
				map);
	}
	/*
	 * 
	 */
	public void updateRecommendState(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("showinfo.updaterecommend", temp);
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
	public String insertGetPk(Showinfo t,List objList) {
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
	public void update(Showinfo t,List objList,String infoid){
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
			Showinfo showinfo = super.get(ids[i].trim());
			if(showinfo!=null&&showinfo.getInfoattr_id()!=null&&!showinfo.getInfoattr_id().equals("")){
				String infoattr_id = showinfo.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}
}

