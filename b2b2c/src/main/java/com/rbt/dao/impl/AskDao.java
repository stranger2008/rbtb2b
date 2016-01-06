/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.dao.impl
 * FileName: AskDao.java 
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
import com.rbt.dao.IAskDao;
import com.rbt.dao.IInfoattrDao;
import com.rbt.model.Ask;
import com.rbt.model.Infoattr;
import com.rbt.model.Supply;

/**
 * @function 功能  问题dao层业务接口实现类
 * @author 创建人 邱景岩
 * @date 创建日期 Mon Jul 25 13:25:38 CST 2011
 */
@Repository
public class AskDao extends GenericDao<Ask,String> implements IAskDao {
	@Autowired
	private IInfoattrDao infoattrDao;
	
	public AskDao() {
		super(Ask.class);
	}

	/*
	 * (non-Javadoc)
	 * @see com.rbt.dao.IAskDao#updateAskState(java.util.Map)
	 */
	public void updateAskState(Map map) {
		this.getSqlMapClientTemplate().update("ask.updateState", map);
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.IAskDao#getWebAskCount(java.util.Map)
	 */
	public int getWebAskCount(Map map) {
		Map infoMap = (Map) this.getSqlMapClientTemplate().queryForObject(
				"ask.getWebAskCount", map);
		return (infoMap != null && infoMap.get("ct") != null) ? Integer
				.parseInt(infoMap.get("ct").toString()) : 0;
	}

	/* (non-Javadoc)
	 * @see com.rbt.dao.IAskDao#getWebAskList(java.util.Map)
	 */
	public List getWebAskList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("ask.getWebAskList",
				map);
	}
	public void updateClickNum(String pk)
    {
      this.getSqlMapClientTemplate().update("ask.updateclicknum", pk);
    }
	/**
	 * 方法描述：批量修改
	 * @param pk
	 * @return java.util.Map
	 */
	public void updateIsrecom(final List list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				Map<String, Object> temp = new HashMap<String, Object>();
				executor.startBatch();
				if(list!=null && list.size()>0){
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						temp = (HashMap)iter.next();
						executor.update("ask.updateisrecom", temp);
					}
				}
				executor.executeBatch();
				return null;
			}
		});

	}

	public String insertGetPk(Ask t, List objList) {
		if(objList != null && objList.size()>0){
			for(int i = 0;i<objList.size();i++){
				this.infoattrDao.insert((Infoattr) objList.get(i));
			}
		}
		return super.insertGetPk(t);
	}

	public void update(Ask t, List objList, String id) {
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

	/*
	 * 重写delete方法（重写：子类继承父类中）
	 * @see com.rbt.dao.impl.GenericDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(String id) {
		super.delete(id);
		String[] ids=id.split(",");
		for(int i=0;i<ids.length;i++){
			Ask ask = super.get(ids[i].trim());
			if(ask!=null&&ask.getInfoattr_id()!=null&&!ask.getInfoattr_id().equals("")){
				String infoattr_id = ask.getInfoattr_id();
				this.infoattrDao.delete(infoattr_id);
			}			
		}		
	}
}

